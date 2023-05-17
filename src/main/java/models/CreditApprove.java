package models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "account_rates")
public class CreditApprove {
    @Id
    @Column(name = "account_id")
    private Long accountId;

    @Column(name = "approver_id")
    private Long approverId;
}