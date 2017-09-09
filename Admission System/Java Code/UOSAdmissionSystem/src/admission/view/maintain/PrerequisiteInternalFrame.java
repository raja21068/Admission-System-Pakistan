package admission.view.maintain;

import admission.controller.DatabaseManager;
import admission.enums.MessageEnum;
import admission.model.Discipline;
import admission.model.Faculty;
import admission.model.Prerequisite;
import admission.model.security.Resources;
import admission.model.Program;
import admission.model.ProgramOfStudy;
import admission.model.ProgramSubject;
import admission.view.admission.AdmissionListDetailsDialog2;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.SwingUtilities;
import javax.swing.text.DocumentFilter;
import org.hibernate.HibernateException;
import admission.utils.MessageBox;
import admission.utils.RoundedBorder;
import admission.utils.Sorter;

/**
 *
 * @author Raja Kumar & Jay Kumar
 */
public class PrerequisiteInternalFrame extends javax.swing.JInternalFrame {

    public PrerequisiteInternalFrame() {
        initComponents();

        admission.utils.Utility.hideOnEscape(this);
        admission.utils.Utility.comboBoxScroll(this.programComboBox);
        this.titlePanel.setBorder(RoundedBorder.createGradientBorder());

        this.tableModel = (javax.swing.table.DefaultTableModel) this.prerequisiteTable.getModel();
    }

