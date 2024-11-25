package org.javaacademy.atomic_station;

import org.javaacademy.atomic_station.department.economic_departments.EconomicDepartment;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.math.RoundingMode;

@SpringBootTest
@ActiveProfiles("france")
@DisplayName("Экономический отдел")
public class FranceEconomicDepartmentTest {

    private static final long COUNT_ELECTRICITY_ONE = 3_620_000_000L;
    private static final long COUNT_ELECTRICITY_TWO = 3_000_000_000L;
    private static final BigDecimal EXPECTED_INCOME_ONE = BigDecimal.valueOf(1785842690);
    private static final BigDecimal EXPECTED_INCOME_TWO = BigDecimal.valueOf(1485050000);

    @Autowired
    EconomicDepartment economicDepartment;

    @Test
    @DisplayName("Успешный расчет стоимости")
    public void successCompute() {
        BigDecimal result = economicDepartment.computeYearIncomes(COUNT_ELECTRICITY_ONE);
        Assertions.assertEquals(EXPECTED_INCOME_ONE.setScale(2, RoundingMode.HALF_UP), result);
        BigDecimal result2 = economicDepartment.computeYearIncomes(COUNT_ELECTRICITY_TWO);
        Assertions.assertEquals(EXPECTED_INCOME_TWO.setScale(2, RoundingMode.HALF_UP), result2);
    }

}
