package com.uam.bolsatrabajo.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.uam.bolsatrabajo.model.CuentaLoginRequestDTO
import com.uam.bolsatrabajo.model.CuentaLoginResponseDTO
import com.uam.bolsatrabajo.remote.EmpresaApi
import com.uam.bolsatrabajo.remote.RetrofitClient
import com.uam.bolsatrabajo.util.SessionManager
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException

class LoginViewModelEmp(private val sessionManager: SessionManager) : ViewModel() {
    private val retrofit = RetrofitClient.getClient()
    private val empresaApi: EmpresaApi? = retrofit.create(EmpresaApi::class.java)
    private val _loginResult = MutableLiveData<CuentaLoginResponseDTO?>()
    val loginResult: LiveData<CuentaLoginResponseDTO?> get() = _loginResult

    fun loginEmpresa(correoElectronico: String, password: String) {
        Log.d("LoginViewModelEmp", "Iniciando loginEmpresa con correo: $correoElectronico")

        val loginRequest = CuentaLoginRequestDTO(correoElectronico, password)

        empresaApi?.loginCuenta(loginRequest)?.enqueue(object : Callback<CuentaLoginResponseDTO> {
            override fun onResponse(call: Call<CuentaLoginResponseDTO>, response: Response<CuentaLoginResponseDTO>) {
                Log.d("LoginViewModelEmp", "onResponse recibido")
                if (response.isSuccessful) {
                    val loginResponse = response.body()
                    Log.d("LoginViewModelEmp", "Respuesta exitosa: $loginResponse")
                    if (loginResponse != null && loginResponse.success) {
                        Log.d("LoginViewModelEmp", "Inicio de sesión exitoso: ${loginResponse.message}")
                        viewModelScope.launch {
                            sessionManager.saveAuthToken(loginResponse.role!!)
                            sessionManager.saveUserName(loginResponse.role)
                        }
                        _loginResult.value = loginResponse
                    } else {
                        val errorMessage = loginResponse?.message ?: "Error en el inicio de sesión"
                        Log.e("LoginViewModelEmp", "Error en el inicio de sesión: $errorMessage")
                        _loginResult.value = CuentaLoginResponseDTO(success = false, message = errorMessage, empresaId = null)
                    }
                } else {
                    val errorBody = response.errorBody()?.string() ?: "Error desconocido"
                    Log.e("LoginViewModelEmp", "Error en el inicio de sesión: $errorBody")
                    _loginResult.value = CuentaLoginResponseDTO(success = false, message = "Error en el inicio de sesión", empresaId = null)
                }
            }

            override fun onFailure(call: Call<CuentaLoginResponseDTO>, t: Throwable) {
                if (t is IOException) {
                    Log.e("LoginViewModelEmp", "Error de red: ${t.message}")
                } else {
                    Log.e("LoginViewModelEmp", "Error en el inicio de sesión: ${t.message}")
                }
                _loginResult.value = CuentaLoginResponseDTO(success = false, message = "Error en el inicio de sesión: ${t.message}", empresaId = null)
            }
        })
    }

    fun logout() {
        Log.d("LoginViewModelEmp", "Cerrando sesión")
        sessionManager.clearAuthToken()
        sessionManager.clearUserName() // Limpia el nombre del usuario
    }
}
