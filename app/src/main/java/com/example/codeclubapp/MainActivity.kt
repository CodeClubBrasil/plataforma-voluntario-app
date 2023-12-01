package com.example.codeclubapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.codeclubapp.databinding.ActivityMainBinding
import com.example.codeclubapp.src.ui.viewmodel.MainActivityViewModel
import com.example.codeclubapp.src.utils.CCUtils
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val mainActivityViewModel: MainActivityViewModel by viewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val isConnectedToNetwork = CCUtils.checkConnectivity(this)

        if (isConnectedToNetwork) checkIfUserHasToBePersistedOnAPIAndDoItIfItIs()

        mainActivityViewModel.userLogged.observe(this@MainActivity) { userLogged ->
            if (userLogged) startActivity(Intent(this, Logado::class.java))
        }

        /*configuração view binding*/
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        /* indo para cadastro */
        binding.btnSignUp.setOnClickListener {
            startActivity(Intent(this, SignUp01::class.java))
        }

        binding.botaoEntrar.setOnClickListener {
            startActivity(Intent(this, Login::class.java))
        }
    }

    private fun checkIfUserHasToBePersistedOnAPIAndDoItIfItIs() {
        mainActivityViewModel.persistUserIfConnectiveIsOkAndHasToBePersisted()
    }
}