package com.example.usage.service;

import com.example.usage.domain.CustomerBalanceRepository;
import com.example.usage.domain.errors.CustomerNotFoundException;
import com.example.usage.domain.errors.InsufficientBalanceException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BalanceService {

    private final CustomerBalanceRepository repository;

    public BalanceService(CustomerBalanceRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public long applyUsage(Long customerId, long amount) {
        int updated = repository.deduct(customerId, amount);

        if (updated == 0) {
            if (!repository.existsById(customerId)) {
                throw new CustomerNotFoundException(customerId);
            }

            throw new InsufficientBalanceException(customerId, amount);
        }

        return getBalance(customerId);
    }

    @Transactional(readOnly = true)
    public long getBalance(Long customerId) {
        return repository.findById(customerId)
                .orElseThrow(() ->
                        new CustomerNotFoundException(customerId))
                .getBalance();
    }
}