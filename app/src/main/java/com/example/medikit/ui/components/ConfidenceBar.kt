package com.example.medikit.ui.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ConfidenceBar(
    percentage: Int,
    modifier: Modifier = Modifier
) {
    val targetProgress = (percentage.coerceIn(0, 100)) / 100f

    var startAnimation by remember { mutableStateOf(false) }

    val animatedProgress by animateFloatAsState(
        targetValue = if (startAnimation) targetProgress else 0f,
        animationSpec = tween(durationMillis = 1200),
        label = "confidenceAnimation"
    )

    LaunchedEffect(Unit) {
        startAnimation = true
    }

    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Nivel de confianza",
                style = MaterialTheme.typography.titleMedium.copy(
                    color = Color(0xFF111827),
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Normal
                ),
            )
            Text(
                text = "$percentage%",
                style = MaterialTheme.typography.bodyLarge.copy(
                    fontSize = 25.sp,
                    color = Color(0xFF4CAF50),
                    fontWeight = FontWeight.SemiBold
                )
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        LinearProgressIndicator(
            progress = { animatedProgress },
            modifier = Modifier
                .fillMaxWidth()
                .height(12.dp)
                .clip(RoundedCornerShape(50)),
            color = Color(0xFF4CAF50),
            trackColor = Color(0xFFE5E7EB)
        )
    }
}