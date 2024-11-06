package technical.task.transaction;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import technical.task.domain.exception.TransactionNotFoundException;
import technical.task.domain.model.transaction.TransactionCreateReq;
import technical.task.domain.model.transaction.TransactionEntity;
import technical.task.domain.model.transaction.TransactionResp;
import technical.task.domain.model.transaction.TransactionSearch;
import technical.task.domain.model.transaction.TransactionUpdateReq;

import java.util.List;

import static technical.task.domain.model.transaction.TransactionConverter.toTransactionCreateEntity;
import static technical.task.domain.model.transaction.TransactionConverter.toTransactionResp;
import static technical.task.domain.model.transaction.TransactionConverter.toTransactionRespList;

/**
 * @author Hryhorii Seniv
 * @version 2024-11-04
 */
@Service
class TransactionService {
    private static final Logger logger = LoggerFactory.getLogger(TransactionService.class);
    private final TransactionRepository repository;

    TransactionService(TransactionRepository repository) {
        this.repository = repository;
    }

    public TransactionResp create(TransactionCreateReq req) {
        logger.info("Create transaction: {}", req);
        TransactionEntity saved = repository.save(toTransactionCreateEntity(req));
        return toTransactionResp(saved);
    }

    @Transactional
    public void update(TransactionUpdateReq req, Long id) {
        logger.info("Update transaction: {}", req);
        repository.update(req.status() == null ? "" : req.status().name(), id);
    }

    public List<TransactionResp> search(TransactionSearch search) {
        logger.info("Getting transaction by search: {}", search);
        List<TransactionEntity> entities = repository.search(search.noPaymentInstructionIdFiltering(), search.getPaymentInstructionId(), PageRequest.of(search.getOffset() / search.getLimit(), search.getLimit()));
        return toTransactionRespList(entities);
    }

    public TransactionResp getById(Long id) {
        return toTransactionResp(getEntity(id));
    }

    public void deleteById(Long id) {
        logger.info("Deleting transaction by id: {}", id);
        repository.deleteById(id);
    }

    private TransactionEntity getEntity(Long id) {
        logger.info("Get transaction by id: {}", id);
        return repository.get(id)
                .orElseThrow(() -> {
                    String msg = String.format("transaction with id: %s not found", id);
                    logger.error(msg);
                    return new TransactionNotFoundException(msg);
                });
    }
}
