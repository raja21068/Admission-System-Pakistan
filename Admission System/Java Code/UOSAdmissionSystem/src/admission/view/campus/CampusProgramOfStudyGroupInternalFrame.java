package admission.view.campus;

import admission.controller.DatabaseManager;
import admission.enums.GroupEnum;
import admission.enums.MessageEnum;
import admission.model.Campus;
import admission.model.CampusProgramOfStudy;
import admission.model.CposGroup;
import admission.model.security.Resources;
import admission.model.ProgramType;
import admission.model.Shift;
import com.yog.component.ComboBoxToolTipRenderer;
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
public class CampusProgramOfStudyGroupInternalFrame extends javax.swing.JInternalFrame {

    public CampusProgramOfStudyGroupInternalFrame() {
        initComponents();
        
        admission.utils.Utility.hideOnEscape(this);
        admission.utils.Utility.comboBoxScroll(this.campusComboBox);
        admission.utils.Utility.comboBoxScroll(this.campusProgramOfStudyComboBox);
        admission.utils.Utility.loadEnum(groupComboBox, GroupEnum.class);
        
        this.titlePanel.setBorder(RoundedBorder.createGradientBorder());
        this.buttonsPanel.add(this.getOperationButtons());
        
        this.modify = new ModificationManager();
        this.groupComboBox.addActionListener(modify);
        ((AbstractDocument) this.codeTextField.getDocument()).addDocumentListener(modify);
        ((AbstractDocument) this.remarksTextArea.getDocument()).addDocumentListener(modify);
        
        documentFilter = new admission.utils.UppercaseDocumentFilter();
        ((AbstractDocument) this.remarksTextArea.getDocument()).setDocumentFilter(documentFilter);
        ((AbstractDocument) this.codeTextField.getDocument()).setDocumentFilter(documentFilter);
        
        comboBoxToolTipRenderer = new ComboBoxToolTipRenderer();
        this.campusProgramOfStudyComboBox.setRenderer(comboBoxToolTipRenderer);
    }
    
    @Override
    public void setVisible(boolean aFlag) {
        if (aFlag) {
            if (modify != null) {
                this.modify.setModifyLabel(operationButtons.getModifyLabel());
            }
            this.clear();
            this.getCampus();
            this.getSession();
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
//        this.addButton.setEnabled((privileges.isAddPrivilige()));
//        this.updateButton.setEnabled((privileges.isUpdatePrivilige()));
//        this.deleteButton.setEnabled((privileges.isDeletePrivilige()));
    }

    private JPanel getOperationButtons() {
        operationButtons = new OperationButtons() {
            @Override public void saveOperation(ActionEvent evt) {
                CampusProgramOfStudyGroupInternalFrame.this.save();
            }
            @Override public void deleteOperation(ActionEvent evt) {
                CampusProgramOfStudyGroupInternalFrame.this.delete();
            }
            @Override public void newOperation(ActionEvent evt) {
                CampusProgramOfStudyGroupInternalFrame.this.clear();
            }
            @Override public void backOperation(ActionEvent evt) {
                CampusProgramOfStudyGroupInternalFrame.this.setVisible(false);
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
        cposGroupIdTextField = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        remarksTextArea = new javax.swing.JTextArea();
        campusComboBox = new javax.swing.JComboBox();
        jLabel9 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        campusProgramOfStudyComboBox = new javax.swing.JComboBox();
        jLabel16 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        cposGroupList = new javax.swing.JList();
        durationTextField = new javax.swing.JTextField();
        semesterTextField = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        groupComboBox = new javax.swing.JComboBox();
        shiftComboBox = new javax.swing.JComboBox();
        programTypeComboBox = new javax.swing.JComboBox();
        jLabel14 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        codeTextField = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        titlePanel = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        buttonsPanel = new javax.swing.JPanel();
        searchJXSearchField = new org.jdesktop.swingx.JXSearchField();

        setClosable(true);
        setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);
        setIconifiable(true);
        setResizable(true);
        setTitle("Campus Program Of Study Group");

        jLabel2.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel2.setText("ID:");

        cposGroupIdTextField.setEditable(false);
        cposGroupIdTextField.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel4.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel4.setText("Remarks");

        remarksTextArea.setColumns(20);
        remarksTextArea.setRows(5);
        jScrollPane2.setViewportView(remarksTextArea);

        campusComboBox.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        campusComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                campusComboBoxActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel9.setText("Duration:");

        jLabel12.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel12.setText("Semester:");

        campusProgramOfStudyComboBox.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        campusProgramOfStudyComboBox.setToolTipText("Program of Study");
        campusProgramOfStudyComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                campusProgramOfStudyComboBoxActionPerformed(evt);
            }
        });

        jLabel16.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel16.setText("Pos:");

        jLabel7.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel7.setText("Campus:");

