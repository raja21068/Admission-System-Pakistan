package admission.view.seats;

import admission.controller.DatabaseManager;
import admission.model.AdmissionYear;
import admission.model.Campus;
import admission.model.DistrictSeatDistribution;
import admission.model.Jurisdiction;
import admission.model.security.Resources;
import admission.model.Shift;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractAction;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;
import javax.swing.event.CellEditorListener;
import javax.swing.event.ChangeEvent;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import admission.utils.Coder;
import admission.utils.RoundedBorder;

/**
 *
 * @author Raja Kumar & Jay Kumar
 */
public class JurisdictionSeatDistributionInternalFrame extends javax.swing.JInternalFrame {

    public JurisdictionSeatDistributionInternalFrame() {
        initComponents();
        
        admission.utils.Utility.hideOnEscape(this);
        this.titlePanel.setBorder(RoundedBorder.createGradientBorder());        
        
        panelDialog = new JDialog((Frame) javax.swing.SwingUtilities.windowForComponent(this), "Copy From Year", true);
        panelDialog.add(this.jPanel3);
        panelDialog.setSize(410, 110);
        panelDialog.setLocationRelativeTo(null);
        panelDialog.setResizable(false);
        
        this.seatDistributionTable.getDefaultEditor(Integer.class).addCellEditorListener(new CellEditorListener(){
            @Override public void editingCanceled(ChangeEvent e) {}
            @Override public void editingStopped(ChangeEvent e) {
                int row = seatDistributionTable.getSelectedRow();
                int col = seatDistributionTable.getSelectedColumn();
                
                if(col > 0 && col < 4){
                    Integer urban;
                    Integer rural;
                    Integer other;
                    if(col == 1) {
                        urban = (Integer) seatDistributionTable.getValueAt(row, col);
                        rural = (Integer) seatDistributionTable.getValueAt(row, col + 1);
                        other = (Integer) seatDistributionTable.getValueAt(row, col + 2);
                    } else if(col == 2) {
                        urban = (Integer) seatDistributionTable.getValueAt(row, col - 1);
                        rural = (Integer) seatDistributionTable.getValueAt(row, col);
                        other = (Integer) seatDistributionTable.getValueAt(row, col + 1);
                    } else {
                        urban = (Integer) seatDistributionTable.getValueAt(row, col - 2);
                        rural = (Integer) seatDistributionTable.getValueAt(row, col - 1);
                        other = (Integer) seatDistributionTable.getValueAt(row, col);
                    }
                    
                    int total = urban + rural + other;
                    defaultTableModel.setValueAt(total, row, 4);
                    
                    int totalCount = 0;
                    for (int i = 0; i < defaultTableModel.getRowCount(); i++) {
                        for (int j = 1; j < defaultTableModel.getColumnCount() - 1; j++) {
                            totalCount += (Integer) defaultTableModel.getValueAt(i, j);
                        }
                    }
                    totalSeatsTextField.setText(String.valueOf(totalCount));
                }else admission.utils.MessageBox.info(null, "Out of index");
            }
        });
        this.seatDistributionTable.getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), "Enter");
        this.seatDistributionTable.getActionMap().put("Enter", new AbstractAction() {
            @Override public void actionPerformed(ActionEvent ae) {
                if(seatDistributionTable.getCellEditor() == null) return;
                
                seatDistributionTable.getCellEditor().stopCellEditing();
                int row = seatDistributionTable.getSelectedRow();
                int col = seatDistributionTable.getSelectedColumn() + 1;
                if(col > seatDistributionTable.getColumnCount() - 1){
                    col = 0; row += 1;
                    if(row > seatDistributionTable.getRowCount() - 1) row = 0;
                }
                seatDistributionTable.changeSelection(row, col, false, false);
            }
        });
        ((DefaultTableCellRenderer)seatDistributionTable.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);       
        this.defaultTableModel = (DefaultTableModel) this.seatDistributionTable.getModel();
    }
    
    @Override
    public void setVisible(boolean aFlag) {
        if(aFlag){
            this.getShift();
            this.getCampus();
            this.getAdmissionYear();
        }
        super.setVisible(aFlag);
    }
    
    public void setPrivileges(Resources privileges){
        this.privileges = privileges;
//        this.deleteButton.setEnabled((privileges.isDeletePrivilige()));
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        yearComboBox = new javax.swing.JComboBox();
        okButton = new javax.swing.JButton();
        cancelButton = new javax.swing.JButton();
        jLabel19 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        seatDistributionTable = new javax.swing.JTable(){
            /*protected JTableHeader createDefaultTableHeader() {
                return new GroupableTableHeader(columnModel);
            }*/
        };
        admissionYearComboBox = new javax.swing.JComboBox();
        copySeatsButton = new javax.swing.JButton();
        newSeatsButton = new javax.swing.JButton();
        progressBar = new javax.swing.JProgressBar();
        jLabel1 = new javax.swing.JLabel();
        shiftComboBox = new javax.swing.JComboBox();
        jLabel15 = new javax.swing.JLabel();
        campusComboBox = new javax.swing.JComboBox();
        loadButton = new javax.swing.JButton();
        totalSeatsTextField = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        suJurisdictionCheckBox = new javax.swing.JCheckBox();
        titlePanel = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        hideButton = new javax.swing.JButton();
        saveButton = new javax.swing.JButton();

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Copy From Year");

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

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(yearComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGap(0, 218, Short.MAX_VALUE)
                        .addComponent(okButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cancelButton)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(yearComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(okButton)
                    .addComponent(cancelButton))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        setClosable(true);
        setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);
        setTitle("Jurisdiction Seat Distribution");

        jLabel19.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel19.setText("Admission Year:");

        seatDistributionTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "District", "Urban", "Rural", "Other", "Total"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, true, true, true, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        seatDistributionTable.setRowHeight(20);
        seatDistributionTable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        seatDistributionTable.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(seatDistributionTable);
        if (seatDistributionTable.getColumnModel().getColumnCount() > 0) {
            seatDistributionTable.getColumnModel().getColumn(0).setPreferredWidth(300);
            seatDistributionTable.getColumnModel().getColumn(1).setResizable(false);
            seatDistributionTable.getColumnModel().getColumn(2).setResizable(false);
            seatDistributionTable.getColumnModel().getColumn(3).setResizable(false);
            seatDistributionTable.getColumnModel().getColumn(4).setResizable(false);
        }

        admissionYearComboBox.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        copySeatsButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Gnome-Edit-Copy-32.png"))); // NOI18N
        copySeatsButton.setToolTipText("Copy From Previous Year Seats");
        copySeatsButton.setFocusPainted(false);
        copySeatsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                copySeatsButtonActionPerformed(evt);
            }
        });

        newSeatsButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Gnome-Document-New-32.png"))); // NOI18N
        newSeatsButton.setToolTipText("New Blank Seats");
        newSeatsButton.setFocusPainted(false);
        newSeatsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newSeatsButtonActionPerformed(evt);
            }
        });

        progressBar.setStringPainted(true);

        jLabel1.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel1.setText("Shift:");

        shiftComboBox.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        jLabel15.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel15.setText("Campus:");

        campusComboBox.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        campusComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                campusComboBoxActionPerformed(evt);
            }
        });

        loadButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Gnome-Emblem-Downloads-32.png"))); // NOI18N
        loadButton.setToolTipText("Load");
        loadButton.setFocusPainted(false);
        loadButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loadButtonActionPerformed(evt);
            }
        });

        totalSeatsTextField.setEditable(false);
        totalSeatsTextField.setBackground(new java.awt.Color(255, 255, 255));
        totalSeatsTextField.setHorizontalAlignment(javax.swing.JTextField.TRAILING);

        jLabel4.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel4.setText("Total Seats:");

        suJurisdictionCheckBox.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        suJurisdictionCheckBox.setSelected(true);
        suJurisdictionCheckBox.setText("Sindh University Jurisdiction");
        suJurisdictionCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                suJurisdictionCheckBoxActionPerformed(evt);
            }
        });

        titlePanel.setLayout(new java.awt.BorderLayout());

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/admission/view/images/form-icon.png"))); // NOI18N
        jLabel7.setText("Jurisdiction Seat Distribution");
        jLabel7.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, java.awt.Color.darkGray), javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5)));
        titlePanel.add(jLabel7, java.awt.BorderLayout.PAGE_END);

        hideButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Gnome-Go-Home-32.png"))); // NOI18N
        hideButton.setFocusPainted(false);
        hideButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hideButtonActionPerformed(evt);
            }
        });

        saveButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Gnome-Media-Floppy-32.png"))); // NOI18N
        saveButton.setToolTipText("Save");
        saveButton.setFocusPainted(false);
        saveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveButtonActionPerformed(evt);
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
                        .addComponent(jLabel19)
                        .addGap(4, 4, 4)
                        .addComponent(admissionYearComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(suJurisdictionCheckBox, javax.swing.GroupLayout.DEFAULT_SIZE, 194, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(4, 4, 4)
                        .addComponent(campusComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(shiftComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(totalSeatsTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(loadButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(saveButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(copySeatsButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(newSeatsButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(hideButton))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(progressBar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(titlePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(loadButton)
                    .addComponent(hideButton)
                    .addComponent(newSeatsButton)
                    .addComponent(copySeatsButton)
                    .addComponent(saveButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(campusComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(shiftComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel15)
                            .addComponent(jLabel1))))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addComponent(jLabel19))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(admissionYearComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(suJurisdictionCheckBox)))
                .addGap(7, 7, 7)
                .addComponent(progressBar, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 363, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(totalSeatsTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void newSeatsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newSeatsButtonActionPerformed
        // TODO add your handling code here:
        if(JOptionPane.showConfirmDialog(this, "Do you want create blank seats?", "Question", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) != JOptionPane.YES_OPTION) return;
        new Thread(){
            @Override public void run() {
                Campus campus = (Campus) campusComboBox.getSelectedItem();
                AdmissionYear ay = (AdmissionYear) admissionYearComboBox.getSelectedItem();
                Shift shift = (Shift) shiftComboBox.getSelectedItem();
                if(campus == null || ay == null || shift == null) return;
        
                List<Jurisdiction> list = DatabaseManager.getJurisdictionArea(campus.getCampusId(), Coder.Encoder.booleanEncode(suJurisdictionCheckBox.isSelected()));
        
                setComponentEnabled(false);
                progressBar.setValue(0);
                progressBar.setMaximum(list.size() - 1);
                int count = 0;
                for (int i = 0; i < list.size(); i++) {
                    Jurisdiction juris = list.get(i);
                    
                    DistrictSeatDistribution dsd = DatabaseManager.getSingleDistrictSeatDistribution(juris.getJurisdictionId(), ay.getAdmissionYearId(), shift.getShiftId());
                    if(dsd != null) continue;
                    Integer urban  = 0;
                    Integer rural  = 0;
                    Integer other  = 0;
                    
                    dsd = new DistrictSeatDistribution(shift, juris, ay, urban, rural, "", other);
                    DatabaseManager.addData(dsd);
                    progressBar.setValue(i);
                    count++;
                }
                progressBar.setValue(progressBar.getMaximum());
                admission.utils.MessageBox.info(null, count + " record added successfully");
                setComponentEnabled(true);
            }
        }.start();
    }//GEN-LAST:event_newSeatsButtonActionPerformed

    private void loadButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loadButtonActionPerformed
        // TODO add your handling code here:
        this.getJurisdictionSeatDistribution();
    }//GEN-LAST:event_loadButtonActionPerformed

    private void suJurisdictionCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_suJurisdictionCheckBoxActionPerformed
        // TODO add your handling code here:
        this.getJurisdictionSeatDistribution();
    }//GEN-LAST:event_suJurisdictionCheckBoxActionPerformed

    private void copySeatsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_copySeatsButtonActionPerformed
        // TODO add your handling code here:
        if(JOptionPane.showConfirmDialog(this, "Have you selected correct 'Year'?", "Question", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) != JOptionPane.YES_OPTION) return;
        panelDialog.show();
    }//GEN-LAST:event_copySeatsButtonActionPerformed

    private void campusComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_campusComboBoxActionPerformed
        // TODO add your handling code here:
        this.getJurisdictionSeatDistribution();
    }//GEN-LAST:event_campusComboBoxActionPerformed

    private void okButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_okButtonActionPerformed
        // TODO add your handling code here:
        if(JOptionPane.showConfirmDialog(this, "Do you want copy?", "Question", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) != JOptionPane.YES_OPTION) return;
        new Thread(){
            @Override public void run() {
                panelDialog.dispose();

//                Campus campus = (Campus) campusComboBox.getSelectedItem();
                AdmissionYear ay1 = (AdmissionYear) yearComboBox.getSelectedItem();
                AdmissionYear ay = (AdmissionYear) admissionYearComboBox.getSelectedItem();
                if(ay == null || ay1 == null) return;

                if(ay.equals(ay1)){
                    admission.utils.MessageBox.info(JurisdictionSeatDistributionInternalFrame.this, "You selected same year");
                    return;
                }
                List<DistrictSeatDistribution> list = DatabaseManager.getDistrictSeatDistribution(ay1.getAdmissionYearId());
//                Set set = ay1.getDistrictSeatDistributions();
//                Object[] toArray = set.toArray();
                if(list.isEmpty()){
                    return;
                }
                setComponentEnabled(false);
                int count = 0;

                progressBar.setValue(0);
                progressBar.setMaximum(list.size() - 1);

                count += list.size();
                
                for (int i = 0; i < list.size(); i++) {
                    DistrictSeatDistribution dsd = (DistrictSeatDistribution) list.get(i);
                    try {
                        dsd = (DistrictSeatDistribution) dsd.clone();
                    } catch (CloneNotSupportedException ex) {
                        Logger.getLogger(JurisdictionSeatDistributionInternalFrame.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    dsd.setDistrictSeatDistributionId(null);
                    dsd.setAdmissionYear(ay);
                    DatabaseManager.addData(dsd);
                    progressBar.setValue(i);
                }

                progressBar.setValue(progressBar.getMaximum());
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
        new Thread() {
            @Override public void run() {
                int rows = defaultTableModel.getRowCount();
                progressBar.setValue(0);
                progressBar.setMaximum(rows);
                for (int i = 0; i < rows; i++) {
                    DistrictSeatDistribution dsd = (DistrictSeatDistribution) defaultTableModel.getValueAt(i, 0);
                    Integer urban = (Integer) defaultTableModel.getValueAt(i, 1);
                    Integer rural = (Integer) defaultTableModel.getValueAt(i, 2);
                    Integer other = (Integer) defaultTableModel.getValueAt(i, 3);
                    dsd.setUrban(urban);
                    dsd.setRural(rural);
                    dsd.setOther(other);

                    DatabaseManager.save(dsd);
                    progressBar.setValue(i + 1);
                }
            }
        }.start();
    }//GEN-LAST:event_saveButtonActionPerformed

    private void getShift(){
        this.shiftComboBox.removeAllItems();
        List<Shift> list = DatabaseManager.getData(Shift.class.getName(), "isMorning DESC");
        
        for (Shift list1 : list) {
            this.shiftComboBox.addItem(list1);
        }
    }
    
    private void getAdmissionYear(){
        this.admissionYearComboBox.removeAllItems();
        this.yearComboBox.removeAllItems();
        List<AdmissionYear> list = DatabaseManager.getData(AdmissionYear.class.getName(), "year DESC");
        
        for (AdmissionYear list1 : list) {
            this.admissionYearComboBox.addItem(list1);
            this.yearComboBox.addItem(list1);
        }
    }
    
    private void getCampus(){
        this.campusComboBox.removeAllItems();
        List<Campus> list = DatabaseManager.getData(Campus.class.getName(), "displayOrder");
        
        for (int i = 0; i < list.size(); i++) {
            this.campusComboBox.addItem(list.get(i));
        }
    }
    
    private void getJurisdictionSeatDistribution(){
        admission.utils.Utility.removeTableRows(this.defaultTableModel);
        
        Campus campus = (Campus) this.campusComboBox.getSelectedItem();
        AdmissionYear ay = (AdmissionYear) this.admissionYearComboBox.getSelectedItem();
        Shift shift = (Shift) this.shiftComboBox.getSelectedItem();
        if(campus == null || ay == null || shift == null) return;
        
        List<DistrictSeatDistribution> list = DatabaseManager.getDistrictSeatDistribution(campus.getCampusId(), ay.getAdmissionYearId(), shift.getShiftId(), Coder.Encoder.booleanEncode(this.suJurisdictionCheckBox.isSelected()));
        
        Integer totalCount = 0;
        for (DistrictSeatDistribution dsd : list) {
            Integer urban  = dsd.getUrban();
            Integer rural  = dsd.getRural();
            Integer other  = dsd.getOther();
            Integer total = urban + rural + other;
            
            List<Object> row = new ArrayList<>();
            row.add(dsd);
            row.add(urban);
            row.add(rural);
            row.add(other);
            row.add(total);
            totalCount += total;
            defaultTableModel.addRow(row.toArray());
        }
        this.totalSeatsTextField.setText(String.valueOf(totalCount));
        if(defaultTableModel.getRowCount() == 0){
            admission.utils.MessageBox.info(this, "Seats are not assigned");
        }
    }
    
    private void setComponentEnabled(boolean b){
        this.campusComboBox.setEnabled(b);
        this.admissionYearComboBox.setEnabled(b);
        this.shiftComboBox.setEnabled(b);
        this.suJurisdictionCheckBox.setEnabled(b);
        this.copySeatsButton.setEnabled(b);
        this.newSeatsButton.setEnabled(b);
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox admissionYearComboBox;
    private javax.swing.JComboBox campusComboBox;
    private javax.swing.JButton cancelButton;
    private javax.swing.JButton copySeatsButton;
    private javax.swing.JButton hideButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton loadButton;
    private javax.swing.JButton newSeatsButton;
    private javax.swing.JButton okButton;
    private javax.swing.JProgressBar progressBar;
    private javax.swing.JButton saveButton;
    private javax.swing.JTable seatDistributionTable;
    private javax.swing.JComboBox shiftComboBox;
    private javax.swing.JCheckBox suJurisdictionCheckBox;
    private javax.swing.JPanel titlePanel;
    private javax.swing.JTextField totalSeatsTextField;
    private javax.swing.JComboBox yearComboBox;
    // End of variables declaration//GEN-END:variables
    //private DocumentFilter documentFilter;
    private Resources privileges;
    private DefaultTableModel defaultTableModel;
    private JDialog panelDialog;
}
