package com.tracking.number.generator.service;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Service;

import com.tracking.number.generator.dto.TrackingNumberResponse;

@Service
public class TrackingNumberService {

    private final AtomicLong counter = new AtomicLong(1000);
    private final DateTimeFormatter formatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME;

    public TrackingNumberResponse generateTrackingNumber(
            String originCountry, String destinationCountry, BigDecimal weight,
            String createdAt, UUID customerId, String customerName, String customerSlug
    ) {
        long nanoTime = System.nanoTime();
        long sequence = counter.getAndIncrement();

        // Combine elements to form a raw string
        String base = originCountry + destinationCountry +
                Math.abs(customerId.hashCode()) +
                Long.toHexString(nanoTime) + sequence;

        // Clean up and format
        String tracking = base.replaceAll("[^A-Z0-9]", "").toUpperCase();
        if (tracking.length() > 16) tracking = tracking.substring(0, 16);

        if (!tracking.matches("^[A-Z0-9]{1,16}$")) {
            throw new RuntimeException("Generated tracking number is invalid.");
        }

        return new TrackingNumberResponse(tracking, OffsetDateTime.now().format(formatter));
    }
}


