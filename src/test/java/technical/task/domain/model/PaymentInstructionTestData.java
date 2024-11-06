package technical.task.domain.model;

import technical.task.domain.model.payment_instruction.PaymentInstructionReq;
import technical.task.domain.model.payment_instruction.PaymentInstructionResp;

import java.math.BigDecimal;
import java.time.Duration;

/**
 * @author Hryhorii Seniv
 * @version 2024-11-04
 */
public class PaymentInstructionTestData {
    public static PaymentInstructionReq createTestData1() {
        return new PaymentInstructionReq(
                "John Doe Father",
                "1234567890",
                "1234567890123456",
                "UA1234567890123456789012345678901",
                "123456",
                "12345678",
                "Doe Enterprises",
                Duration.ofDays(30),
                new BigDecimal("1000.00")
        );
    }

    public static PaymentInstructionReq createTestData2() {
        return new PaymentInstructionReq(
                "Alice Johnson Father",
                "0987654321",
                "9876543210987654",
                "UA9876543210987654321098765432101",
                "654321",
                "87654321",
                "Johnson Supplies",
                Duration.ofHours(720),  // Equivalent to 30 days
                new BigDecimal("250.50")
        );
    }

    public static PaymentInstructionReq createTestData3() {
        return new PaymentInstructionReq(
                "Michael Brown Father",
                "1122334455",
                "1122334455667788",
                "UA1122334455667788990011223344556",
                "112233",
                "44556677",
                "Brown Industries",
                Duration.ofDays(1),
                new BigDecimal("75.25")
        );
    }

    public static PaymentInstructionReq createTestDataWithSamePayerInn() {
        return new PaymentInstructionReq(
                "Jane Doe",
                "1234567890",
                "2233445566778899",
                "UA2233445566778899001122334455667",
                "334455",
                "66778899",
                "Doe Solutions",
                Duration.ofDays(15),
                new BigDecimal("1500.00")
        );
    }

    public static PaymentInstructionReq createTestDataWithSameRecipientOkpo() {
        return new PaymentInstructionReq(
                "Emma Davis",
                "5566778899",
                "5566778899001122",
                "UA5566778899001122334455667788990",
                "778899",
                "87654321",
                "Davis Supplies",
                Duration.ofDays(7),
                new BigDecimal("300.00")
        );
    }

    public static PaymentInstructionReq createUpdateData1(PaymentInstructionResp existing) {
        return new PaymentInstructionReq(
                existing.payerName() + " Updated",
                existing.payerInn(),
                existing.payerCardNumber(),
                existing.recipientAccountNumber(),
                existing.recipientMfo(),
                existing.recipientOkpo(),
                existing.recipientName(),
                existing.periodInterval(),
                existing.paymentAmount().add(new BigDecimal("100.00"))
        );
    }

    public static PaymentInstructionReq createUpdateData2(PaymentInstructionResp existing) {
        return new PaymentInstructionReq(
                existing.payerName(),
                existing.payerInn(),
                existing.payerCardNumber(),
                existing.recipientAccountNumber(),
                existing.recipientMfo(),
                existing.recipientOkpo(),
                existing.recipientName() + " Updated",
                existing.periodInterval(),
                existing.paymentAmount().subtract(new BigDecimal("50.00"))
        );
    }

    public static PaymentInstructionReq createUpdateData3(PaymentInstructionResp existing) {
        return new PaymentInstructionReq(
                existing.payerName(),
                existing.payerInn(),
                existing.payerCardNumber(),
                existing.recipientAccountNumber(),
                existing.recipientMfo(),
                existing.recipientOkpo(),
                existing.recipientName(),
                Duration.ofDays(7),
                existing.paymentAmount()
        );
    }
}