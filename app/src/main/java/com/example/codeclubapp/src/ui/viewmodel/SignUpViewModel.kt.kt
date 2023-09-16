package com.example.codeclubapp.src.ui.viewmodel

import android.graphics.Bitmap
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.codeclubapp.src.classesModelos.user.UserCodeClub
import com.example.codeclubapp.src.db.user.UserDaoImpl
import com.example.codeclubapp.src.utils.CCUtils
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SignUpViewModel(private val userDaoImpl: UserDaoImpl) : ViewModel() {

    private var _byteArrayImg = MutableLiveData<ByteArray>()
    val byteArrayImg: LiveData<ByteArray> = _byteArrayImg

    fun insertNewUser(userCodeClub: UserCodeClub) {
        CoroutineScope(Dispatchers.IO).launch {
            userDaoImpl.insertNewUser(userCodeClub)
        }
    }

    fun getByteArrayFromImg(bitmap: Bitmap) {
        CCUtils.getBytesFromImage(bitmap).also {
            _byteArrayImg.postValue(it)
        }
    }

    fun getBitmapFromByteArray(byteArray: ByteArray): Bitmap {
        return CCUtils.getImageFromByteArray(byteArray)
    }
}