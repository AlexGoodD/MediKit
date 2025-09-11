package com.example.medikit.data
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import com.example.medikit.model.WoundInfo

val woundMap: Map<String, WoundInfo> = mapOf(
    "alergias" to WoundInfo("Alergias", "Reacciones en la piel o respiratorias", Icons.Filled.Sick),
    "cortaduras" to WoundInfo("Cortaduras", "Heridas abiertas en la piel", Icons.Filled.ContentCut),
    "esguinces" to WoundInfo("Esguinces", "Lesiones en ligamentos", Icons.Filled.AccessibilityNew),
    "fracturas" to WoundInfo("Fracturas", "Huesos rotos o fisurados", Icons.Filled.MedicalServices),
    "hematomas" to WoundInfo("Hematomas", "Moretones o acumulación de sangre", Icons.Filled.Bloodtype),
    "no_lesiones" to WoundInfo("Sin lesiones", "No se detectan daños", Icons.Filled.HealthAndSafety),
    "quemaduras" to WoundInfo("Quemaduras", "Lesiones por calor o químicos", Icons.Filled.LocalFireDepartment),
    "raspaduras" to WoundInfo("Raspaduras", "Heridas superficiales en la piel", Icons.Filled.Healing)
)
