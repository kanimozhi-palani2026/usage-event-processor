
package com.example.usage.domain.errors;

public class InsufficientBalanceException extends RuntimeException {

    public InsufficientBalanceException(Long customerId, long amount) {
        super("Customer " + customerId
                + " has insufficient balance for usage of " + amount);
    }
}
