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
import java.awt.Color;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
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
import java.awt.GridBagLayout;
//import javax.awt.Border.layout;
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

/**
 *
 * @author HP
 */
public class time extends javax.swing.JFrame {
    static class Pair<K, V, M , N, O, P> {
        private K key;
        private V aid;
        private M did;
        private N dgns;
        private O prs;
        private P drn;

        public Pair(K key,V aid,M did,N dgns,O prs,P drn) {
            this.key = key;
            this.aid = aid;
            this.did = did;
            this.dgns =dgns;
            this.prs = prs;
            this.drn = drn;
        }

        public K getKey() {
            return key;
        }

        public V getaid() {
            return aid;
        }
        
        public M getdid() {
            return did;
        }
        
        public N getdgns() {
            return dgns;
        }
        
        public O getprs() {
            return prs;
        }
        
        public P getdrn() {
            return drn;
        }
        
    }
static class TimestampDataComparator implements Comparator<Pair<String, String,String, String,String, String>> {
    @Override
    public int compare(Pair<String, String,String, String,String, String> pair1, Pair<String, String,String, String,String, String> pair2) {
        System.out.println("Comparing pair1: " + pair1 + " with pair2: " + pair2);
        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime dateTime1 = LocalDateTime.parse(pair1.getKey(), formatter);
        LocalDateTime dateTime2 = LocalDateTime.parse(pair2.getKey(), formatter);
        
        System.out.println("Parsed dateTime1: " + dateTime1 + ", Parsed dateTime2: " + dateTime2);
        
        int comparisonResult = dateTime1.compareTo(dateTime2);
        System.out.println("Comparison result: " + comparisonResult);
        
        return comparisonResult*-1;
    }
}
    
    
    
    
    
      javax.swing.JDialog dialog;
      ArrayList<String> appointmentIds = new ArrayList<>();
                ArrayList<String> doctorIds=new ArrayList<>();
                ArrayList<String> date=new ArrayList<>();
                ArrayList<String> diagnosis=new ArrayList<>();
                ArrayList<String> prescription=new ArrayList<>();
                ArrayList<String> drname=new ArrayList<>();
                ArrayList<Pair<String, String,String, String,String, String>> timestampDataPairs = new ArrayList<>();
//   Creates new form timelineGUI
                 String patientID;
    public time(String patientID)  {
        this.patientID=patientID;
       
         
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
        initComponents();
        //print in j label
        try (Connection connection = DatabaseConnection.getConnection()) {
            
             Statement statement = connection.createStatement();

            // Execute the query to fetch data
            

//            initComponents();
            // Process the ResultSet and build a string representation of the data
            int i=0;
             for (String element : appointmentIds) {
//                 System.out.println(element);
       String query = "SELECT * FROM appointment where appointmentID= '"+element+"'"; // Modify query according to your table
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                // Assuming your table has columns named 'column1', 'column2', etc.
                String column1Value = resultSet.getString("appointment_date");
                String column2Value = resultSet.getString("diagnosis");
                String column3Value = resultSet.getString("prescription");
                date.add(column1Value);
                diagnosis.add(column2Value);
                prescription.add(column3Value);
               
            }++i;}
             
             
            
           
            String nameq="Select name from patient where patientID="+patientID;
            ResultSet resultSet = statement.executeQuery(nameq);
            while (resultSet.next()) {
               
            String patientName = resultSet.getString("name");
            PatientNameF.setText(patientName);
        
            }
         for(String element : doctorIds){
         String query = "SELECT name FROM doctor where doctorID= '"+element+"'"; // Modify query according to your table
            resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                
                drname.add(resultSet.getString("name"));
            }
    }
for (int j=0;j<appointmentIds.size();j++) {
                 
                 timestampDataPairs.add(new Pair<>(date.get(j),appointmentIds.get(j),doctorIds.get(j),diagnosis.get(j),prescription.get(j),drname.get(j)));
             }
             System.out.println("Size of timestampDataPairs: " + timestampDataPairs.size());
             Collections.sort(timestampDataPairs, new TimestampDataComparator());
             int j=0;
             for (Pair<String, String,String, String,String, String> pair : timestampDataPairs) {
            appointmentIds.set(j,pair.getaid());
            date.set(j,pair.getKey());
            doctorIds.set(j,pair.getdid());
            diagnosis.set(j,pair.getdgns());
            prescription.set(j,pair.getprs());
            drname.set(j,pair.getdrn());
            ++j;
        }
       generateDynamicLabels(appointmentIds.size());
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

