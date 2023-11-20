package yatzy.categories;

import yatzy.service.CalculScoreVisitor;

import java.util.List;

public class Single extends Category {

    private final int singleNumber ;
    protected Single(List<Integer> dices, int singleNumber) {
        super(dices);
        this.singleNumber = singleNumber;
    }

    public int getSingleNumber() {
        return singleNumber;
    }

    @Override
    public int accept(CalculScoreVisitor visitor) {
        return visitor.visit(this);
    }
}
