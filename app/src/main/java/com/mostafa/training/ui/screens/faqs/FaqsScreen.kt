package com.mostafa.training.ui.screens.faqs

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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.mostafa.training.R
import com.mostafa.training.data.remote.dto.FaqsData
import com.mostafa.training.ui.components.AppBar
import com.mostafa.training.ui.components.Body
import com.mostafa.training.ui.components.CheckUiState
import com.mostafa.training.ui.components.SpacerVertical
import com.mostafa.training.ui.components.Title
import com.mostafa.training.ui.screens.profile.ProfileViewModel
import com.mostafa.training.ui.theme.AppTypography
import org.koin.androidx.compose.getViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FaqsScreen(navController: NavController) {

    val viewModel: ProfileViewModel = getViewModel()
    viewModel.getFaqs()
    val faqsUiState by viewModel.faqsUiState.collectAsState()

    Scaffold(
        topBar = {
            Surface(
                elevation = 1.dp,
            ) {
                AppBar(
                    title = "Faqs",
                    painter = painterResource(id = R.drawable.ic_left_back)
                ) { navController.popBackStack() }
            }
        }) { paddingValues ->

        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            CheckUiState(
                isLoading = faqsUiState.isLoading, error = faqsUiState.error,
                data = faqsUiState.data
            ) {
                FaqsContainer(it.data!!.data, paddingValues)
            }
        }
    }
}

@Composable
fun FaqsContainer(data: List<FaqsData>?, paddingValues: PaddingValues) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        contentPadding = PaddingValues(
            top = paddingValues.calculateTopPadding(),
            start = 8.dp,
            end = 8.dp,
            bottom = 8.dp
        ),
    ) {
        items(data!!) { faqs ->
            FaqsItem(faqs)
        }
    }
}

@Composable
fun FaqsItem(faqs: FaqsData) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .background(Color.LightGray, shape = RoundedCornerShape(8.dp))
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalAlignment = Alignment.Start,
            modifier = Modifier.padding(8.dp)
        ) {
            Title(
                title = faqs.question.toString(),
                style = AppTypography.titleMedium,
                color = Color.White
            )
            SpacerVertical(height = 8)
            Body(
                title = faqs.answer.toString(),
                style = AppTypography.bodyMedium,
                maxLine = 2,
                color = Color.White
            )
        }
    }
}
