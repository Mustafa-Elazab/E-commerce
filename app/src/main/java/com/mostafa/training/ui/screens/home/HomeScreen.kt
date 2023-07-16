package com.mostafa.training.ui.screens.home

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.rememberPagerState
import com.mostafa.training.R
import com.mostafa.training.SpannableTextExample
import com.mostafa.training.data.remote.dto.BannerDTO
import com.mostafa.training.data.remote.dto.CategoryDTO
import com.mostafa.training.data.remote.dto.ProductDTO
import com.mostafa.training.ui.components.Body
import com.mostafa.training.ui.components.CheckUiState
import com.mostafa.training.ui.components.ClickableIcon
import com.mostafa.training.ui.components.ProfileImage
import com.mostafa.training.ui.components.SpacerHorizontal
import com.mostafa.training.ui.components.SpacerVertical
import com.mostafa.training.ui.components.Title
import com.mostafa.training.ui.screens.cart.CartViewModel
import com.mostafa.training.ui.screens.category.navigateToCategoryScreen
import com.mostafa.training.ui.screens.home.ui_state.BannerUiState
import com.mostafa.training.ui.screens.home.ui_state.CategoryUiState
import com.mostafa.training.ui.screens.home.ui_state.ProductsUiState
import com.mostafa.training.ui.screens.notification.navigateToNotifications
import com.mostafa.training.ui.screens.product.navigateToProductsScreen
import com.mostafa.training.ui.screens.product_detail.navigateToDetailScreen
import com.mostafa.training.ui.screens.productsByCategory.navigateToProductsByCategoryScreen
import com.mostafa.training.ui.theme.AccentColor
import com.mostafa.training.ui.theme.AppTypography
import com.mostafa.training.ui.theme.BaseColor
import com.mostafa.training.ui.theme.CardBackgroundColor
import com.mostafa.training.ui.theme.SecondaryTextColor
import kotlinx.coroutines.delay
import kotlinx.coroutines.yield
import org.koin.androidx.compose.getViewModel
import org.koin.androidx.compose.koinViewModel


@Composable
fun HomeScreen(
    calculateBottomPadding: Dp,
    navController: NavController,

    ) {
    val viewModel: HomeViewModel = koinViewModel()
    val cartViewModel: CartViewModel = getViewModel()

    val bannerUiState by viewModel.bannersUiState.collectAsState()
    val categoryUiState by viewModel.categoriesUiState.collectAsState()
    val productsUiState by viewModel.productsUiState.collectAsState()

    val context = LocalContext.current

    HomeContent(
        calculateBottomPadding = calculateBottomPadding,
        navController = navController,
        bannerUiState = bannerUiState,
        categoryUiState = categoryUiState,
        productsUiState = productsUiState,
        context = context,
        onClickProducts = navController::navigateToProductsScreen,
        onClickProductItem = navController::navigateToDetailScreen,
        onClickCategories = navController::navigateToCategoryScreen,
        onClickNotificationButton = navController::navigateToNotifications,
        onClickItemCart = {
            cartViewModel.addOrRemoveItemFromCart(it)
        }
    )

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeContent(
    calculateBottomPadding: Dp,
    navController: NavController,
    bannerUiState: BannerUiState,
    categoryUiState: CategoryUiState,
    productsUiState: ProductsUiState,
    context: Context,
    onClickProducts: () -> Unit,
    onClickProductItem: (Int) -> Unit,
    onClickCategories: () -> Unit,
    onClickNotificationButton: () -> Unit,
    onClickItemCart: (Int) -> Unit
) {


    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()
    Scaffold(
        topBar = {
            TopAppBar(
                title = { },
                actions = { AppBar(onClickNotificationButton) },
                scrollBehavior = scrollBehavior
            )
        }, containerColor = BaseColor
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .padding(vertical = 16.dp)
                .fillMaxSize()
                .nestedScroll(scrollBehavior.nestedScrollConnection),
            contentPadding = PaddingValues(
                bottom = calculateBottomPadding,
                top = paddingValues.calculateTopPadding(),
            ),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                CheckUiState(
                    isLoading = bannerUiState.isLoading,
                    error = bannerUiState.error,
                    data = bannerUiState.banners,
                    sizeOfProgress = 30
                ) { banners ->
                    SliderImage(banners)
                }
            }
            item {
                CategoryHeader(onClickCategories = onClickCategories)
                SpacerVertical(height = 8)
                CheckUiState(
                    isLoading = categoryUiState.isLoading,
                    error = categoryUiState.error,
                    data = categoryUiState.categories,
                    sizeOfProgress = 30
                ) { categories ->
                    CategoryItems(
                        categories,
                        navController = navController
                    )
                }
            }
            item {
                SpacerVertical(height = 8)
                ProductHeader(onClickProducts = onClickProducts)
                SpacerVertical(height = 8)
                CheckUiState(
                    isLoading = productsUiState.isLoading,
                    error = productsUiState.error,
                    data = productsUiState.products,
                    sizeOfProgress = 30
                ) { products ->
                    ProductsItems(
                        products,
                        onClickProductItem = onClickProductItem,
                        onClickItemCart = onClickItemCart
                    )
                }
            }
        }
    }
}

