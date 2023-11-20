package yatzy.service;

public interface Visitable {
     int accept(CalculScoreVisitor visitor) ;
}