    @Override
    public void setVisible(boolean aFlag) {
        if (aFlag) {
            this.getFaculty();
            this.getProgram();
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

        jLabel5 = new javax.swing.JLabel();
        facultyComboBox = new javax.swing.JComboBox();
        programOfStudyComboBox = new javax.swing.JComboBox();
        jLabel9 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        durationTextField = new javax.swing.JTextField();
        semesterTextField = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        disciplineComboBox = new javax.swing.JComboBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        prerequisiteTable = new javax.swing.JTable();
        jLabel10 = new javax.swing.JLabel();
        programComboBox = new javax.swing.JComboBox();
        hideButton = new javax.swing.JButton();
        saveButton = new javax.swing.JButton();
        titlePanel = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        checkAllCheckBox = new javax.swing.JCheckBox();

        setClosable(true);
        setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);
        setIconifiable(true);
        setTitle("Prerequisite");

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

        prerequisiteTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "SNo.", "Subject / Group", "%", "Flag"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Object.class, java.lang.Float.class, java.lang.Boolean.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, true, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        prerequisiteTable.setRowHeight(20);
        prerequisiteTable.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(prerequisiteTable);
        if (prerequisiteTable.getColumnModel().getColumnCount() > 0) {
            prerequisiteTable.getColumnModel().getColumn(0).setResizable(false);
            prerequisiteTable.getColumnModel().getColumn(0).setPreferredWidth(40);
            prerequisiteTable.getColumnModel().getColumn(1).setResizable(false);
            prerequisiteTable.getColumnModel().getColumn(1).setPreferredWidth(320);
            prerequisiteTable.getColumnModel().getColumn(2).setResizable(false);
            prerequisiteTable.getColumnModel().getColumn(2).setPreferredWidth(40);
            prerequisiteTable.getColumnModel().getColumn(3).setResizable(false);
            prerequisiteTable.getColumnModel().getColumn(3).setPreferredWidth(40);
        }

        jLabel10.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel10.setText("Program:");

        programComboBox.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        programComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                programComboBoxActionPerformed(evt);
            }
        });

        hideButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Gnome-Go-Home-32.png"))); // NOI18N
        hideButton.setToolTipText("Back");
        hideButton.setFocusPainted(false);
        hideButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hideButtonActionPerformed(evt);
            }
        });

        saveButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Gnome-Media-Floppy-32.png"))); // NOI18N
        saveButton.setToolTipText("Back");
        saveButton.setFocusPainted(false);
        saveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveButtonActionPerformed(evt);
            }
        });

        titlePanel.setLayout(new java.awt.BorderLayout());

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/admission/view/images/form-icon.png"))); // NOI18N
        jLabel6.setText("Prerequisite");
        jLabel6.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, java.awt.Color.darkGray), javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5)));
        titlePanel.add(jLabel6, java.awt.BorderLayout.CENTER);

        checkAllCheckBox.setText("Check All");
        checkAllCheckBox.setToolTipText("Check All");
        checkAllCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkAllCheckBoxActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(titlePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(saveButton, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(hideButton, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(10, 10, 10)
                                .addComponent(facultyComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 417, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addGap(10, 10, 10)
                                .addComponent(disciplineComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 417, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 485, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(10, 10, 10)
                                .addComponent(programOfStudyComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 417, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel9))
                                .addGap(10, 10, 10)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(programComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(durationTextField))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel12)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(semesterTextField))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(0, 0, Short.MAX_VALUE)
                                        .addComponent(checkAllCheckBox)))))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(titlePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(saveButton)
                    .addComponent(hideButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jLabel5))
                    .addComponent(facultyComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jLabel8))
                    .addComponent(disciplineComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jLabel7))
                    .addComponent(programOfStudyComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(durationTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9)
                    .addComponent(semesterTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(programComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10)
                    .addComponent(checkAllCheckBox))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void facultyComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_facultyComboBoxActionPerformed
        this.getDiscipline();
    }//GEN-LAST:event_facultyComboBoxActionPerformed

    private void programOfStudyComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_programOfStudyComboBoxActionPerformed
        // TODO add your handling code here:
        ProgramOfStudy pos = (ProgramOfStudy) this.programOfStudyComboBox.getSelectedItem();
        if (pos == null) {
            return;
        }

        this.durationTextField.setText(pos.getDuration().toString());
        this.semesterTextField.setText(pos.getSemester().toString());
        getPrerequisite();
    }//GEN-LAST:event_programOfStudyComboBoxActionPerformed

    private void disciplineComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_disciplineComboBoxActionPerformed
        // TODO add your handling code here:
        this.getProgramOfStudy();
    }//GEN-LAST:event_disciplineComboBoxActionPerformed

    private void programComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_programComboBoxActionPerformed
        // TODO add your handling code here:
        getProgramSubject();
    }//GEN-LAST:event_programComboBoxActionPerformed

    private void hideButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hideButtonActionPerformed
        // TODO add your handling code here:
        this.setVisible(false);
    }//GEN-LAST:event_hideButtonActionPerformed

    private void saveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveButtonActionPerformed
        // TODO add your handling code here:
        new Thread() {
            @Override
            public void run() {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override public void run() {
                        save();
                    }
                });
            }
        }.start();
    }//GEN-LAST:event_saveButtonActionPerformed

    private void checkAllCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkAllCheckBoxActionPerformed
        // TODO add your handling code here:
        clearMark(checkAllCheckBox.isSelected());
    }//GEN-LAST:event_checkAllCheckBoxActionPerformed

    private void getFaculty() {
        facultyComboBox.removeAllItems();

        List<Faculty> list = DatabaseManager.getData(Faculty.class.getName(), "name");

        for (int i = 0; i < list.size(); i++) {
            this.facultyComboBox.addItem(list.get(i));
        }
    }

    private void getDiscipline() {
        disciplineComboBox.removeAllItems();

        Faculty faculty = (Faculty) this.facultyComboBox.getSelectedItem();
        if (faculty == null) {
            return;
        }

        List<Discipline> list = DatabaseManager.getFacultyDiscipline(faculty.getFacultyId());

        for (Discipline list1 : list) {
            disciplineComboBox.addItem(list1);
        }
    }

    private void getProgramOfStudy() {
        programOfStudyComboBox.removeAllItems();
        Discipline discipline = (Discipline) this.disciplineComboBox.getSelectedItem();
        if (discipline == null) {
            return;
        }

        Set set = discipline.getProgramOfStudies();
        if (set == null) {
            return;
        }

        List list = Sorter.sort(set);

        for (Object list1 : list) {
            this.programOfStudyComboBox.addItem(list1);
        }
    }

    private void getProgram() {
        programComboBox.removeAllItems();

        List<Program> list = DatabaseManager.getData(Program.class.getName(), "name");

        for (Program list1 : list) {
            this.programComboBox.addItem(list1);
        }
    }

    private void getProgramSubject() {
        admission.utils.Utility.removeTableRows(tableModel);

        Program program = (Program) this.programComboBox.getSelectedItem();
        if (program == null) {
            return;
        }

        programSubjectList = DatabaseManager.getProgramSubject(program.getProgramId());
        Sorter.listSort(programSubjectList);

        for (int i = 0; i < programSubjectList.size(); i++) {
            tableModel.addRow(new Object[]{i + 1, programSubjectList.get(i), 45f, false});
        }

        this.getPrerequisite();
    }

    private void getPrerequisite() {
        clearMark(false);

        ProgramOfStudy pos = (ProgramOfStudy) this.programOfStudyComboBox.getSelectedItem();
        if (pos == null) {
            return;
        }
        if(programSubjectList == null){
            System.out.println(pos);
            return;
        }
        List<Prerequisite> list = DatabaseManager.getData(Prerequisite.class, "programOfStudy.programOfStudyId = " + pos.getProgramOfStudyId(), "prerequisiteId");
        for (Prerequisite pre : list) {
            int index = programSubjectList.indexOf(pre.getProgramSubject());
            if (index >= 0) {
                tableModel.setValueAt(pre.getPercentage(), index, 2);
                tableModel.setValueAt(true, index, 3);
            }
        }
//        Set set = pos.getPrerequisites();
//        if (set == null) {
//            return;
//        }
//        Iterator iterator = set.iterator();
//
//        for (Object  : set) {
//            
//        }
//        while (iterator.hasNext()) {
//            Prerequisite prerequisites = (Prerequisite) iterator.next();
//
//            int index = programSubjectList.indexOf(prerequisites.getProgramSubject());
//            if (index >= 0) {
//                tableModel.setValueAt(true, index, 3);
//            }
//        }
    }

    private void save() {
        int rows = tableModel.getRowCount();
        if (rows == 0) {
            MessageBox.error(this, "No rows");
            return;
        }
        ProgramOfStudy pos = (ProgramOfStudy) this.programOfStudyComboBox.getSelectedItem();
        if (pos == null) {
            return;
        }

        try {
            for (int i = 0; i < rows; i++) {
                ProgramSubject programSubject = (ProgramSubject) this.prerequisiteTable.getValueAt(i, 1);
                float percentage = (float) prerequisiteTable.getValueAt(i, 2);
                boolean prerequisite = (boolean) prerequisiteTable.getValueAt(i, 3);

                if (prerequisite) {
                    String hql = "SELECT pr FROM Prerequisite AS pr "
                            + "JOIN pr.programOfStudy AS pos "
                            + "JOIN pr.programSubject AS ps "
                            + "WHERE pos.programOfStudyId = " + pos.getProgramOfStudyId() + " "
                            + "AND ps.programSubjectId = " + programSubject.getProgramSubjectId();

                    List<Prerequisite> list = DatabaseManager.executeQuery(Prerequisite.class, hql);
                    Prerequisite ob = !list.isEmpty() ? list.get(0) : new Prerequisite();//DatabaseManager.getPrerequisite(pos.getProgramOfStudyId(), programSubject.getProgramSubjectId());
//                    if (list.ob == null) {
//                        ob = new Prerequisite();
//                    }
                    ob.setProgramOfStudy(pos);
                    ob.setProgramSubject(programSubject);
                    ob.setPercentage(percentage);
                    DatabaseManager.save(ob);
                } else {
                    Prerequisite ob = DatabaseManager.getPrerequisite(pos.getProgramOfStudyId(), programSubject.getProgramSubjectId());
                    if (ob != null) {
                        DatabaseManager.deleteData(Prerequisite.class.getName(), "prerequisiteId=" + ob.getPrerequisiteId());
                    }
                }
                DatabaseManager.refresh(programSubject);
            }
//            DatabaseManager.refresh(pos);
            MessageBox.info(this, MessageEnum.MSG_SAVE);
        } catch (HibernateException he) {
            Logger.getLogger(PrerequisiteInternalFrame.class.getName()).log(Level.SEVERE, null, he);
            admission.utils.MessageBox.error(this, he);
        }
    }

    private void clearMark(boolean b) {
        int rowCount = prerequisiteTable.getRowCount();

        for (int i = 0; i < rowCount; i++) {
            prerequisiteTable.setValueAt(b, i, 3);
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox checkAllCheckBox;
    private javax.swing.JComboBox disciplineComboBox;
    private javax.swing.JTextField durationTextField;
    private javax.swing.JComboBox facultyComboBox;
    private javax.swing.JButton hideButton;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable prerequisiteTable;
    private javax.swing.JComboBox programComboBox;
    private javax.swing.JComboBox programOfStudyComboBox;
    private javax.swing.JButton saveButton;
    private javax.swing.JTextField semesterTextField;
    private javax.swing.JPanel titlePanel;
    // End of variables declaration//GEN-END:variables
    private DocumentFilter documentFilter;
    private Resources privileges;
    private javax.swing.table.DefaultTableModel tableModel;
    private List<ProgramSubject> programSubjectList;
}
