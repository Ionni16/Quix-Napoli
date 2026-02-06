package it.ionut.quiznapoli.data

import kotlin.random.Random

object QuestionBank {

    fun getQuestions(): List<Question> = questions

    // Pesca domande a caso e le mescola
    fun getQuiz(difficulty: Difficulty, count: Int = 10): List<Question> {
        val pool = questions.filter { it.difficulty == difficulty }
        return pool.shuffled().take(count.coerceAtMost(pool.size))
    }

    // =========================
    // GENERATORE "1000 DOMANDE"
    // =========================

    private enum class FactType { YEAR, PERSON, NUMBER, OPPONENT, TROPHY, STADIUM, NICKNAME, GENERIC }

    private data class Fact(
        val id: String,
        val difficulty: Difficulty,
        val category: String,
        val type: FactType,
        val subject: String,      // cosa sto chiedendo (es. "Primo Scudetto")
        val answer: String,       // risposta corretta (es. "1987")
        val explanation: String
    )

    // Template per ogni tipo: ogni fact produce più domande diverse
    private val templates: Map<FactType, List<(Fact) -> String>> = mapOf(
        FactType.YEAR to listOf(
            { f -> "${f.subject}: in che anno?" },
            { f -> "In che anno avvenne ${f.subject.lowercase()}?" },
            { f -> "Quando è successo: ${f.subject}?" }
        ),
        FactType.PERSON to listOf(
            { f -> "${f.subject}: chi?" },
            { f -> "Chi è associato a ${f.subject.lowercase()}?" },
            { f -> "Chi riguarda: ${f.subject}?" }
        ),
        FactType.NUMBER to listOf(
            { f -> "${f.subject}: quale numero?" },
            { f -> "Qual è il numero per ${f.subject.lowercase()}?" },
            { f -> "A quanto corrisponde ${f.subject.lowercase()}?" }
        ),
        FactType.OPPONENT to listOf(
            { f -> "${f.subject}: contro chi?" },
            { f -> "Contro quale squadra: ${f.subject.lowercase()}?" },
            { f -> "Quale avversario per ${f.subject.lowercase()}?" }
        ),
        FactType.TROPHY to listOf(
            { f -> "${f.subject}: quale trofeo?" },
            { f -> "A quale titolo si riferisce ${f.subject.lowercase()}?" },
            { f -> "Che trofeo è: ${f.subject}?" }
        ),
        FactType.STADIUM to listOf(
            { f -> "${f.subject}: qual è il nome corretto?" },
            { f -> "Come si chiama ${f.subject.lowercase()}?" },
            { f -> "Nome corretto di ${f.subject.lowercase()}?" }
        ),
        FactType.NICKNAME to listOf(
            { f -> "${f.subject}: qual è il soprannome?" },
            { f -> "Come viene soprannominato ${f.subject}?" },
            { f -> "Qual è il soprannome di ${f.subject}?" }
        ),
        FactType.GENERIC to listOf(
            { f -> f.subject }
        )
    )


    // Pool distrattori per tipo (solo roba Napoli/contesto calcio, niente città/cibo ecc.)
    private val yearDistractors = listOf(
        "1926", "1962", "1976", "1987", "1989", "1990", "1998", "2012", "2014", "2020", "2023"
    )

    private val trophyDistractors = listOf(
        "Scudetto", "Coppa Italia", "Supercoppa Italiana", "Coppa UEFA", "Europa League", "Champions League"
    )

    private val opponentDistractors = listOf(
        "Stoccarda", "Juventus", "Milan", "Inter", "Roma", "Lazio", "Fiorentina", "Bayern Monaco", "Real Madrid"
    )

    private val personDistractors = listOf(
        "Diego Armando Maradona",
        "Marek Hamsik",
        "Dries Mertens",
        "Luciano Spalletti",
        "Maurizio Sarri",
        "Antonio Careca",
        "Bruno Giordano",
        "Claudio Garella",
        "Giuseppe Bruscolotti",
        "Lorenzo Insigne",
        "Victor Osimhen",
        "Khvicha Kvaratskhelia",
        "Aurelio De Laurentiis"
    )

