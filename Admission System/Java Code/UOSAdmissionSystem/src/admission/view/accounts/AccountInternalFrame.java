package admission.view.accounts;

import admission.controller.DatabaseManager;
import admission.enums.MessageEnum;
import admission.model.Accounts;
import admission.model.AdmissionListDetails;
import admission.model.AdmissionYear;
import admission.model.Candidate;
import admission.model.Part;
import admission.model.PartRegistry;
import admission.model.security.Resources;
import admission.model.ProgramType;
import com.yog.component.OperationButtons;
import java.awt.event.ActionEvent;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import javax.swing.JPanel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.AbstractDocument;
import javax.swing.text.DocumentFilter;
import org.hibernate.HibernateException;
import admission.utils.IConstant;
import admission.utils.ContainerController;
import admission.utils.DateUtility;
import admission.utils.NumberFormatter;
import admission.utils.RoundedBorder;

/**
 *
 * @author Raja Kumar & Jay Kumar
 */
public class AccountInternalFrame extends javax.swing.JInternalFrame {

    public AccountInternalFrame() {
        initComponents();

        this.titlePanel.setBorder(RoundedBorder.createGradientBorder());

        admission.utils.Utility.hideOnEscape(this);

        defaultTableModel = (DefaultTableModel) partRegistryTable.getModel();
        partRegistryTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                setSelectedPartRegistry();
            }
        });

