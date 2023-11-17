package modelo;

//Imports especificos de funcionalidad

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import com.sun.mail.util.BASE64DecoderStream;
import com.sun.mail.util.BASE64EncoderStream;
import java.security.spec.KeySpec;
//Import generales del proyecto
/**
 *  Esta clase una abstraccion de un metodo de cifrado DES.
 * 
 * @author Eduardo Rojas Gomez y Dylan Montiel Zuñiga.
 * 
 * @version 12/11/2023
 */
public class CifradoTripleDES extends CifradoModerno{

  private String contrasena = "contrasena123456789012345678";// No muevan esta madre o se jode todo.
  private Cipher encriptador;
  private Cipher desencriptador;
  private SecretKey llave;
  
  /**
   * Este es el constructor de la clase CifradoRSA.
   */
  public CifradoTripleDES() {
    super("Cifrado Triple DES");
    setContrasena();
  }


  /**
   * Este metodo permite establecer una llave con la cual se realizara el cifrado
   * y descifrado de datos.
   */    
  public void setContrasena(){
    try {
      //System.out.println(contrasena.getBytes());
      
      // Generar clave DESede directamente
      DESedeKeySpec spec = new DESedeKeySpec(contrasena.getBytes());
      SecretKeyFactory factory = SecretKeyFactory.getInstance("DESede");
      this.llave = factory.generateSecret(spec);

      // Inicializar Cipher para cifrado y descifrado
      encriptador = Cipher.getInstance("DESede/ECB/PKCS5Padding");// Lo mismo, no muevan
      desencriptador = Cipher.getInstance("DESede/ECB/PKCS5Padding");// Tampoco

      encriptador.init(Cipher.ENCRYPT_MODE, this.llave);
      desencriptador.init(Cipher.DECRYPT_MODE, this.llave);
      
    } catch (Exception e) {
      e.printStackTrace();
    }  
    
  }  


  /**
   * Este metodo permite encriptar una cadena de Strings mediante el algoritmo Triple DES.
   * 
   * @param pCadena una cadena de caracteres la cual sera cifrada.
   * @return Una cadena cifrado mediante el algoritmo RSA.
   */  
  public String cifrado(String pCadena){
    try {
      if (pCadena != null) {
        // Codificar la cadena a bytes usando utf-8
        byte[] formato = pCadena.getBytes("UTF8");

        // Cifrar
        byte[] codificado = encriptador.doFinal(formato);

        // Codificar bytes a base64 para obtener cadena
        codificado = BASE64EncoderStream.encode(codificado);
        
        String resultado = new String(codificado);

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
   * @return Una cadena descifrado mediante el algoritmo Triple DES.
   */  
  public String descifrado(String pCadena){

    try {
      if (pCadena != null) {
        // Decodificar base64 para obtener bytes
        byte[] desincriptado = BASE64DecoderStream.decode(pCadena.getBytes());

        // Descifrar
        byte[] formato = desencriptador.doFinal(desincriptado);
        String resultado = new String(formato, "UTF8");

        // Crear nueva cadena basada en utf-8
        return resultado;
      }
    } catch (Exception e) {
      e.printStackTrace();
    }    
    return null;
    
  }  
}