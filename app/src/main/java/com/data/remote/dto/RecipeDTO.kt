package com.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class RecipeDTO (
    val id: Int,
    val name: String
){

}