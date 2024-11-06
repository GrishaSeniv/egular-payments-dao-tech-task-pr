package technical.task.transaction;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import technical.task.domain.model.transaction.TransactionEntity;

import java.util.List;
import java.util.Optional;

/**
 * @author Hryhorii Seniv
 * @version 2024-11-04
 */
@Repository
public interface TransactionRepository extends JpaRepository<TransactionEntity, Long> {
    @Modifying
    @Query(value = """
            UPDATE transaction
            SET status = :status
            WHERE id = :id
            """, nativeQuery = true)
    int update(@Param("status") String status,
               @Param("id") Long id);

    @Query(value = """
            SELECT t
            FROM TransactionEntity t
            JOIN FETCH t.paymentInstruction
            WHERE (:noPaymentInstructionIdFiltering = TRUE OR t.paymentInstruction.id = :paymentInstructionId)
            ORDER BY t.transactionDate DESC
            """)
    List<TransactionEntity> search(@Param("noPaymentInstructionIdFiltering") boolean noPaymentInstructionIdFiltering,
                                   @Param("paymentInstructionId") Long paymentInstructionId,
                                   Pageable pageable);

    @Query(value = """
            SELECT t
            FROM TransactionEntity t
            JOIN FETCH t.paymentInstruction p
            WHERE t.id = :id
            """)
    Optional<TransactionEntity> get(@Param("id") long id);

    @Modifying
    @Query("DELETE FROM TransactionEntity t WHERE t.id = :id")
    void deleteById(@Param("id") Long id);
}
