package day7

class File implements Node{
    String name
    long size
    Node parent

    static File of(String line, Node parent) {
        def m = line =~ /(\d+) ([a-z.]+)/
        new File(size: m[0][1] as long, name: m[0][2], parent: parent)
    }
}
