package com.example.personalize.shopping.cart.Insurance;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.personalize.shopping.cart.vehicle.Vehicle;

@RestController
@RequestMapping("/api/v1/insurance")
//@CrossOrigin(origins = "http://127.0.0.1:5500")
@CrossOrigin(origins = "https://localhost:5500", allowCredentials = "true")
public class InsuranceController {
    
    @Autowired
    private InsuranceService insuranceService;

    public InsuranceController(InsuranceService insuranceService) {
        this.insuranceService = insuranceService;
    }

    @PostMapping()    
    public ResponseEntity<Insurance> addInsurancePolicy(@RequestBody Insurance insurance){
        Insurance newInsurancePolicy = insuranceService.addInsurance(insurance);
        return ResponseEntity.ok(newInsurancePolicy);
    }

    @GetMapping()
    public ResponseEntity<List<Insurance>> getAllInsurancePolicies() {
        List<Insurance> allInsurancePolicies = insuranceService.getAllInsurancePolicies();
        return ResponseEntity.ok(allInsurancePolicies);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Insurance> getInsuranceById(@PathVariable Long id) {
        Insurance insurance = insuranceService.getInsuranceById(id);
        return ResponseEntity.ok(insurance);
    }

    @PostMapping("/vehicle")
    public ResponseEntity<InsuranceVehicle> addVehicleToInsurancePolicy(@RequestBody InsuranceAndVehicleIds insuranceAndVehicleIds) {
        InsuranceVehicle insuranceVehicle = insuranceService.addVehicleToAnInsurancePolicy(insuranceAndVehicleIds.getInsuranceId(), insuranceAndVehicleIds.getVehicleId());
        return ResponseEntity.ok(insuranceVehicle);
    }

    @GetMapping("/vehicle/{id}")
    public ResponseEntity<Insurance> getInsurancePolicyByVehicleId(@PathVariable Long id) {
        Insurance insurance = insuranceService.getInsurancePolicyByVehicleId(id);
        return ResponseEntity.ok(insurance);
    }


    @GetMapping("/vehiclesInsurance/{insuranceId}")
    public ResponseEntity<List<Vehicle>> getVehiclesByInsuranceId(@PathVariable Long insuranceId) {
        List<Vehicle> insurance = insuranceService.getVehiclesByInsuranceId(insuranceId);
        return ResponseEntity.ok(insurance);
    }

}