    private val numberDistractors = listOf(
        "6", "7", "9", "10", "11", "14", "20", "30", "33", "36", "50", "520"
    )

    private val stadiumDistractors = listOf(
        "Diego Armando Maradona", "San Paolo", "Stadio del Sole", "Olimpico", "San Siro", "San Nicola"
    )

    private val nicknameDistractors = listOf(
        "Kvaradona", "Il Matador", "Il Pibe de Oro", "Marekiaro", "Ciro", "Garellik", "Batman"
    )

    private fun distractorsFor(type: FactType): List<String> = when (type) {
        FactType.YEAR -> yearDistractors
        FactType.TROPHY -> trophyDistractors
        FactType.OPPONENT -> opponentDistractors
        FactType.PERSON -> personDistractors
        FactType.NUMBER -> numberDistractors
        FactType.STADIUM -> stadiumDistractors
        FactType.NICKNAME -> nicknameDistractors
        FactType.GENERIC -> (personDistractors + opponentDistractors + trophyDistractors).distinct()
    }

    private fun buildQuestions(targetCount: Int = 1000): List<Question> {
        val out = ArrayList<Question>(targetCount)

        // 1) crea un po' di domande da facts + templates
        var seq = 1
        var cursor = 0

        // per variare: cicliamo sui facts e sui template finché arriviamo a targetCount
        while (out.size < targetCount) {
            val fact = facts[cursor % facts.size]
            val tplList = templates[fact.type].orEmpty()
            if (tplList.isNotEmpty()) {
                val tpl = tplList[(cursor / facts.size) % tplList.size]
                val text = tpl(fact)

                val answers = buildAnswers(fact.type, fact.answer)
                if (answers.size == 4) {
                    out += Question(
                        id = "${fact.difficulty.name.lowercase()}_${seq++}",
                        difficulty = fact.difficulty,
                        category = fact.category,
                        text = text,
                        answers = answers,
                        correctIndex = answers.indexOf(fact.answer),
                        explanation = fact.explanation
                    )
                }
            }

            cursor++
            // sicurezza
            if (cursor > 200_000) break
        }

        return out
    }

    private fun buildAnswers(type: FactType, correct: String): List<String> {
        val pool = distractorsFor(type).filter { it != correct }.distinct()
        if (pool.size < 3) return emptyList()

        val wrongs = pool.shuffled().take(3)
        val all = (wrongs + correct).shuffled()

        // evita duplicati (caso raro)
        return if (all.distinct().size == 4) all else {
            val fixed = (pool.shuffled().take(3) + correct).shuffled()
            if (fixed.distinct().size == 4) fixed else emptyList()
        }
    }

