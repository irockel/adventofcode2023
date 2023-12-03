package de.grimmfrost.aoc2023;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * identify part numbers in the given schematic of the gondola device.
 */
public class MissingParts {
    public int processInput(String inputFileLink, Function<LineBuffer, Integer> lineProcessor) {
        int result = 0;
        LineBuffer lineBuffer = null;
        int lineWidth = 0;
        try (FileReader fr = new FileReader(inputFileLink);
             BufferedReader br = new BufferedReader(fr)) {
            while (br.ready()) {
                String line = br.readLine();
                if (lineBuffer == null) {
                    lineWidth = line.length();
                    lineBuffer = new LineBuffer(lineWidth);
                }

                lineBuffer.addLine(line);

                result += lineProcessor.apply(lineBuffer);
            }

            // special handling for last line
            if (lineBuffer != null) {
                lineBuffer.addLine(".".repeat(lineWidth));
                result += lineProcessor.apply(lineBuffer);

            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return result;
    }

    public static int checkPartNumbersInLine(LineBuffer lineBuffer) {
        int sum = 0;

        // only start if three lines are in buffer.
        if (lineBuffer.length() < 3) {
            return sum;
        }

        String lineToCheck = lineBuffer.getAt(1); // always check the line in the middle
        Pattern digitPattern = Pattern.compile("\\d+");
        Matcher digitMatcher = digitPattern.matcher(lineToCheck);
        while (digitMatcher.find()) {
            String partNumber = digitMatcher.group();

            int indexPartNumber = digitMatcher.start();
            int endIndexPartNumber = indexPartNumber + partNumber.length();

            indexPartNumber = indexPartNumber > 0 ? indexPartNumber - 1 : indexPartNumber;
            endIndexPartNumber = endIndexPartNumber >= lineToCheck.length() ?
                    endIndexPartNumber : endIndexPartNumber + 1;

            String partLineTop = lineBuffer.getAt(0).substring(indexPartNumber, endIndexPartNumber);
            String partLineMiddle = lineBuffer.getAt(1).substring(indexPartNumber, endIndexPartNumber);
            String partLineBottom = lineBuffer.getAt(2).substring(indexPartNumber, endIndexPartNumber);

            if (!partLineTop.matches("[0-9.]*") || !partLineMiddle.matches("[0-9.]*") ||
                    !partLineBottom.matches("[0-9.]*")) {
                sum += Integer.parseInt(partNumber);
            }
        }

        return sum;
    }

    public static int processGearPowerInLine(LineBuffer lineBuffer) {
        int sum = 0;

        // only start if three lines are in buffer.
        if (lineBuffer.length() < 3) {
            return sum;
        }

        String lineToCheck = lineBuffer.getAt(1); // always check the line in the middle
        Pattern starPattern = Pattern.compile("\\*+");
        Matcher starMatcher = starPattern.matcher(lineToCheck);
        while (starMatcher.find()) {
            // star in input data is always within boundaries +3 and -3 from start and end.
            int indexStar = starMatcher.start() - 3;
            int endIndexStar = starMatcher.start() + 4;

            String partLineTop = lineBuffer.getAt(0).substring(indexStar, endIndexStar);
            String partLineMiddle = lineBuffer.getAt(1).substring(indexStar, endIndexStar);
            String partLineBottom = lineBuffer.getAt(2).substring(indexStar, endIndexStar);

            ArrayList<Integer> results = new ArrayList<>(Arrays.asList(matchNumbers(partLineTop)));
            results.addAll(Arrays.asList(matchNumbers(partLineMiddle)));
            results.addAll(Arrays.asList(matchNumbers(partLineBottom)));
            if (results.size() == 2) {
                sum += (results.get(0) * results.get(1));
            }
        }

        return sum;
    }

    private static Integer[] matchNumbers(String line) {
        if (Character.isDigit(line.charAt(3))) {
            int number = Integer.parseInt(((Character.isDigit(line.charAt(1)) && Character.isDigit(line.charAt(2))) ? line.substring(1, 2) : "") +
                    (Character.isDigit(line.charAt(2)) ? line.substring(2, 3) : "") +
                    line.charAt(3) + (Character.isDigit(line.charAt(4)) ? line.substring(4, 5) : "") +
                    ((Character.isDigit(line.charAt(4)) && Character.isDigit(line.charAt(5))) ? line.substring(5, 6) : ""));
            return new Integer[]{number};
        } else if (Character.isDigit(line.charAt(2)) && !Character.isDigit(line.charAt(3)) &&
                Character.isDigit(line.charAt(4))) {
            int numberOne = extractStartNumber(line);
            int numberTwo = extractEndNumber(line);

            return new Integer[]{numberOne, numberTwo};
        } else if (!Character.isDigit(line.charAt(2)) && !Character.isDigit(line.charAt(3)) &&
                Character.isDigit(line.charAt(4))) {
            int number = extractEndNumber(line);
            return new Integer[]{number};
        } else if (Character.isDigit(line.charAt(2)) && !Character.isDigit(line.charAt(3)) &&
                !Character.isDigit(line.charAt(4))) {
            int number = extractStartNumber(line);
            return new Integer[]{number};
        }

        return new Integer[0];
    }

    private static int extractStartNumber(String line) {
        return Integer.parseInt(((Character.isDigit(line.charAt(0)) && Character.isDigit(line.charAt(1)))? line.substring(0, 1) : "") +
                (Character.isDigit(line.charAt(1)) ? line.substring(1, 2) : "") + line.charAt(2));
    }

    private static int extractEndNumber(String line) {
        return Integer.parseInt("" + line.charAt(4) + (Character.isDigit(line.charAt(5)) ? line.substring(5, 6) : "") +
                ((Character.isDigit(line.charAt(5)) && Character.isDigit(line.charAt(6))) ? line.substring(6, 7) : ""));
    }
}


