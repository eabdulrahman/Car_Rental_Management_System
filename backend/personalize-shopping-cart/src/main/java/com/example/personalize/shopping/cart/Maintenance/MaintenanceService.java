package com.example.personalize.shopping.cart.Maintenance;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MaintenanceService {


    @Autowired
    private final MaintenanceRepository maintenanceRepository;

    public MaintenanceService(MaintenanceRepository maintenanceRepository) {
        this.maintenanceRepository = maintenanceRepository;
    }

    public Maintenance addMaintenance(Maintenance maintenance){
        return maintenanceRepository.save(maintenance);
    }

    public Maintenance getMaintenanceById(Long id){
        return maintenanceRepository.findById(id).orElse(null);
    }

    public List<Maintenance> getMaintenanceByVehicleId(Long vehicleId){
        return maintenanceRepository.findMaintenanceByVehicleId(vehicleId);
    }

    public List<Maintenance> getAllMaintenanceRecords(){
        return maintenanceRepository.findAll();
    }

    public Maintenance updateMaintenanceRecord(Long id,Maintenance newMaintenanceRecord){
        Maintenance oldMaintenanceRecord = maintenanceRepository.findById(id).orElse(null);
        if(oldMaintenanceRecord != null){
            // Update the fields of the old maintenance record with the new values
            oldMaintenanceRecord.setVehicleId(newMaintenanceRecord.getVehicleId());
            oldMaintenanceRecord.setMaintenanceType(newMaintenanceRecord.getMaintenanceType());
            oldMaintenanceRecord.setMaintenanceStartDate(newMaintenanceRecord.getMaintenanceStartDate());
            oldMaintenanceRecord.setMaintenanceEndDate(newMaintenanceRecord.getMaintenanceEndDate());
            oldMaintenanceRecord.setDueDate(newMaintenanceRecord.getDueDate());
            oldMaintenanceRecord.setMileage(newMaintenanceRecord.getMileage());
            oldMaintenanceRecord.setCost(newMaintenanceRecord.getCost());
            oldMaintenanceRecord.setMaintenanceStatus(newMaintenanceRecord.getMaintenanceStatus());
            oldMaintenanceRecord.setDescription(newMaintenanceRecord.getDescription());
            oldMaintenanceRecord.setServiceProvider(newMaintenanceRecord.getServiceProvider());
            oldMaintenanceRecord.setNotes(newMaintenanceRecord.getNotes());
            oldMaintenanceRecord.setUpdatedAt(LocalDateTime.now());  // Set the update timestamp
            oldMaintenanceRecord.setIsUrgent(newMaintenanceRecord.getIsUrgent());
            oldMaintenanceRecord.setWarrantyExpirationDate(newMaintenanceRecord.getWarrantyExpirationDate());
            oldMaintenanceRecord.setSourceUser(newMaintenanceRecord.getSourceUser());

            // Save the updated maintenance record (assuming you have a save method)
            return addMaintenance(oldMaintenanceRecord);  // Save the updated record to the database
        }
        return null;
    }

    public void deleteMaintenanceRecord(Long id){
        maintenanceRepository.deleteById(id);
    }

}
