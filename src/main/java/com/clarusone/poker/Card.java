package com.clarusone.poker;

public class Card {
    private SuitType suitType;
    private CardValue cardValue;
    private CardRank cardRank;

    public Card(SuitType suitType, CardValue cardValue, CardRank cardRank) {
        this.suitType = suitType;
        this.cardValue = cardValue;
        this.cardRank = cardRank;
    }

    public SuitType getSuitType() {
        return suitType;
    }

    public void setSuitType(SuitType suitType) {
        this.suitType = suitType;
    }

    public CardValue getCardValue() {
        return cardValue;
    }

    public void setCardValue(CardValue cardValue) {
        this.cardValue = cardValue;
    }

    public CardRank getCardRank() {
        return cardRank;
    }

    public void setCardRank(CardRank cardRank) {
        this.cardRank = cardRank;
    }
}
