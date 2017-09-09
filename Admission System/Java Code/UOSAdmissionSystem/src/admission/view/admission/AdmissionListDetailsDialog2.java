package admission.view.admission;

import admission.controller.DatabaseManager;
import admission.controller.JDBCDatabaseManager;
import admission.controller.JDBCDatabaseManagerAlg;
import admission.controller.algorithm.AdmissionController;
import admission.controller.algorithm.CandidateAlg;
import admission.controller.algorithm.CategoryAlg;
import admission.controller.algorithm.DisciplineAlg;
import admission.reports.beans.CredentialDetailJRBean;
import admission.utils.ExcelHandler;
import admission.enums.AreaEnum;
import admission.enums.CategoryEnum;
import admission.enums.CategoryLogicalCodeEnum;
import admission.enums.GenderEnum;
import admission.enums.MessageEnum;
import admission.model.AdmissionList;
import admission.model.AdmissionSession;
import admission.model.AdmissionYear;
import admission.model.Campus;
import admission.model.District;
import admission.model.DistrictSeatDistribution;
import admission.model.ProgramOfStudy;
import admission.model.ProgramType;
import admission.model.Shift;
import admission.model.security.Resources;
import admission.reports.SelectionReport;
import admission.reports.beans.SelectionCandidateBean;
import admission.reports.beans.SelectionCategoryBean;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.DocumentFilter;
import admission.utils.IConstant;
import admission.utils.MessageBox;
import javax.swing.JFileChooser;

/**
 *
 * @author Raja Kumar & Jay Kumar
 */
public class AdmissionListDetailsDialog2 extends javax.swing.JDialog {

    java.awt.Frame superparent;

    public AdmissionListDetailsDialog2(JPanel parent, java.awt.Frame superparent) {
        this(parent);
        this.superparent = superparent;
    }

    public AdmissionListDetailsDialog2(JPanel parent) {
        super(JOptionPane.getFrameForComponent(parent), true);
        initComponents();

        this.setLocationRelativeTo(null);

        excelFilter = new FileNameExtensionFilter("(*.xls)", "xls");
    }

    public void setVisible(AdmissionList admissionList, boolean aFlag) {
        if (aFlag) {
            this.admissionList = admissionList;
            clear();
            setTitles();
        }
        super.setVisible(aFlag);
    }

