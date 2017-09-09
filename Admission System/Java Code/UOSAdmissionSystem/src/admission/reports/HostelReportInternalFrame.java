package admission.reports;

import admission.controller.DatabaseManager;
import admission.controller.JDBCDatabaseManager;
import admission.model.AdmissionYear;
import admission.model.Part;
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
import admission.utils.RollNoFormatter;
import admission.utils.RoundedBorder;

/**
 *
 * @author Raja Kumar & Jay Kumar
 */
public class HostelReportInternalFrame extends javax.swing.JInternalFrame {

    public HostelReportInternalFrame() {
        initComponents();
        
        admission.utils.Utility.hideOnEscape(this);
        
        this.titlePanel.setBorder(RoundedBorder.createGradientBorder());
    }

    @Override
    public void setVisible(boolean aFlag) {
        if(aFlag) {
            this.getAdmissionYear();
            this.getProgramType();
            this.getShift();
            this.getPart();
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
        exportButton = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        programTypeComboBox = new javax.swing.JComboBox();
        jLabel11 = new javax.swing.JLabel();
        admissionYearComboBox = new javax.swing.JComboBox();
        backButton = new javax.swing.JButton();
        progressBar = new javax.swing.JProgressBar();
        jLabel6 = new javax.swing.JLabel();
        shiftComboBox = new javax.swing.JComboBox();
        jLabel7 = new javax.swing.JLabel();
        reportOfComboBox = new javax.swing.JComboBox();
        titlePanel = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        partComboBox = new javax.swing.JComboBox();
        jLabel25 = new javax.swing.JLabel();

        fileChooser.setDialogType(javax.swing.JFileChooser.SAVE_DIALOG);
        fileChooser.setFileSelectionMode(javax.swing.JFileChooser.DIRECTORIES_ONLY);
        fileChooser.setName(""); // NOI18N

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setIconifiable(true);
        setTitle("Hostel Report");

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

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel6.setText("Shift:");

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel7.setText("Report of:");

        reportOfComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Boys", "Girls" }));

        titlePanel.setLayout(new java.awt.BorderLayout());

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/admission/view/images/report-icon.png"))); // NOI18N
        jLabel2.setText("Hostel Report");
        jLabel2.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, java.awt.Color.darkGray), javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5)));
        titlePanel.add(jLabel2, java.awt.BorderLayout.PAGE_END);

        jLabel25.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel25.setText("Part:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(progressBar, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(exportButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(backButton))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel25, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(programTypeComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(admissionYearComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(shiftComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(partComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(10, 10, 10)
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(reportOfComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                .addContainerGap())
            .addComponent(titlePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(titlePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(admissionYearComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(programTypeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(shiftComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(partComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel25)
                    .addComponent(jLabel7)
                    .addComponent(reportOfComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(backButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(exportButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(progressBar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {admissionYearComboBox, programTypeComboBox});

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
    private void getPart(){
        this.partComboBox.removeAllItems();
        
        List<Part> list = DatabaseManager.getData(Part.class.getName(), "partId");
        for (int i = 0; i < list.size(); i++) {
            partComboBox.addItem(list.get(i));
        }
    }
//    private void getDistrict(){
//        utilities.Utilities.removeTableRows(defaultTableModel);
//        
//        List<District> list = DatabaseManager.getData(District.class.getName(), "name");
//        
//        for (int i = 0; i < list.size(); i++) {
//            defaultTableModel.addRow(new Object[]{list.get(i), false});
//        }
//    }
    
    private void export() {
        if(fileChooser.showSaveDialog(this) != JFileChooser.APPROVE_OPTION) return;
        
        AdmissionYear ay = (AdmissionYear) admissionYearComboBox.getSelectedItem();
        ProgramType pt = (ProgramType) programTypeComboBox.getSelectedItem();
        Shift shift = (Shift) shiftComboBox.getSelectedItem();
        Part part = (Part) partComboBox.getSelectedItem();
        if(ay == null || pt == null || shift == null || part == null) return;
        
        String reportOf = reportOfComboBox.getSelectedItem().toString();
        
        File folder = fileChooser.getSelectedFile();
        File file = new File(folder.getAbsoluteFile() + "/" + reportOf + " Hostel Report.csv");
        
        reportOf = reportOf.equals("Boys") ? "M" : "F";
        
        try (java.io.PrintStream out = new PrintStream(new FileOutputStream(file));){
            exportButton.setEnabled(false);
            backButton.setEnabled(false);
        
            out.println("SNO,SEAT NO.,NAME,FATHER'S NAME,SURNAME,DISTRICT,ROLL NO.,CATEGORY,SELECTION PROGRAM,FINAL SCORE");

            int count = 0;
            try {
                List<HashMap<String, String>> rows = JDBCDatabaseManager.getHostelData(ay.getAdmissionYearId(), pt.getProgramTypeId(), shift.getShiftId(), part.getPartId(), reportOfComboBox.getSelectedIndex(), true);
                progressBar.setMaximum(rows.size() - 1);
                progressBar.setValue(0);
                for (int i = 0; i < rows.size(); i++) {
                    HashMap<String, String> map = rows.get(i);
                    
                    String s = (++count) + ",";
                    s += map.get("seat_no") + ",";
                    s += map.get("name") + ",";
                    s += map.get("fname") + ",";
                    s += map.get("surname") + ",";
                    s += map.get("district") + ",";
                    s += RollNoFormatter.format(ay.getYear(), map.get("code"), Integer.parseInt(map.get("roll_no"))) + ",";
                    s += map.get("category") + ",";
                    s += admission.utils.Utility.programFormat((pt.getIsBachelor()), (shift.getIsMorning()), map.get("program"), map.get("selection"), part.getName()) + ",";
                    s += admission.utils.NumberFormatter.format(Float.parseFloat(map.get("percentage")));
                    
                    out.println(s);
                    progressBar.setValue(i + 1);
                }
                
                admission.utils.MessageBox.info(this, rows.size() + " records export successfully");
            } catch (SQLException ex) {
                Logger.getLogger(HostelReportInternalFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(HostelReportInternalFrame.class.getName()).log(Level.SEVERE, null, ex);
            admission.utils.MessageBox.error(this, ex);
        }
        
        exportButton.setEnabled(true);
        backButton.setEnabled(true);
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox admissionYearComboBox;
    private javax.swing.JButton backButton;
    private javax.swing.JButton exportButton;
    private javax.swing.JFileChooser fileChooser;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JComboBox partComboBox;
    private javax.swing.JComboBox programTypeComboBox;
    private javax.swing.JProgressBar progressBar;
    private javax.swing.JComboBox reportOfComboBox;
    private javax.swing.JComboBox shiftComboBox;
    private javax.swing.JPanel titlePanel;
    // End of variables declaration//GEN-END:variables
    private Resources privileges;
    private DefaultTableModel defaultTableModel;
}
