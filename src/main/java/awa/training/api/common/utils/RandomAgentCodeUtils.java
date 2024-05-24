package awa.training.api.common.utils;
import java.security.SecureRandom;

public class RandomAgentCodeUtils {

    public static String randomNumberGenerator() {
        String NUMBERS = "0123456789";
        SecureRandom SECURE_RANDOM = new SecureRandom();
        StringBuilder stringBuilder = new StringBuilder(5);
        for (int i = 0; i < 5; i++) {
            int randomIndex = SECURE_RANDOM.nextInt(NUMBERS.length());
            stringBuilder.append(NUMBERS.charAt(randomIndex));
        }
        return stringBuilder.toString();
    }

}
