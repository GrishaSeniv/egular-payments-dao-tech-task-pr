package technical.task.domain.common;

/**
 * @author Hryhorii Seniv
 * @version 2024-11-04
 */
public class Constants {
    public static final long NOT_EXISTING_ID = -1;
    public static String BASE_URL = "http://localhost:{port}/api/v1/";
    public static String PAYMENT_INSTRUCTION_BASE_URL;
    public static String TRANSACTION_BASE_URL;

    public static void configureBaseUrl(int port) {
        BASE_URL = BASE_URL.replace("{port}", String.valueOf(port));
        PAYMENT_INSTRUCTION_BASE_URL = BASE_URL + "payment-instructions";
        TRANSACTION_BASE_URL = BASE_URL + "transactions";
    }
}
