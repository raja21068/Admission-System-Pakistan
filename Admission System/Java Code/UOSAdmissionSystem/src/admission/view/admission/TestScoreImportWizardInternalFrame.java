package admission.view.admission;

import admission.controller.DatabaseManager;
import admission.model.AdmissionYear;
import admission.model.Campus;
import admission.model.Candidate;
import admission.model.security.Resources;
import admission.model.ProgramType;
import admission.model.Test;
import admission.utils.CandidateHelper;
import java.awt.HeadlessException;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JScrollBar;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.apache.poi.ss.usermodel.Row;
import org.hibernate.HibernateException;
import admission.utils.IConstant;
import admission.utils.ExcelDocumentReader;
import admission.utils.MessageBox;
import admission.utils.RoundedBorder;

/**
 *
 * @author Raja Kumar & Jay Kumar
 */
public class TestScoreImportWizardInternalFrame extends javax.swing.JInternalFrame {

    public TestScoreImportWizardInternalFrame() {
        initComponents();
        
        admission.utils.Utility.hideOnEscape(this);
        this.titlePanel.setBorder(RoundedBorder.createGradientBorder());
        
        fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("Microsoft Excel Documents[.xls, .xlsx]", "xls", "xlsx"));
        //fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("CSV Documents[.csv]", "csv"));
    }

    @Override
    public void setVisible(boolean aFlag) {
        if(aFlag) {
            this.getCampus();
            this.getAdmissionYear();
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
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        browseButton = new javax.swing.JButton();
        admissionYearComboBox = new javax.swing.JComboBox();
        programTypeComboBox = new javax.swing.JComboBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        successResultTextArea = new javax.swing.JTextArea();
        jLabel4 = new javax.swing.JLabel();
        filePathTextField = new javax.swing.JTextField();
        startProcessButton = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        progressBar = new javax.swing.JProgressBar();
        successCountLabel = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        campusComboBox = new javax.swing.JComboBox();
        jLabel6 = new javax.swing.JLabel();
        seatNoIndexTextField = new javax.swing.JTextField();
        marksIndexTextField = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        failedResultTextArea = new javax.swing.JTextArea();
        failedCountLabel = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        titlePanel = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        backButton = new javax.swing.JButton();

        fileChooser.setAcceptAllFileFilterUsed(false);
        fileChooser.setApproveButtonText("Open");
        fileChooser.setDialogTitle("Select file");

        setClosable(true);
        setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);
        setIconifiable(true);
        setTitle("Test Score Import Wizard");

        jLabel2.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel2.setText("Admission Year:");

        jLabel3.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel3.setText("Program Type:");

        browseButton.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        browseButton.setText("Browse");
        browseButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                browseButtonActionPerformed(evt);
            }
        });

        admissionYearComboBox.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        programTypeComboBox.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        successResultTextArea.setColumns(20);
        successResultTextArea.setRows(5);
        jScrollPane1.setViewportView(successResultTextArea);

        jLabel4.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel4.setText("Select Excel file:");

        filePathTextField.setEditable(false);

        startProcessButton.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        startProcessButton.setText("Start Process");
        startProcessButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                startProcessButtonActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel5.setText("Process: result:");

        progressBar.setStringPainted(true);

        successCountLabel.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        successCountLabel.setText("0 / 0");

        jLabel15.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel15.setText("Campus:");

        campusComboBox.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        campusComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                campusComboBoxActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel6.setText("Enter Seat No column index:");

        seatNoIndexTextField.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        seatNoIndexTextField.setText("0");

        marksIndexTextField.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        marksIndexTextField.setText("1");

        jLabel7.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel7.setText("Enter Marks column index:");

        failedResultTextArea.setColumns(20);
        failedResultTextArea.setRows(5);
        jScrollPane2.setViewportView(failedResultTextArea);

        failedCountLabel.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        failedCountLabel.setText("0 / 0");

        jLabel8.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel8.setText("Note: Index starts from 0");
        jLabel8.setEnabled(false);

        titlePanel.setLayout(new java.awt.BorderLayout());

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Gnome-X-Office-Spreadsheet-40.png"))); // NOI18N
        jLabel9.setText("Test Score Import Wizard");
        jLabel9.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, java.awt.Color.darkGray), javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5)));
        titlePanel.add(jLabel9, java.awt.BorderLayout.CENTER);

        backButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Gnome-Go-Home-32.png"))); // NOI18N
        backButton.setToolTipText("Close");
        backButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(titlePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(progressBar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(startProcessButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(successCountLabel))
                            .addComponent(jScrollPane1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(failedCountLabel, javax.swing.GroupLayout.Alignment.TRAILING)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(seatNoIndexTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(marksIndexTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel8)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(filePathTextField)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(browseButton))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(admissionYearComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(programTypeComboBox, 0, 179, Short.MAX_VALUE))
                            .addComponent(campusComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(backButton)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(titlePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(backButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(filePathTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(browseButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(admissionYearComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(programTypeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(campusComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(seatNoIndexTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(marksIndexTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(startProcessButton, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(progressBar, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(failedCountLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel5)
                        .addComponent(successCountLabel)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 186, Short.MAX_VALUE))
                .addContainerGap())
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {admissionYearComboBox, campusComboBox, filePathTextField, marksIndexTextField, programTypeComboBox, seatNoIndexTextField});

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void browseButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_browseButtonActionPerformed
        if(this.fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION){
            File file = fileChooser.getSelectedFile();
            if(file.exists()){
                this.selectedFile = file;
                this.filePathTextField.setText(file.getAbsolutePath());
            }
        }
    }//GEN-LAST:event_browseButtonActionPerformed

    private void startProcessButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_startProcessButtonActionPerformed
        // TODO add your handling code here:
        int approved = MessageBox.confirm2(this, "Do you want insert data?");
        if(approved != JOptionPane.YES_OPTION){
            return;
        }
        final String i1 = seatNoIndexTextField.getText();
        final String i2 = marksIndexTextField.getText();
        if(i1.isEmpty() || i2.isEmpty()) {
            admission.utils.MessageBox.info(this, "Please enter index of column");
            return;
        }
        new Thread(){
            @Override public void run() {
                setButtonEnabled(false);
                Campus campus = (Campus) campusComboBox.getSelectedItem();
                AdmissionYear ay = (AdmissionYear) admissionYearComboBox.getSelectedItem();
                ProgramType pt = (ProgramType) programTypeComboBox.getSelectedItem();
                if(campus == null || ay == null || pt == null) return;
        
                Integer campusId = campus.getCampusId();
                Integer admissionYearId = ay.getAdmissionYearId();
                Integer programTypeId = pt.getProgramTypeId();
        
                Test test = DatabaseManager.getSingleRecord(Test.class, "campus.campusId = " + campusId + " AND admissionYear.admissionYearId = " + admissionYearId + " AND programType.programTypeId = " + programTypeId);
//                Test test = DatabaseManager.getTest(campusId, admissionYearId, programTypeId);
                if(test == null){
                    admission.utils.MessageBox.info(TestScoreImportWizardInternalFrame.this, "Test record not found in selected campus, academic year and program type");
                    return;
                }
                if(selectedFile.exists()){
                    String path = selectedFile.getAbsolutePath();
                    try {
                        if(path.endsWith(".xls") || path.endsWith(".xlsx")){                            
                            ExcelDocumentReader excelDoc = new ExcelDocumentReader(path);
                            Integer index1 = Integer.parseInt(i1);
                            Integer index2 = Integer.parseInt(i2);
                            
                            int totalRows = excelDoc.getRowCount() + 1, count = 0, failedCount = 0;
                            progressBar.setMaximum(totalRows);
                            
                            Iterator<Row> rows = excelDoc.getRowIterator();
                            rows.next(); // ignore first row
                            
                            String fileName = "not_found.csv";
                            PrintStream out = new PrintStream(fileName);
                            out.println("Seat_No,Test_Score");

                            while (rows.hasNext()){
                                Row row = rows.next();
                                //Iterator<Cell> cells = rows.next().cellIterator();
                                String seatNo = String.valueOf((int) Float.parseFloat(row.getCell(index1).toString()));
                                Integer score = (int) Float.parseFloat(row.getCell(index2).toString());
                                
//                                Float testPer = score * IConstant.TEST_PER;//Float.parseFloat(cells.next().toString());
                                
                                String where = "admissionYear.admissionYearId = " + admissionYearId + " AND programType.programTypeId = " + programTypeId + " AND seatNo = " + seatNo;
                                Candidate c = DatabaseManager.getSingleRecord(Candidate.class, where);
//                                Candidate c = DatabaseManager.getCandidate(admissionYearId, programTypeId, seatNo);
                                if(c == null) {
                                    failedCount++;
                                    failedResultTextArea.append("failed: " + seatNo + " - " + score + "\n");
                                    out.println(seatNo + "," + score);
                                    failedCountLabel.setText("" + failedCount);
                                    JScrollBar vScroll = jScrollPane1.getVerticalScrollBar();
                                    vScroll.setValue(vScroll.getMaximum());
                                    continue;
                                }
                                
                                float percentage = CandidateHelper.getPercentage(c, score);
                                
                                c.setTest(test);
                                c.setTestScore(score);
                                c.setPercentage(percentage);
                                DatabaseManager.updateData(c);
                                progressBar.setValue(++count);
                                successCountLabel.setText(count + "/" + totalRows);
                                successResultTextArea.append("(" + seatNo + ", " + c.getSeatNo() + ") - " + score + " - " + percentage + "\n");
                                JScrollBar vScroll = jScrollPane1.getVerticalScrollBar();
                                vScroll.setValue(vScroll.getMaximum());
                            }
                            successResultTextArea.append("Added record: " + count + ", failed record: " + failedCount);
                            JScrollBar vScroll = jScrollPane1.getVerticalScrollBar();
                            vScroll.setValue(vScroll.getMaximum());
                            progressBar.setValue(progressBar.getMaximum());
                            Runtime.getRuntime().exec("notepad " + fileName);
                            admission.utils.MessageBox.info(TestScoreImportWizardInternalFrame.this, count + " record added successfully, failed record: " + failedCount);
                        }
                    } catch (HeadlessException | IOException | NumberFormatException | HibernateException ex) {
                        admission.utils.MessageBox.info(TestScoreImportWizardInternalFrame.this, ex);
                        Logger.getLogger(TestScoreImportWizardInternalFrame.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                setButtonEnabled(true);
            }
        }.start();
    }//GEN-LAST:event_startProcessButtonActionPerformed

    private void campusComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_campusComboBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_campusComboBoxActionPerformed

    private void backButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backButtonActionPerformed
        setVisible(false);
    }//GEN-LAST:event_backButtonActionPerformed
    
    private void getCampus(){
        this.campusComboBox.removeAllItems();
        
        List<Campus> list = DatabaseManager.getData(Campus.class.getName(), "name");
        
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
    
    private void setButtonEnabled(boolean b){
        this.browseButton.setEnabled(b);
        this.startProcessButton.setEnabled(b);
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox admissionYearComboBox;
    private javax.swing.JButton backButton;
    private javax.swing.JButton browseButton;
    private javax.swing.JComboBox campusComboBox;
    private javax.swing.JLabel failedCountLabel;
    private javax.swing.JTextArea failedResultTextArea;
    private javax.swing.JFileChooser fileChooser;
    private javax.swing.JTextField filePathTextField;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField marksIndexTextField;
    private javax.swing.JComboBox programTypeComboBox;
    private javax.swing.JProgressBar progressBar;
    private javax.swing.JTextField seatNoIndexTextField;
    private javax.swing.JButton startProcessButton;
    private javax.swing.JLabel successCountLabel;
    private javax.swing.JTextArea successResultTextArea;
    private javax.swing.JPanel titlePanel;
    // End of variables declaration//GEN-END:variables
    private Resources privileges;
    private File selectedFile;
}
