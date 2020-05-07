/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ventanas;

import java.sql.*;
import clases.Conexion;
import java.awt.Color;
//import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;
import java.util.*;
import java.math.BigDecimal;
import java.math.RoundingMode;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Element;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Font;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author Adrian
 */
public class NuevaFactura extends javax.swing.JFrame{
    
    String user, nombre_legal_cliente_factura, nif_cliente_factura, direccion_cliente_factura;
    public static String cliente_factura;
    Calendar fecha = Calendar.getInstance();
    String dia = Integer.toString(fecha.get(Calendar.DATE));
    String mes = Integer.toString(fecha.get(Calendar.MONTH)+1);
    String annio = Integer.toString(fecha.get(Calendar.YEAR));
    
    String cadena_fecha = annio + "-" + mes + "-" + dia;

    int num_factura;
    int ID_cliente_factura;
    boolean num_factura_repetido = false;
    boolean factura_guardada  = false;
    
    double subtotal, impuestos, total;
    String impuestos_string;
    
    
    
    DefaultTableModel model = new DefaultTableModel(); //Acceso a todos los métodos necesarios para modificar datos en su interior

    /**
     * Creates new form NuevaFactura
     */
    public NuevaFactura() {
        initComponents();
        
        user = Login.user;
        
        cliente_factura = SeleccionarClienteFactura.cliente_factura;
        if(cliente_factura.equals("")){
            cliente_factura = GestionarClientes.cliente_update;
            try {
                Connection cn = Conexion.conectar();
                PreparedStatement pst = cn.prepareStatement(
                    "select * from cliente where Nombre = '" + cliente_factura + "'");
                ResultSet rs = pst.executeQuery();
                if(rs.next()){
                    ID_cliente_factura = rs.getInt("id_cliente");
                }
                cn.close();
            } catch (SQLException e) {
                System.err.println("Error al obtener el ID del cliente. " + e);
                JOptionPane.showMessageDialog(null, "ERROR al obtener el ID del cliente.\nContacte con el administrador del sistema");
            }
        }else{
            try {
                Connection cn = Conexion.conectar();
                PreparedStatement pst = cn.prepareStatement(
                    "select * from cliente where Nombre = '" + cliente_factura + "'");
                ResultSet rs = pst.executeQuery();
                if(rs.next()){
                    ID_cliente_factura = rs.getInt("id_cliente");
                }
                cn.close();
            } catch (SQLException e) {
                System.err.println("Error al obtener el ID del cliente. " + e);
                JOptionPane.showMessageDialog(null, "ERROR al obtener el ID del cliente.\nContacte con el administrador del sistema");
            }
            
        }
        
        num_factura = Integer.parseInt(JOptionPane.showInputDialog("Introduce numero de factura"));
        try {
            Connection cn = Conexion.conectar();
            PreparedStatement pst = cn.prepareStatement(
                    "select * from factura where num_factura = '" + num_factura + "'");
            ResultSet rs = pst.executeQuery();
            if(rs.next()){
                this.dispose();
                
                JOptionPane.showMessageDialog(null, "Ya existe una factura con el número de factura introducido.\n"
                        + "Por favor, vuelva a intentarlo con otro número de factura");
                num_factura_repetido = true;
                
                
            }else{
                setSize(950,600);
                setResizable(false);
                setTitle("Factura para " + cliente_factura + " - Sesión de " + user);
                setLocationRelativeTo(null);
                setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
                jLabel_titulo.setText("Nueva Factura - [" + cliente_factura + "]");
                jTextField_numFactura.setText(String.valueOf(num_factura));
                jLabel_subtotal.setText("Subtotal: ");
                jLabel_impuestos.setText("Impuestos ");
                jLabel_total.setText("Total: ");

                ImageIcon wallpaper = new ImageIcon("src/images/wallpaperPrincipal.jpg");
                Icon icono = new ImageIcon(wallpaper.getImage().getScaledInstance(jLabel_Wallpaper.getWidth(),
                        jLabel_Wallpaper.getHeight(), java.awt.Image.SCALE_DEFAULT));

                jLabel_Wallpaper.setIcon(icono);
                
                jTable_albaranes = new JTable(model);
                jScrollPane1.setViewportView(jTable_albaranes);
                model.addColumn("Nº ALBARÁN");
                model.addColumn("DESCRIPCIÓN");
                model.addColumn("PRECIO");
                this.repaint();



                int tipo_impuesto_cmb = cmb_impuestos.getSelectedIndex()+1;
                String impuestos_string;
                if(tipo_impuesto_cmb==1){
                    impuestos_string = "IGIC (7%)";
                }else{
                    impuestos_string = "Libre de impuestos";
                }

                try {
                    Connection cn2 = Conexion.conectar();
                    PreparedStatement pst2 = cn2.prepareStatement(
                        "insert into factura values(?, ?, ?, ?, ?, ?)");
                    pst2.setInt(1, num_factura);
                    pst2.setString(2, cadena_fecha);
                    pst2.setString(3, impuestos_string);
                    pst2.setDouble(4, 0.0);
                    pst2.setDouble(5, 0.0);
                    pst2.setInt(6, ID_cliente_factura);

                    pst2.executeUpdate();
                    cn2.close();
                    pst2.close();




                } catch (SQLException e) {
                    System.err.println("Error al iniciar factura. " + e);
                    JOptionPane.showMessageDialog(null, "Error al iniciar factura.\nContace con el administrador del sistema");
                }
                
            }
            cn.close();
            pst.close();
        } catch (SQLException e) {
            System.err.println("Error al comprobar si existe un numero de factura igual. " + e);
            JOptionPane.showMessageDialog(null, "Error al comprobar si existe un numero de factura igual.\n"
                    + "Contacte con el administrador del sistema");
        }
        
        
        
    }
    
