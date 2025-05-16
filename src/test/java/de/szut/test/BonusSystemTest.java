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
        Employee employee = new Employee("Employee", 0, 50, 0, 10, false);
        double totalBonus = bonusCalculator.calculateTotalBonus(employee);
        // 1000 base * 0.8 (performance < 60)
        assertThat(totalBonus).isEqualTo(800.0);
    }
    @Test
    void shouldApplyHighPerformanceBonus() {
        Employee employee = new Employee("Employee", 0, 90, 0, 10, false);
        double totalBonus = bonusCalculator.calculateTotalBonus(employee);
        // 1000 * 1.2 = 1200
        assertThat(totalBonus).isEqualTo(1200.0);
    }
    @Test
    void shouldApplyMiddlePerformanceBonus() {
        Employee employee = new Employee("Employee", 0, 60, 0, 10, false);
        double totalBonus = bonusCalculator.calculateTotalBonus(employee);
        // 1000
        assertThat(totalBonus).isEqualTo(1000.0);
    }
    @Test
    void shouldAddSeniorityBonus() {
        Employee employee = new Employee("Employee", 10, 60, 0, 10, false);
        double totalBonus = bonusCalculator.calculateTotalBonus(employee);
        // 1000 + 2*150 = 1300
        assertThat(totalBonus).isEqualTo(1300.0);
    }

    @Test
    void shouldAddProjectCompletionBonus() {
        Employee employee = new Employee("Employee", 0, 60, 3, 10, false);
        double totalBonus = bonusCalculator.calculateTotalBonus(employee);
        // 1000 + 3*100 = 1300
        assertThat(totalBonus).isEqualTo(1300.0);
    }
    @Test
    void shouldAddTeamLeaderBonus() {
        Employee employee = new Employee("Employee", 0, 60, 0, 10, true);
        double totalBonus = bonusCalculator.calculateTotalBonus(employee);
        // 1000 + 500 = 1500
        assertThat(totalBonus).isEqualTo(1500.0);
    }
    @Test
    void shouldAddLowAbsenceBonusFull() {
        Employee employee = new Employee("Employee", 0, 60, 0, 2, false);
        double totalBonus = bonusCalculator.calculateTotalBonus(employee);
        // 1000 + 300 = 1300
        assertThat(totalBonus).isEqualTo(1300.0);
    }

    @Test
    void shouldAddLowAbsenceBonusHalf() {
        Employee employee = new Employee("Employee", 0, 60, 0, 6, false);
        double totalBonus = bonusCalculator.calculateTotalBonus(employee);
        // 1000 + 150 = 1150
        assertThat(totalBonus).isEqualTo(1150.0);
    }

    @Test
    void shouldApplyHighAbsencePenaltyLevel1() {
        Employee employee = new Employee("Employee", 0, 60, 0, 16, false);
        double totalBonus = bonusCalculator.calculateTotalBonus(employee);
        // 1000 - 200 = 800
        assertThat(totalBonus).isEqualTo(800.0);
    }

    @Test
    void shouldApplyHighAbsencePenaltyLevel2() {
        Employee employee = new Employee("Employee", 0, 60, 0, 30, false);
        double totalBonus = bonusCalculator.calculateTotalBonus(employee);
        // 1000 - 400 = 600
        assertThat(totalBonus).isEqualTo(600.0);
    }
}