package admission.view.campus;

import admission.controller.DatabaseManager;
import admission.enums.MessageEnum;
import admission.model.Campus;
import admission.model.CampusCategory;
import admission.model.Category;
import admission.model.CategoryType;
import admission.model.security.Resources;
import admission.model.ProgramType;
import admission.model.Shift;
import com.yog.component.OperationButtons;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Iterator;
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
public class CampusCategoryInternalFrame extends javax.swing.JInternalFrame {

    public CampusCategoryInternalFrame() {
        initComponents();
        
        campusCategoryDisplayOrderDialog = new CampusCategoryDisplayOrderDialog(this);
        
        admission.utils.Utility.hideOnEscape(this);
        admission.utils.Utility.comboBoxScroll(this.categoryComboBox);
        this.titlePanel.setBorder(RoundedBorder.createGradientBorder());
        this.buttonsPanel.add(this.getOperationButtons());
        
        this.modify = new ModificationManager();
        this.onPercentCheckBox.addActionListener(modify);
        ((AbstractDocument) this.percentTextField.getDocument()).addDocumentListener(modify);
        ((AbstractDocument) this.remarksTextArea.getDocument()).addDocumentListener(modify);
        
        documentFilter = new admission.utils.UppercaseDocumentFilter();
        numberDocumentFilter = new admission.utils.NumberDocumentFilter();
        ((AbstractDocument) this.percentTextField.getDocument()).setDocumentFilter(numberDocumentFilter);
        ((AbstractDocument) this.remarksTextArea.getDocument()).setDocumentFilter(documentFilter);
    }

