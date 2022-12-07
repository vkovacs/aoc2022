package day7

class Directory implements Node {
    String name
    Map<String, Node> children = [:]
    Node parent
    long size = 0

    static boolean isDir(String line) {
        line =~ /dir .*/
    }

    static Directory of(String line, Node parent) {
        def m = line =~ /dir ([a-z.]+)/
        new Directory(name: m[0][1], parent: parent)
    }

    List<Directory> subDirectories() {
        children.values().findAll {
            it instanceof Directory
        } as List<Directory>
    }
}
