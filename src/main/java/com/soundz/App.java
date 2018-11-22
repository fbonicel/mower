package com.soundz;

import com.soundz.model.Lawn;
import com.soundz.model.Mower;
import com.soundz.model.Position;
import com.soundz.utils.InputDataExtractor;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class App {

    public static void main(String[] args) {
        if (args.length < 1)
            throw new IllegalArgumentException("You need to pass an input file path.");

        // Occupied positions list
        List<Position> occupiedPositions = new ArrayList<>();
        Path path = Paths.get(args[0]);
        List<String> lines;
        try {
            // Sanitize lines
            lines = Files
                    .lines(path)
                    .filter(line -> !line.isEmpty())
                    .map(String::trim)
                    .collect(Collectors.toList());
        } catch (IOException ex) {
            throw new IllegalArgumentException(String.format("Passed input file path %s is not correct.",path));
        }

        if (lines.size() == 0)
            throw new IllegalArgumentException("Input file is empty.");

        Lawn lawn = InputDataExtractor.lineToLawn(lines.get(0));

        // Loop over Mowers to Move
        InputDataExtractor.linesToMowersToMove(lines).stream().forEach(mtm -> {
            Mower oldMover = mtm.mower;

            // Need to check if start position of old Mower  is valid
            oldMover.validatePosition(lawn, occupiedPositions)
                    .orElseThrow(() ->
                            new IllegalArgumentException(String.format("Sorry, the start position of your Mower %s is not valid. Already taken or outside of Lawn.", oldMover)
                            )
                    );

            // Init reduce method with old Mower and apply instructions
            Mower movedMower = mtm.getInstructionsStream()
                    .reduce(oldMover, (o, instruction) -> o
                                                        .move(instruction)
                                                        .validatePosition(lawn, occupiedPositions)
                                                        .orElse(o), (o, newMower) -> newMower);
            // Add the moved Mower position to the occupiedPositions list
            occupiedPositions.add(movedMower.position);
            System.out.println(movedMower);
        });
    }
}
