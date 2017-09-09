package admission.view.maintain;

import admission.controller.DatabaseManager;
import admission.enums.DurationEnum;
import admission.enums.MessageEnum;
import admission.enums.SemesterEnum;
import admission.model.Discipline;
import admission.model.Faculty;
import admission.model.security.Resources;
import admission.model.Program;
import admission.model.ProgramOfStudy;
import com.yog.component.OperationButtons;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.text.AbstractDocument;
import javax.swing.text.DocumentFilter;
import org.hibernate.HibernateException;
import admission.utils.MessageBox;
import admission.utils.ModificationManager;
import admission.utils.RoundedBorder;

/**
 *
 * @author Raja Kumar & Jay Kumar
 */
public class ProgramOfStudyInternalFrame extends javax.swing.JInternalFrame {

    public ProgramOfStudyInternalFrame() {
        initComponents();
        
        admission.utils.Utility.hideOnEscape(this);
        admission.utils.Utility.comboBoxScroll(this.disciplineComboBox);
        admission.utils.Utility.loadEnum(durationComboBox, DurationEnum.class);
        admission.utils.Utility.loadEnum(semesterComboBox, SemesterEnum.class);
        
        this.titlePanel.setBorder(RoundedBorder.createGradientBorder());
        this.buttonsPanel.add(this.getOperationButtons());

        this.modify = new ModificationManager();
        ((AbstractDocument) this.nameTextField.getDocument()).addDocumentListener(modify);
        ((AbstractDocument) this.codeTextField.getDocument()).addDocumentListener(modify);
        ((AbstractDocument) this.pCodeTextField.getDocument()).addDocumentListener(modify);
        this.isQuotaOrientedCheckBox.addActionListener(modify);
        this.durationComboBox.addActionListener(modify);
        this.semesterComboBox.addActionListener(modify);
        ((AbstractDocument) this.remarksTextArea.getDocument()).addDocumentListener(modify);
    
        documentFilter = new admission.utils.UppercaseDocumentFilter();
        numberDocumentFilter = new admission.utils.NumberDocumentFilter();
        ((AbstractDocument) this.nameTextField.getDocument()).setDocumentFilter(documentFilter);
        ((AbstractDocument) this.codeTextField.getDocument()).setDocumentFilter(documentFilter);
        ((AbstractDocument) this.pCodeTextField.getDocument()).setDocumentFilter(numberDocumentFilter);
        ((AbstractDocument) this.remarksTextArea.getDocument()).setDocumentFilter(documentFilter);
    }
    
    @Override
    public void setVisible(boolean aFlag) {
        if (aFlag) {
            if (modify != null) {
                this.modify.setModifyLabel(operationButtons.getModifyLabel());
            }
            this.getFaculty();
            this.getProgram();
        } else {
            if (modify != null && modify.isModify()) {
                int v = MessageBox.confirm3(this, MessageEnum.MSG_SAVE_QUESTION);
                if (v == JOptionPane.YES_OPTION) {
                    save();
                } else if (v == JOptionPane.CANCEL_OPTION) {
                    return;
                }
            }
        }
        super.setVisible(aFlag);
    }
    
    public void setPrivileges(Resources privileges){
        this.privileges = privileges;
//        this.addButton.setEnabled((privileges.isAddPrivilige()));
//        this.updateButton.setEnabled((privileges.isUpdatePrivilige()));
//        this.deleteButton.setEnabled((privileges.isDeletePrivilige()));
    }
    
    private JPanel getOperationButtons() {
        operationButtons = new OperationButtons() {
            @Override public void saveOperation(ActionEvent evt) {
                ProgramOfStudyInternalFrame.this.save();
            }
            @Override public void deleteOperation(ActionEvent evt) {
                ProgramOfStudyInternalFrame.this.delete();
            }
            @Override public void newOperation(ActionEvent evt) {
                ProgramOfStudyInternalFrame.this.clear();
            }
            @Override public void backOperation(ActionEvent evt) {
                ProgramOfStudyInternalFrame.this.setVisible(false);
            }
        };
        operationButtons.setSize(335, 60);
        return operationButtons;
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        programOfStudyIdTextField = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        nameTextField = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        remarksTextArea = new javax.swing.JTextArea();
        jLabel5 = new javax.swing.JLabel();
        facultyComboBox = new javax.swing.JComboBox();
        jLabel11 = new javax.swing.JLabel();
        disciplineComboBox = new javax.swing.JComboBox();
        jLabel9 = new javax.swing.JLabel();
        durationComboBox = new javax.swing.JComboBox();
        jLabel12 = new javax.swing.JLabel();
        semesterComboBox = new javax.swing.JComboBox();
        programComboBox = new javax.swing.JComboBox();
        jLabel16 = new javax.swing.JLabel();
        programTypeTextField = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        programOfStudyList = new javax.swing.JList();
        jLabel6 = new javax.swing.JLabel();
        pCodeTextField = new javax.swing.JTextField();
        isQuotaOrientedCheckBox = new javax.swing.JCheckBox();
        jLabel8 = new javax.swing.JLabel();
        codeTextField = new javax.swing.JTextField();
        searchJXSearchField = new org.jdesktop.swingx.JXSearchField();
        titlePanel = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        buttonsPanel = new javax.swing.JPanel();

        setClosable(true);
        setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);
        setIconifiable(true);
        setResizable(true);
        setTitle("Program Of Study");

