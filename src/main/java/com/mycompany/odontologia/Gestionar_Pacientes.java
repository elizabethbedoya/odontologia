/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.odontologia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;








/**
 *
 * @author ADMIN
 */
public class Gestionar_Pacientes extends javax.swing.JFrame {
     conexion co = new conexion();
     Connection conet;
     Statement st; 
     ResultSet rs;
     DefaultTableModel modelo;
    int idc;
     
    /**
     * Creates new form Gestionar_Pacientes
     */
    public Gestionar_Pacientes() {
        initComponents();
      consultar();
       
    }
      public void limpiar(){
      
      while (modelo.getRowCount()>0){
           modelo.removeRow(0);
      } 
        
      }
      
      public void limpiarcampos(){
      txtid.setText(""); 
      txtnombres.setText("");
      txtapellidos.setText("");
      
      jdfecha.setDate(null);
      
      buttonGroup1.clearSelection();
      
      
      }
   
   
       public void consultar(){
       String sql= "select * from pacientes";
        
          try
          {
             conet=co.getConnection();
             st=conet.createStatement();
             rs=st.executeQuery(sql); 
             Object[] persona=new Object[5];
             modelo = (DefaultTableModel) jtregistro.getModel();
             while(rs.next()){
              persona [0]=rs.getInt("PacIdentificacion");
              persona [1]=rs.getString("PacNombres");
              persona [2]=rs.getString("PacApellidos");
              persona [3]=rs.getString("PacFechaNacimiento");
              persona [4]=rs.getString("PacSexo");
              modelo.addRow(persona);
             }
             jtregistro.setModel(modelo);
             
        }catch(Exception e){
           
          }
          
       }
      
     public void agregar(){
         try {
            // primera parte obtener la info
            
            int id = Integer.parseInt(txtid.getText()); // 123
            String nombres = txtnombres.getText(); // eliza
            String apellidos = txtapellidos.getText();  // jhdbd
            
            //obtener la fecha
             java.util.Date fechaseleccionada = jdfecha.getDate();
             java.sql.Date fechanacimiento = new java.sql.Date(fechaseleccionada.getTime());
             
             //obtener el sexo 
             String sexo = rbmasculino.isSelected()? "M" : "F";
             
             //segunda parte guardar la info en la base de datos
              String sql = "insert into pacientes (PacIdentificacion,PacNombres,PacApellidos,PacFechaNacimiento,PacSexo)values (?,?,?,?,?)";
             conet=co.getConnection();
             PreparedStatement ps = conet.prepareStatement(sql);
             ps.setInt(1, id);
             ps.setString(2, nombres);
             ps.setString(3, apellidos);
             ps.setDate(4, fechanacimiento);
             ps.setString(5, sexo);
             ps.executeUpdate();
             JOptionPane.showMessageDialog(null,"Se ha realizado el registro");
             limpiarcampos();
             limpiar();
             consultar();
             
         } catch (Exception e) {
             JOptionPane.showMessageDialog(null,"No se pudo ingresar"); 
             System.out.println(""+ e);
         }
     
     
     
     }
     
     public void actualizar(){ //metodo actualizar
     try{
     int fila = jtregistro.getSelectedRow(); 
     if(fila== -1){
       JOptionPane.showMessageDialog(null,"Seleccione una fila");
      }else {
             int id = Integer.parseInt(jtregistro.getValueAt(fila,0).toString()); //
            String nombres = txtnombres.getText(); // eliza
            String apellidos = txtapellidos.getText();  // jhdbd
            
            //obtener la fecha
             java.util.Date fechaseleccionada = jdfecha.getDate();
             java.sql.Date fechanacimiento = new java.sql.Date(fechaseleccionada.getTime());
             
             //obtener el sexo 
             String sexo = rbmasculino.isSelected()? "M" : "F";
             
             String sql = "update pacientes SET PacNombres=?,PacApellidos=?,PacFechaNacimiento=?,PacSexo=? WHERE PacIdentificacion=?";
              Connection conet= co.getConnection();
              PreparedStatement pst = conet.prepareStatement(sql);
              pst.setString(1,nombres);
              pst.setString(2, apellidos);
              pst.setDate(3, fechanacimiento);
              pst.setString(4, sexo);
              pst.setInt(5, id);
              int filasactualizadas = pst.executeUpdate();
              if(filasactualizadas > 0){
                JOptionPane.showMessageDialog(null,"Se ha actualizado el registro ");
                limpiarcampos();
                limpiar();
                consultar();
              }else{
              JOptionPane.showMessageDialog(null,"No Se ha actualizado el registro");
              }
              
              }
     }catch(Exception e){
         System.err.println("el error es: "+e);
     } 
     }
     
