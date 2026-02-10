package com.example.personalize.shopping.cart.Insurance;

import java.math.BigDecimal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface InsuranceRepository extends JpaRepository<Insurance, Long> {

    //public Insurance findInsuranceByVehicleId(Long vehicleId);

    //Sum the total insurance premium
    @Query("SELECT SUM(i.premium) FROM Insurance i")
    BigDecimal sumTotalInsurancePremium();

}