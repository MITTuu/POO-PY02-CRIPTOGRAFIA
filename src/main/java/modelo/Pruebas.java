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
public class Pruebas {
  public static void main(String[] args) {
    
    /*
    // Instanciar el objeto cifradoCesar
    CifradoCesar cifradoCesar = new CifradoCesar();
    System.out.println("Cifrado Cesar");
    
    // Mensaje original
    String msjOriginal = "Tarea programada criptografia de datos.";
    System.out.println("Mensaje Original: " + msjOriginal);
    // Cifrar el mensaje
    String msjCifrado = cifradoCesar.cifrar(msjOriginal);
    System.out.println("Mensaje Cifrado: " + msjCifrado);
    
    // Descifrar el mensaje
    String msjDescifrado = cifradoCesar.descifrar(msjCifrado);
    System.out.println("Mensaje Descifrado: " + msjDescifrado);
    
    System.out.println("\n");
    
    // Instanciar el objeto cifradoPorLlave
    CifradoPorLlave cifradoPorLlave = new CifradoPorLlave("tango");
    System.out.println("Cifrado por llave");
    
    // Mensaje original
    String msjOriginal1 = "tarea programada de codificacion";
    System.out.println("Mensaje Original: " + msjOriginal1);
    
    // Cifrar el mensaje
    String msjCifrado1 = cifradoPorLlave.cifrar(msjOriginal1);
    System.out.println("Mensaje Cifrado: " + msjCifrado1);
    
    // Descifrar el mensaje
    String msjDescifrado1 = cifradoPorLlave.descifrar(msjCifrado1);
    System.out.println("Mensaje Descifrado: " + msjDescifrado1);    
    */
    CifradoDES_V2 cifrar = new CifradoDES_V2();
    
    CifradoAES cifra = new CifradoAES();
    
    CifradoTripleDES ni = new CifradoTripleDES();
    
    //String nada = "lt/1jm7Bn2Lbz2pNqmFNvoS1HtNFezZrDyVsEVGeqn0=";
    
    String nada = "Este mensaje sera cifrado.";
    
    String na1 = cifra.cifrado(nada);
    //ni.setContrasena();
    System.out.println(na1);
    
    CifradoDES_V2 cifrar1 = new CifradoDES_V2();
    CifradoAES cifra1 = new CifradoAES();
    CifradoTripleDES ni1 = new CifradoTripleDES();
    
    String na2 = cifra1.descifrado(na1);
    System.out.println(na2);
    
    
    
  }
}
