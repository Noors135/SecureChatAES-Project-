package securechataes;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
/**
 * KeyManager
 * ----------
 * Handles RSA key generation, storage, and retrieval.
 * Used for secure key exchange and authentication.
 */
public class KeyManager {

    // Generates a 2048-bit RSA key pair.
    public static KeyPair generateRSAKeyPair() {
   try {
            KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
            keyGen.initialize(2048);
            return keyGen.generateKeyPair();
        } catch (Exception e) {
            throw new RuntimeException("Error generating RSA key pair", e);
        }
    }

    // Saves the RSA public key to a file.
    public static void savePublicKey(String username, PublicKey key) {
         try (FileWriter fw = new FileWriter(username + "_public.key")) {
            fw.write(Base64.getEncoder().encodeToString(key.getEncoded()));
        } catch (Exception e) {
            throw new RuntimeException("Error saving public key", e);
        }
    }

    // Saves the RSA private key to a file.
    public static void savePrivateKey(String username, PrivateKey key) {
           try (FileWriter fw = new FileWriter(username + "_private.key")) {
            fw.write(Base64.getEncoder().encodeToString(key.getEncoded()));
        } catch (Exception e) {
            throw new RuntimeException("Error saving private key", e);
        }
    }

    //  Loads an RSA public key from file.
    public static PublicKey loadPublicKey(String username) {
      try (BufferedReader br = new BufferedReader(
                new FileReader(username + "_public.key"))) {

            String keyBase64 = br.readLine();
            byte[] keyBytes = Base64.getDecoder().decode(keyBase64);

            X509EncodedKeySpec spec = new X509EncodedKeySpec(keyBytes);
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");

            return keyFactory.generatePublic(spec);

        } catch (Exception e) {
            return null;
        }
    }

    // Loads an RSA private key from file.
    public static PrivateKey loadPrivateKey(String username) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(username + "_private.key"));
            String keyBase64 = br.readLine();
            byte[] keyBytes = Base64.getDecoder().decode(keyBase64);

            PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(keyBytes);
            KeyFactory kf = KeyFactory.getInstance("RSA");

            return kf.generatePrivate(spec);

        } catch (Exception e) {
            return null;
        }
    }
}
