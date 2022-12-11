package day10

def lines = new File("../../resources/day10/input").readLines()

def solution(def lines) {
    int x = 1
    int tick = 0
    List<Integer> signalStrengths = []
    for (def line in lines) {
        if (line == "noop") {
            tick++
            signalStrengths << x * tick
        } else {
            tick++
            signalStrengths << x * tick
            tick++
            signalStrengths << x * tick
            x += line.split(" ")[1] as int
        }
    }

    signalStrengths[19] + signalStrengths[59] + signalStrengths[99] + signalStrengths[139] + signalStrengths[179] + signalStrengths[219]
}


println solution(lines)