     public void eliminar(){
     try {
     int fila = jtregistro.getSelectedRow();
     if(fila== -1){
       JOptionPane.showMessageDialog(null,"Seleccione una fila");
       return;
      }
     int id = Integer.parseInt(jtregistro.getValueAt(fila,0).toString());
     int confirmacion = JOptionPane.showConfirmDialog(null,"Esta seguro de eliminar el registro?","Confirmar eliminacion",JOptionPane.YES_NO_OPTION);
      if (confirmacion==JOptionPane.YES_OPTION){
       String sql = "delete from pacientes where PacIdentificacion=?";
      Connection conet= co.getConnection();
     PreparedStatement pst = conet.prepareStatement(sql);
     pst.setInt(1, id);
      int filaseliminadas = pst.executeUpdate();
       if(filaseliminadas > 0){
         JOptionPane.showMessageDialog(null,"Registro eliminado ");
        limpiarcampos();
        limpiar();
        consultar();
              }else{
            JOptionPane.showMessageDialog(null,"No se pudo eliminar el registro ");
       }
       }
     }catch(Exception e){
         System.out.println("error"+e);
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

        buttonGroup1 = new javax.swing.ButtonGroup();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jCalendar1 = new com.toedter.calendar.JCalendar();
        jPopupMenu1 = new javax.swing.JPopupMenu();
        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtid = new javax.swing.JTextField();
        txtnombres = new javax.swing.JTextField();
        txtapellidos = new javax.swing.JTextField();
        rbfemenino = new javax.swing.JRadioButton();
        rbmasculino = new javax.swing.JRadioButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtregistro = new javax.swing.JTable();
        jdfecha = new com.toedter.calendar.JDateChooser();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setText("Gestion de Pacientes");

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel3.setBackground(new java.awt.Color(204, 204, 204));

        jButton1.setBackground(new java.awt.Color(153, 255, 255));
        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton1.setText("INGRESAR");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setBackground(new java.awt.Color(153, 255, 255));
        jButton2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton2.setText("ACTUALIZAR");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setBackground(new java.awt.Color(153, 255, 255));
        jButton3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton3.setText("ELIMINAR");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setBackground(new java.awt.Color(153, 255, 255));
        jButton4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton4.setText("LIMPIAR");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        jLabel7.setText("Volver Menu Principal");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(0, 11, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addGap(14, 14, 14))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton2)
                            .addComponent(jButton4)
                            .addComponent(jButton3)
                            .addComponent(jButton1))
                        .addGap(21, 21, 21))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(65, 65, 65)
                .addComponent(jButton1)
                .addGap(33, 33, 33)
                .addComponent(jButton2)
                .addGap(31, 31, 31)
                .addComponent(jButton3)
                .addGap(32, 32, 32)
                .addComponent(jButton4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 285, Short.MAX_VALUE)
                .addComponent(jLabel7)
                .addContainerGap())
        );

        jLabel2.setText("Identificacion ");

        jLabel3.setText("Nombres");

        jLabel4.setText("Apellidos");

        jLabel5.setText("Fecha de Nacimiento");

        jLabel6.setText("Sexo");

        buttonGroup1.add(rbfemenino);
        rbfemenino.setText("Femenino");

        buttonGroup1.add(rbmasculino);
        rbmasculino.setText("Masculino");

        jtregistro.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Identificacion", "Nombres", "Apellidos", "F. Nacimiento", "Sexo"
            }
        ));
        jtregistro.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtregistroMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jtregistro);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(164, 164, 164)
                                .addComponent(jLabel1))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(64, 64, 64)
                                .addComponent(jLabel2)
                                .addGap(65, 65, 65)
                                .addComponent(txtid, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(64, 64, 64)
                                .addComponent(jLabel3)
                                .addGap(91, 91, 91)
                                .addComponent(txtnombres, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(64, 64, 64)
                                .addComponent(jLabel4)
                                .addGap(91, 91, 91)
                                .addComponent(txtapellidos, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(64, 64, 64)
                                .addComponent(jLabel5)
                                .addGap(28, 28, 28)
                                .addComponent(jdfecha, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(64, 64, 64)
                                .addComponent(jLabel6)
                                .addGap(106, 106, 106)
                                .addComponent(rbfemenino)
                                .addGap(24, 24, 24)
                                .addComponent(rbmasculino)))
                        .addGap(137, 137, 137))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 517, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(54, 54, 54)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel1)
                .addGap(48, 48, 48)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(txtid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(txtnombres, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(txtapellidos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(jdfecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel6)
                    .addComponent(rbfemenino, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(rbmasculino, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        limpiarcampos();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
      agregar();       
    
       
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jtregistroMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtregistroMouseClicked
       //
       txtid.setEditable(false);
       //obtener el indice de la fila
       int fila = jtregistro.getSelectedRow();
       //verificar que se haya seleccionado una fila
       if(fila== -1){
       JOptionPane.showMessageDialog(null,"Seleccione una fila");
      }else {
       idc = Integer.parseInt(jtregistro.getValueAt(fila,0).toString());
       String nombres = (String) jtregistro.getValueAt(fila,1);
       String apellidos = (String) jtregistro.getValueAt(fila,2);
        
       Object fechaobj = jtregistro.getValueAt(fila,3);
       
       //si la fecha es un objecto java.sql.date
       if(fechaobj instanceof java.sql.Date){
          java.sql.Date fechasql = (java.sql.Date) fechaobj;
       // se convierte a java.util.date y se estable en el chooser
       jdfecha.setDate(new java.util.Date(fechasql.getTime()));
       } // si la fecha es un string
       else if (fechaobj instanceof String){
       try{
       // definir el formato especifico parala fecha
           SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
           // se convierte el string a un objetc date
           java.util.Date fecha = formato.parse((String) fechaobj);
           jdfecha.setDate(fecha);
       }catch (Exception e){
       
       JOptionPane.showMessageDialog(null, "Error al convertir la fecha");
       
       }
       
       }
       //obetner el texto del sexo en la columan 4
       String sexotexto = (String) jtregistro.getValueAt(fila,4);
       if(sexotexto.equalsIgnoreCase("M")){
       rbmasculino.setSelected(true);
       rbfemenino.setSelected(false);
       
       }else if(sexotexto.equalsIgnoreCase("F")){
       rbmasculino.setSelected(false);
       rbfemenino.setSelected(true);
       
       }
       txtid.setText(""+idc);
       txtnombres.setText(nombres);
       txtapellidos.setText(apellidos);
       }
    }//GEN-LAST:event_jtregistroMouseClicked

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
      actualizar();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
       eliminar();
        
    }//GEN-LAST:event_jButton3ActionPerformed

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
            java.util.logging.Logger.getLogger(Gestionar_Pacientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Gestionar_Pacientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Gestionar_Pacientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Gestionar_Pacientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Gestionar_Pacientes().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private com.toedter.calendar.JCalendar jCalendar1;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JScrollPane jScrollPane1;
    private com.toedter.calendar.JDateChooser jdfecha;
    private javax.swing.JTable jtregistro;
    private javax.swing.JRadioButton rbfemenino;
    private javax.swing.JRadioButton rbmasculino;
    private javax.swing.JTextField txtapellidos;
    private javax.swing.JTextField txtid;
    private javax.swing.JTextField txtnombres;
    // End of variables declaration//GEN-END:variables
}