@Composable
fun ProductsItems(
    products: List<ProductDTO>,
    onClickProductItem: (Int) -> Unit,
    onClickItemCart: (Int) -> Unit
) {
    LazyRow(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        products.let {
            items(it) { product ->
                ProductItem(
                    product,
                    isSearchScreen = false,
                    onClickProductItem = onClickProductItem,
                    onClickItemCart = {
                        onClickItemCart(it)
                    },
                    modifier = Modifier
                )
            }
        }
    }
}

@Composable
fun ProductItem(
    product: ProductDTO,
    isSearchScreen: Boolean,
    onClickProductItem: (Int) -> Unit,
    modifier: Modifier,
    onClickItemCart: (Int) -> Unit
) {
    val context = LocalContext.current
    val cartViewModel: CartViewModel = getViewModel()
    val addOrRemoveUiState by cartViewModel.addOrRemoveUiState.collectAsState()
    Log.d("TAG", "ProductItem: ${product}")

    Card(
        modifier = Modifier
            .width(150.dp)
            .clickable { onClickProductItem(product.id!!) },
        colors = CardDefaults.cardColors(CardBackgroundColor),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 0.dp
        ),
        shape = RoundedCornerShape(8.dp)
    ) {
        Image(
            painter = rememberAsyncImagePainter(model = product.image),
            contentDescription = product.name,
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp)
                .fillMaxWidth()
                .background(BaseColor),
        )

        SpacerVertical(height = 4)
        Column(
            modifier = Modifier
                .padding(horizontal = 8.dp)
                .wrapContentHeight(),
            horizontalAlignment = Alignment.Start
        ) {
            Title(title = product.name.toString(), style = AppTypography.titleSmall)
            SpacerVertical(height = 4)
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Top
            ) {
                Column(
                    verticalArrangement = Arrangement.SpaceEvenly,
                    horizontalAlignment = Alignment.Start
                ) {
                    Body(
                        title = "${product.price.toString()}  Egp",
                        style = AppTypography.bodySmall
                    )


                }

                if (!isSearchScreen) {
                    if (product.discount!! > 0) {
                        Body(
                            title = "${product.discount.toString()} %",
                            style = AppTypography.bodySmall,
                            color = Red
                        )
                    }
                }

            }
            SpacerVertical(height = 6)
            Row(
                modifier = Modifier
                    .padding(horizontal = 4.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                ClickableIcon(
                    painter = painterResource(id = R.drawable.ic_unselected_fav),
                    contentDescription = "",
                    modifier = Modifier
                        .clip(CircleShape)
                        .clickable { }

                )


                IconButton(
                    onClick = {

                        Toast.makeText(context,
                            product.inCart.toString(),
                            Toast.LENGTH_SHORT
                        ).show()

                        onClickItemCart(product.id!!)

//                        if(product.id == addOrRemoveUiState.addRemoveCartItemDTO!!.data!!.product!!.id){
//                            Toast.makeText(context,
//                                addOrRemoveUiState.addRemoveCartItemDTO?.message.toString(), Toast.LENGTH_SHORT).show()
//                        }



                    },
                    modifier = Modifier
                        .clip(CircleShape)
                ) {

                    val cartIcon = if (product.inCart) {
                        painterResource(id = R.drawable.ic_selected_cart)
                    } else {
                        painterResource(id = R.drawable.ic_unselected_cart)
                    }

                    Icon(
                        painter = cartIcon,
                        contentDescription = ""
                    )
                }


            }

            SpacerVertical(height = 6)


        }
    }
}

