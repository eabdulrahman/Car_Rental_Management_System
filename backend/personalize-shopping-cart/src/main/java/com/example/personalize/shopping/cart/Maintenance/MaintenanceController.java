package com.example.personalize.shopping.cart.Maintenance;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.personalize.shopping.cart.vehicle.VehicleService;

@RestController
@RequestMapping("/api/v1/maintenance")
//@CrossOrigin(origins = "http://127.0.0.1:5500")
@CrossOrigin(origins = "https://localhost:5500", allowCredentials = "true")
public class MaintenanceController {

    @Autowired
    private MaintenanceService maintenanceService;
    private VehicleService vehicleService;

    public MaintenanceController(MaintenanceService maintenanceService, VehicleService vehicleService) {
        this.maintenanceService = maintenanceService;
        this.vehicleService = vehicleService;
    }

    
    @PostMapping
    public ResponseEntity<Maintenance> addMaintenance(@RequestBody Maintenance maintenance) {
        Maintenance savedMaintenance = maintenanceService.addMaintenance(maintenance);
        return ResponseEntity.ok(savedMaintenance);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Maintenance> getMaintenanceById(@PathVariable Long id) {
        Maintenance maintenance = maintenanceService.getMaintenanceById(id);
        if (maintenance != null) {
            return ResponseEntity.ok(maintenance);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/vehicle/{vehicleId}")
    public ResponseEntity<List<Maintenance>> getMaintenanceByVehicleId(@PathVariable Long vehicleId) {
        List<Maintenance> records = maintenanceService.getMaintenanceByVehicleId(vehicleId);
        return ResponseEntity.ok(records);
    }

    @GetMapping
    public ResponseEntity<List<Maintenance>> getAllMaintenanceRecords() {
        List<Maintenance> records = maintenanceService.getAllMaintenanceRecords();
        return ResponseEntity.ok(records);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Maintenance> updateMaintenanceRecord(
            @PathVariable Long id,
            @RequestBody Maintenance maintenance) {
        Maintenance updated = maintenanceService.updateMaintenanceRecord(id, maintenance);
        if (updated != null) {
            return ResponseEntity.ok(updated);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMaintenanceRecord(@PathVariable Long id) {
        maintenanceService.deleteMaintenanceRecord(id);
        return ResponseEntity.noContent().build();
    }
}
