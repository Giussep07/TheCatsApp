package com.giussepr.thecatsapp.domain.model

import com.giussepr.thecatsapp.data.model.CatResponseDTO

data class Cat(
    val id: String,
    val breedName: String,
    val origin: String,
    val intelligence: Int,
    val imageUrl: String
) {

    companion object {
        fun fromResponseDTO(catResponseDTO: CatResponseDTO): Cat {
            return Cat(
                id = catResponseDTO.id,
                breedName = catResponseDTO.name,
                origin = catResponseDTO.origin,
                intelligence = catResponseDTO.intelligence,
                imageUrl = "https://cdn2.thecatapi.com/images/${catResponseDTO.reference_image_id}.jpg"
            )
        }
    }
}
