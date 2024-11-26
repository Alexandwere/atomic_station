package org.javaacademy.atomic_station;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.javaacademy.atomic_station.department.ReactorDepartment;
import org.javaacademy.atomic_station.department.SecurityDepartment;
import org.javaacademy.atomic_station.department.economic_departments.EconomicDepartment;
import org.javaacademy.atomic_station.exceptions.NuclearFuelIsEmptyException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
@RequiredArgsConstructor
@Slf4j
public class NuclearStation {
    private static final int DAYS_OF_YEAR = 365;

    private final ReactorDepartment reactorDepartment;
    private final SecurityDepartment securityDepartment;
    private final EconomicDepartment economicDepartment;
    private long totalAmount = 0;
    private int accidentCountAllTime;
    @Value("${country}")
    private String country;
    @Value("${currency}")
    private String currency;

    private void startYear() {
        long energyForYear = 0L;
        log.info("Атомная станция начала работу");
        for (int i = 0; i < DAYS_OF_YEAR; i++) {
            try {
                long energyOfDay = reactorDepartment.run();
                energyForYear += energyOfDay;
            } catch (NuclearFuelIsEmptyException e) {
                log.warn("Внимание! Происходят работы на атомной станции! Электричества нет!");
                securityDepartment.addAccident();
                continue;
            }
            reactorDepartment.stop();
        }
        totalAmount += energyForYear;
        log.info("Атомная станция закончила работу. За год Выработано {} киловатт/часов", energyForYear);
        log.info("Количество инцидентов за год: {}", securityDepartment.getCountAccidents());
        log.info("Доход за год составил: {} {}", economicDepartment.computeYearIncomes(energyForYear), currency);
        securityDepartment.reset();
    }

    public void start(int year) {
        log.info("Действие происходит в стране: {}", country);
        int i = 0;
        while (i < year) {
            startYear();
            i++;
        }
        log.info("Количество инцидентов за всю работу станции: {}", accidentCountAllTime);
        System.out.println(totalAmount);
    }

    public void incrementAccident(int count) {
        accidentCountAllTime += count;
    }
}
