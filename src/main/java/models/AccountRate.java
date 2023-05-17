package models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "account_rates")
public class AccountRate {
    @Id
    @Column(name = "account_id")
    private Long accountId;

    private BigDecimal rate;

    @Column(name = "initial_balance")
    private BigDecimal initialBalance;

    @Column(name = "payment_frequency")
    private Integer paymentFrequency;

    @Column(name = "payment_processed")
    private Integer paymentsProcessed;

    @Column(name = "next_payment_at")
    private Timestamp nextPaymentAt;
}
