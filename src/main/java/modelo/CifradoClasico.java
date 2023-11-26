package modelo;

/**
 * Clase abstracta que representa un cifrado clásico.
 * Proporciona métodos para cifrar y descifrar mensajes utilizando una clave.
 *
 * @author Eduardo Rojas Gomez
 * @author Dylan Montiel Zuniga
 * @version 1.0
 */
public abstract class CifradoClasico {
  private String clave;

  /**
   * Constructor que inicializa la clave del cifrado.
   *
   * @param pClave La clave a utilizar para el cifrado.
   */  
  public CifradoClasico(String pClave) {
    setClave(pClave);
  }

  // Métodos accesores
  /**
   * Obtiene la clave actual del cifrado.
   *
   * @return La clave actual del cifrado.
   */
  public String getClave() {
    return clave;
  }

  /**
   * Establece la clave del cifrado.
   *
   * @param pClave La nueva clave a establecer.
   */
  public void setClave(String pClave) {
    this.clave = pClave;
  }  

  /**
   * Método abstracto para cifrar un mensaje.
   *
   * @param msj El mensaje a cifrar.
   * @return El mensaje cifrado.
   */  
  public abstract String cifrar(String msj);

  /**
   * Método abstracto para descifrar un mensaje.
   *
   * @param msj El mensaje a descifrar.
   * @return El mensaje descifrado.
   */  
  public abstract String descifrar(String msj);
}
