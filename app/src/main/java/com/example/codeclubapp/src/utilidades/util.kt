package com.example.codeclubapp.src.utilidades

import android.content.Context
import android.widget.Toast

fun mostratToast (context : Context,message : String?){
    Toast.makeText(context, message,Toast.LENGTH_SHORT).show()
}