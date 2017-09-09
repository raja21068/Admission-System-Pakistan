package admission.view.fees;

import admission.controller.DatabaseManager;
import admission.enums.FeeOfEnum;
import admission.enums.MessageEnum;
import admission.model.AdmissionYear;
import admission.model.fee.AdditionalFee;
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
public class AdditionalFeeInternalFrame extends javax.swing.JInternalFrame {

    public AdditionalFeeInternalFrame() {
        initComponents();

        admission.utils.Utility.hideOnEscape(this);

        this.titlePanel.setBorder(RoundedBorder.createGradientBorder());
        this.buttonsPanel.add(this.getOperationButtons());

        admission.utils.Utility.loadEnum(feeOfComboBox, FeeOfEnum.class);
        
        this.modify = new ModificationManager();
        ((AbstractDocument) this.feeFormattedTextField.getDocument()).addDocumentListener(modify);
        this.feeOfComboBox.addActionListener(modify);
        ((AbstractDocument) this.remarksTextArea.getDocument()).addDocumentListener(modify);

        admission.utils.UppercaseDocumentFilter documentFilter = new admission.utils.UppercaseDocumentFilter();
        ((AbstractDocument) this.remarksTextArea.getDocument()).setDocumentFilter(documentFilter);
    }

    private JPanel getOperationButtons() {
        operationButtons = new OperationButtons() {
            @Override
            public void saveOperation(ActionEvent evt) {
                AdditionalFeeInternalFrame.this.save();
            }

            @Override
            public void deleteOperation(ActionEvent evt) {
                AdditionalFeeInternalFrame.this.delete();
            }

            @Override
            public void newOperation(ActionEvent evt) {
                AdditionalFeeInternalFrame.this.clear();
            }

            @Override
            public void backOperation(ActionEvent evt) {
                AdditionalFeeInternalFrame.this.setVisible(false);
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
            getAdmissionYear();
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
        additionalFeeList = new javax.swing.JList();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        feeOfComboBox = new javax.swing.JComboBox();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        remarksTextArea = new javax.swing.JTextArea();
        jLabel7 = new javax.swing.JLabel();
        admissionYearComboBox = new javax.swing.JComboBox();
        feeFormattedTextField = new javax.swing.JFormattedTextField();
        titlePanel = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        buttonsPanel = new javax.swing.JPanel();
        searchJXSearchField = new org.jdesktop.swingx.JXSearchField();

        setClosable(true);
        setIconifiable(true);
        setTitle("Additional Fee");

        jLabel1.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel1.setText("ID:");

        idTextField.setEditable(false);
        idTextField.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        additionalFeeList.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                additionalFeeListValueChanged(evt);
            }
        });
        jScrollPane1.setViewportView(additionalFeeList);

        jLabel3.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel3.setText("Fee:");

        jLabel2.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel2.setText("Fee of:");

        feeOfComboBox.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        jLabel4.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel4.setText("Remarks:");

        remarksTextArea.setColumns(20);
        remarksTextArea.setLineWrap(true);
        remarksTextArea.setWrapStyleWord(true);
        jScrollPane2.setViewportView(remarksTextArea);

        jLabel7.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel7.setText("Admissin Year:");