//        Toolkit.getDefaultToolkit().addAWTEventListener(new AWTEventListener(){
//            @Override
//            public void eventDispatched(AWTEvent event) {
//                if(event.getID() == MouseEvent.MOUSE_CLICKED) {
//                    MouseEvent mevent = (MouseEvent) event;
//                     int row = partRegistryTable.rowAtPoint(mevent.getPoint());
//                     if(row == -1) {
//                         partRegistryTable.clearSelection();
//                     }
//                }               
//            }           
//        }, AWTEvent.MOUSE_EVENT_MASK);
        this.buttonsPanel1.add(this.getOperationButtons(true, true, true, false));

        documentFilter = new admission.utils.UppercaseDocumentFilter();
        numberDocumentFilter = new admission.utils.NumberDocumentFilter();
        ((AbstractDocument) this.seatNoTextField.getDocument()).setDocumentFilter(numberDocumentFilter);
        ((AbstractDocument) this.remarksACTextField.getDocument()).setDocumentFilter(documentFilter);
        ((AbstractDocument) this.remarksPRTextField.getDocument()).setDocumentFilter(documentFilter);
        admission.utils.Utility.comboBoxScroll(activeAdmissionListDetailsComboBox);
    }

    boolean b = true;

    @Override
    public void setVisible(boolean aFlag) {
        if (aFlag) {
            if (b) {
                //this.getDesktopPane().add(partRegistryInternalFrame); b = !b;
            }
            this.getAdmissionYear();
            this.getProgramType();
            clear();
        }
        super.setVisible(aFlag);
    }

    public void setPrivileges(Resources privileges) {
        this.privileges = privileges;
    }

    private JPanel getOperationButtons(boolean save, boolean delete, boolean clear, boolean back) {
        OperationButtons operationButtons = new OperationButtons() {
            @Override
            public void saveOperation(ActionEvent evt) {
                AccountInternalFrame.this.savePartRegistry();
            }

            @Override
            public void deleteOperation(ActionEvent evt) {
                AccountInternalFrame.this.deletePartRegistry();
            }

            @Override
            public void newOperation(ActionEvent evt) {
//                clear();
//                clearAccount();
                clearPartRegistry();
            }

            @Override
            public void backOperation(ActionEvent evt) {
                AccountInternalFrame.this.setVisible(false);
            }
        };
//        operationButtons.setSize(335, 60);
        operationButtons.setVisible(false, save, delete, clear, back);
        return operationButtons;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        masterPanel = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        admissionYearComboBox = new javax.swing.JComboBox();
        jLabel6 = new javax.swing.JLabel();
        programTypeComboBox = new javax.swing.JComboBox();
        seatNoTextField = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        nameTextField = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        fathersNameTextField = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        surnameTextField = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        registryDateFormattedTextField = new javax.swing.JFormattedTextField();
        jLabel4 = new javax.swing.JLabel();
        remarksACTextField = new javax.swing.JTextField();
        activeLabel = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        statusComboBox = new javax.swing.JComboBox();
        statusLabel = new javax.swing.JLabel();
        saveAccountButton = new javax.swing.JButton();
        partRegistryPanel = new javax.swing.JPanel();
        admissionSessionTextField = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        categoryTextField = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        activeAdmissionListDetailsComboBox = new javax.swing.JComboBox();
        partsComboBox = new javax.swing.JComboBox();
        jLabel16 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        challanNoTextField = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        amountTextField = new javax.swing.JTextField();
        challanDateFormattedTextField = new javax.swing.JFormattedTextField();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        challanTypeComboBox = new javax.swing.JComboBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        partRegistryTable = new javax.swing.JTable();
        jLabel21 = new javax.swing.JLabel();
        totalAmountTextField = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        remarksPRTextField = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        buttonsPanel1 = new javax.swing.JPanel();
        titlePanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jButton1 = new javax.swing.JButton();

        setClosable(true);
        setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);
        setIconifiable(true);
        setResizable(true);
        setTitle("Account");

        masterPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Master / Account Details"));

        jLabel5.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel5.setText("Admission Year:");

        admissionYearComboBox.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        jLabel6.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel6.setText("Program Type:");

        programTypeComboBox.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        programTypeComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                programTypeComboBoxActionPerformed(evt);
            }
        });

        seatNoTextField.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        seatNoTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                seatNoTextFieldActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel7.setText("Seat No.:");

        jLabel9.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel9.setText("Name:");

        nameTextField.setEditable(false);
        nameTextField.setBackground(new java.awt.Color(255, 255, 255));

        jLabel10.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel10.setText("Father's Name:");

        fathersNameTextField.setEditable(false);
        fathersNameTextField.setBackground(new java.awt.Color(255, 255, 255));

        jLabel11.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel11.setText("Surname:");

        surnameTextField.setEditable(false);
        surnameTextField.setBackground(new java.awt.Color(255, 255, 255));

        jLabel3.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel3.setText("Registry Date:");

        registryDateFormattedTextField.setEditable(false);
        try {
            registryDateFormattedTextField.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##-##-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        registryDateFormattedTextField.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel4.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel4.setText("Remarks:");

        activeLabel.setForeground(new java.awt.Color(0, 153, 0));
        activeLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        activeLabel.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel2.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel2.setText("Status:");

        statusComboBox.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        statusComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Active", "Deactive" }));
        statusComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                statusComboBoxActionPerformed(evt);
            }
        });

        statusLabel.setForeground(new java.awt.Color(255, 0, 0));
        statusLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        statusLabel.setText("Not Registered");
        statusLabel.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        saveAccountButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Gnome-Media-Floppy-32.png"))); // NOI18N
        saveAccountButton.setText("Save");
        saveAccountButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveAccountButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout masterPanelLayout = new javax.swing.GroupLayout(masterPanel);
        masterPanel.setLayout(masterPanelLayout);
        masterPanelLayout.setHorizontalGroup(
            masterPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(masterPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(masterPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(masterPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(nameTextField)
                    .addGroup(masterPanelLayout.createSequentialGroup()
                        .addComponent(fathersNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(surnameTextField))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, masterPanelLayout.createSequentialGroup()
                        .addGroup(masterPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(seatNoTextField)
                            .addComponent(admissionYearComboBox, 0, 110, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(programTypeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(masterPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(masterPanelLayout.createSequentialGroup()
                        .addGroup(masterPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(masterPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(masterPanelLayout.createSequentialGroup()
                                .addComponent(registryDateFormattedTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(10, 10, 10)
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(statusComboBox, 0, 103, Short.MAX_VALUE))
                            .addComponent(remarksACTextField)))
                    .addGroup(masterPanelLayout.createSequentialGroup()
                        .addGroup(masterPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(statusLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(activeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(saveAccountButton)))
                .addContainerGap(122, Short.MAX_VALUE))
        );
        masterPanelLayout.setVerticalGroup(
            masterPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(masterPanelLayout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(masterPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(masterPanelLayout.createSequentialGroup()
                        .addGroup(masterPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(registryDateFormattedTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2)
                            .addComponent(statusComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(masterPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(remarksACTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(masterPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(masterPanelLayout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(statusLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(masterPanelLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(saveAccountButton))))
                    .addGroup(masterPanelLayout.createSequentialGroup()
                        .addGroup(masterPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6)
                            .addComponent(programTypeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(admissionYearComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(masterPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(seatNoTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(masterPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(nameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(masterPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(masterPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel10)
                                .addComponent(fathersNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel11)
                                .addComponent(surnameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(activeLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        masterPanelLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {activeLabel, admissionYearComboBox, fathersNameTextField, nameTextField, programTypeComboBox, seatNoTextField, statusLabel, surnameTextField});

        masterPanelLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {registryDateFormattedTextField, remarksACTextField, statusComboBox});

        partRegistryPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Part Registry"));

        admissionSessionTextField.setEditable(false);
        admissionSessionTextField.setBackground(new java.awt.Color(255, 255, 255));

        jLabel15.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel15.setText("Admission Session:");

        categoryTextField.setEditable(false);
        categoryTextField.setBackground(new java.awt.Color(255, 255, 255));
        //final java.awt.Dimension d = categoryTextField.getSize();
        //categoryTextField.setPreferredSize(d);

        jLabel14.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel14.setText("Category:");

        jLabel13.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel13.setText("Active Selection:");
        jLabel13.setToolTipText("Admission List Details");

        activeAdmissionListDetailsComboBox.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        activeAdmissionListDetailsComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                activeAdmissionListDetailsComboBoxActionPerformed(evt);
            }
        });

        partsComboBox.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        jLabel16.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel16.setText("Part:");

        jLabel8.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel8.setText("Chalan No.:");

        challanNoTextField.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        challanNoTextField.setPreferredSize(new java.awt.Dimension(6, 25));

        jLabel17.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel17.setText("Amount:");

        amountTextField.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        amountTextField.setPreferredSize(new java.awt.Dimension(6, 25));

        try {
            challanDateFormattedTextField.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##-##-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        challanDateFormattedTextField.setText("");
        challanDateFormattedTextField.setToolTipText("DD-MM-YYYY");

        jLabel18.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel18.setText("Chalan Date:");

        jLabel19.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel19.setText("Challan Type:");

        challanTypeComboBox.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        challanTypeComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Admission", "Retain", "Refund" }));

        jScrollPane1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        partRegistryTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "SNo.", "Program", "Session", "Challan No.", "Amount", "Date", "Type", "Part", "Remarks"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Object.class, java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.String.class
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
        partRegistryTable.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        partRegistryTable.setRowHeight(20);
        partRegistryTable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        partRegistryTable.getTableHeader().setReorderingAllowed(false);
        partRegistryTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                partRegistryTableMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(partRegistryTable);
        if (partRegistryTable.getColumnModel().getColumnCount() > 0) {
            partRegistryTable.getColumnModel().getColumn(0).setResizable(false);
            partRegistryTable.getColumnModel().getColumn(0).setPreferredWidth(40);
            partRegistryTable.getColumnModel().getColumn(1).setPreferredWidth(250);
            partRegistryTable.getColumnModel().getColumn(2).setPreferredWidth(180);
            partRegistryTable.getColumnModel().getColumn(3).setResizable(false);
            partRegistryTable.getColumnModel().getColumn(3).setPreferredWidth(70);
            partRegistryTable.getColumnModel().getColumn(4).setResizable(false);
            partRegistryTable.getColumnModel().getColumn(4).setPreferredWidth(70);
            partRegistryTable.getColumnModel().getColumn(5).setResizable(false);
            partRegistryTable.getColumnModel().getColumn(5).setPreferredWidth(75);
            partRegistryTable.getColumnModel().getColumn(6).setResizable(false);
            partRegistryTable.getColumnModel().getColumn(6).setPreferredWidth(70);
            partRegistryTable.getColumnModel().getColumn(7).setResizable(false);
            partRegistryTable.getColumnModel().getColumn(7).setPreferredWidth(70);
            partRegistryTable.getColumnModel().getColumn(8).setPreferredWidth(500);
        }

        jLabel21.setText("Total Amount:");

        totalAmountTextField.setEditable(false);
        totalAmountTextField.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        totalAmountTextField.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        totalAmountTextField.setText("0");

        jLabel22.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel22.setText("Remarks:");

        buttonsPanel1.setPreferredSize(new java.awt.Dimension(335, 65));
        buttonsPanel1.setLayout(new java.awt.BorderLayout());

        javax.swing.GroupLayout partRegistryPanelLayout = new javax.swing.GroupLayout(partRegistryPanel);
        partRegistryPanel.setLayout(partRegistryPanelLayout);
        partRegistryPanelLayout.setHorizontalGroup(
            partRegistryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, partRegistryPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(partRegistryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jSeparator1)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 934, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, partRegistryPanelLayout.createSequentialGroup()
                        .addGroup(partRegistryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel13)
                            .addComponent(activeAdmissionListDetailsComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 345, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(partRegistryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(categoryTextField)
                            .addComponent(jLabel14))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(partRegistryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel15)
                            .addComponent(admissionSessionTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(partRegistryPanelLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel21)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(totalAmountTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, partRegistryPanelLayout.createSequentialGroup()
                        .addGroup(partRegistryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(partsComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel16))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(partRegistryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(challanNoTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(partRegistryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(amountTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel17))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(partRegistryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(challanDateFormattedTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(partRegistryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel19)
                            .addComponent(challanTypeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(partRegistryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel22)
                            .addComponent(remarksPRTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonsPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        partRegistryPanelLayout.setVerticalGroup(
            partRegistryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, partRegistryPanelLayout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addGroup(partRegistryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel15))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(partRegistryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(categoryTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(activeAdmissionListDetailsComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(admissionSessionTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(partRegistryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(partRegistryPanelLayout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addGroup(partRegistryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel16)
                            .addComponent(jLabel8)
                            .addComponent(jLabel17)
                            .addComponent(jLabel18)
                            .addComponent(jLabel19)
                            .addComponent(jLabel22))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(partRegistryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(partsComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(challanNoTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(amountTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(challanDateFormattedTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(challanTypeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(remarksPRTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, partRegistryPanelLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonsPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(partRegistryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(totalAmountTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jLabel21, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        partRegistryPanelLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {activeAdmissionListDetailsComboBox, admissionSessionTextField, amountTextField, categoryTextField, challanDateFormattedTextField, challanNoTextField, challanTypeComboBox, partsComboBox, remarksPRTextField});

        final java.awt.Dimension d = remarksPRTextField.getPreferredSize();
        remarksPRTextField.setPreferredSize(d);

        titlePanel.setLayout(new java.awt.BorderLayout());

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Emblem-Money-40.png"))); // NOI18N
        jLabel1.setText("Account");
        jLabel1.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, java.awt.Color.darkGray), javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5)));
        titlePanel.add(jLabel1, java.awt.BorderLayout.CENTER);

        jButton1.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jButton1.setText("Close");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(titlePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(masterPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(partRegistryPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jSeparator2)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(titlePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(masterPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(partRegistryPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void seatNoTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_seatNoTextFieldActionPerformed
        // TODO add your handling code here:
        ContainerController.setContainerEnabled(partRegistryPanel, false);
        clearAccount();
        clearPartRegistry();
        candidate = null;
        account = null;
        AdmissionYear ay = (AdmissionYear) this.admissionYearComboBox.getSelectedItem();
        ProgramType pt = (ProgramType) this.programTypeComboBox.getSelectedItem();
        if (ay == null || pt == null) {
            return;
        }

        String seatNo = this.seatNoTextField.getText();
        if (seatNo.isEmpty()) {
            return;
        }

        candidate = DatabaseManager.getCandidate(ay.getAdmissionYearId(), pt.getProgramTypeId(), seatNo);
        if (candidate == null) {
            admission.utils.MessageBox.info(this, "Not found");
            return;
        }

        nameTextField.setText(candidate.getName());
        fathersNameTextField.setText(candidate.getFathersName());
        surnameTextField.setText(candidate.getSurname());

//        partRegistyButton.setEnabled(false);
        account = DatabaseManager.getSingleRecord(Accounts.class, "candidate.candidateId = " + candidate.getCandidateId());
        if (account == null) {
            activeLabel.setText("");
            statusLabel.setText("Not Registered");
            statusLabel.setForeground(new java.awt.Color(255, 0, 0));
            return;
        }
        ContainerController.setContainerEnabled(partRegistryPanel, true);
        statusLabel.setText("Registered");
        statusLabel.setForeground(new java.awt.Color(0, 153, 0));

        registryDateFormattedTextField.setText(DateUtility.getDateToString(account.getRegDate()));
        statusComboBox.setSelectedIndex(account.isActive() ? 0 : 1);
        remarksACTextField.setText(account.getRemarks());

        if (statusComboBox.getSelectedItem().equals("Active")) {
            activeLabel.setText("Activated");
            activeLabel.setForeground(new java.awt.Color(0, 153, 0));
        } else {
            activeLabel.setText("Deactivated");
            activeLabel.setForeground(new java.awt.Color(255, 0, 0));
        }

        getActiveAdmissionListDetails();
        getPartRegistries();
    }//GEN-LAST:event_seatNoTextFieldActionPerformed

    private void activeAdmissionListDetailsComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_activeAdmissionListDetailsComboBoxActionPerformed
        // TODO add your handling code here:
        AdmissionListDetails ald = (AdmissionListDetails) activeAdmissionListDetailsComboBox.getSelectedItem();
        if (ald == null) {
            return;
        }

        categoryTextField.setText(ald.getCampusCategory().toString());
        admissionSessionTextField.setText(ald.getAdmissionList().getAdmissionSession() + " - " + ald.getCampusCategory().getShift() + " (" + ald.getAdmissionList() + ")");
    }//GEN-LAST:event_activeAdmissionListDetailsComboBoxActionPerformed

    private void partRegistryTableMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_partRegistryTableMousePressed
        // TODO add your handling code here:
        setSelectedPartRegistry();
    }//GEN-LAST:event_partRegistryTableMousePressed

    private void statusComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_statusComboBoxActionPerformed
        // TODO add your handling code here:
        if (statusComboBox.getSelectedItem().equals("Active")) {
            activeLabel.setText("Activated");
            activeLabel.setForeground(new java.awt.Color(0, 153, 0));
        } else {
            activeLabel.setText("Deactivated");
            activeLabel.setForeground(new java.awt.Color(255, 0, 0));
        }
    }//GEN-LAST:event_statusComboBoxActionPerformed

    private void programTypeComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_programTypeComboBoxActionPerformed
        // TODO add your handling code here:
        this.getParts();
    }//GEN-LAST:event_programTypeComboBoxActionPerformed

    private void saveAccountButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveAccountButtonActionPerformed
        // TODO add your handling code here:
        this.saveAccount();
    }//GEN-LAST:event_saveAccountButtonActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        setVisible(false);
    }//GEN-LAST:event_jButton1ActionPerformed

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

    private void getActiveAdmissionListDetails() {
        nameTextField.setText(account.getCandidate().getName());

        activeAdmissionListDetailsComboBox.removeAllItems();

        List<AdmissionListDetails> aldList = DatabaseManager.getData(AdmissionListDetails.class, "candidate.candidateId = " + candidate.getCandidateId() + " AND active = true", "admissionListDetailsId");
        for (AdmissionListDetails ald : aldList) {
            activeAdmissionListDetailsComboBox.addItem(ald);
        }
    }

    private void getParts() {
        partsComboBox.removeAllItems();

        ProgramType pt = (ProgramType) programTypeComboBox.getSelectedItem();
        if (pt == null) {
            return;
        }

        List<Part> list = DatabaseManager.getParts(pt.getProgramTypeId());

        for (Part part : list) {
            partsComboBox.addItem(part);
        }
    }

    private void getPartRegistries() {
        admission.utils.Utility.removeTableRows(this.defaultTableModel);

        if (account == null) {
            return;
        }
        Set set = account.getPartRegistries();
        if (set == null) {
            return;
        }

        if (statusComboBox.getSelectedItem().equals("Active")) {
            ContainerController.setContainerEnabled(partRegistryPanel, true);
        } else {
            ContainerController.setContainerEnabled(partRegistryPanel, false);
        }

        int amount = 0;
        int i = 1;
        for (Iterator it = set.iterator(); it.hasNext(); i++) {
            PartRegistry pr = (PartRegistry) it.next();

            AdmissionListDetails ald = pr.getAdmissionListDetails();
            categoryTextField.setText(ald.getCampusCategory().toString());
            admissionSessionTextField.setText(ald.getAdmissionList().getAdmissionSession() + " - " + ald.getCampusCategory().getShift() + " (" + ald.getAdmissionList() + ")");

            defaultTableModel.addRow(new Object[]{
                i,
                ald,
                ald.getAdmissionList().getAdmissionSession() + " - " + ald.getCampusCategory().getShift() + " (" + ald.getAdmissionList() + ")",
                pr.getChallanNo(),
                pr.getAmount(),
                DateUtility.getDateToString(pr.getChallanDate()),
                pr, // Part Registry Object
                pr.getPart(),
                pr.getRemarks()
            });
            if (pr.getType() == IConstant.Challan.REFUND) {
                amount -= pr.getAmount();
            } else if (pr.getType() == IConstant.Challan.ADMISSION) {
                amount += pr.getAmount();
            }

        }
        this.totalAmountTextField.setText(NumberFormatter.format(amount) + "/=");
    }

    private void addAccount() {
        if (candidate == null) {
            admission.utils.MessageBox.info(this, "Candidate not found");
            return;
        }
        if (account != null) {
            admission.utils.MessageBox.info(this, "Candidate already registered");
            return;
        }
        boolean active = (statusComboBox.getSelectedIndex() == 0);
        String remarks = remarksACTextField.getText();
        Date regDate = new Date();

        Accounts account = new Accounts(candidate, regDate, active, remarks, null);

        try {
            Integer accountId = DatabaseManager.addData(account);
            if (accountId > 0) {
                admission.utils.MessageBox.info(this, "Record added successfully");
                account.setAccountId(accountId);
                DatabaseManager.refresh(candidate);
                this.account = account;

                ContainerController.setContainerEnabled(partRegistryPanel, true);
                getActiveAdmissionListDetails();
                getPartRegistries();

                statusLabel.setText("Registered");
                statusLabel.setForeground(new java.awt.Color(0, 153, 0));
                activeLabel.setText("Active");
                activeLabel.setForeground(new java.awt.Color(0, 153, 0));
            }
        } catch (HibernateException he) {
            admission.utils.MessageBox.error(this, he);
        }
    }

    private void saveAccount() {
        if (candidate == null) {
            admission.utils.MessageBox.info(this, "Candidate not found");
            return;
        }
        if (account == null) {
            account = new Accounts();
        }
        boolean active = (statusComboBox.getSelectedIndex() == 0);
        String remarks = remarksACTextField.getText();
        Date regDate = new Date();

        account.setCandidate(candidate);
        account.setActive(active);
        account.setRegDate(regDate);
        account.setRemarks(remarks);

        try {
            DatabaseManager.save(account);

            admission.utils.MessageBox.info(this, MessageEnum.MSG_SAVE);
            DatabaseManager.refresh(account);
            DatabaseManager.refresh(candidate);

            ContainerController.setContainerEnabled(partRegistryPanel, true);
            getActiveAdmissionListDetails();
            getPartRegistries();

            statusLabel.setText("Registered");
            statusLabel.setForeground(new java.awt.Color(0, 153, 0));
//            activeLabel.setText("Active");
//            activeLabel.setForeground(new java.awt.Color(0, 153, 0));

        } catch (HibernateException he) {
            admission.utils.MessageBox.error(this, he);
        }
    }

    private void savePartRegistry() {
        int row = partRegistryTable.getSelectedRow();
        if (row < 0) {
            return;
        }

        PartRegistry pr = (PartRegistry) partRegistryTable.getValueAt(row, 6);
        AdmissionListDetails ald = (AdmissionListDetails) activeAdmissionListDetailsComboBox.getSelectedItem();
        Part part = (Part) partsComboBox.getSelectedItem();
        if (ald == null || part == null) {
            return;
        }

        if (pr == null) {
            pr = new PartRegistry();
        }

        Integer challanNo = Integer.parseInt(this.challanNoTextField.getText());
        Integer amount = Integer.parseInt(this.amountTextField.getText());
        String challanDate = challanDateFormattedTextField.getText();
        Integer type = challanTypeComboBox.getSelectedIndex();
        String remarks = remarksPRTextField.getText();

        Date stringToDate = DateUtility.getStringToDate(challanDate);

        pr.setAccounts(account);
        pr.setAdmissionListDetails(ald);
        pr.setPart(part);
        pr.setChallanNo(challanNo);
        pr.setAmount(amount);
        pr.setChallanDate(stringToDate);
        pr.setType(type);
        pr.setRemarks(remarks);

        try {
            DatabaseManager.save(pr);
            admission.utils.MessageBox.info(this, MessageEnum.MSG_SAVE);
            DatabaseManager.refresh(account);
            DatabaseManager.refresh(ald);
            clearPartRegistry();
            this.getPartRegistries();
        } catch (HibernateException he) {
            admission.utils.MessageBox.error(this, he);
        }
    }

    private void deletePartRegistry() {
        int row = partRegistryTable.getSelectedRow();
        if (row < 0) {
            return;
        }

        PartRegistry pr = (PartRegistry) partRegistryTable.getValueAt(row, 6);
        AdmissionListDetails ald = (AdmissionListDetails) activeAdmissionListDetailsComboBox.getSelectedItem();
        Part part = (Part) partsComboBox.getSelectedItem();
        if (pr == null || ald == null || part == null) {
            return;
        }

        try {
            DatabaseManager.deleteData(PartRegistry.class.getName(), "partRegistryId=" + pr.getPartRegistryId());
            admission.utils.MessageBox.info(this, MessageEnum.MSG_DELETE);
            DatabaseManager.refresh(account);
            DatabaseManager.refresh(ald);
            clearPartRegistry();
            this.getPartRegistries();
        } catch (HibernateException he) {
            admission.utils.MessageBox.error(this, he);
        }
    }

    private void setSelectedPartRegistry() {
        int row = partRegistryTable.getSelectedRow();
        if (row < 0) {
            return;
        }

        PartRegistry pr = (PartRegistry) partRegistryTable.getValueAt(row, 6);
        if (pr == null) {
            return;
        }

//        activeAdmissionListDetailsComboBox.setSelectedItem(pr.getAdmissionListDetails());
        partsComboBox.setSelectedItem(pr.getPart());
        challanNoTextField.setText(String.valueOf(pr.getChallanNo()));
        amountTextField.setText(String.valueOf(pr.getAmount()));
        challanDateFormattedTextField.setText(DateUtility.getDateToString(pr.getChallanDate()));
        challanTypeComboBox.setSelectedIndex(pr.getType());
        remarksPRTextField.setText(pr.getRemarks());
    }

//    private void setPartRegistryComponentEnabled(boolean b) {
//        this.activeAdmissionListDetailsComboBox.setEnabled(b);
//        this.partsComboBox.setEnabled(b);
//        this.amountTextField.setEnabled(b);
//        this.challanNoTextField.setEnabled(b);
//        this.challanDateFormattedTextField.setEnabled(b);
//        this.totalAmountTextField.setEnabled(b);
//        this.categoryTextField.setEnabled(b);
//        this.admissionSessionTextField.setEnabled(b);
//        this.remarksPRTextField.setEnabled(b);
//        this.challanTypeComboBox.setEnabled(b);
//        this.partRegistryTable.setEnabled(b);
//        this.jScrollPane1.setEnabled(b);
////        this.addPRButton.setEnabled(b);
////        this.updatePRButton.setEnabled(b);
////        this.deletePRButton.setEnabled(b);
//        utilities.Utilities.removeTableRows(defaultTableModel);
//    }
    private void clearAccount() {
        admission.utils.Utility.removeTableRows(defaultTableModel);

//        this.seatNoTextField.setText("");
        this.remarksACTextField.setText("");
        this.statusComboBox.setSelectedIndex(0);
        this.statusLabel.setText("Not Registered");
        this.statusLabel.setForeground(new java.awt.Color(255, 0, 0));
        this.registryDateFormattedTextField.setText(DateUtility.getDateToString(new java.util.Date()));
        this.activeAdmissionListDetailsComboBox.removeAllItems();
        this.categoryTextField.setText("");
        this.admissionSessionTextField.setText("");
        ContainerController.setContainerEnabled(partRegistryPanel, false);
//        setPartRegistryComponentEnabled(false);
    }

    private void clear() {
        this.seatNoTextField.setText("");
        this.nameTextField.setText("");
        this.fathersNameTextField.setText("");
        this.surnameTextField.setText("");
        clearAccount();
        clearPartRegistry();
        seatNoTextField.requestFocus();
    }

    private void clearPartRegistry() {
        this.amountTextField.setText("");
        this.challanNoTextField.setText("");
        this.challanDateFormattedTextField.setText(DateUtility.getDateToString(new Date()));
        this.totalAmountTextField.setText("0");
        this.remarksPRTextField.setText("");
        admission.utils.Utility.removeTableRows(defaultTableModel);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox activeAdmissionListDetailsComboBox;
    private javax.swing.JLabel activeLabel;
    private javax.swing.JTextField admissionSessionTextField;
    private javax.swing.JComboBox admissionYearComboBox;
    private javax.swing.JTextField amountTextField;
    private javax.swing.JPanel buttonsPanel1;
    private javax.swing.JTextField categoryTextField;
    private javax.swing.JFormattedTextField challanDateFormattedTextField;
    private javax.swing.JTextField challanNoTextField;
    private javax.swing.JComboBox challanTypeComboBox;
    private javax.swing.JTextField fathersNameTextField;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JPanel masterPanel;
    private javax.swing.JTextField nameTextField;
    private javax.swing.JPanel partRegistryPanel;
    private javax.swing.JTable partRegistryTable;
    private javax.swing.JComboBox partsComboBox;
    private javax.swing.JComboBox programTypeComboBox;
    private javax.swing.JFormattedTextField registryDateFormattedTextField;
    private javax.swing.JTextField remarksACTextField;
    private javax.swing.JTextField remarksPRTextField;
    private javax.swing.JButton saveAccountButton;
    private javax.swing.JTextField seatNoTextField;
    private javax.swing.JComboBox statusComboBox;
    private javax.swing.JLabel statusLabel;
    private javax.swing.JTextField surnameTextField;
    private javax.swing.JPanel titlePanel;
    private javax.swing.JTextField totalAmountTextField;
    // End of variables declaration//GEN-END:variables
    private DocumentFilter documentFilter;
    private DocumentFilter numberDocumentFilter;
    private Resources privileges;
    private Accounts account;
    private Candidate candidate;
    private DefaultTableModel defaultTableModel;
}
