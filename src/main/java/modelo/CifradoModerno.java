package modelo;

/**
 * Esta es una abstraccion de una clase abstracta de cifrado moderno, la cual 
 * implementa metodos abstractos los cuales seran sera implementados por las 
 * clases hijas para cifrar datos.
 * 
 * @author Eduardo Rojas Gomez
 * @author Dylan Montiel Zuniga
 * @version 2.0
 */
public abstract class CifradoModerno {
  
  /**
   * Tipo de cifrado
   */
  public String tipoCifrado;  
  /**
   * Contador de cifrados
   */  
  public static int contadorCifrados;
  
  /**
   * Este es el contructor de la clase asbtracta CifradoModeno.
   * 
   * @param pTipoCifrado El tipo de cifrado.
   */
  public CifradoModerno(String pTipoCifrado) {
    
    setTipoCifrado(pTipoCifrado);
    
    setContadorCifrados(1);
  }

  /**
   * Este metodo permite obtener el tipo de cifrado a realizar.
   * 
   * @return El tipo de cifrado.
   */
  public String getTipoCifrado() {
    return tipoCifrado;
  }
  
  /**
   * Este metodo permite establecer el tipo de cifrado que se realizara.
   * 
   * @param pTipoCifrado El tipo de cifrado.
   */
  public void setTipoCifrado(String pTipoCifrado) {
    this.tipoCifrado = pTipoCifrado;
  }

  /**
   * Este metodo permite obtener la cantidad de instacias de cifrado moderno creadas.
   * 
   * @return La cantidad de instancias de cifrado creadas.
   */
  public static int getContadorCifrados() {
    return contadorCifrados;
  }

  /**
   * Este metodo permite aumentar el contador de la cantidad instancias de cifrado 
   * creadas.
   * 
   * @param pContadorCifrados Una nueva instancia de cifrados creada.
   */
  public static void setContadorCifrados(int pContadorCifrados) {
    CifradoModerno.contadorCifrados += pContadorCifrados;
  }
  
  /**
   * Este metodo abstracto sera permite que las clases hijas implementen
   * una forma de cifrar los datos. 
   * 
   * @param pCadena: Una cadena String la cual sera cifrada.
   * @return El mensaje cifrado
   */
  public abstract String cifrado(String pCadena);
 
  /**
   * Este metodo abstracto sera permite que las clases hijas implementen
   * una forma de descifrar los datos. 
   * @param pCadena: Una cadena String la cual sera descifrada.
   * @return El mensaje descifrado
   */
  public abstract String descifrado(String pCadena); 
   
  /**
   * Este metodo retorna los datos actuales de esta instancia.
   * 
   * @return Los datos de esta clase.
   */
  public String toString() {
    String msg = "";
    
    msg = "Tipo de cifrado" + getTipoCifrado() + "\n";
    msg += "Total de cifrados creados" + getContadorCifrados() + "\n";
    return msg;
  }  
}
