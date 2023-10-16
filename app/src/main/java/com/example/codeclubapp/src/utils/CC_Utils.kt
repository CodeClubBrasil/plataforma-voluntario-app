package com.example.codeclubapp.src.utils

import android.content.Context
import android.widget.Toast

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