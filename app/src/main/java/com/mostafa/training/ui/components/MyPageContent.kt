package com.mostafa.training.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp
import com.mostafa.training.ui.theme.AppTypography
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBar(
    title: String,
    painter: Painter,
    onClickBack: () -> Unit
) {

    TopAppBar(
        modifier = Modifier.background(Color.Blue),
        title = {
            Title(
                title = title.toString(),
                style = AppTypography.titleMedium
            )
        },
        navigationIcon = {
            IconButton(
                onClick = { onClickBack() }
            ) {
                Icon(
                    painter = painter,
                    contentDescription = "Previous screen",
                    modifier = Modifier
                        .clip(RoundedCornerShape(8.dp))
                        .background(Color.LightGray)
                        .fillMaxSize()
                        .padding(4.dp)
                )
            }
        }
    )
}

