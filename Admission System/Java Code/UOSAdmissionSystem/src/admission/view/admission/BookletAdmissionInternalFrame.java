package admission.view.admission;

import admission.view.seats.DefineSeatDistributionDialog;
import admission.controller.DatabaseManager;
import admission.controller.JDBCDatabaseManager;
import admission.enums.MessageEnum;
import admission.enums.SelectionStatusEnum;
import admission.model.AdmissionList;
import admission.model.AdmissionListDetails;
import admission.model.AdmissionYear;
import admission.model.Campus;
import admission.model.CampusCategory;
import admission.model.Candidate;
import admission.model.CandidateProgramOfStudy;
import admission.model.CposGroup;
import admission.model.security.Resources;
import admission.model.ProgramType;
import admission.model.Shift;
import com.yog.component.OperationButtons;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.sql.SQLException;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;
import javax.swing.text.AbstractDocument;
import javax.swing.text.DocumentFilter;
import org.hibernate.HibernateException;
import admission.utils.CandidateHelper;
import admission.utils.NumberFormatter;
import admission.utils.RoundedBorder;

/**
 *
 * @author Raja Kumar & Jay Kumar
 */
public class BookletAdmissionInternalFrame extends javax.swing.JInternalFrame {

    public BookletAdmissionInternalFrame() {
        initComponents();

        admission.utils.Utility.hideOnEscape(this);
        this.titlePanel.setBorder(RoundedBorder.createGradientBorder());
        this.buttonsPanel.add(this.getOperationButtons());

        final Dimension size = cposgComboBox.getPreferredSize();
        cposgComboBox.setPreferredSize(size);
        admission.utils.Utility.comboBoxScroll(cposgComboBox);
        admission.utils.Utility.comboBoxScroll(campusCategoryComboBox);
        admission.utils.Utility.comboBoxScroll(choicesComboBox);

        defineSeatDistributionDialog = new DefineSeatDistributionDialog(this);
        documentFilter = new admission.utils.UppercaseDocumentFilter();
        numberDocumentFilter = new admission.utils.NumberDocumentFilter();
        ((AbstractDocument) this.remarksTextArea.getDocument()).setDocumentFilter(documentFilter);
        ((AbstractDocument) this.seatNoTextField.getDocument()).setDocumentFilter(numberDocumentFilter);
        ((AbstractDocument) this.rollNoTextField.getDocument()).setDocumentFilter(numberDocumentFilter);
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
    
    private JPanel getOperationButtons() {
        operationButtons = new OperationButtons(){
            @Override public void saveOperation(ActionEvent evt) {
                BookletAdmissionInternalFrame.this.save();
            }
            @Override public void deleteOperation(ActionEvent evt) {
                BookletAdmissionInternalFrame.this.delete();
            }
            @Override public void newOperation(ActionEvent evt) {
                BookletAdmissionInternalFrame.this.clear();
            }
            @Override public void backOperation(ActionEvent evt) {
                BookletAdmissionInternalFrame.this.setVisible(false);
            }
        };
//        operationButtons.setVisible(true, privileges., isSelected, closable, isIcon);
        operationButtons.setSize(335, 60);
        return operationButtons;
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        admissionYearComboBox = new javax.swing.JComboBox();
        programTypeComboBox = new javax.swing.JComboBox();
        seatNoTextField = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        nameTextField = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        fathersNameTextField = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        surnameTextField = new javax.swing.JTextField();
        campusComboBox = new javax.swing.JComboBox();
        jLabel12 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        shiftComboBox = new javax.swing.JComboBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        aldList = new javax.swing.JList();
        jLabel13 = new javax.swing.JLabel();
        choicesComboBox = new javax.swing.JComboBox();
        jLabel14 = new javax.swing.JLabel();
        cposgComboBox = new javax.swing.JComboBox();
        jLabel15 = new javax.swing.JLabel();
        campusCategoryComboBox = new javax.swing.JComboBox();
        jLabel16 = new javax.swing.JLabel();
        totalSeatsTextField = new javax.swing.JTextField();
        consumedSeatsTextField = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        admissionListComboBox = new javax.swing.JComboBox();
        jLabel19 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        remarksTextArea = new javax.swing.JTextArea();
        jSeparator1 = new javax.swing.JSeparator();
        idTextField = new javax.swing.JTextField();
        titlePanel = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        buttonsPanel = new javax.swing.JPanel();
        defineButton = new javax.swing.JButton();
        jLabel20 = new javax.swing.JLabel();
        percentageTextField = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        testScoreTextField = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        rollNoTextField = new javax.swing.JTextField();

        setClosable(true);
        setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);
        setIconifiable(true);
        setTitle("Booklet Admission");

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
        jLabel10.setText("Fathers Name:");

        fathersNameTextField.setEditable(false);
        fathersNameTextField.setBackground(new java.awt.Color(255, 255, 255));

        jLabel11.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel11.setText("Surname:");

        surnameTextField.setEditable(false);
        surnameTextField.setBackground(new java.awt.Color(255, 255, 255));

        campusComboBox.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        campusComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                campusComboBoxActionPerformed(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel12.setText("Campus:");

        jLabel8.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel8.setText("Shift:");

        shiftComboBox.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        shiftComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                shiftComboBoxActionPerformed(evt);
            }
        });

        aldList.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                aldListValueChanged(evt);
            }
        });
        jScrollPane1.setViewportView(aldList);

        jLabel13.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel13.setText("Candidate Choices:");

        choicesComboBox.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        jLabel14.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel14.setText("Subject:");

        cposgComboBox.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        cposgComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cposgComboBoxActionPerformed(evt);
            }
        });

        jLabel15.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel15.setText("Category:");

        campusCategoryComboBox.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        jLabel16.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel16.setText("Total Seats:");

        totalSeatsTextField.setEditable(false);
        totalSeatsTextField.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        consumedSeatsTextField.setEditable(false);
        consumedSeatsTextField.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel17.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel17.setText("Consumed:");

        jLabel18.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel18.setText("Admission List:");

        admissionListComboBox.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        jLabel19.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel19.setText("Remarks:");

        remarksTextArea.setLineWrap(true);
        remarksTextArea.setWrapStyleWord(true);
        jScrollPane2.setViewportView(remarksTextArea);

        idTextField.setEditable(false);

        titlePanel.setLayout(new java.awt.BorderLayout());

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/admission/view/images/form-icon.png"))); // NOI18N
        jLabel2.setText("Booklet Admission");
        jLabel2.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, java.awt.Color.darkGray), javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5)));
        titlePanel.add(jLabel2, java.awt.BorderLayout.CENTER);

        buttonsPanel.setPreferredSize(new java.awt.Dimension(335, 60));
        buttonsPanel.setLayout(new java.awt.BorderLayout());

        defineButton.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        defineButton.setText("Define");
        defineButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                defineButtonActionPerformed(evt);
            }
        });

        jLabel20.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel20.setText("Percentage:");

        percentageTextField.setEditable(false);
        percentageTextField.setBackground(new java.awt.Color(255, 255, 255));
        percentageTextField.setHorizontalAlignment(javax.swing.JTextField.TRAILING);

        jLabel21.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel21.setText("Test Score:");

        testScoreTextField.setEditable(false);
        testScoreTextField.setBackground(new java.awt.Color(255, 255, 255));
        testScoreTextField.setHorizontalAlignment(javax.swing.JTextField.TRAILING);

        jLabel22.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel22.setText("Roll No.:");

        rollNoTextField.setHorizontalAlignment(javax.swing.JTextField.TRAILING);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(titlePanel, javax.swing.GroupLayout.PREFERRED_SIZE, 707, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(buttonsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 687, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(10, 10, 10)
                            .addComponent(admissionYearComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(10, 10, 10)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(10, 10, 10)
                            .addComponent(programTypeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(10, 10, 10)
                            .addComponent(campusComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(10, 10, 10)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(10, 10, 10)
                            .addComponent(shiftComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(10, 10, 10)
                            .addComponent(cposgComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 351, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(10, 10, 10)
                            .addComponent(totalSeatsTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(4, 4, 4)
                            .addComponent(jLabel17)
                            .addGap(4, 4, 4)
                            .addComponent(consumedSeatsTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(6, 6, 6)
                            .addComponent(defineButton))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(10, 10, 10)
                            .addComponent(campusCategoryComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 351, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(10, 10, 10)
                            .addComponent(admissionListComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(10, 10, 10)
                            .addComponent(seatNoTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(4, 4, 4)
                            .addComponent(jLabel21)
                            .addGap(4, 4, 4)
                            .addComponent(testScoreTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(4, 4, 4)
                            .addComponent(jLabel20)
                            .addGap(4, 4, 4)
                            .addComponent(percentageTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(10, 10, 10)
                            .addComponent(nameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 351, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(10, 10, 10)
                            .addComponent(fathersNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 351, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(10, 10, 10)
                            .addComponent(surnameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(4, 4, 4)
                            .addComponent(jLabel22)
                            .addGap(4, 4, 4)
                            .addComponent(rollNoTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(37, 37, 37)
                            .addComponent(idTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel13)
                            .addGap(10, 10, 10)
                            .addComponent(choicesComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 351, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jSeparator1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(titlePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addComponent(buttonsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(admissionYearComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(programTypeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(3, 3, 3)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel6))))
                        .addGap(6, 6, 6)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(campusComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(shiftComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(3, 3, 3)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel12)
                                    .addComponent(jLabel8))))
                        .addGap(6, 6, 6)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(3, 3, 3)
                                .addComponent(jLabel14))
                            .addComponent(cposgComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(6, 6, 6)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(4, 4, 4)
                                .addComponent(jLabel16))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(2, 2, 2)
                                .addComponent(totalSeatsTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(4, 4, 4)
                                .addComponent(jLabel17))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(2, 2, 2)
                                .addComponent(consumedSeatsTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(defineButton))
                        .addGap(6, 6, 6)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(3, 3, 3)
                                .addComponent(jLabel15))
                            .addComponent(campusCategoryComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(6, 6, 6)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(3, 3, 3)
                                .addComponent(jLabel18))
                            .addComponent(admissionListComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(seatNoTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(testScoreTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(percentageTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(3, 3, 3)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel21)
                                    .addComponent(jLabel20))))
                        .addGap(6, 6, 6)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(3, 3, 3)
                                .addComponent(jLabel9))
                            .addComponent(nameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(6, 6, 6)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(3, 3, 3)
                                .addComponent(jLabel10))
                            .addComponent(fathersNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(6, 6, 6)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(surnameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(rollNoTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(idTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(3, 3, 3)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel11)
                                    .addComponent(jLabel22))))
                        .addGap(6, 6, 6)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(3, 3, 3)
                                .addComponent(jLabel13))
                            .addComponent(choicesComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(6, 6, 6)
                        .addComponent(jLabel19)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 43, Short.MAX_VALUE))
                    .addComponent(jScrollPane1))
                .addContainerGap())
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {admissionListComboBox, admissionYearComboBox, campusCategoryComboBox, campusComboBox, choicesComboBox, consumedSeatsTextField, cposgComboBox, fathersNameTextField, idTextField, nameTextField, percentageTextField, programTypeComboBox, rollNoTextField, seatNoTextField, shiftComboBox, surnameTextField, testScoreTextField, totalSeatsTextField});

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void seatNoTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_seatNoTextFieldActionPerformed
        // TODO add your handling code here:
        this.candidate = null;
        aldList.setListData(new Object[]{});
        choicesComboBox.removeAllItems();

        AdmissionYear ay = (AdmissionYear) this.admissionYearComboBox.getSelectedItem();
        ProgramType pt = (ProgramType) this.programTypeComboBox.getSelectedItem();
        if (ay == null || pt == null) {
            return;
        }

        String seatNo = this.seatNoTextField.getText();
        if (seatNo.isEmpty()) {
            return;
        }

        this.candidate = DatabaseManager.getCandidate(ay.getAdmissionYearId(), pt.getProgramTypeId(), seatNo);
        if (this.candidate == null) {
            admission.utils.MessageBox.info(this, "Candidate not found");
            return;
        }
        nameTextField.setText(candidate.getName());
        fathersNameTextField.setText(candidate.getFathersName());
        surnameTextField.setText(candidate.getSurname());
        testScoreTextField.setText(String.valueOf(candidate.getTestScore()));
        percentageTextField.setText(NumberFormatter.format(candidate.getPercentage()));
        getChoices();

        List<AdmissionListDetails> aldList = DatabaseManager.getData(AdmissionListDetails.class, "candidate.candidateId = " + candidate.getCandidateId(), "admissionListDetailsId");
        if (aldList.isEmpty()) {
            admission.utils.MessageBox.info(this, "No Selection");
            return;
        }

        this.aldList.setListData(aldList.toArray());
    }//GEN-LAST:event_seatNoTextFieldActionPerformed

    private void campusComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_campusComboBoxActionPerformed
        // TODO add your handling code here:
        this.getCposg();
        this.getCampusCategory();
        this.getAdmissionList();
        this.getChoices();
    }//GEN-LAST:event_campusComboBoxActionPerformed

    private void shiftComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_shiftComboBoxActionPerformed
        // TODO add your handling code here:
        this.getCposg();
        this.getCampusCategory();
        this.getChoices();
    }//GEN-LAST:event_shiftComboBoxActionPerformed

    private void programTypeComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_programTypeComboBoxActionPerformed
        // TODO add your handling code here:
        this.clear();
        this.getCposg();
        this.getCampusCategory();
        this.getAdmissionList();
    }//GEN-LAST:event_programTypeComboBoxActionPerformed

    private void cposgComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cposgComboBoxActionPerformed
        // TODO add your handling code here:
        totalSeatsTextField.setText("0");
        consumedSeatsTextField.setText("0");

        AdmissionYear ay = (AdmissionYear) this.admissionYearComboBox.getSelectedItem();
        CposGroup cposg = (CposGroup) cposgComboBox.getSelectedItem();
        if (cposg == null || ay == null) {
            return;
        }

        try {
            int totalSeats = JDBCDatabaseManager.getCposgSeats(ay.getAdmissionYearId(), cposg.getCposGroupId());
            int consumedSeats = JDBCDatabaseManager.getCountCandidatesOfCposGroup(ay.getAdmissionYearId(), cposg.getCposGroupId(), true);
            totalSeatsTextField.setText(String.valueOf(totalSeats));
            consumedSeatsTextField.setText(String.valueOf(consumedSeats));
        } catch (SQLException ex) {
            Logger.getLogger(BookletAdmissionInternalFrame.class.getName()).log(Level.SEVERE, null, ex);
            admission.utils.MessageBox.error(this, ex);
        }
    }//GEN-LAST:event_cposgComboBoxActionPerformed

    private void aldListValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_aldListValueChanged
        // TODO add your handling code here:
        AdmissionListDetails ald = (AdmissionListDetails) aldList.getSelectedValue();
        if (ald == null) {
            return;
        }

        campusComboBox.setSelectedItem(ald.getCampusCategory().getCampus());
        shiftComboBox.setSelectedItem(ald.getCampusCategory().getShift());
        campusCategoryComboBox.setSelectedItem(ald.getCampusCategory());
        cposgComboBox.setSelectedItem(ald.getCposGroup());
        choicesComboBox.setSelectedItem(ald.getCandidateProgramOfStudy());
        idTextField.setText(String.valueOf(ald.getAdmissionListDetailsId()));
        rollNoTextField.setText(ald.getRollNo() == null ? "" : String.valueOf(ald.getRollNo()));
        remarksTextArea.setText(ald.getRemarks());
    }//GEN-LAST:event_aldListValueChanged

    private void defineButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_defineButtonActionPerformed
        // TODO add your handling code here:
        AdmissionYear ay = (AdmissionYear) this.admissionYearComboBox.getSelectedItem();
        CposGroup cposg = (CposGroup) cposgComboBox.getSelectedItem();
        if (ay == null || cposg == null) {
            admission.utils.MessageBox.info(rootPane, cposg);
            return;
        }
        defineSeatDistributionDialog.setVisible(true, ay, cposg);
    }//GEN-LAST:event_defineButtonActionPerformed

    private void getAdmissionYear() {
        this.admissionYearComboBox.removeAllItems();

        List<AdmissionYear> list = DatabaseManager.getData(AdmissionYear.class.getName(), "year DESC");
        for (AdmissionYear list1 : list) {
            this.admissionYearComboBox.addItem(list1);
        }
    }

    private void getProgramType() {
        this.programTypeComboBox.removeAllItems();

        List<ProgramType> list = DatabaseManager.getData(ProgramType.class.getName(), "name");
        for (ProgramType list1 : list) {
            this.programTypeComboBox.addItem(list1);
        }
    }

    private void getCampus() {
        this.campusComboBox.removeAllItems();
        List<Campus> list = DatabaseManager.getData(Campus.class.getName(), "displayOrder");

        for (Campus list1 : list) {
            this.campusComboBox.addItem(list1);
        }
    }

    private void getShift() {
        shiftComboBox.removeAllItems();

        List<Shift> list = DatabaseManager.getData(Shift.class.getName(), "name DESC");
        for (Shift list1 : list) {
            shiftComboBox.addItem(list1);
        }
    }

    private void getCposg() {
        cposgComboBox.removeAllItems();

        ProgramType pt = (ProgramType) programTypeComboBox.getSelectedItem();
        Campus c = (Campus) campusComboBox.getSelectedItem();
        Shift s = (Shift) shiftComboBox.getSelectedItem();
        if (pt == null || c == null || s == null) {
            return;
        }

        List<CposGroup> cposGroupList = DatabaseManager.getCampusCposGroup(c.getCampusId(), s.getShiftId(), pt.getProgramTypeId());
        for (CposGroup cposGroupList1 : cposGroupList) {
            cposgComboBox.addItem(cposGroupList1);
        }
    }

    private void getCampusCategory() {
        campusCategoryComboBox.removeAllItems();

        ProgramType pt = (ProgramType) programTypeComboBox.getSelectedItem();
        Campus c = (Campus) campusComboBox.getSelectedItem();
        Shift s = (Shift) shiftComboBox.getSelectedItem();
        if (pt == null || c == null || s == null) {
            return;
        }

        List<CampusCategory> campusCategoryList = DatabaseManager.getCampusCategory(c.getCampusId(), pt.getProgramTypeId(), s.getShiftId(), "displayOrder");
        for (CampusCategory campusCategoryList1 : campusCategoryList) {
            campusCategoryComboBox.addItem(campusCategoryList1);
        }
    }

    private void getAdmissionList() {
        admissionListComboBox.removeAllItems();

        ProgramType pt = (ProgramType) programTypeComboBox.getSelectedItem();
        Campus c = (Campus) campusComboBox.getSelectedItem();
        AdmissionYear admissionYear = (AdmissionYear) admissionYearComboBox.getSelectedItem();
        if (pt == null || c == null || admissionYear == null) {
            return;
        }

        List<AdmissionList> admissionListsList;

//        if (!(c.getIsMain())) {
            admissionListsList = DatabaseManager.getAdmissionLists(c.getCampusId(), pt.getProgramTypeId(), admissionYear.getAdmissionYearId());
//        } else {
//            admissionListsList = DatabaseManager.getMainAdmissionLists(c.getIsMain(), pt.getProgramTypeId());
//        }

        for (AdmissionList al : admissionListsList) {
//            if (al.isIsManual()) {
                admissionListComboBox.addItem(al);
//            }
        }
    }

    private void getAdmissionListDetails() {
        aldList.setListData(new Object[]{});
        if (candidate == null) {
            return;
        }

        List<AdmissionListDetails> aldList = DatabaseManager.getData(AdmissionListDetails.class, "candidate.candidateId = " + candidate.getCandidateId(), "admissionListDetailsId");
        if (aldList.isEmpty()) {
            admission.utils.MessageBox.info(this, "No Selection");
            return;
        }

        this.aldList.setListData(aldList.toArray());
    }

    private void getChoices() {
        choicesComboBox.removeAllItems();
        if (candidate == null) {
            return;
        }

        Campus c = (Campus) campusComboBox.getSelectedItem();
        Shift s = (Shift) shiftComboBox.getSelectedItem();
        if (c == null || s == null) {
            return;
        }

        List<CandidateProgramOfStudy> cnposLst = DatabaseManager.getCandidateProgramOfStudy(c.getCampusId(), s.getShiftId(), candidate.getCandidateId());
        for (Object toArray : cnposLst) {
            choicesComboBox.addItem(toArray);
        }
    }

    private void save() {
        if (candidate == null) {
            admission.utils.MessageBox.info(this, MessageEnum.MSG_17);
            return;
        }
        if (candidate.getTestScore() == null || candidate.getTestScore() <= 0) {
            admission.utils.MessageBox.info(this, "Test score not good: " + candidate.getTestScore());
            return;
        }

        AdmissionList al = (AdmissionList) admissionListComboBox.getSelectedItem();
        CposGroup cposg = (CposGroup) cposgComboBox.getSelectedItem();
        CandidateProgramOfStudy cnpos = (CandidateProgramOfStudy) choicesComboBox.getSelectedItem();
        CampusCategory cc = (CampusCategory) campusCategoryComboBox.getSelectedItem();
        if (al == null || cposg == null || cnpos == null || cc == null) {
            return;
        }

        AdmissionListDetails ald = (AdmissionListDetails) aldList.getSelectedValue();
        if (ald == null) {
            ald = new AdmissionListDetails();
            ald.setStatus(SelectionStatusEnum.SELECTED);
            ald.setActive(false);
        }

        if (!cposg.getCampusProgramOfStudy().equals(cnpos.getCampusProgramOfStudy())) {
            admission.utils.MessageBox.info(this, "Subject and choice not same");
            return;
        }

        Integer rollNo = rollNoTextField.getText().isEmpty() ? null : Integer.parseInt(rollNoTextField.getText());
        String remarks = remarksTextArea.getText();

        ald.setCandidate(candidate);
        ald.setAdmissionList(al);
        ald.setCposGroup(cposg);
        ald.setCandidateProgramOfStudy(cnpos);
        ald.setCampusCategory(cc);
        ald.setRollNo(rollNo);
        ald.setRemarks(remarks);
        try {
            DatabaseManager.save(ald);
            DatabaseManager.refresh(candidate);
            DatabaseManager.refresh(al);
            DatabaseManager.refresh(cposg);
            DatabaseManager.refresh(cnpos);
            DatabaseManager.refresh(cc);
            admission.utils.MessageBox.info(this, MessageEnum.MSG_SAVE);
        } catch (HibernateException ex) {
            Logger.getLogger(BookletAdmissionInternalFrame.class.getName()).log(Level.SEVERE, null, ex);
            admission.utils.MessageBox.error(this, ex);
        }
    }

    private void delete() {
        AdmissionListDetails ald = (AdmissionListDetails) aldList.getSelectedValue();
        CposGroup cposg = (CposGroup) cposgComboBox.getSelectedItem();
        if (ald == null || cposg == null) {
            return;
        }

        try {
            int row = DatabaseManager.deleteData(AdmissionListDetails.class.getName(), "admissionListDetailsId = " + ald.getAdmissionListDetailsId());
            if (row > 0) {
                DatabaseManager.refresh(candidate);
                DatabaseManager.refresh(cposg);
                admission.utils.MessageBox.info(this, MessageEnum.MSG_DELETE);
                getAdmissionListDetails();
            }
        } catch (HibernateException ex) {
            Logger.getLogger(BookletAdmissionInternalFrame.class.getName()).log(Level.SEVERE, null, ex);
            admission.utils.MessageBox.error(this, ex);
        }
    }

    private void clear() {
        seatNoTextField.setText("");
        nameTextField.setText("");
        fathersNameTextField.setText("");
        surnameTextField.setText("");
        idTextField.setText("");
        remarksTextArea.setText("");
        aldList.setListData(new Object[]{});
        choicesComboBox.removeAllItems();
        this.seatNoTextField.requestFocus();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox admissionListComboBox;
    private javax.swing.JComboBox admissionYearComboBox;
    private javax.swing.JList aldList;
    private javax.swing.JPanel buttonsPanel;
    private javax.swing.JComboBox campusCategoryComboBox;
    private javax.swing.JComboBox campusComboBox;
    private javax.swing.JComboBox choicesComboBox;
    private javax.swing.JTextField consumedSeatsTextField;
    private javax.swing.JComboBox cposgComboBox;
    private javax.swing.JButton defineButton;
    private javax.swing.JTextField fathersNameTextField;
    private javax.swing.JTextField idTextField;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextField nameTextField;
    private javax.swing.JTextField percentageTextField;
    private javax.swing.JComboBox programTypeComboBox;
    private javax.swing.JTextArea remarksTextArea;
    private javax.swing.JTextField rollNoTextField;
    private javax.swing.JTextField seatNoTextField;
    private javax.swing.JComboBox shiftComboBox;
    private javax.swing.JTextField surnameTextField;
    private javax.swing.JTextField testScoreTextField;
    private javax.swing.JPanel titlePanel;
    private javax.swing.JTextField totalSeatsTextField;
    // End of variables declaration//GEN-END:variables
    private DocumentFilter documentFilter;
    private DocumentFilter numberDocumentFilter;
    private Resources privileges;
    private Candidate candidate;
    private DefineSeatDistributionDialog defineSeatDistributionDialog;
    private OperationButtons operationButtons;
}
