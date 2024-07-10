package com.uam.bolsatrabajo.compose.registroEmpresa

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
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.ui.PlayerView
import com.google.relay.compose.RelayText
import com.uam.bolsatrabajo.R
import com.uam.bolsatrabajo.bienvenida.montserrat
import kotlinx.coroutines.delay

@Composable
fun TermsAndConditionsScreen(navController: NavController) {
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current

    val exoPlayer = remember {
        ExoPlayer.Builder(context).build().apply {
            setMediaItem(MediaItem.fromUri(Uri.parse("android.resource://${context.packageName}/raw/uam_3")))
            playWhenReady = true
            repeatMode = ExoPlayer.REPEAT_MODE_ALL
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
                Lifecycle.Event.ON_PAUSE -> exoPlayer.playWhenReady = true
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
                        colors = listOf(
                            Color(0xFF0099A8).copy(alpha = 0.8f),
                            Color.Black.copy(alpha = 0.8f),
                            Color.Black.copy(alpha = 0.8f),
                            Color(0xFF0099A8).copy(alpha = 0.8f)
                        ),
                        startY = 0f,
                        endY = Float.POSITIVE_INFINITY
                    )
                )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Transparent)
                    .padding(horizontal = 30.dp, vertical = 50.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                var visible by remember { mutableStateOf(false) }

                LaunchedEffect(Unit) {
                    delay(500)
                    visible = true
                }

                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    AnimatedVisibility(
                        visible = visible,
                        enter = fadeIn() + slideInVertically(),
                        exit = fadeOut() + slideOutVertically()
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.uamletras),
                            contentDescription = null,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(50.dp)
                                .align(alignment = Alignment.CenterHorizontally)
                        )

                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    AnimatedVisibility(
                        visible = visible,
                        enter = fadeIn() + slideInVertically(),
                        exit = fadeOut() + slideOutVertically()
                    ) {
                        RelayText(
                            content = "Términos y Condiciones",
                            fontSize = 24.sp,
                            fontFamily = montserrat,
                            color = Color.White,
                            height = 1.2.em,
                            fontWeight = FontWeight.Bold,
                            maxLines = -1,
                            modifier = Modifier.fillMaxWidth()
                        )
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    AnimatedVisibility(
                        visible = visible,
                        enter = fadeIn() + slideInVertically(),
                        exit = fadeOut() + slideOutVertically()
                    ) {
                        Text(
                            text = """
                            Al registrarse, la empresa acepta:
                            
                            • Proporcionar información veraz.
                            
                            • Usar la plataforma solo para contratación.
                            
                            • Manejar confidencialmente los datos de candidatos.
                            
                            • Actualizar y eliminar vacantes no disponibles.
                            
                            • Reconocer los derechos de la UAM sobre la plataforma.
                            
                            • Posibles modificaciones a estos términos.
                            
                            • Eximir a la UAM de responsabilidades por el uso de la plataforma.
                            """.trimIndent(),
                            fontFamily = montserrat,
                            fontWeight = FontWeight.Medium,
                            fontSize = 16.sp,
                            color = Color.White,
                            modifier = Modifier.padding(vertical = 8.dp),
                            textAlign = TextAlign.Left
                        )
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    AnimatedVisibility(
                        visible = visible,
                        enter = fadeIn() + slideInVertically(),
                        exit = fadeOut() + slideOutVertically()
                    ) {
                        Button(
                            onClick = { navController.navigate("soli_empresa") },
                            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF0099A8)),
                            shape = MaterialTheme.shapes.small,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            RelayText(
                                content = "Aceptar y Continuar",
                                fontSize = 12.sp,
                                fontFamily = montserrat,
                                color = Color.White,
                                height = 1.2.em,
                                fontWeight = FontWeight.SemiBold,
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
                                )
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    AnimatedVisibility(
                        visible = visible,
                        enter = fadeIn() + slideInVertically(),
                        exit = fadeOut() + slideOutVertically()
                    ) {
                        Text(
                            text = "Al hacer clic en \"Aceptar y Continuar\", confirma haber leído, entendido y aceptado estos términos.",
                            fontSize = 12.sp,
                            color = Color.White,
                            fontFamily = montserrat,
                            fontWeight = FontWeight.Medium,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.padding(bottom = 16.dp)
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewTermsAndConditionsScreen() {
    val navController = rememberNavController()
    TermsAndConditionsScreen(navController)
}
