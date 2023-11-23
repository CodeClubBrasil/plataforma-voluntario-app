package com.example.codeclubapp.src.ui.viewmodel

import android.graphics.Bitmap
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.codeclubapp.src.classesModelos.user.UserCodeClub
import com.example.codeclubapp.src.db.user.UserDaoImpl
import com.example.codeclubapp.src.retrofit.AppRetrofit
import com.example.codeclubapp.src.retrofit.dto.user.CreateUser
import com.example.codeclubapp.src.retrofit.dto.user.UserOutput
import com.example.codeclubapp.src.utils.CCUtils
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SignUpViewModel(private val userDaoImpl: UserDaoImpl, private val appRetrofit: AppRetrofit) :
    ViewModel() {

    private var _byteArrayImg = MutableLiveData<ByteArray>()
    val byteArrayImg: LiveData<ByteArray> = _byteArrayImg

    private var _createUserSuccess = MutableLiveData<Boolean>()
    val createUserSuccess: LiveData<Boolean> = _createUserSuccess

    private var _userOutput = MutableLiveData<UserOutput>()
    val userOutput: LiveData<UserOutput> = _userOutput

    fun insertNewUser(userCodeClub: UserCodeClub) {
        CoroutineScope(Dispatchers.IO).launch {
            userDaoImpl.insertNewUser(userCodeClub)
        }
    }

    fun createNewUserAPI(createUser: CreateUser) {
        CoroutineScope(Dispatchers.IO).launch {
            val result = appRetrofit.Service.createUser(createUser)
            _createUserSuccess.postValue(result.isSuccessful)
            _userOutput.postValue(result.body())
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