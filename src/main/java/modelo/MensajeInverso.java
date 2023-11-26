package modelo;

/**
 * Clase que implementa un cifrado de mensaje inverso, donde cada caracter
 * del mensaje se invierte en orden.
 *
 * @author Eduardo Rojas Gomez
 * @author Dylan Montiel Zuniga
 * @version 1.0
 */
public class MensajeInverso extends CifradoClasico {

  /**
   * Constructor que inicializa el cifrado de mensaje inverso sin utilizar una clave.
   */  
  public MensajeInverso() {
    super(null);
  }

  /**
   * Cifra un mensaje invirtiendo completamente la frase.
   *
   * @param msj El mensaje a cifrar.
   * @return El mensaje cifrado.
   */  
  @Override
  public String cifrar(String msj) {
    return mensajeInverso(msj);
  }

  /**
   * Descifra un mensaje invirtiendo completamente la frase.
   *
   * @param msj El mensaje a descifrar.
   * @return El mensaje descifrado.
   */  
  @Override
  public String descifrar(String msj) {
    return mensajeInverso(msj);
  }


  /**
   * Cifra o descifra un mensaje invirtiendo completamente la frase.
   *
   * @param msj El mensaje a procesar.
   * @return El mensaje procesado (invertido).
   */
  private String mensajeInverso(String msj) {
    return new StringBuilder(msj).reverse().toString();
  }    
}
