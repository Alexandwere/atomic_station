package org.javaacademy.atomic_station;

import lombok.Getter;
import lombok.Setter;
import org.javaacademy.atomic_station.exception.NuclearFuelIsEmptyException;
import org.javaacademy.atomic_station.exception.ReactorWorkException;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
public class ReactorDepartment {
    private boolean isWorkNow;
    private int countRun;

    public long run() {
        if (isWorkNow) {
            throw new ReactorWorkException("Реактор уже работает");
        }
        countRun++;
        if (countRun % 100 == 0) {
            throw new NuclearFuelIsEmptyException();
        }
        isWorkNow = true;
        return 10_000_000L;
    }

    public void stop() {
        if (!isWorkNow) {
            throw new ReactorWorkException("Реактор уже выключен");
        }
        isWorkNow = false;
    }
}
