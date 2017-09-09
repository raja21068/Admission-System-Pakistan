package admission.view.campus;

import admission.controller.DatabaseManager;
import admission.controller.JDBCDatabaseManager;
import admission.enums.CategoryEnum;
import admission.model.AdmissionYear;
import admission.model.Campus;
import admission.model.CposGroup;
import admission.model.Department;
import admission.model.security.Resources;
import admission.model.ProgramType;
import admission.model.Shift;
import admission.reports.JRViewer300;
import admission.reports.ProgramInformationReport;
import admission.reports.beans.AdmittedCandidate;
import admission.reports.beans.ProgramOfStudyDataBean;
import admission.utils.MessageBox;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.print.PrinterException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractAction;
import javax.swing.JComponent;
import javax.swing.KeyStroke;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.text.DocumentFilter;
import admission.utils.RollNoFormatter;
import admission.utils.RoundedBorder;
import admission.view.MainFrame;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JFileChooser;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

/**
 *
 * @author Raja Kumar & Jay Kumar
 */
public class CampusProgramOfStudyGroupInformationInternalFrame extends javax.swing.JInternalFrame {

    public CampusProgramOfStudyGroupInformationInternalFrame() {
        initComponents();

        this.titlePanel.setBorder(RoundedBorder.createGradientBorder());

        admission.utils.Utility.hideOnEscape(this);
        admission.utils.Utility.comboBoxScroll(cposgComboBox);
        fileChooser = new JFileChooser("../");
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

        this.detailTable.getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), "Enter");
        this.detailTable.getActionMap().put("Enter", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                TableCellEditor cellEditor = detailTable.getCellEditor();
                if (cellEditor != null) {
                    detailTable.getCellEditor().stopCellEditing();
                }

                int row = detailTable.getSelectedRow();
                int col = detailTable.getSelectedColumn() + 1;
                if (col > detailTable.getColumnCount() - 1) {
                    col = 0;
                    row += 1;
                    if (row > detailTable.getRowCount() - 1) {
                        row = 0;
                    }
                }
                detailTable.changeSelection(row, col, false, false);
            }
        });
        this.detailTableModel = (DefaultTableModel) this.detailTable.getModel();

        documentFilter = new admission.utils.UppercaseDocumentFilter();
        numberDocumentFilter = new admission.utils.NumberDocumentFilter();
        admittedReport = new ProgramInformationReport();
