package com.campaigns

import android.view.View
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar

abstract class BaseActivity : AppCompatActivity() {

    private var errorSnackbar: Snackbar? = null

    fun showError(@StringRes errorMessage:Int, listener: View.OnClickListener, view: View){
        errorSnackbar = Snackbar.make(view, errorMessage, Snackbar.LENGTH_INDEFINITE)
        errorSnackbar?.setAction(R.string.retry, listener)
        errorSnackbar?.show()
    }

    fun hideError(){
        errorSnackbar?.dismiss()
    }
}