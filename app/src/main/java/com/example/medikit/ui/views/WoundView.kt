import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.PhotoCamera
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.medikit.ui.components.ConfidenceBar
import com.example.medikit.ui.components.DisclaimerCard
import com.example.medikit.ui.components.PrimaryButton
import com.example.medikit.ui.components.RecommendationList
import com.example.medikit.ui.components.SeverityCard
import com.example.medikit.ui.components.WoundTypeCard

private const val CONFIDENCE_PERCENTAGE = 12
private const val WOUND_TYPE = "alergias" // alergias/cortaduras/esguinces/fracturas/hematomas/no_lesiones/quemaduras/raspaduras

private val RECOMMENDATIONS_LIST = listOf(
    "Limpia la herida suavemente con solución salina",
    "Aplica antiséptico y cúbrela con un vendaje estéril",
    "Monitorea si aparecen signos de infección"
)

private const val SEVERITY_LEVEL = "low" // low/medium/high

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WoundView(navController: NavController, modifier: Modifier = Modifier) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Resultados del análisis") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Regresar"
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 32.dp, vertical = 16.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = "Tu herida ha sido analizada usando inteligencia artificial",
                style = MaterialTheme.typography.bodyLarge.copy(
                    color = Color(0xFF6B7280)
                ),
                textAlign = TextAlign.Start,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(20.dp))

//            ImagePlaceholder(
//                onClick = { println("Image Placeholder Clicked") },
//                modifier = Modifier
//                    .fillMaxWidth()
//            )

            Spacer(modifier = Modifier.height(20.dp))

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .border(
                        width = 1.dp,
                        color = Color(0xFFD1D5DB),
                        shape = RoundedCornerShape(16.dp)
                    )
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                ConfidenceBar(
                    percentage = CONFIDENCE_PERCENTAGE,
                    modifier = Modifier.fillMaxWidth()
                )

                WoundTypeCard(
                    modifier = Modifier.fillMaxWidth(),
                    type = WOUND_TYPE
                )

                RecommendationList(
                    title = "Recomendaciones",
                    recommendations = RECOMMENDATIONS_LIST
                )

                SeverityCard(severity = SEVERITY_LEVEL)
            }

            Spacer(modifier = Modifier.height(20.dp))

            PrimaryButton(
                text = "Analizar nueva foto",
                icon = Icons.Filled.PhotoCamera,
                onClick = { navController.popBackStack() }
            )

            Spacer(modifier = Modifier.height(24.dp))

            DisclaimerCard(
                icon = Icons.Filled.Info,
                title = "Descargo de responsabilidad médico",
                description = "Este análisis es únicamente con fines informativos " +
                        "y no sustituye la evaluación o el tratamiento de un profesional de la salud.",
                iconColor = Color(0xFF6B7280),
                backgroundColor = Color(0xFFF3F4F6)
            )
        }
    }
}