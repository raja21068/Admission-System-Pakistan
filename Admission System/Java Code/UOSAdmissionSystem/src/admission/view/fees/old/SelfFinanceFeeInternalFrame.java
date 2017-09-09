package admission.view.fees.old;

import admission.controller.DatabaseManager;
import admission.model.AdmissionYear;
import admission.model.Faculty;
import admission.model.Part;
import admission.model.security.Resources;
import admission.model.ProgramOfStudy;
import admission.model.ProgramType;
import admission.model.Shift;
import com.yog.component.ColumnGroup;
import com.yog.component.ComboBoxToolTipRenderer;
import com.yog.component.GroupableTableHeader;
import com.yog.component.VerticalTableHeaderCellRenderer;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.text.AbstractDocument;
import javax.swing.text.DocumentFilter;

/**
 *
 * @author Raja Kumar & Jay Kumar
 */
public class SelfFinanceFeeInternalFrame extends javax.swing.JInternalFrame {

    public SelfFinanceFeeInternalFrame() {
        initComponents();
        
        admission.utils.Utility.hideOnEscape(this);
        
        comboBoxToolTipRenderer = new ComboBoxToolTipRenderer();
        this.posComboBox.setRenderer(comboBoxToolTipRenderer);
        
        this.defaultTableModel = (DefaultTableModel) this.feeTable.getModel();
        TableColumnModel cm = feeTable.getColumnModel();
        ColumnGroup g_name = new ColumnGroup("Fee For Local Candidates");
        g_name.add(cm.getColumn(1));
        g_name.add(cm.getColumn(2));
        ColumnGroup g_lang = new ColumnGroup("Fee For Other Provinces Candidates");
        g_lang.add(cm.getColumn(3));
        g_lang.add(cm.getColumn(4));
        
        GroupableTableHeader header = (GroupableTableHeader) feeTable.getTableHeader();
        header.addColumnGroup(g_name);
        header.addColumnGroup(g_lang);
        
        //setVerticalHeaderColumnUI();
        documentFilter = new admission.utils.UppercaseDocumentFilter();
        //numberDocumentFilter = new utilities.NumberDocumentFilter();
        
        ((AbstractDocument) this.classTextField.getDocument()).setDocumentFilter(documentFilter);
//        ((AbstractDocument) this.amountTextField.getDocument()).setDocumentFilter(numberDocumentFilter);
//        ((AbstractDocument) this.remarksTextArea.getDocument()).setDocumentFilter(documentFilter);
    }

