package com.uam.bolsatrabajo.remote

import com.uam.bolsatrabajo.model.CuentaLoginRequestDTO
import com.uam.bolsatrabajo.model.CuentaLoginResponseDTO
import com.uam.bolsatrabajo.model.Empresa
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path


interface EmpresaApi {
    @POST("empresa/registrar")
    suspend fun registrarEmpresa(@Body empresa: Empresa): Response<Empresa>

    @POST("cuenta/login")
    fun loginCuenta(@Body loginRequest: CuentaLoginRequestDTO): Call<CuentaLoginResponseDTO>

    @GET("empresa/{id}")
    suspend fun obtenerEmpresaPorId(@Path("id") id: Long): Response<Empresa>
    }


