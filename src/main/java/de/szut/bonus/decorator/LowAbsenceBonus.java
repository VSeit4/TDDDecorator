package de.szut.bonus.decorator;

import de.szut.bonus.component.BonusDecorator;
import de.szut.bonus.component.IBonus;

public class LowAbsenceBonus extends BonusDecorator {
    private final int absenceDays;

    public LowAbsenceBonus(IBonus bonus, int absenceDays) {
        super(bonus);
        this.absenceDays = absenceDays;
    }

    @Override
    public double calculateBonus() {
        int lowAbsenceDays = 5;
        double lowAbsenceBonus = 300;

        int highAbsenceDays= 10;
        double highAbsenceBonus = 150;

        if (absenceDays < lowAbsenceDays) return decoratedBonus.calculateBonus() + lowAbsenceBonus;
        if (absenceDays < highAbsenceDays) return decoratedBonus.calculateBonus() + highAbsenceBonus;
        return decoratedBonus.calculateBonus();
    }
}