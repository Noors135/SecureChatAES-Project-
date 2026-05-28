package securechataes;

import java.security.MessageDigest;
import java.util.Base64;
import java.nio.charset.StandardCharsets;
/**
 * HashUtil
 * --------
 * Utility class for hashing sensitive data using SHA-256.
 * Used for password/PIN hashing and integrity verification.
 */
public class HashUtil {
//Generates a SHA-256 hash for the given input.
 public static String hash(String input) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = digest.digest(input.getBytes(StandardCharsets.UTF_8));
            return Base64.getEncoder().encodeToString(hashBytes);

        } catch (Exception e) {
            throw new RuntimeException("Error generating hash", e);
        }
    }
}