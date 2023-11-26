/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 * Clase que implementa un cifrado de palabra inversa, donde cada palabra
 * en el mensaje se invierte en orden.
 *
 * @author Eduardo Rojas Gomez
 * @author Dylan Montiel Zuniga
 * @version 1.0
 */
public class PalabraInversa extends CifradoClasico {

  /**
   * Constructor que inicializa el cifrado de palabra inversa sin utilizar una clave.
   */  
  public PalabraInversa() {
    super(null);
  }

  /**
   * Cifra un mensaje invirtiendo el orden de las palabras.
   *
   * @param msj El mensaje a cifrar.
   * @return El mensaje cifrado.
   */  
  @Override
  public String cifrar(String msj) {
    return palabraInversa(msj);
  }

  /**
   * Descifra un mensaje invirtiendo el orden de las palabras.
   *
   * @param msj El mensaje a descifrar.
   * @return El mensaje descifrado.
   */  
  @Override
  public String descifrar(String msj) {
    return palabraInversa(msj);
  }

  /**
   * Invierte el orden de las palabras en un mensaje.
   *
   * @param msj El mensaje a procesar.
   * @return El mensaje con las palabras invertidas.
   */  
  private String palabraInversa(String msj) {
    StringBuilder msjInvertido = new StringBuilder();
    String[] lineas = msj.split("\n");
    
    for (String linea : lineas) {
      String[] palabras = linea.split("\s+");
      for (String palabra : palabras) {
        String palabraInvertida = new StringBuilder(palabra).reverse().toString();
        msjInvertido.append(palabraInvertida).append(" ");
      }
      msjInvertido.append("\n");      
    }    
    return msjInvertido.toString().trim();
  }
}
