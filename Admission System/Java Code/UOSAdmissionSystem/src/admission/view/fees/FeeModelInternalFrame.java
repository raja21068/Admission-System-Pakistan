package admission.view.fees;

import admission.controller.DatabaseManager;
import admission.enums.AmountTypeEnum;
import admission.enums.FeeCategoryTypeEnum;
import admission.enums.MessageEnum;
import admission.enums.ProgramTypeEnum;
import admission.enums.ShiftEnum;
import admission.helpers.CommonHelper;
import admission.model.fee.FeeModel;
import admission.services.KeyConstant;
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

/**
 *
 * @author Adeel, Yougeshwar Khatri
 */
public class FeeModelInternalFrame extends javax.swing.JInternalFrame {

    public FeeModelInternalFrame() {
        initComponents();

        admission.utils.Utility.hideOnEscape(this);
        this.titlePanel.setBorder(RoundedBorder.createGradientBorder());
        this.buttonsPanel.add(this.getOperationButtons());

        admission.utils.Utility.loadEnum(programTypeComboBox, ProgramTypeEnum.class);
        programTypeComboBox.removeItem(ProgramTypeEnum.BOTH);
        admission.utils.Utility.loadEnum(shiftComboBox, ShiftEnum.class);
        admission.utils.Utility.loadEnum(amountTypeComboBox, AmountTypeEnum.class);
        admission.utils.Utility.loadEnum(categoryTypeComboBox, FeeCategoryTypeEnum.class);

        this.modify = new ModificationManager();
        ((AbstractDocument) this.nameTextField.getDocument()).addDocumentListener(modify);
        ((AbstractDocument) this.remarksTextArea.getDocument()).addDocumentListener(modify);

        admission.utils.UppercaseDocumentFilter documentFilter = new admission.utils.UppercaseDocumentFilter();
        ((AbstractDocument) this.remarksTextArea.getDocument()).setDocumentFilter(documentFilter);

        if (CommonHelper.checkUserResourceAccess(KeyConstant.MODEL_MAPPING_FORM)) {
            modelMappingDialog = new ModelMappingDialog(this);
            modelMappingButton.setEnabled(true);
        }
    }

    private JPanel getOperationButtons() {
        operationButtons = new OperationButtons() {
            @Override
            public void saveOperation(ActionEvent evt) {
                FeeModelInternalFrame.this.save();
            }

            @Override
            public void deleteOperation(ActionEvent evt) {
                FeeModelInternalFrame.this.delete();
            }

            @Override
            public void newOperation(ActionEvent evt) {
                FeeModelInternalFrame.this.clear();
            }

            @Override
            public void backOperation(ActionEvent evt) {
                FeeModelInternalFrame.this.setVisible(false);
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
            getFeeModel();
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
        feeModelList = new javax.swing.JList();
        jLabel2 = new javax.swing.JLabel();
        programTypeComboBox = new javax.swing.JComboBox();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        shiftComboBox = new javax.swing.JComboBox();
        nameTextField = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        remarksTextArea = new javax.swing.JTextArea();
        buttonsPanel = new javax.swing.JPanel();
        titlePanel = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        searchJXSearchField = new org.jdesktop.swingx.JXSearchField();
        modelMappingButton = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        amountTypeComboBox = new javax.swing.JComboBox();
        jLabel7 = new javax.swing.JLabel();
        categoryTypeComboBox = new javax.swing.JComboBox();

        setClosable(true);
        setIconifiable(true);
        setTitle("Fee Model");

        jLabel1.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel1.setText("ID:");

        idTextField.setEditable(false);
        idTextField.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        feeModelList.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                feeModelListValueChanged(evt);
            }
        });
        jScrollPane1.setViewportView(feeModelList);

        jLabel2.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel2.setText("Program Type: ");

        programTypeComboBox.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        programTypeComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                programTypeComboBoxActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel3.setText("Shift:");

        jLabel4.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel4.setText("Name:");

        jLabel5.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel5.setText("Remarks: ");

