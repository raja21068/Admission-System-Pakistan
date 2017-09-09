package admission.reports;

import admission.controller.DatabaseManager;
import admission.controller.JDBCDatabaseManager;
import admission.controller.beans.Candidate;
import admission.model.AdmissionList;
import admission.model.AdmissionListDetails;
import admission.model.AdmissionSession;
import admission.model.AdmissionYear;
import admission.model.AppliedCategory;
import admission.model.Campus;
import admission.model.CredentialDetails;
import admission.model.security.Resources;
import admission.model.ProgramType;
import com.hexiong.jdbf.JDBFException;
import java.awt.Cursor;
import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import admission.utils.CandidateHelper;
import admission.utils.IConstant;
import admission.utils.DBFGenerator;
import admission.utils.RollNoFormatter;

/**
 *
 * @author Raja Kumar & Jay Kumar
 */
public class ExportCandidatesDetailInternalFrame extends javax.swing.JInternalFrame {

    public ExportCandidatesDetailInternalFrame() {
        initComponents();

        admission.utils.Utility.hideOnEscape(this);
    }

    @Override
    public void setVisible(boolean aFlag) {
        if (aFlag) {
            this.getCampus();
            this.getAdmissionYear();
            this.getProgramType();
        }
        super.setVisible(aFlag);
    }

    public void setPrivileges(Resources privileges) {
        this.privileges = privileges;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        fileChooser = new javax.swing.JFileChooser();
        jLabel1 = new javax.swing.JLabel();
        exportButton = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        admissionSessionComboBox = new javax.swing.JComboBox();
        jLabel10 = new javax.swing.JLabel();
        campusComboBox = new javax.swing.JComboBox();
        jLabel11 = new javax.swing.JLabel();
        admissionYearComboBox = new javax.swing.JComboBox();
        backButton = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        admissionListComboBox = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();
        exportToComboBox = new javax.swing.JComboBox();
        progressBar = new javax.swing.JProgressBar();
        jLabel3 = new javax.swing.JLabel();
        selectedButton = new javax.swing.JButton();
        nonSelectedButton = new javax.swing.JButton();
        programTypeComboBox = new javax.swing.JComboBox();
        jLabel8 = new javax.swing.JLabel();

        fileChooser.setDialogType(javax.swing.JFileChooser.SAVE_DIALOG);
        fileChooser.setFileSelectionMode(javax.swing.JFileChooser.DIRECTORIES_ONLY);
        fileChooser.setName(""); // NOI18N

        setClosable(true);
        setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);
        setTitle("Export Candidates Detail");

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Export Candidates Detail");

        exportButton.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        exportButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/admission/view/images/import-export-icon.png"))); // NOI18N
        exportButton.setText("Export");
        exportButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exportButtonActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel5.setText("Admission Session:");

        admissionSessionComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                admissionSessionComboBoxActionPerformed(evt);
            }
        });

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

        backButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/admission/view/images/Back.png"))); // NOI18N
        backButton.setToolTipText("Back");
        backButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backButtonActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel6.setText("List:");

        admissionListComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                admissionListComboBoxActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel2.setText("Export to:");

        exportToComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Excel", "DBF" }));

        progressBar.setStringPainted(true);

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel3.setText("Progress:");

        selectedButton.setText("Selected");
        selectedButton.setToolTipText("Admission Year, Program Type");
        selectedButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectedButtonActionPerformed(evt);
            }
        });

        nonSelectedButton.setText("Non-Selected");
        nonSelectedButton.setToolTipText("Admission Year, Program Type");
        nonSelectedButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nonSelectedButtonActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel8.setText("Program Type:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(progressBar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(nonSelectedButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(selectedButton, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(exportButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(backButton))
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(admissionSessionComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 269, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(campusComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 269, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(admissionListComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 269, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(exportToComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, 107, Short.MAX_VALUE)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 107, Short.MAX_VALUE)
                                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(admissionYearComboBox, 0, 269, Short.MAX_VALUE)
                                    .addComponent(programTypeComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addGap(0, 0, Short.MAX_VALUE)))
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(admissionYearComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11))
                .addGap(5, 5, 5)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(programTypeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(admissionSessionComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(admissionListComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(exportToComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addGap(5, 5, 5)
                .addComponent(progressBar, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(backButton, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(exportButton, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(nonSelectedButton)
                        .addComponent(selectedButton)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {admissionSessionComboBox, admissionYearComboBox, campusComboBox});

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {exportButton, nonSelectedButton, selectedButton});

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void exportButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exportButtonActionPerformed
        if (exportToComboBox.getSelectedIndex() == 0) {
            exportToExcel();
        } else {
            AdmissionSession as = (AdmissionSession) this.admissionSessionComboBox.getSelectedItem();
            if (as == null) {
                return;
            }
            if ((as.getProgramType().getIsBachelor())) {
            } else {
                exportToDBF();
            }
        }
    }//GEN-LAST:event_exportButtonActionPerformed

    private void admissionSessionComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_admissionSessionComboBoxActionPerformed
        // TODO add your handling code here:
        getAdmissionList();
    }//GEN-LAST:event_admissionSessionComboBoxActionPerformed

    private void campusComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_campusComboBoxActionPerformed
        // TODO add your handling code here:
        getAdmissionList();
    }//GEN-LAST:event_campusComboBoxActionPerformed

    private void admissionYearComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_admissionYearComboBoxActionPerformed
        // TODO add your handling code here:
        this.getAdmissionSession();
    }//GEN-LAST:event_admissionYearComboBoxActionPerformed

    private void backButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backButtonActionPerformed
        // TODO add your handling code here:
        this.setVisible(false);
    }//GEN-LAST:event_backButtonActionPerformed

    private void admissionListComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_admissionListComboBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_admissionListComboBoxActionPerformed

    private void selectedButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectedButtonActionPerformed
        // TODO add your handling code here:        
        AdmissionYear ay = (AdmissionYear) this.admissionYearComboBox.getSelectedItem();
        ProgramType pt = (ProgramType) programTypeComboBox.getSelectedItem();
        if (ay == null || pt == null) {
            return;
        }

        if (fileChooser.showSaveDialog(this) != JFileChooser.APPROVE_OPTION) {
            return;
        }

        try {
            StringBuilder buffer = new StringBuilder();
            buffer.append("SNO,")
                    .append("SEAT_NO,")
                    .append("NAME,")
                    .append("FNAME,")
                    .append("SURNAME,")
                    .append("ROLL_NO,")
                    .append("PROGRAM,")
                    .append("SELECTION,")
                    .append("SHIFT")
                    .append("\n");
            List<Object[]> data = JDBCDatabaseManager.getSelectedCandidates(ay.getAdmissionYearId(), pt.getProgramTypeId(), true);
            for (int i = 0; i < data.size(); i++) {
                Object[] obs = data.get(i);
                buffer.append(i + 1).append(",")
                        .append(obs[0]).append(",")
                        .append(obs[1]).append(",")
                        .append(obs[2]).append(",")
                        .append(obs[3]).append(",")
                        .append(RollNoFormatter.format(ay.getYear(), (String) obs[5], (Integer) obs[4])).append(",")
                        .append(obs[7]).append(",")
                        .append(((String) obs[6]).replace(',', ' ')).append(",")
                        .append(obs[8])
                        .append("\n");
            }
            try (PrintStream out = new PrintStream(new FileOutputStream(fileChooser.getSelectedFile().getAbsolutePath() + "\\Selected Candidates " + pt.getName() + " " + ay.getYear() + ".csv"));) {
                out.print(buffer.toString());
                admission.utils.MessageBox.info(this, "File saved");
            }
        } catch (SQLException | FileNotFoundException ex) {
            Logger.getLogger(ExportCandidatesDetailInternalFrame.class.getName()).log(Level.SEVERE, null, ex);
            admission.utils.MessageBox.error(this, ex);
        }
    }//GEN-LAST:event_selectedButtonActionPerformed

    private void nonSelectedButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nonSelectedButtonActionPerformed
        // TODO add your handling code here:

        AdmissionYear ay = (AdmissionYear) this.admissionYearComboBox.getSelectedItem();
        ProgramType pt = (ProgramType) programTypeComboBox.getSelectedItem();
        if (ay == null || pt == null) {
            return;
        }

        if (fileChooser.showSaveDialog(this) != JFileChooser.APPROVE_OPTION) {
            return;
        }

        try {
            StringBuilder buffer = new StringBuilder();
            buffer.append("SNO,")
                    .append("SEAT_NO,")
                    .append("NAME,")
                    .append("FNAME,")
                    .append("SURNAME,")
                    .append("\n");
            List<Object[]> data = JDBCDatabaseManager.getNonSelectedCandidates(ay.getAdmissionYearId(), pt.getProgramTypeId(), true);
            for (int i = 0; i < data.size(); i++) {
                Object[] obs = data.get(i);
                buffer.append(i + 1).append(",")
                        .append(obs[0]).append(",")
                        .append(obs[1]).append(",")
                        .append(obs[2]).append(",")
                        .append(obs[3]).append(",")
                        .append("\n");
            }
            try (PrintStream out = new PrintStream(new FileOutputStream(fileChooser.getSelectedFile().getAbsolutePath() + "\\Non Selected Candidates " + pt.getName() + " " + ay.getYear() + ".csv"));) {
                out.print(buffer.toString());
                admission.utils.MessageBox.info(this, "File saved");
            }
        } catch (SQLException | FileNotFoundException ex) {
            Logger.getLogger(ExportCandidatesDetailInternalFrame.class.getName()).log(Level.SEVERE, null, ex);
            admission.utils.MessageBox.error(this, ex);
        }
    }//GEN-LAST:event_nonSelectedButtonActionPerformed

    private void getCampus() {
        this.campusComboBox.removeAllItems();
        List<Campus> list = DatabaseManager.getData(Campus.class.getName(), "displayOrder");

        for (int i = 0; i < list.size(); i++) {
            this.campusComboBox.addItem(list.get(i));
        }
    }

    private void getProgramType() {
        this.programTypeComboBox.removeAllItems();

        List<ProgramType> list = DatabaseManager.getData(ProgramType.class.getName(), "name");
        for (int i = 0; i < list.size(); i++) {
            this.programTypeComboBox.addItem(list.get(i));
        }
    }

    private void getAdmissionYear() {
        this.admissionYearComboBox.removeAllItems();
        List<AdmissionYear> list = DatabaseManager.getData(AdmissionYear.class.getName(), "year DESC");

        for (int i = 0; i < list.size(); i++) {
            this.admissionYearComboBox.addItem(list.get(i));
        }
    }

    private void getAdmissionSession() {
        this.admissionSessionComboBox.removeAllItems();

        AdmissionYear ay = (AdmissionYear) this.admissionYearComboBox.getSelectedItem();
        if (ay == null) {
            return;
        }

        Set set = ay.getAdmissionSessions();
        if (set == null) {
            return;
        }

        Object[] toArray = set.toArray();

        for (int i = 0; i < toArray.length; i++) {
            this.admissionSessionComboBox.addItem(toArray[i]);
        }
    }

    private void getAdmissionList() {
        this.admissionListComboBox.removeAllItems();

        Campus campus = (Campus) this.campusComboBox.getSelectedItem();
        AdmissionSession as = (AdmissionSession) this.admissionSessionComboBox.getSelectedItem();
        if (as == null || campus == null) {
            return;
        }

        List<AdmissionList> list = DatabaseManager.getData(AdmissionList.class, "admissionSession.admissionSessionId = " + as.getAdmissionSessionId() + " AND campus.campusId = " + campus.getCampusId(), "listNo DESC");

        for (AdmissionList al : list) {
            this.admissionListComboBox.addItem(al);
        }

    }

    private void exportToExcel() {
        final Campus c = (Campus) this.campusComboBox.getSelectedItem();
        final AdmissionSession as = (AdmissionSession) this.admissionSessionComboBox.getSelectedItem();
        final AdmissionList al = (AdmissionList) this.admissionListComboBox.getSelectedItem();
        if (c == null || as == null || al == null) {
            return;
        }

        new Thread() {
            @Override
            public void run() {
                try {
                    ExportCandidatesDetailInternalFrame.this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                    exportButton.setEnabled(false);
                    backButton.setEnabled(false);

                    int rows = 0;
                    HSSFWorkbook workbook = new HSSFWorkbook();
                    HSSFSheet sheet = workbook.createSheet("Details");

                    List<Candidate> candidates = JDBCDatabaseManager.getCandidates(as.getAdmissionYear().getAdmissionYearId(), c.getCampusId(), as.getProgramType().getProgramTypeId());
                    if (candidates.isEmpty()) {
                        return;
                    }

                    progressBar.setMaximum(candidates.size() - 1);
                    progressBar.setValue(0);

                    sheet.createRow(rows).createCell(0).setCellValue("SNO");
                    sheet.getRow(rows).createCell(1).setCellValue("SEAT_NO");
                    sheet.getRow(rows).createCell(2).setCellValue("NAME");
                    sheet.getRow(rows).createCell(3).setCellValue("FATHERS_NAME");
                    sheet.getRow(rows).createCell(4).setCellValue("DISTRICT");
                    sheet.getRow(rows).createCell(5).setCellValue("AREA");
                    sheet.getRow(rows).createCell(6).setCellValue("PERCENTAGE");
                    sheet.getRow(rows).createCell(7).setCellValue("OBJECTION");
                    sheet.getRow(rows).createCell(8).setCellValue("OBJ_REMARKS");
                    sheet.getRow(rows).createCell(9).setCellValue("CHOICE_NO");
                    sheet.getRow(rows).createCell(10).setCellValue("CATEGORY"); // category
                    sheet.getRow(rows++).createCell(11).setCellValue("DISCIPLINE"); // program of study

                    for (int i = 0; i < candidates.size(); i++) {
                        Candidate cn = candidates.get(i);

                        List<AdmissionListDetails> aldList = JDBCDatabaseManager.getAdmissionListDetail(cn.getCandidateId(), al.getAdmissionListId());
                        if (aldList.isEmpty()) {
                            sheet.createRow(rows).createCell(0).setCellValue(i + 1);
                            sheet.getRow(rows).createCell(1).setCellValue(cn.getSeatNo());
                            sheet.getRow(rows).createCell(2).setCellValue(cn.getName());
                            sheet.getRow(rows).createCell(3).setCellValue(cn.getFathersName());
                            sheet.getRow(rows).createCell(4).setCellValue(cn.getDistrictName());
                            sheet.getRow(rows).createCell(5).setCellValue(cn.getArea());
                            sheet.getRow(rows).createCell(6).setCellValue(String.valueOf(cn.getPercentage()));
                            sheet.getRow(rows).createCell(7).setCellValue(cn.isObjection() ? "Y" : "N");
                            sheet.getRow(rows).createCell(8).setCellValue(cn.getObjectionRemarks());
                            sheet.getRow(rows).createCell(9).setCellValue("NONE");
                            sheet.getRow(rows).createCell(10).setCellValue("NONE"); // category
                            sheet.getRow(rows++).createCell(11).setCellValue("NONE"); // program of study
                        } else {
                            for (int j = 0; j < aldList.size(); j++) {
                                AdmissionListDetails ald = aldList.get(j);

                                sheet.createRow(rows).createCell(0).setCellValue(i + 1);
                                sheet.getRow(rows).createCell(1).setCellValue(cn.getSeatNo());
                                sheet.getRow(rows).createCell(2).setCellValue(cn.getName());
                                sheet.getRow(rows).createCell(3).setCellValue(cn.getFathersName());
                                sheet.getRow(rows).createCell(4).setCellValue(cn.getDistrictName());
                                sheet.getRow(rows).createCell(5).setCellValue(cn.getArea());
                                sheet.getRow(rows).createCell(6).setCellValue(String.valueOf(cn.getPercentage()));
                                sheet.getRow(rows).createCell(7).setCellValue(cn.isObjection() ? "Y" : "N");
                                sheet.getRow(rows).createCell(8).setCellValue(cn.getObjectionRemarks());
                                sheet.getRow(rows).createCell(9).setCellValue(ald.getChoiceNo());
                                sheet.getRow(rows).createCell(10).setCellValue(ald.getCampusCategoryName()); // category
                                sheet.getRow(rows++).createCell(11).setCellValue(ald.getPosName() + (ald.getCposg().equals("G") ? "" : " " + ald.getCposg())); // program of study
                            }
                        }
                        progressBar.setValue(i);
                    }
                    ExportCandidatesDetailInternalFrame.this.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
                    fileChooser.setSelectedFile(new File(c.getName()));
                    int approve = fileChooser.showSaveDialog(ExportCandidatesDetailInternalFrame.this);
                    String path = "/details.xls";
                    if (approve == JFileChooser.APPROVE_OPTION) {
                        path = fileChooser.getSelectedFile().getAbsolutePath() + ".xls";
                    }
                    File file = new File(path);
                    try (FileOutputStream out = new FileOutputStream(file);) {
                        workbook.write(out);
                        admission.utils.MessageBox.info(ExportCandidatesDetailInternalFrame.this, "Process completed");
                        Desktop.getDesktop().open(file.getParentFile());
                    } catch (Exception ex) {
                        Logger.getLogger(ExportCandidatesDetailInternalFrame.class.getName()).log(Level.SEVERE, null, ex);
                        admission.utils.MessageBox.info(ExportCandidatesDetailInternalFrame.this, ex);
                    }

                } catch (SQLException ex) {
                    Logger.getLogger(ExportCandidatesDetailInternalFrame.class.getName()).log(Level.SEVERE, null, ex);
                    admission.utils.MessageBox.info(ExportCandidatesDetailInternalFrame.this, ex);
                }
                exportButton.setEnabled(true);
                backButton.setEnabled(true);
            }
        }.start();
    }

    private void exportToDBF() {
        final Campus c = (Campus) this.campusComboBox.getSelectedItem();
        final AdmissionSession as = (AdmissionSession) this.admissionSessionComboBox.getSelectedItem();
        final AdmissionList al = (AdmissionList) this.admissionListComboBox.getSelectedItem();
        if (c == null || as == null || al == null) {
            return;
        }

        new Thread() {
            @Override
            public void run() {
                try {
                    fileChooser.setSelectedFile(null);
                    int approve = fileChooser.showSaveDialog(ExportCandidatesDetailInternalFrame.this);
//                    String path = "/";
                    if (approve != JFileChooser.APPROVE_OPTION) {
                        return;
                    }
                    String path = fileChooser.getSelectedFile().getAbsolutePath() + "\\";

                    List<admission.model.Candidate> candidates;
                    if ((c.getIsMain())) {
                        candidates = DatabaseManager.getMainCampusCandidates(as.getAdmissionYear().getAdmissionYearId(), c.getIsMain(), as.getProgramType().getProgramTypeId());
                    } else {
                        candidates = DatabaseManager.getCandidates(as.getAdmissionYear().getAdmissionYearId(), c.getCampusId(), as.getProgramType().getProgramTypeId());
                    }
                    if (candidates.isEmpty()) {
                        return;
                    }

                    ExportCandidatesDetailInternalFrame.this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                    exportButton.setEnabled(true);
                    backButton.setEnabled(true);

                    progressBar.setMaximum(candidates.size() - 1);
                    progressBar.setValue(0);

                    DBFGenerator dbf = new DBFGenerator(path, IConstant.MASTER);
                    NumberFormat nf = new DecimalFormat("#0.00");

                    for (int i = 0; i < candidates.size(); i++) {
                        admission.model.Candidate cn = candidates.get(i);
                        CredentialDetails[] cds = JDBCDatabaseManager.getCredentialDetails(cn.getCandidateId());
                        Object[] data = new Object[83];

                        data[0] = cn.getSeatNo();
                        data[1] = cn.getFormSno();
                        data[2] = cn.getGender();
                        data[3] = Integer.parseInt(cn.getReligion().getRemarks());
                        // categories

                        List<AppliedCategory> appliedCategoryList = DatabaseManager.getData(AppliedCategory.class, "candidate.candidateId = " + cn.getCandidateId(), "appliedCategoryId");
//                        Object[] toArray = cn.getAppliedCategories().toArray();
                        for (int j = 4, c = 0; j <= 9; j++, c++) {
                            if (c < appliedCategoryList.size()) {
                                AppliedCategory ac = appliedCategoryList.get(c);
                                data[j] = getCatCode(ac.getCategoryCode());
                            } else {
                                data[4] = 0;
                            }
                        }
                        data[10] = cn.getName();
                        data[11] = cn.getFathersName();
                        data[12] = cn.getSurname();
                        data[13] = Integer.parseInt(cn.getDistrict() == null ? "0" : cn.getDistrict().getRemarks());
                        data[14] = cn.getArea();
//                        data[15] = Integer.parseInt(cn.getOptionalSubject1().isEmpty() ? "0" : JDBCDatabaseManager.getSubjectCode(cn.getOptionalSubject1())); /*optional subjets*/
//                        data[16] = Integer.parseInt(cn.getOptionalSubject2().isEmpty() ? "0" : JDBCDatabaseManager.getSubjectCode(cn.getOptionalSubject2()));
//                        data[17] = Integer.parseInt(cn.getOptionalSubject3().isEmpty() ? "0" : JDBCDatabaseManager.getSubjectCode(cn.getOptionalSubject3()));

                        // morning choices
                        List<Integer> ccList;
                        if ((c.getIsMain())) {
                            ccList = JDBCDatabaseManager.getCNPOSChoicesCode(1, (c.getIsMain()), cn.getCandidateId());
                        } else {
                            ccList = JDBCDatabaseManager.getCNPOSChoicesCode(1, c.getCampusId(), cn.getCandidateId());
                        }

                        for (int j = 18, c = 0; j <= 27; j++, c++) {
                            if (c < ccList.size()) {
                                data[j] = ccList.get(c);
                            } else {
                                data[j] = 0;
                            }
                        }

                        // evening choices
                        if ((c.getIsMain())) {
                            ccList = JDBCDatabaseManager.getCNPOSChoicesCode(2, (c.getIsMain()), cn.getCandidateId());
                        } else {
                            ccList = JDBCDatabaseManager.getCNPOSChoicesCode(2, c.getCampusId(), cn.getCandidateId());
                        }

                        for (int j = 28, c = 0; j <= 37; j++, c++) {
                            if (c < ccList.size()) {
                                data[j] = ccList.get(c);
                            } else {
                                data[j] = 0;
                            }
                        }

                        data[38] = (cn.getIsObjection()) ? "Y" : "N";
                        data[39] = cn.getObjectionRemarks();
                        data[40] = getChange(cds[0].getGroup());
                        data[41] = cds[0].getMarksObtained();
                        data[42] = cds[0].getTotalMarks();
                        data[43] = cds[0].getPassingYear();
                        data[44] = cds[0].getSeatNo();
                        data[45] = cds[0].getIssuerCode();
                        
                        data[46] = Float.parseFloat(nf.format(CandidateHelper.getPercentage(cds[0], IConstant.M_MATRIC_PER, 0)));
                        data[47] = getChange(cds[1].getGroup());
                        data[48] = cds[1].getMarksObtained();
                        data[49] = cds[1].getTotalMarks();
                        data[50] = cds[1].getPassingYear();
                        data[51] = cds[1].getSeatNo();
                        data[52] = cds[1].getIssuerCode();
                        data[53] = Float.parseFloat(nf.format(CandidateHelper.getPercentage(cds[0], IConstant.M_INTER_PER, 0)));
                        data[54] = Integer.parseInt(JDBCDatabaseManager.getProgramCode(cds[2].getGroup()));
                        data[55] = cn.getYearsDegree();
                        data[56] = cds[2].getMarksObtained();
                        data[57] = cn.getDeductionMarks();
                        data[58] = cds[2].getTotalMarks();
                        data[59] = cds[2].getPassingYear();
                        data[60] = cds[2].getSeatNo();
                        data[61] = cds[2].getIssuerCode();
                        data[62] = Float.parseFloat(nf.format(CandidateHelper.getPercentage(cds[2], IConstant.DEGREE_PER, cn.getDeductionMarks())));
                        data[63] = cn.getTestScore();
                        data[64] = cn.getPercentage();
                        data[65] = "NIL";
                        data[66] = "NIL";
                        data[67] = "NIL";
                        data[68] = "NIL";
                        data[69] = "NIL";

                        List<AdmissionListDetails> aldList = JDBCDatabaseManager.getAdmissionListDetail(cn.getCandidateId(), al.getAdmissionListId());
                        if (!aldList.isEmpty()) {
                            for (int j = 65, k = 0; j <= 69; j++, k++) {
                                if (k >= aldList.size()) {
                                    break;
                                }

                                AdmissionListDetails ald = aldList.get(k);
                                data[j] = ald.getPosPCode() + ", " + ald.getCampusCategoryName() + ", " + ald.getShiftName();
                            }
                        }

                        dbf.addRecord(data);
                        progressBar.setValue(i);
                    }

                    dbf.close();
                    ExportCandidatesDetailInternalFrame.this.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));

                    try {
                        admission.utils.MessageBox.info(ExportCandidatesDetailInternalFrame.this, "Process completed");
                        Desktop.getDesktop().open(new File(path));
                    } catch (IOException ex) {
                        Logger.getLogger(ExportCandidatesDetailInternalFrame.class.getName()).log(Level.SEVERE, null, ex);
                        admission.utils.MessageBox.info(ExportCandidatesDetailInternalFrame.this, ex);
                    }

                } catch (SQLException | JDBFException ex) {
                    ExportCandidatesDetailInternalFrame.this.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
                    Logger.getLogger(ExportCandidatesDetailInternalFrame.class.getName()).log(Level.SEVERE, null, ex);
                    admission.utils.MessageBox.info(ExportCandidatesDetailInternalFrame.this, ex);
                }

                exportButton.setEnabled(true);
                backButton.setEnabled(true);
            }
        }.start();
    }

    private int getChange(String grp) {
        String[] s = new String[]{"", "SC", "AR", "PM", "PE", "AR", "HM", "GS", "GSB", "CM", "CD", "", "OD"};
        for (int j = 0; j < s.length; j++) {
            if (s[j].equals(grp)) {
                return j;
            }
        }
        return 0;
    }

    private int getCatCode(String cat) {
        String cats[] = new String[]{"", "",
            IConstant.GM_DUR_QUOTA,
            IConstant.SUE_QUOTA,
            IConstant.CN_SUE_QUOTA,
            IConstant.AC_QUOTA,
            IConstant.DP_QUOTA,
            IConstant.SFM_QUOTA,
            IConstant.SFE_QUOTA,
            IConstant.NO_QUOTA,
            IConstant.SP_QUOTA,
            IConstant.AP_QUOTA};

        for (int i = 0; i < cats.length; i++) {
            if (cats[i].equals(cat)) {
                return i;
            }
        }
        return 0;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox admissionListComboBox;
    private javax.swing.JComboBox admissionSessionComboBox;
    private javax.swing.JComboBox admissionYearComboBox;
    private javax.swing.JButton backButton;
    private javax.swing.JComboBox campusComboBox;
    private javax.swing.JButton exportButton;
    private javax.swing.JComboBox exportToComboBox;
    private javax.swing.JFileChooser fileChooser;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JButton nonSelectedButton;
    private javax.swing.JComboBox programTypeComboBox;
    private javax.swing.JProgressBar progressBar;
    private javax.swing.JButton selectedButton;
    // End of variables declaration//GEN-END:variables
    private Resources privileges;
}
