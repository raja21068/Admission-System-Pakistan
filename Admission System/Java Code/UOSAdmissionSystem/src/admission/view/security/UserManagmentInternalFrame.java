package admission.view.security;

import admission.controller.DatabaseManager;
import admission.enums.MessageEnum;
import admission.enums.StatusEnum;
import admission.model.security.EntityPrivilege;
import admission.model.security.User;
import admission.model.security.Resources;
import com.yog.component.OperationButtons;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.swing.DefaultListModel;
import javax.swing.JPanel;
import javax.swing.text.AbstractDocument;
import javax.swing.text.DocumentFilter;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import org.hibernate.HibernateException;
import admission.utils.AltEncrypter;
import admission.utils.RoundedBorder;

/**
 *
 * @author Raja Kumar & Jay Kumar
 */
public class UserManagmentInternalFrame extends javax.swing.JInternalFrame {

    public UserManagmentInternalFrame() {
        initComponents();

        this.titlePanel.setBorder(RoundedBorder.createGradientBorder());
        this.buttonsPanel.add(this.getOperationButtons());

        admission.utils.Utility.hideOnEscape(this);
        admission.utils.Utility.loadEnum(statusComboBox, StatusEnum.class);

        defaultListModel = new DefaultListModel<>();
        resourceList.setModel(defaultListModel);

        defaultTreeModel = new DefaultTreeModel(new DefaultMutableTreeNode("Entity"));
        entityTree.setModel(defaultTreeModel);

        documentFilter = new admission.utils.UppercaseDocumentFilter();
        ((AbstractDocument) this.nameTextField.getDocument()).setDocumentFilter(documentFilter);
        ((AbstractDocument) this.surnameTextField.getDocument()).setDocumentFilter(documentFilter);
        ((AbstractDocument) this.remarksTextArea.getDocument()).setDocumentFilter(documentFilter);
    }

    private JPanel getOperationButtons() {
        operationButtons = new OperationButtons() {
            @Override
            public void saveOperation(ActionEvent evt) {
                UserManagmentInternalFrame.this.save();
            }

            @Override
            public void deleteOperation(ActionEvent evt) {
                UserManagmentInternalFrame.this.delete();
            }

            @Override
            public void newOperation(ActionEvent evt) {
                UserManagmentInternalFrame.this.clear();
            }

            @Override
            public void backOperation(ActionEvent evt) {
                UserManagmentInternalFrame.this.setVisible(false);
            }
        };
        operationButtons.setSize(335, 60);
        return operationButtons;
    }

    @Override
    public void setVisible(boolean aFlag) {
        if (aFlag) {
            this.getUsers();
            this.clear();
        }
        super.setVisible(aFlag);
    }

    public void setPrivileges(Resources resources) {
        //this.resources = resources;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        titlePanel = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        basicDetailPanel = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        surnameTextField = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        userList = new javax.swing.JList();
        jScrollPane2 = new javax.swing.JScrollPane();
        remarksTextArea = new javax.swing.JTextArea();
        jLabel5 = new javax.swing.JLabel();
        nameTextField = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        mobileNoTextField = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        usernameTextField = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        passwordField = new javax.swing.JPasswordField();
        jLabel10 = new javax.swing.JLabel();
        statusComboBox = new javax.swing.JComboBox();
        resourcesPanel = new javax.swing.JPanel();
        deleteButton = new javax.swing.JButton();
        moreButton = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        resourceList = new javax.swing.JList();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        entityTree = new javax.swing.JTree();
        moreEntityPrivilegeButton = new javax.swing.JButton();
        deleteEntityPrivilegeButton = new javax.swing.JButton();
        buttonsPanel = new javax.swing.JPanel();

        setClosable(true);
        setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);
        setIconifiable(true);
        setTitle("User Managment");

