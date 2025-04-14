package com.tracking.number.generator.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TrackingNumberResponse {
	private String tracking_number;
    private String created_at;
}