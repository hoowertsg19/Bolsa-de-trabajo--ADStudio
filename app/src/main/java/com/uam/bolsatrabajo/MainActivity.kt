package com.uam.bolsatrabajo

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.uam.bolsatrabajo.compose.*
import com.uam.bolsatrabajo.compose.homeEmp.HomeEmpresaScreen
import com.uam.bolsatrabajo.compose.homeParticipante.HomeScreen
import com.uam.bolsatrabajo.compose.inicio.LoginScreen
import com.uam.bolsatrabajo.compose.inicio.LoginScreenEmp
import com.uam.bolsatrabajo.compose.inicio.PantallaInicio
import com.uam.bolsatrabajo.compose.inicio.RegistrationScreen
import com.uam.bolsatrabajo.compose.inicio.WelcomeScreen
import com.uam.bolsatrabajo.compose.registroEmpresa.RegistrationScreenEmp
import com.uam.bolsatrabajo.compose.registroEmpresa.SoliEmpresa
import com.uam.bolsatrabajo.compose.registroEmpresa.TermsAndConditionsScreen
import com.uam.bolsatrabajo.compose.registroUsuario.RegistroCIF
import com.uam.bolsatrabajo.compose.registroUsuario.RegistroCorreo
import com.uam.bolsatrabajo.compose.registroUsuario.RegistroError
import com.uam.bolsatrabajo.compose.registroUsuario.RegistroExitoso
import com.uam.bolsatrabajo.compose.registroUsuario.RegistroInformacion
import com.uam.bolsatrabajo.compose.registroUsuario.RegistroParticipante
import com.uam.bolsatrabajo.lootie.PacManScreen
import com.uam.bolsatrabajo.lootie.SplashScreen
import com.uam.bolsatrabajo.ui.theme.BolsaTrabajoTheme
import com.uam.bolsatrabajo.util.SessionManager
import com.uam.bolsatrabajo.viewmodel.LoginViewModel
import com.uam.bolsatrabajo.viewmodel.LoginViewModelFactory
import com.uam.bolsatrabajo.viewmodel.LoginViewModelEmp
import com.uam.bolsatrabajo.viewmodel.RegistroEmpViewModel
import com.uam.bolsatrabajo.viewmodel.RegistroViewModel
import com.uam.bolsatrabajo.viewmodel.HomeEmpresaViewModel

class MainActivity : ComponentActivity() {
    private val registroViewModel: RegistroViewModel by viewModels()
    private val registroEmpViewModel: RegistroEmpViewModel by viewModels()
    private val loginViewModel: LoginViewModel by viewModels {
        LoginViewModelFactory(SessionManager(this))
    }
    private val loginViewModelEmp: LoginViewModelEmp by viewModels {
        LoginViewModelFactory(SessionManager(this))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BolsaTrabajoTheme {
                val navController = rememberNavController()
                AppNavigation(navController, registroViewModel, registroEmpViewModel, loginViewModel, loginViewModelEmp)
            }
        }
    }
}

