package de.soniro.kata.yahtzee;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static de.soniro.kata.yahtzee.Dice.*;

enum Category {
    ONES(hasAtLeastOne(ONE)),
    TWOS(hasAtLeastOne(TWO)),
    THREES(hasAtLeastOne(THREE)),
    FOURS(hasAtLeastOne(FOUR)),
    FIVES(hasAtLeastOne(FIVE)),
    SIXES(hasAtLeastOne(SIX)),
    THREE_OF_A_KIND(atLeastOneDieOccursAtLeastXTimes(3)),
    FOUR_OF_A_KIND(atLeastOneDieOccursAtLeastXTimes(4)),
    FULL_HOUSE(atLeastOneDieOccursAtLeastXTimes(3).and(anotherDieOccursAtLeastYTimes(2))),
    SMALL_STRAIGHT(dicesInARow(4)),
    LARGE_STRAIGHT(dicesInARow(5)),
    YAHTZEE(allDiceAreEqual()),
    CHANCE(matchesForEveryDice());

    private Predicate<List<Dice>> rule;

    private Category(Predicate<List<Dice>> rule) {
        this.rule = rule;
    }

    public boolean isPossibleCategoryFor(Dice... dice) {
        return isPossibleCategoryFor(Arrays.asList(dice));
    }

    public boolean isPossibleCategoryFor(List<Dice> dice) {
        return rule.test(dice);
    }

    private static Predicate<List<Dice>> hasAtLeastOne(Dice die) {
        return dice -> dice.contains(die);
    }

    private static Predicate<List<Dice>> allDiceAreEqual() {
        return dice -> dice.stream().allMatch(die -> die == dice.get(0));
    }

    private static Predicate<List<Dice>> anotherDieOccursAtLeastYTimes(int times) {
        return dice -> dice.stream().filter(die -> dice.stream().filter(d -> d == die).count() < 3).anyMatch(die -> dice.stream().filter(d -> d == die).count() >= times);
    }

    private static Predicate<List<Dice>> atLeastOneDieOccursAtLeastXTimes(int times) {
        return dice -> dice.stream().anyMatch(die -> dice.stream().filter(d -> d == die).count() >= times);
    }

    private static Predicate<List<Dice>> dicesInARow(int numberOfDice) {
        Set<Set<Dice>> possibleStraights = possibleStraights(numberOfDice);
        return dice -> possibleStraights.stream().anyMatch(
                possibleStraight -> possibleStraight.stream().allMatch(straightDie -> dice.contains(straightDie)));
    }

    private static Set<Set<Dice>> possibleStraights(int numberOfDice) {
        return IntStream.range(0, Dice.values().length - numberOfDice).mapToObj(startDice ->
                IntStream.range(startDice, startDice+numberOfDice)
                        .mapToObj(die -> Dice.values()[die]).collect(Collectors.toSet())
        ).collect(Collectors.toSet());
    }

    private static Predicate<List<Dice>> matchesForEveryDice() {
        return dice -> true;
    }
}
