package com.tracking.number.generator.controller;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tracking.number.generator.dto.TrackingNumberResponse;
import com.tracking.number.generator.service.TrackingNumberService;

@RestController
@RequestMapping("/next-tracking-number")
public class TrackingNumberController {

    private final TrackingNumberService trackingService;

    public TrackingNumberController(TrackingNumberService trackingService) {
        this.trackingService = trackingService;
    }

    
    @GetMapping
    public ResponseEntity<TrackingNumberResponse> getTrackingNumber(
            @RequestParam("origin_country_id") String origin,
            @RequestParam("destination_country_id") String destination,
            @RequestParam("weight") BigDecimal weight,
            @RequestParam("created_at") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) OffsetDateTime created_at,
            @RequestParam("customer_id") UUID customerId,
            @RequestParam("customer_name") String customerName,
            @RequestParam("customer_slug") String customerSlug
    ) {
        TrackingNumberResponse response = trackingService.generateTrackingNumber(
                origin, destination, weight, created_at, customerId, customerName, customerSlug);
        return ResponseEntity.ok(response);
    }
    
    /*
    @GetMapping
    public ResponseEntity<TrackingNumberResponse> getTrackingNumber(
            
    ) {
        TrackingNumberResponse response = new TrackingNumberResponse();
        
        return ResponseEntity.ok(response);
    }*/
}

