package org.javaacademy.atomic_station;

import org.javaacademy.atomic_station.department.ReactorDepartment;
import org.javaacademy.atomic_station.department.SecurityDepartment;
import org.javaacademy.atomic_station.department.economic_departments.EconomicDepartment;
import org.javaacademy.atomic_station.exceptions.NuclearFuelIsEmptyException;
import org.javaacademy.atomic_station.exceptions.ReactorWorkException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("france")
@DisplayName("Реактор")
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class ReactorDepartmentTest {
    private static final long ENERGY_PER_CYCLE = 10_000_000L;
    private static final int COUNT_RUN = 99;

    @Autowired
    ReactorDepartment reactorDepartment;
    @MockBean
    EconomicDepartment economicDepartment;
    @MockBean
    SecurityDepartment securityDepartment;
    @MockBean
    NuclearStation nuclearStation;

    @Test
    @DisplayName("Запуска реактора - успешно")
    public void successRun() {
        long result = reactorDepartment.run();
        Assertions.assertEquals(ENERGY_PER_CYCLE, result);
    }

    @Test
    @DisplayName("Запуск реактора - ошибка")
    public void failureRun() {
        reactorDepartment.setWorkNow(true);
        Assertions.assertThrows(ReactorWorkException.class, reactorDepartment::run);
    }

    @Test
    @DisplayName("Стоп реактора - ошибка")
    public void failureStop() {
        Assertions.assertThrows(ReactorWorkException.class, reactorDepartment::stop);
    }

    @Test
    @DisplayName("Стоп реактора - успешно")
    public void successStop() {
        reactorDepartment.setWorkNow(true);
        reactorDepartment.stop();
        boolean result = reactorDepartment.isWorkNow();
        Assertions.assertFalse(result);
    }

    @Test
    @DisplayName("100-й запуск - ошибка")
    public void failureRun100() {
        reactorDepartment.setCountRun(COUNT_RUN);
        Assertions.assertThrows(NuclearFuelIsEmptyException.class, reactorDepartment::run);
    }

}
