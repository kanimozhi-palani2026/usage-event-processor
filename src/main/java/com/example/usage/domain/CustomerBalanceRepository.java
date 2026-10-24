
package com.example.usage.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CustomerBalanceRepository
        extends JpaRepository<CustomerBalance, Long> {

    @Modifying(clearAutomatically = true)
    @Query("""
           update CustomerBalance c
              set c.balance = c.balance - :amount
            where c.customerId = :customerId
              and c.balance >= :amount
           """)
    int deduct(
            @Param("customerId") Long customerId,
            @Param("amount") long amount
    );
}
