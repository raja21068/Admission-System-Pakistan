package admission.view.campus;

import admission.controller.DatabaseManager;
import admission.enums.JurisdictionEnum;
import admission.enums.MessageEnum;
import admission.model.Campus;
import admission.model.CampusProgramOfStudy;
import admission.model.Discipline;
import admission.model.Faculty;
import admission.model.security.Resources;
import admission.model.ProgramOfStudy;
import admission.model.ProgramType;
import admission.model.Shift;
import com.yog.component.OperationButtons;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
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
public class CampusProgramOfStudyInternalFrame extends javax.swing.JInternalFrame {

    public CampusProgramOfStudyInternalFrame() {
        initComponents();
        
        admission.utils.Utility.hideOnEscape(this);
        
        admission.utils.Utility.loadEnum(jurisdictionComboBox, JurisdictionEnum.class);
        
        this.titlePanel.setBorder(RoundedBorder.createGradientBorder());
        this.buttonsPanel.add(this.getOperationButtons());
        
        this.modify = new ModificationManager();
        jurisdictionComboBox.addActionListener(modify);
        isQuotaOrientedCheckBox.addActionListener(modify);
        isSeperateCheckBox.addActionListener(modify);
        ((AbstractDocument) this.remarksTextArea.getDocument()).addDocumentListener(modify);

        documentFilter = new admission.utils.UppercaseDocumentFilter();
        ((AbstractDocument) this.remarksTextArea.getDocument()).setDocumentFilter(documentFilter);
    }
    
