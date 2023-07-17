package com.mostafa.training.ui.components

import android.graphics.drawable.PaintDrawable
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.VectorPainter
import androidx.compose.ui.res.painterResource
import com.mostafa.training.ui.theme.AppTypography

@Composable
fun ProfileRow(title: String, painter: Painter, onClick: () -> Unit) {

    Box(modifier = Modifier.clickable {
        onClick()
    }) {
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {


            Image(
                modifier = Modifier.wrapContentSize(),
                painter = painter,
                contentDescription = ""
            )


            Body(title = title, style = AppTypography.bodyMedium)


        }
    }

}