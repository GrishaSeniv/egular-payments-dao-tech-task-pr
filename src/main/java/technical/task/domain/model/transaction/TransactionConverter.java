package technical.task.domain.model.transaction;

import technical.task.domain.model.payment_instruction.PaymentInstructionEntity;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import static technical.task.domain.model.payment_instruction.PaymentInstructionConverter.toPaymentInstructionResp;

/**
 * @author Hryhorii Seniv
 * @version 2024-11-04
 */
public class TransactionConverter {
    public static List<TransactionResp> toTransactionRespList(List<TransactionEntity> entities) {
        if (entities == null) {
            return Collections.emptyList();
        }
        return entities.stream()
                .map(TransactionConverter::toTransactionResp)
                .toList();
    }

    public static TransactionResp toTransactionResp(TransactionEntity entity) {
        return new TransactionResp(entity.getId(), toPaymentInstructionResp(entity.getPaymentInstruction()), entity.getTransactionDate(),
                entity.getTransactionAmount(), entity.getStatus());
    }

    public static TransactionEntity toTransactionCreateEntity(TransactionCreateReq req) {
        return new TransactionEntity()
                .setPaymentInstruction(new PaymentInstructionEntity().setId(req.paymentInstructionId()))
                .setTransactionDate(LocalDateTime.now())
                .setTransactionAmount(req.transactionAmount())
                .setStatus(req.status());
    }
}
