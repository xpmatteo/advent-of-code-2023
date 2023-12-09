package it.xpug.advent2023;

import it.xpug.advent2023.Day03.Number;
import it.xpug.advent2023.Day03.Symbol;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.io.IOException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class Day03Test {

    final String SAMPLE = """
467..114..
...*......
..35..633.
......#...
617*......
.....+.58.
..592.....
......755.
...$.*....
.664.598..
""";

    @Test
    void findNumbers() {
        Day03 day03 = new Day03(SAMPLE);

        List<Number> coordinates = day03.delimitNumbers();

        assertThat(coordinates).contains(
                Number.of(0, 0, 3),
                Number.of(0, 5, 8),
                Number.of(2, 2, 4)
        );
    }

    @Test
    void findSymbols() {
        Day03 day03 = new Day03(SAMPLE);

        List<Symbol> symbols = day03.symbols();

        assertThat(symbols).contains(
                Symbol.of(1, 3),
                Symbol.of(3, 6)
        );
    }

    @ParameterizedTest
    @CsvSource({
            "0, 0, 3, 0, 3, true , same row symbol right",
            "0, 1, 3, 0, 0, true , same row symbol left",
            "0, 0, 3, 0, 4, false, same row symbol right not adjacent ",
            "0, 2, 3, 0, 0, false, same row symbol left not adjacent",
            "0, 0, 3, 1, 3, true , diagonal down right",
            "1, 0, 3, 0, 3, true , diagonal up right",
            "0, 0, 3, 1, 4, false, down right not adjacent",
            "2, 1, 3, 3, 0, true , diagonal down left",
    })
    void adjacent(int row1, int columnStart1, int columnEnd1, int row2, int columnStart2, boolean expected, String description) {
        var a = Number.of(row1, columnStart1, columnEnd1);
        var b = Symbol.of(row2, columnStart2);
        assertThat(a.isAdjacentTo(b))
                .describedAs(description)
                .isEqualTo(expected);
    }

    @Test
    void findPartNumbers() {
        Day03 day03 = new Day03(SAMPLE);

        List<Number> numbers = day03.findPartNumbers();

        assertThat(numbers).doesNotContain(
                Number.of(0, 5, 8),
                Number.of(5, 7, 9)
        );
        assertThat(numbers).contains(
                Number.of(0, 0, 3)
        );
    }

    @Test
    void valueOfNumber() {
        Day03 day03 = new Day03(SAMPLE);

        int value = day03.valueOfNumber(Number.of(0, 0, 3));

        assertThat(value).isEqualTo(467);
    }

    @Test
    void sumOfPartNumbers() {
        Day03 day03 = new Day03(SAMPLE);

        int sum = day03.sumOfPartNumbers();

        assertThat(sum).isEqualTo(4361);
    }

    @Test
    void acceptancePart1() throws IOException {
        // read all bytes of file into a string
        String data = new String(getClass().getResourceAsStream("/day03.txt").readAllBytes());
        Day03 day03 = new Day03(data);

        int sum = day03.sumOfPartNumbers();

        assertThat(sum).isEqualTo(544664);
    }
}
