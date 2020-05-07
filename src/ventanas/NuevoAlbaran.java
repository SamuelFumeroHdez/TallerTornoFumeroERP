/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ventanas;

import java.sql.*;
import clases.Conexion;
import java.awt.Image;
import java.awt.Toolkit;
import java.sql.Connection;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;

/**
 *
 * @author Adrian
 */
public class NuevoAlbaran extends javax.swing.JFrame {

    /**
     * Creates new form NuevoAlbaran
     */
    
    String user;
    String cliente_albaran;
    static int num_albaran;
    double precio;
    String descripcion;
    NuevaFactura nf;
    boolean cancelar = true;
    static boolean num_albaran_repetido = false;
    public NuevoAlbaran(NuevaFactura nuevaFactura) {
        num_albaran_repetido = false;
        initComponents();
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        
        user = Login.user;
        cliente_albaran = nuevaFactura.cliente_factura;
        nf = nuevaFactura;
        
        num_albaran = Integer.parseInt(JOptionPane.showInputDialog(null, "Introduzca el número del Albarán"));
        try { //Consultar en la BD si existe un albarán con el mismo número
            Connection cn = Conexion.conectar();
            PreparedStatement pst = cn.prepareStatement(
                    "select * from albaran where Num_albaran = '" + num_albaran + "'");
            ResultSet rs = pst.executeQuery();
            if(rs.next()){ //Si hay coincidencia, NO crear nuevo albaran
                
                JOptionPane.showMessageDialog(null, "Numero de albarán repetido.");
                num_albaran_repetido = true;
                
                nuevaFactura.setVisible(true);
                comprobarNumAlbaranRepetido();
            }else{
                this.setVisible(true);
                jLabel_titulo.setText(cliente_albaran);
                setSize(950,475);
                setResizable(false);
                setTitle("Albarán para " + cliente_albaran + " - Sesión de " + user);
                setLocationRelativeTo(null);
                setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

                ImageIcon wallpaper = new ImageIcon("src/images/wallpaperPrincipal.jpg");
                Icon icono = new ImageIcon(wallpaper.getImage().getScaledInstance(jLabel_Wallpaper.getWidth(),
                        jLabel_Wallpaper.getHeight(), Image.SCALE_DEFAULT));

                jLabel_Wallpaper.setIcon(icono);
                this.repaint();
                
                precio = 0.0;
                descripcion = "";
                jTextField_numFactura.setText(String.valueOf(num_albaran));
                jTextField_precio.setText("");
                jTextArea_Descripcion.setText("");
                
                crearAlbaran();
                actualizarPantallaAlbaran();
            }
            pst.close();
            cn.close();
        } catch (SQLException e) {
            System.err.println("Error al comprobar número de albarán. " + e);
            JOptionPane.showMessageDialog(null, "Error al comprobar número de albarán.\nContacte con el administrador del sistema");
        }
         
    }
    
   /* @Override
    public Image getIconImage(){
        Image retValue = Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("images/iconoERP_1.png"));
        return retValue;
    }*/
    
    public void crearAlbaran(){
        
        try {
            Connection cn = Conexion.conectar();
            PreparedStatement pst = cn.prepareStatement(
                    "insert into albaran values(?, ?, ?, ?)");

            pst.setInt(1, num_albaran);
            pst.setDouble(2,precio);
            pst.setString(3, descripcion);
            pst.setInt(4, nf.num_factura);
            pst.executeUpdate();
            
        } catch (SQLException e) {
            System.out.println("Error al actualziar albarán. " + e);
            JOptionPane.showMessageDialog(null, "ERROR al actualziar albarán.\n"
                    + "Contacte con el administrador del sistema");
        }
    }
    
    public void actualizarAlbaran(){
        descripcion = jTextArea_Descripcion.getText();
        precio = Double.parseDouble(jTextField_precio.getText());
        try {
            Connection cn = Conexion.conectar();
            PreparedStatement pst = cn.prepareStatement(
                    "update albaran set Precio = ?, Descripcion = ? where Num_albaran = '" + num_albaran + "'");
            
            pst.setDouble(1, precio);
            pst.setString(2, descripcion);
            pst.executeUpdate();
            
        } catch (SQLException e) {
            System.out.println("Error al actualziar albarán. " + e);
            JOptionPane.showMessageDialog(null, "ERROR al actualziar albarán.\n"
                    + "Contacte con el administrador del sistema");
        }
        
    }
    
