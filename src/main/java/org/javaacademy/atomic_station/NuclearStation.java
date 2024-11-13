package org.javaacademy.atomic_station;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.javaacademy.atomic_station.exception.NuclearFuelIsEmptyException;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Slf4j
@Component
public class NuclearStation {
    private ReactorDepartment reactorDepartment = new ReactorDepartment();
    private long totalAmount = 0L;

    public void startYear() {
        long energyForYear = 0L;
        log.info("Атомная станция начала работу");
        for (int i = 0; i < 365; i++) {
            try {
                long energyOfDay = reactorDepartment.run();
                energyForYear += energyOfDay;
            } catch (NuclearFuelIsEmptyException e) {
                log.info("Внимание! Происходят работы на атомной станции! Электричества нет!");
                continue;
            }
            reactorDepartment.stop();
        }
        log.info("Атомная станция закончила работу. За год Выработано {} киловатт/часов", energyForYear);
    }

    public void start(int year) {
        int i = 0;
        while (i < year) {
            startYear();
            i++;
        }
    }
}