@Composable
fun AppNavigation(navController: NavHostController, registroViewModel: RegistroViewModel, registroEmpViewModel: RegistroEmpViewModel, loginViewModel: LoginViewModel, loginViewModelEmp: LoginViewModelEmp) {
    NavHost(navController = navController, startDestination = "splash_screen") {
        composable("splash_screen") {
            SplashScreen(
                onAnimationFinish = {
                    Log.d("Navigation", "Navigating to transition_screen from splash_screen")
                    navController.navigate("transition_screen") {
                        popUpTo("splash_screen") { inclusive = true }
                    }
                }
            )
        }
        composable("transition_screen") {
            TransitionScreen(
                onTransitionFinish = {
                    Log.d("Navigation", "Navigating to welcome_screen from transition_screen")
                    navController.navigate("welcome_screen") {
                        popUpTo("transition_screen") { inclusive = true }
                    }
                }
            )
        }
        composable("welcome_screen") {
            WelcomeScreen(
                onIngresarClick = {
                    Log.d("Navigation", "Navigating to pantalla_inicio from welcome_screen")
                    navController.navigate("pantalla_inicio")
                }
            )
        }
        composable("pantalla_inicio") {
            PantallaInicio(
                onParticipanteClick = {
                    Log.d("Navigation", "Navigating to registration_screen from pantalla_inicio")
                    navController.navigate("registration_screen")
                },
                onEmpresaClick = {
                    Log.d("Navigation", "Navigating to registration_screen_emp from pantalla_inicio")
                    navController.navigate("registration_screen_emp")
                }
            )
        }
        composable("registration_screen") {
            RegistrationScreen(navController = navController)
        }
        composable("registration_screen_emp") {
            RegistrationScreenEmp(navController = navController)
        }
        composable("terms_and_conditions_screen") {
            TermsAndConditionsScreen(navController = navController)
        }
        composable("soli_empresa") {
            SoliEmpresa(navController = navController, viewModel = registroEmpViewModel)
        }
        composable("registro_participante") {
            RegistroParticipante(
                navController = navController,
                viewModel = registroViewModel,
                onCloseClick = {
                    Log.d("Navigation", "Navigating back from registro_participante")
                    navController.popBackStack()
                }
            )
        }
        composable("registro_cif") {
            RegistroCIF(
                navController = navController,
                viewModel = registroViewModel,
                onCloseClick = {
                    Log.d("Navigation", "Navigating back from registro_cif")
                    navController.popBackStack()
                }
            )
        }
        composable("registro_informacion") {
            RegistroInformacion(
                navController = navController,
                viewModel = registroViewModel,
                onCloseClick = {
                    Log.d("Navigation", "Navigating back from registro_informacion")
                    navController.popBackStack()
                }
            )
        }
        composable("registro_correo") {
            RegistroCorreo(
                navController = navController,
                viewModel = registroViewModel,
                onCloseClick = {
                    Log.d("Navigation", "Navigating back from registro_correo")
                    navController.popBackStack()
                }
            )
        }
        composable("pacman_screen") {
            PacManScreen(
                onAnimationFinish = {
                    Log.d("Navigation", "PacMan animation finished")
                    if (registroEmpViewModel.registroResult.value == "Registro exitoso_empresa") {
                        Log.d("Navigation", "Navigating to registro_exitoso_empresa from pacman_screen")
                        navController.navigate("registro_exitoso_empresa") {
                            popUpTo("pacman_screen") { inclusive = true }
                        }
                    } else {
                        Log.d("Navigation", "Navigating to registro_error_empresa from pacman_screen")
                        navController.navigate("registro_error_empresa")
                    }
                }
            )
        }
        composable("registro_exitoso") {
            RegistroExitoso(
                onContinueClick = {
                    Log.d("Navigation", "Navigating to login_screen from registro_exitoso")
                    navController.navigate("login_screen")
                }
            )
        }
        composable("registro_error") {
            RegistroError(
                onRetryClick = {
                    Log.d("Navigation", "Retrying registro from registro_error")
                    navController.navigate("registro_correo")
                }
            )
        }
        composable("login_screen") {
            LoginScreen(
                navController = navController,
                viewModel = loginViewModel,
                onForgotPasswordClick = {
                    Log.d("Navigation", "Forgot password clicked in login_screen")
                }
            )
        }
        composable("login_screen_emp") {
            LoginScreenEmp(
                navController = navController,
                viewModel = loginViewModelEmp,
                onForgotPasswordClick = {
                    Log.d("Navigation", "Forgot password clicked in login_screen_emp")
                }
            )
        }
        composable("home_screen") {
            HomeScreen(
                navController = navController,
                viewModel = loginViewModel
            )
        }
        composable("empresa_app/{empresaId}",
            arguments = listOf(navArgument("empresaId") { type = NavType.LongType })
        ) { backStackEntry ->
            val empresaId = backStackEntry.arguments?.getLong("empresaId")
            if (empresaId != null) {
                HomeEmpresaScreen(navController, empresaId)
            } else {
                Log.e("Navigation", "Invalid empresaId: ${backStackEntry.arguments?.getLong("empresaId")}")
            }
        }
    }
}
