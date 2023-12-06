package de.grimmfrost.aoc2023;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/**
 * parse and resole the given food chains from the input file.
 */
public class Planting {
    public int resolveFoodChain(String inputFileLink) {
        int result = 0;
        try (FileReader fr = new FileReader(inputFileLink);
             BufferedReader br = new BufferedReader(fr)) {
            while (br.ready()) {
                String line = br.readLine();
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return result;
    }


}


