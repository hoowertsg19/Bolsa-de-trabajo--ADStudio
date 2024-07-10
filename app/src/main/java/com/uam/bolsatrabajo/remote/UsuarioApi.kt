package com.uam.bolsatrabajo.remote

import com.uam.bolsatrabajo.model.LoginRequestDTO
import com.uam.bolsatrabajo.model.LoginResponseDTO
import com.uam.bolsatrabajo.model.RegisterUserDTO
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface UsuarioApi {
    @POST("usuario/registro")
    fun registrarUsuario(@Body registerUserDTO: RegisterUserDTO): Call<String>

    @POST("usuario/login")
    fun loginUser(@Body loginRequest: LoginRequestDTO): Call<LoginResponseDTO>
}
