package com.example.personalize.shopping.cart.Booking;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.personalize.shopping.cart.availability.VehicleCalendarService;
import com.example.personalize.shopping.cart.user.User;


@RestController
//@CrossOrigin(origins = "http://127.0.0.1:5500")
@CrossOrigin(origins = "https://localhost:5500", allowCredentials = "true")
@RequestMapping("/api/v1/booking")
public class BookingController {

    @Autowired
    private final BookingService bookingService;
    private final VehicleCalendarService vehicleCalendarService;

    public BookingController(BookingService bookingService, VehicleCalendarService calendarService) {
        this.bookingService = bookingService;
        this.vehicleCalendarService = calendarService;
    }

    @PostMapping()
    public ResponseEntity<String> bookAVehicle(@RequestBody Booking booking) {
        try{
            Booking bookingResponse = bookingService.addBooking(booking);
            vehicleCalendarService.getAvailabilityUsingBooking(bookingResponse);
            return ResponseEntity.ok().body("Car booked successfully");
        } catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error booking vehicle: " + e.getMessage());
        }
    }
    
    @GetMapping()
    public ResponseEntity<List<Booking>> viewAllBookings() {
        List<Booking> allBookings = bookingService.viewAllBookings(); 
        return ResponseEntity.ok(allBookings);
    }

    @GetMapping("/{bookingId}")
    public ResponseEntity<Booking> viewBookingById(@PathVariable Long bookingId) {
        Booking booking = bookingService.getBookingById(bookingId);
        return ResponseEntity.ok(booking);
    }
    
    @GetMapping("/user/{username}")
	public ResponseEntity<List<Booking>> getBookingsByUserID(@PathVariable String username) {
		//List<Booking> bookingsByUser = bookingService.getBookingsByUserID(userId);
        List<Booking> bookingsByUser = bookingService.getBookingsByUsername(username);
        return ResponseEntity.ok(bookingsByUser);
	}


    @GetMapping("/status/{status}")
	public ResponseEntity<List<Booking>> getBookingByStatus(@PathVariable String status) {
		List<Booking> bookingsByStatus = bookingService.getBookingByStatus(status);
        return ResponseEntity.ok(bookingsByStatus);
	}

    @GetMapping("/statistics")
	public ResponseEntity<Statistics> getBookingStatistics() {
		Statistics statistics = bookingService.ReservationsStatistics();
        return ResponseEntity.ok(statistics);
	}


    @GetMapping("/vehicle/{vehicleId}")
    public ResponseEntity<List<Booking>> getBookingsByVehicleID(@PathVariable Long vehicleId){
        List<Booking> vehicleBookings = bookingService.getBookingsByVehicleID(vehicleId);
        return ResponseEntity.ok(vehicleBookings); 
    }

    @PutMapping("/{bookingId}")
	public ResponseEntity<Booking> updateBooking(@PathVariable Long bookingId, @RequestBody Booking booking) {
        vehicleCalendarService.deleteCalenderByBookingId(bookingId);
        //what happens after deletion, you should reinsert the new calender
        Booking updatedBooking = bookingService.updateBooking(bookingId, booking);
        vehicleCalendarService.getAvailabilityUsingBooking(updatedBooking);
        return ResponseEntity.ok(updatedBooking);
	}

    @DeleteMapping("/{bookingId}")
    public void deleteBookingById(@PathVariable Long bookingId){
        bookingService.deleteBooking(bookingId);
        vehicleCalendarService.deleteCalenderByBookingId(bookingId);
    }
    
}
