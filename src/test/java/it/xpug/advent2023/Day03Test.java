package it.xpug.advent2023;

import it.xpug.advent2023.Day03.PartNumber;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

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
    void delimitNumbers() {
        Day03 day03 = new Day03(SAMPLE);

        List<PartNumber> coordinates = day03.delimitNumbers();

        assertThat(coordinates).contains(
                PartNumber.of(0, 0, 3),
                PartNumber.of(0, 5, 8),
                PartNumber.of(2, 2, 4)
        );
    }

    @Test
    void findSymbols() {
        Day03 day03 = new Day03(SAMPLE);

        List<PartNumber> coordinates = day03.symbols();

        assertThat(coordinates).contains(
                PartNumber.of(1, 3, 4),
                PartNumber.of(3, 6, 7)
        );
    }

    @ParameterizedTest
    @CsvSource({
            "0, 0, 3, 0, 3, 4, true , same row",
            "0, 0, 3, 0, 4, 5, false, same row not adjacent ",
            "0, 0, 3, 2, 3, 4, false, not same row",
//            "0, 0, 3, 1, 3, 4, true , diagonal right",
    })
    void adjacent(int row1, int columnStart1, int columnEnd1, int row2, int columnStart2, int columnEnd2, boolean expected, String description) {
        PartNumber a = PartNumber.of(row1, columnStart1, columnEnd1);
        PartNumber b = PartNumber.of(row2, columnStart2, columnEnd2);
        assertThat(a.isAdjacentTo(b))
                .describedAs(description + " a<->b")
                .isEqualTo(expected);
        assertThat(b.isAdjacentTo(a))
                .describedAs(description + " b<->a")
                .isEqualTo(expected);
    }
}
