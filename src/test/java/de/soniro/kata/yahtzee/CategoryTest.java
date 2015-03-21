package de.soniro.kata.yahtzee;

import org.junit.Test;

import java.util.Arrays;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static de.soniro.kata.yahtzee.Category.*;
import static de.soniro.kata.yahtzee.Dice.*;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class CategoryTest {

    @Test
    public void onesIsAPossibleCategoryForADiceContainingAOne() {
        assertTrue(ONES.isPossibleCategoryFor(ONE));
    }

    @Test
    public void onesIsNotAPossibleCategoryForADiceNotContainingAOne() {
        assertFalse(ONES.isPossibleCategoryFor(TWO, THREE, FOUR, FIVE, SIX));
    }

    @Test
    public void twosIsAPossibleCategoryForADiceContainingATwo() {
        assertTrue(TWOS.isPossibleCategoryFor(TWO));
    }

    @Test
    public void twosIsNotAPossibleCategoryForADiceNotContainingATwo() {
        assertFalse(TWOS.isPossibleCategoryFor(ONE, THREE, FOUR, FIVE, SIX));
    }

    @Test
    public void threesIsAPossibleCategoryForADiceContainingAThree() {
        assertTrue(THREES.isPossibleCategoryFor(THREE));
    }

    @Test
    public void threesIsNotAPossibleCategoryForADiceNotContainingAThree() {
        assertFalse(THREES.isPossibleCategoryFor(ONE, TWO, FOUR, FIVE, SIX));
    }

    @Test
    public void foursIsAPossibleCategoryForADiceContainingAFour() {
        assertTrue(FOURS.isPossibleCategoryFor(FOUR));
    }

    @Test
    public void foursIsNotAPossibleCategoryForADiceNotContainingAFour() {
        assertFalse(FOURS.isPossibleCategoryFor(ONE, TWO, THREE, FIVE, SIX));
    }

    @Test
    public void fivesIsAPossibleCategoryForADiceContainingAFive() {
        assertTrue(FIVES.isPossibleCategoryFor(FIVE));
    }

    @Test
    public void fivesIsNotAPossibleCategoryForADiceNotContainingAFive() {
        assertFalse(FIVES.isPossibleCategoryFor(ONE, TWO, THREE, FOUR, SIX));
    }

    @Test
    public void sixesIsAPossibleCategoryForADiceContainingASix() {
        assertTrue(SIXES.isPossibleCategoryFor(SIX));
    }

    @Test
    public void sixesIsNotAPossibleCategoryForADiceNotContainingASix() {
        assertFalse(SIXES.isPossibleCategoryFor(ONE, TWO, THREE, FOUR, FIVE));
    }

    @Test
    public void yahtzeeIsAPossibleCategoryIfAllDiceAreEqual() {
        assertTrue(YAHTZEE.isPossibleCategoryFor(ONE, ONE, ONE, ONE, ONE));
    }

    @Test
    public void yahtzeeIsNotAPossibleCategoryIfSomeDiceDiffer() {
        assertFalse(YAHTZEE.isPossibleCategoryFor(ONE, TWO));
    }

    @Test
    public void fullHouseIsAPossibleCategoryForARollContainingTwoOfAKindAndThreeOfADifferentKind() {
        assertTrue(FULL_HOUSE.isPossibleCategoryFor(ONE, ONE, ONE, TWO, TWO));
    }

    @Test
    public void fullHouseIsNotAPossibleCategoryForAYahtzee() {
        assertFalse(FULL_HOUSE.isPossibleCategoryFor(SIX, SIX, SIX, SIX, SIX));
    }

    @Test
    public void fullHouseIsNotPossibleForARollOnlyContainingThreeofAKind() {
        assertFalse(FULL_HOUSE.isPossibleCategoryFor(THREE, THREE, THREE, ONE, TWO));
    }

    @Test
    public void threeOfAKindIsAPossibleCategoryForARollContainingAtLeastOneDieThreeTimes() {
        assertTrue(THREE_OF_A_KIND.isPossibleCategoryFor(ONE, TWO, THREE, THREE, THREE));
    }

    @Test
    public void threeOfAKindIsAPossibleCategoryForARollContainingAtLeastOneDieMoreThanThreeTimes() {
        assertTrue(THREE_OF_A_KIND.isPossibleCategoryFor(ONE, THREE, THREE, THREE, THREE));
    }

    @Test
    public void threeOfAKindIsNotAPossibleCategoryForARollContainingEachDieLessThanThreeTimes() {
        assertFalse(THREE_OF_A_KIND.isPossibleCategoryFor(ONE, ONE, TWO, TWO, THREE));
    }

    @Test
    public void fourOfAKindIsAPossibleCategoryForARollContainingAtLeastOneDieFourTimes() {
        assertTrue(FOUR_OF_A_KIND.isPossibleCategoryFor(FOUR, FOUR, FOUR, ONE, FOUR));
    }

    @Test
    public void fourOfAKindIsAPossibleCategoryForARollContainingAtLeastOneDieMoreThanFourTimes() {
        assertTrue(FOUR_OF_A_KIND.isPossibleCategoryFor(FOUR, FOUR, FOUR, FOUR, FOUR));
    }

    @Test
    public void fourOfAKindIsNotAPossibleCategoryForARollContainingEachDieLessThanFourTimes() {
        assertFalse(FOUR_OF_A_KIND.isPossibleCategoryFor(ONE, TWO, FOUR, FOUR, FOUR));
    }

    @Test
    public void chanceIsAPossibleCategoryForEveryRandomDice() {
        Dice[] randomDice = createRandomDice();
        assertTrue(documentFailingRandomDice(randomDice), CHANCE.isPossibleCategoryFor(randomDice));
    }

    @Test
    public void smallStraightIsAPossibleCandidateForARollHavingFourDiceInARow() {
        assertTrue(SMALL_STRAIGHT.isPossibleCategoryFor(ONE, TWO, TWO, THREE, FOUR));
    }

    @Test
    public void smallStraightIsNotAPossibleCandidateForARollHavingLessThanFourDiceInARow() {
        assertFalse(SMALL_STRAIGHT.isPossibleCategoryFor(ONE, TWO, THREE, FIVE, SIX));
    }

    @Test
    public void smallStraightIsAPossibleCandidateForARollHavingMoreThanFourDiceInARow() {
        assertTrue(SMALL_STRAIGHT.isPossibleCategoryFor(ONE, TWO, THREE, FOUR, FIVE));
    }

    @Test
    public void largeStraightIsAPossibleCandidateForARollHavingFiveDiceInARow() {
        assertTrue(LARGE_STRAIGHT.isPossibleCategoryFor(ONE, TWO, THREE, FOUR, FIVE));
    }

    @Test
    public void largeStraightIsNotAPossibleCandidateForARollHavingLessThanFiveDiceInARow() {
        assertFalse(LARGE_STRAIGHT.isPossibleCategoryFor(ONE, TWO, THREE, FOUR, SIX));
    }

    private Dice[] createRandomDice() {
        Dice[] allDices = Dice.values();
        int numberOfPossibleDices = allDices.length;
        return IntStream.range(0, 5).mapToObj(i -> allDices[new Random().nextInt(numberOfPossibleDices)]).collect(Collectors.toList()).toArray(new Dice[5]);
    }

    private String documentFailingRandomDice(Dice... dice) {
        return "Chance should be a possible category for all dice. But it is not for: " + Arrays.toString(dice);
    }
}
