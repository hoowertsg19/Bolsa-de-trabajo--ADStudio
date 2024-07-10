package com.uam.bolsatrabajo.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.uam.bolsatrabajo.util.SessionManager

class LoginViewModelFactory(private val sessionManager: SessionManager) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
            return LoginViewModel(sessionManager) as T
        }
        if (modelClass.isAssignableFrom(LoginViewModelEmp::class.java)) {
            return LoginViewModelEmp(sessionManager) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}