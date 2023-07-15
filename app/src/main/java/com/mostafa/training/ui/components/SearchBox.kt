package com.mostafa.training.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.mostafa.training.R
import com.mostafa.training.ui.theme.AppTypography
import com.mostafa.training.ui.theme.CardBackgroundColor
import com.mostafa.training.ui.theme.PrimaryTextAndIconColor
import com.mostafa.training.ui.theme.SecondaryTextColor

@Composable
fun SearchBox(
    modifier: Modifier = Modifier,
    onClickSearchBox: () -> Unit
) {
    Row(
        modifier = modifier
            .padding(horizontal = 16.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(CardBackgroundColor)
            .height(56.dp)
            .fillMaxWidth()
            .clickable { onClickSearchBox() },
        verticalAlignment = Alignment.CenterVertically,

        ) {
        SpacerHorizontal(width = 16)
        StaticIcon(
            painter = painterResource(id = R.drawable.ic_search),
            contentDescription = stringResource(id = R.string.search),
            tint = SecondaryTextColor
        )
        SpacerHorizontal(width = 8)
        Body(
            title = stringResource(id = R.string.search),
            style = AppTypography.labelMedium,
            color = SecondaryTextColor,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun Body(
    title: String,
    maxLine: Int = 1,
    style: TextStyle = AppTypography.bodySmall,
    color: Color = PrimaryTextAndIconColor,
    textAlign: TextAlign = TextAlign.Start,
    modifier: Modifier = Modifier,
) {
    Title(
        modifier = modifier,
        title = title,
        maxLine = maxLine,
        style = style,
        color = color,
        textAlign = textAlign
    )
}

@Composable
fun Title(
    title: String,
    maxLine: Int = 1,
    style: TextStyle = AppTypography.titleMedium,
    color: Color = PrimaryTextAndIconColor,
    textAlign: TextAlign = TextAlign.Start,
    modifier: Modifier = Modifier,
) {
    Text(
        modifier = modifier,
        text = title,
        maxLines = maxLine,
        color = color,
        overflow = TextOverflow.Ellipsis,
        style = style,
        textAlign = textAlign
    )
}

@Composable
fun StaticIcon(
    painter: Painter,
    contentDescription: String,
    modifier: Modifier = Modifier,
    tint: Color = PrimaryTextAndIconColor
) {
    Icon(
        modifier = modifier,
        painter = painter,
        contentDescription = contentDescription,
        tint = tint
    )
}