    @Override
    public void setVisible(boolean aFlag) {
        if (aFlag) {
            if (modify != null) {
                this.modify.setModifyLabel(operationButtons.getModifyLabel());
            }
            this.clear();
            this.getFaculty();
            this.getCampus();
            this.getShift();
            this.getProgramType();
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

    public void setPrivileges(Resources privileges) {
        this.privileges = privileges;
//        this.addButton.setEnabled((privileges.isAddPrivilige()));
//        this.updateButton.setEnabled((privileges.isUpdatePrivilige()));
//        this.deleteButton.setEnabled((privileges.isDeletePrivilige()));
    }

    private JPanel getOperationButtons() {
        operationButtons = new OperationButtons() {
            @Override public void saveOperation(ActionEvent evt) {
                CampusProgramOfStudyInternalFrame.this.save();
            }
            @Override public void deleteOperation(ActionEvent evt) {
                CampusProgramOfStudyInternalFrame.this.delete();
            }
            @Override public void newOperation(ActionEvent evt) {
                CampusProgramOfStudyInternalFrame.this.clear();
            }
            @Override public void backOperation(ActionEvent evt) {
                CampusProgramOfStudyInternalFrame.this.setVisible(false);
            }
        };
//        operationButtons.setVisible(true, privileges., isSelected, closable, isIcon);
        operationButtons.setSize(335, 60);
        return operationButtons;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        campusProgramOfStudyIdTextField = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        remarksTextArea = new javax.swing.JTextArea();
        jLabel5 = new javax.swing.JLabel();
        facultyComboBox = new javax.swing.JComboBox();
        programOfStudyComboBox = new javax.swing.JComboBox();
        jLabel9 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        campusComboBox = new javax.swing.JComboBox();
        jLabel7 = new javax.swing.JLabel();
        durationTextField = new javax.swing.JTextField();
        semesterTextField = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        disciplineComboBox = new javax.swing.JComboBox();
        shiftComboBox = new javax.swing.JComboBox();
        jScrollPane3 = new javax.swing.JScrollPane();
        cposList = new javax.swing.JList();
        jLabel3 = new javax.swing.JLabel();
        programTypeComboBox = new javax.swing.JComboBox();
        jLabel6 = new javax.swing.JLabel();
        programTextField = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jurisdictionComboBox = new javax.swing.JComboBox();
        isSeperateCheckBox = new javax.swing.JCheckBox();
        isQuotaOrientedCheckBox = new javax.swing.JCheckBox();
        searchJXSearchField = new org.jdesktop.swingx.JXSearchField();
        titlePanel = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        buttonsPanel = new javax.swing.JPanel();
        jSeparator1 = new javax.swing.JSeparator();

        setClosable(true);
        setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);
        setIconifiable(true);
        setTitle("Campus Program Of Study");

        jLabel2.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel2.setText("ID:");

        campusProgramOfStudyIdTextField.setEditable(false);
        campusProgramOfStudyIdTextField.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel4.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel4.setText("Remarks");

        remarksTextArea.setColumns(20);
        jScrollPane2.setViewportView(remarksTextArea);

        jLabel5.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel5.setText("Faculty:");

        facultyComboBox.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        facultyComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                facultyComboBoxActionPerformed(evt);
            }
        });

        programOfStudyComboBox.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        programOfStudyComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                programOfStudyComboBoxActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel9.setText("Duration:");

        jLabel12.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel12.setText("Semester:");

        jLabel13.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel13.setText("Shift:");

        jLabel15.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel15.setText("Campus:");

        campusComboBox.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        campusComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                campusComboBoxActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel7.setText("POS:");

        durationTextField.setEditable(false);

        semesterTextField.setEditable(false);

        jLabel8.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel8.setText("Discipline:");

        disciplineComboBox.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        disciplineComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                disciplineComboBoxActionPerformed(evt);
            }
        });

        shiftComboBox.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        shiftComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                shiftComboBoxActionPerformed(evt);
            }
        });

        cposList.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                cposListValueChanged(evt);
            }
        });
        jScrollPane3.setViewportView(cposList);

        jLabel3.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel3.setText("Program Type:");

        programTypeComboBox.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        programTypeComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                programTypeComboBoxActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel6.setText("Program:");

        programTextField.setEditable(false);

        jLabel10.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel10.setText("Jurisdiction:");

        jurisdictionComboBox.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jurisdictionComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Both", "Inside", "Out of" }));

        isSeperateCheckBox.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        isSeperateCheckBox.setText("Seperate pre-entry test");

        isQuotaOrientedCheckBox.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        isQuotaOrientedCheckBox.setText("Is Quota Oriented");
        isQuotaOrientedCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                isQuotaOrientedCheckBoxActionPerformed(evt);
            }
        });

        searchJXSearchField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchJXSearchFieldActionPerformed(evt);
            }
        });

        titlePanel.setLayout(new java.awt.BorderLayout());

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/admission/view/images/form-icon.png"))); // NOI18N
        jLabel11.setText("Campus Program Of Study");
        jLabel11.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, java.awt.Color.darkGray), javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5)));
        titlePanel.add(jLabel11, java.awt.BorderLayout.PAGE_END);

        buttonsPanel.setPreferredSize(new java.awt.Dimension(335, 60));
        buttonsPanel.setLayout(new java.awt.BorderLayout());

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(titlePanel, javax.swing.GroupLayout.PREFERRED_SIZE, 871, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(buttonsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 851, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(campusComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 444, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(10, 10, 10)
                                    .addComponent(shiftComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(4, 4, 4)
                                    .addComponent(jLabel3)
                                    .addGap(4, 4, 4)
                                    .addComponent(programTypeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 520, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(10, 10, 10)
                                    .addComponent(facultyComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 444, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(10, 10, 10)
                                    .addComponent(disciplineComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 444, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(10, 10, 10)
                                    .addComponent(programOfStudyComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 444, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(10, 10, 10)
                                    .addComponent(durationTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(10, 10, 10)
                                    .addComponent(jLabel6)
                                    .addGap(4, 4, 4)
                                    .addComponent(programTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jLabel12)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(semesterTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(10, 10, 10)
                                    .addComponent(campusProgramOfStudyIdTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(10, 10, 10)
                                    .addComponent(isSeperateCheckBox))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel10)
                                    .addGap(10, 10, 10)
                                    .addComponent(jurisdictionComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(10, 10, 10)
                                    .addComponent(isQuotaOrientedCheckBox))
                                .addComponent(jLabel4)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 520, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(6, 6, 6)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 325, Short.MAX_VALUE)
                            .addComponent(searchJXSearchField, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(titlePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addComponent(buttonsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(searchJXSearchField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(campusComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(shiftComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(programTypeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(4, 4, 4)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel13)
                                    .addComponent(jLabel3))))
                        .addGap(6, 6, 6)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(3, 3, 3)
                                .addComponent(jLabel5))
                            .addComponent(facultyComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(11, 11, 11)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(3, 3, 3)
                                .addComponent(jLabel8))
                            .addComponent(disciplineComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(11, 11, 11)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(3, 3, 3)
                                .addComponent(jLabel7))
                            .addComponent(programOfStudyComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(11, 11, 11)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(durationTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(programTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel12)
                                .addComponent(semesterTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(3, 3, 3)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel9)
                                    .addComponent(jLabel6))))
                        .addGap(11, 11, 11)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(3, 3, 3)
                                .addComponent(jLabel2))
                            .addComponent(campusProgramOfStudyIdTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(isSeperateCheckBox))
                        .addGap(11, 11, 11)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(4, 4, 4)
                                .addComponent(jLabel10))
                            .addComponent(jurisdictionComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(isQuotaOrientedCheckBox))
                        .addGap(10, 10, 10)
                        .addComponent(jLabel4)
                        .addGap(4, 4, 4)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane3))
                .addContainerGap(15, Short.MAX_VALUE))
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {campusComboBox, campusProgramOfStudyIdTextField, disciplineComboBox, durationTextField, facultyComboBox, jurisdictionComboBox, programOfStudyComboBox, programTextField, programTypeComboBox, semesterTextField, shiftComboBox});

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void facultyComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_facultyComboBoxActionPerformed
        this.getDiscipline();
    }//GEN-LAST:event_facultyComboBoxActionPerformed

    private void campusComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_campusComboBoxActionPerformed
        // TODO add your handling code here:
        this.getCampusProgramOfStudy();
    }//GEN-LAST:event_campusComboBoxActionPerformed

    private void programOfStudyComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_programOfStudyComboBoxActionPerformed
        // TODO add your handling code here:
        ProgramOfStudy pos = (ProgramOfStudy) this.programOfStudyComboBox.getSelectedItem();
        if(pos == null) return;
        
        this.programTextField.setText(pos.getProgram().toString());
        this.durationTextField.setText(pos.getDuration().toString());
        this.semesterTextField.setText(pos.getSemester().toString());
        this.jurisdictionComboBox.setSelectedIndex(0);
        this.jurisdictionComboBox.setEnabled((pos.getIsQuotaOriented()));
        modify.setModify(false);
    }//GEN-LAST:event_programOfStudyComboBoxActionPerformed

    private void disciplineComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_disciplineComboBoxActionPerformed
        // TODO add your handling code here:
        this.getProgramOfStudy();
    }//GEN-LAST:event_disciplineComboBoxActionPerformed

    private void cposListValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_cposListValueChanged
        // TODO add your handling code here:
        CampusProgramOfStudy cpos = (CampusProgramOfStudy) this.cposList.getSelectedValue();
        if(cpos == null) return;
        
        ProgramOfStudy pos = cpos.getProgramOfStudy();
        Discipline dis = pos.getDiscipline();
        Faculty fac = dis.getDepartment().getFaculty();
        
        this.facultyComboBox.setSelectedItem(fac);
        this.disciplineComboBox.setSelectedItem(dis);
        this.programOfStudyComboBox.setSelectedItem(pos);
        
        this.campusProgramOfStudyIdTextField.setText(String.valueOf(cpos.getCampusProgramOfStudyId()));
        this.jurisdictionComboBox.setEnabled((cpos.getIsQuotaOriented()));
        this.jurisdictionComboBox.setSelectedItem(cpos.getJurisdiction());
        this.isSeperateCheckBox.setSelected((cpos.getIsSeperate()));
        this.isQuotaOrientedCheckBox.setSelected((cpos.getIsQuotaOriented()));
        this.remarksTextArea.setText(cpos.getRemarks());
        modify.setModify(false);
    }//GEN-LAST:event_cposListValueChanged

    private void shiftComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_shiftComboBoxActionPerformed
        // TODO add your handling code here:
        this.getCampusProgramOfStudy();
    }//GEN-LAST:event_shiftComboBoxActionPerformed

    private void programTypeComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_programTypeComboBoxActionPerformed
        // TODO add your handling code here:
        this.getCampusProgramOfStudy();
    }//GEN-LAST:event_programTypeComboBoxActionPerformed

    private void isQuotaOrientedCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_isQuotaOrientedCheckBoxActionPerformed
        // TODO add your handling code here:
        this.jurisdictionComboBox.setEnabled(this.isQuotaOrientedCheckBox.isSelected());
    }//GEN-LAST:event_isQuotaOrientedCheckBoxActionPerformed

    private void searchJXSearchFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchJXSearchFieldActionPerformed
        // TODO add your handling code here:
        String text = searchJXSearchField.getText();
        admission.utils.Utility.filterDataList(cposList, cposDataList, text);
    }//GEN-LAST:event_searchJXSearchFieldActionPerformed
    
    private void getFaculty(){
        facultyComboBox.removeAllItems();
        disciplineComboBox.removeAllItems();
        
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
    
    private void getCampus(){
        this.campusComboBox.removeAllItems();
        
        List<Campus> list = DatabaseManager.getData(Campus.class.getName(), "name");
        
        for (Campus list1 : list) {
            this.campusComboBox.addItem(list1);
        }
    }
    
    private void getShift(){
        this.shiftComboBox.removeAllItems();
        
        List<Shift> list = DatabaseManager.getData(Shift.class.getName(), "isMorning DESC");
        
        for (Shift list1 : list) {
            this.shiftComboBox.addItem(list1);
        }
    }
    
    private void getProgramType(){
        this.programTypeComboBox.removeAllItems();
        
        List<ProgramType> list = DatabaseManager.getData(ProgramType.class.getName(), "isBachelor DESC");
        
        for (ProgramType list1 : list) {
            this.programTypeComboBox.addItem(list1);
        }
    }
    
    private void getProgramOfStudy(){
        programOfStudyComboBox.removeAllItems();
        Discipline discipline = (Discipline) this.disciplineComboBox.getSelectedItem();
        if(discipline == null) return;
        
        Set<ProgramOfStudy> set = discipline.getProgramOfStudies();

        for (ProgramOfStudy pos : set) {
            this.programOfStudyComboBox.addItem(pos);
        }
    }
    
    private void getCampusProgramOfStudy(){
        cposDataList.clear();
        cposList.setListData(cposDataList.toArray());
        
        Campus campus = (Campus) this.campusComboBox.getSelectedItem();
        Shift shift = (Shift) this.shiftComboBox.getSelectedItem();
        ProgramType pt = (ProgramType) this.programTypeComboBox.getSelectedItem();
        if(campus == null || shift == null || pt == null) return;
        
        String hql = "SELECT cpos FROM CampusProgramOfStudy AS cpos "
                + "INNER JOIN cpos.programOfStudy AS pos "
                + "INNER JOIN cpos.shift AS s "
                + "INNER JOIN cpos.campus AS ca "
                + "INNER JOIN pos.program AS p "
                + "INNER JOIN p.programType AS pt "
                + "WHERE ca.campusId = " + campus.getCampusId() + " "
                + "AND pt.programTypeId = " + pt.getProgramTypeId() + " "
                + "AND s.shiftId = " + shift.getShiftId() + " "
                + "ORDER BY pos.name";

//        cposDataList = DatabaseManager.getCampusProgramOfStudy(campus.getCampusId(), programType.getProgramTypeId(), shift.getShiftId());
        cposDataList = DatabaseManager.executeQuery(CampusProgramOfStudy.class, hql);
        cposList.setListData(cposDataList.toArray());
    }
    
    private void save() {
        ProgramOfStudy pos = (ProgramOfStudy) this.programOfStudyComboBox.getSelectedItem();
        Campus campus = (Campus) this.campusComboBox.getSelectedItem();
        Shift shift = (Shift) this.shiftComboBox.getSelectedItem();
        CampusProgramOfStudy cpos = (CampusProgramOfStudy) this.cposList.getSelectedValue();
        if(campus == null || pos == null || shift == null) return;
        if(cpos == null) {
            cpos = new CampusProgramOfStudy();
        }
        
        boolean isSeperate = (isSeperateCheckBox.isSelected());
        boolean isQuotaOriented = (this.isQuotaOrientedCheckBox.isSelected());
        JurisdictionEnum jurisdiction = (JurisdictionEnum) jurisdictionComboBox.getSelectedItem();
        String remarks = this.remarksTextArea.getText();
        
        cpos.setProgramOfStudy(pos);
        cpos.setCampus(campus);
        cpos.setShift(shift);
        cpos.setJurisdiction(jurisdiction);
        cpos.setIsSeperate(isSeperate);
        cpos.setIsQuotaOriented(isQuotaOriented);
        cpos.setRemarks(remarks);
        
        try{
            DatabaseManager.save(cpos);
            admission.utils.MessageBox.info(this, MessageEnum.MSG_SAVE);
            
            DatabaseManager.refresh(campus);
            DatabaseManager.refresh(shift);
            DatabaseManager.refresh(pos);
            
            this.getCampusProgramOfStudy();
            clear();
        }catch(HibernateException he){
            admission.utils.MessageBox.error(this, he);
        }
    }
    
    private void delete() {
        ProgramOfStudy pos = (ProgramOfStudy) this.programOfStudyComboBox.getSelectedItem();
        Campus campus = (Campus) this.campusComboBox.getSelectedItem();
        Shift shift = (Shift) this.shiftComboBox.getSelectedItem();
        CampusProgramOfStudy cpos = (CampusProgramOfStudy) this.cposList.getSelectedValue();
        if(campus == null || pos == null || cpos == null || shift == null) return;
        
        try{
            DatabaseManager.deleteData(CampusProgramOfStudy.class.getName(), "campusProgramOfStudyId = " + cpos.getCampusProgramOfStudyId());
            
            admission.utils.MessageBox.info(this, MessageEnum.MSG_DELETE);
            
            DatabaseManager.refresh(campus);
            DatabaseManager.refresh(shift);
            DatabaseManager.refresh(pos);
            
            this.getCampusProgramOfStudy();
            clear();
        }catch(HibernateException he){
            admission.utils.MessageBox.error(this, he);
        }
    }
    
    private void clear(){
        this.campusProgramOfStudyIdTextField.setText("");
        this.remarksTextArea.setText("");
        this.cposList.clearSelection();
        modify.setModify(false);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel buttonsPanel;
    private javax.swing.JComboBox campusComboBox;
    private javax.swing.JTextField campusProgramOfStudyIdTextField;
    private javax.swing.JList cposList;
    private javax.swing.JComboBox disciplineComboBox;
    private javax.swing.JTextField durationTextField;
    private javax.swing.JComboBox facultyComboBox;
    private javax.swing.JCheckBox isQuotaOrientedCheckBox;
    private javax.swing.JCheckBox isSeperateCheckBox;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JComboBox jurisdictionComboBox;
    private javax.swing.JComboBox programOfStudyComboBox;
    private javax.swing.JTextField programTextField;
    private javax.swing.JComboBox programTypeComboBox;
    private javax.swing.JTextArea remarksTextArea;
    private org.jdesktop.swingx.JXSearchField searchJXSearchField;
    private javax.swing.JTextField semesterTextField;
    private javax.swing.JComboBox shiftComboBox;
    private javax.swing.JPanel titlePanel;
    // End of variables declaration//GEN-END:variables
    private DocumentFilter documentFilter;
    private Resources privileges;
    private OperationButtons operationButtons;
    private List<CampusProgramOfStudy> cposDataList = new ArrayList<>();
    private ModificationManager modify;
}
