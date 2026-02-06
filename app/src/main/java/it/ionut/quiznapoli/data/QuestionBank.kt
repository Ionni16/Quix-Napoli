package it.ionut.quiznapoli.data

object QuestionBank {

    private val questions = listOf(
        Question(
            id = "n1",
            difficulty = Difficulty.EASY,
            category = "Base",
            text = "In che città gioca il Napoli?",
            answers = listOf("Napoli", "Roma", "Milano", "Torino"),
            correctIndex = 0,
            explanation = "Il Napoli è la squadra della città di Napoli."
        ),
        Question(
            id = "n2",
            difficulty = Difficulty.EASY,
            category = "Stadio",
            text = "Come si chiama lo stadio del Napoli?",
            answers = listOf(
                "Diego Armando Maradona",
                "San Siro",
                "Olimpico",
                "Artemio Franchi"
            ),
            correctIndex = 0,
            explanation = "Lo stadio di casa del Napoli è il Diego Armando Maradona."
        )
    )

    fun getQuiz(difficulty: Difficulty, count: Int = 10): List<Question> {
        return questions
            .filter { it.difficulty == difficulty }
            .shuffled()
            .take(count)
    }
}
