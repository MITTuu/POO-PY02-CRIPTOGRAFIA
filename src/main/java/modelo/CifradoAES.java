package modelo;

//Imports especificos de funcionalidad

import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

//Import generales del proyecto
/**
 *
 * @author Eduardo Rojas Gomez y Dylan Montiel Zuñiga.
 * 
 * @version 12/11/2023
 */
public class CifradoAES {
  private Cipher cipher;// El cifrado AES solo ocupa un objeto para realizar ambas funciones.
  private SecretKey llave;
  
  private String contrasena = "contrasena123456";// No muevan esta madre o se jode todo.
  
  
  public CifradoAES() {
  }

    public String cifrado(String pCadena) {
        setLlave();
        try {
            if (pCadena != null) {
                byte[] utf8 = pCadena.getBytes("UTF8");
                cipher.init(Cipher.ENCRYPT_MODE, llave);
                byte[] encryptedByte = cipher.doFinal(utf8);
                Base64.Encoder encoder = Base64.getEncoder();
                String encryptedText = encoder.encodeToString(encryptedByte);
                return encryptedText;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public String descifrado(String pCadena) {
        setLlave();
        try {
            if (pCadena != null) {
                Base64.Decoder decoder = Base64.getDecoder();
                byte[] encryptedTextByte = decoder.decode(pCadena);
                cipher.init(Cipher.DECRYPT_MODE, llave);
                byte[] decryptedByte = cipher.doFinal(encryptedTextByte);
                String decryptedText = new String(decryptedByte, "UTF8");
                return decryptedText;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private void setLlave() {
        try {
            // Utilizar la contraseña fija para generar la clave AES
            byte[] keyBytes = contrasena.getBytes();
            llave = new SecretKeySpec(keyBytes, "AES");
            cipher = Cipher.getInstance("AES");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }  
  
  
  
  
  
  
  
  
}
