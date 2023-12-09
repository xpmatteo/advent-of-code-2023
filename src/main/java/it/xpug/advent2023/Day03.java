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

    public List<PartNumber> delimitNumbers() {
        List<PartNumber> result = new ArrayList<>();
        Pattern pattern = Pattern.compile("\\d+");
        for (int row = 0; row < this.lines.size(); row++) {
            var results = pattern.matcher(lines.get(row)).results().toList();
            for (MatchResult matchResult : results) {
                result.add(PartNumber.of(row, matchResult.start(), matchResult.end()));
            }
        }
        return result;
    }

    public List<Symbol> symbols() {
        List<Symbol> result = new ArrayList<>();
        Pattern pattern = Pattern.compile("[^.\\d\\s]");
        for (int row = 0; row < this.lines.size(); row++) {
            var results = pattern.matcher(lines.get(row)).results().toList();
            for (MatchResult matchResult : results) {
                result.add(Symbol.of(row, matchResult.start()));
            }
        }
        return result;
    }

    public record Symbol(int row, int column) {
        public static Symbol of(int row, int column) {
            return new Symbol(row, column);
        }
    }

    public record PartNumber(int row, int columnStart, int columnEnd) {
        public static PartNumber of(int row, int columnStart, int columnEnd) {
            return new PartNumber(row, columnStart, columnEnd);
        }

        public boolean isAdjacentTo(Symbol other) {
            if (this.row == other.row) {
                return this.columnEnd == other.column || other.column + 1 == this.columnStart;
            }
            if (Math.abs(this.row - other.row) == 1) {
                return other.column >= this.columnStart-1 && other.column <= this.columnEnd;
            }
            return false;
        }
    }
}
