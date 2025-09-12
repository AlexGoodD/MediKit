package com.example.medikit.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Report
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun SeverityCard(
    severity: String,
    modifier: Modifier = Modifier
) {
    val (backgroundColor, iconColor, icon, title, description) = when (severity.lowercase()) {
        "low" -> SeverityData(
            backgroundColor = Color(0xFFFFFBEB),
            iconColor = Color(0xFFA97200),
            icon = Icons.Filled.Warning,
            title = "Severidad baja",
            description = "La herida parece ser menor. Si los síntomas empeoran, consulta a un profesional de la salud."
        )
        "medium" -> SeverityData(
            backgroundColor = Color(0xFFFFF7ED),
            iconColor = Color(0xFFC2410C),
            icon = Icons.Filled.Warning,
            title = "Severidad media",
            description = "Monitorea la herida cuidadosamente. Toma precauciones y busca ayuda si empeora."
        )
        "high" -> SeverityData(
            backgroundColor = Color(0xFFFEF2F2),
            iconColor = Color(0xFFB91C1C),
            icon = Icons.Filled.Report,
            title = "Severidad alta",
            description = "Esta herida puede ser grave. Por favor, consulta a un profesional de la salud inmediatamente."
        )
        else -> SeverityData(
            backgroundColor = Color(0xFFF1F1F1),
            iconColor = Color.DarkGray,
            icon = Icons.Filled.Report,
            title = "Severidad desconocida",
            description = "El nivel de severidad no fue reconocido. Intenta de nuevo o contacta con soporte."
        )
    }

    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(
                color = backgroundColor,
                shape = RoundedCornerShape(12.dp)
            )
            .padding(16.dp)
    ) {
        Column {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {

                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = iconColor,
                    modifier = Modifier.size(20.dp)
                )

                Spacer(modifier = Modifier.width(10.dp))

                Text(
                    text = title,
                    style = MaterialTheme.typography.headlineSmall.copy(
                        color = iconColor,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                )
            }


            Text(
                text = description,
                style = MaterialTheme.typography.bodySmall.copy(
                    color = iconColor,
                    fontSize = 14.sp
                )
            )
        }
    }
}

private data class SeverityData(
    val backgroundColor: Color,
    val iconColor: Color,
    val icon: ImageVector,
    val title: String,
    val description: String
)