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
public class SustitucionVigenere extends CifradoClasico {
  private String clave;

  public SustitucionVigenere(String pClave) {
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
    int indiceClave = 0;
    
    for (char caracter : msj.toCharArray()) {
      if (Character.isLetter(caracter)) {
        
        // Calcular el valor del cáracter cifrado
        int valorOriginal = caracter - 'a' + 1;
        int valorDesplazamiento = clave.charAt(indiceClave) - 'a' + 1;
        
        // Calcular el valor cifrado utilizando el desplazamiento
        int valorCifrado = (valorOriginal + valorDesplazamiento - 1) % 26 + 1;
        
        // Convertir el valor cifrado a letra
        char caracterCifrado = (char) (valorCifrado + 'a' - 1);
        
        // Agregar el carácter cifrado al mensaje cifrado
        msjCifrado.append(caracterCifrado);
        
        // ¨Pasar al siguiente carácter
        indiceClave = (indiceClave + 1) % clave.length();        
      } else {
        msjCifrado.append(caracter);
      }
    }
    return msjCifrado.toString();
  }

  @Override
  public String descifrar(String msj) {
    StringBuilder msjDescifrado = new StringBuilder();
    int indiceClave = 0;
    
    for (char caracter : msj.toCharArray()) {
      if (Character.isLetter(caracter)) {
        
        // Calcular el valor del cáracter descifrado
        int valorCifrado = caracter - 'a' + 1;
        int valorDesplazamiento = clave.charAt(indiceClave) - 'a' + 1;
        
        // Calcular el valor descifrado utilizando el desplazamiento
        int valorDescifrado = (valorCifrado + valorDesplazamiento - 1) % 26 + 1;
        
        // Convertir el valor descifrado a letra
        char caracterDescifrado = (char) (valorDescifrado + 'a' - 1);
        
        // Agregar el carácter descifrado al mensaje cifrado
        msjDescifrado.append(caracterDescifrado);
        
        // ¨Pasar al siguiente carácter de la clave
        indiceClave = (indiceClave + 1) % clave.length();        
      } else {
        msjDescifrado.append(caracter);
      }
    }
    return msjDescifrado.toString();
  }
}
