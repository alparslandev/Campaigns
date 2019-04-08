package com.campaigns.utils

import com.campaigns.App
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

        fun remainingTimeToHumanReadableForm(milliseconds: Long): String {
            val seconds = (milliseconds / 1000) % 60
            val minutes = (milliseconds / (1000 * 60) % 60)
            val hours = (milliseconds / (1000 * 60 * 60) % 24)
            val days = (milliseconds / (60 * 60 * 24 * 1000))

            val sb = StringBuilder()
            if (days > 0) buildString(sb, days, App.str(R.string.string_day))
            if (hours > 0) buildString(sb, hours, App.str(R.string.string_hour))
            if (minutes > 0) buildString(sb, minutes, App.str(R.string.string_minutes))
            if (seconds > 0) buildString(sb, seconds, App.str(R.string.string_seconds))
            return sb.toString()
        }

        private fun buildString(sb : StringBuilder, model: Long, text: String): StringBuilder{
            sb.append(model)
            sb.append(seperator)
            sb.append(text)
            sb.append(seperator)
            return sb
        }
    }
}