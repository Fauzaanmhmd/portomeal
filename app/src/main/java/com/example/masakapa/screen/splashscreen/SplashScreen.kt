package com.example.masakapa.screen.splashscreen

import androidx.compose.runtime.Composable
import com.example.masakapa.screen.extension.ImageSliderWithIndicator
import com.plcoding.typesafecomposenavigation.R

@Composable
fun MyScreen(onNavigate: () -> Unit) {
    val images = listOf(
        R.drawable.pizza,
        R.drawable.ic_splash_1,
        R.drawable.ic_splash
    )

    ImageSliderWithIndicator(images = images, onNavigate = onNavigate)
}