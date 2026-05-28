package securechataes;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
/**
 * AuthManager
 * -----------
 * Handles authentication-related operations:
 * - Storing hashed PIN values
 * - Loading hashed PIN values
 *
 * Hash computation is delegated to HashUtil.
 */
public class AuthManager {
// Saves hashed PIN to file.
    public static void savePinHash(String username, String pinHash) {
        try (FileWriter fw = new FileWriter(username + "_pin.txt")) {
            fw.write(pinHash);
        } catch (Exception e) {
            throw new RuntimeException("Error saving PIN hash", e);
        }
    }
    
     // Loads hashed PIN from file.
    public static String loadPinHash(String username) {
        try (BufferedReader br = new BufferedReader(
                new FileReader(username + "_pin.txt"))) {

            return br.readLine();

        } catch (Exception e) {
            return null;
        }
    }
}

