package day3

def lines = new File("../../resources/day3/input").readLines()

def sum = lines.collect {
    def half = it.length() / 2
    def first = it[0..half - 1]
    def second = it[half..it.length() - 1]
    assert first + second == it

    def firstSet = first.split("") as Set
    def secondSet = second.split("") as Set

    firstSet.intersect(secondSet)[0]
}
.collect {
    char ch = it.charAt(0)
    return charValue(ch)
}
.sum()

println sum

int sum2 = 0

//part2

for (int i = 2; i < lines.size(); i += 3) {
    def firstSet = lines[i - 2] as Set
    def secondSet = lines[i - 1] as Set
    def thirdSet = lines[i] as Set

    def intersect = firstSet.intersect(secondSet).intersect(thirdSet)
    assert intersect.size() == 1
    sum2 += charValue((char) intersect[0])
}
println sum2

private int charValue(char ch) {
    if (Character.isUpperCase(ch)) {
        return (int) ch - 38
    } else {
        return (int) ch - 96
    }
}
