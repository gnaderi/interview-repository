package com.clarusone.poker;

import com.clarusone.poker.exception.UnsupportedCardValueException;

import java.util.*;
import java.util.stream.Collectors;

public enum CardRank {
    ONE(1, '1'), TWO(2, '2'), THREE(3, '3'), FOUR(4, '4'), FIVE(5, '5'), SIX(6, '6'), SEVEN(7, '7'),
    EIGHT(8, '8'), NINE(9, '9'), TEN(10, 'T'), JACK(11, 'J'), QUEEN(12, 'Q'), KING(13, 'K'), ACE(14, 'A');
    Integer rankValue;
    Character cardValue;

    CardRank(Integer rankValue, Character cardValue) {
        this.rankValue = rankValue;
        this.cardValue = cardValue;
    }

    public static CardRank forValue(Character cardValue) throws UnsupportedCardValueException {
        for (CardRank rank : values()) {
            if (Character.toUpperCase(cardValue) == rank.cardValue) {
                return rank;
            }
        }
        throw new UnsupportedCardValueException("Invalid Card Value Character[" + cardValue);
    }

    /**
     * Get whether the given set of cards are consecutive in the given order.
     *
     * @param cardRanks The ranks to check.
     */
    public static boolean areConsecutive(Set<CardRank> cardRanks) {
        List<CardRank> cards = cardRanks.stream().sorted(Comparator.comparing(o -> o.rankValue)).collect(Collectors.toList());
        for (int i = 0; i < cardRanks.size() - 1; i++) {
            if (Math.abs(cards.get(i).rankValue - cards.get(i + 1).rankValue) != 1)
                return false;
        }
        return true;
    }
}
