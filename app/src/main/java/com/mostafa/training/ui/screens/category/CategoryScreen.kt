package com.mostafa.training.ui.screens.category

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.mostafa.training.R
import com.mostafa.training.data.remote.dto.CategoryDTO
import com.mostafa.training.ui.components.AppBar
import com.mostafa.training.ui.components.Body
import com.mostafa.training.ui.components.CheckUiState
import com.mostafa.training.ui.screens.productsByCategory.navigateToProductsByCategoryScreen
import com.mostafa.training.ui.theme.AppTypography
import com.mostafa.training.ui.theme.BaseColor
import com.mostafa.training.ui.theme.CardBackgroundColor
import org.koin.androidx.compose.getViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategoryScreen(navController: NavController) {

    val viewModel: CategoryViewModel = getViewModel()
    val categoryUiState = viewModel.categoriesUiState.collectAsState().value

    Scaffold(
        topBar = {
            Surface(
                elevation = 1.dp,
            ) {
                AppBar(
                    title = "Categories",
                    painter = painterResource(id = R.drawable.ic_left_back)
                ) { navController.popBackStack() }
            }
        }
    ) { paddingValues ->
        Column(modifier = Modifier.fillMaxSize()) {
            CheckUiState(
                isLoading = categoryUiState.isLoading,
                error = categoryUiState.error,
                data = categoryUiState.categories,
                sizeOfProgress = 30
            ) { categories ->
                CategoriesContainer(
                    categories,
                    paddingValues = paddingValues.calculateTopPadding(),
                    navController=navController
                )
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CategoriesContainer(
    categories: List<CategoryDTO?>,
    paddingValues: Dp,
    navController:NavController
) {
    LazyVerticalGrid(
        modifier = Modifier
            .background(BaseColor)
            .fillMaxSize()
            .padding(top = 16.dp),
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(
            top = paddingValues,
            start = 8.dp,
            end = 8.dp,
            bottom = 8.dp
        ),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        items(
            items = categories,
            key = { category ->
                category?.id!!
            }
        ) {
            CategoryCardItem(
                it,
                navController = navController,
                modifier = Modifier.animateItemPlacement()
            )
        }
    }
}

@Composable
fun CategoryCardItem(
    category: CategoryDTO?,
    modifier: Modifier,
    navController: NavController
) {
    Card(
        modifier = Modifier
            .width(150.dp)
            .clickable {  navController.navigateToProductsByCategoryScreen(id = category?.id!!)},
        colors = CardDefaults.cardColors(CardBackgroundColor),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 2.dp
        ),
        shape = RoundedCornerShape(8.dp)
    ) {
        Column(Modifier.padding(2.dp)) {
            Image(
                painter = rememberAsyncImagePainter(category?.image!!),
                contentDescription = category.name,
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
                    .fillMaxWidth()
                    .background(BaseColor),
            )
            Body(title = category.name.toString(), style = AppTypography.bodyMedium)
        }
    }
}
