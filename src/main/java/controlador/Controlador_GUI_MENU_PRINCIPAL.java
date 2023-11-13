/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.JFileChooser;
import vista.*;

/**
 *
 * @author Dylan Montiel Zúñiga
 * @version 1.0 
 */
public class Controlador_GUI_MENU_PRINCIPAL implements ActionListener {

  private GUI_MENU_PRINCIPAL gui_menu_principal;
  public Controlador_GUI_MENU_PRINCIPAL(GUI_MENU_PRINCIPAL gui_menu_principal) {
    this.gui_menu_principal = gui_menu_principal;
  }
    
  @Override
  public void actionPerformed(ActionEvent evento) {
    if (evento.getActionCommand().equals("Abrir archivo TXT")) {
      gui_menu_principal.abrirArchivo();
    }
    if (evento.getActionCommand().equals("Salir")) {
      System.exit(0);   
    }
  }
}
