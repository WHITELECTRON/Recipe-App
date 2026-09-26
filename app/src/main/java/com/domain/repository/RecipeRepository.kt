package com.domain.repository

import com.data.remote.dto.RecipeDTO

interface RecipeRepository {

    suspend fun getAllRecipes():List<RecipeDTO>
    suspend fun getRecipeById(id:Int): RecipeDTO

}