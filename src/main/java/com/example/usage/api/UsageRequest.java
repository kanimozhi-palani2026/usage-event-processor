package com.example.usage.api;

import com.example.usage.domain.UsageType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record UsageRequest(
        @NotNull Long customerId,
        @NotNull UsageType type,
        @NotNull @Positive Long amount
) {
}