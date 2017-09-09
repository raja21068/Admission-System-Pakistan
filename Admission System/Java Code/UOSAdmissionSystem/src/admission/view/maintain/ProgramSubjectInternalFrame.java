package admission.view.maintain;

import admission.controller.DatabaseManager;
import admission.enums.MessageEnum;
import admission.model.security.Resources;
import admission.model.Program;
import admission.model.ProgramSubject;
import admission.model.Subject;
import java.util.List;
import java.util.Set;
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
public class ProgramSubjectInternalFrame extends javax.swing.JInternalFrame {

    public ProgramSubjectInternalFrame() {
        initComponents();
        
        admission.utils.Utility.hideOnEscape(this);
        admission.utils.Utility.comboBoxScroll(this.programComboBox);
        this.titlePanel.setBorder(RoundedBorder.createGradientBorder());
        
        this.tableModel = (javax.swing.table.DefaultTableModel) this.subjectTable.getModel();
    }
    
    @Override
    public void setVisible(boolean aFlag) {
        if(aFlag) {
            this.getSubject();
            this.getProgram();
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

        jLabel11 = new javax.swing.JLabel();
        programComboBox = new javax.swing.JComboBox();
        jLabel16 = new javax.swing.JLabel();
        programTypeTextField = new javax.swing.JTextField();
        hideButton = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        yearTextField = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        subjectTable = new javax.swing.JTable();
        titlePanel = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        saveButton = new javax.swing.JButton();
        progressBar = new javax.swing.JProgressBar();

        setClosable(true);
        setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);
        setIconifiable(true);
        setTitle("Program Subject");

        jLabel11.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel11.setText("Type:");

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

        jScrollPane3.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        subjectTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "SNo.", "Subject", "Flag"
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
        subjectTable.setRowHeight(20);
        subjectTable.getTableHeader().setReorderingAllowed(false);
        jScrollPane3.setViewportView(subjectTable);
        if (subjectTable.getColumnModel().getColumnCount() > 0) {
            subjectTable.getColumnModel().getColumn(0).setResizable(false);
            subjectTable.getColumnModel().getColumn(0).setPreferredWidth(30);
            subjectTable.getColumnModel().getColumn(1).setResizable(false);
            subjectTable.getColumnModel().getColumn(1).setPreferredWidth(290);
            subjectTable.getColumnModel().getColumn(2).setResizable(false);
            subjectTable.getColumnModel().getColumn(2).setPreferredWidth(30);
        }

        titlePanel.setLayout(new java.awt.BorderLayout());

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/admission/view/images/form-icon.png"))); // NOI18N
        jLabel6.setText("Program Subject");
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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(titlePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 414, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel16))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(programTypeTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(yearTextField))
                            .addComponent(programComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(progressBar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(saveButton, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(hideButton, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(titlePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(programComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel16))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11)
                            .addComponent(programTypeTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3)
                            .addComponent(yearTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(progressBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(hideButton)
                            .addComponent(saveButton))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 376, Short.MAX_VALUE)
                .addContainerGap())
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {programTypeTextField, yearTextField});

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void programComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_programComboBoxActionPerformed
        // TODO add your handling code here:
        Program program = (Program) this.programComboBox.getSelectedItem();
        if(program == null) return;
        
        this.programTypeTextField.setText(program.getProgramType().getName());
        this.yearTextField.setText(program.getPeriods() + " Year");
        this.getProgramSubject();
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
    
    private void save() {
        Program p = (Program) this.programComboBox.getSelectedItem();
        if(p == null) return;
        
        int rows = tableModel.getRowCount();
        progressBar.setValue(0);
        progressBar.setMaximum(rows);
        try {
            for (int i = 0; i < rows; i++) {
                Subject s = (Subject) this.subjectTable.getValueAt(i, 1);
                boolean b = (boolean) subjectTable.getValueAt(i, 2);

                if (b) {
                    ProgramSubject ps = DatabaseManager.getProgramSubject(p.getProgramId(), s.getSubjectId());
                    if (ps == null) {
                        ps = new ProgramSubject();
                    }
                    ps.setProgram(p);
                    ps.setSubject(s);
                    DatabaseManager.save(ps);
                } else {
                    ProgramSubject ps = DatabaseManager.getProgramSubject(p.getProgramId(), s.getSubjectId());
                    if (ps != null) {
                        DatabaseManager.deleteData(ProgramSubject.class.getName(), "programSubjectId=" + ps.getProgramSubjectId());
                    }
                }
                DatabaseManager.refresh(s);
                progressBar.setValue(i + 1);
            }
            DatabaseManager.refresh(p);
            MessageBox.info(this, MessageEnum.MSG_SAVE);
        } catch (HibernateException he) {
            he.printStackTrace();
        }
    }
    
    private void getSubject(){
        admission.utils.Utility.removeTableRows(tableModel);
        
        subjectList = DatabaseManager.getData(Subject.class.getName(), "name");
        Sorter.listSort(subjectList);
        
        for (int i = 0; i < subjectList.size(); i++) {
            this.tableModel.addRow(new Object[]{i + 1 + " ", subjectList.get(i), false});
        }
    }

    private void getProgram(){
        this.programComboBox.removeAllItems();
        
        List<Program> list = DatabaseManager.getData(Program.class.getName(), "name");
        
        for (Program list1 : list) {
            this.programComboBox.addItem(list1);
        }
    }
    
    private void getProgramSubject(){
        clearMark();
        
        Program program = (Program) this.programComboBox.getSelectedItem();
        if(program == null) return;
        
        Set set = program.getProgramSubjects();
        if(set == null) return;
        
        List list = Sorter.sort(set);
        
        for (Object ob : list) {
            ProgramSubject ps = (ProgramSubject) ob;
            int index = subjectList.indexOf(ps.getSubject());
            if(index >= 0){
                tableModel.setValueAt(true, index, 2);
            }
        }
    }
    
    private void clearMark(){
        int rowCount = subjectTable.getRowCount();
        
        for (int i = 0; i < rowCount; i++) {
            subjectTable.setValueAt(false, i, 2);
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton hideButton;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JComboBox programComboBox;
    private javax.swing.JTextField programTypeTextField;
    private javax.swing.JProgressBar progressBar;
    private javax.swing.JButton saveButton;
    private javax.swing.JTable subjectTable;
    private javax.swing.JPanel titlePanel;
    private javax.swing.JTextField yearTextField;
    // End of variables declaration//GEN-END:variables
    private DocumentFilter documentFilter;
    private javax.swing.table.DefaultTableModel tableModel;
    private Resources privileges;
    private List<Subject> subjectList;
}
