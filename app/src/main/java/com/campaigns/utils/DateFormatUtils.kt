package com.campaigns.utils

import android.content.Context
import androidx.annotation.StringRes
import com.campaigns.R
import java.text.SimpleDateFormat
import java.util.*

class DateFormatUtils {
    companion object { // 2019-04-16T23:27:00+03:00
        val seperator = " "
        val dateFormat = SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ssz", Locale.ENGLISH)

        fun toDate(text: String): Date {
            return dateFormat.parse(text) ?: Date()
        }

        fun remainingTimeToHumanReadableForm(milliseconds: Long, context: Context): String {
            val seconds = (milliseconds / 1000) % 60
            val minutes = (milliseconds / (1000 * 60) % 60)
            val hours = (milliseconds / (1000 * 60 * 60) % 24)
            val days = (milliseconds / (60 * 60 * 24 * 1000))

            val sb = StringBuilder()
            if (days > 0) buildString(sb, days, R.string.string_day, context)
            if (hours > 0) buildString(sb, hours, R.string.string_hour, context)
            if (minutes > 0) buildString(sb, minutes, R.string.string_minutes, context)
            if (seconds > 0) buildString(sb, seconds, R.string.string_seconds, context)
            return sb.toString()
        }

        private fun buildString(sb : StringBuilder, model: Long, @StringRes id: Int, context: Context): StringBuilder{
            sb.append(model)
            sb.append(seperator)
            sb.append(context.getString(id))
            sb.append(seperator)
            return sb
        }
    }
}