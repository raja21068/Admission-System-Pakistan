package admission.utils;

import java.io.UnsupportedEncodingException;
import java.security.*;
import javax.crypto.*;

public class AltEncrypter {
    
    private Cipher cipherDecrypt;
    private Cipher cipherEncrypt;
    
    /** Creates a new instance of Encrypter */
    public AltEncrypter() {
        try {
            SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
            sr.setSeed(IConstant.DB.PASS_PHRASE.getBytes("UTF8"));
            KeyGenerator kGen = KeyGenerator.getInstance("DESEDE");
            kGen.init(168, sr);
            Key key = kGen.generateKey();

            cipherEncrypt = Cipher.getInstance("DESEDE/ECB/PKCS5Padding");
            cipherEncrypt.init(Cipher.ENCRYPT_MODE, key);
            
            cipherDecrypt = Cipher.getInstance("DESEDE/ECB/PKCS5Padding");
            cipherDecrypt.init(Cipher.DECRYPT_MODE, key);
        } catch (UnsupportedEncodingException | NoSuchPaddingException | NoSuchAlgorithmException | InvalidKeyException e) {
        }
    }
    
    public String encrypt(String str) {
        try {
            return StringUtils.byte2hex(cipherEncrypt.doFinal(str.getBytes("UTF8")));
        } catch (UnsupportedEncodingException | BadPaddingException | IllegalBlockSizeException e) {
        }
        return null;
    }
    
    public String decrypt(String str) {
        try {
            return new String(cipherDecrypt.doFinal(StringUtils.hex2byte(str)), "UTF8");
        } catch (UnsupportedEncodingException | BadPaddingException | IllegalBlockSizeException e) {
        }
        return null;
    }
    
}