//        ((AbstractDocument) this.seatNoTextField.getDocument()).setDocumentFilter(numberDocumentFilter);
    }

    @Override
    public void setVisible(boolean aFlag) {
        if (aFlag) {
            this.getAdmissionYear();
            this.getProgramType();
            this.getCampus();
            this.getShift();
            clear();
        }
        super.setVisible(aFlag);
    }

    public void setPrivileges(Resources privileges) {
        this.privileges = privileges;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        backButton = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        admissionYearComboBox = new javax.swing.JComboBox();
        programTypeComboBox = new javax.swing.JComboBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        detailTable = new javax.swing.JTable();
        campusComboBox = new javax.swing.JComboBox();
        jLabel14 = new javax.swing.JLabel();
        shiftComboBox = new javax.swing.JComboBox();
        jLabel16 = new javax.swing.JLabel();
        cposgComboBox = new javax.swing.JComboBox();
        jLabel9 = new javax.swing.JLabel();
        durationTextField = new javax.swing.JTextField();
        semesterTextField = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        loadButton = new javax.swing.JButton();
        countLabel = new javax.swing.JLabel();
        printButton = new javax.swing.JButton();
        allCheckBox = new javax.swing.JCheckBox();
        additionalCheckBox = new javax.swing.JCheckBox();
        titlePanel = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        buttonExportCSV = new javax.swing.JButton();
        exportCSVWebButton = new javax.swing.JButton();
        printOrderByRollNoButton = new javax.swing.JButton();
        printOrderByNameButton = new javax.swing.JButton();

        setClosable(true);
        setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);
        setIconifiable(true);
        setTitle("Program Information");

        backButton.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        backButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Gnome-Go-Home-32.png"))); // NOI18N
        backButton.setToolTipText("Hide");
        backButton.setFocusPainted(false);
        backButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backButtonActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel5.setText("Admission Year:");

        jLabel6.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel6.setText("Program Type:");

        admissionYearComboBox.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        programTypeComboBox.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        programTypeComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                programTypeComboBoxActionPerformed(evt);
            }
        });

        jScrollPane1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        detailTable.setAutoCreateRowSorter(true);
        detailTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "SNo.", "Seat No.", "Name", "Father's Name", "Surname", "Roll No.", "District", "Area", "%"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Float.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        detailTable.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        detailTable.setRowHeight(20);
        detailTable.setRowSelectionAllowed(false);
        detailTable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        detailTable.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(detailTable);
        if (detailTable.getColumnModel().getColumnCount() > 0) {
            detailTable.getColumnModel().getColumn(0).setResizable(false);
            detailTable.getColumnModel().getColumn(0).setPreferredWidth(40);
            detailTable.getColumnModel().getColumn(1).setPreferredWidth(60);
            detailTable.getColumnModel().getColumn(2).setPreferredWidth(200);
            detailTable.getColumnModel().getColumn(3).setPreferredWidth(200);
            detailTable.getColumnModel().getColumn(4).setPreferredWidth(150);
            detailTable.getColumnModel().getColumn(5).setPreferredWidth(100);
            detailTable.getColumnModel().getColumn(6).setPreferredWidth(200);
            detailTable.getColumnModel().getColumn(7).setResizable(false);
            detailTable.getColumnModel().getColumn(7).setPreferredWidth(45);
            detailTable.getColumnModel().getColumn(8).setPreferredWidth(40);
        }

        campusComboBox.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        campusComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                campusComboBoxActionPerformed(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel14.setText("Shift:");

        shiftComboBox.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        shiftComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                shiftComboBoxActionPerformed(evt);
            }
        });

        jLabel16.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel16.setText("Program of Study:");

        cposgComboBox.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        cposgComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cposgComboBoxActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel9.setText("Duration:");

        durationTextField.setEditable(false);
        durationTextField.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        semesterTextField.setEditable(false);
        semesterTextField.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel12.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel12.setText("Semester:");

        jLabel7.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel7.setText("Campus:");

        loadButton.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        loadButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Gnome-Emblem-Downloads-32.png"))); // NOI18N
        loadButton.setToolTipText("Load");
        loadButton.setFocusPainted(false);
        loadButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loadButtonActionPerformed(evt);
            }
        });

        countLabel.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        countLabel.setText("0 record fetched");

        printButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Gnome-Document-Print-32.png"))); // NOI18N
        printButton.setToolTipText("Print");
        printButton.setFocusPainted(false);
        printButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                printButtonActionPerformed(evt);
            }
        });

        allCheckBox.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        allCheckBox.setText("All Programs");

        additionalCheckBox.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        additionalCheckBox.setText("Additional Roll no.");

        titlePanel.setLayout(new java.awt.BorderLayout());

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/form-icon2.png"))); // NOI18N
        jLabel2.setText("Program Information");
        jLabel2.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, java.awt.Color.darkGray), javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5)));
        titlePanel.add(jLabel2, java.awt.BorderLayout.CENTER);

        buttonExportCSV.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Gnome-X-Office-Document-32.png"))); // NOI18N
        buttonExportCSV.setToolTipText("Export In CSV");
        buttonExportCSV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonExportCSVActionPerformed(evt);
            }
        });

        exportCSVWebButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Gnome-X-Office-Drawing-32.png"))); // NOI18N
        exportCSVWebButton.setToolTipText("Export CSV For Web");
        exportCSVWebButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exportCSVWebButtonActionPerformed(evt);
            }
        });

        printOrderByRollNoButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Gnome-X-Office-Drawing-32.png"))); // NOI18N
        printOrderByRollNoButton.setToolTipText("Export CSV For Web");
        printOrderByRollNoButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                printOrderByRollNoButtonActionPerformed(evt);
            }
        });

        printOrderByNameButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Gnome-X-Office-Drawing-32.png"))); // NOI18N
        printOrderByNameButton.setToolTipText("Export CSV For Web");
        printOrderByNameButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                printOrderByNameButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(printOrderByRollNoButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(printOrderByNameButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(exportCSVWebButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonExportCSV)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(printButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(backButton))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(4, 4, 4)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(admissionYearComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(campusComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(4, 4, 4)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel16))
                        .addGap(4, 4, 4)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(programTypeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(10, 10, 10)
                                .addComponent(jLabel14)
                                .addGap(10, 10, 10)
                                .addComponent(shiftComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(cposgComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 347, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(loadButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(5, 5, 5)
                                .addComponent(durationTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(4, 4, 4)
                                .addComponent(jLabel12)
                                .addGap(44, 44, 44)
                                .addComponent(semesterTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(75, 75, 75)
                                .addComponent(allCheckBox)
                                .addGap(2, 2, 2)
                                .addComponent(additionalCheckBox))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 770, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(countLabel))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(titlePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(titlePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(backButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(printButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(buttonExportCSV, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(exportCSVWebButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(printOrderByRollNoButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(printOrderByNameButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(11, 11, 11)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(admissionYearComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(campusComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(programTypeComboBox)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(3, 3, 3)
                                .addComponent(jLabel14))
                            .addComponent(shiftComboBox))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cposgComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addGap(12, 12, 12)
                                .addComponent(jLabel7))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addGap(12, 12, 12)
                                .addComponent(jLabel16))))
                    .addComponent(loadButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(jLabel9))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(durationTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel12)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(semesterTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(allCheckBox)
                    .addComponent(additionalCheckBox))
                .addGap(6, 6, 6)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 424, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addComponent(countLabel)
                .addContainerGap(19, Short.MAX_VALUE))
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {admissionYearComboBox, campusComboBox, cposgComboBox, durationTextField, programTypeComboBox, semesterTextField, shiftComboBox});

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void backButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backButtonActionPerformed
        // TODO add your handling code here:
        this.setVisible(false);
    }//GEN-LAST:event_backButtonActionPerformed

    private void campusComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_campusComboBoxActionPerformed
        // TODO add your handling code here:
        getCampusProgramOfStudyGroup();
    }//GEN-LAST:event_campusComboBoxActionPerformed

    private void shiftComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_shiftComboBoxActionPerformed
        // TODO add your handling code here:
        getCampusProgramOfStudyGroup();
    }//GEN-LAST:event_shiftComboBoxActionPerformed

    private void cposgComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cposgComboBoxActionPerformed
        // TODO add your handling code here:
        durationTextField.setText(null);
        semesterTextField.setText(null);
        admission.utils.Utility.removeTableRows(detailTableModel);

        CposGroup cposg = (CposGroup) cposgComboBox.getSelectedItem();
        if (cposg == null) {
            return;
        }

        this.durationTextField.setText(cposg.getCampusProgramOfStudy().getProgramOfStudy().getDuration() + " Year");
        this.semesterTextField.setText(cposg.getCampusProgramOfStudy().getProgramOfStudy().getSemester() + " Semester");
    }//GEN-LAST:event_cposgComboBoxActionPerformed

    private void programTypeComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_programTypeComboBoxActionPerformed
        // TODO add your handling code here:
        getCampusProgramOfStudyGroup();
    }//GEN-LAST:event_programTypeComboBoxActionPerformed

    private void loadButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loadButtonActionPerformed

        admission.utils.Utility.removeTableRows(detailTableModel);

        AdmissionYear ay = (AdmissionYear) admissionYearComboBox.getSelectedItem();
        CposGroup cposg = (CposGroup) cposgComboBox.getSelectedItem();
        if (ay == null || cposg == null) {
            return;
        }
        try {
            List<Object[]> list = JDBCDatabaseManager.getCandidatesOfCposGroup(ay.getAdmissionYearId(), cposg.getCposGroupId(), true);
            countLabel.setText(list.size() + " record fetched");
            for (Object[] objects : list) {
                if (objects[5] != null) {
                    objects[5] = RollNoFormatter.format(ay.getYear(), cposg.getCode(), (int) objects[5]);
                } else {
                    objects[5] = "";
                }

                detailTableModel.addRow(objects);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CampusProgramOfStudyGroupInformationInternalFrame.class.getName()).log(Level.SEVERE, null, ex);
            admission.utils.MessageBox.error(this, ex);
        }
    }//GEN-LAST:event_loadButtonActionPerformed

    private void printButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_printButtonActionPerformed
        // TODO add your handling code here:
        AdmissionYear ay = (AdmissionYear) admissionYearComboBox.getSelectedItem();
        if (ay == null) {
            return;
        }

        try {
            List<ProgramOfStudyDataBean> beanList = getProgramOfStudyBeans(ay);
            if (beanList == null) {
                return;
            }
            admittedReport.print(ay.getYear(), beanList);
        } catch (PrinterException | SQLException ex) {
            Logger.getLogger(CampusProgramOfStudyGroupInformationInternalFrame.class.getName()).log(Level.SEVERE, null, ex);
            admission.utils.MessageBox.error(this, ex);
        }
    }//GEN-LAST:event_printButtonActionPerformed

    private void buttonExportCSVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonExportCSVActionPerformed
        AdmissionYear ay = (AdmissionYear) admissionYearComboBox.getSelectedItem();
        if (ay == null) {
            return;
        }
        try {
            List<ProgramOfStudyDataBean> beanList = getProgramOfStudyBeans(ay);
            if (beanList == null) {
                return;
            }
            int showSaveDialog = fileChooser.showSaveDialog(this);
            for (ProgramOfStudyDataBean programOfStudyDataBean : beanList) {
                String path = fileChooser.getSelectedFile().getAbsolutePath() + File.separator + ay.getYear() + "-" + programOfStudyDataBean.getCposg().getProgramName() + "(" + programOfStudyDataBean.getCposg().getPosName() + ") FIRST YEAR";
                File f = new File(path + ".CSV");
                if (f.exists()) {
                    for (int i = 1; i < 100; i++) {
                        f = new File(path + "(" + i + ")" + ".CSV");
                        if (!f.exists()) {
                            break;
                        }
                    }
                }
                f.createNewFile();
                PrintStream out = new PrintStream(new FileOutputStream(f));
                out.println("S.NO,ROLL NO,NAME,FATHER'S NAME,SURNAME");
                List<Object[]> candidatesData = programOfStudyDataBean.getCandidatesData();
                for (Object[] objects : candidatesData) {
                    StringBuilder builder = new StringBuilder();
                    builder.append(objects[0]).append(",");
                    if (objects[5] != null) {
                        objects[5] = RollNoFormatter.format(ay.getYear(), programOfStudyDataBean.getCposg().getCode(), (int) objects[5]);
                    } else {
                        objects[5] = "";
                    }
                    builder.append(objects[5]).append(",");
                    //builder.append(objects[1]).append(",");
                    builder.append(objects[2]).append(",");
                    builder.append(objects[3]).append(",");
                    builder.append(objects[4]).append(",");

                    out.println(builder.toString());
                    out.flush();
                }
                out.close();
            }
            MessageBox.info(this, "Succesfuuly exported..");
        } catch (PrinterException | SQLException | IOException ex) {
            Logger.getLogger(CampusProgramOfStudyGroupInformationInternalFrame.class.getName()).log(Level.SEVERE, null, ex);
            admission.utils.MessageBox.error(this, ex);
        }
    }//GEN-LAST:event_buttonExportCSVActionPerformed

    private void exportCSVWebButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exportCSVWebButtonActionPerformed
        AdmissionYear ay = (AdmissionYear) admissionYearComboBox.getSelectedItem();
        if (ay == null) {
            return;
        }
        try {
            List<ProgramOfStudyDataBean> beanList = getProgramOfStudyBeans(ay);
            if (beanList == null) {
                return;
            }
            int showSaveDialog = fileChooser.showSaveDialog(this);
            String path = fileChooser.getSelectedFile().getAbsolutePath() + File.separator + ay.getYear() + "- ROLL_NO WEB FIRST YEAR";
            File f = new File(path + ".CSV");
            if (f.exists()) {
                for (int i = 1; i < 100; i++) {
                    f = new File(path + "(" + i + ")" + ".CSV");
                    if (!f.exists()) {
                        break;
                    }
                }
            }
            f.createNewFile();
            PrintStream out = new PrintStream(new FileOutputStream(f));
            out.println("ROLL NO,SEAT_NO,NAME,FATHER'S NAME,SURNAME,MOBILE,DISCIPLINE,DEPARTMENT");

            for (ProgramOfStudyDataBean programOfStudyDataBean : beanList) {
                List<Object[]> candidatesData = programOfStudyDataBean.getCandidatesData();
                for (Object[] objects : candidatesData) {
                    StringBuilder builder = new StringBuilder();
//                    builder.append(objects[0]).append(",");
                    if (objects[5] != null) {
                        objects[5] = RollNoFormatter.format(ay.getYear(), programOfStudyDataBean.getCposg().getCode(), (int) objects[5]);
                    } else {
                        objects[5] = "";
                    }

                    builder.append("'").append(objects[5]).append("'").append(",");
                    builder.append(objects[1]).append(",");
                    builder.append("'").append(objects[2]).append("'").append(",");
                    builder.append("'").append(objects[3]).append("'").append(",");
                    builder.append("'").append(objects[4]).append("'").append(",");
                    builder.append("'").append(objects[9]).append("'").append(",");
                    builder.append("'").append(programOfStudyDataBean.getCposg().getPosName()).append("'").append(",");
                    builder.append("'").append(programOfStudyDataBean.getCposg().getDepartmentName()).append("'");

                    out.println(builder.toString());
                    out.flush();
                }

            }
            out.close();
            MessageBox.info(this, "Succesfuuly exported..");
        } catch (PrinterException | SQLException | IOException ex) {
            Logger.getLogger(CampusProgramOfStudyGroupInformationInternalFrame.class.getName()).log(Level.SEVERE, null, ex);
            admission.utils.MessageBox.error(this, ex);
        }
    }//GEN-LAST:event_exportCSVWebButtonActionPerformed

    private void printOrderByRollNoButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_printOrderByRollNoButtonActionPerformed
    
        printAdmittedStudents(true);
    }//GEN-LAST:event_printOrderByRollNoButtonActionPerformed

    
    private void printOrderByNameButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_printOrderByNameButtonActionPerformed
        printAdmittedStudents(false);
    }//GEN-LAST:event_printOrderByNameButtonActionPerformed

    private void printAdmittedStudents(boolean sortByRollNo){
        try {
            AdmissionYear ay = (AdmissionYear) admissionYearComboBox.getSelectedItem();
            CposGroup cposg = (CposGroup) cposgComboBox.getSelectedItem();

            if (ay == null || cposg == null) {
                return;
            }

            CposGroup cposGroup = JDBCDatabaseManager.getCampusCposGroup(cposg.getCposGroupId());
            cposGroup.setCampusProgramOfStudy(cposg.getCampusProgramOfStudy());

            Map<String, Object> parameterMap = new HashMap<>();
            parameterMap.put("ADMISSION_YEAR", ay.toString());

            parameterMap.put("DEPARTMENT", cposg.getCampusProgramOfStudy().getProgramOfStudy().getDiscipline().getDepartment().getName());
            String posName = getPosName(cposGroup);
//          String posName = cposg.getPosName();
//          if (!cposg.getCampusProgramOfStudy().getShift().getIsMorning()) {
//            posName += " Evening";
//          }
            parameterMap.put("PROGRAM", posName);

            List<Object[]> list = JDBCDatabaseManager.getCandidatesOfCposGroupWithCategory(ay.getAdmissionYearId(), cposg.getCposGroupId(), true, sortByRollNo);
            List<AdmittedCandidate> admitList = new ArrayList();
            for (Object[] objects : list) {

                if (objects[5] != null) {
                    objects[5] = RollNoFormatter.format(ay.getYear(), cposg.getCode(), (int) objects[5]);
                } else {
                    objects[5] = "";
                }

                AdmittedCandidate admitCandidate = new AdmittedCandidate();
                admitCandidate.setSeatNo((int) objects[1]);
                admitCandidate.setName((String) objects[2]);
                admitCandidate.setFathersName((String) objects[3]);
                admitCandidate.setSurname((String) objects[4]);
                admitCandidate.setRollNo((String) objects[5]);
                String category = "";
                int cat = (Integer) objects[10];
                if (cat == 10 || cat == 11 || cat == 19) {
                    category = "SELF";
                } else {
                    category = "MERIT";
                }
                admitCandidate.setCategory(category);
                admitList.add(admitCandidate);
            }
            JasperDesign jd = JRXmlLoader.load(getClass().getResourceAsStream("/reports/" + "admitted_report.jrxml"));
            JasperReport report = JasperCompileManager.compileReport(jd);
            JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(admitList);

            JasperPrint jasperprint = JasperFillManager.fillReport(report, parameterMap, beanColDataSource);
            JRViewer300 view = new JRViewer300(jasperprint);
            MainFrame.reportViewerInternalFrame.addTab(view, "Campus Program of Study Group Information");
        } catch (SQLException | JRException ex) {
            Logger.getLogger(CampusProgramOfStudyGroupInformationInternalFrame.class.getName()).log(Level.SEVERE, null, ex);
            admission.utils.MessageBox.error(this, ex);
        }
    }
    
    private String getPosName(CposGroup cposg) {
        String posName;//!cposg.isIsBachelor() ? (cposg.getProgramName().equals("M.LIS") || cposg.getProgramName().equals("PGD") ? "" : + (Previous)) + " " + cposg.getPosName() : cposg.toString();
        if (!cposg.isIsBachelor()) {
            posName = (cposg.getProgramName().equals("M.B.A")
                    ? (cposg.isIsMorning() ? cposg.getProgramName() + " (PREVIOUS)" : cposg.getProgramName() + " First Year")
                    : cposg.getProgramName().equals("M.C.S")
                    || cposg.getProgramName().equals("LL.M")
                    || //                    cposg.getProgramName().equals("LL.B") || 
                    cposg.getProgramName().equals("M.COM")
                    || //                    cposg.getProgramName().equals("B.COM") || 
                    cposg.getProgramName().equals("M.P.A")
                    ? cposg.getProgramName() + " (PREVIOUS)"
                    : cposg.getProgramName().equals("PGD")
                    || cposg.getProgramName().startsWith("1 Year Con")
                    ? cposg.getProgramName() + " " + cposg.getPosName()
                    : cposg.getProgramName().equals("BPEHSS")
                    || cposg.getProgramName().equals("M.LIS")
                    || cposg.getProgramName().equals("MPEHSS")
                    ? cposg.getPosName() + " (" + cposg.getProgramName() + ")"
                    : cposg.getProgramName().equals("M.Sc (HONS)") ? cposg.getProgramName() + " " + cposg.getPosName()
                    : cposg.getProgramName() + " (PREVIOUS) " + cposg.getPosName());

            posName += (cposg.isIsMorning() ? "" : " EVENING");
        } else {
//                posName = cposg.toString();
            posName = cposg.getProgramName().equals("B.B.A") ? cposg.getProgramName() + " (HONS) FIRST YEAR"
                    : (cposg.getProgramName().equals("B.P.A")
                    || cposg.getProgramName().equals("LL.M")) ? cposg.getProgramName() + " FIRST YEAR"
                    : cposg.getProgramName().equals("PHARM-D") ? cposg.getProgramName() + " FIRST PROF."
                    : cposg.getProgramName() + " (" + cposg.getPosName() + ") FIRST YEAR";
            posName += (cposg.isIsMorning() ? "" : " (EVENING)");
        }
        return posName;
    }

    
    private List<ProgramOfStudyDataBean> getProgramOfStudyBeans(AdmissionYear ay) throws PrinterException, SQLException {
        List<ProgramOfStudyDataBean> beanList;
        if (!allCheckBox.isSelected()) {
            CposGroup cposg = (CposGroup) cposgComboBox.getSelectedItem();
            if (cposg == null) {
                return null;
            }

            beanList = new ArrayList<>(1);

            List<Object[]> list;
            if (additionalCheckBox.isSelected()) {
                list = JDBCDatabaseManager.getCandidatesOfCposGroup(ay.getAdmissionYearId(), cposg.getCposGroupId(), true, 1);
            } else {
                list = JDBCDatabaseManager.getCandidatesOfCposGroup(ay.getAdmissionYearId(), cposg.getCposGroupId(), true);
            }
            if (list.isEmpty()) {
                return null;
            }
            CposGroup cposGroup = JDBCDatabaseManager.getCampusCposGroup(cposg.getCposGroupId());
            cposGroup.setCampusProgramOfStudy(cposg.getCampusProgramOfStudy());

            ProgramOfStudyDataBean bean = new ProgramOfStudyDataBean(cposGroup, list, null);
            beanList.add(bean);
        } else {
            int items = cposgComboBox.getItemCount();
            beanList = new ArrayList<>(items);
            for (int i = 0; i < items; i++) {
                CposGroup cposg = (CposGroup) cposgComboBox.getItemAt(i);
                List<Object[]> list;
                if (additionalCheckBox.isSelected()) {
                    list = JDBCDatabaseManager.getCandidatesOfCposGroup(ay.getAdmissionYearId(), cposg.getCposGroupId(), true, 1);
                } else {
                    list = JDBCDatabaseManager.getCandidatesOfCposGroup(ay.getAdmissionYearId(), cposg.getCposGroupId(), true);
                }

                if (list.isEmpty()) {
                    continue;
                }
                CposGroup cposGroup = JDBCDatabaseManager.getCampusCposGroup(cposg.getCposGroupId());
                cposGroup.setCampusProgramOfStudy(cposg.getCampusProgramOfStudy());

                ProgramOfStudyDataBean bean = new ProgramOfStudyDataBean(cposGroup, list, null);

                beanList.add(bean);
            }
        }
        return beanList;
    }

    private void getAdmissionYear() {
        this.admissionYearComboBox.removeAllItems();

        List<AdmissionYear> list = DatabaseManager.getData(AdmissionYear.class.getName(), "year DESC");
        for (int i = 0; i < list.size(); i++) {
            this.admissionYearComboBox.addItem(list.get(i));
        }
    }

    private void getProgramType() {
        this.programTypeComboBox.removeAllItems();

        List<ProgramType> list = DatabaseManager.getData(ProgramType.class.getName(), "name");
        for (int i = 0; i < list.size(); i++) {
            this.programTypeComboBox.addItem(list.get(i));
        }
    }

    private void getCampus() {
        this.campusComboBox.removeAllItems();

        List<Campus> list = DatabaseManager.getData(Campus.class.getName(), "name");
        for (int i = 0; i < list.size(); i++) {
            this.campusComboBox.addItem(list.get(i));
        }
    }

    private void getShift() {
        this.shiftComboBox.removeAllItems();

        List<Shift> list = DatabaseManager.getData(Shift.class.getName(), "isMorning DESC");
        for (int i = 0; i < list.size(); i++) {
            this.shiftComboBox.addItem(list.get(i));
        }
    }

    private void getCampusProgramOfStudyGroup() {
        cposgComboBox.removeAllItems();

        Campus campus = (Campus) this.campusComboBox.getSelectedItem();
        Shift shift = (Shift) this.shiftComboBox.getSelectedItem();
        ProgramType pt = (ProgramType) this.programTypeComboBox.getSelectedItem();
        if (campus == null || shift == null || pt == null) {
            return;
        }

        List<CposGroup> list = DatabaseManager.getCampusCposGroup(campus.getCampusId(), shift.getShiftId(), pt.getProgramTypeId());

        for (int i = 0; i < list.size(); i++) {
            cposgComboBox.addItem(list.get(i));
        }
    }

    private void clear() {
        admission.utils.Utility.removeTableRows(detailTableModel);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox additionalCheckBox;
    private javax.swing.JComboBox admissionYearComboBox;
    private javax.swing.JCheckBox allCheckBox;
    private javax.swing.JButton backButton;
    private javax.swing.JButton buttonExportCSV;
    private javax.swing.JComboBox campusComboBox;
    private javax.swing.JLabel countLabel;
    private javax.swing.JComboBox cposgComboBox;
    private javax.swing.JTable detailTable;
    private javax.swing.JTextField durationTextField;
    private javax.swing.JButton exportCSVWebButton;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton loadButton;
    private javax.swing.JButton printButton;
    private javax.swing.JButton printOrderByNameButton;
    private javax.swing.JButton printOrderByRollNoButton;
    private javax.swing.JComboBox programTypeComboBox;
    private javax.swing.JTextField semesterTextField;
    private javax.swing.JComboBox shiftComboBox;
    private javax.swing.JPanel titlePanel;
    // End of variables declaration//GEN-END:variables
    private DocumentFilter documentFilter;
    private DocumentFilter numberDocumentFilter;
    private Resources privileges;
    private DefaultTableModel detailTableModel;
    private ProgramInformationReport admittedReport;
    private JFileChooser fileChooser;
}
