package com.example.personalize.shopping.cart.Insurance;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.personalize.shopping.cart.vehicle.Vehicle;
import com.example.personalize.shopping.cart.vehicle.VehicleService;

@Service
public class InsuranceService {
    
    @Autowired
    private final InsuranceRepository insuranceRepository;
    private final InsuranceVehicleRepository insuranceVehicleRepository;
    private final VehicleService vehicleService;

    public InsuranceService(InsuranceRepository insuranceRepository, InsuranceVehicleRepository insuranceVehicleRepository, VehicleService vehicleService) {
        this.insuranceRepository = insuranceRepository;
        this.insuranceVehicleRepository = insuranceVehicleRepository;
        this.vehicleService = vehicleService;
    }

    public Insurance addInsurance(Insurance insurance){
        return insuranceRepository.save(insurance);
    }
    
    //Add vehicle to an insurance policy
    public InsuranceVehicle addVehicleToAnInsurancePolicy(Long  insuranceId, Long vehicleId){
        //return insuranceRepository.save(Insurance);
        Insurance insurance = getInsuranceById(insuranceId);
        Vehicle vehicle = vehicleService.getVehicleById(vehicleId);
        InsuranceVehicle insuranceVehicle = insuranceVehicleRepository.findByVehicleId(vehicleId);
        if(insuranceVehicle != null){
            insuranceVehicle.setInsurance(insurance);
            return insuranceVehicleRepository.save(insuranceVehicle);
        }
        insuranceVehicle = new InsuranceVehicle(insurance, vehicle);
        return insuranceVehicleRepository.save(insuranceVehicle);
    }

    
    public List<Insurance> getAllInsurancePolicies(){
        return insuranceRepository.findAll();
    }

    public Insurance getInsuranceById(Long id){
        return insuranceRepository.findById(id).orElse(null);
    }

    public Insurance getInsurancePolicyByVehicleId(Long vehicleId){
        InsuranceVehicle insuranceVehicle = insuranceVehicleRepository.findByVehicleId(vehicleId);
        if (insuranceVehicle != null){
            return insuranceVehicle.getInsurance();
        }
        return null;
    }

    
    public List<Vehicle> getVehiclesByInsuranceId(Long insuranceId){
        List<Vehicle> vehiclesInsuranceList = insuranceVehicleRepository.findVehiclesByInsuranceId(insuranceId);
        return vehiclesInsuranceList;
    }

    public void deleteInsurancePolicy(Long id){
        insuranceRepository.deleteById(id);
    }

}
