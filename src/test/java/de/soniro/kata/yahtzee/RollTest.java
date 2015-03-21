package de.soniro.kata.yahtzee;

import org.junit.Test;

import java.util.Set;

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


    @Test
    public void aRollWhereAllDiceAreEqualHasAllPossibleCategories() {
        Set<Category> categories = Roll.withDice(FIVE, FIVE, FIVE, FIVE, FIVE).possibleCategories();
        assertThat(categories.size(), is(5));
        assertThat(categories, hasItems(FIVES, THREE_OF_A_KIND, FOUR_OF_A_KIND, YAHTZEE, CHANCE));
    }
}
