package com.example.personalize.shopping.cart.Notification;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.example.personalize.shopping.cart.Booking.Booking;
import com.example.personalize.shopping.cart.Booking.BookingService;

@Component
public class CronJob {

    @Autowired
    private final BookingService bookingService;
    private final EmailService emailService;

    LocalDate today = LocalDate.now();
    LocalDate tomorrow = LocalDate.now().plusDays(1);

    String overDueEmailMessage = "Dear Renter, \nGood Day \nThis is to inform you that your booking in now overdue. \nRegards \nYB Car Rental Team";


    //@Scheduled(cron = "0 0 12 * * ?") // Every day at 12 PM
        // Runs every 10 seconds
    //@Scheduled(cron = "*/10 * * * * *")
    //public void runTask() {
    //    System.out.println("Scheduled task running at " + new java.util.Date());
    //}

    // Fixed delay after previous execution completes
    /*
    @Scheduled(fixedDelay = 5000)
    public void runWithFixedDelay() {
        System.out.println("Fixed delay task at " + new java.util.Date());
    }
    */

    public CronJob(BookingService bookingService,EmailService eService) {
        this.bookingService = bookingService;
        this.emailService = eService;
    }

    // Runs every 5 seconds regardless of the duration of the task
    /*
    @Scheduled(fixedRate = 10000) // 10,000 ms = 10 seconds
    public void runWithFixedRate() {
        //System.out.println("Fixed rate task at " + new java.util.Date());
        LocalDate today = LocalDate.now();
        //System.out.println(today); // Already prints in yyyy-MM-dd format by default
        List<Booking> bookingList = bookingService.getBookingByStartDate(today,"Confirmed");
        if(!bookingList.isEmpty()){
            for(Booking booking : bookingList){
                System.out.println("This booking starts today: "+booking);
            }
        }

    }
         */

    @Scheduled(cron = "0 0 */1 * * *") // Every 5 minutes
    //@Scheduled(fixedRate = 10000) // 10,000 ms = 10 seconds
    public void pickupNotification(){
        //System.out.println(today); // Already prints in yyyy-MM-dd format by default
        List<Booking> todayBookingList = bookingService.getBookingByStartDate(today,"Confirmed");
        if(!todayBookingList.isEmpty()){
            for(Booking booking : todayBookingList){
                System.out.println("Today's pickup: "+booking.getBookingId());
            }
        }

        //Pickup that're happening tomorrow 
        List<Booking> tomorrowBookingList = bookingService.getBookingByStartDate(tomorrow,"Confirmed");
        if(!tomorrowBookingList.isEmpty()){
            for(Booking booking : tomorrowBookingList){
                System.out.println("Tomorrow's pickup: "+booking.getBookingId());
            }
        }

    }

    //@Scheduled(fixedRate = 10000) // 10,000 ms = 10 seconds
    @Scheduled(cron = "0 0 */1 * * *")
    public void dropOffNotification(){
        //drop of today remindres

        
        //System.out.println(today); // Already prints in yyyy-MM-dd format by default
        List<Booking> todayDropOffList = bookingService.getBookingByEndDate(today,"active");
        if(!todayDropOffList.isEmpty()){
            for(Booking booking : todayDropOffList){
                System.out.println("Today's drop off: "+booking.getBookingId());
            }
        }

        //drop-offs that're happening tomorrow 
        List<Booking> tomorrowDropOffList = bookingService.getBookingByEndDate(tomorrow,"active");
        if(!tomorrowDropOffList.isEmpty()){
            for(Booking booking : tomorrowDropOffList){
                System.out.println("Tomorrow's drop off: "+booking.getBookingId());
            }
        } 
    }

    //@Scheduled(fixedRate = 10000) // 10,000 ms = 10 seconds
    //@Scheduled(cron = "0 */5 * * * *") // Every 5 minutes
    @Scheduled(cron = "0 0 */1 * * *")
    public void updateBookingStatus(){
        List<Booking> overdueBooking = bookingService.getOverdueBookings("active");
        if(!overdueBooking.isEmpty()){
            for(Booking booking : overdueBooking){
                booking.setStatus("Overdue");
                bookingService.addBooking(booking);
                String emailStatus = emailService.sendSimpleEmail("eissa.abdualrahman@gmail.com", "Overedue Car Rental", overDueEmailMessage);
                System.out.println("Overdue Booking: "+booking.getBookingId()+ " has been updated successfully"+"\n"+emailStatus);

            }
        } 

        List<Booking> expiredBooking = bookingService.getOverdueBookings("confirmed");
        if(!expiredBooking.isEmpty()){
            for(Booking booking : expiredBooking){
                booking.setStatus("Expired");
                bookingService.addBooking(booking);
                System.out.println("Expired Booking: "+booking.getBookingId()+" has been updated successfully");
            }
        } 
    }


}