        jPanel2 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        PatientNameF = new javax.swing.JLabel();
        jButton6 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(153, 153, 153), 1, true));

        jLabel9.setFont(new java.awt.Font("Helvetica Neue", 1, 24)); // NOI18N
        jLabel9.setText("Medic History");

        PatientNameF.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        PatientNameF.setText("PatientName");

        jButton6.setText("Home");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jButton9.setBackground(new java.awt.Color(102, 102, 255));
        jButton9.setForeground(new java.awt.Color(255, 255, 255));
        jButton9.setText("Doctors Consulted");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setText("Timeline");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jButton9, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(8, 8, 8)
                                .addComponent(PatientNameF, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 5, Short.MAX_VALUE)))
                .addContainerGap())
            .addComponent(jButton6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(58, 58, 58)
                .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 203, Short.MAX_VALUE)
                .addComponent(PatientNameF)
                .addGap(19, 19, 19))
        );

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 657, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("APPOINTMENT ID");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("DATE OF APPOINTMENT");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("CLICK FOR DETAILS");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(52, 52, 52)
                        .addComponent(jLabel3)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 25, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE, false)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
     private void showPatientPan(String patientID) throws SQLException{
        // Create an instance of the patient panel GUI
        System.out.println("Hello");
         try {
        PatientPanelUI patientPanel = new PatientPanelUI(patientID);
        patientPanel.setVisible(true);
        this.dispose(); // or this.setVisible(false);
    } catch (Exception e) {
        e.printStackTrace();
    }

//         Show the patient panel GUI
System.out.println("Hello");
        // Dispose or hide the login GUI
        this.dispose(); // or this.setVisible(false);
    }
    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
try {
            showPatientPan(patientID);
        } catch (SQLException ex) {
            Logger.getLogger(timelineGUI.class.getName()).log(Level.SEVERE, null, ex);
        }        // TODO add your handling code here:
    }//GEN-LAST:event_jButton6ActionPerformed
private void showdocList(String patientID) throws SQLException{
        // Create an instance of the patient panel GUI
        doctorlist docpanel = new doctorlist(patientID);

        // Show the patient panel GUI
//        System.out.println("hello3");
        docpanel.setVisible(true);
//System.out.println("hello4");
        // Dispose or hide the login GUI
        this.dispose(); // or this.setVisible(false);
    }
    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
try {
            showdocList(patientID);
        } catch (SQLException ex) {
            Logger.getLogger(PatientPanelUI.class.getName()).log(Level.SEVERE, null, ex);
        }         // TODO add your handling code here:
    }//GEN-LAST:event_jButton9ActionPerformed


