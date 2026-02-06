package it.ionut.quiznapoli.data

object NapoliFacts {

    val facts: List<Question> = listOf(

        // =========================
        // FONDAZIONE E PRIMI ANNI
        // =========================

        Question(
            id = "n1",
            difficulty = Difficulty.EASY,
            category = "Fondazione",
            text = "In che anno è stato fondato il Calcio Napoli?",
            answers = listOf("1926", "1919", "1930", "1921"),
            correctIndex = 0,
            explanation = "Il Napoli nasce ufficialmente il 1° agosto 1926 come Associazione Calcio Napoli."
        ),
        Question(
            id = "n2",
            difficulty = Difficulty.MEDIUM,
            category = "Fondazione",
            text = "Chi fu il fondatore del Calcio Napoli?",
            answers = listOf("Giorgio Ascarelli", "Achille Lauro", "Corrado Ferlaino", "Antonio Juliano"),
            correctIndex = 0,
            explanation = "Giorgio Ascarelli, imprenditore ebreo napoletano, fondò il club nel 1926."
        ),
        Question(
            id = "n3",
            difficulty = Difficulty.HARD,
            category = "Fondazione",
            text = "Qual era il nome completo del Napoli al momento della fondazione?",
            answers = listOf(
                "Associazione Calcio Napoli",
                "Società Sportiva Calcio Napoli",
                "Napoli Football Club",
                "Internaples Napoli"
            ),
            correctIndex = 0,
            explanation = "Il nome ufficiale alla nascita era Associazione Calcio Napoli."
        ),

        // =========================
        // STADIO
        // =========================

        Question(
            id = "n4",
            difficulty = Difficulty.EASY,
            category = "Stadio",
            text = "Come si chiama oggi lo stadio del Napoli?",
            answers = listOf(
                "Diego Armando Maradona",
                "San Paolo",
                "Stadio del Sole",
                "San Gennaro"
            ),
            correctIndex = 0,
            explanation = "Dal 2020 lo stadio è intitolato a Diego Armando Maradona."
        ),
        Question(
            id = "n5",
            difficulty = Difficulty.MEDIUM,
            category = "Stadio",
            text = "In quale anno è stato inaugurato lo stadio del Napoli?",
            answers = listOf("1959", "1962", "1954", "1970"),
            correctIndex = 0,
            explanation = "Lo stadio fu inaugurato nel 1959 con il nome di Stadio del Sole."
        ),
        Question(
            id = "n6",
            difficulty = Difficulty.HARD,
            category = "Stadio",
            text = "Quale fu il primo nome ufficiale dello stadio del Napoli?",
            answers = listOf(
                "Stadio del Sole",
                "Stadio San Paolo",
                "Arena Partenopea",
                "Campo Napoli"
            ),
            correctIndex = 0,
            explanation = "Alla sua inaugurazione nel 1959 si chiamava Stadio del Sole."
        ),

        // =========================
        // ERA MARADONA
        // =========================

        Question(
            id = "n7",
            difficulty = Difficulty.EASY,
            category = "Maradona",
            text = "In che anno Diego Armando Maradona arrivò al Napoli?",
            answers = listOf("1984", "1982", "1986", "1980"),
            correctIndex = 0,
            explanation = "Maradona arrivò a Napoli nell'estate del 1984."
        ),
        Question(
            id = "n8",
            difficulty = Difficulty.MEDIUM,
            category = "Maradona",
            text = "Da quale squadra arrivò Maradona al Napoli?",
            answers = listOf("Barcellona", "Boca Juniors", "River Plate", "Siviglia"),
            correctIndex = 0,
            explanation = "Il Napoli acquistò Maradona dal Barcellona."
        ),
        Question(
            id = "n9",
            difficulty = Difficulty.EASY,
            category = "Maradona",
            text = "Quale numero di maglia indossava Maradona al Napoli?",
            answers = listOf("10", "9", "8", "11"),
            correctIndex = 0,
            explanation = "La maglia numero 10 è stata ritirata in suo onore."
        ),
        Question(
            id = "n10",
            difficulty = Difficulty.MEDIUM,
            category = "Maradona",
            text = "In quale anno il Napoli vinse il primo Scudetto?",
            answers = listOf("1987", "1985", "1989", "1990"),
            correctIndex = 0,
            explanation = "Il primo Scudetto arrivò nel 1986-87."
        ),
        Question(
            id = "n11",
            difficulty = Difficulty.HARD,
            category = "Maradona",
            text = "Quale trofeo europeo vinse il Napoli con Maradona?",
            answers = listOf(
                "Coppa UEFA",
                "Champions League",
                "Coppa delle Coppe",
                "Europa League"
            ),
            correctIndex = 0,
            explanation = "Il Napoli vinse la Coppa UEFA nel 1989."
        ),

        // =========================
        // RECORD E LEGGENDE
        // =========================

        Question(
            id = "n12",
            difficulty = Difficulty.MEDIUM,
            category = "Record",
            text = "Chi è il miglior marcatore della storia del Napoli?",
            answers = listOf(
                "Dries Mertens",
                "Diego Maradona",
                "Marek Hamsik",
                "Edinson Cavani"
            ),
            correctIndex = 0,
            explanation = "Dries Mertens è il miglior marcatore All Time del Napoli."
        ),
        Question(
            id = "n13",
            difficulty = Difficulty.HARD,
            category = "Record",
            text = "Chi detiene il record di presenze con la maglia del Napoli?",
            answers = listOf(
                "Marek Hamsik",
                "Giuseppe Bruscolotti",
                "Antonio Juliano",
                "Lorenzo Insigne"
            ),
            correctIndex = 0,
            explanation = "Hamsik ha superato Bruscolotti diventando recordman di presenze."
        ),

        // =========================
        // FALLIMENTO E RINASCITA
        // =========================

        Question(
            id = "n14",
            difficulty = Difficulty.MEDIUM,
            category = "Rinascita",
            text = "In quale anno il Napoli fallì e ripartì dalla Serie C?",
            answers = listOf("2004", "2002", "2006", "1998"),
            correctIndex = 0,
            explanation = "Nel 2004 il Napoli fallì e ripartì dalla Serie C1."
        ),
        Question(
            id = "n15",
            difficulty = Difficulty.HARD,
            category = "Rinascita",
            text = "Qual era il nome del club subito dopo il fallimento del 2004?",
            answers = listOf(
                "Napoli Soccer",
                "AC Napoli",
                "SSC Partenopea",
                "Nuova Napoli"
            ),
            correctIndex = 0,
            explanation = "Il club si chiamò temporaneamente Napoli Soccer."
        ),
        Question(
            id = "n16",
            difficulty = Difficulty.MEDIUM,
            category = "Rinascita",
            text = "Chi acquistò il Napoli dopo il fallimento del 2004?",
            answers = listOf(
                "Aurelio De Laurentiis",
                "Massimo Moratti",
                "Claudio Lotito",
                "Diego Della Valle"
            ),
            correctIndex = 0,
            explanation = "De Laurentiis rilanciò il club portandolo dalla C alla Champions."
        ),

        // =========================
        // SCUDETTO 2023
        // =========================

        Question(
            id = "n17",
            difficulty = Difficulty.EASY,
            category = "Scudetto 2023",
            text = "In che anno il Napoli ha vinto il suo terzo Scudetto?",
            answers = listOf("2023", "2022", "2021", "2024"),
            correctIndex = 0,
            explanation = "Il Napoli ha vinto il terzo Scudetto nella stagione 2022-23."
        ),
        Question(
            id = "n18",
            difficulty = Difficulty.MEDIUM,
            category = "Scudetto 2023",
            text = "Chi era l'allenatore del Napoli campione d'Italia nel 2023?",
            answers = listOf(
                "Luciano Spalletti",
                "Maurizio Sarri",
                "Carlo Ancelotti",
                "Rino Gattuso"
            ),
            correctIndex = 0,
            explanation = "Spalletti ha guidato il Napoli al terzo Scudetto."
        ),
        Question(
            id = "n19",
            difficulty = Difficulty.MEDIUM,
            category = "Scudetto 2023",
            text = "Quale attaccante fu capocannoniere del Napoli nello Scudetto 2023?",
            answers = listOf(
                "Victor Osimhen",
                "Kvaratskhelia",
                "Politano",
                "Raspadori"
            ),
            correctIndex = 0,
            explanation = "Osimhen vinse la classifica marcatori nel 2023."
        )
    )
}
