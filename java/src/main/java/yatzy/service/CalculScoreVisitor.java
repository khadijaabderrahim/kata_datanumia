package yatzy.service;

import yatzy.categories.*;

public interface CalculScoreVisitor {
     int visit(Chance chance);
     int visit(FourOfKind fourOfKind);
     int visit(Pair pair);
     int visit(Single single);
     int visit(ThreeOfKind threeOfKind);
     int visit(TwoPairs twoPairs);
     int visit(Yatzy yatzy);
     int visit(SmallStraight smallStraight);
     int visit(LargeStraight largeStraight);
     int visit(FullHouse fullHouse);
}