        titlePanel.setLayout(new java.awt.BorderLayout());

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/admission/view/images/form-icon.png"))); // NOI18N
        jLabel11.setText("User Managment");
        jLabel11.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, java.awt.Color.darkGray), javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5)));
        titlePanel.add(jLabel11, java.awt.BorderLayout.PAGE_END);

        jTabbedPane1.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        basicDetailPanel.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        jLabel3.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel3.setText("Last Name:");

        jLabel4.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel4.setText("Remarks");

        userList.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                userListValueChanged(evt);
            }
        });
        jScrollPane1.setViewportView(userList);

        remarksTextArea.setColumns(20);
        remarksTextArea.setRows(5);
        jScrollPane2.setViewportView(remarksTextArea);

        jLabel5.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel5.setText("First Name:");

        nameTextField.setPreferredSize(new java.awt.Dimension(6, 25));

        jLabel6.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel6.setText("Mobile No.:");

        jLabel8.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel8.setText("Username:");

        jLabel9.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel9.setText("Password:");

        jLabel10.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel10.setText("Status:");

        statusComboBox.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        javax.swing.GroupLayout basicDetailPanelLayout = new javax.swing.GroupLayout(basicDetailPanel);
        basicDetailPanel.setLayout(basicDetailPanelLayout);
        basicDetailPanelLayout.setHorizontalGroup(
            basicDetailPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(basicDetailPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(basicDetailPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(basicDetailPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel4)
                        .addGroup(basicDetailPanelLayout.createSequentialGroup()
                            .addGroup(basicDetailPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel8)
                                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(basicDetailPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(basicDetailPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(usernameTextField)
                                .addComponent(surnameTextField)
                                .addComponent(mobileNoTextField)
                                .addGroup(basicDetailPanelLayout.createSequentialGroup()
                                    .addGroup(basicDetailPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(nameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 292, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(passwordField, javax.swing.GroupLayout.PREFERRED_SIZE, 292, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(statusComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(0, 0, Short.MAX_VALUE))))
                        .addComponent(jScrollPane2))
                    .addComponent(jLabel10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        basicDetailPanelLayout.setVerticalGroup(
            basicDetailPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(basicDetailPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(basicDetailPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, basicDetailPanelLayout.createSequentialGroup()
                        .addGroup(basicDetailPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(nameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(basicDetailPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(surnameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(basicDetailPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(mobileNoTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(basicDetailPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(usernameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(basicDetailPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(passwordField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(basicDetailPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(statusComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        basicDetailPanelLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {mobileNoTextField, nameTextField, passwordField, surnameTextField, usernameTextField});

        jTabbedPane1.addTab("Basic Detail", basicDetailPanel);

        resourcesPanel.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        deleteButton.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        deleteButton.setText("Delete");
        deleteButton.setFocusPainted(false);
        deleteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteButtonActionPerformed(evt);
            }
        });

        moreButton.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        moreButton.setText("More");
        moreButton.setFocusPainted(false);
        moreButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                moreButtonActionPerformed(evt);
            }
        });

        jScrollPane3.setViewportView(resourceList);

        javax.swing.GroupLayout resourcesPanelLayout = new javax.swing.GroupLayout(resourcesPanel);
        resourcesPanel.setLayout(resourcesPanelLayout);
        resourcesPanelLayout.setHorizontalGroup(
            resourcesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(resourcesPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(resourcesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(resourcesPanelLayout.createSequentialGroup()
                        .addGap(0, 411, Short.MAX_VALUE)
                        .addComponent(moreButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(deleteButton))
                    .addComponent(jScrollPane3))
                .addContainerGap())
        );
        resourcesPanelLayout.setVerticalGroup(
            resourcesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, resourcesPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(resourcesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(deleteButton)
                    .addComponent(moreButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 295, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Resources", resourcesPanel);

        jScrollPane4.setViewportView(entityTree);

        moreEntityPrivilegeButton.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        moreEntityPrivilegeButton.setText("More");
        moreEntityPrivilegeButton.setFocusPainted(false);
        moreEntityPrivilegeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                moreEntityPrivilegeButtonActionPerformed(evt);
            }
        });

        deleteEntityPrivilegeButton.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        deleteEntityPrivilegeButton.setText("Delete");
        deleteEntityPrivilegeButton.setFocusPainted(false);
        deleteEntityPrivilegeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteEntityPrivilegeButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 411, Short.MAX_VALUE)
                        .addComponent(moreEntityPrivilegeButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(deleteEntityPrivilegeButton)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(deleteEntityPrivilegeButton)
                    .addComponent(moreEntityPrivilegeButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 295, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Entity Privilege", jPanel1);

        buttonsPanel.setPreferredSize(new java.awt.Dimension(335, 60));
        buttonsPanel.setLayout(new java.awt.BorderLayout());

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(titlePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(buttonsPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jTabbedPane1))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(titlePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(buttonsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void userListValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_userListValueChanged
        resourcesList = null;
        defaultListModel.clear();

        user = (User) this.userList.getSelectedValue();
        if (user == null) {
            return;
        }

        this.nameTextField.setText(user.getFirstName());
        this.surnameTextField.setText(user.getLastName());
        this.usernameTextField.setText(user.getUsername());
        this.mobileNoTextField.setText(user.getMobileNo());
        this.passwordField.setText(new AltEncrypter().decrypt(user.getPassword()));
        this.statusComboBox.setSelectedItem(user.getStatus());
        this.remarksTextArea.setText(user.getRemarks());

        resourcesList = DatabaseManager.getData(Resources.class, "user.id = " + user.getId(), "code");
        for (Resources resources : resourcesList) {
            defaultListModel.addElement(resources);
        }
        entityPrivilegelist = DatabaseManager.getData(EntityPrivilege.class, "user.id = " + user.getId(), "modelName");
        loadEntityTree();
    }//GEN-LAST:event_userListValueChanged

    private void moreButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_moreButtonActionPerformed
        // TODO add your handling code here:
        if (user == null) {
            return;
        }
        List<String> list = new ArrayList<>();
        for (Resources r : resourcesList) {
            list.add(r.getCode());
        }
        newResourcesList = ResourcesDialog.showDialog(null, list);
    }//GEN-LAST:event_moreButtonActionPerformed

    private void deleteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteButtonActionPerformed
        // TODO add your handling code here:
        Resources r = defaultListModel.getElementAt(resourceList.getSelectedIndex());
        if (r == null) {
            return;
        }

        DatabaseManager.deleteData(r);
        defaultListModel.remove(resourceList.getSelectedIndex());
    }//GEN-LAST:event_deleteButtonActionPerformed

    private void moreEntityPrivilegeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_moreEntityPrivilegeButtonActionPerformed
        // TODO add your handling code here:
        if (user == null) {
            return;
        }
        //List<EntityPrivilege> list = DatabaseManager.getData(EntityPrivilege.class, "user.id = " + user.getId(), "modelName");
        newEntityPrivilegelist = EntityPrivilegeDialog.showDialog(null, entityPrivilegelist);
    }//GEN-LAST:event_moreEntityPrivilegeButtonActionPerformed

    private void deleteEntityPrivilegeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteEntityPrivilegeButtonActionPerformed
        // TODO add your handling code here:
        DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) entityTree.getLastSelectedPathComponent();
        if (selectedNode == null || selectedNode.getLevel() < 2) {
            return;
        }

        if (!(selectedNode.getUserObject() instanceof EntityPrivilege)) {
            return;
        }
        
        EntityPrivilege ep = (EntityPrivilege) selectedNode.getUserObject();
        DatabaseManager.deleteData(ep);
        entityPrivilegelist.remove(ep);
        loadEntityTree();
    }//GEN-LAST:event_deleteEntityPrivilegeButtonActionPerformed

    private void getUsers() {
        List<User> list = DatabaseManager.getData(User.class, "type = null", "firstName");
        this.userList.setListData(list.toArray());
    }

    private Map<String, List<EntityPrivilege>> getEntityMap(List<EntityPrivilege> epList) {
        Map<String, List<EntityPrivilege>> map = new LinkedHashMap<>();
        for (EntityPrivilege ep : epList) {
            if (map.containsKey(ep.getModelName())) {
                List<EntityPrivilege> list = map.get(ep.getModelName());
                list.add(ep);
            } else {
                List<EntityPrivilege> list = new ArrayList<>();
                list.add(ep);
                map.put(ep.getModelName(), list);
            }
        }

        return map;
    }

    private void loadEntityTree() {
        Map<String, List<EntityPrivilege>> map = getEntityMap(entityPrivilegelist);
        DefaultMutableTreeNode root = (DefaultMutableTreeNode) defaultTreeModel.getRoot();
        root.removeAllChildren();

        for (Map.Entry<String, List<EntityPrivilege>> entry : map.entrySet()) {
            String modelName = entry.getKey();
            DefaultMutableTreeNode parentNode = new DefaultMutableTreeNode(modelName);
            List<EntityPrivilege> list = entry.getValue();
            for (EntityPrivilege ep : list) {
                DefaultMutableTreeNode node = new DefaultMutableTreeNode(ep);
                parentNode.add(node);
            }
            root.add(parentNode);
        }
        
        defaultTreeModel.reload(root);
        entityTree.repaint();
    }

    private void save() {
        user = (User) this.userList.getSelectedValue();
        if (user == null) {
            user = new User();
            user.setDateOfCreated(System.currentTimeMillis());
        }

        String firstName = this.nameTextField.getText();
        String lastName = this.surnameTextField.getText();
        String mobileNo = this.mobileNoTextField.getText();
        String username = this.usernameTextField.getText();
        String password = this.passwordField.getText();
        StatusEnum status = (StatusEnum) statusComboBox.getSelectedItem();
        String remarks = this.remarksTextArea.getText();

        if (username.isEmpty() || password.isEmpty()) {
            return;
        }
        password = new AltEncrypter().encrypt(password);

        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setMobileNo(mobileNo);
        user.setUsername(username);
        user.setPassword(password);
        user.setStatus(status);
        user.setRemarks(remarks);
        try {
            if (user.getId() == null) {
                int id = DatabaseManager.addData(user);
                user.setId(id);
            } else {
                DatabaseManager.save(user);
            }
            if (newResourcesList != null) {
                for (Resources r : newResourcesList) {
                    r.setUser(user);
                    DatabaseManager.save(r);
                }
            }
            if (newEntityPrivilegelist != null) {
                for (EntityPrivilege ep : newEntityPrivilegelist) {
                    ep.setUser(user);
                    DatabaseManager.save(ep);
                }
            }
            admission.utils.MessageBox.info(this, MessageEnum.MSG_SAVE);
            clear();
            this.getUsers();
        } catch (HibernateException he) {
            admission.utils.MessageBox.error(this, he);
        }
    }

    private void delete() {
        user = (User) this.userList.getSelectedValue();
        if (user == null) {
            return;
        }

        try {
            DatabaseManager.deleteData(User.class.getName(), "id=" + user.getId());
            admission.utils.MessageBox.info(this, MessageEnum.MSG_DELETE);
            clear();
            this.getUsers();
        } catch (HibernateException he) {
            admission.utils.MessageBox.error(this, he);
        }
    }

    private void clear() {
        this.user = null;
        this.nameTextField.setText("");
        this.surnameTextField.setText("");
        this.mobileNoTextField.setText("");
        this.usernameTextField.setText("");
        this.passwordField.setText("");
        this.remarksTextArea.setText("");
        this.userList.clearSelection();
        this.statusComboBox.setSelectedIndex(-1);
        this.defaultListModel.clear();
        ((DefaultMutableTreeNode)defaultTreeModel.getRoot()).removeAllChildren();
        this.resourcesList = null;
        this.newResourcesList = null;
        this.jTabbedPane1.setSelectedIndex(0);
        this.nameTextField.requestFocus();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel basicDetailPanel;
    private javax.swing.JPanel buttonsPanel;
    private javax.swing.JButton deleteButton;
    private javax.swing.JButton deleteEntityPrivilegeButton;
    private javax.swing.JTree entityTree;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextField mobileNoTextField;
    private javax.swing.JButton moreButton;
    private javax.swing.JButton moreEntityPrivilegeButton;
    private javax.swing.JTextField nameTextField;
    private javax.swing.JPasswordField passwordField;
    private javax.swing.JTextArea remarksTextArea;
    private javax.swing.JList resourceList;
    private javax.swing.JPanel resourcesPanel;
    private javax.swing.JComboBox statusComboBox;
    private javax.swing.JTextField surnameTextField;
    private javax.swing.JPanel titlePanel;
    private javax.swing.JList userList;
    private javax.swing.JTextField usernameTextField;
    // End of variables declaration//GEN-END:variables
    private DocumentFilter documentFilter;
    private OperationButtons operationButtons;
    private User user;
    private List<Resources> resourcesList;
    private List<Resources> newResourcesList;
    private DefaultListModel<Resources> defaultListModel;
    private DefaultTreeModel defaultTreeModel;
    private List<EntityPrivilege> entityPrivilegelist;
    private List<EntityPrivilege> newEntityPrivilegelist;
}
