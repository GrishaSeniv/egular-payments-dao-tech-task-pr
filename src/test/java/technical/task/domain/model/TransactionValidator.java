package technical.task.domain.model;

import technical.task.domain.model.transaction.TransactionCreateReq;
import technical.task.domain.model.transaction.TransactionResp;
import technical.task.domain.model.transaction.TransactionUpdateReq;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * @author Hryhorii Seniv
 * @version 2024-11-04
 */
public class TransactionValidator {
    public static void validate(TransactionCreateReq req, TransactionResp resp) {
        assertNotNull(resp.id(), "Transaction ID should not be null after creation");
        assertEquals(req.paymentInstructionId(), resp.paymentInstruction().id());
        assertEquals(req.transactionAmount(), resp.transactionAmount());
        assertEquals(req.status(), resp.status());
    }

    public static void validate(TransactionUpdateReq req, TransactionResp resp) {
        assertEquals(req.status(), resp.status());
    }

    public static void validate(TransactionResp actual, TransactionResp expected) {
        assertEquals(actual.id(), expected.id());
        assertEquals(actual.paymentInstruction().id(), expected.paymentInstruction().id());
        assertEquals(actual.transactionAmount(), expected.transactionAmount());
        assertEquals(actual.status(), expected.status());
    }
}
