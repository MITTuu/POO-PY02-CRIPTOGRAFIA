package modelo;

import java.util.*;

/**
 * Esta clase una abstraccion de un metodo de cifrado por codificacion binaria.
 * 
 * @author Eduardo Rojas Gomez
 * @author Dylan Montiel Zuniga
 * @version 1.0
 */
public class CifradoBinario extends CifradoClasico {
 
  /**
   * Este es el constructor de la clase CifradoBinario.
   */  
  public CifradoBinario() {  
    super(null);
  }
 
  /**
   * Encripta una cadena de texto mediante el algoritmo binario.
   * 
   * @param pCadena Cadena de caracteres que será cifrada.
   * @return Cadena cifrada mediante el algoritmo binario.
   */
  @Override
  public String cifrar(String pCadena) {
    String[] lineas = pCadena.split("\n");
    StringBuilder msjCifrado = new StringBuilder();
    
    for (String linea : lineas) {
      
      linea = linea.toLowerCase();
      //System.out.println(linea);
    
      for (char letra : linea.toCharArray()) {
        //System.out.println(letra);

        if (Character.isLetter(letra)) {
          if (letra != 'ñ' || letra != 'Ñ') {
            msjCifrado.append(letraABinario(String.valueOf(letra))+ " ");
          } else {
            return null;
          }
          
        } else if (letra == ' ') {
          msjCifrado.append("* ");
        } else if (letra == '\n' || letra == '\r') {
          msjCifrado.append("\n");
        } else {
          return null;
        }

      }      
      //msjCifrado.append("\n");
    }
    return msjCifrado.toString();
  }

  /**
   * Descifra una cadena de texto cifrada mediante el algoritmo binario.
   * 
   * @param pCadena Cadena de caracteres cifrada.
   * @return Cadena descifrada mediante el algoritmo binario.
   */  
  @Override
  public String descifrar(String pCadena) {
    StringBuilder msjCifrado = new StringBuilder();
    
    String[] lineas = pCadena.split("\\n");
    //System.out.println("Tamaño: " + lineas.length + " |  " + lineas[0] + " A " + lineas[1]);

    for (String linea : lineas) {
    String[] listaString = linea.split("\\s+");
    
    for (String letra : listaString) {
      //System.out.println(letra+"--1");
      if (letra.equals("*" ) || letra.matches("\\*+") ) {
        msjCifrado.append(" ");
      } else {

        //System.out.println(letra+"--1");
        //System.out.println(binarioALetra(letra)+"--2");
        if (binarioALetra(letra) == null) {
          //System.out.println("Pass");
          return null;
        }
        msjCifrado.append(binarioALetra(letra));
        
      }

    }
    msjCifrado.append("\n");
    }
    return msjCifrado.toString();
  }  

  /**
   * Este metodo permite obtener el valor binario de una letra del alfabeto.
   * 
   * @param pLetra Letra para hacer el cambio a binario
   * @return El valor binario correspondiente a la letra ingresada.
   */
  public String letraABinario(String pLetra) {
    //System.out.println(pLetra);
    //String p = "e";
    switch (pLetra){
      case "a":
        return "00000"; 
        
      
      case "b":
        return "00001";         

      case "c":
        return "00010";        

      case "d":
        return "00011"; 
      
      case "e":
        //System.out.println("paso");
       return "00100"; 
       
      case "f":
       return "00101";       

      case "g":
        return "00110"; 

      case "h":
        return "00111"; 
        
      case "i":
        return "01000";         

      case "j":
        return "01001"; 
        
      case "k":
        return "01010"; 
        
      case "l":
        return "01011";         

      case "m":
        return "01100"; 

      case "n":
        return "01101"; 
        
      //case "ñ":
        //return "00000";        

      case "o":
        return "01110"; 

      case "p":
        return "01111"; 
        
      case "q":
        return "10000";         

      case "r":
        return "10001";

      case "s":
        return "10010"; 
        
      case "t":
        return "10011";         

      case "u":
        return "10100"; 

      case "v":
        return "10101"; 
        
      case "w":
        return "10110";         

      case "x":
        return "10111";         

      case "y":
        return "11000"; 
        
      case "z":
        return "11001";         

      default :
        //System.out.println("paso");
        return null;
    
    }
  }  

  /**
   * Este metodo permite obtener la letra correspondiente a un valor binario. 
   * 
   * @param pLetra Combinacion binaria para cambiar a su letra correspondiente
   * @return La letra correspondiente a un valor binario.
   */
  public String binarioALetra(String pLetra) {
    switch (pLetra) {
      case "00000":
        return "a";

      case "00001":
        return "b";

      case "00010":
        return "c";

      case "00011":
        return "d";

      case "00100":
        return "e";

      case "00101":
        return "f";

      case "00110":
        return "g";

      case "00111":
        return "h";

      case "01000":
        return "i";

      case "01001":
        return "j";

      case "01010":
        return "k";

      case "01011":
        return "l";

      case "01100":
        return "m";

      case "01101":
        return "n";

      case "01110":
        return "o";

      case "01111":
        return "p";

      case "10000":
        return "q";

      case "10001":
        return "r";

      case "10010":
        return "s";

      case "10011":
        return "t";

      case "10100":
        return "u";

      case "10101":
        return "v";

      case "10110":
        return "w";

      case "10111":
        return "x";

      case "11000":
        return "y";

      case "11001":
        return "z";
             
      default:
        return null;
    }
  }       
}
