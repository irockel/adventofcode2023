package de.grimmfrost.aoc2023;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class CamelCardsTest
{
    /**
     * test sort winning cards in camel cards game.
     */
    @Test
    public void testSortWinningCards() {
        CamelCards camelCards = new CamelCards();
        int result = camelCards.sortWinningCards("src/test/resources/inputtest.txt");
        assertEquals(6440, result);
    }

}
