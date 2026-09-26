package com.data.repository

import com.data.remote.RecipeAPIService
import com.data.remote.dto.RecipeDTO
import com.domain.repository.RecipeRepository

class RecipeRepositoryImpl(private val apiService: RecipeAPIService) : RecipeRepository {

    override suspend fun getAllRecipes(): List<RecipeDTO> {
        return apiService.getAllRecipes().recipes
    }

    override suspend fun getRecipeById(id: Int): RecipeDTO {
        return apiService.getRecipeById(id)
    }

}
