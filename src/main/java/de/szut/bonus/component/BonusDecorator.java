package de.szut.bonus.component;

public abstract class BonusDecorator implements IBonus {
    protected final IBonus decoratedBonus;

    public BonusDecorator(IBonus bonus) {
        this.decoratedBonus = bonus;
    }

    @Override
    public abstract double calculateBonus();
}