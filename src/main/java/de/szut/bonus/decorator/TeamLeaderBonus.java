package de.szut.bonus.decorator;

import de.szut.bonus.component.BonusDecorator;
import de.szut.bonus.component.IBonus;

public class TeamLeaderBonus extends BonusDecorator {

    public TeamLeaderBonus(IBonus bonus) {
        super(bonus);
    }

    @Override
    public double calculateBonus() {
        int teamLeadBonus = 500;
        return decoratedBonus.calculateBonus() + teamLeadBonus;
    }
}