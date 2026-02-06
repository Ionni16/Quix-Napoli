package it.ionut.quiznapoli.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Lightbulb
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha // Import aggiunto per correggere l'errore su .alpha()
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import it.ionut.quiznapoli.data.Question
import it.ionut.quiznapoli.ui.theme.NapoliAzure
import it.ionut.quiznapoli.ui.theme.NapoliBlue

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QuizScreen(
    questions: List<Question>,
    onFinish: (score: Int) -> Unit,
    onBack: () -> Unit
) {
    var index by remember { mutableIntStateOf(0) }
    var score by remember { mutableIntStateOf(0) }

    // Stati del gioco
    var selectedIndex by remember { mutableIntStateOf(-1) }
    var isAnswered by remember { mutableStateOf(false) } // Se l'utente ha risposto (o tempo scaduto)
    var showExplanation by remember { mutableStateOf(false) } // Mostra il box spiegazione

    // Lifelines (Aiuti)
    var fiftyFiftyUsed by remember { mutableStateOf(false) }
    var timePlusUsed by remember { mutableStateOf(false) }
    var hiddenAnswers by remember { mutableStateOf<List<Int>>(emptyList()) } // Indici nascosti dal 50/50

    // Timer
    val initialTime = 20
    var timeLeft by remember { mutableIntStateOf(initialTime) }

    val q = questions.getOrNull(index)

    // Logica Timer
    LaunchedEffect(index, isAnswered, timeLeft) {
        if (!isAnswered && q != null && timeLeft > 0) {
            delay(1000L)
            timeLeft--
        } else if (timeLeft == 0 && !isAnswered) {
            // Tempo scaduto!
            isAnswered = true
            showExplanation = true
        }
    }

    // Reset quando cambia domanda
    LaunchedEffect(index) {
        selectedIndex = -1
        isAnswered = false
        showExplanation = false
        timeLeft = initialTime
        hiddenAnswers = emptyList() // Reset 50/50
    }

    if (q == null) return

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Default.AccessTime, contentDescription = null, tint = if(timeLeft < 5) Color.Red else NapoliBlue)
                        Spacer(Modifier.width(8.dp))
                        Text("$timeLeft s", color = if(timeLeft < 5) Color.Red else NapoliBlue, fontWeight = FontWeight.Bold)
                    }
                },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.Default.Close, contentDescription = "Esci", tint = Color.Gray)
                    }
                },
                actions = {
                    // Indicatore domanda 1/10
                    Box(
                        modifier = Modifier
                            .padding(end = 16.dp)
                            .background(NapoliAzure.copy(alpha = 0.1f), RoundedCornerShape(8.dp))
                            .padding(horizontal = 12.dp, vertical = 6.dp)
                    ) {
                        Text("${index + 1}/${questions.size}", color = NapoliBlue, fontWeight = FontWeight.Bold)
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(containerColor = Color.White)
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .background(Color(0xFFF5F7FA))
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            // Barra Progresso
            val progress by animateFloatAsState(targetValue = (index) / questions.size.toFloat(), label = "progress")
            LinearProgressIndicator(
                progress = { progress },
                modifier = Modifier.fillMaxWidth().height(6.dp).clip(RoundedCornerShape(4.dp)),
                color = NapoliAzure,
                trackColor = Color.LightGray.copy(alpha = 0.5f),
            )

            // --- AREA LIFELINES (AIUTI) ---
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                LifelineButton(
                    text = "50:50",
                    isUsed = fiftyFiftyUsed,
                    isEnabled = !isAnswered && !fiftyFiftyUsed
                ) {
                    // Logica 50/50
                    fiftyFiftyUsed = true
                    val wrongIndices = q.answers.indices.filter { it != q.correctIndex }
                    hiddenAnswers = wrongIndices.shuffled().take(2)
                }

                LifelineButton(
                    text = "+15s",
                    isUsed = timePlusUsed,
                    isEnabled = !isAnswered && !timePlusUsed
                ) {
                    timePlusUsed = true
                    timeLeft += 15
                }
            }

            // --- CARD DOMANDA ---
            Card(
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(4.dp),
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(24.dp)) {
                    Text(q.category.uppercase(), style = MaterialTheme.typography.labelMedium, color = NapoliAzure, fontWeight = FontWeight.Bold)
                    Spacer(Modifier.height(12.dp))
                    Text(q.text, style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold), color = NapoliBlue)
                }
            }

            // --- RISPOSTE ---
            Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                q.answers.forEachIndexed { i, ans ->
                    // Se la risposta è nascosta dal 50/50, mostriamo uno spazio vuoto
                    if (i in hiddenAnswers) {
                        Spacer(Modifier.height(56.dp))
                        return@forEachIndexed
                    }

                    val isCorrect = i == q.correctIndex
                    val isSelected = i == selectedIndex

                    // Colori Dinamici
                    val (containerColor, contentColor, borderColor) = when {
                        isAnswered && isCorrect -> Triple(Color(0xFF4CAF50), Color.White, Color.Transparent) // Verde
                        isAnswered && isSelected && !isCorrect -> Triple(Color(0xFFE53935), Color.White, Color.Transparent) // Rosso
                        isAnswered && !isCorrect && isSelected -> Triple(Color.White, Color.LightGray, Color.LightGray) // Altre disabilitate
                        isSelected -> Triple(NapoliAzure, Color.White, NapoliAzure) // Selezionato
                        else -> Triple(Color.White, NapoliBlue, Color(0xFFE0E0E0)) // Default
                    }

                    OutlinedButton(
                        onClick = {
                            if (!isAnswered) {
                                selectedIndex = i
                                isAnswered = true
                                showExplanation = true // Mostra subito la spiegazione
                                if (isCorrect) score++
                            }
                        },
                        modifier = Modifier.fillMaxWidth().height(56.dp),
                        shape = RoundedCornerShape(12.dp),
                        colors = ButtonDefaults.outlinedButtonColors(
                            containerColor = containerColor,
                            contentColor = contentColor
                        ),
                        border = androidx.compose.foundation.BorderStroke(1.dp, borderColor),
                        enabled = !isAnswered || isSelected || isCorrect // Disabilita le altre alla fine
                    ) {
                        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Start) {
                            Text(
                                text = "${'A' + i}.",
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.width(30.dp)
                            )
                            Text(ans, fontSize = 16.sp, fontWeight = FontWeight.Medium)
                        }
                    }
                }
            }

            Spacer(Modifier.weight(1f))

            // --- BOX SPIEGAZIONE E TASTO NEXT ---
            AnimatedVisibility(visible = showExplanation) {
                Column(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    // Box Spiegazione
                    Card(
                        colors = CardDefaults.cardColors(containerColor = NapoliBlue),
                        shape = RoundedCornerShape(12.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Row(modifier = Modifier.padding(16.dp)) {
                            Icon(Icons.Default.Lightbulb, contentDescription = null, tint = Color(0xFFFFD700), modifier = Modifier.size(24.dp))
                            Spacer(Modifier.width(12.dp))
                            Text(
                                text = q.explanation,
                                style = MaterialTheme.typography.bodyMedium,
                                color = Color.White
                            )
                        }
                    }

                    Spacer(Modifier.height(16.dp))

                    // Bottone Avanti
                    Button(
                        onClick = {
                            if (index < questions.size - 1) {
                                index++
                            } else {
                                onFinish(score)
                            }
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = NapoliAzure),
                        modifier = Modifier.fillMaxWidth().height(56.dp),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Text(
                            if (index < questions.size - 1) "PROSSIMA DOMANDA ➜" else "VEDI RISULTATI 🏆",
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun LifelineButton(
    text: String,
    isUsed: Boolean,
    isEnabled: Boolean,
    onClick: () -> Unit
) {
    val alpha = if (isUsed) 0.3f else 1f

    Button(
        onClick = onClick,
        enabled = isEnabled,
        colors = ButtonDefaults.buttonColors(
            containerColor = if (isUsed) Color.Gray else NapoliBlue,
            disabledContainerColor = Color.Gray
        ),
        contentPadding = PaddingValues(horizontal = 24.dp, vertical = 8.dp),
        modifier = Modifier.alpha(alpha) // Ora funziona correttamente
    ) {
        Text(text, fontWeight = FontWeight.Bold)
    }
}