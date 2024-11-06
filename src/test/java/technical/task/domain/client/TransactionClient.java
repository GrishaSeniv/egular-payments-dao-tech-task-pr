package technical.task.domain.client;

import org.junit.jupiter.api.Assertions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import technical.task.domain.model.transaction.TransactionCreateReq;
import technical.task.domain.model.transaction.TransactionResp;
import technical.task.domain.model.transaction.TransactionSearch;
import technical.task.domain.model.transaction.TransactionUpdateReq;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static technical.task.domain.client.ClientUtils.buildSearchUrl;
import static technical.task.domain.client.ClientUtils.buildURIWithId;
import static technical.task.domain.common.Constants.TRANSACTION_BASE_URL;

/**
 * @author Hryhorii Seniv
 * @version 2024-11-04
 */
@Component
public class TransactionClient {
    private static final Logger logger = LoggerFactory.getLogger(TransactionClient.class);
    private final RestTemplate restTemplate;

    public TransactionClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public TransactionResp create(TransactionCreateReq req) {
        try {
            logger.info("[RestClient] [TransactionClient#create] with req: {}", req);
            return restTemplate.postForObject(TRANSACTION_BASE_URL, req, TransactionResp.class);
        } catch (Exception e) {
            logger.error("[RestClient] [TransactionClient#create] error:", e);
            return null;
        }
    }

    public TransactionResp update(TransactionUpdateReq req, Long id) {
        logger.info("[RestClient] [TransactionClient#update] with req: {} and id: {}", req, id);
        try {
            return restTemplate.patchForObject(buildURIWithId(TRANSACTION_BASE_URL, id), req, TransactionResp.class);
        } catch (Exception e) {
            logger.error("[RestClient] [TransactionClient#update] error:", e);
            return null;
        }
    }

    public TransactionResp getById(Long id) {
        try {
            logger.info("[RestClient] [TransactionClient#getById] with id: {}", id);
            return restTemplate.getForObject(buildURIWithId(TRANSACTION_BASE_URL, id), TransactionResp.class);
        } catch (Exception e) {
            logger.error("[RestClient] [TransactionClient#getById] error:", e);
            return null;
        }
    }

    public ResponseEntity<TransactionResp> getByIdNotFound(Long id) {
        try {
            logger.info("[RestClient] [TransactionClient#getById] with id: {}", id);
            ResponseEntity<TransactionResp> entity = restTemplate.getForEntity(buildURIWithId(TRANSACTION_BASE_URL, id), TransactionResp.class);
            return entity;
        } catch (HttpClientErrorException.NotFound e) {
            logger.error("Transaction with id {} not found.", id, e);
            Assertions.assertEquals(HttpStatus.NOT_FOUND, e.getStatusCode());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            logger.error("[RestClient] [TransactionClient#getById] error:", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    public List<TransactionResp> search(TransactionSearch req) {
        try {
            logger.info("[RestClient] [TransactionClient#search] with req: {}", req);
            TransactionResp[] restArray = restTemplate.getForObject(buildSearchUrl(TRANSACTION_BASE_URL, req), TransactionResp[].class);
            return restArray == null ? Collections.emptyList() : Arrays.asList(restArray);
        } catch (Exception e) {
            logger.error("[RestClient] [TransactionClient#search] error:", e);
            return Collections.emptyList();
        }
    }

    public ResponseEntity<Void> delete(Long id) {
        try {
            logger.info("[RestClient] [TransactionClient#delete] by id: {}", id);
            ResponseEntity<Void> entity = restTemplate.exchange(
                    buildURIWithId(TRANSACTION_BASE_URL, id),
                    HttpMethod.DELETE,
                    null,
                    Void.class
            );
            entity.getStatusCode().isSameCodeAs(HttpStatus.NO_CONTENT);
            return entity;
        } catch (Exception e) {
            logger.error("[RestClient] [TransactionClient#delete] error:", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
