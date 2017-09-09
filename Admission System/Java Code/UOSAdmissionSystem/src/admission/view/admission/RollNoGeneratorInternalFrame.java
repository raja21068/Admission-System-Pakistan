package admission.view.admission;

import admission.controller.DatabaseManager;
import admission.controller.JDBCDatabaseManager;
import admission.model.AdmissionListDetails;
import admission.model.AdmissionYear;
import admission.model.Campus;
import admission.model.CposGroup;
import admission.model.security.Resources;
import admission.model.ProgramType;
import admission.model.Shift;
import admission.reports.BankSelectionReport;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import admission.utils.RoundedBorder;

/**
 *
 * @author Raja Kumar & Jay Kumar
 */
public class RollNoGeneratorInternalFrame extends javax.swing.JInternalFrame {

    public RollNoGeneratorInternalFrame() {
        initComponents();
        
        this.titlePanel.setBorder(RoundedBorder.createGradientBorder());
        
        bankSelectionReport = new BankSelectionReport(progressBar);
        
        admission.utils.Utility.hideOnEscape(this);
        
        admission.utils.Utility.comboBoxScroll(cposgComboBox);
    }

    @Override
    public void setVisible(boolean aFlag) {
        if(aFlag) {
            this.getCampus();
            this.getAdmissionYear();
            this.getShift();
            this.getProgramType();
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
        buttonGroup1 = new javax.swing.ButtonGroup();
        generateButton = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        campusComboBox = new javax.swing.JComboBox();
        jLabel11 = new javax.swing.JLabel();
        programTypeComboBox = new javax.swing.JComboBox();
        backButton = new javax.swing.JButton();
        progressBar = new javax.swing.JProgressBar();
        jLabel3 = new javax.swing.JLabel();
        shiftComboBox = new javax.swing.JComboBox();
        jLabel14 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        cposgComboBox = new javax.swing.JComboBox();
        jLabel9 = new javax.swing.JLabel();
        admissionYearComboBox = new javax.swing.JComboBox();
        durationTextField = new javax.swing.JTextField();
        semesterTextField = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        isAllCheckBox = new javax.swing.JCheckBox();
        jPanel1 = new javax.swing.JPanel();
        newRadioButton = new javax.swing.JRadioButton();
        continueRadioButton = new javax.swing.JRadioButton();
        titlePanel = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();

        fileChooser.setDialogType(javax.swing.JFileChooser.SAVE_DIALOG);
        fileChooser.setFileSelectionMode(javax.swing.JFileChooser.DIRECTORIES_ONLY);
        fileChooser.setName(""); // NOI18N

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setIconifiable(true);
        setTitle("Roll No Generator");

        generateButton.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        generateButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Generate-tables-icon (1).png"))); // NOI18N
        generateButton.setText("Generate");
        generateButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                generateButtonActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel10.setText("Campus:");

        campusComboBox.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        campusComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                campusComboBoxActionPerformed(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel11.setText("Program Type:");

        programTypeComboBox.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        programTypeComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                programTypeComboBoxActionPerformed(evt);
            }
        });

        backButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Gnome-Go-Home-32.png"))); // NOI18N
        backButton.setToolTipText("Back");
        backButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backButtonActionPerformed(evt);
            }
        });

        progressBar.setStringPainted(true);

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel3.setText("Progress:");

        shiftComboBox.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        shiftComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                shiftComboBoxActionPerformed(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel14.setText("Shift:");

        jLabel16.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel16.setText("Program of Study:");

        final java.awt.Dimension size = cposgComboBox.getPreferredSize();
        cposgComboBox.setPreferredSize(size);
        cposgComboBox.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        cposgComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cposgComboBoxActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel9.setText("Duration:");

        admissionYearComboBox.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        durationTextField.setEditable(false);
        durationTextField.setBackground(new java.awt.Color(255, 255, 255));
        durationTextField.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        semesterTextField.setEditable(false);
        semesterTextField.setBackground(new java.awt.Color(255, 255, 255));
        semesterTextField.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel5.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel5.setText("Admission Year:");

        jLabel12.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel12.setText("Semester:");

        isAllCheckBox.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        isAllCheckBox.setText("All Programs");
        isAllCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                isAllCheckBoxActionPerformed(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Seat No. from"));

        buttonGroup1.add(newRadioButton);
        newRadioButton.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        newRadioButton.setSelected(true);
        newRadioButton.setText("New");

        buttonGroup1.add(continueRadioButton);
        continueRadioButton.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        continueRadioButton.setText("Continue with previous");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(newRadioButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(continueRadioButton)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(newRadioButton)
                .addComponent(continueRadioButton))
        );

        titlePanel.setLayout(new java.awt.BorderLayout());

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/admission/view/images/form-icon.png"))); // NOI18N
        jLabel2.setText("Roll No Generator");
        jLabel2.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, java.awt.Color.darkGray), javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5)));
        titlePanel.add(jLabel2, java.awt.BorderLayout.CENTER);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(titlePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(progressBar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jLabel16)
                            .addComponent(jLabel9))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(admissionYearComboBox, 0, 124, Short.MAX_VALUE)
                                    .addComponent(campusComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(shiftComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(programTypeComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(durationTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jLabel12))
                                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(semesterTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(isAllCheckBox)))
                            .addComponent(cposgComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(generateButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(backButton))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(titlePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(admissionYearComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11)
                    .addComponent(programTypeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(campusComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14)
                    .addComponent(shiftComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(cposgComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(durationTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12)
                    .addComponent(semesterTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(isAllCheckBox, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(progressBar, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(generateButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(backButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {admissionYearComboBox, campusComboBox, cposgComboBox, durationTextField, programTypeComboBox, semesterTextField, shiftComboBox});

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void generateButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_generateButtonActionPerformed
        if(isAllCheckBox.isSelected()) {
            Campus campus = (Campus) this.campusComboBox.getSelectedItem();
            Shift shift = (Shift) this.shiftComboBox.getSelectedItem();
            ProgramType pt = (ProgramType) this.programTypeComboBox.getSelectedItem();
            if(campus == null || shift == null || pt == null) return;
        
            List<CposGroup> list = DatabaseManager.getCampusCposGroup(campus.getCampusId(), shift.getShiftId(), pt.getProgramTypeId());
            
            new RollNoGeneratorThread(list).start();
        } else {
            CposGroup cposg = (CposGroup) cposgComboBox.getSelectedItem();
            if(cposg == null) return;
            
            List<CposGroup> list = new ArrayList<>();
            list.add(cposg);
            new RollNoGeneratorThread(list).start();
        }
    }//GEN-LAST:event_generateButtonActionPerformed

    private void campusComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_campusComboBoxActionPerformed
        // TODO add your handling code here:
        getCampusProgramOfStudyGroup();
    }//GEN-LAST:event_campusComboBoxActionPerformed

    private void programTypeComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_programTypeComboBoxActionPerformed
        // TODO add your handling code here:
        getCampusProgramOfStudyGroup();
    }//GEN-LAST:event_programTypeComboBoxActionPerformed

    private void backButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backButtonActionPerformed
        // TODO add your handling code here:
        this.setVisible(false);
    }//GEN-LAST:event_backButtonActionPerformed

    private void shiftComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_shiftComboBoxActionPerformed
        // TODO add your handling code here:
        getCampusProgramOfStudyGroup();
    }//GEN-LAST:event_shiftComboBoxActionPerformed

    private void cposgComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cposgComboBoxActionPerformed
        // TODO add your handling code here:
        durationTextField.setText(null);
        semesterTextField.setText(null);

        CposGroup cposg = (CposGroup) cposgComboBox.getSelectedItem();
        if(cposg == null) return;

        this.durationTextField.setText(cposg.getCampusProgramOfStudy().getProgramOfStudy().getDuration() + " Year");
        this.semesterTextField.setText(cposg.getCampusProgramOfStudy().getProgramOfStudy().getSemester() + " Semester");
    }//GEN-LAST:event_cposgComboBoxActionPerformed

    private void isAllCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_isAllCheckBoxActionPerformed
        // TODO add your handling code here:
        cposgComboBox.setEnabled(!isAllCheckBox.isSelected());
        this.durationTextField.setEnabled(!isAllCheckBox.isSelected());
        this.semesterTextField.setEnabled(!isAllCheckBox.isSelected());
    }//GEN-LAST:event_isAllCheckBoxActionPerformed

    private void getProgramType(){
        this.programTypeComboBox.removeAllItems();
        
        List<ProgramType> list = DatabaseManager.getData(ProgramType.class.getName(), "name");
        for (int i = 0; i < list.size(); i++) {
            this.programTypeComboBox.addItem(list.get(i));
        }
    }

    private void getCampus(){
        this.campusComboBox.removeAllItems();
        
        List<Campus> list = DatabaseManager.getData(Campus.class.getName(), "name");
        for (int i = 0; i < list.size(); i++) {
            this.campusComboBox.addItem(list.get(i));
        }
    }

    private void getShift(){
        this.shiftComboBox.removeAllItems();
        
        List<Shift> list = DatabaseManager.getData(Shift.class.getName(), "isMorning DESC");
        for (int i = 0; i < list.size(); i++) {
            this.shiftComboBox.addItem(list.get(i));
        }
    }
    
    private void getAdmissionYear(){
        this.admissionYearComboBox.removeAllItems();
        List<AdmissionYear> list = DatabaseManager.getData(AdmissionYear.class.getName(), "year DESC");
        
        for (int i = 0; i < list.size(); i++) {
            this.admissionYearComboBox.addItem(list.get(i));
        }
    }
    
    private void getCampusProgramOfStudyGroup(){
        cposgComboBox.removeAllItems();
    
        Campus campus = (Campus) this.campusComboBox.getSelectedItem();
        Shift shift = (Shift) this.shiftComboBox.getSelectedItem();
        ProgramType pt = (ProgramType) this.programTypeComboBox.getSelectedItem();
        if(campus == null || shift == null || pt == null) return;
        
        List<CposGroup> list = DatabaseManager.getCampusCposGroup(campus.getCampusId(), shift.getShiftId(), pt.getProgramTypeId());

        for (int i = 0; i < list.size(); i++) {
            cposgComboBox.addItem(list.get(i));
        }
    }

    private class RollNoGeneratorThread extends Thread {
        private List<CposGroup> list;
        
        public RollNoGeneratorThread(List<CposGroup> list) {
            this.list = list;
        }
        
        @Override public void run() {
            AdmissionYear ay = (AdmissionYear) admissionYearComboBox.getSelectedItem();
            if(ay == null) return;
            
            generateButton.setEnabled(false);
            backButton.setEnabled(false);
            int count = 0;
            progressBar.setMinimum(0);
            for (int i = 0; i < list.size(); i++) {
                CposGroup cposGroup = list.get(i);
                try {
                    List<Integer> aldIds;
                    int maxRollNo = 0;
                    if(newRadioButton.isSelected()) {
                        aldIds = JDBCDatabaseManager.getCandidatesOfCposGroup2(ay.getAdmissionYearId(), cposGroup.getCposGroupId(), true);
                    } else {
                        aldIds = JDBCDatabaseManager.getCandidatesOfCposGroup3(ay.getAdmissionYearId(), cposGroup.getCposGroupId(), true);
                        maxRollNo = JDBCDatabaseManager.getMaxRollNo(ay.getAdmissionYearId(), cposGroup.getCposGroupId(), true);
                    }
                    //if(aldIds.isEmpty()) continue;
                    
                    JDBCDatabaseManager.updateAdmissionListDetails(cposGroup.getCposGroupId(), null);
                    
                    progressBar.setMaximum(aldIds.size());
                    progressBar.setValue(0);
                    
                    for (int j = 0; j < aldIds.size(); j++) {
                        Integer aldId = aldIds.get(j);
                        count += JDBCDatabaseManager.updateAdmissionListDetails(aldId, ++maxRollNo, 1);
                        
                        AdmissionListDetails ald = (AdmissionListDetails) DatabaseManager.getSingleRecord(AdmissionListDetails.class.getName(), aldId);
                        DatabaseManager.refresh(ald);
                        
                        progressBar.setValue(j + 1);
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(RollNoGeneratorInternalFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            admission.utils.MessageBox.info(RollNoGeneratorInternalFrame.this, count + " Roll No. Generated successfully");
            generateButton.setEnabled(true);
            backButton.setEnabled(true);
        }
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox admissionYearComboBox;
    private javax.swing.JButton backButton;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox campusComboBox;
    private javax.swing.JRadioButton continueRadioButton;
    private javax.swing.JComboBox cposgComboBox;
    private javax.swing.JTextField durationTextField;
    private javax.swing.JFileChooser fileChooser;
    private javax.swing.JButton generateButton;
    private javax.swing.JCheckBox isAllCheckBox;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JRadioButton newRadioButton;
    private javax.swing.JComboBox programTypeComboBox;
    private javax.swing.JProgressBar progressBar;
    private javax.swing.JTextField semesterTextField;
    private javax.swing.JComboBox shiftComboBox;
    private javax.swing.JPanel titlePanel;
    // End of variables declaration//GEN-END:variables
    private Resources privileges;
    private BankSelectionReport bankSelectionReport; 
}
