package com.mostafa.training.ui.screens.search

import android.annotation.SuppressLint
import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.mostafa.training.R
import com.mostafa.training.ui.components.Body
import com.mostafa.training.ui.components.CheckUiState
import com.mostafa.training.ui.components.StaticIcon
import com.mostafa.training.ui.screens.cart.CartViewModel
import com.mostafa.training.ui.screens.favorites.FavoritesViewModel
import com.mostafa.training.ui.screens.home.ui_state.ProductsUiState
import com.mostafa.training.ui.screens.product.ProductContainer
import com.mostafa.training.ui.screens.product_detail.navigateToDetailScreen
import com.mostafa.training.ui.theme.AccentColor
import com.mostafa.training.ui.theme.AppTypography
import com.mostafa.training.ui.theme.CardBackgroundColor
import com.mostafa.training.ui.theme.PrimaryTextAndIconColor
import com.mostafa.training.ui.theme.SecondaryTextColor
import org.koin.androidx.compose.getViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun SearchScreen(
    calculateBottomPadding: Dp,
    navController: NavController
) {
    val viewModel: SearchViewModel = koinViewModel()
    val cartViewModel: CartViewModel = getViewModel()
    val favoritesViewModel: FavoritesViewModel = getViewModel()
    val searchUiState by viewModel.productsUiState.collectAsState()
    val searchQuery = viewModel.searchQuery.value
    val focusRequester = remember { FocusRequester() }
    val context = LocalContext.current
    val view = LocalView.current

    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
        showKeyboard(context, view)
    }

    SearchContent(
        productsState = searchUiState,
        query = searchQuery,
        focusRequester = focusRequester,
        onTextChange = viewModel::setSearchQuery,
        onClickAddToCart = {
            cartViewModel.addOrRemoveItemFromCart(it)
        },
        onClickItemFavorites = {
            favoritesViewModel.addOrRemoveFavorites(it)
        },
        onClickProductItem = {
            navController.navigateToDetailScreen(id = it)
        }
    )
}

@Composable
fun SearchContent(
    productsState: ProductsUiState,
    query: String,
    focusRequester: FocusRequester,
    onTextChange: (String) -> Unit,
    onClickAddToCart: (Int) -> Unit,
    onClickProductItem: (Int) -> Unit,
    onClickItemFavorites: (Int) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .focusRequester(focusRequester)
    ) {
        SearchBox(query = query, onTextChange = onTextChange)

        CheckUiState(
            isLoading = productsState.isLoading,
            error = productsState.error,
            data = productsState.products
        ) { products ->
            ProductContainer(
                products,
                onClickAddToCart = onClickAddToCart,
                onClickProductItem = onClickProductItem,
                isSearchScreen = true,
                paddingValues = 0.dp,
                onClickItemFavorites = onClickItemFavorites
            )
        }
    }

}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
private fun SearchBox(
    query: String,
    onTextChange: (String) -> Unit
) {
    Box(modifier = Modifier.padding(top = 8.dp)) {
        Row(
            modifier = Modifier
                .height(56.dp)
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            TextField(
                modifier = Modifier
                    .fillMaxSize()
                    .clip(RoundedCornerShape(8.dp)),
                value = query,
                onValueChange = { onTextChange(it) },
                leadingIcon = {
                    StaticIcon(
                        painter = painterResource(id = R.drawable.ic_search),
                        contentDescription = stringResource(id = R.string.search),
                        tint = SecondaryTextColor
                    )
                },
                textStyle = AppTypography.bodyMedium,
                colors = TextFieldDefaults.textFieldColors(
                    textColor = PrimaryTextAndIconColor,
                    containerColor = CardBackgroundColor,
                    cursorColor = AccentColor,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                ),
                singleLine = true,
                label = {
                    Body(title = stringResource(id = R.string.search), color = SecondaryTextColor)
                },
            )
        }
    }
}

@SuppressLint("ServiceCast")
private fun showKeyboard(context: Context, view: View?) {
    val inputMethodManager =
        context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT)
}
