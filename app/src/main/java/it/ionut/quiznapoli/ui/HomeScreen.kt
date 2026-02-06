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
import androidx.compose.ui.unit.dp
import it.ionut.quiznapoli.data.Difficulty
import it.ionut.quiznapoli.ui.theme.NapoliAzure
import it.ionut.quiznapoli.ui.theme.NapoliBlue

@Composable
fun HomeScreen(
    highScore: Int,
    onStart: (Difficulty) -> Unit
) {
    // Sfondo sfumato Premium
    val gradient = Brush.verticalGradient(
        colors = listOf(NapoliBlue, NapoliAzure)
    )

    Box(modifier = Modifier.fillMaxSize().background(gradient)) {
        Column(
            modifier = Modifier.padding(24.dp).fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // Badge Record
            Card(
                colors = CardDefaults.cardColors(containerColor = Color.White.copy(alpha = 0.2f)),
                shape = RoundedCornerShape(50)
            ) {
                Text(
                    text = "🏆 RECORD: $highScore",
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
                )
            }

            Spacer(Modifier.height(32.dp))

            Text(
                "QUIZ NAPOLI",
                style = MaterialTheme.typography.displayMedium.copy(fontWeight = FontWeight.Black),
                color = Color.White
            )
            Text(
                "Mettiti alla prova, tifoso!",
                style = MaterialTheme.typography.titleMedium,
                color = Color.White.copy(alpha = 0.8f)
            )

            Spacer(Modifier.height(48.dp))

            // Pulsanti
            Card(
                colors = CardDefaults.cardColors(containerColor = Color.White),
                shape = RoundedCornerShape(16.dp),
                elevation = CardDefaults.cardElevation(8.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    LevelButton("Riscaldamento (Facile)", NapoliAzure) { onStart(Difficulty.EASY) }
                    Spacer(Modifier.height(12.dp))
                    LevelButton("Campionato (Medio)", NapoliBlue) { onStart(Difficulty.MEDIUM) }
                    Spacer(Modifier.height(12.dp))
                    LevelButton("Champions (Difficile)", Color(0xFF00295A)) { onStart(Difficulty.HARD) }
                }
            }
        }
    }
}

@Composable
private fun LevelButton(text: String, color: Color, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier.fillMaxWidth().height(56.dp),
        colors = ButtonDefaults.buttonColors(containerColor = color),
        shape = RoundedCornerShape(12.dp)
    ) {
        Text(text, style = MaterialTheme.typography.titleMedium)
    }
}