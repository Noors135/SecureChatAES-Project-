package securechataes;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

/**
 * AESManager
 * ------------
 * Utility class responsible for:
 * - Generating AES keys
 * - Encrypting plaintext messages
 * - Decrypting ciphertext messages
 * - Encoding/Decoding AES keys using Base64
 */
public class AESManager {
    // Generates a 128-bit AES Secret Key.
    public static SecretKey generateAESKey() {
        try {
            KeyGenerator keyGen = KeyGenerator.getInstance("AES");
            keyGen.init(128);  // AES-128
            return keyGen.generateKey();
        } catch (Exception e) {
         throw new RuntimeException("Error generating AES key", e);
        }
    }

    // Encrypts a plaintext message using AES encryption.
    public static String encryptAES(String message, SecretKey key) {
        try {
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, key);
            
            byte[] encrypted = cipher.doFinal(message.getBytes());
            return Base64.getEncoder().encodeToString(encrypted);

        } catch (Exception e) {
            throw new RuntimeException("Error encrypting message", e);
        }
    }

    //Decrypts an AES encrypted message.
    public static String decryptAES(String encryptedMessage, SecretKey key) {
        try {
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE, key);

            byte[] decoded = Base64.getDecoder().decode(encryptedMessage);
            byte[] decrypted = cipher.doFinal(decoded);

            return new String(decrypted);

        } catch (Exception e) {
              throw new RuntimeException("Error decrypting message", e);
        }
    }

    // Converts a Base64 encoded string into an AES SecretKey.
    public static SecretKey decodeAESKey(String base64Key) {
        byte[] keyBytes = Base64.getDecoder().decode(base64Key);
        return new SecretKeySpec(keyBytes, "AES");
    }

    // Converts an AES SecretKey into a Base64 encoded string.
    public static String encodeAESKey(SecretKey key) {
        return Base64.getEncoder().encodeToString(key.getEncoded());
    }
}
