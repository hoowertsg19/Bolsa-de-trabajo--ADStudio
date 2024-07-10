package com.uam.bolsatrabajo.model

data class CuentaLoginResponseDTO(
    val success: Boolean,
    val message: String,
    val role: String? = null,
    val empresaId: Long?
)
