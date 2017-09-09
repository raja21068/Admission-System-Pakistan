package admission.view.campus;

import admission.controller.DatabaseManager;
import admission.enums.MessageEnum;
import admission.model.Campus;
import admission.model.security.Resources;
import com.yog.component.OperationButtons;
import java.awt.event.ActionEvent;
import java.util.List;
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
public class CampusInternalFrame extends javax.swing.JInternalFrame {

    public CampusInternalFrame() {
        initComponents();

        campusDisplayOrderDialog = new CampusDisplayOrderDialog(this);

        admission.utils.Utility.hideOnEscape(this);
        
        this.titlePanel.setBorder(RoundedBorder.createGradientBorder());
        this.buttonsPanel.add(this.getOperationButtons());
        
        this.modify = new ModificationManager();
        ((AbstractDocument) this.nameTextField.getDocument()).addDocumentListener(modify);
        ((AbstractDocument) this.locationTextField.getDocument()).addDocumentListener(modify);
        ((AbstractDocument) this.codeTextField.getDocument()).addDocumentListener(modify);
        ((AbstractDocument) this.remarksTextArea.getDocument()).addDocumentListener(modify);

        documentFilter = new admission.utils.UppercaseDocumentFilter();
        ((AbstractDocument) this.nameTextField.getDocument()).setDocumentFilter(documentFilter);
        ((AbstractDocument) this.locationTextField.getDocument()).setDocumentFilter(documentFilter);
        ((AbstractDocument) this.codeTextField.getDocument()).setDocumentFilter(documentFilter);
        ((AbstractDocument) this.remarksTextArea.getDocument()).setDocumentFilter(documentFilter);
    }

