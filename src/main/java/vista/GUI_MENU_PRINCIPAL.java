/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package vista;

import controlador.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import modelo.*;

/**
 *
 * @author Dylan Montiel Zúñiga
 * @version 3.0
 */
public class GUI_MENU_PRINCIPAL extends javax.swing.JFrame {

  /**
   * Creates new form GUI_MENU_PRINCIPAL
   */
  private Controlador_GUI_MENU_PRINCIPAL controlador_GUI_MENU_PRINCIPAL;
  
  public GUI_MENU_PRINCIPAL() {
    initComponents();
    setLocationRelativeTo(null);
    controlador_GUI_MENU_PRINCIPAL = new Controlador_GUI_MENU_PRINCIPAL(this);
    Jbtn_Salir.addActionListener(controlador_GUI_MENU_PRINCIPAL);
    Jbtn_AbrirTXT.addActionListener(controlador_GUI_MENU_PRINCIPAL);
    Jbtn_AplicarAlgoritmo.addActionListener(controlador_GUI_MENU_PRINCIPAL);
    Jbtn_Limpiar.addActionListener(controlador_GUI_MENU_PRINCIPAL);
    Jtf_Clave.setEnabled(false);
    Jcb_Algoritmo.addActionListener(controlador_GUI_MENU_PRINCIPAL);
    Jbtn_EnviarCorreo.addActionListener(controlador_GUI_MENU_PRINCIPAL);
  }

  public void abrirTXT() {
    JFileChooser fileChooser = new JFileChooser();
    FileNameExtensionFilter filter = new FileNameExtensionFilter("Archivos de texto", "txt");
    fileChooser.setFileFilter(filter);

    int resultado = fileChooser.showOpenDialog(this);

    if (resultado == JFileChooser.APPROVE_OPTION) {
      File archivoSeleccionado = fileChooser.getSelectedFile();
      String contenido = ManejoArchivos.leerArchivo(archivoSeleccionado.getAbsolutePath());
      Jta_Entrada.setText(contenido);
    }
  }
  
  public void aplicarAlgoritmo() {
    if (Jta_Entrada.getText().isEmpty()) {
      JOptionPane.showMessageDialog(this, "Debes abrir un archivo para aplicar algún algorítmo", "Error", JOptionPane.ERROR_MESSAGE);                
      return;
    }
    
    String operacion = Jcb_Operacion.getSelectedItem().toString().trim();
    String algoritmo = Jcb_Algoritmo.getSelectedItem().toString().trim();
    String contenido = Jta_Entrada.getText();
    String clave = Jtf_Clave.getText();
    
    switch (algoritmo) {
      case "Cifrado César" -> {
        CifradoCesar cifradoCesar = new CifradoCesar();
        if ("Cifrado".equals(operacion)) {
          contenido = cifradoCesar.cifrar(contenido);
        } else {
          contenido = cifradoCesar.descifrar(contenido);
        }
        Jta_Salida.setText(contenido);
      }
      
      case "Cifrado por Llave" -> {    
        if (!validarClaveCifradoPorLlave(clave)) {
          // Mostrar mensaje de error para la clave
          JOptionPane.showMessageDialog(this, "Debes ingresar una clave valida", "Error", JOptionPane.ERROR_MESSAGE);
          return;
        }
        
        CifradoPorLlave cifradoPorLlave = new CifradoPorLlave(clave);
        
        if ("Cifrado".equals(operacion)) {
          contenido = cifradoPorLlave.cifrar(contenido);
        } else {
          contenido = cifradoPorLlave.descifrar(contenido);
        }
        
        Jta_Salida.setText(contenido);
      } 

      case "Sustitución Vigenére" -> {    
        if (!validarClaveSustitucionVigenere(clave)) {
          // Mostrar mensaje de error para la clave
          JOptionPane.showMessageDialog(this, "Debes ingresar una clave valida", "Error", JOptionPane.ERROR_MESSAGE);
          return;
        }
        
        SustitucionVigenere sustitucionVigenere = new SustitucionVigenere(clave);
        
        if ("Cifrado".equals(operacion)) {
          contenido = sustitucionVigenere.cifrar(contenido);
        } else {
          contenido = sustitucionVigenere.descifrar(contenido);
        }        
        Jta_Salida.setText(contenido);
      } 
  
      case "Palabra Inversa" -> {    
        PalabraInversa palabraInversa = new PalabraInversa();
        
        if ("Cifrado".equals(operacion)) {
          contenido = palabraInversa.cifrar(contenido);
        } else {
          contenido = palabraInversa.descifrar(contenido);
        }        
        Jta_Salida.setText(contenido);
      } 

      case "Mensaje Inverso" -> {    
        MensajeInverso mensajeInverso = new MensajeInverso();
        
        if ("Cifrado".equals(operacion)) {
          contenido = mensajeInverso.cifrar(contenido);
        } else {
          contenido = mensajeInverso.descifrar(contenido);
        }        
        Jta_Salida.setText(contenido);
      }       
      
    }
  }
  
