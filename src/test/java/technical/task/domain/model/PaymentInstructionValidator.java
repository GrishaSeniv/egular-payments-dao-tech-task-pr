package technical.task.domain.model;

import technical.task.domain.model.payment_instruction.PaymentInstructionReq;
import technical.task.domain.model.payment_instruction.PaymentInstructionResp;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * @author Hryhorii Seniv
 * @version 2024-11-04
 */
public class PaymentInstructionValidator {
    public static void validate(PaymentInstructionReq req, PaymentInstructionResp resp) {
        assertNotNull(resp.id(), "Response ID should not be null after creation");
        assertEquals(req.payerName(), resp.payerName(), "Payer name should match");
        assertEquals(req.payerInn(), resp.payerInn(), "Payer INN should match");
        assertEquals(req.payerCardNumber(), resp.payerCardNumber(), "Payer card number should match");
        assertEquals(req.recipientAccountNumber(), resp.recipientAccountNumber(), "Recipient account number should match");
        assertEquals(req.recipientMfo(), resp.recipientMfo(), "Recipient MFO should match");
        assertEquals(req.recipientOkpo(), resp.recipientOkpo(), "Recipient OKPO should match");
        assertEquals(req.recipientName(), resp.recipientName(), "Recipient name should match");
        assertEquals(req.periodInterval(), resp.periodInterval(), "Period interval should match");
        assertEquals(req.paymentAmount(), resp.paymentAmount(), "Payment amount should match");
    }

    public static void validate(PaymentInstructionResp actual, PaymentInstructionResp excpected) {
        assertEquals(actual.id(), excpected.id(), "Response ID should not be null after creation");
        assertEquals(actual.payerName(), excpected.payerName(), "Payer name should match");
        assertEquals(actual.payerInn(), excpected.payerInn(), "Payer INN should match");
        assertEquals(actual.payerCardNumber(), excpected.payerCardNumber(), "Payer card number should match");
        assertEquals(actual.recipientAccountNumber(), excpected.recipientAccountNumber(), "Recipient account number should match");
        assertEquals(actual.recipientMfo(), excpected.recipientMfo(), "Recipient MFO should match");
        assertEquals(actual.recipientOkpo(), excpected.recipientOkpo(), "Recipient OKPO should match");
        assertEquals(actual.recipientName(), excpected.recipientName(), "Recipient name should match");
        assertEquals(actual.periodInterval(), excpected.periodInterval(), "Period interval should match");
        assertEquals(actual.paymentAmount(), excpected.paymentAmount(), "Payment amount should match");
    }
}