    /*@Override
    public java.awt.Image getIconImage(){
        java.awt.Image retValue = Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("images/iconoERP_1.png"));
        return retValue;
    }*/
    
    public void eliminarDatos(){
        System.out.println("Datos eliminados");
        try {
            Connection cn = Conexion.conectar();
            PreparedStatement pst = cn.prepareStatement(
                "delete from factura where num_factura = '" + num_factura + "'");
            pst.executeUpdate();
            cn.close();
            pst.close();
        } catch (SQLException e) {
            System.err.println("Error al eliminar factura. "  + e);
            JOptionPane.showMessageDialog(null, "Error al eliminar factura.\nContacte con el administrador del sistema");
        }
    }
    
    public void actualizarDatos(){
        String tipo_impuesto_string="";
        try {
            Connection cn = Conexion.conectar();
            PreparedStatement pst = cn.prepareStatement(
                    "select Precio from albaran where Num_factura = '" + num_factura + "'");
            ResultSet rs = pst.executeQuery();
            subtotal = 0.0;
            while(rs.next()){
                subtotal += rs.getDouble("Precio");
            }
            
            try {
                Connection cn2 = Conexion.conectar();
                PreparedStatement pst2 = cn2.prepareStatement(
                        "update factura set subtotal = ? where Num_factura = '" + num_factura + "'");
                pst2.setDouble(1, subtotal);
                pst2.executeUpdate();
                cn2.close();
                pst2.close();
            } catch (SQLException e) {
                System.err.println("Error al sactualizar subtotal. " + e);
                JOptionPane.showMessageDialog(null, "ERROR al actualizar subtotal."
                        + "\nContacte con el administrador.");
            }
            
            
            
        } catch (SQLException e) {
            System.err.println("Error al sumar precios de albaranes. " + e);
            JOptionPane.showMessageDialog(null, "ERROR al sumar precios de albaranes."
                    + "\nContacte con el administrador.");
        }
        try {
            Connection cn = Conexion.conectar();
            PreparedStatement pst = cn.prepareStatement(
                    "select tipo_impuesto from factura where num_factura = '" + num_factura + "'");
            ResultSet rs = pst.executeQuery();
            if(rs.next()){
                tipo_impuesto_string = rs.getString("tipo_impuesto");
                if(tipo_impuesto_string.equals("IGIC (7%)")){
                    impuestos = subtotal * 0.07;
                }else{
                    impuestos = 0.0;
                }
            }
            cn.close();
            pst.close();
            
            refrescarPantalla(tipo_impuesto_string);
        } catch (SQLException e) {
            System.err.println("Error al actualizar Datos");
        }
    }
    