@Composable
fun TextWithStrikethrough(text: String) {
    val annotatedString = buildAnnotatedString {
        withStyle(style = SpanStyle(textDecoration = TextDecoration.LineThrough)) {
            append(text)
        }
    }

    Text(text = annotatedString, style = AppTypography.bodySmall)
}

@Composable
fun CategoryItems(categories: List<CategoryDTO?>, navController: NavController) {
    LazyRow(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        categories.let {
            items(it) { category ->
                CategoryItem(category, navController = navController)
            }
        }
    }
}

@Composable
fun CategoryItem(
    category: CategoryDTO?,
    navController: NavController
) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(8.dp))
            .background(CardBackgroundColor)
            .padding(8.dp)
            .clickable { navController.navigateToProductsByCategoryScreen(id = category?.id!!) },
        contentAlignment = Alignment.Center
    ) {
        Title(
            title = category?.name.toString(),
            style = AppTypography.labelLarge,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun CategoryHeader(onClickCategories: () -> Unit) {
    Row(
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {

        Header(text = stringResource(id = R.string.shopping_by_category))
        ClickableHeader(text = stringResource(id = R.string.see_all)) { onClickCategories() }


    }
}

@Composable
fun ProductHeader(onClickProducts: () -> Unit) {
    Row(
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {

        Header(text = stringResource(id = R.string.new_products))
        ClickableHeader(text = stringResource(id = R.string.see_all)) { onClickProducts() }


    }
}


@Composable
private fun Header(text: String) {
    Title(title = text, style = AppTypography.bodyLarge)
}

@Composable
private fun ClickableHeader(
    modifier: Modifier = Modifier,
    text: String,
    onClick: () -> Unit,
) {
    Body(
        title = text,
        style = AppTypography.labelLarge,
        modifier = modifier
            .clip(RoundedCornerShape(8.dp))
            .clickable { onClick() },
        color = AccentColor
    )
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun SliderImage(
    banners: List<BannerDTO?>
) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        val pagerState = rememberPagerState()
        AutoSliderImage(pagerState)
        SliderImageHorizontal(pagerState, banners)
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun AutoSliderImage(pagerState: PagerState) {
    LaunchedEffect(key1 = Unit) {
        while (pagerState.pageCount > pagerState.currentPage) {
            yield()
            delay(2000)
            tween<Float>(500)
            pagerState.animateScrollToPage(
                page = if (pagerState.pageCount != 0) {
                    (pagerState.currentPage + 1) % pagerState.pageCount
                } else pagerState.currentPage + 1
            )
        }
    }
}

@ExperimentalPagerApi
@Composable
private fun SliderImageHorizontal(
    state: PagerState,
    banners: List<BannerDTO?>
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        HorizontalSlider(banners, state)
        IndicatorOfSliderImage(state = state)
    }
}

@ExperimentalPagerApi
@Composable
private fun HorizontalSlider(
    banners: List<BannerDTO?>,
    state: PagerState
) {
    HorizontalPager(
        count = banners.size,
        state = state,
        itemSpacing = 16.dp,
        contentPadding = PaddingValues(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxSize(),
        key = { index ->
            "${banners[index]?.image?.length}-${index}"
        }
    ) { index ->
        SliderImage(banners, index)
    }
}

@Composable
private fun SliderImage(
    banners: List<BannerDTO?>,
    index: Int
) {
    Image(
        painter = rememberAsyncImagePainter(model = banners[index]!!.image),
        contentDescription = "",
        modifier = Modifier
            .height(200.dp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp)),
        contentScale = ContentScale.Fit
    )
}

@ExperimentalPagerApi
@Composable
private fun IndicatorOfSliderImage(
    state: PagerState,
) {
    HorizontalPagerIndicator(
        pagerState = state,
        pageCount = state.pageCount,
        modifier = Modifier.padding(vertical = 4.dp, horizontal = 16.dp),
        activeColor = AccentColor,
        inactiveColor = SecondaryTextColor,
    )
}

@Composable
fun AppBar(onClickNotificationButton: () -> Unit) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
    ) {

        Row(verticalAlignment = Alignment.CenterVertically) {
            ProfileImage(painter = painterResource(id = R.drawable.eg_flag))
            SpacerHorizontal(width = 2)
            SpannableTextExample(fText = "Hi,", sText = "Mostafa")

        }

        IconButton(
            onClick = { onClickNotificationButton() },
            modifier = Modifier
                .clip(CircleShape)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_notification),
                contentDescription = ""
            )
        }
    }
}


