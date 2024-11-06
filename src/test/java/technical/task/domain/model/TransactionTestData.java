package technical.task.domain.model;

import technical.task.domain.model.payment_instruction.PaymentInstructionResp;
import technical.task.domain.model.transaction.TransactionCreateReq;
import technical.task.domain.model.transaction.TransactionUpdateReq;
import technical.task.domain.type.TransactionStatus;

import java.math.BigDecimal;

/**
 * @author Hryhorii Seniv
 * @version 2024-11-05
 */
public class TransactionTestData {
    public static TransactionCreateReq createTestTransaction1(PaymentInstructionResp respPayment1) {
        return new TransactionCreateReq(
                respPayment1.id(),
                new BigDecimal("500.00"),
                TransactionStatus.A
        );
    }

    public static TransactionCreateReq createTestTransaction2(PaymentInstructionResp respPayment2) {
        return new TransactionCreateReq(
                respPayment2.id(),
                new BigDecimal("750.50"),
                TransactionStatus.S
        );
    }

    public static TransactionCreateReq createTestTransaction3(PaymentInstructionResp respPayment3) {
        return new TransactionCreateReq(
                respPayment3.id(),
                new BigDecimal("250.00"),
                TransactionStatus.A
        );
    }

    public static TransactionCreateReq createTestTransaction4(PaymentInstructionResp respPayment4) {
        return new TransactionCreateReq(
                respPayment4.id(),
                new BigDecimal("10000.00"),
                TransactionStatus.A
        );
    }

    public static TransactionCreateReq createTestTransaction5(PaymentInstructionResp respPayment5) {
        return new TransactionCreateReq(
                respPayment5.id(),
                new BigDecimal("0.01"),
                TransactionStatus.S
        );
    }

    public static TransactionUpdateReq updateTestTransaction1() {
        return new TransactionUpdateReq(
                TransactionStatus.S
        );
    }
}
