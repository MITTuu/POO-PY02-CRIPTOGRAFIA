/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import vista.*;

/**
 *
 * @author Dylan Montiel Zúñiga
 * @version 3.0 
 */
public class Controlador_GUI_MENU_PRINCIPAL implements ActionListener {

  private GUI_MENU_PRINCIPAL gui_menu_principal;
  public Controlador_GUI_MENU_PRINCIPAL(GUI_MENU_PRINCIPAL gui_menu_principal) {
    this.gui_menu_principal = gui_menu_principal;
  }
    
  @Override
  public void actionPerformed(ActionEvent evento) {
    if (evento.getActionCommand().equals("Abrir archivo TXT")) {
      gui_menu_principal.abrirTXT();
    }
    if (evento.getActionCommand().equals("Aplicar algoritmo")) {
      gui_menu_principal.aplicarAlgoritmo();
    }   
    if (evento.getActionCommand().equals("Limpiar pantallas")) {
      gui_menu_principal.limpiarPantallas();
    }        
    if (evento.getActionCommand().equals("Enviar por correo")) {
      gui_menu_principal.enviarCorreo();
    }     
    if (evento.getActionCommand().equals("Salir")) {
        int opcion = JOptionPane.showOptionDialog(null, "¿Deseas salir del programa?", "Confirmación", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
        if (opcion == JOptionPane.YES_OPTION) {
            System.exit(0);
        }       
    }
    gui_menu_principal.habilitarClave();
  }
  
}
