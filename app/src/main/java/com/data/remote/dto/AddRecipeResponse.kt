package com.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AddRecipeResponse(
    @SerialName("id") val id: Int? = null,
    @SerialName("name") val name: String,
    @SerialName("ingredients") val ingredients: List<String>,
    @SerialName("instructions") val instructions: List<String>,
    @SerialName("prepTimeMinutes") val prepTimeMinutes: Int,
    @SerialName("cookTimeMinutes") val cookTimeMinutes: Int,
    @SerialName("servings") val servings: Int,
    @SerialName("difficulty") val difficulty: String,
    @SerialName("cuisine") val cuisine: String,
    @SerialName("caloriesPerServing") val caloriesPerServing: Int,
    @SerialName("tags") val tags: List<String>,
    @SerialName("mealType") val mealType: List<String>
)
