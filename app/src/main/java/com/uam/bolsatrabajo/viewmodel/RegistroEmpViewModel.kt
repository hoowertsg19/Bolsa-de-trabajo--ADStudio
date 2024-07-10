package com.uam.bolsatrabajo.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.uam.bolsatrabajo.model.Empresa
import com.uam.bolsatrabajo.repository.EmpresaRepository
import kotlinx.coroutines.launch

class RegistroEmpViewModel : ViewModel() {

    val nombreEmpresa = MutableLiveData<String>()
    val correoElectronico = MutableLiveData<String>()
    val telefonoContacto = MutableLiveData<String>()
    val ubicacion = MutableLiveData<String>()
    val registroResult = MutableLiveData<String>()

    fun registrarEmpresa() {
        val empresa = Empresa(
            nombre = nombreEmpresa.value.orEmpty(),
            correoElectronico = correoElectronico.value.orEmpty(),
            telefonoContacto = telefonoContacto.value.orEmpty(),
            ubicacion = ubicacion.value.orEmpty()
        )

        Log.d("RegistroEmpViewModel", "Registrando empresa con los siguientes datos:")
        Log.d("RegistroEmpViewModel", "Nombre: ${empresa.nombre}")
        Log.d("RegistroEmpViewModel", "Correo Electrónico: ${empresa.correoElectronico}")
        Log.d("RegistroEmpViewModel", "Teléfono de Contacto: ${empresa.telefonoContacto}")
        Log.d("RegistroEmpViewModel", "Ubicación: ${empresa.ubicacion}")

        viewModelScope.launch {
            try {
                val response = EmpresaRepository.registrarEmpresa(empresa)
                if (response.isSuccessful) {
                    registroResult.value = "Registro exitoso_empresa"
                    Log.d("RegistroEmpViewModel", "Registro exitoso")
                } else {
                    registroResult.value = "Error en el registro_empresa"
                    Log.d("RegistroEmpViewModel", "Error en el registro: ${response.errorBody()?.string()}")
                }
            } catch (e: Exception) {
                registroResult.value = "Error en el registro_empresa"
                Log.e("RegistroEmpViewModel", "Error al registrar la empresa", e)
            }
        }
    }
}
