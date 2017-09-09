package admission.view.fees;

import admission.controller.DatabaseManager;
import admission.controller.JDBCDatabaseManager;
import admission.enums.FeeCategoryTypeEnum;
import admission.enums.MessageEnum;
import admission.enums.ProgramTypeEnum;
import admission.enums.ShiftEnum;
import admission.model.Shift;
import admission.model.fee.FeeCategory;
import com.yog.component.OperationButtons;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.text.AbstractDocument;
import org.hibernate.HibernateException;
import admission.utils.MessageBox;
import admission.utils.ModificationManager;
import admission.utils.RoundedBorder;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Adeel, Yougeshwar Khatri
 */
public class FeeCategoryInternalFrame extends javax.swing.JInternalFrame {

    public FeeCategoryInternalFrame() {
        initComponents();

        admission.utils.Utility.hideOnEscape(this);

        feeCategoryDisplayOrderDialog = new FeeCategoryDisplayOrderDialog(this);

        this.titlePanel.setBorder(RoundedBorder.createGradientBorder());
        this.buttonsPanel.add(this.getOperationButtons());

        admission.utils.Utility.loadEnum(categoryTypeComboBox, FeeCategoryTypeEnum.class);
        admission.utils.Utility.loadEnum(programTypeComboBox, ProgramTypeEnum.class);
        admission.utils.Utility.loadEnum(shiftComboBox, ShiftEnum.class);

        this.modify = new ModificationManager();
        ((AbstractDocument) this.nameTextField.getDocument()).addDocumentListener(modify);
        this.categoryTypeComboBox.addActionListener(modify);
        this.programTypeComboBox.addActionListener(modify);
        ((AbstractDocument) this.codeFormattedTextField.getDocument()).addDocumentListener(modify);
        ((AbstractDocument) this.remarksTextArea.getDocument()).addDocumentListener(modify);

        admission.utils.UppercaseDocumentFilter documentFilter = new admission.utils.UppercaseDocumentFilter();
        ((AbstractDocument) this.nameTextField.getDocument()).setDocumentFilter(documentFilter);
        ((AbstractDocument) this.remarksTextArea.getDocument()).setDocumentFilter(documentFilter);

    }

    private JPanel getOperationButtons() {
        operationButtons = new OperationButtons() {
            @Override
            public void saveOperation(ActionEvent evt) {
                FeeCategoryInternalFrame.this.save();
            }

            @Override
            public void deleteOperation(ActionEvent evt) {
                FeeCategoryInternalFrame.this.delete();
            }

            @Override
            public void newOperation(ActionEvent evt) {
                FeeCategoryInternalFrame.this.clear();
            }

            @Override
            public void backOperation(ActionEvent evt) {
                FeeCategoryInternalFrame.this.setVisible(false);
            }
        };
        operationButtons.setSize(335, 60);
        return operationButtons;
    }

