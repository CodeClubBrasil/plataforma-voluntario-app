package com.example.codeclubapp.src.utils

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.Toast
import com.example.codeclubapp.TAG
import org.w3c.dom.Text

class CC_Utils {
    companion object {

        fun showToast(context: Context, message: String?) {
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
        }

        fun encodePassword(password: String?): String? {
            //todo: encode password
            val passwordEncoded = password
            return passwordEncoded
        }
    }
}