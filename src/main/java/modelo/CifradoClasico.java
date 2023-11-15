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
public abstract class CifradoClasico {
  private String clave;
  
  public CifradoClasico(String pClave) {
    setClave(pClave);
  }

  // Métodos accesores
  public String getClave() {
    return clave;
  }
  public void setClave(String pClave) {
    this.clave = pClave;
  }    
  
  public abstract String cifrar(String msj);
  public abstract String descifrar(String msj);
}
