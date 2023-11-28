package com.example.codeclubapp.src.utils

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Bitmap.CompressFormat
import android.graphics.BitmapFactory
import android.net.ConnectivityManager
import android.net.NetworkInfo
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
            stream.close()
            return stream.toByteArray()
        }

        // convert byte array to bitmap
        fun getImageFromByteArray(bytes:ByteArray) : Bitmap {
            return BitmapFactory.decodeByteArray(bytes,0, bytes.size)
        }

        fun checkConnectivity(context: Context): Boolean {
            val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val activeNetwork: NetworkInfo? = cm.activeNetworkInfo
            return activeNetwork?.isConnectedOrConnecting == true
        }
    }
}