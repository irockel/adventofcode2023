package de.grimmfrost.aoc2023;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * find valid games which fit in the max amount of cubes displayed
 * only 12 red cubes, 13 green cubes, and 14 blue cubes
 */
public class CubesGames {
    public int checkForValidGamesAndSumUp(String inputFileLink) {
        int result = 0;
        try {
            FileReader fr = new FileReader(inputFileLink);
            BufferedReader br = new BufferedReader(fr);
            while (br.ready()) {
                String line = br.readLine();
                String[] lineTokens = line.split(":");
                if (isValidGame(lineTokens[1].trim())) {
                    int gameId = Integer.parseInt(lineTokens[0].split(" ")[1]);
                    result += gameId;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return result;
    }

    private boolean isValidGame(String gameData) {
        String[] gameMoves = gameData.split(";");
        for (String gameMove : gameMoves) {
            String[] moveTokens = gameMove.trim().split(",");
            for (String moveToken : moveTokens) {
                if (moveToken.contains("red")) {
                    int numCubes = extractNumber(moveToken);
                    if (numCubes > 12) {
                        return false;
                    }
                } else if (moveToken.contains("green")) {
                    int numCubes = extractNumber(moveToken);
                    if (numCubes > 13) {
                        return false;
                    }
                } else if (moveToken.contains("blue")) {
                    int numCubes = extractNumber(moveToken);
                    if (numCubes > 14) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    private int extractNumber(String moveToken) {
        return Integer.parseInt(moveToken.trim().split(" ")[0]);
    }

    public int findAndMultiplyMinCubes(String inputFileLink) {
        int result = 0;
        try {
            FileReader fr = new FileReader(inputFileLink);
            BufferedReader br = new BufferedReader(fr);
            while (br.ready()) {
                String line = br.readLine();
                String[] lineTokens = line.split(":");
                int cubes = checkMinCubesForGameAndMultiply(lineTokens[1]);
                result += cubes;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return result;
    }

    private int checkMinCubesForGameAndMultiply(String gameData) {
        String[] gameMoves = gameData.split(";");
        int redCubes = 0;
        int greenCubes = 0;
        int blueCubes = 0;
        for (String gameMove : gameMoves) {
            String[] moveTokens = gameMove.trim().split(",");
            for (String moveToken : moveTokens) {
                if (moveToken.contains("red")) {
                    redCubes = Math.max(extractNumber(moveToken), redCubes);
                } else if (moveToken.contains("green")) {
                    greenCubes = Math.max(extractNumber(moveToken), greenCubes);
                } else if (moveToken.contains("blue")) {
                    blueCubes = Math.max(extractNumber(moveToken), blueCubes);
                }
            }
        }
        return redCubes * greenCubes * blueCubes;
    }

}


