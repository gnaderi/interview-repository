package com.clarusone.poker;

import com.clarusone.poker.exception.UnsupportedCardValueException;

public enum CardValue {
    ONE('1'), TWO('2'), THREE('3'), FOUR('4'), FIVE('5'), SIX('6'), SEVEN('7'),
    EIGHT('8'), NINE('9'), TEN('T'), JACK('J'), QUEEN('Q'), KING('K'), ACE('A');
    Character value;

    CardValue(Character value) {
        this.value = value;
    }

    public static CardValue forValue(Character value) throws UnsupportedCardValueException {
        for (CardValue cardValue : values()) {
            if (Character.toUpperCase(value) == cardValue.value) {
                return cardValue;
            }
        }
        throw new UnsupportedCardValueException("Invalid Card Value Character[" + value);
    }
}
