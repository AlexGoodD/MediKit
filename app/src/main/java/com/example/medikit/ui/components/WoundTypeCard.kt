package com.example.medikit.ui.components
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.medikit.data.woundMap

@Composable
fun WoundTypeCard(
    type: String,
    modifier: Modifier = Modifier
) {
    val woundInfo = woundMap[type]

    if(woundInfo != null) {
        Column {
            Text(
                text = "Tipo de herida detectada",
                style = MaterialTheme.typography.titleMedium.copy(
                    color = Color(0xFF111827),
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Normal
                ),
            )

            Spacer(modifier = Modifier.height(10.dp))

            Row(
                modifier = modifier
                    .fillMaxWidth()
                    .background(Color(0xFF4CAF50).copy(alpha = 0.05f), RoundedCornerShape(16.dp))
                    .border(
                        width = 1.dp,
                        color = Color(0x5B4CAF50).copy(alpha = 0.15f),
                        shape = RoundedCornerShape(16.dp)
                    )
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .background(Color(0x5B4CAF50).copy(alpha = 0.1f), CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = woundInfo.icon,
                        contentDescription = null,
                        tint = Color(0xFF4CAF50),
                        modifier = Modifier.size(20.dp)
                    )
                }

                Spacer(modifier = Modifier.width(12.dp))

                Column {
                    Text(
                        text = woundInfo.title,
                        style = MaterialTheme.typography.bodyMedium.copy(
                            fontSize = 16.sp,
                            color = Color(0xFF111827)
                        )
                    )
                    Text(
                        text = woundInfo.description,
                        style = MaterialTheme.typography.bodySmall.copy(
                            fontSize = 12.sp,
                            color = Color(0xFF4B5563)
                        )
                    )
                }
            }
        }
    } else {
        Text("Tipo de lesión no reconocido")
    }
}
