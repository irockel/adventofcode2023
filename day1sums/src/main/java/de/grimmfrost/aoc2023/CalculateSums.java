package de.grimmfrost.aoc2023;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class CalculateSums {
    public static void main(String[] args) {
        try {
            FileReader fr = new FileReader("src/resources/input.txt");
            BufferedReader br = new BufferedReader(fr);

            int sum = 0;
            while (br.ready()) {
                String line = br.readLine();
                String numberSting = String.format("%c%c", getFirstDigit(line), getLastDigit(line));
                int number = Integer.parseInt(numberSting);
                sum += number;
            }
            System.out.println(sum);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static char getFirstDigit(String line) {
        for (int i = 0; i < line.length(); i++) {
            char c = line.charAt(i);
            if (Character.isDigit(c)) {
                return c;
            }
        }

        return '0';
    }
    private static char getLastDigit(String line) {
        for (int i = line.length() -1; i >= 0; i--) {
            char c = line.charAt(i);
            if (Character.isDigit(c)) {
                return c;
            }
        }

        return '0';
    }
}


