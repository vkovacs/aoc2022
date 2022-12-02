package day2

def lines = new File("../../resources/day2/input").readLines()

static def score(int roundResult, String myChoice) {
    if (roundResult == -1) return 6 + scoreValue(myChoice)
    if (roundResult == 0) return 3 + scoreValue(myChoice)
    if (roundResult == 1) return 0 + scoreValue(myChoice)
}

static def choiceString(String choiceChar) {
    switch (choiceChar) {
        case "A", "X" -> "Rock"
        case "B", "Y" -> "Paper"
        case "C", "Z" -> "Scissor"
    }
}

static def compare(String o1, String o2) {
    if (o1 == o2) return 0

    if (o1 == "Rock") {
        if (o2 == "Paper") return -1
        else return 1
    }

    if (o1 == "Paper") {
        if (o2 == "Scissor") return -1
        else return 1
    }

    if (o1 == "Scissor") {
        if (o2 == "Rock") return -1
        else return 1
    }
}

static int scoreValue(choiceChar) {
    switch (choiceChar) {
        case "Rock" -> 1
        case "Paper" -> 2
        case "Scissor" -> 3
    }
}

//part1
def totalScore = lines.sum {
    def (opponentChoiceChar, myChoiceChar) = it.split(" ")
    def opponentChoice = choiceString(opponentChoiceChar)
    def myChoice = choiceString(myChoiceChar)
    def roundResult = compare(opponentChoice, myChoice)
    score(roundResult, myChoice)
}

println(totalScore)


//part2
static String myChoiceWithExpectedResult(String otherChoice, String expectedResult) {
    if (expectedResult == "Draw") return otherChoice

    if (otherChoice == "Rock") {
        if (expectedResult == "Win") {
            return "Paper"
        }
        return "Scissor"

    }

    if (otherChoice == "Paper") {
        if (expectedResult == "Win") {
            return "Scissor"
        }
        return "Rock"
    }

    if (otherChoice == "Scissor") {
        if (expectedResult == "Win") {
            return "Rock"
        }
        return "Paper"
    }
}

static String expectedResult(String expectedResultChar) {
    switch (expectedResultChar) {
        case "X" -> "Loose"
        case "Y" -> "Draw"
        case "Z" -> "Win"
    }
}

def totalScoreWithExpectedResult = lines.sum {
    def (opponentChoiceChar, expectedResultChar) = it.split(" ")
    def opponentChoice = choiceString(opponentChoiceChar)
    def expectedResult = expectedResult(expectedResultChar)
    def myChoiceWithExpectedResult = myChoiceWithExpectedResult(opponentChoice, expectedResult)
    def roundResult = compare(opponentChoice, myChoiceWithExpectedResult)
    score(roundResult, myChoiceWithExpectedResult)
}

println(totalScoreWithExpectedResult)
