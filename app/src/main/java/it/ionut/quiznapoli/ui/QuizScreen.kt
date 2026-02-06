package it.ionut.quiznapoli.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Lightbulb
import it.ionut.quiznapoli.ui.ads.AdBanner
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import it.ionut.quiznapoli.data.Question
import kotlinx.coroutines.delay
import android.app.Activity
import androidx.compose.ui.platform.LocalContext
import it.ionut.quiznapoli.ui.ads.RewardedAdManager


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
    var isAnswered by remember { mutableStateOf(false) }
    var showExplanation by remember { mutableStateOf(false) }

    // Lifelines globali
    var fiftyFiftyUsed by remember { mutableStateOf(false) }
    var timePlusUsed by remember { mutableStateOf(false) }
    var hiddenAnswers by remember { mutableStateOf<Set<Int>>(emptySet()) }

    val initialTime = 20
    var timeLeft by remember { mutableIntStateOf(initialTime) }

    val q = questions.getOrNull(index) ?: return
    val context = LocalContext.current
    val activity = context as? Activity

    val rewarded = remember {
        RewardedAdManager(
            context = context,
            adUnitId = "ca-app-pub-3402722098398750/7122003890" // ✅ TEST rewarded
        )
    }

    LaunchedEffect(Unit) {
        rewarded.preload()
    }

    val scroll = rememberScrollState()

    LaunchedEffect(index) {
        selectedIndex = -1
        isAnswered = false
        showExplanation = false
        timeLeft = initialTime
        hiddenAnswers = emptySet()
    }

    LaunchedEffect(index, isAnswered) {
        while (!isAnswered && timeLeft > 0) {
            delay(1000L)
            timeLeft--
        }
        if (!isAnswered && timeLeft == 0) {
            isAnswered = true
            showExplanation = true
        }
    }

    Box(Modifier.fillMaxSize()) {
        NapoliPremiumBackground()

        Scaffold(
            containerColor = Color.Transparent,
            topBar = {
                CenterAlignedTopAppBar(
                    colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                        containerColor = Color.Transparent
                    ),
                    navigationIcon = {
                        IconButton(onClick = onBack) { Icon(Icons.Default.Close, null, tint = Color.White) }
                    },
                    title = {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            TimerRing(secondsLeft = timeLeft, totalSeconds = initialTime)
                            Spacer(Modifier.width(10.dp))
                            Text(
                                text = "${timeLeft}s",
                                color = Color.White,
                                fontWeight = FontWeight.Black,
                                fontSize = 20.sp
                            )
                        }
                    },
                    actions = {
                        GlassCard(corner = 14.dp, modifier = Modifier.padding(end = 14.dp)) {
                            Text(
                                text = "${index + 1}/${questions.size}",
                                color = Color.White,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                )
            },
            bottomBar = {
                AdBanner(
                    adUnitId = "ca-app-pub-3402722098398750/3374330571", // ✅ TEST banner
                    modifier = Modifier.fillMaxWidth()
                )
            },

            ) { padding ->
            Column(
                modifier = Modifier
                    .padding(padding)
                    .fillMaxSize()
                    .verticalScroll(scroll)
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(14.dp)
            ) {
                val prog by animateFloatAsState(
                    targetValue = (index + 1) / questions.size.toFloat(),
                    label = "p"
                )
                LinearProgressIndicator(
                    progress = { prog },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(7.dp)
                        .clip(RoundedCornerShape(99.dp)),
                    color = Color(0xFF00A7E1),
                    trackColor = Color.White.copy(alpha = 0.14f)
                )

                // Lifelines (chip glass)
                Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                    PremiumLifelineChip(
                        text = "50:50",
                        used = fiftyFiftyUsed,
                        enabled = !isAnswered && !fiftyFiftyUsed
                    ) {
                        val act = activity ?: return@PremiumLifelineChip

                        rewarded.show(
                            activity = act,
                            onReward = {
                                fiftyFiftyUsed = true
                                val wrong = q.answers.indices.filter { it != q.correctIndex }
                                hiddenAnswers = wrong.shuffled().take(2).toSet()
                            },
                            onNoAd = {
                                // opzionale: mostra un messaggio "Annuncio non disponibile"
                            }
                        )
                    }


                    PremiumLifelineChip(
                        text = "+15s",
                        used = timePlusUsed,
                        enabled = !isAnswered && !timePlusUsed
                    ) {
                        val act = activity ?: return@PremiumLifelineChip

                        rewarded.show(
                            activity = act,
                            onReward = {
                                timePlusUsed = true
                                timeLeft += 15
                            }
                        )
                    }

                }

                // Domanda (glass)
                GlassCard(modifier = Modifier.fillMaxWidth()) {
                    Text(
                        text = q.category.uppercase(),
                        color = Color.White.copy(alpha = 0.75f),
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(Modifier.height(10.dp))
                    val questionText = remember(q.text) { cleanQuestionText(q.text) }

                    Text(
                        questionText,
                        style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold),
                        color = Color.White
                    )

                }

                // Risposte
                Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                    q.answers.forEachIndexed { i, ans ->
                        AnimatedVisibility(visible = i !in hiddenAnswers) {
                            PremiumAnswer(
                                index = i,
                                text = ans,
                                state = answerState(
                                    isAnswered = isAnswered,
                                    isSelected = i == selectedIndex,
                                    isCorrect = i == q.correctIndex
                                ),
                                onClick = {
                                    if (!isAnswered) {
                                        selectedIndex = i
                                        isAnswered = true
                                        showExplanation = true
                                        if (i == q.correctIndex) score++
                                    }
                                }
                            )
                        }
                    }
                }

                AnimatedVisibility(visible = showExplanation) {
                    Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                        GlassCard(modifier = Modifier.fillMaxWidth()) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(Icons.Default.Lightbulb, null, tint = Color(0xFFFFD56A))
                                Spacer(Modifier.width(10.dp))
                                Text(
                                    text = q.explanation,
                                    color = Color.White.copy(alpha = 0.92f),
                                    fontWeight = FontWeight.Medium
                                )
                            }
                        }

                        PremiumGradientButton(
                            modifier = Modifier.fillMaxWidth(),
                            onClick = { if (index < questions.size - 1) index++ else onFinish(score) }
                        ) {
                            Text(
                                if (index < questions.size - 1) "PROSSIMA DOMANDA ➜" else "VEDI RISULTATI 🏆",
                                color = Color.White,
                                fontWeight = FontWeight.Black
                            )
                        }
                    }
                }

                Spacer(Modifier.height(80.dp)) // lascia spazio al banner + gesture bar
            }
        }
    }
}