    // =========================
    // FACTS: SOLO SSC NAPOLI
    // (metti qui fatti STABILI)
    // =========================
    private val facts = listOf(
        // --- Identità / base ---
        Fact("f1", Difficulty.EASY, "Base", FactType.YEAR, "Fondazione dell'Associazione Calcio Napoli", "1926",
            "Il club nasce ufficialmente nel 1926."),
        Fact("f2", Difficulty.EASY, "Base", FactType.GENERIC, "Di che colore è la maglia principale del Napoli?",
            "Azzurro", "L'azzurro è il colore storico del club."),
        Fact("f3", Difficulty.EASY, "Stadio", FactType.STADIUM, "Lo stadio del Napoli", "Diego Armando Maradona",
            "Lo stadio è intitolato a Diego Armando Maradona."),

        // --- Scudetti / titoli ---
        Fact("f4", Difficulty.MEDIUM, "Scudetto", FactType.YEAR, "Primo Scudetto del Napoli", "1987",
            "Il Napoli vince il primo Scudetto nel 1987."),
        Fact("f5", Difficulty.MEDIUM, "Scudetto", FactType.YEAR, "Secondo Scudetto del Napoli", "1990",
            "Il Napoli vince il secondo Scudetto nel 1990."),
        Fact("f6", Difficulty.EASY, "Scudetto", FactType.YEAR, "Terzo Scudetto del Napoli", "2023",
            "Il terzo Scudetto arriva nel 2023."),
        Fact("f7", Difficulty.MEDIUM, "Europa", FactType.TROPHY, "Il titolo europeo vinto dal Napoli nel 1989", "Coppa UEFA",
            "Il Napoli vinse la Coppa UEFA nel 1989."),

        // --- Finale Coppa UEFA 1989 ---
        Fact("f8", Difficulty.MEDIUM, "Europa", FactType.OPPONENT, "Finale di Coppa UEFA 1989", "Stoccarda",
            "La finale fu contro lo Stoccarda (doppia sfida)."),

        // --- Coppa Italia (numero) ---
        Fact("f9", Difficulty.MEDIUM, "Trofei", FactType.NUMBER, "Coppe Italia vinte dal Napoli (totale storico)", "6",
            "Il Napoli ha vinto 6 Coppe Italia (dato storico)."),

        // --- Leggende / record ---
        Fact("f10", Difficulty.EASY, "Leggende", FactType.PERSON, "Il calciatore simbolo degli anni d'oro del Napoli", "Diego Armando Maradona",
            "Maradona è il simbolo del Napoli dei grandi successi."),
        Fact("f11", Difficulty.MEDIUM, "Record", FactType.PERSON, "Miglior marcatore All Time del Napoli", "Dries Mertens",
            "Mertens è il miglior marcatore della storia del Napoli."),
        Fact("f12", Difficulty.HARD, "Presenze", FactType.PERSON, "Record di presenze ufficiali con la maglia del Napoli", "Marek Hamsik",
            "Hamsik è tra i recordman assoluti del club (presenze)."),
        Fact("f13", Difficulty.EASY, "Soprannomi", FactType.NICKNAME, "Khvicha Kvaratskhelia", "Kvaradona",
            "Kvaratskhelia è stato soprannominato 'Kvaradona' dai tifosi."),
        Fact("f14", Difficulty.MEDIUM, "Soprannomi", FactType.NICKNAME, "Dries Mertens", "Ciro",
            "Mertens è amatissimo e spesso chiamato 'Ciro'."),
        Fact("f15", Difficulty.HARD, "Portieri", FactType.NICKNAME, "Pino Taglialatela", "Batman",
            "Taglialatela era soprannominato 'Batman'."),
        Fact("f16", Difficulty.MEDIUM, "Soprannomi", FactType.NICKNAME, "Edinson Cavani", "Il Matador",
            "Cavani è noto come 'Il Matador'."),

        // --- Numeri famosi (sempre stabili) ---
        Fact("f17", Difficulty.EASY, "Leggende", FactType.NUMBER, "Numero di maglia storico associato a Maradona nel Napoli", "10",
            "La 10 è legata a Maradona nella storia del Napoli."),

        // --- Presidenti / rinascita 2004 (nome club) ---
        Fact("f18", Difficulty.MEDIUM, "Rinascita", FactType.GENERIC, "Qual era il nome del club nel primo periodo post-fallimento (2004)?",
            "Napoli Soccer", "Dopo il fallimento, la squadra ripartì come 'Napoli Soccer'."),

        // --- Allenatori storici (evito attualità, solo cose consolidate) ---
        Fact("f19", Difficulty.EASY, "Allenatori", FactType.PERSON, "Allenatore del Napoli Campione d'Italia 2023", "Luciano Spalletti",
            "Spalletti ha guidato la squadra allo Scudetto 2023."),

        // --- Curiosità storiche di stadio ---
        Fact("f20", Difficulty.HARD, "Stadio", FactType.STADIUM, "Nome storico dello stadio all'inaugurazione (1959)", "Stadio del Sole",
            "In origine fu chiamato 'Stadio del Sole'."),

        // --- Coppa UEFA 1989 vs Juve (partita celebre) ---
        Fact("f21", Difficulty.HARD, "Europa", FactType.NUMBER, "Risultato della rimonta 3-0 del Napoli contro la Juventus in Coppa UEFA 1989",
            "3-0", "Partita celebre della Coppa UEFA 1989: vittoria 3-0 al San Paolo.")
    )

    // Qui generiamo davvero ~1000 domande
    private val questions: List<Question> = buildQuestions(targetCount = 1000)
}
