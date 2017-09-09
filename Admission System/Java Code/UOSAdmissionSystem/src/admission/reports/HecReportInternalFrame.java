package admission.reports;

import admission.controller.DatabaseManager;
import admission.controller.JDBCDatabaseManager;
import admission.model.AdmissionYear;
import admission.model.Campus;
import admission.model.CampusCategory;
import admission.model.CredentialDetails;
import admission.model.District;
import admission.model.security.Resources;
import admission.model.ProgramType;
import admission.model.Shift;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.table.DefaultTableModel;
import admission.utils.CandidateHelper;
import admission.utils.IConstant;

/**
 *
 * @author Raja Kumar & Jay Kumar
 */
public class HecReportInternalFrame extends javax.swing.JInternalFrame {

    public HecReportInternalFrame() {
        initComponents();
        
        admission.utils.Utility.hideOnEscape(this);
        
        districtTableModel = (DefaultTableModel) districtTable.getModel();
        categoryTableModel = (DefaultTableModel) categoryTable.getModel();
    }

    @Override
    public void setVisible(boolean aFlag) {
        if(aFlag) {
            this.getAdmissionYear();
            this.getProgramType();
            this.getDistrict();
            this.getShift();
            this.getCampus();
        }
        super.setVisible(aFlag);
    }
    
