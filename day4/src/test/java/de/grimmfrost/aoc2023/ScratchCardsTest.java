package de.grimmfrost.aoc2023;

import static org.junit.Assert.assertEquals;

import org.junit.Ignore;
import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class ScratchCardsTest
{
    /**
     * Test find the winning numbers on the scratch cards.
     */
    @Test
    public void testFindWinningNumbersOnCards() {
        ScratchCards scratchCards = new ScratchCards();
        int sum = scratchCards.findWinningNumbers("src/test/resources/inputtest.txt");
        assertEquals(13, sum);
    }

    @Test
    public void testProcessRealData() {
        ScratchCards scratchCards = new ScratchCards();
        int sum = scratchCards.findWinningNumbers("src/main/resources/input.txt");
        assertEquals(25004, sum);
    }

    @Test
    public void testCollectScratchCards() {
        ScratchCards scratchCards = new ScratchCards();
        int sum = scratchCards.collectScratchCards("src/test/resources/inputtest.txt");
        assertEquals(30, sum);
    }

    @Test
    public void testCollectScratchCardsRealData() {
        ScratchCards scratchCards = new ScratchCards();
        int sum = scratchCards.collectScratchCards("src/main/resources/input.txt");
        assertEquals(14427616, sum);
    }

}
