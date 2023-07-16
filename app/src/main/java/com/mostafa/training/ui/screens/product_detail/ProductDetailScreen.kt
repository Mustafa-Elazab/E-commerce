package com.mostafa.training.ui.screens.product_detail

import android.annotation.SuppressLint
import android.util.Log
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
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
import com.mostafa.training.ui.screens.product_detail.uiState.ProductDetailUiState
import com.mostafa.training.ui.theme.AccentColor
import com.mostafa.training.ui.theme.AppTypography
import com.mostafa.training.ui.theme.PrimaryTextAndIconColor
import org.koin.androidx.compose.getViewModel
import org.koin.androidx.compose.koinViewModel


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ProductDetailScreen(
    navController: NavController,
) {
    val viewModel: ProductDetailViewModel = koinViewModel()
    val productUiState = viewModel.productDetailState.collectAsState().value
    val context = LocalContext.current
    Log.d("TAG", "ProductDetailScreen: $productUiState")


    ProductDetailsContent(
        onClickBack = navController::popBackStack,
        product = productUiState
    )


}

@Composable
fun ProductDetailsContent(onClickBack: () -> Unit, product: ProductDetailUiState?) {
    CheckUiState(
        isLoading = product!!.isLoading,
        error = product.error,
        data = product.product
    ) { product ->
        ProductDetailContainer(
            product.data!!,
            onClickBack,
        )
    }
}

@SuppressLint("UnrememberedMutableState")
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ProductDetailContainer(product: ProductDTO, onClickBack: () -> Unit) {

    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        LazyColumn(modifier = Modifier.weight(1f)) {
            stickyHeader {
                BackButton(onClickBack)
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
                    AddItemToCart(itemCount = mutableStateOf(1))
                    ContainerClickableButtons(itemCount = mutableStateOf(1))
                }
            }
        }
    }

}

@Composable
private fun AddItemToCart(itemCount: MutableState<Int>) {
    Box(
        modifier = Modifier
            .padding(16.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(AccentColor)
            .clickable { itemCount.value = 1 }
            .fillMaxHeight()
            .width(120.dp),
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
                title = stringResource(id = R.string.add),
                color = Color.White,
                style = AppTypography.bodyLarge
            )
        }
    }
}

@Composable
private fun ContainerClickableButtons(itemCount: MutableState<Int>) {
    Row(
        modifier = Modifier
            .fillMaxHeight()
            .width(164.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        ClickableButton("-", itemCount = itemCount.value) {
            if (it > 1)
                itemCount.value--
        }
        Title(title = itemCount.value.toString(), style = AppTypography.titleLarge)
        ClickableButton("+", itemCount = itemCount.value) {
            itemCount.value++
        }
    }
}

@Composable
private fun ClickableButton(
    text: String,
    itemCount: Int,
    onClick: (Int) -> Unit,
) {
    Box(modifier = Modifier
        .padding(16.dp)
        .clickable { onClick(itemCount) }
        .clip(RoundedCornerShape(8.dp))
        .background(PrimaryTextAndIconColor)
        .size(32.dp),
        contentAlignment = Alignment.Center) {
        Title(title = text, color = Color.White, style = AppTypography.titleLarge)
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

            Title(title = product.price.toString(), style = AppTypography.titleMedium)
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

