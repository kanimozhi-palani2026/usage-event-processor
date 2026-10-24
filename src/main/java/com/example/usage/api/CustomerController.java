
package com.example.usage.api;

import com.example.usage.domain.CustomerBalance;
import com.example.usage.domain.CustomerBalanceRepository;
import com.example.usage.service.BalanceService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    private final BalanceService balanceService;
    private final CustomerBalanceRepository repository;

    public CustomerController(
            BalanceService balanceService,
            CustomerBalanceRepository repository) {
        this.balanceService = balanceService;
        this.repository = repository;
    }

    @GetMapping("/{customerId}/balance")
    public BalanceResponse getBalance(
            @PathVariable Long customerId) {

        long balance = balanceService.getBalance(customerId);

        return new BalanceResponse(customerId, balance);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BalanceResponse createCustomer(
            @RequestBody CreateCustomerRequest request) {

        if (request.customerId() == null
                || request.customerId() <= 0) {
            throw new IllegalArgumentException(
                    "Customer ID must be positive");
        }

        if (request.balance() < 0) {
            throw new IllegalArgumentException(
                    "Balance cannot be negative");
        }

        if (repository.existsById(request.customerId())) {
            throw new IllegalArgumentException(
                    "Customer already exists: "
                            + request.customerId());
        }

        CustomerBalance customer = new CustomerBalance(
                request.customerId(),
                request.balance());

        repository.save(customer);

        return new BalanceResponse(
                customer.getCustomerId(),
                customer.getBalance());
    }
}