    public void actualizarPantallaAlbaran(){
         try {
            Connection cn = Conexion.conectar();
            PreparedStatement pst = cn.prepareStatement(
                    "select * from albaran where Num_albaran = '" + num_albaran + "'");
            ResultSet rs = pst.executeQuery();
            
            if(rs.next()){
                precio = rs.getDouble("Precio");
                descripcion = rs.getString("Descripcion");
                jTextField_precio.setText(String.valueOf(precio));
                jTextArea_Descripcion.setText(descripcion);
            }
            
        } catch (SQLException e) {
            System.out.println("Error al actualziar albarán. " + e);
            JOptionPane.showMessageDialog(null, "ERROR al actualziar albarán.\n"
                    + "Contacte con el administrador del sistema");
        }
        
        
    }
    private void comprobarNumAlbaranRepetido(){
        if(num_albaran_repetido){
            this.dispose();
        }
    }

    private NuevoAlbaran() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel_titulo = new javax.swing.JLabel();
        jLabel_NumeroFactura = new javax.swing.JLabel();
        jTextField_precio = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea_Descripcion = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jButton_Actualizar = new javax.swing.JButton();
        jTextField_numFactura = new javax.swing.JTextField();
        jLabel_Wallpaper = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setIconImage(getIconImage());
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel_titulo.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel_titulo.setForeground(new java.awt.Color(255, 255, 255));
        jLabel_titulo.setText("jLabel1");
        getContentPane().add(jLabel_titulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        jLabel_NumeroFactura.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel_NumeroFactura.setForeground(new java.awt.Color(255, 255, 255));
        jLabel_NumeroFactura.setText("Nº de Albarán:");
        getContentPane().add(jLabel_NumeroFactura, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 10, -1, -1));

        jTextField_precio.setBackground(new java.awt.Color(153, 153, 255));
        jTextField_precio.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        jTextField_precio.setForeground(new java.awt.Color(255, 255, 255));
        jTextField_precio.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField_precio.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        getContentPane().add(jTextField_precio, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 350, 210, -1));

        jTextArea_Descripcion.setColumns(20);
        jTextArea_Descripcion.setRows(5);
        jScrollPane1.setViewportView(jTextArea_Descripcion);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, 910, 170));

        jLabel1.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Descripción:");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, -1, -1));

        jLabel2.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Precio:");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 320, -1, -1));

        jButton_Actualizar.setBackground(new java.awt.Color(153, 153, 255));
        jButton_Actualizar.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        jButton_Actualizar.setForeground(new java.awt.Color(255, 255, 255));
        jButton_Actualizar.setText("Guardar Albarán");
        jButton_Actualizar.setBorder(null);
        jButton_Actualizar.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jButton_Actualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_ActualizarActionPerformed(evt);
            }
        });
        getContentPane().add(jButton_Actualizar, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 360, 210, 35));

        jTextField_numFactura.setBackground(new java.awt.Color(153, 153, 255));
        jTextField_numFactura.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        jTextField_numFactura.setForeground(new java.awt.Color(255, 255, 255));
        jTextField_numFactura.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField_numFactura.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jTextField_numFactura.setEnabled(false);
        getContentPane().add(jTextField_numFactura, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 10, 210, -1));
        getContentPane().add(jLabel_Wallpaper, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 950, 600));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton_ActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_ActualizarActionPerformed
        actualizarAlbaran();
        cancelar = false;
        nf.setVisible(true);
        dispose();
    }//GEN-LAST:event_jButton_ActualizarActionPerformed

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        if(cancelar && !num_albaran_repetido){
            try {
            Connection cn = Conexion.conectar();
            PreparedStatement pst = cn.prepareStatement(
                    "delete from albaran where Num_albaran = '" + num_albaran + "'");
            pst.executeUpdate();
            pst.close();
            cn.close();
            } catch (SQLException e) {
                System.err.println("ERROR al eliminar albarán. " + e);
                JOptionPane.showMessageDialog(null, "ERROR al eliminar albarán."
                        + "\nContacte con el administrador del sistema");
            }
        }
        
        nf.setVisible(true);
    }//GEN-LAST:event_formWindowClosed
    private void formWindowOpened(java.awt.event.WindowEvent evt) {                                  
        // TODO add your handling code here:
        comprobarNumAlbaranRepetido();
    } 
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
            java.util.logging.Logger.getLogger(NuevoAlbaran.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(NuevoAlbaran.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(NuevoAlbaran.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(NuevoAlbaran.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new NuevoAlbaran().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton_Actualizar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel_NumeroFactura;
    private javax.swing.JLabel jLabel_Wallpaper;
    private javax.swing.JLabel jLabel_titulo;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea_Descripcion;
    private javax.swing.JTextField jTextField_numFactura;
    private javax.swing.JTextField jTextField_precio;
    // End of variables declaration//GEN-END:variables
}
