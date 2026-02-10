package com.example.personalize.shopping.cart.Booking;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
    public List<Booking> findBookingByStatus(String status);

    public List<Booking> findBookingByStartDateAndStatus(LocalDate startDate, String status);

    public List<Booking> findBookingByEndDateAndStatus(LocalDate enDate, String status);

    @Query("SELECT b FROM Booking b WHERE b.endDate < CURRENT_DATE AND b.status = :status")
    List<Booking> findOverdueBookings(@Param("status") String status);

    // Count the number of 'new' bookings
    @Query("SELECT COUNT(b) FROM Booking b WHERE b.status = 'requested'")
    int RequestedBookingsCount();

    // Count the number of 'confirmed' bookings
    @Query("SELECT COUNT(b) FROM Booking b WHERE b.status = 'confirmed'")
    int confirmedBookingsCount();

    // Count the number of 'active' bookings
    @Query("SELECT COUNT(b) FROM Booking b WHERE b.status = 'active'")
    int activeRentalsCount();

    // Count the number of 'overdue' bookings
    @Query("SELECT COUNT(b) FROM Booking b WHERE b.status = 'Overdue'")
    int overdueBookingsCount();

    // Count the number of 'expired' bookings
    @Query("SELECT COUNT(b) FROM Booking b WHERE b.status = 'Expired'")
    int expiredBookingsCount();

    // Count the number of 'cancelled' bookings
    @Query("SELECT COUNT(b) FROM Booking b WHERE b.status = 'Cancelled'")
    int cancelledBookingsCount();


    // Sum the revenue of 'confirmed' bookings
    @Query("SELECT COALESCE(SUM(b.totalPrice),0) FROM Booking b WHERE b.status IN ('confirmed', 'active','completed')")
    BigDecimal calculateRevenue();

}
