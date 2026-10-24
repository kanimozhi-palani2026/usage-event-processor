
package com.example.usage.api;

public record CreateCustomerRequest(
        Long customerId,
        long balance
) {
}
