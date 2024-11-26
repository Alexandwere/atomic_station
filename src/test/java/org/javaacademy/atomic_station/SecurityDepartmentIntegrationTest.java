package org.javaacademy.atomic_station;

import org.javaacademy.atomic_station.department.SecurityDepartment;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("france")
@DisplayName("Отдел безопасности - интеграционный")
public class SecurityDepartmentIntegrationTest {
    private static final int EXPECTED_ACCIDENTS = 1;

    @Autowired
    SecurityDepartment securityDepartment;
    @Autowired
    NuclearStation nuclearStation;

    @Test
    @DisplayName("Успешное взаимодействие с атомной станцией")
    public void successReset() {
        securityDepartment.addAccident();
        securityDepartment.reset();
        Assertions.assertEquals(EXPECTED_ACCIDENTS, nuclearStation.getAccidentCountAllTime());
    }

}
