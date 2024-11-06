package technical.task.transaction;

import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import technical.task.domain.model.transaction.TransactionCreateReq;
import technical.task.domain.model.transaction.TransactionResp;
import technical.task.domain.model.transaction.TransactionSearch;
import technical.task.domain.model.transaction.TransactionUpdateReq;

import java.util.List;

/**
 * @author Hryhorii Seniv
 * @version 2024-11-04
 */
@RestController
@RequestMapping("/api/v1/transactions")
class TransactionController {
    private static final Logger logger = LoggerFactory.getLogger(TransactionController.class);
    private final TransactionService service;

    TransactionController(TransactionService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<TransactionResp> create(@RequestBody @Valid TransactionCreateReq req) {
        try {
            TransactionResp resp = service.create(req);
            return ResponseEntity.status(HttpStatus.CREATED).body(resp);
        } catch (Exception e) {
            logger.error("Something went wrong, while creating new transaction", e);
            return ResponseEntity.internalServerError().build();
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<TransactionResp> update(@RequestBody @Valid TransactionUpdateReq req,
                                                  @PathVariable Long id) {
        try {
            service.update(req, id);
            TransactionResp resp = service.getById(id);
            return ResponseEntity.status(HttpStatus.OK).body(resp);
        } catch (Exception e) {
            logger.error("Something went wrong, while updating transaction", e);
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<TransactionResp> getById(@PathVariable Long id) {
        TransactionResp resp = service.getById(id);
        return ResponseEntity.ok(resp);
    }

    @GetMapping
    public ResponseEntity<List<TransactionResp>> search(TransactionSearch req) {
        List<TransactionResp> resp = service.search(req);
        return ResponseEntity.ok(resp);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
