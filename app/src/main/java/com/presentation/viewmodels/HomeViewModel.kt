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

class HomeViewModel : ViewModel() {

    private val repository: RecipeRepository =
        RecipeRepositoryImpl(apiService = RecipeAPIService(KtorClient.client))

    var isLoading by mutableStateOf(value = false)
        private set

    var errorMessage by mutableStateOf<String?>(value = null)
        private set

    var recipes by mutableStateOf<List<RecipeDTO>>(value = emptyList())
        private set

    var categories by mutableStateOf<List<String>>(value = listOf("All"))
        private set

    var selectedCategory by mutableStateOf(value = "All")
        private set

    private var allRecipes: List<RecipeDTO> = emptyList()

    init {
        fetchRecipes()
    }

    fun fetchRecipes() {

        isLoading = true
        errorMessage = null

        viewModelScope.launch {

            try {
                val result = repository.getAllRecipes()
                allRecipes = result

                val cuisines = result.map { it.cuisine }.distinct().sorted()
                categories = listOf("All") + cuisines

                applyFilters()
            } catch (e: Exception) {
                errorMessage = e.message ?: "An unexpected error occurred"
            } finally {
                isLoading = false
            }

        }

    }

    fun onCategorySelected(category: String) {
        selectedCategory = category
        applyFilters()
    }

    private fun applyFilters() {
        recipes =
            if (selectedCategory == "All") allRecipes
            else allRecipes.filter { it.cuisine == selectedCategory }
    }
}
