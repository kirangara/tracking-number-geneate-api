package com.tracking.number.generator.controller;

import java.math.BigDecimal;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tracking.number.generator.dto.TrackingNumberResponse;
import com.tracking.number.generator.service.TrackingNumberService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/next-tracking-number")
@RequiredArgsConstructor
public class TrackingNumberController {

    private final TrackingNumberService service;
    
    @Autowired
    public TrackingNumberController(TrackingNumberService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<TrackingNumberResponse> getTrackingNumber(
            @RequestParam String origin_country_id,
            @RequestParam String destination_country_id,
            @RequestParam BigDecimal weight,
            @RequestParam String created_at,
            @RequestParam UUID customer_id,
            @RequestParam String customer_name,
            @RequestParam String customer_slug
    ) {
    	TrackingNumberResponse response = service.generateTrackingNumber(
                origin_country_id, destination_country_id, weight, created_at,
                customer_id, customer_name, customer_slug
        );
        return ResponseEntity.ok(response);
    }
}


