package admission.view;

import admission.controller.DatabaseManager;
import admission.model.AdmissionList;
import admission.model.AdmissionSession;
import admission.model.AdmissionYear;
import admission.model.Campus;
import admission.model.security.Resources;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.List;
import java.util.Set;
import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.KeyStroke;
import javax.swing.text.DocumentFilter;

/**
 *
 * @author Raja Kumar & Jay Kumar
 */
public class MessageSenderInternalFrame extends javax.swing.JInternalFrame {

    public MessageSenderInternalFrame() {
        initComponents();
        
        admission.utils.Utility.hideOnEscape(this);
    }

    @Override
    public void setVisible(boolean aFlag) {
        if(aFlag) {
            this.getCampus();
            this.getAdmissionYear();
        }
        super.setVisible(aFlag);
    }
    
    public void setPrivileges(Resources privileges){
        this.privileges = privileges;
//        this.addButton.setEnabled(Coder.Decoder.booleanDecode(privileges.getAddPrivilige()));
//        this.updateButton.setEnabled(Coder.Decoder.booleanDecode(privileges.getUpdatePrivilige()));
//        this.deleteButton.setEnabled(Coder.Decoder.booleanDecode(privileges.getDeletePrivilige()));
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        admissionSessionComboBox = new javax.swing.JComboBox();
        jLabel9 = new javax.swing.JLabel();
        programTypeTextField = new javax.swing.JTextField();
        shiftTextField = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        campusComboBox = new javax.swing.JComboBox();
        jLabel11 = new javax.swing.JLabel();
        admissionYearComboBox = new javax.swing.JComboBox();
        jLabel6 = new javax.swing.JLabel();
        admissionListComboBox = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        totalCandidateTextField = new javax.swing.JTextField();
        startProcessButton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        resultTable = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        progressBar = new javax.swing.JProgressBar();

        setClosable(true);
        setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);
        setIconifiable(true);
        setTitle("Message Sender");

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Message Sender");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel5.setText("Admission Session:");

        admissionSessionComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                admissionSessionComboBoxActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel9.setText("Program Type:");

        programTypeTextField.setEditable(false);

        shiftTextField.setEditable(false);

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel10.setText("Campus:");

        campusComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                campusComboBoxActionPerformed(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel11.setText("Admission Year:");

        admissionYearComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                admissionYearComboBoxActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel6.setText("Admission List:");

        admissionListComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                admissionListComboBoxActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel2.setText("Session:");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel3.setText("Total Candidate:");

        totalCandidateTextField.setEditable(false);

        startProcessButton.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        startProcessButton.setText("Start Process");

        resultTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "SNo.", "Name", "Seat No", "Operation"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Object.class, java.lang.Integer.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        resultTable.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(resultTable);
        resultTable.getColumnModel().getColumn(0).setResizable(false);
        resultTable.getColumnModel().getColumn(0).setPreferredWidth(50);
        resultTable.getColumnModel().getColumn(1).setResizable(false);
        resultTable.getColumnModel().getColumn(1).setPreferredWidth(300);
        resultTable.getColumnModel().getColumn(2).setResizable(false);
        resultTable.getColumnModel().getColumn(3).setResizable(false);

        jLabel4.setText("Result:");

        progressBar.setStringPainted(true);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(startProcessButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, 93, Short.MAX_VALUE)
                            .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.LEADING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(campusComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(admissionListComboBox, javax.swing.GroupLayout.Alignment.LEADING, 0, 123, Short.MAX_VALUE)
                                    .addComponent(admissionYearComboBox, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(programTypeTextField, javax.swing.GroupLayout.Alignment.LEADING))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(admissionSessionComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(shiftTextField)
                                    .addComponent(totalCandidateTextField)))))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 502, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(progressBar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(campusComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10))
                .addGap(7, 7, 7)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(admissionYearComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11)
                    .addComponent(admissionSessionComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(programTypeTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(shiftTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(admissionListComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(totalCandidateTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(startProcessButton, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(progressBar, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addGap(4, 4, 4)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(16, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void admissionSessionComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_admissionSessionComboBoxActionPerformed
        // TODO add your handling code here:
        this.getAdmissionList();
    }//GEN-LAST:event_admissionSessionComboBoxActionPerformed

    private void campusComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_campusComboBoxActionPerformed
        // TODO add your handling code here:
        this.getAdmissionList();
    }//GEN-LAST:event_campusComboBoxActionPerformed

    private void admissionYearComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_admissionYearComboBoxActionPerformed
        // TODO add your handling code here:
        this.getAdmissionSession();
    }//GEN-LAST:event_admissionYearComboBoxActionPerformed

    private void admissionListComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_admissionListComboBoxActionPerformed
        // TODO add your handling code here:
        this.getTotalCandidate();
    }//GEN-LAST:event_admissionListComboBoxActionPerformed

    private void getCampus(){
        this.campusComboBox.removeAllItems();
        List<Campus> list = DatabaseManager.getData(Campus.class.getName(), "displayOrder");
        
        for (int i = 0; i < list.size(); i++) {
            this.campusComboBox.addItem(list.get(i));
        }
    }
    
    private void getAdmissionYear(){
        this.admissionYearComboBox.removeAllItems();
        List<AdmissionYear> list = DatabaseManager.getData(AdmissionYear.class.getName(), "year DESC");
        
        for (int i = 0; i < list.size(); i++) {
            this.admissionYearComboBox.addItem(list.get(i));
        }
    }
    
    private void getAdmissionSession(){
        this.admissionSessionComboBox.removeAllItems();
        
        AdmissionYear ay = (AdmissionYear) this.admissionYearComboBox.getSelectedItem();
        if(ay == null) return;
        
        Set set = ay.getAdmissionSessions();
        if(set == null) return;
        
        Object[] toArray = set.toArray();
        
        for (int i = 0; i < toArray.length; i++) {
            this.admissionSessionComboBox.addItem(toArray[i]);
        }
    }

    private void getAdmissionList(){
//        this.admissionListComboBox.removeAllItems();
//        
//        Campus campus = (Campus) this.campusComboBox.getSelectedItem();
//        AdmissionSession as = (AdmissionSession) this.admissionSessionComboBox.getSelectedItem();
//        if(as == null || campus == null) return;
//        
//        Set set = campus.getAdmissionLists();
//        if(set == null) return;
//        
//        Object[] toArray = set.toArray();
//        for (int i = 0; i < toArray.length; i++) {
//            AdmissionList ob = (AdmissionList) toArray[i];
//            if(as.getAdmissionLists().contains(ob)){
//                this.admissionListComboBox.addItem(ob);
//            }
//        }
    }
    
    private void getTotalCandidate(){
        AdmissionList al = (AdmissionList) this.admissionListComboBox.getSelectedItem();
        if(al == null) return;
        
        this.totalCandidateTextField.setText(String.valueOf(al.getAdmissionListDetailses().size()));
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox admissionListComboBox;
    private javax.swing.JComboBox admissionSessionComboBox;
    private javax.swing.JComboBox admissionYearComboBox;
    private javax.swing.JComboBox campusComboBox;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField programTypeTextField;
    private javax.swing.JProgressBar progressBar;
    private javax.swing.JTable resultTable;
    private javax.swing.JTextField shiftTextField;
    private javax.swing.JButton startProcessButton;
    private javax.swing.JTextField totalCandidateTextField;
    // End of variables declaration//GEN-END:variables
    private DocumentFilter documentFilter;
    private Resources privileges;
    private boolean b;
}
