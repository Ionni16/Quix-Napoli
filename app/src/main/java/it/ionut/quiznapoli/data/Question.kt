package it.ionut.quiznapoli.data

enum class Difficulty {
    EASY, MEDIUM, HARD
}

data class Question(
    val id: String,
    val difficulty: Difficulty,
    val category: String,
    val text: String,
    val answers: List<String>,
    val correctIndex: Int,
    val explanation: String
)
