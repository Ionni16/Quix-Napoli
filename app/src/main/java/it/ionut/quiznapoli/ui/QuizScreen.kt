package it.ionut.quiznapoli.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import it.ionut.quiznapoli.data.Question

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QuizScreen(
    questions: List<Question>,
    onFinish: (score: Int) -> Unit,
    onBack: () -> Unit
) {
    var index by remember { mutableIntStateOf(0) }
    var score by remember { mutableIntStateOf(0) }

    var selectedIndex by remember { mutableIntStateOf(-1) }
    var isLocked by remember { mutableStateOf(false) }

    val q = questions.getOrNull(index)

    if (q == null) {
        LaunchedEffect(Unit) { onFinish(score) }
        return
    }

    val progress = (index + 1) / questions.size.toFloat()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Domanda ${index + 1}/${questions.size}") },
                navigationIcon = {
                    TextButton(onClick = onBack) { Text("Indietro") }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            LinearProgressIndicator(
                progress = { progress },
                modifier = Modifier.fillMaxWidth()
            )

            Card(modifier = Modifier.fillMaxWidth()) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(q.category, style = MaterialTheme.typography.labelLarge)
                    Spacer(Modifier.height(6.dp))
                    Text(q.text, style = MaterialTheme.typography.titleLarge)
                }
            }

            q.answers.forEachIndexed { i, ans ->
                val isCorrect = i == q.correctIndex
                val isSelected = i == selectedIndex

                val colors = when {
                    !isLocked -> ButtonDefaults.elevatedButtonColors()
                    isSelected && isCorrect -> ButtonDefaults.elevatedButtonColors(
                        containerColor = MaterialTheme.colorScheme.primaryContainer
                    )
                    isSelected && !isCorrect -> ButtonDefaults.elevatedButtonColors(
                        containerColor = MaterialTheme.colorScheme.errorContainer
                    )
                    isCorrect -> ButtonDefaults.elevatedButtonColors(
                        containerColor = MaterialTheme.colorScheme.primaryContainer
                    )
                    else -> ButtonDefaults.elevatedButtonColors()
                }

                ElevatedButton(
                    onClick = {
                        if (isLocked) return@ElevatedButton
                        selectedIndex = i
                        isLocked = true
                        if (i == q.correctIndex) score += 1
                    },
                    enabled = !isLocked,
                    modifier = Modifier.fillMaxWidth(),
                    contentPadding = PaddingValues(16.dp),
                    colors = colors
                ) {
                    Text(ans, style = MaterialTheme.typography.bodyLarge)
                }
            }

            if (isLocked) {
                val wasCorrect = selectedIndex == q.correctIndex
                Card(modifier = Modifier.fillMaxWidth()) {
                    Column(modifier = Modifier.padding(14.dp)) {
                        Text(
                            if (wasCorrect) "Corretto ✅" else "Sbagliato ❌",
                            style = MaterialTheme.typography.titleMedium
                        )
                        Spacer(Modifier.height(6.dp))
                        Text(q.explanation, style = MaterialTheme.typography.bodyMedium)
                    }
                }

                LaunchedEffect(index, isLocked) {
                    delay(700)
                    if (index == questions.lastIndex) {
                        onFinish(score)
                    } else {
                        index += 1
                        selectedIndex = -1
                        isLocked = false
                    }
                }
            }

            Spacer(Modifier.weight(1f))
            Text("Punteggio: $score", style = MaterialTheme.typography.bodyMedium)
        }
    }
}
