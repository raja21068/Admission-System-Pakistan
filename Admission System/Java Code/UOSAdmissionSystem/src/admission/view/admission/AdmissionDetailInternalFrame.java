package admission.view.admission;

import admission.controller.DatabaseManager;
import admission.helpers.CommonHelper;
import admission.helpers.EntityHelper;
import admission.model.AdmissionListDetails;
import admission.model.AdmissionYear;
import admission.model.Candidate;
import admission.model.CposGroup;
import admission.model.security.Resources;
import admission.model.ProgramType;
import admission.model.Shift;
import admission.services.KeyConstant;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.List;
import javax.swing.AbstractAction;
import javax.swing.JComponent;
import javax.swing.KeyStroke;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.text.AbstractDocument;
import javax.swing.text.DocumentFilter;
import admission.utils.NumberFormatter;
import admission.utils.RollNoFormatter;
import admission.utils.RoundedBorder;

/**
 *
 * @author Raja Kumar & Jay Kumar
 */
public class AdmissionDetailInternalFrame extends javax.swing.JInternalFrame {

    boolean activeSelectionAccess;

    public AdmissionDetailInternalFrame() {
        initComponents();
        admission.utils.Utility.hideOnEscape(this);

        this.titlePanel.setBorder(RoundedBorder.createGradientBorder());

        this.detailTable.getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), "Enter");
        this.detailTable.getActionMap().put("Enter", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                TableCellEditor cellEditor = detailTable.getCellEditor();
                if (cellEditor != null) {
                    detailTable.getCellEditor().stopCellEditing();
                }

                int row = detailTable.getSelectedRow();
                int col = detailTable.getSelectedColumn() + 1;
                if (col > detailTable.getColumnCount() - 1) {
                    col = 0;
                    row += 1;
                    if (row > detailTable.getRowCount() - 1) {
                        row = 0;
                    }
                }
                detailTable.changeSelection(row, col, false, false);
            }
        });
        this.detailTableModel = (DefaultTableModel) this.detailTable.getModel();

        detailTableModel.addTableModelListener(
                new TableModelListener() {
                    @Override
                    public void tableChanged(TableModelEvent evt) {
                        if (evt.getType() == TableModelEvent.UPDATE) {
                            updateCell(evt);
                        }
                    }
                }
        );

        documentFilter = new admission.utils.UppercaseDocumentFilter();
        numberDocumentFilter = new admission.utils.NumberDocumentFilter();
        ((AbstractDocument) this.seatNoTextField.getDocument()).setDocumentFilter(numberDocumentFilter);

        if (CommonHelper.checkUserResourceAccess(KeyConstant.BOOKLET_ADMISSION_FORM)) {
            bookletAdmissionButton.setEnabled(true);
            bookletAdmissionInternalFrame = new BookletAdmissionInternalFrame();
        }
    }

    boolean b;

    @Override
    public void setVisible(boolean aFlag) {
        if (aFlag) {
            if (!b) {
                this.getDesktopPane().add(this.bookletAdmissionInternalFrame);
                b = !b;
            }
            activeSelectionAccess = CommonHelper.checkUserResourceAccess(KeyConstant.ACTIVE_ADMISSION_SELECTION);
            this.getAdmissionYear();
            this.getProgramType();
            clear(true);
        }
        super.setVisible(aFlag);
        if (seatNoTextField != null) {
            this.seatNoTextField.requestFocus();
        }
    }

    public void setPrivileges(Resources privileges) {
        this.privileges = privileges;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        clearButton = new javax.swing.JButton();
        backButton = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        admissionYearComboBox = new javax.swing.JComboBox();
        programTypeComboBox = new javax.swing.JComboBox();
        seatNoTextField = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        nameTextField = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        fathersNameTextField = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        surnameTextField = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        detailTable = new javax.swing.JTable();
        bookletAdmissionButton = new javax.swing.JButton();
        titlePanel = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        testScoreTextField = new javax.swing.JTextField();
        percentageTextField = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        districtTextField = new javax.swing.JTextField();

        setClosable(true);
        setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);
        setIconifiable(true);
        setResizable(true);
        setTitle("Admission Details");

        clearButton.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        clearButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Gnome-Document-New-32.png"))); // NOI18N
        clearButton.setToolTipText("New");
        clearButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearButtonActionPerformed(evt);
            }
        });

        backButton.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        backButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Gnome-Go-Home-32.png"))); // NOI18N
        backButton.setToolTipText("Hide Form");
        backButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backButtonActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel5.setText("Admission Year:");

        jLabel6.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel6.setText("Program Type:");

        admissionYearComboBox.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        programTypeComboBox.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        seatNoTextField.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        seatNoTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                seatNoTextFieldActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel7.setText("Seat No.:");

        jLabel9.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel9.setText("Name:");

        nameTextField.setEditable(false);
        nameTextField.setBackground(new java.awt.Color(255, 255, 255));

        jLabel10.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel10.setText("Fathers Name:");

        fathersNameTextField.setEditable(false);
        fathersNameTextField.setBackground(new java.awt.Color(255, 255, 255));

        jLabel11.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel11.setText("Surname:");

        surnameTextField.setEditable(false);
        surnameTextField.setBackground(new java.awt.Color(255, 255, 255));

        detailTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Active", "SNo.", "Roll No.", "Category", "Shift", "Discipline", "Campus", "Choice No.", "List"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Boolean.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class, java.lang.String.class, java.lang.Integer.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                true, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                if(columnIndex == 0) return activeSelectionAccess;
                return canEdit [columnIndex];
            }
        });
        detailTable.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        detailTable.setRowHeight(20);
        detailTable.setRowSelectionAllowed(false);
        detailTable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        detailTable.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(detailTable);

        bookletAdmissionButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Gnome-Emblem-Documents-32.png"))); // NOI18N
        bookletAdmissionButton.setToolTipText("Booklet");
        bookletAdmissionButton.setEnabled(false);
        bookletAdmissionButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bookletAdmissionButtonActionPerformed(evt);
            }
        });

        titlePanel.setLayout(new java.awt.BorderLayout());

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/admission/view/images/form-icon.png"))); // NOI18N
        jLabel3.setText("Admission Details");
        jLabel3.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, java.awt.Color.darkGray), javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5)));
        titlePanel.add(jLabel3, java.awt.BorderLayout.CENTER);

        jLabel1.setText("Press enter");

        jLabel12.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel12.setText("Test Score:");

        testScoreTextField.setEditable(false);
        testScoreTextField.setBackground(new java.awt.Color(255, 255, 255));
        testScoreTextField.setHorizontalAlignment(javax.swing.JTextField.TRAILING);

        percentageTextField.setEditable(false);
        percentageTextField.setBackground(new java.awt.Color(255, 255, 255));
        percentageTextField.setHorizontalAlignment(javax.swing.JTextField.TRAILING);

        jLabel13.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel13.setText("Percentage:");

        jLabel14.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel14.setText("District:");

        districtTextField.setEditable(false);
        districtTextField.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(titlePanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(jLabel9)
                            .addComponent(jLabel10)
                            .addComponent(jLabel5)
                            .addComponent(jLabel14))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(districtTextField)
                            .addComponent(nameTextField)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                    .addComponent(seatNoTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jLabel1))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                    .addComponent(admissionYearComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(4, 4, 4)
                                    .addComponent(jLabel6)
                                    .addGap(4, 4, 4)
                                    .addComponent(programTypeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(fathersNameTextField, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(surnameTextField)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(testScoreTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel13))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(clearButton, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(backButton, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(percentageTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 53, Short.MAX_VALUE)
                                    .addComponent(bookletAdmissionButton, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))))))
                .addContainerGap())
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {backButton, clearButton});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(titlePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(admissionYearComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel5))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(programTypeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel6)))
                        .addGap(6, 6, 6)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(seatNoTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1)))
                    .addComponent(backButton)
                    .addComponent(clearButton)
                    .addComponent(bookletAdmissionButton, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9)
                    .addComponent(testScoreTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12)
                    .addComponent(percentageTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(fathersNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel14)
                            .addComponent(districtTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(surnameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(15, Short.MAX_VALUE))
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {districtTextField, fathersNameTextField, nameTextField, percentageTextField, programTypeComboBox, seatNoTextField, surnameTextField, testScoreTextField});

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {backButton, bookletAdmissionButton, clearButton});

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void clearButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearButtonActionPerformed
        clear(true);
    }//GEN-LAST:event_clearButtonActionPerformed

    private void backButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backButtonActionPerformed
        // TODO add your handling code here:
        this.setVisible(false);
    }//GEN-LAST:event_backButtonActionPerformed

    private void seatNoTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_seatNoTextFieldActionPerformed
        // TODO add your handling code here:
        clear(false);
        admission.utils.Utility.removeTableRows(detailTableModel);
        AdmissionYear ay = (AdmissionYear) this.admissionYearComboBox.getSelectedItem();
        ProgramType pt = (ProgramType) this.programTypeComboBox.getSelectedItem();
        if (ay == null || pt == null) {
            return;
        }

        String seatNo = this.seatNoTextField.getText();
        if (seatNo.isEmpty()) {
            return;
        }

        Candidate candidate = DatabaseManager.getSingleRecord(Candidate.class,
                "admissionYear.admissionYearId = " + ay.getAdmissionYearId()
                + " AND programType.programTypeId = " + pt.getProgramTypeId()
                + " AND seatNo = " + seatNo);
        //getCandidate(ay.getAdmissionYearId(), pt.getProgramTypeId(), seatNo);
