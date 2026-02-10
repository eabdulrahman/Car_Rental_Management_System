package com.example.personalize.shopping.cart.Booking;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.personalize.shopping.cart.Expenses.ExpensesRepository;
import com.example.personalize.shopping.cart.Insurance.InsuranceRepository;
import com.example.personalize.shopping.cart.Maintenance.MaintenanceRepository;
import com.example.personalize.shopping.cart.availability.VehicleCalendarService;
import com.example.personalize.shopping.cart.user.User;
import com.example.personalize.shopping.cart.user.UserService;

@Service
public class BookingService {
    
    @Autowired
    private final BookingRepository bookingRepository;
    private final VehicleCalendarService vehicleCalendarService;
    private final ExpensesRepository expensesRepository;
    private final MaintenanceRepository maintenanceRepository;
    private final InsuranceRepository insuranceRepository;
    private final UserService userService;

    public BookingService(BookingRepository bookingRepository, VehicleCalendarService calendarService,
                        ExpensesRepository expensesRepository, MaintenanceRepository maintenanceRepository, 
                        InsuranceRepository insuranceRepository, UserService userService) {
        this.bookingRepository = bookingRepository;
        this.vehicleCalendarService = calendarService;
        this.expensesRepository = expensesRepository;
        this.maintenanceRepository = maintenanceRepository;
        this.insuranceRepository = insuranceRepository;
        this.userService = userService;
    }

    public Booking addBooking(Booking booking){
        try{
        //vehicleCalendarService.blockAvailabilityUsingBooking(booking);
        return bookingRepository.save(booking);
        } catch(Exception e){
            //log.error("Error during booking process: {}", e.getMessage());
            throw new RuntimeException("Booking process failed");
        }
        //return bookingRepository.save(booking);
    }

    public List<Booking> viewAllBookings(){
        return bookingRepository.findAll();
    }

    public Booking getBookingById(Long id){
        return bookingRepository.findById(id).orElse(null);
    }

    public List<Booking> getBookingsByUsername(String username){
        //List<Booking> bookingByUserId = bookingRepository.findById(id).orElse(null);
		// fetch all rows with the same userId
        User user = userService.getUserByUsername(username);
        if (user != null){
		return bookingRepository.findAll()
				.stream()
				.filter(booking -> booking.getUserId().equals(user.getId()))
				.toList();
        }
        return null;
    }

    public List<Booking> getBookingsByVehicleID(Long vehicleId){
        //List<Booking> bookingByUserId = bookingRepository.findById(id).orElse(null);
		// fetch all rows with the same userId
		return bookingRepository.findAll()
				.stream()
				.filter(booking -> booking.getVehicleId().equals(vehicleId))
				.toList();
    }

    public Booking updateBooking(Long id,Booking booking){
        Booking existingBooking = bookingRepository.findById(id).orElse(null);	
		if (existingBooking != null) {
            existingBooking.setStartDate(booking.getStartDate());
            existingBooking.setEndDate(booking.getEndDate());
            existingBooking.setNoOfDays(booking.getNoOfDays());
            existingBooking.setDailyPrice(booking.getDailyPrice());
            existingBooking.setTotalPrice(booking.getTotalPrice());
            existingBooking.setStatus(booking.getStatus());
			return bookingRepository.save(existingBooking);
		}
		return null;
    }

    public List<Booking> getBookingByStatus(String status) {
        return bookingRepository.findBookingByStatus(status);
    }

    //These are for the cron job
    public List<Booking> getBookingByStartDate(LocalDate startDate,String status) {
        return bookingRepository.findBookingByStartDateAndStatus(startDate,status);
    }

    public List<Booking> getBookingByEndDate(LocalDate endDate, String status) {
        return bookingRepository.findBookingByEndDateAndStatus(endDate,status);
    }

    public List<Booking> getOverdueBookings(String status) {
        return bookingRepository.findOverdueBookings(status);
    }




    public Statistics ReservationsStatistics() {

        Statistics statistics = new Statistics();
        statistics.setRequestedBookings(bookingRepository.RequestedBookingsCount());
        statistics.setConfirmedBookings(bookingRepository.confirmedBookingsCount());
        statistics.setActiveRentals(bookingRepository.activeRentalsCount());
        statistics.setOverdueRentals(bookingRepository.overdueBookingsCount());
        statistics.setExpiredRentals(bookingRepository.expiredBookingsCount());
        statistics.setCancelledBookings(bookingRepository.cancelledBookingsCount());
        statistics.setRevenue(bookingRepository.calculateRevenue());
        statistics.setOtherExpenses(expensesRepository.sumTotalExpenses());
        statistics.setTotalInsurance(insuranceRepository.sumTotalInsurancePremium());
        statistics.setTotalMaintenance(maintenanceRepository.sumTotalMaintenanceCost());
        statistics.setTotalExpenses(statistics.getOtherExpenses().add(statistics.getTotalInsurance()).add(statistics.getTotalMaintenance()));
        statistics.setProfits(statistics.getRevenue().subtract(statistics.getTotalExpenses()));

        return statistics;
    }




    public void deleteBooking(Long id){
        bookingRepository.deleteById(id);
    }

}
