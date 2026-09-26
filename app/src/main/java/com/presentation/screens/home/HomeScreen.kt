package com.presentation.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.myrecipeapp.ui.theme.MyOrange
import com.presentation.components.LoadingIndicator
import com.presentation.viewmodels.HomeViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    onRecipeClick: (Int) -> Unit,
    viewModel: HomeViewModel = viewModel()
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Recipes",
                        fontWeight = FontWeight.Bold,
                        color = Color.DarkGray
                    )
                }
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
                    LoadingIndicator(strokeWidth = 2.dp)
                }

                viewModel.errorMessage != null -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Text(
                                text = viewModel.errorMessage ?: "",
                                fontSize = 24.sp,
                                fontWeight = FontWeight.SemiBold,
                                color = MyOrange
                            )
                            Spacer(modifier = Modifier.height(24.dp))
                            Button(
                                onClick = { viewModel.fetchRecipes() },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 24.dp),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = MyOrange,
                                    contentColor = Color.White
                                )
                            ) {
                                Text(
                                    text = "Retry",
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }
                    }
                }

                else -> {
                    LazyVerticalGrid(
                        columns = GridCells.Fixed(2),
                        contentPadding = PaddingValues(16.dp),
                        horizontalArrangement = Arrangement.spacedBy(12.dp),
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        // 1. Home Header Banner
                        item(span = { GridItemSpan(maxLineSpan) }) {
                            HomeHeader()
                        }

                        // 2. Categories Horizontal Row
                        if (viewModel.categories.size > 1) {
                            item(span = { GridItemSpan(maxLineSpan) }) {
                                CategorySection(
                                    categories = viewModel.categories,
                                    selectedCategory = viewModel.selectedCategory,
                                    onCategorySelected = { category ->
                                        viewModel.onCategorySelected(category)
                                    }
                                )
                            }
                        }

                        // 3. Section Title for Recipes List
                        item(span = { GridItemSpan(maxLineSpan) }) {
                            val headerTitle = if (viewModel.selectedCategory == "All") {
                                "All Recipes"
                            } else {
                                viewModel.selectedCategory
                            }
                            SectionHeader(
                                title = headerTitle,
                                icon = Icons.Default.Menu
                            )
                        }

                        // 4. Recipe Grid Items or Empty State
                        if (viewModel.recipes.isEmpty()) {
                            item(span = { GridItemSpan(maxLineSpan) }) {
                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(vertical = 48.dp),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text(
                                        text = "No Recipes Found",
                                        fontSize = 20.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = MyOrange
                                    )
                                }
                            }
                        } else {
                            items(
                                items = viewModel.recipes,
                                key = { it.id }
                            ) { recipe ->
                                RecipeCard(
                                    recipe = recipe,
                                    onClick = { onRecipeClick(recipe.id) }
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
