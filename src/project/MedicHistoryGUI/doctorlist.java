/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package project.MedicHistoryGUI;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.FlowLayout;
import javax.swing.BoxLayout;
import java.awt.Dimension;
import java.awt.BorderLayout;
//import java.awt.Box;
import javax.swing.Box;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.GridLayout;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import project.database.DatabaseConnection;
import javax.swing.BorderFactory;
import javax.swing.border.Border;
import javax.swing.JScrollPane;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.awt.GridBagConstraints;
import javax.swing.*;
import java.awt.*;
import java.sql.*;

/**
 *
 * @author HP
 */
public class doctorlist extends javax.swing.JFrame {

    /**jPanel1.setLayout(new GridLayout(0, 1));
     * Creates new form doctorlist
     */
    ArrayList<String> appointmentIds = new ArrayList<>();
                
               HashSet<String> doctorIds = new HashSet<>();
                ArrayList<time.Pair<String, String,String, String,String, String>> timestampDataPairs = new ArrayList<>();
//   Creates new form timelineGUI
                 String patientID;
    public doctorlist(String patientID)  {
        this.patientID=patientID;
       initComponents();
         jPanel1.setLayout(new GridLayout(3,3,20,20));
    /**
     * Creates new form timelineGUI
     */
  
        
        try (Connection connection = DatabaseConnection.getConnection()) {
//            System.out.println("Getting Connection");
        Statement statement = connection.createStatement();
        
            // Execute the query
            String query = "SELECT appointmentID,doctorID FROM current_appointment WHERE patientID = " + patientID;
            
            ResultSet resultSet = statement.executeQuery(query);

            // Process the ResultSet and populate the ArrayList
            while (resultSet.next()) {
                String appointmentId = resultSet.getString("appointmentID");
                        String doctorId = resultSet.getString("doctorID");
                appointmentIds.add(appointmentId);
                doctorIds.add(doctorId);
            }
            
           
        
        }
            catch (SQLException ex) {
            System.out.println(ex);
        }
        
        setResizable(false); 
        
        //print in j label
        try (Connection connection = DatabaseConnection.getConnection()) {
            
             Statement statement = connection.createStatement();

            // Execute the query to fetch data
            

//            initComponents();
            // Process the ResultSet and build a string representation of the data
            int i=0;
             for (String element : doctorIds) {
//                 System.out.println(element);
       String query = "SELECT * FROM doctor where doctorID= '"+element+"'"; // Modify query according to your table
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                // Assuming your table has columns named 'column1', 'column2', etc.
                String column1Value = resultSet.getString("doctorID");
                String column2Value = resultSet.getString("name");
                String column3Value = resultSet.getString("specialization");
                dynamicCell(column2Value,column3Value,column1Value);
                dynamicCell(column2Value,column3Value,column1Value);
                dynamicCell(column2Value,column3Value,column1Value);
                dynamicCell(column2Value,column3Value,column1Value);
                dynamicCell(column2Value,column3Value,column1Value);
                dynamicCell(column2Value,column3Value,column1Value);
                dynamicCell(column2Value,column3Value,column1Value);
                
            }++i;}
             
             
            
           
            String nameq="Select name from patient where patientID="+patientID;
            ResultSet resultSet = statement.executeQuery(nameq);
            while (resultSet.next()) {
               
            String patientName = resultSet.getString("name");
            PatientNameF.setText(patientName);
        
            }

       
    } catch (SQLException ex) {
            System.out.println(ex);
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

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        PatientNameF = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jPanel1.setLayout(new java.awt.GridLayout(1, 0));

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        PatientNameF.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        PatientNameF.setText("jLabel1");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel2.setText("Medic History");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                        .addComponent(PatientNameF, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(PatientNameF, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(17, 17, 17))
        );

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Doctors Consulted");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 764, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(18, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 507, Short.MAX_VALUE)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

     private void dynamicCell(String name,String specialization,String Did)//for creating the dynamic cells having info about patients 
    {   
        
        javax.swing.JPanel cell;
        cell = new javax.swing.JPanel();
        cell.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
//        javax.swing.JLabel celltxt;
//        celltxt=new javax.swing.JLabel();
//        celltxt.setText("HELLLO");
        
        javax.swing.JLabel PatientNameField = new javax.swing.JLabel();
        javax.swing.JLabel PatientAddressField = new javax.swing.JLabel();
//        javax.swing.JLabel LastVisited = new javax.swing.JLabel();
        javax.swing.JLabel PatientPhoneNo = new javax.swing.JLabel();
      
        
        PatientNameField.setFont(new java.awt.Font("Segoe UI", 1, 18));
        PatientNameField.setForeground(Color.white);// NOI18N
        PatientNameField.setText(name);
//        System.out.println(name);

        PatientAddressField.setFont(new java.awt.Font("Segoe UI", 0, 14));
        // NOI18N
        PatientAddressField.setForeground(Color.white);
        PatientAddressField.setText("Specialization: "+specialization);


        PatientPhoneNo.setFont(new java.awt.Font("Segoe UI", 0, 14));
        PatientPhoneNo.setForeground(Color.white);// NOI18N
        PatientPhoneNo.setText("Doctor ID: "+Did);

       
        

        javax.swing.GroupLayout cellLayout = new javax.swing.GroupLayout(cell);
        cell.setLayout(cellLayout);
        cell.setBackground(new java.awt.Color(102,102,255));
        cell.setForeground(Color.white);
        cellLayout.setHorizontalGroup(
            cellLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cellLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(cellLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(cellLayout.createSequentialGroup()
                        .addGroup(cellLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            
                            .addComponent(PatientAddressField)
                            .addComponent(PatientNameField))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(cellLayout.createSequentialGroup()
                        .addComponent(PatientPhoneNo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        
                        .addGap(47, 47, 47))))
        );
        cellLayout.setVerticalGroup(
            cellLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cellLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(PatientNameField)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(PatientAddressField)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                
                .addGap(18, 18, 18)
                .addGroup(cellLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(PatientPhoneNo)
                    )
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1.add(cell);
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
            java.util.logging.Logger.getLogger(doctorlist.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(doctorlist.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(doctorlist.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(doctorlist.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
//                new doctorlist().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel PatientNameF;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    // End of variables declaration//GEN-END:variables
}
