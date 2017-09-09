package admission.view.admission;

import admission.controller.DatabaseManager;
import admission.enums.MessageEnum;
import admission.model.Campus;
import admission.model.CampusProgramOfStudy;
import admission.model.Candidate;
import admission.model.CandidateProgramOfStudy;
import admission.model.ProgramType;
import admission.model.Shift;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import admission.utils.IConstant;

/**
 *
 * @author Raja Kumar & Jay Kumar
 */
public class ChoicePanel extends javax.swing.JPanel {

    public ChoicePanel(ProgramType pt, Campus campus, Shift shift, Candidate candidate) {
        this.pt = pt;
        this.campus = campus;
        this.shift = shift;
        this.candidate = candidate;

        initComponents();

        defaultTableModel = (DefaultTableModel) this.studentChoiceTable.getModel();
        countChoice = 0;
        removeAllRows();
        this.init();
    }

    public final void init() {
        this.getCampusDiscipline();
        if (candidate != null) {
            this.getCandidateChoices();
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        cposComboBox = new javax.swing.JComboBox();
        addButton = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        studentChoiceTable = new javax.swing.JTable();

        jLabel1.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel1.setText("Discipline:");

        cposComboBox.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        cposComboBox.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cposComboBoxKeyPressed(evt);
            }
        });

        addButton.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        addButton.setText("Add");
        addButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addButtonActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel2.setText("Student choice list:");

        studentChoiceTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                { new Integer(1), "A"},
                { new Integer(2), "B"},
                { new Integer(3), "C"},
                { new Integer(4), "D"},
                { new Integer(5), "E"},
                { new Integer(6), "F"},
                { new Integer(7), "G"},
                { new Integer(8), "H"},
                { new Integer(9), "I"},
                { new Integer(10), "J"}
            },
            new String [] {
                "SNo.", "Program"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        studentChoiceTable.setRowHeight(20);
        studentChoiceTable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        studentChoiceTable.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                studentChoiceTableKeyPressed(evt);
            }
        });
        jScrollPane2.setViewportView(studentChoiceTable);
        if (studentChoiceTable.getColumnModel().getColumnCount() > 0) {
            studentChoiceTable.getColumnModel().getColumn(0).setResizable(false);
            studentChoiceTable.getColumnModel().getColumn(1).setResizable(false);
            studentChoiceTable.getColumnModel().getColumn(1).setPreferredWidth(500);
        }

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cposComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(addButton)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(addButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cposComboBox)
                        .addComponent(jLabel1)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 227, Short.MAX_VALUE)
                .addContainerGap())
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {addButton, cposComboBox});

    }// </editor-fold>//GEN-END:initComponents

    private void studentChoiceTableKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_studentChoiceTableKeyPressed
        if (evt.getKeyCode() == 107) { // +
            int index = this.studentChoiceTable.getSelectedRow();
            if (index > -1 && index < this.studentChoiceTable.getRowCount() - 1) {
                Object ob1 = this.defaultTableModel.getValueAt(index, 1);
                Object ob2 = this.defaultTableModel.getValueAt(index + 1, 1);
                this.defaultTableModel.setValueAt(ob2, index, 1);
                this.defaultTableModel.setValueAt(ob1, index + 1, 1);
                this.studentChoiceTable.setRowSelectionInterval(index + 1, index + 1);
            }
        } else if (evt.getKeyCode() == 109) { // -
            int index = this.studentChoiceTable.getSelectedRow();
            if (index > 0) {
                Object ob1 = this.defaultTableModel.getValueAt(index, 1);
                Object ob2 = this.defaultTableModel.getValueAt(index - 1, 1);
                this.defaultTableModel.setValueAt(ob2, index, 1);
                this.defaultTableModel.setValueAt(ob1, index - 1, 1);
                this.studentChoiceTable.setRowSelectionInterval(index - 1, index - 1);
            }
        } else if (evt.getKeyCode() == KeyEvent.VK_DELETE) { // delete
            if (JOptionPane.showConfirmDialog(this, MessageEnum.MSG_07, "Message", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
                int index = this.studentChoiceTable.getSelectedRow();
                if (index < 0) {
                    return;
                }
                Object value = this.defaultTableModel.getValueAt(index, 1);
                cposComboBox.addItem(value);
                this.defaultTableModel.removeRow(index);
                countChoice--;
                resetNo();
            }
        }
    }//GEN-LAST:event_studentChoiceTableKeyPressed

    private void addButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addButtonActionPerformed
        // TODO add your handling code here:
        Object selectedItem = this.cposComboBox.getSelectedItem();
        if (selectedItem == null) {
            return;
        }
        if (selectedItem instanceof String) {
            return;
        }

        if (countChoice == IConstant.CHOICE_LIMIT) {
            admission.utils.MessageBox.info(this, MessageEnum.MSG_08);
            return;
        }
        cposComboBox.removeItem(selectedItem);
        cposComboBox.setSelectedItem("");
        this.defaultTableModel.addRow(new Object[]{countChoice++, selectedItem});
        resetNo();
    }//GEN-LAST:event_addButtonActionPerformed

    private void cposComboBoxKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cposComboBoxKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Object selectedItem = cposComboBox.getSelectedItem();
            if (selectedItem instanceof String) {
                return;
            }
            this.defaultTableModel.addRow(new Object[]{countChoice++, selectedItem});
            resetNo();
            cposComboBox.removeItem(selectedItem);
            cposComboBox.setSelectedItem("");
        }
    }//GEN-LAST:event_cposComboBoxKeyPressed

    private void removeAllRows() {
        int rows = this.defaultTableModel.getRowCount();
        for (int i = rows - 1; i >= 0; i--) {
            this.defaultTableModel.removeRow(i);
        }
    }

    private void resetNo() {
        int rows = this.defaultTableModel.getRowCount();
        for (int i = 0; i < rows; i++) {
            this.defaultTableModel.setValueAt((i + 1) + " ", i, 0);
        }
    }

    private void getCampusDiscipline() {
        this.cposComboBox.removeAllItems();

        this.cposComboBox.addItem("");

        String hql = "SELECT cpos FROM CampusProgramOfStudy AS cpos "
                + "INNER JOIN cpos.programOfStudy AS pos "
                + "INNER JOIN pos.program AS p "
                + "INNER JOIN cpos.shift AS s "
                + "INNER JOIN cpos.campus AS ca "
                + "WHERE ca.campusId = " + campus.getCampusId() + " "
                + "AND s.shiftId = " + shift.getShiftId() + " "
                + "AND p.programType.programTypeId = " + pt.getProgramTypeId() + " "
//                + "AND pos.programOfStudyId IN (SELECT apos.programOfStudy.programOfStudyId FROM AllowedProgramOfStudy AS apos WHERE apos.program.programId = " + program.getProgramId() + ") "
                + "ORDER BY pos.name";

        List<CampusProgramOfStudy> list = DatabaseManager.executeQuery(CampusProgramOfStudy.class, hql);
        for (CampusProgramOfStudy cpos : list) {
            this.cposComboBox.addItem(cpos);
        }
    }

    private void getCandidateChoices() {
        this.removeAllRows();
        String hql = "SELECT cpos FROM CandidateProgramOfStudy AS cnpos "
                + "INNER JOIN cnpos.campusProgramOfStudy AS cpos "
                + "INNER JOIN cpos.campus AS c "
                + "INNER JOIN cpos.shift AS s "
                + "INNER JOIN cnpos.candidate AS cn "
                + "WHERE c.campusId=" + campus.getCampusId() + " "
                + "AND s.shiftId=" + shift.getShiftId() + " "
                + "AND cn.candidateId=" + candidate.getCandidateId() + " "
                + "ORDER BY cnpos.candidateProgramOfStudyId";

        List<CampusProgramOfStudy> candidateChoices = DatabaseManager.executeQuery(CampusProgramOfStudy.class, hql);

        for (CampusProgramOfStudy cpos : candidateChoices) {
            cposComboBox.removeItem(cpos);
            this.defaultTableModel.addRow(new Object[]{++countChoice, cpos});
        }
    }

    public List<CandidateProgramOfStudy> getAppliedProgramOfStudyList() {
        int count = defaultTableModel.getRowCount();
        List<CandidateProgramOfStudy> v = new ArrayList();
        for (int i = 0; i < count; i++) {
            CampusProgramOfStudy cmpos = (CampusProgramOfStudy) defaultTableModel.getValueAt(i, 1);
            //v.add((CampusProgramOfStudy)defaultTableModel.getValueAt(i, 1));
            v.add(new CandidateProgramOfStudy(null, cmpos, "", i + 1, null));
        }
        return v;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addButton;
    private javax.swing.JComboBox cposComboBox;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane2;
    protected javax.swing.JTable studentChoiceTable;
    // End of variables declaration//GEN-END:variables
    public DefaultTableModel defaultTableModel;
    private Campus campus;
    private ProgramType pt;
    private Shift shift;
    private Candidate candidate;
    private int countChoice;
}
