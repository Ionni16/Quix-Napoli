package it.ionut.quiznapoli

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.remember
import androidx.navigation.compose.*
import it.ionut.quiznapoli.data.Difficulty
import it.ionut.quiznapoli.data.QuestionBank
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

                NavHost(navController = nav, startDestination = "home") {
                    composable("home") {
                        HomeScreen(
                            onStart = { diff ->
                                nav.navigate("quiz/${diff.name}")
                            }
                        )
                    }

                    composable("quiz/{diff}") { backStack ->
                        val diffName = backStack.arguments?.getString("diff") ?: "EASY"
                        val diff = Difficulty.valueOf(diffName)

                        // Genero qui il quiz (10 domande). In MVP va benissimo.
                        val quiz = remember(diff) { QuestionBank.getQuiz(diff, count = 10) }

                        QuizScreen(
                            questions = quiz,
                            onFinish = { score ->
                                nav.navigate("result/$score/${quiz.size}") {
                                    popUpTo("home") { saveState = true }
                                }
                            },
                            onBack = { nav.popBackStack() }
                        )
                    }

                    composable("result/{score}/{total}") { backStack ->
                        val score = backStack.arguments?.getString("score")?.toIntOrNull() ?: 0
                        val total = backStack.arguments?.getString("total")?.toIntOrNull() ?: 10

                        ResultScreen(
                            score = score,
                            total = total,
                            onPlayAgain = { nav.navigate("home") },
                        )
                    }
                }
            }
        }
    }
}
