package com.example.personalize.shopping.cart.availability;
import org.springframework.stereotype.Service;

// Distance Calculation - Haversine Formula
//ChatGPT was used to generate this code
@Service
public class DistanceService {

    //private static final double R = 6371.0;  // Radius of the Earth in kilometers
    private static final double R = 3958.8; // Earth's radius in miles

    // Haversine formula to calculate distance between two lat/long points
    public double calculateDistance(double lat1, double lon1, double lat2, double lon2) {
        // Convert degrees to radians
        lat1 = Math.toRadians(lat1);
        lon1 = Math.toRadians(lon1);
        lat2 = Math.toRadians(lat2);
        lon2 = Math.toRadians(lon2);

        double dlat = lat2 - lat1;
        double dlon = lon2 - lon1;

        // Haversine formula
        double a = Math.sin(dlat / 2) * Math.sin(dlat / 2)
                + Math.cos(lat1) * Math.cos(lat2) 
                * Math.sin(dlon / 2) * Math.sin(dlon / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return R * c;  // distance in kilometers
    }
}
