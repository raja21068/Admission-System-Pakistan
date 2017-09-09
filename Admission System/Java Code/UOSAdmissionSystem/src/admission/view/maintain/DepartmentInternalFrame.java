package admission.view.maintain;

import admission.controller.DatabaseManager;
import admission.enums.MessageEnum;
import admission.model.Campus;
import admission.model.Department;
import admission.model.DeptType;
import admission.model.Faculty;
import admission.model.security.Resources;
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
public class DepartmentInternalFrame extends javax.swing.JInternalFrame {

    public DepartmentInternalFrame() {
        initComponents();
        
        admission.utils.Utility.hideOnEscape(this);
        this.titlePanel.setBorder(RoundedBorder.createGradientBorder());
        this.buttonsPanel.add(this.getOperationButtons());
        
        this.modify = new ModificationManager();
        ((AbstractDocument) this.nameTextField.getDocument()).addDocumentListener(modify);
        ((AbstractDocument) this.remarksTextArea.getDocument()).addDocumentListener(modify);
        
        documentFilter = new admission.utils.UppercaseDocumentFilter();
        ((AbstractDocument) this.nameTextField.getDocument()).setDocumentFilter(documentFilter);
        ((AbstractDocument) this.remarksTextArea.getDocument()).setDocumentFilter(documentFilter);
    }
    
    @Override
    public void setVisible(boolean aFlag) {
        if(aFlag) {
            if (modify != null) this.modify.setModifyLabel(operationButtons.getModifyLabel());
            this.getDepartmentType();
            this.getFaculty();
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
                DepartmentInternalFrame.this.save();
            }
            @Override public void deleteOperation(ActionEvent evt) {
                DepartmentInternalFrame.this.delete();
            }
            @Override public void newOperation(ActionEvent evt) {
                DepartmentInternalFrame.this.clear();
            }
            @Override public void backOperation(ActionEvent evt) {
                DepartmentInternalFrame.this.setVisible(false);
            }
        };
        operationButtons.setSize(335, 60);
        return operationButtons;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        deptIdTextField = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        nameTextField = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        deptList = new javax.swing.JList();
        jScrollPane2 = new javax.swing.JScrollPane();
        remarksTextArea = new javax.swing.JTextArea();
        jLabel5 = new javax.swing.JLabel();
        facultyComboBox = new javax.swing.JComboBox();
        jLabel7 = new javax.swing.JLabel();
        deptTypeComboBox = new javax.swing.JComboBox();
        titlePanel = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        buttonsPanel = new javax.swing.JPanel();
        searchJXSearchField = new org.jdesktop.swingx.JXSearchField();

