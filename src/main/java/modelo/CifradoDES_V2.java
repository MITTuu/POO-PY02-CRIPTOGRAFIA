package modelo;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import com.sun.mail.util.BASE64DecoderStream;
import com.sun.mail.util.BASE64EncoderStream;
import java.security.spec.KeySpec;

public class CifradoDES_V2 {

  private String contrasena = "contrase";// No muevan esta madre o se jode todo.
  private Cipher encriptador;
  private Cipher desencriptador;
  private SecretKey llave;

  public CifradoDES_V2() {
    }

  public String cifrado(String pCadena) {
    setLlave();
    try {
      if (pCadena != null) {
        
        byte[] formato = pCadena.getBytes("UTF8");// Medimos el largo

        byte[] resultado = encriptador.doFinal(formato);

        resultado = BASE64EncoderStream.encode(resultado);
        
        String finalizado  = new String(resultado);
       
        return finalizado;//new String(resultado);
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
        // Decodificar base64 para obtener bytes
        byte[] decodificar = BASE64DecoderStream.decode(pCadena.getBytes());

        // Descifrar
        byte[] formato = desencriptador.doFinal(decodificar);
        
        String resultado = new String(formato, "UTF8");
        // Crear nueva cadena basada en utf-8
        return resultado; //new String(formato, "UTF8");
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

  public void setLlave() {
    try {
      // Generar clave DES directamente
      KeySpec spec = new DESKeySpec(contrasena.getBytes());
      SecretKeyFactory factory = SecretKeyFactory.getInstance("DES");
      this.llave = factory.generateSecret(spec);

      // Inicializar Cipher para cifrado y descifrado
      encriptador = Cipher.getInstance("DES/ECB/PKCS5Padding");// Esta dos lineas ni las vuelvan a ver
      desencriptador = Cipher.getInstance("DES/ECB/PKCS5Padding");// Menos esas cadenas de caracteres

      encriptador.init(Cipher.ENCRYPT_MODE, this.llave);
      desencriptador.init(Cipher.DECRYPT_MODE, this.llave);

    } catch (Exception e) {
      e.printStackTrace();
    }
  }
  
}
