package org.javaacademy.atomic_station.economic_departments;

import org.javaacademy.atomic_station.NuclearStation;
import org.javaacademy.atomic_station.department.ReactorDepartment;
import org.javaacademy.atomic_station.department.SecurityDepartment;
import org.javaacademy.atomic_station.department.economic_departments.EconomicDepartment;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.math.RoundingMode;

@SpringBootTest
@ActiveProfiles("morocco")
@DisplayName("Экономический отдел Марокко")
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
public class MoroccoEconomicDepartmentTest {
    private static final long COUNT_ELECTRICITY_ONE = 1_000_000_000L;
    private static final long COUNT_ELECTRICITY_TWO = 6_000_000_000L;
    private static final BigDecimal EXPECTED_INCOME_ONE = BigDecimal.valueOf(5_000_000_000L);
    private static final BigDecimal EXPECTED_INCOME_TWO = BigDecimal.valueOf(31_000_000_000L);

    @Autowired
    EconomicDepartment economicDepartment;
    @MockBean
    SecurityDepartment securityDepartment;
    @MockBean
    NuclearStation nuclearStation;
    @MockBean
    ReactorDepartment reactorDepartment;

    @Test
    @DisplayName("Успешный расчёт стоимости")
    public void successCompute() {
        BigDecimal result = economicDepartment.computeYearIncomes(COUNT_ELECTRICITY_ONE);
        Assertions.assertEquals(EXPECTED_INCOME_ONE.setScale(2, RoundingMode.HALF_UP), result);
        BigDecimal result2 = economicDepartment.computeYearIncomes(COUNT_ELECTRICITY_TWO);
        Assertions.assertEquals(EXPECTED_INCOME_TWO.setScale(2, RoundingMode.HALF_UP), result2);
    }

}
