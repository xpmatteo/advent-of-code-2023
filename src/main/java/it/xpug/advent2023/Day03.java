package it.xpug.advent2023;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.MatchResult;
import java.util.regex.Pattern;

public class Day03 {
    private final List<String> lines;

    public Day03(String input) {
        this.lines = Arrays.asList(input.split("\n"));
    }

    public List<Coordinates> delimitNumbers() {
        List<Coordinates> result = new ArrayList<>();
        Pattern pattern = Pattern.compile("\\d+");
        for (int i = 0; i < this.lines.size(); i++) {
            var results = pattern.matcher(lines.get(i)).results().toList();
            for (MatchResult matchResult : results) {
                result.add(Coordinates.of(i, matchResult.start(), matchResult.end()));
            }
        }
        return result;
    }

    public List<Coordinates> symbols() {
        List<Coordinates> result = new ArrayList<>();
        Pattern pattern = Pattern.compile("[^.\\d\\s]");
        for (int i = 0; i < this.lines.size(); i++) {
            var results = pattern.matcher(lines.get(i)).results().toList();
            for (MatchResult matchResult : results) {
                result.add(Coordinates.of(i, matchResult.start(), matchResult.end()));
            }
        }
        return result;
    }

    public record Coordinates(int row, int columnStart, int columnEnd) {
        public static Coordinates of(int row, int columnStart, int columnEnd) {
            return new Coordinates(row, columnStart, columnEnd);
        }

        public boolean isAdjacentTo(Coordinates other) {
            boolean sameRow = this.columnEnd == other.columnStart && this.row == other.row
                    || other.columnEnd == this.columnStart && this.row == other.row;

            boolean diagonal = other.row == this.row + 1 && other.columnEnd == this.columnEnd + 1;
            return sameRow || diagonal
                    ;
        }
    }
}