        setClosable(true);
        setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);
        setIconifiable(true);
        setTitle("Department");

        jLabel2.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel2.setText("Dept ID:");

        deptIdTextField.setEditable(false);
        deptIdTextField.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel3.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel3.setText("Deptartment:");

        jLabel4.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel4.setText("Remarks");

        deptList.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                deptListValueChanged(evt);
            }
        });
        jScrollPane1.setViewportView(deptList);

        remarksTextArea.setColumns(20);
        remarksTextArea.setRows(5);
        jScrollPane2.setViewportView(remarksTextArea);

        jLabel5.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel5.setText("Faculty:");

        facultyComboBox.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        facultyComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                facultyComboBoxActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel7.setText("Type:");

        deptTypeComboBox.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        titlePanel.setLayout(new java.awt.BorderLayout());

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/admission/view/images/form-icon.png"))); // NOI18N
        jLabel6.setText("Department");
        jLabel6.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, java.awt.Color.darkGray), javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5)));
        titlePanel.add(jLabel6, java.awt.BorderLayout.PAGE_END);

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
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(buttonsPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 407, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(4, 4, 4)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(facultyComboBox, 0, 332, Short.MAX_VALUE)
                                    .addComponent(deptIdTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(nameTextField)
                                    .addComponent(deptTypeComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addGap(6, 6, 6)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 238, Short.MAX_VALUE)
                            .addComponent(searchJXSearchField, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(titlePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(buttonsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(facultyComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(searchJXSearchField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(deptIdTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(5, 5, 5)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(deptTypeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(3, 3, 3)
                                .addComponent(jLabel3))
                            .addComponent(nameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(6, 6, 6)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {deptIdTextField, deptTypeComboBox, facultyComboBox, nameTextField, searchJXSearchField});

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void facultyComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_facultyComboBoxActionPerformed
        getDepartment();
    }//GEN-LAST:event_facultyComboBoxActionPerformed

    private void deptListValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_deptListValueChanged
        Department department = (Department) deptList.getSelectedValue();
        if(department == null) return;
        
        this.deptTypeComboBox.setSelectedItem(department.getDeptType());
        this.deptIdTextField.setText(String.valueOf(department.getDepartmentId()));
        this.nameTextField.setText(department.getName());
        this.remarksTextArea.setText(department.getRemarks());
        modify.setModify(false);
    }//GEN-LAST:event_deptListValueChanged

    private void searchJXSearchFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchJXSearchFieldActionPerformed
        // TODO add your handling code here:
        String text  = searchJXSearchField.getText();
        admission.utils.Utility.filterDataList(deptList, deptDataList, text);
    }//GEN-LAST:event_searchJXSearchFieldActionPerformed
    
    private void getFaculty(){
        facultyComboBox.removeAllItems();
        
        List<Faculty> list = DatabaseManager.getData(Faculty.class.getName(), "name");
        
        for (Faculty list1 : list) {
            this.facultyComboBox.addItem(list1);
        }
    }

    private void getDepartmentType(){
        deptTypeComboBox.removeAllItems();
        
        List<DeptType> list = DatabaseManager.getData(DeptType.class.getName(), "name");
        
        for (DeptType list1 : list) {
            this.deptTypeComboBox.addItem(list1);
        }
    }
    
    private void getDepartment(){
        deptDataList.clear();
        deptList.setListData(deptDataList.toArray());
        
        Faculty faculty = (Faculty) this.facultyComboBox.getSelectedItem();
        if(faculty == null) return;
        
        deptDataList.addAll(faculty.getDepartments());
        deptList.setListData(deptDataList.toArray());
    }
    
    private void save() {
        String name = this.nameTextField.getText();
        String remarks = this.remarksTextArea.getText();
        if(name.isEmpty()) return;
        
        Faculty faculty = (Faculty) this.facultyComboBox.getSelectedItem();
        DeptType deptType = (DeptType) this.deptTypeComboBox.getSelectedItem();
        Department department = (Department) deptList.getSelectedValue();
        if(faculty == null || deptType == null) return;
        if(department == null) {
            department = new Department();
        }

        department.setDeptType(deptType);
        department.setFaculty(faculty);
        department.setName(name);
        department.setRemarks(remarks);
        
        try{
            DatabaseManager.save(department);
            admission.utils.MessageBox.info(this, MessageEnum.MSG_SAVE);
            clear();
            this.getDepartment();
        }catch(HibernateException he){
            admission.utils.MessageBox.error(this, he);
            he.printStackTrace();
        }
    }
    
    private void delete() {
        Faculty faculty = (Faculty) this.facultyComboBox.getSelectedItem();
        DeptType deptType = (DeptType) this.deptTypeComboBox.getSelectedItem();
        Department department = (Department) deptList.getSelectedValue();
        if(department == null || faculty == null || deptType == null) return;

        try{
            DatabaseManager.deleteData(Department.class.getName(), "departmentId = " + department.getDepartmentId());
            admission.utils.MessageBox.info(this, MessageEnum.MSG_DELETE);
            DatabaseManager.refresh(faculty);
            DatabaseManager.refresh(deptType);
            clear();
//            faculty.getDepartments().remove(department);
//            deptType.getDepartments().remove(department);
            this.getDepartment();
        }catch(HibernateException he){
            admission.utils.MessageBox.error(this, he);
            he.printStackTrace();
        }
    }
    
    private void clear(){
        this.deptIdTextField.setText("");
        this.nameTextField.setText("");
        this.remarksTextArea.setText("");
        this.deptList.clearSelection();
        modify.setModify(false);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel buttonsPanel;
    private javax.swing.JTextField deptIdTextField;
    private javax.swing.JList deptList;
    private javax.swing.JComboBox deptTypeComboBox;
    private javax.swing.JComboBox facultyComboBox;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField nameTextField;
    private javax.swing.JTextArea remarksTextArea;
    private org.jdesktop.swingx.JXSearchField searchJXSearchField;
    private javax.swing.JPanel titlePanel;
    // End of variables declaration//GEN-END:variables
    private DocumentFilter documentFilter;
    private Resources privileges;
    private ModificationManager modify;
    private OperationButtons operationButtons;
    private List<Department> deptDataList = new ArrayList<>();
}
