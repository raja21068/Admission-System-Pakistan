package admission.view.maintain;

import admission.controller.DatabaseManager;
import admission.enums.MessageEnum;
import admission.model.AllowedProgramOfStudy;
import admission.model.security.Resources;
import admission.model.Program;
import admission.model.ProgramOfStudy;
import admission.model.ProgramType;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.SwingUtilities;
import org.hibernate.HibernateException;
import admission.utils.MessageBox;
import admission.utils.RoundedBorder;
import admission.utils.Sorter;

/**
 *
 * @author Raja Kumar & Jay Kumar
 */
public class AllowedProgramOfStudyInternalFrame extends javax.swing.JInternalFrame {

    public AllowedProgramOfStudyInternalFrame() {
        initComponents();
        
        admission.utils.Utility.hideOnEscape(this);
        admission.utils.Utility.comboBoxScroll(this.programComboBox);
        this.titlePanel.setBorder(RoundedBorder.createGradientBorder());
        
        this.tableModel = (javax.swing.table.DefaultTableModel) this.disciplineTable.getModel();
    }
    
    @Override
    public void setVisible(boolean aFlag) {
        if(aFlag) {
            this.getProgramType();
        }
        super.setVisible(aFlag);
    }
    
    public void setPrivileges(Resources privileges){
        this.privileges = privileges;
//        this.addButton.setEnabled(Coder.Decoder.booleanDecode(privileges.getAddPrivilige()));
//        this.updateButton.setEnabled(Coder.Decoder.booleanDecode(privileges.getUpdatePrivilige()));
//        this.deleteButton.setEnabled(Coder.Decoder.booleanDecode(privileges.getDeletePrivilige()));
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        programComboBox = new javax.swing.JComboBox();
        jLabel16 = new javax.swing.JLabel();
        hideButton = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        yearTextField = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        disciplineTable = new javax.swing.JTable();
        titlePanel = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        saveButton = new javax.swing.JButton();
        progressBar = new javax.swing.JProgressBar();
        jLabel17 = new javax.swing.JLabel();
        programTypeComboBox = new javax.swing.JComboBox();

        setClosable(true);
        setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);
        setIconifiable(true);
        setResizable(true);
        setTitle("Program of Study for Program");

