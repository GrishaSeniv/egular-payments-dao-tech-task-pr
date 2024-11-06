package technical.task;

import jakarta.annotation.PostConstruct;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import technical.task.scenario.CrudScenario;

import static technical.task.domain.common.Constants.configureBaseUrl;

/**
 * @author Hryhorii Seniv
 * @version 2024-11-04
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RegularPaymentsDaoAppTest {
    private static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>(
            "postgres:latest"
    ).withDatabaseName("regular-payments-test-db")
            .withUsername("postgres")
            .withPassword("root");
    @Autowired
    private CrudScenario crudScenario;
    @LocalServerPort
    private int port;

    @BeforeAll
    static void beforeAll() {
        postgres.start();
    }

    @AfterAll
    static void afterAll() {
        postgres.stop();
    }

    @DynamicPropertySource
    static void configureProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgres::getJdbcUrl);
        registry.add("spring.datasource.username", postgres::getUsername);
        registry.add("spring.datasource.password", postgres::getPassword);
    }

    @PostConstruct
    public void init() {
        configureBaseUrl(port);
    }

    @Test
    void crudScenario() {
        crudScenario.crudScenario();
    }
}
