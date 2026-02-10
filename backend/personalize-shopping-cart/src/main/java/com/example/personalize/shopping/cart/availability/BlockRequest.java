package com.example.personalize.shopping.cart.availability;

import java.time.LocalDate;

public class BlockRequest {
    private Long vehicleId;
    private Long bookingId;
    private String sourceUsername;
    private LocalDate startDate;
    private LocalDate endDate;
    private String reason;

    public BlockRequest(Long vehicleId, Long bookingId, String sourceUsername, LocalDate startDate, LocalDate endDate,
            String reason) {
        this.vehicleId = vehicleId;
        this.bookingId = bookingId;
        this.sourceUsername = sourceUsername;
        this.startDate = startDate;
        this.endDate = endDate;
        this.reason = reason;
    }

    public Long getVehicleId() {
        return vehicleId;
    }
    public void setVehicleId(Long vehicleId) {
        this.vehicleId = vehicleId;
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
    
    public String getReason() {
        return reason;
    }
    public void setReason(String reason) {
        this.reason = reason;
    }
    public Long getBookingId() {
        return bookingId;
    }
    public void setBookingId(Long bookingId) {
        this.bookingId = bookingId;
    }

    public String getSourceUsername() {
        return sourceUsername;
    }

    public void setSourceUsername(String sourceUsername) {
        this.sourceUsername = sourceUsername;
    }

    @Override
    public String toString() {
        return "BlockRequest [vehicleId=" + vehicleId + ", bookingId=" + bookingId + ", sourceUserType="
                + sourceUsername + ", startDate=" + startDate + ", endDate=" + endDate + ", reason=" + reason + "]";
    }


    
    
    
}