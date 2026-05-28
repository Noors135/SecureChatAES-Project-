package securechataes;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
/**
 * SecurityLogger
 * --------------
 * Responsible for logging security-related events:
 * - PIN hashes
 * - RSA keys
 * - AES session keys
 * - Encrypted messages and HMAC values
 *
 * Used for auditing and integrity verification.
 */
public class SecurityLogger {

    private static final String LOG_FILE = "security_log_database.txt";

    //Appends a line of text to the security log file
    private static void writeToFile(String text) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(LOG_FILE, true))) {
            bw.write(text);
            bw.newLine();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Returns current timestamp.
    private static String getCurrentTimestamp() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
    }

    // Logs hashed PIN value.
    public static void logPINHash(String username, String pinHash) {
        writeToFile("\n================== PIN HASH ==================");
        writeToFile("User: " + username);
        writeToFile("Hash (SHA-256): " + pinHash);
        writeToFile("Timestamp: " + getCurrentTimestamp());
        writeToFile("===============================================\n");
    }

    // Logs RSA public and private keys (educational purposes only).
    public static void logRSAKeys(String username, String publicKey, String privateKey) {
        writeToFile("\n==================== RSA KEYS ====================");
        writeToFile("User: " + username);
        writeToFile("Public Key:\n" + publicKey);
        writeToFile("\nPrivate Key:\n" + privateKey);
        writeToFile("Timestamp: " + getCurrentTimestamp());
        writeToFile("==================================================\n");
    }

    // Logs AES session key
    public static void logAESKey(String aesKeyBase64) {
        writeToFile("\n==================== AES KEY ======================");
        writeToFile("AES Session Key (Base64): " + aesKeyBase64);
        writeToFile("Generated At: " + getCurrentTimestamp());
        writeToFile("===================================================\n");
    }

    //Logs sent message details including HMAC for integrity checking.
    public static void logMessage(
            String sender, 
            String receiver,
            String plainText,
            String encryptedText,
            String hmacValue) 
    {
        writeToFile("\n=================== MESSAGE LOG ===================");
        writeToFile("From: " + sender + "   -->   To: " + receiver);
        writeToFile("Timestamp: " + getCurrentTimestamp());
        writeToFile("---------------------------------------------------");
        writeToFile("Plain Text:\n" + plainText);
        writeToFile("\nEncrypted (AES):\n" + encryptedText);
        writeToFile("\nHMAC:\n" + hmacValue);
        writeToFile("===================================================\n");
    }
}