        shiftComboBox.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        shiftComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                shiftComboBoxActionPerformed(evt);
            }
        });

        remarksTextArea.setColumns(20);
        remarksTextArea.setLineWrap(true);
        remarksTextArea.setWrapStyleWord(true);
        jScrollPane2.setViewportView(remarksTextArea);

        buttonsPanel.setPreferredSize(new java.awt.Dimension(335, 60));
        buttonsPanel.setLayout(new java.awt.BorderLayout());

        titlePanel.setLayout(new java.awt.BorderLayout());

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/admission/view/images/form-icon.png"))); // NOI18N
        jLabel8.setText("Fee Model");
        jLabel8.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, java.awt.Color.darkGray), javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5)));
        titlePanel.add(jLabel8, java.awt.BorderLayout.CENTER);

        searchJXSearchField.setInstantSearchDelay(0);
        searchJXSearchField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchJXSearchFieldActionPerformed(evt);
            }
        });

        modelMappingButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Gnome-Media-Playlist-Shuffle-32.png"))); // NOI18N
        modelMappingButton.setEnabled(false);
        modelMappingButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                modelMappingButtonActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel6.setText("Amount Type:");

        amountTypeComboBox.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        jLabel7.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel7.setText("Category Type:");

        categoryTypeComboBox.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(titlePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(modelMappingButton)
                        .addGap(20, 20, 20)
                        .addComponent(buttonsPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 324, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(programTypeComboBox, 0, 231, Short.MAX_VALUE)
                                    .addComponent(idTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(shiftComboBox, 0, 231, Short.MAX_VALUE)
                                    .addComponent(nameTextField)
                                    .addComponent(amountTypeComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(categoryTypeComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 282, Short.MAX_VALUE)
                            .addComponent(searchJXSearchField, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jLabel1, jLabel2});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(titlePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(buttonsPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE)
                    .addComponent(modelMappingButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(idTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(searchJXSearchField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(programTypeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(shiftComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(categoryTypeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(nameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(amountTypeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 64, Short.MAX_VALUE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 216, Short.MAX_VALUE))
                .addContainerGap())
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {idTextField, nameTextField, programTypeComboBox, shiftComboBox});

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void programTypeComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_programTypeComboBoxActionPerformed
        getFeeModel();
    }//GEN-LAST:event_programTypeComboBoxActionPerformed

    private void shiftComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_shiftComboBoxActionPerformed
        getFeeModel();
    }//GEN-LAST:event_shiftComboBoxActionPerformed

    private void searchJXSearchFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchJXSearchFieldActionPerformed
        String text = searchJXSearchField.getText();
        admission.utils.Utility.filterDataList(feeModelList, feeModelDataList, text);
    }//GEN-LAST:event_searchJXSearchFieldActionPerformed

    private void feeModelListValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_feeModelListValueChanged
        feeModel = (FeeModel) feeModelList.getSelectedValue();
        if (feeModel == null) {
            return;
        }

        categoryTypeComboBox.setSelectedItem(feeModel.getCategoryType());
        
        idTextField.setText(String.valueOf(feeModel.getId()));
        nameTextField.setText(feeModel.getName());
        amountTypeComboBox.setSelectedItem(feeModel.getAmountType());
        remarksTextArea.setText(feeModel.getRemarks());
        modify.setModify(false);
    }//GEN-LAST:event_feeModelListValueChanged

    private void modelMappingButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_modelMappingButtonActionPerformed
        if(feeModel == null) {
            MessageBox.error(this, MessageEnum.MSG_NO_SELECTED);
            return;
        }
        modelMappingDialog.setFeeModel(feeModel);
        modelMappingDialog.setVisible(true);
    }//GEN-LAST:event_modelMappingButtonActionPerformed

    private void getFeeModel() {
        feeModelDataList = new ArrayList<>();
        feeModelList.setListData(feeModelDataList.toArray());

        ProgramTypeEnum pt = (ProgramTypeEnum) programTypeComboBox.getSelectedItem();
        ShiftEnum shift = (ShiftEnum) shiftComboBox.getSelectedItem();
        if (shift == null || pt == null) {
            return;
        }

        feeModelDataList = DatabaseManager.getData(FeeModel.class, "programType = " + pt.ordinal() + " AND shift = " + shift.ordinal(), "name");
        feeModelList.setListData(feeModelDataList.toArray());
    }

    private void clear() {
        idTextField.setText("");
        nameTextField.setText("");
        remarksTextArea.setText("");
        feeModelList.clearSelection();
        nameTextField.requestFocus();
        modify.setModify(false);
    }

    private void save() {
        ProgramTypeEnum pt = (ProgramTypeEnum) programTypeComboBox.getSelectedItem();
        ShiftEnum shift = (ShiftEnum) shiftComboBox.getSelectedItem();

        if (pt == null || shift == null) {
            return;
        }

        FeeCategoryTypeEnum feeCategoryType = (FeeCategoryTypeEnum) categoryTypeComboBox.getSelectedItem();
        String name = nameTextField.getText();
        AmountTypeEnum amountType = (AmountTypeEnum) amountTypeComboBox.getSelectedItem();
        String remarks = remarksTextArea.getText();

        if (name.isEmpty()) {
            MessageBox.error(this, MessageEnum.MSG_29);
            return;
        }

        if (feeModel == null) {
            feeModel = new FeeModel();
        }

        feeModel.setProgramType(pt);
        feeModel.setShift(shift);
        feeModel.setCategoryType(feeCategoryType);
        feeModel.setName(name);
        feeModel.setAmountType(amountType);
        feeModel.setRemarks(remarks);

        try {
            DatabaseManager.save(feeModel);
            admission.utils.MessageBox.info(this, MessageEnum.MSG_SAVE);
            this.getFeeModel();
            clear();
        } catch (HibernateException he) {
            admission.utils.MessageBox.error(this, he);
        }
    }

    private void delete() {
        if (feeModel == null) {
            return;
        }

        try {
            DatabaseManager.deleteData(feeModel);
            admission.utils.MessageBox.info(this, MessageEnum.MSG_DELETE);
            this.getFeeModel();
            clear();
        } catch (HibernateException he) {
            admission.utils.MessageBox.error(this, he);
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox amountTypeComboBox;
    private javax.swing.JPanel buttonsPanel;
    private javax.swing.JComboBox categoryTypeComboBox;
    private javax.swing.JList feeModelList;
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
    private javax.swing.JButton modelMappingButton;
    private javax.swing.JTextField nameTextField;
    private javax.swing.JComboBox programTypeComboBox;
    private javax.swing.JTextArea remarksTextArea;
    private org.jdesktop.swingx.JXSearchField searchJXSearchField;
    private javax.swing.JComboBox shiftComboBox;
    private javax.swing.JPanel titlePanel;
    // End of variables declaration//GEN-END:variables
    private OperationButtons operationButtons;
    private List<FeeModel> feeModelDataList;
    private ModificationManager modify;
    private FeeModel feeModel;
    private ModelMappingDialog modelMappingDialog;
}
