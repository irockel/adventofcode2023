package de.grimmfrost.aoc2023;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * parse and simulate winning combinations for the boat races.
 */
public class BoatRaces {

    public int findWinningCombinations(String inputFileLink) {
        int result = 0;
        int[] times = null;
        int[] distances = null;
        try (FileReader fr = new FileReader(inputFileLink);
             BufferedReader br = new BufferedReader(fr)) {
            while (br.ready()) {
                String line = br.readLine();
                if (line.startsWith("Time")) {
                    times = parseValues(line);
                } else if (line.startsWith("Distance")) {
                    distances = parseValues(line);
                }
            }

            result = findWinningGameCombinations(times, distances);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return result;
    }

    private int findWinningGameCombinations(int[] times, int[] distances) {
        int result = 1;
        for (int i = 0; i < times.length; i++) {
            int time = times[i];
            int distance = distances[i];

            // Simulation
            int winningPossibilities = 0;
            for (int speed = 0; speed < time; speed++) {
                int remainingTime = time - speed;
                if (distance < remainingTime * speed) {
                    winningPossibilities++;
                }
            }

            result *= winningPossibilities;
        }

        return (result);
    }

    private int[] parseValues(String line) {
        String valuePart = line.split(":")[1];

        valuePart = valuePart.trim().replaceAll(" +", " ");
        System.out.printf("valuePart: %s%n", valuePart);
        String[] valueTokens = valuePart.split(" ");

        return (Arrays.stream(valueTokens).mapToInt(Integer::parseInt).toArray());
    }

    public long simulateBigRace(String inputFileLink) {
        long result = 0;
        long time = 0;
        long distance = 0;
        try (FileReader fr = new FileReader(inputFileLink);
             BufferedReader br = new BufferedReader(fr)) {
            while (br.ready()) {
                String line = br.readLine();
                if (line.startsWith("Time")) {
                    String timeToken = line.split(":")[1];
                    time = Long.parseLong(timeToken.replaceAll(" ", ""));
                } else if (line.startsWith("Distance")) {
                    String distanceToken = line.split(":")[1];
                    distance = Long.parseLong(distanceToken.replaceAll(" ", ""));
                }
            }

            result = findWinningGameCombinationForBigRace(time, distance);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return result;
    }

    private long findWinningGameCombinationForBigRace(long time, long distance) {

        // first the lower bound
        long lowerBound = 0;
        for (long speed = 0; speed < time; speed++) {
            long remainingTime = time - speed;
            if (distance < remainingTime * speed) {
                lowerBound = speed;
                break;
            }
        }

        // now the upper bound.
        long upperBound = 0;
        for (long speed = time; speed > 0; speed--) {
            long remainingTime = time - speed;
            if (distance < remainingTime * speed) {
                upperBound = speed;
                break;
            }
        }

        return (upperBound - lowerBound +1);
    }
}


