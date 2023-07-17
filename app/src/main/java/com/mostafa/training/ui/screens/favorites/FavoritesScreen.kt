package com.mostafa.training.ui.screens.favorites

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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.mostafa.training.R
import com.mostafa.training.data.remote.dto.FavoriteDTO
import com.mostafa.training.ui.components.AppBar
import com.mostafa.training.ui.components.Body
import com.mostafa.training.ui.components.CheckUiState
import com.mostafa.training.ui.components.SpacerHorizontal
import com.mostafa.training.ui.components.SpacerVertical
import com.mostafa.training.ui.components.Title
import com.mostafa.training.ui.screens.product_detail.navigateToDetailScreen
import com.mostafa.training.ui.theme.AccentColor
import com.mostafa.training.ui.theme.AppTypography
import com.mostafa.training.ui.theme.CardBackgroundColor
import com.mostafa.training.ui.theme.WhiteColor
import org.koin.androidx.compose.getViewModel
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavoritesScreen(navController: NavController) {

    val viewModel: FavoritesViewModel = getViewModel()
    viewModel.getAllFavoritesProducts()
    val favoritesUiState by viewModel.favoritesUiState.collectAsState()

    Scaffold(
        topBar = {
            Surface(
                elevation = 1.dp,
            ) {
                AppBar(
                    title = "Favorites",
                    painter = painterResource(id = R.drawable.ic_left_back)
                ) { navController.popBackStack() }
            }
        }) { paddingValues ->

        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            if (favoritesUiState.data?.data?.data?.isEmpty() == true) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Try adding one first",
                        style = AppTypography.bodyMedium,
                        textAlign = TextAlign.Center
                    )
                }
            } else {
                CheckUiState(
                    isLoading = favoritesUiState.isLoading,
                    error = favoritesUiState.error,
                    data = favoritesUiState.data,
                    sizeOfProgress = 30
                ) { favoritesItems ->
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
                        items(favoritesItems.data?.data!!) { favorite ->
                            FavoritesItem(
                                favorite,
                                onClickProductImage = {
                                navController.navigateToDetailScreen(id = it)
                                },
                                onRemoveClick = {
                                    viewModel.addOrRemoveFavorites(it)
                                }
                            )
                        }
                    }
                }


            }

        }
    }
}

@Composable
fun FavoritesItem(
    favorite: FavoriteDTO,
    onClickProductImage: (Int) -> Unit,
    onRemoveClick: (Int) -> Unit
) {
    Box(
        modifier = Modifier
            .height(200.dp)
            .padding(horizontal = 2.dp, vertical = 4.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(CardBackgroundColor)
            .clickable {
                       onClickProductImage(favorite.product?.id!!)
            },
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
                painter = rememberAsyncImagePainter(model = favorite?.product?.image),
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
                    title = favorite?.product?.name.toString(),
                    style = AppTypography.bodyMedium,
                    maxLine = 3
                )
                SpacerVertical(height = 8)
                Body(
                    title = "${favorite?.product?.price.toString()} EGP",
                    style = AppTypography.bodyMedium,
                    color = Color.Green
                )
                SpacerVertical(height = 8)
                Body(
                    title = if (favorite?.product?.discount!! > 0) "${favorite?.product?.discount.toString()} %" else "",
                    style = AppTypography.bodySmall,
                    color = Color.Red
                )
                SpacerVertical(height = 12)

                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {


                    SpacerHorizontal(width = 32)
                    Box(
                        modifier = Modifier
                            .padding(2.dp)
                            .clip(RoundedCornerShape(4.dp))
                            .fillMaxWidth()
                            .height(32.dp)
                            .clickable {
                                onRemoveClick(favorite.product?.id!!)
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
