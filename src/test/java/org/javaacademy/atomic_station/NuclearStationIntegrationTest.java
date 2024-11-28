package org.javaacademy.atomic_station;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("france")
@DisplayName("Атомная станция")
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class NuclearStationIntegrationTest {
    private static final int WORK_YEAR = 1;
    private static final int EXCEPTED_ACCIDENTS = 3;
    private static final int COUNT_ACCIDENTS = 3;

    @Autowired
    NuclearStation nuclearStation;

    @Test
    @DisplayName("Успешный старт")
    public void successStart() {
        nuclearStation.start(WORK_YEAR);
        int resultAccidents = nuclearStation.getAccidentCountAllTime();
        Assertions.assertEquals(EXCEPTED_ACCIDENTS, resultAccidents);
    }

    @Test
    @DisplayName("Успешное добавление инцидентов")
    public void successIncrementAccident() {
        nuclearStation.incrementAccident(COUNT_ACCIDENTS);
        Assertions.assertEquals(nuclearStation.getAccidentCountAllTime(), EXCEPTED_ACCIDENTS);
    }

}
