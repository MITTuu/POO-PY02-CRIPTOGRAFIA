package modelo;

/**
 * Clase que implementa un cifrado por llave, donde cada letra en el texto
 * sin formato se cifra o descifra utilizando una clave proporcionada.
 *
 * @author Eduardo Rojas Gomez
 * @author Dylan Montiel Zuniga
 * @version 3.0
 */
public class CifradoPorLlave extends CifradoClasico {  
  private String clave;
  
  /**
   * Constructor que inicializa el cifrado por llave con una clave dada.
   *
   * @param pClave La clave a utilizar para el cifrado.
   */  
  public CifradoPorLlave(String pClave) {
    super(pClave);
    clave = super.getClave();
  }

  /**
   * Cifra un mensaje utilizando el cifrado por llave.
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

  /**
   * Descifra un mensaje cifrado utilizando el cifrado por llave.
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
