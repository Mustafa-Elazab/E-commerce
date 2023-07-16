package com.mostafa.training.ui.screens.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.modifier.modifierLocalProvider
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.mostafa.training.data.remote.dto.ProfileData
import com.mostafa.training.ui.components.CheckUiState
import com.mostafa.training.ui.components.ProfileImage
import com.mostafa.training.ui.components.ProfileRow
import com.mostafa.training.ui.components.SpacerVertical
import com.mostafa.training.ui.components.Title
import com.mostafa.training.ui.theme.AppTypography
import org.koin.androidx.compose.getViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun ProfileScreen(
    calculateBottomPadding: Dp,
    navController: NavController
) {

    val viewModel: ProfileViewModel = koinViewModel()
    val profileUiState = viewModel.profileUiState.collectAsState().value

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {


        CheckUiState(
            isLoading = profileUiState.isLoading,
            error = profileUiState.error,
            data = profileUiState.profileData
        ) {

            ProfileContainer(
                navController,
                profileData = profileUiState.profileData
            )

        }


    }
}

@Composable
fun ProfileContainer(navController: NavController, profileData: ProfileData?) {
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
    SpacerVertical(height = 32)

    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.Start
    ) {

        ProfileRow(
            title = "Edit Profile",
            painter = rememberVectorPainter(image = Icons.Default.Face),
            onClick = {

            }
        )

        ProfileRow(
            title = "My Orders",
            painter = rememberVectorPainter(image = Icons.Default.Face),
            onClick = {

            }
        )

        ProfileRow(
            title = "Wallet",
            painter = rememberVectorPainter(image = Icons.Default.Face),
            onClick = {

            }
        )

        ProfileRow(
            title = "Vouchers",
            painter = rememberVectorPainter(image = Icons.Default.Face),
            onClick = {

            }
        )

        ProfileRow(
            title = "My Address",
            painter = rememberVectorPainter(image = Icons.Default.Face),
           onClick = {

           }
        )

        ProfileRow(
            title = "Log Out",
            painter = rememberVectorPainter(image = Icons.Default.Face),
            onClick = {

            }
        )


    }
}

@Composable
fun HorizontalLine() {
    Box(
        modifier = Modifier
            .fillMaxWidth(.95f)
            .height(height = 1.dp)
            .background(Color.Black)
    )

}
