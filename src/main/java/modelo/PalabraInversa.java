/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author dylan
 */
public class PalabraInversa extends CifradoClasico {

  public PalabraInversa() {
    super("");
  }

  @Override
  public String cifrar(String msj) {
    return palabraInversa(msj);
  }

  @Override
  public String descifrar(String msj) {
    return palabraInversa(msj);
  }

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
