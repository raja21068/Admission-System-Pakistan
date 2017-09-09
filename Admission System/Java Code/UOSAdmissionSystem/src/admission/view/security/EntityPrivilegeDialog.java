package admission.view.security;

import admission.controller.DatabaseManager;
import admission.model.AdmissionYear;
import admission.model.ProgramType;
import admission.model.security.EntityPrivilege;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultListModel;
import admission.utils.RoundedBorder;

/**
 *
 * @author Yougeshwar
 */
public class EntityPrivilegeDialog extends javax.swing.JDialog {

    private EntityPrivilegeDialog(java.awt.Frame parent) {
        super(parent, true);
        initComponents();

        this.titlePanel.setBorder(RoundedBorder.createGradientBorder());
        defaultListModel = new DefaultListModel<>();
        allowedEntityList.setModel(defaultListModel);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        titlePanel = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        cancelButton = new javax.swing.JButton();
        okayButton = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        admissionYearComboBox = new javax.swing.JComboBox();
        programTypeComboBox = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();
        ayAllowButton = new javax.swing.JButton();
        ptAllowButton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        allowedEntityList = new javax.swing.JList();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Entity Privilege");
        setResizable(false);

        titlePanel.setLayout(new java.awt.BorderLayout());

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/admission/view/images/form-icon.png"))); // NOI18N
        jLabel6.setText("Entity Privilege");
        jLabel6.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, java.awt.Color.darkGray), javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5)));
        titlePanel.add(jLabel6, java.awt.BorderLayout.CENTER);

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

        jLabel1.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel1.setText("Admission Year");

        admissionYearComboBox.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        programTypeComboBox.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        jLabel2.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel2.setText("Program Type");

        ayAllowButton.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        ayAllowButton.setText("Allow");
        ayAllowButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ayAllowButtonActionPerformed(evt);
            }
        });

        ptAllowButton.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        ptAllowButton.setText("Allow");
        ptAllowButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ptAllowButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(programTypeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(ptAllowButton))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(admissionYearComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(ayAllowButton)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(admissionYearComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ayAllowButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(programTypeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(ptAllowButton))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jScrollPane2.setViewportView(jPanel1);

        jScrollPane1.setViewportView(allowedEntityList);

        jLabel3.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel3.setText("Press delete key for remove from list");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(titlePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 422, Short.MAX_VALUE)
                    .addComponent(jScrollPane1)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(okayButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cancelButton)))
                .addContainerGap())
            .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING)
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {cancelButton, okayButton});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(titlePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cancelButton)
                        .addComponent(okayButton))
                    .addComponent(jLabel3))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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

    private void ayAllowButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ayAllowButtonActionPerformed
        // TODO add your handling code here:
        if (admissionYearComboBox.getSelectedItem() instanceof String) {
            return;
        }

        AdmissionYear ay = (AdmissionYear) admissionYearComboBox.getSelectedItem();
        if (ay == null) {
            return;
        }

        defaultListModel.addElement(new EntityPrivilege(AdmissionYear.class.getName(), ay.getAdmissionYearId(), ""));
    }//GEN-LAST:event_ayAllowButtonActionPerformed

    private void ptAllowButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ptAllowButtonActionPerformed
        if (programTypeComboBox.getSelectedItem() instanceof String) {
            return;
        }

        ProgramType pt = (ProgramType) programTypeComboBox.getSelectedItem();
        if (pt == null) {
            return;
        }

        defaultListModel.addElement(new EntityPrivilege(ProgramType.class.getName(), pt.getProgramTypeId(), ""));
    }//GEN-LAST:event_ptAllowButtonActionPerformed

    private void loadEntity(List<EntityPrivilege> list) {
        List<AdmissionYear> ayList = DatabaseManager.getData(AdmissionYear.class, "year DESC");
        admissionYearComboBox.addItem("");
        for (AdmissionYear ay : ayList) {
            LABEL:
            {
                for (EntityPrivilege ep : list) {
                    if (ep.getModelName().equals(AdmissionYear.class.getName())
                            && ep.getModelId() == ay.getAdmissionYearId().intValue()) {
                        break LABEL;
                    }
                }
                admissionYearComboBox.addItem(ay);
            }
        }
        List<ProgramType> ptList = DatabaseManager.getData(ProgramType.class, "isBachelor");
        programTypeComboBox.addItem("");
        for (ProgramType pt : ptList) {
            LABEL:
            {
                for (EntityPrivilege ep : list) {
                    if (ep.getModelName().equals(ProgramType.class.getName())
                            && ep.getModelId() == pt.getProgramTypeId().intValue()) {
                        break LABEL;
                    }
                }
                programTypeComboBox.addItem(pt);
            }
        }
    }

    public List<EntityPrivilege> getAllowdEntityList() {
        List<EntityPrivilege> list = new ArrayList<>();

        if (access) {
            for (int i = 0; i < defaultListModel.getSize(); i++) {
                EntityPrivilege ep = defaultListModel.get(i);
                list.add(ep);
            }
        }
        return list;
    }

    public static List<EntityPrivilege> showDialog(java.awt.Frame parent, List<EntityPrivilege> list) {
        EntityPrivilegeDialog ep = new EntityPrivilegeDialog(parent);
        ep.loadEntity(list);
        ep.setVisible(true);
        return ep.getAllowdEntityList();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox admissionYearComboBox;
    private javax.swing.JList allowedEntityList;
    private javax.swing.JButton ayAllowButton;
    private javax.swing.JButton cancelButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JButton okayButton;
    private javax.swing.JComboBox programTypeComboBox;
    private javax.swing.JButton ptAllowButton;
    private javax.swing.JPanel titlePanel;
    // End of variables declaration//GEN-END:variables
    private DefaultListModel<EntityPrivilege> defaultListModel;
    private boolean access;
}
