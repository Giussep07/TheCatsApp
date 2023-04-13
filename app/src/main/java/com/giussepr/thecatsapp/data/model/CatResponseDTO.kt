package com.giussepr.thecatsapp.data.model

data class CatResponseDTO(
    val id: String,
    val name: String,
    val origin: String,
    val intelligence: Int,
    val reference_image_id: String
)