        cposGroupList.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                cposGroupListValueChanged(evt);
            }
        });
        jScrollPane1.setViewportView(cposGroupList);

        durationTextField.setEditable(false);

        semesterTextField.setEditable(false);

        jLabel3.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel3.setText("Group:");

        groupComboBox.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        groupComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "General", "Medical", "Engineering", "Commerce General", "Commerce", "Arts" }));

        shiftComboBox.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        shiftComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                shiftComboBoxActionPerformed(evt);
            }
        });

        programTypeComboBox.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        programTypeComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                programTypeComboBoxActionPerformed(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel14.setText("Shift:");

        jLabel5.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel5.setText("Program Type:");

        codeTextField.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel6.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel6.setText("Code:");

        titlePanel.setLayout(new java.awt.BorderLayout());

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/admission/view/images/form-icon.png"))); // NOI18N
        jLabel11.setText("Campus Program Of Study Group");
        jLabel11.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, java.awt.Color.darkGray), javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5)));
        titlePanel.add(jLabel11, java.awt.BorderLayout.PAGE_END);

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
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel4)
                            .addComponent(jScrollPane2)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(shiftComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel5)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(programTypeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(campusProgramOfStudyComboBox, 0, 469, Short.MAX_VALUE)
                                    .addComponent(campusComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(durationTextField)
                                            .addComponent(groupComboBox, 0, 184, Short.MAX_VALUE))
                                        .addGap(14, 14, 14)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(codeTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jLabel2)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(cposGroupIdTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addComponent(semesterTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 310, Short.MAX_VALUE)
                            .addComponent(searchJXSearchField, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(buttonsPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(titlePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(buttonsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jLabel7))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(campusComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(searchJXSearchField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(programTypeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(shiftComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel14)
                                .addComponent(jLabel5)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(campusProgramOfStudyComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel16))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(durationTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(semesterTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel12))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel6)
                                .addComponent(codeTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel3)
                                .addComponent(groupComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel2)
                                .addComponent(cposGroupIdTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {campusComboBox, campusProgramOfStudyComboBox, codeTextField, cposGroupIdTextField, durationTextField, groupComboBox, programTypeComboBox, searchJXSearchField, semesterTextField, shiftComboBox});

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void campusProgramOfStudyComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_campusProgramOfStudyComboBoxActionPerformed
        // TODO add your handling code here:
        this.getCampusProgramOfStudyGroup();
    }//GEN-LAST:event_campusProgramOfStudyComboBoxActionPerformed

    private void campusComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_campusComboBoxActionPerformed
        // TODO add your handling code here:
        this.getCampusProgramOfStudy();
    }//GEN-LAST:event_campusComboBoxActionPerformed

    private void cposGroupListValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_cposGroupListValueChanged
        // TODO add your handling code here:
        CposGroup cposGroup = (CposGroup) this.cposGroupList.getSelectedValue();
        if(cposGroup == null) return;
        
        this.cposGroupIdTextField.setText(String.valueOf(cposGroup.getCposGroupId()));
        this.groupComboBox.setSelectedItem(cposGroup.getGroup());
        this.codeTextField.setText(cposGroup.getCode());
        this.remarksTextArea.setText(cposGroup.getRemarks());
        modify.setModify(false); 
    }//GEN-LAST:event_cposGroupListValueChanged

    private void shiftComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_shiftComboBoxActionPerformed
        // TODO add your handling code here:
        this.getCampusProgramOfStudy();
    }//GEN-LAST:event_shiftComboBoxActionPerformed

    private void programTypeComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_programTypeComboBoxActionPerformed
        // TODO add your handling code here:
        this.getCampusProgramOfStudy();
    }//GEN-LAST:event_programTypeComboBoxActionPerformed

    private void searchJXSearchFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchJXSearchFieldActionPerformed
        // TODO add your handling code here:
        String text = searchJXSearchField.getText();
        admission.utils.Utility.filterDataList(cposGroupList, cposgDataList, text);
    }//GEN-LAST:event_searchJXSearchFieldActionPerformed
    
    private void getCampus(){
        campusComboBox.removeAllItems();
        
        List<Campus> list = DatabaseManager.getData(Campus.class.getName(), "name");
        
        for (Campus list1 : list) {
            this.campusComboBox.addItem(list1);
        }
    }
    
    private void getSession(){
        this.shiftComboBox.removeAllItems();
        
        List<Shift> list = DatabaseManager.getData(Shift.class.getName(), "isMorning DESC");
        
        for (Shift s : list) {
            this.shiftComboBox.addItem(s);
        }
    }
    
    private void getProgramType(){
        this.programTypeComboBox.removeAllItems();
        
        List<ProgramType> list = DatabaseManager.getData(ProgramType.class.getName(), "isBachelor DESC");
        
        for (ProgramType pt : list) {
            this.programTypeComboBox.addItem(pt);
        }
    }
    
    private void getCampusProgramOfStudy(){
        campusProgramOfStudyComboBox.removeAllItems();
        
        Campus campus = (Campus) this.campusComboBox.getSelectedItem();
        Shift shift = (Shift) this.shiftComboBox.getSelectedItem();
        ProgramType pt = (ProgramType) this.programTypeComboBox.getSelectedItem();
        if(campus == null || shift == null || pt == null) return;
        
//        List<CampusProgramOfStudy> list = DatabaseManager.getCampusProgramOfStudy(campus.getCampusId(), programType.getProgramTypeId(), shift.getShiftId());
        String hql = "SELECT cpos FROM CampusProgramOfStudy AS cpos "
                + "INNER JOIN cpos.programOfStudy AS pos "
                + "INNER JOIN cpos.shift AS s "
                + "INNER JOIN cpos.campus AS ca "
                + "INNER JOIN pos.program AS p "
                + "INNER JOIN p.programType AS pt "
                + "WHERE ca.campusId = " + campus.getCampusId() + " "
                + "AND pt.programTypeId = " + pt.getProgramTypeId() + " "
                + "AND s.shiftId = " + shift.getShiftId() + " "
                + "ORDER BY pos.name";

        List<CampusProgramOfStudy> list = DatabaseManager.executeQuery(CampusProgramOfStudy.class, hql);
//        List<String> toolTipList = new ArrayList<>();
//        this.comboBoxToolTipRenderer.setTooltips(toolTipList);

        for (CampusProgramOfStudy cpos : list) {
////            toolTipList.add(list1.getProgramOfStudy().getProgram().toString());
            campusProgramOfStudyComboBox.addItem(cpos);
        }
    }
    
    private void getCampusProgramOfStudyGroup(){
        cposgDataList.clear();
        this.cposGroupList.setListData(cposgDataList.toArray());
        this.durationTextField.setText("");
        this.semesterTextField.setText("");
        
        CampusProgramOfStudy cpos = (CampusProgramOfStudy) this.campusProgramOfStudyComboBox.getSelectedItem();
        if(cpos == null) return;

        this.durationTextField.setText(cpos.getProgramOfStudy().getDuration() + " Year");
        this.semesterTextField.setText(cpos.getProgramOfStudy().getSemester() + " Semester");
        
        cposgDataList.addAll(cpos.getCposGroups());
        this.cposGroupList.setListData(cposgDataList.toArray());
    }
    
    private void save() {
        CampusProgramOfStudy campusProgramOfStudy = (CampusProgramOfStudy) this.campusProgramOfStudyComboBox.getSelectedItem();
        CposGroup cposGroup = (CposGroup) this.cposGroupList.getSelectedValue();
        if(campusProgramOfStudy == null) return;
        if(cposGroup == null) {
            cposGroup = new CposGroup();
        }
        
        GroupEnum group = (GroupEnum) this.groupComboBox.getSelectedItem();
        String code = this.codeTextField.getText();
        String remarks = this.remarksTextArea.getText();
        
        cposGroup.setCampusProgramOfStudy(campusProgramOfStudy);
        cposGroup.setGroup(group);
        cposGroup.setCode(code);
        cposGroup.setRemarks(remarks);
        
        try{
            DatabaseManager.save(cposGroup);
            
            admission.utils.MessageBox.info(this, MessageEnum.MSG_SAVE);
            DatabaseManager.refresh(campusProgramOfStudy);
            this.getCampusProgramOfStudyGroup();
            clear();
        }catch(HibernateException he){
            admission.utils.MessageBox.error(this, he);
        }
    }
    
    private void delete() {
        CampusProgramOfStudy campusProgramOfStudy = (CampusProgramOfStudy) this.campusProgramOfStudyComboBox.getSelectedItem();
        CposGroup cposGroup = (CposGroup) this.cposGroupList.getSelectedValue();
        if(campusProgramOfStudy == null || cposGroup == null) return;
        
        try{
            DatabaseManager.deleteData(CposGroup.class.getName(), "cposGroupId="+cposGroup.getCposGroupId());
            
            admission.utils.MessageBox.info(this, MessageEnum.MSG_DELETE);
            DatabaseManager.refresh(campusProgramOfStudy);
            this.getCampusProgramOfStudyGroup();
            clear();
        }catch(HibernateException he){
            admission.utils.MessageBox.error(this, he);
        }
    }
    
    private void clear(){
        this.cposGroupIdTextField.setText("");
        this.codeTextField.setText("");
        this.remarksTextArea.setText("");
        this.cposGroupList.clearSelection();
        modify.setModify(false); 
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel buttonsPanel;
    private javax.swing.JComboBox campusComboBox;
    private javax.swing.JComboBox campusProgramOfStudyComboBox;
    private javax.swing.JTextField codeTextField;
    private javax.swing.JTextField cposGroupIdTextField;
    private javax.swing.JList cposGroupList;
    private javax.swing.JTextField durationTextField;
    private javax.swing.JComboBox groupComboBox;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JComboBox programTypeComboBox;
    private javax.swing.JTextArea remarksTextArea;
    private org.jdesktop.swingx.JXSearchField searchJXSearchField;
    private javax.swing.JTextField semesterTextField;
    private javax.swing.JComboBox shiftComboBox;
    private javax.swing.JPanel titlePanel;
    // End of variables declaration//GEN-END:variables
    private DocumentFilter documentFilter;
    private Resources privileges;
    private ComboBoxToolTipRenderer comboBoxToolTipRenderer; 
    private OperationButtons operationButtons;
    private List<CposGroup> cposgDataList = new ArrayList<>();
    private ModificationManager modify;
}
