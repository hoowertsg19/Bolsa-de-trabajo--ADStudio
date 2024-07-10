package com.uam.bolsatrabajo.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.uam.bolsatrabajo.model.RegisterUserDTO
import com.uam.bolsatrabajo.remote.RetrofitClient
import com.uam.bolsatrabajo.remote.UsuarioApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegistroViewModel : ViewModel() {
    var nombre: String by mutableStateOf("")
    var apellido: String by mutableStateOf("")
    var tipoUsuario: String by mutableStateOf("Estudiante")
    var cif: String by mutableStateOf("")
    var carrera: String by mutableStateOf("")
    var anioGraduacion: String by mutableStateOf("")
    var sexo: String by mutableStateOf("")
    var correoElectronico: String by mutableStateOf("")
    var contrasena: String by mutableStateOf("")

    private val retrofit = RetrofitClient.getClient()
    private val usuarioApi: UsuarioApi? = retrofit.create(UsuarioApi::class.java)
    private val _registroResult = MutableLiveData<String>()
    val registroResult: LiveData<String> get() = _registroResult

    fun registrarUsuario() {
        // Log inputs for debugging
        Log.d("RegistroViewModel", "Datos antes de registro - Nombre: $nombre, Apellido: $apellido, TipoUsuario: $tipoUsuario, CIF: $cif, Carrera: $carrera, AnioGraduacion: $anioGraduacion, Sexo: $sexo, Correo: $correoElectronico, Contrasena: $contrasena")

        if (carrera.isEmpty() || anioGraduacion.isEmpty() || sexo.isEmpty() || nombre.isEmpty() ||
            apellido.isEmpty() || tipoUsuario.isEmpty() || cif.isEmpty() || correoElectronico.isEmpty() ||
            contrasena.isEmpty()) {
            Log.e("Registro", "Campos vacíos detectados")
            Log.e("Registro", "nombre: $nombre, apellido: $apellido, tipoUsuario: $tipoUsuario, cif: $cif, carrera: $carrera, anioGraduacion: $anioGraduacion, sexo: $sexo, correoElectronico: $correoElectronico, contrasena: $contrasena")
            _registroResult.value = "Error: Campos vacíos"
            return
        }

        val registerUserDTO = RegisterUserDTO(
            nombre = nombre,
            apellido = apellido,
            correoElectronico = correoElectronico,
            contrasena = contrasena,
            tipoUsuario = tipoUsuario,
            cif = cif,
            carrera = carrera,
            anioGraduacion = anioGraduacion.toIntOrNull(),
            sexo = sexo
        )

        Log.d("RegistroViewModel", "Datos del usuario a registrar: $registerUserDTO")

        usuarioApi?.registrarUsuario(registerUserDTO)?.enqueue(object : Callback<String?> {
            override fun onResponse(call: Call<String?>, response: Response<String?>) {
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    Log.d("Registro", "Registro exitoso: $responseBody")
                    _registroResult.value = "Registro exitoso"
                } else {
                    val errorBody = response.errorBody()?.string()
                    Log.e("Registro", "Error en el registro: $errorBody")
                    _registroResult.value = "Error en el registro"
                }
            }

            override fun onFailure(call: Call<String?>, t: Throwable) {
                Log.e("Registro", "Error en el registro: ${t.message}")
                _registroResult.value = "Error en el registro: ${t.message}"
            }
        })
    }
}
