package com.presentation.screens.recipe_detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.myrecipeapp.ui.theme.MyOrange
import com.presentation.components.ErrorMessage
import com.presentation.components.LoadingIndicator
import com.presentation.components.MyTopBar
import com.presentation.viewmodels.RecipeDetailViewModel

@Composable
fun RecipeDetailScreen(
    recipeId: Int,
    onBack: () -> Unit,
    viewModel: RecipeDetailViewModel = viewModel()
) {
    LaunchedEffect(key1 = recipeId) {
        viewModel.fetchRecipeDetails(recipeId)
    }

    Scaffold(
        topBar = {
            MyTopBar(
                title = "Recipe Details",
                icon = Icons.AutoMirrored.Filled.ArrowBack,
                onBackClick = onBack
            )
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(MyOrange.copy(alpha = 0.02f))
        ) {
            when {
                viewModel.isLoading -> {
                    LoadingIndicator(strokeWidth = 1.dp)
                }

                viewModel.errorMessage != null -> {
                    ErrorMessage(
                        errorMessage = viewModel.errorMessage,
                        onRetry = {
                            viewModel.fetchRecipeDetails(recipeId)
                        }
                    )
                }

                else -> {
                    // Full recipe details UI (ingredients, instructions, nutrition) will be completed in Part 08
                }
            }
        }
    }
}
