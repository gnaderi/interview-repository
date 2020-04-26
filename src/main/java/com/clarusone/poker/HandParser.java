package com.clarusone.poker;

import com.clarusone.poker.exception.InvalidHandException;

import java.util.List;

public interface HandParser {
    List<Card> parse(String hand) throws InvalidHandException;
}
