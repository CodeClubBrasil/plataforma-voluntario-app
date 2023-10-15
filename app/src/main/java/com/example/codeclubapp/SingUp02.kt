package com.example.codeclubapp
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.codeclubapp.databinding.ActivityCadastro2Binding
import com.example.codeclubapp.databinding.ActivitySignUp01Binding
import com.example.codeclubapp.src.ui.viewmodel.SignUpViewModel
import com.squareup.picasso.Picasso
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.koin.androidx.viewmodel.ext.android.viewModel


class SingUp02 : AppCompatActivity() {

    private lateinit var binding: ActivityCadastro2Binding
    private val signUpViewModel: SignUpViewModel by viewModel()
    private var byteArrayImg: ByteArray? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCadastro2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSelectImage.setOnClickListener {
            pickPhoto()
        }
    }
    private fun pickPhoto() {
        val i = Intent()
        i.setType("image/**")
        i.setAction(Intent.ACTION_GET_CONTENT)
        startActivityForResult(i, 200)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK) {
            if (requestCode == 200) {
                val selectedUri: Uri? = data?.data
                //setando imagem
                binding.imgProfile.setImageURI(selectedUri)
                //salvando bytearray da imagem
                selectedUri.let {
                    GlobalScope.launch(Dispatchers.IO) {
                        //setando a imagem de URI pra Bitmap
                        val bitmap = Picasso.get().load(it).get()
                        withContext(Dispatchers.Main) {
                            //recuperando o bytearray do bitmap
                            signUpViewModel.getByteArrayFromImg(bitmap).also {
                                //salvando na variavel byteArrayImg o bytearray do bitmap
                                signUpViewModel.byteArrayImg.observe(this@SingUp02){
                                    byteArrayImg = it
                                }
                            }
                        }
                    }
                }
            }
        }
    }

}