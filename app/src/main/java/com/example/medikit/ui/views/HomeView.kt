package com.example.medikit.ui.views
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PhotoCamera
import androidx.compose.material.icons.filled.Image
import androidx.compose.material.icons.filled.Lightbulb
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.medikit.ui.components.*

@Composable
fun HomeView(navController: NavController, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp, vertical = 16.dp)
            .verticalScroll(rememberScrollState()), // scroll si es necesario
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Título principal
        Text(
            text = "Analiza tu herida",
            style = MaterialTheme.typography.headlineMedium.copy(
                fontWeight = FontWeight.Bold,
                color = Color(0xFF111827)
            ),
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Subtítulo
        Text(
            text = "Sube o toma una foto para recibir un análisis instantáneo con IA",
            style = MaterialTheme.typography.bodyLarge.copy(
                color = Color(0xFF6B7280)
            ),
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Placeholder cuadrado
        ImagePlaceholder(
            onClick = { println("Image Placeholder Clicked") },
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f) // 👈 siempre cuadrado
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Botón principal
        PrimaryButton(
            text = "Tomar foto",
            icon = Icons.Filled.PhotoCamera,
            onClick = { navController.navigate("wound") }
        )

        Spacer(modifier = Modifier.height(12.dp))

        // Botón secundario
        SecondaryButton(
            text = "Elegir desde galería",
            icon = Icons.Filled.Image,
            onClick = { println("Choose from Gallery Clicked") }
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Tarjeta de consejo
        AnnotationCard(
            icon = Icons.Filled.Lightbulb,
            title = "Consejo para la fotografía",
            description = "Asegúrate de tener buena iluminación, limpiar el área " +
                    "y tomar la foto lo más enfocada posible"
        )
    }
}