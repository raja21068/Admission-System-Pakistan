package admission.view.fees.old;

import admission.controller.DatabaseManager;
import admission.model.AdmissionYear;
import admission.model.security.Resources;
import admission.model.ProgramType;
import admission.model.Shift;
import com.yog.component.ComboBoxToolTipRenderer;
import com.yog.component.VerticalTableHeaderCellRenderer;
import java.util.Enumeration;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.text.DocumentFilter;

/**
 *
 * @author Raja Kumar & Jay Kumar
 */
public class FeeInternalFrame extends javax.swing.JInternalFrame {

    public FeeInternalFrame() {
        initComponents();
        
        admission.utils.Utility.hideOnEscape(this);
        
        this.defaultTableModel = (DefaultTableModel) this.feeTable.getModel();
        setVerticalHeaderColumnUI();
        documentFilter = new admission.utils.UppercaseDocumentFilter();
        //numberDocumentFilter = new utilities.NumberDocumentFilter();
        
//        ((AbstractDocument) this.amountTextField.getDocument()).setDocumentFilter(numberDocumentFilter);
//        ((AbstractDocument) this.remarksTextArea.getDocument()).setDocumentFilter(documentFilter);
    }

    @Override
    public void setVisible(boolean aFlag) {
        if(aFlag) {
            this.getShift();
            this.getProgramType();
            this.getAdmissionYear();
        }
        super.setVisible(aFlag);
    }
    
    public void setPrivileges(Resources privileges){
        this.privileges = privileges;
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        clearButton = new javax.swing.JButton();
        deleteButton = new javax.swing.JButton();
        updateButton = new javax.swing.JButton();
        addButton = new javax.swing.JButton();
        backButton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        feeTable = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        programTypeComboBox = new javax.swing.JComboBox();
        feeOfComboBox = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        admissionYearComboBox = new javax.swing.JComboBox();
        shiftComboBox = new javax.swing.JComboBox();

        setClosable(true);
        setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);
        setIconifiable(true);
        setTitle("Fee");

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Fee");

