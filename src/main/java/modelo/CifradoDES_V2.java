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
        // Codificar la cadena a bytes usando utf-8
        byte[] utf8 = pCadena.getBytes("UTF8");

        // Cifrar
        byte[] enc = encriptador.doFinal(utf8);

        // Codificar bytes a base64 para obtener cadena
        enc = BASE64EncoderStream.encode(enc);
        
        //byte[] resultado = enc;

        return new String(enc);
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
        byte[] dec = BASE64DecoderStream.decode(pCadena.getBytes());

        // Descifrar
        byte[] utf8 = desencriptador.doFinal(dec);

        // Crear nueva cadena basada en utf-8
        return new String(utf8, "UTF8");
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
