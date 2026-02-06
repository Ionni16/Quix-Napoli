package it.ionut.quiznapoli.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import it.ionut.quiznapoli.ui.theme.NapoliAzure
import it.ionut.quiznapoli.ui.theme.NapoliBlue

@Composable
fun ResultScreen(
    score: Int,
    total: Int,
    isNewRecord: Boolean,
    onPlayAgain: () -> Unit
) {
    val percentage = (score.toFloat() / total.toFloat()) * 100

    // Tema dinamico in base al risultato
    val (bgGradient, emoji, message) = when {
        percentage == 100f -> Triple(
            listOf(Color(0xFFFFD700), Color(0xFFFF8F00)), // Oro
            "🏆",
            "CAMPIONE D'ITALIA!"
        )
        percentage >= 60f -> Triple(
            listOf(NapoliAzure, NapoliBlue), // Napoli
            "💪",
            "OTTIMO LAVORO!"
        )
        else -> Triple(
            listOf(Color(0xFFEF5350), Color(0xFFC62828)), // Rosso
            "⚽",
            "DEVI ALLENARTI!"
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Brush.verticalGradient(bgGradient))
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Emoji gigante
        Text(emoji, fontSize = 80.sp)

        Spacer(Modifier.height(16.dp))

        Text(
            message,
            style = MaterialTheme.typography.headlineMedium,
            color = Color.White,
            fontWeight = FontWeight.Black,
            textAlign = TextAlign.Center
        )

        if (isNewRecord) {
            Spacer(Modifier.height(8.dp))
            Badge(containerColor = Color.White, contentColor = NapoliBlue) {
                Text(" NUOVO RECORD ", fontWeight = FontWeight.Bold, modifier = Modifier.padding(4.dp))
            }
        }

        Spacer(Modifier.height(48.dp))

        // Card Risultato
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(24.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White)
        ) {
            Column(
                modifier = Modifier.padding(32.dp).fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text("PUNTEGGIO FINALE", style = MaterialTheme.typography.labelMedium, color = Color.Gray)
                Text(
                    "$score / $total",
                    style = MaterialTheme.typography.displayLarge,
                    fontWeight = FontWeight.Bold,
                    color = NapoliBlue
                )
                Spacer(Modifier.height(8.dp))
                Text(
                    "Risposte corrette: ${percentage.toInt()}%",
                    style = MaterialTheme.typography.bodyLarge,
                    color = NapoliAzure
                )
            }
        }

        Spacer(Modifier.height(48.dp))

        Button(
            onClick = onPlayAgain,
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.White,
                contentColor = NapoliBlue
            ),
            modifier = Modifier.fillMaxWidth().height(60.dp),
            shape = RoundedCornerShape(16.dp),
            elevation = ButtonDefaults.buttonElevation(8.dp)
        ) {
            Text("GIOCA DI NUOVO", fontSize = 18.sp, fontWeight = FontWeight.Bold)
        }
    }
}