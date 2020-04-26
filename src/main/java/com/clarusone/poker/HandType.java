package com.clarusone.poker;

/**
 * The possible winning hands.
 */
public enum HandType {
    /**
     * Ace, King, Queen, Jack and 10 of the same suit.
     */
    ROYAL_FLUSH(900, "royal flush"),

    /**
     * Five consecutive cards of the same suit.
     */
    STRAIGHT_FLUSH(800, "straight flush"),

    /**
     * Four cards with the same rank.
     */
    FOUR_OF_A_KIND(700, "four of a kind"),

    /**
     * Three cards of the same rank together with two cards of the same rank.
     */
    FULL_HOUSE(600, "full house"),

    /**
     * Five cards of the same suit (if not consecutive).
     */
    FLUSH(500, "flush"),

    /**
     * Five consecutive cards (if not the same suit).
     */
    STRAIGHT(400, "straight"),

    /**
     * Three cards of the same rank.
     */
    THREE_OF_A_KIND(300, "three of a kind"),

    /**
     * Two cards of the same rank together with two other cards of the same rank.
     */
    TWO_PAIR(200, "two pair"),

    /**
     * Two cards of the same rank.
     */
    ONE_PAIR(100, "one pair"),

    /**
     * Highest card value.
     */
    HIGH_CARD(0, "high card");

    public final Integer value;

    public final String name;

    HandType(Integer value, String name) {
        this.value = value;
        this.name = name;
    }
}