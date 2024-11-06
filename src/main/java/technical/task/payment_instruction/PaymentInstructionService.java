package technical.task.payment_instruction;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import technical.task.domain.exception.PaymentInstructionNotFoundException;
import technical.task.domain.model.payment_instruction.PaymentInstructionEntity;
import technical.task.domain.model.payment_instruction.PaymentInstructionReq;
import technical.task.domain.model.payment_instruction.PaymentInstructionResp;
import technical.task.domain.model.payment_instruction.PaymentInstructionSearch;

import java.util.List;

import static technical.task.domain.model.payment_instruction.PaymentInstructionConverter.toPaymentInstructionEntity;
import static technical.task.domain.model.payment_instruction.PaymentInstructionConverter.toPaymentInstructionResp;
import static technical.task.domain.model.payment_instruction.PaymentInstructionConverter.toPaymentInstructionRespList;

/**
 * @author Hryhorii Seniv
 * @version 2024-11-04
 */
@Service
class PaymentInstructionService {
    private static final Logger logger = LoggerFactory.getLogger(PaymentInstructionService.class);
    private final PaymentInstructionRepository repository;

    public PaymentInstructionService(PaymentInstructionRepository repository) {
        this.repository = repository;
    }

    public PaymentInstructionResp create(PaymentInstructionReq req) {
        logger.info("Creating payment instruction: {}", req);
        PaymentInstructionEntity saved = repository.save(toPaymentInstructionEntity(req));
        return toPaymentInstructionResp(saved);
    }

    @Transactional
    public PaymentInstructionResp update(PaymentInstructionReq req, Long id) {
        logger.info("Updating payment instruction: {} by id: {}", req, id);
        PaymentInstructionEntity toUpdateEntity = toPaymentInstructionEntity(req);
        toUpdateEntity.setId(id);
        repository.update(req.payerName(), req.payerInn(), req.payerCardNumber(), req.recipientAccountNumber(),
                req.recipientMfo(), req.recipientOkpo(), req.recipientName(), req.periodInterval(), req.paymentAmount(), id);
        return toPaymentInstructionResp(toUpdateEntity);
    }

    public List<PaymentInstructionResp> search(PaymentInstructionSearch search) {
        logger.info("Getting payment instructions by search: {}", search);
        List<PaymentInstructionEntity> entities = repository.search(search.noPayerrInnFiltering(), search.getPayerInn(), search.noRecipientOkpoFiltering(),
                search.getRecipientOkpo(), search.getOffset(), search.getLimit());
        return toPaymentInstructionRespList(entities);
    }

    public PaymentInstructionResp getById(Long id) {
        logger.info("Getting payment instruction by id: {}", id);
        PaymentInstructionEntity entity = repository.findById(id).orElseThrow(() -> {
            String msg = String.format("Payment instruction with id: %s not found", id);
            logger.error(msg);
            return new PaymentInstructionNotFoundException(msg);
        });
        return toPaymentInstructionResp(entity);
    }

    public void deleteById(Long id) {
        logger.info("Deleting payment instruction by id: {}", id);
        repository.deleteById(id);
    }
}
