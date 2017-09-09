package admission.view.seats;

import admission.controller.DatabaseManager;
import admission.controller.JDBCDatabaseManager;
import admission.model.AdmissionYear;
import admission.model.Campus;
import admission.model.CampusCategory;
import admission.model.CampusProgramOfStudy;
import admission.model.CposGroup;
import admission.model.ProgramType;
import admission.model.Shift;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.print.PrinterException;
import java.sql.SQLException;
import java.text.MessageFormat;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.KeyStroke;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author Raja Kumar & Jay Kumar
 */
public class DefineSeatDistributionDialog extends javax.swing.JDialog {

    public DefineSeatDistributionDialog(JInternalFrame parent) {
        super(JOptionPane.getFrameForComponent(parent), true);
        initComponents();
        
        defaultTableModel = (javax.swing.table.DefaultTableModel) facultyTable.getModel();
        
        admission.utils.Utility.hideOnEscape(this);
        
        this.setLocationRelativeTo(null);
    }

    public void setVisible(boolean b, AdmissionYear admissionYear, CposGroup cposg) {
        if(b) {
            this.admissionYear = admissionYear;
            this.cposg = cposg;
            this.getResult();
        }
        super.setVisible(b);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        facultyTable = new javax.swing.JTable();
        updateButton = new javax.swing.JButton();
        titlePanel = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        programLabel = new javax.swing.JLabel();
        activeCheckBox = new javax.swing.JCheckBox();
        buttonPrint = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Category Display Order");

        facultyTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Category", "Total", "Filled", "Vacant"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        facultyTable.setColumnSelectionAllowed(true);
        facultyTable.setRowHeight(20);
        facultyTable.getTableHeader().setReorderingAllowed(false);
        facultyTable.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                facultyTableKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(facultyTable);
        facultyTable.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        if (facultyTable.getColumnModel().getColumnCount() > 0) {
            facultyTable.getColumnModel().getColumn(0).setResizable(false);
            facultyTable.getColumnModel().getColumn(0).setPreferredWidth(270);
            facultyTable.getColumnModel().getColumn(1).setResizable(false);
            facultyTable.getColumnModel().getColumn(1).setPreferredWidth(30);
            facultyTable.getColumnModel().getColumn(2).setResizable(false);
            facultyTable.getColumnModel().getColumn(2).setPreferredWidth(30);
            facultyTable.getColumnModel().getColumn(3).setResizable(false);
            facultyTable.getColumnModel().getColumn(3).setPreferredWidth(30);
        }

        updateButton.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        updateButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/admission/view/images/Back.png"))); // NOI18N
        updateButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateButtonActionPerformed(evt);
            }
        });

        titlePanel.setLayout(new java.awt.BorderLayout());

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/admission/view/images/form-icon.png"))); // NOI18N
        jLabel3.setText("Program Seat Distribution");
        jLabel3.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, java.awt.Color.darkGray), javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5)));
        titlePanel.add(jLabel3, java.awt.BorderLayout.CENTER);

        programLabel.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        programLabel.setText("Program title");

        activeCheckBox.setSelected(true);
        activeCheckBox.setText("Active");

        buttonPrint.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        buttonPrint.setText("PRINT");
        buttonPrint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonPrintActionPerformed(evt);
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
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 472, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(buttonPrint, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(activeCheckBox)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(updateButton))
                    .addComponent(programLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(titlePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(programLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 471, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(updateButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(activeCheckBox, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(buttonPrint, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void facultyTableKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_facultyTableKeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode() == 107){ // +
            int index = this.facultyTable.getSelectedRow();
            if(index > -1 && index < this.facultyTable.getRowCount() - 1){
                Object ob1 = this.defaultTableModel.getValueAt(index, 1);
                Object ob2 = this.defaultTableModel.getValueAt(index + 1, 1);
                this.defaultTableModel.setValueAt(ob2, index, 1);
                this.defaultTableModel.setValueAt(ob1, index + 1, 1);
                this.facultyTable.setRowSelectionInterval(index + 1, index + 1);
            }
        }else if(evt.getKeyCode() == 109){ // -
            int index = this.facultyTable.getSelectedRow();
            if(index > 0){
                Object ob1 = this.defaultTableModel.getValueAt(index, 1);
                Object ob2 = this.defaultTableModel.getValueAt(index - 1, 1);
                this.defaultTableModel.setValueAt(ob2, index, 1);
                this.defaultTableModel.setValueAt(ob1, index - 1, 1);
                this.facultyTable.setRowSelectionInterval(index - 1, index - 1);
            }
        }
    }//GEN-LAST:event_facultyTableKeyPressed

    private void updateButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateButtonActionPerformed
        // TODO add your handling code here:
        admission.utils.Utility.removeTableRows(defaultTableModel);
        this.admissionYear = null;
        this.cposg = null;
        setVisible(false);
    }//GEN-LAST:event_updateButtonActionPerformed

    private void buttonPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonPrintActionPerformed
        try {
            MessageFormat msg = new MessageFormat(""+programLabel.getText());
            facultyTable.print(javax.swing.JTable.PrintMode.NORMAL, msg,null);
        }catch(Exception ex){
            JOptionPane.showMessageDialog(this, ex);
            ex.printStackTrace();
        }
    }//GEN-LAST:event_buttonPrintActionPerformed

    private void getResult(){
        programLabel.setText(cposg.toString());
        new Thread(){
            @Override public void run() {
                admission.utils.Utility.removeTableRows(defaultTableModel);
        
                CampusProgramOfStudy cpos = cposg.getCampusProgramOfStudy();
                Campus campus = cpos.getCampus();
                ProgramType pt = cpos.getProgramOfStudy().getProgram().getProgramType();
                Shift shift = cpos.getShift();
                
                int total = 0;
                int consumed = 0;
                List<CampusCategory> ccList = DatabaseManager.getCampusCategory(campus.getCampusId(), pt.getProgramTypeId(), shift.getShiftId(), "displayOrder");
                for (int i = 0; i < ccList.size(); i++) {
                    CampusCategory cc = ccList.get(i);
                    try {
                        int t = JDBCDatabaseManager.getCposgSeats(admissionYear.getAdmissionYearId(), cposg.getCposGroupId(), cc.getCampusCategoryId());
                        int c = JDBCDatabaseManager.getCountCandidatesOfCposGroup(admissionYear.getAdmissionYearId(), cposg.getCposGroupId(), cc.getCampusCategoryId(), activeCheckBox.isSelected());
                        defaultTableModel.addRow(new Object[]{cc, t, c, (t - c)});
                        total += t;
                        consumed += c;
                    } catch (SQLException ex) {
                        Logger.getLogger(DefineSeatDistributionDialog.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

                defaultTableModel.addRow(new Object[]{"<html><b>Total</b></html>", "<html><b>"+total+"</b></html>", "<html><b>"+consumed+"</b></html>", "<html><b>"+(total - consumed)+"</b></html>"});
//                int count = defaultTableModel.getRowCount();
//                DefaultTableCellRenderer cellRend = new DefaultTableCellRenderer();
//                cellRend.setHorizontalAlignment(javax.swing.JLabel.RIGHT);
//                facultyTable.getColumnModel().getColumn(0).setCellRenderer(cellRend);
            }
        }.start();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox activeCheckBox;
    private javax.swing.JButton buttonPrint;
    private javax.swing.JTable facultyTable;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel programLabel;
    private javax.swing.JPanel titlePanel;
    private javax.swing.JButton updateButton;
    // End of variables declaration//GEN-END:variables
    private javax.swing.table.DefaultTableModel defaultTableModel;
    private AdmissionYear admissionYear;
    private CposGroup cposg;
}