        programComboBox.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        programComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                programComboBoxActionPerformed(evt);
            }
        });

        jLabel16.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel16.setText("Program:");

        hideButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Gnome-Go-Home-32.png"))); // NOI18N
        hideButton.setToolTipText("Back");
        hideButton.setFocusPainted(false);
        hideButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hideButtonActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel3.setText("Year:");

        yearTextField.setEditable(false);
        yearTextField.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jScrollPane3.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        disciplineTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "SNo.", "Discipline", "Yes/No"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Object.class, java.lang.Boolean.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        disciplineTable.setRowHeight(20);
        disciplineTable.getTableHeader().setReorderingAllowed(false);
        jScrollPane3.setViewportView(disciplineTable);
        if (disciplineTable.getColumnModel().getColumnCount() > 0) {
            disciplineTable.getColumnModel().getColumn(0).setPreferredWidth(30);
            disciplineTable.getColumnModel().getColumn(1).setPreferredWidth(290);
            disciplineTable.getColumnModel().getColumn(2).setResizable(false);
            disciplineTable.getColumnModel().getColumn(2).setPreferredWidth(30);
        }

        titlePanel.setLayout(new java.awt.BorderLayout());

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/admission/view/images/form-icon.png"))); // NOI18N
        jLabel6.setText("Program of Study for Program");
        jLabel6.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, java.awt.Color.darkGray), javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5)));
        titlePanel.add(jLabel6, java.awt.BorderLayout.PAGE_END);

        saveButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Gnome-Media-Floppy-32.png"))); // NOI18N
        saveButton.setToolTipText("Back");
        saveButton.setFocusPainted(false);
        saveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveButtonActionPerformed(evt);
            }
        });

        progressBar.setFont(new java.awt.Font("Tahoma", 0, 8)); // NOI18N
        progressBar.setStringPainted(true);

        jLabel17.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel17.setText("Program Type:");

        programTypeComboBox.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        programTypeComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                programTypeComboBoxActionPerformed(evt);
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
                    .addComponent(jScrollPane3)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel16, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel17, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(programTypeComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(programComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(yearTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(progressBar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(saveButton, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(hideButton, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(titlePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addComponent(saveButton))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(hideButton)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel17)
                        .addComponent(programTypeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(progressBar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(programComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(yearTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 392, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void programComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_programComboBoxActionPerformed
        // TODO add your handling code here:
        Program program = (Program) this.programComboBox.getSelectedItem();
        if(program == null) return;
        
        this.yearTextField.setText(program.getPeriods() + " Year");
        this.getAllowedProgramOfStudy();
    }//GEN-LAST:event_programComboBoxActionPerformed

    private void hideButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hideButtonActionPerformed
        // TODO add your handling code here:
        this.setVisible(false);
    }//GEN-LAST:event_hideButtonActionPerformed

    private void saveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveButtonActionPerformed
        // TODO add your handling code here:
        new Thread() {
            @Override public void run() {
                SwingUtilities.invokeLater(new Runnable(){
                    @Override public void run() {
                        save();
                    }
                });
            }
        }.start();
    }//GEN-LAST:event_saveButtonActionPerformed

    private void programTypeComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_programTypeComboBoxActionPerformed
        // TODO add your handling code here:
        getProgramOfStudy();
        getProgram();
    }//GEN-LAST:event_programTypeComboBoxActionPerformed
    
    private void getProgramType(){
        this.programTypeComboBox.removeAllItems();
        
        List<ProgramType> list = DatabaseManager.getData(ProgramType.class, "isBachelor");
        
        for (ProgramType pt : list) {
            this.programTypeComboBox.addItem(pt);
        }
    }
    
    private void getProgram(){
        this.programComboBox.removeAllItems();
        clearMark();
        
        ProgramType pt = (ProgramType) programTypeComboBox.getSelectedItem();
        if(pt == null) return;
        
        List<Program> list = DatabaseManager.getData(Program.class, "name");
        
        for (Program list1 : list) {
            this.programComboBox.addItem(list1);
        }
    }
    
    private void getProgramOfStudy(){
        admission.utils.Utility.removeTableRows(tableModel);
        
        ProgramType pt = (ProgramType) programTypeComboBox.getSelectedItem();
        if(pt == null) return;
        
        String hql = "SELECT pos FROM ProgramOfStudy pos "
                + "INNER JOIN pos.program p "
                + "INNER JOIN p.programType pt "
                + "WHERE pt.programTypeId = " + pt.getProgramTypeId();
        
        posList = DatabaseManager.executeQuery(ProgramOfStudy.class, hql);
        Sorter.listSort(posList);
        
        for (int i = 0; i < posList.size(); i++) {
            this.tableModel.addRow(new Object[]{i + 1 + " ", posList.get(i), false});
        }
    }

    private void save() {
        Program p = (Program) this.programComboBox.getSelectedItem();
        if(p == null) return;
        
        int rows = tableModel.getRowCount();
        progressBar.setValue(0);
        progressBar.setMaximum(rows);
        try {
            for (int i = 0; i < rows; i++) {
                ProgramOfStudy pos = (ProgramOfStudy) this.disciplineTable.getValueAt(i, 1);
                boolean b = (boolean) disciplineTable.getValueAt(i, 2);

                AllowedProgramOfStudy apos = DatabaseManager.getSingleRecord(AllowedProgramOfStudy.class, "program.programId = " + p.getProgramId() + " AND programOfStudy.programOfStudyId = " + pos.getProgramOfStudyId());
                if (b) {
                    if (apos == null) {
                        apos = new AllowedProgramOfStudy();
                    }
                    apos.setProgram(p);
                    apos.setProgramOfStudy(pos);
                    DatabaseManager.save(apos);
                } else {
                    if (apos != null) {
                        DatabaseManager.deleteData(apos);
                    }
                }
                progressBar.setValue(i + 1);
            }
            MessageBox.info(this, MessageEnum.MSG_SAVE);
        } catch (HibernateException he) {
            Logger.getLogger(AllowedProgramOfStudyInternalFrame.class.getName()).log(Level.SEVERE, null, he);
            MessageBox.error(this, he);
        }
    }
    
    private void getAllowedProgramOfStudy(){
        clearMark();
        
        Program p = (Program) this.programComboBox.getSelectedItem();
        if(p == null) return;
        
        List<AllowedProgramOfStudy> aposList = DatabaseManager.getData(AllowedProgramOfStudy.class, "program.programId = " + p.getProgramId(), "id");
        
        for (AllowedProgramOfStudy apos : aposList) {
            int index = posList.indexOf(apos.getProgramOfStudy());
            if(index >= 0){
                tableModel.setValueAt(true, index, 2);
            }
        }
    }
    
    private void clearMark(){
        int rowCount = disciplineTable.getRowCount();
        
        for (int i = 0; i < rowCount; i++) {
            disciplineTable.setValueAt(false, i, 2);
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable disciplineTable;
    private javax.swing.JButton hideButton;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JComboBox programComboBox;
    private javax.swing.JComboBox programTypeComboBox;
    private javax.swing.JProgressBar progressBar;
    private javax.swing.JButton saveButton;
    private javax.swing.JPanel titlePanel;
    private javax.swing.JTextField yearTextField;
    // End of variables declaration//GEN-END:variables
    private javax.swing.table.DefaultTableModel tableModel;
    private Resources privileges;
    private List<ProgramOfStudy> posList;
}
