package admission.view.maintain;

import admission.controller.DatabaseManager;
import admission.enums.MessageEnum;
import admission.model.Campus;
import admission.model.District;
import admission.model.Jurisdiction;
import admission.model.security.Resources;
import admission.model.Province;
import admission.model.Taluka;
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
import admission.utils.Sorter;

/**
 *
 * @author Raja Kumar & Jay Kumar
 */
public class JurisdictionInternalFrame extends javax.swing.JInternalFrame {

    public JurisdictionInternalFrame() {
        initComponents();
        
        admission.utils.Utility.hideOnEscape(this);
        
        this.titlePanel.setBorder(RoundedBorder.createGradientBorder());
        this.buttonsPanel.add(this.getOperationButtons());
        
        this.modify = new ModificationManager();
        this.jusrisdictionCheckBox.addActionListener(modify);
        this.byTalukaCheckBox.addActionListener(modify);
        
        documentFilter = new admission.utils.UppercaseDocumentFilter();
        ((AbstractDocument) this.remarksTextArea.getDocument()).setDocumentFilter(documentFilter);
    }
    
    @Override
    public void setVisible(boolean aFlag) {
        if(aFlag) {
            if (modify != null) this.modify.setModifyLabel(operationButtons.getModifyLabel());
            this.getCampus();
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
                JurisdictionInternalFrame.this.save();
            }
            @Override public void deleteOperation(ActionEvent evt) {
                JurisdictionInternalFrame.this.delete();
            }
            @Override public void newOperation(ActionEvent evt) {
                JurisdictionInternalFrame.this.clear();
            }
            @Override public void backOperation(ActionEvent evt) {
                JurisdictionInternalFrame.this.setVisible(false);
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
        jurisdictionIdTextField = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jurisdictionList = new javax.swing.JList();
        jScrollPane2 = new javax.swing.JScrollPane();
        remarksTextArea = new javax.swing.JTextArea();
        jLabel5 = new javax.swing.JLabel();
        districtComboBox = new javax.swing.JComboBox();
        campusComboBox = new javax.swing.JComboBox();
        jLabel7 = new javax.swing.JLabel();
        jusrisdictionCheckBox = new javax.swing.JCheckBox();
        jLabel8 = new javax.swing.JLabel();
        provinceComboBox = new javax.swing.JComboBox();
        byTalukaCheckBox = new javax.swing.JCheckBox();
        jLabel9 = new javax.swing.JLabel();
        talukaComboBox = new javax.swing.JComboBox();
        titlePanel = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        buttonsPanel = new javax.swing.JPanel();
        searchJXSearchField = new org.jdesktop.swingx.JXSearchField();

        setClosable(true);
        setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);
        setTitle("Jurisdiction");

        jLabel2.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel2.setText("Jurisdiction ID:");

        jurisdictionIdTextField.setEditable(false);
        jurisdictionIdTextField.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel4.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel4.setText("Remarks");

        jurisdictionList.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                jurisdictionListValueChanged(evt);
            }
        });
        jScrollPane1.setViewportView(jurisdictionList);

        remarksTextArea.setColumns(20);
        remarksTextArea.setRows(5);
        jScrollPane2.setViewportView(remarksTextArea);

        jLabel5.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel5.setText("District:");

        districtComboBox.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        districtComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                districtComboBoxActionPerformed(evt);
            }
        });

        campusComboBox.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        campusComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                campusComboBoxActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel7.setText("Campus:");

        jusrisdictionCheckBox.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jusrisdictionCheckBox.setText("Jurisdiction");

        jLabel8.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel8.setText("Province:");

        provinceComboBox.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        provinceComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                provinceComboBoxActionPerformed(evt);
            }
        });

        byTalukaCheckBox.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        byTalukaCheckBox.setText("By Taluka");

        jLabel9.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel9.setText("Taluka:");

        talukaComboBox.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        titlePanel.setLayout(new java.awt.BorderLayout());

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/admission/view/images/form-icon.png"))); // NOI18N
        jLabel6.setText("Jurisdiction");
        jLabel6.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, java.awt.Color.darkGray), javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5)));
        titlePanel.add(jLabel6, java.awt.BorderLayout.PAGE_END);

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
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(districtComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(provinceComboBox, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(talukaComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jurisdictionIdTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jusrisdictionCheckBox)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(byTalukaCheckBox)
                                        .addGap(0, 23, Short.MAX_VALUE))
                                    .addComponent(campusComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 218, Short.MAX_VALUE)
                            .addComponent(searchJXSearchField, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(0, 0, Short.MAX_VALUE))
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
                    .addComponent(campusComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(searchJXSearchField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(provinceComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(districtComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(talukaComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(jurisdictionIdTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jusrisdictionCheckBox)
                            .addComponent(byTalukaCheckBox))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {campusComboBox, districtComboBox, jurisdictionIdTextField, provinceComboBox, searchJXSearchField, talukaComboBox});

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jurisdictionListValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_jurisdictionListValueChanged
        Jurisdiction jurisdiction = (Jurisdiction) this.jurisdictionList.getSelectedValue();
        if(jurisdiction == null) return;
        
        this.provinceComboBox.setSelectedItem(jurisdiction.getDistrict().getDivision().getProvince());
        this.districtComboBox.setSelectedItem(jurisdiction.getDistrict());
        this.jurisdictionIdTextField.setText(String.valueOf(jurisdiction.getJurisdictionId()));
        this.jusrisdictionCheckBox.setSelected((jurisdiction.getIsJurisdiction()));
        if(jurisdiction.getTaluka() != null){
            this.byTalukaCheckBox.setSelected(true);
            this.talukaComboBox.setSelectedItem(jurisdiction.getTaluka());
        }else 
            this.byTalukaCheckBox.setSelected(false);
        this.remarksTextArea.setText(jurisdiction.getRemarks());
        modify.setModify(false);
    }//GEN-LAST:event_jurisdictionListValueChanged

    private void provinceComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_provinceComboBoxActionPerformed
        getDistrict();
    }//GEN-LAST:event_provinceComboBoxActionPerformed

    private void districtComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_districtComboBoxActionPerformed
        // TODO add your handling code here:
        this.getTaluka();
    }//GEN-LAST:event_districtComboBoxActionPerformed

    private void campusComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_campusComboBoxActionPerformed
        // TODO add your handling code here:
        this.getJurisdiction();
    }//GEN-LAST:event_campusComboBoxActionPerformed

    private void searchJXSearchFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchJXSearchFieldActionPerformed
        // TODO add your handling code here:
        String text = searchJXSearchField.getText();        
        admission.utils.Utility.filterDataList(jurisdictionList, jurisdictionDataList, text);
    }//GEN-LAST:event_searchJXSearchFieldActionPerformed
    
    private void getCampus(){
        this.campusComboBox.removeAllItems();
        
        List<Campus> list = DatabaseManager.getData(Campus.class.getName(), "name");
        
        for (Campus list1 : list) {
            this.campusComboBox.addItem(list1);
        }
    }
    
    private void getProvince(){
        provinceComboBox.removeAllItems();
        
        List<Province> list = DatabaseManager.getData(Province.class.getName(), "provinceId");
        for (Province list1 : list) {
            provinceComboBox.addItem(list1);
        }
    }

    private void getDistrict(){
        this.districtComboBox.removeAllItems();
        
        Province province = (Province) this.provinceComboBox.getSelectedItem();
        if(province == null) return;
        
        List<District> districts = DatabaseManager.getProvinceDistricts(province.getProvinceId());
        for (District district : districts) {
            this.districtComboBox.addItem(district);
        }
    }

    private void getTaluka(){
        this.talukaComboBox.removeAllItems();
        
        District district = (District) this.districtComboBox.getSelectedItem();
        if(district == null) return;
        
        Set set = district.getTalukas();
        if(set == null) return;
        
        List list = Sorter.sort(set);
        
        Iterator it = list.iterator();
        while(it.hasNext()){
            this.talukaComboBox.addItem(it.next());
        }
    }
    
    private void getJurisdiction(){
        jurisdictionDataList.clear();
        jurisdictionList.setListData(jurisdictionDataList.toArray());
        
        Campus campus = (Campus) this.campusComboBox.getSelectedItem();
        if(campus == null) return;
        
        Set<Jurisdiction> set = campus.getJurisdictions();
        if(set == null) return;
        
        jurisdictionDataList = Sorter.sort(set);
        
        this.jurisdictionList.setListData(jurisdictionDataList.toArray());
    }
    
    private void save() {
        District district = (District) this.districtComboBox.getSelectedItem();
        Taluka taluka = (Taluka) this.talukaComboBox.getSelectedItem();
        Campus campus = (Campus) this.campusComboBox.getSelectedItem();
        Jurisdiction jurisdiction = (Jurisdiction) jurisdictionList.getSelectedValue();
        if(district == null || campus == null) return;
        if(jurisdiction == null) {
            jurisdiction = new Jurisdiction();
        }
        
        boolean isJurisdiction = (this.jusrisdictionCheckBox.isSelected());
        String remarks = this.remarksTextArea.getText();
        
        if(this.byTalukaCheckBox.isSelected() && taluka == null){
            admission.utils.MessageBox.info(this, "Taluka not found");
            return;
        }else{
            taluka = null;
        }
        
        jurisdiction.setDistrict(district);
        jurisdiction.setTaluka(taluka);
        jurisdiction.setCampus(campus);
        jurisdiction.setIsJurisdiction(isJurisdiction);
        jurisdiction.setRemarks(remarks);
        try{
            DatabaseManager.save(jurisdiction);
            admission.utils.MessageBox.info(this, MessageEnum.MSG_SAVE);
            DatabaseManager.refresh(district);
            DatabaseManager.refresh(campus);
            if(taluka != null) DatabaseManager.refresh(taluka);
            this.getJurisdiction();
            clear();
        }catch(HibernateException he){
            admission.utils.MessageBox.error(this, he);
        }
    }
    
    private void delete() {
        District district = (District) this.districtComboBox.getSelectedItem();
        Taluka taluka = (Taluka) this.talukaComboBox.getSelectedItem();
        Campus campus = (Campus) this.campusComboBox.getSelectedItem();
        Jurisdiction jurisdiction = (Jurisdiction) jurisdictionList.getSelectedValue();
        if(district == null || campus == null || jurisdiction == null) return;
        
        try{
            DatabaseManager.deleteData(Jurisdiction.class.getName(), "jurisdictionId = " + jurisdiction.getJurisdictionId());
            admission.utils.MessageBox.info(this, MessageEnum.MSG_DELETE);
            DatabaseManager.refresh(district);
            DatabaseManager.refresh(campus);
            if(taluka != null) DatabaseManager.refresh(taluka);
            this.getJurisdiction();
            clear();
        }catch(HibernateException he){
            admission.utils.MessageBox.error(this, he);
        }
    }
    
    private void clear(){
        this.jurisdictionIdTextField.setText("");
        this.remarksTextArea.setText("");
        this.jurisdictionList.clearSelection();
        modify.setModify(false);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel buttonsPanel;
    private javax.swing.JCheckBox byTalukaCheckBox;
    private javax.swing.JComboBox campusComboBox;
    private javax.swing.JComboBox districtComboBox;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField jurisdictionIdTextField;
    private javax.swing.JList jurisdictionList;
    private javax.swing.JCheckBox jusrisdictionCheckBox;
    private javax.swing.JComboBox provinceComboBox;
    private javax.swing.JTextArea remarksTextArea;
    private org.jdesktop.swingx.JXSearchField searchJXSearchField;
    private javax.swing.JComboBox talukaComboBox;
    private javax.swing.JPanel titlePanel;
    // End of variables declaration//GEN-END:variables
    private DocumentFilter documentFilter;
    private Resources privileges;
    private OperationButtons operationButtons;
    private List<Jurisdiction> jurisdictionDataList = new ArrayList<>();
    private ModificationManager modify;
}