    public void setPrivileges(Resources privileges){
        this.privileges = privileges;
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        fileChooser = new javax.swing.JFileChooser();
        jLabel1 = new javax.swing.JLabel();
        exportButton = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        programTypeComboBox = new javax.swing.JComboBox();
        jLabel11 = new javax.swing.JLabel();
        admissionYearComboBox = new javax.swing.JComboBox();
        backButton = new javax.swing.JButton();
        progressBar = new javax.swing.JProgressBar();
        jScrollPane1 = new javax.swing.JScrollPane();
        districtTable = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        shiftComboBox = new javax.swing.JComboBox();
        selectAllCheckBox = new javax.swing.JCheckBox();
        jScrollPane2 = new javax.swing.JScrollPane();
        categoryTable = new javax.swing.JTable();
        selectAllCheckBox1 = new javax.swing.JCheckBox();
        jLabel3 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        campusComboBox = new javax.swing.JComboBox();
        jLabel4 = new javax.swing.JLabel();
        minPercenatageTextField = new javax.swing.JTextField();
        jCheckBox1 = new javax.swing.JCheckBox();

        fileChooser.setDialogType(javax.swing.JFileChooser.SAVE_DIALOG);
        fileChooser.setFileSelectionMode(javax.swing.JFileChooser.DIRECTORIES_ONLY);
        fileChooser.setName(""); // NOI18N

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("HEC Report");

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("HEC Report");

        exportButton.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        exportButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/admission/view/images/import-export-icon.png"))); // NOI18N
        exportButton.setText("Export");
        exportButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exportButtonActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel5.setText("Program Type:");

        programTypeComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                programTypeComboBoxActionPerformed(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel11.setText("Admission Year:");

        backButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/admission/view/images/Back.png"))); // NOI18N
        backButton.setToolTipText("Back");
        backButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backButtonActionPerformed(evt);
            }
        });

        progressBar.setStringPainted(true);

        districtTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "District", "Flag"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Boolean.class
            };
            boolean[] canEdit = new boolean [] {
                false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        districtTable.setRowHeight(20);
        jScrollPane1.setViewportView(districtTable);
        if (districtTable.getColumnModel().getColumnCount() > 0) {
            districtTable.getColumnModel().getColumn(0).setResizable(false);
            districtTable.getColumnModel().getColumn(0).setPreferredWidth(300);
            districtTable.getColumnModel().getColumn(1).setResizable(false);
            districtTable.getColumnModel().getColumn(1).setPreferredWidth(40);
        }

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel2.setText("District:");

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel6.setText("Shift:");

        shiftComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                shiftComboBoxActionPerformed(evt);
            }
        });

        selectAllCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectAllCheckBoxActionPerformed(evt);
            }
        });

        categoryTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Category", "Flag"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Boolean.class
            };
            boolean[] canEdit = new boolean [] {
                false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        categoryTable.setRowHeight(20);
        jScrollPane2.setViewportView(categoryTable);
        if (categoryTable.getColumnModel().getColumnCount() > 0) {
            categoryTable.getColumnModel().getColumn(0).setResizable(false);
            categoryTable.getColumnModel().getColumn(0).setPreferredWidth(300);
            categoryTable.getColumnModel().getColumn(1).setResizable(false);
            categoryTable.getColumnModel().getColumn(1).setPreferredWidth(40);
        }

        selectAllCheckBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectAllCheckBox1ActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel3.setText("Category:");

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel12.setText("Campus:");

        campusComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                campusComboBoxActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel4.setText("Min. %:");

        minPercenatageTextField.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        minPercenatageTextField.setText("60");

        jCheckBox1.setSelected(true);
        jCheckBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(progressBar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(exportButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(backButton))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(247, 247, 247)
                                .addComponent(selectAllCheckBox))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 312, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(selectAllCheckBox1))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 312, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(admissionYearComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel5))
                            .addComponent(campusComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(10, 10, 10)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(minPercenatageTextField))
                            .addComponent(programTypeComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(shiftComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jCheckBox1))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jCheckBox1)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(campusComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel12)
                        .addComponent(jLabel4)
                        .addComponent(minPercenatageTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(admissionYearComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11)
                    .addComponent(programTypeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6)
                    .addComponent(shiftComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(backButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(progressBar, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE)
                        .addComponent(exportButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(selectAllCheckBox)
                    .addComponent(selectAllCheckBox1)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 338, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 338, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {admissionYearComboBox, minPercenatageTextField, programTypeComboBox});

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void exportButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exportButtonActionPerformed
        new Thread() {
            @Override public void run() {
                export();
            }
        }.start();
    }//GEN-LAST:event_exportButtonActionPerformed

    private void backButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backButtonActionPerformed
        // TODO add your handling code here:
        this.setVisible(false);
    }//GEN-LAST:event_backButtonActionPerformed

    private void selectAllCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectAllCheckBoxActionPerformed
        // TODO add your handling code here:
        int row = districtTableModel.getRowCount();
        for (int i = 0; i < row; i++) {
            districtTableModel.setValueAt(selectAllCheckBox.isSelected(), i, 1);
        }
    }//GEN-LAST:event_selectAllCheckBoxActionPerformed

    private void selectAllCheckBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectAllCheckBox1ActionPerformed
        // TODO add your handling code here:
        int row = categoryTableModel.getRowCount();
        for (int i = 0; i < row; i++) {
            categoryTableModel.setValueAt(selectAllCheckBox1.isSelected(), i, 1);
        }
    }//GEN-LAST:event_selectAllCheckBox1ActionPerformed

    private void campusComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_campusComboBoxActionPerformed
        // TODO add your handling code here:
        this.getCampusCategory();
    }//GEN-LAST:event_campusComboBoxActionPerformed

    private void programTypeComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_programTypeComboBoxActionPerformed
        // TODO add your handling code here:
        this.getCampusCategory();
    }//GEN-LAST:event_programTypeComboBoxActionPerformed

    private void shiftComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_shiftComboBoxActionPerformed
        // TODO add your handling code here:
        this.getCampusCategory();
    }//GEN-LAST:event_shiftComboBoxActionPerformed

    private void jCheckBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox1ActionPerformed
        // TODO add your handling code here:
        minPercenatageTextField.setEnabled(jCheckBox1.isSelected());
        shiftComboBox.setEnabled(jCheckBox1.isSelected());
    }//GEN-LAST:event_jCheckBox1ActionPerformed

    private void getCampus(){
        this.campusComboBox.removeAllItems();
        List<Campus> list = DatabaseManager.getData(Campus.class.getName(), "isMain DESC");
        
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
    
    private void getProgramType(){
        this.programTypeComboBox.removeAllItems();
        List<ProgramType> list = DatabaseManager.getData(ProgramType.class.getName(), "name");
        
        for (int i = 0; i < list.size(); i++) {
            this.programTypeComboBox.addItem(list.get(i));
        }
    }
    
    private void getShift(){
        this.shiftComboBox.removeAllItems();
        List<Shift> list = DatabaseManager.getData(Shift.class.getName(), "name DESC");
        
        for (int i = 0; i < list.size(); i++) {
            this.shiftComboBox.addItem(list.get(i));
        }
    }
    
    private void getDistrict(){
        admission.utils.Utility.removeTableRows(districtTableModel);
        
        List<District> list = DatabaseManager.getData(District.class.getName(), "name");
        
        for (int i = 0; i < list.size(); i++) {
            districtTableModel.addRow(new Object[]{list.get(i), false});
        }
    }
    
    private void getCampusCategory(){
        admission.utils.Utility.removeTableRows(categoryTableModel);
        
        Campus c = (Campus) campusComboBox.getSelectedItem();
        ProgramType pt = (ProgramType) programTypeComboBox.getSelectedItem();
        Shift shift = (Shift) shiftComboBox.getSelectedItem();
        if(c == null || pt == null || shift == null) return;
        
        List<CampusCategory> list = DatabaseManager.getCampusCategory(c.getCampusId(), pt.getProgramTypeId(), shift.getShiftId(), "displayOrder");
        
        for (int i = 0; i < list.size(); i++) {
            categoryTableModel.addRow(new Object[]{list.get(i), false});
        }
    }
    
    
    private void export() {
        if(fileChooser.showSaveDialog(this) != JFileChooser.APPROVE_OPTION) return;
        
        AdmissionYear ay = (AdmissionYear) admissionYearComboBox.getSelectedItem();
        ProgramType pt = (ProgramType) programTypeComboBox.getSelectedItem();
        Shift shift = (Shift) shiftComboBox.getSelectedItem();
        if(ay == null || pt == null || shift == null) return;
        
        String mp = minPercenatageTextField.getText();
        float minPer;
        try {
            minPer = Float.parseFloat(mp);
        } catch (NumberFormatException e) {
            admission.utils.MessageBox.info(this, "Number input");
            return;
        }
        File folder = fileChooser.getSelectedFile();
        File file = new File(folder.getAbsoluteFile() + "/HEC Report.csv");
        java.io.PrintStream out;
        try {
            out = new PrintStream(new FileOutputStream(file));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(HecReportInternalFrame.class.getName()).log(Level.SEVERE, null, ex);
            admission.utils.MessageBox.error(this, ex);
            return;
        }
        
        int detailOf = (pt.getIsBachelor()) ? IConstant.HSC : IConstant.GRADUATION;
        exportButton.setEnabled(false);
        backButton.setEnabled(false);
        
        String campusCategoryIds = this.getCategoryIdStringLine();
        if(campusCategoryIds.isEmpty()) {
            admission.utils.MessageBox.info(this, "Category not selected");
            return;
        }
        int row = districtTableModel.getRowCount();
        
        out.println("SNO,NAME,FATHER'S NAME,DOMICILE,CNIC NO," + (jCheckBox1.isSelected() ? "PERCENTAGE," : "") + "REGISTRATION NO.,SELECTION PROGRAM,DURATION,DATE OF JOIN,1ST YEAR FEE,2ND YEAR FEE,CURRENT SEMESTER,OTHER FEES,ADMISSION FEE,TOTAL FEES");
        
        int count = 0;
        for (int i = 0; i < row; i++) {
            boolean b = (boolean) districtTableModel.getValueAt(i, 1);
            if(!b) continue;
            
            District district = (District) districtTableModel.getValueAt(i, 0);
            try {
                List<HashMap<String, String>> rows = JDBCDatabaseManager.getHECData(ay.getAdmissionYearId(), pt.getProgramTypeId(), shift.getShiftId(), district.getDistrictId(), campusCategoryIds, true);
                progressBar.setMaximum(rows.size() - 1);
                progressBar.setValue(0);
                for (int j = 0; j < rows.size(); j++) {
                    HashMap<String, String> map = rows.get(j);
                    
                    float per = 0.0f;
                    if(jCheckBox1.isSelected()) {
                        CredentialDetails cd = (CredentialDetails) DatabaseManager.getSingleRecord(CredentialDetails.class.getName(), "candidate.candidateId = " + map.get("candidate_id") + " AND detailOf = " + detailOf);
                        per = CandidateHelper.getPercentage(cd);
                        if(per < minPer) continue;
                    }
                    String s = (++count) + ",";
                    s += map.get("name") + ",";
                    s += map.get("fname") + ",";
                    s += district.getName() + ",";
                    s += map.get("cnic") + ",";
                    //s += map.get("address") + ",";
                    if(jCheckBox1.isSelected())
                        s += per + ",";
                    s += ay.getYear() + "-" + map.get("seat_no") + ",";
                    s += map.get("program") + " (" + map.get("selection") + "),";
                    s += map.get("duration") + " YEAR,";
                    s += "12 JAN " + ay.getYear() + ",";
                    s += "2000,";
                    s += "2000,";
                    s += "1st SEMESTER,";
                    s += "0,";
                    s += "0,";
                    s += "0";
                    
                    out.println(s);
                    progressBar.setValue(i + 1);
                }
            } catch (SQLException ex) {
                Logger.getLogger(HecReportInternalFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        out.close();
        
        admission.utils.MessageBox.info(this, "Process Completed");
        
        exportButton.setEnabled(true);
        backButton.setEnabled(true);
    }
    
    private String getCategoryIdStringLine() {
        int row = categoryTableModel.getRowCount();
        String line = "";
        
        for (int i = 0; i < row; i++) {
            boolean b = (boolean) categoryTableModel.getValueAt(i, 1);
            if(!b) continue;
            
            CampusCategory cc = (CampusCategory) categoryTableModel.getValueAt(i, 0);
            line += cc.getCampusCategoryId() + ",";
        }
        
        if(line.endsWith(",")) {
            line = line.substring(0, line.length() - 1);
        }
        
        return line;
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox admissionYearComboBox;
    private javax.swing.JButton backButton;
    private javax.swing.JComboBox campusComboBox;
    private javax.swing.JTable categoryTable;
    private javax.swing.JTable districtTable;
    private javax.swing.JButton exportButton;
    private javax.swing.JFileChooser fileChooser;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField minPercenatageTextField;
    private javax.swing.JComboBox programTypeComboBox;
    private javax.swing.JProgressBar progressBar;
    private javax.swing.JCheckBox selectAllCheckBox;
    private javax.swing.JCheckBox selectAllCheckBox1;
    private javax.swing.JComboBox shiftComboBox;
    // End of variables declaration//GEN-END:variables
    private Resources privileges;
    private DefaultTableModel districtTableModel;
    private DefaultTableModel categoryTableModel;
}
