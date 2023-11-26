/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 * Clase que implementa un cifrado de sustitución Vigenère, donde cada letra
 * del mensaje se cifra o descifra utilizando una clave específica.
 *
 * @author Dylan Montiel Zúñiga
 * @version 1.0
 */
public class SustitucionVigenere extends CifradoClasico {
  private String clave;

  /**
   * Constructor que inicializa el cifrado de sustitución Vigenère con una clave dada.
   *
   * @param pClave La clave a utilizar para el cifrado.
   */  
  public SustitucionVigenere(String pClave) {
    super(pClave); 
    clave = super.getClave();
  }

  /**
   * Cifra un mensaje utilizando el cifrado de sustitución Vigenère.
   *
   * @param msj El mensaje a cifrar.
   * @return El mensaje cifrado.
   */  
  @Override
  public String cifrar(String msj) {
    StringBuilder msjCifrado = new StringBuilder();
    String[] lineas = msj.split("\n");
    
    for (String linea : lineas) {
      String[] palabras = linea.split("\\s+"); // Dividir el texto en palabras

      for (String palabra : palabras) {
        for (int i = 0; i < palabra.length(); i++) {
          char caracter = palabra.charAt(i);

          if (Character.isLetter(caracter)) {
            int valorOriginal = Character.toLowerCase(caracter) - 'a' + 1;
            int valorClave = Integer.parseInt(clave.substring(i % 2, i % 2 + 1));

            int valorCifrado = (valorOriginal + valorClave - 1) % 26 + 1;

            // Ajustar en caso de que el resultado sea menor que 1
            if (valorCifrado < 1) {
              valorCifrado += 26;
            }            

            char caracterCifrado = (char) (valorCifrado + 'a' - 1);

            msjCifrado.append(caracterCifrado);
          } else {
            msjCifrado.append(caracter);
          }
        }
        msjCifrado.append(" "); // Agregar espacio entre palabras
      }
      msjCifrado.append("\n");
    }
    return msjCifrado.toString().trim();
  }


  /**
   * Descifra un mensaje cifrado utilizando el cifrado de sustitución Vigenère.
   *
   * @param msj El mensaje cifrado a descifrar.
   * @return El mensaje descifrado.
   */  
  @Override
  public String descifrar(String msj) {
    StringBuilder msjDescifrado = new StringBuilder();
    String[] lineas = msj.split("\n");

    for (String linea : lineas) {
      String[] palabras = linea.split("\\s+"); // Dividir el texto en palabras

      for (String palabra : palabras) {
        for (int i = 0; i < palabra.length(); i++) {
          char caracter = palabra.charAt(i);

          if (Character.isLetter(caracter)) {
            int valorCifrado = Character.toLowerCase(caracter) - 'a' + 1;
            int valorClave = Integer.parseInt(clave.substring(i % 2, i % 2 + 1));

            int valorDescifrado = (valorCifrado - valorClave + 26) % 26;

            // Ajustar en caso de que el resultado sea menor que 1
            if (valorDescifrado < 1) {
              valorDescifrado += 26;
            }            

            char caracterDescifrado = (char) (valorDescifrado + 'a' - 1);

            msjDescifrado.append(caracterDescifrado);
          } else {
            msjDescifrado.append(caracter);
          }
        }
        msjDescifrado.append(" "); // Agregar espacio entre palabras
      }
      msjDescifrado.append("\n");
    }
    return msjDescifrado.toString().trim();
  }
}
