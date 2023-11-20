package yatzy.service;

import yatzy.categories.*;

public class CalculScoreVisitorImpl implements CalculScoreVisitor {
    private static CalculScoreVisitorImpl instance;

    private CalculScoreVisitorImpl() {
    }

    public static CalculScoreVisitor getInstance() {
        if (instance == null)
            instance = new CalculScoreVisitorImpl();
        return instance;
    }

    @Override
    public int visit(Chance chance) {
        return 0;
    }

    @Override
    public int visit(FourOfKind fourOfKind) {
        return 0;
    }

    @Override
    public int visit(Pair pair) {
        return 0;
    }

    @Override
    public int visit(Single single) {
        return 0;
    }

    @Override
    public int visit(ThreeOfKind threeOfKind) {
        return 0;
    }

    @Override
    public int visit(TwoPairs twoPairs) {
        return 0;
    }

    @Override
    public int visit(Yatzy yatzy) {
        return 0;
    }

    @Override
    public int visit(SmallStraight smallStraight) {
        return 0;
    }

    @Override
    public int visit(LargeStraight largeStraight) {
        return 0;
    }

    @Override
    public int visit(FullHouse fullHouse) {
        return 0;
    }
}
