
package com.example.usage.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "customer_balance")
public class CustomerBalance {

    @Id
    @Column(name = "customer_id")
    private Long customerId;

    @Column(nullable = false)
    private long balance;

    protected CustomerBalance() {
    }

    public CustomerBalance(Long customerId, long balance) {
        this.customerId = customerId;
        this.balance = balance;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public long getBalance() {
        return balance;
    }
}
