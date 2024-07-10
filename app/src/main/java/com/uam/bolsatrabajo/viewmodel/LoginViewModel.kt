package com.uam.bolsatrabajo.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.uam.bolsatrabajo.model.LoginRequestDTO
import com.uam.bolsatrabajo.model.LoginResponseDTO
import com.uam.bolsatrabajo.remote.RetrofitClient
import com.uam.bolsatrabajo.remote.UsuarioApi
import com.uam.bolsatrabajo.util.SessionManager
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginViewModel( val sessionManager: SessionManager) : ViewModel() {
    private val retrofit = RetrofitClient.getClient()
    private val usuarioApi: UsuarioApi? = retrofit.create(UsuarioApi::class.java)
    private val _loginResult = MutableLiveData<LoginResponseDTO?>()
    val loginResult: LiveData<LoginResponseDTO?> get() = _loginResult

    fun login(cif: String, contrasena: String) {
        val loginRequest = LoginRequestDTO(cif, contrasena)

        usuarioApi?.loginUser(loginRequest)?.enqueue(object : Callback<LoginResponseDTO> {
            override fun onResponse(call: Call<LoginResponseDTO>, response: Response<LoginResponseDTO>) {
                if (response.isSuccessful) {
                    val loginResponse = response.body()
                    if (loginResponse != null && loginResponse.success) {
                        viewModelScope.launch {
                            sessionManager.saveAuthToken(loginResponse.userId.toString())
                            sessionManager.saveUserName(loginResponse.userName ?: "") // Guarda el nombre del usuario
                        }
                        _loginResult.value = loginResponse
                    } else {
                        val errorMessage = loginResponse?.message ?: "Error en el inicio de sesión"
                        Log.e("Login", "Error en el inicio de sesión: $errorMessage")
                        _loginResult.value = LoginResponseDTO(success = false, message = errorMessage)
                    }
                } else {
                    val errorBody = response.errorBody()?.string() ?: "Error desconocido"
                    Log.e("Login", "Error en el inicio de sesión: $errorBody")
                    _loginResult.value = LoginResponseDTO(success = false, message = "Error en el inicio de sesión")
                }
            }

            override fun onFailure(call: Call<LoginResponseDTO>, t: Throwable) {
                Log.e("Login", "Error en el inicio de sesión: ${t.message}")
                _loginResult.value = LoginResponseDTO(success = false, message = "Error en el inicio de sesión: ${t.message}")
            }
        })
    }

    fun logout() {
        sessionManager.clearAuthToken()
        sessionManager.clearUserName() // Limpia el nombre del usuario
    }
}
