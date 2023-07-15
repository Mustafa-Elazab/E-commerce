package com.mostafa.training.ui.screens.cart

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.mostafa.training.R
import com.mostafa.training.SpannableTextExample
import com.mostafa.training.data.remote.dto.CartDataDTO
import com.mostafa.training.data.remote.dto.CartItemDTO
import com.mostafa.training.ui.components.Body
import com.mostafa.training.ui.components.CheckUiState
import com.mostafa.training.ui.components.ProfileImage
import com.mostafa.training.ui.components.SpacerHorizontal
import com.mostafa.training.ui.components.SpacerVertical
import com.mostafa.training.ui.components.Title
import com.mostafa.training.ui.theme.AccentColor
import com.mostafa.training.ui.theme.AppTypography
import com.mostafa.training.ui.theme.CardBackgroundColor
import com.mostafa.training.ui.theme.WhiteColor
import org.koin.androidx.compose.getViewModel
import java.math.BigDecimal
import java.math.RoundingMode


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CartScreen(
    calculateBottomPadding: Dp,
    navController: NavController
) {
    val viewModel: CartViewModel = getViewModel()
    val cartUiState = viewModel.cartUiState.collectAsState().value


    Scaffold(
        topBar = {
            Surface(elevation = 1.dp) {
                TopAppBar(title = {
                    SpannableTextExample(fText = "Your\n", sText = "Shopping Cart")

                }, actions = {
                    ProfileImage(painter = painterResource(id = R.drawable.eg_flag))
                })
            }
        }) { paddingValues ->

        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            CheckUiState(
                isLoading = cartUiState.isLoading,
                error = cartUiState.error,
                data = cartUiState.cartData,
                sizeOfProgress = 30
            ) { cartItems ->
                LazyColumn(
                    modifier = Modifier.weight(1.9f),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    contentPadding = PaddingValues(
                        top = paddingValues.calculateTopPadding(),
                        end = 8.dp,
                        start = 8.dp,
                        bottom = paddingValues.calculateBottomPadding()
                    )
                ) {
                    items(cartItems.cartItems!!) { cart ->
                        CartItem(
                            cart,
                            quantity = viewModel.getCartItemQuantity(cart?.id!!),
                            onQuantityChange = { newQuantity ->
                                viewModel.updateCartItemQuantity(newQuantity, cart?.id!!)
                            })
                    }
                }
            }

            CheckUiState(
                isLoading = cartUiState.isLoading,
                error = cartUiState.error,
                data = cartUiState.cartData,
                sizeOfProgress = 30
            ) { cartItems ->
                CheckOutContainer(
                    cartItems = cartItems,
                    navController = navController,
                    calculateBottomPadding = calculateBottomPadding
                )
            }

        }

    }

}

@Composable
fun CheckOutContainer(
    cartItems: CartDataDTO,
    navController: NavController,
    calculateBottomPadding: Dp
) {


    val subTotal = remember { mutableStateOf(cartItems.subTotal) }
    val total = remember { mutableStateOf(cartItems.total) }

    Card(
        colors = CardDefaults.cardColors(containerColor = CardBackgroundColor),
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier.padding(
            bottom = calculateBottomPadding + 8.dp,
            top = 8.dp,
            start = 8.dp,
            end = 8.dp
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Body(title = "Items", style = AppTypography.bodyMedium)
                Title(
                    title = cartItems.cartItems?.size.toString(),
                    style = AppTypography.titleMedium
                )
            }

            SpacerVertical(height = 4)
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Body(title = "Sub Total", style = AppTypography.bodyMedium)
                Title(title = subTotal.value.toString(), style = AppTypography.titleMedium)
            }

            SpacerVertical(height = 4)
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Body(title = "Total", style = AppTypography.bodyMedium)
                Title(
                    title = BigDecimal(cartItems.total!!)
                        .setScale(2, RoundingMode.HALF_UP).toString(),
                    style = AppTypography.titleMedium
                )
            }

            SpacerVertical(height = 8)

            Button(
                onClick = { },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp),
                shape = RoundedCornerShape(8.dp),
            ) {
                Title(
                    title = "CheckOut",
                    textAlign = TextAlign.Center,
                    color = Color.White,
                    style = AppTypography.titleLarge
                )
            }
        }
    }


    LaunchedEffect(cartItems) {
        subTotal.value = cartItems.subTotal
        total.value = cartItems.total
    }
}


