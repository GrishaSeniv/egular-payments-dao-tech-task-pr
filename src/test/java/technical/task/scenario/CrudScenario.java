package technical.task.scenario;

import org.assertj.core.api.Assertions;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import technical.task.domain.client.PaymentInstructionClient;
import technical.task.domain.client.TransactionClient;
import technical.task.domain.model.PaymentInstructionValidator;
import technical.task.domain.model.TransactionValidator;
import technical.task.domain.model.payment_instruction.PaymentInstructionReq;
import technical.task.domain.model.payment_instruction.PaymentInstructionResp;
import technical.task.domain.model.payment_instruction.PaymentInstructionSearch;
import technical.task.domain.model.transaction.TransactionCreateReq;
import technical.task.domain.model.transaction.TransactionResp;
import technical.task.domain.model.transaction.TransactionSearch;
import technical.task.domain.model.transaction.TransactionUpdateReq;

import java.util.List;

import static technical.task.domain.common.Constants.NOT_EXISTING_ID;
import static technical.task.domain.model.PaymentInstructionTestData.createTestData1;
import static technical.task.domain.model.PaymentInstructionTestData.createTestData2;
import static technical.task.domain.model.PaymentInstructionTestData.createTestData3;
import static technical.task.domain.model.PaymentInstructionTestData.createTestDataWithSamePayerInn;
import static technical.task.domain.model.PaymentInstructionTestData.createTestDataWithSameRecipientOkpo;
import static technical.task.domain.model.PaymentInstructionTestData.createUpdateData1;
import static technical.task.domain.model.PaymentInstructionTestData.createUpdateData2;
import static technical.task.domain.model.PaymentInstructionTestData.createUpdateData3;
import static technical.task.domain.model.TransactionTestData.createTestTransaction1;
import static technical.task.domain.model.TransactionTestData.createTestTransaction2;
import static technical.task.domain.model.TransactionTestData.createTestTransaction3;
import static technical.task.domain.model.TransactionTestData.createTestTransaction4;
import static technical.task.domain.model.TransactionTestData.createTestTransaction5;
import static technical.task.domain.model.TransactionTestData.updateTestTransaction1;

/**
 * @author Hryhorii Seniv
 * @version 2024-11-05
 */
@Component
public class CrudScenario {
    private final PaymentInstructionClient paymentClient;
    private final TransactionClient transactionClient;

    public CrudScenario(PaymentInstructionClient paymentClient,
                        TransactionClient transactionClient) {
        this.paymentClient = paymentClient;
        this.transactionClient = transactionClient;
    }

