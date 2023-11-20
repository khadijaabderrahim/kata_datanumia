package yatzy.categories;

import org.junit.jupiter.api.Test;
import yatzy.service.CalculScoreVisitor;
import yatzy.service.CalculScoreVisitorImpl;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class YatzyCategoriesTest {

    private final CalculScoreVisitor calculScoreVisitor = CalculScoreVisitorImpl.getInstance();

    @Test
    public void testDiceInput() {
        assertThrows(IllegalArgumentException.class, () -> new Chance(List.of(1, 2, 3, 4, 7)), "error in dice input validation");
        assertThrows(IllegalArgumentException.class, () -> new Pair(List.of(0, 2, 3, 4, 1)), "error in dice input validation");
        assertThrows(IllegalArgumentException.class, () -> new TwoPairs(List.of(1, 2, 3, 4, 1,6)), "error in dice count validation");
        assertThrows(IllegalArgumentException.class, () -> new Yatzy(Collections.emptyList()), "error in dice count validation");
    }

    @Test
    public void testChance() {
        Chance fiveDifferentDices = new Chance(List.of(1, 2, 3, 4, 5));
        Chance allDicesAreFives = new Chance(List.of(5, 5, 5, 5, 5));

        assertEquals(15, fiveDifferentDices.accept(calculScoreVisitor), "error in chance with 5 different dices");
        assertEquals(25, allDicesAreFives.accept(calculScoreVisitor), "error in chance with 5 fives dices");
    }

    @Test
    public void testYatzy() {

        Yatzy fiveOneDices = new Yatzy(List.of(1, 1, 1, 1, 1));
        Yatzy oneDifferentDices = new Yatzy(List.of(1, 1, 1, 1, 2));
        assertEquals(50, fiveOneDices.accept(calculScoreVisitor), "error for 5 one dices");
        assertEquals(0, oneDifferentDices.accept(calculScoreVisitor), "error for one different dices");
    }

    @Test
    public void testSingle() {

        for (int i = 1; i <= 6; i++) {
            Single twoEqualDices;
            Single threeEqualDices;
            Single fourEqualDices;
            Single allEqualDices;
            Single noEqualDices;
            if (i == 1) {
                twoEqualDices = new Single(List.of(1, 1, 2, 3, 2), i);
                threeEqualDices = new Single(List.of(1, 1, 1, 5, 6), i);
                fourEqualDices = new Single(List.of(1, 1, 1, 1, 5), i);
                allEqualDices = new Single(List.of(1, 1, 1, 1, 1), i);
                noEqualDices = new Single(List.of(2, 3, 4, 5, 6), i);
            } else {
                twoEqualDices = new Single(List.of(i, i, i - 1, i - 1, i - 1), i);
                threeEqualDices = new Single(List.of(i, i, i, i - 1, i - 1), i);
                fourEqualDices = new Single(List.of(i, i, i, i, i - 1), i);
                allEqualDices = new Single(List.of(i, i, i, i, i), i);
                noEqualDices = new Single(List.of(i - 1, i - 1, i - 1, i - 1, i - 1), i);
            }
            assertEquals(2 * i, twoEqualDices.accept(calculScoreVisitor), "error for two \'" + i + "\' dices");
            assertEquals(3 * i, threeEqualDices.accept(calculScoreVisitor), "error for three \'" + i + "\' two dices");
            assertEquals(4 * i, fourEqualDices.accept(calculScoreVisitor), "error for four \'" + i + "\' dices");
            assertEquals(5 * i, allEqualDices.accept(calculScoreVisitor), "error for five \'" + i + "\' dices");
            assertEquals(0, noEqualDices.accept(calculScoreVisitor), "error for no \'" + i + "\' dices");
        }
    }

    @Test
    public void testPair() {
        Pair noPair = new Pair(List.of(1, 2, 3, 4, 5));
        Pair pairOfFour = new Pair(List.of(3, 3, 3, 4, 4));
        Pair pairOfSix = new Pair(List.of(3, 6, 6, 4, 4));
        Pair pairOfThree = new Pair(List.of(3, 3, 3, 4, 1));
        Pair twoPairsOfThree = new Pair(List.of(3, 3, 3, 3, 1));
        assertEquals(0, noPair.accept(calculScoreVisitor), "error in no pair");
        assertEquals(8, pairOfFour.accept(calculScoreVisitor), "error in pair of four");
        assertEquals(12, pairOfSix.accept(calculScoreVisitor), "error in pair of six");
        assertEquals(6, pairOfThree.accept(calculScoreVisitor), "error in pair of three");
        assertEquals(6, twoPairsOfThree.accept(calculScoreVisitor), "error in two pairs of three");
    }

    @Test
    public void testTwoPairs() {
        TwoPairs noTwoPairs = new TwoPairs(List.of(1, 2, 3, 4, 5));
        TwoPairs twoPairsOfThreeAndOne = new TwoPairs(List.of(1, 1, 2, 3, 3));
        TwoPairs onePairOfOne = new TwoPairs(List.of(1, 1, 2, 6, 3));
        TwoPairs twoPairOfOneAndTwo = new TwoPairs(List.of(1, 1, 2, 2, 3));
        TwoPairs fourThrees = new TwoPairs(List.of(3, 3, 2, 3, 3));
        assertEquals(0, noTwoPairs.accept(calculScoreVisitor), "error in no two pair");
        assertEquals(8, twoPairsOfThreeAndOne.accept(calculScoreVisitor), "error in no two pairs of three and one");
        assertEquals(0, onePairOfOne.accept(calculScoreVisitor), "error in one pair of one");
        assertEquals(6, twoPairOfOneAndTwo.accept(calculScoreVisitor), "error in two pairs of one and two");
        assertEquals(0, fourThrees.accept(calculScoreVisitor), "error in four three");
    }

    @Test
    public void testThreeOfKind() {
        ThreeOfKind threeOfThrees = new ThreeOfKind(List.of(3, 3, 3, 4, 5));
        ThreeOfKind pairOfThrees = new ThreeOfKind(List.of(3, 3, 4, 5, 6));
        ThreeOfKind fourOfFives = new ThreeOfKind(List.of(5, 5, 5, 5, 1));
        assertEquals(9, threeOfThrees.accept(calculScoreVisitor), "error in three of threes");
        assertEquals(0, pairOfThrees.accept(calculScoreVisitor), "error in pair of threes");
        assertEquals(15, fourOfFives.accept(calculScoreVisitor), "error in four of fives");
    }

    @Test
    public void testFourOfKind() {
        FourOfKind threeOfThrees = new FourOfKind(List.of(3, 3, 3, 4, 5));
        FourOfKind pairOfThrees = new FourOfKind(List.of(3, 3, 4, 5, 6));
        FourOfKind fourOfFives = new FourOfKind(List.of(5, 5, 5, 5, 1));
        FourOfKind fiveOfSixes = new FourOfKind(List.of(6, 6, 6, 6, 6));
        assertEquals(0, threeOfThrees.accept(calculScoreVisitor), "error in three of threes");
        assertEquals(0, pairOfThrees.accept(calculScoreVisitor), "error in pair of threes");
        assertEquals(20, fourOfFives.accept(calculScoreVisitor), "error in four of fives");
        assertEquals(24, fiveOfSixes.accept(calculScoreVisitor), "error in five of sixes");
    }

    @Test
    public void testSmallStraight() {
        SmallStraight smallStraightOk = new SmallStraight(List.of(1, 2, 3, 4, 5));
        SmallStraight smallStraightKo = new SmallStraight(List.of(1, 2, 6, 4, 5));
        SmallStraight largeStraight = new SmallStraight(List.of(2, 3, 4, 5, 6));
        SmallStraight twoFives = new SmallStraight(List.of(2, 3, 4, 5, 5));
        SmallStraight smallStraightOrder = new SmallStraight(List.of(5, 3, 4, 2, 1));
        assertEquals(15, smallStraightOk.accept(calculScoreVisitor), "error in small straight");
        assertEquals(0, smallStraightKo.accept(calculScoreVisitor), "error in small straight ko");
        assertEquals(0, largeStraight.accept(calculScoreVisitor), "error in large straight");
        assertEquals(0, twoFives.accept(calculScoreVisitor), "error in two fives");
        assertEquals(15, smallStraightOrder.accept(calculScoreVisitor), "error in small straight order");

    }

    @Test
    public void testLargeStraight() {
        LargeStraight largeStraightOk = new LargeStraight(List.of(2, 3, 4, 5, 6));
        LargeStraight largeStraightKo = new LargeStraight(List.of(1, 2, 6, 4, 5));
        LargeStraight smallStraight = new LargeStraight(List.of(1, 2, 3, 4, 5));
        LargeStraight twoFives = new LargeStraight(List.of(2, 3, 4, 5, 5));
        LargeStraight largeStraightOrder = new LargeStraight(List.of(5, 3, 4, 2, 6));
        assertEquals(20, largeStraightOk.accept(calculScoreVisitor), "error in large straight");
        assertEquals(0, largeStraightKo.accept(calculScoreVisitor), "error in large straight ko");
        assertEquals(0, smallStraight.accept(calculScoreVisitor), "error in small straight");
        assertEquals(0, twoFives.accept(calculScoreVisitor), "error in two fives");
        assertEquals(20, largeStraightOrder.accept(calculScoreVisitor), "error in large straight order");
    }

    @Test
    public void testFullHouse() {

        FullHouse fullHouseOk = new FullHouse(List.of(1, 1, 2, 2, 2));
        FullHouse twoPairs = new FullHouse(List.of(2, 2, 3, 3, 4));
        FullHouse fiveFours = new FullHouse(List.of(4, 4, 4, 4, 4));
        FullHouse fullHouseOrder = new FullHouse(List.of(5, 6, 6, 5, 6));

        assertEquals(8, fullHouseOk.accept(calculScoreVisitor), "error in full house ok");
        assertEquals(0, twoPairs.accept(calculScoreVisitor), "error in two pairs");
        assertEquals(0, fiveFours.accept(calculScoreVisitor), "error in five fours");
        assertEquals(28, fullHouseOrder.accept(calculScoreVisitor), "error in full house order");
    }
}
