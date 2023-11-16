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
    String msjOriginal = "Tarea programada criptografía de datos xyz.";
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
    String msjOriginal1 = "tarea programada de codificacion xyz xyz";
    System.out.println("Mensaje Original: " + msjOriginal1);
    
    // Cifrar el mensaje
    String msjCifrado1 = cifradoPorLlave.cifrar(msjOriginal1);
    System.out.println("Mensaje Cifrado: " + msjCifrado1);
    
    // Descifrar el mensaje
    String msjDescifrado1 = cifradoPorLlave.descifrar(msjCifrado1);
    System.out.println("Mensaje Descifrado: " + msjDescifrado1);    
  
    System.out.println("\n");
    
    // Instanciar el objeto cifradoPorLlave
    SustitucionVigenere sustitucionVigenere = new SustitucionVigenere("32");
    System.out.println("Cifrado Vigenere");
    
    // Mensaje original
    String msjOriginal2 = "tarea programada de codificacion xyz";
    System.out.println("Mensaje Original: " + msjOriginal2);
    
    // Cifrar el mensaje
    String msjCifrado2 = sustitucionVigenere.cifrar(msjOriginal2);
    System.out.println("Mensaje Cifrado: " + msjCifrado2);
    
    // Descifrar el mensaje
    String msjDescifrado2 = sustitucionVigenere.descifrar(msjCifrado2);
    System.out.println("Mensaje Descifrado: " + msjDescifrado2);    
    
    // Enviar un correo con la verificacion de la API
    CuentaCorreo cuentaCorreo = new CuentaCorreo("py02.cifradodemensajes@gmail.com");

    String destinatario = "dylanmmz01apsdfsfobrpebojbidylanmmz01@gmail.com";
    if (cuentaCorreo.validarCorreo(destinatario)) {
      System.out.println("si");
      cuentaCorreo.enviarCorreo(destinatario, "Asunto", "Cuerpo del correo");        
    } else {
      System.out.println("La dirección de correo electrónico no es válida.");    
    }  
    
    String destinatario1 = "dylanmmz01@gmail.com";
    if (cuentaCorreo.validarCorreo(destinatario1)) {
        cuentaCorreo.enviarCorreo(destinatario1, "Cifrado Cesar", msjCifrado);
    } else {
        System.out.println("La dirección de correo electrónico no es válida.");
    }      
  }  
}
