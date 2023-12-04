package de.grimmfrost.aoc2023;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.regex.Pattern;

/**
 * Parse and Process the scratch cards from the given input file.
 */
public class ScratchCards {
    public int findWinningNumbers(String inputFileLink) {
        int result = 0;
        try (FileReader fr = new FileReader(inputFileLink);
             BufferedReader br = new BufferedReader(fr)) {
            while (br.ready()) {
                String line = br.readLine();
                result += (int) Math.pow(2, processWinningNumbersOnCard(line) -1);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return result;
    }

    private int processWinningNumbersOnCard(String line) {
        int result = 0;

        String[] lineTokens = line.split("\\|");

        String[] winningNumbersArray = lineTokens[0].split(":")[1].trim().
                replaceAll(" {2}", " ").split(" ");
        ArrayList<String> winningNumbers = new ArrayList<>(Arrays.asList(winningNumbersArray));

        String[] ownNumbers = lineTokens[1].trim().replaceAll(" {2}", " ").split(" ");
        for (String ownNumber : ownNumbers) {
            if (winningNumbers.contains(ownNumber)) {
                result++;
            }
        }

        return result;
    }

    public int collectScratchCards(String inputFileLink) {
        int result = 0;
        Map<Integer, Integer> collectedCards = new HashMap<>();

        try (FileReader fr = new FileReader(inputFileLink);
             BufferedReader br = new BufferedReader(fr)) {
            while (br.ready()) {
                String line = br.readLine();
                int currentCardNumber = extractCardNumber(line);
                int copies = collectedCards.containsKey(currentCardNumber)
                        ? collectedCards.get(currentCardNumber)+1 : 1;
                int wonCards = processWinningNumbersOnCard(line);
                result++; // count the current card, regardless of won or not.
                for (int i = 0; i < copies; i++) {
                    for (int j = 1; j <= wonCards; j++) {
                        if (collectedCards.containsKey(currentCardNumber + j)) {
                            collectedCards.put(currentCardNumber +j, collectedCards.get(currentCardNumber +j) +1);
                        } else {
                            collectedCards.put(currentCardNumber +j, 1);
                        }
                    }

                }
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        for (Integer collected : collectedCards.values()) {
            result += collected;
        }
        return result;
    }

    private Integer extractCardNumber(String line) {
        String [] cardTokens = line.split(":")[0].split(" ");
        String cardNumber = cardTokens[cardTokens.length-1];
        return(Integer.parseInt(cardNumber));
    }
}


