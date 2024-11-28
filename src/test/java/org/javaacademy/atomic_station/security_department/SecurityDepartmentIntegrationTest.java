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
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("france")
@DisplayName("Отдел безопасности - интеграционный")
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class SecurityDepartmentIntegrationTest {
    private static final int EXPECTED_ACCIDENTS = 1;

    @Autowired
    SecurityDepartment securityDepartment;
    @Autowired
    NuclearStation nuclearStation;
    @MockBean
    EconomicDepartment economicDepartment;
    @MockBean
    ReactorDepartment reactorDepartment;

    @Test
    @DisplayName("Успешное взаимодействие с атомной станцией")
    public void successReset() {
        securityDepartment.addAccident();
        securityDepartment.reset();
        Assertions.assertEquals(EXPECTED_ACCIDENTS, nuclearStation.getAccidentCountAllTime());
    }

}