    @Override
    public void setVisible(boolean aFlag) {
        if (aFlag) {
            if (modify != null) {
                this.modify.setModifyLabel(operationButtons.getModifyLabel());
            }
            this.clear();
            this.getCampus();
            this.getProgramType();
            this.getShift();
            this.getCategoryType();
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
    
    public void setPrivileges(Resources privileges){
        this.privileges = privileges;
//        this.addButton.setEnabled((privileges.isAddPrivilige()));
//        this.updateButton.setEnabled((privileges.isUpdatePrivilige()));
//        this.deleteButton.setEnabled((privileges.isDeletePrivilige()));
    }
    
    private JPanel getOperationButtons() {
        operationButtons = new OperationButtons() {
            @Override public void saveOperation(ActionEvent evt) {
                CampusCategoryInternalFrame.this.save();
            }
            @Override public void deleteOperation(ActionEvent evt) {
                CampusCategoryInternalFrame.this.delete();
            }
            @Override public void newOperation(ActionEvent evt) {
                CampusCategoryInternalFrame.this.clear();
            }
            @Override public void backOperation(ActionEvent evt) {
                CampusCategoryInternalFrame.this.setVisible(false);
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
        campusCategoryIdTextField = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        campusCategoryList = new javax.swing.JList();
        jScrollPane2 = new javax.swing.JScrollPane();
        remarksTextArea = new javax.swing.JTextArea();
        jLabel5 = new javax.swing.JLabel();
        categoryComboBox = new javax.swing.JComboBox();
        campusComboBox = new javax.swing.JComboBox();
        jLabel6 = new javax.swing.JLabel();
        onPercentCheckBox = new javax.swing.JCheckBox();
        percentTextField = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        categoryTypeComboBox = new javax.swing.JComboBox();
        jLabel7 = new javax.swing.JLabel();
        displayOrderButton = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        programTypeComboBox = new javax.swing.JComboBox();
        shiftComboBox = new javax.swing.JComboBox();
        jLabel9 = new javax.swing.JLabel();
        buttonsPanel = new javax.swing.JPanel();
        titlePanel = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        searchJXSearchField = new org.jdesktop.swingx.JXSearchField();

        setClosable(true);
        setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);
        setTitle("Campus Category");

        jLabel2.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel2.setText("CC ID:");

        campusCategoryIdTextField.setEditable(false);
        campusCategoryIdTextField.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel4.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel4.setText("Remarks");

        campusCategoryList.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                campusCategoryListValueChanged(evt);
            }
        });
        jScrollPane1.setViewportView(campusCategoryList);

        remarksTextArea.setColumns(20);
        remarksTextArea.setLineWrap(true);
        remarksTextArea.setRows(5);
        remarksTextArea.setWrapStyleWord(true);
        jScrollPane2.setViewportView(remarksTextArea);

        jLabel5.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel5.setText("Category:");

        categoryComboBox.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        campusComboBox.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        campusComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                campusComboBoxActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel6.setText("Campus:");

        onPercentCheckBox.setText("On Percent");
        onPercentCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                onPercentCheckBoxActionPerformed(evt);
            }
        });

        percentTextField.setEditable(false);
        percentTextField.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        percentTextField.setText("0");

        jLabel8.setText("%");

        categoryTypeComboBox.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        categoryTypeComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                categoryTypeComboBoxActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel7.setText("Category Type:");

        displayOrderButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/order.png"))); // NOI18N
        displayOrderButton.setToolTipText("Display Order");
        displayOrderButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                displayOrderButtonActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel3.setText("Program Type:");

        programTypeComboBox.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        programTypeComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                programTypeComboBoxActionPerformed(evt);
            }
        });

        shiftComboBox.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        shiftComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                shiftComboBoxActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel9.setText("Shift:");

        buttonsPanel.setPreferredSize(new java.awt.Dimension(335, 60));
        buttonsPanel.setLayout(new java.awt.BorderLayout());

        titlePanel.setLayout(new java.awt.BorderLayout());

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/admission/view/images/form-icon.png"))); // NOI18N
        jLabel11.setText("Campus Program Of Study Group");
        jLabel11.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, java.awt.Color.darkGray), javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5)));
        titlePanel.add(jLabel11, java.awt.BorderLayout.PAGE_END);

        searchJXSearchField.setInstantSearchDelay(0);
        searchJXSearchField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchJXSearchFieldActionPerformed(evt);
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
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel4)
                            .addComponent(jScrollPane2)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(campusComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(categoryComboBox, 0, 323, Short.MAX_VALUE)
                                            .addComponent(categoryTypeComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addGroup(layout.createSequentialGroup()
                                                        .addComponent(programTypeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                        .addComponent(jLabel9))
                                                    .addComponent(campusCategoryIdTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addGroup(layout.createSequentialGroup()
                                                        .addComponent(onPercentCheckBox)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                        .addComponent(percentTextField)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                        .addComponent(jLabel8))
                                                    .addComponent(shiftComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                                        .addGap(0, 0, Short.MAX_VALUE)))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 232, Short.MAX_VALUE)
                            .addComponent(searchJXSearchField, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(displayOrderButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonsPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(titlePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(buttonsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(displayOrderButton, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(campusComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(searchJXSearchField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(categoryTypeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(categoryComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(programTypeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9)
                            .addComponent(shiftComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(campusCategoryIdTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(onPercentCheckBox, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(percentTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1))
                .addContainerGap(15, Short.MAX_VALUE))
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {campusCategoryIdTextField, campusComboBox, categoryComboBox, categoryTypeComboBox, percentTextField, programTypeComboBox, searchJXSearchField, shiftComboBox});

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void campusCategoryListValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_campusCategoryListValueChanged
        CampusCategory campusCategory = (CampusCategory) this.campusCategoryList.getSelectedValue();
        if(campusCategory == null) return;
        
        this.categoryTypeComboBox.setSelectedItem(campusCategory.getCategory().getCategoryType());
        this.categoryComboBox.setSelectedItem(campusCategory.getCategory());
        this.campusCategoryIdTextField.setText(String.valueOf(campusCategory.getCampusCategoryId()));
        this.onPercentCheckBox.setSelected((campusCategory.getOnPercent()));
        this.percentTextField.setEditable(onPercentCheckBox.isSelected());
        this.percentTextField.setText(String.valueOf(campusCategory.getPercent()));
        this.remarksTextArea.setText(campusCategory.getRemarks());
        modify.setModify(false);
    }//GEN-LAST:event_campusCategoryListValueChanged

    private void campusComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_campusComboBoxActionPerformed
        getCampusCategory();
    }//GEN-LAST:event_campusComboBoxActionPerformed

    private void onPercentCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_onPercentCheckBoxActionPerformed
        // TODO add your handling code here:
        this.percentTextField.setEditable(this.onPercentCheckBox.isSelected());
        if(!this.onPercentCheckBox.isSelected()){
            this.percentTextField.setText("0");
        }
    }//GEN-LAST:event_onPercentCheckBoxActionPerformed

    private void categoryTypeComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_categoryTypeComboBoxActionPerformed
        // TODO add your handling code here:
        this.getCategory();
    }//GEN-LAST:event_categoryTypeComboBoxActionPerformed

    private void displayOrderButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_displayOrderButtonActionPerformed
        // TODO add your handling code here:
        Campus campus = (Campus) this.campusComboBox.getSelectedItem();
        ProgramType programType = (ProgramType) this.programTypeComboBox.getSelectedItem();
        Shift session = (Shift) this.shiftComboBox.getSelectedItem();
        if(campus == null || programType == null || session == null) return;
        
        campusCategoryDisplayOrderDialog.setVisible(campus, programType, session, true);
    }//GEN-LAST:event_displayOrderButtonActionPerformed

    private void programTypeComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_programTypeComboBoxActionPerformed
        // TODO add your handling code here:
        this.getCampusCategory();
    }//GEN-LAST:event_programTypeComboBoxActionPerformed

    private void shiftComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_shiftComboBoxActionPerformed
        // TODO add your handling code here:
        this.getCampusCategory();
    }//GEN-LAST:event_shiftComboBoxActionPerformed

    private void searchJXSearchFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchJXSearchFieldActionPerformed
        // TODO add your handling code here:
        String text = searchJXSearchField.getText();
        admission.utils.Utility.filterDataList(campusCategoryList, campusCategoryDataList, text);
    }//GEN-LAST:event_searchJXSearchFieldActionPerformed

    private void getCampus(){
        campusComboBox.removeAllItems();
        
        List<Campus> list = DatabaseManager.getData(Campus.class.getName(), "displayOrder");
        for (Campus list1 : list) {
            campusComboBox.addItem(list1);
        }
    }

    private void getProgramType(){
        this.programTypeComboBox.removeAllItems();
        
        List<ProgramType> list = DatabaseManager.getData(ProgramType.class.getName(), "programTypeId");
        
        for (ProgramType list1 : list) {
            this.programTypeComboBox.addItem(list1);
        }
    }
    
    private void getShift(){
        this.shiftComboBox.removeAllItems();
        
        List<Shift> list = DatabaseManager.getData(Shift.class.getName(), "shiftId");
        
        for (Shift list1 : list) {
            this.shiftComboBox.addItem(list1);
        }
    }
    
    private void getCategoryType(){
        this.categoryTypeComboBox.removeAllItems();
        
        List<CategoryType> list = DatabaseManager.getData(CategoryType.class.getName(), "name");
        for (CategoryType list1 : list) {
            categoryTypeComboBox.addItem(list1);
        }
    }
    
    private void getCategory(){
        this.categoryComboBox.removeAllItems();
        
        CategoryType categoryType = (CategoryType) this.categoryTypeComboBox.getSelectedItem();
        if(categoryType == null) return;
        
        Set set = categoryType.getCategories();
        if(set == null) return;
        
        Iterator it = set.iterator();
        while(it.hasNext()){
            categoryComboBox.addItem(it.next());
        }
    }
    
    private void getCampusCategory(){
        campusCategoryDataList.clear();
        this.campusCategoryList.setListData(campusCategoryDataList.toArray());
        
        Campus campus = (Campus) this.campusComboBox.getSelectedItem();
        ProgramType pt = (ProgramType) this.programTypeComboBox.getSelectedItem();
        Shift shift = (Shift) this.shiftComboBox.getSelectedItem();
        if(campus == null || pt == null || shift == null) return;

        Set set = campus.getCampusCategories();
        if(set == null) return;
        
        campusCategoryDataList = DatabaseManager.getCampusCategory(campus.getCampusId(), pt.getProgramTypeId(), shift.getShiftId(), "displayOrder");
        this.campusCategoryList.setListData(campusCategoryDataList.toArray());
    }

    private void save() {
        Campus campus = (Campus) this.campusComboBox.getSelectedItem();
        Category category = (Category) this.categoryComboBox.getSelectedItem();
        ProgramType programType = (ProgramType) this.programTypeComboBox.getSelectedItem();
        Shift shift = (Shift) this.shiftComboBox.getSelectedItem();
        CampusCategory campusCategory = (CampusCategory) this.campusCategoryList.getSelectedValue();
        if(category == null || campus == null || programType == null || shift == null) return;
        if(campusCategory == null ) {
            campusCategory = new CampusCategory();
        }
        
        boolean onPercent = (this.onPercentCheckBox.isSelected());
        Integer percent = Integer.parseInt(this.percentTextField.getText());
        String remarks = this.remarksTextArea.getText();
        
        campusCategory.setCampus(campus);
        campusCategory.setCategory(category);
        campusCategory.setProgramType(programType);
        campusCategory.setShift(shift);
        campusCategory.setCategory(category);
        campusCategory.setOnPercent(onPercent);
        campusCategory.setPercent(percent);
        campusCategory.setRemarks(remarks);
                
        try{
            DatabaseManager.save(campusCategory);
            admission.utils.MessageBox.info(this, MessageEnum.MSG_SAVE);
            DatabaseManager.refresh(campus);
            DatabaseManager.refresh(category);
            DatabaseManager.refresh(programType);
            DatabaseManager.refresh(shift);
            this.getCampusCategory();
            clear();
        }catch(HibernateException he){
            admission.utils.MessageBox.error(this, he);
            he.printStackTrace();
        }
    }
    
    private void delete() {
        Campus campus = (Campus) this.campusComboBox.getSelectedItem();
        Category category = (Category) this.categoryComboBox.getSelectedItem();
        ProgramType programType = (ProgramType) this.programTypeComboBox.getSelectedItem();
        Shift shift = (Shift) this.shiftComboBox.getSelectedItem();
        CampusCategory campusCategory = (CampusCategory) this.campusCategoryList.getSelectedValue();
        if(category == null || campus == null || programType == null || shift == null || campusCategory == null ) return;
        
        try{
            DatabaseManager.deleteData(CampusCategory.class.getName(), "campusCategoryId = " + campusCategory.getCampusCategoryId());
            admission.utils.MessageBox.info(this, MessageEnum.MSG_DELETE);
            DatabaseManager.refresh(campus);
            DatabaseManager.refresh(category);
            DatabaseManager.refresh(programType);
            DatabaseManager.refresh(shift);
            this.getCampusCategory();
            clear();
        }catch(HibernateException he){
            admission.utils.MessageBox.error(this, he);
            he.printStackTrace();
        }
    }
    
    private void clear(){
        this.campusCategoryIdTextField.setText("");
        this.onPercentCheckBox.setSelected(false);
        this.percentTextField.setEditable(false);
        this.percentTextField.setText("0");
        this.remarksTextArea.setText("");
        this.campusCategoryList.clearSelection();
        modify.setModify(false);
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel buttonsPanel;
    private javax.swing.JTextField campusCategoryIdTextField;
    private javax.swing.JList campusCategoryList;
    private javax.swing.JComboBox campusComboBox;
    private javax.swing.JComboBox categoryComboBox;
    private javax.swing.JComboBox categoryTypeComboBox;
    private javax.swing.JButton displayOrderButton;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JCheckBox onPercentCheckBox;
    private javax.swing.JTextField percentTextField;
    private javax.swing.JComboBox programTypeComboBox;
    private javax.swing.JTextArea remarksTextArea;
    private org.jdesktop.swingx.JXSearchField searchJXSearchField;
    private javax.swing.JComboBox shiftComboBox;
    private javax.swing.JPanel titlePanel;
    // End of variables declaration//GEN-END:variables
    private CampusCategoryDisplayOrderDialog campusCategoryDisplayOrderDialog;
    private DocumentFilter documentFilter;
    private DocumentFilter numberDocumentFilter;
    private Resources privileges;
    private OperationButtons operationButtons;
    private List<CampusCategory> campusCategoryDataList = new ArrayList<>();
    private ModificationManager modify;
}
