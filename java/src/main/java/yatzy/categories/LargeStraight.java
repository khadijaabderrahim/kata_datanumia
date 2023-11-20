package yatzy.categories;

import yatzy.service.CalculScoreVisitor;

import java.util.List;

public class LargeStraight extends Category {
    protected LargeStraight(List<Integer> dices) {
        super(dices);
    }

    @Override
    public int accept(CalculScoreVisitor visitor) {
        return visitor.visit(this);
    }
}
