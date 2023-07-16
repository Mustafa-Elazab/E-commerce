package com.mostafa.training.ui.screens.productsByCategory

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.Surface
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.mostafa.training.R
import com.mostafa.training.data.remote.dto.ProductDTO
import com.mostafa.training.ui.components.AppBar
import com.mostafa.training.ui.components.CheckUiState
import com.mostafa.training.ui.screens.cart.CartViewModel
import com.mostafa.training.ui.screens.home.ProductItem
import com.mostafa.training.ui.screens.product_detail.navigateToDetailScreen
import com.mostafa.training.ui.theme.BaseColor
import org.koin.androidx.compose.getViewModel
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductsByCategoryScreen(navController: NavController) {

    val viewModel: ProductsByCategoryViewModel = koinViewModel()
    val cartViewModel: CartViewModel = getViewModel()
    val productsByCategoryUiState = viewModel.productsUiState.collectAsState().value
    val context = LocalContext.current

    Scaffold(topBar = {
        Surface(elevation = 1.dp) {
            AppBar(
                title ="",
                painter = painterResource(id = R.drawable.ic_left_back)
            ) {
                navController.popBackStack()
            }
        }
    }
    ) { paddingValues ->
        Column(modifier = Modifier.fillMaxSize()) {
            CheckUiState(
                isLoading = productsByCategoryUiState.isLoading,
                error = productsByCategoryUiState.error,
                data = productsByCategoryUiState.products
            ) {
                LazyGridProducts(
                    it,
                    paddingValues.calculateTopPadding(),
                    onClickProductItem = navController::navigateToDetailScreen,
                    onClickAddToCart = {
                        cartViewModel.addOrRemoveItemFromCart(product_id = it)
                    }
                )
            }
        }
    }
}

@Composable
fun LazyGridProducts(
    products: List<ProductDTO>,
    paddingValues: Dp,
    onClickProductItem: (Int) -> Unit,
    onClickAddToCart: (Int) -> Unit
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
            end = 8.dp, bottom = 8.dp
        ),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        items(
            items = products,
            key = {
                it.name!!
            }
        ) {
            ProductItem(
                product = it,
                isSearchScreen = false,
                onClickProductItem = onClickProductItem,
                modifier = Modifier,
                onClickItemCart = onClickAddToCart
            )
        }
    }
}
