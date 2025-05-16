package de.szut.bonus.decorator;

import de.szut.bonus.component.BonusDecorator;
import de.szut.bonus.component.IBonus;

public class ProjectCompletionBonus extends BonusDecorator {
    private final int completedProjects;

    public ProjectCompletionBonus(IBonus bonus, int completedProjects) {
        super(bonus);
        this.completedProjects = completedProjects;
    }

    @Override
    public double calculateBonus() {
        double completedProjectsMultiplier = 100;

        return decoratedBonus.calculateBonus() + completedProjects * completedProjectsMultiplier;
    }
}