        clearButton.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        clearButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/admission/view/images/Empty.png"))); // NOI18N
        clearButton.setToolTipText("Clear");
        clearButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearButtonActionPerformed(evt);
            }
        });

        deleteButton.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        deleteButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/admission/view/images/Remove.png"))); // NOI18N
        deleteButton.setToolTipText("Delete");
        deleteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteButtonActionPerformed(evt);
            }
        });

        updateButton.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        updateButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/admission/view/images/Update.png"))); // NOI18N
        updateButton.setToolTipText("Update");
        updateButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateButtonActionPerformed(evt);
            }
        });

        addButton.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        addButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/admission/view/images/Add.png"))); // NOI18N
        addButton.setToolTipText("Add");
        addButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addButtonActionPerformed(evt);
            }
        });

        backButton.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        backButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/admission/view/images/Back.png"))); // NOI18N
        backButton.setToolTipText("Back");
        backButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backButtonActionPerformed(evt);
            }
        });

        feeTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "SNo.", "Class", "Admission Fee", "Identity Card Fee", "Enrolment Fee", "Caution Money", "Sports Fee", "Bus Fare for shuttle Bus service", "Extra Curricular activites fee", "Marks Certificate verfication fee", "Library Development fund", "Laboratory user charges", "University Development fund", "Bus fare", "Registration with Pharmecy Council", "Tution fee", "Exam fee", "Total"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Object.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        feeTable.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        feeTable.setRowHeight(20);
        feeTable.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(feeTable);
        if (feeTable.getColumnModel().getColumnCount() > 0) {
            feeTable.getColumnModel().getColumn(0).setResizable(false);
            feeTable.getColumnModel().getColumn(1).setResizable(false);
            feeTable.getColumnModel().getColumn(2).setResizable(false);
            feeTable.getColumnModel().getColumn(3).setResizable(false);
            feeTable.getColumnModel().getColumn(4).setResizable(false);
            feeTable.getColumnModel().getColumn(5).setResizable(false);
            feeTable.getColumnModel().getColumn(6).setResizable(false);
            feeTable.getColumnModel().getColumn(7).setResizable(false);
            feeTable.getColumnModel().getColumn(8).setResizable(false);
            feeTable.getColumnModel().getColumn(9).setResizable(false);
            feeTable.getColumnModel().getColumn(10).setResizable(false);
            feeTable.getColumnModel().getColumn(11).setResizable(false);
            feeTable.getColumnModel().getColumn(12).setResizable(false);
            feeTable.getColumnModel().getColumn(13).setResizable(false);
            feeTable.getColumnModel().getColumn(14).setResizable(false);
            feeTable.getColumnModel().getColumn(15).setResizable(false);
            feeTable.getColumnModel().getColumn(16).setResizable(false);
            feeTable.getColumnModel().getColumn(17).setResizable(false);
        }

        jLabel3.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel3.setText("Program Type:");

        jLabel4.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel4.setText("Fee Of:");

        programTypeComboBox.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        feeOfComboBox.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        jLabel2.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel2.setText("Admission Year:");

        jLabel5.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel5.setText("Shift:");

        admissionYearComboBox.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        shiftComboBox.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 858, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(addButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(updateButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(deleteButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(clearButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(backButton))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(admissionYearComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(shiftComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(programTypeComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(feeOfComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {addButton, clearButton, deleteButton, updateButton});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(admissionYearComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(programTypeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(shiftComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(feeOfComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(clearButton)
                    .addComponent(deleteButton)
                    .addComponent(updateButton)
                    .addComponent(addButton)
                    .addComponent(backButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 507, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void addButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addButtonActionPerformed
//        String amount = this.amountTextField.getText();
//        String currency = this.currencyTextField.getText();
//        Date date = null;
//        try {
//            date = DateFormatter.getStringToDate(this.dateFormattedTextField.getText());
//        } catch (ParseException ex) {
//            Logger.getLogger(FeeInternalFrame.class.getName()).log(Level.SEVERE, null, ex);
//            utilities.MessageBox.error(this, ex);
//            return;
//        }
//        String remarks = this.remarksTextArea.getText();
//        if(amount.isEmpty()) return;
        
//        Fee fee = new Fee(Integer.parseInt(amount), currency, date, remarks, null);
//        try{
//            Integer feeId = DatabaseManager.addData(fee);
//            if(feeId > 0){
//                utilities.MessageBox.info(this, "Record added successfully");
//                clear();
//                this.getFee();
//            }
//        }catch(HibernateException he){
//            utilities.MessageBox.error(this, he);
//        }
    }//GEN-LAST:event_addButtonActionPerformed

    private void updateButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateButtonActionPerformed
//        Fee fee = (Fee) this.feeList.getSelectedValue();
//        if(fee == null) return;
//        
//        String amount = this.amountTextField.getText();
//        String currency = this.currencyTextField.getText();
//        Date date = null;
//        try {
//            date = DateFormatter.getStringToDate(this.dateFormattedTextField.getText());
//        } catch (ParseException ex) {
//            Logger.getLogger(FeeInternalFrame.class.getName()).log(Level.SEVERE, null, ex);
//            utilities.MessageBox.error(this, ex);
//            return;
//        }
//        String remarks = this.remarksTextArea.getText();
//        if(amount.isEmpty()) return;
        
//        fee.setAmount(Integer.parseInt(amount));
//        fee.setCurrency(currency);
//        fee.setDate(date);
//        fee.setRemarks(remarks);
//        try{
//            DatabaseManager.updateData(fee);
//            utilities.MessageBox.info(this, "Record updated successfully");
//            clear();
//            this.getFee();
//        }catch(HibernateException he){
//            utilities.MessageBox.error(this, he);
//        }
    }//GEN-LAST:event_updateButtonActionPerformed

    private void deleteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteButtonActionPerformed
//        Fee fee = (Fee) this.feeList.getSelectedValue();
//        if(fee == null) return;
//        
//        try{
//            DatabaseManager.deleteData(Fee.class.getName(), "feeId=" + fee.getFeeId());
//            utilities.MessageBox.info(this, "Record deleted successfully");
//            clear();
//            this.getFee();
//        }catch(HibernateException he){
//            utilities.MessageBox.error(this, he);
//        }
    }//GEN-LAST:event_deleteButtonActionPerformed

    private void clearButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearButtonActionPerformed
//        clear();
    }//GEN-LAST:event_clearButtonActionPerformed

    private void backButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backButtonActionPerformed
        // TODO add your handling code here:
        this.setVisible(false);
    }//GEN-LAST:event_backButtonActionPerformed

    private void getShift(){
        this.shiftComboBox.removeAllItems();
        List<Shift> list = DatabaseManager.getData(Shift.class.getName(), "shiftId");
        
        for (int i = 0; i < list.size(); i++) {
            this.shiftComboBox.addItem(list.get(i));
        }
    }
    
    private void getProgramType(){
        this.programTypeComboBox.removeAllItems();
        List<ProgramType> list = DatabaseManager.getData(ProgramType.class.getName(), "name");
        
        for (int i = 0; i < list.size(); i++) {
            this.programTypeComboBox.addItem(list.get(i));
        }
    }
    
    private void getAdmissionYear(){
        this.admissionYearComboBox.removeAllItems();
        List<AdmissionYear> list = DatabaseManager.getData(AdmissionYear.class.getName(), "year DESC");
        
        for (int i = 0; i < list.size(); i++) {
            this.admissionYearComboBox.addItem(list.get(i));
        }
    }
    
    private void setVerticalHeaderColumnUI(){
        TableCellRenderer headerRenderer = new VerticalTableHeaderCellRenderer();
        Enumeration<TableColumn> columns = feeTable.getColumnModel().getColumns();
        int c = 0;
        while (columns.hasMoreElements()) {
            TableColumn columm = columns.nextElement();
            if(c > 1){
                columm.setPreferredWidth(35);
                columm.setMaxWidth(35);
                columm.setHeaderRenderer(headerRenderer);
            }else{
                if(c == 0){
                    columm.setPreferredWidth(35);
                    columm.setMaxWidth(35);
                }else{
                    columm.setPreferredWidth(300);
                    columm.setMaxWidth(300);
                }
            }
            c++;
            columm.setResizable(false);
        }
    }
    
//    private void getFee(){
//        List<Fee> list = DatabaseManager.getData(Fee.class.getName(), "amount");
//        this.feeList.setListData(list.toArray());
//    }
//
//    private void clear(){
//        this.feeIdTextField.setText("");
//        this.currencyTextField.setText("");
//        this.dateFormattedTextField.setText("");
//        this.remarksTextArea.setText("");
//        this.feeList.clearSelection();
//    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addButton;
    private javax.swing.JComboBox admissionYearComboBox;
    private javax.swing.JButton backButton;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton clearButton;
    private javax.swing.JButton deleteButton;
    private javax.swing.JComboBox feeOfComboBox;
    private javax.swing.JTable feeTable;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JComboBox programTypeComboBox;
    private javax.swing.JComboBox shiftComboBox;
    private javax.swing.JButton updateButton;
    // End of variables declaration//GEN-END:variables
    private DocumentFilter documentFilter;
    //private DocumentFilter numberDocumentFilter;
    private Resources privileges;
    private DefaultTableModel defaultTableModel;
    private ComboBoxToolTipRenderer comboBoxToolTipRenderer; 
}
