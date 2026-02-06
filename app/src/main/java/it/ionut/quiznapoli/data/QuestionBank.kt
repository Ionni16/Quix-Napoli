package it.ionut.quiznapoli.data

object QuestionBank {

    fun getQuestions(): List<Question> {
        return questions
    }

    // Pesca domande a caso e le mescola
    fun getQuiz(difficulty: Difficulty, count: Int = 10): List<Question> {
        return questions
            .filter { it.difficulty == difficulty }
            .shuffled()
            .take(count)
    }

    private val questions = listOf(
        // --- LIVELLO FACILE (Riscaldamento) ---
        Question(
            id = "e1",
            difficulty = Difficulty.EASY,
            category = "Base",
            text = "Di che colore è la maglia principale del Napoli?",
            answers = listOf("Rosso", "Azzurro", "Giallo", "Nero"),
            correctIndex = 1,
            explanation = "L'Azzurro è il colore storico che rappresenta il cielo e il mare del Golfo di Napoli."
        ),
        Question(
            id = "e2",
            difficulty = Difficulty.EASY,
            category = "Stadio",
            text = "Come si chiama lo stadio del Napoli?",
            answers = listOf("San Siro", "Diego Armando Maradona", "Olimpico", "San Nicola"),
            correctIndex = 1,
            explanation = "Lo stadio San Paolo è stato rinominato in onore del Pibe de Oro dopo la sua scomparsa nel 2020."
        ),
        Question(
            id = "e3",
            difficulty = Difficulty.EASY,
            category = "Simboli",
            text = "Qual è il simbolo presente nello stemma del Napoli?",
            answers = listOf("Un Vesuvio stilizzato", "Un Cavallo rampante", "Una 'N' napoleonica", "Un pallone d'oro"),
            correctIndex = 2,
            explanation = "La 'N' sta per Napoli, introdotta negli anni '20 al posto del cavallo rampante."
        ),
        Question(
            id = "e4",
            difficulty = Difficulty.EASY,
            category = "Leggende",
            text = "Quale numero di maglia indossava Diego Armando Maradona?",
            answers = listOf("7", "9", "10", "11"),
            correctIndex = 2,
            explanation = "La maglia numero 10 è stata ritirata dal club e non può essere indossata da nessun altro giocatore."
        ),
        Question(
            id = "e5",
            difficulty = Difficulty.EASY,
            category = "Scudetto",
            text = "Chi era l'allenatore del Terzo Scudetto (2023)?",
            answers = listOf("Maurizio Sarri", "Carlo Ancelotti", "Luciano Spalletti", "Rino Gattuso"),
            correctIndex = 2,
            explanation = "Spalletti ha guidato gli azzurri al trionfo 33 anni dopo l'ultimo titolo."
        ),
        Question(
            id = "e6",
            difficulty = Difficulty.EASY,
            category = "Giocatori",
            text = "Quale attaccante nigeriano è stato capocannoniere nel 2023?",
            answers = listOf("Victor Osimhen", "George Weah", "Obafemi Martins", "Simy"),
            correctIndex = 0,
            explanation = "Osimhen ha vinto la classifica marcatori ed è diventato un idolo della tifoseria con la sua maschera protettiva."
        ),
        Question(
            id = "e7",
            difficulty = Difficulty.EASY,
            category = "Curiosità",
            text = "Qual è l'animale mascotte non ufficiale del Napoli?",
            answers = listOf("Il Lupo", "Il Ciuccio", "L'Aquila", "Il Toro"),
            correctIndex = 1,
            explanation = "Nato come presa in giro ('siete lenti come un asino'), il Ciuccio è diventato un simbolo di orgoglio e testardaggine."
        ),
        Question(
            id = "e8",
            difficulty = Difficulty.EASY,
            category = "Società",
            text = "Chi è il presidente del Napoli dal 2004?",
            answers = listOf("Corrado Ferlaino", "Aurelio De Laurentiis", "Luigi De Magistris", "Achille Lauro"),
            correctIndex = 1,
            explanation = "Aurelio De Laurentiis ha rilevato il club dopo il fallimento, portandolo dalla Serie C alla Champions League."
        ),
        Question(
            id = "e9",
            difficulty = Difficulty.EASY,
            category = "Giocatori",
            text = "Chi è soprannominato 'Kvaradona'?",
            answers = listOf("Zielinski", "Kvaratskhelia", "Politano", "Raspadori"),
            correctIndex = 1,
            explanation = "Khvicha Kvaratskhelia ha incantato tutti al suo primo anno, meritandosi il paragone con Diego."
        ),
        Question(
            id = "e10",
            difficulty = Difficulty.EASY,
            category = "Città",
            text = "In quale quartiere di Napoli si trova lo stadio?",
            answers = listOf("Vomero", "Fuorigrotta", "Posillipo", "Scampia"),
            correctIndex = 1,
            explanation = "Lo stadio sorge nel quartiere occidentale di Fuorigrotta."
        ),
        Question(
            id = "e11",
            difficulty = Difficulty.EASY,
            category = "Geografia",
            text = "Quale vulcano domina il panorama di Napoli?",
            answers = listOf("Etna", "Vesuvio", "Stromboli", "Vulcano"),
            correctIndex = 1,
            explanation = "Il Vesuvio è il simbolo naturale della città, spesso invocato ironicamente nei cori."
        ),
        Question(
            id = "e12",
            difficulty = Difficulty.EASY,
            category = "Ex Capitani",
            text = "Quale scugnizzo napoletano è stato capitano prima di Di Lorenzo?",
            answers = listOf("Lorenzo Insigne", "Ciro Immobile", "Fabio Quagliarella", "Paolo Cannavaro"),
            correctIndex = 0,
            explanation = "Lorenzo Insigne, nato a Frattamaggiore, è stato capitano e simbolo di napoletanità per anni."
        ),

        // --- LIVELLO MEDIO (Tifoso Esperto) ---
        Question(
            id = "m1",
            difficulty = Difficulty.MEDIUM,
            category = "Storia",
            text = "In che anno il Napoli ha vinto il suo PRIMO Scudetto?",
            answers = listOf("1984", "1987", "1990", "1989"),
            correctIndex = 1,
            explanation = "Il 10 maggio 1987, dopo un pareggio con la Fiorentina, Napoli esplose di gioia per il primo storico tricolore."
        ),
        Question(
            id = "m2",
            difficulty = Difficulty.MEDIUM,
            category = "Record",
            text = "Chi è il miglior marcatore della storia del Napoli (All Time)?",
            answers = listOf("Diego Maradona", "Marek Hamsik", "Dries Mertens", "Edinson Cavani"),
            correctIndex = 2,
            explanation = "Dries 'Ciro' Mertens ha superato sia Hamsik che Maradona, diventando il miglior bomber di sempre."
        ),
        Question(
            id = "m3",
            difficulty = Difficulty.MEDIUM,
            category = "Europa",
            text = "Contro quale squadra il Napoli vinse la Coppa UEFA nel 1989?",
            answers = listOf("Bayern Monaco", "Stoccarda", "Real Madrid", "Juventus"),
            correctIndex = 1,
            explanation = "Il Napoli batté lo Stoccarda in una doppia finale memorabile (2-1 all'andata, 3-3 al ritorno)."
        ),
        Question(
            id = "m4",
            difficulty = Difficulty.MEDIUM,
            category = "Formazioni",
            text = "Chi componeva il tridente 'Ma-Gi-Ca'?",
            answers = listOf("Maradona-Giordano-Careca", "Maradona-Garella-Carnevale", "Mertens-Gabbiadini-Callejon", "Maradona-Gullit-Careca"),
            correctIndex = 0,
            explanation = "Un attacco leggendario formato da Maradona, Bruno Giordano e Antonio Careca."
        ),
        Question(
            id = "m5",
            difficulty = Difficulty.MEDIUM,
            category = "Trofei",
            text = "Quante Coppe Italia ha vinto il Napoli (fino al 2023)?",
            answers = listOf("4", "5", "6", "7"),
            correctIndex = 2,
            explanation = "Il Napoli ha vinto 6 Coppe Italia (1962, 1976, 1987, 2012, 2014, 2020)."
        ),
        Question(
            id = "m6",
            difficulty = Difficulty.MEDIUM,
            category = "Allenatori",
            text = "Chi allenava il Napoli dei record (91 punti) senza vincere lo scudetto?",
            answers = listOf("Rafa Benitez", "Maurizio Sarri", "Carlo Ancelotti", "Walter Mazzarri"),
            correctIndex = 1,
            explanation = "Il Napoli di Sarri espresse un calcio spettacolare ('Sarrismo') sfiorando l'impresa contro la Juventus."
        ),
        Question(
            id = "m7",
            difficulty = Difficulty.MEDIUM,
            category = "Soprannomi",
            text = "Quale giocatore era chiamato 'Il Matador'?",
            answers = listOf("Ezequiel Lavezzi", "Gonzalo Higuain", "Edinson Cavani", "Roberto Sosa"),
            correctIndex = 2,
            explanation = "Cavani si guadagnò questo soprannome per la sua freddezza sotto porta e la grinta."
        ),
        Question(
            id = "m8",
            difficulty = Difficulty.MEDIUM,
            category = "Rinascite",
            text = "Qual è stato il primo nome del club dopo il fallimento del 2004?",
            answers = listOf("SSC Napoli", "Napoli Soccer", "AC Napoli", "Real Napoli"),
            correctIndex = 1,
            explanation = "De Laurentiis iscrisse la squadra in Serie C1 con il nome provvisorio di 'Napoli Soccer'."
        ),
        Question(
            id = "m9",
            difficulty = Difficulty.MEDIUM,
            category = "Stadio",
            text = "Prima di chiamarsi San Paolo, come era noto lo stadio?",
            answers = listOf("Stadio del Sole", "Arena Partenopea", "Stadio dei Mille", "Campo Vomero"),
            correctIndex = 0,
            explanation = "Inaugurato nel 1959, fu inizialmente chiamato 'Stadio del Sole' per la sua esposizione."
        ),
        Question(
            id = "m10",
            difficulty = Difficulty.MEDIUM,
            category = "Portieri",
            text = "Chi difendeva la porta del primo Scudetto?",
            answers = listOf("Giovanni Galli", "Claudio Garella", "Pino Taglialatela", "Luciano Castellini"),
            correctIndex = 1,
            explanation = "Garella, detto 'Garellik', era famoso per le sue parate non stilisticamente perfette ma efficaci, spesso con i piedi."
        ),
        Question(
            id = "m11",
            difficulty = Difficulty.MEDIUM,
            category = "Gol Storici",
            text = "Chi segnò il gol decisivo nella finale di Supercoppa 2014 a Doha?",
            answers = listOf("Higuain", "Koulibaly", "Rafael (Portiere)", "Callejon"),
            correctIndex = 2,
            explanation = "Il portiere Rafael parò il rigore decisivo a Padoin dopo una serie infinita, regalando il trofeo al Napoli."
        ),
        Question(
            id = "m12",
            difficulty = Difficulty.MEDIUM,
            category = "Stranieri",
            text = "Quale slovacco è diventato una bandiera del club?",
            answers = listOf("Marek Hamsik", "Stanislav Lobotka", "Milan Skriniar", "Vladimir Weiss"),
            correctIndex = 0,
            explanation = "Marekiaro ha giocato nel Napoli per 12 stagioni, diventandone capitano e recordman di presenze."
        ),

        // --- LIVELLO DIFFICILE (Ultra Curva B) ---
        Question(
            id = "h1",
            difficulty = Difficulty.HARD,
            category = "Record",
            text = "Chi detiene il record assoluto di presenze con la maglia del Napoli?",
            answers = listOf("Giuseppe Bruscolotti", "Marek Hamsik", "Antonio Juliano", "Lorenzo Insigne"),
            correctIndex = 1,
            explanation = "Hamsik ha superato Bruscolotti ('Palo 'e fierro') fermandosi a 520 presenze ufficiali."
        ),
        Question(
            id = "h2",
            difficulty = Difficulty.HARD,
            category = "Storia Antica",
            text = "In che anno è stato fondato il Calcio Napoli (Associazione Calcio Napoli)?",
            answers = listOf("1904", "1926", "1969", "1921"),
            correctIndex = 1,
            explanation = "Il 1° agosto 1926 l'industriale Giorgio Ascarelli fondò l'Associazione Calcio Napoli."
        ),
        Question(
            id = "h3",
            difficulty = Difficulty.HARD,
            category = "Record Gol",
            text = "Quanti gol segnò Higuain nella stagione del record (2015-16)?",
            answers = listOf("30", "33", "36", "38"),
            correctIndex = 2,
            explanation = "Il Pipita superò il record storico di Nordahl segnando 36 gol in una singola stagione di Serie A."
        ),
        Question(
            id = "h4",
            difficulty = Difficulty.HARD,
            category = "Soprannomi",
            text = "Quale allenatore era soprannominato 'O Petisso'?",
            answers = listOf("Bruno Pesaola", "Omar Sivori", "Luis Vinicio", "Ottavio Bianchi"),
            correctIndex = 0,
            explanation = "Bruno Pesaola, argentino di nascita ma napoletano d'adozione, era chiamato così per la sua piccola statura."
        ),
        Question(
            id = "h5",
            difficulty = Difficulty.HARD,
            category = "Partite Memorabili",
            text = "Qual è il risultato della famosa vittoria contro la Juventus in Coppa UEFA 1989?",
            answers = listOf("1-0", "2-0", "3-0 (d.t.s.)", "3-1"),
            correctIndex = 2,
            explanation = "Il Napoli ribaltò lo 0-2 dell'andata vincendo 3-0 al San Paolo con gol di Renica al 119' minuto."
        ),
        Question(
            id = "h6",
            difficulty = Difficulty.HARD,
            category = "Mercato",
            text = "Chi è stato l'acquisto più costoso nella storia del Napoli?",
            answers = listOf("Gonzalo Higuain", "Victor Osimhen", "Hirving Lozano", "Manolo Gabbiadini"),
            correctIndex = 1,
            explanation = "L'operazione Osimhen con il Lille ha avuto un valore complessivo di circa 70-80 milioni di euro."
        ),
        Question(
            id = "h7",
            difficulty = Difficulty.HARD,
            category = "Ex Giocatori",
            text = "Chi era soprannominato 'Il Pampa'?",
            answers = listOf("Edinson Cavani", "Roberto Sosa", "German Denis", "Hugo Campagnaro"),
            correctIndex = 1,
            explanation = "Roberto Sosa fu l'ultimo a indossare la maglia numero 10 al San Paolo (in C1) e il primo a chiedere di ritirarla."
        ),
        Question(
            id = "h8",
            difficulty = Difficulty.HARD,
            category = "Aneddoti",
            text = "Quale giocatore del Napoli segnò un gol da centrocampo contro la Lazio?",
            answers = listOf("Dries Mertens", "Lorenzo Insigne", "Edinson Cavani", "Fabio Quagliarella"),
            correctIndex = 0,
            explanation = "Mertens emulò Maradona con un pallonetto incredibile allo stadio Olimpico nel 2017."
        ),
        Question(
            id = "h9",
            difficulty = Difficulty.HARD,
            category = "Curiosità",
            text = "Chi è l'unico giocatore ad aver vinto Scudetto, Coppa Italia e Coppa UEFA col Napoli?",
            answers = listOf("Diego Maradona", "Ciro Ferrara", "Moreno Ferrario", "Alessandro Renica"),
            correctIndex = 3,
            explanation = "Renica (insieme ad altri della rosa storica come Ferrara e Maradona) ha vinto tutto il possibile in quegli anni d'oro."
        ),
        Question(
            id = "h10",
            difficulty = Difficulty.HARD,
            category = "Retrocessioni",
            text = "Qual è il punteggio più basso mai fatto dal Napoli in Serie A?",
            answers = listOf("14 punti", "20 punti", "24 punti", "10 punti"),
            correctIndex = 0,
            explanation = "Nella disastrosa stagione 1997-98 il Napoli retrocesse facendo solo 14 punti in tutto il campionato."
        ),
        Question(
            id = "h11",
            difficulty = Difficulty.HARD,
            category = "Statistiche",
            text = "Quale allenatore ha la media punti più alta nella storia del Napoli in A?",
            answers = listOf("Maurizio Sarri", "Luciano Spalletti", "Rafa Benitez", "Carlo Ancelotti"),
            correctIndex = 1,
            explanation = "Spalletti, grazie alla cavalcata scudetto, ha registrato medie record superando anche Sarri."
        ),
        Question(
            id = "h12",
            difficulty = Difficulty.HARD,
            category = "Portieri",
            text = "Chi era il portiere soprannominato 'Batman'?",
            answers = listOf("Pino Taglialatela", "Morgan De Sanctis", "Pepe Reina", "Gennaro Iezzo"),
            correctIndex = 0,
            explanation = "Pino Taglialatela, napoletano e tifoso, era famoso per le sue uscite spettacolari e la maglia col pipistrello."
        )
    )
}