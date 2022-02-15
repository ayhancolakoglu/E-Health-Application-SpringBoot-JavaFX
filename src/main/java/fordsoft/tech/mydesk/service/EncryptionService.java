package fordsoft.tech.mydesk.service;

import fordsoft.tech.mydesk.FxApplication;
import org.springframework.stereotype.Component;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;


/**
 * @author Bayram Ayhan Colakoglu
 */


@Component
public class EncryptionService {


    String password = FxApplication.currentuser.getPassword();


    /**
     * @param encryptionType
     * @return a generated key
     */
    public static SecretKey generateKey(String encryptionType) {

        try {
            KeyGenerator keyGenerator = KeyGenerator.getInstance(encryptionType);
            SecretKey myKey = keyGenerator.generateKey();
            return myKey;
        } catch (Exception e) {
            return null;
        }
    }


    /**
     * @param datatoEncrypt
     * @param myKey
     * @param cipher
     * @return encrypted String
     */
    public static byte[] encryptPassword(String datatoEncrypt, SecretKey myKey, Cipher cipher) {

        try {
            byte[] password = datatoEncrypt.getBytes(StandardCharsets.UTF_8);
            cipher.init(Cipher.ENCRYPT_MODE, myKey);
            return cipher.doFinal(password);
        } catch (Exception e) {
            return null;
        }

    }


    /**
     * @param dataToDecrypt
     * @param myKey
     * @param cipher
     * @return decrypted string
     */
    public static String decryptPassword(byte[] dataToDecrypt, SecretKey myKey, Cipher cipher) {

        try {
            cipher.init(Cipher.DECRYPT_MODE, myKey);
            byte[] passwordDecrypted = cipher.doFinal(dataToDecrypt);
            String result = new String(passwordDecrypted);
            return result;
        } catch (Exception e) {
            return null;
        }
    }
}
