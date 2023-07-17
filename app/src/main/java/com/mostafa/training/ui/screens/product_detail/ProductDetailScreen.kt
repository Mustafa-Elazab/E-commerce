package com.mostafa.training.ui.screens.product_detail

import android.annotation.SuppressLint
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.mostafa.training.NormalTextComponent
import com.mostafa.training.R
import com.mostafa.training.data.remote.dto.ProductDTO
import com.mostafa.training.ui.components.BackButton
import com.mostafa.training.ui.components.CheckUiState
import com.mostafa.training.ui.components.SpacerVertical
import com.mostafa.training.ui.components.Title
import com.mostafa.training.ui.screens.cart.CartViewModel
import com.mostafa.training.ui.screens.favorites.FavoritesViewModel
import com.mostafa.training.ui.screens.home.TextWithStrikethrough
import com.mostafa.training.ui.screens.product_detail.uiState.ProductDetailUiState
import com.mostafa.training.ui.theme.AccentColor
import com.mostafa.training.ui.theme.AppTypography
import org.koin.androidx.compose.getViewModel


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ProductDetailScreen(
    navController: NavController,
) {
    val viewModel: ProductDetailViewModel = getViewModel()
    val cartViewModel: CartViewModel = getViewModel()
    val favoritesViewModel: FavoritesViewModel = getViewModel()
    val productUiState = viewModel.productDetailState.collectAsState().value

    val context = LocalContext.current



    ProductDetailsContent(
        onClickBack = navController::popBackStack,
        product = productUiState,
        onClickCart = {
            cartViewModel.addOrRemoveItemFromCart(product_id = it)
        },
        onClickItemFavorites = {
            favoritesViewModel.addOrRemoveFavorites(it)
        }
    )


}

@Composable
fun ProductDetailsContent(
    onClickBack: () -> Unit,
    product: ProductDetailUiState?,
    onClickCart: (Int) -> Unit,
    onClickItemFavorites: (Int) -> Unit
) {

    CheckUiState(
        isLoading = product!!.isLoading,
        error = product.error,
        data = product.product
    ) { product ->
        ProductDetailContainer(
            product.data!!,
            onClickBack,
            onClickCart = onClickCart,
            onClickItemFavorites = onClickItemFavorites

        )
    }
}

@SuppressLint("UnrememberedMutableState")
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ProductDetailContainer(
    product: ProductDTO,
    onClickBack: () -> Unit,
    onClickItemFavorites: (Int) -> Unit,
    onClickCart: (Int) -> Unit
) {
    val cartViewModel: CartViewModel = getViewModel()

    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        LazyColumn(modifier = Modifier.weight(1f)) {
            stickyHeader {
                BackButton(
                    product = product,
                    onClickBack = onClickBack,
                    onClickItemFavorites = onClickItemFavorites
                )
            }

            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(250.dp)
                ) {
                    ProductImage(product.image!!)
                }
            }


            item {
                ProductDetails(product)
            }
        }

        Surface(
            elevation = 8.dp,
        ) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(88.dp),
                backgroundColor = Color.White,
            ) {
                Row(
                    modifier = Modifier.fillMaxHeight(),
                    horizontalArrangement = Arrangement.SpaceBetween
                )
                {
                    AddItemToCart(product, onClickCart = onClickCart)

                }
            }
        }
    }

}

@Composable
private fun AddItemToCart(product: ProductDTO, onClickCart: (Int) -> Unit) {
    Box(
        modifier = Modifier
            .padding(16.dp)
            .background(AccentColor, shape = RoundedCornerShape(8.dp))
            .clickable {
                onClickCart(product.id!!)
            }
            .fillMaxSize(),

        contentAlignment = Alignment.Center
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_cart_white),
                contentDescription = "Add item to cart",
                tint = Color.White
            )
            Title(
                title = if (product.inCart == true) "In Cart" else "Add Cart",
                color = Color.White,
                style = AppTypography.bodyLarge
            )
        }
    }
}


@Composable
fun ProductDetails(product: ProductDTO) {

    Column(modifier = Modifier.padding(16.dp)) {
        Title(title = product.name.toString(), style = AppTypography.titleMedium)
        SpacerVertical(height = 10)
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {

            Column() {
                Title(title = product.price.toString(), style = AppTypography.titleMedium)
                if (product.discount!! > 0) {
                    TextWithStrikethrough(product.oldPrice.toString())

                }
            }


            if (product.discount!! > 0) {
                Title(
                    title = "${product.discount.toString()}%",
                    style = AppTypography.titleMedium,
                    color = Red
                )
            }
        }


        SpacerVertical(height = 10)
        NormalTextComponent(
            value = product.description.toString(),
            fontSize = 12,
            textAlign = TextAlign.Start
        )


    }
}

@Composable
private fun ProductImage(image: String) {
    Image(
        modifier = Modifier
            .fillMaxSize(),
        painter = rememberAsyncImagePainter(model = image),
        contentDescription = "product image",
        alignment = Alignment.TopCenter,
    )
}

