package it.xpug.advent2023;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.provider.CsvSource;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.lang.Character.isDigit;
import static org.assertj.core.api.Assertions.assertThat;

public class Day01Test {

    private Integer day01(Stream<String> input) {
        Function<Integer, Integer> timesTen = n -> n * 10;
        Function<Integer, Integer> id = n -> n;
        Function<Pair<Integer, Integer>, Integer> sum = pair -> pair.left() + pair.right();

        Function<String, Integer> processLine =
                Pair.split(this::firstDigitInString, this::lastDigitInString)
                        .andThen(Pair.cross(timesTen, id))
                        .andThen(sum);

        return input.map(processLine).reduce(0, Integer::sum);
    }

    static String[] DIGITS = {
            "zero",
            "one",
            "two",
            "three",
            "four",
            "five",
            "six",
            "seven",
            "eight",
            "nine",
    };

    private Optional<Integer> startsWithDigit(String s) {
        if (isDigit(s.charAt(0))) {
            return Optional.of(s.charAt(0) - '0');
        }
        for (int j = 1; j < DIGITS.length; j++) {
            if (s.startsWith(DIGITS[j])) {
                return Optional.of(j);
            }
        }
        return Optional.empty();
    }

    private Integer firstDigitInString(String s) {
        return IntStream.range(0, s.length())
                .mapToObj(s::substring)
                .flatMap(ss -> startsWithDigit(ss).stream())
                .findFirst()
                .orElseThrow();
    }

    // Thanks https://stackoverflow.com/a/24011264/164802
    static IntStream reverseRange(int from, int to) {
        return IntStream.range(from, to)
                .map(i -> to - i + from - 1);
    }

    private Integer lastDigitInString(String s) {
        return reverseRange(0, s.length())
                .mapToObj(s::substring)
                .flatMap(ss -> startsWithDigit(ss).stream())
                .findFirst()
                .orElseThrow();
    }

    @Test
    void acceptance1() {
        var input = """
                1abc2
                pqr3stu8vwx
                a1b2c3d4e5f
                treb7uchet
                """;

        assertThat(day01(Arrays.stream(input.split("\n")))).isEqualTo(142);
    }

    @Test
    void acceptance2() {
        var input = """
                two1nine
                eightwothree
                abcone2threexyz
                xtwone3four
                4nineeightseven2
                zoneight234
                7pqrstsixteen
                """;

        assertThat(day01(Arrays.stream(input.split("\n")))).isEqualTo(281);
    }

    @Test
    void solution() throws IOException {
        var result = day01(Files.lines(Path.of("src/main/resources/day01.txt")));
        System.out.println(result);
    }

    void testFirstDigitInString() {
        assertThat(firstDigitInString("1abc")).isEqualTo(1);
        assertThat(firstDigitInString("sss2abc")).isEqualTo(2);
        assertThat(firstDigitInString("two1nine")).isEqualTo(2);
        assertThat(firstDigitInString("abcone2threexyz")).isEqualTo(1);
    }

    @Test
    void testLastDigitInString() {
        assertThat(lastDigitInString("1a4bc")).isEqualTo(4);
        assertThat(lastDigitInString("sss2abc")).isEqualTo(2);
        assertThat(lastDigitInString("4nineeightseven2")).isEqualTo(2);
        assertThat(lastDigitInString("4nineeightseven")).isEqualTo(7);
        assertThat(lastDigitInString("4nineeightaaaa")).isEqualTo(8);
    }

}
