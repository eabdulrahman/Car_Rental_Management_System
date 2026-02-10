package com.example.personalize.shopping.cart.availability;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.personalize.shopping.cart.Booking.Booking;
import com.example.personalize.shopping.cart.user.UserService;
import com.example.personalize.shopping.cart.vehicle.Vehicle;
import com.example.personalize.shopping.cart.vehicle.VehicleService;

import jakarta.transaction.Transactional;

@Service
public class VehicleCalendarService {

    @Autowired
    private final VehicleCalendarRepository calendarRepository;
    private final VehicleService vehicleService;
    private final UserService userService;
    private DistanceService distanceService;


    public VehicleCalendarService(VehicleCalendarRepository vehicleCalendarRepository, VehicleService vehicleService, UserService userService, DistanceService distanceService) {
        this.calendarRepository = vehicleCalendarRepository;
        this.vehicleService = vehicleService;
        this.userService = userService;
        this.distanceService = distanceService;
    }

    public void blockVehicleAvailability(BlockRequest blockRequest) {
        List<VehicleCalendar> calendarEntries = new ArrayList<>();

        LocalDate current = blockRequest.getStartDate();
        while (!current.isAfter(blockRequest.getEndDate())) {
            VehicleCalendar entry = new VehicleCalendar();
            entry.setVehicleId(blockRequest.getVehicleId());
            entry.setDate(current);
            entry.setIsAvailable(false);
            entry.setReason(blockRequest.getReason());
            entry.setBookingId(blockRequest.getBookingId());
            entry.setUsername(blockRequest.getSourceUsername());
            calendarEntries.add(entry);
            current = current.plusDays(1);
        }

        calendarRepository.saveAll(calendarEntries);  // Bulk insert
        //return "Booking was successfully blocked on availability calender";
    }

    public List<VehicleCalendar> getAvailabilityUsingBooking(Booking booking){
        try{
            String username = userService.getUserById(booking.getUserId()).getUsername();
            BlockRequest blockRequest = new BlockRequest(
            booking.getVehicleId(),booking.getBookingId(),username,
            booking.getStartDate(),booking.getEndDate(),"Booking");
            blockVehicleAvailability(blockRequest);
            return getAvailabilityByBookingId(booking.getBookingId());
        } catch(Exception e){
            throw new RuntimeException("error while blocking vehicle calendar");
        }
    }

    public List<VehicleCalendar> getVehicleAvailability(Long vehicleId){
        /*
        return calendarRepository.findAll()
        		.stream()
				.filter(entry -> entry.getVehicleId().equals(vehicleId))
				.toList();
        */
        return calendarRepository.findByVehicleId(vehicleId);
    }

    public List<Vehicle> checkVehiclesAvailability(DateRangeRequest request) {
        List<Vehicle> availableVehicles = new ArrayList<>();
        List<Vehicle> vehiclesList = vehicleService.getAllVehicles();

        for (Vehicle vehicle : vehiclesList) {
            List<VehicleCalendar> vehicleCalendarList = getVehicleAvailability(vehicle.getId());

            boolean isBooked = false;

            for (VehicleCalendar vehicleCalendar : vehicleCalendarList) {
                LocalDate bookedDate = vehicleCalendar.getDate();
                if ((bookedDate.isEqual(request.getStartDate()) || bookedDate.isAfter(request.getStartDate())) &&
                    (bookedDate.isEqual(request.getEndDate()) || bookedDate.isBefore(request.getEndDate()))) {
                    isBooked = true;
                    break; // No need to check further if it's already booked
                }
            }

            if (!isBooked) {
                availableVehicles.add(vehicle);
            }
        }
        //return availableVehicles;
        return findNearbyCars(availableVehicles,request);
    }

    @Transactional
    public String deleteCalenderByBookingId(Long bookingId){
        calendarRepository.deleteByBookingId(bookingId);
        return "Calender deleted successfully";
    }

    //@Transactional
    public List<VehicleCalendar> getAvailabilityByBookingId(Long bookingId){
        return calendarRepository.findByBookingId(bookingId);
    }

    // Get cars within a certain radius of the customer's location
    public List<Vehicle> findNearbyCars(List<Vehicle> allCars, DateRangeRequest request) {
        //List<Car> allCars = carRepository.findAll();
        double customerLat = request.getLatitude();
        double customerLon = request.getLongitude();
        double radiusInKm = request.getRadius();

        return allCars.stream()
                .filter(car -> {
                    double distance = distanceService.calculateDistance(customerLat, customerLon, car.getLocation().getLatitude(), car.getLocation().getLongitude());
                    return distance <= radiusInKm;  // Keep cars within the radius
                })
                .collect(Collectors.toList());
    }
    
}
