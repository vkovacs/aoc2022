package day7

def lines = new java.io.File("../../resources/day7/input").readLines()


Directory root(List<String> lines) {
    Node root = new Directory(name: "/")
    Directory head = root

    def i = 1
    while (i < lines.size()) {
        if (lines[i] == /$ ls/) {
            i = i + 1
            while (i < lines.size() && !lines[i].startsWith(/$/)) {
                if (Directory.isDir(lines[i])) {
                    def newDirectory = Directory.of(lines[i], head)
                    head.children.put(newDirectory.name, newDirectory)
                } else {
                    def newFile = File.of(lines[i], head)
                    head.children.put(newFile.name, newFile)
                }
                i++
            }
        }

        def cmdCommandMatch = lines[i] =~ /\$ cd ([a-z.]+)/
        if (cmdCommandMatch.matches()) {
            def directoryName = cmdCommandMatch[0][1]
            if (directoryName == "..") {
                head = head.parent as Directory
            } else {
                head = head.children[directoryName] as Directory
            }
            i++
        }
    }
    root
}

def calculateSize(Directory head) {
    long size = 0
    for (Node node : head.children.values()) {
        if (node instanceof File) {
            size += node.size
        } else {
            size += calculateSize(node as Directory)
        }
    }
    head.size = size
}

List<Directory> filterDirectories(Directory node, def sizeFilter) {
    List<Directory> filteredDirectories = []
    node.subDirectories().forEach
            {
                if (sizeFilter(it.size)) filteredDirectories << it

                filteredDirectories.addAll(filterDirectories(it, sizeFilter))
            }

    return filteredDirectories
}

def root = root(lines)
calculateSize(root)

long part1(Directory root) {
    filterDirectories(root, { value -> value <= 100_000 })
            .collect {
                it.size
            }.sum() as long
}

def par1Solution = part1(root)
assert par1Solution == 1443806
println par1Solution


long part2(Directory root) {
    long currentFreeSpace = 70000000 - root.size
    long spaceNeedsToBeFree = 30000000 - currentFreeSpace

    def largerThenSpaceNeedsToBeFreed = { value ->
        value >= spaceNeedsToBeFree
    }

    filterDirectories(root, largerThenSpaceNeedsToBeFreed)
            .collect {
                it.size
            }
            .min()
}

def part2Solution = part2(root)
assert part2Solution == 942298
println part2Solution





