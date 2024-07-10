package com.uam.bolsatrabajo.remote

import com.uam.bolsatrabajo.model.Vacante
import retrofit2.http.GET
import retrofit2.http.Path

interface VacanteApi {

    @GET("vacantes/empresa/{empresaId}")
    suspend fun obtenerVacantesPorEmpresaId(@Path("empresaId") empresaId: Long): List<Vacante>

}