        jLabel2.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel2.setText("POS ID:");

        programOfStudyIdTextField.setEditable(false);
        programOfStudyIdTextField.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel3.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel3.setText("Name:");

        jLabel4.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel4.setText("Remarks");

        jScrollPane2.setViewportView(remarksTextArea);

        jLabel5.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel5.setText("Faculty:");

        facultyComboBox.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        facultyComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                facultyComboBoxActionPerformed(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel11.setText("Type:");

        disciplineComboBox.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        disciplineComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                disciplineComboBoxActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel9.setText("Duration:");

        durationComboBox.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        durationComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1 Year", "2 Year", "3 Year", "4 Year", "5 Year" }));

        jLabel12.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel12.setText("Semester:");

        semesterComboBox.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        semesterComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "2 Semester", "4 Semester", "6 Semester", "8 Semester", "10 Semester" }));

        programComboBox.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        programComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                programComboBoxActionPerformed(evt);
            }
        });

        jLabel16.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel16.setText("Program:");

        programTypeTextField.setEditable(false);
        programTypeTextField.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel7.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel7.setText("Discipline:");

        programOfStudyList.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                programOfStudyListValueChanged(evt);
            }
        });
        jScrollPane1.setViewportView(programOfStudyList);

        jLabel6.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel6.setText("PCode:");

        pCodeTextField.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        pCodeTextField.setText("0");

        isQuotaOrientedCheckBox.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        isQuotaOrientedCheckBox.setText("Is Quota Oriented:");

        jLabel8.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel8.setText("Code:");

        searchJXSearchField.setInstantSearchDelay(0);
        searchJXSearchField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchJXSearchFieldActionPerformed(evt);
            }
        });

        titlePanel.setLayout(new java.awt.BorderLayout());

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/admission/view/images/form-icon.png"))); // NOI18N
        jLabel10.setText("Program Of Study");
        jLabel10.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, java.awt.Color.darkGray), javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5)));
        titlePanel.add(jLabel10, java.awt.BorderLayout.PAGE_END);

        buttonsPanel.setLayout(new java.awt.BorderLayout());

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(titlePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(buttonsPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(facultyComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel7)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(disciplineComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 472, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(10, 10, 10)
                                    .addComponent(programComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(10, 10, 10)
                                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(4, 4, 4)
                                    .addComponent(programTypeTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(10, 10, 10)
                                    .addComponent(programOfStudyIdTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(126, 126, 126)
                                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(4, 4, 4)
                                    .addComponent(codeTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(27, 27, 27)
                                    .addComponent(jLabel6)
                                    .addGap(10, 10, 10)
                                    .addComponent(pCodeTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(10, 10, 10)
                                    .addComponent(nameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 472, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(10, 10, 10)
                                    .addComponent(durationComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(10, 10, 10)
                                    .addComponent(jLabel12)
                                    .addGap(10, 10, 10)
                                    .addComponent(semesterComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(6, 6, 6)
                                    .addComponent(isQuotaOrientedCheckBox))
                                .addComponent(jLabel4)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 540, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1)
                            .addComponent(searchJXSearchField, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(titlePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addComponent(buttonsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(facultyComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(searchJXSearchField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(4, 4, 4)
                                .addComponent(jLabel7))
                            .addComponent(disciplineComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(6, 6, 6)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(4, 4, 4)
                                .addComponent(jLabel16))
                            .addComponent(programComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(4, 4, 4)
                                .addComponent(jLabel11))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(1, 1, 1)
                                .addComponent(programTypeTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(6, 6, 6)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(programOfStudyIdTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(codeTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(pCodeTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(3, 3, 3)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel8)
                                    .addComponent(jLabel6))))
                        .addGap(6, 6, 6)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(3, 3, 3)
                                .addComponent(jLabel3))
                            .addComponent(nameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(6, 6, 6)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(durationComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(semesterComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(isQuotaOrientedCheckBox)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(4, 4, 4)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel9)
                                    .addComponent(jLabel12))))
                        .addGap(5, 5, 5)
                        .addComponent(jLabel4)
                        .addGap(6, 6, 6)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1))
                .addContainerGap(19, Short.MAX_VALUE))
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {codeTextField, disciplineComboBox, durationComboBox, facultyComboBox, nameTextField, pCodeTextField, programComboBox, programOfStudyIdTextField, programTypeTextField, semesterComboBox});

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void facultyComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_facultyComboBoxActionPerformed
        this.getDiscipline();
    }//GEN-LAST:event_facultyComboBoxActionPerformed

    private void programComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_programComboBoxActionPerformed
        // TODO add your handling code here:
        Program program = (Program) this.programComboBox.getSelectedItem();
        if(program == null) return;
        
        this.programTypeTextField.setText(program.getProgramType().getName());
    }//GEN-LAST:event_programComboBoxActionPerformed

    private void disciplineComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_disciplineComboBoxActionPerformed
        // TODO add your handling code here:
        this.getProgramOfStudy();
    }//GEN-LAST:event_disciplineComboBoxActionPerformed

    private void programOfStudyListValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_programOfStudyListValueChanged
        // TODO add your handling code here:
        ProgramOfStudy programOfStudy = (ProgramOfStudy) this.programOfStudyList.getSelectedValue();
        if(programOfStudy == null) return;
        
        this.programComboBox.setSelectedItem(programOfStudy.getProgram());
        this.programOfStudyIdTextField.setText(String.valueOf(programOfStudy.getProgramOfStudyId()));
        this.nameTextField.setText(programOfStudy.getName());
        this.pCodeTextField.setText(String.valueOf(programOfStudy.getPCode()));
        this.codeTextField.setText(programOfStudy.getCode());
        this.isQuotaOrientedCheckBox.setSelected((programOfStudy.getIsQuotaOriented()));
        this.durationComboBox.setSelectedItem(programOfStudy.getDuration());
        this.semesterComboBox.setSelectedItem(programOfStudy.getSemester());
        this.remarksTextArea.setText(programOfStudy.getRemarks());
        modify.setModify(false);
    }//GEN-LAST:event_programOfStudyListValueChanged

    private void searchJXSearchFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchJXSearchFieldActionPerformed
        // TODO add your handling code here:
        String text = searchJXSearchField.getText();
        admission.utils.Utility.filterDataList(programOfStudyList, posDataList, text);
    }//GEN-LAST:event_searchJXSearchFieldActionPerformed
    
    private void getFaculty(){
        facultyComboBox.removeAllItems();
        
        List<Faculty> list = DatabaseManager.getData(Faculty.class.getName(), "name");
        
        for (Faculty list1 : list) {
            this.facultyComboBox.addItem(list1);
        }
    }

    private void getDiscipline(){
        disciplineComboBox.removeAllItems();
        
        Faculty faculty = (Faculty) this.facultyComboBox.getSelectedItem();
        if(faculty == null) return;
        
        List<Discipline> list = DatabaseManager.getFacultyDiscipline(faculty.getFacultyId());
        
        for (Discipline list1 : list) {
            disciplineComboBox.addItem(list1);
        }
    }
    
    private void getProgram(){
        this.programComboBox.removeAllItems();
        
        List<Program> list = DatabaseManager.getData(Program.class.getName(), "name");
        
        for (Program list1 : list) {
            this.programComboBox.addItem(list1);
        }
    }
    
    private void getProgramOfStudy(){
        posDataList.clear();
        programOfStudyList.setListData(posDataList.toArray());
        this.nameTextField.setText("");
        
        Discipline discipline = (Discipline) this.disciplineComboBox.getSelectedItem();
        if(discipline == null) return;
        
        this.nameTextField.setText(discipline.getName());
        modify.setModify(false);
        
        posDataList.addAll(discipline.getProgramOfStudies());
        programOfStudyList.setListData(posDataList.toArray());
    }
    
    private void save() {
        String name = this.nameTextField.getText();
        String code = this.codeTextField.getText();
        String pCode = this.pCodeTextField.getText();
        boolean isQuotaOriented = (this.isQuotaOrientedCheckBox.isSelected());
        DurationEnum duration = (DurationEnum) this.durationComboBox.getSelectedItem();
        SemesterEnum semester = (SemesterEnum) this.semesterComboBox.getSelectedItem();
        String remarks = this.remarksTextArea.getText();
        if(name.isEmpty()) return;
        
        Discipline discipline = (Discipline) this.disciplineComboBox.getSelectedItem();
        Program program = (Program) this.programComboBox.getSelectedItem();
        ProgramOfStudy programOfStudy = (ProgramOfStudy) programOfStudyList.getSelectedValue();
        if(program == null || discipline == null) return;
        if(programOfStudy == null) {
            programOfStudy = new ProgramOfStudy();
        }
        
        programOfStudy.setDiscipline(discipline);
        programOfStudy.setProgram(program);
        programOfStudy.setName(name);
        programOfStudy.setCode(code);
        programOfStudy.setPCode(Integer.parseInt(pCode));
        programOfStudy.setIsQuotaOriented(isQuotaOriented);
        programOfStudy.setDuration(duration);
        programOfStudy.setSemester(semester);
        programOfStudy.setRemarks(remarks);
        
        try{
            DatabaseManager.save(programOfStudy);
            
            admission.utils.MessageBox.info(this, MessageEnum.MSG_SAVE);
            DatabaseManager.refresh(discipline);
            DatabaseManager.refresh(program);
            this.getProgramOfStudy();
            clear();
        }catch(HibernateException he){
            admission.utils.MessageBox.error(this, he);
        }
    }
    
    private void delete() {
        Discipline discipline = (Discipline) this.disciplineComboBox.getSelectedItem();
        Program program = (Program) this.programComboBox.getSelectedItem();
        ProgramOfStudy programOfStudy = (ProgramOfStudy) programOfStudyList.getSelectedValue();
        if(program == null || discipline == null || programOfStudy == null) return;
        
        try{
            DatabaseManager.deleteData(ProgramOfStudy.class.getName(), "programOfStudyId="+programOfStudy.getProgramOfStudyId());
            
            admission.utils.MessageBox.info(this, MessageEnum.MSG_DELETE);
            DatabaseManager.refresh(discipline);
            DatabaseManager.refresh(program);
            this.getProgramOfStudy();
            clear();
        }catch(HibernateException he){
            admission.utils.MessageBox.error(this, he);
        }
    }
    
//    private void setProgramOfStudyName(){
//        this.programOfStudyTextField.setText("");
//        
//        Discipline discipline = (Discipline) this.disciplineComboBox.getSelectedItem();
//        Program program = (Program) this.programComboBox.getSelectedItem();
//        if(discipline == null || program == null) return;
//
////        String group = Coder.Encoder.groupEncode(groupComboBox.getSelectedItem().toString());
//        
//        if(Coder.Decoder.booleanDecode(program.getIsDiscipline()))
//            this.programOfStudyTextField.setText(program.getName() + " (" + discipline.getName() + ")");
//        else
//            this.programOfStudyTextField.setText(program.getName());
//    }
//    
    
    private void clear(){
        this.programOfStudyIdTextField.setText("");
        this.nameTextField.setText("");
        this.isQuotaOrientedCheckBox.setSelected(false);
        this.remarksTextArea.setText("");
        this.programOfStudyList.clearSelection();
        modify.setModify(false);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel buttonsPanel;
    private javax.swing.JTextField codeTextField;
    private javax.swing.JComboBox disciplineComboBox;
    private javax.swing.JComboBox durationComboBox;
    private javax.swing.JComboBox facultyComboBox;
    private javax.swing.JCheckBox isQuotaOrientedCheckBox;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
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
    private javax.swing.JTextField nameTextField;
    private javax.swing.JTextField pCodeTextField;
    private javax.swing.JComboBox programComboBox;
    private javax.swing.JTextField programOfStudyIdTextField;
    private javax.swing.JList programOfStudyList;
    private javax.swing.JTextField programTypeTextField;
    private javax.swing.JTextArea remarksTextArea;
    private org.jdesktop.swingx.JXSearchField searchJXSearchField;
    private javax.swing.JComboBox semesterComboBox;
    private javax.swing.JPanel titlePanel;
    // End of variables declaration//GEN-END:variables
    private DocumentFilter documentFilter;
    private DocumentFilter numberDocumentFilter;
    private Resources privileges;
    private ModificationManager modify;
    private OperationButtons operationButtons;
    private List<ProgramOfStudy> posDataList = new ArrayList<>();
}
