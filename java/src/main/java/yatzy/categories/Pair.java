package yatzy.categories;

import yatzy.service.CalculScoreVisitor;

import java.util.List;

public class Pair extends Category {
    protected Pair(List<Integer> dices) {
        super(dices);
    }

    @Override
    public int accept(CalculScoreVisitor visitor) {
        return visitor.visit(this);
    }
}
