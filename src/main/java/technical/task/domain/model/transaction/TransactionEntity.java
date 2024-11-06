package technical.task.domain.model.transaction;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import technical.task.domain.model.payment_instruction.PaymentInstructionEntity;
import technical.task.domain.type.TransactionStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * @author Hryhorii Seniv
 * @version 2024-11-04
 */
@Entity
@Table(name = "transaction")
public class TransactionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "payment_instruction_id")
    private PaymentInstructionEntity paymentInstruction;
    @Column(name = "transaction_date", nullable = false)
    private LocalDateTime transactionDate;
    @Column(name = "transaction_amount", nullable = false)
    private BigDecimal transactionAmount;
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, length = 1)
    private TransactionStatus status;

    public Long getId() {
        return id;
    }

    public TransactionEntity setId(Long id) {
        this.id = id;
        return this;
    }

    public PaymentInstructionEntity getPaymentInstruction() {
        return paymentInstruction;
    }

    public TransactionEntity setPaymentInstruction(PaymentInstructionEntity paymentInstruction) {
        this.paymentInstruction = paymentInstruction;
        return this;
    }

    public LocalDateTime getTransactionDate() {
        return transactionDate;
    }

    public TransactionEntity setTransactionDate(LocalDateTime transactionDate) {
        this.transactionDate = transactionDate;
        return this;
    }

    public BigDecimal getTransactionAmount() {
        return transactionAmount;
    }

    public TransactionEntity setTransactionAmount(BigDecimal transactionAmount) {
        this.transactionAmount = transactionAmount;
        return this;
    }

    public TransactionStatus getStatus() {
        return status;
    }

    public TransactionEntity setStatus(TransactionStatus status) {
        this.status = status;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TransactionEntity that = (TransactionEntity) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", paymentInstruction=" + paymentInstruction +
                ", transactionDate=" + transactionDate +
                ", transactionAmount=" + transactionAmount +
                ", status=" + status +
                '}';
    }
}
