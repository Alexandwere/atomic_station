package org.javaacademy.atomic_station;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.javaacademy.atomic_station.exception.NuclearFuelIsEmptyException;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Getter
@Setter
@Slf4j
@Component
public class NuclearStation {
    public static final int DAYS_OF_YEAR = 365;

    @NonNull
    private ReactorDepartment reactorDepartment;
    @NonNull
    private SecurityDepartment securityDepartment;
    private long totalAmount;
    private int accidentCountAllTime;


    public void startYear() {
        long energyForYear = 0L;
        log.info("Атомная станция начала работу");
        for (int i = 0; i < DAYS_OF_YEAR; i++) {
            try {
                long energyOfDay = reactorDepartment.run();
                energyForYear += energyOfDay;
            } catch (NuclearFuelIsEmptyException e) {
                log.info("Внимание! Происходят работы на атомной станции! Электричества нет!");
                securityDepartment.addAccident();
                continue;
            }
            reactorDepartment.stop();
        }
        log.info("Атомная станция закончила работу. За год Выработано {} киловатт/часов", energyForYear);
        log.info("Количество инцидентов за год: {}", securityDepartment.getCountAccidents());
        securityDepartment.reset();
    }

    public void start(int year) {
        int i = 0;
        while (i < year) {
            startYear();
            i++;
        }
        log.info("Количество инцидентов за всю работу станции: {}", accidentCountAllTime);
    }

    public void incrementAccident(int count) {
        accidentCountAllTime += count;
    }
}
