package de.szut.bonus.service;

public class BonusService {
    private final double minBonus;
    private final double maxBonus;

    public BonusService(double minBonus, double maxBonus) {
        this.minBonus = minBonus;
        this.maxBonus = maxBonus;
    }

    public double applyRestrictions(double bonus) {
        return Math.max(minBonus, Math.min(maxBonus, bonus));
    }
}