    @Override
    public void setVisible(boolean aFlag) {
        if (aFlag) {
            if (modify != null) {
                this.modify.setModifyLabel(operationButtons.getModifyLabel());
            }
            this.getCampus();
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

    public void setPrivileges(Resources privileges) {
        this.privileges = privileges;
//        this.addButton.setEnabled((privileges.isAddPrivilige()));
//        this.updateButton.setEnabled((privileges.isUpdatePrivilige()));
//        this.deleteButton.setEnabled((privileges.isDeletePrivilige()));
    }

    private JPanel getOperationButtons() {
        operationButtons = new OperationButtons() {
            @Override
            public void saveOperation(ActionEvent evt) {
                CampusInternalFrame.this.save();
            }

            @Override
            public void deleteOperation(ActionEvent evt) {
                CampusInternalFrame.this.delete();
            }

            @Override
            public void newOperation(ActionEvent evt) {
                CampusInternalFrame.this.clear();
            }

            @Override
            public void backOperation(ActionEvent evt) {
                CampusInternalFrame.this.setVisible(false);
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
        campusIdTextField = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        nameTextField = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        campusList = new javax.swing.JList();
        jScrollPane2 = new javax.swing.JScrollPane();
        remarksTextArea = new javax.swing.JTextArea();
        locationTextField = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        displayOrderButton = new javax.swing.JButton();
        isMainCheckBox = new javax.swing.JCheckBox();
        jLabel6 = new javax.swing.JLabel();
        codeTextField = new javax.swing.JTextField();
        titlePanel = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        buttonsPanel = new javax.swing.JPanel();
        searchJXSearchField = new org.jdesktop.swingx.JXSearchField();

        setClosable(true);
        setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);
        setIconifiable(true);
        setTitle("Campus");

        jLabel2.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel2.setText("Campus ID:");

        campusIdTextField.setEditable(false);
        campusIdTextField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        campusIdTextField.setNextFocusableComponent(nameTextField);
        campusIdTextField.setPreferredSize(new java.awt.Dimension(6, 25));

        jLabel3.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel3.setText("Campus:");

        nameTextField.setPreferredSize(new java.awt.Dimension(6, 25));

        jLabel4.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel4.setText("Remarks");

        campusList.setNextFocusableComponent(displayOrderButton);
        campusList.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                campusListValueChanged(evt);
            }
        });
        jScrollPane1.setViewportView(campusList);

        remarksTextArea.setColumns(20);
        remarksTextArea.setLineWrap(true);
        remarksTextArea.setRows(5);
        remarksTextArea.setWrapStyleWord(true);
        remarksTextArea.setNextFocusableComponent(campusList);
        jScrollPane2.setViewportView(remarksTextArea);

        locationTextField.setPreferredSize(new java.awt.Dimension(6, 25));

        jLabel5.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel5.setText("Location:");

        displayOrderButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/order.png"))); // NOI18N
        displayOrderButton.setToolTipText("Display Order");
        displayOrderButton.setFocusPainted(false);
        displayOrderButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                displayOrderButtonActionPerformed(evt);
            }
        });

        isMainCheckBox.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        isMainCheckBox.setText("Is Main Campus");

        jLabel6.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel6.setText("Code:");

        titlePanel.setLayout(new java.awt.BorderLayout());

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/admission/view/images/form-icon.png"))); // NOI18N
        jLabel7.setText("Campus");
        jLabel7.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, java.awt.Color.darkGray), javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5)));
        titlePanel.add(jLabel7, java.awt.BorderLayout.PAGE_END);

        buttonsPanel.setPreferredSize(new java.awt.Dimension(335, 60));
        buttonsPanel.setLayout(new java.awt.BorderLayout());

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
                                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(locationTextField, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(campusIdTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(nameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(codeTextField)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(isMainCheckBox)
                                                .addGap(50, 50, 50)))
                                        .addGap(0, 0, Short.MAX_VALUE)))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 196, Short.MAX_VALUE)
                            .addComponent(searchJXSearchField, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
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
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(buttonsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(displayOrderButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2)
                        .addComponent(campusIdTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(searchJXSearchField, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(nameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(locationTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(codeTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(isMainCheckBox))
                        .addGap(12, 12, 12)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {campusIdTextField, codeTextField, isMainCheckBox, locationTextField, nameTextField, searchJXSearchField});

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void campusListValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_campusListValueChanged
        Campus campus = (Campus) this.campusList.getSelectedValue();
        if (campus == null) {
            return;
        }

        this.campusIdTextField.setText(String.valueOf(campus.getCampusId()));
        this.nameTextField.setText(campus.getName());
        this.locationTextField.setText(campus.getLocation());
        this.codeTextField.setText(campus.getCode());
        this.isMainCheckBox.setSelected((campus.getIsMain()));
        this.remarksTextArea.setText(campus.getRemarks());
        modify.setModify(false);
    }//GEN-LAST:event_campusListValueChanged

    private void displayOrderButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_displayOrderButtonActionPerformed
        campusDisplayOrderDialog.setVisible(true);
    }//GEN-LAST:event_displayOrderButtonActionPerformed

    private void searchJXSearchFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchJXSearchFieldActionPerformed
        String text = searchJXSearchField.getText();
        admission.utils.Utility.filterDataList(campusList, campusDataList, text);
    }//GEN-LAST:event_searchJXSearchFieldActionPerformed

    private void getCampus() {
        campusDataList = DatabaseManager.getData(Campus.class.getName(), "name");
        this.campusList.setListData(campusDataList.toArray());
    }

    private void save() {
        String name = this.nameTextField.getText();
        String location = this.locationTextField.getText();
        String code = this.codeTextField.getText();
        boolean isMain = (this.isMainCheckBox.isSelected());
        String remarks = this.remarksTextArea.getText();
        if (name.isEmpty() || location.isEmpty()) {
            return;
        }

        Campus campus = (Campus) this.campusList.getSelectedValue();
        if (campus == null) {
            campus = new Campus();
            campus.setDisplayOrder(0);
        }

        campus.setName(name);
        campus.setLocation(location);
        campus.setCode(code);
        campus.setIsMain(isMain);
        campus.setRemarks(remarks);

        try {
            DatabaseManager.save(campus);
            admission.utils.MessageBox.info(this, MessageEnum.MSG_SAVE);
            this.getCampus();
            clear();
        } catch (HibernateException he) {
            admission.utils.MessageBox.error(this, he);
        }
    }

    private void delete() {
        Campus campus = (Campus) this.campusList.getSelectedValue();
        if (campus == null) {
            return;
        }

        try {
            DatabaseManager.deleteData(Campus.class.getName(), "campusId = " + campus.getCampusId());
            admission.utils.MessageBox.info(this, MessageEnum.MSG_DELETE);
            this.getCampus();
            clear();
        } catch (HibernateException he) {
            admission.utils.MessageBox.error(this, he);
        }
    }

    private void clear() {
        this.campusIdTextField.setText("");
        this.nameTextField.setText("");
        this.locationTextField.setText("");
        this.codeTextField.setText("");
        this.isMainCheckBox.setSelected(false);
        this.remarksTextArea.setText("");
        this.campusList.clearSelection();
        this.nameTextField.requestFocus();
        modify.setModify(false);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel buttonsPanel;
    private javax.swing.JTextField campusIdTextField;
    private javax.swing.JList campusList;
    private javax.swing.JTextField codeTextField;
    private javax.swing.JButton displayOrderButton;
    private javax.swing.JCheckBox isMainCheckBox;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField locationTextField;
    private javax.swing.JTextField nameTextField;
    private javax.swing.JTextArea remarksTextArea;
    private org.jdesktop.swingx.JXSearchField searchJXSearchField;
    private javax.swing.JPanel titlePanel;
    // End of variables declaration//GEN-END:variables
    private DocumentFilter documentFilter;
    private Resources privileges;
    private CampusDisplayOrderDialog campusDisplayOrderDialog;
    private OperationButtons operationButtons;
    private List<Campus> campusDataList;
    private ModificationManager modify;
}
