package admission.view.admission;

import admission.controller.DatabaseManager;
import admission.enums.MessageEnum;
import admission.model.AdmissionYear;
import admission.model.Campus;
import admission.model.security.Resources;
import admission.model.ProgramType;
import admission.model.Test;
import com.yog.component.OperationButtons;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.text.AbstractDocument;
import javax.swing.text.DocumentFilter;
import org.hibernate.HibernateException;
import admission.utils.DateUtility;
import admission.utils.MessageBox;
import admission.utils.ModificationManager;
import admission.utils.RoundedBorder;

/**
 *
 * @author Raja Kumar & Jay Kumar
 */
public class TestInternalFrame extends javax.swing.JInternalFrame {

    public TestInternalFrame() {
        initComponents();
        
        admission.utils.Utility.hideOnEscape(this);
        
        documentFilter = new admission.utils.UppercaseDocumentFilter();
        ((AbstractDocument) this.remarksTextArea.getDocument()).setDocumentFilter(documentFilter);
        
        this.titlePanel.setBorder(RoundedBorder.createGradientBorder());
        this.buttonsPanel.add(this.getOperationButtons());
        
        this.modify = new ModificationManager();
        ((AbstractDocument) this.dateFormattedTextField.getDocument()).addDocumentListener(modify);
        ((AbstractDocument) this.timeFormattedTextField.getDocument()).addDocumentListener(modify);
        ((AbstractDocument) this.remarksTextArea.getDocument()).addDocumentListener(modify);
    }
    
