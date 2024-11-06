package technical.task.domain.model.transaction;

import technical.task.domain.SearchAware;
import technical.task.domain.model.Search;

/**
 * @author Hryhorii Seniv
 * @version 2024-11-04
 */
public class TransactionSearch extends Search implements SearchAware {
    private Long paymentInstructionId;

    public boolean noPaymentInstructionIdFiltering() {
        return paymentInstructionId == null;
    }

    public Long getPaymentInstructionId() {
        return paymentInstructionId == null ? 0 : paymentInstructionId;
    }

    public TransactionSearch setPaymentInstructionId(Long paymentInstructionId) {
        this.paymentInstructionId = paymentInstructionId;
        return this;
    }

    @Override
    public String toString() {
        return "TransactionSearch{" +
                "paymentInstructionId=" + paymentInstructionId +
                '}';
    }
}
