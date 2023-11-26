package vista;

import controlador.*;
import modelo.*;
import java.io.File;
import java.io.FileFilter;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import com.jtattoo.plaf.acryl.AcrylLookAndFeel;
import javax.swing.SwingWorker;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * Clase que representa la interfaz gráfica del menú principal de la aplicación.
 * 
 * @author Eduardo Rojas Gomez
 * @author Dylan Montiel Zuniga
 * @version 5.0
 */
public class GUI_MENU_PRINCIPAL extends javax.swing.JFrame {

  /**
   * Instancia de la clase Controlador_GUI_MENU_PRINCIPAL
   */    
  private Controlador_GUI_MENU_PRINCIPAL controlador_GUI_MENU_PRINCIPAL;  
  /**
   * Instancia de la clase CifradoRSA
   */  
  private CifradoRSA cifradoRSA;
  
  private static int countRSA= 0;

  /**
   * Constructor de la clase `GUI_MENU_PRINCIPAL`.
   */  
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

  /**
   * Abre un archivo de texto y carga su contenido en el área de entrada.
   */  
  public void abrirTXT() {
    JFileChooser fileChooser = new JFileChooser() {
      public void addChoosableFileFilter(FileFilter filter) {
        // Evitar agregar filtros adicionales
      }

      public void setFileFilter(FileFilter filter) {
        // Ignorar cualquier filtro que se intente establecer
      }
    };

    FileNameExtensionFilter filter = new FileNameExtensionFilter("Archivos de texto", "txt");
    fileChooser.setAcceptAllFileFilterUsed(false); // Desactiva el filtro "Todos los archivos"
    fileChooser.addChoosableFileFilter(filter);
    fileChooser.setFileFilter(filter);

    int resultado = fileChooser.showOpenDialog(this);

    if (resultado == JFileChooser.APPROVE_OPTION) {
      File archivoSeleccionado = fileChooser.getSelectedFile();
      String contenido = ManejoArchivos.leerArchivo(archivoSeleccionado.getAbsolutePath());
      Jta_Entrada.setText(contenido);
    }
  }  

