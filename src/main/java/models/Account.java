package models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "accounts")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "profile_id")
    private Long profileId;

    @Column(name = "account_number")
    private String accountNumber;

    @Column(name = "type")
    private String type;

    @Column(name = "balance")
    private BigDecimal balance;

    @Column(name = "status")
    private String status;

    @Column(name = "expired_at")
    private Timestamp expiredAt;

    @Column(name = "opened_at")
    private Timestamp openedAt;

    @Column(name = "closed_at")
    private Timestamp closedAt;

    @Column(name = "created_at")
    private Timestamp createdAt;

    @Column(name = "updated_at")
    private Timestamp updatedAt;

    public Account(Long profileId, String type, BigDecimal balance, String accountNumber) {
        this.profileId = profileId;
        this.type = type;
        this.balance = balance;
        this.accountNumber = accountNumber;
    }

    public Account(
            Long id,
            Long profileId,
            String accountNumber,
            String type,
            BigDecimal balance,
            String status,
            Timestamp expiredAt,
            Timestamp openedAt,
            Timestamp closedAt
    ) {
        this.id = id;
        this.profileId = profileId;
        this.accountNumber = accountNumber;
        this.type = type;
        this.balance = balance;
        this.status = status;
        this.expiredAt = expiredAt;
        this.openedAt = openedAt;
        this.closedAt = closedAt;
    }
}
