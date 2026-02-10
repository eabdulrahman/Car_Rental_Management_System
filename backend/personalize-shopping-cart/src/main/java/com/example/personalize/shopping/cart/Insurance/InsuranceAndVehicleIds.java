package com.example.personalize.shopping.cart.Insurance;

public class InsuranceAndVehicleIds {
   private Long insuranceId;
   private Long vehicleId;


   public InsuranceAndVehicleIds(Long insuranceId, Long vehicleId) {
    this.insuranceId = insuranceId;
    this.vehicleId = vehicleId;
   }

   public Long getInsuranceId() {
    return insuranceId;
   }

   public void setInsuranceId(Long insuranceId) {
    this.insuranceId = insuranceId;
   }

   public Long getVehicleId() {
    return vehicleId;
   }

   public void setVehicleId(Long vehicleId) {
    this.vehicleId = vehicleId;
   }
   
}
