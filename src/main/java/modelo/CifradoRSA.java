package modelo;

//Imports especificos de funcionalidad
import java.security.*;
import javax.crypto.Cipher;
import java.util.Base64;
import java.io.*;
//Import generales del proyecto

/**
 *
 * @author Eduardo Rojas Gomez y Dylan Montiel Zuñiga.
 * 
 * @version 12/11/2023
 */
public class CifradoRSA {
  //private PrivateKey clavePrivada;
  //private PublicKey clavePublica;  
  private PrivateKey privateKey;
  private PublicKey publicKey;
  
  private String contrasena = "contrasena";
  
  
  public CifradoRSA() {
    setLlaves();
    //guardarLlaves();
  }
  
  public void setLlaves() {
    
    try {
      //setLlaves();
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(2048);
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        this.privateKey = keyPair.getPrivate();
        this.publicKey = keyPair.getPublic();      
    
    } catch (Exception e) {
      e.printStackTrace();
    }
    
  }
  
  public String cifrado(String pCadena) {
  
    try {
      //setLlaves();
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cipher.init(Cipher.ENCRYPT_MODE, this.publicKey);
        return Base64.getEncoder().encodeToString(cipher.doFinal(pCadena.getBytes()));
      
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }
  
  public String descifrado(String pCadena) {
    try {
      //setLlaves();
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cipher.init(Cipher.DECRYPT_MODE, this.privateKey);
        return new String(cipher.doFinal(Base64.getDecoder().decode(pCadena)));
      
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;  
  
  
  
  
  }
  
  
  public void guardarLlaves() {
  
    try {
      // Comprobar si las claves ya existen
      File archivoPrivado = new File("private.key");
      File archivoPublico = new File("public.key");

      if (archivoPrivado.exists() && archivoPublico.exists()) {
        // Leer las claves del archivo
        ObjectInputStream privateKeyStream = new ObjectInputStream(new FileInputStream(archivoPrivado));
        this.privateKey = (PrivateKey) privateKeyStream.readObject();
        privateKeyStream.close();

        ObjectInputStream publicKeyStream = new ObjectInputStream(new FileInputStream(archivoPublico));
        this.publicKey = (PublicKey) publicKeyStream.readObject();
        publicKeyStream.close();
        
      } else {
        
        // Generar nuevas claves
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
        secureRandom.setSeed(contrasena.getBytes());
        keyPairGenerator.initialize(2048, secureRandom);
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        this.privateKey = keyPair.getPrivate();
        this.publicKey = keyPair.getPublic();

        // Guardar las claves en un archivo
        ObjectOutputStream privateKeyStream = new ObjectOutputStream(new FileOutputStream(archivoPrivado));
        privateKeyStream.writeObject(this.privateKey);
        privateKeyStream.close();

        ObjectOutputStream publicKeyStream = new ObjectOutputStream(new FileOutputStream(archivoPublico));
        publicKeyStream.writeObject(this.publicKey);
        publicKeyStream.close();
      }
    } catch (Exception e) {
      e.printStackTrace();
    }  
  }  
  
}
