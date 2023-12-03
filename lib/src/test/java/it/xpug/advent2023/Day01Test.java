package it.xpug.advent2023;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class Day01Test {

    private Integer day01(Stream<String> input) {
        return 1;
    }

    @Test
    @Disabled("WIP")
    void acceptance() {
        var input = """
                1abc2
                pqr3stu8vwx
                a1b2c3d4e5f
                treb7uchet
                """;

        assertThat(day01(Arrays.stream(input.split("\n")))).isEqualTo(142);
    }



}
