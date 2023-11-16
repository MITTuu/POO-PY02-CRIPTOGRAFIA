package modelo;

//Imports especificos de funcionalidad

//Import generales del proyecto

/**
 * Esta es una abstraccion de una clase abstracta de cifrado moderno, la cual 
 * implementa metodos abstractos los cuales seran sera implementados por las 
 * clases hijas para cifrar datos.
 * 
 * @author Eduardo Rojas Gomez y Dylan Montiel Zuñiga.
 * 
 * @version 15/11/2023
 */
public abstract class CifradoModerno {
  public String tipoCifrado;
  
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
   * @param pTipoCifrado: El tipo de cifrado.
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
   * @return 
   */
  public abstract String cifrado(String pCadena);
 
  /**
   * Este metodo abstracto sera permite que las clases hijas implementen
   * una forma de descifrar los datos. 
   * @param pCadena: Una cadena String la cual sera descifrada.
   * @return 
   */
  public abstract String descifrado(String pCadena); 
  
}
