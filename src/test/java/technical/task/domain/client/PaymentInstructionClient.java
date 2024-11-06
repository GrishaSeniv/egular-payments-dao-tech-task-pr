package technical.task.domain.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import technical.task.domain.model.payment_instruction.PaymentInstructionReq;
import technical.task.domain.model.payment_instruction.PaymentInstructionResp;
import technical.task.domain.model.payment_instruction.PaymentInstructionSearch;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static technical.task.domain.client.ClientUtils.buildSearchUrl;
import static technical.task.domain.client.ClientUtils.buildURIWithId;
import static technical.task.domain.client.ClientUtils.getHttpEntity;
import static technical.task.domain.common.Constants.PAYMENT_INSTRUCTION_BASE_URL;
import static technical.task.domain.common.Constants.TRANSACTION_BASE_URL;

/**
 * @author Hryhorii Seniv
 * @version 2024-11-04
 */
@Component
public class PaymentInstructionClient {
    private static final Logger logger = LoggerFactory.getLogger(PaymentInstructionClient.class);
    private final RestTemplate restTemplate;

    PaymentInstructionClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public PaymentInstructionResp create(PaymentInstructionReq req) {
        try {
            logger.info("[RestClient] [PaymentInstructionClient#create] with req: {}", req);
            return restTemplate.postForObject(PAYMENT_INSTRUCTION_BASE_URL, req, PaymentInstructionResp.class);
        } catch (Exception e) {
            logger.error("[RestClient] [PaymentInstructionClient#create] error:", e);
            return null;
        }
    }

    public PaymentInstructionResp update(PaymentInstructionReq req, Long id) {
        logger.info("[RestClient] [PaymentInstructionClient#update] with req: {} and id: {}", req, id);
        try {
            ResponseEntity<PaymentInstructionResp> resp = restTemplate.exchange(
                    buildURIWithId(PAYMENT_INSTRUCTION_BASE_URL, id),
                    HttpMethod.PUT,
                    getHttpEntity(req),
                    PaymentInstructionResp.class
            );
            return resp.getBody();
        } catch (Exception e) {
            logger.error("[RestClient] [PaymentInstructionClient#update] error:", e);
            return null;
        }
    }

    public PaymentInstructionResp getById(Long id) {
        try {
            logger.info("[RestClient] [PaymentInstructionClient#getById] with id: {}", id);
            return restTemplate.getForObject(buildURIWithId(PAYMENT_INSTRUCTION_BASE_URL, id), PaymentInstructionResp.class);
        } catch (Exception e) {
            logger.error("[RestClient] [PaymentInstructionClient#getById] error:", e);
            return null;
        }
    }

    public ResponseEntity<PaymentInstructionResp> getByIdEntity(Long id, HttpStatus expectedStatus) {
        try {
            logger.info("[RestClient] [PaymentInstructionClient#getById] with id: {}", id);
            ResponseEntity<PaymentInstructionResp> entity = restTemplate.getForEntity(buildURIWithId(PAYMENT_INSTRUCTION_BASE_URL, id), PaymentInstructionResp.class);
            assertThat(entity.getStatusCode()).isEqualTo(expectedStatus);
            return entity;
        } catch (Exception e) {
            logger.error("[RestClient] [PaymentInstructionClient#getById] error:", e);
            return null;
        }
    }

    public List<PaymentInstructionResp> search(PaymentInstructionSearch req) {
        try {
            logger.info("[RestClient] [PaymentInstructionClient#search] with req: {}", req);
            PaymentInstructionResp[] restArray = restTemplate.getForObject(buildSearchUrl(PAYMENT_INSTRUCTION_BASE_URL, req), PaymentInstructionResp[].class);
            return restArray == null ? Collections.emptyList() : Arrays.asList(restArray);
        } catch (Exception e) {
            logger.error("[RestClient] [PaymentInstructionClient#search] error:", e);
            return Collections.emptyList();
        }
    }

    public ResponseEntity<Void> delete(Long id) {
        try {
            logger.info("[RestClient] [PaymentInstructionClient#delete] by id: {}", id);
            ResponseEntity<Void> entity = restTemplate.exchange(
                    buildURIWithId(PAYMENT_INSTRUCTION_BASE_URL, id),
                    HttpMethod.DELETE,
                    null,
                    Void.class
            );
            entity.getStatusCode().isSameCodeAs(HttpStatus.NO_CONTENT);
            return entity;
        } catch (Exception e) {
            logger.error("[RestClient] [PaymentInstructionClient#delete] error:", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
