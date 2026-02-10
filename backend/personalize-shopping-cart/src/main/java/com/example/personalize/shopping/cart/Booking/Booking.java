package com.example.personalize.shopping.cart.Booking;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="bookings")
public class Booking {

    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookingId;
    
    private String bookingTime;
    private Long userId;
    private Long vehicleId;
    private LocalDate startDate;
    private LocalDate endDate;
    private Long noOfDays;
    private Long dailyPrice;
    private Long totalPrice;
    private String status;
    private String notes;

    public Long getBookingId() {
        return bookingId;
    }
    public void setBookingId(Long bookingId) {
        this.bookingId = bookingId;
    }
    public String getBookingTime() {
        return bookingTime;
    }
    public void setBookingTime(String bookingTime) {
        this.bookingTime = bookingTime;
    }
    public Long getUserId() {
        return userId;
    }
    public void setUserId(Long userId) {
        this.userId = userId;
    }
    
    public void setVehicleId(Long vehicleId) {
        this.vehicleId = vehicleId;
    }

    public Long getVehicleId() {
        return vehicleId;
    }
    public void setVehicleId(long vehicleId) {
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
    public Long getNoOfDays() {
        return noOfDays;
    }
    public void setNoOfDays(Long noOfDays) {
        this.noOfDays = noOfDays;
    }
    public Long getDailyPrice() {
        return dailyPrice;
    }
    public void setDailyPrice(Long dailyPrice) {
        this.dailyPrice = dailyPrice;
    }
    public Long getTotalPrice() {
        return totalPrice;
    }
    public void setTotalPrice(Long totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }

    public String getNotes() {
        return notes;
    }
    public void setNotes(String notes) {
        this.notes = notes;
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Booking{");
        sb.append("bookingId=").append(bookingId);
        sb.append(", bookingTime=").append(bookingTime);
        sb.append(", userId=").append(userId);
        sb.append(", vehicleId=").append(vehicleId);
        sb.append(", startDate=").append(startDate);
        sb.append(", endDate=").append(endDate);
        sb.append(", noOfDays=").append(noOfDays);
        sb.append(", dailyPrice=").append(dailyPrice);
        sb.append(", totalPrice=").append(totalPrice);
        sb.append(", status=").append(status);
        sb.append('}');
        return sb.toString();
    }


}