  /**
   * Aplica el algoritmo de cifrado o descifrado seleccionado y muestra el resultado en el área de salida.
   */  
  public void aplicarAlgoritmo() {
    if (Jta_Entrada.getText().isEmpty()) {
      JOptionPane.showMessageDialog(this, "Debes escribir un texto o abrir un archivo para aplicar algún algorítmo.", "Error", JOptionPane.ERROR_MESSAGE);                
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

      case "Cifrado por código telefónico" -> {    
        CifradoTelefonico cifradoTelefonico = new CifradoTelefonico();
        
        if ("Cifrado".equals(operacion)) {
          contenido = cifradoTelefonico.cifrar(contenido);
        } else {
          contenido = cifradoTelefonico.descifrar(contenido);
        }        
        Jta_Salida.setText(contenido);
      } 

      case "Cifrado por Codificacion Binaria" -> {    
        CifradoBinario cifradoBinario = new CifradoBinario();
        
        if ("Cifrado".equals(operacion)) {
          contenido = cifradoBinario.cifrar(contenido);
          
          if (contenido == null){
            JOptionPane.showMessageDialog(this, "Ha ocurrido un error, por favor verifica los datos ingresados y la seleccion del algoritmo.", "Error", JOptionPane.ERROR_MESSAGE); 
            return;         
          }
          
        } else {
          contenido = cifradoBinario.descifrar(contenido);
          if (contenido == null){
            JOptionPane.showMessageDialog(this, "Ha ocurrido un error, por favor verifica los datos ingresados y la seleccion del algoritmo.", "Error", JOptionPane.ERROR_MESSAGE); 
            return;         
          }          
        }        
        Jta_Salida.setText(contenido);
      } 

      case "Cifrado RSA" -> {    
        
        if (countRSA == 0) {
          cifradoRSA = new CifradoRSA();
          countRSA++;
        }
        
        if ("Cifrado".equals(operacion)) {
          contenido = cifradoRSA.cifrar(contenido);
          if (contenido == null){
            System.out.println("Error");
            JOptionPane.showMessageDialog(this, "Ha ocurrido un error, por favor verifica los datos ingresados y la seleccion del algoritmo.", "Error", JOptionPane.ERROR_MESSAGE); 
            return;          
          }          
        } else {
          contenido = cifradoRSA.descifrar(contenido);
          if (contenido == null){
            System.out.println("Error");
            JOptionPane.showMessageDialog(this, "Ha ocurrido un error, por favor verifica los datos ingresados y la seleccion del algoritmo.", "Error", JOptionPane.ERROR_MESSAGE); 
            return;          
          }          
        }        
        Jta_Salida.setText(contenido);
      }  
      
      case "Cifrado Triple DES" -> {    
        CifradoTripleDES cifradoTripleDES = new CifradoTripleDES();
        
        if ("Cifrado".equals(operacion)) {
          contenido = cifradoTripleDES.cifrado(contenido);
          if (contenido == null){
            System.out.println("Error");
            JOptionPane.showMessageDialog(this, "Ha ocurrido un error, por favor verifica los datos ingresados y la seleccion del algoritmo.", "Error", JOptionPane.ERROR_MESSAGE); 
            return;          
          }          
        } else {
          contenido = cifradoTripleDES.descifrado(contenido);
          if (contenido == null){
            System.out.println("Error");
            JOptionPane.showMessageDialog(this, "Ha ocurrido un error, por favor verifica los datos ingresados y la seleccion del algoritmo.", "Error", JOptionPane.ERROR_MESSAGE); 
            return;          
          }          
        }        
        Jta_Salida.setText(contenido);
      }  

      case "Cifrado AES" -> {   
        
        CifradoAES cifradoTelefonico = new CifradoAES();
        
        if ("Cifrado".equals(operacion)) {
          contenido = cifradoTelefonico.cifrado(contenido);
          if (contenido == null){
            System.out.println("Error");
            JOptionPane.showMessageDialog(this, "Ha ocurrido un error, por favor verifica los datos ingresados y la seleccion del algoritmo.", "Error", JOptionPane.ERROR_MESSAGE); 
            return;
          }          
        } else {
          contenido = cifradoTelefonico.descifrado(contenido);
          if (contenido == null){
            System.out.println("Error");
            JOptionPane.showMessageDialog(this, "Ha ocurrido un error, por favor verifica los datos ingresados y la seleccion del algoritmo.", "Error", JOptionPane.ERROR_MESSAGE); 
            return;            
          }
        }        
        Jta_Salida.setText(contenido);
      }       
    }
  }

  /**
   * Valida la clave para el cifrado por llave.
   * 
   * @param clave Clave ingresada.
   * @return true si la clave es válida, false de lo contrario.
   */  
  public boolean validarClaveCifradoPorLlave(String clave) {
    return !clave.trim().isEmpty();
  }

  /**
   * Valida la clave para el cifrado por sustitución Vigenère.
   * 
   * @param clave Clave ingresada.
   * @return true si la clave es válida, false de lo contrario.
   */  
  public boolean validarClaveSustitucionVigenere(String clave) {
    return !clave.trim().isEmpty() && clave.matches("\\d{2}") && clave.matches("\\d+");
  }  
  
  /**
   * Limpia las áreas de entrada y salida en la interfaz gráfica.
   */  
  public void limpiarPantallas() {
    Jta_Entrada.setText(null);
    Jta_Salida.setText(null);
  }

  /**
   * Habilita o deshabilita el campo de clave según el algoritmo seleccionado.
   */  
  public void habilitarClave() {
    String algoritmo = Jcb_Algoritmo.getSelectedItem().toString().trim();
    if ("Cifrado por Llave".equals(algoritmo) || 
        "Sustitución Vigenére".equals(algoritmo)) {
        Jtf_Clave.setEnabled(true);
    } else {
        Jtf_Clave.setEnabled(false);
    }    
  }

