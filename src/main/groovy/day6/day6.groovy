package day6

def lines = new File("../../resources/day6/input").readLines()

def solution(String message, int packetLength) {
    def prevSignificantCharacters = []
    int charToChangeIndex = 0
    for (int i = 0; i < message.length(); i++) {
        prevSignificantCharacters[charToChangeIndex] = message[i]

        if ((prevSignificantCharacters as Set).size() == packetLength) return i + 1

        charToChangeIndex = ++charToChangeIndex % packetLength
    }
}

println solution(lines[0], 4)
println solution(lines[0], 14)