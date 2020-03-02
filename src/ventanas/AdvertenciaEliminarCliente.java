/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ventanas;

import clases.Conexion;
import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;

/**
 *
 * @author Adrian
 */
public class AdvertenciaEliminarCliente extends javax.swing.JFrame {
    
    String user, cliente_delete;
    int ID;
    static boolean eliminado = false;

    /**
     * Creates new form AdvertenciaEliminarCliente
     */
    public AdvertenciaEliminarCliente() {
        initComponents();
        
        user = Login.user;
        cliente_delete = GestionarClientes.cliente_update;
        
        setSize(600,350);
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Eliminar cliente " + cliente_delete + " - Sesión de " + user);
        setLocationRelativeTo(null);
        
        ImageIcon wallpaper = new ImageIcon("src/images/wallpaperPrincipal.jpg");
        Icon icono = new ImageIcon(wallpaper.getImage().getScaledInstance(jLabel_Wallpaper.getWidth(),
                jLabel_Wallpaper.getHeight(), Image.SCALE_DEFAULT));
        
        jLabel_Wallpaper.setIcon(icono);
        this.repaint();
        
        jLabel_advertencia.setText("Si eliminas al cliente " + cliente_delete);
        jLabel_advertencia1.setText("No podrás recuperar su información.");
        jLabel_advertencia2.setText("Esta decisión es innamovible");
        jLabel_advertencia3.setText("¿Desea eliminar a " + cliente_delete + " DEFINITIVAMENTE?");
        
    }
    @Override
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

        jLabel1 = new javax.swing.JLabel();
        jLabel_advertencia = new javax.swing.JLabel();
        jLabel_advertencia1 = new javax.swing.JLabel();
        jLabel_advertencia2 = new javax.swing.JLabel();
        jLabel_advertencia3 = new javax.swing.JLabel();
        jButton_EliminarCliente = new javax.swing.JButton();
        jButton_Cancelar = new javax.swing.JButton();
        jLabel_Wallpaper = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setIconImage(getIconImage());
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setBackground(java.awt.Color.red);
        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel1.setForeground(java.awt.Color.red);
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("¡¡ATENCIÓN!!");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 30, -1, -1));

        jLabel_advertencia.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel_advertencia.setForeground(new java.awt.Color(255, 255, 255));
        jLabel_advertencia.setText("jLabel2");
        getContentPane().add(jLabel_advertencia, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 95, -1, -1));

        jLabel_advertencia1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel_advertencia1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel_advertencia1.setText("jLabel2");
        getContentPane().add(jLabel_advertencia1, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 120, -1, -1));

        jLabel_advertencia2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel_advertencia2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel_advertencia2.setText("jLabel2");
        getContentPane().add(jLabel_advertencia2, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 145, -1, -1));

        jLabel_advertencia3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel_advertencia3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel_advertencia3.setText("jLabel2");
        getContentPane().add(jLabel_advertencia3, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 180, -1, -1));

        jButton_EliminarCliente.setBackground(new java.awt.Color(204, 0, 51));
        jButton_EliminarCliente.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        jButton_EliminarCliente.setForeground(new java.awt.Color(255, 255, 255));
        jButton_EliminarCliente.setText("Sí, Eliminar Cliente Definitivamente");
        jButton_EliminarCliente.setBorder(null);
        jButton_EliminarCliente.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jButton_EliminarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_EliminarClienteActionPerformed(evt);
            }
        });
        getContentPane().add(jButton_EliminarCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 250, 240, 35));

        jButton_Cancelar.setBackground(new java.awt.Color(153, 153, 255));
        jButton_Cancelar.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        jButton_Cancelar.setForeground(new java.awt.Color(255, 255, 255));
        jButton_Cancelar.setText("Volver Atrás");
        jButton_Cancelar.setBorder(null);
        jButton_Cancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_CancelarActionPerformed(evt);
            }
        });
        getContentPane().add(jButton_Cancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 250, 210, 35));
        getContentPane().add(jLabel_Wallpaper, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 600, 350));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton_EliminarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_EliminarClienteActionPerformed
        try {
            Connection cn = Conexion.conectar();
            PreparedStatement pst = cn.prepareStatement(
                "delete from cliente where Nombre = '" + cliente_delete + "'");
            int res = pst.executeUpdate();
            
            if(res==0){
                
                JOptionPane.showMessageDialog(null, "No se ha podido eliminar el cliente");
            }else{
                eliminado = true;
                JOptionPane.showMessageDialog(null, "Cliente Eliminado");
                dispose();
            }
            
        } catch (Exception e) {
        }
        
    }//GEN-LAST:event_jButton_EliminarClienteActionPerformed

    private void jButton_CancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_CancelarActionPerformed
        dispose();
    }//GEN-LAST:event_jButton_CancelarActionPerformed

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
            java.util.logging.Logger.getLogger(AdvertenciaEliminarCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AdvertenciaEliminarCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AdvertenciaEliminarCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AdvertenciaEliminarCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AdvertenciaEliminarCliente().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton_Cancelar;
    private javax.swing.JButton jButton_EliminarCliente;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel_Wallpaper;
    private javax.swing.JLabel jLabel_advertencia;
    private javax.swing.JLabel jLabel_advertencia1;
    private javax.swing.JLabel jLabel_advertencia2;
    private javax.swing.JLabel jLabel_advertencia3;
    // End of variables declaration//GEN-END:variables
}