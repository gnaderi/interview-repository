package com.clarusone.poker;

import static com.clarusone.poker.HandResult.*;
import static org.junit.Assert.assertEquals;

import com.clarusone.poker.exception.InvalidHandException;
import org.junit.Test;

public class PokerHandTests {

    @Test
    public void highest_straight_flush_wins() throws InvalidHandException {
        compareHands(LOSS, "2H 3H 4H 5H 6H", "KS AS TS QS JS");
    }

    @Test
    public void straight_flush_beats_4_of_a_kind() throws InvalidHandException {
        compareHands(WIN, "2H 3H 4H 5H 6H", "AS AD AC AH JD");
    }

    @Test
    public void highest_4_of_a_kind_wins() throws InvalidHandException {
        compareHands(WIN, "AS AH 2H AD AC", "JS JD JC JH 3D");
    }

    @Test
    public void four_of_a_kind_beats_a_full_house() throws InvalidHandException {
        compareHands(LOSS, "2S AH 2H AS AC", "JS JD JC JH AD");
    }

    @Test
    public void full_house_beats_a_flush() throws InvalidHandException {
        compareHands(WIN, "2S AH 2H AS AC", "2H 3H 5H 6H 7H");
    }

    @Test
    public void highest_flush_wins() throws InvalidHandException {
        compareHands(WIN, "AS 3S 4S 8S 2S", "2H 3H 5H 6H 7H");
    }

    @Test
    public void flush_beats_a_straight() throws InvalidHandException {
        compareHands(WIN, "2H 3H 5H 6H 7H", "2S 3H 4H 5S 6C");
    }

    @Test
    public void two_straights_with_same_highest_card_tie() throws InvalidHandException {
        compareHands(TIE, "2S 3H 4H 5S 6C", "3D 4C 5H 6H 2S");
    }

    @Test
    public void straight_beats_three_of_a_kind() throws InvalidHandException {
        compareHands(WIN, "2S 3H 4H 5S 6C", "AH AC 5H 6H AS");
    }

    @Test
    public void three_of_a_kind_beats_two_pairs() throws InvalidHandException {
        compareHands(LOSS, "2S 2H 4H 5S 4C", "AH AC 5H 6H AS");
    }

    @Test
    public void two_pairs_beats_a_single_pair() throws InvalidHandException {
        compareHands(WIN, "2S 2H 4H 5S 4C", "AH AC 5H 6H 7S");
    }

    @Test
    public void highest_pair_wins() throws InvalidHandException {
        compareHands(LOSS, "6S AD 7H 4S AS", "AH AC 5H 6H 7S");
    }

    @Test
    public void pair_beats_a_high_card() throws InvalidHandException {
        compareHands(LOSS, "2S AH 4H 5S KC", "AH AC 5H 6H 7S");
    }

    @Test
    public void lowest_card_loses() throws InvalidHandException {
        compareHands(LOSS, "2S 3H 6H 7S 9C", "7H 3C TH 6H 9S");
    }

    @Test
    public void highest_card_wins() throws InvalidHandException {
        compareHands(WIN, "4S 5H 6H TS AC", "3S 5H 6H TS AC");
    }

    @Test
    public void equal_cards_tie() throws InvalidHandException {
        compareHands(TIE, "2S AH 4H 5S 6C", "AD 4C 5H 6H 2C");
    }

    @Test
    public void two_full_houses_highest_three_of_a_kind_wins() throws InvalidHandException {
        compareHands(WIN, "KS KH KD 2S 2C", "JS JH JD AS AC");
    }
    
    private void compareHands(HandResult expectedResult, String playerHand, String opponentHand) throws InvalidHandException {
        PokerHand player = new PokerHand(playerHand);
        PokerHand opponent = new PokerHand(opponentHand);
        int actualResult = player.compareTo(opponent);
        assertEquals(expectedResult.comparatorValue, actualResult);
    }
}

