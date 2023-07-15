package com.mostafa.training.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.VectorPainter
import androidx.compose.ui.unit.dp
import com.mostafa.training.ui.theme.AppTypography

@Composable
fun ProfileRow(title: String, painter: VectorPainter,onClick : ()-> Unit) {

    Box(modifier = Modifier.clickable {
        onClick()
    }) {
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(8.dp))
                    .size(60.dp)
                    .padding(8.dp)
                    .border(
                        border = BorderStroke(.5.dp, Color.Black),
                        shape = RoundedCornerShape(8.dp)
                    )
            ) {
                Icon(
                    modifier = Modifier
                        .size(30.dp)
                        .align(Alignment.Center),
                    painter = painter,
                    contentDescription = ""
                )
            }

            Title(title = title, style = AppTypography.titleMedium)


        }
    }

}