        admissionYearComboBox.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        admissionYearComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                admissionYearComboBoxActionPerformed(evt);
            }
        });

        feeFormattedTextField.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
        feeFormattedTextField.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        titlePanel.setLayout(new java.awt.BorderLayout());

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/admission/view/images/form-icon.png"))); // NOI18N
        jLabel8.setText("Additional Fee");
        jLabel8.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, java.awt.Color.darkGray), javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5)));
        titlePanel.add(jLabel8, java.awt.BorderLayout.CENTER);

        buttonsPanel.setPreferredSize(new java.awt.Dimension(335, 60));
        buttonsPanel.setLayout(new java.awt.BorderLayout());

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
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(idTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(feeOfComboBox, 0, 241, Short.MAX_VALUE)
                                    .addComponent(admissionYearComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(feeFormattedTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jScrollPane2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 286, Short.MAX_VALUE)
                            .addComponent(searchJXSearchField, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(buttonsPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 626, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(titlePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(buttonsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(admissionYearComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(searchJXSearchField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(idTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(feeOfComboBox))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(feeFormattedTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 170, Short.MAX_VALUE))
                .addContainerGap())
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {admissionYearComboBox, feeFormattedTextField, feeOfComboBox, idTextField, searchJXSearchField});

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void admissionYearComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_admissionYearComboBoxActionPerformed
        getAdditionalFee();
    }//GEN-LAST:event_admissionYearComboBoxActionPerformed

    private void additionalFeeListValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_additionalFeeListValueChanged
        additionalFee = (AdditionalFee) additionalFeeList.getSelectedValue();
        if(additionalFee == null) return;
        
        idTextField.setText(String.valueOf(additionalFee.getId()));
        feeOfComboBox.setSelectedItem(additionalFee.getFeeOf());
        feeFormattedTextField.setText(String.valueOf(additionalFee.getFee()));
        remarksTextArea.setText(additionalFee.getRemarks());
        modify.setModify(false);
    }//GEN-LAST:event_additionalFeeListValueChanged

    private void searchJXSearchFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchJXSearchFieldActionPerformed
        String text = searchJXSearchField.getText();
        admission.utils.Utility.filterDataList(additionalFeeList, additionalFeeDataList, text);
    }//GEN-LAST:event_searchJXSearchFieldActionPerformed

    private void getAdmissionYear() {
        admissionYearComboBox.removeAllItems();
        List<AdmissionYear> list = DatabaseManager.getData(AdmissionYear.class, "year DESC");
        for (AdmissionYear ay : list) {
            admissionYearComboBox.addItem(ay);
        }
    }
    
    private void getAdditionalFee() {
        additionalFeeDataList = new ArrayList<>();
        additionalFeeList.setListData(additionalFeeDataList.toArray());
        
        AdmissionYear ay = (AdmissionYear) admissionYearComboBox.getSelectedItem();
        if(ay == null) return;
        
        additionalFeeDataList = DatabaseManager.getData(AdditionalFee.class, "admissionYearId = " + ay.getAdmissionYearId(), "id");
        additionalFeeList.setListData(additionalFeeDataList.toArray());
    }
    
    private void clear() {
        additionalFee = null;
        idTextField.setText("");
        feeOfComboBox.setSelectedIndex(-1);
        feeFormattedTextField.setText("");
        additionalFeeList.clearSelection();
        modify.setModify(false);
    }

    private void save() {
        AdmissionYear ay = (AdmissionYear) admissionYearComboBox.getSelectedItem();
        if(ay == null) return;
        
        FeeOfEnum feeOf = (FeeOfEnum) feeOfComboBox.getSelectedItem();
        Long fee = Long.parseLong(feeFormattedTextField.getText());
        String remarks = remarksTextArea.getText();
        
        if(additionalFee == null) {
            additionalFee = new AdditionalFee();
        }
        
        additionalFee.setAdmissionYearId(ay.getAdmissionYearId());
        additionalFee.setFeeOf(feeOf);
        additionalFee.setFee(fee);
        additionalFee.setRemarks(remarks);
        
        try {
            DatabaseManager.save(additionalFee);
            admission.utils.MessageBox.info(this, MessageEnum.MSG_SAVE);
            this.getAdditionalFee();
            clear();
        } catch (HibernateException he) {
            admission.utils.MessageBox.error(this, he);
        }
    }

    private void delete() {
        if (additionalFee == null) {
            return;
        }

        try {
            DatabaseManager.deleteData(additionalFee);
            admission.utils.MessageBox.info(this, MessageEnum.MSG_DELETE);
            this.getAdditionalFee();
            clear();
        } catch (HibernateException he) {
            admission.utils.MessageBox.error(this, he);
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JList additionalFeeList;
    private javax.swing.JComboBox admissionYearComboBox;
    private javax.swing.JPanel buttonsPanel;
    private javax.swing.JFormattedTextField feeFormattedTextField;
    private javax.swing.JComboBox feeOfComboBox;
    private javax.swing.JTextField idTextField;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea remarksTextArea;
    private org.jdesktop.swingx.JXSearchField searchJXSearchField;
    private javax.swing.JPanel titlePanel;
    // End of variables declaration//GEN-END:variables
    private OperationButtons operationButtons;
    private List<AdditionalFee> additionalFeeDataList;
    private ModificationManager modify;
    private AdditionalFee additionalFee;
}