    public void actualizarTotal(){
        total = subtotal + impuestos;
        
        
        try {
            Connection cn = Conexion.conectar();
            PreparedStatement pst = cn.prepareStatement(
                    "update factura set total = ? where num_factura = '" + num_factura + "'");
            
            pst.setDouble(1,total);
            pst.executeUpdate();
            cn.close();
            pst.close();
            
        } catch (SQLException e) {
            System.err.println("Error al actualizar el total. " + e);
            JOptionPane.showMessageDialog(null, "No se ha podido actualizar el total"
                    + "\nContace con el administrador del sistema");
        }
    }
    
    public void refrescarPantalla(String tipo_impuesto_string){
        jLabel_impuestos.setText("Impuestos");
        jLabel_subtotal.setText("Subtotal:");
        jLabel_total.setText("Total:");
        BigDecimal a = BigDecimal.valueOf(subtotal);
        a = a.setScale(2, RoundingMode.HALF_UP);
        jLabel_subtotal.setText(jLabel_subtotal.getText() + " " + a + " €");
        BigDecimal b = BigDecimal.valueOf(impuestos);
        b = b.setScale(2, RoundingMode.HALF_UP);
        jLabel_impuestos.setText(jLabel_impuestos.getText() + " " + tipo_impuesto_string + ": " + b  + " €");
        actualizarTotal();
        BigDecimal c = BigDecimal.valueOf(total);
        c = c.setScale(2, RoundingMode.HALF_UP);
        jLabel_total.setText(jLabel_total.getText() + " " + c + " €");
        actualizarTabla();
    }
    
    public void actualizarTabla(){
        if(!NuevoAlbaran.num_albaran_repetido)
        {
            limpiarTabla();
            try {
                Connection cn = Conexion.conectar();
                PreparedStatement pst = cn.prepareStatement(
                        "select Num_albaran, Descripcion, Precio from albaran where num_factura = '" 
                                + num_factura  + "'");
                ResultSet rs = pst.executeQuery();
                while(rs.next()){
                    Object[] fila = new Object[3];
                    for (int i = 0; i < 3; i++) {
                        fila[i] = rs.getObject(i + 1);
                    }
                    model.addRow(fila);
                }
                pst.close();
                cn.close();
            } catch (SQLException e) {
                System.err.println("Error al mostrar albaranes. " + e);
                JOptionPane.showMessageDialog(null, "ERROR al mostrar albaranes."
                        + "\nContace con el administrador del sistema.");
                dispose();
            }
        }
        
    }
    
    public void recuperarDatosCliente(){
        try {
            Connection cn = Conexion.conectar();
             PreparedStatement pst = cn.prepareStatement(
                     "select * from factura where num_factura = '" + num_factura + "'");
             ResultSet rs = pst.executeQuery();
             if(rs.next()){
                 
             }
        } catch (SQLException e) {
        }
    }
    
    public void limpiarTabla(){
        model.setRowCount(0);
    }
    
    
    
    public void comprobarNumFacturaRepetido(){
        if(this.num_factura_repetido){
            this.dispose();
        }
    }
    
