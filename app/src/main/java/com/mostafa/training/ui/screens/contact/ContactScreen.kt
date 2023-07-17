package com.mostafa.training.ui.screens.contact

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.mostafa.training.R
import com.mostafa.training.data.remote.dto.ContactData
import com.mostafa.training.ui.components.AppBar
import com.mostafa.training.ui.components.Body
import com.mostafa.training.ui.components.CheckUiState
import com.mostafa.training.ui.screens.profile.ProfileViewModel
import org.koin.androidx.compose.getViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContactScreen(navController: NavController) {

    val viewModel: ProfileViewModel = getViewModel()
    viewModel.getContact()
    val contactUiState by viewModel.contactUiState.collectAsState()

    Scaffold(
        topBar = {
            Surface(
                elevation = 1.dp,
            ) {
                AppBar(
                    title = "Contact",
                    painter = painterResource(id = R.drawable.ic_left_back)
                ) { navController.popBackStack() }
            }
        }) { paddingValues ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = paddingValues.calculateTopPadding()),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            CheckUiState(
                isLoading = contactUiState.isLoading, error = contactUiState.error,
                data = contactUiState.data
            ) { data ->
                ContactContainer(data.data!!.data, paddingValues)
            }
        }
    }
}

@Composable
fun ContactContainer(data: List<ContactData>?, paddingValues: PaddingValues) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        contentPadding = PaddingValues(
            top = paddingValues.calculateTopPadding(), start = 8.dp,
            end = 8.dp,
            bottom = 8.dp
        )
    ) {
        items(data!!) { contact ->
            ContactItems(contact)
        }
    }
}

@Composable
fun ContactItems(contact: ContactData) {
    Box(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
    ) {
        Column() {
            Body(title = contact.value.toString())
            Image(painter = rememberAsyncImagePainter(model = contact.image), contentDescription = "")
            
        }
    }
}
