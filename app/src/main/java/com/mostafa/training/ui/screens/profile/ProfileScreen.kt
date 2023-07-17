package com.mostafa.training.ui.screens.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.mostafa.training.R
import com.mostafa.training.data.remote.dto.ProfileData
import com.mostafa.training.ui.components.Body
import com.mostafa.training.ui.components.CheckUiState
import com.mostafa.training.ui.components.ProfileImage
import com.mostafa.training.ui.components.ProfileRow
import com.mostafa.training.ui.components.SpacerVertical
import com.mostafa.training.ui.components.Title
import com.mostafa.training.ui.screens.contact.navigateToContactScreen
import com.mostafa.training.ui.screens.faqs.navigateToFaqsScreen
import com.mostafa.training.ui.screens.favorites.navigateToFavoritesScreen
import com.mostafa.training.ui.theme.AppTypography
import com.mostafa.training.ui.theme.BgColor
import org.koin.androidx.compose.getViewModel
import org.koin.androidx.compose.koinViewModel
import java.math.BigDecimal
import java.math.RoundingMode

@Composable
fun ProfileScreen(
    calculateBottomPadding: Dp,
    navController: NavController
) {

    val viewModel: ProfileViewModel = getViewModel()
    val profileUiState by viewModel.profileUiState.collectAsState()

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 16.dp, end = 16.dp, bottom = calculateBottomPadding + 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {


        item {
            CheckUiState(
                isLoading = profileUiState.isLoading,
                error = profileUiState.error,
                data = profileUiState.profileData,
                sizeOfProgress = 30
            ) {

                ProfileContainer(
                    navController,
                    profileData = profileUiState.profileData,
                    onClickFavorites = navController::navigateToFavoritesScreen,
                    onContactClick = navController::navigateToContactScreen,
                    onFaqsClick = navController::navigateToFaqsScreen
                )

            }
        }


    }
}

@Composable
fun ProfileContainer(
    navController: NavController,
    profileData: ProfileData?,
    onClickFavorites: () -> Unit,
    onFaqsClick: () -> Unit,
    onContactClick: () -> Unit
) {
    Column(
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        SpacerVertical(height = 20)
        ProfileImage(
            painter = rememberAsyncImagePainter(model = profileData!!.image.toString()),
            size = 100, borderColor = Color.Green,
        )
        Title(
            title = profileData.name.toString(),
            textAlign = TextAlign.Center,
            style = AppTypography.titleLarge
        )
    }
    SpacerVertical(height = 16)

    Box(
        modifier = Modifier
            .background(BgColor, shape = RoundedCornerShape(8.dp))
            .fillMaxWidth()
            .height(80.dp)

    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            val iconPainter = painterResource(id = R.drawable.wallet)
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .wrapContentHeight(Alignment.CenterVertically)
            ) {
                Image(
                    modifier = Modifier.wrapContentSize(),
                    painter = iconPainter,
                    contentDescription = ""
                )

                Body(title = "My Wallet", style = AppTypography.bodyMedium)
            }

            Title(
                title = BigDecimal(profileData?.credit!!)
                    .setScale(2, RoundingMode.HALF_UP).toString(), style = AppTypography.titleLarge
            )
        }
    }


    SpacerVertical(height = 8)

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(1f)
                    .padding(end = 4.dp)
                    .background(BgColor, shape = RoundedCornerShape(8.dp))

            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(8.dp),
                    verticalArrangement = Arrangement.SpaceEvenly,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(painter = painterResource(id = R.drawable.map), contentDescription = "")
                    Body(title = "Orders", style = AppTypography.bodyLarge)
                }
            }

            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(1f)
                    .padding(horizontal = 4.dp)
                    .background(BgColor, shape = RoundedCornerShape(8.dp))

            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(8.dp),
                    verticalArrangement = Arrangement.SpaceEvenly,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(painter = painterResource(id = R.drawable.map), contentDescription = "")
                    Body(title = "Addresses", style = AppTypography.bodyLarge)
                }
            }

            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(1f)
                    .padding(start = 4.dp)
                    .background(BgColor, shape = RoundedCornerShape(8.dp))

            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(8.dp),
                    verticalArrangement = Arrangement.SpaceEvenly,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.credit_card),
                        contentDescription = ""
                    )
                    Body(title = "Cards", style = AppTypography.bodyLarge)
                }
            }
        }
    }


    SpacerVertical(height = 32)

    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.Start
    ) {


        ProfileRow(
            title = "Favorites",
            painter = painterResource(id = R.drawable.privacy),
            onClick = onClickFavorites
        )

        HorizontalLine()
        ProfileRow(
            title = "About",
            painter = painterResource(id = R.drawable.about),
            onClick = onFaqsClick
        )
        HorizontalLine()
        ProfileRow(
            title = "Contact Us",
            painter = painterResource(id = R.drawable.contact),
            onClick = onContactClick
        )
        HorizontalLine()

        ProfileRow(
            title = "Settings",
            painter = painterResource(id = R.drawable.setting),
            onClick = {

            }
        )


    }
}

@Composable
fun HorizontalLine() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(height = .2.dp)
            .background(Color.Black)
    )

}
