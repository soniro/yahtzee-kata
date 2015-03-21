package de.soniro.kata.yahtzee;

import org.junit.Test;

import static de.soniro.kata.yahtzee.Category.*;
import static de.soniro.kata.yahtzee.Dice.*;
import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.*;

public class RollTest {

    @Test
    public void aRollContainingAtLeastOneOneHasThePossibleCategoryOnes() {
        assertThat(Roll.withDice(ONE, TWO, THREE, FOUR, FIVE).possibleCategories(), hasItems(ONES, TWOS, THREES, FOURS, FIVES));
    }

    @Test
    public void aRollNotContainingAOneHasDoesNotHaveThePossibleCategoryOnes() {
        assertThat(Roll.withDice(TWO, THREE, FOUR, FIVE, SIX).possibleCategories(), not(hasItem(ONES)));
    }
}