    @Override
    public void setVisible(boolean aFlag) {
        if (aFlag) {
            if (modify != null) {
                this.modify.setModifyLabel(operationButtons.getModifyLabel());
            }
            getFeeCategory();
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

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        idTextField = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        feeCategoryList = new javax.swing.JList();
        jLabel3 = new javax.swing.JLabel();
        nameTextField = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        categoryTypeComboBox = new javax.swing.JComboBox();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        remarksTextArea = new javax.swing.JTextArea();
        jLabel6 = new javax.swing.JLabel();
        shiftComboBox = new javax.swing.JComboBox();
        jLabel7 = new javax.swing.JLabel();
        programTypeComboBox = new javax.swing.JComboBox();
        titlePanel = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        buttonsPanel = new javax.swing.JPanel();
        displayOrderButton = new javax.swing.JButton();
        searchJXSearchField = new org.jdesktop.swingx.JXSearchField();
        jLabel5 = new javax.swing.JLabel();
        codeFormattedTextField = new javax.swing.JFormattedTextField();

        setClosable(true);
        setIconifiable(true);
        setTitle("Fee Category");

        jLabel1.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel1.setText("ID:");

        idTextField.setEditable(false);
        idTextField.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        feeCategoryList.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                feeCategoryListValueChanged(evt);
            }
        });
        jScrollPane1.setViewportView(feeCategoryList);

        jLabel3.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel3.setText("Name:");

        jLabel2.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel2.setText("Category Type:");

        categoryTypeComboBox.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        jLabel4.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel4.setText("Remarks:");

        remarksTextArea.setColumns(20);
        remarksTextArea.setLineWrap(true);
        remarksTextArea.setWrapStyleWord(true);
        jScrollPane2.setViewportView(remarksTextArea);

        jLabel6.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel6.setText("Shift:");

        shiftComboBox.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        shiftComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                shiftComboBoxActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel7.setText("Program Type: ");

        programTypeComboBox.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        titlePanel.setLayout(new java.awt.BorderLayout());

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/admission/view/images/form-icon.png"))); // NOI18N
        jLabel8.setText("Fee Category");
        jLabel8.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, java.awt.Color.darkGray), javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5)));
        titlePanel.add(jLabel8, java.awt.BorderLayout.CENTER);

        buttonsPanel.setPreferredSize(new java.awt.Dimension(335, 60));
        buttonsPanel.setLayout(new java.awt.BorderLayout());

        displayOrderButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/order.png"))); // NOI18N
        displayOrderButton.setToolTipText("Display Order");
        displayOrderButton.setFocusPainted(false);
        displayOrderButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                displayOrderButtonActionPerformed(evt);
            }
        });

        searchJXSearchField.setInstantSearchDelay(0);
        searchJXSearchField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchJXSearchFieldActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel5.setText("Code:");

        codeFormattedTextField.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
        codeFormattedTextField.setHorizontalAlignment(javax.swing.JTextField.TRAILING);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(titlePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(displayOrderButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonsPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 525, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 334, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(10, 10, 10)))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(shiftComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(nameTextField)
                                    .addComponent(categoryTypeComboBox, 0, 241, Short.MAX_VALUE)
                                    .addComponent(idTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(programTypeComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(codeFormattedTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(searchJXSearchField, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(titlePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(buttonsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(displayOrderButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(programTypeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(searchJXSearchField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(shiftComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(idTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(nameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(categoryTypeComboBox)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(6, 6, 6)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(codeFormattedTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {categoryTypeComboBox, codeFormattedTextField, idTextField, nameTextField, programTypeComboBox, searchJXSearchField, shiftComboBox});

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void shiftComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_shiftComboBoxActionPerformed
        getFeeCategory();
    }//GEN-LAST:event_shiftComboBoxActionPerformed

    private void displayOrderButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_displayOrderButtonActionPerformed
        feeCategoryDisplayOrderDialog.setVisible(true);
    }//GEN-LAST:event_displayOrderButtonActionPerformed

    private void feeCategoryListValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_feeCategoryListValueChanged
        feeCategory = (FeeCategory) feeCategoryList.getSelectedValue();
        if (feeCategory == null) {
            return;
        }

        idTextField.setText(String.valueOf(feeCategory.getId()));
        nameTextField.setText(feeCategory.getName());
        remarksTextArea.setText(feeCategory.getRemarks());
        categoryTypeComboBox.setSelectedItem(feeCategory.getCategoryType());
        programTypeComboBox.setSelectedItem(feeCategory.getProgramType());
        codeFormattedTextField.setText(String.valueOf(feeCategory.getCode()));
        modify.setModify(false);
    }//GEN-LAST:event_feeCategoryListValueChanged

    private void searchJXSearchFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchJXSearchFieldActionPerformed
        String text = searchJXSearchField.getText();
        admission.utils.Utility.filterDataList(feeCategoryList, feeCategoryDataList, text);
    }//GEN-LAST:event_searchJXSearchFieldActionPerformed

    private void getFeeCategory() {
        feeCategoryDataList = new ArrayList<>();
        feeCategoryList.setListData(feeCategoryDataList.toArray());

        ShiftEnum shift = (ShiftEnum) shiftComboBox.getSelectedItem();
        if (shift == null) {
            return;
        }

        feeCategoryDataList = DatabaseManager.getData(FeeCategory.class, "shift = " + shift.ordinal(), "name");
        feeCategoryList.setListData(feeCategoryDataList.toArray());
    }

    private void clear() {
        feeCategory = null;
        idTextField.setText("");
        nameTextField.setText("");
        Integer value = -2;
        try {
            value = JDBCDatabaseManager.getIntValue("SELECT MAX(CODE) AS CODE FROM YOG_FEE_CATEGORY", "CODE");
        } catch (SQLException ex) {
            Logger.getLogger(FeeCategoryInternalFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        codeFormattedTextField.setText(String.valueOf(value + 1));
        remarksTextArea.setText("");
        this.feeCategoryList.clearSelection();
        this.nameTextField.requestFocus();
        modify.setModify(false);
    }

    private void save() {
        ShiftEnum shift = (ShiftEnum) shiftComboBox.getSelectedItem();
        if (shift == null) {
            return;
        }

        String name = nameTextField.getText();
        String code = codeFormattedTextField.getText();
        FeeCategoryTypeEnum feeCategoryType = (FeeCategoryTypeEnum) categoryTypeComboBox.getSelectedItem();
        ProgramTypeEnum programType = (ProgramTypeEnum) programTypeComboBox.getSelectedItem();
        String remarks = remarksTextArea.getText();

        if (name.isEmpty() || code.isEmpty()) {
            MessageBox.error(this, MessageEnum.MSG_29);
            return;
        }

        if (feeCategory == null) {
            try {
                if (JDBCDatabaseManager.isConfirm("SELECT * FROM YOG_FEE_CATEGORY WHERE CODE = " + code)) {
                    MessageBox.error(this, "Code already in use");
                    return;
                }
            } catch (SQLException ex) {
                Logger.getLogger(FeeCategoryInternalFrame.class.getName()).log(Level.SEVERE, null, ex);
                return;
            }
            feeCategory = new FeeCategory();
        }

        feeCategory.setProgramType(programType);
        feeCategory.setShift(shift);
        feeCategory.setName(name);
        feeCategory.setCode(Integer.parseInt(code));
        feeCategory.setCategoryType(feeCategoryType);
        feeCategory.setRemarks(remarks);

        try {
            DatabaseManager.save(feeCategory);
            admission.utils.MessageBox.info(this, MessageEnum.MSG_SAVE);
            this.getFeeCategory();
            clear();
        } catch (HibernateException he) {
            admission.utils.MessageBox.error(this, he);
        }
    }

    private void delete() {
        if (feeCategory == null) {
            return;
        }

        try {
            DatabaseManager.deleteData(feeCategory);
            admission.utils.MessageBox.info(this, MessageEnum.MSG_DELETE);
            this.getFeeCategory();
            clear();
        } catch (HibernateException he) {
            admission.utils.MessageBox.error(this, he);
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel buttonsPanel;
    private javax.swing.JComboBox categoryTypeComboBox;
    private javax.swing.JFormattedTextField codeFormattedTextField;
    private javax.swing.JButton displayOrderButton;
    private javax.swing.JList feeCategoryList;
    private javax.swing.JTextField idTextField;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField nameTextField;
    private javax.swing.JComboBox programTypeComboBox;
    private javax.swing.JTextArea remarksTextArea;
    private org.jdesktop.swingx.JXSearchField searchJXSearchField;
    private javax.swing.JComboBox shiftComboBox;
    private javax.swing.JPanel titlePanel;
    // End of variables declaration//GEN-END:variables
    private FeeCategoryDisplayOrderDialog feeCategoryDisplayOrderDialog;
    private OperationButtons operationButtons;
    private List<FeeCategory> feeCategoryDataList;
    private ModificationManager modify;
    private FeeCategory feeCategory;
}
