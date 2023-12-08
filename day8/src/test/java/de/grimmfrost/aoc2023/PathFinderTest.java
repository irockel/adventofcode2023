package de.grimmfrost.aoc2023;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class PathFinderTest
{
    /**
     * test find basic path
     */
    @Test
    public void testFindPath() {
        PathFinder pathFinder = new PathFinder();
        int result = pathFinder.findPath("src/test/resources/inputtest.txt");
        assertEquals(2, result);
    }

    /**
     * test find path multiple iterations.
     */
    @Test
    public void testFindPathMultiple() {
        PathFinder pathFinder = new PathFinder();
        int result = pathFinder.findPath("src/test/resources/inputtest2.txt");
        assertEquals(6, result);
    }

    /**
     * test find path with the real data.
     */
    @Test
    public void testFindPathRealData() {
        PathFinder pathFinder = new PathFinder();
        int result = pathFinder.findPath("src/main/resources/input.txt");
        assertEquals(13939, result);
    }

    /**
     * test find path multiple iterations.
     */
    @Test
    public void testGhostPaths() {
        PathFinder pathFinder = new PathFinder();
        long result = pathFinder.findGhostPath("src/test/resources/inputtestghosts.txt");
        assertEquals(6, result);
    }

    /**
     * test find path multiple iterations.
     */
    @Test
    public void testGhostPathsRealData() {
        PathFinder pathFinder = new PathFinder();
        long result = pathFinder.findGhostPath("src/main/resources/input.txt");
        assertEquals( 8906539031197L, result);
    }

}
