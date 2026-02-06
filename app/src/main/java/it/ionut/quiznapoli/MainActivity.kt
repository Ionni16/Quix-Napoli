package it.ionut.quiznapoli

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.*
import androidx.navigation.NavType
import androidx.navigation.navArgument
import it.ionut.quiznapoli.data.Difficulty
import it.ionut.quiznapoli.data.QuestionBank
import it.ionut.quiznapoli.data.ScoreManager
import it.ionut.quiznapoli.ui.HomeScreen
import it.ionut.quiznapoli.ui.QuizScreen
import it.ionut.quiznapoli.ui.ResultScreen
import it.ionut.quiznapoli.ui.theme.QuizNapoliTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            QuizNapoliTheme {
                val nav = rememberNavController()
                val context = LocalContext.current
                // Inizializziamo il gestore dei salvataggi
                val scoreManager = remember { ScoreManager(context) }

                // Stato per aggiornare la home quando si torna indietro
                var highScore by remember { mutableIntStateOf(scoreManager.getHighScore()) }

                NavHost(navController = nav, startDestination = "home") {

                    // --- HOME SCREEN ---
                    composable("home") {
                        // Aggiorniamo il record ogni volta che torniamo alla home
                        highScore = scoreManager.getHighScore()

                        HomeScreen(
                            highScore = highScore,
                            onStart = { diff ->
                                nav.navigate("quiz/${diff.name}")
                            }
                        )
                    }

                    // --- QUIZ SCREEN ---
                    composable("quiz/{diff}") { backStack ->
                        val diffName = backStack.arguments?.getString("diff") ?: "EASY"
                        val diff = Difficulty.valueOf(diffName)

                        // Carichiamo domande diverse ogni volta
                        val quiz = remember(diff) { QuestionBank.getQuiz(diff, count = 10) }

                        QuizScreen(
                            questions = quiz,
                            onFinish = { score ->
                                // Salviamo il punteggio se è un record
                                scoreManager.saveScore(score)
                                nav.navigate("result/$score/${quiz.size}") {
                                    popUpTo("home") { saveState = true }
                                }
                            },
                            onBack = { nav.popBackStack() }
                        )
                    }

                    // --- RESULT SCREEN ---
                    composable(
                        "result/{score}/{total}",
                        arguments = listOf(
                            navArgument("score") { type = NavType.IntType },
                            navArgument("total") { type = NavType.IntType }
                        )
                    ) { backStack ->
                        val score = backStack.arguments?.getInt("score") ?: 0
                        val total = backStack.arguments?.getInt("total") ?: 10
                        val oldRecord = scoreManager.getHighScore()

                        // È un nuovo record se il punteggio attuale è uguale al record salvato (e > 0)
                        val isNewRecord = score >= oldRecord && score > 0

                        ResultScreen(
                            score = score,
                            total = total,
                            isNewRecord = isNewRecord,
                            onPlayAgain = { nav.navigate("home") },
                        )
                    }
                }
            }
        }
    }
}