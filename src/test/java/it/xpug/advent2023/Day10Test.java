package it.xpug.advent2023;

import org.junit.jupiter.api.Test;


public class Day10Test {

    final String SAMPLE = """
7-F7-
.FJ|7
SJLL7
|F--J
LJ.LJ""";

    public static String cleanup(String s) {
        return s.replaceAll("F", "┌")
                .replaceAll("7", "┐")
                .replaceAll("L", "└")
                .replaceAll("J", "┘")
                .replaceAll("\\|", "│")
                .replaceAll("-", "─")
                ;
    }

    @Test
    void cleanItup() {
        System.out.println(cleanup(SAMPLE));
    }
}
