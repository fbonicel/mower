package com.soundz.utils;

import com.soundz.model.*;

import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * The InputDataExtractor class exposes static methods to validate and convert
 * String lines to domain objects.
 */
public class InputDataExtractor {
    private final static String DELIMITER = " ";
    private final static String LAWN_PATTERN = "^\\d+\\s\\d+$";
    private final static String MOWER_PATTERN = "^\\d+\\s\\d+\\s[NSEW]$";
    private final static String INSTRUCTIONS_PATTERN = "^[LRF]*$";

    /**
     * Validate and convert String lines to list of {@link MowerToMove}.
     *
     * @param lines a List of String lines
     * @return a List of Mowers to move
     */
    public static List<MowerToMove> linesToMowersToMove(List<String> lines) {
        if (lines.size() < 3 || lines.size() % 2 == 0)
            throw new IllegalArgumentException("Not enough data !");

        return IntStream.range(1, lines.size() - 1)
                .filter(s -> s % 2 != 0) // sliding window size 2 step 2
                .mapToObj(idx -> {
                    List<String> mowerLines = lines.subList(idx, idx + 2);
                    Mower mower = lineToMower(mowerLines.get(0), idx);
                    String instructions = lineToInstructions(mowerLines.get(1), idx + 1);
                    return new MowerToMove(mower, instructions);
                })
                .collect(Collectors.toList());
    }

    /**
     * Validate and convert a String line to {@link Lawn}.
     *
     * @param line a String line
     * @return a {@link Lawn}
     */
    public static Lawn lineToLawn(String line) {
        if (!Pattern.matches(LAWN_PATTERN, line))
            throw new IllegalArgumentException(String.format("Lawn format error at line 1 : %s.", line));
        String[] lawnArray = line.split(DELIMITER);
        return new Lawn(Integer.parseInt(lawnArray[0]), Integer.parseInt(lawnArray[1]));
    }

    /**
     * Validate and convert a String line to {@link Mower}.
     *
     * @param line a String line
     * @return a {@link Mower}
     */
    private static Mower lineToMower(String line, int lineIndex) {
        if (!Pattern.matches(MOWER_PATTERN, line))
            throw new IllegalArgumentException(String.format("Mower format error at line %s : %s. Example : 1 2 N.", lineIndex, line));

        String[] mowerArray = line.split(DELIMITER);

        Position position = new Position(Integer.parseInt(mowerArray[0]), Integer.parseInt(mowerArray[1]));
        return new Mower(position, Orientation.valueOf(mowerArray[2]));
    }

    /**
     * Validate and convert a String line to instructions.
     *
     * @param line a String line
     * @return a String representing the instructions
     */
    private static String lineToInstructions(String line, int lineIndex) {
        if (!Pattern.matches(INSTRUCTIONS_PATTERN, line)) {
            throw new IllegalArgumentException(String.format("Instructions format error at line $s : %s. Only L,R or F instructions accepted.", lineIndex, line));
        }

        return line;
    }
}
