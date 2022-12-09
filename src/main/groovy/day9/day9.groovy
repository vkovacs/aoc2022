package day9

def lines = new File("../../resources/day9/input").readLines()

def commands = lines.collect { line ->
    def m = line =~ /([LRUD]) (\d+)/
    String direction = m[0][1]
    def step = m[0][2] as int
    new Tuple2<>(direction, step)
}

def solution1(List<Tuple2<String, Integer>> commands) {

    def visited = [] as Set
    def head = new Position(0, 0)
    def tail = new Position(0, 0)
    visited << tail

    commands.forEach { command ->
        def (direction, step) = command
        step.times {
            head = moveHead(direction, head)
            tail = moveTail(head, tail)

            visited << tail
        }
    }
    visited.size()
}

Position moveHead(String direction, Position position) {
    int x = position.x
    int y = position.y

    switch (direction) {
        case "L": --y; break
        case "R": ++y; break
        case "U": --x; break
        case "D": ++x; break
    }
    new Position(x, y)
}

Position moveTail(Position head, Position tail) {
    if (Math.abs(head.x - tail.x) < 2 && Math.abs(head.y - tail.y) < 2) {
        return tail
    }

    //in the same row
    if (head.x == tail.x) {
        if (head.y > tail.y) return new Position(tail.x, tail.y + 1)
        else return new Position(tail.x, tail.y - 1)
    }

    //in the same col
    if (head.y == tail.y) {
        if (head.x > tail.x) return new Position(tail.x + 1, tail.y)
        else return new Position(tail.x - 1, tail.y)
    }

    //move diagonally
    if (head.x != tail.x && head.y != tail.y) {
        int diffX = head.x - tail.x
        int diffY = head.y - tail.y

        double moveXHalf = diffX / 2
        double moveYHalf = diffY / 2

        int moveX = moveXHalf < 0 ? Math.floor(moveXHalf) as int : Math.ceil(moveXHalf) as int
        int moveY = moveYHalf < 0 ? Math.floor(moveYHalf) as int : Math.ceil(moveYHalf) as int

        return new Position(tail.x + moveX, tail.y + moveY)
    }
}

println solution1(commands)