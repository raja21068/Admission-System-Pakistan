package admission.view.admission;

import admission.controller.DatabaseManager;
import admission.controller.JDBCDatabaseManager;
import admission.enums.CategoryLogicalCodeEnum;
import admission.enums.MessageEnum;
import admission.model.AdmissionList;
import admission.model.AdmissionListDetails;
import admission.model.AdmissionSession;
import admission.model.AdmissionYear;
import admission.model.Campus;
import admission.model.CampusProgramOfStudy;
import admission.model.PartRegistry;
import admission.model.security.Resources;
import com.yog.component.OperationButtons;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.text.AbstractDocument;
import javax.swing.text.DocumentFilter;
import org.hibernate.HibernateException;
import admission.utils.DateUtility;
import admission.utils.MessageBox;
import admission.utils.ModificationManager;
import admission.utils.RoundedBorder;
import admission.utils.UppercaseDocumentFilter;
import java.awt.Frame;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.Set;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JDialog;
import javax.swing.JFileChooser;

/**
 *
 * @author Yougeshwar
 */
public class AdmissionListPanel extends javax.swing.JPanel {

    public AdmissionListPanel() {
        initComponents();

        this.titlePanel.setBorder(RoundedBorder.createGradientBorder());
        this.buttonsPanel.add(this.getOperationButtons());

        this.modify = new ModificationManager();
        ((AbstractDocument) this.dateProvFormattedTextField.getDocument()).addDocumentListener(modify);
        ((AbstractDocument) this.dateFinalFormattedTextField.getDocument()).addDocumentListener(modify);
        ((AbstractDocument) this.remarksTextArea.getDocument()).addDocumentListener(modify);

        admissionListDetailsDialog = new AdmissionListDetailsDialog2(this);

        documentFilter = new UppercaseDocumentFilter();
        ((AbstractDocument) this.remarksTextArea.getDocument()).setDocumentFilter(documentFilter);
        
        promoteDialog = new JDialog((Frame) javax.swing.SwingUtilities.windowForComponent(this), "Copy From Year", true);
        promoteDialog.add(this.panelPromote);
        promoteDialog.setSize(410, 170);
        promoteDialog.setLocationRelativeTo(null);
        promoteDialog.setResizable(false);
        
        fileChooser = new javax.swing.JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                
    }

