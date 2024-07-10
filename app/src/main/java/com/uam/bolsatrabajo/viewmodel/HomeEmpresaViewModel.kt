package com.uam.bolsatrabajo.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.uam.bolsatrabajo.model.Empresa
import com.uam.bolsatrabajo.model.Vacante
import com.uam.bolsatrabajo.repository.EmpresaRepository
import kotlinx.coroutines.launch

class HomeEmpresaViewModel : ViewModel() {

    private val _empresa = MutableLiveData<Empresa?>()
    val empresa: LiveData<Empresa?> get() = _empresa

    private val _vacantes = MutableLiveData<List<Vacante>>()
    val vacantes: LiveData<List<Vacante>> get() = _vacantes

    fun obtenerEmpresaPorId(id: Long) {
        viewModelScope.launch {
            try {
                val empresa = EmpresaRepository.obtenerEmpresaPorId(id)
                _empresa.postValue(empresa)
                if (empresa != null) {
                    Log.d("HomeEmpresaViewModel", "Empresa obtenida: ${empresa.nombre}")
                } else {
                    Log.e("HomeEmpresaViewModel", "Error obteniendo empresa: Empresa es null")
                }
            } catch (e: Exception) {
                Log.e("HomeEmpresaViewModel", "Error obteniendo empresa", e)
            }
        }
    }

    fun obtenerVacantesPorEmpresaId(empresaId: Long) {
        viewModelScope.launch {
            try {
                val vacantes = EmpresaRepository.obtenerVacantesPorEmpresaId(empresaId)
                _vacantes.postValue(vacantes)
                Log.d("HomeEmpresaViewModel", "Vacantes obtenidas: $vacantes")
            } catch (e: Exception) {
                Log.e("HomeEmpresaViewModel", "Error obteniendo vacantes", e)
            }
        }
    }
}
