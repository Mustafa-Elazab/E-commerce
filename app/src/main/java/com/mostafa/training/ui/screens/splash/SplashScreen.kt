package com.mostafa.training.ui.screens.splash

import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.mostafa.training.NormalTextComponent
import com.mostafa.training.R
import com.mostafa.training.ui.components.SpacerVertical
import com.mostafa.training.ui.components.Title
import com.mostafa.training.ui.theme.AccentColor
import com.mostafa.training.ui.theme.WhiteColor
import com.mostafa.training.ui.theme.robotoBold
import com.mostafa.training.utils.Screen
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavController) {

    val scale = remember {
        Animatable(0f)
    }

    LaunchedEffect(key1 = true) {
        scale.animateTo(targetValue = .5f, animationSpec = tween(
            durationMillis = 500,
            easing = {
                OvershootInterpolator(2f).getInterpolation(it)
            }
        ))
        delay(2000)
        navController.navigate(Screen.Home.route) {
            popUpTo(navController.graph.startDestinationRoute!!) {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }
    }
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .background(AccentColor)
    ) {

        Image(
            painter = painterResource(id = R.drawable.store),
            contentDescription = "app_logo",
            modifier = Modifier
                .width(300.dp)
                .height(300.dp)
                .scale(scale.value)
        )
        SpacerVertical(height = 8)
        
        NormalTextComponent(value = "E-Commerce", fontSize = 40, textAlign = TextAlign.Center
        , textColor = WhiteColor, fontFamily = robotoBold
        )


    }
}