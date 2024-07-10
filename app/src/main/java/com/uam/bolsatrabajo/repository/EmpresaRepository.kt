package com.uam.bolsatrabajo.repository

import com.uam.bolsatrabajo.model.Empresa
import com.uam.bolsatrabajo.model.Postulacion
import com.uam.bolsatrabajo.model.Vacante
import com.uam.bolsatrabajo.remote.EmpresaApi
import com.uam.bolsatrabajo.remote.RetrofitClient
import com.uam.bolsatrabajo.remote.VacanteApi
import retrofit2.Response

object EmpresaRepository {
    private val api = RetrofitClient.getClient().create(EmpresaApi::class.java)
    private val vacanteApi = RetrofitClient.vacanteApi
    private val empresaApi = RetrofitClient.empresaApi
    private val postulacionApi = RetrofitClient.postulacionApi

    suspend fun obtenerEmpresaPorId(id: Long): Empresa? {
        val response = empresaApi.obtenerEmpresaPorId(id)
        if (response.isSuccessful) {
            return response.body()
        }
        return null
    }

    suspend fun obtenerVacantesPorEmpresaId(empresaId: Long): List<Vacante> {
        return vacanteApi.obtenerVacantesPorEmpresaId(empresaId).filter { it.autorizado }
    }

    suspend fun registrarEmpresa(empresa: Empresa): Response<Empresa> {
        return api.registrarEmpresa(empresa)
    }

    suspend fun obtenerPostulacionesPorVacanteId(vacanteId: Long): List<Postulacion> {
        return postulacionApi.obtenerPostulacionesPorVacanteId(vacanteId)
    }
}
