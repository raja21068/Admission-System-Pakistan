package admission.utils;

//package utilities;
//
//import java.io.IOException;
//import java.security.InvalidKeyException;
//import java.security.Key;
//import java.security.NoSuchAlgorithmException;
//import javax.crypto.BadPaddingException;
//import javax.crypto.Cipher;
//import javax.crypto.IllegalBlockSizeException;
//import javax.crypto.NoSuchPaddingException;
//import javax.crypto.spec.SecretKeySpec;
//import sun.misc.BASE64Decoder;
//import sun.misc.BASE64Encoder;
//
///**
// *
// * @author Raja Kumar & Jay Kumar
// */
//public class Cryptography {
//    private static final String ALGO = "AES";
//    private static final byte[] keyValue = new byte[] { 'T', 'h', 'e', 'B', 'e', 's', 't', 'S', 'e', 'c', 'r','e', 't', 'K', 'e', 'y' };
//
//    public static String encrypt(String Data) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException{
//        Key key = generateKey();
//        Cipher c = Cipher.getInstance(ALGO);
//        c.init(Cipher.ENCRYPT_MODE, key);
//        byte[] encVal = c.doFinal(Data.getBytes());
//        String encryptedValue = new BASE64Encoder().encode(encVal);
//        
//        return encryptedValue;
//    }
//
//    public static String decrypt(String encryptedData)  throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, IOException{
//        Key key = generateKey();
//        Cipher c = Cipher.getInstance(ALGO);
//        c.init(Cipher.DECRYPT_MODE, key);
//        byte[] decordedValue = new BASE64Decoder().decodeBuffer(encryptedData);
//        byte[] decValue = c.doFinal(decordedValue);
//        String decryptedValue = new String(decValue);
//        
//        return decryptedValue;
//    }
//    private static Key generateKey() {
//        Key key = new SecretKeySpec(keyValue, ALGO);
//        return key;
//    }
//    
////    public static void main(String arg[]) throws Exception{
////        String password = "khatri";
////        String passwordEnc = encrypt(password);
////        String passwordDec = decrypt(passwordEnc);
////        
////        System.out.println("Plain Text : " + password);
////        System.out.println("Encrypted Text : " + passwordEnc);
////        System.out.println("Decrypted Text : " + passwordDec);
////    }
//}
