package modelo;
//Imports especificos de funcionalidad

import java.util.*;
//Import generales del proyecto

/**
 * Esta clase una abstraccion de un metodo de cifrado por codificacion binaria.
 * @author Eduardo Rojas Gomez y Dylan Montiel Zuñiga.
 * 
 * @version 15/11/2023
 */
public class CifradoBinario {
  /**
   * Este es el constructor de la clase CifradoRSA.
   */  
  public CifradoBinario() {
  }
 
  /**
   * Este metodo permite encriptar una cadena de Strings mediante el algoritmo RSA.
   * 
   * @param pCadena una cadena de caracteres la cual sera cifrada.
   * @return Una cadena descifrado mediante el algoritmo RSA.
   */    
  public String cifrado(String pCadena) {
    pCadena = pCadena.toLowerCase();
    StringBuilder msjCifrado = new StringBuilder();
    for (char letra : pCadena.toCharArray()) {
      if (letra != ' ') {
      //System.out.println(letra);
        if (Character.isLetter(letra)) {
          //if (letra != 'ñ') {
         
          msjCifrado.append(" " + letraABinario(String.valueOf(letra)));
            
        }
        // msjCifrado.append(letra);
        
      }  else {
          
       msjCifrado.append(" *");
      }
               
     // } else {
        //return null;        
    // }
    
    }
    return msjCifrado.toString();
  }

  /**
   * Este metodo permite descifrar una cadena de Strings mediante el algoritmo RSA.
   * 
   * @param pCadena una cadena de caracteres.
   * @return Una cadena descifrado mediante el algoritmo RSA.
   */  
  public String descifrado(String pCadena) {
    StringBuilder msjCifrado = new StringBuilder();
    
    String[] listaString = pCadena.split(" ");
    
    for (String letra : listaString) {

      if (letra.equals("*") ) {
        msjCifrado.append(" ");
      } else {

        //System.out.println(letra+"--1");
        //System.out.println(binarioALetra(letra)+"--2");
        msjCifrado.append(binarioALetra(letra));

      }

    }
    return msjCifrado.toString();
  }  

  /**
   * Este metodo permite obtener el valor binario de una letra del alfabeto.
   * 
   * @param pLetra 
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
   * @param pLetra
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
        return " ";
    }
  }  
    
  
}
