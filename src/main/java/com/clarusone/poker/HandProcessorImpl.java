package com.clarusone.poker;

import java.util.*;
import java.util.stream.Collectors;

public class HandProcessorImpl implements HandProcessor {
    /**
     * Get the type of the player's hand (e.g. royal flush).
     *
     * @return HandEvaluationResult
     */
    @Override
    public HandEvaluationResult evaluate(List<Card> hand) {
        // Analyze hand card
        HandDetails handDetails = new HandDetails(hand.toArray(new Card[0]));
        HandEvaluationResult handResult = new HandEvaluationResult();
        Map<CardRank, Integer> ranks = handDetails.cardRanks;

        // get hand type
        if (handDetails.totalSuits == 1 && ranks.containsKey(CardRank.ACE) && ranks.containsKey(CardRank.KING) && ranks.containsKey(CardRank.QUEEN) && ranks.containsKey(CardRank.JACK) && ranks.containsKey(CardRank.TEN)) {
            handResult.setScore(hand.stream().mapToInt(c -> c.getCardRank().rankValue).sum());
            handResult.setHandType(HandType.ROYAL_FLUSH);
            return handResult;
        }

        if (handDetails.totalSuits == 1 && CardRank.areConsecutive(ranks.keySet())) {
            handResult.setScore(hand.stream().mapToInt(c -> c.getCardRank().rankValue).sum());
            handResult.setHandType(HandType.STRAIGHT_FLUSH);
            return handResult;
        }
        if (handDetails.rankCounts[0] == 4) {
            CardRank card = ranks.entrySet().stream().filter(e -> e.getValue() == 4).findFirst().get().getKey();
            int score = (card.rankValue + 100) * 4;
            card = ranks.entrySet().stream().filter(e -> e.getValue() == 1).findFirst().get().getKey();
            score += card.rankValue;
            handResult.setScore(score);
            handResult.setHandType(HandType.FOUR_OF_A_KIND);
            return handResult;
        }
        if (handDetails.totalRanks == 2 && handDetails.rankCounts[0] == 3 && handDetails.rankCounts[1] == 2) {
            CardRank card = ranks.entrySet().stream().filter(e -> e.getValue() == 3).findFirst().get().getKey();
            int score = card.rankValue * 3 * 10;
            card = ranks.entrySet().stream().filter(e -> e.getValue() == 2).findFirst().get().getKey();
            score += card.rankValue * 2;
            handResult.setScore(score);
            handResult.setHandType(HandType.FULL_HOUSE);
            return handResult;
        }
        if (handDetails.totalSuits == 1) {
            handResult.setScore(hand.stream().mapToInt(c -> c.getCardRank().rankValue).sum());
            handResult.setHandType(HandType.FLUSH);
            return handResult;
        }
        if (CardRank.areConsecutive(ranks.keySet())) {
            handResult.setScore(hand.stream().mapToInt(c -> c.getCardRank().rankValue).sum());
            handResult.setHandType(HandType.STRAIGHT);
            return handResult;
        }
        if (handDetails.rankCounts[0] == 3) {
            CardRank card = ranks.entrySet().stream().filter(e -> e.getValue() == 3).findFirst().get().getKey();
            handResult.setScore(card.rankValue * 3);
            handResult.setHandType(HandType.THREE_OF_A_KIND);
            return handResult;
        }
        if (handDetails.totalRanks >= 2 && handDetails.rankCounts[0] == 2 && handDetails.rankCounts[1] == 2) {
            List<CardRank> list = ranks.entrySet().stream().filter(e -> e.getValue() == 2).map(Map.Entry::getKey).collect(Collectors.toList());
            handResult.setScore(2 * (list.get(0).rankValue + list.get(1).rankValue));
            handResult.setHandType(HandType.TWO_PAIR);
            return handResult;
        }
        if (handDetails.rankCounts[0] == 2) {
            handResult.setScore(hand.stream().mapToInt(c -> c.getCardRank().rankValue).sum());
            handResult.setHandType(HandType.ONE_PAIR);
            return handResult;
        } else {
            handResult.setScore(hand.stream().mapToInt(c -> c.getCardRank().rankValue).sum());
            handResult.setHandType(HandType.HIGH_CARD);
            return handResult;
        }
    }

}


/**
 * Calculates metrics and data for a set of cards.
 */
class HandDetails {
    /**
     * The ranks in the hand.
     */
    final Map<CardRank, Integer> cardRanks;

    /**
     * The number of cards in each distinct rank.
     */
    final int[] rankCounts;


    /**
     * The number of unique ranks.
     */
    final int totalRanks;

    /**
     * The number of unique suits.
     */
    final int totalSuits;

    protected HandDetails(Card[] cards) {
        // get rank & suit counts
        HashMap<SuitType, Integer> suits = new HashMap<>();
        cardRanks = new HashMap<>();
        for (Card card : cards) {
            cardRanks.put(card.getCardRank(), cardRanks.containsKey(card.getCardRank()) ? cardRanks.get(card.getCardRank()) + 1 : 1);
            suits.put(card.getSuitType(), suits.containsKey(card.getSuitType()) ? suits.get(card.getSuitType()) + 1 : 1);
        }

        // save metrics
        this.totalRanks = cardRanks.size();
        this.totalSuits = suits.size();

        // save rank counts
        {
            List<Integer> values = new ArrayList<>(cardRanks.values());
            values.sort((a, b) -> b - a); // sort in descending order

            int size = values.size();
            this.rankCounts = new int[size];
            for (int i = 0; i < size; i++)
                this.rankCounts[i] = values.get(i);
        }
    }
}

