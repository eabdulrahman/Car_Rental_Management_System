package com.example.personalize.shopping.cart.vehicle;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VehicleService {
    
    @Autowired
    VehicleRepository vehicleRepository;

    public VehicleService(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    	public Vehicle getVehicleById(Long id) {
		return vehicleRepository.findById(id).orElse(null);
	}

	public List<Vehicle> getAllVehicles() {
		return vehicleRepository.findAll();
	}

	public Vehicle addVehicle(Vehicle vehicle) {
		return vehicleRepository.save(vehicle);
	}

	public Vehicle updateVehicle(Long id, Vehicle vehicle) {
		Vehicle existingVehicle = vehicleRepository.findById(id).orElse(null);
		if (existingVehicle != null) {
			existingVehicle.setMake(vehicle.getMake());
            existingVehicle.setModel(vehicle.getModel());
            existingVehicle.setYear(vehicle.getYear());
            existingVehicle.setLicensePlate(vehicle.getLicensePlate());
            existingVehicle.setVin(vehicle.getVin());
            existingVehicle.setTransmission(vehicle.getTransmission());
            existingVehicle.setFuelType(vehicle.getFuelType());
            existingVehicle.setSeats(vehicle.getSeats());
            existingVehicle.setDoors(vehicle.getDoors());
            existingVehicle.setCategory(vehicle.getCategory());
            existingVehicle.setRentalPricePerDay(vehicle.getRentalPricePerDay());
            existingVehicle.setColor(vehicle.getColor());
            existingVehicle.setMileage(vehicle.getMileage());
            existingVehicle.setImageUrl(vehicle.getImageUrl());
            existingVehicle.setLastServiceDate(vehicle.getLastServiceDate());
            //existingVehicle.setInsuranceExpiryDate(vehicle.getInsuranceExpiryDate());
            existingVehicle.setDescription(vehicle.getDescription());
            existingVehicle.setNextServiceDate(vehicle.getNextServiceDate());
			return vehicleRepository.save(existingVehicle);
		}
		return null;
	}

	public void deleteVehicle(Long id) {
		vehicleRepository.deleteById(id);
	}
    
}
