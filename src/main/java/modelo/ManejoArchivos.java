package modelo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 * La clase ManejoArchivos proporciona métodos para leer y sobrescribir archivos.
 *
 * @author Eduardo Rojas Gomez
 * @author Dylan Montiel Zuniga
 * @version 1.0
 */
public class ManejoArchivos {

  /**
   * Constructor predeterminado de la clase ManejoArchivos.
   * Este constructor no realiza ninguna operación específica.
   */
  public ManejoArchivos() {
    // Este constructor no realiza ninguna operación específica.
  }
  
  /**
   * Lee el contenido de un archivo y lo devuelve como un String.
   *
   * @param ruta La ruta del archivo que se va a leer.
   * @return El contenido del archivo como un String.
   */
  public static String leerArchivo(String ruta) {
    StringBuilder contenido = new StringBuilder();
    
    try (BufferedReader br = new BufferedReader(new FileReader(ruta))) {
      String linea;
      while ((linea = br.readLine()) != null) {
        contenido.append(linea).append("\n");
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
    return contenido.toString();
  }

  /**
   * Sobrescribe un archivo con el contenido proporcionado.
   *
   * @param ruta      La ruta del archivo que se va a sobrescribir.
   * @param contenido El nuevo contenido que se escribirá en el archivo.
   */  
  public static void sobreescribirArchivo(String ruta, String contenido) {
    try (BufferedWriter bw = new BufferedWriter(new FileWriter(ruta))) {
      bw.write(contenido);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
