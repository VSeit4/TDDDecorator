package de.szut.bonus.calculator;

import de.szut.bonus.component.BaseBonus;
import de.szut.bonus.component.IBonus;
import de.szut.bonus.decorator.*;
import de.szut.bonus.model.Employee;
import de.szut.bonus.service.BonusService;

public class BonusCalculator {
    private final BonusService bonusService;

    public BonusCalculator(BonusService bonusService) {
        this.bonusService = bonusService;
    }

    public double calculateTotalBonus(Employee e) {
        int baseBonusAmount = 1000;
        IBonus bonus = new BaseBonus(baseBonusAmount);
        bonus = new SeniorityBonus(bonus, e.getYearsAtCompany());
        bonus = new ProjectCompletionBonus(bonus, e.getCompletedProjects());
        if (e.isTeamLeader()) bonus = new TeamLeaderBonus(bonus);
        bonus = new LowAbsenceBonus(bonus, e.getAbsenceDays());
        bonus = new HighAbsencePenalty(bonus, e.getAbsenceDays());
        bonus = new PerformanceBonus(bonus, e.getPerformanceRating());
        return bonus.calculateBonus();
    }
}