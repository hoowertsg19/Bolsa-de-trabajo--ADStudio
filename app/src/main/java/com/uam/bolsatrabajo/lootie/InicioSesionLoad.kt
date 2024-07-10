package com.uam.bolsatrabajo.lootie

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.*
import com.uam.bolsatrabajo.R
import com.uam.bolsatrabajo.ui.theme.SemiTransparentBlue

@Composable
fun InicioSesionLoad() {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.iniciosesionload))
    val progress by animateLottieCompositionAsState(composition)

    Box(
        modifier = Modifier.fillMaxSize().size(400.dp).background(Color.White),
        contentAlignment = Alignment.Center
    ) {
        LottieAnimation(
            composition = composition,
            progress = { progress }
        )
    }
}
