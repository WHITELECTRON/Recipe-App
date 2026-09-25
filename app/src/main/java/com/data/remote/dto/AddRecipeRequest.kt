package com.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class AddRecipeRequest(
    val name: String
)
