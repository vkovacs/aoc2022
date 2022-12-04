package day4

def lines = new File("../../resources/day4/input").readLines()

def sum = lines.collect {
    def (number0, number1, number2, number3) = numberTokens(it)

    if ((number2 <= number0 && number1 <= number3) || (number0 <= number2 && number3 <= number1)) return 1
    return 0
}.sum()

println(sum)

def sum2 = lines.collect {
    def (number0, number1, number2, number3) = numberTokens(it)

    def intersect = (number0..number1).intersect(number2..number3)
    intersect.isEmpty() ? 0 : 1
}.sum()

println(sum2)

List<Integer> numberTokens(String line) {
    def match = line =~ /(\d+)-(\d+),(\d+)-(\d+)/ //https://stackoverflow.com/a/764503/1141880
    match[0][1..-1].collect {Integer.parseInt(it)}
}