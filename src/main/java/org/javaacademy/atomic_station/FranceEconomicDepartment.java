package org.javaacademy.atomic_station;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@Profile("france")
public class FranceEconomicDepartment extends EconomicDepartment {
    private final BigDecimal priceOneKWT = BigDecimal.valueOf(5);

    @Override
    public BigDecimal computeYearIncomes(long countElectricity) {
        return null;
    }
}
