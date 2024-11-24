package org.javaacademy.atomic_station.department.economic_departments;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

import static java.math.BigDecimal.ZERO;
import static java.math.BigDecimal.valueOf;

@Component
@Profile("morocco")
public class MoroccoEconomicDepartment extends EconomicDepartment {
    @Value("${base-price}")
    private BigDecimal basePrice;
    @Value("${increased-price}")
    private BigDecimal increasedPrice;
    private static final long ENERGY_FOR_BASE_PRICE = 5_000_000_000L;

    @Override
    public BigDecimal computeYearIncomes(long countElectricity) {
        BigDecimal totalIncome = ZERO;

        if (countElectricity <= ENERGY_FOR_BASE_PRICE) {
            totalIncome = basePrice.multiply(valueOf(ENERGY_FOR_BASE_PRICE));
        }

        if (countElectricity > ENERGY_FOR_BASE_PRICE) {
            totalIncome = basePrice.multiply(valueOf(ENERGY_FOR_BASE_PRICE))
                    .add(increasedPrice.multiply(valueOf(countElectricity - ENERGY_FOR_BASE_PRICE)));
        }

        return totalIncome.setScale(2);
    }

}
