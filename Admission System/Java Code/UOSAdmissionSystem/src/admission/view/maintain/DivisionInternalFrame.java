package admission.view.maintain;

import admission.controller.DatabaseManager;
import admission.enums.MessageEnum;
import admission.model.Division;
import admission.model.security.Resources;
import admission.model.Province;
import com.yog.component.OperationButtons;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
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
public class DivisionInternalFrame extends javax.swing.JInternalFrame {

    public DivisionInternalFrame() {
        initComponents();
        
        admission.utils.Utility.hideOnEscape(this);
        this.titlePanel.setBorder(RoundedBorder.createGradientBorder());
        this.buttonsPanel.add(this.getOperationButtons());
        
        this.modify = new ModificationManager();
        ((AbstractDocument) this.divisionTextField.getDocument()).addDocumentListener(modify);
        ((AbstractDocument) this.remarksTextArea.getDocument()).addDocumentListener(modify);
        
        documentFilter = new admission.utils.UppercaseDocumentFilter();
        ((AbstractDocument) this.divisionTextField.getDocument()).setDocumentFilter(documentFilter);
        ((AbstractDocument) this.remarksTextArea.getDocument()).setDocumentFilter(documentFilter);
    }

    @Override
    public void setVisible(boolean aFlag) {
        if(aFlag) {
            if (modify != null) this.modify.setModifyLabel(operationButtons.getModifyLabel());
            this.getProvince();
        }  else {
            if (modify != null && modify.isModify()) {
                int v = MessageBox.confirm3(this, MessageEnum.MSG_SAVE_QUESTION);
                if (v == JOptionPane.YES_OPTION) save();
                else if (v == JOptionPane.CANCEL_OPTION) return;
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
        operationButtons = new OperationButtons(){
            @Override public void saveOperation(ActionEvent evt) {
                DivisionInternalFrame.this.save();
            }
            @Override public void deleteOperation(ActionEvent evt) {
                DivisionInternalFrame.this.delete();
            }
            @Override public void newOperation(ActionEvent evt) {
                DivisionInternalFrame.this.clear();
            }
            @Override public void backOperation(ActionEvent evt) {
                DivisionInternalFrame.this.setVisible(false);
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
        divisionIdTextField = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        divisionTextField = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        divisionList = new javax.swing.JList();
        jScrollPane2 = new javax.swing.JScrollPane();
        remarksTextArea = new javax.swing.JTextArea();
        provinceComboBox = new javax.swing.JComboBox();
        jLabel5 = new javax.swing.JLabel();
        titlePanel = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        buttonsPanel = new javax.swing.JPanel();
        searchJXSearchField = new org.jdesktop.swingx.JXSearchField();

        setClosable(true);
        setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);
        setIconifiable(true);
        setTitle("Division");
        setToolTipText("Division");

        jLabel2.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel2.setText("Division ID:");

        divisionIdTextField.setEditable(false);
        divisionIdTextField.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel3.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel3.setText("Division:");

        jLabel4.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel4.setText("Remarks");

        divisionList.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                divisionListValueChanged(evt);
            }
        });
        jScrollPane1.setViewportView(divisionList);

        remarksTextArea.setColumns(20);
        remarksTextArea.setRows(5);
        jScrollPane2.setViewportView(remarksTextArea);

        provinceComboBox.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        provinceComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                provinceComboBoxActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel5.setText("Province:");

        titlePanel.setLayout(new java.awt.BorderLayout());

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/admission/view/images/form-icon.png"))); // NOI18N
        jLabel6.setText("Division");
        jLabel6.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, java.awt.Color.darkGray), javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5)));
        titlePanel.add(jLabel6, java.awt.BorderLayout.CENTER);

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
                            .addComponent(jLabel4)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(divisionTextField)
                                    .addComponent(divisionIdTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(provinceComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 294, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jScrollPane2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 1, Short.MAX_VALUE))
                            .addComponent(searchJXSearchField, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(buttonsPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                    .addComponent(provinceComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(searchJXSearchField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(divisionIdTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(divisionTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {divisionIdTextField, divisionTextField, provinceComboBox, searchJXSearchField});

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void divisionListValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_divisionListValueChanged
        Division division = (Division) this.divisionList.getSelectedValue();
        if(division == null) return;
        
        this.divisionIdTextField.setText(String.valueOf(division.getDivisionId()));
        this.divisionTextField.setText(division.getName());
        this.remarksTextArea.setText(division.getRemarks());
        modify.setModify(false);
    }//GEN-LAST:event_divisionListValueChanged

    private void provinceComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_provinceComboBoxActionPerformed
        getDivision();
    }//GEN-LAST:event_provinceComboBoxActionPerformed

    private void searchJXSearchFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchJXSearchFieldActionPerformed
        // TODO add your handling code here:
        String text = searchJXSearchField.getText();
        admission.utils.Utility.filterDataList(divisionList, divisionDataList, text);
    }//GEN-LAST:event_searchJXSearchFieldActionPerformed

    private void getProvince(){
        provinceComboBox.removeAllItems();
        
        List<Province> list = DatabaseManager.getData(Province.class.getName(), "name");
        for (Province province : list) {
            provinceComboBox.addItem(province);
        }
    }

    private void getDivision(){
        divisionDataList.clear();
        this.divisionList.setListData(divisionDataList.toArray());
        
        Province province = (Province) this.provinceComboBox.getSelectedItem();
        if(province == null) return;

        
        divisionDataList.addAll(province.getDivisions());//Arrays.asList((Division[]) province.getDivisions().toArray());
        this.divisionList.setListData(divisionDataList.toArray());
    }

    private void save() {
        Province province = (Province) this.provinceComboBox.getSelectedItem();
        Division division = (Division) this.divisionList.getSelectedValue();
        if(province == null) return;
        if(division == null) {
            division = new Division();
        }
        
        String name = this.divisionTextField.getText();
        String remarks = this.remarksTextArea.getText();
        if(name.isEmpty()) return;
        
        division.setProvince(province);
        division.setName(name);
        division.setRemarks(remarks);
        
        try{
            DatabaseManager.save(division);
            admission.utils.MessageBox.info(this, MessageEnum.MSG_SAVE);
            DatabaseManager.refresh(province);
            this.getDivision();
            clear();
        }catch(HibernateException he){
            admission.utils.MessageBox.error(this, he);
        }
    }
    private void delete() {
        Province province = (Province) this.provinceComboBox.getSelectedItem();
        Division division = (Division) this.divisionList.getSelectedValue();
        if(province == null || division == null) return;
        
        try{
            DatabaseManager.deleteData(Division.class.getName(), "divisionId=" + division.getDivisionId());
            admission.utils.MessageBox.info(this, MessageEnum.MSG_DELETE);
            DatabaseManager.refresh(province);
            this.getDivision();
            clear();
        }catch(HibernateException he){
            admission.utils.MessageBox.error(this, he);
        }
    }
    
    private void clear(){
        this.divisionIdTextField.setText("");
        this.divisionTextField.setText("");
        this.remarksTextArea.setText("");
        this.divisionList.clearSelection();
        modify.setModify(false);
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel buttonsPanel;
    private javax.swing.JTextField divisionIdTextField;
    private javax.swing.JList divisionList;
    private javax.swing.JTextField divisionTextField;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JComboBox provinceComboBox;
    private javax.swing.JTextArea remarksTextArea;
    private org.jdesktop.swingx.JXSearchField searchJXSearchField;
    private javax.swing.JPanel titlePanel;
    // End of variables declaration//GEN-END:variables
    private DocumentFilter documentFilter;
    private Resources privileges;
    private OperationButtons operationButtons;
    private List<Division> divisionDataList = new ArrayList<>();
    private ModificationManager modify;
}
