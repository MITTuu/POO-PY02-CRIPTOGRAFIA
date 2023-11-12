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
public class CifradoPorLlave extends CifradoClasico {
  private String clave;
  
  public CifradoPorLlave(String pClave) {
    setClave(pClave);
  }

  // Métodos accesores
  public String getClave() {
    return clave;
  }
  public void setClave(String pClave) {
    this.clave = pClave;
  }

  @Override
  public String cifrar(String msj) {
    StringBuilder msjCifrado = new StringBuilder();
    int indiceClave = 0; // Índice para recorrer la clave

    for (char caracter : msj.toCharArray()) {
      if (Character.isLetter(caracter)) {
        // Calcular el valor del carácter cifrado
        int valorOriginal = caracter - 'a' + 1; // Obtener el valor original de la letra
        int valorClave = clave.charAt(indiceClave) - 'a' + 1; // Obtener el valor de la letra de la clave

        // Sumar los valores y ajustar para mantenerse en el rango de las letras
        int valorCifrado = (valorOriginal + valorClave - 1) % 26 + 1;

        // Convertir el valor cifrado de nuevo a la letra
        char caracterCifrado = (char) (valorCifrado + 'a' - 1);

        // Agregar el carácter cifrado al mensaje cifrado
        msjCifrado.append(caracterCifrado);

        // Mover al siguiente carácter de la clave
        indiceClave = (indiceClave + 1) % clave.length();
      } else {
        msjCifrado.append(caracter);
      }
    }
    return msjCifrado.toString();
  }  

  public String descifrar(String msj) {
    StringBuilder msjDescifrado = new StringBuilder();
    int indiceClave = 0; 

    for (char caracter : msj.toCharArray()) {
      if (Character.isLetter(caracter)) {
        int valorCifrado = caracter - 'a' + 1; 
        int valorClave = clave.charAt(indiceClave) - 'a' + 1; 

        int valorDescifrado = (valorCifrado - valorClave + 26) % 26;

        char caracterDescifrado = (char) (valorDescifrado + 'a' - 1);

        msjDescifrado.append(caracterDescifrado);

        indiceClave = (indiceClave + 1) % clave.length();
      } else {
        msjDescifrado.append(caracter);
      }
    }
    return msjDescifrado.toString();
  } 
  
}
