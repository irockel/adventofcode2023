package de.grimmfrost.aoc2023;

import static org.junit.Assert.assertEquals;

import org.junit.Ignore;
import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class MissingPartsTest
{
    /**
     * Check the sum of valid games for the test input data
     */
    @Test
    public void testIdentifyPartNumbers() {
        MissingParts missingParts = new MissingParts();
        int sum = missingParts.processInput("src/test/resources/inputtest.txt", MissingParts::checkPartNumbersInLine);
        assertEquals(4361, sum);
    }

    @Test @Ignore
    public void testProcessRealData() {
        MissingParts missingParts = new MissingParts();
        int sum = missingParts.processInput("src/main/resources/input.txt", MissingParts::checkPartNumbersInLine);
        assertEquals(528819, sum);
    }

    /**
     * Check the sum of valid games for the test input data
     */
    @Test
    public void testCalculateGearRatio() {
        MissingParts missingParts = new MissingParts();
        int sum = missingParts.processInput("src/test/resources/inputtest-gearratio.txt", MissingParts::processGearPowerInLine);
        assertEquals(467835, sum);
    }

    /**
     * Check the sum of valid games for the test input data
     */
    @Test
    public void testCalculateGearRatioRealValue() {
        MissingParts missingParts = new MissingParts();
        int sum = missingParts.processInput("src/main/resources/input.txt", MissingParts::processGearPowerInLine);
        assertEquals(80403602, sum);
    }
}
