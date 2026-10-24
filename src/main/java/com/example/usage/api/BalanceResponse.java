package com.example.usage.api;

public record BalanceResponse(
        Long customerId,
        long balance
) {
}