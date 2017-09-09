package admission.view.campus;

import admission.controller.DatabaseManager;
import admission.model.Campus;
import admission.model.CampusCategory;
import admission.model.ProgramType;
import admission.model.Shift;
import java.util.List;
import javax.swing.JOptionPane;
import org.hibernate.HibernateException;
import admission.utils.RoundedBorder;

/**
 *
 * @author Raja Kumar & Jay Kumar
 */
public class CampusCategoryDisplayOrderDialog extends javax.swing.JDialog {

    public CampusCategoryDisplayOrderDialog(CampusCategoryInternalFrame parent) {
        super(JOptionPane.getFrameForComponent(parent), true);
        initComponents();
        
        this.titlePanel.setBorder(RoundedBorder.createGradientBorder());
        
        defaultTableModel = (javax.swing.table.DefaultTableModel) categoryTable.getModel();
        
        admission.utils.Utility.hideOnEscape(this);
        
        this.setLocationRelativeTo(null);
    }

    public void setVisible(Campus campus, ProgramType programType, Shift shift, boolean b) {
        if(b) {
            if(campus == null || programType == null){
                admission.utils.MessageBox.info(this, "CampusCategory is null");
                return;
            }
            this.campus = campus;
            this.programType = programType;
            this.shift = shift;
            this.getCampusCategory();
        }
        super.setVisible(b);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        categoryTable = new javax.swing.JTable();
        updateButton = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        titlePanel = new javax.swing.JPanel();
        jLabel38 = new javax.swing.JLabel();
        backButton = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Category Display Order");

        categoryTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Order", "Category"
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
        categoryTable.setColumnSelectionAllowed(true);
        categoryTable.getTableHeader().setReorderingAllowed(false);
        categoryTable.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                categoryTableKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(categoryTable);
        categoryTable.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        if (categoryTable.getColumnModel().getColumnCount() > 0) {
            categoryTable.getColumnModel().getColumn(0).setResizable(false);
            categoryTable.getColumnModel().getColumn(0).setPreferredWidth(20);
            categoryTable.getColumnModel().getColumn(1).setResizable(false);
            categoryTable.getColumnModel().getColumn(1).setPreferredWidth(300);
        }

        updateButton.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
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
        jLabel2.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        titlePanel.setLayout(new java.awt.BorderLayout());

        jLabel38.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel38.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Gnome-View-Sort-Ascending-40.png"))); // NOI18N
        jLabel38.setText("Category Display Order");
        jLabel38.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, java.awt.Color.darkGray), javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5)));
        titlePanel.add(jLabel38, java.awt.BorderLayout.CENTER);

        backButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Gnome-Go-Home-32.png"))); // NOI18N
        backButton.setToolTipText("Hide Form");
        backButton.setFocusPainted(false);
        backButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 402, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(updateButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel2))
                    .addComponent(backButton))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void categoryTableKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_categoryTableKeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode() == 107){ // +
            int index = this.categoryTable.getSelectedRow();
            if(index > -1 && index < this.categoryTable.getRowCount() - 1){
                Object ob1 = this.defaultTableModel.getValueAt(index, 1);
                Object ob2 = this.defaultTableModel.getValueAt(index + 1, 1);
                this.defaultTableModel.setValueAt(ob2, index, 1);
                this.defaultTableModel.setValueAt(ob1, index + 1, 1);
                this.categoryTable.setRowSelectionInterval(index + 1, index + 1);
            }
        }else if(evt.getKeyCode() == 109){ // -
            int index = this.categoryTable.getSelectedRow();
            if(index > 0){
                Object ob1 = this.defaultTableModel.getValueAt(index, 1);
                Object ob2 = this.defaultTableModel.getValueAt(index - 1, 1);
                this.defaultTableModel.setValueAt(ob2, index, 1);
                this.defaultTableModel.setValueAt(ob1, index - 1, 1);
                this.categoryTable.setRowSelectionInterval(index - 1, index - 1);
            }
        }
    }//GEN-LAST:event_categoryTableKeyPressed

    private void updateButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateButtonActionPerformed
        // TODO add your handling code here:
        new Thread(){
            @Override public void run() {
                updateButton.setEnabled(false);
                int rows = defaultTableModel.getRowCount();
                for (int i = rows - 1; i >= 0; i--) {
                    try{
                        Integer order = Integer.parseInt(defaultTableModel.getValueAt(i, 0).toString().trim());
                        CampusCategory campusCategory = (CampusCategory) defaultTableModel.getValueAt(i, 1);
                        campusCategory.setDisplayOrder(order);
                        DatabaseManager.updateData(campusCategory);
                    }catch(HibernateException he){
                        System.out.println(he);
                    }
                }
                updateButton.setEnabled(true);
                admission.utils.MessageBox.info(CampusCategoryDisplayOrderDialog.this, "Display order set successfully");
            }
        }.start();
    }//GEN-LAST:event_updateButtonActionPerformed

    private void backButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backButtonActionPerformed
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_backButtonActionPerformed

    private void getCampusCategory(){
        removeTableRows();
        
        List<CampusCategory> list = DatabaseManager.getCampusCategory(campus.getCampusId(), programType.getProgramTypeId(), shift.getShiftId(), "displayOrder");
        
        for (int i = 0; i < list.size(); i++) {
            this.defaultTableModel.addRow(new Object[]{i + 1 + "   ", list.get(i)});
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
    private javax.swing.JTable categoryTable;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JPanel titlePanel;
    private javax.swing.JButton updateButton;
    // End of variables declaration//GEN-END:variables
    private javax.swing.table.DefaultTableModel defaultTableModel;
    private Campus campus;
    private ProgramType programType;
    private Shift shift;
}
