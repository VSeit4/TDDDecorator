package de.szut.bonus.component;

public class BaseBonus implements IBonus {
    private final double baseAmount;

    public BaseBonus(double baseAmount) {
        this.baseAmount = baseAmount;
    }

    @Override
    public double calculateBonus() {
        return baseAmount;
    }
}
