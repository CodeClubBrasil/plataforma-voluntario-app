package com.example.codeclubapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import com.example.codeclubapp.databinding.ActivitySignUp01Binding
import com.example.codeclubapp.src.db.entities.RoomAvaiableTime
import com.example.codeclubapp.src.retrofit.dto.DAYS
import com.example.codeclubapp.src.db.entities.RoomUser
import com.example.codeclubapp.src.retrofit.user.AvailableTime
import com.example.codeclubapp.src.retrofit.user.UserAPI
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
        var username = ""

        //Botão CADASTRAR clicado
        binding.btnContinueToSignUp02.setOnClickListener {

            //Atualizando variáveis para o que foi digitado pelo usuário
            name = binding.editTextNome.text.toString().trim()
            lastName = binding.editTextSobrenome.text.toString().trim()
            city = binding.editTextCidade.text.toString().trim()
            neighborhood = binding.editTextBairro.text.toString().trim()
            tels = binding.editTextTelefone.text.toString().trim()
            knowledges = binding.editTextKnowledge.text.toString().trim()
            email = binding.editTextEmail.text.toString().trim()
            password = binding.editTextSenha.text.toString().trim()
            username = binding.editTextNickname.text.toString().trim()

            val availableTime = generateAvailableTime()
            val roomAvaiableTime = roomAvailableTime(availableTime)
            val knowledgesList = CCUtils.stringToListOfString(knowledges)
            val phones = CCUtils.stringToListOfString(tels)

            val userApi = UserAPI(
                true, availableTime,
                city, LocalDateTime.now().toString(),
                email, knowledgesList,
                lastName, name, neighborhood,
                password, "PA", phones,
                username
            )

            validaFormulario(
                name,
                lastName,
                password,
                city,
                stateSelected,
                neighborhood,
                tels,
                email,
                knowledges
            )

            if (isConnectedOnNetwork) {
                creatingUserOnApi(userApi, roomAvaiableTime, knowledges, tels)
            } else {
                saveUserOnDatabase(
                    userApi.active, roomAvaiableTime,
                    userApi.city, userApi.created_at, userApi.email,
                    knowledges, userApi.last_name, userApi.name,
                    userApi.neighborhood, userApi.password, userApi.state,
                    tels, userApi.created_at, userApi.name, true
                )
            }

            startActivity(Intent(this, SingUp02::class.java))

        }

        val entrar = binding.botaoEntrar
        entrar.setOnClickListener {
            startActivity(Intent(this, Login::class.java))
        }
    }

    private fun creatingUserOnApi(
        createUser: UserAPI,
        roomAvaiableTime: RoomAvaiableTime,
        knowledges: String,
        tels: String
    ) {
        signUpViewModel.createNewUserAPI(createUser).also {
            signUpViewModel.createUserSuccess.observe(this@SignUp01) { success ->
                if (!success!!) {
                    CCUtils.showToast(this@SignUp01, "Falha ao cadastrar usuário")
                    Log.i(TAG, "Falha ao cadastrar usuario")
                } else {
                    signUpViewModel.userOutput.observe(this@SignUp01) { _ ->
                        saveUserOnDatabase(
                            createUser.active, roomAvaiableTime,
                            createUser.city, createUser.created_at, createUser.email,
                            knowledges, createUser.last_name, createUser.name,
                            createUser.neighborhood, createUser.password, createUser.state,
                            tels, createUser.created_at, createUser.name, true
                        )
                    }
                }
            }
        }
    }

    private fun roomAvailableTime(availableTime: List<AvailableTime>): RoomAvaiableTime {
        return RoomAvaiableTime(
            availableTime[0].time_end,
            availableTime[0].time_start,
            availableTime[0].week_day,
        )
    }


    private fun generateAvailableTime(): List<AvailableTime> {
        val availableTime = listOf<AvailableTime>(
            AvailableTime(
                "2023-12-01T22:25:23.069Z",
                "2023-12-01T22:25:23.069Z",
                DAYS.MONDAY.name,
            )
        )
        return availableTime
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
            123, active, city, createdAt, email, knowLedges, lastName,
            neighborhood, password, state, telephone, updatedAt, userName, name, availableTime,
            needToBePersisted, false
        )
        signUpViewModel.insertNewUserOnRoomDB(user).also {
            Log.i(TAG, "Usuario salvo Room")
        }
    }


}