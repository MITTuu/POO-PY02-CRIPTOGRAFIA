package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import vista.*;

/**
 * Clase que actúa como controlador para la interfaz gráfica del menú principal.
 * 
 * @author Eduardo Rojas Gomez
 * @author Dylan Montiel Zuniga
 * @version 4.0
 */
public class Controlador_GUI_MENU_PRINCIPAL implements ActionListener {
  private GUI_MENU_PRINCIPAL gui_menu_principal;

  /**
   * Constructor de la clase `Controlador_GUI_MENU_PRINCIPAL`.
   * 
   * @param gui_menu_principal Referencia a la interfaz gráfica del menú principal.
   */  
  public Controlador_GUI_MENU_PRINCIPAL(GUI_MENU_PRINCIPAL gui_menu_principal) {
    this.gui_menu_principal = gui_menu_principal;
  }

  /**
   * Maneja los eventos de los componentes de la interfaz gráfica.
   * 
   * @param evento Evento producido.
   */ 
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
