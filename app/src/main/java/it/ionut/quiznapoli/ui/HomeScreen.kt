package it.ionut.quiznapoli.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import it.ionut.quiznapoli.data.Difficulty

@Composable
fun HomeScreen(
    highScore: Int,
    onStart: (Difficulty) -> Unit
) {
    Box(Modifier.fillMaxSize()) {
        NapoliPremiumBackground()

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(22.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Spacer(Modifier.height(4.dp))

            // HERO
            Column(
                horizontalAlignment = Alignment.Start
            ) {
                GlassCard(modifier = Modifier.fillMaxWidth()) {
                    Text(
                        text = "QUIZ SSC NAPOLI",
                        style = MaterialTheme.typography.headlineMedium,
                        fontWeight = FontWeight.Black,
                        color = Color.White
                    )
                    Spacer(Modifier.height(6.dp))
                    Text(
                        text = "Storia completa • Record • Leggende • Trofei",
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color.White.copy(alpha = 0.82f)
                    )

                    Spacer(Modifier.height(14.dp))

                    GlassCard(modifier = Modifier.fillMaxWidth(), corner = 18.dp) {
                        Text(
                            text = "🏆 RECORD PERSONALE",
                            color = Color.White.copy(alpha = 0.78f),
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(Modifier.height(6.dp))
                        Text(
                            text = "$highScore",
                            style = MaterialTheme.typography.displaySmall,
                            fontWeight = FontWeight.Black,
                            color = Color.White
                        )
                    }
                }
            }

            // CTA
            GlassCard(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = "Scegli la difficoltà",
                    color = Color.White.copy(alpha = 0.9f),
                    fontWeight = FontWeight.Bold
                )
                Spacer(Modifier.height(14.dp))

                PremiumGradientButton(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = { onStart(Difficulty.EASY) }
                ) { Text("Riscaldamento • Facile", color = Color.White, fontWeight = FontWeight.Bold) }

                Spacer(Modifier.height(10.dp))

                PremiumGradientButton(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = { onStart(Difficulty.MEDIUM) }
                ) { Text("Campionato • Medio", color = Color.White, fontWeight = FontWeight.Bold) }

                Spacer(Modifier.height(10.dp))

                PremiumGradientButton(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = { onStart(Difficulty.HARD) }
                ) { Text("Champions • Difficile", color = Color.White, fontWeight = FontWeight.Bold) }
            }
        }
    }
}
