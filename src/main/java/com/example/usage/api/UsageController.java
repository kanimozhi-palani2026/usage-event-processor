package com.example.usage.api;

import com.example.usage.service.BalanceService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usage")
public class UsageController {

    private final BalanceService balanceService;

    public UsageController(BalanceService balanceService) {
        this.balanceService = balanceService;
    }

    @PostMapping
    public BalanceResponse recordUsage(
            @Valid @RequestBody UsageRequest request) {

        long updatedBalance = balanceService.applyUsage(
                request.customerId(),
                request.amount()
        );

        return new BalanceResponse(
                request.customerId(),
                updatedBalance
        );
    }
}