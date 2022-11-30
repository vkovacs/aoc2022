import day1.day1
import org.junit.jupiter.api.Test

class Day1Test {
    @Test
    void name() {
        def day1 = new day1()

        assert day1.greet() == "hello"
    }
}
