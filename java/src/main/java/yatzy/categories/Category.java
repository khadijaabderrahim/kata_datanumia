package yatzy.categories;

import yatzy.service.Visitable;

import java.util.List;

public abstract class Category implements Visitable {

    private final List<Integer> dices;

    protected Category(List<Integer> dices) {
        validateDicesInput(dices);
        this.dices = dices;
    }

    private void validateDicesInput(List<Integer> dices){
        if(dices.stream().anyMatch(d -> !List.of(1,2,3,4,5,6).contains(d)))
            throw new IllegalArgumentException("dice value must be in range [1-6]");
        if(dices.size() != 5)
            throw new IllegalArgumentException("roll must contain five dices");
    }

    public List<Integer> getDices() {
        return dices;
    }

}
