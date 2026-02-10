package com.example.personalize.shopping.cart.Insurance;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.personalize.shopping.cart.vehicle.Vehicle;

public interface InsuranceVehicleRepository extends JpaRepository<InsuranceVehicle, Long>{
    public InsuranceVehicle findByVehicleId(Long vehicleId);

    @Query("SELECT iv.vehicle FROM InsuranceVehicle iv WHERE iv.insurance.id = :insuranceId")
    List<Vehicle> findVehiclesByInsuranceId(@Param("insuranceId") Long insuranceId);
}
