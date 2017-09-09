package admission.view.fees;

import admission.controller.DatabaseManager;
import admission.model.fee.FeeCategory;
import java.util.List;
import javax.swing.JOptionPane;
import org.hibernate.HibernateException;
import admission.utils.MessageBox;
import admission.utils.RoundedBorder;

/**
 *
 * @author Raja Kumar & Jay Kumar
 */
public class FeeCategoryDisplayOrderDialog extends javax.swing.JDialog {

    public FeeCategoryDisplayOrderDialog(FeeCategoryInternalFrame parent) {
        super(JOptionPane.getFrameForComponent(parent), true);
        initComponents();
        
        this.titlePanel.setBorder(RoundedBorder.createGradientBorder());
        
        defaultTableModel = (javax.swing.table.DefaultTableModel) campusTable.getModel();
        
        admission.utils.Utility.hideOnEscape(this);
        
        this.setLocationRelativeTo(null);
    }

    @Override
    public void setVisible(boolean b) {
        if(b) this.getFeeCategory();
        super.setVisible(b);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        campusTable = new javax.swing.JTable();
        updateButton = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        backButton = new javax.swing.JButton();
        titlePanel = new javax.swing.JPanel();
        jLabel38 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        campusTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Order", "Fee Category"
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
        campusTable.setColumnSelectionAllowed(true);
        campusTable.setRowHeight(20);
        campusTable.getTableHeader().setReorderingAllowed(false);
        campusTable.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                campusTableKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(campusTable);
        campusTable.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        if (campusTable.getColumnModel().getColumnCount() > 0) {
            campusTable.getColumnModel().getColumn(0).setResizable(false);
            campusTable.getColumnModel().getColumn(0).setPreferredWidth(35);
            campusTable.getColumnModel().getColumn(1).setResizable(false);
            campusTable.getColumnModel().getColumn(1).setPreferredWidth(235);
        }

        updateButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Gnome-Media-Floppy-32.png"))); // NOI18N
        updateButton.setToolTipText("Save");
        updateButton.setFocusPainted(false);
        updateButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateButtonActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel2.setText("<html>Numpad '+' and '-' key use for up and down");

        backButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Gnome-Go-Home-32.png"))); // NOI18N
        backButton.setToolTipText("Back");
        backButton.setFocusPainted(false);
        backButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backButtonActionPerformed(evt);
            }
        });

        titlePanel.setLayout(new java.awt.BorderLayout());

        jLabel38.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel38.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Gnome-View-Sort-Ascending-40.png"))); // NOI18N
        jLabel38.setText("Fee Category Display Order");
        jLabel38.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, java.awt.Color.darkGray), javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5)));
        titlePanel.add(jLabel38, java.awt.BorderLayout.PAGE_END);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(updateButton, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(backButton, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
            .addComponent(titlePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jSeparator1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(titlePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 283, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(updateButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2)
                    .addComponent(backButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void campusTableKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_campusTableKeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode() == 107){ // +
            int index = this.campusTable.getSelectedRow();
            if(index > -1 && index < this.campusTable.getRowCount() - 1){
                Object ob1 = this.defaultTableModel.getValueAt(index, 1);
                Object ob2 = this.defaultTableModel.getValueAt(index + 1, 1);
                this.defaultTableModel.setValueAt(ob2, index, 1);
                this.defaultTableModel.setValueAt(ob1, index + 1, 1);
                this.campusTable.setRowSelectionInterval(index + 1, index + 1);
            }
        }else if(evt.getKeyCode() == 109){ // -
            int index = this.campusTable.getSelectedRow();
            if(index > 0){
                Object ob1 = this.defaultTableModel.getValueAt(index, 1);
                Object ob2 = this.defaultTableModel.getValueAt(index - 1, 1);
                this.defaultTableModel.setValueAt(ob2, index, 1);
                this.defaultTableModel.setValueAt(ob1, index - 1, 1);
                this.campusTable.setRowSelectionInterval(index - 1, index - 1);
            }
        }
    }//GEN-LAST:event_campusTableKeyPressed

    private void updateButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateButtonActionPerformed
        // TODO add your handling code here:
        new Thread(){
            @Override public void run() {
                updateButton.setEnabled(false);
                int rows = defaultTableModel.getRowCount();
                for (int i = rows - 1; i >= 0; i--) {
                    try{
                        Integer order = (Integer) defaultTableModel.getValueAt(i, 0);
                        FeeCategory feeCategory = (FeeCategory) defaultTableModel.getValueAt(i, 1);
                        feeCategory.setDisplayOrder(order);
                        DatabaseManager.updateData(feeCategory);
                    }catch(HibernateException he){
                        MessageBox.error(FeeCategoryDisplayOrderDialog.this, he);
                    }
                }
                updateButton.setEnabled(true);
                admission.utils.MessageBox.info(FeeCategoryDisplayOrderDialog.this, "Display oreder set successfully");
            }
        }.start();
    }//GEN-LAST:event_updateButtonActionPerformed

    private void backButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backButtonActionPerformed
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_backButtonActionPerformed

    private void getFeeCategory(){
        removeTableRows();
        List<FeeCategory> list = DatabaseManager.getData(FeeCategory.class, "displayOrder");
        
        for (int i = 0; i < list.size(); i++) {
            FeeCategory campus = list.get(i);
            this.defaultTableModel.addRow(new Object[]{i + 1, campus});
        }
    }

    private void removeTableRows(){
        int rows = this.defaultTableModel.getRowCount();
        for (int i = rows - 1; i >= 0; i--) {
            this.defaultTableModel.removeRow(i);
        }
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton backButton;
    private javax.swing.JTable campusTable;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JPanel titlePanel;
    private javax.swing.JButton updateButton;
    // End of variables declaration//GEN-END:variables
    private javax.swing.table.DefaultTableModel defaultTableModel;
}