    @Override
    public void setVisible(boolean aFlag) {
        if(aFlag) {
            this.getFaculty();
            this.getShift();
            this.getProgramType();
            this.getAdmissionYear();
            this.getPart();
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
        jLabel8 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        facultyComboBox = new javax.swing.JComboBox();
        posComboBox = new javax.swing.JComboBox();
        jLabel11 = new javax.swing.JLabel();
        admissionYearComboBox = new javax.swing.JComboBox();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel12 = new javax.swing.JLabel();
        shiftComboBox = new javax.swing.JComboBox();
        jLabel13 = new javax.swing.JLabel();
        programTypeComboBox = new javax.swing.JComboBox();
        jLabel14 = new javax.swing.JLabel();
        partComboBox = new javax.swing.JComboBox();
        jLabel15 = new javax.swing.JLabel();
        classTextField = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        feeTable = new javax.swing.JTable(){
            protected JTableHeader createDefaultTableHeader() {
                return new GroupableTableHeader(columnModel);
            }
        };
        posProgramTextField = new javax.swing.JTextField();

        setClosable(true);
        setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);
        setIconifiable(true);
        setTitle("Self Finance Fee");

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Self Finance Fee");

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

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel8.setText("Faculty:");

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel10.setText("Program Of Study:");

        facultyComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                facultyComboBoxActionPerformed(evt);
            }
        });

        posComboBox.setEnabled(false);
        posComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                posComboBoxActionPerformed(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel11.setText("Admission Year:");

        jSeparator2.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel12.setText("Shift:");

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel13.setText("Program Type:");

        programTypeComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                programTypeComboBoxActionPerformed(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel14.setText("Part:");

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel15.setText("Class:");

        feeTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Discipline/Subject", "Installments (Per Year)", "Total Fee", "Installment (Per Year)", "Total Fee"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, true, true, true, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        feeTable.setRowHeight(20);
        feeTable.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(feeTable);
        if (feeTable.getColumnModel().getColumnCount() > 0) {
            feeTable.getColumnModel().getColumn(0).setResizable(false);
            feeTable.getColumnModel().getColumn(0).setPreferredWidth(400);
            feeTable.getColumnModel().getColumn(1).setResizable(false);
            feeTable.getColumnModel().getColumn(1).setPreferredWidth(100);
            feeTable.getColumnModel().getColumn(2).setResizable(false);
            feeTable.getColumnModel().getColumn(3).setResizable(false);
            feeTable.getColumnModel().getColumn(3).setPreferredWidth(100);
            feeTable.getColumnModel().getColumn(4).setResizable(false);
        }

        posProgramTextField.setEditable(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel15)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(classTextField)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
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
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(posProgramTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(posComboBox, 0, 265, Short.MAX_VALUE)
                                    .addComponent(facultyComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 5, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(shiftComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(admissionYearComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(programTypeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel14)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(partComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 99, Short.MAX_VALUE)))
                .addContainerGap())
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {addButton, clearButton, deleteButton, updateButton});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(facultyComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(posComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(6, 6, 6)
                        .addComponent(posProgramTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11)
                            .addComponent(admissionYearComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel12)
                            .addComponent(shiftComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel13)
                            .addComponent(programTypeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel14)
                            .addComponent(partComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel15)
                        .addComponent(classTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(clearButton)
                        .addComponent(deleteButton)
                        .addComponent(updateButton)
                        .addComponent(addButton)
                        .addComponent(backButton)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 512, Short.MAX_VALUE)
                .addContainerGap())
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {admissionYearComboBox, classTextField, facultyComboBox, partComboBox, posComboBox, posProgramTextField, programTypeComboBox, shiftComboBox});

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

    private void facultyComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_facultyComboBoxActionPerformed
        // TODO add your handling code here:
        this.getProgramOfStudy();
    }//GEN-LAST:event_facultyComboBoxActionPerformed

    private void programTypeComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_programTypeComboBoxActionPerformed
        // TODO add your handling code here:
        this.getProgramOfStudy();
    }//GEN-LAST:event_programTypeComboBoxActionPerformed

    private void posComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_posComboBoxActionPerformed
        // TODO add your handling code here:
        ProgramOfStudy pos = (ProgramOfStudy) this.posComboBox.getSelectedItem();
        if(pos == null) return;
        
        this.posProgramTextField.setText(pos.getProgram().toString());
    }//GEN-LAST:event_posComboBoxActionPerformed

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
    
    private void getFaculty(){
        facultyComboBox.removeAllItems();
        
        List<Faculty> list = DatabaseManager.getData(Faculty.class.getName(), "name");
        for (int i = 0; i < list.size(); i++) {
            facultyComboBox.addItem(list.get(i));
        }
    }
    
    private void getPart(){
        partComboBox.removeAllItems();
        
        List<Part> list = DatabaseManager.getData(Part.class.getName(), "name");
        
        for (int i = 0; i < list.size(); i++) {
            partComboBox.addItem(list.get(i));
        }
    }
    
    private void getProgramOfStudy(){
        posComboBox.removeAllItems();
        Faculty faculty = (Faculty) this.facultyComboBox.getSelectedItem();
        ProgramType pt = (ProgramType) this.programTypeComboBox.getSelectedItem();
        if(faculty == null || pt == null) return;
        
        List<ProgramOfStudy> list = DatabaseManager.getFacultyProgramOfStudy(faculty.getFacultyId(), pt.getProgramTypeId());
        List<String> toolTipList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            ProgramOfStudy pos = list.get(i);
            posComboBox.addItem(pos);
            toolTipList.add(pos.getProgram().toString());
        }
        this.comboBoxToolTipRenderer.setTooltips(toolTipList);
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
    private javax.swing.JTextField classTextField;
    private javax.swing.JButton clearButton;
    private javax.swing.JButton deleteButton;
    private javax.swing.JComboBox facultyComboBox;
    private javax.swing.JTable feeTable;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JComboBox partComboBox;
    private javax.swing.JComboBox posComboBox;
    private javax.swing.JTextField posProgramTextField;
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
