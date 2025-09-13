package com.example.medikit.ui.views
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.medikit.ui.components.*
import com.example.medikit.utils.rememberImagePickerLauncher
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.medikit.viewmodels.WoundViewModel

@Composable
fun HomeView(navController: NavController, modifier: Modifier = Modifier, viewModel: WoundViewModel = viewModel()) {

    val context = LocalContext.current
    var selectedImageBitmap by remember { mutableStateOf<Bitmap?>(null) }

    val imagePickerLauncher = rememberImagePickerLauncher { uri: Uri? ->
        uri?.let {
            val inputStream = context.contentResolver.openInputStream(it)
            selectedImageBitmap = BitmapFactory.decodeStream(inputStream)

            selectedImageBitmap?.let { bitmap ->
                viewModel.analyzeImage(bitmap) {
                    navController.navigate("wound")
                }
            }
        }
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp, vertical = 16.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
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

        Text(
            text = "Sube o toma una foto para recibir un análisis instantáneo con IA",
            style = MaterialTheme.typography.bodyLarge.copy(
                color = Color(0xFF6B7280)
            ),
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(24.dp))

        ImagePlaceholder(
            bitmap = selectedImageBitmap,
            onClick = { imagePickerLauncher.launch("image/*") },
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f)
        )

        Spacer(modifier = Modifier.height(24.dp))

        PrimaryButton(
            text = "Tomar foto",
            icon = Icons.Filled.PhotoCamera,
            onClick = { navController.navigate("loading") }
        )

        Spacer(modifier = Modifier.height(12.dp))

        SecondaryButton(
            text = "Elegir desde galería",
            icon = Icons.Filled.Image,
            onClick = { imagePickerLauncher.launch("image/*") }
        )

        Spacer(modifier = Modifier.height(24.dp))

        AnnotationCard(
            icon = Icons.Filled.Lightbulb,
            title = "Consejo para la fotografía",
            description = "Asegúrate de tener buena iluminación, limpiar el área " +
                    "y tomar la foto lo más enfocada posible"
        )
    }
}