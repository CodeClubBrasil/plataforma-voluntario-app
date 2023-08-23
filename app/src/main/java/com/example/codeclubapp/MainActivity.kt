package com.example.codeclubapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.activity.viewModels
import com.example.codeclubapp.databinding.ActivityMainBinding
import com.example.codeclubapp.src.classesModelos.ROOM_MODEL.UserRoom
import com.example.codeclubapp.src.ui.viewmodel.MainActivityViewModel
import com.example.codeclubapp.src.classesModelos.ROOM_MODEL.UsuarioRoomViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    /*
    * Para funcionar o by viewModels() é preciso adicionar a dependencia do fragment ktx
    * */
    private val viewModel: MainActivityViewModel by viewModels()
    private val userRoomViewModel: UsuarioRoomViewModel by viewModels()

    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        /*configuração view binding*/
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)



        viewModel.mostrarTexto()
        viewModel.texto.observe(this) { _text ->
        }

        /*DEVELOPMENT BRANCH */
        val novoBotao = binding.botaoCadastrar

        novoBotao.setOnClickListener {
            /*
            val intent = Intent(this, Cadastro::class.java)
            startActivity(intent)
             */
            CoroutineScope(Dispatchers.IO).launch {
                userRoomViewModel.insertItem(
                    UserRoom(0, "Pedro", "1234")
                )
            }
        }

        val entrar: Button = findViewById(R.id.botaoEntrar)

        entrar.setOnClickListener {
            /*
            val intent = Intent(this, Login::class.java)
            startActivity(intent)
             */
            CoroutineScope(Dispatchers.IO).launch {
                userRoomViewModel.getAllItems().forEach {
                    println(it.username)
                }
            }
        }
    }
}