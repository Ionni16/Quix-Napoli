package it.ionut.quiznapoli.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ResultScreen(
    score: Int,
    total: Int,
    onPlayAgain: () -> Unit,
) {
    val percent = if (total == 0) 0 else (score * 100) / total

    val badge = when {
        percent >= 90 -> "Leggenda Azzurra 🏆"
        percent >= 70 -> "Super Tifoso 💙"
        percent >= 50 -> "Buon Tifoso 👍"
        else -> "Ci riproviamo 😄"
    }

    Scaffold { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(20.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text("Risultato", style = MaterialTheme.typography.headlineLarge)
            Text("$score / $total", style = MaterialTheme.typography.titleLarge)
            Text(badge, style = MaterialTheme.typography.titleMedium)

            Spacer(Modifier.height(10.dp))

            ElevatedButton(
                onClick = onPlayAgain,
                modifier = Modifier.fillMaxWidth(),
                contentPadding = PaddingValues(16.dp)
            ) {
                Text("Rigioca", style = MaterialTheme.typography.bodyLarge)
            }

            // In seguito aggiungiamo "Condividi"
            Spacer(Modifier.weight(1f))
            Text(
                "App non ufficiale. Nessun logo/immagine ufficiale.",
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}
