package technical.task.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Hryhorii Seniv
 * @version 2024-11-04
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class PaymentInstructionNotFoundException extends RuntimeException {
    public PaymentInstructionNotFoundException(String message) {
        super(message);
    }
}
