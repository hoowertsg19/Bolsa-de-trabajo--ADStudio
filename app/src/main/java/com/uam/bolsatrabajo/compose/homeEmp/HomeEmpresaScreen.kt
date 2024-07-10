package com.uam.bolsatrabajo.compose.homeEmp

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.BottomNavigation
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.BottomNavigationItem
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.Card
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Message
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.uam.bolsatrabajo.R
import com.uam.bolsatrabajo.model.Vacante
import com.uam.bolsatrabajo.ui.theme.BolsaTrabajoTheme
import com.uam.bolsatrabajo.viewmodel.HomeEmpresaViewModel


@Composable
fun HomeEmpresaScreen(navController: NavHostController, empresaId: Long) {
    val viewModel: HomeEmpresaViewModel = viewModel()
    val empresa = viewModel.empresa.observeAsState()
    val vacantes = viewModel.vacantes.observeAsState()

    Scaffold(
        bottomBar = {
            BottomAppBar(navController = navController)
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .background(Color.White)
        ) {
            empresa.value?.let {
                TopBar(it.nombre)
                Spacer(modifier = Modifier.height(16.dp))
                CompanyBanner()
                Spacer(modifier = Modifier.height(16.dp))
                JobSummary(
                    remoteJobs = vacantes.value?.count { it.tipoVacante == "Remoto" } ?: 0,
                    fullTimeJobs = vacantes.value?.count { it.tipoVacante == "Tiempo Completo" } ?: 0,
                    partTimeJobs = vacantes.value?.count { it.tipoVacante == "Híbrido" } ?: 0
                )
                Spacer(modifier = Modifier.height(16.dp))
                RecentJobs(vacantes.value ?: listOf())
            } ?: run {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }
        }
    }

    // Llamar a obtener datos
    viewModel.obtenerEmpresaPorId(empresaId)
    viewModel.obtenerVacantesPorEmpresaId(empresaId)
}

@Composable
fun TopBar(companyName: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column {
            Text("Hola,", fontSize = 20.sp, color = Color.Black)
            Text(companyName, fontWeight = FontWeight.Bold, fontSize = 24.sp, color = Color.Black)
        }
        IconButton(onClick = { /* Acción para perfil */ }) {
            Icon(Icons.Default.AccountCircle, contentDescription = "Perfil", tint = Color.Black, modifier = Modifier.size(50.dp))
        }
    }
}

@Composable
fun CompanyBanner() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .height(180.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.uam_empresa_logo),
            contentDescription = "Para empresa",
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .size(200.dp)
        )
    }
}

@Composable
fun JobSummary(remoteJobs: Int, fullTimeJobs: Int, partTimeJobs: Int) {
    Column(modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)) {
        Text("Resumen de tipo de vacantes publicadas", fontSize = 16.sp, fontWeight = FontWeight.Bold, color = Color.Black)
        Spacer(modifier = Modifier.height(8.dp))
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            JobTypeCard("En la Empresa", fullTimeJobs, Color(0xFF80DEEA))
            JobTypeCard("Trabajos Remotos", remoteJobs, Color(0xFFCE93D8))
            JobTypeCard("Trabajos Híbridos", partTimeJobs, Color(0xFFFFCC80))
        }
    }
}

@Composable
fun JobTypeCard(title: String, count: Int, color: Color) {
    Card(
        backgroundColor = color,
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("$count", fontWeight = FontWeight.Bold, fontSize = 20.sp, color = Color.Black)
            Text(title, fontSize = 16.sp, color = Color.Black)
        }
    }
}

@Composable
fun RecentJobs(jobs: List<Vacante>) {
    Column(modifier = Modifier.padding(16.dp)) {
        Text("Trabajos Publicados Recientemente", fontSize = 16.sp, fontWeight = FontWeight.Bold, color = Color.Black)
        Spacer(modifier = Modifier.height(8.dp))
        if (jobs.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
                    .background(Color(0xFFF0F0F0))
                    .padding(16.dp),
                contentAlignment = Alignment.Center
            ) {
                Text("No hay nada nuevo...", fontSize = 16.sp, color = Color.Gray)
            }
        } else {
            LazyColumn {
                items(jobs) { job ->
                    JobCard(job)
                }
            }
        }
    }
}

@Composable
fun JobCard(job: Vacante) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(job.posicion, fontSize = 18.sp, color = Color.Black)
            Text(job.descripcion, fontSize = 16.sp, color = Color.Gray)
        }
    }
}

@Composable
fun BottomAppBar(navController: NavHostController) {
    BottomNavigation(
        backgroundColor = Color.White,
        contentColor = Color(0xFF0099A8)
    ) {
        BottomNavigationItem(
            icon = { Icon(Icons.Default.Home, contentDescription = "Home") },
            selected = true,
            onClick = { /* Acción de navegación a Home */ },
            selectedContentColor = Color(0xFF0099A8),
            unselectedContentColor = Color(0xFF0099A8)
        )
        BottomNavigationItem(
            icon = { Icon(Icons.Default.Add, contentDescription = "Agregar Vacante") },
            selected = false,
            onClick = { navController.navigate("add_job") },
            selectedContentColor = Color(0xFF0099A8),
            unselectedContentColor = Color(0xFF0099A8)
        )
        BottomNavigationItem(
            icon = { Icon(Icons.AutoMirrored.Filled.Message, contentDescription = "Mensajes") },
            selected = false,
            onClick = { /* Acción de navegación a Mensajes */ },
            selectedContentColor = Color(0xFF0099A8),
            unselectedContentColor = Color(0xFF0099A8)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun HomeEmpresaScreenPreview() {
    BolsaTrabajoTheme {
        HomeEmpresaScreen(navController = rememberNavController(), empresaId = 1)
    }
}