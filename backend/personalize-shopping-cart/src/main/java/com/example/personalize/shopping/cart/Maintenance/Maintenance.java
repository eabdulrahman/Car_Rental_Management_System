package com.example.personalize.shopping.cart.Maintenance;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Maintenance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //@ManyToOne
    //@JoinColumn(name = "vehicle_id", nullable = false)
    //private Vehicle vehicle;
    private Long vehicleId;
    private String sourceUser;

    private String maintenanceType;
    private LocalDate maintenanceStartDate;
    private LocalDate maintenanceEndDate;
    private LocalDate dueDate;
    private Double mileage;
    private BigDecimal cost;
    private String maintenanceStatus;
    private String description;
    private String serviceProvider;
    private String notes;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Boolean isUrgent;
    private LocalDate warrantyExpirationDate;
    
    
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public Long getVehicleId() {
        return vehicleId;
    }
    public void setVehicleId(Long vehicleId) {
        this.vehicleId = vehicleId;
    }

    public String getMaintenanceType() {
        return maintenanceType;
    }
    public void setMaintenanceType(String maintenanceType) {
        this.maintenanceType = maintenanceType;
    }
    public LocalDate getMaintenanceStartDate() {
        return maintenanceStartDate;
    }
    public void setMaintenanceStartDate(LocalDate maintenanceStartDate) {
        this.maintenanceStartDate = maintenanceStartDate;
    }
    public LocalDate getMaintenanceEndDate() {
        return maintenanceEndDate;
    }
    public void setMaintenanceEndDate(LocalDate maintenanceEndDate) {
        this.maintenanceEndDate = maintenanceEndDate;
    }
    public LocalDate getDueDate() {
        return dueDate;
    }
    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }
    public Double getMileage() {
        return mileage;
    }
    public void setMileage(Double mileage) {
        this.mileage = mileage;
    }
    public BigDecimal getCost() {
        return cost;
    }
    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }
    public String getMaintenanceStatus() {
        return maintenanceStatus;
    }
    public void setMaintenanceStatus(String maintenanceStatus) {
        this.maintenanceStatus = maintenanceStatus;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getServiceProvider() {
        return serviceProvider;
    }
    public void setServiceProvider(String serviceProvider) {
        this.serviceProvider = serviceProvider;
    }
    public String getNotes() {
        return notes;
    }
    public void setNotes(String notes) {
        this.notes = notes;
    }
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
    public Boolean getIsUrgent() {
        return isUrgent;
    }
    public void setIsUrgent(Boolean isUrgent) {
        this.isUrgent = isUrgent;
    }
    public LocalDate getWarrantyExpirationDate() {
        return warrantyExpirationDate;
    }
    public void setWarrantyExpirationDate(LocalDate warrantyExpirationDate) {
        this.warrantyExpirationDate = warrantyExpirationDate;
    }
    public String getSourceUser() {
        return sourceUser;
    }
    public void setSourceUser(String sourceUser) {
        this.sourceUser = sourceUser;
    }

}
