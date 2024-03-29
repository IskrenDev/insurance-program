package com.github.iskrendev.insuranceprogram.controllers;

import com.github.iskrendev.insuranceprogram.enums.InsuranceType;
import com.github.iskrendev.insuranceprogram.models.LifeInsurance;
import com.github.iskrendev.insuranceprogram.models.PropertyInsurance;
import com.github.iskrendev.insuranceprogram.models.VehicleInsurance;
import com.github.iskrendev.insuranceprogram.repositories.LifeInsuranceRepo;
import com.github.iskrendev.insuranceprogram.repositories.PropertyInsuranceRepo;
import com.github.iskrendev.insuranceprogram.repositories.VehicleInsuranceRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class InsuranceSummaryControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private LifeInsuranceRepo lifeInsuranceRepo;
    @Autowired
    private PropertyInsuranceRepo propertyInsuranceRepo;
    @Autowired
    private VehicleInsuranceRepo vehicleInsuranceRepo;
    private static final String BASE_URI = "/api/summary";

    @Test
    @DirtiesContext
    void getInsuranceSummary_whenNoInsurancesInRepos_thenReturn0() throws Exception {
        mockMvc.perform(get(BASE_URI))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"totalAmount\":0,\"lifeInsuranceCount\":0,\"propertyInsuranceCount\":0,\"vehicleInsuranceCount\":0}"));
    }

    @Test
    @DirtiesContext
    void getInsuranceSummary_whenOneInsuranceInEachRepo_thenReturnCorrectCountsAndTotalAmount() throws Exception {
        LifeInsurance lifeInsurance = LifeInsurance.builder()
                .id("1")
                .firstName("TestFirstName")
                .familyName("TestFamilyName")
                .zipCode("12345")
                .city("Testcity")
                .address("Test str. 123")
                .telephone("012345")
                .email("testmail@example.com")
                .type(InsuranceType.LIFE)
                .duration(48)
                .paymentPerMonth(BigDecimal.valueOf(100))
                .startDate(LocalDate.of(2024, 1, 1))
                .endDate(LocalDate.of(2028, 1, 1))
                .hasHealthIssues(false)
                .healthConditionDetails("")
                .build();

        PropertyInsurance propertyInsurance = PropertyInsurance.builder()
                .id("1")
                .firstName("TestFirstName")
                .familyName("TestFamilyName")
                .zipCode("12345")
                .city("Testcity")
                .address("Test str. 123")
                .telephone("012345")
                .email("testmail@example.com")
                .type(InsuranceType.PROPERTY)
                .duration(48)
                .paymentPerMonth(BigDecimal.valueOf(100))
                .startDate(LocalDate.of(2024, 1, 1))
                .endDate(LocalDate.of(2028, 1, 1))
                .propertyType("House")
                .propertyAddress("Test str. 1")
                .constructionYear(1994)
                .build();

        VehicleInsurance vehicleInsurance = VehicleInsurance.builder()
                .id("1")
                .firstName("TestFirstName")
                .familyName("TestFamilyName")
                .zipCode("12345")
                .city("Testcity")
                .address("Test str. 123")
                .email("testmail@example.com")
                .type(InsuranceType.VEHICLE)
                .duration(12)
                .paymentPerMonth(BigDecimal.valueOf(100))
                .startDate(LocalDate.of(2024, 1, 1))
                .endDate(LocalDate.of(2025, 1, 1))
                .vehicleMake("Testmake")
                .vehicleModel("Testmodel")
                .vehicleYear(2015)
                .licensePlateNumber("AB 123 CD")
                .build();

        lifeInsuranceRepo.save(lifeInsurance);
        propertyInsuranceRepo.save(propertyInsurance);
        vehicleInsuranceRepo.save(vehicleInsurance);

        mockMvc.perform(get(BASE_URI))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"totalAmount\":10800,\"lifeInsuranceCount\":1,\"propertyInsuranceCount\":1,\"vehicleInsuranceCount\":1}"));
    }
}