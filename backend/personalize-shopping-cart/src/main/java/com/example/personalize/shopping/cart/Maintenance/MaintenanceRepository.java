package com.example.personalize.shopping.cart.Maintenance;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MaintenanceRepository extends JpaRepository<Maintenance, Long> {
    
    public List<Maintenance> findMaintenanceByVehicleId(Long vehicleId);

    @Query("SELECT SUM(m.cost) FROM Maintenance m")
    BigDecimal sumTotalMaintenanceCost();
}
