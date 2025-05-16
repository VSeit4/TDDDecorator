package de.szut.bonus.decorator;

import de.szut.bonus.component.BonusDecorator;
import de.szut.bonus.component.IBonus;

public class PerformanceBonus extends BonusDecorator {
    private final int performanceRating;

    public PerformanceBonus(IBonus bonus, int performanceRating) {
        super(bonus);
        this.performanceRating = performanceRating;
    }

    @Override
    public double calculateBonus() {
        int highRating = 80;
        int middleRating = 60;

        double highPerformanceMultiplier = 1.2;
        double middlePerformanceMultiplier = 1;
        double lowPerformanceMultiplier = 0.8;

        double base = decoratedBonus.calculateBonus();
        if (performanceRating > highRating) return base * highPerformanceMultiplier;
        if (performanceRating >= middleRating) return base * middlePerformanceMultiplier;
        return base * lowPerformanceMultiplier;
    }
}