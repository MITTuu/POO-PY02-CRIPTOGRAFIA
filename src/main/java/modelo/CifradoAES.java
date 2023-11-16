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
public class CifradoAES extends CifradoModerno{
  
  private Cipher cifrador;// El cifrado AES solo ocupa un objeto para realizar ambas funciones.
  private SecretKey llave;  
  private String contrasena = "contrasena123456";// No muevan esta madre o se jode todo.
  
  public CifradoAES() {
    super ("Cifrado AES");
  }

  public String cifrado(String pCadena) {
    setLlave();
    try {
      if (pCadena != null) {
        cifrador.init(Cipher.ENCRYPT_MODE, llave);
        
        byte[] formato = pCadena.getBytes("UTF8");
        
        byte[] codificado = cifrador.doFinal(formato);
        
        Base64.Encoder encoder = Base64.getEncoder();
        String resultado = encoder.encodeToString(codificado);
        return resultado;
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
        
        cifrador.init(Cipher.DECRYPT_MODE, llave);
        Base64.Decoder decoder = Base64.getDecoder();
        byte[] texto = decoder.decode(pCadena);
        
        byte[] descifrado = cifrador.doFinal(texto);
        String resultado = new String(descifrado, "UTF8");
        return resultado;
      }
    } catch (Exception e) {
    e.printStackTrace();
    }
    return null;
  }

  private void setLlave() {
    try {
     // Utilizar la contraseña fija para generar la clave AES
      byte[] tamano = contrasena.getBytes();
      llave = new SecretKeySpec(tamano, "AES");
      cifrador = Cipher.getInstance("AES");
      
    } catch (Exception e) {
      
      e.printStackTrace();
    }
  }  
  
  
}