    public void crudScenario() {
        // test payments create
        PaymentInstructionReq testPayment1 = createTestData1();
        PaymentInstructionResp respPayment1 = paymentClient.create(testPayment1);
        PaymentInstructionValidator.validate(testPayment1, respPayment1);

        PaymentInstructionReq testPayment2 = createTestData2();
        PaymentInstructionResp respPayment2 = paymentClient.create(testPayment2);
        PaymentInstructionValidator.validate(testPayment2, respPayment2);

        PaymentInstructionReq testPayment3 = createTestData3();
        PaymentInstructionResp respPayment3 = paymentClient.create(testPayment3);
        PaymentInstructionValidator.validate(testPayment3, respPayment3);

        PaymentInstructionReq testPayment4 = createTestDataWithSamePayerInn();
        PaymentInstructionResp respPayment4 = paymentClient.create(testPayment4);
        PaymentInstructionValidator.validate(testPayment4, respPayment4);

        PaymentInstructionReq testPayment5 = createTestDataWithSameRecipientOkpo();
        PaymentInstructionResp respPayment5 = paymentClient.create(testPayment5);
        PaymentInstructionValidator.validate(testPayment5, respPayment5);

        // test transactions create
        TransactionCreateReq testTransaction1 = createTestTransaction1(respPayment1);
        TransactionResp transactionResp1 = transactionClient.create(testTransaction1);
        TransactionValidator.validate(testTransaction1, transactionResp1);

        TransactionCreateReq testTransaction2 = createTestTransaction2(respPayment2);
        TransactionResp transactionResp2 = transactionClient.create(testTransaction2);
        TransactionValidator.validate(testTransaction2, transactionResp2);

        TransactionCreateReq testTransaction3 = createTestTransaction3(respPayment3);
        TransactionResp transactionResp3 = transactionClient.create(testTransaction3);
        TransactionValidator.validate(testTransaction3, transactionResp3);

        TransactionCreateReq testTransaction4 = createTestTransaction4(respPayment4);
        TransactionResp transactionResp4 = transactionClient.create(testTransaction4);
        TransactionValidator.validate(testTransaction4, transactionResp4);

        TransactionCreateReq testTransaction5 = createTestTransaction5(respPayment5);
        TransactionResp transactionResp5 = transactionClient.create(testTransaction5);
        TransactionValidator.validate(testTransaction5, transactionResp5);

        // test payments update
        PaymentInstructionReq updatePayment1 = createUpdateData1(respPayment1);
        PaymentInstructionResp respUpdatePayment1 = paymentClient.update(updatePayment1, respPayment1.id());
        PaymentInstructionValidator.validate(updatePayment1, respUpdatePayment1);

        PaymentInstructionReq updatePayment2 = createUpdateData2(respPayment2);
        PaymentInstructionResp respUpdatePayment2 = paymentClient.update(updatePayment2, respPayment2.id());
        PaymentInstructionValidator.validate(updatePayment2, respUpdatePayment2);

        PaymentInstructionReq updatePayment3 = createUpdateData3(respPayment3);
        PaymentInstructionResp respUpdatePayment3 = paymentClient.update(updatePayment3, respPayment3.id());
        PaymentInstructionValidator.validate(updatePayment3, respUpdatePayment3);

        // test transactions update
        TransactionUpdateReq updateTransaction1 = updateTestTransaction1();
        TransactionResp respUpdateTransaction1 = transactionClient.update(updateTransaction1, respPayment1.id());
        TransactionValidator.validate(updateTransaction1, respUpdateTransaction1);

        // test payments getById
        PaymentInstructionResp respPayment1ById = paymentClient.getById(respPayment1.id());
        PaymentInstructionValidator.validate(respPayment1ById, respUpdatePayment1);

        PaymentInstructionResp respPayment2ById = paymentClient.getById(respPayment2.id());
        PaymentInstructionValidator.validate(respPayment2ById, respUpdatePayment2);

        PaymentInstructionResp respPayment3ById = paymentClient.getById(respPayment3.id());
        PaymentInstructionValidator.validate(respPayment3ById, respUpdatePayment3);

        PaymentInstructionResp respPayment4ById = paymentClient.getById(respPayment4.id());
        PaymentInstructionValidator.validate(respPayment4ById, respPayment4);

        PaymentInstructionResp respPayment5ById = paymentClient.getById(respPayment5.id());
        PaymentInstructionValidator.validate(respPayment5ById, respPayment5);

        paymentClient.getByIdEntity(NOT_EXISTING_ID, HttpStatus.NOT_FOUND);

        // test transactions getById
        TransactionResp respTransaction1ById = transactionClient.getById(respUpdateTransaction1.id());
        TransactionValidator.validate(respTransaction1ById, respUpdateTransaction1);

        TransactionResp respTransaction2ById = transactionClient.getById(transactionResp2.id());
        TransactionValidator.validate(respTransaction2ById, transactionResp2);

        TransactionResp respTransaction3ById = transactionClient.getById(transactionResp3.id());
        TransactionValidator.validate(respTransaction3ById, transactionResp3);

        TransactionResp respTransaction4ById = transactionClient.getById(transactionResp4.id());
        TransactionValidator.validate(respTransaction4ById, transactionResp4);

        TransactionResp respTransaction5ById = transactionClient.getById(transactionResp5.id());
        TransactionValidator.validate(respTransaction5ById, transactionResp5);

        transactionClient.getByIdNotFound(NOT_EXISTING_ID);

        // test payments search
        List<PaymentInstructionResp> all = paymentClient.search(new PaymentInstructionSearch());
        Assertions.assertThat(all.size()).isEqualTo(5);
        Assertions.assertThat(all).containsExactlyInAnyOrder(respUpdatePayment1, respUpdatePayment2, respUpdatePayment3,
                respPayment4, respPayment5);

        List<PaymentInstructionResp> allByIpn = paymentClient.search(new PaymentInstructionSearch()
                .setPayerInn(testPayment1.payerInn()));
        Assertions.assertThat(allByIpn.size()).isEqualTo(2);
        Assertions.assertThat(allByIpn).containsExactlyInAnyOrder(respUpdatePayment1, respPayment4);

        List<PaymentInstructionResp> allByOkpo = paymentClient.search(new PaymentInstructionSearch()
                .setRecipientOkpo(testPayment2.recipientOkpo()));
        Assertions.assertThat(allByOkpo.size()).isEqualTo(2);
        Assertions.assertThat(allByOkpo).containsExactlyInAnyOrder(respUpdatePayment2, respPayment5);

        // test transactions search
        List<TransactionResp> allTransactions = transactionClient.search(new TransactionSearch().setPaymentInstructionId(transactionResp2.id()));
        Assertions.assertThat(allTransactions.size()).isEqualTo(1);

        // test transactions delete
        transactionClient.delete(transactionResp1.id());
        transactionClient.delete(transactionResp2.id());
        transactionClient.delete(transactionResp3.id());
        transactionClient.delete(transactionResp4.id());
        transactionClient.delete(transactionResp5.id());

        // test payments delete
        paymentClient.delete(respPayment1.id());
        paymentClient.delete(respPayment2.id());
        paymentClient.delete(respPayment3.id());
        paymentClient.delete(respPayment4.id());
        paymentClient.delete(respPayment5.id());
        all = paymentClient.search(new PaymentInstructionSearch());
        Assertions.assertThat(all).isEmpty();
    }
}