  /**
   * Envía un correo con el mensaje de salida
   */  
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
      SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
        @Override
        protected Void doInBackground() throws Exception {
          // Operación de envío de correo
          cuentaCorreo.enviarCorreo(correoDestinatario, "Criptografía de Mensaje: " + operacion + "; " + algoritmo, contenido);
          return null;
        }

        @Override
        protected void done() {
          // Este método se ejecuta en el hilo de despacho de eventos cuando el SwingWorker ha terminado
          JOptionPane.showMessageDialog(GUI_MENU_PRINCIPAL.this, "Correo enviado con éxito.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        }
      };
      worker.execute();
    } else {
      JOptionPane.showMessageDialog(this, "La dirección de correo electrónico no es válida.", "Error", JOptionPane.ERROR_MESSAGE);   
    }      
  }

  /**
   * Valida si la terminacion del correo
   * @param correo Correo para enviar el email
   */  
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

    jToolBar1 = new javax.swing.JToolBar();
    jDialog1 = new javax.swing.JDialog();
    scrollPane1 = new java.awt.ScrollPane();
    Jl_Salida = new javax.swing.JLabel();
    Jbtn_Salir = new javax.swing.JButton();
    Jbtn_EnviarCorreo = new javax.swing.JButton();
    Jta_Salida = new java.awt.TextArea();
    Jbtn_Limpiar = new javax.swing.JButton();
    Jl_Clave = new javax.swing.JLabel();
    Jtf_Clave = new javax.swing.JTextField();
    jLabel1 = new javax.swing.JLabel();
    Jtf_CorreoDestinatario = new javax.swing.JTextField();
    Jl_OperacionRealizar = new javax.swing.JLabel();
    Jcb_Operacion = new javax.swing.JComboBox<>();
    Jl_Algoritmo = new javax.swing.JLabel();
    Jcb_Algoritmo = new javax.swing.JComboBox<>();
    Jl_Entrada = new javax.swing.JLabel();
    Jta_Entrada = new java.awt.TextArea();
    Jbtn_AbrirTXT = new javax.swing.JButton();
    Jbtn_AplicarAlgoritmo = new javax.swing.JButton();
    jSeparator1 = new javax.swing.JSeparator();
    jSeparator2 = new javax.swing.JSeparator();

    jToolBar1.setRollover(true);

    javax.swing.GroupLayout jDialog1Layout = new javax.swing.GroupLayout(jDialog1.getContentPane());
    jDialog1.getContentPane().setLayout(jDialog1Layout);
    jDialog1Layout.setHorizontalGroup(
      jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGap(0, 400, Short.MAX_VALUE)
    );
    jDialog1Layout.setVerticalGroup(
      jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGap(0, 300, Short.MAX_VALUE)
    );

    setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
    setBackground(new java.awt.Color(255, 255, 255));
    setName("frameGeneral"); // NOI18N

    Jl_Salida.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
    Jl_Salida.setText("Salida");

    Jbtn_Salir.setText("Salir");

    Jbtn_EnviarCorreo.setText("Enviar por correo");

    Jta_Salida.setBackground(new java.awt.Color(255, 255, 255));
    Jta_Salida.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
    Jta_Salida.setEditable(false);
    Jta_Salida.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N

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

    Jl_OperacionRealizar.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
    Jl_OperacionRealizar.setText("Operación a realizar:");

    Jcb_Operacion.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Cifrado", "Descifrado" }));

    Jl_Algoritmo.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
    Jl_Algoritmo.setText("Algoritmo:");

    Jcb_Algoritmo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Cifrado César", "Cifrado por Llave", "Sustitución Vigenére", "Palabra Inversa", "Mensaje Inverso", "Cifrado por código telefónico", "Cifrado por Codificacion Binaria", "Cifrado RSA", "Cifrado Triple DES", "Cifrado AES" }));

    Jl_Entrada.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
    Jl_Entrada.setText("Entrada");

    Jta_Entrada.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N

    Jbtn_AbrirTXT.setText("Abrir archivo TXT");

    Jbtn_AplicarAlgoritmo.setText("Aplicar algorítmo");
    Jbtn_AplicarAlgoritmo.setActionCommand("Aplicar algoritmo");

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(layout.createSequentialGroup()
        .addGap(23, 23, 23)
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addComponent(Jl_Salida)
          .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(layout.createSequentialGroup()
              .addComponent(jLabel1)
              .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
              .addComponent(Jtf_CorreoDestinatario, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
              .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
              .addComponent(Jbtn_EnviarCorreo)
              .addGap(154, 154, 154)
              .addComponent(Jbtn_Salir))
            .addGroup(layout.createSequentialGroup()
              .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(Jta_Salida, javax.swing.GroupLayout.PREFERRED_SIZE, 698, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                  .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                    .addComponent(Jl_Entrada)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Jbtn_AbrirTXT))
                  .addComponent(Jta_Entrada, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 698, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createSequentialGroup()
                  .addComponent(Jl_OperacionRealizar)
                  .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                  .addComponent(Jcb_Operacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                  .addGap(41, 41, 41)
                  .addComponent(Jl_Algoritmo)
                  .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                  .addComponent(Jcb_Algoritmo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                  .addGap(30, 30, 30)
                  .addComponent(Jl_Clave)
                  .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                  .addComponent(Jtf_Clave, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)))
              .addGap(13, 13, 13)))
          .addGroup(layout.createSequentialGroup()
            .addGap(217, 217, 217)
            .addComponent(Jbtn_AplicarAlgoritmo)
            .addGap(18, 18, 18)
            .addComponent(Jbtn_Limpiar)))
        .addContainerGap(10, Short.MAX_VALUE))
      .addComponent(jSeparator1)
      .addComponent(jSeparator2, javax.swing.GroupLayout.Alignment.TRAILING)
    );
    layout.setVerticalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(layout.createSequentialGroup()
        .addGap(30, 30, 30)
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(Jl_OperacionRealizar)
          .addComponent(Jcb_Operacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
          .addComponent(Jl_Algoritmo)
          .addComponent(Jcb_Algoritmo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
          .addComponent(Jl_Clave)
          .addComponent(Jtf_Clave, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        .addGap(18, 18, 18)
        .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
          .addComponent(Jl_Entrada)
          .addComponent(Jbtn_AbrirTXT))
        .addGap(12, 12, 12)
        .addComponent(Jta_Entrada, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addGap(17, 17, 17)
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(Jbtn_AplicarAlgoritmo)
          .addComponent(Jbtn_Limpiar))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(Jl_Salida)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(Jta_Salida, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 16, Short.MAX_VALUE)
        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(jLabel1)
          .addComponent(Jtf_CorreoDestinatario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
          .addComponent(Jbtn_EnviarCorreo)
          .addComponent(Jbtn_Salir))
        .addGap(26, 26, 26))
    );

    setSize(new java.awt.Dimension(760, 615));
    setLocationRelativeTo(null);
  }// </editor-fold>//GEN-END:initComponents

  private void Jtf_CorreoDestinatarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Jtf_CorreoDestinatarioActionPerformed
    // TODO add your handling code here:
  }//GEN-LAST:event_Jtf_CorreoDestinatarioActionPerformed
 
  /**
   * Este método inicia la aplicación GUI_MENU_PRINCIPAL.
   *
   * @param args[] the command line arguments
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
 
    try {
      UIManager.setLookAndFeel(new AcrylLookAndFeel());
    } catch (Exception e) {
      e.printStackTrace();  
    }
    
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
  private java.awt.TextArea Jta_Entrada;
  private java.awt.TextArea Jta_Salida;
  private javax.swing.JTextField Jtf_Clave;
  private javax.swing.JTextField Jtf_CorreoDestinatario;
  private javax.swing.JDialog jDialog1;
  private javax.swing.JLabel jLabel1;
  private javax.swing.JSeparator jSeparator1;
  private javax.swing.JSeparator jSeparator2;
  private javax.swing.JToolBar jToolBar1;
  private java.awt.ScrollPane scrollPane1;
  // End of variables declaration//GEN-END:variables

}
