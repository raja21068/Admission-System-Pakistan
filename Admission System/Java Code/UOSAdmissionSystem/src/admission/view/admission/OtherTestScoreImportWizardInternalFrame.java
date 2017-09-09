package admission.view.admission;

import admission.controller.DatabaseManager;
import admission.model.AdmissionYear;
import admission.model.Campus;
import admission.model.CampusProgramOfStudy;
import admission.model.Candidate;
import admission.model.OtherTest;
import admission.model.security.Resources;
import admission.model.ProgramType;
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
import admission.utils.ExcelDocumentReader;

/**
 *
 * @author Raja Kumar & Jay Kumar
 */
public class OtherTestScoreImportWizardInternalFrame extends javax.swing.JInternalFrame {

    public OtherTestScoreImportWizardInternalFrame() {
        initComponents();
        
        admission.utils.Utility.hideOnEscape(this);
        fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("Microsoft Excel Documents[.xls, .xlsx]", "xls", "xlsx"));
        //fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("CSV Documents[.csv]", "csv"));
    }

    @Override
    public void setVisible(boolean aFlag) {
        if(aFlag) {
            this.getProgramType();
            this.getAdmissionYear();
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
        jLabel16 = new javax.swing.JLabel();
        campusProgramOfStudyComboBox = new javax.swing.JComboBox();
        choiceNoIndexTextField = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();

        fileChooser.setAcceptAllFileFilterUsed(false);
        fileChooser.setApproveButtonText("Open");
        fileChooser.setDialogTitle("Select file");

        setClosable(true);
        setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);
        setIconifiable(true);
        setTitle("Other Test Score Import Wizard");

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Other Test Score Import Wizard");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel2.setText("Admission Year:");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel3.setText("Program Type:");

        browseButton.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        browseButton.setText("Browse");
        browseButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                browseButtonActionPerformed(evt);
            }
        });

        successResultTextArea.setColumns(20);
        successResultTextArea.setRows(5);
        jScrollPane1.setViewportView(successResultTextArea);

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel4.setText("Select file:");

        filePathTextField.setEditable(false);

        startProcessButton.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        startProcessButton.setText("Start Process");
        startProcessButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                startProcessButtonActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel5.setText("Process: result:");

        progressBar.setStringPainted(true);

        successCountLabel.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        successCountLabel.setText("0 / 0");

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel15.setText("Campus:");

        campusComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                campusComboBoxActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel6.setText("Seat No column index:");

        seatNoIndexTextField.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        seatNoIndexTextField.setText("0");

        marksIndexTextField.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        marksIndexTextField.setText("1");

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel7.setText("Marks column index:");

        failedResultTextArea.setColumns(20);
        failedResultTextArea.setRows(5);
        jScrollPane2.setViewportView(failedResultTextArea);

        failedCountLabel.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        failedCountLabel.setText("0 / 0");

        jLabel8.setText("Note: Index starts from 0");
        jLabel8.setEnabled(false);

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel16.setText("Campus POS:");

        choiceNoIndexTextField.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        choiceNoIndexTextField.setText("2");

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel9.setText("Choice No column index:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 520, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(4, 4, 4)
                        .addComponent(campusComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 425, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(4, 4, 4)
                        .addComponent(campusProgramOfStudyComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 425, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addGap(4, 4, 4)
                        .addComponent(seatNoIndexTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(4, 4, 4)
                        .addComponent(jLabel7)
                        .addGap(4, 4, 4)
                        .addComponent(marksIndexTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(4, 4, 4)
                        .addComponent(jLabel9)
                        .addGap(4, 4, 4)
                        .addComponent(choiceNoIndexTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel8)
                    .addComponent(startProcessButton, javax.swing.GroupLayout.PREFERRED_SIZE, 520, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(progressBar, javax.swing.GroupLayout.PREFERRED_SIZE, 520, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(152, 152, 152)
                        .addComponent(successCountLabel)
                        .addGap(230, 230, 230)
                        .addComponent(failedCountLabel))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 264, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(4, 4, 4)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(filePathTextField)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(browseButton))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(admissionYearComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(4, 4, 4)
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(4, 4, 4)
                                .addComponent(programTypeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addComponent(jLabel1)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(filePathTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addComponent(browseButton)))
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(admissionYearComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(programTypeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3))))
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jLabel15))
                    .addComponent(campusComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jLabel16))
                    .addComponent(campusProgramOfStudyComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(seatNoIndexTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel6))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(marksIndexTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel7))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(choiceNoIndexTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel9)))
                .addGap(6, 6, 6)
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11)
                .addComponent(startProcessButton, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addComponent(progressBar, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(successCountLabel)
                    .addComponent(failedCountLabel))
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {admissionYearComboBox, browseButton, campusComboBox, campusProgramOfStudyComboBox, choiceNoIndexTextField, filePathTextField, marksIndexTextField, programTypeComboBox, seatNoIndexTextField});

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
        int approved = JOptionPane.showConfirmDialog(campusComboBox, "Do you want insert data?", "Message", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if(approved != JOptionPane.YES_OPTION){
            return;
        }
        final String i1 = seatNoIndexTextField.getText();
        final String i2 = marksIndexTextField.getText();
        final String i3 = choiceNoIndexTextField.getText();
        if(i1.isEmpty() || i2.isEmpty() || i3.isEmpty()) {
            admission.utils.MessageBox.info(this, "Please enter index of column");
            return;
        }
        new Thread(){
            @Override public void run() {
                setButtonEnabled(false);
                CampusProgramOfStudy cpos = (CampusProgramOfStudy) campusProgramOfStudyComboBox.getSelectedItem();
                AdmissionYear ay = (AdmissionYear) admissionYearComboBox.getSelectedItem();
                ProgramType pt = (ProgramType) programTypeComboBox.getSelectedItem();
                if(cpos == null || ay == null || pt == null) return;
        
//                Integer campusId = campus.getCampusId();
                Integer admissionYearId = ay.getAdmissionYearId();
                Integer programTypeId = pt.getProgramTypeId();

                if(selectedFile.exists()){
                    String path = selectedFile.getAbsolutePath();
                    try {
                        if(path.endsWith(".xls") || path.endsWith(".xlsx")){                            
                            ExcelDocumentReader excelDoc = new ExcelDocumentReader(path);
                            Integer index1 = Integer.parseInt(i1);
                            Integer index2 = Integer.parseInt(i2);
                            Integer index3 = Integer.parseInt(i3);
                            
                            int totalRows = excelDoc.getRowCount() + 1, count = 0, failedCount = 0;
                            progressBar.setMaximum(totalRows);
                            
                            Iterator<Row> rows = excelDoc.getRowIterator();
                            rows.next();
                            
                            String fileName = "not_found.csv";
                            PrintStream out = new PrintStream(fileName);
                            out.println("Seat_No,Test_Score,Choice_no");

                            while (rows.hasNext()){
                                Row row = rows.next();
                                //Iterator<Cell> cells = rows.next().cellIterator();
                                String seatNo = String.valueOf((int) Float.parseFloat(row.getCell(index1).toString()));
                                Integer score = (int) Float.parseFloat(row.getCell(index2).toString());
                                Integer choiceNo = (int) Float.parseFloat(row.getCell(index3).toString());
                                
                                Candidate candidate = DatabaseManager.getCandidate(admissionYearId, programTypeId, seatNo);
                                if(candidate == null) {
                                    failedCount++;
                                    failedResultTextArea.append("failed: " + seatNo + " - " + score + " - " + choiceNo + "\n");
                                    out.println(seatNo + "," + score);
                                    failedCountLabel.setText("" + failedCount);
                                    JScrollBar vScroll = jScrollPane1.getVerticalScrollBar();
                                    vScroll.setValue(vScroll.getMaximum());
                                    continue;
                                }
                                OtherTest ot = new OtherTest(candidate, cpos, score, choiceNo, "");
                                DatabaseManager.addData(ot);
                                DatabaseManager.refresh(candidate);
                                progressBar.setValue(++count);
                                successCountLabel.setText(count + "/" + totalRows);
                                successResultTextArea.append("(" + seatNo + ", " + candidate.getSeatNo() + ") - " + score + " - " + choiceNo + "\n");
                                JScrollBar vScroll = jScrollPane1.getVerticalScrollBar();
                                vScroll.setValue(vScroll.getMaximum());
                            }
                            successResultTextArea.append("Added record: " + count + ", failed record: " + failedCount);
                            JScrollBar vScroll = jScrollPane1.getVerticalScrollBar();
                            vScroll.setValue(vScroll.getMaximum());
                            progressBar.setValue(progressBar.getMaximum());
                            Runtime.getRuntime().exec("notepad " + fileName);
                            admission.utils.MessageBox.info(OtherTestScoreImportWizardInternalFrame.this, count + " record added successfully, failed record: " + failedCount);
                        }
                    } catch (HeadlessException | IOException | NumberFormatException | HibernateException ex) {
                        admission.utils.MessageBox.info(OtherTestScoreImportWizardInternalFrame.this, ex);
                        Logger.getLogger(OtherTestScoreImportWizardInternalFrame.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                setButtonEnabled(true);
            }
        }.start();
    }//GEN-LAST:event_startProcessButtonActionPerformed

    private void campusComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_campusComboBoxActionPerformed
        // TODO add your handling code here:
        getCampusProgramOfStudy();
    }//GEN-LAST:event_campusComboBoxActionPerformed
    
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
    
    private void getCampusProgramOfStudy() {
        this.campusProgramOfStudyComboBox.removeAllItems();
        
        Campus campus = (Campus) this.campusComboBox.getSelectedItem();
        ProgramType pt = (ProgramType) this.programTypeComboBox.getSelectedItem();
        if(campus == null || pt == null) return;
        
        List<CampusProgramOfStudy> list = DatabaseManager.getCampusProgramOfStudy(campus.getCampusId(), pt.getProgramTypeId(), Byte.valueOf("1"));
        for (int i = 0; i < list.size(); i++) {
            campusProgramOfStudyComboBox.addItem(list.get(i));
        }
    }
    private void setButtonEnabled(boolean b){
        this.browseButton.setEnabled(b);
        this.startProcessButton.setEnabled(b);
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox admissionYearComboBox;
    private javax.swing.JButton browseButton;
    private javax.swing.JComboBox campusComboBox;
    private javax.swing.JComboBox campusProgramOfStudyComboBox;
    private javax.swing.JTextField choiceNoIndexTextField;
    private javax.swing.JLabel failedCountLabel;
    private javax.swing.JTextArea failedResultTextArea;
    private javax.swing.JFileChooser fileChooser;
    private javax.swing.JTextField filePathTextField;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
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
    // End of variables declaration//GEN-END:variables
    private Resources privileges;
    private File selectedFile;
}
