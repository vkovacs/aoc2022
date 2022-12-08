package day8

def lines = new File("../../resources/day8/input").readLines()

def forest = []
lines.forEach {
    forest << it.split("")
}


int part1(List<List<Integer>> forest) {
    int forestSize = forest[0].size()

    List<List<Boolean>> forestVisibility = []
    forestSize.times {
        forestVisibility << (0..forestSize - 1).collect { false }
    }

//left to right
    for (int i = 0; i < forestSize; i++) {
        int max = 0
        for (int j = 0; j < forestSize; j++) {
            if (j == 0) {
                max = forest[i][j]
            }
            if (i == 0 || i == forestSize - 1 || j == 0 || j == forestSize - 1) {
                forestVisibility[i][j] = true
            } else {
                if (forest[i][j] > max) {
                    forestVisibility[i][j] = true
                    max = forest[i][j]
                }
            }
        }
    }

// right to left
    for (int i = 0; i < forestSize; i++) {
        int max = 0
        for (int j = forestSize - 1; j >= 0; j--) {
            if (j == forestSize - 1) {
                max = forest[i][j]
            }
            if (i == 0 || i == forestSize - 1 || j == 0 || j == forestSize - 1) {
                forestVisibility[i][j] = true
            } else {
                if (forest[i][j] > max) {
                    forestVisibility[i][j] = true
                    max = forest[i][j]
                }
            }
        }
    }

// up to down
    for (int j = 0; j < forestSize; j++) {
        int max = 0
        for (int i = 0; i < forestSize; i++) {
            if (i == 0) {
                max = forest[i][j]
            }
            if (i == 0 || i == forestSize - 1 || j == 0 || j == forestSize - 1) {
                forestVisibility[i][j] = true
            } else {
                if (forest[i][j] > max) {
                    forestVisibility[i][j] = true
                    max = forest[i][j]
                }
            }
        }
    }
//down to up
    for (int j = 0; j < forestSize; j++) {
        int max = 0
        for (int i = forestSize - 1; i >= 0; i--) {
            if (i == forestSize - 1) {
                max = forest[i][j]
            }
            if (i == 0 || i == forestSize - 1 || j == 0 || j == forestSize - 1) {
                forestVisibility[i][j] = true
            } else {
                if (forest[i][j] > max) {
                    forestVisibility[i][j] = true
                    max = forest[i][j]
                }
            }
        }
    }

    forestVisibility.flatten().count { it == true }
}


def solution1 = part1(forest)
println solution1
assert solution1 == 1796


