package com.example.codeclubapp

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.example.codeclubapp.databinding.ActivitySignUp01Binding
import com.example.codeclubapp.src.db.entities.RoomAvaiableTime
import com.example.codeclubapp.src.retrofit.dto.DAYS
import com.example.codeclubapp.src.db.entities.RoomUser
import com.example.codeclubapp.src.retrofit.dto.user.AvailableTime
import com.example.codeclubapp.src.retrofit.dto.user.CreateUser
import com.example.codeclubapp.src.ui.viewmodel.SignUpViewModel
import com.example.codeclubapp.src.utils.CCUtils
import com.example.codeclubapp.src.utils.ValidateFields
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.time.LocalDateTime

private const val TAG = "SignUp01"

class SignUp01 : AppCompatActivity() {


    private val signUpViewModel: SignUpViewModel by viewModel()
    private lateinit var binding: ActivitySignUp01Binding
    private var byteArrayImg: ByteArray? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUp01Binding.inflate(layoutInflater)
        setContentView(binding.root)
        val isConnectedOnNetwork = CCUtils.checkConnectivity(this)

        //Lidando com o array de Estados
        val stateSpinner = binding.stateSpinner
        val estados = resources.getStringArray(R.array.siglasEstados)
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, estados)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        stateSpinner.adapter = adapter

        var stateSelected = " "
        //Definindo qual estado foi selecionado pelo usuário
        stateSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val state = estados[position]
                stateSelected = state
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }

        //Declarando as variáveis de cada campo
        var name = ""
        var lastName = ""
        var city = ""
        var neighborhood = ""
        var tels = ""
        var knowledges = ""
        var email = ""
        var password = ""

        //Botão CADASTRAR clicado
        binding.btnContinueToSignUp02.setOnClickListener {
            //Atualizando variáveis para o que foi digitado pelo usuário
            name = binding.editTextNome.text.toString()
            lastName = binding.editTextSobrenome.text.toString()
            city = binding.editTextCidade.text.toString()
            neighborhood = binding.editTextBairro.text.toString()
            tels = binding.editTextTelefone.text.toString()
            knowledges = binding.editTextKnowledge.text.toString()
            email = binding.editTextEmail.text.toString()
            password = binding.editTextSenha.text.toString()

            val (roomAvaiableTime, createUser) = creatingUser(
                knowledges,
                tels,
                city,
                email,
                lastName,
                name,
                neighborhood,
                password,
                stateSelected
            )

            if (isConnectedOnNetwork) {
                creatingUserOnApi(createUser)
            }
            saveUserOnDatabase(
                createUser.active, roomAvaiableTime,
                createUser.city, createUser.created_at, createUser.email,
                knowledges, createUser.last_name, createUser.name,
                createUser.neighborhood, createUser.password, createUser.state,
                tels, createUser.created_at, createUser.name, !isConnectedOnNetwork
            )
        }

        val entrar = binding.botaoEntrar
        entrar.setOnClickListener {
            startActivity(Intent(this, Login::class.java))
        }
    }

    private fun creatingUserOnApi(
        createUser: CreateUser
    ) {
        signUpViewModel.createNewUserAPI(createUser).also {
            signUpViewModel.createUserSuccess.observe(this@SignUp01) { success ->
                if (!success) {
                    CCUtils.showToast(this@SignUp01, "Falha ao cadastrar usuário")
                    Log.i(TAG, "Falha ao salvar usuario")
                } else {
                    signUpViewModel.userOutput.observe(this@SignUp01) { _ ->
                        CCUtils.showToast(this@SignUp01, "Usuário cadastrado com sucesso!")
                        Log.i(TAG, "Usuario salvo API")
                    }
                }
            }
        }
    }

    private fun creatingUser(
        knowledges: String,
        tels: String,
        city: String,
        email: String,
        lastName: String,
        name: String,
        neighborhood: String,
        password: String,
        stateSelected: String
    ): Pair<RoomAvaiableTime, CreateUser> {
        val availableTime = listOf<AvailableTime>(
            AvailableTime(
                LocalDateTime.now().toString(),
                LocalDateTime.now().toString(),
                DAYS.MONDAY.name,
            )
        )

        val roomAvaiableTime = RoomAvaiableTime(
            availableTime[0].time_end,
            availableTime[0].time_start,
            availableTime[0].week_day,
        )

        val knowledgesList = listOf<String>(
            knowledges
        )


        val phones = listOf<String>(
            tels
        )

        val createUser = CreateUser(
            true, availableTime,
            city, LocalDateTime.now().toString(),
            email, knowledgesList,
            lastName, name, neighborhood,
            password, stateSelected, phones,
            LocalDateTime.now().toString(), name
        )
        return Pair(roomAvaiableTime, createUser)
    }

    private fun validaFormulario(
        name: String,
        lastName: String,
        password: String,
        city: String,
        state: String,
        neighborhood: String,
        tels: String,
        email: String,
        knowledges: String,
    ): Boolean {
        val listFields =
            listOf(name, lastName, password, city, state, neighborhood, tels, email, knowledges)

        val validateFields = ValidateFields()
        val blankField = validateFields.isBlankField(listFields)
        if (blankField) {
            CCUtils.showToast(this@SignUp01, "Preencha corretamente todos os campos")
        }
        return blankField
    }


    private fun saveUserOnDatabase(
        active: Boolean,
        availableTime: RoomAvaiableTime,
        city: String,
        createdAt: String,
        email: String,
        knowLedges: String,
        lastName: String,
        name: String,
        neighborhood: String,
        password: String,
        state: String,
        telephone: String,
        updatedAt: String,
        userName: String,
        needToBePersisted: Boolean
    ) {
        val user = RoomUser(
            0, active, city, createdAt, email, knowLedges, lastName,
            neighborhood, password, state, telephone, updatedAt, userName, name, availableTime,
            needToBePersisted
        )
        signUpViewModel.insertNewUserOnRoomDB(user).also {
            Log.i(TAG, "Usuario salvo Room")
        }
    }


}