package it.ionut.quiznapoli.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import it.ionut.quiznapoli.data.Difficulty

@Composable
fun HomeScreen(
    onStart: (Difficulty) -> Unit
) {
    Scaffold { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(20.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text("Quiz Napoli", style = MaterialTheme.typography.headlineLarge)
            Text(
                "10 domande • 3 livelli • per tutte le età",
                style = MaterialTheme.typography.bodyLarge
            )

            Spacer(Modifier.height(16.dp))

            LevelButton(
                title = "Facile",
                subtitle = "Per iniziare senza stress",
                onClick = { onStart(Difficulty.EASY) }
            )

            LevelButton(
                title = "Medio",
                subtitle = "Per chi segue davvero",
                onClick = { onStart(Difficulty.MEDIUM) }
            )

            LevelButton(
                title = "Difficile",
                subtitle = "Solo per veri esperti",
                onClick = { onStart(Difficulty.HARD) }
            )

            Spacer(Modifier.weight(1f))

            Text(
                "App non ufficiale. Nessun logo/immagine ufficiale.",
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}

@Composable
private fun LevelButton(
    title: String,
    subtitle: String,
    onClick: () -> Unit
) {
    ElevatedButton(
        onClick = onClick,
        modifier = Modifier.fillMaxWidth(),
        contentPadding = PaddingValues(16.dp)
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            Text(title, style = MaterialTheme.typography.titleLarge)
            Text(subtitle, style = MaterialTheme.typography.bodyMedium)
        }
    }
}
