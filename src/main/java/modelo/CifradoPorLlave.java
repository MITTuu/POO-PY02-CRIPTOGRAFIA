/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author Dylan Montiel Zúñiga
 * @version 3.0
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
    String[] lineas = msj.split("\n");
    
    for (String linea : lineas) {
      String[] palabras = linea.split("\\s+"); // Dividir el texto en palabras
      
      for (String palabra : palabras) {
        int indiceClave = 0;

        for (char caracter : palabra.toCharArray()) {
          if (Character.isLetter(caracter)) {
            int valorOriginal = Character.toLowerCase(caracter) - 'a' + 1;
            int valorClave = clave.charAt(indiceClave) - 'a' + 1;

            int valorCifrado = (valorOriginal + valorClave - 1) % 26 + 1;

            // Ajustar en caso de que el resultado sea menor que 1
            if (valorCifrado < 1) {
              valorCifrado += 26;
            }            

            char caracterCifrado = (char) (valorCifrado + 'a' - 1);

            msjCifrado.append(caracterCifrado);

            // Mover al siguiente carácter de la clave
            indiceClave = (indiceClave + 1) % clave.length();
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

  @Override
  public String descifrar(String msj) {
    StringBuilder msjDescifrado = new StringBuilder();
    String[] lineas = msj.split("\n");
    
    for (String linea : lineas) {
      String[] palabras = linea.split("\\s+"); // Dividir el texto en palabras

      for (String palabra : palabras) {
        int indiceClave = 0;

        for (char caracter : palabra.toCharArray()) {
          if (Character.isLetter(caracter)) {
            int valorCifrado = Character.toLowerCase(caracter) - 'a' + 1;
            int valorClave = clave.charAt(indiceClave) - 'a' + 1;

            int valorDescifrado = (valorCifrado - valorClave + 26) % 26;

            // Ajustar en caso de que el resultado sea menor que 1
            if (valorDescifrado < 1) {
              valorDescifrado += 26;
            }

            char caracterDescifrado = (char) (valorDescifrado + 'a' - 1);

            msjDescifrado.append(caracterDescifrado);

            // Mover al siguiente carácter de la clave
            indiceClave = (indiceClave + 1) % clave.length();
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
