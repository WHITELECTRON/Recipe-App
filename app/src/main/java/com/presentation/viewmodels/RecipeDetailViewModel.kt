package com.presentation.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.data.remote.KtorClient
import com.data.remote.RecipeAPIService
import com.data.remote.dto.RecipeDTO
import com.data.repository.RecipeRepositoryImpl
import com.domain.repository.RecipeRepository
import kotlinx.coroutines.launch

class RecipeDetailViewModel : ViewModel() {

    private val repository: RecipeRepository = RecipeRepositoryImpl(
        apiService = RecipeAPIService(client = KtorClient.client)
    )

    var isLoading by mutableStateOf(false)
        private set

    var errorMessage by mutableStateOf<String?>(null)
        private set

    var recipe by mutableStateOf<RecipeDTO?>(null)
        private set

    fun fetchRecipeDetails(id: Int) {
        isLoading = true
        errorMessage = null

        viewModelScope.launch {
            try {
                recipe = repository.getRecipeById(id)
            } catch (e: Exception) {
                errorMessage = e.message ?: "An unexpected error occurred"
            } finally {
                isLoading = false
            }
        }
    }
}
