package admission.view.security;

import admission.model.security.Resources;
import admission.xmlparser.Resource;
import admission.xmlparser.ResourceCategory;
import admission.xmlparser.ResourceXMLParser;
import java.util.ArrayList;
import java.util.List;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import admission.utils.RoundedBorder;

/**
 *
 * @author Yougeshwar
 */
public class ResourcesDialog extends javax.swing.JDialog {

    private ResourcesDialog(java.awt.Frame parent) {
        super(parent, true);
        initComponents();

        this.titlePanel.setBorder(RoundedBorder.createGradientBorder());
    }

    public void loadResources(List<String> list) {
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("Resources");

        List<ResourceCategory> resourceCategoryList = ResourceXMLParser.getResourceCategoryList();
        for (ResourceCategory resourceCategory : resourceCategoryList) {
            DefaultMutableTreeNode categoryNode = new DefaultMutableTreeNode(resourceCategory);
            List<Resource> resourceList = resourceCategory.getResourceList();
            admission.utils.Sorter.listSort(resourceList);
            for (Resource resource : resourceList) {
                if (list.contains(resource.getCode())) {
                    continue;
                }
                DefaultMutableTreeNode resourceNode = new DefaultMutableTreeNode(resource);
                categoryNode.add(resourceNode);
            }
            root.add(categoryNode);
        }

        defaultTreeModel = new DefaultTreeModel(root);
        resourcesTree.setModel(defaultTreeModel);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        titlePanel = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        resourcesTree = new javax.swing.JTree();
        jSeparator1 = new javax.swing.JSeparator();
        cancelButton = new javax.swing.JButton();
        okayButton = new javax.swing.JButton();
        resourceLabel = new javax.swing.JLabel();
        allowCheckBox = new javax.swing.JCheckBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        titlePanel.setLayout(new java.awt.BorderLayout());

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/admission/view/images/form-icon.png"))); // NOI18N
        jLabel6.setText("Resources");
        jLabel6.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, java.awt.Color.darkGray), javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5)));
        titlePanel.add(jLabel6, java.awt.BorderLayout.CENTER);

        resourcesTree.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                resourcesTreeMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(resourcesTree);

        cancelButton.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        cancelButton.setText("Cancel");
        cancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelButtonActionPerformed(evt);
            }
        });

        okayButton.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        okayButton.setText("OK");
        okayButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                okayButtonActionPerformed(evt);
            }
        });

        resourceLabel.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        resourceLabel.setForeground(new java.awt.Color(0, 51, 102));
        resourceLabel.setText("Resource Name");

        allowCheckBox.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        allowCheckBox.setText("Allow");
        allowCheckBox.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        allowCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                allowCheckBoxActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(titlePanel, javax.swing.GroupLayout.DEFAULT_SIZE, 524, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(okayButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cancelButton))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(resourceLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(allowCheckBox)))
                .addContainerGap())
            .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING)
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {cancelButton, okayButton});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(titlePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(allowCheckBox, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(resourceLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cancelButton)
                    .addComponent(okayButton))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void resourcesTreeMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_resourcesTreeMousePressed
        // TODO add your handling code here:
        resourceLabel.setVisible(false);
        allowCheckBox.setVisible(false);
        DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) resourcesTree.getLastSelectedPathComponent();
        if (selectedNode == null || !(selectedNode.getUserObject() instanceof Resource)) {
            return;
        }
        resource = (Resource) selectedNode.getUserObject();
        resourceLabel.setText(resource.getName());
        allowCheckBox.setSelected(resource.isAllow());
        resourceLabel.setVisible(true);
        allowCheckBox.setVisible(true);
    }//GEN-LAST:event_resourcesTreeMousePressed

    private void allowCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_allowCheckBoxActionPerformed
        // TODO add your handling code here:
        if (resource == null) {
            return;
        }

        resource.setAllow(allowCheckBox.isSelected());
    }//GEN-LAST:event_allowCheckBoxActionPerformed

    private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelButtonActionPerformed
        // TODO add your handling code here:
        access = false;
        dispose();
    }//GEN-LAST:event_cancelButtonActionPerformed

    private void okayButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_okayButtonActionPerformed
        // TODO add your handling code here:
        access = true;
        dispose();
    }//GEN-LAST:event_okayButtonActionPerformed

    public List<Resources> getAllowdResourceList() {
        List<Resources> list = new ArrayList<>();

        if (access) {
            DefaultMutableTreeNode root = (DefaultMutableTreeNode) defaultTreeModel.getRoot();
            int childCount = root.getChildCount();
            for (int i = 0; i < childCount; i++) {
                DefaultMutableTreeNode category = (DefaultMutableTreeNode) root.getChildAt(i);
                for (int j = 0; j < category.getChildCount(); j++) {
                    DefaultMutableTreeNode resourceNode = (DefaultMutableTreeNode) category.getChildAt(j);
                    Resource r = (Resource) resourceNode.getUserObject();

                    if (r.isAllow()) {
                        Resources rs = new Resources();
                        rs.setCode(r.getCode());
                        rs.setName(r.getName());
                        list.add(rs);
                    }
                }
            }
        }
        return list;
    }

    public static List<Resources> showDialog(java.awt.Frame parent, List<String> list) {
        ResourcesDialog rs = new ResourcesDialog(parent);
        rs.loadResources(list);
        rs.setVisible(true);
        return rs.getAllowdResourceList();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox allowCheckBox;
    private javax.swing.JButton cancelButton;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JButton okayButton;
    private javax.swing.JLabel resourceLabel;
    private javax.swing.JTree resourcesTree;
    private javax.swing.JPanel titlePanel;
    // End of variables declaration//GEN-END:variables
    private DefaultTreeModel defaultTreeModel;
    private Resource resource;
    private boolean access;
}
