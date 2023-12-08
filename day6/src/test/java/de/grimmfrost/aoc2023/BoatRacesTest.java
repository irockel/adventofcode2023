package de.grimmfrost.aoc2023;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class BoatRacesTest
{
    /**
     * test finding the winning combinations for the boat races.
     */
    @Test
    public void testFindWinningCombinations() {
        BoatRaces boatRaces = new BoatRaces();
        int result = boatRaces.findWinningCombinations("src/test/resources/inputtest.txt");
        assertEquals(288, result);
    }

    /**
     * test finding the winning combinations for the boat races.
     */
    @Test
    public void testFindWinningCombinationsWithRealData() {
        BoatRaces boatRaces = new BoatRaces();
        int result = boatRaces.findWinningCombinations("src/main/resources/input.txt");
        assertEquals(1731600, result);
    }

    /**
     * test finding the winning combinations for the boat races.
     */
    @Test
    public void testSimulateBigRace() {
        BoatRaces boatRaces = new BoatRaces();
        long result = boatRaces.simulateBigRace("src/test/resources/inputtest.txt");
        assertEquals(71503, result);
    }

    /**
     * test finding the winning combinations for the boat races.
     */
    @Test
    public void testSimulateBigRaceRealData() {
        BoatRaces boatRaces = new BoatRaces();
        long result = boatRaces.simulateBigRace("src/main/resources/input.txt");
        assertEquals(71503, result);
    }

}
