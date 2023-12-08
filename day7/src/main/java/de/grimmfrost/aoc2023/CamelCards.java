package de.grimmfrost.aoc2023;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

/**
 * parse and sort cards based on winning groups in camel cards rules.
 */
public class CamelCards {

    private ArrayList<Character> order;

    private enum HandType {
        FIVE_OF_KIND,
        FOUR_OF_KIND,
        FULL_HOUSE,
        THREE_OF_KIND,
        ONE_PAIR,
        TWO_PAIRS,
        HIGH_CARDS
    }

    public CamelCards() {
        Character[] cards = { 'A', 'K', 'Q', 'J', 'T', '9', '8', '7', '6', '5', '4', '3', '2'};
        order = new ArrayList<>();
        order.addAll(Arrays.asList(cards));
    }

    public int sortWinningCards(String inputFileLink) {
        int result = 0;
        ArrayList<CamelHand> hands = new ArrayList<>();
        try (FileReader fr = new FileReader(inputFileLink);
             BufferedReader br = new BufferedReader(fr)) {
            while (br.ready()) {
                String line = br.readLine();
                String[] tokens = line.split(" ");
                hands.add(new CamelHand(tokens[0], Integer.parseInt(tokens[1])));
            }

            determineHandType(hands);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return result;
    }

    private void determineHandType(ArrayList<CamelHand> hands) {

        for (CamelHand hand : hands) {
            for (Character cardValue : order) {
                int cardCount = 0;
                for (int i = 0; i < hand.hand.length(); i++) {
                    if (hand.hand.charAt(i) == cardValue) {
                        cardCount++;
                    }
                }

                if (cardCount > 1) {

                }
            }

        }
    }

    private static class CamelHand {
        String hand;
        Integer bid;
        Map<String, Integer> sortedHand;
        HandType type;

        private CamelHand(String hand, Integer bid) {
            this.hand = hand;
            this.bid = bid;
            this.sortedHand = new ArrayList<>();
        }

        private static boolean isFourOfKind(String hand) {
            return (hand.substring(0, 4).matches("(.)\\1*") ||
                    hand.substring(1, 5).matches("(.)\\1*"));
        }
    }

}


