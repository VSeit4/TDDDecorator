package de.szut.test;

import de.szut.bonus.calculator.BonusCalculator;
import de.szut.bonus.model.Employee;
import de.szut.bonus.service.BonusService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


class BonusSystemTest {

    private BonusService bonusService;
    private BonusCalculator bonusCalculator;

    @BeforeEach
    void setUp() {
        bonusService = new BonusService();
        bonusCalculator = new BonusCalculator(bonusService);
    }

    @Test
    void shouldReturnBaseBonusWithPerformancePenalty() {
        Employee employee = new Employee(50);
        double totalBonus = bonusCalculator.calculateTotalBonus(employee);
        assertThat(totalBonus).isEqualTo(800.0);
    }
    @Test
    void shouldApplyHighPerformanceBonus() {
        Employee employee = new Employee(90);
        double totalBonus = bonusCalculator.calculateTotalBonus(employee);
        assertThat(totalBonus).isEqualTo(1200.0);
    }
    @Test
    void shouldApplyMiddlePerformanceBonus() {
        Employee employee = new Employee(60);
        double totalBonus = bonusCalculator.calculateTotalBonus(employee);
        assertThat(totalBonus).isEqualTo(1000.0);
    }


}