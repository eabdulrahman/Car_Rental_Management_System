package com.example.personalize.shopping.cart.availability;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleCalendarRepository extends JpaRepository<VehicleCalendar, Long> {
    // Optional: custom delete or upsert queries if needed
    List<VehicleCalendar> findByVehicleId(Long vehicleId);
    List<VehicleCalendar> findByBookingId(Long bookingId);
    void deleteByBookingId(Long bookingId);
}