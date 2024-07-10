package com.uam.bolsatrabajo.model

data class Postulacion(
    val id: Long,
    val estado: String,
    val fecha: String, // Asegúrate de que este formato coincide con el formato de fecha en el backend
    val vacante: Vacante, // Si necesitas los detalles de la vacante
    val egresado: Egresado?, // Puede ser null si el postulante es un estudiante
    val estudiante: Estudiante? // Puede ser null si el postulante es un egresado
)
