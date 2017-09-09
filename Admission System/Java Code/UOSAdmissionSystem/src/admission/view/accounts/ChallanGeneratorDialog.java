package admission.view.accounts;

import admission.controller.DatabaseManager;
import admission.controller.JDBCDatabaseManager;
import admission.model.AdmissionList;
import admission.model.AdmissionSession;
import admission.model.AdmissionYear;
import admission.model.Campus;
import admission.model.Part;
import admission.model.ProgramType;
import admission.model.view.SelectionListView;
import admission.utils.DateUtility;
import admission.utils.MessageBox;
import java.util.List;
import javax.swing.JOptionPane;
import admission.utils.RoundedBorder;
import admission.utils.SecureKeyProvider;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.text.DateFormatter;

/**
 *
 * @author Raja Kumar & Jay Kumar
 */
public class ChallanGeneratorDialog extends javax.swing.JDialog {

    public ChallanGeneratorDialog(JFrame parent) {
        super(JOptionPane.getFrameForComponent(parent), true);
        initComponents();

        this.titlePanel.setBorder(RoundedBorder.createGradientBorder());

        admission.utils.Utility.hideOnEscape(this);

        this.setLocationRelativeTo(null);
    }

    @Override
    public void setVisible(boolean b) {
        if (b) {
            getCampus();
            getAdmissionYear();
        }
        super.setVisible(b);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        generateButton = new javax.swing.JButton();
        backButton = new javax.swing.JButton();
        titlePanel = new javax.swing.JPanel();
        jLabel38 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        admissionYearComboBox = new javax.swing.JComboBox();
        campusComboBox = new javax.swing.JComboBox();
        jLabel5 = new javax.swing.JLabel();
        admissionSessionComboBox = new javax.swing.JComboBox();
        admissionListComboBox = new javax.swing.JComboBox();
        jLabel6 = new javax.swing.JLabel();
        progressBar = new javax.swing.JProgressBar();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel7 = new javax.swing.JLabel();
        partComboBox = new javax.swing.JComboBox();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        deleteChallanButton = new javax.swing.JButton();
        validFromFormattedTextField = new javax.swing.JFormattedTextField();
        validToFormattedTextField = new javax.swing.JFormattedTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Challan Generator");

        generateButton.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        generateButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Generate-tables-icon (1).png"))); // NOI18N
        generateButton.setText("Generate");
        generateButton.setToolTipText("");
        generateButton.setFocusPainted(false);
        generateButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                generateButtonActionPerformed(evt);
            }
        });

        backButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Gnome-Go-Home-32.png"))); // NOI18N
        backButton.setToolTipText("Back");
        backButton.setFocusPainted(false);
        backButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backButtonActionPerformed(evt);
            }
        });

        titlePanel.setLayout(new java.awt.BorderLayout());

        jLabel38.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel38.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Gnome-Edit-Select-All-40.png"))); // NOI18N
        jLabel38.setText("Challan Generator");
        jLabel38.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, java.awt.Color.darkGray), javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5)));
        titlePanel.add(jLabel38, java.awt.BorderLayout.PAGE_END);

        jLabel10.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel10.setText("Campus:");

        jLabel11.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel11.setText("Admission Year:");

        admissionYearComboBox.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        admissionYearComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                admissionYearComboBoxActionPerformed(evt);
            }
        });

        campusComboBox.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        campusComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                campusComboBoxActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel5.setText("Admission Session:");

        admissionSessionComboBox.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        admissionSessionComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                admissionSessionComboBoxActionPerformed(evt);
            }
        });

        admissionListComboBox.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        jLabel6.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel6.setText("Admission List:");

        progressBar.setStringPainted(true);

        jLabel7.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel7.setText("Part:");

        partComboBox.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        jLabel8.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel8.setText("Valid From:");

        jLabel9.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel9.setText("To:");

        deleteChallanButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Gnome-Edit-Delete-32.png"))); // NOI18N
        deleteChallanButton.setText("Delete Temp Submitted Challan");
        deleteChallanButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteChallanButtonActionPerformed(evt);
            }
        });

        try {
            validFromFormattedTextField.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##-##-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        validFromFormattedTextField.setToolTipText("DD-MM-YYYY");

        try {
            validToFormattedTextField.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##-##-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        validToFormattedTextField.setToolTipText("DD-MM-YYYY");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(titlePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jSeparator1)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(deleteChallanButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(generateButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(backButton, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(progressBar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(validFromFormattedTextField)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jLabel9)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(validToFormattedTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(admissionYearComboBox, 0, 265, Short.MAX_VALUE)
                                    .addComponent(campusComboBox, 0, 265, Short.MAX_VALUE)
                                    .addComponent(admissionSessionComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(admissionListComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(partComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(titlePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(campusComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10))
                .addGap(7, 7, 7)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(admissionYearComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(admissionSessionComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(admissionListComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(partComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jLabel9)
                    .addComponent(validFromFormattedTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(validToFormattedTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(progressBar, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(generateButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(deleteChallanButton))
                    .addComponent(backButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {admissionListComboBox, admissionSessionComboBox, admissionYearComboBox, campusComboBox, partComboBox, validFromFormattedTextField, validToFormattedTextField});

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void backButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backButtonActionPerformed
        dispose();
    }//GEN-LAST:event_backButtonActionPerformed

    private void generateButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_generateButtonActionPerformed
        final AdmissionList al = (AdmissionList) admissionListComboBox.getSelectedItem();
        final Part part = (Part) partComboBox.getSelectedItem();
        final AdmissionYear ay = (AdmissionYear) admissionYearComboBox.getSelectedItem();
        AdmissionSession as = (AdmissionSession) admissionSessionComboBox.getSelectedItem();
        if (al == null || part == null || ay == null || as == null) {
            return;
        }
        
        String date1 = validFromFormattedTextField.getText().trim();
        String date2 = validToFormattedTextField.getText().trim();
        if(date1.length() <= 4 || date2.length() <= 4) {
            MessageBox.error(this, "Fill date field properly");
            return;
        }
        
        final long validFrom = DateUtility.toMilliseconds(date1);
        final long validTo = DateUtility.toMilliseconds(date2);
        final ProgramType pt = as.getProgramType();

        new Thread() {
            @Override public void run() {
                generateButton.setEnabled(false);
                List<SelectionListView> list = DatabaseManager.getData(SelectionListView.class, "admissionListId = " + al.getAdmissionListId(), "seatNo");
                List<Long> keys = JDBCDatabaseManager.getCodes();
                
                progressBar.setMaximum(list.size());
                for (int i = 0; i < list.size(); i++) {
                    SelectionListView slv = list.get(i);
                    
                    String code = SecureKeyProvider.encypt(ay.getYear(), "" + pt.getName().charAt(0), part.getRemarks(), keys);
                    String sql = "INSERT INTO YOG_TEMP_CHALLAN (ADMISSION_LIST_DETAILS_ID, PART_ID, CODE, VALID_FROM, VALID_TO, STATUS) VALUES "
                            + "(" + slv.getId() + ", " + part.getPartId() + ", '" + code + "', " + validFrom + ", " + validTo + ", 0)";
                    
                    try {
                        JDBCDatabaseManager.executeSQL(sql);
                    } catch (SQLException ex) {
                        Logger.getLogger(ChallanGeneratorDialog.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                    progressBar.setValue(i);
                }
                generateButton.setEnabled(true);
                MessageBox.info(ChallanGeneratorDialog.this, list.size() + " Temp challan generated successfully");
            }
        }.start();
    }//GEN-LAST:event_generateButtonActionPerformed

    private void admissionYearComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_admissionYearComboBoxActionPerformed
        getAdmissionSession();
    }//GEN-LAST:event_admissionYearComboBoxActionPerformed

    private void campusComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_campusComboBoxActionPerformed
        getAdmissionList();
    }//GEN-LAST:event_campusComboBoxActionPerformed

    private void admissionSessionComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_admissionSessionComboBoxActionPerformed
        getPart();
        getAdmissionList();
    }//GEN-LAST:event_admissionSessionComboBoxActionPerformed

    private void deleteChallanButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteChallanButtonActionPerformed
        String sql = "DELETE FROM YOG_TEMP_CHALLAN WHERE STATUS = 1";
        try {
            int rows = JDBCDatabaseManager.executeSQL(sql);
            MessageBox.info(this, rows + " submitted temp challan deleted");
        } catch(SQLException ex) {
            Logger.getLogger(ChallanGeneratorDialog.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_deleteChallanButtonActionPerformed

    private void getCampus() {
        this.campusComboBox.removeAllItems();
        List<Campus> list = DatabaseManager.getData(Campus.class.getName(), "displayOrder");

        for (Campus list1 : list) {
            this.campusComboBox.addItem(list1);
        }
    }

    private void getAdmissionYear() {
        this.admissionYearComboBox.removeAllItems();
        List<AdmissionYear> list = DatabaseManager.getData(AdmissionYear.class.getName(), "year DESC");

        for (AdmissionYear list1 : list) {
            this.admissionYearComboBox.addItem(list1);
        }
    }

    private void getAdmissionSession() {
        this.admissionSessionComboBox.removeAllItems();

        AdmissionYear ay = (AdmissionYear) this.admissionYearComboBox.getSelectedItem();
        if (ay == null) {
            return;
        }

        List<AdmissionSession> list = DatabaseManager.getData(AdmissionSession.class, "admissionYear.admissionYearId = " + ay.getAdmissionYearId(), "admissionSessionId");

        for (AdmissionSession as : list) {
            this.admissionSessionComboBox.addItem(as);
        }
    }

    private void getPart() {
        partComboBox.removeAllItems();
        AdmissionSession as = (AdmissionSession) this.admissionSessionComboBox.getSelectedItem();
        if (as == null) {
            return;
        }

        List<Part> list = DatabaseManager.getData(Part.class, "programType.programTypeId = " + as.getProgramType().getProgramTypeId(), "partNo");
        for (Part part : list) {
            partComboBox.addItem(part);
        }
    }

    private void getAdmissionList() {
        admissionListComboBox.removeAllItems();
        Campus campus = (Campus) campusComboBox.getSelectedItem();
        AdmissionSession as = (AdmissionSession) this.admissionSessionComboBox.getSelectedItem();
        if (as == null || campus == null) {
            return;
        }

        List<AdmissionList> list = DatabaseManager.getData(AdmissionList.class, "admissionSession.admissionSessionId = " + as.getAdmissionSessionId() + " AND campus.campusId = " + campus.getCampusId(), "listNo DESC");
        for (AdmissionList al : list) {
            admissionListComboBox.addItem(al);
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox admissionListComboBox;
    private javax.swing.JComboBox admissionSessionComboBox;
    private javax.swing.JComboBox admissionYearComboBox;
    private javax.swing.JButton backButton;
    private javax.swing.JComboBox campusComboBox;
    private javax.swing.JButton deleteChallanButton;
    private javax.swing.JButton generateButton;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JComboBox partComboBox;
    private javax.swing.JProgressBar progressBar;
    private javax.swing.JPanel titlePanel;
    private javax.swing.JFormattedTextField validFromFormattedTextField;
    private javax.swing.JFormattedTextField validToFormattedTextField;
    // End of variables declaration//GEN-END:variables

}