//        Candidate candidate = DatabaseManager.getCandidate(ay.getAdmissionYearId(), pt.getProgramTypeId(), seatNo);
        if (candidate == null) {
            admission.utils.MessageBox.info(this, "Candidate not found");
            return;
        }
        nameTextField.setText(candidate.getName());
        fathersNameTextField.setText(candidate.getFathersName());
        surnameTextField.setText(candidate.getSurname());
        districtTextField.setText(candidate.getDistrict().getName());
        testScoreTextField.setText(String.valueOf(candidate.getTestScore()));
        percentageTextField.setText(NumberFormatter.format(candidate.getPercentage()));

        List<AdmissionListDetails> aldList = DatabaseManager.getData(AdmissionListDetails.class, "candidate.candidateId = " + candidate.getCandidateId(), "admissionListDetailsId");
        if (aldList.isEmpty()) {
            admission.utils.MessageBox.info(this, "No Selection");
            return;
        }

        int i = 1;
        for (AdmissionListDetails ald : aldList) {
            DatabaseManager.refresh(ald);

            CposGroup cposg = ald.getCposGroup();
//            Integer pCode = cposGroup.getCampusProgramOfStudy().getProgramOfStudy().getPCode();
            Shift shift = cposg.getCampusProgramOfStudy().getShift(); //ald.getAdmissionList().getAdmissionSession().getShift();

            detailTableModel.addRow(new Object[]{
                (ald.getActive()),
                i++,
                RollNoFormatter.format(ay.getYear(), cposg.getCode(), ald.getRollNo()),
                ald.getCampusCategory().toString(), // CampusCategory toString 
                shift.getName(),
                ald, //cposGroup.toString() + " (" + pCode + ")", 
                ald.getCampusCategory().getCampus().toString(),
                ald.getCandidateProgramOfStudy().getChoiceNo(),
                ald.getAdmissionList().toString(), //                candidate.getTestScore(),//ald.getSscPer(),
            //                ald.getHscPer(),
            //                ald.getGraduationPer(),
            //                ald.getPreTestScore(),
            //                ald.getScore()
            //                candidate.getPercentage()
            });
        }
    }//GEN-LAST:event_seatNoTextFieldActionPerformed

    private void bookletAdmissionButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bookletAdmissionButtonActionPerformed
        // TODO add your handling code here:
        bookletAdmissionInternalFrame.setVisible(true);
    }//GEN-LAST:event_bookletAdmissionButtonActionPerformed

    private void getAdmissionYear() {
//        this.admissionYearComboBox.removeAllItems();
//        
//        List<AdmissionYear> list = DatabaseManager.getData(AdmissionYear.class.getName(), "year DESC");
//        for (AdmissionYear list1 : list) {
//            this.admissionYearComboBox.addItem(list1);
//        }
        Object ob = admissionYearComboBox.getSelectedItem();
        this.admissionYearComboBox.removeAllItems();

        List<AdmissionYear> list = EntityHelper.getEntities(AdmissionYear.class);
        if (list.isEmpty()) {
            list = DatabaseManager.getData(AdmissionYear.class.getName(), "year DESC");
        }

        for (AdmissionYear list1 : list) {
            this.admissionYearComboBox.addItem(list1);
        }
        if (ob != null) {
            admissionYearComboBox.setSelectedItem(ob);
        }
    }

    private void getProgramType() {
//        this.programTypeComboBox.removeAllItems();
//        
//        List<ProgramType> list = DatabaseManager.getData(ProgramType.class.getName(), "name");
//        for (ProgramType list1 : list) {
//            this.programTypeComboBox.addItem(list1);
//        }
        Object ob = programTypeComboBox.getSelectedItem();
        this.programTypeComboBox.removeAllItems();

        List<ProgramType> list = EntityHelper.getEntities(ProgramType.class);
        if (list.isEmpty()) {
            list = DatabaseManager.getData(ProgramType.class, "isBachelor DESC");
        }

        for (ProgramType list1 : list) {
            this.programTypeComboBox.addItem(list1);
        }
        if (ob != null) {
            programTypeComboBox.setSelectedItem(ob);
        }
    }

    private void clear(boolean b) {
        if (b) {
            seatNoTextField.setText("");
        }
        nameTextField.setText("");
        fathersNameTextField.setText("");
        surnameTextField.setText("");
        testScoreTextField.setText("");
        percentageTextField.setText("");
        admission.utils.Utility.removeTableRows(detailTableModel);
        this.seatNoTextField.requestFocus();
    }

    private void updateCell(TableModelEvent evt) {
        int row = detailTable.getSelectedRow();
        if (row < 0) {
            return;
        }

        TableModelListener listener = detailTableModel.getTableModelListeners()[0];
        detailTableModel.removeTableModelListener(listener);

        for (int i = 0; i < detailTableModel.getRowCount(); i++) {
            AdmissionListDetails ald = (AdmissionListDetails) detailTableModel.getValueAt(i, 5);
            ald.setActive((false));

            if (row == i) {
                boolean active = (boolean) detailTableModel.getValueAt(i, 0);
                ald.setActive((active));
            } else {
                detailTableModel.setValueAt(false, i, 0);
            }
            DatabaseManager.updateData(ald);
            DatabaseManager.refresh(ald.getCandidate());
        }

        detailTableModel.addTableModelListener(listener);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox admissionYearComboBox;
    private javax.swing.JButton backButton;
    private javax.swing.JButton bookletAdmissionButton;
    private javax.swing.JButton clearButton;
    private javax.swing.JTable detailTable;
    private javax.swing.JTextField districtTextField;
    private javax.swing.JTextField fathersNameTextField;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField nameTextField;
    private javax.swing.JTextField percentageTextField;
    private javax.swing.JComboBox programTypeComboBox;
    private javax.swing.JTextField seatNoTextField;
    private javax.swing.JTextField surnameTextField;
    private javax.swing.JTextField testScoreTextField;
    private javax.swing.JPanel titlePanel;
    // End of variables declaration//GEN-END:variables
    private DocumentFilter documentFilter;
    private DocumentFilter numberDocumentFilter;
    private Resources privileges;
    private DefaultTableModel detailTableModel;
    private BookletAdmissionInternalFrame bookletAdmissionInternalFrame;
}