private enum class AnsState { NORMAL, SELECTED, CORRECT, WRONG }

private fun answerState(isAnswered: Boolean, isSelected: Boolean, isCorrect: Boolean): AnsState = when {
    isAnswered && isCorrect -> AnsState.CORRECT
    isAnswered && isSelected && !isCorrect -> AnsState.WRONG
    isSelected -> AnsState.SELECTED
    else -> AnsState.NORMAL
}

@Composable
private fun PremiumAnswer(
    index: Int,
    text: String,
    state: AnsState,
    onClick: () -> Unit
) {
    val shape = RoundedCornerShape(18.dp)

    val baseBg = Color.White.copy(alpha = 0.10f)
    val border = Color.White.copy(alpha = 0.18f)
    val azzurro = Color(0xFF00A7E1)
    val green = Color(0xFF2EE59D)
    val red = Color(0xFFFF4D4D)

    val (bg, bd) = when (state) {
        AnsState.CORRECT -> green.copy(alpha = 0.24f) to green.copy(alpha = 0.55f)
        AnsState.WRONG -> red.copy(alpha = 0.22f) to red.copy(alpha = 0.55f)
        AnsState.SELECTED -> azzurro.copy(alpha = 0.22f) to azzurro.copy(alpha = 0.55f)
        AnsState.NORMAL -> baseBg to border
    }

    OutlinedButton(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .height(58.dp),
        shape = shape,
        border = BorderStroke(1.dp, bd),
        colors = ButtonDefaults.outlinedButtonColors(
            containerColor = bg,
            contentColor = Color.White
        ),
        enabled = true // non farle sparire
    ) {
        Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            Box(
                Modifier
                    .size(28.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .background(Color.White.copy(alpha = 0.12f)),
                contentAlignment = Alignment.Center
            ) {
                Text(text = "${'A' + index}", fontWeight = FontWeight.Black)
            }
            Spacer(Modifier.width(12.dp))
            Text(text = text, fontWeight = FontWeight.SemiBold)
        }
    }
}

@Composable
private fun PremiumLifelineChip(
    text: String,
    used: Boolean,
    enabled: Boolean,
    onClick: () -> Unit
) {
    val shape = RoundedCornerShape(999.dp)
    val alpha = if (used) 0.35f else 1f

    OutlinedButton(
        onClick = onClick,
        enabled = enabled,
        shape = shape,
        border = BorderStroke(1.dp, Color.White.copy(alpha = 0.18f)),
        colors = ButtonDefaults.outlinedButtonColors(
            containerColor = Color.White.copy(alpha = 0.10f),
            contentColor = Color.White
        ),
        modifier = Modifier
            .height(44.dp)
            .alpha(alpha),
        contentPadding = PaddingValues(horizontal = 18.dp, vertical = 8.dp)
    ) {
        Text(text, fontWeight = FontWeight.Bold)
    }
}

private fun cleanQuestionText(raw: String): String {
    var t = raw.trim()

    val prefixes = listOf(
        "Indica l'anno corretto:",
        "Indica la persona corretta:",
        "Indica il numero corretto:",
        "Evento SSC Napoli:",
        "Domanda SSC Napoli:",
        "Completa:",
        "Quanto vale:",
        "Qual è il numero corretto:",
        "Indica l'anno giusto per:",
        "Indica la definizione corretta di:",
        "Scegli la risposta corretta:",
        "Indica la squadra avversaria corretta:"
    )

    // rimuove eventuale prefisso (case-insensitive)
    for (p in prefixes) {
        if (t.startsWith(p, ignoreCase = true)) {
            t = t.substring(p.length).trim()
            // se dopo il prefisso resta ":" o "-" lo togliamo
            t = t.trimStart(' ', ':', '-', '–', '—')
            break
        }
    }

    // opzionale: se rimane doppio spazio o newline brutte
    t = t.replace(Regex("\\s+"), " ").trim()

    // opzionale: prima lettera maiuscola (se serve)
    return t.replaceFirstChar { if (it.isLowerCase()) it.titlecase() else it.toString() }
}
