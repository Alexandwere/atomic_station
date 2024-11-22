package org.javaacademy.atomic_station;

import lombok.Getter;
import lombok.Setter;
import org.javaacademy.atomic_station.exceptions.NuclearFuelIsEmptyException;
import org.javaacademy.atomic_station.exceptions.ReactorWorkException;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
public class ReactorDepartment {
    public static final int NUMBER_FAILURE_RUN = 100;
    public static final long ENERGY_PER_CYCLE = 10_000_000L;

    private boolean isWorkNow;
    private int countRun;

    public long run() {
        if (isWorkNow) {
            throw new ReactorWorkException("Реактор уже работает");
        }
        countRun++;
        if (countRun % NUMBER_FAILURE_RUN == 0) {
            throw new NuclearFuelIsEmptyException();
        }
        isWorkNow = true;
        return ENERGY_PER_CYCLE;
    }

    public void stop() {
        if (!isWorkNow) {
            throw new ReactorWorkException("Реактор уже выключен");
        }
        isWorkNow = false;
    }
}