  public boolean validarClaveCifradoPorLlave(String clave) {
    return !clave.trim().isEmpty();
  }

  public boolean validarClaveSustitucionVigenere(String clave) {
    return !clave.trim().isEmpty() && clave.matches("\\d{2}") && clave.matches("\\d+");
  }  
  
  public void limpiarPantallas() {
    Jta_Entrada.setText(null);
    Jta_Salida.setText(null);
  }

  public void habilitarClave() {
    String algoritmo = Jcb_Algoritmo.getSelectedItem().toString().trim();
    if ("Cifrado por Llave".equals(algoritmo) || 
        "Sustitución Vigenére".equals(algoritmo)) {
        Jtf_Clave.setEnabled(true);
    } else {
        Jtf_Clave.setEnabled(false);
    }    
  }

  public void enviarCorreo() {
    String correoDestinatario = Jtf_CorreoDestinatario.getText().trim();
    String contenido = Jta_Salida.getText();
    String operacion = Jcb_Operacion.getSelectedItem().toString().trim();
    String algoritmo = Jcb_Algoritmo.getSelectedItem().toString().trim();    
    
    if (!validarCorreo(correoDestinatario)) {
      // Mostrar mensaje de error para el correo
      JOptionPane.showMessageDialog(this, "Debes ingresar un correo válido", "Error", JOptionPane.ERROR_MESSAGE);
      return;
    }    
    
    CuentaCorreo cuentaCorreo = new CuentaCorreo("py02.cifradodemensajes@gmail.com");

    if (cuentaCorreo.validarCorreo(correoDestinatario)) {     
      cuentaCorreo.enviarCorreo(correoDestinatario, "Cifrado de Mensaje: " + operacion + "; " + algoritmo, contenido);        
      JOptionPane.showMessageDialog(this, "Correo enviado con éxito.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
    } else {
      JOptionPane.showMessageDialog(this, "La dirección de correo electrónico no es válida.", "Error", JOptionPane.ERROR_MESSAGE);   
    }      
  }

  private boolean validarCorreo(String correo) {
    return correo.trim().matches("^.+@(gmail|hotmail|outlook)\\..+$");
  }
    
  /**
   * This method is called from within the constructor to initialize the form.
   * WARNING: Do NOT modify this code. The content of this method is always
   * regenerated by the Form Editor.
   */
  @SuppressWarnings("unchecked")
  // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
  private void initComponents() {

    Jp_Opciones = new javax.swing.JPanel();
    Jl_OperacionRealizar = new javax.swing.JLabel();
    Jcb_Operacion = new javax.swing.JComboBox<>();
    Jl_Algoritmo = new javax.swing.JLabel();
    Jcb_Algoritmo = new javax.swing.JComboBox<>();
    Jl_Entrada = new javax.swing.JLabel();
    Jta_Entrada = new java.awt.TextArea();
    Jbtn_AbrirTXT = new javax.swing.JButton();
    Jbtn_AplicarAlgoritmo = new javax.swing.JButton();
    Jl_Salida = new javax.swing.JLabel();
    Jbtn_Salir = new javax.swing.JButton();
    Jbtn_EnviarCorreo = new javax.swing.JButton();
    Jta_Salida = new java.awt.TextArea();
    Jbtn_Limpiar = new javax.swing.JButton();
    Jl_Clave = new javax.swing.JLabel();
    Jtf_Clave = new javax.swing.JTextField();
    jLabel1 = new javax.swing.JLabel();
    Jtf_CorreoDestinatario = new javax.swing.JTextField();

    setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
    setBackground(new java.awt.Color(255, 255, 255));

    Jp_Opciones.setBackground(new java.awt.Color(255, 255, 255));

    Jl_OperacionRealizar.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
    Jl_OperacionRealizar.setText("Operación a realizar:");

    Jcb_Operacion.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Cifrado", "Descifrado" }));

    Jl_Algoritmo.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
    Jl_Algoritmo.setText("Algoritmo:");

    Jcb_Algoritmo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Cifrado César", "Cifrado por Llave", "Sustitución Vigenére", "Palabra Inversa", "Mensaje Inverso" }));

    Jl_Entrada.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
    Jl_Entrada.setText("Entrada");

    Jta_Entrada.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N

    Jbtn_AbrirTXT.setText("Abrir archivo TXT");

    Jbtn_AplicarAlgoritmo.setText("Aplicar algorítmo");
    Jbtn_AplicarAlgoritmo.setActionCommand("Aplicar algoritmo");

    Jl_Salida.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
    Jl_Salida.setText("Salida");

    Jbtn_Salir.setText("Salir");

    Jbtn_EnviarCorreo.setText("Enviar por correo");

    Jta_Salida.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
    Jta_Salida.setEditable(false);

    Jbtn_Limpiar.setText("Limpiar pantallas");

    Jl_Clave.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
    Jl_Clave.setText("Clave:");

    jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
    jLabel1.setText("Correo Destinatario:");

    Jtf_CorreoDestinatario.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        Jtf_CorreoDestinatarioActionPerformed(evt);
      }
    });

    javax.swing.GroupLayout Jp_OpcionesLayout = new javax.swing.GroupLayout(Jp_Opciones);
    Jp_Opciones.setLayout(Jp_OpcionesLayout);
    Jp_OpcionesLayout.setHorizontalGroup(
      Jp_OpcionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(Jp_OpcionesLayout.createSequentialGroup()
        .addGap(242, 242, 242)
        .addComponent(Jbtn_AplicarAlgoritmo)
        .addGap(32, 32, 32)
        .addComponent(Jbtn_Limpiar)
        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
      .addGroup(Jp_OpcionesLayout.createSequentialGroup()
        .addGap(12, 12, 12)
        .addGroup(Jp_OpcionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addGroup(Jp_OpcionesLayout.createSequentialGroup()
            .addGroup(Jp_OpcionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
              .addGroup(Jp_OpcionesLayout.createSequentialGroup()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(Jtf_CorreoDestinatario, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(Jbtn_EnviarCorreo)
                .addGap(0, 0, Short.MAX_VALUE))
              .addGroup(Jp_OpcionesLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(Jp_OpcionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                  .addComponent(Jta_Salida, javax.swing.GroupLayout.PREFERRED_SIZE, 698, javax.swing.GroupLayout.PREFERRED_SIZE)
                  .addComponent(Jl_Salida))))
            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
          .addGroup(Jp_OpcionesLayout.createSequentialGroup()
            .addComponent(Jl_OperacionRealizar)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(Jcb_Operacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(18, 18, 18)
            .addComponent(Jl_Algoritmo)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(Jcb_Algoritmo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(18, 18, 18)
            .addComponent(Jl_Clave)
            .addGap(18, 18, 18)
            .addComponent(Jtf_Clave, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(0, 0, Short.MAX_VALUE))))
      .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Jp_OpcionesLayout.createSequentialGroup()
        .addContainerGap(30, Short.MAX_VALUE)
        .addGroup(Jp_OpcionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Jp_OpcionesLayout.createSequentialGroup()
            .addGroup(Jp_OpcionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
              .addGroup(javax.swing.GroupLayout.Alignment.LEADING, Jp_OpcionesLayout.createSequentialGroup()
                .addComponent(Jl_Entrada)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(Jbtn_AbrirTXT))
              .addComponent(Jta_Entrada, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 698, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGap(30, 30, 30))
          .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Jp_OpcionesLayout.createSequentialGroup()
            .addComponent(Jbtn_Salir)
            .addContainerGap())))
    );
    Jp_OpcionesLayout.setVerticalGroup(
      Jp_OpcionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(Jp_OpcionesLayout.createSequentialGroup()
        .addGap(20, 20, 20)
        .addGroup(Jp_OpcionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(Jl_OperacionRealizar)
          .addComponent(Jcb_Operacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
          .addComponent(Jl_Algoritmo)
          .addComponent(Jcb_Algoritmo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
          .addComponent(Jl_Clave)
          .addComponent(Jtf_Clave, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        .addGap(25, 25, 25)
        .addGroup(Jp_OpcionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
          .addComponent(Jl_Entrada)
          .addComponent(Jbtn_AbrirTXT))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(Jta_Entrada, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addGap(22, 22, 22)
        .addGroup(Jp_OpcionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(Jbtn_AplicarAlgoritmo)
          .addComponent(Jbtn_Limpiar))
        .addGap(18, 18, 18)
        .addComponent(Jl_Salida)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(Jta_Salida, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 51, Short.MAX_VALUE)
        .addGroup(Jp_OpcionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(jLabel1)
          .addComponent(Jtf_CorreoDestinatario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
          .addComponent(Jbtn_EnviarCorreo))
        .addGap(11, 11, 11)
        .addComponent(Jbtn_Salir)
        .addContainerGap())
    );

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addComponent(Jp_Opciones, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
    );
    layout.setVerticalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addComponent(Jp_Opciones, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
    );

    pack();
  }// </editor-fold>//GEN-END:initComponents

  private void Jtf_CorreoDestinatarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Jtf_CorreoDestinatarioActionPerformed
    // TODO add your handling code here:
  }//GEN-LAST:event_Jtf_CorreoDestinatarioActionPerformed

  /**
   * @param args the command line arguments
   */
  public static void main(String args[]) {
    /* Set the Nimbus look and feel */
    //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
    /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
     */
    try {
      for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
        if ("Nimbus".equals(info.getName())) {
          javax.swing.UIManager.setLookAndFeel(info.getClassName());
          break;
        }
      }
    } catch (ClassNotFoundException ex) {
      java.util.logging.Logger.getLogger(GUI_MENU_PRINCIPAL.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    } catch (InstantiationException ex) {
      java.util.logging.Logger.getLogger(GUI_MENU_PRINCIPAL.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    } catch (IllegalAccessException ex) {
      java.util.logging.Logger.getLogger(GUI_MENU_PRINCIPAL.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    } catch (javax.swing.UnsupportedLookAndFeelException ex) {
      java.util.logging.Logger.getLogger(GUI_MENU_PRINCIPAL.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    }
    //</editor-fold>

    /* Create and display the form */
    java.awt.EventQueue.invokeLater(new Runnable() {
      public void run() {
        new GUI_MENU_PRINCIPAL().setVisible(true);
      }
    });
  }

  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.JButton Jbtn_AbrirTXT;
  private javax.swing.JButton Jbtn_AplicarAlgoritmo;
  private javax.swing.JButton Jbtn_EnviarCorreo;
  private javax.swing.JButton Jbtn_Limpiar;
  private javax.swing.JButton Jbtn_Salir;
  private javax.swing.JComboBox<String> Jcb_Algoritmo;
  private javax.swing.JComboBox<String> Jcb_Operacion;
  private javax.swing.JLabel Jl_Algoritmo;
  private javax.swing.JLabel Jl_Clave;
  private javax.swing.JLabel Jl_Entrada;
  private javax.swing.JLabel Jl_OperacionRealizar;
  private javax.swing.JLabel Jl_Salida;
  private javax.swing.JPanel Jp_Opciones;
  private java.awt.TextArea Jta_Entrada;
  private java.awt.TextArea Jta_Salida;
  private javax.swing.JTextField Jtf_Clave;
  private javax.swing.JTextField Jtf_CorreoDestinatario;
  private javax.swing.JLabel jLabel1;
  // End of variables declaration//GEN-END:variables
}
