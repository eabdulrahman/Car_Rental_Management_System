package com.example.personalize.shopping.cart.availability;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.personalize.shopping.cart.Booking.Booking;
import com.example.personalize.shopping.cart.vehicle.Vehicle;

@RestController
//@CrossOrigin(origins = "http://127.0.0.1:5500")
@CrossOrigin(origins = "https://localhost:5500", allowCredentials = "true")
@RequestMapping("/api/v1/vehicle")
public class VehicleCalendarController {

    @Autowired
    VehicleCalendarService vehicleCalendarService;

    public VehicleCalendarController(VehicleCalendarService calendarService) {
        this.vehicleCalendarService = calendarService;
    }

    @PostMapping("/availability")
    public ResponseEntity<String> blockAvailability(@RequestBody BlockRequest request) {
        vehicleCalendarService.blockVehicleAvailability(request);
        return ResponseEntity.ok("Availability blocked successfully");
    }

    @PostMapping("/availability/booking")
    public ResponseEntity<List<VehicleCalendar>> blockAvailabilityBooking(@RequestBody Booking booking) {
        List<VehicleCalendar> calenderList = vehicleCalendarService.getAvailabilityUsingBooking(booking);
        return ResponseEntity.ok(calenderList);
    }

    @PostMapping("/checkavailability")
    public ResponseEntity<List<Vehicle>> listAvailableVehicles(@RequestBody DateRangeRequest request) {
        List<Vehicle> availableVehicles = vehicleCalendarService.checkVehiclesAvailability(request);
        return ResponseEntity.ok(availableVehicles);
    }

    @GetMapping("/availability/{vehicleId}")
    public ResponseEntity<List<VehicleCalendar>> getVehicleAvailability(@PathVariable Long vehicleId) {
        //List<VehicleCalendar> vehicleCalendars = vehicleCalendarService.getVehicleAvailability(vehicleId);
        //return ResponseEntity.ok(vehicleCalendars);
        List<VehicleCalendar> vehicleCalendars = vehicleCalendarService.getVehicleAvailability(vehicleId);
        //getVehicleAvailability(vehicleId);
        return ResponseEntity.ok(vehicleCalendars);
    }

    @DeleteMapping("/availability/{bookingId}")
    public ResponseEntity<String> deleteCalenderByBookingId(@PathVariable Long bookingId){
        String response = vehicleCalendarService.deleteCalenderByBookingId(bookingId);
        return ResponseEntity.ok("Booking with id: "+bookingId+" "+response);
    }

}

    /*
     * 
     *     @GetMapping("/findCars")
    public List<Car> findNearbyCars(
            @RequestParam double lat, 
            @RequestParam double lon, 
            @RequestParam(defaultValue = "10") double radius) {
        
        return carService.findNearbyCars(lat, lon, radius);
    }
     */

