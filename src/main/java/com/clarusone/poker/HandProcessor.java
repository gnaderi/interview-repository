package com.clarusone.poker;

import java.util.List;

public interface HandProcessor {
    HandEvaluationResult evaluate(List<Card> hand);
}
