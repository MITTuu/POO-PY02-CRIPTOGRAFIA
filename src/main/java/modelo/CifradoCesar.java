/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author Dylan Montiel Zúñiga
 * @version 2.0
 */
public class CifradoCesar extends CifradoClasico {
  private static final int desplazamiento = 3;
  
  @Override
  public String cifrar(String msj) {
    StringBuilder msjCifrado = new StringBuilder();
    
    for (char caracter : msj.toCharArray()) {
      if (Character.isLetter(caracter)) {
        char base = Character.isUpperCase(caracter) ? 'A' : 'a';
        char cifrado = (char) ((caracter - base + desplazamiento) % 26 + base);
        msjCifrado.append(cifrado);
      } else {
        msjCifrado.append(caracter);
      }
    }
    return msjCifrado.toString();
  }

  @Override
  public String descifrar(String msj) {
    StringBuilder msjDescifrado = new StringBuilder();
    
    for (char caracter : msj.toCharArray()) {
      if (Character.isLetter(caracter)) {
        char base = Character.isUpperCase(caracter) ? 'A' : 'a';
        char descifrado = (char) ((caracter - base - desplazamiento + 26) % 26 + base);
        msjDescifrado.append(descifrado);
      } else {
        msjDescifrado.append(caracter);
      }
    }
    return msjDescifrado.toString();
  }
}
