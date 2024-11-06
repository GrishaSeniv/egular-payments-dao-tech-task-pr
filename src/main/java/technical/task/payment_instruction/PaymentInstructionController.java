package technical.task.payment_instruction;

import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import technical.task.domain.model.payment_instruction.PaymentInstructionReq;
import technical.task.domain.model.payment_instruction.PaymentInstructionResp;
import technical.task.domain.model.payment_instruction.PaymentInstructionSearch;

import java.util.List;

/**
 * @author Hryhorii Seniv
 * @version 2024-11-04
 */
@RestController
@RequestMapping("/api/v1/payment-instructions")
class PaymentInstructionController {
    private static final Logger logger = LoggerFactory.getLogger(PaymentInstructionController.class);
    private final PaymentInstructionService service;

    PaymentInstructionController(PaymentInstructionService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<PaymentInstructionResp> create(@RequestBody @Valid PaymentInstructionReq req) {
        try {
            PaymentInstructionResp resp = service.create(req);
            return ResponseEntity.status(HttpStatus.CREATED).body(resp);
        } catch (Exception e) {
            logger.error("Something went wrong, while creating new payment instruction", e);
            return ResponseEntity.internalServerError().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<PaymentInstructionResp> update(@RequestBody @Valid PaymentInstructionReq req,
                                                         @PathVariable Long id) {
        try {
            PaymentInstructionResp resp = service.update(req, id);
            return ResponseEntity.status(HttpStatus.OK).body(resp);
        } catch (Exception e) {
            logger.error("Something went wrong, while updating payment instruction", e);
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<PaymentInstructionResp> getById(@PathVariable Long id) {
        PaymentInstructionResp resp = service.getById(id);
        return ResponseEntity.ok(resp);
    }

    @GetMapping
    public ResponseEntity<List<PaymentInstructionResp>> search(PaymentInstructionSearch req) {
        List<PaymentInstructionResp> resp = service.search(req);
        return ResponseEntity.ok(resp);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
