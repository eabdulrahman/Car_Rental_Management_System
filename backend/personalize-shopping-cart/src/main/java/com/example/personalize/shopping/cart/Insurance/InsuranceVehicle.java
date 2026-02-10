package com.example.personalize.shopping.cart.Insurance;

import com.example.personalize.shopping.cart.vehicle.Vehicle;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class InsuranceVehicle {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        
        @ManyToOne
        @JoinColumn(name = "insurance_id")
        private Insurance insurance;
        
        @ManyToOne
        @JoinColumn(name = "vehicle_id", unique = true)  // Adding unique constraint here
        private Vehicle vehicle;

    public InsuranceVehicle(){}

    public InsuranceVehicle(Insurance insurance, Vehicle vehicle) {
            this.insurance = insurance;
            this.vehicle = vehicle;
        }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Insurance getInsurance() {
        return insurance;
    }

    public void setInsurance(Insurance insurance) {
        this.insurance = insurance;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

}
