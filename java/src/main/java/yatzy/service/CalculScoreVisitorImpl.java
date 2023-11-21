package yatzy.service;


import yatzy.categories.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
        return chance.getDices().stream().mapToInt(Integer::intValue).sum();
    }

    @Override
    public int visit(FourOfKind fourOfKind) {
        return fourOfKind.getDices().stream().collect(Collectors.groupingBy(d -> d))
            .entrySet()
            .stream()
            .map(d -> Map.entry(d.getKey(), d.getValue().size()))
            .filter(d -> d.getValue() >= 4)
            .map(Map.Entry::getKey)
            .map(d -> d * 4)
            .findAny()
            .orElse(0);
    }

    @Override
    public int visit(Pair pair) {
        return pair.getDices().stream().collect(Collectors.groupingBy(d -> d))
            .entrySet()
            .stream()
            .map(d -> Map.entry(d.getKey(), d.getValue().size()))
            .filter(d -> d.getValue() >= 2)
            .mapToInt(Map.Entry::getKey)
            .map(d -> d * 2)
            .max()
            .orElse(0);
    }

    @Override
    public int visit(Single single) {
        return single.getDices().stream().filter(d -> d.equals(single.getSingleNumber())).mapToInt(Integer::intValue).sum();

    }

    @Override
    public int visit(ThreeOfKind threeOfKind) {
        return threeOfKind.getDices().stream().collect(Collectors.groupingBy(d -> d))
            .entrySet()
            .stream()
            .map(d -> Map.entry(d.getKey(), d.getValue().size()))
            .filter(d -> d.getValue() >= 3)
            .map(Map.Entry::getKey)
            .map(d -> d * 3)
            .findAny()
            .orElse(0);
    }

    @Override
    public int visit(TwoPairs twoPairs) {
        List<Integer> distinctPairs = twoPairs.getDices().stream().collect(Collectors.groupingBy(d -> d))
            .entrySet()
            .stream()
            .map(d -> Map.entry(d.getKey(), d.getValue().size()))
            .filter(d -> d.getValue() >= 2)
            .map(Map.Entry::getKey)
            .toList();
        if (distinctPairs.size() != 2)
            return 0;
        return distinctPairs.stream().mapToInt(Integer::intValue).sum() * 2;
    }

    @Override
    public int visit(Yatzy yatzy) {
        if (yatzy.getDices().stream().distinct().count() == 1)
            return 50;
        else
            return 0;
    }

    @Override
    public int visit(SmallStraight smallStraight) {
        List<Integer> smallStraightOk = List.of(1, 2, 3, 4, 5);
        return smallStraightOk.stream()
            .filter(d -> !smallStraight.getDices().contains(d))
            .findAny()
            .map(d -> 0)
            .orElse(15);
    }

    @Override
    public int visit(LargeStraight largeStraight) {
        List<Integer> largeStraightOk = List.of(2, 3, 4, 5, 6);
        return largeStraightOk.stream()
            .filter(d -> !largeStraight.getDices().contains(d))
            .findAny()
            .map(d -> 0)
            .orElse(20);
    }

    @Override
    public int visit(FullHouse fullHouse) {
        Map<Integer, List<Integer>> groupedDices = fullHouse.getDices().stream()
            .collect(Collectors.groupingBy(d -> d));
        if (groupedDices.size() != 2)
            return 0;
        return groupedDices
            .entrySet()
            .stream()
            .mapToInt(e -> e.getKey() * e.getValue().size())
            .sum();
    }
}
