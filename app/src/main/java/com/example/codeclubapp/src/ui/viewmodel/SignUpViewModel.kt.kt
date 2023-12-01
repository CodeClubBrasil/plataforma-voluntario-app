package com.example.codeclubapp.src.ui.viewmodel

import android.graphics.Bitmap
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.codeclubapp.src.db.entities.RoomUser
import com.example.codeclubapp.src.db.user.UserDaoImpl
import com.example.codeclubapp.src.retrofit.WebClient
import com.example.codeclubapp.src.retrofit.dto.user.CreateUser
import com.example.codeclubapp.src.retrofit.dto.user.UserOutput
import com.example.codeclubapp.src.retrofit.user.UserAPI
import com.example.codeclubapp.src.utils.CCUtils
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SignUpViewModel(private val userDaoImpl: UserDaoImpl, private val appRetrofit: WebClient) :
    ViewModel() {

    private var _byteArrayImg = MutableLiveData<ByteArray>()
    val byteArrayImg: LiveData<ByteArray> = _byteArrayImg

    private var _createUserSuccess = MutableLiveData<Boolean?>()
    val createUserSuccess: LiveData<Boolean?> = _createUserSuccess

    private var _userOutput = MutableLiveData<UserOutput?>()
    val userOutput: LiveData<UserOutput?> = _userOutput

    fun insertNewUserOnRoomDB(roomUser: RoomUser) {
        CoroutineScope(Dispatchers.IO).launch {
            userDaoImpl.insertNewUser(roomUser)
        }
    }

    fun createNewUserAPI(createUser: UserAPI) {
        CoroutineScope(Dispatchers.IO).launch {
            appRetrofit.insertUser(createUser).also {
                _createUserSuccess.postValue(it.first)
                _userOutput.postValue(it.second)
            }

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