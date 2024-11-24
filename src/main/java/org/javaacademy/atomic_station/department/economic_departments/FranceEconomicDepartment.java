package org.javaacademy.atomic_station.department.economic_departments;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

import static java.math.BigDecimal.ZERO;
import static java.math.BigDecimal.valueOf;

@Component
@Profile("france")
public class FranceEconomicDepartment extends EconomicDepartment {
    @Value("${base-price}")
    private BigDecimal basePrice;
    private static final long PART_ENERGY_TO_SALE = 1_000_000_000L;
    @Value("${discount-rate}")
    private double discountRate;

    @Override
    public BigDecimal computeYearIncomes(long countElectricity) {
        BigDecimal totalIncome = ZERO;
        long billionBlocks = countElectricity / PART_ENERGY_TO_SALE;

        for (int i = 0; i < billionBlocks; i++) {
            BigDecimal effectivePrice = basePrice.multiply(valueOf(Math.pow(1 - discountRate, i)));
            totalIncome = totalIncome.add(effectivePrice.multiply(valueOf(PART_ENERGY_TO_SALE)));
        }

        long remainingEnergy = countElectricity % PART_ENERGY_TO_SALE;
        if (remainingEnergy > 0) {
            BigDecimal effectivePrice = basePrice.multiply(valueOf(Math.pow(1 - discountRate, billionBlocks)));
            totalIncome = totalIncome.add(effectivePrice.multiply(valueOf(remainingEnergy)));
        }

        return totalIncome.setScale(2);
    }

}
