package modelo;

import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/**
 * Esta clase una abstraccion de un metodo de cifrado AES. 
 * 
 * @author Eduardo Rojas Gomez, Dylan Montiel Zuniga
 * @version 2.0
 */
public class CifradoAES extends CifradoModerno{
  
  private Cipher cifrador;// El cifrado AES solo ocupa un objeto para realizar ambas funciones.
  private SecretKey llave;  
  private String contrasena = "contrasena123456";// No muevan esta madre o se jode todo.
 
  /**
   * Este metodo es el constructor de la clase CifradoAES.
   */
  public CifradoAES() {
    super ("Cifrado AES");
  }

  /**
   * Este metodo permite establecer una llave con la cual se realizara el cifrado
   * y descifrado de datos.
   */
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
 
  /**
   * Este metodo permite encriptar una cadena de Strings mediante el algoritmo AES.
   * 
   * @param pCadena una cadena de caracteres la cual sera cifrada.
   * @return Una cadena cifrada mediante el algoritmo RSA.
   */  
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

  /**
   * Este metodo permite descifrar una cadena de Strings mediante el algoritmo AES.
   * 
   * @param pCadena una cadena de caracteres.
   * @return Una cadena descifrado mediante el algoritmo RSA.
   */
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
}