    private JPanel getOperationButtons() {
        operationButtons = new OperationButtons() {
            @Override
            public void saveOperation(ActionEvent evt) {
                TestInternalFrame.this.save();
            }

            @Override
            public void deleteOperation(ActionEvent evt) {
                TestInternalFrame.this.delete();
            }

            @Override
            public void newOperation(ActionEvent evt) {
                TestInternalFrame.this.clear();
            }

            @Override
            public void backOperation(ActionEvent evt) {
                TestInternalFrame.this.setVisible(false);
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
            this.getCampus();
            this.getAdmissionYear();
            this.getProgramType();
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
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        testIdTextField = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        testList = new javax.swing.JList();
        jScrollPane2 = new javax.swing.JScrollPane();
        remarksTextArea = new javax.swing.JTextArea();
        jLabel5 = new javax.swing.JLabel();
        programTypeComboBox = new javax.swing.JComboBox();
        jLabel6 = new javax.swing.JLabel();
        admissionYearComboBox = new javax.swing.JComboBox();
        dateFormattedTextField = new javax.swing.JFormattedTextField();
        jLabel8 = new javax.swing.JLabel();
        timeFormattedTextField = new javax.swing.JFormattedTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        campusComboBox = new javax.swing.JComboBox();
        buttonsPanel = new javax.swing.JPanel();
        titlePanel = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        searchJXSearchField = new org.jdesktop.swingx.JXSearchField();

        setClosable(true);
        setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);
        setIconifiable(true);
        setTitle("Test");

        jLabel2.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel2.setText("Test ID:");

        testIdTextField.setEditable(false);
        testIdTextField.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel3.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel3.setText("Date:");

        jLabel4.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel4.setText("Remarks");

        testList.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                testListValueChanged(evt);
            }
        });
        jScrollPane1.setViewportView(testList);

        remarksTextArea.setColumns(20);
        remarksTextArea.setRows(5);
        jScrollPane2.setViewportView(remarksTextArea);

        jLabel5.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel5.setText("Program Type:");

        programTypeComboBox.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        jLabel6.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel6.setText("Admission Year:");

        admissionYearComboBox.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        admissionYearComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                admissionYearComboBoxActionPerformed(evt);
            }
        });

        try {
            dateFormattedTextField.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##-##-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        jLabel8.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel8.setText("Time:");

        try {
            timeFormattedTextField.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##:## UU")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        jLabel9.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel9.setText("e.g: DD-MM-YYYY");

        jLabel10.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel10.setText("e.g: hh:mm AM");

        jLabel15.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel15.setText("Campus:");

        campusComboBox.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        campusComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                campusComboBoxActionPerformed(evt);
            }
        });

        buttonsPanel.setPreferredSize(new java.awt.Dimension(335, 60));
        buttonsPanel.setLayout(new java.awt.BorderLayout());

        titlePanel.setLayout(new java.awt.BorderLayout());

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/admission/view/images/form-icon.png"))); // NOI18N
        jLabel11.setText("Test");
        jLabel11.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, java.awt.Color.darkGray), javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5)));
        titlePanel.add(jLabel11, java.awt.BorderLayout.CENTER);

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
                    .addComponent(buttonsPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel4)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLabel6)
                                        .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING))
                                    .addComponent(jLabel15))
                                .addGap(5, 5, 5)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(campusComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(programTypeComboBox, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(admissionYearComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(testIdTextField, javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                                .addGap(1, 1, 1)
                                                .addComponent(jLabel9))
                                            .addComponent(dateFormattedTextField, javax.swing.GroupLayout.Alignment.LEADING))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel8)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(timeFormattedTextField))
                                        .addGap(0, 0, Short.MAX_VALUE)))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 204, Short.MAX_VALUE)
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
                    .addComponent(jLabel15)
                    .addComponent(campusComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(searchJXSearchField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(admissionYearComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6))
                        .addGap(7, 7, 7)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(programTypeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(5, 5, 5)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(testIdTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(dateFormattedTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8)
                            .addComponent(timeFormattedTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(jLabel9)
                            .addComponent(jLabel10))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {admissionYearComboBox, dateFormattedTextField, programTypeComboBox, testIdTextField, timeFormattedTextField});

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void testListValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_testListValueChanged
        test = (Test) this.testList.getSelectedValue();
        if(test == null) return;
        
        this.programTypeComboBox.setSelectedItem(test.getProgramType());
        this.testIdTextField.setText(String.valueOf(test.getTestId()));
        this.dateFormattedTextField.setText(DateUtility.getDateToString(test.getDate()));
        this.timeFormattedTextField.setText(DateUtility.getTimeToString(test.getTime()));
        this.remarksTextArea.setText(test.getRemarks());
        modify.setModify(false);
    }//GEN-LAST:event_testListValueChanged

    private void campusComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_campusComboBoxActionPerformed
        this.getTest();
    }//GEN-LAST:event_campusComboBoxActionPerformed

    private void admissionYearComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_admissionYearComboBoxActionPerformed
        this.getTest();
    }//GEN-LAST:event_admissionYearComboBoxActionPerformed

    private void searchJXSearchFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchJXSearchFieldActionPerformed
        String text = searchJXSearchField.getText();
        admission.utils.Utility.filterDataList(testList, testDataList, text);
    }//GEN-LAST:event_searchJXSearchFieldActionPerformed
    
    private void getCampus(){
        this.campusComboBox.removeAllItems();
        
        List<Campus> list = DatabaseManager.getData(Campus.class, "name");
        
        for (Campus list1 : list) {
            this.campusComboBox.addItem(list1);
        }
    }
    
    private void getAdmissionYear(){
        this.admissionYearComboBox.removeAllItems();
        
        List<AdmissionYear> list = DatabaseManager.getData(AdmissionYear.class, "year DESC");
        
        for (AdmissionYear list1 : list) {
            this.admissionYearComboBox.addItem(list1);
        }
    }

    private void getProgramType(){
        this.programTypeComboBox.removeAllItems();
        List<ProgramType> list = DatabaseManager.getData(ProgramType.class, "name");
        
        for (ProgramType list1 : list) {
            this.programTypeComboBox.addItem(list1);
        }
    }

    private void getTest(){
        testDataList = new ArrayList<>();
        this.testList.setListData(testDataList.toArray());
        
        Campus campus = (Campus) this.campusComboBox.getSelectedItem();
        AdmissionYear ay = (AdmissionYear) this.admissionYearComboBox.getSelectedItem();
        if(campus == null || ay == null) return;
        
        testDataList = DatabaseManager.getData(Test.class, "campus.campusId = " + campus.getCampusId() + " AND admissionYear.admissionYearId = " + ay.getAdmissionYearId(), "testId");

        this.testList.setListData(testDataList.toArray());
    }
    
    private void save() {
        Campus campus = (Campus) this.campusComboBox.getSelectedItem();
        AdmissionYear admissionYear = (AdmissionYear) this.admissionYearComboBox.getSelectedItem();
        ProgramType programType = (ProgramType) this.programTypeComboBox.getSelectedItem();
        if(campus == null || admissionYear == null || programType == null) return;
        
        if(test == null) {
            test = new Test();
        }
        
        String remarks = this.remarksTextArea.getText();
        
        try{
            Date date = DateUtility.getStringToDate(this.dateFormattedTextField.getText());
            Date time = DateUtility.getStringToTime(this.timeFormattedTextField.getText());
        
            test.setCampus(campus);
            test.setAdmissionYear(admissionYear);
            test.setProgramType(programType);
            test.setDate(date);
            test.setTime(time);
            test.setRemarks(remarks);
        
            DatabaseManager.save(test);
            
            admission.utils.MessageBox.info(this, MessageEnum.MSG_SAVE);
            this.getTest();
            clear();
        }catch(HibernateException he){
            admission.utils.MessageBox.error(this, he);
            Logger.getLogger(TestInternalFrame.class.getName()).log(Level.SEVERE, null, he);
        }
    }
    
    private void delete() {
        if(test == null) return;
        
        try{
            DatabaseManager.deleteData(test);
            
            admission.utils.MessageBox.info(this, MessageEnum.MSG_DELETE);
            this.getTest();
            clear();
        }catch(HibernateException he){
            admission.utils.MessageBox.error(this, he);
            Logger.getLogger(TestInternalFrame.class.getName()).log(Level.SEVERE, null, he);
        }
    }
    
    private void clear(){
        test = null;
        this.testIdTextField.setText("");
        this.dateFormattedTextField.setText("");
        this.timeFormattedTextField.setText("");
        this.remarksTextArea.setText("");
        this.testList.clearSelection();
        modify.setModify(false);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox admissionYearComboBox;
    private javax.swing.JPanel buttonsPanel;
    private javax.swing.JComboBox campusComboBox;
    private javax.swing.JFormattedTextField dateFormattedTextField;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JComboBox programTypeComboBox;
    private javax.swing.JTextArea remarksTextArea;
    private org.jdesktop.swingx.JXSearchField searchJXSearchField;
    private javax.swing.JTextField testIdTextField;
    private javax.swing.JList testList;
    private javax.swing.JFormattedTextField timeFormattedTextField;
    private javax.swing.JPanel titlePanel;
    // End of variables declaration//GEN-END:variables
    private DocumentFilter documentFilter;
    private Resources privileges;
    private OperationButtons operationButtons;
    private List<Test> testDataList;
    private ModificationManager modify;
    private Test test;
}
