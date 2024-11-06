package technical.task.payment_instruction;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import technical.task.domain.model.payment_instruction.PaymentInstructionEntity;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.List;

/**
 * @author Hryhorii Seniv
 * @version 2024-11-04
 */
@Repository
interface PaymentInstructionRepository extends JpaRepository<PaymentInstructionEntity, Long> {
    @Modifying
    @Query(value = """
            UPDATE payment_instruction
            SET payer_full_name = :payerFullName,
                payer_inn = :payerInn,
                payer_card_number = :payerCardNumber,
                recipient_account_number = :recipientAccountNumber,
                recipient_mfo = :recipientMfo,
                recipient_okpo = :recipientOkpo,
                recipient_name = :recipientName,
                period_interval = :periodInterval,
                payment_amount = :paymentAmount
            WHERE id = :id
            """, nativeQuery = true)
    int update(@Param("payerFullName") String payerFullName,
               @Param("payerInn") String payerInn,
               @Param("payerCardNumber") String payerCardNumber,
               @Param("recipientAccountNumber") String recipientAccountNumber,
               @Param("recipientMfo") String recipientMfo,
               @Param("recipientOkpo") String recipientOkpo,
               @Param("recipientName") String recipientName,
               @Param("periodInterval") Duration periodInterval,
               @Param("paymentAmount") BigDecimal paymentAmount,
               @Param("id") Long id);

    @Query(value = """
            SELECT p.*
            FROM payment_instruction p
            WHERE (:noPayerrInnFiltering OR p.payer_inn = :payerrInnFiltering)
            AND
            (:noRecipientOkpoFiltering OR p.recipient_okpo = :recipientOkpoFiltering)
            ORDER BY p.id
            OFFSET :offset
            LIMIT :limit
            """, nativeQuery = true)
    List<PaymentInstructionEntity> search(@Param("noPayerrInnFiltering") boolean noPayerrInnFiltering,
                                          @Param("payerrInnFiltering") String payerrInnFiltering,
                                          @Param("noRecipientOkpoFiltering") boolean noRecipientOkpoFiltering,
                                          @Param("recipientOkpoFiltering") String recipientOkpoFiltering,
                                          @Param("offset") int offset,
                                          @Param("limit") int limit);

    @Modifying
    @Query("DELETE FROM PaymentInstructionEntity t WHERE t.id = :id")
    void deleteById(@Param("id") Long id);
}
