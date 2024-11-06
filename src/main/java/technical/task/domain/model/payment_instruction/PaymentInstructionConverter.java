package technical.task.domain.model.payment_instruction;

import java.util.Collections;
import java.util.List;

/**
 * @author Hryhorii Seniv
 * @version 2024-11-04
 */
public class PaymentInstructionConverter {
    public static PaymentInstructionEntity toPaymentInstructionEntity(PaymentInstructionReq req) {
        return new PaymentInstructionEntity()
                .setPayerName(req.payerName())
                .setPayerInn(req.payerInn())
                .setPayerCardNumber(req.payerCardNumber())
                .setRecipientAccountNumber(req.recipientAccountNumber())
                .setRecipientMfo(req.recipientMfo())
                .setRecipientOkpo(req.recipientOkpo())
                .setRecipientName(req.recipientName())
                .setPeriodInterval(req.periodInterval())
                .setPaymentAmount(req.paymentAmount());
    }

    public static List<PaymentInstructionResp> toPaymentInstructionRespList(List<PaymentInstructionEntity> entities) {
        if (entities == null) {
            return Collections.emptyList();
        }
        return entities.stream()
                .map(PaymentInstructionConverter::toPaymentInstructionResp)
                .toList();
    }

    public static PaymentInstructionResp toPaymentInstructionResp(PaymentInstructionEntity entity) {
        return new PaymentInstructionResp(entity.getId(), entity.getPayerName(), entity.getPayerInn(), entity.getPayerCardNumber(),
                entity.getRecipientAccountNumber(), entity.getRecipientMfo(), entity.getRecipientOkpo(), entity.getRecipientName(),
                entity.getPeriodInterval(), entity.getPaymentAmount());
    }
}
