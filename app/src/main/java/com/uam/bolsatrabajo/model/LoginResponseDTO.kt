package com.uam.bolsatrabajo.model

data class LoginResponseDTO(
    val success: Boolean,
    val message: String,
    val userId: Long? = null,
    val userName: String? = null // Nuevo campo para el nombre del usuario
)
