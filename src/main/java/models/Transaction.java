package models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import types.TransactionType;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "transaction")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "src_account")
    private Long sourceAccount;

    @Column(name = "dst_account")
    private Long destinationAccount;
    private BigDecimal volume;
    private String type;

    @Column(name = "executed_at")
    private Timestamp executedAt;

    @Column(name = "created_at")
    private Timestamp createdAt;

    @Column(name = "updated_at")
    private Timestamp updatedAt;

    public Transaction(Long sourceAccount, Long destinationAccount, BigDecimal volume, String type, Timestamp executedAt) {
        this.sourceAccount = sourceAccount;
        this.destinationAccount = destinationAccount;
        this.volume = volume;
        this.type = type;
        this.executedAt = executedAt;
    }
}

