package de.grimmfrost.aoc2023;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class PlantingTest
{
    /**
     * test resolving the food chain with the test input.
     */
    @Test
    public void testResolveFoodChain() {
        Planting planting = new Planting();
        int sum = planting.resolveFoodChain("src/test/resources/inputtest.txt");
        assertEquals(13, sum);
    }
}
