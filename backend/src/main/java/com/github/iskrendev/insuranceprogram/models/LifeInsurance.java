package com.github.iskrendev.insuranceprogram.models;

import com.github.iskrendev.insuranceprogram.enums.InsuranceType;
import lombok.Builder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDate;

@Builder
@Document(collection = "life_insurance")
public record LifeInsurance(
        @Id
        String id,
        String firstName,
        String familyName,
        String zipCode,
        String city,
        String address,
        String telephone,
        String email,
        InsuranceType type,
        Integer duration,
        BigDecimal paymentPerMonth,
        LocalDate startDate,
        LocalDate endDate,
        Boolean hasHealthIssues,
        String healthConditionDetails

) {
    public BigDecimal calculateInsuranceAmount() {
        return BigDecimal.valueOf(this.duration).multiply(this.paymentPerMonth);
    }
}