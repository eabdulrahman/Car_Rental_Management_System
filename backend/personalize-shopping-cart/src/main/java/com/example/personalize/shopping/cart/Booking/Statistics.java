package com.example.personalize.shopping.cart.Booking;

import java.math.BigDecimal;

public class Statistics {
  private int requestedBookings;
  private int confirmedBookings;
  private int activeRentals;
  private int overdueRentals;
  private int expiredRentals;
  private int cancelledBookings;
  private BigDecimal revenue;
  private BigDecimal otherExpenses;
  private BigDecimal totalInsurance;
  private BigDecimal totalMaintenance;
  private BigDecimal totalExpenses;
  private BigDecimal profits;

    public int getRequestedBookings() {
        return requestedBookings;
    }

    public void setRequestedBookings(int requestedBookings) {
        this.requestedBookings = requestedBookings;
    }

    public int getConfirmedBookings() {
        return confirmedBookings;
    }

    public void setConfirmedBookings(int confirmedBookings) {
        this.confirmedBookings = confirmedBookings;
    }

    public int getActiveRentals() {
        return activeRentals;
    }

    public void setActiveRentals(int activeRentals) {
        this.activeRentals = activeRentals;
    }


    public int getOverdueRentals() {
        return overdueRentals;
    }

    public void setOverdueRentals(int overdueRentals) {
        this.overdueRentals = overdueRentals;
    }

    public int getExpiredRentals() {
        return expiredRentals;
    }

    public void setExpiredRentals(int expiredRentals) {
        this.expiredRentals = expiredRentals;
    }

    public BigDecimal getRevenue() {
        return revenue;
    }

    public void setRevenue(BigDecimal revenue) {
        this.revenue = revenue;
    }

    public BigDecimal getTotalExpenses() {
        return totalExpenses;
    }

    public void setTotalExpenses(BigDecimal totalExpenses) {
        this.totalExpenses = totalExpenses;
    }

    public BigDecimal getProfits() {
        return profits;
    }

    public void setProfits(BigDecimal profits) {
        this.profits = profits;
    }

    public int getCancelledBookings() {
        return cancelledBookings;
    }

    public void setCancelledBookings(int cancelledBookings) {
        this.cancelledBookings = cancelledBookings;
    }

    public BigDecimal getTotalInsurance() {
        return totalInsurance;
    }

    public void setTotalInsurance(BigDecimal totalInsurance) {
        this.totalInsurance = totalInsurance;
    }

    public BigDecimal getTotalMaintenance() {
        return totalMaintenance;
    }

    public void setTotalMaintenance(BigDecimal totalMaintenance) {
        this.totalMaintenance = totalMaintenance;
    }

    public BigDecimal getOtherExpenses() {
        return otherExpenses;
    }

    public void setOtherExpenses(BigDecimal otherExpenses) {
        this.otherExpenses = otherExpenses;
    }


}
