package com.uam.bolsatrabajo.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.uam.bolsatrabajo.compose.homeEmp.AddJobScreen
import com.uam.bolsatrabajo.compose.homeEmp.HomeEmpresaScreen
import com.uam.bolsatrabajo.compose.homeEmp.JobConfirmationScreen
import com.uam.bolsatrabajo.compose.homeEmp.JobDetailsScreen
import com.uam.bolsatrabajo.ui.theme.BolsaTrabajoTheme

@Composable
fun EmpresaApp(empresaId: Long) {
    val navController = rememberNavController()
    BolsaTrabajoTheme {
        NavHost(navController = navController, startDestination = "home_empresa/$empresaId") {
            composable(
                "home_empresa/{empresaId}",
                arguments = listOf(navArgument("empresaId") { type = NavType.LongType })
            ) { backStackEntry ->
                val id = backStackEntry.arguments?.getLong("empresaId")
                if (id != null) {
                    HomeEmpresaScreen(navController, empresaId = id)
                }
            }
            composable("add_job") {
                AddJobScreen(navController)
            }
            composable("job_details") {
                JobDetailsScreen(navController)
            }
            composable("job_confirmation") {
                JobConfirmationScreen(navController)
            }
        }
    }
}
