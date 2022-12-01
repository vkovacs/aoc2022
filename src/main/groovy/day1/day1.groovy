package day1

def inputFile = new File("../../resources/day1/input")

def lines = inputFile.readLines()
        .collect { it == "" ? 0 : it as int }


//part 1
static int maxCaloriesSum(List<Integer> lines) {
    int maxCaloriesSum = 0;

    int sum = 0
    for (int calorie : lines) {
        if (calorie > 0) {
            sum += calorie
        } else {
            if (sum > maxCaloriesSum) {
                maxCaloriesSum = sum
            }
            sum = 0
        }
    }

    maxCaloriesSum
}

println(maxCaloriesSum(lines))

//part2

static int sumOfMaxThreeCaloriesSum(List<Integer> lines) {
    List<Integer> sums = []
    int sum = 0
    for (int calorie : lines) {
        if (calorie > 0) {
            sum += calorie
        } else {
            sums << sum
            sum = 0
        }
    }

    def sortedSums = sums.sort((x,y) -> (x > y) ? -1 : ((x == y) ? 0 : 1))

    (0..2).sum {sortedSums[it]} as int
}

println(sumOfMaxThreeCaloriesSum(lines))