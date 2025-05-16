package de.szut.bonus.decorator;

import de.szut.bonus.component.BonusDecorator;
import de.szut.bonus.component.IBonus;

public class SeniorityBonus extends BonusDecorator {
    private final int yearsAtCompany;

    public SeniorityBonus(IBonus bonus, int yearsAtCompany) {
        super(bonus);
        this.yearsAtCompany = yearsAtCompany;
    }

    @Override
    public double calculateBonus() {
        int yearPeriode = 5;
        double periodeMultiplier = 150;
        int intervals = yearsAtCompany / yearPeriode;
        return decoratedBonus.calculateBonus() + intervals * periodeMultiplier;
    }
}