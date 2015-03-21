package de.soniro.kata.yahtzee;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Roll {

    List<Dice> dice;

    private Roll(Dice... dice) {
        this.dice = Arrays.asList(dice);
    }

    public static Roll withDice(Dice first, Dice second, Dice third, Dice fourth, Dice fifth) {
        return new Roll(first, second, third, fourth, fifth);
    }

    public Set<Category> possibleCategories() {
        return Stream.of(Category.values()).filter(category -> category.isPossibleCategoryFor(dice)).collect(Collectors.toSet());
    }
}