    public void recopilarDatosCliente(){
        try {
            Connection cn = Conexion.conectar();
            PreparedStatement pst = cn.prepareStatement(
                    "select Nombre_legal, NIF, Dirección, email from cliente where id_cliente = '" + ID_cliente_factura + "'");
            ResultSet rs = pst.executeQuery();
            if(rs.next()){
                this.nombre_legal_cliente_factura = rs.getString("Nombre_legal");
                this.nif_cliente_factura = rs.getString("NIF");
                this.direccion_cliente_factura = rs.getString("Dirección");
            }
        } catch (SQLException e) {
            System.err.println(e);
        }
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
        jTextField_numFactura = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        cmb_impuestos = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable_albaranes = new javax.swing.JTable();
        jLabel_subtotal = new javax.swing.JLabel();
        jLabel_impuestos = new javax.swing.JLabel();
        jLabel_total = new javax.swing.JLabel();
        jButton_NuevoAlbaran = new javax.swing.JButton();
        jButton_GuardarFactura = new javax.swing.JButton();
        jLabel_Wallpaper = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setIconImage(getIconImage());
        addContainerListener(new java.awt.event.ContainerAdapter() {
            public void componentAdded(java.awt.event.ContainerEvent evt) {
                formComponentAdded(evt);
            }
        });
        addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                formFocusGained(evt);
            }
        });
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel_titulo.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel_titulo.setForeground(new java.awt.Color(255, 255, 255));
        jLabel_titulo.setText("jLabel1");
        getContentPane().add(jLabel_titulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        jLabel_NumeroFactura.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel_NumeroFactura.setForeground(new java.awt.Color(255, 255, 255));
        jLabel_NumeroFactura.setText("Nº de Factura:");
        getContentPane().add(jLabel_NumeroFactura, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 10, -1, -1));

        jTextField_numFactura.setBackground(new java.awt.Color(153, 153, 255));
        jTextField_numFactura.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        jTextField_numFactura.setForeground(new java.awt.Color(255, 255, 255));
        jTextField_numFactura.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField_numFactura.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jTextField_numFactura.setEnabled(false);
        getContentPane().add(jTextField_numFactura, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 10, 210, -1));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Tipo impuestos:");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 410, -1, -1));

        cmb_impuestos.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "IGIC (7%)", "Libre de impuestos" }));
        cmb_impuestos.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmb_impuestosItemStateChanged(evt);
            }
        });
        getContentPane().add(cmb_impuestos, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 405, -1, -1));

        jTable_albaranes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable_albaranes.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(jTable_albaranes);
        if (jTable_albaranes.getColumnModel().getColumnCount() > 0) {
            jTable_albaranes.getColumnModel().getColumn(0).setResizable(false);
            jTable_albaranes.getColumnModel().getColumn(0).setPreferredWidth(50);
        }

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 90, 950, 300));

        jLabel_subtotal.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel_subtotal.setForeground(new java.awt.Color(255, 255, 255));
        jLabel_subtotal.setText("jLabel1");
        getContentPane().add(jLabel_subtotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 410, -1, -1));

        jLabel_impuestos.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel_impuestos.setForeground(new java.awt.Color(255, 255, 255));
        jLabel_impuestos.setText("jLabel1");
        getContentPane().add(jLabel_impuestos, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 460, -1, -1));

        jLabel_total.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel_total.setForeground(new java.awt.Color(255, 255, 255));
        jLabel_total.setText("jLabel1");
        getContentPane().add(jLabel_total, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 510, -1, -1));

        jButton_NuevoAlbaran.setBackground(new java.awt.Color(153, 153, 255));
        jButton_NuevoAlbaran.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        jButton_NuevoAlbaran.setForeground(new java.awt.Color(255, 255, 255));
        jButton_NuevoAlbaran.setText("Añadir Albarán");
        jButton_NuevoAlbaran.setBorder(null);
        jButton_NuevoAlbaran.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jButton_NuevoAlbaran.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_NuevoAlbaranActionPerformed(evt);
            }
        });
        getContentPane().add(jButton_NuevoAlbaran, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 460, 210, 35));

        jButton_GuardarFactura.setBackground(new java.awt.Color(153, 153, 255));
        jButton_GuardarFactura.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        jButton_GuardarFactura.setForeground(new java.awt.Color(255, 255, 255));
        jButton_GuardarFactura.setText("Guardar Factura");
        jButton_GuardarFactura.setBorder(null);
        jButton_GuardarFactura.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jButton_GuardarFactura.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_GuardarFacturaActionPerformed(evt);
            }
        });
        getContentPane().add(jButton_GuardarFactura, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 510, 210, 35));
        getContentPane().add(jLabel_Wallpaper, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 950, 600));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton_NuevoAlbaranActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_NuevoAlbaranActionPerformed
        
        //dispose();
        NuevoAlbaran nuevoAlbaran = new NuevoAlbaran(this);
        
    }//GEN-LAST:event_jButton_NuevoAlbaranActionPerformed

    private void jButton_GuardarFacturaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_GuardarFacturaActionPerformed
        factura_guardada = true;
        Document documento = new Document();
        try {
            recopilarDatosCliente();
            String ruta = System.getProperty("user.home");
            PdfWriter.getInstance(documento, new FileOutputStream(ruta + "/Desktop/Factura" + num_factura + ".pdf"));
            
            documento.open();
            Image header = Image.getInstance("src/images/logo.jpg");
            header.scaleToFit(300, 320);
            header.setAlignment(Chunk.ALIGN_LEFT);
            
            
            Paragraph espacios = new Paragraph("\n");
            Paragraph datos_cliente = new Paragraph("Nombre Legal: " + nombre_legal_cliente_factura + "\nNIF: " + 
                    nif_cliente_factura + "\nDirección: " + direccion_cliente_factura);
            
            Paragraph p_nombre_cliente = new Paragraph(cliente_factura, FontFactory.getFont("Times New Roman", 12, Font.BOLD));
            
            String fecha_num_factura;
            fecha_num_factura = annio.charAt(annio.length()-2) + "" + annio.charAt(annio.length()-1);
            Paragraph p_n_factura = new Paragraph("FACTURA NUM. " + fecha_num_factura + "-" + num_factura, FontFactory.getFont("Times New Roman", 14, BaseColor.RED));
            
            String dia_documento = "";
            String mes_documento = "";
            if(dia.length()==1){
                dia_documento = "0" + dia;
            }else{
                dia_documento = dia;
            }
            if(mes.length() == 1){
                mes_documento = "0" + mes;
            }else{
                mes_documento = mes;
            }
            Paragraph p_fecha = new Paragraph("FECHA: " + dia_documento + "/" + mes_documento + "/" + annio, FontFactory.getFont("Times New Roman", 14, BaseColor.RED));
            
            int tipo_impuestos = cmb_impuestos.getSelectedIndex()+1;
            String tipo_impuestos_string = "";
            if(tipo_impuestos == 1){
                tipo_impuestos_string = "IGIC (7%)";
            }else{
                tipo_impuestos_string = "Libre de Impuestos";
            }
            
            
            
            PdfPCell celda_logotipo = new PdfPCell(header);
            //celda_logotipo.setPaddingLeft(0);
            /*celda_logotipo.setUseVariableBorders(true);
            celda_logotipo.setBorderColorTop(new BaseColor(255,0,0));
            celda_logotipo.setBorderColorBottom(BaseColor.RED);*/
            PdfPCell celda_vacia_header1 = new PdfPCell(new Paragraph(""));
            celda_vacia_header1.setUseVariableBorders(true);
            celda_vacia_header1.setBorderColorTop(BaseColor.WHITE);
            celda_vacia_header1.setBorderColorBottom(BaseColor.WHITE);
            celda_vacia_header1.setBorderColorLeft(BaseColor.WHITE);
            celda_vacia_header1.setBorderColorRight(BaseColor.WHITE);
            PdfPCell celda_vacia_header2 = new PdfPCell(new Paragraph(""));
            celda_vacia_header2.setUseVariableBorders(true);
            celda_vacia_header2.setBorderColorTop(BaseColor.WHITE);
            celda_vacia_header2.setBorderColorBottom(BaseColor.WHITE);
            celda_vacia_header2.setBorderColorLeft(BaseColor.WHITE);
            celda_vacia_header2.setBorderColorRight(BaseColor.WHITE);
         
            Paragraph p_datos_taller = new Paragraph("TALLER TORNO FUMERO\n"
                    + "Juan Basilio Fumero León\n"
                    + "\n"
                    + "NIF: 43607094Z\n"
                    + "C/Granadilla Esqu.C/Arona, 16\n"
                    + "38205-FINCA ESPAÑA, TENERIFE\n"
                    + "Tfno: 922654458 - 615932011\n"
                    + "Email: tallertornofumero@hotmail.com", FontFactory.getFont("Times New Roman", 10, BaseColor.BLACK));
            PdfPCell celda_datos_taller = new PdfPCell(p_datos_taller);
            //celda_datos_taller.setPaddingLeft(0);
            /*celda_datos_taller.setUseVariableBorders(true);
            celda_datos_taller.setBorderColorTop(BaseColor.WHITE);
            celda_datos_taller.setBorderColorBottom(BaseColor.WHITE);*/
            float anchos[] = {350, 150, 150, 350};
            PdfPTable tablaDatosTaller = new PdfPTable(anchos);
            tablaDatosTaller.setHorizontalAlignment(Element.ALIGN_LEFT);
            tablaDatosTaller.getDefaultCell().setBorder(Rectangle.NO_BORDER);
            tablaDatosTaller.addCell(header);
            tablaDatosTaller.addCell(celda_vacia_header1);
            tablaDatosTaller.addCell(celda_vacia_header2);
            tablaDatosTaller.addCell(p_datos_taller);
            
            
            
            
            float[] anchosTablaDatos = {100,300,100};
            PdfPTable tablaDatos = new PdfPTable(anchosTablaDatos);
            Paragraph p_n_albaran = new Paragraph("Nº ALBARÁN", FontFactory.getFont("Times New Roman", 14, BaseColor.RED));
            p_n_albaran.setAlignment(Element.ALIGN_CENTER);
            Paragraph p_desc_albaran = new Paragraph("                    "
                    + "DESCRIPCIÓN", FontFactory.getFont("Times New Roman", 14, BaseColor.RED));
            p_desc_albaran.setAlignment(Element.ALIGN_CENTER);
            Paragraph p_precio_albaran = new Paragraph("    PRECIO", FontFactory.getFont("Times New Roman", 14, BaseColor.RED));
            p_precio_albaran.setAlignment(Element.ALIGN_CENTER);
            
            tablaDatos.addCell(p_n_albaran);
            tablaDatos.addCell(p_desc_albaran);
            tablaDatos.addCell(p_precio_albaran);
            
            
            try {
                Connection cn = Conexion.conectar();
                PreparedStatement pst = cn.prepareStatement(
                        "select Num_albaran, Descripcion, Precio from albaran where num_factura = '" + num_factura + "'");
                ResultSet rs = pst.executeQuery();
                if(rs.next()){
                    do{
                        tablaDatos.addCell(rs.getString(1));
                        tablaDatos.addCell(rs.getString(2));
                        
                        BigDecimal a = BigDecimal.valueOf(rs.getDouble(3));
                        a = a.setScale(2, RoundingMode.HALF_UP);
                        String precio_albaran = String.valueOf(a);
                        tablaDatos.addCell(precio_albaran + " €");
                    }while(rs.next());
                }
            } catch (SQLException e) {
                System.err.println("");
               
            }
            
            BigDecimal subtotalDocumento = BigDecimal.valueOf(subtotal);
            subtotalDocumento = subtotalDocumento.setScale(2, RoundingMode.HALF_UP);
            
            BigDecimal impuestosDocumento = BigDecimal.valueOf(impuestos);
            impuestosDocumento = impuestosDocumento.setScale(2, RoundingMode.HALF_UP);
            
            BigDecimal totalDocumento = BigDecimal.valueOf(total);
            totalDocumento = totalDocumento.setScale(2, RoundingMode.HALF_UP);
            
            
            PdfPCell celda_vacia = new PdfPCell(new Paragraph(""));
            celda_vacia.setBorder(Rectangle.NO_BORDER);
            
            
            Paragraph texto_celda_final_titulos = new Paragraph("\nSubtotal" +  
                    "\nImpuestos: " + tipo_impuestos_string + 
                    "\n\nTotal");
            
            PdfPCell celdaFinalTitulos = new PdfPCell(texto_celda_final_titulos);
            
            
            //celdaFinalTitulos.setBorder(Rectangle.NO_BORDER);
            
            PdfPCell celdaFinalDatos = new PdfPCell(new Paragraph("\n" + subtotalDocumento + " €\n" + impuestosDocumento + " €\n\n" +
                    totalDocumento + " €"));
            //celdaFinalTitulos.setColspan(2);
            
            float[] anchos_tabla_importe = {150,120,60};
            PdfPTable tablaImporte = new PdfPTable(anchos_tabla_importe);
            
            tablaImporte.addCell(celda_vacia);
            tablaImporte.addCell(celdaFinalTitulos);
            tablaImporte.addCell(celdaFinalDatos);
            
            documento.add(espacios);
            documento.add(tablaDatosTaller);
            documento.add(espacios);
            documento.add(datos_cliente);
            documento.add(espacios);
            documento.add(p_nombre_cliente);
            documento.add(espacios);
            documento.add(p_n_factura);
            documento.add(espacios);
            documento.add(p_fecha);
            documento.add(espacios);
            documento.add(tablaDatos);
            documento.add(espacios);
            documento.add(tablaImporte);
            documento.close();
            JOptionPane.showMessageDialog(null, "Factura guardada correctamente.");
            
            //CONTINUAR POR AQUÍ
        } catch (Exception e) {
            System.err.println(e);
            JOptionPane.showInputDialog("El sistema no puede crear la ruta\n"
                    + "Contacte con el administrador del sistema");
            
        }
        
        dispose();

    }//GEN-LAST:event_jButton_GuardarFacturaActionPerformed

    private void formFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_formFocusGained

    }//GEN-LAST:event_formFocusGained

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        //comprobarNumFacturaRepetido();
        actualizarDatos();
        
    }//GEN-LAST:event_formWindowActivated

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        if(!factura_guardada){
           AdvertenciaCancelarFactura advertenciaCancelarFactura = new AdvertenciaCancelarFactura(this);
           advertenciaCancelarFactura.setVisible(true); 
        }
        
    }//GEN-LAST:event_formWindowClosing

    private void cmb_impuestosItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmb_impuestosItemStateChanged
        int tipo_impuesto = cmb_impuestos.getSelectedIndex()+1;
        String tipo_impuesto_string;
        if(tipo_impuesto==1){
            tipo_impuesto_string = "IGIC (7%)";
        }else{
            tipo_impuesto_string = "Libre de impuestos";
        }
        Connection cn = Conexion.conectar();
        try {
            PreparedStatement pst = cn.prepareStatement(
                    "update factura set tipo_impuesto = ? where num_factura = '" + num_factura + "'");
            pst.setString(1, tipo_impuesto_string);
            pst.executeUpdate();
            cn.close();
            pst.close();
        } catch (SQLException e) {
            System.err.println("Error al cambiar tipo de impuesto. " + e);
            JOptionPane.showMessageDialog(null, "ERROR al cambiar tipo de impuesto.\nContacte con el administrador del sistema");
        }
        actualizarDatos();
    }//GEN-LAST:event_cmb_impuestosItemStateChanged

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        // TODO add your handling code here:
        comprobarNumFacturaRepetido();
    }//GEN-LAST:event_formWindowOpened

    private void formComponentAdded(java.awt.event.ContainerEvent evt) {//GEN-FIRST:event_formComponentAdded
        // TODO add your handling code here:
    }//GEN-LAST:event_formComponentAdded

    
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
            java.util.logging.Logger.getLogger(NuevaFactura.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(NuevaFactura.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(NuevaFactura.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(NuevaFactura.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new NuevaFactura().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> cmb_impuestos;
    private javax.swing.JButton jButton_GuardarFactura;
    private javax.swing.JButton jButton_NuevoAlbaran;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel_NumeroFactura;
    private javax.swing.JLabel jLabel_Wallpaper;
    private javax.swing.JLabel jLabel_impuestos;
    private javax.swing.JLabel jLabel_subtotal;
    private javax.swing.JLabel jLabel_titulo;
    private javax.swing.JLabel jLabel_total;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable_albaranes;
    private javax.swing.JTextField jTextField_numFactura;
    // End of variables declaration//GEN-END:variables

}
