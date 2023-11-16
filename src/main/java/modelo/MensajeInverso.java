/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author Dylan Montiel Zúñiga
 * @version 1.0
 */
public class MensajeInverso extends CifradoClasico {

  public MensajeInverso() {
    super(null);
  }

  @Override
  public String cifrar(String msj) {
    return mensajeInverso(msj);
  }

  @Override
  public String descifrar(String msj) {
    return mensajeInverso(msj);
  }

  /**
   * Cifra el mensaje invirtiendo completamente la frase.
   *
   * @param msj El mensaje a cifrar.
   * @return El mensaje cifrado.
   */
  private String mensajeInverso(String msj) {
    return new StringBuilder(msj).reverse().toString();
  }  
  
}
