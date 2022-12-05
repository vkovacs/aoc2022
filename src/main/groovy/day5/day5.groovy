package day5

def lines = new File("../../resources/day5/input").readLines()

String solution(List<String> lines, moveStrategy) {
    def separatorIndex = separatorIndex(lines)
    def stacks = stacks(lines[0..separatorIndex - 1])
    def commands = commands(lines[separatorIndex + 1..lines.size() - 1])
    println(stacks)
    println(commands)

    commands.size().times {
        def (count, from, to) = commands[it]
        moveStrategy(count as Integer, from as Integer, to as Integer, stacks)
    }

    stacks.collect {
        it.peekFirst()
    }.join()
}


List<Deque<String>> stacks(List<String> lines) {
    def stackCount = stackCount(lines)
    List<Deque<String>> stacks = []
    stackCount.times {
        stacks << new LinkedList<String>()
    }

    for (int lineIndex in (lines.size() - 2..0)) {
        def line = lines[lineIndex]

        int stackIndex = 0
        for (int chIndex = 1; chIndex < line.size(); chIndex += 4) {
            def ch = line[chIndex]
            if (ch != " ") stacks[stackIndex].push(ch)

            stackIndex++
        }
    }

    stacks
}


int stackCount(List<String> lines) {
    def stackCountLine = lines[lines.size() - 1]

    stackCountLine[-1] as Integer
}

int separatorIndex(List<String> lines) {
    int separatorIndex = 0
    while (lines[separatorIndex] != "") separatorIndex++;

    separatorIndex
}


List<Tuple<Integer>> commands(List<String> lines) {
    List<Tuple<Integer>> commands = []
    lines.forEach {
        def match = it =~ /move (\d+) from (\d+) to (\d+)/

        commands << new Tuple3<>(match[0][1], match[0][2], match[0][3])
    }

    commands
}

def move(int count, int from, int to, List<Deque<String>> stacks) {
    count.times {
        def item = stacks[from - 1].removeFirst()
        stacks[to - 1].push(item)
    }
}

def move2(int count, int from, int to, List<Deque<String>> stacks) {
    Deque<String> tmp = new LinkedList<>()
    count.times {
        def item = stacks[from - 1].removeFirst()
        tmp.push(item)
    }

    count.times {
        def item = tmp.removeFirst()
        stacks[to - 1].push(item)
    }
}

println solution(lines, this::move)
println solution(lines, this::move2)

