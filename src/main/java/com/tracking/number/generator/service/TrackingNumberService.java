package com.tracking.number.generator.service;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.UUID;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import com.tracking.number.generator.dto.TrackingNumberResponse;

@Service
public class TrackingNumberService {

    private final StringRedisTemplate redisTemplate;

    public TrackingNumberService(StringRedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public TrackingNumberResponse generateTrackingNumber(String origin, String destination,
                                                          BigDecimal weight, OffsetDateTime createdAt,
                                                          UUID customerId, String customerName,
                                                          String customerSlug) {
        // Atomic counter using Redis
        String key = "tracking-number-counter";
        Long sequence = redisTemplate.opsForValue().increment(key);

        // Construct unique ID using params + sequence
        String raw = origin + destination + customerSlug.replace("-", "").toUpperCase() + String.format("%06d", sequence);
        String trackingNumber = raw.replaceAll("[^A-Z0-9]", "").substring(0, Math.min(16, raw.length()));

        OffsetDateTime now = OffsetDateTime.now(ZoneOffset.UTC);
        return new TrackingNumberResponse(trackingNumber, now);
    }
}

