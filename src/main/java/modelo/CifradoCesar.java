package modelo;

/**
 * Clase que implementa el cifrado César, una forma de cifrado de sustitución
 * en la que cada letra en el texto sin formato se desplaza un número fijo de
 * posiciones hacia la derecha en el alfabeto.
 *
 * @author Eduardo Rojas Gomez, Dylan Montiel Zuniga
 * @version 2.0
 */
public class CifradoCesar extends CifradoClasico {
  private static final int DESPLAZAMIENTO = 3;

  /**
   * Constructor que inicializa el cifrado César con un desplazamiento predefinido.
   */  
  public CifradoCesar() {
    super(Integer.toString(DESPLAZAMIENTO));
  }
  
  /**
   * Cifra un mensaje utilizando el cifrado César.
   *
   * @param msj El mensaje a cifrar.
   * @return El mensaje cifrado.
   */  
  @Override
  public String cifrar(String msj) {
    StringBuilder msjCifrado = new StringBuilder();
    
    for (char caracter : msj.toCharArray()) {
      if (Character.isLetter(caracter)) {
        char base = Character.isUpperCase(caracter) ? 'A' : 'a';
        char cifrado = (char) ((caracter - base + DESPLAZAMIENTO) % 26 + base);
        msjCifrado.append(cifrado);
      } else {
        msjCifrado.append(caracter);
      }
    }
    return msjCifrado.toString();
  }

  /**
   * Descifra un mensaje cifrado utilizando el cifrado César.
   *
   * @param msj El mensaje cifrado a descifrar.
   * @return El mensaje descifrado.
   */  
  @Override
  public String descifrar(String msj) {
    StringBuilder msjDescifrado = new StringBuilder();
    
    for (char caracter : msj.toCharArray()) {
      if (Character.isLetter(caracter)) {
        char base = Character.isUpperCase(caracter) ? 'A' : 'a';
        char descifrado = (char) ((caracter - base - DESPLAZAMIENTO + 26) % 26 + base);
        msjDescifrado.append(descifrado);
      } else {
        msjDescifrado.append(caracter);
      }
    }
    return msjDescifrado.toString();
  }
}
