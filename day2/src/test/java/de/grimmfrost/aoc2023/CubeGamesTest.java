package de.grimmfrost.aoc2023;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class CubeGamesTest
{
    /**
     * Check the sum of valid games for the test input data
     */
    @Test
    public void testFindValidGames() {
        CubesGames findValidGames = new CubesGames();
        int sum = findValidGames.checkForValidGamesAndSumUp("src/test/resources/inputtest.txt");
        assertEquals(8, sum);
    }

    @Test
    public void testProcessRealData() {
        CubesGames findValidGames = new CubesGames();
        int sum = findValidGames.checkForValidGamesAndSumUp("src/main/resources/input.txt");
        assertEquals(3035, sum);
    }

    /**
     * Check the sum of minimal needed cubes multiplied and summed up.
     */
    @Test
    public void testFindAndMultiplyMinCubes() {
        CubesGames cubesGames = new CubesGames();
        int sum = cubesGames.findAndMultiplyMinCubes("src/test/resources/inputtest.txt");
        assertEquals(2286, sum);
    }

    /**
     * Check the sum of minimal needed cubes multiplied and summed up.
     */
    @Test
    public void testFindAndMultiplyMinCubesRealData() {
        CubesGames cubesGames = new CubesGames();
        int sum = cubesGames.findAndMultiplyMinCubes("src/main/resources/input.txt");
        assertEquals(66027, sum);
    }
}
