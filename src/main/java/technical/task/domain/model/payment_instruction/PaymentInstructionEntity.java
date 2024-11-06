package technical.task.domain.model.payment_instruction;

import io.hypersistence.utils.hibernate.type.interval.PostgreSQLIntervalType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.hibernate.annotations.Type;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.Objects;

/**
 * @author Hryhorii Seniv
 * @version 2024-11-04
 */
@Entity
@Table(name = "payment_instruction")
public class PaymentInstructionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "payer_full_name", nullable = false)
    private String payerName;
    @Column(name = "payer_inn", nullable = false, length = 10)
    private String payerInn;
    @Column(name = "payer_card_number", nullable = false, length = 20)
    private String payerCardNumber;
    @Column(name = "recipient_account_number", nullable = false, length = 34)
    private String recipientAccountNumber;
    @Column(name = "recipient_mfo", nullable = false, length = 6)
    private String recipientMfo;
    @Column(name = "recipient_okpo", nullable = false, length = 10)
    private String recipientOkpo;
    @Column(name = "recipient_name", nullable = false)
    private String recipientName;
    @Type(PostgreSQLIntervalType.class)
    @Column(name = "period_interval", nullable = false)
    private Duration periodInterval;
    @Column(name = "payment_amount", nullable = false)
    private BigDecimal paymentAmount;

    public Long getId() {
        return id;
    }

    public PaymentInstructionEntity setId(Long id) {
        this.id = id;
        return this;
    }

    public String getPayerName() {
        return payerName;
    }

    public PaymentInstructionEntity setPayerName(String payerName) {
        this.payerName = payerName;
        return this;
    }

    public String getPayerInn() {
        return payerInn;
    }

    public PaymentInstructionEntity setPayerInn(String payerInn) {
        this.payerInn = payerInn;
        return this;
    }

    public String getPayerCardNumber() {
        return payerCardNumber;
    }

    public PaymentInstructionEntity setPayerCardNumber(String payerCardNumber) {
        this.payerCardNumber = payerCardNumber;
        return this;
    }

    public String getRecipientAccountNumber() {
        return recipientAccountNumber;
    }

    public PaymentInstructionEntity setRecipientAccountNumber(String recipientAccountNumber) {
        this.recipientAccountNumber = recipientAccountNumber;
        return this;
    }

    public String getRecipientMfo() {
        return recipientMfo;
    }

    public PaymentInstructionEntity setRecipientMfo(String recipientMfo) {
        this.recipientMfo = recipientMfo;
        return this;
    }

    public String getRecipientOkpo() {
        return recipientOkpo;
    }

    public PaymentInstructionEntity setRecipientOkpo(String recipientOkpo) {
        this.recipientOkpo = recipientOkpo;
        return this;
    }

    public String getRecipientName() {
        return recipientName;
    }

    public PaymentInstructionEntity setRecipientName(String recipientName) {
        this.recipientName = recipientName;
        return this;
    }

    public Duration getPeriodInterval() {
        return periodInterval;
    }

    public PaymentInstructionEntity setPeriodInterval(Duration periodInterval) {
        this.periodInterval = periodInterval;
        return this;
    }

    public BigDecimal getPaymentAmount() {
        return paymentAmount;
    }

    public PaymentInstructionEntity setPaymentAmount(BigDecimal paymentAmount) {
        this.paymentAmount = paymentAmount;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PaymentInstructionEntity that = (PaymentInstructionEntity) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "PaymentInstructionEntity{" +
                "id=" + id +
                ", payerName='" + payerName + '\'' +
                ", payerInn='" + payerInn + '\'' +
                ", payerCardNumber='" + payerCardNumber + '\'' +
                ", recipientAccountNumber='" + recipientAccountNumber + '\'' +
                ", recipientMfo='" + recipientMfo + '\'' +
                ", recipientOkpo='" + recipientOkpo + '\'' +
                ", recipientName='" + recipientName + '\'' +
                ", periodInterval='" + periodInterval + '\'' +
                ", paymentAmount=" + paymentAmount +
                '}';
    }
}
