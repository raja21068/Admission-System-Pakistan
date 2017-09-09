package admission.view.fees;

import admission.controller.DatabaseManager;
import admission.enums.FeeCategoryTypeEnum;
import admission.enums.FeeOfEnum;
import admission.enums.MessageEnum;
import admission.enums.ProgramTypeEnum;
import admission.enums.ShiftEnum;
import admission.model.AdmissionYear;
import admission.model.fee.Fee;
import admission.model.fee.FeeCategory;
import admission.model.fee.FeeModel;
import com.yog.component.VerticalTableHeaderCellRenderer;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractAction;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;
import javax.swing.event.CellEditorListener;
import javax.swing.event.ChangeEvent;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import org.hibernate.HibernateException;
import admission.utils.FormScroller;
import admission.utils.MessageBox;
import admission.utils.RoundedBorder;

/**
 *
 * @author Adeel, Yougeshwar Khatri 
 */
public class ClassFeeInternalFrame extends javax.swing.JInternalFrame {

    public ClassFeeInternalFrame() {
        initComponents();
        
        final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        screenSize.setSize(1020, screenSize.height - 100);
        setSize(screenSize);

        this.titlePanel.setBorder(RoundedBorder.createGradientBorder());
        this.titlePanel1.setBorder(RoundedBorder.createGradientBorder());
        FormScroller scroller = new FormScroller(jScrollPane1);
        scroller.setScrollInsets(new Insets(50, 0, 50, 0));
        
        admission.utils.Utility.hideOnEscape(this);

        this.jScrollPane1.getVerticalScrollBar().setUnitIncrement(20);
        
        this.feeTable.getDefaultEditor(Long.class).addCellEditorListener(new CellEditorListener() {
            @Override public void editingCanceled(ChangeEvent e) {
            }

            @Override public void editingStopped(ChangeEvent e) {
                int row = feeTable.getSelectedRow();
                int col = feeTable.getSelectedColumn();

                if (col > 0 && col <= feeCategoriesList.size()) {
                    int totalColumns = defaultTableModel.getColumnCount();
                    long count = 0L;
                    for (int i = 1; i < totalColumns - 1; i++) {
                        long fees = (Long) defaultTableModel.getValueAt(row, i);
                        count += fees;
                    }
                    defaultTableModel.setValueAt(count, row, totalColumns - 1);
                } else {
                    admission.utils.MessageBox.error(null, "Out of index");
                }
            }
        });
        
        this.feeTable.getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), "Enter");
        this.feeTable.getActionMap().put("Enter", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                TableCellEditor cellEditor = feeTable.getCellEditor();
                if (cellEditor != null) {
                    feeTable.getCellEditor().stopCellEditing();
                }

                int row = feeTable.getSelectedRow();
                int col = feeTable.getSelectedColumn() + 1;
                if (col > feeTable.getColumnCount() - 1) {
                    col = 0;
                    row += 1;
                    if (row > feeTable.getRowCount() - 1) {
                        row = 0;
                    }
                }
                feeTable.changeSelection(row, col, false, false);
            }
        });
        ((DefaultTableCellRenderer) feeTable.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);
        feeCategoriesList = new ArrayList<>();
        feeRowsList = new ArrayList<>();
        
        admission.utils.Utility.loadEnum(programTypeComboBox, ProgramTypeEnum.class);
        admission.utils.Utility.loadEnum(shiftComboBox, ShiftEnum.class);
        programTypeComboBox.removeItem(ProgramTypeEnum.BOTH);
        admission.utils.Utility.loadEnum(feeCategoryComboBox, FeeCategoryTypeEnum.class);
    }

    @Override
    public void setVisible(boolean aFlag) {
        if (aFlag) {
            this.getAdmissionYear();
        }
        super.setVisible(aFlag);
    }
    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        copyFromDialog = new javax.swing.JDialog();
        jLabel6 = new javax.swing.JLabel();
        yearComboBox = new javax.swing.JComboBox();
        okButton = new javax.swing.JButton();
        cancelButton = new javax.swing.JButton();
        titlePanel1 = new javax.swing.JPanel();
        jLabel38 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jScrollPane3 = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        admissionYearComboBox = new javax.swing.JComboBox();
        shiftComboBox = new javax.swing.JComboBox();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        programTypeComboBox = new javax.swing.JComboBox();
        feeCategoryComboBox = new javax.swing.JComboBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        feeTable = new javax.swing.JTable();
        jSeparator1 = new javax.swing.JSeparator();
        progressBar = new javax.swing.JProgressBar();
        topPanel = new javax.swing.JPanel();
        titlePanel = new javax.swing.JPanel();
        jLabel37 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        loadButton = new javax.swing.JButton();
        saveButton = new javax.swing.JButton();
        copySessionButton = new javax.swing.JButton();
        newAmountsButton = new javax.swing.JButton();
        hideButton = new javax.swing.JButton();

        jLabel6.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel6.setText("Year:");

        yearComboBox.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        okButton.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        okButton.setText("Ok");
        okButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                okButtonActionPerformed(evt);
            }
        });

        cancelButton.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        cancelButton.setText("Cancel");
        cancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelButtonActionPerformed(evt);
            }
        });

        titlePanel1.setLayout(new java.awt.BorderLayout());

        jLabel38.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel38.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Gnome-Edit-Copy-32.png"))); // NOI18N
        jLabel38.setText("Copy From Year");
        jLabel38.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, java.awt.Color.darkGray), javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5)));
        titlePanel1.add(jLabel38, java.awt.BorderLayout.CENTER);

        javax.swing.GroupLayout copyFromDialogLayout = new javax.swing.GroupLayout(copyFromDialog.getContentPane());
        copyFromDialog.getContentPane().setLayout(copyFromDialogLayout);
        copyFromDialogLayout.setHorizontalGroup(
            copyFromDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(titlePanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
            .addGroup(copyFromDialogLayout.createSequentialGroup()
                .addGroup(copyFromDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(copyFromDialogLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(yearComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, copyFromDialogLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(okButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cancelButton)))
                .addContainerGap())
            .addComponent(jSeparator2)
        );
        copyFromDialogLayout.setVerticalGroup(
            copyFromDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(copyFromDialogLayout.createSequentialGroup()
                .addComponent(titlePanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(copyFromDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(yearComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(copyFromDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(okButton)
                    .addComponent(cancelButton))
                .addContainerGap())
        );

        setClosable(true);
        setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);
        setIconifiable(true);
        setMaximizable(true);
        setTitle("Class Fee");
        setPreferredSize(new java.awt.Dimension(1008, 907));

        jPanel1.setLayout(new java.awt.GridBagLayout());

        jLabel1.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel1.setText("Admission Year:");

        jLabel2.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel2.setText("Shift:");

        admissionYearComboBox.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        admissionYearComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                admissionYearComboBoxActionPerformed(evt);
            }
        });

        shiftComboBox.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        shiftComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                shiftComboBoxActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel3.setText("Program Type:");

        jLabel4.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel4.setText("Fee of:");

        programTypeComboBox.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        programTypeComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                programTypeComboBoxActionPerformed(evt);
            }
        });

        feeCategoryComboBox.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        feeCategoryComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                feeCategoryComboBoxActionPerformed(evt);
            }
        });

        feeTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Class", "1", "2", "3", "4"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                true, true, false, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        feeTable.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        feeTable.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(feeTable);
        if (feeTable.getColumnModel().getColumnCount() > 0) {
            feeTable.getColumnModel().getColumn(0).setResizable(false);
            feeTable.getColumnModel().getColumn(0).setPreferredWidth(300);
            feeTable.getColumnModel().getColumn(1).setResizable(false);
            feeTable.getColumnModel().getColumn(1).setPreferredWidth(60);
            feeTable.getColumnModel().getColumn(2).setResizable(false);
            feeTable.getColumnModel().getColumn(2).setPreferredWidth(60);
            feeTable.getColumnModel().getColumn(3).setResizable(false);
            feeTable.getColumnModel().getColumn(3).setPreferredWidth(60);
            feeTable.getColumnModel().getColumn(4).setResizable(false);
            feeTable.getColumnModel().getColumn(4).setPreferredWidth(60);
        }

        progressBar.setStringPainted(true);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator1)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(admissionYearComboBox, 0, 185, Short.MAX_VALUE)
                            .addComponent(shiftComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(programTypeComboBox, 0, 171, Short.MAX_VALUE)
                            .addComponent(feeCategoryComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(progressBar, javax.swing.GroupLayout.DEFAULT_SIZE, 358, Short.MAX_VALUE)))
                .addContainerGap())
        );

        jPanel2Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {admissionYearComboBox, feeCategoryComboBox, programTypeComboBox, shiftComboBox});

        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(admissionYearComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(programTypeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2)
                        .addComponent(shiftComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel4)
                        .addComponent(feeCategoryComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(progressBar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 621, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel2Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {admissionYearComboBox, feeCategoryComboBox, programTypeComboBox, shiftComboBox});

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHEAST;
        jPanel1.add(jPanel2, gridBagConstraints);

        jScrollPane3.setViewportView(jPanel1);

        getContentPane().add(jScrollPane3, java.awt.BorderLayout.CENTER);

        topPanel.setLayout(new java.awt.BorderLayout());

        titlePanel.setLayout(new java.awt.BorderLayout());

        jLabel37.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel37.setIcon(new javax.swing.ImageIcon(getClass().getResource("/admission/view/images/form-icon.png"))); // NOI18N
        jLabel37.setText("Class Fee");
        jLabel37.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, java.awt.Color.darkGray), javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5)));
        titlePanel.add(jLabel37, java.awt.BorderLayout.CENTER);

        topPanel.add(titlePanel, java.awt.BorderLayout.CENTER);

        loadButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Gnome-Emblem-Downloads-32.png"))); // NOI18N
        loadButton.setToolTipText("Load");
        loadButton.setFocusPainted(false);
        loadButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loadButtonActionPerformed(evt);
            }
        });
        jPanel4.add(loadButton);

        saveButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Gnome-Media-Floppy-32.png"))); // NOI18N
        saveButton.setToolTipText("Save");
        saveButton.setFocusPainted(false);
        saveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveButtonActionPerformed(evt);
            }
        });
        jPanel4.add(saveButton);

        copySessionButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Gnome-Edit-Copy-32.png"))); // NOI18N
        copySessionButton.setToolTipText("Copy From Previous Session Fees");
        copySessionButton.setFocusPainted(false);
        copySessionButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                copySessionButtonActionPerformed(evt);
            }
        });
        jPanel4.add(copySessionButton);

        newAmountsButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Gnome-Document-New-32.png"))); // NOI18N
        newAmountsButton.setToolTipText("New Blank Fees");
        newAmountsButton.setFocusPainted(false);
        newAmountsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newAmountsButtonActionPerformed(evt);
            }
        });
        jPanel4.add(newAmountsButton);

        hideButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Gnome-Go-Home-32.png"))); // NOI18N
        hideButton.setToolTipText("Back");
        hideButton.setFocusPainted(false);
        hideButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hideButtonActionPerformed(evt);
            }
        });
        jPanel4.add(hideButton);

        topPanel.add(jPanel4, java.awt.BorderLayout.LINE_END);

        getContentPane().add(topPanel, java.awt.BorderLayout.PAGE_START);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void loadButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loadButtonActionPerformed
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                setComponentEnabled(false);
                getClassFee();
                setComponentEnabled(true);
            }
        });
    }//GEN-LAST:event_loadButtonActionPerformed

    private void saveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveButtonActionPerformed
        if (feeRowsList.isEmpty()) {
            return;
        }
        if (MessageBox.confirm2(this, MessageEnum.MSG_SAVE_QUESTION) != JOptionPane.YES_OPTION) {
            return;
        }
        new Thread() {
            @Override public void run() {
                setComponentEnabled(false);
                progressBar.setValue(0);
                progressBar.setMaximum(feeRowsList.size() - 1);

                try {
                    int rows = defaultTableModel.getRowCount();
                    int columns = defaultTableModel.getColumnCount() - 2;

                    for (int i = 0; i < rows; i++) {
                        List<Fee> list = feeRowsList.get(i);
                        for (int j = 0; j < columns; j++) {
                            Long amount = (Long) defaultTableModel.getValueAt(i, j + 1);
                            Fee dcs = list.get(j);
                            dcs.setAmount(amount);
                            DatabaseManager.save(dcs);
                        }
                        progressBar.setValue(i);
                    }
                    MessageBox.info(ClassFeeInternalFrame.this, "Session saved successfully");
                } catch (HibernateException he) {
                    MessageBox.error(ClassFeeInternalFrame.this, he);
                }
                progressBar.setValue(feeRowsList.size() - 1);
                setComponentEnabled(true);
            }
        }.start();
    }//GEN-LAST:event_saveButtonActionPerformed

    private void copySessionButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_copySessionButtonActionPerformed
        // TODO add your handling code here:
        if (MessageBox.confirm2(this, "Have you selected correct 'Year' and 'Program Type'?") != JOptionPane.YES_OPTION) {
            return;
        }
        copyFromDialog.show();
    }//GEN-LAST:event_copySessionButtonActionPerformed

    private void newAmountsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newAmountsButtonActionPerformed
        if (MessageBox.confirm2(this, "Do you want to create blank session?") != JOptionPane.YES_OPTION) {
            return;
        }
        new Thread() {
            @Override public void run() {
                AdmissionYear ay = (AdmissionYear) admissionYearComboBox.getSelectedItem();
                ProgramTypeEnum pt = (ProgramTypeEnum) programTypeComboBox.getSelectedItem();
                ShiftEnum shift = (ShiftEnum) shiftComboBox.getSelectedItem();
                if (ay == null || pt == null || shift == null) {
                    return;
                }

                List<FeeModel> list = DatabaseManager.getData(FeeModel.class, "programType = " + pt.ordinal() + " AND shift = " + shift.ordinal(), "name");

                setComponentEnabled(false);
                int count = 0;

                for (FeeModel feeModel : list) {
                    progressBar.setValue(0);
                    progressBar.setMaximum(feeCategoriesList.size() - 1);

                    for (int j = 0; j < feeCategoriesList.size(); j++) {
                        FeeCategory feeCategory = feeCategoriesList.get(j);
                        String hql = "feeModel.id = " + feeModel.getId() + " "
                        + "AND feeCategory.id = " + feeCategory.getId() + " "
                        + "AND admissionYearId = " + ay.getAdmissionYearId();
                        Fee fee = DatabaseManager.getSingleRecord(Fee.class, hql);
                        if (fee != null) {
                            continue;
                        }

                        fee = new Fee(ay.getAdmissionYearId(), 0L, "", feeCategory, feeModel);
                        DatabaseManager.save(fee);
                        count++;
                        progressBar.setValue(j);
                    }
                }
                progressBar.setValue(progressBar.getMaximum());
                admission.utils.MessageBox.info(null, count + MessageEnum.MSG_SAVE.toString());
                setComponentEnabled(true);
            }
        }.start();
    }//GEN-LAST:event_newAmountsButtonActionPerformed

    private void hideButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hideButtonActionPerformed
        setVisible(false);
    }//GEN-LAST:event_hideButtonActionPerformed

    private void programTypeComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_programTypeComboBoxActionPerformed
        getFeeCategory();
    }//GEN-LAST:event_programTypeComboBoxActionPerformed

    private void shiftComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_shiftComboBoxActionPerformed
        getFeeCategory();
    }//GEN-LAST:event_shiftComboBoxActionPerformed

    private void feeCategoryComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_feeCategoryComboBoxActionPerformed
        getFeeCategory();
    }//GEN-LAST:event_feeCategoryComboBoxActionPerformed

    private void admissionYearComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_admissionYearComboBoxActionPerformed
        feeTable.removeAll();
    }//GEN-LAST:event_admissionYearComboBoxActionPerformed

    private void okButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_okButtonActionPerformed
        if (MessageBox.confirm2(this, "Do you want copy?") != JOptionPane.YES_OPTION) {
            return;
        }
        new Thread() {
            @Override public void run() {
                copyFromDialog.dispose();

                AdmissionYear fromYear = (AdmissionYear) yearComboBox.getSelectedItem();
                AdmissionYear toYear = (AdmissionYear) admissionYearComboBox.getSelectedItem();
                ProgramTypeEnum pt = (ProgramTypeEnum) programTypeComboBox.getSelectedItem();
                if (toYear == null || fromYear == null || pt == null) {
                    return;
                }

                if (toYear.equals(fromYear)) {
                    admission.utils.MessageBox.error(ClassFeeInternalFrame.this, "You selected same year");
                    return;
                }

                setComponentEnabled(false);
                int count = 0;

                String hql = "SELECT f FROM Fee f "
                    + "INNER JOIN f.feeCategory fc "
                    + "WHERE fc.programType = " + pt.ordinal() + " "
                    + "AND f.admissionYearId = " + fromYear.getAdmissionYearId();
                
                List<Fee> list = DatabaseManager.executeQuery(Fee.class, hql);
                progressBar.setValue(0);
                progressBar.setMaximum(list.size() - 1);

                count += list.size();
                for (int j = 0; j < list.size(); j++) {
                    Fee fee = list.get(j);
                    try {
                        fee = (Fee) fee.clone();
                    } catch (CloneNotSupportedException ex) {
                        Logger.getLogger(ClassFeeInternalFrame.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    fee.setId(null);
                    fee.setAdmissionYearId(toYear.getAdmissionYearId());
                    DatabaseManager.save(fee);
                    progressBar.setValue(j);
                }

                progressBar.setValue(progressBar.getMaximum());
                admission.utils.MessageBox.info(null, count + MessageEnum.MSG_SAVE.toString());
                setComponentEnabled(true);
            }
        }.start();
    }//GEN-LAST:event_okButtonActionPerformed

    private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelButtonActionPerformed
        this.copyFromDialog.dispose();
    }//GEN-LAST:event_cancelButtonActionPerformed

    private void setComponentEnabled(boolean b) {
        loadButton.setEnabled(b);
        saveButton.setEnabled(b);
        newAmountsButton.setEnabled(b);
        copySessionButton.setEnabled(b);
        hideButton.setEnabled(b);
        programTypeComboBox.setEnabled(b);
        admissionYearComboBox.setEnabled(b);
        shiftComboBox.setEnabled(b);
    }
    
    private void getAdmissionYear() {
        this.admissionYearComboBox.removeAllItems();
        List<AdmissionYear> list = DatabaseManager.getData(AdmissionYear.class.getName(), "year DESC");

        for (AdmissionYear ay : list) {
            this.admissionYearComboBox.addItem(ay);
            this.yearComboBox.addItem(ay);
        }
    }
    
    private void getFeeCategory() {
        ProgramTypeEnum pt = (ProgramTypeEnum) this.programTypeComboBox.getSelectedItem();
        ShiftEnum shift = (ShiftEnum) this.shiftComboBox.getSelectedItem();
        FeeCategoryTypeEnum feeCategoryType = (FeeCategoryTypeEnum) this.feeCategoryComboBox.getSelectedItem();
        if (pt == null || shift == null || feeCategoryType == null) {
            return;
        }
        
        feeCategoriesList = DatabaseManager.getData(FeeCategory.class, "programType IN (" + pt.ordinal() + ", " + ProgramTypeEnum.BOTH.ordinal() + ") AND shift = " + shift.ordinal() + " AND categoryType = " + feeCategoryType.ordinal(), "displayOrder");

        List<Object> header = new ArrayList<>();
        final List<Class> types = new ArrayList();
        final List<Boolean> canEdit = new ArrayList();

        header.add("CLASS");
        types.add(java.lang.Object.class);
        canEdit.add(false);

        
        for (FeeCategory fc : feeCategoriesList) {
            header.add(fc);
            types.add(java.lang.Long.class);
            canEdit.add(true);
        }

        header.add("TOTAL");
        types.add(java.lang.Long.class);
        canEdit.add(false);
        feeRowsList.clear();

        defaultTableModel = new javax.swing.table.DefaultTableModel(new Object[][]{}, header.toArray()) {
            @Override
            public Class getColumnClass(int columnIndex) {
                return types.get(columnIndex);
            }

            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit.get(columnIndex);
            }
        };
        feeTable.setModel(defaultTableModel);
        setVerticalHeaderColumnUI();
    }
    
    private void getClassFee() {
        admission.utils.Utility.removeTableRows(this.defaultTableModel);
        feeRowsList.clear();
        DatabaseManager.clear();

        AdmissionYear ay = (AdmissionYear) this.admissionYearComboBox.getSelectedItem();
        ProgramTypeEnum pt = (ProgramTypeEnum) this.programTypeComboBox.getSelectedItem();
        ShiftEnum shift = (ShiftEnum) this.shiftComboBox.getSelectedItem();
        FeeCategoryTypeEnum feeCategoryType = (FeeCategoryTypeEnum) this.feeCategoryComboBox.getSelectedItem();
        if (ay == null || pt == null || shift == null || feeCategoryType == null) {
            return;
        }
        
        List<FeeModel> list = DatabaseManager.getData(FeeModel.class, "programType = " + pt.ordinal() + " AND shift = " + shift.ordinal() + " AND categoryType = " + feeCategoryType.ordinal(), "name");

        for (FeeModel feeModel : list) {
            
            String hql = "SELECT f FROM Fee f "
                    + "INNER JOIN f.feeCategory fc "
                    + "WHERE fc.programType IN (" + pt.ordinal() + ", " + ProgramTypeEnum.BOTH.ordinal() + ") "
                    + "AND fc.shift = " + shift.ordinal() + " "
                    + "AND f.admissionYearId = " + ay.getAdmissionYearId() + " "
                    + "AND f.feeModel.id = " + feeModel.getId() + " "
                    + "ORDER BY fc.displayOrder";
            
            List<Fee> list2 = DatabaseManager.executeQuery(Fee.class, hql);
            if (list2.isEmpty()) {
                continue;
            }

            feeRowsList.add(list2);

            List<Object> row = new ArrayList<>();
            row.add(feeModel);
            Double count = 0.0;
            for (Fee fee : list2) {
                row.add(fee.getAmount());
                count += fee.getAmount();
            }
            row.add(count.intValue());
            defaultTableModel.addRow(row.toArray());
        }
        if (feeRowsList.isEmpty()) {
            admission.utils.MessageBox.info(this, "Fees are not assigned");
        } else {
            setColumnDataWidth(0);
        }
    }

    private void setColumnDataWidth(int column) {
        int preferredWidth = 0;
        for (int row = 0; row < feeTable.getRowCount(); row++) {
            preferredWidth = Math.max(preferredWidth, getCellDataWidth(row, column));
        }
        feeTable.getColumnModel().getColumn(column).setPreferredWidth(preferredWidth + 10);
    }

    private int getCellDataWidth(int row, int column) {
        TableCellRenderer cellRenderer = feeTable.getCellRenderer(row, column);
        Component c = feeTable.prepareRenderer(cellRenderer, row, column);
        return c.getPreferredSize().width + feeTable.getIntercellSpacing().width;
    }
    
    private void setVerticalHeaderColumnUI() {
        TableCellRenderer headerRenderer = new VerticalTableHeaderCellRenderer();
        Enumeration<TableColumn> columns = feeTable.getColumnModel().getColumns();
        boolean b = false;
        while (columns.hasMoreElements()) {
            TableColumn columm = columns.nextElement();
            if (b) {
                columm.setPreferredWidth(50);
                columm.setMaxWidth(50);
                columm.setHeaderRenderer(headerRenderer);
            } else {
                b = true;
                columm.setPreferredWidth(300);
                //columm.setMaxWidth(300);
            }
            columm.setResizable(false);
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox admissionYearComboBox;
    private javax.swing.JButton cancelButton;
    private javax.swing.JDialog copyFromDialog;
    private javax.swing.JButton copySessionButton;
    private javax.swing.JComboBox feeCategoryComboBox;
    private javax.swing.JTable feeTable;
    private javax.swing.JButton hideButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JButton loadButton;
    private javax.swing.JButton newAmountsButton;
    private javax.swing.JButton okButton;
    private javax.swing.JComboBox programTypeComboBox;
    private javax.swing.JProgressBar progressBar;
    private javax.swing.JButton saveButton;
    private javax.swing.JComboBox shiftComboBox;
    private javax.swing.JPanel titlePanel;
    private javax.swing.JPanel titlePanel1;
    private javax.swing.JPanel topPanel;
    private javax.swing.JComboBox yearComboBox;
    // End of variables declaration//GEN-END:variables
    private List<FeeCategory> feeCategoriesList;
    private DefaultTableModel defaultTableModel;
    private List<List<Fee>> feeRowsList;
}
