package com.uam.bolsatrabajo.compose.inicio

import android.net.Uri
import android.widget.FrameLayout
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.ui.PlayerView
import com.google.relay.compose.Case
import com.google.relay.compose.RelayText
import com.google.relay.compose.relayDropShadow
import com.uam.bolsatrabajo.R
import com.uam.bolsatrabajo.bienvenida.montserrat
import kotlinx.coroutines.delay

@Composable
fun WelcomeScreen(onIngresarClick: () -> Unit) {
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current

    val exoPlayer = remember {
        ExoPlayer.Builder(context).build().apply {
            setMediaItem(MediaItem.fromUri(Uri.parse("android.resource://${context.packageName}/raw/uam_3")))
            playWhenReady = true
            repeatMode = ExoPlayer.REPEAT_MODE_ALL // Repite el video indefinidamente
            prepare()
        }
    }

    DisposableEffect(
        AndroidView(
            factory = { ctx ->
                PlayerView(ctx).apply {
                    player = exoPlayer
                    useController = false
                    layoutParams = FrameLayout.LayoutParams(
                        FrameLayout.LayoutParams.MATCH_PARENT,
                        FrameLayout.LayoutParams.MATCH_PARENT
                    )
                }
            }
        )
    ) {
        val observer = LifecycleEventObserver { _, event ->
            when (event) {
                Lifecycle.Event.ON_PAUSE -> exoPlayer.playWhenReady = true // Mantén el video en reproducción
                Lifecycle.Event.ON_RESUME -> exoPlayer.playWhenReady = true
                else -> Unit
            }
        }
        lifecycleOwner.lifecycle.addObserver(observer)
        onDispose {
            exoPlayer.release()
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        AndroidView(
            factory = {
                PlayerView(it).apply {
                    player = exoPlayer
                    useController = false
                    layoutParams = FrameLayout.LayoutParams(
                        FrameLayout.LayoutParams.MATCH_PARENT,
                        FrameLayout.LayoutParams.MATCH_PARENT
                    )
                }
            }
        )

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(Color(0xFF0099A8).copy(alpha = 0.6f), Color.Black),
                        startY = 0f,
                        endY = Float.POSITIVE_INFINITY
                    )
                )
        ){
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Transparent)
                    .padding(horizontal = 30.dp, vertical = 100.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                var visible by remember { mutableStateOf(false) }

                LaunchedEffect(Unit) {
                    delay(500) // Retraso para hacer las animaciones más visibles
                    visible = true
                }


                AnimatedVisibility(
                    visible = visible,
                    enter = fadeIn() + slideInVertically(),
                    exit = fadeOut() + slideOutVertically()
                ) {
                    RelayText(
                        content = "BOLSA DE TRABAJO UAM",
                        fontSize = 44.1327018737793.sp,
                        fontFamily = montserrat,
                        color = Color(
                            alpha = 255,
                            red = 255,
                            green = 255,
                            blue = 255
                        ),
                        height = 1.1562499237060546.em,
                        fontWeight = FontWeight.ExtraBold,
                        case = Case.Upper,
                        maxLines = -1,
                        shadow = Shadow(
                            color = Color(
                                alpha = 127,
                                red = 0,
                                green = 0,
                                blue = 0
                            ),
                            offset = Offset(
                                x = 0.0f,
                                y = 5.12f
                            ),
                            blurRadius = 5.12f
                        ),
                        modifier = Modifier.fillMaxWidth().requiredHeight(80.dp)
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                AnimatedVisibility(
                    visible = visible,
                    enter = fadeIn() + slideInVertically(),
                    exit = fadeOut() + slideOutVertically()
                ) {

                    Image(
                        painter = painterResource(id = R.drawable.logoazul),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        alignment = Alignment.Center,
                        modifier = Modifier
                            .width(780.dp)
                            .height(289.68.dp)
                    )

                }

                Spacer(modifier = Modifier.height(16.dp))

                AnimatedVisibility(
                    visible = visible,
                    enter = fadeIn() + slideInVertically(),
                    exit = fadeOut() + slideOutVertically()
                ) {
                    Button(
                        onClick = onIngresarClick,
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF0099A8)),
                        shape = RoundedCornerShape(50),
                        modifier = Modifier
                            .width(215.75.dp)
                            .height(49.79.dp)
                            .align(Alignment.CenterHorizontally)
                            .relayDropShadow(color = Color.Black, blur = 30.dp)
                    ) {
                        RelayText(
                            content = "Ingresar",
                            fontSize = 16.549762725830078.sp,
                            fontFamily = montserrat,
                            color = Color(
                                alpha = 255,
                                red = 255,
                                green = 255,
                                blue = 255
                            ),
                            height = 1.218999954130777.em,
                            textAlign = TextAlign.Left,
                            fontWeight = FontWeight(700.0.toInt()),
                            maxLines = -1,
                            shadow = Shadow(
                                color = Color(
                                    alpha = 127,
                                    red = 0,
                                    green = 0,
                                    blue = 0
                                ),
                                offset = Offset(
                                    x = 0.0f,
                                    y = 5.516587734222412f
                                ),
                                blurRadius = 5.516587734222412f
                            ),

                        )


                    }
                }
                Spacer(modifier = Modifier.height(16.dp))

                AnimatedVisibility(
                    visible = visible,
                    enter = fadeIn() + slideInVertically(),
                    exit = fadeOut() + slideOutVertically()
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.iso_21001), // Reemplaza con tu imagen de certificaciones
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxSize()
                            .align(Alignment.CenterHorizontally)
                    )
                }
            }
        }
    }
}

