package com.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class RecipesResponse(
    val recipes: List<RecipeDTO>,
    val total: Int,
    val skip: Int,
    val limit: Int
)