    public void setPrivileges(Resources privileges) {
        this.privileges = privileges;
//        this.addButton.setEnabled(Coder.Decoder.booleanDecode(privileges.getAddPrivilige()));
//        this.updateButton.setEnabled(Coder.Decoder.booleanDecode(privileges.getUpdatePrivilige()));
//        this.deleteButton.setEnabled(Coder.Decoder.booleanDecode(privileges.getDeletePrivilige()));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        fileChooser = new javax.swing.JFileChooser();
        buttonGroup1 = new javax.swing.ButtonGroup();
        generateListButton = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        filterProgressBar = new javax.swing.JProgressBar();
        jLabel12 = new javax.swing.JLabel();
        totalCandidateLabel = new javax.swing.JLabel();
        disciplinesLabel = new javax.swing.JLabel();
        listOfLabel = new javax.swing.JLabel();
        candidateLabel = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        timeLabel = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        totalCandidateChoicesLabel = new javax.swing.JLabel();
        backButton = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        yesRadioButton = new javax.swing.JRadioButton();
        noRadioButton = new javax.swing.JRadioButton();
        seperateTestCheckBox = new javax.swing.JCheckBox();
        titlePanel = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        dataProgressBar = new javax.swing.JProgressBar();
        loadDataButton = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        defaulterCandidateCheckBox = new javax.swing.JCheckBox();
        jLabel13 = new javax.swing.JLabel();
        exportButton = new javax.swing.JButton();
        buttonPrint = new javax.swing.JButton();
        buttonStoreInDatabase = new javax.swing.JButton();
        buttonObjectionRemarks = new javax.swing.JButton();

        fileChooser.setDialogType(javax.swing.JFileChooser.SAVE_DIALOG);
        fileChooser.setApproveButtonToolTipText("");
        fileChooser.setFileSelectionMode(javax.swing.JFileChooser.DIRECTORIES_ONLY);

        setTitle("Admission List Details");
        setResizable(false);

        generateListButton.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        generateListButton.setText("Generate List");
        generateListButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                generateListButtonActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel5.setText("Total Candidates:");

        jLabel10.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel10.setText("List of:");

        jLabel11.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel11.setText("Disciplines:");

        filterProgressBar.setStringPainted(true);

        jLabel12.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel12.setText("Data Filteration:");

        totalCandidateLabel.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        totalCandidateLabel.setText("0");

        disciplinesLabel.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        disciplinesLabel.setText("0");

        listOfLabel.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        listOfLabel.setText("abc");

        final java.awt.Dimension dd = candidateLabel.getPreferredSize();
        candidateLabel.setMinimumSize(dd);
        candidateLabel.setPreferredSize(dd);

        jLabel3.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel3.setText("Time Elapsed:");

        timeLabel.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        timeLabel.setText("hh:mm:ss");

        jLabel6.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel6.setText("Total Candidates Choices:");

        totalCandidateChoicesLabel.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        totalCandidateChoicesLabel.setText("0");

        backButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Gnome-Go-Home-32.png"))); // NOI18N
        backButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backButtonActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel4.setText("Store in database:");

        buttonGroup1.add(yesRadioButton);
        yesRadioButton.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        yesRadioButton.setText("Yes");

        buttonGroup1.add(noRadioButton);
        noRadioButton.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        noRadioButton.setSelected(true);
        noRadioButton.setText("No");

        seperateTestCheckBox.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        seperateTestCheckBox.setText("Seperate Test");
        seperateTestCheckBox.setToolTipText("Seperate conducted program test selection");

        titlePanel.setLayout(new java.awt.BorderLayout());

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Generate-tables-icon40.png"))); // NOI18N
        jLabel7.setText("Admission List Details");
        jLabel7.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, java.awt.Color.darkGray), javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5)));
        titlePanel.add(jLabel7, java.awt.BorderLayout.PAGE_END);

        dataProgressBar.setStringPainted(true);

        loadDataButton.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        loadDataButton.setText("Load Data");
        loadDataButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loadDataButtonActionPerformed(evt);
            }
        });

        defaulterCandidateCheckBox.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        defaulterCandidateCheckBox.setText("Defaulter Candidate");
        defaulterCandidateCheckBox.setToolTipText("Seperate conducted program test selection");

        jLabel13.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel13.setText("Data Loading:");

        exportButton.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        exportButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/import-export-icon.png"))); // NOI18N
        exportButton.setText("Export");
        exportButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exportButtonActionPerformed(evt);
            }
        });

        buttonPrint.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        buttonPrint.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Print.png"))); // NOI18N
        buttonPrint.setText("Print");
        buttonPrint.setEnabled(false);
        buttonPrint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonPrintActionPerformed(evt);
            }
        });

        buttonStoreInDatabase.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        buttonStoreInDatabase.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Generate-tables-icon (1).png"))); // NOI18N
        buttonStoreInDatabase.setText("Store In Database");
        buttonStoreInDatabase.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonStoreInDatabaseActionPerformed(evt);
            }
        });

        buttonObjectionRemarks.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        buttonObjectionRemarks.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Gnome-Document-Print-32.png"))); // NOI18N
        buttonObjectionRemarks.setEnabled(false);
        buttonObjectionRemarks.setLabel("Print Objection");
        buttonObjectionRemarks.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonObjectionRemarksActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(titlePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jSeparator1)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(filterProgressBar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(candidateLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.LEADING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(disciplinesLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(listOfLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 559, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(totalCandidateLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(timeLabel)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(yesRadioButton)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(noRadioButton)
                                        .addGap(229, 229, 229)
                                        .addComponent(defaulterCandidateCheckBox)))
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(dataProgressBar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(loadDataButton, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(generateListButton))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(seperateTestCheckBox)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel6)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(totalCandidateChoicesLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(buttonObjectionRemarks, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(buttonPrint, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(exportButton, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(backButton))))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel13, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(buttonStoreInDatabase, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(titlePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(listOfLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(disciplinesLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(totalCandidateLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(totalCandidateChoicesLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel6)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(timeLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(yesRadioButton)
                    .addComponent(noRadioButton)
                    .addComponent(seperateTestCheckBox)
                    .addComponent(defaulterCandidateCheckBox))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(generateListButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(loadDataButton, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(dataProgressBar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(candidateLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(filterProgressBar, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(buttonObjectionRemarks, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(buttonPrint, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(backButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(buttonStoreInDatabase, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(exportButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {generateListButton, loadDataButton});

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void generateListButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_generateListButtonActionPerformed
        if (JOptionPane.showConfirmDialog(this, "Do you want to start process to generate selection list?", "Question", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) != JOptionPane.YES_OPTION) {
            return;
        }

//        if (myThread == null || !myThread.isAlive()) {
//            myThread = new SelectionThread();
//            myThread.start();
        this.generateListButton.setEnabled(false);
//            this.pauseButton.setEnabled(true);
//            this.stopButton.setEnabled(true);
        this.backButton.setEnabled(true);
        new Thread() {
            @Override
            public void run() {
                try {
                    if(ac.getPt().getIsBachelor()){
                        ac.processForBSCommerce();
                    }
                    ac.process();
                    if (!admissionList.getCampus().getIsMain()) {
                        ac.processForRemainingSeats();
                    }
                    MessageBox.info(AdmissionListDetailsDialog2.this, MessageEnum.MSG_27);
                    exportButton.setEnabled(true);
                } catch (Exception ex) {
                    ex.printStackTrace(System.err);
                    int option = JOptionPane.showConfirmDialog(rootPane, "Error Occured in selection\nDo you want to export selected?", "", JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE);
                    if (option == JOptionPane.YES_OPTION) {
                        exportButton.setEnabled(true);
                    }
                }
            }
        }.start();
//        }
    }//GEN-LAST:event_generateListButtonActionPerformed

    private void backButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backButtonActionPerformed
        // TODO add your handling code here:
        setVisible(false);
    }//GEN-LAST:event_backButtonActionPerformed

    private void loadDataButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loadDataButtonActionPerformed
        // TODO add your handling code here:
        new Thread() {
            @Override
            public void run() {
                loadDataButton.setEnabled(false);
                generateListButton.setEnabled(false);
                
                Campus campus = admissionList.getCampus();
                AdmissionSession admissionSession = admissionList.getAdmissionSession();
                AdmissionYear ay = admissionSession.getAdmissionYear();
                ProgramType pt = admissionSession.getProgramType();
                List<Shift> shiftList = DatabaseManager.getData(Shift.class, "isMorning DESC");

                ac = new AdmissionController(ay, pt);

                String objectionCriteria = defaulterCandidateCheckBox.isSelected() ? "" : "AND C.IS_OBJECTION = false";

                List<Campus> campusList;//DatabaseManager.getData(Campus.class, "displayOrder");
//                if ((campus.getIsMain())) {
//                    campusList = DatabaseManager.getData(Campus.class, "isMain = true", "displayOrder");
//                } else {
                campusList = new ArrayList<>();
                campusList.add(campus);
//                }
                /**
                 * [campusId, [isMorning, List<DisciplineAlg>]]
                 */
                Map<Integer, Map<Boolean, Map<Integer, List<DisciplineAlg>>>> dMap = new LinkedHashMap<>();
                //<editor-fold defaultstate="collapsed" desc="Discipline">
                //Discipline
                {
                    try {
                        int countDiscipline = 0;
                        for (Campus c : campusList) {

                            Map<Boolean, Map<Integer, List<DisciplineAlg>>> sMap = new LinkedHashMap<>();
                            for (Shift shift : shiftList) {

                                List<DisciplineAlg> disciplineAlgList = JDBCDatabaseManagerAlg.getDisciplineAlgList(shift.getShiftId(), c.getCampusId(), pt.getProgramTypeId(), seperateTestCheckBox.isSelected());
                                countDiscipline += disciplineAlgList.size();
                                dataProgressBar.setMaximum(disciplineAlgList.size() - 1);
                                int i = 0;
                                for (DisciplineAlg d : disciplineAlgList) {
                                    List<Object[]> prerequisiteList = JDBCDatabaseManagerAlg.getProgramSubjectAlgList(d.getPosId());
                                    Map<CategoryLogicalCodeEnum, CategoryAlg> map = JDBCDatabaseManagerAlg.getDisciplineCategorySeatsAlgList(d.isQuotaOriented(), ay.getAdmissionYearId(), pt.getProgramTypeId(), d.getCposgId());

                                    if (d.isQuotaOriented()) {

                                        CategoryAlg cat1 = map.get(CategoryLogicalCodeEnum.GENERAL_MERIT_QUOTA);
                                        if (cat1 != null) {
                                            List<DistrictSeatDistribution> dsd = JDBCDatabaseManagerAlg.getDistrictSeatDistributionAlg(campus.getCampusId(), ay.getAdmissionYearId(), true);
                                            Map<Integer, int[]> toMap = toMap(dsd, cat1.getCampusCategoryId(), d.getCposgId());
                                            cat1.setJurisMap(toMap);
                                        }

                                        cat1 = map.get(CategoryLogicalCodeEnum.UPPER_SINDH_OUT_OF_JURISDICTION_QUOTA);
                                        if (cat1 != null) {
                                            List<DistrictSeatDistribution> dsd = JDBCDatabaseManagerAlg.getDistrictSeatDistributionAlg(campus.getCampusId(), ay.getAdmissionYearId(), false);
                                            Map<Integer, int[]> toMap = toMap(dsd, cat1.getCampusCategoryId(), d.getCposgId());
                                            cat1.setJurisMap(toMap);
                                        }
                                    }
                                    d.setPrerequisiteList(prerequisiteList);
                                    d.setCategoryMap(map);

                                    Map<Integer, List<DisciplineAlg>> cposMap = sMap.get(shift.getIsMorning());
                                    if (cposMap == null) {
                                        cposMap = new LinkedHashMap<>();
                                        sMap.put(shift.getIsMorning(), cposMap);
                                    }
                                    List<DisciplineAlg> list = cposMap.get(d.getCposId());
                                    if (list == null) {
                                        list = new ArrayList<>();
                                        cposMap.put(d.getCposId(), list);
                                    }
                                    list.add(d);
                                    dataProgressBar.setValue(++i);
                                }
                            }
                            dMap.put(c.getCampusId(), sMap);
                        }
                        ac.setdMap(dMap);
                        disciplinesLabel.setText(String.valueOf(countDiscipline));
                    } catch (SQLException ex) {
                        Logger.getLogger(AdmissionListDetailsDialog2.class.getName()).log(Level.SEVERE, null, ex);
                        MessageBox.error(AdmissionListDetailsDialog2.this, ex);
                        loadDataButton.setEnabled(true);
                        return;
                    }
                }
                //</editor-fold>

                //<editor-fold defaultstate="collapsed" desc="Pre-requisite">
                // Prequisite
                {
                    try {
                        Map<Integer, List<Object[]>> prerequisite = JDBCDatabaseManagerAlg.getPrerequisite(pt.getProgramTypeId());
                        ac.setPrerequisite(prerequisite);
                    } catch (SQLException ex) {
                        Logger.getLogger(AdmissionListDetailsDialog2.class.getName()).log(Level.SEVERE, null, ex);
                        MessageBox.error(AdmissionListDetailsDialog2.this, ex);
                        loadDataButton.setEnabled(true);
                        return;
                    }
                }
                //</editor-fold>

                try {
                    String errorFile = "Error Loading Data.txt";
                    PrintStream out = new PrintStream(errorFile);
                    //Candidate
                    {
                        try {

                            List<CandidateAlg> candidateAlgList = null;
                            candidateAlgList = JDBCDatabaseManagerAlg.getCandidateAlgList(ay.getAdmissionYearId(), pt.getProgramTypeId(), campus.getCampusId(), objectionCriteria);

                            dataProgressBar.setMaximum(candidateAlgList.size() - 1);
                            dataProgressBar.setValue(0);
                            int i = 0;
                            List<CandidateAlg> candidatesHasToBeRemoved = new LinkedList<>();
                            for (CandidateAlg cn : candidateAlgList) {
                                int seat = cn.getSeatNo();
                                
                                CANDIDATE:
                                {
                                    if (JDBCDatabaseManagerAlg.isRetainedPartRegistry(cn.getId())) {
                                        candidatesHasToBeRemoved.add(cn);
                                        break CANDIDATE;
                                    }
                                    Map<Integer, Map<Boolean, List<Integer[]>>> choices = JDBCDatabaseManagerAlg.getCandidateChoicesAlg(cn.getId(), campus.getCampusId(), seperateTestCheckBox.isSelected());
                                    cn.setChoices(choices);

                                Object[] selection = JDBCDatabaseManagerAlg.getCandidateActiveSelectionAlg(cn.getId(), campus.getCampusId());
                                cn.setSelection(selection);
                                if(selection != null)
                                {
                                    
                                    int activeLogicalCode = (Integer)selection[CandidateAlg.Selection.SELECTION_CAT_LOGICAL_CODE];
                                    boolean activeIsSelf = (activeLogicalCode == CategoryLogicalCodeEnum.OP_SELF_FINANCE_QUOTA.ordinal()) || (activeLogicalCode==CategoryLogicalCodeEnum.SELF_FINANCE_QUOTA.ordinal());
                                    boolean activeIsMorning = (boolean)selection[CandidateAlg.Selection.SELECTION_IS_MORNING];
                                    
                                    
                                    boolean previousIsMeritCategory = false;
                                    boolean previousIsSFM = false;
                                    boolean previousIsEvening = false;
                                    
                                        List<Object[]> selectionLogicalCodeShift = JDBCDatabaseManagerAlg.getDistinctSelectionLogicalCode(cn.getId(), campus.getCampusId());
                                        if(selectionLogicalCodeShift!=null && (!selectionLogicalCodeShift.isEmpty())){
                                            for (Object[] objects : selectionLogicalCodeShift) {
                                                 CategoryLogicalCodeEnum catLogical = (CategoryLogicalCodeEnum)objects[0];
                                                 boolean isMorning = (Boolean)objects[1];
                                                 int catLogicalOrdinal = catLogical.ordinal();
                                                 int OPSF = CategoryLogicalCodeEnum.OP_SELF_FINANCE_QUOTA.ordinal();
                                                 int SF = CategoryLogicalCodeEnum.SELF_FINANCE_QUOTA.ordinal();
                                                 boolean isSelf = (catLogicalOrdinal == OPSF) || ( catLogicalOrdinal == SF );
                                                 if( !isSelf){
                                                     previousIsMeritCategory = true;
                                                 }else if( isSelf && isMorning ){
                                                     previousIsSFM = true;
                                                 }else if(isSelf && (!isMorning)){
                                                     previousIsEvening = true;
                                                 }
                                            }
                                         /*active Merit*/   
                                         if(!activeIsSelf){
                                             if(previousIsSFM){
                                                 cn.getCategories().remove( CategoryEnum.SFM_QUOTA );
                                             }
                                             if(previousIsEvening){
                                                 cn.getCategories().remove(CategoryEnum.SFE_QUOTA);
                                             }
                                         }
                                         /*active SFM*/
                                         else if( activeIsSelf && activeIsMorning ){
                                             if(previousIsEvening){
                                                 cn.getCategories().remove(CategoryEnum.SFE_QUOTA);
                                             }  
                                         }
                                         /*active SFE*/
                                         else if( activeIsSelf && (!activeIsMorning) ){
                                             if(previousIsMeritCategory){
                                                 cn.getCategories().remove(CategoryEnum.GM_DUR_QUOTA);
                                             }
                                             if(previousIsSFM){
                                                 cn.getCategories().remove(CategoryEnum.SFM_QUOTA);
                                             }
                                         }
                                       }
                                        
                                        //<editor-fold defaultstate="collapsed" desc="commented Adding candidates into disciplines ">
//                                    int catLogicalCode = (Integer)selection[6];
//                                    boolean isMorning = (boolean)selection[1];
//                                    int cposId = (Integer)selection[7];
//                                    Map<Boolean, Map<Integer, List<DisciplineAlg>>> sMap = dMap.get(campus.getCampusId());
//                                    Map<Integer, List<DisciplineAlg>> map = sMap.get(isMorning);
//                                    List<DisciplineAlg> dList = map.get(cposId);
//                                    if(dList.size() == 1){
//                                        CategoryAlg cat = dList.get(0).getCategoryMap().get(CategoryLogicalCodeEnum.values()[catLogicalCode]);
//                                        cat.addCandidate(cn);
//                                    }else{
//                                        for (DisciplineAlg d : dList) {
//                                            if (d.getCposGroup().getTitle().toUpperCase().equals(cn.getProgramGrp().toUpperCase())) {
//                                                CategoryAlg cat = d.getCategoryMap().get(CategoryLogicalCodeEnum.values()[catLogicalCode]);
//                                                cat.addCandidate(cn);
//                                                break;
//                                            }
//                                        }
//                                    }
//
                                        //</editor-fold>
                                }

                                    try {
                                        // Last credential Percentage JK
                                        float perc = JDBCDatabaseManagerAlg.getLastCredentailPercentage(cn.getId());
                                        cn.setLastCredetialPercentage(perc);
                                    } catch (Exception ex) {
                                        out.println("seatNo: " + cn.getSeatNo() + " --> " + ex);
                                    }
                                }
                                dataProgressBar.setValue(++i);
                            }
                            //<editor-fold defaultstate="collapsed" desc="Removing candidates from list">
                            System.err.println("size before: "+candidateAlgList.size());
                            for (CandidateAlg candidateAlg : candidatesHasToBeRemoved) {
                                candidateAlgList.remove(candidateAlg);
                            }
                            candidateLabel.setText(String.valueOf(candidateAlgList.size()));
                            System.err.println("size after: "+candidateAlgList.size());
                            //</editor-fold>
                            ac.setcList(candidateAlgList);

//                            System.out.println("Candidates: " + candidateAlgList.size());
                        } catch (SQLException ex) {
                            Logger.getLogger(AdmissionListDetailsDialog2.class.getName()).log(Level.SEVERE, null, ex);
                            MessageBox.error(AdmissionListDetailsDialog2.this, ex);
                            loadDataButton.setEnabled(true);
                            return;
                        }finally{
                        
                        }
                    }
                    out.close();
                    if (new File(errorFile).length() > 0) {
                        Runtime.getRuntime().exec("notepad " + errorFile);
                    }
                } catch (FileNotFoundException fex) {
                    MessageBox.error(AdmissionListDetailsDialog2.this, fex);
                } catch (IOException fex) {
                    Logger.getLogger(AdmissionListDetailsDialog2.class.getName()).log(Level.SEVERE, null, fex);
                    MessageBox.error(AdmissionListDetailsDialog2.this, fex);
                }
                MessageBox.info(AdmissionListDetailsDialog2.this, MessageEnum.MSG_26);
                generateListButton.setEnabled(true);
            }
            
        }.start();
    }//GEN-LAST:event_loadDataButtonActionPerformed

    int candidateSNO = 0;
    private void exportButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exportButtonActionPerformed
        new Thread() {
            @Override
            public void run() {
                export();
            }
        }.start();


    }//GEN-LAST:event_exportButtonActionPerformed

    private void buttonPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonPrintActionPerformed
        try {
            Campus campus = admissionList.getCampus();
            List<CandidateAlg> cList = ac.getcList();
            AdmissionSession admissionSession = admissionList.getAdmissionSession();
            ProgramType pt = admissionSession.getProgramType();
            List<ProgramOfStudy> posList = JDBCDatabaseManagerAlg.getProgramOfStudy(campus.getCampusId(), pt.getProgramTypeId(), seperateTestCheckBox.isSelected());
            SelectionReport report1 = new SelectionReport();
            SelectionReport report2 = new SelectionReport();
            SelectionReport report3 = new SelectionReport();
            List<District> districts = DatabaseManager.getData(District.class, "name");

            for (ProgramOfStudy programOfStudy : posList) {

                //<editor-fold desc="Categories wise">
                        /*  <CategoryLogicalEnum , < CandidateAlg, ChoiceNo>  >  */
                Map<Integer, Map<CandidateAlg, Integer>> morningSelection = new LinkedHashMap<>();
                Map<Integer, Map<CandidateAlg, Integer>> eveningSelection = new LinkedHashMap<>();
                //<editor-fold defaultstate="collapsed" desc="fill morning and evening selection">
                for (CandidateAlg candidateAlg : cList) {
                    if (candidateAlg.getCurrentSelection().size() > 0) {

                        /* [[isMorning, [posId, cat, choiceNo,CategoryLogicalCodeEnum]]]*/
                        Map<Boolean, List<int[]>> currentSelection = candidateAlg.getCurrentSelection();
                        List<int[]> selProgMorning = currentSelection.get(true);
                        List<int[]> selProgEvening = currentSelection.get(false);

                        if (selProgMorning != null) {
                            for (int[] spm : selProgMorning) {
                                int posId = spm[0];
                                int catLogicalCode = spm[3];
                                if (posId == programOfStudy.getProgramOfStudyId().intValue()) {
                                    CategoryEnum en = CategoryEnum.values()[spm[1]];
                                    if (morningSelection.get(catLogicalCode) == null) {
                                        morningSelection.put(catLogicalCode, new LinkedHashMap<CandidateAlg, Integer>());
                                    }
                                    morningSelection.get(catLogicalCode).put(candidateAlg, spm[2]);
                                }
                            }
                        }
                        if (selProgEvening != null) {
                            for (int[] spe : selProgEvening) {
                                int posId = spe[0];
                                int catLogicalCode = spe[3];
                                if (posId == programOfStudy.getProgramOfStudyId().intValue()) {
                                    CategoryEnum en = CategoryEnum.values()[spe[1]];
                                    if (eveningSelection.get(catLogicalCode) == null) {
                                        eveningSelection.put(catLogicalCode, new LinkedHashMap< CandidateAlg, Integer>());
                                    }
                                    eveningSelection.get(catLogicalCode).put(candidateAlg, spe[2]);
                                }
                            }
                        }
                    }
                }
                //</editor-fold>

                //<editor-fold defaultstate="collapsed" desc="MORNING SELECTION INTO SELECTION BEAN">
                List<SelectionCategoryBean> selectionCatsMorning = new LinkedList<>();
                List<SelectionCategoryBean> selectionCatsEvening = new LinkedList<>();
                prepareSelectionMap(pt, morningSelection, programOfStudy, districts, selectionCatsMorning);
                prepareSelectionMap(pt, eveningSelection, programOfStudy, districts, selectionCatsEvening);

                if (!morningSelection.isEmpty()) {
                    report1.append(pt, programOfStudy, selectionCatsMorning, admissionList, "MORNING");
                    report2.append2(pt, programOfStudy, selectionCatsMorning, admissionList, "MORNING");
                    report3.append3(pt, programOfStudy, selectionCatsMorning, admissionList, "MORNING");
                }
                if (!eveningSelection.isEmpty()) {
                    report1.append(pt, programOfStudy, selectionCatsEvening, admissionList, "EVENING");
                    report2.append2(pt, programOfStudy, selectionCatsEvening, admissionList, "EVENING");
                    report3.append3(pt, programOfStudy, selectionCatsEvening, admissionList, "EVENING");
                    System.out.println("");
                }
                //</editor-fold>
            }
            report1.print(superparent);
            report2.print(superparent);
            report3.print(superparent);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error Ocurred Printing..!" + ex);
            ex.printStackTrace(System.err);
        }
    }//GEN-LAST:event_buttonPrintActionPerformed

    private void prepareSelectionMap(ProgramType pt, Map<Integer, Map<CandidateAlg, Integer>> selectionMap, ProgramOfStudy programOfStudy, List<District> districts, List<SelectionCategoryBean> selectionCatsshift) throws Exception {
        List<Integer> catLogicalsMorning = new LinkedList<>(selectionMap.keySet());
        Collections.sort(catLogicalsMorning);
        for (Integer catLogicalCode : catLogicalsMorning) {
            SelectionCategoryBean selCat = new SelectionCategoryBean();
            selCat.setCategory(CategoryLogicalCodeEnum.values()[catLogicalCode].getTitle());
            Map<CandidateAlg, Integer> candidatesMap = selectionMap.get(catLogicalCode);
            Set<CandidateAlg> keySet = candidatesMap.keySet();
            List<SelectionCandidateBean> candis = new LinkedList<>();
            if (programOfStudy.getIsQuotaOriented()) {
                //    DistrictId, < Area, List<Candidates> >
                Map<Integer, Map<Integer, List<CandidateAlg>>> districtwiseCandidatesMap = new LinkedHashMap<>();

                //desc="traversing all districts"
                for (District dis : districts) {
                    districtwiseCandidatesMap.put(dis.getDistrictId(), new LinkedHashMap<Integer, List<CandidateAlg>>());
                }

                // sorting candidate district wise
                for (CandidateAlg candidateAlg : keySet) {
                    Map<Integer, List<CandidateAlg>> districtURMap = districtwiseCandidatesMap.get(candidateAlg.getDistrictId());
                    if (districtURMap.get(candidateAlg.getArea()) == null) {
                        districtURMap.put(candidateAlg.getArea(), new LinkedList<CandidateAlg>());
                    }
                    districtURMap.get(candidateAlg.getArea()).add(candidateAlg);
                }
                Set<Integer> districtIdSet = districtwiseCandidatesMap.keySet();
                for (Integer districtId : districtIdSet) {
                    Map<Integer, List<CandidateAlg>> urCandis = districtwiseCandidatesMap.get(districtId);
                    List<Integer> urIdList = new LinkedList(urCandis.keySet());
                    Collections.sort(urIdList);
                    for (Integer urId : urIdList) {
                        List<CandidateAlg> candidatesList = urCandis.get(urId);
                        for (CandidateAlg candidateAlg : candidatesList) {
                            SelectionCandidateBean bean = prepareCandidateSelection(candidateAlg, pt, candidatesMap.get(candidateAlg));
                            candis.add(bean);
                        }
                    }
                }
            } else {
                for (CandidateAlg candidateAlg : keySet) {
                    SelectionCandidateBean bean = prepareCandidateSelection(candidateAlg, pt, candidatesMap.get(candidateAlg));
                    candis.add(bean);
                }
            }

            selCat.setCandidates(candis);
            selectionCatsshift.add(selCat);
        }
        //</editor-fold>
    }

    private void buttonStoreInDatabaseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonStoreInDatabaseActionPerformed
        new Thread() {
            @Override
            public void run() {
                buttonStoreInDatabase.setEnabled(false);
                Campus campus = admissionList.getCampus();
                List<CandidateAlg> cList = ac.getcList();

                int progress = 0;

                filterProgressBar.setMaximum(cList.size() - 1);
                int noOfCandidate = 0;
                // Traversing all candidate and appending in ouputstream of excel files
                for (CandidateAlg candidateAlg : cList) {
                    Map<Boolean, List<int[]>> currentSelection = candidateAlg.getCurrentSelection(); // [[isMorning, [posId, cat, choiceNo]]]

                    if (!currentSelection.isEmpty()) {
                        int size = addInDatabase(currentSelection, true, candidateAlg, campus.getCampusId());
                        noOfCandidate += size;
                        size = addInDatabase(currentSelection, false, candidateAlg, campus.getCampusId());
                        noOfCandidate += size;
                    }
                    filterProgressBar.setValue(++progress);
                }
                //</editor-fold>
                JOptionPane.showMessageDialog(AdmissionListDetailsDialog2.this, noOfCandidate + " candidates Stored in database..!");

            }
        }.start();

    }//GEN-LAST:event_buttonStoreInDatabaseActionPerformed

    private void buttonObjectionRemarksActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonObjectionRemarksActionPerformed
        try {
            Campus campus = admissionList.getCampus();
            List<CandidateAlg> cList = ac.getcList();
            AdmissionSession admissionSession = admissionList.getAdmissionSession();
            ProgramType pt = admissionSession.getProgramType();
            List<ProgramOfStudy> posList = JDBCDatabaseManagerAlg.getProgramOfStudy(campus.getCampusId(), pt.getProgramTypeId(), seperateTestCheckBox.isSelected());
            SelectionReport reportobjection = new SelectionReport();
            List<District> districts = DatabaseManager.getData(District.class, "name");

            for (ProgramOfStudy programOfStudy : posList) {

                //<editor-fold desc="Categories wise">
                        /*  <CategoryLogicalEnum , < CandidateAlg, ChoiceNo>  >  */
                Map<Integer, Map<CandidateAlg, Integer>> morningSelection = new LinkedHashMap<>();
                Map<Integer, Map<CandidateAlg, Integer>> eveningSelection = new LinkedHashMap<>();
                //<editor-fold defaultstate="collapsed" desc="fill morning and evening selection">
                for (CandidateAlg candidateAlg : cList) {
                    if (candidateAlg.getCurrentSelection().size() > 0) {

                        /* [[isMorning, [posId, cat, choiceNo,CategoryLogicalCodeEnum]]]*/
                        Map<Boolean, List<int[]>> currentSelection = candidateAlg.getCurrentSelection();
                        List<int[]> selProgMorning = currentSelection.get(true);
                        List<int[]> selProgEvening = currentSelection.get(false);

                        if (selProgMorning != null) {
                            for (int[] spm : selProgMorning) {
                                int posId = spm[0];
                                int catLogicalCode = spm[3];
                                if (posId == programOfStudy.getProgramOfStudyId().intValue()) {
                                    CategoryEnum en = CategoryEnum.values()[spm[1]];
                                    if (morningSelection.get(catLogicalCode) == null) {
                                        morningSelection.put(catLogicalCode, new LinkedHashMap<CandidateAlg, Integer>());
                                    }
                                    morningSelection.get(catLogicalCode).put(candidateAlg, spm[2]);
                                }
                            }
                        }
                        if (selProgEvening != null) {
                            for (int[] spe : selProgEvening) {
                                int posId = spe[0];
                                int catLogicalCode = spe[3];
                                if (posId == programOfStudy.getProgramOfStudyId().intValue()) {
                                    CategoryEnum en = CategoryEnum.values()[spe[1]];
                                    if (eveningSelection.get(catLogicalCode) == null) {
                                        eveningSelection.put(catLogicalCode, new LinkedHashMap< CandidateAlg, Integer>());
                                    }
                                    eveningSelection.get(catLogicalCode).put(candidateAlg, spe[2]);
                                }
                            }
                        }
                    }
                }
                //</editor-fold>

                //<editor-fold defaultstate="collapsed" desc="MORNING SELECTION INTO SELECTION BEAN">
                List<SelectionCategoryBean> selectionCatsMorning = new LinkedList<>();
                List<SelectionCategoryBean> selectionCatsEvening = new LinkedList<>();
                prepareSelectionMap(pt, morningSelection, programOfStudy, districts, selectionCatsMorning);
                prepareSelectionMap(pt, eveningSelection, programOfStudy, districts, selectionCatsEvening);

                if (!morningSelection.isEmpty()) {
                    reportobjection.appendObjection(pt, programOfStudy, selectionCatsMorning, admissionList, "MORNING");
                }
                if (!eveningSelection.isEmpty()) {
                    reportobjection.appendObjection(pt, programOfStudy, selectionCatsEvening, admissionList, "EVENING");
                }
                //</editor-fold>
            }
            reportobjection.print(superparent);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error Ocurred Printing..!" + ex);
            ex.printStackTrace(System.err);
        }
    }//GEN-LAST:event_buttonObjectionRemarksActionPerformed

    private void export() {
        //If admission controll is not null
        if (ac != null) {
            try {
                //<editor-fold defaultstate="collapsed" desc="File Chooser Dialog">
//                fileChooser.setFileFilter(excelFilter);
                fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                int i = fileChooser.showSaveDialog(this);
                if (!(i < 1)) {
                    return;
                }
                File f = fileChooser.getSelectedFile();
                if (f == null) {
                    return;
                }
                //</editor-fold>

                Campus campus = admissionList.getCampus();
                ProgramType programType = admissionList.getAdmissionSession().getProgramType();

                //<editor-fold defaultstate="collapsed" desc="Declaring Files Path">          
                String filePathWebCandidate = "";
                String filePathWebCandidateCategories = "";
                String filePathWebCandidateChoices = "";
                String filePath = f.getAbsolutePath();
                String filePathNoteSelected = "";
                String filePathDisciplineList = "";
                String fileLastPerc = "";
                String fileMaleFemale = "";
                String fileConsumedSeats = "";
                //</editor-fold>

                List<District> districtData = DatabaseManager.getData(District.class, "name");
                Map<Integer, District> districtMap = new LinkedHashMap<>();
                for (District district : districtData) {
                    districtMap.put(district.getDistrictId(), district);
                }

                //<editor-fold defaultstate="collapsed" desc="Handling File Extension">
                filePathWebCandidate = (filePath + File.separator + programType.getName() + "_" + campus.getLocation() + "_CANDIDATE_RESULT" + ".sql");
                filePathWebCandidateCategories = (filePath + File.separator + programType.getName() + "_" + campus.getLocation() + "_CANDIDATE_CATEGORY" + ".sql");
                filePathWebCandidateChoices = (filePath + File.separator + programType.getName() + "_" + campus.getLocation() + "_CANDIDATE_CHOICES" + ".sql");
                PrintStream outCandidateResult = new PrintStream(new File(filePathWebCandidate));
                PrintStream outCandidatChoices = new PrintStream(new File(filePathWebCandidateChoices));
                PrintStream outCandidatCategories = new PrintStream(new File(filePathWebCandidateCategories));

                filePathNoteSelected = (filePath + File.separator + programType.getName() + "_" + campus.getLocation() + "_NOT_SELECTED" + ".xls");
                filePathDisciplineList = (filePath + File.separator + programType.getName() + "_" + campus.getLocation() + "_DISCIPLINE_ORDER" + ".xls");
                fileLastPerc = (filePath + File.separator + programType.getName() + "_" + campus.getLocation() + "_LAST_PERC" + ".xls");
                fileMaleFemale = (filePath + File.separator + programType.getName() + "_" + campus.getLocation() + "_MF" + ".xls");
                fileConsumedSeats = (filePath + File.separator + programType.getName() + "_" + campus.getLocation() + "_CONSUMED" + ".xls");
                filePath = filePath + File.separator + programType.getName() + "_" + campus.getLocation() + ".xls";
                //</editor-fold>

                //<editor-fold defaultstate="collapsed" desc="Creating ExcelHandler for SELECTED, NON-SELECTED, DISCIPLINE-WISE-SELECTED (Excel files)">
                ExcelHandler ehNotSelected = new ExcelHandler(filePathNoteSelected);
                ExcelHandler eh = new ExcelHandler(filePath);
                ehNotSelected.createSheet();
                ExcelHandler ehLastPerc = new ExcelHandler(fileLastPerc);
                ExcelHandler ehMF = new ExcelHandler(fileMaleFemale);
                ExcelHandler ehConsumed = new ExcelHandler(fileConsumedSeats);
                ExcelHandler ehDiscipline = new ExcelHandler(filePathDisciplineList);
                eh.createSheet();
                ehLastPerc.createSheet();
                ehMF.createSheet();
                ehConsumed.createSheet();
                ehDiscipline.createSheet();
                //</editor-fold> 

                //<editor-fold defaultstate="collapsed" desc="Printing Columns in excel files">
                if (admissionList.getAdmissionSession().getProgramType().getIsBachelor()) {
                    ehNotSelected.appendRow(ExcelHandler.BACHALOR_NOT_SELECTED_COLUMNS);
                    eh.appendRow(ExcelHandler.BACHALOR_SELECTED_COLUMNS);
                } else {
                    ehNotSelected.appendRow(ExcelHandler.MASTER_NOT_SELECTED_COLUMNS);
                    eh.appendRow(ExcelHandler.MASTER_SELECTED_COLUMNS);
                }
                //</editor-fold>

                List<CandidateAlg> cList = ac.getcList();
//                Map<Integer, Map<Boolean, Map<Integer, List<DisciplineAlg>>>> dMap = ac.getdMap();
                candidateSNO = 1;
                int progress = 0;

                filterProgressBar.setMaximum(cList.size() - 1);

                int maleSelected = 0;
                int femaleSelected = 0;
                int maleApplying = 0;
                int femaleApplying = 0;

                //<editor-fold defaultstate="collapsed" desc="Preparing data for two files, selected and not selected"> 
                // Traversing all candidate and appending in ouputstream of excel files
                for (CandidateAlg candidateAlg : cList) {
                    Map<Boolean, List<int[]>> currentSelection = candidateAlg.getCurrentSelection(); // [[isMorning, [posId, cat, choiceNo]]]

                    //<editor-fold defaultstate="collapsed" desc="Total Male Female Appliing">
                    if (candidateAlg.getGender() == GenderEnum.MALE.ordinal()) {
                        maleApplying++;
                        if (!currentSelection.isEmpty()) {
                            maleSelected++;
                        }
                    } else {
                        femaleApplying++;
                        if (!currentSelection.isEmpty()) {
                            femaleSelected++;
                        }
                    }
                    //</editor-fold>

                    boolean morning = true;
                    if (!currentSelection.isEmpty()) {
                        appendSelectedExcel(candidateSNO, currentSelection, morning, candidateAlg, campus, programType, eh, outCandidateResult);
                        appendSelectedExcel(candidateSNO, currentSelection, !morning, candidateAlg, campus, programType, eh, outCandidateResult);
                        candidateSNO++;
                    } else if (candidateAlg.getSelection() != null) {
                        // candidate no current selection and already selected in previous list
                    } else {
                        appendExcelNotSelected(candidateSNO, candidateAlg, campus.getCampusId(), ehNotSelected);
//                        appendNotSelectedResult(outCandidateResult, candidateAlg, programType);
                    }

                    printAppliedCategories(candidateAlg, outCandidatCategories);

                    filterProgressBar.setValue(++progress);
                }
                //</editor-fold>

                ehMF.appendRow(new Object[]{"Applying", "Male", maleApplying, "Female", femaleApplying});
                ehMF.appendRow(new Object[]{"Selected", "Male", maleSelected, "Female", femaleSelected});

                //<editor-fold defaultstate="collapsed" desc="Preparing data for Program Of Study Wise Selected Students">
                AdmissionSession admissionSession = admissionList.getAdmissionSession();
                ProgramType pt = admissionSession.getProgramType();

                ehDiscipline.appendRow(new Object[]{"LIST OF CANDIDATES PROVISIONALLY SELECTED FOR ADMISSION DURING THE ACADEMIC YEAR " + admissionSession.getAdmissionYear().getYear() + "  " + admissionList.getCampus().getName() + " " + admissionList.getCampus().getLocation() + "    List-" + admissionList.getListNo()});
                ehDiscipline.appendRow(new Object[]{""});
                ehDiscipline.appendRow(new Object[]{""});

                List<ProgramOfStudy> posList = JDBCDatabaseManagerAlg.getProgramOfStudy(campus.getCampusId(), pt.getProgramTypeId(), seperateTestCheckBox.isSelected());

                fillChoiceFile(cList, outCandidatChoices);

                for (ProgramOfStudy programOfStudy : posList) {

                    //Either Program should check by district
//                    if (programOfStudy.getIsQuotaOriented()) {
                    //<editor-fold defaultstate="collapsed" desc="Commented District wise">
//                        /*  < District, <CandidateAlg, choiceNo> > */
//                        Map<Integer, Map<CandidateAlg, Integer>> morningSelection = new LinkedHashMap<>();
//                        Map<Integer, Map<CandidateAlg, Integer>> eveningSelection = new LinkedHashMap<>();
//                        for (CandidateAlg candidateAlg : cList) {
//                            if (candidateAlg.getCurrentSelection().size() > 0) {
//                                /* [[isMorning, [posId, cat, choiceNo]]]*/
//                                Map<Boolean, List<int[]>> currentSelection = candidateAlg.getCurrentSelection();
//                                List<int[]> selProgMorning = currentSelection.get(true);
//                                List<int[]> selProgEvening = currentSelection.get(false);
//
//                                if (selProgMorning != null) {
//                                    for (int[] spm : selProgMorning) {
//                                        int posId = spm[0];
//                                        if (posId == programOfStudy.getProgramOfStudyId().intValue()) {
//                                            if (morningSelection.get(candidateAlg.getDistrictId()) == null) {
//                                                morningSelection.put(candidateAlg.getDistrictId(), new LinkedHashMap<CandidateAlg, Integer>());
//                                            }
//                                            Map<CandidateAlg, Integer> disCandis = morningSelection.get(candidateAlg.getDistrictId());
//                                            disCandis.put(candidateAlg, spm[2]);
//                                        }
//                                    }
//                                }
//                                if (selProgEvening != null) {
//                                    for (int[] spe : selProgEvening) {
//                                        int posId = spe[0];
//                                        if (posId == programOfStudy.getProgramOfStudyId().intValue()) {
//                                            if (eveningSelection.get(candidateAlg.getDistrictId()) == null) {
//                                                eveningSelection.put(candidateAlg.getDistrictId(), new LinkedHashMap<CandidateAlg, Integer>());
//                                            }
//                                            Map<CandidateAlg, Integer> disCandis = eveningSelection.get(candidateAlg.getDistrictId());
//                                            disCandis.put(candidateAlg, spe[2]);
//                                        }
//                                    }
//                                }
//                            }
//                        }
//                        if ((morningSelection.isEmpty()) && (eveningSelection.isEmpty())) {
//                        } else {
//                            ehDiscipline.appendRow(new Object[]{});
//                            ehDiscipline.appendRow(new Object[]{});
//                            ehDiscipline.appendRow(new Object[]{programOfStudy.getName(),});
//                            ehDiscipline.appendRow(new Object[]{"QUOTA ORIENTED", programOfStudy.getIsQuotaOriented() ? "YES" : "NO",});
//                            ehDiscipline.appendRow(new Object[]{});
//                            ehDiscipline.appendRow(new Object[]{});
//
//                            appendExcel(ehDiscipline, morningSelection, "Morning", campus.getCampusId(), programOfStudy.getProgramOfStudyId());
//                            appendExcel(ehDiscipline, eveningSelection, "Evening", campus.getCampusId(), programOfStudy.getProgramOfStudyId());
//
//                        }
//                        //</editor-fold>
//                    } else {
                    //<editor-fold desc="Categories wise">
                        /*  <CategoryLogicalEnum , < CandidateAlg, ChoiceNo>  >  */
                    Map<Integer, Map<CandidateAlg, Integer>> morningSelection = new LinkedHashMap<>();
                    Map<Integer, Map<CandidateAlg, Integer>> eveningSelection = new LinkedHashMap<>();
                    for (CandidateAlg candidateAlg : cList) {
                        if (candidateAlg.getCurrentSelection().size() > 0) {

                            /* [[isMorning, [posId, cat, choiceNo,CategoryLogicalCodeEnum]]]*/
                            Map<Boolean, List<int[]>> currentSelection = candidateAlg.getCurrentSelection();
                            List<int[]> selProgMorning = currentSelection.get(true);
                            List<int[]> selProgEvening = currentSelection.get(false);

                            if (selProgMorning != null) {
                                for (int[] spm : selProgMorning) {
                                    int posId = spm[0];
                                    int catLogicalCode = spm[3];
                                    if (posId == programOfStudy.getProgramOfStudyId().intValue()) {
                                        CategoryEnum en = CategoryEnum.values()[spm[1]];
                                        if (morningSelection.get(catLogicalCode) == null) {
                                            morningSelection.put(catLogicalCode, new LinkedHashMap<CandidateAlg, Integer>());
                                        }
                                        morningSelection.get(catLogicalCode).put(candidateAlg, spm[2]);
                                    }
                                }
                            }
                            if (selProgEvening != null) {
                                for (int[] spe : selProgEvening) {
                                    int posId = spe[0];
                                    int catLogicalCode = spe[3];
                                    if (posId == programOfStudy.getProgramOfStudyId().intValue()) {

                                        if (eveningSelection.get(catLogicalCode) == null) {
                                            eveningSelection.put(catLogicalCode, new LinkedHashMap< CandidateAlg, Integer>());
                                        }
                                        eveningSelection.get(catLogicalCode).put(candidateAlg, spe[2]);
                                    }
                                }
                            }
                        }
                    }

                    if ((morningSelection.isEmpty()) && (eveningSelection.isEmpty())) {
                        // DO NOTHING
                    } else {
                        appendInExcel(ehDiscipline, morningSelection, true, campus.getCampusId(), programOfStudy, ehLastPerc, districtData, districtMap, ehConsumed);
                        appendInExcel(ehDiscipline, eveningSelection, false, campus.getCampusId(), programOfStudy, ehLastPerc, districtData, districtMap, ehConsumed);

                    }
//                        </editor-fold>
//                    }

                }// end if program of study
                //</editor-fold>

                //<editor-fold defaultstate="collapsed" desc="Exporting data to file"> 
                eh.readyAndClose();
                ehNotSelected.readyAndClose();
                ehDiscipline.readyAndClose();
                ehLastPerc.readyAndClose();
                ehMF.readyAndClose();
                ehConsumed.readyAndClose();
                outCandidateResult.close();
                outCandidatChoices.close();
                outCandidatCategories.close();
                //</editor-fold>
                MessageBox.info(this, "Successfully exported");
            } catch (Exception ex) {
                MessageBox.error(this, "Error Occured creating xls file");
                ex.printStackTrace(System.err);
            }
        }
    }

    private static Map<Integer, int[]> toMap(List<DistrictSeatDistribution> dsdList, int campusCategoryId, int cposgId) throws SQLException {
        Map<Integer, int[]> map = new LinkedHashMap<>(dsdList.size());
        /**
         * This hard for some program seats are double or tipple, it will
         * changed in dynamic functions
         */
        int multiply = cposgId == 14 ? 2 /*B.B.A*/
                //                : cposgId == 32 ? 3
                : cposgId == 113 ? 2 /*M.B.A*/
                : cposgId == 130 ? 2 /*M.P.A*/
                : cposgId == 55 ? 2 /*L.L.B HYD*/
                :cposgId == 194 ? 2 /*L.L.B JAMSHORO */
                : 1;

        for (DistrictSeatDistribution dsd : dsdList) {
            int urban = JDBCDatabaseManager.getFreeCandidatesCount(dsd.getAdmissionYearId(), campusCategoryId, cposgId, /*dsd.getShiftId(),*/ dsd.getDistrictId(), AreaEnum.URBAN.ordinal());
            int rural = JDBCDatabaseManager.getFreeCandidatesCount(dsd.getAdmissionYearId(), campusCategoryId, cposgId, /*dsd.getShiftId(),*/ dsd.getDistrictId(), AreaEnum.RURAL.ordinal());

            int arr[] = new int[]{dsd.getUrban() * multiply - urban, dsd.getRural() * multiply - rural, multiply * dsd.getOther() - urban - rural};
            map.put(dsd.getDistrictId(), arr);
        }
        return map;
    }

    private void setTitles() {
        Campus campus = admissionList.getCampus();
        AdmissionSession admissionSession = admissionList.getAdmissionSession();
        AdmissionYear admissionYear = admissionSession.getAdmissionYear();
        ProgramType programType = admissionSession.getProgramType();

        try {
            int total;
//            if ((campus.getIsMain())) {
//                total = JDBCDatabaseManager.getMainCposGroup(true, programType.getProgramTypeId());
//            } else {
            total = JDBCDatabaseManager.getCampusCposGroup(campus.getCampusId(), programType.getProgramTypeId());
//            }

            this.disciplinesLabel.setText(String.valueOf(total));

            total = JDBCDatabaseManager.getCandidatesCount(admissionYear.getAdmissionYearId(), campus.getCampusId(), programType.getProgramTypeId());
            this.totalCandidateLabel.setText(String.valueOf(total));

            total = JDBCDatabaseManager.getCandidatesChoices(admissionYear.getAdmissionYearId(), campus.getCampusId(), programType.getProgramTypeId());
            this.totalCandidateChoicesLabel.setText(String.valueOf(total));

            String title = campus.getLocation() + " (" + programType.toString() + ") " + admissionSession.getAdmissionYear().toString();
            this.listOfLabel.setText(title);

        } catch (SQLException ex) {
            Logger.getLogger(AdmissionListDetailsDialog2.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void clear() {
        ac = null;
        loadDataButton.setEnabled(true);
        disciplinesLabel.setText("0");
        totalCandidateChoicesLabel.setText("0");
        totalCandidateLabel.setText("0");
        timeLabel.setText("hh:mm:ss");
        exportButton.setEnabled(false);
    }

    //<editor-fold defaultstate="collapsed" desc="Yougesh Commented Coding">
//    private class SelectionThread extends Thread{
//        @Override public void run() {
//            //Session
//            long start = System.currentTimeMillis();
//            Campus campus = admissionList.getCampus();
//            AdmissionSession admissionSession = admissionList.getAdmissionSession();
//            AdmissionYear admissionYear = admissionSession.getAdmissionYear();
////            Shift shift = admissionSession.getShift();
//            ProgramType programType = admissionSession.getProgramType();
////            DecimalFormat df = new DecimalFormat("###.##");
//            
//            timer = new Timer(1000, new StopWatch(timeLabel));
//            timer.start();
//            /*
//             * Filter candidate in all pos and all category
//             */
////            int count = 0;
////            try {
////                List<CposGroup> cposgList;
////                if ((campus.getIsMain())) {
////                    cposgList = JDBCDatabaseManager.getMainCposGroup(true, programType.getProgramTypeId(), seperateTestCheckBox.isSelected());
////                } else 
////                    cposgList = JDBCDatabaseManager.getCampusCposGroup(campus.getCampusId(), programType.getProgramTypeId(), seperateTestCheckBox.isSelected());
////                
////                List<ProgramOfStudy> filteredCandidates = AdmissionController.filterCandidates(cposgList, campus, admissionYear, filterProgressBar, candidateLabel);
////                candidateLabel.setText("Process completed");
////                
////                count += this.nextStep(filteredCandidates, admissionYear);
////                
//////                filteredCandidates = AdmissionController.filterCandidates(campus, programType, admissionYear, shift, true, filterProgressBar, candidateLabel);
//////                candidateLabel.setText("Process completed");
//////                
//////                count += this.nextStep(filteredCandidates, admissionSession, admissionYear, shift);
////            } catch (SQLException ex) {
////                utilities.MessageBox.info(AdmissionListDetailsDialog2.this, ex);
////                Logger.getLogger(AdmissionListDetailsDialog2.class.getName()).log(Level.SEVERE, null, ex);
////            }
//
//            long end = System.currentTimeMillis();
//            NumberFormat nf = new DecimalFormat("#0.00000");
//            System.out.println(nf.format((end - start) / 1000d) + " s");
//            timer.stop();
//            
////            nameLabel.setText("Process completed");
////            filterProgressBar.setMaximum(50);
////            distributionProgressBar.setMaximum(50);
////            filterProgressBar.setValue(0);
////            distributionProgressBar.setValue(0);
////            utilities.MessageBox.info(AdmissionListDetailsDialog2.this, count + " record added successfully");
////            generateListButton.setEnabled(true);
////            backButton.setEnabled(true);
////            pauseButton.setEnabled(false);
////            stopButton.setEnabled(false);
//        }
//        private int nextStep(List<ProgramOfStudy> filteredCandidates, AdmissionYear ay) {
//            /**
//             * Get list seat distribution of pos\"s
//             */
//            NumberFormat nf = new DecimalFormat("#0.00");
////            List<ProgramOfStudy> generateAdmissionList = null;
////            try {
////                DistributeSeatInCandidates.distribute(filteredCandidates, ay, distributionProgressBar, nameLabel);
////            } catch (SQLException ex) {
////                utilities.MessageBox.info(AdmissionListDetailsDialog2.this, ex);
////                Logger.getLogger(AdmissionListDetailsDialog2.class.getName()).log(Level.SEVERE, null, ex);
////            }
////            distributionProgressBar.setValue(0);
////            distributionProgressBar.setMaximum(filteredCandidates.size() - 1);
//            
//            int count = 0;
//            int rows = 0;
//            HSSFWorkbook workbook = new HSSFWorkbook();
//            HSSFSheet sheet = workbook.createSheet("Selection List");
//            
//            int rows1 = 0;
//            HSSFWorkbook workbook1 = new HSSFWorkbook();
//            HSSFSheet sheet1 = workbook1.createSheet("SUMMERY");
//            
//            int rows2 = 0;
//            HSSFWorkbook workbook2 = new HSSFWorkbook();
//            HSSFSheet sheet2 = workbook2.createSheet("PERCENTAGE SUMMERY");
//            
//            int rows3 = 0;
//            HSSFWorkbook workbook3 = new HSSFWorkbook();
//            HSSFSheet sheet3 = workbook3.createSheet("UPLOAD");
//            
//            String title = "LIST OF CANDIDATES PROVISIONALLY SELECTED FOR ADMISSION DURING THE ACADEMIC YEAR " + ay.getYear() + " - " + admissionList.toString();
//            sheet1.createRow(rows1++).createCell(0).setCellValue(title);
//            sheet2.createRow(rows2++).createCell(0).setCellValue(title);
//            
//            for (int i = 0; i < filteredCandidates.size(); i++) {
//                ProgramOfStudy pos = filteredCandidates.get(i);
//                CposGroup cposg = pos.getCposg();
//                
//                title = "LIST OF CANDIDATES PROVISIONALLY SELECTED FOR ADMISSION DURING THE ACADEMIC YEAR " + ay.getYear() + " " + cposg.getShiftName() + " - " + admissionList.toString();
//            
//                sheet.createRow(rows++).createCell(0).setCellValue(title);
//                /**
//                 * Get all categories of perticular
//                 */
//                List<Category> list = pos.getCategories();
//                    
//                sheet1.createRow(rows1++);
//                sheet1.createRow(rows1++).createCell(0).setCellValue("SHIFT & CAMPUS: " + cposg.getShiftName() + " - " + cposg.getCampusName());
//                sheet1.createRow(rows1++).createCell(0).setCellValue("DISCIPLINE: " + cposg.toString() + " (" + cposg.getPosPCode() + ")");
//                sheet1.createRow(rows1++);
//                sheet1.createRow(rows1).createCell(0).setCellValue("CATEGORY");
//                sheet1.getRow(rows1).createCell(1).setCellValue("TOTAL SEATS");
//                sheet1.getRow(rows1).createCell(2).setCellValue("CONSUMED");
//                sheet1.getRow(rows1++).createCell(3).setCellValue("REMAINING");
//                    
//                sheet2.createRow(rows2++);
//                sheet2.createRow(rows2++).createCell(0).setCellValue("SHIFT & CAMPUS: " + cposg.getShiftName() + " - " + cposg.getCampusName());
//                sheet2.createRow(rows2++).createCell(0).setCellValue("DISCIPLINE: " + cposg.toString() + " (" + cposg.getPosPCode() + ")");
//                sheet2.createRow(rows2++).createCell(0).setCellValue("IS QUOTA ORIENTED: " + (cposg.isPosIsQuotaOriented() ? "YES" : "NO"));
//                sheet2.createRow(rows2++);
//                
//                if (!cposg.isPosIsQuotaOriented()) {
//                    sheet2.createRow(rows2).createCell(0).setCellValue("CATEGORY");
//                    sheet2.getRow(rows2++).createCell(1).setCellValue("LAST PERCENAGE");
//                }
//                    
//                int total = 0;
//                for (int k = 0; k < list.size(); k++) {
//                    Category category = list.get(k);
//                    if(category.getCandidates().isEmpty()) continue;
//                    
//                    CampusCategory cc = category.getCampusCategory();
//                    List<admission.controller.beans.Candidate> candidates = category.getCandidates();
//                    if(cposg.isPosIsQuotaOriented() && (cc.getCategoryCode() == Constant.CategoryCodes.GENERAL_MERIT_QUOTA || 
//                            cc.getCategoryCode() == Constant.CategoryCodes.UPPER_SINDH_OUT_OF_JURISDICTION_QUOTA)) {
//                        Sorter.sortByThree(candidates);
//                    }
//                    if (!cposg.isPosIsQuotaOriented()) {
//                        sheet2.createRow(rows2).createCell(0).setCellValue(cc.toString());
//                        Float per = candidates.isEmpty() ? 0.0F : candidates.get(candidates.size() - 1).getPercentage();
//                        sheet2.getRow(rows2++).createCell(1).setCellValue(per + "");
//                    } else {
//                        if(cc.getCategoryCode() == Constant.CategoryCodes.GENERAL_MERIT_QUOTA || 
//                            cc.getCategoryCode() == Constant.CategoryCodes.UPPER_SINDH_OUT_OF_JURISDICTION_QUOTA) {
//                            sheet2.createRow(++rows2).createCell(0).setCellValue("CATEGORY: " + cc.toString());
//                            rows2++;
//                        } else {
//                            sheet2.createRow(rows2).createCell(0).setCellValue(cc.toString());
//                            Float per = candidates.isEmpty() ? 0.0F : candidates.get(candidates.size() - 1).getPercentage();
//                            sheet2.getRow(rows2++).createCell(1).setCellValue(per + "");
//                        }
//                    }
//                    
//                    total += category.getCandidates().size();
//                    sheet1.createRow(rows1).createCell(0).setCellValue(cc.toString());
//                    sheet1.getRow(rows1).createCell(1).setCellValue(category.getTotalSeats());
//                    sheet1.getRow(rows1).createCell(2).setCellValue(category.getCandidates().size());
//                    sheet1.getRow(rows1++).createCell(3).setCellValue(category.getTotalSeats() - category.getCandidates().size());
//
//                    sheet.createRow(rows++);
//                    sheet.createRow(rows++).createCell(0).setCellValue("SHIFT & CAMPUS: " + cposg.getShiftName() + " - " + cposg.getCampusName());
//                    sheet.createRow(rows++).createCell(0).setCellValue("DISCIPLINE: " + cposg.toString() + " (" + cposg.getPosPCode() + ")");
//                    sheet.createRow(rows++).createCell(0).setCellValue("CATEGORY: " + cc.toString());
//                    sheet.createRow(rows++);
//                    sheet.createRow(rows).createCell(0).setCellValue("SNO.");
//                    sheet.getRow(rows).createCell(1).setCellValue("SEAT NO.");
//                    sheet.getRow(rows).createCell(2).setCellValue("NAME");
//                    sheet.getRow(rows).createCell(3).setCellValue("FATHER\"S NAME");
//                    sheet.getRow(rows).createCell(4).setCellValue("DISTRICT");
//                    sheet.getRow(rows).createCell(5).setCellValue("U/R");
//                    sheet.getRow(rows).createCell(6).setCellValue("M10%");
//                    sheet.getRow(rows).createCell(7).setCellValue("M_Y");
//                    sheet.getRow(rows).createCell(8).setCellValue("D_M");
//                    sheet.getRow(rows).createCell(9).setCellValue("I" + (cposg.isIsBachelor() ? "50%" : "15%"));
//                    sheet.getRow(rows).createCell(10).setCellValue("I_Y");
//                    int ii = 11;
//                    if(!cposg.isIsBachelor()) {
//                        sheet.getRow(rows).createCell(ii++).setCellValue("G35%");
//                        sheet.getRow(rows).createCell(ii++).setCellValue("G_Y");
//                    }
//                    sheet.getRow(rows).createCell(ii++).setCellValue("T_M");
//                    sheet.getRow(rows).createCell(ii++).setCellValue("T40%");
//                    sheet.getRow(rows).createCell(ii++).setCellValue("FINAL PER");
//                    sheet.getRow(rows).createCell(ii++).setCellValue("CHOICE NO.");
//                    sheet.getRow(rows).createCell(ii++).setCellValue("STATUS");
//                    sheet.getRow(rows++).createCell(ii++).setCellValue("REMARKS");
//
//                    distributionProgressBar.setValue(0);
//                    distributionProgressBar.setMaximum(candidates.size() - 1);
//                    count += candidates.size();
//                        
//                    System.out.println(cposg.toString() + " - " + cc.toString() + " | " + candidates.size());
//                    HashMap<String, Float> map = new HashMap<>();
//                    for (int l = 0; l < candidates.size(); l++) {
//                        admission.controller.beans.Candidate cn = candidates.get(l);
//                        
//                        if(cposg.isPosIsQuotaOriented() && (cc.getCategoryCode() == Constant.CategoryCodes.GENERAL_MERIT_QUOTA || 
//                            cc.getCategoryCode() == Constant.CategoryCodes.UPPER_SINDH_OUT_OF_JURISDICTION_QUOTA)) {
//                            String s = cn.getDistrictName() + (category.getStatus() == 1 ? "" : ("," + cn.getArea()));
//                            Float per = map.get(s);
//                            if (per == null) {
//                                map.put(s, cn.getPercentage());
//                            } else if(cn.getPercentage() < per) {
//                                map.put(s, cn.getPercentage());
//                            }
//                        }
//                        
//                        try {
//                            CredentialDetails[] array = JDBCDatabaseManager.getCredentialDetails(cn.getCandidateId());
//                            cn.setCredentialDetailses(array);
//                        } catch (SQLException ex) {
//                            Logger.getLogger(AdmissionListDetailsDialog2.class.getName()).log(Level.SEVERE, null, ex);
//                            utilities.MessageBox.info(AdmissionListDetailsDialog2.this, ex);
//                        }
//                        nameLabel.setText(cposg.toString() + " - " + cc.toString() + " (" + cn.getName() + ")");
//                        CredentialDetails matric = cn.getCredentialDetailses()[0];
//                        CredentialDetails inter = cn.getCredentialDetailses()[1];
//                        Integer interMarksObtained = inter.getMarksObtained();
//        
//                        Float matricPer = 0.0F;
//                        Float interPer = 0.0F;
//                        Float gradPer = 0.0F;
//        
//                        Float up = matric.getMarksObtained() * 1.0F;
//                        Float down = matric.getTotalMarks() * 1.0F;
//                        if(up > 0 && down > 0)
//                            matricPer = (up / down) * 100.0F * Constant.MATRIC_PER;
//        
//                        if(!cposg.isIsBachelor()) {
//                            up = (interMarksObtained) * 1.0F;
//                        } else {
//                            up = (interMarksObtained - cn.getDeductionMarks()) * 1.0F;
//                        }
//                        down = inter.getTotalMarks() * 1.0F;
//                        
//                        if(up > 0 && down > 0)
//                            interPer = (up / down) * 100.0F * (cposg.isIsBachelor() ? Constant.INTER_PER : Constant.M_INTER_PER);
//        
//                        if(!cposg.isIsBachelor()) {
//                            CredentialDetails grad = cn.getCredentialDetailses()[2];
//                            up = (grad.getMarksObtained() - cn.getDeductionMarks()) * 1.0F;
//                            down = grad.getTotalMarks() * 1.0F;
//                            if(up > 0 && down > 0)
//                                gradPer = (up / down) * 100.0F * Constant.DEGREE_PER;
//                        }
//                        Float testPer = (cn.getTestScore() / Constant.TOTAL_MARKS) * 100.0F * Constant.TEST_PER;
//                        
//                        matricPer = Float.parseFloat(nf.format(matricPer));
//                        interPer = Float.parseFloat(nf.format(interPer));
//                        gradPer = Float.parseFloat(nf.format(gradPer));
//                        float finalPer = Float.parseFloat(nf.format(cn.getPercentage()));
//                        
//                        if(yesRadioButton.isSelected()){
//                            try {
//                                //System.out.println(cn.getCandidateProgramOfStudyId());
//                                JDBCDatabaseManager.addAdmissionListDetails(cn.getCandidateId(), admissionList.getAdmissionListId(), cposg.getCposGroupId(), cn.getCandidateProgramOfStudyId(), cc.getCampusCategoryId(), matricPer, interPer, testPer, 0, 0, finalPer, cn.getStatus(), "");
//                            } catch (SQLException ex) {
//                                utilities.MessageBox.info(AdmissionListDetailsDialog2.this, ex);
//                                Logger.getLogger(AdmissionListDetailsDialog2.class.getName()).log(Level.SEVERE, null, ex);
//                            }
//                        }
//                        sheet.createRow(rows).createCell(0).setCellValue(l + 1);
//                        sheet.getRow(rows).createCell(1).setCellValue(cn.getSeatNo());
//                        sheet.getRow(rows).createCell(2).setCellValue(cn.getName());
//                        sheet.getRow(rows).createCell(3).setCellValue(cn.getFathersName());
//                        sheet.getRow(rows).createCell(4).setCellValue(cn.getDistrictName());
//                        sheet.getRow(rows).createCell(5).setCellValue(cn.getArea());
//                        sheet.getRow(rows).createCell(6).setCellValue(matricPer);
//                        sheet.getRow(rows).createCell(7).setCellValue(matric.getPassingYear());
//                        sheet.getRow(rows).createCell(8).setCellValue(cn.getDeductionMarks());
//                        sheet.getRow(rows).createCell(9).setCellValue(interPer);
//                        sheet.getRow(rows).createCell(10).setCellValue(inter.getPassingYear());
//                        ii = 11;
//                        if(!cposg.isIsBachelor()) {
//                            sheet.getRow(rows).createCell(ii++).setCellValue(gradPer);
//                            sheet.getRow(rows).createCell(ii++).setCellValue(cn.getCredentialDetailses()[2].getPassingYear());
//                        }
//                        sheet.getRow(rows).createCell(ii++).setCellValue(cn.getTestScore());
//                        sheet.getRow(rows).createCell(ii++).setCellValue(Float.parseFloat(nf.format(testPer)));
//                        sheet.getRow(rows).createCell(ii++).setCellValue(cn.getPercentage()/*Math.round(cn.getPercentage() * 100.0F) / 100.0F*/);
//                        sheet.getRow(rows).createCell(ii++).setCellValue(cn.getChoiceNo());
//                        sheet.getRow(rows).createCell(ii++).setCellValue(cn.getStatus());
//                        sheet.getRow(rows++).createCell(ii++).setCellValue(cn.getObjectionRemarks());
//
//                        sheet3.createRow(rows3).createCell(0).setCellValue(l + 1);
//                        sheet3.getRow(rows3).createCell(1).setCellValue(cn.getSeatNo());
//                        sheet3.getRow(rows3).createCell(2).setCellValue(cn.getName());
//                        sheet3.getRow(rows3).createCell(3).setCellValue(cn.getFathersName());
//                        sheet3.getRow(rows3).createCell(4).setCellValue(cn.getDistrictName());
//                        sheet3.getRow(rows3).createCell(5).setCellValue(cn.getArea());
//                        sheet3.getRow(rows3).createCell(6).setCellValue(String.valueOf(cn.getPercentage()));
//                        sheet3.getRow(rows3).createCell(7).setCellValue(cn.getChoiceNo());
//                        sheet3.getRow(rows3).createCell(8).setCellValue(cn.getStatus());
//                        sheet3.getRow(rows3).createCell(9).setCellValue(cc.isMorning() ? cc.toString() : (cc.toString() + " " + cc.getShiftName()));
//                        String posName;
//                        if(!cposg.isIsBachelor()) {
//                            posName = cposg.getProgramName() + (cposg.getProgramName().equals("M.LIS") || cposg.getProgramName().equals("PGD") || cposg.getProgramName().startsWith("1 Year Con")  || cposg.getProgramName().equals("LL.M") ? "" : " (PREVIOUS)") + " " + 
//                                    (!cposg.getProgramName().equals("M.COM") && !cposg.getProgramName().equals("M.B.A") && !cposg.getProgramName().equals("M.P.A") && !cposg.getProgramName().equals("M.C.S") ? cposg.getPosName() : "");
////                                    (cc.isMorning() ? "" : " EVENING");
//                        } else posName = cposg.toString();
//                        sheet3.getRow(rows3++).createCell(10).setCellValue(posName);
//                        
//                        distributionProgressBar.setValue(l + 1);
//                    }
//                    if(cposg.isPosIsQuotaOriented() && (cc.getCategoryCode() == Constant.CategoryCodes.GENERAL_MERIT_QUOTA || 
//                        cc.getCategoryCode() == Constant.CategoryCodes.UPPER_SINDH_OUT_OF_JURISDICTION_QUOTA)) {
//                        
//                        sheet2.createRow(rows2).createCell(1).setCellValue("DISTRICT");
//                        sheet2.getRow(rows2).createCell(2).setCellValue("AREA");
//                        sheet2.getRow(rows2++).createCell(3).setCellValue("PERCENTAGE");
//                        
//                        Set<String> keySet = map.keySet();
//                        for (String key : keySet) {
//                            String[] split = key.split(",");
//                            Float per = map.get(key);
//                            sheet2.createRow(rows2).createCell(1).setCellValue(split[0]);
//                            sheet2.getRow(rows2).createCell(2).setCellValue(split.length == 2 ? split[1] : "");
//                            sheet2.getRow(rows2++).createCell(3).setCellValue(per + "");
//                        }
//                    }
//                        
//                    sheet.createRow(rows++);
//                    sheet.createRow(rows++);
//                }
//                sheet1.createRow(++rows1).createCell(0).setCellValue("TOTAL");
//                sheet1.getRow(rows1++).createCell(2).setCellValue(total);
//                    
//                sheet2.createRow(rows2++);
//                
//                sheet.createRow(rows++);
//                sheet.createRow(rows++);
//            }
//            try(FileOutputStream out = new FileOutputStream(new File("D:\\List\\Selection_List.xls"));
//                    FileOutputStream out1 = new FileOutputStream(new File("D:\\List\\Summery.xls"));
//                    FileOutputStream out3 = new FileOutputStream(new File("D:\\List\\UPLOAD.xls"));
//                    FileOutputStream out2 = new FileOutputStream(new File("D:\\List\\Percentage_Summery.xls"));){
//                workbook.write(out);
//                workbook1.write(out1);
//                workbook2.write(out2);
//                workbook3.write(out3);
//            } catch (FileNotFoundException ex) {
//                utilities.MessageBox.info(AdmissionListDetailsDialog2.this, ex);
//                Logger.getLogger(AdmissionListDetailsDialog2.class.getName()).log(Level.SEVERE, null, ex);
//            } catch (IOException ex) {
//                utilities.MessageBox.info(AdmissionListDetailsDialog2.this, ex);
//                Logger.getLogger(AdmissionListDetailsDialog2.class.getName()).log(Level.SEVERE, null, ex);
//            }
//            return count;
//        }
//    }
    //</editor-fold>

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton backButton;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton buttonObjectionRemarks;
    private javax.swing.JButton buttonPrint;
    private javax.swing.JButton buttonStoreInDatabase;
    private javax.swing.JLabel candidateLabel;
    private javax.swing.JProgressBar dataProgressBar;
    private javax.swing.JCheckBox defaulterCandidateCheckBox;
    private javax.swing.JLabel disciplinesLabel;
    private javax.swing.JButton exportButton;
    private javax.swing.JFileChooser fileChooser;
    private javax.swing.JProgressBar filterProgressBar;
    private javax.swing.JButton generateListButton;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel listOfLabel;
    private javax.swing.JButton loadDataButton;
    private javax.swing.JRadioButton noRadioButton;
    private javax.swing.JCheckBox seperateTestCheckBox;
    private javax.swing.JLabel timeLabel;
    private javax.swing.JPanel titlePanel;
    private javax.swing.JLabel totalCandidateChoicesLabel;
    private javax.swing.JLabel totalCandidateLabel;
    private javax.swing.JRadioButton yesRadioButton;
    // End of variables declaration//GEN-END:variables
    private DocumentFilter documentFilter;
    private Resources privileges;
    private AdmissionList admissionList;
    private Thread myThread;
    private javax.swing.Timer timer;
    private AdmissionController ac;
    private FileNameExtensionFilter excelFilter;

    private int appendSelectedExcel(Integer candidateSNO, Map<Boolean, List<int[]>> currentSelection, boolean shift, CandidateAlg candidateAlg, Campus campus, ProgramType pt, ExcelHandler eh, PrintStream outCandidateResult) {
        List<int[]> list = currentSelection.get(shift);
        if (list != null) {
            for (int[] is : list) {
                int posId = is[0];
                int cat = is[1];
                int choiceNo = is[2];
                int catLogicalCode = is[3];
                int cnposId = is[4];
                int campusCategoryId = is[5];
                int cposgId = is[6];
                try {
                    /* [0seatno, 1candidate_name, 2fathers_name, 3percentage, 4program_name, 5program_remarks, 6program_of_study_name, 7district_name, 8is_jurisdiction, 9years_degree,12Mobile,13email,14family_mobile] */
                    Object[] candidateAndSelectionInfo = JDBCDatabaseManagerAlg.getCandidateAndSelectionInfoAlg(candidateAlg.getId(), posId, campus.getCampusId());
                    if (candidateAndSelectionInfo == null) {
                        candidateAndSelectionInfo = JDBCDatabaseManagerAlg.getCandidateAndSelectionInfoAlg(candidateAlg.getId(), posId);
                    }
//                            System.out.println(candidateAndSelectionInfo);
                    String seatNo = candidateAndSelectionInfo[0] + "";
                    String name = candidateAndSelectionInfo[1] + "";
                    String father = candidateAndSelectionInfo[2] + "";
                    String surname = candidateAndSelectionInfo[3] + "";
                    String district = candidateAndSelectionInfo[7] + "";
                    String area = AreaEnum.values()[candidateAlg.getArea()].getCode() + " ";
                    String perc = candidateAndSelectionInfo[3] + "";
                    String catCode = CategoryEnum.values()[cat].getDesc() + "";
                    String mobile = candidateAndSelectionInfo[candidateAndSelectionInfo.length - 3]+"";
                    String email = candidateAndSelectionInfo[candidateAndSelectionInfo.length - 2]+"";
                    String familyMobile = candidateAndSelectionInfo[candidateAndSelectionInfo.length - 1]+"";
                    int deductionMarks = (Integer) candidateAndSelectionInfo[candidateAndSelectionInfo.length - 5];
                    String objectionRemarks = (String) candidateAndSelectionInfo[candidateAndSelectionInfo.length - 4];
                    String jurisdiction = "";
                    String p_name = (String)candidateAndSelectionInfo[4];
                    String pos_name = (String)candidateAndSelectionInfo[6];
                    
                    if (candidateAndSelectionInfo[8] != null) {
                        if (cat == CategoryEnum.GM_DUR_QUOTA.ordinal()) {
                            if ((boolean) candidateAndSelectionInfo[8]) {
                                jurisdiction = " (JURISDICTION)";
                            } else {
                                jurisdiction = " (OUT OF JURISDICTION)";
                            }
                        }
                    }
                    ArrayList<String> choices = JDBCDatabaseManagerAlg.getCandidateChoicesCodeAlg(candidateAlg.getId(), campus.getCampusId());
                    StringBuilder builder = new StringBuilder();
                    for (String string : choices) {
                        builder.append(string);
                    }
                    Object[] lastCre = JDBCDatabaseManagerAlg.getLastCredential(candidateAlg.getId());
                    StringBuilder credential = new StringBuilder();
                    for (Object object : lastCre) {
                        credential.append(object.toString() + " ");
                    }
                    List<String> optionals = JDBCDatabaseManagerAlg.getOptionalSubjects(candidateAlg.getId());
                    StringBuilder optionalSubjects = new StringBuilder();
                    for (String object : optionals) {
                        optionalSubjects.append(">" + object + "  ");
                    }

                    //<editor-fold defaultstate="collapsed" desc="Printing in Candidate Result SQL">
                    //<editor-fold defaultstate="collapsed" desc="Credentials">
                    List<CredentialDetailJRBean> credentialsJRBeans = JDBCDatabaseManagerAlg.getCredential(candidateAlg.getId());
                    int sscObtained = 0;
                    int hscObtained = 0;
                    int grdObtained = 0;
                    int sscTotal = 0;
                    int hscTotal = 0;
                    int grdTotal = 0;
                    int sscYear = 0;
                    int hscYear = 0;
                    int grdYear = 0;
                    int marksAfterDeduction = 0;
                    float sscPerc = 0f;
                    float hscPerc = 0f;
                    float grdPerc = 0f;
                    String group = null;
                    String hscGroup=null;

                    for (int index = 0; index < credentialsJRBeans.size(); index++) {
                        CredentialDetailJRBean cd = credentialsJRBeans.get(index);
                        if (cd.getoMrks() < 1 || cd.gettMrks() < 1) {
                            continue;
                        }
                        if (index == 0) { //SSC
                            sscObtained = cd.getoMrks().intValue();
                            sscTotal = cd.gettMrks().intValue();
                            sscYear = cd.getYear();
                            if (credentialsJRBeans.size() == 3){
                                sscPerc = (cd.getoMrks() / cd.gettMrks()) * IConstant.M_MATRIC_PER * 100.0F;
                            }else{
                                sscPerc = (cd.getoMrks() / cd.gettMrks()) * IConstant.MATRIC_PER * 100.0F;
                            }
                        } else if (index == 1) { //HSC
                            if (credentialsJRBeans.size() == 3) { //master hsc
                                hscPerc = (cd.getoMrks() / cd.gettMrks()) * 100.0F * IConstant.M_INTER_PER;
                                hscTotal = cd.gettMrks().intValue();
                                hscYear = cd.getYear();
                                hscObtained = cd.getoMrks().intValue();
                            } else if (credentialsJRBeans.size() == 2) { // bachalor hsc
                                hscPerc = (cd.getoMrks() / cd.gettMrks()) * 100.0F * IConstant.INTER_PER;
                                hscTotal = cd.gettMrks().intValue();
                                hscYear = cd.getYear();
                                hscObtained = cd.getoMrks().intValue();
                                group = cd.getGrp();
                                hscGroup=group;
                                marksAfterDeduction = (cd.getoMrks().intValue() - deductionMarks);
                            }

                        } else if (index == 2) { // GRADUATION
                            grdPerc = (cd.getoMrks() / cd.gettMrks()) * 100.0F * IConstant.DEGREE_PER;
                            grdTotal = cd.gettMrks().intValue();
                            grdYear = cd.getYear();
                            grdObtained = cd.getoMrks().intValue();
                            group = cd.getGrp();
                            marksAfterDeduction = (cd.getoMrks().intValue() - deductionMarks);
                        }
                    }
                    //</editor-fold>

                    outCandidateResult.println("INSERT INTO CANDIDATE_RESULT VALUES "
                            + "(" + candidateAlg.getId() + ", " + seatNo + ", \"" + name + "\" , \"" + father + "\" "
                            + " , \"" + district + "\" , \"" + area + "\" , \"" + group + "\", " + sscObtained + ", " + String.format("%.2f", sscPerc) + ""
                            + " , " + hscObtained + " , " + String.format("%.2f", hscPerc) + ", " + grdObtained + ", " + String.format("%.2f", grdPerc) + " , " + deductionMarks + " "
                            + " , " + candidateAlg.getScore()
                            + " , " + ((candidateAlg.getScore() / IConstant.TOTAL_MARKS) * 100.F * IConstant.TEST_PER) + ""
                            + " , " + candidateAlg.getPercentage() + ", \"" + campus.getName() + (" " + campus.getLocation()) + "\", \"" + CategoryLogicalCodeEnum.values()[catLogicalCode].getTitle() + " " + (shift ? " MORNING" : " EVENING") + "\""
                            + " , \"" + (candidateAndSelectionInfo[4] + " (" + candidateAndSelectionInfo[6] + ")") + "\" "
                            + " , " + choiceNo + " , " + campus.getCampusId() + ", " + pt.getProgramTypeId() + " "
                            + " , 0, \"00-00-0000\" , " + admissionList.getAdmissionSession().getAdmissionYear().getYear() + " "
                            + " , " + admissionList.getListNo() + " , \"" + objectionRemarks + "\", " + marksAfterDeduction + ",'"
                            + lastCre[lastCre.length - 1] + "', " + sscTotal + ", " + hscTotal + ", " + grdTotal + ", " + sscYear + ", " + hscYear + ", " + grdYear + ", \""+mobile+"\" , \""+email+"\", \""+familyMobile+"\", \""+p_name+"\",\""+hscGroup+"\",\""+surname+"\"  );");
                    outCandidateResult.flush();
                    //</editor-fold>
                    //<editor-fold defaultstate="collapsed" desc="Printing in Candidate Result in EXCEL">
                    Object[] cand = new Object[]{
                        (candidateSNO),
                        candidateAlg.getId(),
                        admissionList.getAdmissionListId(),
                        cposgId,
                        cnposId,
                        campusCategoryId,
                        cat,
                        catLogicalCode,
                        seatNo,
                        name,
                        father,
                        district,
                        area,
                        perc,
                        mobile,
                        email,
                        familyMobile,
                        choiceNo + "",
                        catCode + jurisdiction,
                        CategoryLogicalCodeEnum.values()[catLogicalCode].getTitle(),
                        candidateAndSelectionInfo[4] + " " + candidateAndSelectionInfo[6],
                        builder.toString(),
                        credential.toString(),
                        String.valueOf(candidateAndSelectionInfo[9]),
                        optionalSubjects.toString(),
                        objectionRemarks};
                    eh.appendRow(cand);
                    //</editor-fold>
                } catch (SQLException ex) {
                    MessageBox.error(this, "Error Occured fetching student selection");
                    ex.printStackTrace();
                    return -1;
                }
            }

        } else {
            return -1;
        }
        return candidateSNO;
    }

    private void appendExcelNotSelected(int candidateSNO, CandidateAlg candidateAlg, Integer campusId, ExcelHandler ehNotSelected) {
        try {
            /* SEAT_NO, NAME, FATHER, PERCENTAGE, DISTRICT, IS_JURISDICTION, YEARS_DEGREE */
            Object[] candidateAndSelectionInfo = JDBCDatabaseManagerAlg.getCandidate(candidateAlg.getId());

            ArrayList<String> choices = JDBCDatabaseManagerAlg.getCandidateChoicesCodeAlg(candidateAlg.getId(), campusId);
            StringBuilder builder = new StringBuilder();
            for (String string : choices) {
                builder.append(string);
            }
            Object[] lastCre = JDBCDatabaseManagerAlg.getLastCredential(candidateAlg.getId());
            StringBuilder credential = new StringBuilder();
            for (Object object : lastCre) {
                credential.append(object.toString() + " ");
            }
            List<String> optionals = JDBCDatabaseManagerAlg.getOptionalSubjects(candidateAlg.getId());
            StringBuilder optionalSubjects = new StringBuilder();
            for (String object : optionals) {
                optionalSubjects.append(">" + object + "  ");
            }

            String choicebuilder = builder.toString();
            String credentailbuilder = credential.toString();
            String optionalbuilder = optionalSubjects.toString();
            String seatNo = candidateAlg.getSeatNo() + "";
            String name = candidateAndSelectionInfo[1] + "";
            String father = candidateAndSelectionInfo[2] + "";
            String perc = candidateAndSelectionInfo[3] + "";
            String district = candidateAndSelectionInfo[4] + "";
            String objectionRemarks = candidateAndSelectionInfo[8] + "";
            String area = candidateAndSelectionInfo[9] + "";
            Object[] cand = new Object[]{
                "",
                seatNo,
                name,
                father,
                district,
                area,
                perc,
                choicebuilder,
                credentailbuilder,
                optionalbuilder,
                objectionRemarks
            };
            ehNotSelected.appendRow(cand);
        } catch (NullPointerException ex) {
            System.err.println(candidateAlg.getId() + " :  " + candidateAlg.getSeatNo());
            ex.printStackTrace(System.err);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    //<editor-fold desc="Discipline Wise Exporting">
    private void appendInExcel(ExcelHandler ehDiscipline, Map<Integer, Map<CandidateAlg, Integer>> selection, boolean morning, int campusId, ProgramOfStudy programOfStudy, ExcelHandler ehLastPerc, List<District> districts, Map<Integer, District> districtMap, ExcelHandler ehConsumed) {
        boolean isQuotaOriental = programOfStudy.getIsQuotaOriented();
        int posId = programOfStudy.getProgramOfStudyId();
        if (!(selection.isEmpty())) {

            //<editor-fold defaultstate="collapsed" desc="Printing Shift">
            String shiftname = "";
            if (morning) {
                shiftname = "MORNING";
            } else {
                shiftname = "EVENING";
            }
            ehDiscipline.appendRow(new Object[]{});
            ehDiscipline.appendRow(new Object[]{"DISCIPLINE:", programOfStudy.getName(), shiftname});

            ehLastPerc.appendRow(new Object[]{});
            ehLastPerc.appendRow(new Object[]{"DISCIPLINE:", programOfStudy.getName(), shiftname});

            ehConsumed.appendRow(new Object[]{});
            ehConsumed.appendRow(new Object[]{"DISCIPLINE:", programOfStudy.getName() + "-" + shiftname});
            //</editor-fold>

            Set<Integer> catLogicals = selection.keySet();
            ArrayList<Integer> catLogicalsList = new ArrayList(catLogicals);
            Collections.sort(catLogicalsList);
            //<editor-fold defaultstate="collapsed" desc="treaversing all categories and their candidates">
            for (Integer logicalCode : catLogicalsList) {
                //<editor-fold defaultstate="collapsed" desc="Printing Category Logical Code">
                ehDiscipline.appendRow(new Object[]{CategoryLogicalCodeEnum.values()[logicalCode.intValue()]});
                //</editor-fold>

                Map<CandidateAlg, Integer> candiMap = selection.get(logicalCode);
                int sno = 1;
                Set<CandidateAlg> candidates = candiMap.keySet();
                if (admissionList.getAdmissionSession().getProgramType().getIsBachelor()) {
                    ehDiscipline.appendRow(ExcelHandler.BACHALOR_DISCIPLINE_COLUMNS);
                } else {
                    ehDiscipline.appendRow(ExcelHandler.MASTER_DISCIPLINE_COLUMNS);
                }

                if (isQuotaOriental && (morning) && (logicalCode.intValue() == CategoryLogicalCodeEnum.GENERAL_MERIT_QUOTA.ordinal() || logicalCode.intValue() == CategoryLogicalCodeEnum.UPPER_SINDH_OUT_OF_JURISDICTION_QUOTA.ordinal())) {

                    //<editor-fold defaultstate="collapsed" desc="printing category">
                    ehConsumed.appendRow(new Object[]{CategoryLogicalCodeEnum.values()[logicalCode.intValue()]});
                    ehConsumed.appendRow(new Object[]{"", "U", "R", "Remaining", "Total"});
                    //</editor-fold>
                    //    DistrictId, < Area, List<Candidates> >
                    Map<Integer, Map<Integer, List<CandidateAlg>>> districtwiseCandidatesMap = new LinkedHashMap<>();
                    //desc="traversing all districts"
                    for (District dis : districts) {
                        districtwiseCandidatesMap.put(dis.getDistrictId(), new LinkedHashMap<Integer, List<CandidateAlg>>());
                    }

                    // sorting candidate district wise
                    for (CandidateAlg candidateAlg : candidates) {
                        Map<Integer, List<CandidateAlg>> districtURMap = districtwiseCandidatesMap.get(candidateAlg.getDistrictId());
                        if (districtURMap.get(candidateAlg.getArea()) == null) {
                            districtURMap.put(candidateAlg.getArea(), new LinkedList<CandidateAlg>());
                        }
                        districtURMap.get(candidateAlg.getArea()).add(candidateAlg);
                    }
                    Set<Integer> districtIdSet = districtwiseCandidatesMap.keySet();
                    for (Integer districtId : districtIdSet) {
                        Float lastPercU = null;
                        Float lastPercR = null;
                        int consumedRural = 0;
                        int consumedUrban = 0;
                        Map<Integer, List<CandidateAlg>> urCandis = districtwiseCandidatesMap.get(districtId);
                        List<Integer> urIdList = new LinkedList(urCandis.keySet());
                        Collections.sort(urIdList);
                        for (Integer urId : urIdList) {
                            List<CandidateAlg> candidatesList = urCandis.get(urId);
                            for (CandidateAlg candidateAlg : candidatesList) {
                                if (candidateAlg.getArea() == 0) {
                                    consumedUrban++;
                                    if (lastPercU == null || lastPercU > candidateAlg.getPercentage()) {
                                        lastPercU = candidateAlg.getPercentage();
                                    }
                                } else {
                                    consumedRural++;
                                    if (lastPercR == null || lastPercR > candidateAlg.getPercentage()) {
                                        lastPercR = candidateAlg.getPercentage();
                                    }
                                }
                                print(ehDiscipline, candiMap, candidateAlg, sno, posId, campusId);
                                sno++;
                            }
                        }
                        if (lastPercR != null || lastPercU != null) {
                            int[] seats = null;
                            int total = 0;
                            try {
                                seats = JDBCDatabaseManagerAlg.getDistrictSeats(ac.getAy().getAdmissionYearId(), campusId, districtId);
                            } catch (Exception ex) {
                                ex.printStackTrace();
                            }

                            if (seats != null) {
                                for (int t : seats) {
                                    total += t;
                                }
                            }
                            if (total < (consumedRural + consumedUrban)) {
                                total *= 2;
                            }
                            ehLastPerc.appendRow(new Object[]{districtMap.get(districtId).getName(), "Urban", lastPercU, "Rural", lastPercR});
                            ehConsumed.appendRow(new Object[]{districtMap.get(districtId).getName(), consumedUrban, consumedRural, (total - (consumedRural + consumedUrban)), total});
                        }
                    }

                    //                    //<editor-fold defaultstate="collapsed" desc="Quota Oriented Commented">
//                    /*   districtId,<area,candidateAlg >*/
//                    Map<Integer, Map<Integer, List<CandidateAlg>>> sortbydistrict = new LinkedHashMap<>();
//                    for (CandidateAlg candidateAlg : candidates) {
//                        if (sortbydistrict.get(candidateAlg.getDistrictId()) == null) {
//                            sortbydistrict.put(candidateAlg.getDistrictId(), new LinkedHashMap<Integer, List<CandidateAlg>>());
//                        }
//                        if (sortbydistrict.get(candidateAlg.getDistrictId()).get(candidateAlg.getArea()) == null) {
//                            sortbydistrict.get(candidateAlg.getDistrictId()).put(candidateAlg.getArea(), new LinkedList<CandidateAlg>());
//                        }
//                        sortbydistrict.get(candidateAlg.getDistrictId()).get(candidateAlg.getArea()).add(candidateAlg);
//                    }
//
//                    Set<Integer> districts = sortbydistrict.keySet();
//                    for (Integer districtId : districts) {
//                        Float lastPercU = null;
//                        Float lastPercR = null;
//                        Map<Integer, List<CandidateAlg>> districtUR = sortbydistrict.get(districtId);
//                        Set<Integer> areasSet = districtUR.keySet();
//                        ArrayList<Integer> areas = new ArrayList<>(areasSet);
//                        Collections.sort(areas);
//                        for (Integer ur : areas) {
//                            List<CandidateAlg> get = districtUR.get(ur);
//                            for (CandidateAlg candidateAlg : get) {
//                                //<editor-fold defaultstate="collapsed" desc="Last Perc">
//                                if (ur.intValue() == 0) {
//                                    if (lastPercU == null || lastPercU > candidateAlg.getPercentage()) {
//                                        lastPercU = candidateAlg.getPercentage();
//                                    }
//                                } else {
//                                    if (lastPercR == null || lastPercR > candidateAlg.getPercentage()) {
//                                        lastPercR = candidateAlg.getPercentage();
//                                    }
//                                }//</editor-fold>
//                                print(eh, candiMap, candidateAlg, sno, posId, campusId);
//                                sno++;
//                            }
//                        }
//                        ehLastPerc.appendRow(new Object[]{districtMap.get(districtId).getName(), "", "URBAN", lastPercU, "", "RURAL", lastPercR});
//                    }
                    //</editor-fold>
                } else {
                    Float perc = null;
                    for (CandidateAlg candidateAlg : candidates) {
                        if (perc == null || candidateAlg.getPercentage() < perc) {
                            perc = candidateAlg.getPercentage();
                        }
                        print(ehDiscipline, candiMap, candidateAlg, sno, posId, campusId);
                        sno++;
                    }

                    ehLastPerc.appendRow(new Object[]{CategoryLogicalCodeEnum.values()[logicalCode.intValue()], perc});
                    ehConsumed.appendRow(new Object[]{CategoryLogicalCodeEnum.values()[logicalCode.intValue()], (sno - 1)});
                }
            }
            //</editor-fold>

//            //<editor-fold defaultstate="collapsed" desc="calculating last perc percentage">
//            if (isQuotaOriental && morning) {
////                List<CandidateAlg> candisList = new LinkedList<>();
//                Set<Integer> catSet = selection.keySet();
//                for (Integer cat : catSet) {
//                    ehLastPerc.appendRow(new Object[]{CategoryLogicalCodeEnum.values()[cat].getTitle()});
//                    Map<CandidateAlg, Integer> get = selection.get(cat);
//                    List<CandidateAlg> candis = new LinkedList<>(get.keySet());
//                    for (District dis : districts) {
//                        Float lastPercU = null;
//                        Float lastPercR = null;
//                        for (CandidateAlg candi : candis) {
//                            if (candi.getDistrictId() == dis.getDistrictId().intValue()) {
//                                if (candi.getArea() == 0) {
//                                    if (lastPercU == null || lastPercU > candi.getPercentage()) {
//                                        lastPercU = candi.getPercentage();
//                                    }
//                                } else {
//                                    if (lastPercR == null || lastPercR > candi.getPercentage()) {
//                                        lastPercR = candi.getPercentage();
//                                    }
//                                }
//                            }
//                        }
//                        if (lastPercU != null || lastPercR != null) {
//                            ehLastPerc.appendRow(new Object[]{dis.getName(), "Urban", lastPercU, "Rural", lastPercR});
//                        }
//                    }
//                }
//
////                Collection<Map<CandidateAlg, Integer>> values = selection.values();
////                for (Map<CandidateAlg, Integer> map : values) {
////                    Set<CandidateAlg> keySet = map.keySet();
////                    for (CandidateAlg candidateAlg : keySet) {
////                        candisList.add(candidateAlg);
////                    }
////                }
////                for (District dis : districts) {
////                    Float lastPercU = null;
////                    Float lastPercR = null;
////                    for (CandidateAlg candi : candisList) {
////                        if (candi.getDistrictId() == dis.getDistrictId().intValue()) {
////                            if (candi.getArea() == 0) {
////                                if (lastPercU == null || lastPercU > candi.getPercentage()) {
////                                    lastPercU = candi.getPercentage();
////                                }
////                            } else {
////                                if (lastPercR == null || lastPercR > candi.getPercentage()) {
////                                    lastPercR = candi.getPercentage();
////                                }
////                            }
////                        }
////                    }
////                    if (lastPercU != null || lastPercR != null) {
////                        ehLastPerc.appendRow(new Object[]{dis.getName(),"Urban",""+ lastPercU  ,"Rural",""+lastPercR});
////                    }
////                }
//            }
//            //</editor-fold>
        }
    }

    private void print(ExcelHandler eh, Map<CandidateAlg, Integer> candiMap, CandidateAlg candidateAlg, int sno, int posId, int campusId) {
        try {
            //<editor-fold defaultstate="collapsed" desc="fetching data from database">
            /* [0seatno, 1candidate_name, 2fathers_name, 3percentage, 4program_name, 5program_remarks, 6program_of_study_name, 7district_name, 8is_jurisdiction, 9years_degree] */
            Object[] candidateAndSelectionInfo = JDBCDatabaseManagerAlg.getCandidateAndSelectionInfoAlg(candidateAlg.getId(), posId, campusId);
            if (candidateAndSelectionInfo == null) {
                candidateAndSelectionInfo = JDBCDatabaseManagerAlg.getCandidateAndSelectionInfoAlg(candidateAlg.getId(), posId);
            }
            String seatNo = candidateAndSelectionInfo[0] + "";
            String name = candidateAndSelectionInfo[1] + "";
            String father = candidateAndSelectionInfo[2] + "";
            String district = candidateAndSelectionInfo[7] + "";
            String area = AreaEnum.values()[candidateAlg.getArea()].getCode() + " ";
            String perc = candidateAndSelectionInfo[3] + "";
            String objection_remarks = candidateAndSelectionInfo[candidateAndSelectionInfo.length - 4] + "";
            Integer deductionMarks = (Integer) candidateAndSelectionInfo[candidateAndSelectionInfo.length - 5];
//                        String catCode = categoryEnum.getTitle() + "";
//                        String jurisdiction = "";
            int candAlgId = candidateAlg.getId();
            List<CredentialDetailJRBean> credential = JDBCDatabaseManagerAlg.getCredential(candAlgId);
            String gender = GenderEnum.values()[candidateAlg.getGender()].getCode();
            //</editor-fold>
            eh.appendRow(new Object[]{
                sno,
                seatNo,
                gender,
                name,
                father,
                district,
                area,
                candiMap.get(candidateAlg),
                credential,
                deductionMarks,
                credential.get(credential.size() - 1).getoMrks() - deductionMarks,
                candidateAlg.getScore(),
                ((candidateAlg.getScore() / IConstant.TOTAL_MARKS) * 100.F * IConstant.TEST_PER),
                perc,
                objection_remarks});

        } catch (SQLException ex) {
            MessageBox.error(this, "Error Occured fetching student selection");
            ex.printStackTrace(System.err);
        }

    }

    private void appendExcel(ExcelHandler eh, Map<Integer, Map<CandidateAlg, Integer>> selection, String shift, int campusId, int posId) {
        if (!(selection.isEmpty())) {
            eh.appendRow(new Object[]{shift,});
            eh.appendRow(new Object[]{""});
            Set<Integer> keySet = selection.keySet();
            eh.appendRow(new Object[]{"S.No", "Seat No", "Name", "Fathers Name", "District", "Area", "Choice#", "", "SSC-OB", "SSC-T", "SSC-GRP", "SSC-Year", "HSC-OB", "HSC-T", "HSC-GRP", "HSC-Year", "DEG-OB", "DEG-T", "GRD-DEG", "GRD-Year"});
            int sno = 1;
            for (Integer district : keySet) {

                Map<CandidateAlg, Integer> candiMap = selection.get(district);

                //   AreaId ,< candidate,choiceNo>
                Map<Integer, Map<CandidateAlg, Integer>> areaCandiMap = new LinkedHashMap<>();

                Set<CandidateAlg> candidates = candiMap.keySet();
                for (CandidateAlg candidateAlg : candidates) {
                    if (areaCandiMap.get(candidateAlg.getArea()) == null) {
                        areaCandiMap.put(candidateAlg.getArea(), new LinkedHashMap<CandidateAlg, Integer>());
                    }
                    Map<CandidateAlg, Integer> cands = areaCandiMap.get(candidateAlg.getArea());
                    cands.put(candidateAlg, candiMap.get(candidateAlg));
                }

                Set<Integer> keySetArea = areaCandiMap.keySet();
                for (Integer area : keySetArea) {
                    Map<CandidateAlg, Integer> areaCandidatesMap = areaCandiMap.get(area);
                    Set<CandidateAlg> areaCandidates = areaCandidatesMap.keySet();
                    for (CandidateAlg candidateAlg : areaCandidates) {
                        try {
                            //<editor-fold desc="fetching data from database">
                        /* [0seatno, 1candidate_name, 2fathers_name, 3percentage, 4program_name, 5program_remarks, 6program_of_study_name, 7district_name, 8is_jurisdiction, 9years_degree] */
                            Object[] candidateAndSelectionInfo = JDBCDatabaseManagerAlg.getCandidateAndSelectionInfoAlg(candidateAlg.getId(), posId, campusId);
                            if (candidateAndSelectionInfo == null) {
                                candidateAndSelectionInfo = JDBCDatabaseManagerAlg.getCandidateAndSelectionInfoAlg(candidateAlg.getId(), posId);
                            }
                            String seatNo = candidateAndSelectionInfo[0] + "";
                            String name = candidateAndSelectionInfo[1] + "";
                            String father = candidateAndSelectionInfo[2] + "";
                            String districtName = candidateAndSelectionInfo[7] + "";
                            String areaName = AreaEnum.values()[candidateAlg.getArea()].getCode() + " ";
                            String perc = candidateAndSelectionInfo[3] + "";
//                        String catCode = district.getDesc() + "";
//                        String jurisdiction = "";

                            List<CredentialDetailJRBean> credential = JDBCDatabaseManagerAlg.getCredential(candidateAlg.getId());

                            //</editor-fold>
                            eh.appendRow(new Object[]{sno, seatNo, name, father, districtName, areaName, candiMap.get(candidateAlg), credential, perc});
                            sno++;
                        } catch (SQLException ex) {
                            MessageBox.error(this, "Error Occured fetching student selection");
                            ex.printStackTrace(System.err);
                        }

                    }
                }
            }
        }
    }
    //</editor-fold>

    private SelectionCandidateBean prepareCandidateSelection(CandidateAlg candidateAlg, ProgramType type, int choiceNo) throws SQLException {
        SelectionCandidateBean bean = new SelectionCandidateBean();
        bean.setSeatNo(candidateAlg.getSeatNo());

        Object[] candidateAndSelectionInfo = JDBCDatabaseManagerAlg.getCandidate(candidateAlg.getId());

        bean.setSeatNo((Integer) candidateAndSelectionInfo[0]);
        bean.setName(candidateAndSelectionInfo[1] + "");
        bean.setFathersName(candidateAndSelectionInfo[2] + "");
        bean.setDistrict(candidateAndSelectionInfo[4] + "");
        bean.setArea(AreaEnum.values()[candidateAlg.getArea()].getCode());
        String perc = candidateAndSelectionInfo[3] + "";
        bean.setDeductionMarks((Integer) candidateAndSelectionInfo[7]);
        bean.setChoiceNo(choiceNo);
        //<editor-fold defaultstate="collapsed" desc="set credentials">
        List<CredentialDetailJRBean> credential = JDBCDatabaseManagerAlg.getCredential(candidateAlg.getId());
        for (CredentialDetailJRBean credentialDetailJRBean : credential) {
            if (credentialDetailJRBean.getoMrks() < 1 || credentialDetailJRBean.gettMrks() < 1) {
                continue;
            }
            if (credentialDetailJRBean.getDetailOf().contains("H")) {
                bean.setInterGroup(credentialDetailJRBean.getGrp());
                bean.setInterObtained(Math.round(credentialDetailJRBean.getoMrks()));
                if (type.getIsBachelor()) {
                    bean.setInterPerc(((credentialDetailJRBean.getoMrks() - ((Integer) candidateAndSelectionInfo[7])) / credentialDetailJRBean.gettMrks()) * 100 * (IConstant.INTER_PER));
                } else {
                    bean.setInterPerc((credentialDetailJRBean.getoMrks() / credentialDetailJRBean.gettMrks()) * 100 * (IConstant.M_INTER_PER));
                }
                bean.setInterYear(credentialDetailJRBean.getYear());
            } else if (credentialDetailJRBean.getDetailOf().contains("S")) {
                bean.setMatricGroup(credentialDetailJRBean.getGrp());
                bean.setMatricObtained(Math.round(credentialDetailJRBean.getoMrks()));
                bean.setMatricPerc((credentialDetailJRBean.getoMrks() / credentialDetailJRBean.gettMrks()) * 100 * (type.getIsBachelor()?IConstant.MATRIC_PER:IConstant.M_MATRIC_PER));
                bean.setMatricYear(credentialDetailJRBean.getYear());
            } else if (credentialDetailJRBean.getDetailOf().contains("G")) {
                bean.setGraduationGroup(credentialDetailJRBean.getGrp());
                bean.setGraduationObtained(Math.round(credentialDetailJRBean.getoMrks()));
                bean.setGraduationPerc(((credentialDetailJRBean.getoMrks() - (Integer) candidateAndSelectionInfo[7]) / credentialDetailJRBean.gettMrks()) * 100 * (IConstant.DEGREE_PER));
                bean.setGraduationYear(credentialDetailJRBean.getYear());
            }
        }
        //</editor-fold>
        bean.setScore(candidateAlg.getScore());
        bean.setScorePerc((candidateAlg.getScore() / IConstant.TOTAL_MARKS) * 100 * IConstant.TEST_PER);
        return bean;
    }

    private int addInDatabase(Map<Boolean, List<int[]>> currentSelection, boolean morning, CandidateAlg candidateAlg, int campusId) {
        List<int[]> list = currentSelection.get(morning);
        if (list != null) {
            for (int[] is : list) {
                int posId = is[0];
                int cat = is[1];
                int choiceNo = is[2];
                int catLogicalCode = is[3];
                int cnposId = is[4];
                int campusCategoryId = is[5];
                int cposgId = is[6];
                try {
                    JDBCDatabaseManagerAlg.addAdmissionListDetails(candidateAlg.getId(), admissionList.getAdmissionListId(), cposgId, cnposId, campusCategoryId, 0, cat);
                } catch (SQLException ex) {
                    MessageBox.error(this, "Error Occured fetching student selection");
                    ex.printStackTrace();
                    return -1;
                }
            }

        } else {
            return 0;
        }
        return list.size();
    }

    private void fillChoiceFile(List<CandidateAlg> cList, PrintStream outChoice) throws SQLException {
        for (CandidateAlg candidateAlg : cList) {
//            if (candidateAlg.getCurrentSelection() == null || candidateAlg.getCurrentSelection().isEmpty()) {
//                continue;
//            }
            List<Object[]> candidateChoice = JDBCDatabaseManagerAlg.getCandidateChoice(candidateAlg.getId(), admissionList.getCampus().getCampusId());
            for (Object[] object : candidateChoice) {
                int campusId = (Integer) object[1];
                String discName = (String) object[2];
                int choiceNo = (Integer) object[3];
                int shiftId = (Integer) object[4];
                String shiftName = (String) object[5];
                outChoice.println("INSERT INTO CANDIDATE_CHOICE VALUES ( " + candidateAlg.getId() + ", " + campusId + ", \"" + discName + "\" , " + choiceNo + ", " + shiftId + " , \"" + shiftName + "\"); ");
                outChoice.flush();
            }
        }
    }

    private void printAppliedCategories(CandidateAlg candidateAlg, PrintStream outCandidatCategories) {
        try {
            List<Integer> appliedCategories = JDBCDatabaseManagerAlg.getAppliedCategories(candidateAlg.getId());
            for (Integer code : appliedCategories) {
                outCandidatCategories.println("INSERT INTO CANDIDATE_CATEGORY (CANDIDATE_ID, CODE, CATEGORY_NAME) VALUES ( " + candidateAlg.getId() + ", " + code + ",'" + CategoryEnum.values()[code] + "' );");
            }
            outCandidatCategories.flush();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void appendNotSelectedResult(PrintStream outCandidateResult, CandidateAlg candidateAlg, ProgramType pt) throws Exception {
        Object[] candidateInfo = JDBCDatabaseManagerAlg.getCandidate(candidateAlg.getId());
        String name = (String) candidateInfo[1];
        String father = (String) candidateInfo[2];
        String surname = (String) candidateInfo[3];
        String district = (String) candidateInfo[4];
        int deductionMarks = (Integer) candidateInfo[7];
        String objectionRemarks = (String) candidateInfo[8];
        int area = (Integer) candidateInfo[9];

        //<editor-fold defaultstate="collapsed"  desc="credentials">
        List<CredentialDetailJRBean> credentialsJRBeans = JDBCDatabaseManagerAlg.getCredential(candidateAlg.getId());
        int sscObtained = 0;
        int hscObtained = 0;
        int grdObtained = 0;
        int sscTotal = 0;
        int hscTotal = 0;
        int group_=0;
        int grdTotal = 0;
        int sscYear = 0;
        int hscYear = 0;
        int grdYear = 0;
        int marksAfterDeduction = 0;
        float sscPerc = 0f;
        float hscPerc = 0f;
        float grdPerc = 0f;
        String group = null;
        String interGroup=null;

        for (int index = 0; index < credentialsJRBeans.size(); index++) {
            CredentialDetailJRBean cd = credentialsJRBeans.get(index);
            if (cd.getoMrks() < 1 || cd.gettMrks() < 1) {
                continue;
            }
            if (index == 0) { //SSC
                sscObtained = cd.getoMrks().intValue();
                sscTotal = cd.gettMrks().intValue();
                sscYear = cd.getYear();
                sscPerc = (cd.getoMrks() / cd.gettMrks()) * ( (credentialsJRBeans.size() == 3)?IConstant.M_MATRIC_PER:IConstant.MATRIC_PER) * 100.0F;
            } else if (index == 1) { //HSC
                if (credentialsJRBeans.size() == 3) { //master hsc
                    hscPerc = (cd.getoMrks() / cd.gettMrks()) * 100.0F * IConstant.M_INTER_PER;
                    hscTotal = cd.gettMrks().intValue();
                    hscYear = cd.getYear();
                    hscObtained = cd.getoMrks().intValue();
                } else if (credentialsJRBeans.size() == 2) { // bachalor hsc
                    hscPerc = (cd.getoMrks() / cd.gettMrks()) * 100.0F * IConstant.INTER_PER;
                    hscTotal = cd.gettMrks().intValue();
                    hscYear = cd.getYear();
                    hscObtained = cd.getoMrks().intValue();
                    group = cd.getGrp();
                    interGroup=group;
                    marksAfterDeduction = (cd.getoMrks().intValue() - deductionMarks);
                }

            } else if (index == 2) { // GRADUATION
                grdPerc = (cd.getoMrks() / cd.gettMrks()) * 100.0F * IConstant.DEGREE_PER;
                grdTotal = cd.gettMrks().intValue();
                grdYear = cd.getYear();
                grdObtained = cd.getoMrks().intValue();
                group = cd.getGrp();
                marksAfterDeduction = (cd.getoMrks().intValue() - deductionMarks);
            }
        }
        //</editor-fold>

        outCandidateResult.println("INSERT INTO CANDIDATE_RESULT VALUES "
                + "(" + candidateAlg.getId() + ", " + candidateAlg.getSeatNo() + ", \"" + name + "\" , \"" + father + "\" "
                + " , \"" + district + "\" , \"" + area + "\" , \"" + group + "\", " + sscObtained + ", " + String.format("%.2f", sscPerc) + ""
                + " , " + hscObtained + " , " + String.format("%.2f", hscPerc) + ", " + grdObtained + ", " + String.format("%.2f", grdPerc) + " , " + deductionMarks + " "
                + " , " + candidateAlg.getScore()
                + " , " + ((candidateAlg.getScore() / IConstant.TOTAL_MARKS) * 100.F * IConstant.TEST_PER) + ""
                + " , " + candidateAlg.getPercentage() + ", \"" + "" + (" ") + "\", \"" + "" + " " + "" + "\""
                + " , \"" + "0" + "\" "
                + " , " + 0 + " , " + 0 + ", " + pt.getProgramTypeId() + " "
                + " , 0, \"00-00-0000\" , " + admissionList.getAdmissionSession().getAdmissionYear().getYear() + " "
                + " , " + admissionList.getListNo() + " , \"" + objectionRemarks + "\", " + marksAfterDeduction + ",'"
                + credentialsJRBeans.get(credentialsJRBeans.size() - 1).getBd() + "', " + sscTotal + ", " + hscTotal + ", " + grdTotal + ", " + sscYear + ", " + hscYear + ", " + grdYear + " );");
        outCandidateResult.flush();
    }
}