@Composable
private fun CartItem(
    cart: CartItemDTO?,
    quantity: Int,
    onQuantityChange: (Int) -> Unit
) {


    Box(

        modifier = Modifier
            .height(200.dp)
            .padding(horizontal = 2.dp, vertical = 4.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(CardBackgroundColor),
    ) {
        Row(
            modifier = Modifier
                .padding(4.dp)
                .fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Image(
                modifier = Modifier
                    .fillMaxWidth(.25f)
                    .fillMaxHeight()
                    .clip(RoundedCornerShape(8.dp)), contentScale = ContentScale.Fit,
                painter = rememberAsyncImagePainter(model = cart?.product?.image),
                contentDescription = ""
            )
            Column(
                modifier = Modifier
                    .padding(start = 8.dp)
                    .fillMaxWidth()
                    .fillMaxHeight(),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.SpaceEvenly
            ) {
                Body(
                    title = cart?.product?.name.toString(),
                    style = AppTypography.bodyMedium,
                    maxLine = 3
                )
                SpacerVertical(height = 8)
                Body(
                    title = "${cart?.product?.price.toString()} EGP",
                    style = AppTypography.bodyMedium,
                    color = Color.Green
                )
                SpacerVertical(height = 8)
                Body(
                    title = if (cart?.product?.discount!! > 0) "${cart?.product?.discount.toString()} %" else "",
                    style = AppTypography.bodySmall,
                    color = Color.Red
                )
                SpacerVertical(height = 12)

                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Row(
                        horizontalArrangement = Arrangement.SpaceEvenly,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(
                            modifier = Modifier
                                .padding(2.dp)
                                .clickable {
                                    val updatedQuantity = quantity + 1
                                    onQuantityChange(updatedQuantity)

                                }
                                .clip(RoundedCornerShape(4.dp))
                                .background(AccentColor)
                                .size(32.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Title(
                                title = "+",
                                color = Color.White,
                                style = AppTypography.titleMedium
                            )
                        }

                        SpacerHorizontal(width = 6)

                        Title(title = quantity.toString(), style = AppTypography.titleMedium)
                        SpacerHorizontal(width = 6)
                        Box(
                            modifier = Modifier
                                .clickable {
                                    if (quantity > 1) {
                                        val updatedQuantity = quantity - 1
                                        onQuantityChange(updatedQuantity)
                                    }
                                }
                                .padding(2.dp)
                                .clip(RoundedCornerShape(4.dp))
                                .background(AccentColor)
                                .size(36.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Title(
                                title = "-",
                                color = Color.White,
                                style = AppTypography.titleMedium
                            )
                        }
                    }

                    SpacerHorizontal(width = 32)
                    Box(
                        modifier = Modifier
                            .padding(2.dp)
                            .clip(RoundedCornerShape(4.dp))
                            .fillMaxWidth()
                            .height(32.dp)
                            .clickable {


                            }
                            .background(WhiteColor)
                            .border(
                                border = BorderStroke(1.dp, AccentColor),
                                shape = RoundedCornerShape(4.dp)
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        Row(
                            horizontalArrangement = Arrangement.SpaceEvenly,
                            verticalAlignment = Alignment.CenterVertically
                        ) {

                            Icon(
                                painter = rememberVectorPainter(image = Icons.Default.Delete),
                                contentDescription = "",
                                tint = AccentColor
                            )

                            Title(
                                title = "Remove",
                                color = AccentColor,
                                style = AppTypography.titleMedium
                            )


                        }
                    }
                }
            }


        }
    }
}




