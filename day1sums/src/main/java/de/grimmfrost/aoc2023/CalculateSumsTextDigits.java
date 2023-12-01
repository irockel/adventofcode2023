package de.grimmfrost.aoc2023;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class CalculateSumsTextDigits {
    private static final String[] letterDigits =
            {"one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};

    public static void main(String[] args) {
        try {
            FileReader fr = new FileReader("src/resources/input.txt");
            BufferedReader br = new BufferedReader(fr);

            int sum = 0;
            while (br.ready()) {
                String line = br.readLine();
                String digitTokens = convertLineToDigitTokens(line);
                String numberSting = String.format("%c%c", getFirstDigit(digitTokens), getLastDigit(digitTokens));
                int number = Integer.parseInt(numberSting);
                sum += number;
            }
            System.out.println(sum);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static char getFirstDigit(String digitTokens) {
        for (int i = 0; i < digitTokens.length(); i++) {
            char c = digitTokens.charAt(i);
            if (Character.isDigit(c)) {
                return c;
            }
        }

        return '0';
    }

    private static char getLastDigit(String digitTokens) {
        for (int i = digitTokens.length() -1; i >= 0; i--) {
            char c = digitTokens.charAt(i);
            if (Character.isDigit(c)) {
                return c;
            }
        }

        return '0';
    }

    private static String convertLineToDigitTokens(String line) {
        StringBuilder resultBuilder = new StringBuilder();
        for (int i = 0; i < line.length(); i++) {
            if (Character.isDigit(line.charAt(i))) {
                resultBuilder.append(line.charAt(i));
            } else {
                for (int j = 0; j < letterDigits.length; j++) {
                    if (line.substring(i).startsWith(letterDigits[j])) {
                        resultBuilder.append(j+1);
                        break;
                    }
                }
            }

        }
        System.out.printf("converted %s to %s%n", line, resultBuilder.toString());
        return resultBuilder.toString();
    }
}
