package org.javaacademy.atomic_station.security_department;

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
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("france")
@DisplayName("Отдел безопасности")
public class SecurityDepartmentTest {
    private static final int EXPECTED_ACCIDENTS = 1;
    private static final int EXPECTED_ACCIDENTS_AFTER_RESET = 0;

    @Autowired
    SecurityDepartment securityDepartment;
    @MockBean
    NuclearStation nuclearStation;
    @MockBean
    EconomicDepartment economicDepartment;
    @MockBean
    ReactorDepartment reactorDepartment;

    @Test
    @DisplayName("Успешное заполнение счётчика инцидентов и получение их количества")
    public void successAddAccident() {
        securityDepartment.addAccident();
        Assertions.assertEquals(EXPECTED_ACCIDENTS, securityDepartment.getCountAccidents());
    }

    @Test
    @DisplayName("Успешный сброс счётчика инцидентов")
    public void successReset() {
        securityDepartment.addAccident();
        securityDepartment.reset();
        Assertions.assertEquals(EXPECTED_ACCIDENTS_AFTER_RESET, securityDepartment.getCountAccidents());
    }

}
