package de.szut.bonus.decorator;

import de.szut.bonus.component.BonusDecorator;
import de.szut.bonus.component.IBonus;

    public class HighAbsencePenalty extends BonusDecorator {
    private final int absenceDays;

    public HighAbsencePenalty(IBonus bonus, int absenceDays) {
        super(bonus);
        this.absenceDays = absenceDays;
    }

    @Override
    public double calculateBonus() {
        int lowAbsenceDays = 15;
        double lowAbsencePenalty = 200;

        int highAbsenceDays= 25;
        double highAbsencePenalty = 400;


        if (absenceDays > highAbsenceDays) return decoratedBonus.calculateBonus() - highAbsencePenalty;
        if (absenceDays > lowAbsenceDays) return decoratedBonus.calculateBonus() - lowAbsencePenalty;
        return decoratedBonus.calculateBonus();
    }
}