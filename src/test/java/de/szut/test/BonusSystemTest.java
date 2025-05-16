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
        bonusService = new BonusService(500.0, 10000.0);
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
    @Test
    void shouldNotApplyPenaltyAt15Days() {
        Employee employee = new Employee("Employee", 0, 60, 0, 15, false);
        double totalBonus = bonusCalculator.calculateTotalBonus(employee);
        // 1000  -> no penalty because 15 is not > 15
        assertThat(totalBonus).isEqualTo(1000.0);
    }

    @Test
    void shouldApplyPenaltyAt16Days() {
        Employee employee = new Employee("Employee", 0, 60, 0, 16, false);
        double totalBonus = bonusCalculator.calculateTotalBonus(employee);
        // 1000 - 200 = 800
        assertThat(totalBonus).isEqualTo(800.0);
    }
    @Test
    void shouldApplyPerformancePenaltyAfterDecorators() {
        Employee employee = new Employee("Employee", 0, 50, 2, 2, false);
        // 1000 + 200 (projects) + 300 (low absence) = 1500
        // then * 0.8 (perf < 60) = 1200
        double totalBonus = bonusCalculator.calculateTotalBonus(employee);
        assertThat(totalBonus).isEqualTo(1200.0);
    }
    @Test
    void shouldApplyMaximumBonusLimit() {
        Employee employee = new Employee("Employee", 50, 100, 100, 0, true);
        double totalBonus = bonusCalculator.calculateTotalBonus(employee);
        // base = 1000
        // + seniority: 50 / 5 * 150 = 10 * 150 = 1500 -> 2500
        // + project: 100 * 100 = 10000 -> 12500
        // + teamLeader: +500 -> 13000
        // + low absence: + 300 -> 13300
        // * performance: 100 -> +20% -> 13300 * 1.2 = 15960
        assertThat(totalBonus).isEqualTo(10000.0);
    }

    @Test
    void shouldApplyMinimumBonusLimit() {
        Employee employee = new Employee("Employee", 0, 0, 0, 50, false);
        double totalBonus = bonusCalculator.calculateTotalBonus(employee);
        // base = 1000
        // + seniority: 0 -> nichts
        // + project: 0 -> nichts
        // + teamLeader: false -> nichts
        // + low absence: 50 -> 1000 - 400 -> 600
        // * performance: 0 -> -20% -> 600 * 0.8 = 480
        assertThat(totalBonus).isEqualTo(500.0);
    }
}