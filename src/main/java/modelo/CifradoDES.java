package modelo;

//Imports especificos de funcionalidad

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import com.sun.mail.util.BASE64DecoderStream;
import com.sun.mail.util.BASE64EncoderStream;
//Import generales del proyecto
/**
 *
 * @author Eduardo Rojas Gomez y Dylan Montiel Zuñiga.
 * 
 * @version 12/11/2023
 */
public class CifradoDES {
  
  private static Cipher ecipher;
  private static Cipher dcipher;
  private static SecretKey key; 
  
  private static String contrasena = "";
  
  public CifradoDES() {
  }
  
  public String cifrado1(String pCadena) {
  
    return null;
  }

  public static void main(String[] args) throws InvalidKeyException {
    try {
  
      key = KeyGenerator.getInstance("DES").generateKey();
      ecipher = Cipher.getInstance("DES");
      dcipher = Cipher.getInstance("DES");

      // Inicializar los cifrados con la clave dada
      ecipher.init(Cipher.ENCRYPT_MODE, key);
      dcipher.init(Cipher.DECRYPT_MODE, key);

      String encrypted = encrypt("Quiero dormir.");
            
      System.out.println("Encrypted: " + encrypted);
            
      String decrypted = decrypt(encrypted);

      System.out.println("Decrypted: " + decrypted);
      
    } catch (NoSuchAlgorithmException e) {
      System.out.println("No Such Algorithm:" + e.getMessage());
      return;
    } catch (NoSuchPaddingException e) {
      System.out.println("No Such Padding:" + e.getMessage());
    }
  }  
public static String encrypt(String str) {
    try {
        // Codificar la cadena a bytes usando utf-8
        byte[] utf8 = str.getBytes("UTF8");

        // Cifrar
        byte[] enc = ecipher.doFinal(utf8);

        // Codificar bytes a base64 para obtener cadena
        enc = BASE64EncoderStream.encode(enc);

        return new String(enc);
    } catch (Exception e) {
        e.printStackTrace();
    }
    return null;
}

public static String decrypt(String str) {
    try {
        // Decodificar base64 para obtener bytes
        byte[] dec = BASE64DecoderStream.decode(str.getBytes());

        // Descifrar
        byte[] utf8 = dcipher.doFinal(dec);

        // Crear nueva cadena basada en utf-8
        return new String(utf8, "UTF8");
    } catch (Exception e) {
        e.printStackTrace();
    }
    return null;
}
   
   
}
