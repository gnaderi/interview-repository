package com.clarusone.poker;

import com.clarusone.poker.exception.InvalidHandException;

import java.util.List;

public class PokerHand implements Comparable<PokerHand> {
    private HandEvaluationResult evaluationResult;

    public PokerHand(String fiveCards) throws InvalidHandException {
        HandParser handParser = new DefaultHandParserImpl();
        List<Card> cards = handParser.parse(fiveCards);
        HandProcessor handProcessor = new HandProcessorImpl();
        evaluationResult = handProcessor.evaluate(cards);
    }

    @Override
    public int compareTo(PokerHand opponentHand) {
        int result = this.evaluationResult.getHandType().value.compareTo(opponentHand.evaluationResult.getHandType().value);
        result = result == 0 ? this.evaluationResult.getScore().compareTo(opponentHand.evaluationResult.getScore()) : result;
        if (result > 0) return HandResult.WIN.comparatorValue;
        if (result < 0) return HandResult.LOSS.comparatorValue;
        return HandResult.TIE.comparatorValue;
    }
}
