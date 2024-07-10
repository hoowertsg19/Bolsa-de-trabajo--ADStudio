package com.uam.bolsatrabajo.remote

import com.uam.bolsatrabajo.model.Postulacion
import retrofit2.http.GET
import retrofit2.http.Path

interface PostulacionApi {
    @GET("api/postulaciones/{vacanteId}")
    suspend fun obtenerPostulacionesPorVacanteId(@Path("vacanteId") vacanteId: Long): List<Postulacion>
}
