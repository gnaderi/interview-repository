package com.clarusone.poker;

public class HandEvaluationResult {
    private HandType handType;
    private Integer score;

    public HandEvaluationResult() {
    }

    public HandEvaluationResult(HandType handType, Integer score) {
        this.handType = handType;
        this.score = score;
    }

    public HandType getHandType() {
        return handType;
    }

    public void setHandType(HandType handType) {
        this.handType = handType;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }
}
