package it.xpug.advent2023;

import it.xpug.advent2023.Day03.Coordinates;
import org.junit.jupiter.api.Test;

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

        List<Coordinates> coordinates = day03.delimitNumbers();

        assertThat(coordinates).contains(
                Coordinates.of(0, 0, 3),
                Coordinates.of(0, 5, 8),
                Coordinates.of(2, 2, 4)
        );
    }

    @Test
    void findSymbols() {
        Day03 day03 = new Day03(SAMPLE);

        List<Coordinates> coordinates = day03.symbols();

        assertThat(coordinates).contains(
                Coordinates.of(1, 3, 4),
                Coordinates.of(3, 6, 7)
        );
    }
}
