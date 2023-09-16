package com.example.codeclubapp.src.utils

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Bitmap.CompressFormat
import android.graphics.BitmapFactory
import android.widget.Toast
import java.io.ByteArrayOutputStream


class CCUtils {
    companion object {

        fun showToast(context: Context, message: String?) {
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
        }

        fun encodePassword(password: String?): String? {
            //todo: encode password
            return password
        }

        // convert from bitmap to byte array
        fun getBytesFromImage(bitmap: Bitmap): ByteArray {
            val stream = ByteArrayOutputStream()
            bitmap.compress(CompressFormat.PNG, 0, stream)
            return stream.toByteArray()
        }

        // convert byte array to bitmap
        suspend fun getImageFromByteArray(bytes:ByteArray) : Bitmap {
            return BitmapFactory.decodeByteArray(bytes,0, bytes.size)
        }
    }
}