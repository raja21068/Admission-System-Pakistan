package admission.view.seats;

import admission.controller.DatabaseManager;
import admission.model.AdmissionSession;
import admission.model.AdmissionYear;
import admission.model.Campus;
import admission.model.CampusCategory;
import admission.model.CposGroup;
import admission.model.DisciplineCategorySeats;
import admission.model.Faculty;
import admission.model.security.Resources;
import admission.model.ProgramType;
import admission.model.Shift;
import com.yog.component.*;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Frame;
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
import javax.swing.JDialog;
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
import javax.swing.text.DocumentFilter;
import org.hibernate.HibernateException;
import admission.utils.FormScroller;
import admission.utils.MessageBox;
import admission.utils.RoundedBorder;

/**
 *
 * @author Raja Kumar & Jay Kumar
 */
public class DisciplineSeatDistributionInternalFrame extends javax.swing.JInternalFrame {

    public DisciplineSeatDistributionInternalFrame() {
        initComponents();

        final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        screenSize.setSize(1020, screenSize.height - 100);
        setSize(screenSize);

        this.titlePanel.setBorder(RoundedBorder.createGradientBorder());
        this.titlePanel1.setBorder(RoundedBorder.createGradientBorder());
        FormScroller scroller = new FormScroller(jScrollPane2);
        scroller.setScrollInsets(new Insets(50, 0, 50, 0));

//        final Dimension size = new Dimension(750, 750);
//        this.jPanel1.setPreferredSize(size);
        panelDialog = new JDialog((Frame) javax.swing.SwingUtilities.windowForComponent(this), "Copy From Year", true);
        panelDialog.add(this.jPanel3);
        panelDialog.setSize(410, 170);
        panelDialog.setLocationRelativeTo(null);
        panelDialog.setResizable(false);

//        this.jPanel1.setSize(this.jPanel1.getWidth(), Toolkit.getDefaultToolkit().getScreenSize().height);
        admission.utils.Utility.hideOnEscape(this);

        this.jScrollPane2.getVerticalScrollBar().setUnitIncrement(20);

        this.seatDistributionTable.getDefaultEditor(Integer.class).addCellEditorListener(new CellEditorListener() {
            @Override
            public void editingCanceled(ChangeEvent e) {
            }

            @Override
            public void editingStopped(ChangeEvent e) {
                int row = seatDistributionTable.getSelectedRow();
                int col = seatDistributionTable.getSelectedColumn();

                if (col > 0 && col <= campusCategoriesList.size()) {
                    Integer noOfSeats = (Integer) defaultTableModel.getValueAt(row, col);
                    if (noOfSeats == null) {
                        noOfSeats = 0;
                    }
//
                    int totalColumns = defaultTableModel.getColumnCount();
                    Integer count = 0;
                    for (int i = 1; i < totalColumns - 1; i++) {
                        Integer seats = (Integer) defaultTableModel.getValueAt(row, i);
                        count += seats;
                    }
                    defaultTableModel.setValueAt(count, row, totalColumns - 1);

                    int totalCount = 0;
                    for (int i = 0; i < defaultTableModel.getRowCount(); i++) {
                        for (int j = 1; j < defaultTableModel.getColumnCount() - 1; j++) {
                            totalCount += (Integer) defaultTableModel.getValueAt(i, j);
                        }
                    }
                    totalSeatsTextField.setText(String.valueOf(totalCount));
                } else {
                    admission.utils.MessageBox.info(null, "Out of index");
                }
            }
        });
        this.seatDistributionTable.getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), "Enter");
        this.seatDistributionTable.getActionMap().put("Enter", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                TableCellEditor cellEditor = seatDistributionTable.getCellEditor();
                if (cellEditor != null) {
                    seatDistributionTable.getCellEditor().stopCellEditing();
                }

                int row = seatDistributionTable.getSelectedRow();
                int col = seatDistributionTable.getSelectedColumn() + 1;
                if (col > seatDistributionTable.getColumnCount() - 1) {
                    col = 0;
                    row += 1;
                    if (row > seatDistributionTable.getRowCount() - 1) {
                        row = 0;
                    }
                }
                seatDistributionTable.changeSelection(row, col, false, false);
            }
        });
        ((DefaultTableCellRenderer) seatDistributionTable.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);
        campusCategoriesList = new ArrayList<>();
        disciplineRowsList = new ArrayList<>();
    }

    @Override
    public void setVisible(boolean aFlag) {
        if (aFlag) {
            this.getProgramType();
            this.getShift();
            this.getCampus();
            this.getAdmissionYear();
        }
        super.setVisible(aFlag);
    }

    public void setPrivileges(Resources privileges) {
        this.privileges = privileges;
//        this.deleteButton.setEnabled((privileges.isDeletePrivilige()));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        yearComboBox = new javax.swing.JComboBox();
        okButton = new javax.swing.JButton();
        cancelButton = new javax.swing.JButton();
        titlePanel1 = new javax.swing.JPanel();
        jLabel38 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jScrollPane2 = new javax.swing.JScrollPane();
        jPanel2 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        facultyComboBox = new javax.swing.JComboBox();
        programTypeComboBox = new javax.swing.JComboBox();
        jLabel19 = new javax.swing.JLabel();
        seatDistributionScrollPane = new javax.swing.JScrollPane();
        seatDistributionTable = new javax.swing.JTable(){
            /*protected JTableHeader createDefaultTableHeader() {
                return new GroupableTableHeader(columnModel);
            }*/
        };
        admissionYearComboBox = new javax.swing.JComboBox();
        jLabel3 = new javax.swing.JLabel();
        progressBar = new javax.swing.JProgressBar();
        jLabel1 = new javax.swing.JLabel();
        shiftComboBox = new javax.swing.JComboBox();
        jLabel15 = new javax.swing.JLabel();
        campusComboBox = new javax.swing.JComboBox();
        totalSeatsTextField = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        topPanel = new javax.swing.JPanel();
        titlePanel = new javax.swing.JPanel();
        jLabel37 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        loadButton = new javax.swing.JButton();
        saveButton = new javax.swing.JButton();
        copySeatsButton = new javax.swing.JButton();
        newSeatsButton = new javax.swing.JButton();
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

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(titlePanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 386, Short.MAX_VALUE)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(yearComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(okButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cancelButton)))
                .addContainerGap())
            .addComponent(jSeparator1)
        );

        jPanel3Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {cancelButton, okButton});

        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(titlePanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(yearComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(okButton)
                    .addComponent(cancelButton))
                .addContainerGap())
        );

        setClosable(true);
        setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);
        setIconifiable(true);
        setMaximizable(true);
        setTitle("Discipline Seat Distribution");

        jPanel1.setPreferredSize(new java.awt.Dimension(990, 820));

        jLabel2.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel2.setText("Faculty:");

        facultyComboBox.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        programTypeComboBox.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        programTypeComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                programTypeComboBoxActionPerformed(evt);
            }
        });

        jLabel19.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel19.setText("Admission Year:");

        seatDistributionScrollPane.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        seatDistributionScrollPane.setAutoscrolls(true);

        seatDistributionTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Integer.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, true, true, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        seatDistributionTable.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        seatDistributionTable.setColumnSelectionAllowed(true);
        seatDistributionTable.setRowHeight(20);
        seatDistributionTable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        seatDistributionTable.getTableHeader().setReorderingAllowed(false);
        seatDistributionScrollPane.setViewportView(seatDistributionTable);
        seatDistributionTable.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        seatDistributionTable.getColumnModel().getColumn(0).setResizable(false);
        seatDistributionTable.getColumnModel().getColumn(0).setPreferredWidth(30);

        admissionYearComboBox.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        jLabel3.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel3.setText("Program Type:");

        progressBar.setStringPainted(true);

        jLabel1.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel1.setText("Shift:");

        shiftComboBox.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        shiftComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                shiftComboBoxActionPerformed(evt);
            }
        });

        jLabel15.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel15.setText("Campus:");

        campusComboBox.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        campusComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                campusComboBoxActionPerformed(evt);
            }
        });

        totalSeatsTextField.setEditable(false);
        totalSeatsTextField.setBackground(new java.awt.Color(255, 255, 255));
        totalSeatsTextField.setHorizontalAlignment(javax.swing.JTextField.TRAILING);

        jLabel4.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel4.setText("Total Seats:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(seatDistributionScrollPane)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(campusComboBox, 0, 188, Short.MAX_VALUE)
                            .addComponent(programTypeComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(admissionYearComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(shiftComboBox, 0, 125, Short.MAX_VALUE))
                            .addComponent(facultyComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(progressBar, javax.swing.GroupLayout.DEFAULT_SIZE, 325, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(totalSeatsTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(facultyComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel15)
                        .addComponent(campusComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel2)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(programTypeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel19)
                    .addComponent(admissionYearComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(shiftComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(progressBar, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(seatDistributionScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 715, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(totalSeatsTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addContainerGap())
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {programTypeComboBox, shiftComboBox, totalSeatsTextField});

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(4, 4, 4))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jScrollPane2.setViewportView(jPanel2);

        getContentPane().add(jScrollPane2, java.awt.BorderLayout.CENTER);

        topPanel.setLayout(new java.awt.BorderLayout());

        titlePanel.setLayout(new java.awt.BorderLayout());

        jLabel37.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel37.setIcon(new javax.swing.ImageIcon(getClass().getResource("/admission/view/images/form-icon.png"))); // NOI18N
        jLabel37.setText("Discipline Seat Distribution");
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

        copySeatsButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Gnome-Edit-Copy-32.png"))); // NOI18N
        copySeatsButton.setToolTipText("Copy From Previous Session Seats");
        copySeatsButton.setFocusPainted(false);
        copySeatsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                copySeatsButtonActionPerformed(evt);
            }
        });
        jPanel4.add(copySeatsButton);

        newSeatsButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Gnome-Document-New-32.png"))); // NOI18N
        newSeatsButton.setToolTipText("New Blank Seats");
        newSeatsButton.setFocusPainted(false);
        newSeatsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newSeatsButtonActionPerformed(evt);
            }
        });
        jPanel4.add(newSeatsButton);

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

    private void newSeatsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newSeatsButtonActionPerformed
        // TODO add your handling code here:
        if (JOptionPane.showConfirmDialog(this, "Do you want create blank seats?", "Question", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) != JOptionPane.YES_OPTION) {
            return;
        }
        new Thread() {
            @Override
            public void run() {
                Campus campus = (Campus) campusComboBox.getSelectedItem();
                Faculty faculty = (Faculty) facultyComboBox.getSelectedItem();
                AdmissionYear ay = (AdmissionYear) admissionYearComboBox.getSelectedItem();
                ProgramType pt = (ProgramType) programTypeComboBox.getSelectedItem();
                Shift shift = (Shift) shiftComboBox.getSelectedItem();
                if (campus == null || ay == null || pt == null || shift == null) {
                    return;
                }

//                AdmissionSession as = DatabaseManager.getAdmissionSession(ay.getAdmissionYearId(), shift.getShiftId(), pt.getProgramTypeId());
                AdmissionSession as = DatabaseManager.getSingleRecord(AdmissionSession.class, "admissionYear.admissionYearId = " + ay.getAdmissionYearId() + " AND programType.programTypeId = " + pt.getProgramTypeId());
                if (as == null) {
                    admission.utils.MessageBox.info(DisciplineSeatDistributionInternalFrame.this, "AdmissionSession not found");
                    return;
                }

                List<CposGroup> list;
                if (faculty == null) {
                    list = DatabaseManager.getCampusCposGroup(campus.getCampusId(), shift.getShiftId(), pt.getProgramTypeId());
                } else {
                    list = DatabaseManager.getCampusFacultyCposGroup(faculty.getFacultyId(), campus.getCampusId(), pt.getProgramTypeId(), shift.getShiftId());
                }

                setComponentEnabled(false);
                int count = 0;

                for (CposGroup cposGroup : list) {
                    progressBar.setValue(0);
                    progressBar.setMaximum(campusCategoriesList.size() - 1);

                    for (int j = 0; j < campusCategoriesList.size(); j++) {
                        CampusCategory campusCategory = campusCategoriesList.get(j);
                        DisciplineCategorySeats dcs = DatabaseManager.getDisciplineCategorySeats(campusCategory.getCampusCategoryId(), as.getAdmissionSessionId(), cposGroup.getCposGroupId(), shift.getShiftId());
                        if (dcs != null) {
                            continue;
                        }

                        DisciplineCategorySeats ob = new DisciplineCategorySeats(as, campusCategory, cposGroup, new Integer(0), "");
                        DatabaseManager.addData(ob);
                        count++;
                        progressBar.setValue(j);
                    }
                }
                progressBar.setValue(progressBar.getMaximum());
                //getDisciplineSeatDistribution();
                admission.utils.MessageBox.info(null, count + " record added successfully");
                setComponentEnabled(true);
            }
        }.start();
    }//GEN-LAST:event_newSeatsButtonActionPerformed

    private void campusComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_campusComboBoxActionPerformed
        // TODO add your handling code here:
        Campus campus = (Campus) this.campusComboBox.getSelectedItem();
        if (campus == null) {
            return;
        }
        this.getCampusCategory();

        boolean b = (campus.getIsMain());
        if (b) {
            this.facultyComboBox.setEnabled(true);
            this.getFaculty(campus);
        } else {
            this.facultyComboBox.setEnabled(false);
            facultyComboBox.removeAllItems();
        }
    }//GEN-LAST:event_campusComboBoxActionPerformed

    private void loadButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loadButtonActionPerformed
        // TODO add your handling code here:
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                setComponentEnabled(false);
                getDisciplineSeatDistribution();
                setComponentEnabled(true);
            }
        });
    }//GEN-LAST:event_loadButtonActionPerformed

    private void programTypeComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_programTypeComboBoxActionPerformed
        // TODO add your handling code here:
        this.getCampusCategory();
    }//GEN-LAST:event_programTypeComboBoxActionPerformed

    private void shiftComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_shiftComboBoxActionPerformed
        // TODO add your handling code here:
        this.getCampusCategory();
    }//GEN-LAST:event_shiftComboBoxActionPerformed

    private void copySeatsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_copySeatsButtonActionPerformed
        // TODO add your handling code here:
        if (JOptionPane.showConfirmDialog(this, "Have you selected correct 'Year' and 'Program Type'?", "Question", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) != JOptionPane.YES_OPTION) {
            return;
        }
        panelDialog.show();
    }//GEN-LAST:event_copySeatsButtonActionPerformed

    private void okButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_okButtonActionPerformed
        // TODO add your handling code here:
        if (JOptionPane.showConfirmDialog(this, "Do you want copy?", "Question", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) != JOptionPane.YES_OPTION) {
            return;
        }
        new Thread() {
            @Override
            public void run() {
                panelDialog.dispose();

                AdmissionYear fromYear = (AdmissionYear) yearComboBox.getSelectedItem();
                AdmissionYear toYear = (AdmissionYear) admissionYearComboBox.getSelectedItem();
                ProgramType pt = (ProgramType) programTypeComboBox.getSelectedItem();
                if (toYear == null || fromYear == null || pt == null) {
                    return;
                }

                if (toYear.equals(fromYear)) {
                    admission.utils.MessageBox.info(DisciplineSeatDistributionInternalFrame.this, "You selected same year");
                    return;
                }

                setComponentEnabled(false);
                int count = 0;

                AdmissionSession fromSession = DatabaseManager.getSingleRecord(AdmissionSession.class, "admissionYear.admissionYearId = " + fromYear.getAdmissionYearId() + " AND programType.programTypeId = " + pt.getProgramTypeId());
                AdmissionSession toSession = DatabaseManager.getSingleRecord(AdmissionSession.class, "admissionYear.admissionYearId = " + toYear.getAdmissionYearId() + " AND programType.programTypeId = " + pt.getProgramTypeId());
                if (fromSession == null || toSession == null) {
                    return;
                }

                List<DisciplineCategorySeats> list = DatabaseManager.getData(DisciplineCategorySeats.class, "admissionSession.admissionSessionId = " + fromSession.getAdmissionSessionId(), null);
//                Set set = fromSession.getDisciplineCategorySeatses();
//                if (set == null) {
//                    return;
//                }

//                Object[] toArray = set.toArray();
                progressBar.setValue(0);
                progressBar.setMaximum(list.size() - 1);

                count += list.size();
                for (int j = 0; j < list.size(); j++) {
                    DisciplineCategorySeats dcs = list.get(j);
                    try {
                        dcs = (DisciplineCategorySeats) dcs.clone();
                    } catch (CloneNotSupportedException ex) {
                        Logger.getLogger(DisciplineSeatDistributionInternalFrame.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    dcs.setDisciplineCategorySeatsId(null);
                    dcs.setAdmissionSession(toSession);
                    DatabaseManager.addData(dcs);
                    progressBar.setValue(j);
                }

                progressBar.setValue(progressBar.getMaximum());
                //getDisciplineSeatDistribution();
                admission.utils.MessageBox.info(null, count + " record added successfully");
                setComponentEnabled(true);
            }
        }.start();
    }//GEN-LAST:event_okButtonActionPerformed

    private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelButtonActionPerformed
        // TODO add your handling code here:
        this.panelDialog.dispose();
    }//GEN-LAST:event_cancelButtonActionPerformed

    private void hideButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hideButtonActionPerformed
        // TODO add your handling code here:
        setVisible(false);
    }//GEN-LAST:event_hideButtonActionPerformed

    private void saveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveButtonActionPerformed
        // TODO add your handling code here:
        if (disciplineRowsList.isEmpty()) {
            return;
        }
        new Thread() {
            @Override
            public void run() {
                setComponentEnabled(false);
                progressBar.setValue(0);
                progressBar.setMaximum(disciplineRowsList.size() - 1);

                try {
                    int rows = defaultTableModel.getRowCount();
                    int columns = defaultTableModel.getColumnCount() - 2;

                    for (int i = 0; i < rows; i++) {
                        List<DisciplineCategorySeats> list = disciplineRowsList.get(i);
                        for (int j = 0; j < columns; j++) {
                            Integer seats = (Integer) defaultTableModel.getValueAt(i, j + 1);
                            DisciplineCategorySeats dcs = list.get(j);
                            dcs.setNumberOfSeats(seats);
                            DatabaseManager.save(dcs);
                        }
                        progressBar.setValue(i);
                    }
                    MessageBox.info(DisciplineSeatDistributionInternalFrame.this, "Session saved successfully");
                } catch (HibernateException he) {
                    MessageBox.error(DisciplineSeatDistributionInternalFrame.this, he);
                    he.printStackTrace();
                }
                setComponentEnabled(false);
            }
        }.start();
    }//GEN-LAST:event_saveButtonActionPerformed

    private void setComponentEnabled(boolean b) {
        loadButton.setEnabled(b);
        saveButton.setEnabled(b);
        newSeatsButton.setEnabled(b);
        copySeatsButton.setEnabled(b);
        hideButton.setEnabled(b);
        campusComboBox.setEnabled(b);
        facultyComboBox.setEnabled(b);
        programTypeComboBox.setEnabled(b);
        admissionYearComboBox.setEnabled(b);
        shiftComboBox.setEnabled(b);
    }

    private void getShift() {
        this.shiftComboBox.removeAllItems();
        List<Shift> list = DatabaseManager.getData(Shift.class.getName(), "shiftId");

        for (Shift list1 : list) {
            this.shiftComboBox.addItem(list1);
        }
    }

    private void getProgramType() {
        this.programTypeComboBox.removeAllItems();
        List<ProgramType> list = DatabaseManager.getData(ProgramType.class.getName(), "name");

        for (ProgramType list1 : list) {
            this.programTypeComboBox.addItem(list1);
        }
    }

    private void getAdmissionYear() {
        this.admissionYearComboBox.removeAllItems();
        this.yearComboBox.removeAllItems();
        List<AdmissionYear> list = DatabaseManager.getData(AdmissionYear.class.getName(), "year DESC");

        for (AdmissionYear list1 : list) {
            this.admissionYearComboBox.addItem(list1);
            this.yearComboBox.addItem(list1);
        }
    }

    private void getCampus() {
        this.campusComboBox.removeAllItems();
        List<Campus> list = DatabaseManager.getData(Campus.class.getName(), "displayOrder");

        for (Campus list1 : list) {
            this.campusComboBox.addItem(list1);
        }
    }

    private void getFaculty(Campus campus) {
        facultyComboBox.removeAllItems();

        List<Faculty> list = DatabaseManager.getCampusFaculties(campus.getCampusId());
        for (Faculty list1 : list) {
            facultyComboBox.addItem(list1);
        }
    }

    private void getCampusCategory() {

        Campus campus = (Campus) this.campusComboBox.getSelectedItem();
        ProgramType programType = (ProgramType) this.programTypeComboBox.getSelectedItem();
        Shift shift = (Shift) this.shiftComboBox.getSelectedItem();
        if (campus == null || programType == null || shift == null) {
            return;
        }

        campusCategoriesList = DatabaseManager.getCampusCategory(campus.getCampusId(), programType.getProgramTypeId(), shift.getShiftId(), "displayOrder");

        List<Object> header = new ArrayList<>();
        final List<Class> types = new ArrayList();
        final List<Boolean> canEdit = new ArrayList();

        header.add("DISCIPLINE");
        types.add(java.lang.Object.class);
        canEdit.add(false);

        for (CampusCategory cc : campusCategoriesList) {
            header.add(cc);
            types.add(java.lang.Integer.class);
            canEdit.add(true);
        }

        header.add("TOTAL SEATS");
        types.add(java.lang.Integer.class);
        canEdit.add(false);
        disciplineRowsList.clear();

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
        seatDistributionTable.setModel(defaultTableModel);
        setVerticalHeaderColumnUI();
    }

    private void getDisciplineSeatDistribution() {
        admission.utils.Utility.removeTableRows(this.defaultTableModel);
        disciplineRowsList.clear();
        DatabaseManager.clear();

        Campus campus = (Campus) this.campusComboBox.getSelectedItem();
        Faculty faculty = (Faculty) this.facultyComboBox.getSelectedItem();
        AdmissionYear ay = (AdmissionYear) this.admissionYearComboBox.getSelectedItem();
        ProgramType pt = (ProgramType) this.programTypeComboBox.getSelectedItem();
        Shift shift = (Shift) this.shiftComboBox.getSelectedItem();
        if (campus == null || ay == null || pt == null || shift == null) {
            return;
        }

//        AdmissionSession as = DatabaseManager.getAdmissionSession(ay.getAdmissionYearId(), shift.getShiftId(), pt.getProgramTypeId());
        AdmissionSession as = DatabaseManager.getSingleRecord(AdmissionSession.class, "admissionYear.admissionYearId = " + ay.getAdmissionYearId() + " AND programType.programTypeId = " + pt.getProgramTypeId());
        if (as == null) {
            admission.utils.MessageBox.info(this, "AdmissionSession not found");
            return;
        }

        List<CposGroup> list;
        if (faculty == null) {
            list = DatabaseManager.getCampusCposGroup(campus.getCampusId(), shift.getShiftId(), pt.getProgramTypeId());
        } else {
            list = DatabaseManager.getCampusFacultyCposGroup(faculty.getFacultyId(), campus.getCampusId(), pt.getProgramTypeId(), shift.getShiftId());
        }
        Integer totalCount = 0;
        for (CposGroup cposGroup : list) {
            List<DisciplineCategorySeats> list2 = DatabaseManager.getCposGroupSeatsDistribution(cposGroup.getCposGroupId(), as.getAdmissionSessionId(), pt.getProgramTypeId(), shift.getShiftId());
            if (list2.isEmpty()) {
                continue;
            }

            disciplineRowsList.add(list2);

            List<Object> row = new ArrayList<>();
            row.add(cposGroup);
            Integer count = 0;
            for (DisciplineCategorySeats dcs : list2) {
                row.add(dcs.getNumberOfSeats());
                count += dcs.getNumberOfSeats();
            }
            totalCount += count;
            row.add(count);
            defaultTableModel.addRow(row.toArray());
        }
        this.totalSeatsTextField.setText(String.valueOf(totalCount));
        if (disciplineRowsList.isEmpty()) {
            admission.utils.MessageBox.info(this, "Seats are not assigned");
        } else {
            setColumnDataWidth(0);
        }
    }

    private void setColumnDataWidth(int column) {
        int preferredWidth = 0;
        for (int row = 0; row < seatDistributionTable.getRowCount(); row++) {
            preferredWidth = Math.max(preferredWidth, getCellDataWidth(row, column));
        }
        seatDistributionTable.getColumnModel().getColumn(column).setPreferredWidth(preferredWidth + 10);
    }

    private int getCellDataWidth(int row, int column) {
        TableCellRenderer cellRenderer = seatDistributionTable.getCellRenderer(row, column);
        Component c = seatDistributionTable.prepareRenderer(cellRenderer, row, column);
        return c.getPreferredSize().width + seatDistributionTable.getIntercellSpacing().width;
    }

    private void setVerticalHeaderColumnUI() {
        TableCellRenderer headerRenderer = new VerticalTableHeaderCellRenderer();
        Enumeration<TableColumn> columns = seatDistributionTable.getColumnModel().getColumns();
        boolean b = false;
        while (columns.hasMoreElements()) {
            TableColumn columm = columns.nextElement();
            if (b) {
                columm.setPreferredWidth(40);
                columm.setMaxWidth(40);
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
    private javax.swing.JComboBox campusComboBox;
    private javax.swing.JButton cancelButton;
    private javax.swing.JButton copySeatsButton;
    private javax.swing.JComboBox facultyComboBox;
    private javax.swing.JButton hideButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JButton loadButton;
    private javax.swing.JButton newSeatsButton;
    private javax.swing.JButton okButton;
    private javax.swing.JComboBox programTypeComboBox;
    private javax.swing.JProgressBar progressBar;
    private javax.swing.JButton saveButton;
    private javax.swing.JScrollPane seatDistributionScrollPane;
    private javax.swing.JTable seatDistributionTable;
    private javax.swing.JComboBox shiftComboBox;
    private javax.swing.JPanel titlePanel;
    private javax.swing.JPanel titlePanel1;
    private javax.swing.JPanel topPanel;
    private javax.swing.JTextField totalSeatsTextField;
    private javax.swing.JComboBox yearComboBox;
    // End of variables declaration//GEN-END:variables
    private DocumentFilter documentFilter;
    private Resources privileges;
    private List<CampusCategory> campusCategoriesList;
    private DefaultTableModel defaultTableModel;
    private List<List<DisciplineCategorySeats>> disciplineRowsList;
    private JDialog panelDialog;
}
