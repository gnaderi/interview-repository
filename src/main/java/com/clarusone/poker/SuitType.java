package com.clarusone.poker;

import com.clarusone.poker.exception.UnsupportedSuitTypeException;

public enum SuitType {
    SPADES('S'), HEARTS('H'), DIAMONDS('D'), CLUBS('C');
    Character value;

    SuitType(Character value) {
        this.value = value;
    }

    public static SuitType forValue(Character value) throws UnsupportedSuitTypeException {
        for (SuitType type : values()) {
            if (Character.toUpperCase(value) == type.value) {
                return type;
            }
        }
        throw new UnsupportedSuitTypeException("Invalid Suit Type Character[" + value);
    }
}