    @Override
    public void setVisible(boolean aFlag) {
        if (aFlag) {
            if (modify != null) {
                this.modify.setModifyLabel(operationButtons.getModifyLabel());
            }
            this.getCampus();
            this.getAdmissionYear();
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

    private JPanel getOperationButtons() {
        operationButtons = new OperationButtons() {
            @Override
            public void saveOperation(ActionEvent evt) {
                AdmissionListPanel.this.save();
            }

            @Override
            public void deleteOperation(ActionEvent evt) {
                AdmissionListPanel.this.delete();
            }

            @Override
            public void newOperation(ActionEvent evt) {
                AdmissionListPanel.this.clear();
            }

            @Override
            public void backOperation(ActionEvent evt) {
                if (internalFrame == null) {
                    System.exit(0);
                }
                internalFrame.setVisible(false);
            }
        };
//        operationButtons.setVisible(true, privileges., isSelected, closable, isIcon);
        operationButtons.setSize(335, 60);
        return operationButtons;
    }

    public void setPrivileges(Resources privileges) {
        this.privileges = privileges;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelPromote = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        comboBoxPromoteAdmissionList = new javax.swing.JComboBox();
        buttonPromoteOk = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        admissionIdTextField = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        admissionListList = new javax.swing.JList();
        jScrollPane2 = new javax.swing.JScrollPane();
        remarksTextArea = new javax.swing.JTextArea();
        jLabel5 = new javax.swing.JLabel();
        admissionSessionComboBox = new javax.swing.JComboBox();
        dateProvFormattedTextField = new javax.swing.JFormattedTextField();
        jLabel6 = new javax.swing.JLabel();
        dateFinalFormattedTextField = new javax.swing.JFormattedTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        listNoTextField = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        programTypeTextField = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        campusComboBox = new javax.swing.JComboBox();
        jLabel11 = new javax.swing.JLabel();
        admissionYearComboBox = new javax.swing.JComboBox();
        generateListButton = new javax.swing.JButton();
        isManualCheckBox = new javax.swing.JCheckBox();
        promoteButton = new javax.swing.JButton();
        buttonsPanel = new javax.swing.JPanel();
        titlePanel = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        progressBar = new javax.swing.JProgressBar();
        buttonExportWeb = new javax.swing.JButton();

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel1.setText("LIST");

        comboBoxPromoteAdmissionList.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        comboBoxPromoteAdmissionList.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        buttonPromoteOk.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        buttonPromoteOk.setText("OK");
        buttonPromoteOk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonPromoteOkActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelPromoteLayout = new javax.swing.GroupLayout(panelPromote);
        panelPromote.setLayout(panelPromoteLayout);
        panelPromoteLayout.setHorizontalGroup(
            panelPromoteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelPromoteLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelPromoteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(panelPromoteLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(buttonPromoteOk, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelPromoteLayout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(comboBoxPromoteAdmissionList, 0, 285, Short.MAX_VALUE)))
                .addContainerGap())
        );
        panelPromoteLayout.setVerticalGroup(
            panelPromoteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelPromoteLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelPromoteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(comboBoxPromoteAdmissionList, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(buttonPromoteOk)
                .addContainerGap())
        );

        jLabel2.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel2.setText("Admission List ID:");

        admissionIdTextField.setEditable(false);
        admissionIdTextField.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel3.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel3.setText("Date Prov:");

        jLabel4.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel4.setText("Remarks");

        admissionListList.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                admissionListListValueChanged(evt);
            }
        });
        jScrollPane1.setViewportView(admissionListList);

        jScrollPane2.setViewportView(remarksTextArea);

        jLabel5.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel5.setText("Admission Session:");

        admissionSessionComboBox.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        admissionSessionComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                admissionSessionComboBoxActionPerformed(evt);
            }
        });

        try {
            dateProvFormattedTextField.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##-##-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        dateProvFormattedTextField.setToolTipText("e.g: DD-MM-YYYY");

        jLabel6.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel6.setText("Date Final:");

        try {
            dateFinalFormattedTextField.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##-##-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        dateFinalFormattedTextField.setToolTipText("e.g: DD-MM-YYYY");

        jLabel7.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel7.setText("e.g: DD-MM-YYYY");

        jLabel8.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel8.setText("List Number:");

        jLabel9.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel9.setText("<html>Program Type & Session:</html>");

        programTypeTextField.setEditable(false);

        jLabel10.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel10.setText("Campus:");

        campusComboBox.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        campusComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                campusComboBoxActionPerformed(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel11.setText("Admission Year:");

        admissionYearComboBox.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        admissionYearComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                admissionYearComboBoxActionPerformed(evt);
            }
        });

        generateListButton.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        generateListButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/admission/view/images/Generate-tables-icon (1).png"))); // NOI18N
        generateListButton.setText("Generate List");
        generateListButton.setFocusPainted(false);
        generateListButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                generateListButtonActionPerformed(evt);
            }
        });

        isManualCheckBox.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        isManualCheckBox.setText("Manual");

        promoteButton.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        promoteButton.setText("Promote");
        promoteButton.setFocusPainted(false);
        promoteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                promoteButtonActionPerformed(evt);
            }
        });

        buttonsPanel.setPreferredSize(new java.awt.Dimension(335, 60));
        buttonsPanel.setLayout(new java.awt.BorderLayout());

        titlePanel.setLayout(new java.awt.BorderLayout());

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/admission/view/images/form-icon.png"))); // NOI18N
        jLabel12.setText("Admission List");
        jLabel12.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, java.awt.Color.darkGray), javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5)));
        titlePanel.add(jLabel12, java.awt.BorderLayout.PAGE_END);

        progressBar.setStringPainted(true);

        buttonExportWeb.setIcon(new javax.swing.ImageIcon(getClass().getResource("/admission/view/images/import-export-icon.png"))); // NOI18N
        buttonExportWeb.setText("Export Web");
        buttonExportWeb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonExportWebActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(titlePanel, javax.swing.GroupLayout.PREFERRED_SIZE, 591, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(generateListButton)
                                .addGap(6, 6, 6)
                                .addComponent(promoteButton)
                                .addGap(6, 6, 6)
                                .addComponent(buttonsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 346, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(4, 4, 4)
                                        .addComponent(campusComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 265, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(4, 4, 4)
                                        .addComponent(admissionYearComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 265, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel5)
                                        .addGap(4, 4, 4)
                                        .addComponent(admissionSessionComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 265, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(4, 4, 4)
                                        .addComponent(programTypeTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(4, 4, 4)
                                        .addComponent(admissionIdTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(67, 67, 67)
                                        .addComponent(isManualCheckBox, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(4, 4, 4)
                                        .addComponent(dateProvFormattedTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(4, 4, 4)
                                        .addComponent(jLabel6)
                                        .addGap(4, 4, 4)
                                        .addComponent(dateFinalFormattedTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(4, 4, 4)
                                        .addComponent(listNoTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(57, 57, 57)
                                        .addComponent(jLabel7))
                                    .addComponent(jLabel4)
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 380, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(6, 6, 6)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(progressBar, javax.swing.GroupLayout.PREFERRED_SIZE, 380, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(buttonExportWeb, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(titlePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(generateListButton)
                    .addComponent(promoteButton, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(3, 3, 3)
                                .addComponent(jLabel10))
                            .addComponent(campusComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(7, 7, 7)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(3, 3, 3)
                                .addComponent(jLabel11))
                            .addComponent(admissionYearComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(5, 5, 5)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(3, 3, 3)
                                .addComponent(jLabel5))
                            .addComponent(admissionSessionComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(6, 6, 6)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(2, 2, 2)
                                .addComponent(programTypeTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(4, 4, 4)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(4, 4, 4)
                                .addComponent(jLabel2))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(2, 2, 2)
                                .addComponent(admissionIdTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(isManualCheckBox))
                        .addGap(6, 6, 6)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(dateProvFormattedTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(dateFinalFormattedTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(2, 2, 2)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel6))))
                        .addGap(6, 6, 6)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(listNoTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(2, 2, 2)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel8)
                                    .addComponent(jLabel7))))
                        .addGap(6, 6, 6)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(buttonExportWeb)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(progressBar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {admissionIdTextField, admissionSessionComboBox, admissionYearComboBox, campusComboBox, dateFinalFormattedTextField, dateProvFormattedTextField, listNoTextField, programTypeTextField});

    }// </editor-fold>//GEN-END:initComponents

    private void admissionListListValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_admissionListListValueChanged
        AdmissionList admissionList = (AdmissionList) this.admissionListList.getSelectedValue();
        if (admissionList == null) {
            return;
        }

        this.admissionIdTextField.setText(String.valueOf(admissionList.getAdmissionListId()));
        this.dateProvFormattedTextField.setText(DateUtility.getDateToString(admissionList.getObDateFrom()));
        this.dateFinalFormattedTextField.setText(DateUtility.getDateToString(admissionList.getObDateTo()));
        this.listNoTextField.setText(String.valueOf(admissionList.getListNo()));
        this.isManualCheckBox.setSelected(admissionList.isIsManual());
        this.remarksTextArea.setText(admissionList.getRemarks());
        modify.setModify(false);
    }//GEN-LAST:event_admissionListListValueChanged

    private void admissionSessionComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_admissionSessionComboBoxActionPerformed
        // TODO add your handling code here:
        this.getAdmissionList();
    }//GEN-LAST:event_admissionSessionComboBoxActionPerformed

    private void campusComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_campusComboBoxActionPerformed
        // TODO add your handling code here:
        this.getAdmissionList();
    }//GEN-LAST:event_campusComboBoxActionPerformed

    private void admissionYearComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_admissionYearComboBoxActionPerformed
        // TODO add your handling code here:
        this.getAdmissionSession();
    }//GEN-LAST:event_admissionYearComboBoxActionPerformed

    private void generateListButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_generateListButtonActionPerformed
        // TODO add your handling code here:
        AdmissionList admissionList = (AdmissionList) this.admissionListList.getSelectedValue();
        if (admissionList == null) {
            MessageBox.info(this, MessageEnum.MSG_15);
            return;
        }
        admissionListDetailsDialog.setVisible(admissionList, true);
    }//GEN-LAST:event_generateListButtonActionPerformed

    private void promoteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_promoteButtonActionPerformed
        new Thread(){
            public void run(){
                promoteButton.setEnabled(false);
                promoteProcess();
                promoteButton.setEnabled(true);
            }
        }.start();
        //<editor-fold defaultstate="collapsed" desc="Yougesh Coding">
        // TODO add your handling code here:
        //        AdmissionList admissionList = (AdmissionList) this.admissionListList.getSelectedValue();
        //        if(admissionList == null) return;
        //
        //        List<AdmissionListDetails> list = DatabaseManager.getAdmissionListDetails(admissionList.getAdmissionListId());
        //        int count = 0;
        //        for (AdmissionListDetails ald : list) {
        //            Candidate cn = ald.getCandidate();
        //
        //            AdmissionListDetails activeAld = DatabaseManager.getActiveAdmissionListDetails(cn.getSeatNo(), cn.getAdmissionYear().getAdmissionYearId(), cn.getProgramType().getProgramTypeId());
        //            if(activeAld == null) {
        //                continue;
        //            }
        //
        //            activeAld.setActive((false));
        //            DatabaseManager.updateData(activeAld);
        //
        //            Set set = activeAld.getPartRegistries();
        //            Object[] toArray = set.toArray();
        //            for (Object toArray1 : toArray) {
        //                PartRegistry pr = (PartRegistry) toArray1;
        //                pr.setAdmissionListDetails(ald);
        //                DatabaseManager.updateData(ald);
        //            }
        //            count ++;
        //        }
        //
        //        MessageBox.info(this, count + " candidates promoted successfully");
        //</editor-fold>

    }//GEN-LAST:event_promoteButtonActionPerformed

    private void buttonPromoteOkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonPromoteOkActionPerformed
        promoteDialog.setVisible(false);
    }//GEN-LAST:event_buttonPromoteOkActionPerformed

    private void buttonExportWebActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonExportWebActionPerformed
        try{
            int i = fileChooser.showSaveDialog(this);
            if (!(i < 1)) {
                return;
            }
            AdmissionList admissionList = (AdmissionList) this.admissionListList.getSelectedValue();
            if (admissionList == null) {
                MessageBox.info(this, MessageEnum.MSG_15);
                return;
            }
            String basicPath = fileChooser.getSelectedFile().getAbsolutePath()+File.separator+admissionList.getAdmissionSession().getProgramType().getName()+"_"+admissionList.getCampus().getLocation()+"_LIST_"+admissionList.getListNo()+"_"+admissionList.getAdmissionSession().getAdmissionYear().getYear();
            PrintStream out = new PrintStream(new FileOutputStream(new File(basicPath+"_CANDIDATE_RESULT.CSV")));

            List<Object[]> resultForAdmissionList = JDBCDatabaseManager.getexportResultForBacheclorAdmissionList(admissionList.getAdmissionSession().getProgramType().getProgramTypeId(), admissionList.getAdmissionSession().getAdmissionYear().getAdmissionYearId(), admissionList.getAdmissionListId());
                StringBuilder builder = new StringBuilder("");
                builder.append("CANDIDATE_ID").append(",");
                builder.append("SEAT_NO").append(","); 
                builder.append("\"").append("NAME").append("\"").append(",");
                builder.append("\"").append("FATHERS NAME").append("\"").append(",");
                builder.append("\"").append("DISTRICT").append("\"").append(",");
                builder.append("AREA").append(",");
                builder.append("\"").append("DEGREE").append("\"").append(",");
                builder.append("SSC_OBTAINED").append(",");
                builder.append("SSC_PERC").append(",");
                builder.append("HSC_OBTAINED").append(",");
                builder.append("HSC_PERC").append(",");
                builder.append("GRD_OBTAINED").append(","); // 
                builder.append("GRD_PERC").append(",");
                builder.append("DEDUCTION_MARKS").append(",");               
                builder.append("TEST_SCORE").append(",");               
                builder.append("TEST_PERC").append(",");
                builder.append("CPN").append(",");               
                builder.append("\"").append("CAMPUS").append("\"").append(",");
                builder.append("\"").append("CATEGORY").append("\"").append(",");
                builder.append("\"").append("DISCIPLIINE").append("\"").append(",");
                builder.append("CHOICE_NO").append(",");               
                builder.append("CAMPUS_ID").append(",");
                builder.append("PROGRAM_TYPE_ID").append(",");
                builder.append("FEES_AMOUNT").append(",");
                builder.append("\"").append("LAST_DATE").append("\"").append(",");               
                builder.append("ADMISSION_SESSION").append(",");
                builder.append("ADMISSION_LIST").append(",");
                builder.append("\"").append("OBJECTION REMARKS").append("\"").append(",");
                builder.append("MARKS_AFTER_DEDUCTION").append(",");
                builder.append("\"").append("ISSER").append("\"").append(",");
                builder.append("SSC_TOTAL").append(",");
                builder.append("HSC_TOTAL").append(",");
                builder.append("GRD_TOTAL").append(",");
                builder.append("SSC_YEAR").append(",");
                builder.append("HSC_YEAR").append(",");
                builder.append("GRD_YEAR");               
                builder.append(",").append("EMAIL").append(",");
                builder.append("FAMILY_MOBILE");
                out.println(builder.toString());
                out.flush();
            for(Object[] result: resultForAdmissionList){
                builder = new StringBuilder("");
                builder.append(result[0]).append(","); //CANDIDATE_ID
                builder.append(result[1]).append(","); //SEAT_NO
                builder.append("\"").append(result[2]).append("\"").append(","); //NAME
                builder.append("\"").append(result[3]).append("\"").append(","); //FATHERS NAME
                builder.append("\"").append(result[4]).append("\"").append(","); // DISTRICT
                builder.append(result[5]).append(","); // AREA
                builder.append("\"").append(result[6]).append("\"").append(","); // DEGREE
                builder.append(result[7]).append(","); // SSC_OBTAINED
                builder.append(result[8]).append(","); // SSC_PERC
                builder.append(result[9]).append(","); // HSC_OBTAINED
                builder.append(result[10]).append(","); // HSC_PERC
                builder.append(/*resultForAdmissionList.getInt(11)*/"0").append(","); // GRD_OBTAINED
                builder.append(/*resultForAdmissionList.getFloat(12)*/"0").append(","); // GRD_PERC
                builder.append(result[13]).append(","); // DEDUCTION_MARKS               
                builder.append(result[14]).append(","); // TEST_SCORE               
                builder.append(result[15]).append(","); // TEST_PERC               
                builder.append(result[16]).append(","); // CPN               
                builder.append("\"").append(result[17]).append("\"").append(","); // CAMPUS
                builder.append("\"").append( CategoryLogicalCodeEnum.values()[(Integer)result[18]]).append("\"").append(","); // CATEGORY
                builder.append("\"").append(result[19]).append("\"").append(","); // DISCIPLIINE
                builder.append(result[20]).append(","); // CHOICE_NO               
                builder.append(result[21]).append(","); // CAMPUS_ID               
                builder.append(result[22]).append(","); // PROGRAM_TYPE_ID               
                builder.append(result[23]).append(","); // FEES_AMOUNT               
                builder.append("\"").append(result[24]).append("\"").append(","); // LAST_DATE               
                builder.append(result[25]).append(","); // ADMISSION_SESSION               
                builder.append(result[26]).append(","); // ADMISSION_LIST               
                builder.append("\"").append(result[27]).append("\"").append(","); // OBJECTION REMARKS
                builder.append(result[28]).append(","); // MARKS_AFTER_DEDUCTION               
                builder.append("\"").append(result[29]).append("\"").append(","); // ISSER
                builder.append(result[30]).append(","); // SSC_TOTAL               
                builder.append(result[31]).append(","); // HSC_TOTAL               
                builder.append(0).append(","); // GRD_TOTAL  30             
                builder.append(result[33]).append(","); // SSC_YEAR               
                builder.append(result[34]).append(","); // HSC_YEAR               
                builder.append(0); // GRD_YEAR               
                builder.append(",").append(result[36]).append(","); // EMAIL               
                builder.append(result[37]); // FAMILY_MOBILE               
                
                out.println(builder.toString());
                out.flush();
            }
            out.close();
            MessageBox.info(this, "Succesffuly exported..");
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }//GEN-LAST:event_buttonExportWebActionPerformed

    private void getCampus() {
        admissionListDataList.clear();
        this.admissionListList.setListData(admissionListDataList.toArray());
        this.campusComboBox.removeAllItems();
        List<Campus> list = DatabaseManager.getData(Campus.class.getName(), "displayOrder");

        for (Campus list1 : list) {
            this.campusComboBox.addItem(list1);
        }
    }

    private void getAdmissionYear() {
        admissionListDataList.clear();
        this.admissionListList.setListData(admissionListDataList.toArray());
        this.admissionYearComboBox.removeAllItems();
        List<AdmissionYear> list = DatabaseManager.getData(AdmissionYear.class.getName(), "year DESC");

        for (AdmissionYear list1 : list) {
            this.admissionYearComboBox.addItem(list1);
        }
    }

    private void getAdmissionSession() {
        admissionListDataList.clear();
        this.admissionListList.setListData(admissionListDataList.toArray());
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

    private void getAdmissionList() {
        admissionListDataList.clear();
        this.admissionListList.setListData(admissionListDataList.toArray());

        Campus campus = (Campus) this.campusComboBox.getSelectedItem();
        AdmissionSession as = (AdmissionSession) this.admissionSessionComboBox.getSelectedItem();
        if (as == null || campus == null) {
            return;
        }

        admissionListDataList = DatabaseManager.getData(AdmissionList.class, "campus.campusId = " + campus.getCampusId() + " AND admissionSession.admissionSessionId = " + as.getAdmissionSessionId(), "listNo DESC");
//        admissionListDataList.addAll(campus.getAdmissionLists());
        this.admissionListList.setListData(admissionListDataList.toArray());
        comboBoxPromoteAdmissionList.setModel(new DefaultComboBoxModel(admissionListDataList.toArray()));
    }

    private void save() {
        String dateProvS = this.dateProvFormattedTextField.getText();
        String dateFinalS = this.dateFinalFormattedTextField.getText();
        Integer listNo = Integer.parseInt(this.listNoTextField.getText());

        Date dateProv = DateUtility.getStringToDate(dateProvS);
        Date dateFinal = DateUtility.getStringToDate(dateFinalS);
        boolean isManual = (isManualCheckBox.isSelected());
        String remarks = this.remarksTextArea.getText();

        if (dateProv == null || dateFinal == null) {
            return;
        }

        Campus campus = (Campus) this.campusComboBox.getSelectedItem();
        AdmissionSession admissionSession = (AdmissionSession) this.admissionSessionComboBox.getSelectedItem();
        AdmissionList admissionList = (AdmissionList) this.admissionListList.getSelectedValue();
        if (campus == null || admissionSession == null) {
            return;
        }
        if (admissionList == null) {
            admissionList = new AdmissionList();
        }

        admissionList.setCampus(campus);
        admissionList.setAdmissionSession(admissionSession);
        admissionList.setObDateFrom(dateProv);
        admissionList.setObDateTo(dateFinal);
        admissionList.setListNo(listNo);
        admissionList.setIsManual(isManual);
        admissionList.setRemarks(remarks);

        try {
            DatabaseManager.save(admissionList);
            MessageBox.info(this, MessageEnum.MSG_SAVE);
            DatabaseManager.refresh(campus);
            DatabaseManager.refresh(admissionSession);
            this.getAdmissionList();
            clear();
        } catch (HibernateException he) {
            Logger.getLogger(AdmissionListInternalFrame.class.getName()).log(Level.SEVERE, null, he);
            MessageBox.error(this, he);
        }
    }

    private void delete() {
        Campus campus = (Campus) this.campusComboBox.getSelectedItem();
        AdmissionSession admissionSession = (AdmissionSession) this.admissionSessionComboBox.getSelectedItem();
        AdmissionList admissionList = (AdmissionList) this.admissionListList.getSelectedValue();
        if (campus == null || admissionSession == null || admissionList == null) {
            return;
        }

        try {
            DatabaseManager.deleteData(AdmissionList.class.getName(), "admissionListId=" + admissionList.getAdmissionListId());
            MessageBox.info(this, MessageEnum.MSG_DELETE);
            DatabaseManager.refresh(campus);
            DatabaseManager.refresh(admissionSession);
            this.getAdmissionList();
            clear();
        } catch (HibernateException he) {
            Logger.getLogger(AdmissionListInternalFrame.class.getName()).log(Level.SEVERE, null, he);
            MessageBox.error(this, he);
        }
    }

    private void clear() {
        this.admissionIdTextField.setText("");
        this.dateProvFormattedTextField.setText("");
        this.dateFinalFormattedTextField.setText("");
        this.admissionListList.clearSelection();
        modify.setModify(false);
    }

    public void setInternalFrame(JInternalFrame internalFrame) {
        this.internalFrame = internalFrame;
    }

    
    
    private void promote(AdmissionListDetails prevoiusALD, AdmissionListDetails newALD,PrintStream outPromoted,boolean deletedPrevious,List<AdmissionListDetails> aldsTobeDeleted) {
        try {
            Set<PartRegistry> partRegistries = prevoiusALD.getPartRegistries();
            for (PartRegistry partRegistry : partRegistries) {
                partRegistry.setAdmissionListDetails(newALD);
                DatabaseManager.updateData(partRegistry);
            }
            prevoiusALD.setActive(false);
            newALD.setActive(true);
            DatabaseManager.updateData(prevoiusALD);
            DatabaseManager.updateData(newALD);
            StringBuilder builder = new StringBuilder("");
            builder.append(prevoiusALD.getCandidate().getSeatNo()).append(",");
            builder.append(prevoiusALD.getCandidate().getName()).append(",");
            builder.append(prevoiusALD.getCampusCategory().getCategory().getName()).append(",");
            builder.append(newALD.getCampusCategory().getCategory().getName());
            outPromoted.println(builder.toString());
            outPromoted.flush();
            
//            if(deletedPrevious){
                aldsTobeDeleted.add(prevoiusALD);
//            }
        } catch (Exception ex) {
            ex.printStackTrace(System.err);
        }
    }
    
    private void notPromote(AdmissionListDetails prevoiusALD, List<AdmissionListDetails> newALDs, PrintStream outNotPromoted){
            StringBuilder builder = new StringBuilder("");
            builder.append(prevoiusALD.getCandidate().getSeatNo()).append(",");
            builder.append(prevoiusALD.getCandidate().getName()).append(",");
            builder.append(prevoiusALD.getCampusCategory().getCategory().getName());
            for (AdmissionListDetails newALD : newALDs) {
                builder.append(",").append(newALD.getCampusCategory().getCategory().getName());
            }
            outNotPromoted.println(builder.toString());
            outNotPromoted.flush();
    }
    
    
    private void promoteProcess(){
        Date dat = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("(dd-MM-YYYY)hh_mm_a");
        int option = MessageBox.confirm2(this, "do you want to promote in next list?");
        if (option != JOptionPane.YES_OPTION) {
            return;
        }
        AdmissionList admissionList = (AdmissionList) this.admissionListList.getSelectedValue();
        if (admissionList == null) {
            MessageBox.info(this, "Please select any list..!");
            return;
        }
        comboBoxPromoteAdmissionList.removeItem(admissionList);
        promoteDialog.setVisible(true);
        AdmissionList newAdmissionList = (AdmissionList)comboBoxPromoteAdmissionList.getSelectedItem();
        
//        AdmissionList newAdmissionList = DatabaseManager.getNextAdmissionList(admissionList);
        if (newAdmissionList == null) {
            MessageBox.warning(this, "There is no next list..!");
            return;
        }
        
        //<editor-fold defaultstate="collapsed" desc="creating Promoted & non-promoted files">
        PrintStream outPromote = null;
        PrintStream outNotPromote = null;
        try{
            outPromote = new PrintStream(new FileOutputStream( new File(admissionList.getAdmissionSession().getProgramType().getName()+"-"+admissionList.getCampus().getLocation()+"-Promoted - "+sdf.format(dat)+".csv")));
            outNotPromote = new PrintStream(new FileOutputStream(new File(admissionList.getAdmissionSession().getProgramType().getName()+"-"+admissionList.getCampus().getLocation()+"-Not-Promoted - "+sdf.format(dat)+".csv")));
            outPromote.println("Seat#,Name,Previous,New");
            outNotPromote.println("Seat#,Name,Previous,New");
        }catch(FileNotFoundException ex){
            ex.printStackTrace(System.err);
            JOptionPane.showMessageDialog(this, "Error occured while creating file..! opration failed..!");
            return;
        }
        //</editor-fold>


        List<AdmissionListDetails> activeAdmissionListDetails = DatabaseManager.getActiveAdmissionListDetails(admissionList);
        int progress = 0;
        progressBar.setMinimum(progress);
        progressBar.setMaximum(activeAdmissionListDetails.size()-1);
        
        List<AdmissionListDetails> aldsTobeDeleted = new LinkedList();
        
        for (AdmissionListDetails previousALD : activeAdmissionListDetails) {
            progressBar.setValue(progress++);
    

            List<AdmissionListDetails> newALDs = DatabaseManager.getAdmissionListDetails(previousALD.getCandidate(), newAdmissionList);
            if(newALDs.isEmpty()){
                continue;
            }
            
            //<editor-fold defaultstate="collapsed" desc="analyzing previous selection">
            boolean previosIsMerit = previousALD.getCampusCategory().getCategory().getCategoryLogicalCodeEnum() != CategoryLogicalCodeEnum.SELF_FINANCE_QUOTA;
            boolean previosSelfFinance = previousALD.getCampusCategory().getCategory().getCategoryLogicalCodeEnum() == CategoryLogicalCodeEnum.SELF_FINANCE_QUOTA;
            boolean previousIsMorning = previousALD.getCposGroup().getCampusProgramOfStudy().getShift().getIsMorning();
            int previousChoiceNo = previousALD.getCandidateProgramOfStudy().getChoiceNo();
            //</editor-fold>
            
            //<editor-fold defaultstate="collapsed" desc="analysing new selections">
            
            boolean newMerit = false;
            int newMeritIndex = -1;
            boolean newSFM = false;
            int newSFMIndex = -1;
            boolean newEvening = false;
            int newEveningIndex = -1;
            for (AdmissionListDetails newALD : newALDs) {
                CampusProgramOfStudy campusProgramOfStudy = newALD.getCposGroup().getCampusProgramOfStudy();
                Boolean isMorning = campusProgramOfStudy.getShift().getIsMorning();
                Boolean isSelfFinance = (newALD.getCampusCategory().getCategory().getCategoryLogicalCodeEnum() == CategoryLogicalCodeEnum.SELF_FINANCE_QUOTA)
                        || (newALD.getCampusCategory().getCategory().getCategoryLogicalCodeEnum() == CategoryLogicalCodeEnum.OP_SELF_FINANCE_QUOTA);
                
                if (!isSelfFinance) {
                    newMerit = true;
                    newMeritIndex = newALDs.indexOf(newALD);
                } else if (isSelfFinance && isMorning) {
                    newSFM = true;
                    newSFMIndex = newALDs.indexOf(newALD);
                } else {
                    newEvening = true;
                    newEveningIndex = newALDs.indexOf(newALD);
                }
            }
               //</editor-fold>

            if (newALDs.size() == 1) {
                AdmissionListDetails newALD = newALDs.get(0);

                //<editor-fold defaultstate="collapsed" desc="Merit ---->">
               /*Previous Merit*/
                if (previosIsMerit) {
                    /*new Merit*/
                    if (newMerit) {
                        promote(previousALD, newALD,outPromote,false,aldsTobeDeleted);
                        continue;
                    } else {
                        /*new SFM / Evening*/
                    }
                } //</editor-fold>
                
                //<editor-fold defaultstate="collapsed" desc="SFM---->">
                /*Previous Sekf Finance Morning*/ 
                else if (previosSelfFinance && previousIsMorning) {
                    /*SFM-->Merit*/
                    if (newMerit) {
                        /*SFM# >= Merit# */
                        if (previousChoiceNo >= newALD.getCandidateProgramOfStudy().getChoiceNo()) {
                            promote(previousALD, newALD,outPromote,true,aldsTobeDeleted);
                            continue;
                        } else {
                            /*SFM# < Merit#*/
                        }
                    } 
                    /*new SFM*/ 
                    else if (newSFM) {
                        /*SFM-->SFM*/
                        promote(previousALD, newALD,outPromote,false,aldsTobeDeleted);
                        continue;
                    } else {
                        /*SFM-->Evening*/
                    }
                } 
//             </editor-fold>
                
                //<editor-fold defaultstate="collapsed" desc="Evening---->">
                /*Previous Evening*/
                else {
                    /*new Merit && Evening# >= Merit#*/
                    if(newALD.getCampusCategory().getCategory().getCategoryLogicalCodeEnum() != CategoryLogicalCodeEnum.SELF_FINANCE_QUOTA
                            && previousALD.getCandidateProgramOfStudy().getChoiceNo()   >= newALD.getCandidateProgramOfStudy().getChoiceNo()){
                        promote(previousALD, newALD, outPromote,true,aldsTobeDeleted);
                        continue;
                    }
                    /*new Evening*/
                    else if(newEvening){
                        promote(previousALD, newALD,outPromote,false,aldsTobeDeleted);
                        continue;
                    }
                }
                //</editor-fold>
            
            } else if (newALDs.size() == 2) {

                //<editor-fold defaultstate="collapsed" desc="Merit ---->">
               /*Previous Merit*/
                if (previosIsMerit) {
                    if (newMerit && newSFM) {
                        promote(previousALD, newALDs.get(newMeritIndex),outPromote,false,aldsTobeDeleted);
                        continue;
                    } else if (newMerit && newEvening) {
                        promote(previousALD, newALDs.get(newMeritIndex),outPromote,false,aldsTobeDeleted);
                        continue;
                    } else if (newSFM && newEvening) {
                        
                    } else {

                    }
                } //</editor-fold>
                
                //<editor-fold defaultstate="collapsed" desc="SFM ---->">
                /*Previous self finance*/
                else if (previosSelfFinance  && previousIsMorning) {
                    /*SFM---> SFM, Merit*/
                    if (newSFM && newMerit) {
                        /*SFM# >= Merit#   && SFM#> SFM#*/
                        if ( previousChoiceNo >= newALDs.get(newMeritIndex).getCandidateProgramOfStudy().getChoiceNo()
                                && (previousChoiceNo > newALDs.get(newSFMIndex).getCandidateProgramOfStudy().getChoiceNo()) ) {
                            promote(previousALD, newALDs.get(newSFMIndex),outPromote,false,aldsTobeDeleted);
                            continue;
                        }
                        /*SFM# >= Merit# */
                        else if (previousChoiceNo >= newALDs.get(newMeritIndex).getCandidateProgramOfStudy().getChoiceNo()) {
                            promote(previousALD, newALDs.get(newMeritIndex),outPromote,true,aldsTobeDeleted);
                            continue;
                        }
                        /*SFM# > SFM#*/
                        else if (previousChoiceNo > newALDs.get(newSFMIndex).getCandidateProgramOfStudy().getChoiceNo()) {
                            promote(previousALD, newALDs.get(newSFMIndex),outPromote,false,aldsTobeDeleted);
                            continue;
                        } else {
                           
                        }
                    } 
                    /*SFM---> SFM, Evening*/ 
                    else if (newSFM && newEvening) {
                        promote(previousALD, newALDs.get(newSFMIndex),outPromote,false,aldsTobeDeleted);
                        continue;
                    }
                    /*SFM---> Merit, Evening*/ 
                    else if (newMerit && newEvening) {
                        /*SFM# >= Merit#*/
                        if (previousChoiceNo >= newALDs.get(newMeritIndex).getCandidateProgramOfStudy().getChoiceNo()) {
                            promote(previousALD, newALDs.get(newMeritIndex),outPromote,true,aldsTobeDeleted);
                            continue;
                        } else {
                            /**/
                        }
                    }

                } //</editor-fold>
                
                //<editor-fold defaultstate="collapsed" desc="Evening ---->">
                /*Previous Evening*/ 
                else {
                        if (newEvening) {
                        promote(previousALD, newALDs.get(newEveningIndex),outPromote,false,aldsTobeDeleted);
                        continue;
                    }
                }
                //</editor-fold>
            
            } else if (newALDs.size() == 3) {
                
                 //<editor-fold defaultstate="collapsed" desc="Merit ---->">
                /*Previous Merit*/
                if (previosIsMerit) {
                    promote(previousALD, newALDs.get(newMeritIndex),outPromote,false,aldsTobeDeleted);
                    continue;
                }
                //</editor-fold>
                
                 //<editor-fold defaultstate="collapsed" desc="SFM ---->">
               /*Previous self finance*/ 
                else if (previosSelfFinance && previousIsMorning) {
                        promote(previousALD, newALDs.get(newSFMIndex),outPromote,false,aldsTobeDeleted);
                        continue;
                }
                //</editor-fold>
                
                 //<editor-fold defaultstate="collapsed" desc="Evening ---->">
                /*Previous Evening*/
                else {
                        promote(previousALD, newALDs.get(newEveningIndex),outPromote,false,aldsTobeDeleted);
                        continue;
                }
                //</editor-fold>
            }
            notPromote(previousALD, newALDs,outNotPromote);
        }
        for(int i=0;i<aldsTobeDeleted.size();i++){
                DatabaseManager.deleteData(aldsTobeDeleted.remove(0));
        }
        progressBar.setValue(progress++);
        outPromote.close();
        outNotPromote.close();
        MessageBox.info(this, "Promoted successfully..!");
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField admissionIdTextField;
    private javax.swing.JList admissionListList;
    private javax.swing.JComboBox admissionSessionComboBox;
    private javax.swing.JComboBox admissionYearComboBox;
    private javax.swing.JButton buttonExportWeb;
    private javax.swing.JButton buttonPromoteOk;
    private javax.swing.JPanel buttonsPanel;
    private javax.swing.JComboBox campusComboBox;
    private javax.swing.JComboBox comboBoxPromoteAdmissionList;
    private javax.swing.JFormattedTextField dateFinalFormattedTextField;
    private javax.swing.JFormattedTextField dateProvFormattedTextField;
    private javax.swing.JButton generateListButton;
    private javax.swing.JCheckBox isManualCheckBox;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField listNoTextField;
    private javax.swing.JPanel panelPromote;
    private javax.swing.JTextField programTypeTextField;
    private javax.swing.JProgressBar progressBar;
    private javax.swing.JButton promoteButton;
    private javax.swing.JTextArea remarksTextArea;
    private javax.swing.JPanel titlePanel;
    // End of variables declaration//GEN-END:variables
    private DocumentFilter documentFilter;
    private Resources privileges;
    private AdmissionListDetailsDialog2 admissionListDetailsDialog;
    private OperationButtons operationButtons;
    private List<AdmissionList> admissionListDataList = new ArrayList<>();
    private ModificationManager modify;
    private JInternalFrame internalFrame;
    private JDialog promoteDialog;
    private javax.swing.JFileChooser fileChooser;

    
}
