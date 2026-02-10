package com.example.personalize.shopping.cart.Insurance;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Insurance {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String provider; // Insurance Provider
    private String insuranceType; //Comprehensive, Third-party liability, Collision damage waiver (CDW), Theft protection, Personal accident insurance (PAI)
    private String policyNumber;
    private LocalDateTime creationDateTime;
    private LocalDate purchaseDate;
    private LocalDate startDate;
    private LocalDate endDate;
    private BigDecimal coverageAmount;
    private BigDecimal premium;
    private BigDecimal deductible;  //The deductible amount the customer must pay before the insurance coverage kicks in.
    private String coverageArea;
    //private List<Long> vehiclesCovered; //List<String> Details on which vehicles are covered under the insurance policy (e.g., car model or type).
    private BigDecimal customerLiability;   //The amount the customer will be liable for in case of an accident or damages.
    private String policyStatus; // Active, Expired, Suspended
    private String additionalNotes;
    private Boolean isValid;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public String getInsuranceType() {
        return insuranceType;
    }

    public void setInsuranceType(String insuranceType) {
        this.insuranceType = insuranceType;
    }

    public String getPolicyNumber() {
        return policyNumber;
    }

    public void setPolicyNumber(String policyNumber) {
        this.policyNumber = policyNumber;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public BigDecimal getCoverageAmount() {
        return coverageAmount;
    }

    public void setCoverageAmount(BigDecimal coverageAmount) {
        this.coverageAmount = coverageAmount;
    }

    public BigDecimal getPremium() {
        return premium;
    }

    public void setPremium(BigDecimal premium) {
        this.premium = premium;
    }

    public BigDecimal getDeductible() {
        return deductible;
    }

    public void setDeductible(BigDecimal deductible) {
        this.deductible = deductible;
    }

    public String getCoverageArea() {
        return coverageArea;
    }

    public void setCoverageArea(String coverageArea) {
        this.coverageArea = coverageArea;
    }

    /*
    public List<Long> getVehiclesCovered() {
        return vehiclesCovered;
    }

    public void setVehiclesCovered(List<Long> vehiclesCovered) {
        this.vehiclesCovered = vehiclesCovered;
    }
    */

    public BigDecimal getCustomerLiability() {
        return customerLiability;
    }

    public void setCustomerLiability(BigDecimal customerLiability) {
        this.customerLiability = customerLiability;
    }

    public String getPolicyStatus() {
        return policyStatus;
    }

    public void setPolicyStatus(String policyStatus) {
        this.policyStatus = policyStatus;
    }

    public String getAdditionalNotes() {
        return additionalNotes;
    }

    public void setAdditionalNotes(String additionalNotes) {
        this.additionalNotes = additionalNotes;
    }

    public Boolean getIsValid() {
        return isValid;
    }

    public void setIsValid(Boolean isValid) {
        this.isValid = isValid;
    }

    public LocalDate getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(LocalDate purchaseDate) {
        this.purchaseDate = purchaseDate;
    }
    
}
