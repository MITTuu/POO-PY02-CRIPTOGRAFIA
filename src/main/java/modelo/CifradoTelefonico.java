package modelo;

/**
 * Clase que implementa un cifrado telefónico, donde cada letra en el texto
 * sin formato se representa por un par de números que indican el número y la posición
 * de la letra en un teclado telefónico.
 *
 * @author Eduardo Rojas Gomez, Dylan Montiel Zuniga
 * @version 1.0
 */
public class CifradoTelefonico extends CifradoClasico {

  /**
   * Constructor que inicializa el cifrado telefónico sin utilizar una clave.
   */  
  public CifradoTelefonico() {
    super(null);
  }

  /**
   * Cifra un mensaje utilizando el cifrado telefónico.
   *
   * @param msj El mensaje a cifrar.
   * @return El mensaje cifrado.
   */  
  @Override
  public String cifrar(String msj) {
    StringBuilder msjCifrado = new StringBuilder();
    String[] lineas = msj.split("\n");
    
    for (String linea : lineas) {
      for (char caracter : linea.toCharArray()) {
        if (Character.isLetter(caracter)) {
          int numero = obtenerNumero(caracter);
          int posicion = obtenerPosicion(caracter);

          msjCifrado.append(numero).append(posicion).append(" ");              
        } else if (caracter == ' ') {
          msjCifrado.append("* ");
        }      
      }
      msjCifrado.append("\n");      
    }    
    return msjCifrado.toString().trim();
  }

  /**
   * Descifra un mensaje cifrado utilizando el cifrado telefónico.
   *
   * @param msj El mensaje cifrado a descifrar.
   * @return El mensaje descifrado.
   */  
  @Override
  public String descifrar(String msj) {
    StringBuilder msjDescifrado = new StringBuilder();
    String[] lineas = msj.split("\n");
    
    for (String linea : lineas) {    
      String[] partes = linea.split("\\s+");
      for (String parte : partes) {
        if (parte.equals("*")) {             
          msjDescifrado.append(" ");
        } else {
          int numero = Integer.parseInt(parte.substring(0, 1));
          int posicion = Integer.parseInt(parte.substring(1));
          char letra = obtenerLetra(numero, posicion);
          msjDescifrado.append(letra);        
        }      
      }
      msjDescifrado.append("\n");
    }
    return msjDescifrado.toString();
  }

  /**
   * Obtiene el número asociado a una letra en un teclado telefónico.
   *
   * @param letra La letra a buscar.
   * @return El número asociado a la letra.
   */
  private int obtenerNumero(char letra) {
    if ("abc".contains(String.valueOf(letra))) {
      return 2;
    } else if ("def".contains(String.valueOf(letra))) {
      return 3;
    } else if ("ghi".contains(String.valueOf(letra))) {
      return 4;
    } else if ("jkl".contains(String.valueOf(letra))) {
      return 5;
    } else if ("mno".contains(String.valueOf(letra))) {
      return 6;
    } else if ("pqrs".contains(String.valueOf(letra))) {
      return 7;
    } else if ("tuv".contains(String.valueOf(letra))) {
      return 8;
    } else if ("wxyz".contains(String.valueOf(letra))) {
      return 9;
    }
    return 0; // En caso de que no sea una letra válida
  }

  /**
   * Obtiene la posición asociada a una letra en un teclado telefónico.
   *
   * @param letra La letra a buscar.
   * @return La posición asociada a la letra.
   */  
  private int obtenerPosicion(char letra) {
    if ("abc".contains(String.valueOf(letra))) {
      return "abc".indexOf(letra) + 1;
    } else if ("def".contains(String.valueOf(letra))) {
      return "def".indexOf(letra) + 1;
    } else if ("ghi".contains(String.valueOf(letra))) {
      return "ghi".indexOf(letra) + 1;
    } else if ("jkl".contains(String.valueOf(letra))) {
      return "jkl".indexOf(letra) + 1;
    } else if ("mno".contains(String.valueOf(letra))) {
      return "mno".indexOf(letra) + 1;
    } else if ("pqrs".contains(String.valueOf(letra))) {
      return "pqrs".indexOf(letra) + 1;
    } else if ("tuv".contains(String.valueOf(letra))) {
      return "tuv".indexOf(letra) + 1;
    } else if ("wxyz".contains(String.valueOf(letra))) {
      return "wxyz".indexOf(letra) + 1;
    }
    return 0; // En caso de que no sea una letra válida
  }

  /**
   * Obtiene la letra asociada a un número y posición en un teclado telefónico.
   *
   * @param numero   El número a buscar.
   * @param posicion La posición a buscar.
   * @return La letra asociada al número y posición.
   */  
  private char obtenerLetra(int numero, int posicion) {
    switch (numero) {
      case 2 -> {
        return "abc".charAt(posicion - 1);
      }
      case 3 -> {
        return "def".charAt(posicion - 1);
      }
      case 4 -> {
        return "ghi".charAt(posicion - 1);
      }
      case 5 -> {
        return "jkl".charAt(posicion - 1);
      }
      case 6 -> {
        return "mno".charAt(posicion - 1);
      }
      case 7 -> {
        return "pqrs".charAt(posicion - 1);
      }
      case 8 -> {
        return "tuv".charAt(posicion - 1);
      }
      case 9 -> {
        return "wxyz".charAt(posicion - 1);
      }
      default -> {
      }
    }
    return ' '; // Caracter vacío en caso de que no sea un número válido
  }
}