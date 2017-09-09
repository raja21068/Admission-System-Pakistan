package admission.view.maintain;

import admission.controller.DatabaseManager;
import admission.enums.MessageEnum;
import admission.enums.ProvinceEnum;
import admission.model.security.Resources;
import admission.model.Province;
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
public class ProvinceInternalFrame extends javax.swing.JInternalFrame {

    public ProvinceInternalFrame() {
        initComponents();
        
        admission.utils.Utility.hideOnEscape(this);
        
        this.titlePanel.setBorder(RoundedBorder.createGradientBorder());
        this.buttonsPanel.add(this.getOperationButtons());
        
        admission.utils.Utility.loadEnum(codeComboBox, ProvinceEnum.class);
        
        this.modify = new ModificationManager();
        ((AbstractDocument) this.provinceTextField.getDocument()).addDocumentListener(modify);
        codeComboBox.addActionListener(modify);
        ((AbstractDocument) this.remarksTextArea.getDocument()).addDocumentListener(modify);

        documentFilter = new admission.utils.UppercaseDocumentFilter();
        ((AbstractDocument) this.provinceTextField.getDocument()).setDocumentFilter(documentFilter);
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
                ProvinceInternalFrame.this.save();
            }
            @Override public void deleteOperation(ActionEvent evt) {
                ProvinceInternalFrame.this.delete();
            }
            @Override public void newOperation(ActionEvent evt) {
                ProvinceInternalFrame.this.clear();
            }
            @Override public void backOperation(ActionEvent evt) {
                ProvinceInternalFrame.this.setVisible(false);
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
        provinceIdTextField = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        provinceTextField = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        provinceList = new javax.swing.JList();
        jScrollPane2 = new javax.swing.JScrollPane();
        remarksTextArea = new javax.swing.JTextArea();
        jLabel5 = new javax.swing.JLabel();
        titlePanel = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        buttonsPanel = new javax.swing.JPanel();
        searchJXSearchField = new org.jdesktop.swingx.JXSearchField();
        codeComboBox = new javax.swing.JComboBox();

        setClosable(true);
        setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);
        setIconifiable(true);
        setTitle("Province");
        setToolTipText("");

        jLabel2.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel2.setText("Province ID:");

        provinceIdTextField.setEditable(false);
        provinceIdTextField.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel3.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel3.setText("Province:");

        jLabel4.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel4.setText("Remarks");

        provinceList.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                provinceListValueChanged(evt);
            }
        });
        jScrollPane1.setViewportView(provinceList);

        remarksTextArea.setColumns(20);
        remarksTextArea.setRows(5);
        jScrollPane2.setViewportView(remarksTextArea);

        jLabel5.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel5.setText("Code:");

        titlePanel.setLayout(new java.awt.BorderLayout());

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/admission/view/images/form-icon.png"))); // NOI18N
        jLabel6.setText("Province");
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

        codeComboBox.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(titlePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(buttonsPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel4)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(provinceIdTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(provinceTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 296, Short.MAX_VALUE)
                                    .addComponent(codeComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 365, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane1)
                            .addComponent(searchJXSearchField, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(0, 0, Short.MAX_VALUE)))
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
                    .addComponent(jLabel2)
                    .addComponent(provinceIdTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(searchJXSearchField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(provinceTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(codeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {codeComboBox, provinceIdTextField, provinceTextField, searchJXSearchField});

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void provinceListValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_provinceListValueChanged
        Province province = (Province) this.provinceList.getSelectedValue();
        if(province == null) return;
        
        this.provinceIdTextField.setText(String.valueOf(province.getProvinceId()));
        this.provinceTextField.setText(province.getName());
        this.codeComboBox.setSelectedItem(province.getCode());
        this.remarksTextArea.setText(province.getRemarks());
        modify.setModify(false);
    }//GEN-LAST:event_provinceListValueChanged

    private void searchJXSearchFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchJXSearchFieldActionPerformed
        // TODO add your handling code here:
        String text = searchJXSearchField.getText();
        admission.utils.Utility.filterDataList(provinceList, provinceDataList, text);
    }//GEN-LAST:event_searchJXSearchFieldActionPerformed

    private void getProvince(){
        provinceDataList = DatabaseManager.getData(Province.class.getName(), "name");
        this.provinceList.setListData(provinceDataList.toArray());
    }

    private void save() {
        Province province = (Province) this.provinceList.getSelectedValue();
        if(province == null) {
            province = new Province();
        }
        
        String name = this.provinceTextField.getText();
        ProvinceEnum code = (ProvinceEnum) this.codeComboBox.getSelectedItem();
        String remarks = this.remarksTextArea.getText();
        if(name.isEmpty()) return;
        
        province.setName(name);
        province.setCode(code);
        province.setRemarks(remarks);
        
        try{
            DatabaseManager.updateData(province);
            admission.utils.MessageBox.info(this, MessageEnum.MSG_SAVE);
            this.getProvince();
            clear();
        }catch(HibernateException he){
            admission.utils.MessageBox.error(this, he);
        }
    }
    
    private void delete() {
        Province province = (Province) this.provinceList.getSelectedValue();
        if(province == null) return;
        
        try{
            DatabaseManager.deleteData(Province.class.getName(), "provinceId=" + province.getProvinceId());
            admission.utils.MessageBox.info(this, MessageEnum.MSG_DELETE);
            this.getProvince();
            clear();
        }catch(HibernateException he){
            admission.utils.MessageBox.error(this, he);
        }
    }
    
    private void clear(){
        this.provinceIdTextField.setText("");
        this.provinceTextField.setText("");
        this.codeComboBox.setSelectedIndex(-1);
        this.remarksTextArea.setText("");
        this.provinceList.clearSelection();
        modify.setModify(false);
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel buttonsPanel;
    private javax.swing.JComboBox codeComboBox;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField provinceIdTextField;
    private javax.swing.JList provinceList;
    private javax.swing.JTextField provinceTextField;
    private javax.swing.JTextArea remarksTextArea;
    private org.jdesktop.swingx.JXSearchField searchJXSearchField;
    private javax.swing.JPanel titlePanel;
    // End of variables declaration//GEN-END:variables
    private DocumentFilter documentFilter;
    private Resources privileges;
    private OperationButtons operationButtons;
    private List<Province> provinceDataList;
    private ModificationManager modify;
}