private void generateDynamicLabels(int numberOfLabels) {
    // Remove existing components
    jPanel3.removeAll();
    jPanel3.setLayout(new BoxLayout(jPanel3, BoxLayout.Y_AXIS));
//    System.out.println(appointmentIds.size());
    int i;
    for (i = 0; i < numberOfLabels; i++) {
        final int index=i;
        // Create a custom JPanel for each label
        javax.swing.JPanel labelPanel = new javax.swing.JPanel();
        labelPanel.setLayout(new FlowLayout());
        labelPanel.setBackground(new java.awt.Color(102, 102, 255));
        // Add two text fields
        javax.swing.JLabel textField1 = new javax.swing.JLabel();
        javax.swing.JLabel textField2 = new javax.swing.JLabel();
        textField1.setPreferredSize(new Dimension(200, 50));
        textField2.setPreferredSize(new Dimension(200, 50));
        textField1.setFont(new java.awt.Font("Segoe UI", 4, 16));
        textField2.setFont(new java.awt.Font("Segoe UI", 4, 16));
        textField1.setForeground(Color.white);
        textField2.setForeground(Color.white);
        Border border = BorderFactory.createBevelBorder(3, Color.lightGray, Color.yellow); // Example: white line border
         textField1.setBorder(border);
          textField2.setBorder(border);
        String t=appointmentIds.get(i);
        textField1.setText(t);
        textField2.setText(date.get(i));
        labelPanel.add(textField1);
        labelPanel.add(textField2);
        
        // Add a button
        javax.swing.JButton button = new javax.swing.JButton("Button " + i);
        textField1.setBackground(Color.white); 
      
        button.setPreferredSize(new Dimension(120, 50));
        button.setText("Details");
        // Add action listener to the button
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Create and show the pop-out panel
                showPopOutPanel(index);
            }
        });
        
        labelPanel.add(button);
        labelPanel.add(Box.createHorizontalStrut(10));
        jPanel3.add(labelPanel);
    }
    
    // Refresh the panel to reflect changes
    jPanel3.revalidate();
    jPanel3.repaint();
}



    
// Method to create and show the pop-out panel
private void showPopOutPanel(int k) {
    // Create a custom JPanel for the pop-out panel
    javax.swing.JPanel popOutPanel = new javax.swing.JPanel();
    popOutPanel.setLayout(new BorderLayout()); // Use BorderLayout for better organization

    // Create a JPanel to contain the labels vertically
    javax.swing.JPanel labelsPanel = new javax.swing.JPanel();
    labelsPanel.setLayout(new GridBagLayout()); // Use GridBagLayout for flexible sizing
    
    // Create GridBagConstraints for labelsPanel
    GridBagConstraints gbc = new GridBagConstraints();
    gbc.gridx = 0;
    gbc.gridy = 0;
    gbc.anchor = GridBagConstraints.WEST; // Align components to the left

    // Add labels to the labelsPanel
    javax.swing.JLabel label1 = new javax.swing.JLabel("Doctor Name: " + drname.get(k));
    label1.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 14)); // Set font style
    label1.setForeground(new java.awt.Color(0,0,0)); // Set text color
    label1.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Add padding
    labelsPanel.add(label1, gbc);
    
    gbc.gridy++; // Move to the next row
    javax.swing.JLabel label2 = new javax.swing.JLabel("Diagnosis: " + diagnosis.get(k));
    label2.setFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 14)); // Set font style
    label2.setForeground(new java.awt.Color(0,0,0)); // Set text color
    label2.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Add padding
    labelsPanel.add(label2, gbc);
    
    gbc.gridy++; // Move to the next row
    javax.swing.JLabel label3 = new javax.swing.JLabel("Prescription: " + prescription.get(k));
    label3.setFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 14)); // Set font style
    label3.setForeground(new java.awt.Color(0,0,0)); // Set text color
    label3.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Add padding
    labelsPanel.add(label3, gbc);
    
    // Add the labelsPanel to the popOutPanel
    popOutPanel.add(labelsPanel, BorderLayout.CENTER);
    
    // Create a JPanel for the button
    javax.swing.JPanel buttonPanel = new javax.swing.JPanel();
    buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER)); // Align button to the center
    
    // Create a button to close the pop-out panel
    javax.swing.JButton closeButton = new javax.swing.JButton("Close");
    closeButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Close the dialog when the button is clicked
            dialog.dispose();
        }
    });
    
    buttonPanel.add(closeButton); // Add the closeButton to buttonPanel
    // Add buttonPanel to popOutPanel at SOUTH position
    popOutPanel.add(buttonPanel, BorderLayout.SOUTH);
    
    // Create a dialog to show the pop-out panel
    dialog = new javax.swing.JDialog();
    dialog.setContentPane(popOutPanel); // Set popOutPanel as content pane of the dialog
    dialog.pack(); // Size the dialog to fit the components
    dialog.setLocationRelativeTo(null); // Center the dialog on the screen
    dialog.setVisible(true); // Show the dialog
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
            java.util.logging.Logger.getLogger(timelineGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(timelineGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(timelineGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(timelineGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
//generateDynamicButtons(3);
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
//                new time("124").setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel PatientNameF;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    // End of variables declaration//GEN-END:variables
}
