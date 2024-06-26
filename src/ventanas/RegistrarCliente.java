/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ventanas;


import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import java.sql.*;
import clases.Conexion;


/**
 *
 * @author Adrian
 */
public class RegistrarCliente extends javax.swing.JFrame {
    
    String user;

    /**
     * Creates new form RegistrarCliente
     */
    public RegistrarCliente() {
        initComponents();
        
        user = Login.user;
        setSize(630,350);
        setResizable(false);
        setLocationRelativeTo(null);
        setTitle("Registrar nuevo cliente - Sesión de " + user);
        
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        
        ImageIcon wallpaper = new ImageIcon("src/images/wallpaperPrincipal.jpg");
        Icon icono = new ImageIcon(wallpaper.getImage().getScaledInstance(jLabel_Wallpaper.getWidth(),
                jLabel_Wallpaper.getHeight(), Image.SCALE_DEFAULT));
        jLabel_Wallpaper.setIcon(icono);
        this.repaint();
        
    }
    
    /*@Override
    public Image getIconImage(){
        Image retValue = Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("images/iconoERP_1.png"));
        return retValue;
    }
    
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txt_nombre = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txt_nombre_legal = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txt_telefono = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txt_direccion = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txt_NIF = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        txt_email = new javax.swing.JTextField();
        jLabel_Wallpaper = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setIconImage(getIconImage());
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Registro de Clientes");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 10, -1, -1));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Nombre:");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, -1, -1));

        txt_nombre.setBackground(new java.awt.Color(153, 153, 255));
        txt_nombre.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        txt_nombre.setForeground(new java.awt.Color(255, 255, 255));
        txt_nombre.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_nombre.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        txt_nombre.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txt_nombreMouseClicked(evt);
            }
        });
        txt_nombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_nombreActionPerformed(evt);
            }
        });
        getContentPane().add(txt_nombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, 210, -1));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Nombre legal:");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, -1, -1));

        txt_nombre_legal.setBackground(new java.awt.Color(153, 153, 255));
        txt_nombre_legal.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        txt_nombre_legal.setForeground(new java.awt.Color(255, 255, 255));
        txt_nombre_legal.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_nombre_legal.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        txt_nombre_legal.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txt_nombre_legalMouseClicked(evt);
            }
        });
        txt_nombre_legal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_nombre_legalActionPerformed(evt);
            }
        });
        getContentPane().add(txt_nombre_legal, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 130, 210, -1));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Teléfono:");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 170, -1, -1));

        txt_telefono.setBackground(new java.awt.Color(153, 153, 255));
        txt_telefono.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        txt_telefono.setForeground(new java.awt.Color(255, 255, 255));
        txt_telefono.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_telefono.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        txt_telefono.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txt_telefonoMouseClicked(evt);
            }
        });
        txt_telefono.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_telefonoActionPerformed(evt);
            }
        });
        getContentPane().add(txt_telefono, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 190, 210, -1));

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("NIF:");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 230, -1, -1));

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Direccion:");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 50, -1, -1));

        txt_direccion.setBackground(new java.awt.Color(153, 153, 255));
        txt_direccion.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        txt_direccion.setForeground(new java.awt.Color(255, 255, 255));
        txt_direccion.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_direccion.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        txt_direccion.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txt_direccionMouseClicked(evt);
            }
        });
        txt_direccion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_direccionActionPerformed(evt);
            }
        });
        getContentPane().add(txt_direccion, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 70, 210, -1));

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Email:");
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 110, -1, -1));

        txt_NIF.setBackground(new java.awt.Color(153, 153, 255));
        txt_NIF.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        txt_NIF.setForeground(new java.awt.Color(255, 255, 255));
        txt_NIF.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_NIF.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        txt_NIF.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txt_NIFMouseClicked(evt);
            }
        });
        txt_NIF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_NIFActionPerformed(evt);
            }
        });
        getContentPane().add(txt_NIF, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 250, 210, -1));

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/add.png"))); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 170, 120, 100));

        txt_email.setBackground(new java.awt.Color(153, 153, 255));
        txt_email.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        txt_email.setForeground(new java.awt.Color(255, 255, 255));
        txt_email.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_email.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        txt_email.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txt_emailMouseClicked(evt);
            }
        });
        txt_email.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_emailActionPerformed(evt);
            }
        });
        getContentPane().add(txt_email, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 130, 210, -1));
        getContentPane().add(jLabel_Wallpaper, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 630, 350));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        int validacion = 0;
        String nombre, nombre_legal, mail, telefono, nif, direccion;
        
        nombre = txt_nombre.getText();
        nombre_legal = txt_nombre_legal.getText();
        mail = txt_email.getText();
        telefono = txt_telefono.getText();
        nif = txt_NIF.getText();
        direccion = txt_direccion.getText();
        
        if(nombre.equals("")){
            txt_nombre.setBackground(Color.red);
            validacion++;
        }
        if(nombre_legal.equals("")){
            txt_nombre_legal.setBackground(Color.red);
            validacion++;
        }
        if(mail.equals("")){
            txt_email.setBackground(Color.red);
            validacion++;
        }
        if(telefono.equals("")){
            txt_telefono.setBackground(Color.red);
            validacion++;
        }
        if(nif.equals("")){
            txt_NIF.setBackground(Color.red);
            validacion++;
        }
        if(direccion.equals("")){
            txt_direccion.setBackground(Color.red);
            validacion++;
        }
        
        if(validacion==0){
            
            try {
                Connection cn = Conexion.conectar();
                PreparedStatement pst = cn.prepareStatement(
                    "insert into cliente values (?, ?, ?, ?, ?, ?, ?)");
                pst.setInt(1, 0);
                pst.setString(2, nombre);
                pst.setString(3, nombre_legal);
                pst.setString(4, direccion);
                pst.setString(5, nif);
                pst.setString(6, telefono);
                pst.setString(7, mail);

                pst.executeUpdate();
                cn.close();
                limpiar();
                txt_NIF.setBackground(Color.GREEN);
                txt_direccion.setBackground(Color.GREEN);
                txt_email.setBackground(Color.GREEN);
                txt_nombre.setBackground(Color.GREEN);
                txt_nombre_legal.setBackground(Color.GREEN);
                txt_telefono.setBackground(Color.GREEN);

                JOptionPane.showMessageDialog(null, "Registro realizado con éxito");
                dispose();
            } catch (Exception e) {
                System.err.println("ERROR al registrar cliente. " + e);
                JOptionPane.showMessageDialog(null, "ERROR al registrar cliente.\nContacte con el administrador del sistema");
            }
        }else{
            JOptionPane.showMessageDialog(null, "Debe llenar todos los campos");
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void txt_nombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_nombreActionPerformed
        txt_nombre.setBackground(new Color(153,153,255));
    }//GEN-LAST:event_txt_nombreActionPerformed

    private void txt_nombre_legalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_nombre_legalActionPerformed
        txt_nombre_legal.setBackground(new Color(153,153,255));
    }//GEN-LAST:event_txt_nombre_legalActionPerformed

    private void txt_telefonoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_telefonoActionPerformed
        txt_telefono.setBackground(new Color(153,153,255));
    }//GEN-LAST:event_txt_telefonoActionPerformed

    private void txt_NIFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_NIFActionPerformed
        txt_NIF.setBackground(new Color(153,153,255));
    }//GEN-LAST:event_txt_NIFActionPerformed

    private void txt_direccionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_direccionActionPerformed
        txt_direccion.setBackground(new Color(153,153,255));
    }//GEN-LAST:event_txt_direccionActionPerformed

    private void txt_emailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_emailActionPerformed
        txt_email.setBackground(new Color(153,153,255));
    }//GEN-LAST:event_txt_emailActionPerformed

    private void txt_nombreMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txt_nombreMouseClicked
        txt_nombre.setBackground(new Color(153,153,255));
    }//GEN-LAST:event_txt_nombreMouseClicked

    private void txt_nombre_legalMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txt_nombre_legalMouseClicked
        txt_nombre_legal.setBackground(new Color(153,153,255));
    }//GEN-LAST:event_txt_nombre_legalMouseClicked

    private void txt_telefonoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txt_telefonoMouseClicked
        txt_telefono.setBackground(new Color(153,153,255));
    }//GEN-LAST:event_txt_telefonoMouseClicked

    private void txt_NIFMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txt_NIFMouseClicked
        txt_NIF.setBackground(new Color(153,153,255));
    }//GEN-LAST:event_txt_NIFMouseClicked

    private void txt_direccionMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txt_direccionMouseClicked
        txt_direccion.setBackground(new Color(153,153,255));
    }//GEN-LAST:event_txt_direccionMouseClicked

    private void txt_emailMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txt_emailMouseClicked
        txt_email.setBackground(new Color(153,153,255));
    }//GEN-LAST:event_txt_emailMouseClicked

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
            java.util.logging.Logger.getLogger(RegistrarCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(RegistrarCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(RegistrarCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(RegistrarCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new RegistrarCliente().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel_Wallpaper;
    private javax.swing.JTextField txt_NIF;
    private javax.swing.JTextField txt_direccion;
    private javax.swing.JTextField txt_email;
    private javax.swing.JTextField txt_nombre;
    private javax.swing.JTextField txt_nombre_legal;
    private javax.swing.JTextField txt_telefono;
    // End of variables declaration//GEN-END:variables
    public void limpiar(){
        txt_email.setText("");
        txt_nombre.setText("");
        txt_nombre_legal.setText("");
        txt_telefono.setText("");
        txt_direccion.setText("");
        txt_NIF.setText("");
    }
}
