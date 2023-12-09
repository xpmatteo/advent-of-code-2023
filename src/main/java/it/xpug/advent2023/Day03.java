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
        for (int i = 0; i < this.lines.size(); i++) {
            var results = pattern.matcher(lines.get(i)).results().toList();
            for (MatchResult matchResult : results) {
                result.add(PartNumber.of(i, matchResult.start(), matchResult.end()));
            }
        }
        return result;
    }

    public List<Symbol> symbols() {
        List<Symbol> result = new ArrayList<>();
        Pattern pattern = Pattern.compile("[^.\\d\\s]");
        for (int i = 0; i < this.lines.size(); i++) {
            var results = pattern.matcher(lines.get(i)).results().toList();
            for (MatchResult matchResult : results) {
                result.add(Symbol.of(i, matchResult.start()));
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
