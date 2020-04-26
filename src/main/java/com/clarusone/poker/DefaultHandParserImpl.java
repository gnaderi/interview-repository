package com.clarusone.poker;

import com.clarusone.poker.exception.InvalidHandException;
import com.clarusone.poker.exception.UnsupportedCardValueException;
import com.clarusone.poker.exception.UnsupportedSuitTypeException;

import java.util.ArrayList;
import java.util.List;

public class DefaultHandParserImpl implements HandParser {
    @Override
    public List<Card> parse(String stringOfCards) throws InvalidHandException {
        try {
            String[] rawCards = stringOfCards.split(" ");
            List<Card> hand = new ArrayList<>();
            for (String rawCard : rawCards) {
                Card card = parseRawCardValue(rawCard);
                hand.add(card);
            }
            return hand;
        } catch (Exception exception) {
            throw new InvalidHandException("Unable to parse the hand.");
        }
    }

    private Card parseRawCardValue(String rawCard) throws UnsupportedSuitTypeException, UnsupportedCardValueException {
        CardRank cardRank = CardRank.forValue(rawCard.charAt(0));
        CardValue cardValue = CardValue.forValue(rawCard.charAt(0));
        SuitType suitType = SuitType.forValue(rawCard.charAt(1));
        return new Card(suitType, cardValue, cardRank);
    }
}
