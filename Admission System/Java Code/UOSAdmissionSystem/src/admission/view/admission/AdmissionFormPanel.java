package admission.view.admission;

import admission.utils.FormScroller;
import admission.utils.Coder;
import admission.utils.Sorter;
import admission.utils.MessageBox;
import admission.utils.ExtensionsFilter;
import admission.utils.UppercaseDocumentFilter;
import admission.utils.DeductionCalculation;
import admission.utils.IConstant;
import admission.utils.NumberDocumentFilter;
import admission.utils.RoundedBorder;
import admission.utils.ImageUtils;
import admission.model.security.Resources;
import admission.utils.CandidateHelper;
import admission.controller.DatabaseManager;
import admission.enums.AreaEnum;
import admission.enums.CategoryEnum;
import admission.enums.DetailOfEnum;
import admission.enums.GenderEnum;
import admission.enums.LevelEnum;
import admission.enums.MessageEnum;
import admission.helpers.CommonHelper;
import admission.helpers.EntityHelper;
import admission.helpers.LoggerHelper;
import admission.model.*;
import admission.model.security.Log;
import admission.services.KeyConstant;
import com.yog.component.JCustomCheckBox;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.Insets;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.logging.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.text.*;
import org.hibernate.HibernateException;
import admission.utils.DateUtility;
import admission.utils.Utility;

/**
 *
 * @author Yougeshwar
 */
public class AdmissionFormPanel extends javax.swing.JPanel {

    public AdmissionFormPanel() {
        initComponents();
        this.titlePanel.setBorder(RoundedBorder.createGradientBorder());
        FormScroller scroller = new FormScroller(jScrollPane3);
        scroller.setScrollInsets(new Insets(50, 0, 50, 0));

        documentFilter = new UppercaseDocumentFilter();
        numberDocumentFilter = new NumberDocumentFilter();
        disciplineChoicesDialog = new DisciplineChoicesDialog(this);

        this.jScrollPane3.getVerticalScrollBar().setUnitIncrement(20);
        jScrollPane3.getVerticalScrollBar().setPreferredSize(new Dimension(15, jScrollPane3.getVerticalScrollBar().getPreferredSize().height));
        ((AbstractDocument) this.candidateIdTextField.getDocument()).setDocumentFilter(numberDocumentFilter);
        ((AbstractDocument) this.seatNoTextField.getDocument()).setDocumentFilter(numberDocumentFilter);
        ((AbstractDocument) this.formNoTextField.getDocument()).setDocumentFilter(numberDocumentFilter);
        ((AbstractDocument) this.nameTextField.getDocument()).setDocumentFilter(documentFilter);
        ((AbstractDocument) this.fathersNameTextField.getDocument()).setDocumentFilter(documentFilter);
        ((AbstractDocument) this.surnameTextField.getDocument()).setDocumentFilter(documentFilter);
        ((AbstractDocument) this.placeOfBirthTextField.getDocument()).setDocumentFilter(documentFilter);
        ((AbstractDocument) this.permanentHomeAddressTextField.getDocument()).setDocumentFilter(documentFilter);
        ((AbstractDocument) this.presentPostelAddressTextField.getDocument()).setDocumentFilter(documentFilter);
        ((AbstractDocument) this.guardiansNameTextField.getDocument()).setDocumentFilter(documentFilter);
        ((AbstractDocument) this.guardiansAddressTextField.getDocument()).setDocumentFilter(documentFilter);
        ((AbstractDocument) this.sscTotalMarksTextField.getDocument()).setDocumentFilter(numberDocumentFilter);
        ((AbstractDocument) this.sscMarksObtainedTextField.getDocument()).setDocumentFilter(numberDocumentFilter);
        ((AbstractDocument) this.sscSeatNoTextField.getDocument()).setDocumentFilter(numberDocumentFilter);
        ((AbstractDocument) this.hscTotalMarksTextField.getDocument()).setDocumentFilter(numberDocumentFilter);
        ((AbstractDocument) this.hscMarksObtainedTextField.getDocument()).setDocumentFilter(numberDocumentFilter);
        ((AbstractDocument) this.hscSeatNoTextField.getDocument()).setDocumentFilter(numberDocumentFilter);
        ((AbstractDocument) this.graduationTotalMarksTextField.getDocument()).setDocumentFilter(numberDocumentFilter);
//        ((AbstractDocument) this.graduationMarksObtainedTextField.getDocument()).setDocumentFilter(new FloatDocumentFilter());
        ((AbstractDocument) this.graduationSeatNoTextField.getDocument()).setDocumentFilter(numberDocumentFilter);
        ((AbstractDocument) this.objectRemarksTextField.getDocument()).setDocumentFilter(documentFilter);
        ((AbstractDocument) this.remarksTextField.getDocument()).setDocumentFilter(numberDocumentFilter);

        Utility.comboBoxScroll(this.fathersOrGuardiansOccupationComboBox);
        Utility.comboBoxScroll(this.nationalityComboBox);
        Utility.comboBoxScroll(this.religionComboBox);
        Utility.comboBoxScroll(this.sscGroupComboBox);
        Utility.comboBoxScroll(this.sscBoardComboBox);
        Utility.comboBoxScroll(this.hscGroupComboBox);
        Utility.comboBoxScroll(this.hscBoardComboBox);
        Utility.comboBoxScroll(this.grdGroupComboBox);
        Utility.comboBoxScroll(this.grdUniComboBox);
        Utility.comboBoxScroll(this.admissionYearComboBox);
        Utility.comboBoxScroll(this.subject1ComboBox);
        Utility.comboBoxScroll(this.subject2ComboBox);
        Utility.comboBoxScroll(this.subject3ComboBox);

        Utility.loadEnum(areaComboBox, AreaEnum.class);
        Utility.loadEnum(genderComboBox, GenderEnum.class);

        Dimension size3 = new Dimension(910, 990);
        this.jPanel5.setPreferredSize(size3);

        this.forForeignPanel.setVisible(foreignQuotaCheckBox.isSelected());

        Utility.setStarkRed(this);
        this.loadCatalog();
    }

    @Override
    public void setVisible(boolean aFlag) {
        if (aFlag) {
            if (seatNoTextField != null) {
                this.seatNoTextField.requestFocus();
            }
        }
        super.setVisible(aFlag);
    }

    @Override
    public void requestFocus() {
        seatNoTextField.requestFocus();
    }

    public void setPrivileges(Resources privileges) {
        this.privileges = privileges;
//        this.addButton.setEnabled((privileges.isAddPrivilige()));
//        this.updateButton.setEnabled((privileges.isUpdatePrivilige()));
//        this.deleteButton.setEnabled((privileges.isDeletePrivilige()));
    }

    public final void loadCatalog() {
        this.getProgramType();
        this.getProgram();
        this.getAdmissionYear();
        this.getDistrict();
        this.getReligion();
        this.getCampus();
        this.getIssuer();
        this.getCountry();
        this.getSubject();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        photoDialog = new javax.swing.JDialog();
        titlePanel = new javax.swing.JPanel();
        jLabel37 = new javax.swing.JLabel();
        photoLabel = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        fileChooser = new javax.swing.JFileChooser();
        reLoadButton = new javax.swing.JButton();
        deleteButton = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        disciplineChoiceButton = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        generalMeritCheckBox = new javax.swing.JCheckBox();
        selfFinanceMorningCheckBox = new javax.swing.JCheckBox();
        selfFinanceEveningCheckBox = new javax.swing.JCheckBox();
        suEmployeesQuotaCheckBox = new javax.swing.JCheckBox();
        disabledPersonQuotaCheckBox = new javax.swing.JCheckBox();
        nominationQuotaCheckBox = new javax.swing.JCheckBox();
        affiliatedCollegeQuotaCheckBox = new javax.swing.JCheckBox();
        sportsQuotaCheckBox = new javax.swing.JCheckBox();
        foreignQuotaCheckBox = new javax.swing.JCheckBox();
        sueQuotaComboBox = new javax.swing.JComboBox();
        armyPersonalQuotaCheckBox = new javax.swing.JCheckBox();
        karachiQuotaCheckBox = new javax.swing.JCheckBox();
        jLabel41 = new javax.swing.JLabel();
        chalanDateFormattedTextField = new javax.swing.JFormattedTextField();
        formNoTextField = new javax.swing.JTextField();
        jLabel43 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        candidateIdTextField = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        seatNoTextField = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        programTypeComboBox = new javax.swing.JComboBox();
        campusesPanel = new javax.swing.JPanel();
        chalanFeeTextField = new javax.swing.JTextField();
        jLabel40 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        forForeignPanel = new javax.swing.JPanel();
        jLabel46 = new javax.swing.JLabel();
        passportNoTextField = new javax.swing.JTextField();
        jLabel47 = new javax.swing.JLabel();
        placeOfIssueTextField = new javax.swing.JTextField();
        jLabel48 = new javax.swing.JLabel();
        datedFormattedTextField = new javax.swing.JFormattedTextField();
        jLabel49 = new javax.swing.JLabel();
        visaNoTextField = new javax.swing.JTextField();
        jLabel50 = new javax.swing.JLabel();
        visaNoDatedFormattedTextField = new javax.swing.JFormattedTextField();
        jLabel51 = new javax.swing.JLabel();
        dateOfValidityFormattedTextField = new javax.swing.JFormattedTextField();
        arrivalDateinPakistanFormattedTextField = new javax.swing.JFormattedTextField();
        jLabel52 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        remarksTextField = new javax.swing.JTextField();
        admissionYearComboBox = new javax.swing.JComboBox();
        isObjectionCheckBox = new javax.swing.JCheckBox();
        objectRemarksTextField = new javax.swing.JTextField();
        jLabel35 = new javax.swing.JLabel();
        photoButton = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        sscGroupComboBox = new javax.swing.JComboBox();
        sscMarksObtainedTextField = new javax.swing.JTextField();
        sscTotalMarksTextField = new javax.swing.JTextField();
        sscSeatNoTextField = new javax.swing.JTextField();
        sscBoardComboBox = new javax.swing.JComboBox();
        hscGroupComboBox = new javax.swing.JComboBox();
        hscMarksObtainedTextField = new javax.swing.JTextField();
        hscTotalMarksTextField = new javax.swing.JTextField();
        hscSeatNoTextField = new javax.swing.JTextField();
        hscBoardComboBox = new javax.swing.JComboBox();
        grdGroupComboBox = new javax.swing.JComboBox();
        graduationTotalMarksTextField = new javax.swing.JTextField();
        graduationSeatNoTextField = new javax.swing.JTextField();
        grdUniComboBox = new javax.swing.JComboBox();
        jLabel53 = new javax.swing.JLabel();
        jLabel54 = new javax.swing.JLabel();
        jLabel55 = new javax.swing.JLabel();
        jLabel56 = new javax.swing.JLabel();
        jSeparator8 = new javax.swing.JSeparator();
        jSeparator9 = new javax.swing.JSeparator();
        jLabel57 = new javax.swing.JLabel();
        jSeparator10 = new javax.swing.JSeparator();
        jLabel58 = new javax.swing.JLabel();
        jSeparator11 = new javax.swing.JSeparator();
        jLabel59 = new javax.swing.JLabel();
        jSeparator12 = new javax.swing.JSeparator();
        jLabel60 = new javax.swing.JLabel();
        jSeparator13 = new javax.swing.JSeparator();
        jLabel61 = new javax.swing.JLabel();
        jLabel62 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        subject1ComboBox = new javax.swing.JComboBox();
        subject2ComboBox = new javax.swing.JComboBox();
        subject3ComboBox = new javax.swing.JComboBox();
        sscYearComboBox = new javax.swing.JComboBox();
        hscYearComboBox = new javax.swing.JComboBox();
        grdYearComboBox = new javax.swing.JComboBox();
        yearsDegreeComboBox = new javax.swing.JComboBox();
        grdMarksObtainedFormattedTextField = new javax.swing.JFormattedTextField();
        jPanel1 = new javax.swing.JPanel();
        presentPostelAddressTextField = new javax.swing.JTextField();
        jLabel45 = new javax.swing.JLabel();
        relationshipComboBox = new javax.swing.JComboBox();
        jLabel21 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        surnameTextField = new javax.swing.JTextField();
        fathersNameTextField = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        religionComboBox = new javax.swing.JComboBox();
        fathersOrGuardiansOccupationComboBox = new javax.swing.JComboBox();
        jLabel10 = new javax.swing.JLabel();
        guardiansAddressTextField = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        guardiansNameTextField = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        nameTextField = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        placeOfBirthTextField = new javax.swing.JTextField();
        jLabel44 = new javax.swing.JLabel();
        districtComboBox = new javax.swing.JComboBox();
        jLabel12 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        cnicFormattedTextField = new javax.swing.JFormattedTextField();
        cnicOfComboBox = new javax.swing.JComboBox();
        jLabel9 = new javax.swing.JLabel();
        dateOfBirthFormattedTextField = new javax.swing.JFormattedTextField();
        jLabel15 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        nationalityComboBox = new javax.swing.JComboBox();
        provinceTextField = new javax.swing.JTextField();
        divisionTextField = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        telephoneTextField = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        genderComboBox = new javax.swing.JComboBox();
        mobileTextField = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        emailTextField = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        permanentHomeAddressTextField = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        areaComboBox = new javax.swing.JComboBox();
        jLabel36 = new javax.swing.JLabel();
        bankNameTextField = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jLabel33 = new javax.swing.JLabel();
        testScoreTextField = new javax.swing.JTextField();
        jLabel34 = new javax.swing.JLabel();
        finalPercentageTextField = new javax.swing.JTextField();
        loggerDetailLabel = new javax.swing.JLabel();
        objectionRemarksCheckboxPanel = new javax.swing.JPanel();
        noSscMarksCheckBox = new javax.swing.JCheckBox();
        noSscPassCheckBox = new javax.swing.JCheckBox();
        noHscMarksCheckBox = new javax.swing.JCheckBox();
        noHscPassCheckBox = new javax.swing.JCheckBox();
        noGrdMarksCheckBox = new javax.swing.JCheckBox();
        noGrdPassCheckBox = new javax.swing.JCheckBox();
        noDomicileCheckBox = new javax.swing.JCheckBox();
        noAffidavitCheckBox = new javax.swing.JCheckBox();

        photoDialog.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        photoDialog.setTitle("Photo");
        photoDialog.setResizable(false);
        photoDialog.setSize(220, 370);
        photoDialog.setLocationRelativeTo(null);

        InputMap inputMap = this.photoDialog.getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        ActionMap actionMap = this.photoDialog.getRootPane().getActionMap();

        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), actionMap);
        actionMap.put(actionMap, new AbstractAction() {
            @Override public void actionPerformed(ActionEvent e) {
                photoDialog.dispose();
            }
        });

        titlePanel.setLayout(new java.awt.BorderLayout());

        jLabel37.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel37.setIcon(new javax.swing.ImageIcon(getClass().getResource("/admission/view/images/photo_booth.png"))); // NOI18N
        jLabel37.setText("Photo");
        jLabel37.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, java.awt.Color.darkGray), javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5)));
        titlePanel.add(jLabel37, java.awt.BorderLayout.CENTER);

        photoLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        photoLabel.setText("<html>No photo<br>Click to browse");
        photoLabel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        photoLabel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        photoLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                photoLabelMouseClicked(evt);
            }
        });

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/admission/view/images/Remove.png"))); // NOI18N
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/admission/view/images/Update.png"))); // NOI18N
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/admission/view/images/Back.png"))); // NOI18N
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout photoDialogLayout = new javax.swing.GroupLayout(photoDialog.getContentPane());
        photoDialog.getContentPane().setLayout(photoDialogLayout);
        photoDialogLayout.setHorizontalGroup(
            photoDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(titlePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(photoDialogLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton4)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(photoDialogLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(photoLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        photoDialogLayout.setVerticalGroup(
            photoDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(photoDialogLayout.createSequentialGroup()
                .addComponent(titlePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(photoLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(photoDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3)
                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        ExtensionsFilter ef = new ExtensionsFilter("Image files", "png", "gif", "jpg", "jpeg", "bmp");
        fileChooser.addChoosableFileFilter(ef);
        fileChooser.setFileFilter(ef);

        reLoadButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Gnome-View-Refresh-32.png"))); // NOI18N
        reLoadButton.setToolTipText("Reload");
        reLoadButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                reLoadButtonActionPerformed(evt);
            }
        });

        deleteButton.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        deleteButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/admission/view/images/Remove.png"))); // NOI18N
        deleteButton.setToolTipText("Delete");
        deleteButton.setEnabled(false);
        deleteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteButtonActionPerformed(evt);
            }
        });

        setFocusable(false);
        setLayout(new java.awt.BorderLayout());

        jScrollPane3.setAutoscrolls(true);

        jPanel6.setLayout(new java.awt.GridBagLayout());

        disciplineChoiceButton.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        disciplineChoiceButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/admission/view/images/Editor.png"))); // NOI18N
        disciplineChoiceButton.setText("Discipline Choices");
        disciplineChoiceButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                disciplineChoiceButtonActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel2.setText("Computer Code");

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Selection of admission categories*", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N

        generalMeritCheckBox.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        generalMeritCheckBox.setText("General Merit/District U/R Quota");
        generalMeritCheckBox.setName("GM_DUR_QUOTA");

        selfFinanceMorningCheckBox.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        selfFinanceMorningCheckBox.setText("Self Finance (Morning)");
        selfFinanceMorningCheckBox.setName("SFM_QUOTA");

        selfFinanceEveningCheckBox.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        selfFinanceEveningCheckBox.setText("Self Finance (Evening)");
        selfFinanceEveningCheckBox.setName("SFE_QUOTA");

        suEmployeesQuotaCheckBox.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        suEmployeesQuotaCheckBox.setText("S.U. Employees Quota");
        suEmployeesQuotaCheckBox.setName("SUE_QUOTA");
        suEmployeesQuotaCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                suEmployeesQuotaCheckBoxActionPerformed(evt);
            }
        });

        disabledPersonQuotaCheckBox.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        disabledPersonQuotaCheckBox.setText("Disabled Person Quota");
        disabledPersonQuotaCheckBox.setName("DP_QUOTA");

        nominationQuotaCheckBox.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        nominationQuotaCheckBox.setText("Nomination Quota");
        nominationQuotaCheckBox.setName("NO_QUOTA");

        affiliatedCollegeQuotaCheckBox.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        affiliatedCollegeQuotaCheckBox.setText("Affiliated College Quota");
        affiliatedCollegeQuotaCheckBox.setName("AC_QUOTA");

        sportsQuotaCheckBox.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        sportsQuotaCheckBox.setText("Sports Quota");
        sportsQuotaCheckBox.setName("SP_QUOTA");

        foreignQuotaCheckBox.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        foreignQuotaCheckBox.setText("Foreign Quota");
        foreignQuotaCheckBox.setName("FR_QUOTA");
        foreignQuotaCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                foreignQuotaCheckBoxActionPerformed(evt);
            }
        });

        sueQuotaComboBox.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        sueQuotaComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Son/Daughter", "Brother/Sister" }));
        sueQuotaComboBox.setEnabled(false);

        armyPersonalQuotaCheckBox.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        armyPersonalQuotaCheckBox.setText("Army Personal");
        armyPersonalQuotaCheckBox.setName("AP_QUOTA");

        karachiQuotaCheckBox.setText("karachi");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(generalMeritCheckBox)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(selfFinanceMorningCheckBox)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(selfFinanceEveningCheckBox)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(suEmployeesQuotaCheckBox)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(sueQuotaComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(disabledPersonQuotaCheckBox)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(nominationQuotaCheckBox)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(affiliatedCollegeQuotaCheckBox)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(sportsQuotaCheckBox)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(foreignQuotaCheckBox)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(armyPersonalQuotaCheckBox)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(karachiQuotaCheckBox)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(generalMeritCheckBox, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(selfFinanceMorningCheckBox)
                        .addComponent(selfFinanceEveningCheckBox)
                        .addComponent(suEmployeesQuotaCheckBox)
                        .addComponent(sueQuotaComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 3, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nominationQuotaCheckBox)
                    .addComponent(affiliatedCollegeQuotaCheckBox)
                    .addComponent(sportsQuotaCheckBox)
                    .addComponent(foreignQuotaCheckBox)
                    .addComponent(disabledPersonQuotaCheckBox)
                    .addComponent(armyPersonalQuotaCheckBox)
                    .addComponent(karachiQuotaCheckBox)))
        );

        jLabel41.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel41.setText("Challan date*");

        try {
            chalanDateFormattedTextField.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##-##-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        chalanDateFormattedTextField.setFocusLostBehavior(javax.swing.JFormattedTextField.COMMIT);

        formNoTextField.setHorizontalAlignment(javax.swing.JTextField.TRAILING);

        jLabel43.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel43.setText("e.g., DD-MM-YYYY");
        jLabel43.setEnabled(false);

        jLabel3.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel3.setText("T - No./Seat No.*");

        candidateIdTextField.setEditable(false);
        candidateIdTextField.setHorizontalAlignment(javax.swing.JTextField.TRAILING);

        jLabel4.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel4.setText("Form SNo.");

        seatNoTextField.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        seatNoTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                seatNoTextFieldActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel6.setText("Admission form for*");

        programTypeComboBox.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        programTypeComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                programTypeComboBoxActionPerformed(evt);
            }
        });

        campusesPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Selection of campus for admission*", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        campusesPanel.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEADING, 5, 0));

        chalanFeeTextField.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        chalanFeeTextField.setText("1500");

        jLabel40.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel40.setText("Challan amount*");

        jLabel5.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel5.setText("Admission for Academic year*");

        forForeignPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Details to be filled by the Foreign National candidates only*"));

        jLabel46.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel46.setText("Passport No.*");

        passportNoTextField.setPreferredSize(new java.awt.Dimension(59, 25));

        jLabel47.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel47.setText("Place of Issue*");

        jLabel48.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel48.setText("Dated*");

        try {
            datedFormattedTextField.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##-##-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        jLabel49.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel49.setText("Visa No.*");

        jLabel50.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel50.setText("Dated*");

        try {
            visaNoDatedFormattedTextField.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##-##-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        jLabel51.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel51.setText("Date of validity*");

        try {
            dateOfValidityFormattedTextField.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##-##-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        try {
            arrivalDateinPakistanFormattedTextField.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##-##-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        jLabel52.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel52.setText("Date of arrival in Pakistan*");

        javax.swing.GroupLayout forForeignPanelLayout = new javax.swing.GroupLayout(forForeignPanel);
        forForeignPanel.setLayout(forForeignPanelLayout);
        forForeignPanelLayout.setHorizontalGroup(
            forForeignPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(forForeignPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(forForeignPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(forForeignPanelLayout.createSequentialGroup()
                        .addComponent(jLabel49)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(visaNoTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel50)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(visaNoDatedFormattedTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(forForeignPanelLayout.createSequentialGroup()
                        .addComponent(jLabel46)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(passportNoTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(forForeignPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel47, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel51, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(forForeignPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(forForeignPanelLayout.createSequentialGroup()
                        .addComponent(placeOfIssueTextField)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel48))
                    .addGroup(forForeignPanelLayout.createSequentialGroup()
                        .addComponent(dateOfValidityFormattedTextField)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel52)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(forForeignPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(datedFormattedTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 111, Short.MAX_VALUE)
                    .addComponent(arrivalDateinPakistanFormattedTextField))
                .addContainerGap())
        );
        forForeignPanelLayout.setVerticalGroup(
            forForeignPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(forForeignPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(forForeignPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel46)
                    .addComponent(passportNoTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel47)
                    .addComponent(placeOfIssueTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel48)
                    .addComponent(datedFormattedTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(forForeignPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel49)
                    .addComponent(visaNoTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel50)
                    .addComponent(visaNoDatedFormattedTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel51)
                    .addComponent(dateOfValidityFormattedTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(arrivalDateinPakistanFormattedTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel52))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        forForeignPanelLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {arrivalDateinPakistanFormattedTextField, dateOfValidityFormattedTextField, datedFormattedTextField, passportNoTextField, placeOfIssueTextField, visaNoDatedFormattedTextField, visaNoTextField});

        jLabel31.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel31.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel31.setText("Challan No*");

        remarksTextField.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        admissionYearComboBox.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        admissionYearComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                admissionYearComboBoxActionPerformed(evt);
            }
        });

        isObjectionCheckBox.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        isObjectionCheckBox.setText("Objection");
        isObjectionCheckBox.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                isObjectionCheckBoxStateChanged(evt);
            }
        });
        isObjectionCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                isObjectionCheckBoxActionPerformed(evt);
            }
        });

        jLabel35.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel35.setText("Press enter");
        jLabel35.setEnabled(false);

        photoButton.setText("Photo");
        photoButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                photoButtonActionPerformed(evt);
            }
        });

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Academic Record", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N

        sscGroupComboBox.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        sscGroupComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "General Science", "Arts", "Commerce" }));

        sscMarksObtainedTextField.setHorizontalAlignment(javax.swing.JTextField.TRAILING);

        sscTotalMarksTextField.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        sscTotalMarksTextField.setText("850");

        sscSeatNoTextField.setHorizontalAlignment(javax.swing.JTextField.TRAILING);

        sscBoardComboBox.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        hscGroupComboBox.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        hscGroupComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Medical", "Engineering", "Commerce", "Arts", "General Science", "AODP" }));

        hscMarksObtainedTextField.setHorizontalAlignment(javax.swing.JTextField.TRAILING);

        hscTotalMarksTextField.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        hscTotalMarksTextField.setText("1100");

        hscSeatNoTextField.setHorizontalAlignment(javax.swing.JTextField.TRAILING);

        hscBoardComboBox.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        grdGroupComboBox.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        grdGroupComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Science" }));
        grdGroupComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                grdGroupComboBoxActionPerformed(evt);
            }
        });

        graduationTotalMarksTextField.setHorizontalAlignment(javax.swing.JTextField.TRAILING);

        graduationSeatNoTextField.setHorizontalAlignment(javax.swing.JTextField.TRAILING);

        grdUniComboBox.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        jLabel53.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel53.setText("Examination Passed");

        jLabel54.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel54.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel54.setText("Group");

        jLabel55.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel55.setText("SSC (Matriculation)*");

        jLabel56.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel56.setText("HSC (Intermediate)*");
        jLabel56.setMaximumSize(new java.awt.Dimension(113, 14));
        jLabel56.setMinimumSize(new java.awt.Dimension(113, 14));
        jLabel56.setPreferredSize(new java.awt.Dimension(113, 14));

        jSeparator8.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jSeparator9.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jLabel57.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel57.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel57.setText("<html><center>Marks<br>Obtained</html>");

        jSeparator10.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jLabel58.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel58.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel58.setText("<html><center>Out of/Total<br>Marks</html>");

        jSeparator11.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jLabel59.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel59.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel59.setText("Year");

        jSeparator12.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jLabel60.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel60.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel60.setText("Seat No.");

        jSeparator13.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jLabel61.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel61.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel61.setText("Name of Board");

        jLabel62.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel62.setText("<html>Graduation<br>(Bachelor Degree)*</html>");
        jLabel62.setMaximumSize(new java.awt.Dimension(113, 14));
        jLabel62.setMinimumSize(new java.awt.Dimension(113, 14));
        jLabel62.setPreferredSize(new java.awt.Dimension(113, 14));

        jLabel26.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel26.setText("Optional subjects");

        jLabel28.setText("1.");

        jLabel29.setText("2.");

        jLabel30.setText("3.");

        jLabel32.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel32.setText("Degree Years*");

        subject1ComboBox.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        subject2ComboBox.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        subject3ComboBox.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        sscYearComboBox.setMaximumSize(new java.awt.Dimension(2147483647, 2147483647));
        sscYearComboBox.setMinimumSize(new java.awt.Dimension(6, 20));
        sscYearComboBox.setPreferredSize(new java.awt.Dimension(6, 20));

        hscYearComboBox.setMaximumSize(new java.awt.Dimension(2147483647, 2147483647));
        hscYearComboBox.setMinimumSize(new java.awt.Dimension(6, 20));
        hscYearComboBox.setPreferredSize(new java.awt.Dimension(6, 20));

        grdYearComboBox.setMaximumSize(new java.awt.Dimension(2147483647, 2147483647));
        grdYearComboBox.setMinimumSize(new java.awt.Dimension(6, 20));
        grdYearComboBox.setPreferredSize(new java.awt.Dimension(6, 20));

        yearsDegreeComboBox.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        yearsDegreeComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1", "2", "3", "4", "5" }));

        grdMarksObtainedFormattedTextField.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.##"))));
        grdMarksObtainedFormattedTextField.setHorizontalAlignment(javax.swing.JTextField.TRAILING);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel53)
                            .addComponent(jLabel55, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel56, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel62, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(4, 4, 4)
                        .addComponent(jSeparator8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(yearsDegreeComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel54, javax.swing.GroupLayout.DEFAULT_SIZE, 169, Short.MAX_VALUE)
                            .addComponent(sscGroupComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(hscGroupComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(grdGroupComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel57, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(sscMarksObtainedTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(hscMarksObtainedTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(grdMarksObtainedFormattedTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(6, 6, 6)
                        .addComponent(jSeparator10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(sscTotalMarksTextField)
                            .addComponent(hscTotalMarksTextField)
                            .addComponent(graduationTotalMarksTextField)
                            .addComponent(jLabel58, javax.swing.GroupLayout.DEFAULT_SIZE, 69, Short.MAX_VALUE))
                        .addGap(6, 6, 6)
                        .addComponent(jSeparator11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel59, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(sscYearComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(hscYearComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(grdYearComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(6, 6, 6)
                        .addComponent(jSeparator12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel60, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(sscSeatNoTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(hscSeatNoTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(graduationSeatNoTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(6, 6, 6)
                        .addComponent(jSeparator13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel26)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel28)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(subject1ComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel29)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(subject2ComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel30)))
                .addGap(6, 6, 6)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(subject3ComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel61, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(hscBoardComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(grdUniComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(sscBoardComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 13, Short.MAX_VALUE)))
                .addContainerGap())
        );

        jPanel4Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {grdGroupComboBox, hscGroupComboBox, sscGroupComboBox});

        jPanel4Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {grdUniComboBox, hscBoardComboBox, sscBoardComboBox});

        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel53, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(jLabel55, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(jLabel56, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(jLabel62, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jSeparator8, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel54, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(sscGroupComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(hscGroupComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(grdGroupComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jSeparator9, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel57, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(sscMarksObtainedTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(hscMarksObtainedTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(grdMarksObtainedFormattedTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jSeparator10, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel58, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(sscTotalMarksTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(hscTotalMarksTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(graduationTotalMarksTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jSeparator11, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel59, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(sscYearComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(hscYearComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(grdYearComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jSeparator12, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel60, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(sscSeatNoTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(hscSeatNoTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(graduationSeatNoTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jSeparator13, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel61, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(sscBoardComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(hscBoardComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(grdUniComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(subject3ComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel30)
                            .addComponent(subject2ComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel29)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel32)
                            .addComponent(yearsDegreeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel26)
                            .addComponent(jLabel28)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(subject1ComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        jPanel4Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {graduationSeatNoTextField, graduationTotalMarksTextField, grdGroupComboBox, grdMarksObtainedFormattedTextField, grdUniComboBox, grdYearComboBox, hscBoardComboBox, hscGroupComboBox, hscMarksObtainedTextField, hscSeatNoTextField, hscTotalMarksTextField, hscYearComboBox, sscBoardComboBox, sscGroupComboBox, sscMarksObtainedTextField, sscSeatNoTextField, sscTotalMarksTextField, sscYearComboBox, subject1ComboBox, subject2ComboBox, subject3ComboBox});

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Personal Details*", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N

        jLabel45.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel45.setText("Relationship");

        relationshipComboBox.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        relationshipComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "None", "Uncle", "Brother", "Sister", "Other" }));

        jLabel21.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel21.setText("Surname:");

        jLabel20.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel20.setText("Province/Region:");

        jLabel11.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel11.setText("Nationality*");

        jLabel27.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel27.setText("Occupation");

        religionComboBox.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        religionComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Islam", "Hinduism", "Christen", "Other" }));

        fathersOrGuardiansOccupationComboBox.setEditable(true);
        fathersOrGuardiansOccupationComboBox.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        fathersOrGuardiansOccupationComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Business Man", "Engineer", "Doctor", "Farmer", "Govt Employee", "Retired", "Other" }));

        jLabel10.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel10.setText("Religion*");

        jLabel25.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel25.setText("Guardian's Name");

        jLabel18.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel18.setText("Father’s Name*");

        jLabel7.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel7.setText("Division:");

        jLabel17.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel17.setText("Name*");

        jLabel44.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel44.setText("Area*");

        districtComboBox.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        districtComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                districtComboBoxActionPerformed(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel12.setText("District of Domicile*");

        jLabel14.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel14.setText("Date of Birth*");

        jLabel8.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel8.setText("C.N.I.C No.*");

        try {
            cnicFormattedTextField.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("#####-#######-#")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        cnicOfComboBox.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        cnicOfComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Self", "Father" }));

        jLabel9.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel9.setText("Gender*");

        try {
            dateOfBirthFormattedTextField.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##-##-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        dateOfBirthFormattedTextField.setToolTipText("DD-MM-YYYY");
        dateOfBirthFormattedTextField.setFocusLostBehavior(javax.swing.JFormattedTextField.COMMIT);

        jLabel15.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel15.setText("Place of Birth");

        jLabel1.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel1.setText("Address of Guardian");

        nationalityComboBox.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        provinceTextField.setEditable(false);
        provinceTextField.setFocusable(false);

        divisionTextField.setEditable(false);
        divisionTextField.setFocusable(false);

        jLabel22.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel22.setText("Telephone No.");

        jLabel23.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel23.setText("Mobile");

        genderComboBox.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        genderComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Male", "Female", "Other" }));

        jLabel24.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel24.setText("Email");

        jLabel16.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel16.setText("Permanent Home Address");

        jLabel19.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel19.setText("Present Postel Address");

        areaComboBox.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        areaComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Urban", "Rural" }));

        jLabel36.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel36.setText("Bank");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel25, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(10, 10, 10)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(guardiansNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel45)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(relationshipComboBox, 0, 241, Short.MAX_VALUE))
                            .addComponent(guardiansAddressTextField)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(nationalityComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, 113, Short.MAX_VALUE))
                        .addGap(10, 10, 10)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(dateOfBirthFormattedTextField)
                            .addComponent(religionComboBox, 0, 93, Short.MAX_VALUE)
                            .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(telephoneTextField)
                            .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel22, javax.swing.GroupLayout.DEFAULT_SIZE, 135, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel24))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(placeOfBirthTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(10, 10, 10)
                                .addComponent(fathersOrGuardiansOccupationComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(mobileTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(emailTextField))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel16)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(permanentHomeAddressTextField))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(presentPostelAddressTextField))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addComponent(districtComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(areaComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                            .addComponent(jLabel17)
                                            .addGap(4, 4, 4)
                                            .addComponent(nameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(4, 4, 4)
                                        .addComponent(cnicFormattedTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(cnicOfComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel18)
                                    .addComponent(jLabel44, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(5, 5, 5)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(fathersNameTextField, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(provinceTextField, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel20, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 257, Short.MAX_VALUE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(genderComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(divisionTextField)
                            .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jLabel36, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel21, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(surnameTextField)
                                    .addComponent(bankNameTextField))))))
                .addContainerGap())
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {areaComboBox, jLabel18, jLabel44, jLabel9});

        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cnicFormattedTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8)
                            .addComponent(cnicOfComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9)
                            .addComponent(genderComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(6, 6, 6))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel36)
                            .addComponent(bankNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(nameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(fathersNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel18)
                        .addComponent(jLabel21)
                        .addComponent(surnameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(jLabel17)))
                .addGap(7, 7, 7)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(jLabel44)
                    .addComponent(jLabel20)
                    .addComponent(jLabel7))
                .addGap(5, 5, 5)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(districtComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(areaComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(provinceTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(divisionTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(jLabel16))
                    .addComponent(permanentHomeAddressTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(jLabel19))
                    .addComponent(presentPostelAddressTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel11)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel22)
                        .addComponent(jLabel10)
                        .addComponent(jLabel23)
                        .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(6, 6, 6)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(nationalityComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(religionComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(1, 1, 1)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(emailTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(mobileTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(telephoneTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(placeOfBirthTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel27))
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel14)
                                .addComponent(dateOfBirthFormattedTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel15)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(11, 11, 11)
                                        .addComponent(jLabel25))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(9, 9, 9)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(guardiansNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel45))))
                                .addGap(6, 6, 6)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(2, 2, 2)
                                        .addComponent(jLabel1))
                                    .addComponent(guardiansAddressTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(8, 8, 8)
                                .addComponent(relationshipComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(fathersOrGuardiansOccupationComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(15, Short.MAX_VALUE))
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {areaComboBox, cnicFormattedTextField, cnicOfComboBox, dateOfBirthFormattedTextField, districtComboBox, divisionTextField, emailTextField, fathersNameTextField, fathersOrGuardiansOccupationComboBox, genderComboBox, guardiansAddressTextField, guardiansNameTextField, mobileTextField, nameTextField, nationalityComboBox, permanentHomeAddressTextField, placeOfBirthTextField, presentPostelAddressTextField, provinceTextField, relationshipComboBox, religionComboBox, surnameTextField, telephoneTextField});

        org.jdesktop.swingx.border.DropShadowBorder dropShadowBorder1 = new org.jdesktop.swingx.border.DropShadowBorder();
        dropShadowBorder1.setShowLeftShadow(true);
        dropShadowBorder1.setShowTopShadow(true);
        jPanel2.setBorder(dropShadowBorder1);

        jLabel33.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel33.setText("Test Score:");

        testScoreTextField.setEditable(false);
        testScoreTextField.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        testScoreTextField.setText("0");
        testScoreTextField.setFocusable(false);

        jLabel34.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel34.setText("Final Percentage:");

        finalPercentageTextField.setEditable(false);
        finalPercentageTextField.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        finalPercentageTextField.setFocusable(false);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel34)
                    .addComponent(jLabel33))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(testScoreTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 54, Short.MAX_VALUE)
                    .addComponent(finalPercentageTextField))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel33)
                    .addComponent(testScoreTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel34)
                    .addComponent(finalPercentageTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        loggerDetailLabel.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        loggerDetailLabel.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);

        objectionRemarksCheckboxPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        noSscMarksCheckBox.setText("SSC Marks");
        noSscMarksCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                noSscMarksCheckBoxActionPerformed(evt);
            }
        });
        objectionRemarksCheckboxPanel.add(noSscMarksCheckBox);

        noSscPassCheckBox.setText("SSC Pass");
        noSscPassCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                noSscPassCheckBoxActionPerformed(evt);
            }
        });
        objectionRemarksCheckboxPanel.add(noSscPassCheckBox);

        noHscMarksCheckBox.setText("HSC Marks");
        noHscMarksCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                noHscMarksCheckBoxActionPerformed(evt);
            }
        });
        objectionRemarksCheckboxPanel.add(noHscMarksCheckBox);

        noHscPassCheckBox.setText("HSC Pass");
        noHscPassCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                noHscPassCheckBoxActionPerformed(evt);
            }
        });
        objectionRemarksCheckboxPanel.add(noHscPassCheckBox);

        noGrdMarksCheckBox.setText("GRD Marksheet");
        noGrdMarksCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                noGrdMarksCheckBoxActionPerformed(evt);
            }
        });
        objectionRemarksCheckboxPanel.add(noGrdMarksCheckBox);

        noGrdPassCheckBox.setText("GRD Pass");
        noGrdPassCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                noGrdPassCheckBoxActionPerformed(evt);
            }
        });
        objectionRemarksCheckboxPanel.add(noGrdPassCheckBox);

        noDomicileCheckBox.setText("Domicile");
        noDomicileCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                noDomicileCheckBoxActionPerformed(evt);
            }
        });
        objectionRemarksCheckboxPanel.add(noDomicileCheckBox);

        noAffidavitCheckBox.setText("Affidavit");
        noAffidavitCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                noAffidavitCheckBoxActionPerformed(evt);
            }
        });
        objectionRemarksCheckboxPanel.add(noAffidavitCheckBox);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator2, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(forForeignPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(seatNoTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel35)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel4))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(candidateIdTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel5)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(formNoTextField)
                            .addComponent(admissionYearComboBox, 0, 89, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(programTypeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(loggerDetailLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(photoButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGap(28, 28, 28))
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(campusesPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(disciplineChoiceButton, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(objectionRemarksCheckboxPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(jLabel40, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(chalanFeeTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(24, 24, 24)
                                .addComponent(jLabel31)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(remarksTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel41, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(chalanDateFormattedTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel43))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(isObjectionCheckBox)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(objectRemarksTextField)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(46, 46, 46))
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(candidateIdTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6)
                    .addComponent(programTypeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(admissionYearComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel3)
                        .addComponent(seatNoTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel4)
                        .addComponent(formNoTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel35))
                    .addComponent(loggerDetailLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(photoButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(campusesPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(forForeignPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(isObjectionCheckBox, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(objectRemarksTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel40)
                            .addComponent(chalanFeeTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel41)
                            .addComponent(jLabel31)
                            .addComponent(remarksTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(chalanDateFormattedTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel43)))
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(disciplineChoiceButton)
                    .addComponent(objectionRemarksCheckboxPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel5Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {admissionYearComboBox, candidateIdTextField, formNoTextField, programTypeComboBox, seatNoTextField});

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHEAST;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 5, 5);
        jPanel6.add(jPanel5, gridBagConstraints);

        jScrollPane3.setViewportView(jPanel6);

        add(jScrollPane3, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void photoLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_photoLabelMouseClicked
        // TODO add your handling code here:
        if (fileChooser.showOpenDialog(photoDialog) == JFileChooser.APPROVE_OPTION) {
            try {
                this.photoLabel.setText(null);
                File file = fileChooser.getSelectedFile();
                BufferedImage readImage = ImageIO.read(file);
                readImage = ImageUtils.resizeImage(readImage, 190, 228);
                this.photoLabel.setIcon(new ImageIcon(readImage));
            } catch (IOException ex) {
                Logger.getLogger(AdmissionFormPanel.class.getName()).log(Level.SEVERE, null, ex);
                admission.utils.MessageBox.error(this, ex);
            }
        }
    }//GEN-LAST:event_photoLabelMouseClicked

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        this.photoLabel.setText("No photo");
        this.photoLabel.setIcon(null);
        candidate.setImagePath(null);
        DatabaseManager.updateData(candidate);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        try {
            // TODO add your handling code here:
            ImageIcon ic = (ImageIcon) this.photoLabel.getIcon();
            if (ic == null) {
                return;
            }
            BufferedImage bf = ImageUtils.toBufferedImage(ic.getImage());
            ImageIO.write(bf, "png", new File(IConstant.PATH + candidate.getCandidateId() + ".png"));
            candidate.setImagePath(candidate.getCandidateId() + ".png");
            //            candidate.setImage(ImageUtils.writeImage(bf));
            DatabaseManager.updateData(candidate);
        } catch (IOException ex) {
            Logger.getLogger(AdmissionFormPanel.class.getName()).log(Level.SEVERE, null, ex);
            admission.utils.MessageBox.error(this, ex);
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        photoDialog.dispose();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void disciplineChoiceButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_disciplineChoiceButtonActionPerformed
//        this.disciplineChoicesDialog.clearAll();
        if (programTypeComboBox.getSelectedItem() == null || admissionYearComboBox.getSelectedItem() == null) {
            MessageBox.error(this, MessageEnum.MSG_11);
            return;
        }
        boolean b = false;
        for (Component comp : campusesPanel.getComponents()) {
            JCustomCheckBox cb = (JCustomCheckBox) comp;
            if (cb.isSelected()) {
//                addChoicePanel(cb, candidate);
                b = true;
            }
        }
        if (b) {
            this.disciplineChoicesDialog.setVisible(true);
            return;
        }
        admission.utils.MessageBox.info(this, MessageEnum.MSG_09);
    }//GEN-LAST:event_disciplineChoiceButtonActionPerformed

    private void suEmployeesQuotaCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_suEmployeesQuotaCheckBoxActionPerformed
        // TODO add your handling code here:
        this.sueQuotaComboBox.setEnabled(this.suEmployeesQuotaCheckBox.isSelected());
    }//GEN-LAST:event_suEmployeesQuotaCheckBoxActionPerformed

    private void foreignQuotaCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_foreignQuotaCheckBoxActionPerformed
        // TODO add your handling code here:
        this.forForeignPanel.setVisible(foreignQuotaCheckBox.isSelected());
        if (foreignQuotaCheckBox.isSelected()) {
            jPanel5.setPreferredSize(new Dimension(902, 1110));
        } else {
            jPanel5.setPreferredSize(new Dimension(902, 990));
        }
    }//GEN-LAST:event_foreignQuotaCheckBoxActionPerformed

    private void deleteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteButtonActionPerformed
        // TODO add your handling code here:
        String candidateId = this.candidateIdTextField.getText();
        if (candidateId.isEmpty()) {
            return;
        }

        try {
            DatabaseManager.deleteData(Candidate.class.getName(), "candidateId=" + Integer.valueOf(candidateId));

            admission.utils.MessageBox.info(this, "Record deleted successfully");
            clear();
        } catch (HibernateException he) {
            Logger.getLogger(AdmissionFormPanel.class.getName()).log(Level.SEVERE, null, he);
            admission.utils.MessageBox.error(this, he);
        }
    }//GEN-LAST:event_deleteButtonActionPerformed

    private void seatNoTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_seatNoTextFieldActionPerformed
        // TODO add your handling code here:
        if (!CommonHelper.checkUserResourceAccess(KeyConstant.VIEW_ADMISSION_FORM)) {
            MessageBox.lock(this, MessageEnum.MSG_24);
            return;
        }
        String seatNo = this.seatNoTextField.getText();
        AdmissionYear ay = (AdmissionYear) this.admissionYearComboBox.getSelectedItem();
        ProgramType pt = (ProgramType) this.programTypeComboBox.getSelectedItem();
        if (ay == null || pt == null || seatNo.isEmpty()) {
            return;
        }

        this.clear();
        String title = this.setCandidate(seatNo, ay.getAdmissionYearId(), pt.getProgramTypeId());
        if (title != null) {
            IConstant.USER.ADMISSION_FORM.setTabTitle(this, title);
        }
    }//GEN-LAST:event_seatNoTextFieldActionPerformed

    private void programTypeComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_programTypeComboBoxActionPerformed
        // TODO add your handling code here:
        ProgramType programType = (ProgramType) this.programTypeComboBox.getSelectedItem();
        if (programType == null) {
            return;
        }

        boolean b = (programType.getIsBachelor());
        armyPersonalQuotaCheckBox.setEnabled(!b);
        if (b) {
            armyPersonalQuotaCheckBox.setSelected(false);
        }
        setGraduationComponentEnabled(!b);
        loadEducationYearsCombo();
    }//GEN-LAST:event_programTypeComboBoxActionPerformed

    private void grdGroupComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_grdGroupComboBoxActionPerformed
        // TODO add your handling code here:
        getSubject();
    }//GEN-LAST:event_grdGroupComboBoxActionPerformed

    private void reLoadButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_reLoadButtonActionPerformed
        // TODO add your handling code here:
        loadCatalog();
    }//GEN-LAST:event_reLoadButtonActionPerformed

    private void photoButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_photoButtonActionPerformed
        // TODO add your handling code here:
        if (candidate != null) {
            setPhoto();
            photoDialog.setVisible(true);
        }
    }//GEN-LAST:event_photoButtonActionPerformed

    private void admissionYearComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_admissionYearComboBoxActionPerformed
        // TODO add your handling code here:
        loadEducationYearsCombo();
    }//GEN-LAST:event_admissionYearComboBoxActionPerformed

    private void districtComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_districtComboBoxActionPerformed
        // TODO add your handling code here:
        District district = (District) this.districtComboBox.getSelectedItem();
        if (district == null) {
            return;
        }

        Division division = district.getDivision();
        this.divisionTextField.setText(division.getName());
        this.provinceTextField.setText(division.getProvince().getName());
    }//GEN-LAST:event_districtComboBoxActionPerformed

    private void isObjectionCheckBoxStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_isObjectionCheckBoxStateChanged
        //objectRemarksTextField.setEditable(isObjectionCheckBox.isSelected());
       // objectionRemarksCheckboxPanel.setVisible(isObjectionCheckBox.isSelected());
       // if(!isObjectionCheckBox.isSelected()){objectRemarksTextField.setText("");}
        
    }//GEN-LAST:event_isObjectionCheckBoxStateChanged

    private void noSscMarksCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_noSscMarksCheckBoxActionPerformed
        //if()
        updateObjectionRemarks("SSC MARKSHET",noSscMarksCheckBox.isSelected());
    }//GEN-LAST:event_noSscMarksCheckBoxActionPerformed

    private void noHscMarksCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_noHscMarksCheckBoxActionPerformed
        updateObjectionRemarks("HSC MARKSHET", noHscMarksCheckBox.isSelected());
    }//GEN-LAST:event_noHscMarksCheckBoxActionPerformed

    private void noHscPassCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_noHscPassCheckBoxActionPerformed
        updateObjectionRemarks("HSC PASS CERRTIFICATE", noHscPassCheckBox.isSelected());
    }//GEN-LAST:event_noHscPassCheckBoxActionPerformed

    private void noSscPassCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_noSscPassCheckBoxActionPerformed
        updateObjectionRemarks("SSC PASS CERTIFICATE", noSscPassCheckBox.isSelected());
    }//GEN-LAST:event_noSscPassCheckBoxActionPerformed

    private void noGrdMarksCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_noGrdMarksCheckBoxActionPerformed
        updateObjectionRemarks("GRADUATION MARKSHET", noGrdMarksCheckBox.isSelected());
    }//GEN-LAST:event_noGrdMarksCheckBoxActionPerformed

    private void noGrdPassCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_noGrdPassCheckBoxActionPerformed
        updateObjectionRemarks("GRADUATION PASS CERTIFICATE ", noGrdPassCheckBox.isSelected());
    }//GEN-LAST:event_noGrdPassCheckBoxActionPerformed

    private void noDomicileCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_noDomicileCheckBoxActionPerformed
        updateObjectionRemarks("DOMICILE", noDomicileCheckBox.isSelected());
    }//GEN-LAST:event_noDomicileCheckBoxActionPerformed

    private void noAffidavitCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_noAffidavitCheckBoxActionPerformed
        updateObjectionRemarks("NO AFFIDAVIT", noAffidavitCheckBox.isSelected());
    }//GEN-LAST:event_noAffidavitCheckBoxActionPerformed

    private void isObjectionCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_isObjectionCheckBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_isObjectionCheckBoxActionPerformed

    private void updateObjectionRemarks(String str, boolean isAppend){
        String text = objectRemarksTextField.getText().toLowerCase();
        if(!(text.equals(""))){
        text = (text.trim())+(", ");
        }
        if(text.equals("")){
        text = (text.trim())+("REQUIRED ");
        }
        if(isAppend){
            if(!text.contains(str.toLowerCase())){
                text = (text.trim())+(" "+str);
            }
        }else{
            text = text.replaceAll(str.toLowerCase(), "");
        }
        objectRemarksTextField.setText(text.toUpperCase());
    }
    private void setPhoto() {
        if (candidate.getImagePath() == null || candidate.getImagePath().isEmpty()) {
            this.photoLabel.setText("<html>No photo<br>Click to browse");
            this.photoLabel.setIcon(null);
        } else {
            File file = new File(IConstant.PATH + candidate.getImagePath());
            if (!file.exists()) {
                return;
            }

            this.photoLabel.setText(null);
            try {
                BufferedImage readImage = ImageIO.read(file);
                readImage = ImageUtils.resizeImage(readImage, 190, 228);
                this.photoLabel.setIcon(new ImageIcon(readImage));
            } catch (IOException ex) {
                Logger.getLogger(AdmissionFormPanel.class.getName()).log(Level.SEVERE, null, ex);
                admission.utils.MessageBox.error(this, ex);
            }
        }
    }

    private void loadEducationYearsCombo() {
        sscYearComboBox.removeAllItems();
        hscYearComboBox.removeAllItems();
        grdYearComboBox.removeAllItems();

        AdmissionYear ay = (AdmissionYear) admissionYearComboBox.getSelectedItem();
        ProgramType pt = (ProgramType) programTypeComboBox.getSelectedItem();
        if (ay == null || pt == null) {
            return;
        }

        int end = ay.getYear();
        int start = end - 60;

        int val = pt.getIsBachelor() ? 3 : 6;
        for (int i = end - val; i >= start + val; i--) {
            sscYearComboBox.addItem("" + i);
        }
        val = pt.getIsBachelor() ? 1 : 4;
        for (int i = end - val; i >= start + val; i--) {
            hscYearComboBox.addItem("" + i);
        }
        for (int i = end - 2; i >= start + 2; i--) {
            grdYearComboBox.addItem("" + i);
        }
    }

    private void getAdmissionYear() {
        Object ob = admissionYearComboBox.getSelectedItem();
        this.admissionYearComboBox.removeAllItems();

        List<AdmissionYear> list = EntityHelper.getEntities(AdmissionYear.class);
        if (list.isEmpty()) {
            list = DatabaseManager.getData(AdmissionYear.class.getName(), "year DESC");
        }

        for (AdmissionYear list1 : list) {
            this.admissionYearComboBox.addItem(list1);
        }
        if (ob != null) {
            admissionYearComboBox.setSelectedItem(ob);
        }
    }

    private void getProgramType() {
        Object ob = programTypeComboBox.getSelectedItem();
        this.programTypeComboBox.removeAllItems();

        List<ProgramType> list = EntityHelper.getEntities(ProgramType.class);
        if (list.isEmpty()) {
            list = DatabaseManager.getData(ProgramType.class, "isBachelor DESC");
        }

        for (ProgramType list1 : list) {
            this.programTypeComboBox.addItem(list1);
        }
        if (ob != null) {
            programTypeComboBox.setSelectedItem(ob);
        }
    }

    private void getCampus() {
        campusesPanel.removeAll();
        List<Campus> list = DatabaseManager.getData(Campus.class.getName(), "displayOrder");

        for (Campus campus : list) {
            final JCustomCheckBox ob = new JCustomCheckBox(campus, false);
            ob.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    addChoicePanel(ob, candidate);
                }
            });
            campusesPanel.add(ob);
        }
    }

    private void getIssuer() {
        Object ob1 = this.sscBoardComboBox.getSelectedItem();
        Object ob2 = this.hscBoardComboBox.getSelectedItem();
        Object ob3 = this.grdUniComboBox.getSelectedItem();
        this.sscBoardComboBox.removeAllItems();
        this.hscBoardComboBox.removeAllItems();
        this.grdUniComboBox.removeAllItems();

        List<Issuer> list = DatabaseManager.getData(Issuer.class.getName(), "name");

        for (Issuer issuer : list) {
            boolean isBoard = (issuer.getIsBoard());
            if (isBoard) {
                sscBoardComboBox.addItem(issuer);
                hscBoardComboBox.addItem(issuer);
            } else {
                grdUniComboBox.addItem(issuer);
            }
        }

        this.sscBoardComboBox.setSelectedItem(ob1);
        this.hscBoardComboBox.setSelectedItem(ob2);
        this.grdUniComboBox.setSelectedItem(ob3);
    }

    private void getProgram() {
        Object ob1 = this.sscGroupComboBox.getSelectedItem();
        Object ob2 = this.hscGroupComboBox.getSelectedItem();
        Object ob3 = this.grdGroupComboBox.getSelectedItem();
        this.sscGroupComboBox.removeAllItems();
        this.hscGroupComboBox.removeAllItems();
        this.grdGroupComboBox.removeAllItems();

        List<Program> list = DatabaseManager.getData(Program.class, "name");
        for (Program p : list) {
            if (p.getLevel().equals(LevelEnum.SSC)) {
                this.sscGroupComboBox.addItem(p);
            } else if (p.getLevel().equals(LevelEnum.HSC)) {
                this.hscGroupComboBox.addItem(p);
            } else if (p.getLevel().equals(LevelEnum.OTHER)) {
                this.grdGroupComboBox.addItem(p);
            }
        }
        this.sscGroupComboBox.setSelectedItem(ob1);
        this.hscGroupComboBox.setSelectedItem(ob2);
        this.grdGroupComboBox.setSelectedItem(ob3);
    }

    private void getReligion() {
        Object ob = religionComboBox.getSelectedItem();
        this.religionComboBox.removeAllItems();
        List<Religion> list = DatabaseManager.getData(Religion.class.getName(), "religionId");

        for (Religion list1 : list) {
            this.religionComboBox.addItem(list1);
        }
        religionComboBox.setSelectedItem(ob);
    }

    private void getCountry() {
        Object ob = nationalityComboBox.getSelectedItem();
        this.nationalityComboBox.removeAllItems();
        List<Country> list = DatabaseManager.getData(Country.class.getName(), "name");

        for (Country list1 : list) {
            this.nationalityComboBox.addItem(list1);
        }
        if (ob == null) {
            ob = DatabaseManager.getSingleRecord(Country.class.getName(), "name = 'PAKISTAN'");
        }
        nationalityComboBox.setSelectedItem(ob);
    }

    private void getDistrict() {
        Object ob = districtComboBox.getSelectedItem();
        districtComboBox.removeAllItems();
        List<District> list = DatabaseManager.getData(District.class.getName(), "name");

        for (District list1 : list) {
            this.districtComboBox.addItem(list1);
        }
        districtComboBox.setSelectedItem(ob);
    }

    public String setCandidate(String seatNo, int ayId, int ptId) {
        String title = null;
        try {
            candidate = (Candidate) DatabaseManager.getCandidate(ayId, ptId, seatNo);
            if (candidate == null) {
                admission.utils.MessageBox.info(this, MessageEnum.MSG_NO_FOUND);
                return title;
            }

            AdmissionYear ay = candidate.getAdmissionYear();
            this.admissionYearComboBox.setSelectedItem(ay);
            ProgramType pt = candidate.getProgramType();
            this.programTypeComboBox.setSelectedItem(pt);

            title = ay.getYear() + "-" + pt.getName().charAt(0) + "-" + candidate.getSeatNo();

            List<AppliedCampus> appliedCampusList = DatabaseManager.getData(AppliedCampus.class, "candidate.candidateId = " + candidate.getCandidateId(), "appliedCampusId");
            this.setCampusSelected(appliedCampusList, candidate);

            List<AppliedCategory> appliedCategoryList = DatabaseManager.getData(AppliedCategory.class, "candidate.candidateId = " + candidate.getCandidateId(), "appliedCategoryId");
            this.setCategorySelected(appliedCategoryList);

            this.photoButton.setVisible(true);
            this.candidateIdTextField.setText(String.valueOf(candidate.getCandidateId()));
            this.seatNoTextField.setText(String.valueOf(candidate.getSeatNo()));
            this.formNoTextField.setText(String.valueOf(candidate.getFormSno()));
            this.programTypeComboBox.setSelectedItem(candidate.getProgramType());
            this.cnicFormattedTextField.setText(candidate.getCnicNo());
            this.cnicOfComboBox.setSelectedItem(Coder.Decoder.decode(candidate.getCnicOf()));
            this.nameTextField.setText(candidate.getName());
            this.fathersNameTextField.setText(candidate.getFathersName());
            this.surnameTextField.setText(candidate.getSurname());
            this.genderComboBox.setSelectedItem((candidate.getGender()));
            this.areaComboBox.setSelectedItem(candidate.getArea());
            this.districtComboBox.setSelectedItem(candidate.getDistrict());
            this.permanentHomeAddressTextField.setText(candidate.getPermanentHomeAddress());
            this.presentPostelAddressTextField.setText(candidate.getPresentPostelAddress());
            this.nationalityComboBox.setSelectedItem(candidate.getCountry());
            this.religionComboBox.setSelectedItem(candidate.getReligion());
            this.telephoneTextField.setText(candidate.getTelephone());
            this.mobileTextField.setText(candidate.getMobile());
            this.emailTextField.setText(candidate.getEmail());
            this.dateOfBirthFormattedTextField.setText(DateUtility.getDateToString(candidate.getDateOfBirth()));
            this.placeOfBirthTextField.setText(candidate.getPlaceOfBirth());
            ((JTextComponent) fathersOrGuardiansOccupationComboBox.getEditor().getEditorComponent()).setText(candidate.getFathersOccupation());
//            this.fathersOrGuardiansOccupationComboBox.setSelectedItem(candidate.getFathersOccupation());
            this.guardiansNameTextField.setText(candidate.getGuardiansName());
            this.guardiansAddressTextField.setText(candidate.getGuardiansAddress());
            this.remarksTextField.setText(candidate.getRemarks());

            List<CredentialDetails> credentialDetailsList = DatabaseManager.getData(CredentialDetails.class, "candidate.candidateId = " + candidate.getCandidateId(), "detailOf");
            CredentialDetails ssc = credentialDetailsList.get(0);
            CredentialDetails hsc = credentialDetailsList.get(1);

            this.sscBoardComboBox.setSelectedItem(ssc.getIssuer());
            this.sscGroupComboBox.setSelectedItem(ssc.getProgram());//(Coder.Decoder.groupDecode(ssc.getGroup()));
            this.sscTotalMarksTextField.setText(String.valueOf(ssc.getTotalMarks()));
            this.sscMarksObtainedTextField.setText(String.valueOf(ssc.getMarksObtained()));
//            ((JTextComponent) sscYearComboBox.getEditor().getEditorComponent()).setText(String.valueOf(ssc.getPassingYear()));
            this.sscYearComboBox.setSelectedItem(String.valueOf(ssc.getPassingYear()));
            this.sscSeatNoTextField.setText(String.valueOf(ssc.getSeatNo()));

            this.hscBoardComboBox.setSelectedItem(hsc.getIssuer());
            this.hscGroupComboBox.setSelectedItem(hsc.getProgram());//(Coder.Decoder.groupDecode(hsc.getGroup()));
            this.hscTotalMarksTextField.setText(String.valueOf(hsc.getTotalMarks()));
            this.hscMarksObtainedTextField.setText(String.valueOf(hsc.getMarksObtained()));
//            ((JTextComponent) hscYearComboBox.getEditor().getEditorComponent()).setText(String.valueOf(hsc.getPassingYear()));
            this.hscYearComboBox.setSelectedItem(String.valueOf(hsc.getPassingYear()));
            this.hscSeatNoTextField.setText(String.valueOf(hsc.getSeatNo()));

            this.isObjectionCheckBox.setSelected((candidate.getIsObjection()));
            this.objectRemarksTextField.setText(candidate.getObjectionRemarks());

            this.testScoreTextField.setText(candidate.getTestScore() + "");
            this.finalPercentageTextField.setText(String.valueOf(candidate.getPercentage()));

            this.chalanFeeTextField.setText(String.valueOf(candidate.getChalanFee()));
            this.chalanDateFormattedTextField.setText(DateUtility.getDateToString(candidate.getChalanDate()));

            boolean b = (candidate.getProgramType().getIsBachelor());
            if (!b && credentialDetailsList.size() == 3) {
                CredentialDetails graduation = credentialDetailsList.get(2);
                this.grdUniComboBox.setSelectedItem(graduation.getIssuer());
                this.graduationTotalMarksTextField.setText(String.valueOf(graduation.getTotalMarks()));
                this.grdMarksObtainedFormattedTextField.setText(String.valueOf(graduation.getMarksObtained()));
//                ((JTextComponent) grdYearComboBox.getEditor().getEditorComponent()).setText(String.valueOf(ssc.getPassingYear()));
                this.grdYearComboBox.setSelectedItem(String.valueOf(graduation.getPassingYear()));
                this.graduationSeatNoTextField.setText(String.valueOf(graduation.getSeatNo()));
                this.grdGroupComboBox.setSelectedItem(graduation.getProgram());

                List<OptionalSubject> optionalSubjectsList = DatabaseManager.getData(OptionalSubject.class, "credentialDetails.credentialDetailsId = " + graduation.getCredentialDetailsId(), "optionalSubjectId");
//                Object[] toArray = graduation.getOptionalSubjects().toArray();
//                System.out.println(graduation.getGroup() + " : " + graduation.getCredentialDetailsId());
                Subject sb1 = optionalSubjectsList.size() >= 1 ? optionalSubjectsList.get(0).getSubject() : null;
                Subject sb2 = optionalSubjectsList.size() >= 2 ? optionalSubjectsList.get(1).getSubject() : null;
                Subject sb3 = optionalSubjectsList.size() >= 3 ? optionalSubjectsList.get(2).getSubject() : null;
                subject1ComboBox.setSelectedItem(sb1);
                subject2ComboBox.setSelectedItem(sb2);
                subject3ComboBox.setSelectedItem(sb3);

                yearsDegreeComboBox.setSelectedItem(String.valueOf(candidate.getYearsDegree()));
            } else {
                setGraduationComponentEnabled(false);
            }

            ForeignCandidate foreignCandidate = DatabaseManager.getSingleRecord(ForeignCandidate.class, "candidate.candidateId = " + candidate.getCandidateId());
            if (foreignCandidate != null) {
                this.passportNoTextField.setText(foreignCandidate.getPassportNo());
                this.placeOfIssueTextField.setText(foreignCandidate.getPlaceOfIssue());
                this.visaNoTextField.setText(foreignCandidate.getVisaNo());
                this.datedFormattedTextField.setText(DateUtility.getDateToString(foreignCandidate.getDated()));
                this.visaNoDatedFormattedTextField.setText(DateUtility.getDateToString(foreignCandidate.getVisaNoDated()));
                this.dateOfValidityFormattedTextField.setText(DateUtility.getDateToString(foreignCandidate.getDateOfValidity()));
                this.arrivalDateinPakistanFormattedTextField.setText(DateUtility.getDateToString(foreignCandidate.getArrivalDateInPakistan()));
            }
            this.bankNameTextField.setText(candidate.getBankBranch()!= null ? candidate.getBankBranch().getName():"");
            Log log = LoggerHelper.getLoggerName(Candidate.class, candidate.getCandidateId(), "ADD");
            /*
            if (log == null) {
                MessageBox.error(this, MessageEnum.MSG_25);
            } else {
                loggerDetailLabel.setText("<html><p align=right>Entered by: " + log.getUser().getFirstName() + "<br>Entered date: " + Utility.dateFormat(log.getLogDate()));
            }
             */
        } catch (HibernateException he) {
            Logger.getLogger(AdmissionFormPanel.class.getName()).log(Level.SEVERE, null, he);
            admission.utils.MessageBox.error(this, he);
            title = null;
        }

        return title;
    }

    public String save() {
        String s = "";
        if (candidate == null) {
            if (CommonHelper.checkUserResourceAccess(KeyConstant.SAVE_ADMISSION_FORM)) {
                if (disciplineChoicesDialog.getAppliedCampusDisciplineList().isEmpty()) {
                    MessageBox.info(this, MessageEnum.MSG_23);
                    return s;
                }
                s = add();
            } else {
                MessageBox.lock(this, MessageEnum.MSG_19);
                s = "";
            }
        } else {
            if (CommonHelper.checkUserResourceAccess(KeyConstant.UPDATE_ADMISSION_FORM)) {
                if (disciplineChoicesDialog.getAppliedCampusDisciplineList().isEmpty()) {
                    MessageBox.info(this, MessageEnum.MSG_23);
                    return s;
                }
                update();
            } else {
                MessageBox.lock(this, MessageEnum.MSG_20);
            }
            s = "";
        }
        return s;
    }

    private String add() {
        String title = null;
        AdmissionYear ay = (AdmissionYear) this.admissionYearComboBox.getSelectedItem();
        ProgramType pt = (ProgramType) this.programTypeComboBox.getSelectedItem();
        if (ay == null || pt == null) {
            admission.utils.MessageBox.error(this, MessageEnum.MSG_11);
            return title;
        }
        District district = (District) this.districtComboBox.getSelectedItem();
        if (district == null) {
            admission.utils.MessageBox.error(this, MessageEnum.MSG_12);
            return title;
        }

        String seatNo = this.seatNoTextField.getText();
        if (seatNo == null || seatNo.isEmpty()) {
            admission.utils.MessageBox.error(this, MessageEnum.MSG_13);
            return title;
        }

        Candidate c = DatabaseManager.getSingleRecord(Candidate.class, "admissionYear.admissionYearId = " + ay.getAdmissionYearId() + " AND programType.programTypeId = " + pt.getProgramTypeId() + " AND seatNo = " + seatNo);
//        Candidate c = (Candidate) DatabaseManager.getCandidate(ay.getAdmissionYearId(), pt.getProgramTypeId(), seatNo);
        if (c != null) {
            admission.utils.MessageBox.info(this, MessageEnum.MSG_14);
            return title;
        }

        boolean isBachelor = pt.getIsBachelor();

        Integer formSno = this.formNoTextField.getText().isEmpty() ? 0 : Integer.parseInt(formNoTextField.getText());
        String cnicNo = this.cnicFormattedTextField.getText().trim();
        cnicNo = cnicNo.length() == 2 ? "" : cnicNo;
        String cnicOf = Coder.Encoder.encode(this.cnicOfComboBox.getSelectedItem().toString());
        String name = this.nameTextField.getText();
        String fathersName = this.fathersNameTextField.getText();
        String surname = this.surnameTextField.getText();
        GenderEnum gender = (GenderEnum) this.genderComboBox.getSelectedItem();
        AreaEnum area = (AreaEnum) this.areaComboBox.getSelectedItem();
        String permanentHomeAddress = this.permanentHomeAddressTextField.getText();
        String presentPostelAddress = this.presentPostelAddressTextField.getText();
        Country country = (Country) this.nationalityComboBox.getSelectedItem();
        if (country == null) {
            admission.utils.MessageBox.info(this, MessageEnum.MSG_21);
            return title;
        }
        Religion religion = (Religion) this.religionComboBox.getSelectedItem();
        if (religion == null) {
            admission.utils.MessageBox.info(this, MessageEnum.MSG_22);
            return title;
        }
        Date dateOfBirth;
        String placeOfBirth = this.placeOfBirthTextField.getText();
        String fathersOccupation = ((JTextComponent) fathersOrGuardiansOccupationComboBox.getEditor().getEditorComponent()).getText();//this.fathersOrGuardiansOccupationComboBox.getSelectedItem().toString();
        String guardiansName = this.guardiansNameTextField.getText();
        String relationship = this.relationshipComboBox.getSelectedItem().toString();
        String guardiansAddress = this.guardiansAddressTextField.getText();
        String telephone = this.telephoneTextField.getText();
        String mobile = this.mobileTextField.getText();
        String email = this.emailTextField.getText();
        Issuer sscIssuer = (Issuer) this.sscBoardComboBox.getSelectedItem();
        String sscTotalMarks = this.sscTotalMarksTextField.getText();
        String sscMarksObtained = this.sscMarksObtainedTextField.getText();

        String sscYear = this.sscYearComboBox.getSelectedItem().toString();//((JTextComponent) sscYearComboBox.getEditor().getEditorComponent()).getText();
        String sscSeatNo = this.sscSeatNoTextField.getText();
        Program sscGroupProgram = (Program) sscGroupComboBox.getSelectedItem();
        Issuer hscIssuer = (Issuer) this.hscBoardComboBox.getSelectedItem();
        String hscTotalMarks = this.hscTotalMarksTextField.getText();
        String hscMarksObtained = this.hscMarksObtainedTextField.getText();
        String hscYear = this.hscYearComboBox.getSelectedItem().toString();//((JTextComponent) hscYearComboBox.getEditor().getEditorComponent()).getText();
        String hscSeatNo = this.hscSeatNoTextField.getText();
        Program hscGroupProgram = (Program) hscGroupComboBox.getSelectedItem();
        Issuer graduationIssuer = (Issuer) this.grdUniComboBox.getSelectedItem();
        String graduationTotalMarks = this.graduationTotalMarksTextField.getText();
        String graduationMarksObtained = this.grdMarksObtainedFormattedTextField.getText();
        String graduationYear = this.grdYearComboBox.getSelectedItem().toString();//((JTextComponent) grdYearComboBox.getEditor().getEditorComponent()).getText();
        String graduationSeatNo = this.graduationSeatNoTextField.getText();
        Program grdGroupProgram = (Program) grdGroupComboBox.getSelectedItem();
        String challanFee = this.chalanFeeTextField.getText();
        Date chalanDate;
        Integer yearsDegree = isBachelor ? null : Integer.parseInt(this.yearsDegreeComboBox.getSelectedItem().toString());
        boolean isObjection = (isObjectionCheckBox.isSelected());
        String remarks = this.remarksTextField.getText();
        String objectionRemarks = this.objectRemarksTextField.getText();
        Integer testScore = Integer.parseInt(testScoreTextField.getText());
        Float percentage = 0.0F;
        Integer deductionMarks = 0;
        // For foreign
        String passportNo = this.passportNoTextField.getText();
        String placeOfIssue = this.placeOfIssueTextField.getText();
        Date dated = null;
        String visaNo = this.visaNoTextField.getText();
        Date visaNoDated = null;
        Date dateOfValidity = null;
        Date arrivalDateInPakistan = null;

        if (seatNo.isEmpty()) {
            admission.utils.MessageBox.error(this, "Seat no is empty");
            return title;
        } else if (sscIssuer == null || hscIssuer == null) {
            admission.utils.MessageBox.error(this, "SSC or HSC Board not selected");
            return title;
        } else if (!isBachelor & graduationIssuer == null) {
            admission.utils.MessageBox.error(this, "Graduation university not selected");
            return title;
        } else if (challanFee.isEmpty() || remarks.isEmpty()) {
            admission.utils.MessageBox.error(this, "Challan fee or Challan no field is empty");
            return title;
        }
        if (sscTotalMarks.isEmpty() || sscMarksObtained.isEmpty() || sscYear.isEmpty() || sscSeatNo.isEmpty()
                || hscTotalMarks.isEmpty() || hscMarksObtained.isEmpty() || hscYear.isEmpty() || hscSeatNo.isEmpty()) {
            admission.utils.MessageBox.error(this, "Please fill valid credential details");
            return title;
        }
        if (!isBachelor && (graduationTotalMarks.isEmpty() || graduationMarksObtained.isEmpty() || graduationYear.isEmpty() || graduationSeatNo.isEmpty())) {
            admission.utils.MessageBox.error(this, "Please fill valid grdaduation credential details");
            return title;
        }
        if (Float.parseFloat(sscMarksObtained) > Integer.parseInt(sscTotalMarks)
                || Float.parseFloat(hscMarksObtained) > Integer.parseInt(hscTotalMarks)
                || (!isBachelor && Float.parseFloat(sscMarksObtained) > Integer.parseInt(sscTotalMarks))) {
            admission.utils.MessageBox.error(this, "Obtain marks are greater than total marks");
            return title;
        }

        dateOfBirth = DateUtility.getStringToDate(this.dateOfBirthFormattedTextField.getText().trim());
        chalanDate = DateUtility.getStringToDate(this.chalanDateFormattedTextField.getText().trim());
        if (forForeignPanel.isVisible()) {
            dated = DateUtility.getStringToDate(this.datedFormattedTextField.getText());
            visaNoDated = DateUtility.getStringToDate(this.visaNoDatedFormattedTextField.getText());
            dateOfValidity = DateUtility.getStringToDate(this.dateOfValidityFormattedTextField.getText());
            arrivalDateInPakistan = DateUtility.getStringToDate(this.arrivalDateinPakistanFormattedTextField.getText());
        }

        if (dateOfBirth == null || chalanDate == null) {
            admission.utils.MessageBox.info(this, "Date of Birth or Challan Date is not valid");
            return title;
        }
        if (forForeignPanel.isVisible()) {
            district = null;
        }

        List<AppliedCategory> acList = getSelectedCategories();
        if (acList.isEmpty()) {
            admission.utils.MessageBox.info(this, "No category selected");
            return title;
        }

        List<CandidateProgramOfStudy> appliedCposList = this.disciplineChoicesDialog.getAppliedCampusDisciplineList();
        if (appliedCposList.isEmpty()) {
            admission.utils.MessageBox.info(this, "No choice selected");
            return title;
        }

        candidate = new Candidate(null, country, religion, district, pt, ay, Integer.parseInt(seatNo), formSno, cnicNo, cnicOf, name, fathersName, surname, gender, area, dateOfBirth, placeOfBirth, permanentHomeAddress, presentPostelAddress, guardiansName, relationship, guardiansAddress, fathersOccupation, telephone, mobile, email, Integer.parseInt(challanFee), chalanDate, yearsDegree, remarks, testScore, percentage, deductionMarks, isObjection, objectionRemarks, null);
        try {
            Integer candidateId = DatabaseManager.addData(candidate);

            if (candidateId <= 0) {
                MessageBox.error(this, "Record not saved");
                return title;
            }
            candidate.setCandidateId(candidateId);

            for (Component comp : campusesPanel.getComponents()) {
                JCustomCheckBox cb = (JCustomCheckBox) comp;
                if (!cb.isSelected()) {
                    continue;
                }
                Campus campus = (Campus) cb.getValue();
                AppliedCampus appliedCampus = new AppliedCampus(candidate, campus, "");
                DatabaseManager.addData(appliedCampus);
            }

            for (AppliedCategory ac : acList) {
                ac.setCandidate(candidate);
                DatabaseManager.addData(ac);
            }

            if (forForeignPanel.isVisible()) {
                ForeignCandidate foreignCandidate = new ForeignCandidate(candidate, passportNo, placeOfIssue, dated, visaNo, visaNoDated, dateOfValidity, arrivalDateInPakistan, "");
                DatabaseManager.addData(foreignCandidate);
            }

            DatabaseManager.addData(new CredentialDetails(sscIssuer, candidate, sscGroupProgram, Integer.parseInt(sscTotalMarks), Float.parseFloat(sscMarksObtained), "", sscSeatNo, Integer.parseInt(sscYear), DetailOfEnum.SSC, ""));
            DatabaseManager.addData(new CredentialDetails(hscIssuer, candidate, hscGroupProgram, Integer.parseInt(hscTotalMarks), Float.parseFloat(hscMarksObtained), "", hscSeatNo, Integer.parseInt(hscYear), DetailOfEnum.HSC, ""));

            if (!isBachelor) {
                CredentialDetails grd = new CredentialDetails(graduationIssuer, candidate, grdGroupProgram, Integer.parseInt(graduationTotalMarks), Float.parseFloat(graduationMarksObtained), "", graduationSeatNo, Integer.parseInt(graduationYear), DetailOfEnum.GRADUATION, "");
                grd.setCredentialDetailsId(DatabaseManager.addData(grd));
//                DatabaseManager.refresh(graduationIssuer);
                if (this.subject1ComboBox.getSelectedItem() instanceof Subject) {
                    Subject subject = ((Subject) this.subject1ComboBox.getSelectedItem());
                    DatabaseManager.addData(new OptionalSubject(subject, grd));
                }
                if (this.subject2ComboBox.getSelectedItem() instanceof Subject) {
                    Subject subject = ((Subject) this.subject2ComboBox.getSelectedItem());
                    DatabaseManager.addData(new OptionalSubject(subject, grd));
                }
                if (this.subject3ComboBox.getSelectedItem() instanceof Subject) {
                    Subject subject = ((Subject) this.subject3ComboBox.getSelectedItem());
                    DatabaseManager.addData(new OptionalSubject(subject, grd));
                }
//                DatabaseManager.refresh(grd);
            }

            for (CandidateProgramOfStudy cmpos : appliedCposList) {
                cmpos.setCandidate(candidate);
                DatabaseManager.addData(cmpos);
            }
//            DatabaseManager.refresh(ay);
//            DatabaseManager.refresh(pt);
//            DatabaseManager.refresh(district);
//            DatabaseManager.refresh(sscIssuer);
//            DatabaseManager.refresh(hscIssuer);
//            DatabaseManager.refresh(candidate);
            if (!isBachelor) {
                if (graduationIssuer.getRemarks().startsWith("USINDH")) {
                    deductionMarks = DeductionCalculation.forMasterUSindh(ay.getYear(), candidate);
                } else {
                    deductionMarks = DeductionCalculation.forMasterOther(ay.getYear(), candidate);
                }
                candidate.setDeductionMarks(deductionMarks);
            } else {
                deductionMarks = DeductionCalculation.forBachalor(ay.getYear(), candidate);
                candidate.setDeductionMarks(deductionMarks);
            }
            percentage = CandidateHelper.getPercentage(candidate);
            candidate.setPercentage(percentage);
            DatabaseManager.updateData(candidate);
            DatabaseManager.refresh(candidate);
            LoggerHelper.addLog(Candidate.class, candidate.getCandidateId());

            title = ay.getYear() + "-" + pt.getName().charAt(0) + "-" + candidate.getSeatNo();
            admission.utils.MessageBox.info(this, MessageEnum.MSG_SAVE);
            loggerDetailLabel.setText("<html><p align=right>Entered by: " + CommonHelper.getActiveUserName() + "<br>Entered date: " + Utility.dateFormat(new Date().getTime()));
            seatNoTextField.requestFocus();
        } catch (HibernateException | NumberFormatException | HeadlessException he) {
            Logger.getLogger(AdmissionFormPanel.class.getName()).log(Level.SEVERE, null, he);
            admission.utils.MessageBox.error(this, he);
            title = null;
        }
        return title;
    }

    private void update() {
        AdmissionYear ay = (AdmissionYear) this.admissionYearComboBox.getSelectedItem();
        ProgramType pt = (ProgramType) this.programTypeComboBox.getSelectedItem();
        District district = (District) this.districtComboBox.getSelectedItem();
        if (district == null || ay == null || pt == null) {
            return;
        }

        String candidateId = this.candidateIdTextField.getText();
        if (candidateId.isEmpty()) {
            admission.utils.MessageBox.info(this, MessageEnum.MSG_NO_FOUND);
            return;
        }

        boolean b = (pt.getIsBachelor());

        String seatNo = this.seatNoTextField.getText();

        if (!seatNo.equals("" + candidate.getSeatNo())) {
            Candidate c = DatabaseManager.getSingleRecord(Candidate.class, "admissionYear.admissionYearId = " + ay.getAdmissionYearId() + " AND programType.programTypeId = " + pt.getProgramTypeId() + " AND seatNo = " + seatNo);
            if (c != null) {
                admission.utils.MessageBox.info(this, MessageEnum.MSG_14);
                return;
            }
        }

        String formSno = this.formNoTextField.getText().isEmpty() ? "0" : this.formNoTextField.getText();
        String cnicNo = this.cnicFormattedTextField.getText();
        String cnicOf = Coder.Encoder.encode(this.cnicOfComboBox.getSelectedItem().toString());
        String name = this.nameTextField.getText();
        String fathersName = this.fathersNameTextField.getText();
        String surname = this.surnameTextField.getText();
        GenderEnum gender = (GenderEnum) this.genderComboBox.getSelectedItem();
        AreaEnum area = (AreaEnum) this.areaComboBox.getSelectedItem();
        String permanentHomeAddress = this.permanentHomeAddressTextField.getText();
        String presentPostelAddress = this.presentPostelAddressTextField.getText();
        Country country = (Country) this.nationalityComboBox.getSelectedItem();
        if (country == null) {
            return;
        }
        Religion religion = (Religion) this.religionComboBox.getSelectedItem();
        if (religion == null) {
            return;
        }
        Date dateOfBirth;
        String placeOfBirth = this.placeOfBirthTextField.getText();
        String fathersOccupation = ((JTextComponent) fathersOrGuardiansOccupationComboBox.getEditor().getEditorComponent()).getText();//this.fathersOrGuardiansOccupationComboBox.getSelectedItem().toString();
        String guardiansName = this.guardiansNameTextField.getText();
        String relationship = this.relationshipComboBox.getSelectedItem().toString();
        String guardiansAddress = this.guardiansAddressTextField.getText();
        String telephone = this.telephoneTextField.getText();
        String mobile = this.mobileTextField.getText();
        String email = this.emailTextField.getText();
        Issuer sscIssuer = (Issuer) this.sscBoardComboBox.getSelectedItem();
        String sscTotalMarks = this.sscTotalMarksTextField.getText();
        String sscMarksObtained = this.sscMarksObtainedTextField.getText();
        String sscYear = this.sscYearComboBox.getSelectedItem().toString();//((JTextComponent) sscYearComboBox.getEditor().getEditorComponent()).getText();
        String sscSeatNo = this.sscSeatNoTextField.getText();
        Program sscGroupProgram = (Program) sscGroupComboBox.getSelectedItem();
        Issuer hscIssuer = (Issuer) this.hscBoardComboBox.getSelectedItem();
        String hscTotalMarks = this.hscTotalMarksTextField.getText();
        String hscMarksObtained = this.hscMarksObtainedTextField.getText();
        String hscYear = this.hscYearComboBox.getSelectedItem().toString();//((JTextComponent) hscYearComboBox.getEditor().getEditorComponent()).getText();
        String hscSeatNo = this.hscSeatNoTextField.getText();
        Program hscGroupProgram = (Program) hscGroupComboBox.getSelectedItem();
        Issuer graduationIssuer = (Issuer) this.grdUniComboBox.getSelectedItem();
        String graduationTotalMarks = this.graduationTotalMarksTextField.getText();
        String graduationMarksObtained = this.grdMarksObtainedFormattedTextField.getText();
        String graduationYear = this.grdYearComboBox.getSelectedItem().toString();//((JTextComponent) grdYearComboBox.getEditor().getEditorComponent()).getText();
        String graduationSeatNo = this.graduationSeatNoTextField.getText();
        Program grdGroupProgram = (Program) grdGroupComboBox.getSelectedItem();
        Integer chalanFee = Integer.parseInt(this.chalanFeeTextField.getText().trim());
        Date chalanDate;
        Integer yearsDegree = b ? null : Integer.parseInt(this.yearsDegreeComboBox.getSelectedItem().toString());
        String objectionRemarks = this.objectRemarksTextField.getText();
        String remarks = this.remarksTextField.getText();
        boolean isObjection = (isObjectionCheckBox.isSelected());
        Integer testScore = Integer.parseInt(testScoreTextField.getText());

        // For foreign
        String passportNo = this.passportNoTextField.getText();
        String placeOfIssue = this.placeOfIssueTextField.getText();
        Date dated = null;
        String visaNo = this.visaNoTextField.getText();
        Date visaNoDated = null;
        Date dateOfValidity = null;
        Date arrivalDateInPakistan = null;

        if (seatNo.isEmpty()) {
            admission.utils.MessageBox.error(this, MessageEnum.MSG_02);
            return;
        } else if (sscIssuer == null || hscIssuer == null) {
            admission.utils.MessageBox.error(this, MessageEnum.MSG_03);
            return;
        } else if (b & graduationIssuer == null) {
            admission.utils.MessageBox.error(this, MessageEnum.MSG_04);
            return;
        } else if (remarks.isEmpty()) {
            admission.utils.MessageBox.error(this, "Challan no field is empty");
        }

        if (sscTotalMarks.isEmpty() || sscMarksObtained.isEmpty() || sscYear.isEmpty() || sscSeatNo.isEmpty()
                || hscTotalMarks.isEmpty() || hscMarksObtained.isEmpty() || hscYear.isEmpty() || hscSeatNo.isEmpty()) {
            admission.utils.MessageBox.error(this, MessageEnum.MSG_05);
            return;
        }
        if (!b && (graduationTotalMarks.isEmpty() || graduationMarksObtained.isEmpty() || graduationYear.isEmpty() || graduationSeatNo.isEmpty())) {
            admission.utils.MessageBox.error(this, MessageEnum.MSG_05);
            return;
        }

        if (Float.parseFloat(sscMarksObtained) > Integer.parseInt(sscTotalMarks)
                || Float.parseFloat(hscMarksObtained) > Integer.parseInt(hscTotalMarks)
                || (!b && Float.parseFloat(sscMarksObtained) > Integer.parseInt(sscTotalMarks))) {
            admission.utils.MessageBox.error(this, MessageEnum.MSG_28);
            return;
        }

        List<AppliedCategory> acList = getSelectedCategories();
        if (acList.isEmpty()) {
            admission.utils.MessageBox.info(this, "No category selected");
            return;
        }
        List<CandidateProgramOfStudy> cposList = this.disciplineChoicesDialog.getAppliedCampusDisciplineList();
        if (cposList.isEmpty()) {
            admission.utils.MessageBox.info(this, "No choice selected");
            return;
        }

        dateOfBirth = DateUtility.getStringToDate(this.dateOfBirthFormattedTextField.getText().trim());
        chalanDate = DateUtility.getStringToDate(this.chalanDateFormattedTextField.getText().trim());
        if (forForeignPanel.isVisible()) {
            dated = DateUtility.getStringToDate(this.datedFormattedTextField.getText());
            visaNoDated = DateUtility.getStringToDate(this.visaNoDatedFormattedTextField.getText());
            dateOfValidity = DateUtility.getStringToDate(this.dateOfValidityFormattedTextField.getText());
            arrivalDateInPakistan = DateUtility.getStringToDate(this.arrivalDateinPakistanFormattedTextField.getText());
        }

        //Candidate candidate = new Candidate(programType, district, admissionYear, taluka, religion, country, Integer.parseInt(seatNo), Integer.parseInt(formSno), cnicNo, cnicOf, name, fathersName, surname, gender, area, dateOfBirth, placeOfBirth, permanentHomeAddress, presentPostelAddress, guardiansName, guardiansRelationship, guardiansAddress, fathersOccupation, telephone, mobile, email, chalanFee, chalanDate, amountRs, subject1, subject2, subject3, null, remarks, null, null, null, null, null, null, null, null);
        //        candidate.setCandidateId(Integer.parseInt(candidateId));
        candidate.setSeatNo(Integer.parseInt(seatNo));
        candidate.setFormSno(Integer.parseInt(formSno));
        candidate.setCnicNo(cnicNo);
        candidate.setCnicOf(cnicOf);
        candidate.setName(name);
        candidate.setFathersName(fathersName);
        candidate.setSurname(surname);
        candidate.setGender(gender);
        candidate.setDistrict(district);
        candidate.setArea(area);
        candidate.setPermanentHomeAddress(permanentHomeAddress);
        candidate.setPresentPostelAddress(presentPostelAddress);
        candidate.setCountry(country);
        candidate.setReligion(religion);
        candidate.setDateOfBirth(dateOfBirth);
        candidate.setPlaceOfBirth(placeOfBirth);
        candidate.setFathersOccupation(fathersOccupation);
        candidate.setGuardiansName(guardiansName);
        candidate.setRelationship(relationship);
        candidate.setGuardiansAddress(guardiansAddress);
        candidate.setTelephone(telephone);
        candidate.setMobile(mobile);
        candidate.setEmail(email);
        candidate.setChalanFee(chalanFee);
        candidate.setChalanDate(chalanDate);
        candidate.setYearsDegree(yearsDegree);
        candidate.setRemarks(remarks);
        candidate.setIsObjection(isObjection);
        candidate.setTestScore(testScore);
        candidate.setObjectionRemarks(objectionRemarks);

        try {
            DatabaseManager.updateData(candidate);

            DatabaseManager.deleteData(AppliedCampus.class, "candidate.candidateId = " + candidate.getCandidateId());
            for (Component comp : campusesPanel.getComponents()) {
                JCustomCheckBox cb = (JCustomCheckBox) comp;
                if (!cb.isSelected()) {
                    continue;
                }
                Campus campus = (Campus) cb.getValue();
                AppliedCampus appliedCampus = new AppliedCampus(candidate, campus, "");
                DatabaseManager.addData(appliedCampus);
            }

            DatabaseManager.deleteData(AppliedCategory.class, "candidate.candidateId = " + candidate.getCandidateId());
            for (AppliedCategory ac : acList) {
                ac.setCandidate(candidate);
                DatabaseManager.addData(ac);
            }

            if (forForeignPanel.isVisible()) {
                ForeignCandidate fc = DatabaseManager.getSingleRecord(ForeignCandidate.class, "candidate.candidateId = " + candidate.getCandidateId());
                if (fc == null) {
                    fc = new ForeignCandidate(candidate);
                }
                fc.setPassportNo(passportNo);
                fc.setPlaceOfIssue(placeOfIssue);
                fc.setDated(dated);
                fc.setVisaNo(visaNo);
                fc.setVisaNoDated(visaNoDated);
                fc.setDateOfValidity(dateOfValidity);
                fc.setArrivalDateInPakistan(arrivalDateInPakistan);

                DatabaseManager.save(fc);
            }

            List<CredentialDetails> credentialDetailsList = DatabaseManager.getData(CredentialDetails.class, "candidate.candidateId = " + candidate.getCandidateId(), "detailOf");
//            Set set = candidate.getCredentialDetailses();
//            Object[] array = set.toArray();

            CredentialDetails ssc = credentialDetailsList.get(0);
            CredentialDetails hsc = credentialDetailsList.get(1);

            ssc.setIssuer(sscIssuer);
            ssc.setProgram(sscGroupProgram);
            ssc.setTotalMarks(Integer.parseInt(sscTotalMarks));
            ssc.setMarksObtained(Float.parseFloat(sscMarksObtained));
            ssc.setPassingYear(Integer.parseInt(sscYear));
            ssc.setSeatNo(sscSeatNo);

            hsc.setIssuer(hscIssuer);
            hsc.setProgram(hscGroupProgram);
            hsc.setTotalMarks(Integer.parseInt(hscTotalMarks));
            hsc.setMarksObtained(Float.parseFloat(hscMarksObtained));
            hsc.setPassingYear(Integer.parseInt(hscYear));
            hsc.setSeatNo(hscSeatNo);

            DatabaseManager.updateData(ssc);
            DatabaseManager.updateData(hsc);

            if (!b && credentialDetailsList.size() == 3) {
                CredentialDetails grd = credentialDetailsList.get(2);
                grd.setIssuer(graduationIssuer);
                grd.setProgram(grdGroupProgram);
                grd.setTotalMarks(Integer.parseInt(graduationTotalMarks));
                grd.setMarksObtained(Float.parseFloat(graduationMarksObtained));
                grd.setPassingYear(Integer.parseInt(graduationYear));
                grd.setSeatNo(graduationSeatNo);

                DatabaseManager.save(grd);

                CandidateHelper.optionalSubjectModel(grd, this.subject1ComboBox.getSelectedItem(), 0);
                CandidateHelper.optionalSubjectModel(grd, this.subject2ComboBox.getSelectedItem(), 1);
                CandidateHelper.optionalSubjectModel(grd, this.subject3ComboBox.getSelectedItem(), 2);

                DatabaseManager.refresh(grd);
            } else if (!b) {
                CredentialDetails grd = new CredentialDetails(graduationIssuer, candidate, grdGroupProgram, Integer.parseInt(graduationTotalMarks), Float.parseFloat(graduationMarksObtained), "", graduationSeatNo, Integer.parseInt(graduationYear), DetailOfEnum.GRADUATION, "");
                DatabaseManager.addData(grd);
                DatabaseManager.refresh(graduationIssuer);
                CandidateHelper.optionalSubjectModel(grd, this.subject1ComboBox.getSelectedItem(), 0);
                CandidateHelper.optionalSubjectModel(grd, this.subject2ComboBox.getSelectedItem(), 1);
                CandidateHelper.optionalSubjectModel(grd, this.subject3ComboBox.getSelectedItem(), 2);
            }

            if (JOptionPane.showConfirmDialog(this, MessageEnum.MSG_01, "Message", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
                DatabaseManager.deleteData(CandidateProgramOfStudy.class, "candidate.candidateId = " + candidate.getCandidateId());
                for (CandidateProgramOfStudy cmpos : cposList) {
                    cmpos.setCandidate(candidate);
                    DatabaseManager.save(cmpos);
                }

            }
            DatabaseManager.refresh(ay);
            DatabaseManager.refresh(pt);
            DatabaseManager.refresh(district);
            DatabaseManager.refresh(sscIssuer);
            DatabaseManager.refresh(hscIssuer);
            DatabaseManager.refresh(candidate);

            Integer deductionMarks = 0;
            Float percentage;

            if (!b) {
                if (graduationIssuer.getRemarks().startsWith("USINDH")) {
                    deductionMarks = DeductionCalculation.forMasterUSindh(ay.getYear(), candidate);
                } else {
                    deductionMarks = DeductionCalculation.forMasterOther(ay.getYear(), candidate);
                }
                candidate.setDeductionMarks(deductionMarks);
            } else {
                deductionMarks = DeductionCalculation.forBachalor(ay.getYear(), candidate);
                candidate.setDeductionMarks(deductionMarks);
            }
            percentage = CandidateHelper.getPercentage(candidate);
            candidate.setPercentage(percentage);
            DatabaseManager.updateData(candidate);
            DatabaseManager.refresh(candidate);

            LoggerHelper.updateLog(Candidate.class, candidate.getCandidateId());
            admission.utils.MessageBox.info(this, MessageEnum.MSG_SAVE);
            seatNoTextField.requestFocus();
//            this.programOfStudyChoicesDialog.clearAll();
//            clear();
        } catch (HibernateException | NumberFormatException | HeadlessException he) {
            Logger.getLogger(AdmissionFormPanel.class.getName()).log(Level.SEVERE, null, he);
            admission.utils.MessageBox.error(this, he);
        }
    }

    public void delete() {
        String candidateId = this.candidateIdTextField.getText();
        if (candidateId.isEmpty()) {
            return;
        }

        try {
            DatabaseManager.deleteData(Candidate.class.getName(), "candidateId=" + Integer.valueOf(candidateId));

            admission.utils.MessageBox.info(this, MessageEnum.MSG_DELETE);
            clear();
        } catch (HibernateException he) {
            Logger.getLogger(AdmissionFormPanel.class.getName()).log(Level.SEVERE, null, he);
            admission.utils.MessageBox.error(this, he);
        }
    }

    private void setCampusSelected(List<AppliedCampus> list, Candidate candidate) {
        clearAllCampusSelection();

        for (AppliedCampus ac : list) {
            for (Component comp : campusesPanel.getComponents()) {
                JCustomCheckBox item = (JCustomCheckBox) comp;
                Campus campus = (Campus) item.getValue();
                if (ac.getCampus().equals(campus)) {
                    item.setSelected(true);
                    addChoicePanel((JCheckBox) item, candidate);
                }
            }
        }
    }

    private void setCategorySelected(List<AppliedCategory> list) {
        this.clearCategorySelection();

        for (AppliedCategory ac : list) {
            CategoryEnum ce = ac.getCategory();

            if (generalMeritCheckBox.getName().equals(ce.getTitle())) {
                generalMeritCheckBox.setSelected(true);
            } else if (selfFinanceEveningCheckBox.getName().equals(ce.getTitle())) {
                selfFinanceEveningCheckBox.setSelected(true);
            } else if (selfFinanceMorningCheckBox.getName().equals(ce.getTitle())) {
                selfFinanceMorningCheckBox.setSelected(true);
            } else if (suEmployeesQuotaCheckBox.getName().equals(ce.getTitle())) {
                suEmployeesQuotaCheckBox.setSelected(true);
                sueQuotaComboBox.setEnabled(true);
                sueQuotaComboBox.setSelectedItem(ac.getRemarks());
            } else if (nominationQuotaCheckBox.getName().equals(ce.getTitle())) {
                nominationQuotaCheckBox.setSelected(true);
            } else if (disabledPersonQuotaCheckBox.getName().equals(ce.getTitle())) {
                disabledPersonQuotaCheckBox.setSelected(true);
            } else if (affiliatedCollegeQuotaCheckBox.getName().equals(ce.getTitle())) {
                affiliatedCollegeQuotaCheckBox.setSelected(true);
            } else if (sportsQuotaCheckBox.getName().equals(ce.getTitle())) {
                sportsQuotaCheckBox.setSelected(true);
            } else if (foreignQuotaCheckBox.getName().equals(ce.getTitle())) {
                foreignQuotaCheckBox.setSelected(true);
                this.forForeignPanel.setVisible(foreignQuotaCheckBox.isSelected());
            } else if (armyPersonalQuotaCheckBox.getName().equals(ce.getTitle())) {
                armyPersonalQuotaCheckBox.setSelected(true);
            }else if (karachiQuotaCheckBox.getName().equals(ce.getTitle())){
            karachiQuotaCheckBox.setSelected(true);
            }
        }
    }

    private void getSubject() {
        subject1ComboBox.removeAllItems();
        subject2ComboBox.removeAllItems();
        subject3ComboBox.removeAllItems();
        subject1ComboBox.addItem("");
        subject2ComboBox.addItem("");
        subject3ComboBox.addItem("");

        Program p = (Program) grdGroupComboBox.getSelectedItem();
        if (p == null) {
            return;
        }

        yearsDegreeComboBox.setSelectedItem("" + p.getPeriods());

        String hql = "SELECT s FROM ProgramSubject ps "
                + "INNER JOIN ps.subject s "
                + "INNER JOIN ps.program p "
                + "WHERE p.programId = " + p.getProgramId();

        List<Subject> list = DatabaseManager.executeQuery(Subject.class, hql);
        Sorter.listSort(list);
        for (Subject sb : list) {
            subject1ComboBox.addItem(sb);
            subject2ComboBox.addItem(sb);
            subject3ComboBox.addItem(sb);
        }
    }

    private void setGraduationComponentEnabled(boolean b) {
        this.grdUniComboBox.setEnabled(b);
        this.graduationTotalMarksTextField.setEnabled(b);
        this.grdGroupComboBox.setEnabled(b);
        this.grdMarksObtainedFormattedTextField.setEnabled(b);
        this.graduationSeatNoTextField.setEnabled(b);
        this.grdYearComboBox.setEnabled(b);
        this.yearsDegreeComboBox.setEnabled(b);
        this.subject1ComboBox.setEnabled(b);
        this.subject2ComboBox.setEnabled(b);
        this.subject3ComboBox.setEnabled(b);
    }

    private void addChoicePanel(JCheckBox cb, Candidate candidate) {
        ProgramType pt = (ProgramType) this.programTypeComboBox.getSelectedItem();
        if (pt == null) {
            return;
        }
//        Program program;
//        if (programType.getIsBachelor()) {
//            program = (Program) hscGroupComboBox.getSelectedItem();
//        } else {
//            program = (Program) grdGroupComboBox.getSelectedItem();
//        }
//        if (program == null) {
//            return;
//        }

        JCustomCheckBox item = (JCustomCheckBox) cb;
        if (cb.isSelected()) {
            this.disciplineChoicesDialog.addChoicePanel(pt, (Campus) item.getValue(), candidate);
        } else {
            this.disciplineChoicesDialog.removeChoicePanel(item.getText());
        }
    }

    private List<AppliedCategory> getSelectedCategories() {
        List<AppliedCategory> v = new ArrayList<>();
        if (generalMeritCheckBox.isSelected()) {
            CategoryEnum ce = CategoryEnum.valueOf(generalMeritCheckBox.getName());
            AppliedCategory ac = new AppliedCategory(ce.getCode(), ce, "");
            v.add(ac);
//            v.add(CategoryEnum.valueOf(generalMeritCheckBox.getName()));
        }
        if (karachiQuotaCheckBox.isSelected()) {
            CategoryEnum ce = CategoryEnum.KHI_RES_QUOTA;
            AppliedCategory ac = new AppliedCategory(ce.getCode(), ce, "");
            v.add(ac);
//            v.add(CategoryEnum.valueOf(generalMeritCheckBox.getName()));
        }
        if (selfFinanceMorningCheckBox.isSelected()) {
            CategoryEnum ce = CategoryEnum.valueOf(selfFinanceMorningCheckBox.getName());
            AppliedCategory ac = new AppliedCategory(ce.getCode(), ce, "");
            v.add(ac);
//            v.add(CategoryEnum.valueOf(selfFinanceMorningCheckBox.getName()));
        }
        if (selfFinanceEveningCheckBox.isSelected()) {
            CategoryEnum ce = CategoryEnum.valueOf(selfFinanceEveningCheckBox.getName());
            AppliedCategory ac = new AppliedCategory(ce.getCode(), ce, "");
            v.add(ac);
//            v.add(CategoryEnum.valueOf(selfFinanceEveningCheckBox.getName()));
        }
        if (suEmployeesQuotaCheckBox.isSelected()) {
            CategoryEnum ce = CategoryEnum.valueOf(suEmployeesQuotaCheckBox.getName());
            AppliedCategory ac = new AppliedCategory(ce.getCode(), ce, sueQuotaComboBox.getSelectedItem().toString());
            v.add(ac);
//            v.add(CategoryEnum.valueOf(suEmployeesQuotaCheckBox.getName() + " " + sueQuotaComboBox.getSelectedItem().toString()));
        }
        if (disabledPersonQuotaCheckBox.isSelected()) {
            CategoryEnum ce = CategoryEnum.valueOf(disabledPersonQuotaCheckBox.getName());
            AppliedCategory ac = new AppliedCategory(ce.getCode(), ce, "");
            v.add(ac);
//            v.add(CategoryEnum.valueOf(disabledPersonQuotaCheckBox.getName()));
        }
        if (nominationQuotaCheckBox.isSelected()) {
            CategoryEnum ce = CategoryEnum.valueOf(nominationQuotaCheckBox.getName());
            AppliedCategory ac = new AppliedCategory(ce.getCode(), ce, "");
            v.add(ac);
//            v.add(CategoryEnum.valueOf(nominationQuotaCheckBox.getName()));
        }
        if (affiliatedCollegeQuotaCheckBox.isSelected()) {
            CategoryEnum ce = CategoryEnum.valueOf(affiliatedCollegeQuotaCheckBox.getName());
            AppliedCategory ac = new AppliedCategory(ce.getCode(), ce, "");
            v.add(ac);
//            v.add(CategoryEnum.valueOf(affiliatedCollegeQuotaCheckBox.getName()));
        }
        if (sportsQuotaCheckBox.isSelected()) {
            CategoryEnum ce = CategoryEnum.valueOf(sportsQuotaCheckBox.getName());
            AppliedCategory ac = new AppliedCategory(ce.getCode(), ce, "");
            v.add(ac);
//            v.add(CategoryEnum.valueOf(sportsQuotaCheckBox.getName()));
        }
        if (foreignQuotaCheckBox.isSelected()) {
            CategoryEnum ce = CategoryEnum.valueOf(foreignQuotaCheckBox.getName());
            AppliedCategory ac = new AppliedCategory(ce.getCode(), ce, "");
            v.add(ac);
//            v.add(CategoryEnum.valueOf(foreignQuotaCheckBox.getName()));
        }
        if (armyPersonalQuotaCheckBox.isSelected()) {
            CategoryEnum ce = CategoryEnum.valueOf(armyPersonalQuotaCheckBox.getName());
            AppliedCategory ac = new AppliedCategory(ce.getCode(), ce, "");
            v.add(ac);
//            v.add(CategoryEnum.valueOf(armyPersonalQuotaCheckBox.getName()));
        }
        return v;
    }

    private void clear() {
        candidate = null;
        photoButton.setVisible(false);
        this.candidateIdTextField.setText("");
        this.seatNoTextField.setText("");
        this.formNoTextField.setText("");
        this.programTypeComboBox.setSelectedItem("");
        this.cnicFormattedTextField.setText("");
        this.cnicOfComboBox.setSelectedItem("");
        this.religionComboBox.setSelectedIndex(0);
        this.divisionTextField.setText("");
        this.provinceTextField.setText("");
        this.nameTextField.setText("");
        this.fathersNameTextField.setText("");
        this.surnameTextField.setText("");
        this.genderComboBox.setSelectedIndex(0);
        this.areaComboBox.setSelectedIndex(0);
        this.dateOfBirthFormattedTextField.setText("");
        this.placeOfBirthTextField.setText("");
        this.permanentHomeAddressTextField.setText("");
        this.presentPostelAddressTextField.setText("");
        this.guardiansNameTextField.setText("");
        this.guardiansAddressTextField.setText("");
        this.fathersOrGuardiansOccupationComboBox.setSelectedIndex(0);
        this.telephoneTextField.setText("");
        this.mobileTextField.setText("");
        this.emailTextField.setText("");
        this.sscBoardComboBox.setSelectedIndex(0);
        this.sscGroupComboBox.setSelectedIndex(0);
        this.sscMarksObtainedTextField.setText("");
        this.sscYearComboBox.setSelectedIndex(0);
        this.sscSeatNoTextField.setText("");
        this.hscBoardComboBox.setSelectedIndex(0);
        this.hscGroupComboBox.setSelectedIndex(0);
        this.hscMarksObtainedTextField.setText("");
        this.hscYearComboBox.setSelectedIndex(0);
        this.hscSeatNoTextField.setText("");
        this.grdUniComboBox.setSelectedIndex(0);
        this.grdGroupComboBox.setSelectedIndex(0);
        this.grdMarksObtainedFormattedTextField.setText("");
        this.grdYearComboBox.setSelectedIndex(0);
        this.graduationSeatNoTextField.setText("");
        this.graduationTotalMarksTextField.setText("0");
        this.yearsDegreeComboBox.setSelectedItem("2");
        this.chalanFeeTextField.setText("");
        this.chalanDateFormattedTextField.setText("");
        this.isObjectionCheckBox.setSelected(false);
        this.testScoreTextField.setText("0");
        this.objectRemarksTextField.setText("");
        
        this.noSscMarksCheckBox.setSelected(false);
        this.noSscPassCheckBox.setSelected(false);
        this.noHscMarksCheckBox.setSelected(false);
        this.noHscPassCheckBox.setSelected(false);
        this.noGrdMarksCheckBox.setSelected(false);
        this.noGrdPassCheckBox.setSelected(false);
        this.noDomicileCheckBox.setSelected(false);
        this.noAffidavitCheckBox.setSelected(false);
        
        
        this.finalPercentageTextField.setText("0");
        this.clearCategorySelection();
        clearAllCampusSelection();
        this.disciplineChoicesDialog.clearAll();
        this.seatNoTextField.requestFocus();
        loggerDetailLabel.setText("");
    }

    private void clearCategorySelection() {
        this.generalMeritCheckBox.setSelected(false);
        this.selfFinanceEveningCheckBox.setSelected(false);
        this.selfFinanceMorningCheckBox.setSelected(false);
        this.suEmployeesQuotaCheckBox.setSelected(false);
        this.sueQuotaComboBox.setEnabled(false);
        this.nominationQuotaCheckBox.setSelected(false);
        this.disabledPersonQuotaCheckBox.setSelected(false);
        this.affiliatedCollegeQuotaCheckBox.setSelected(false);
        this.sportsQuotaCheckBox.setSelected(false);
        this.foreignQuotaCheckBox.setSelected(false);
        this.armyPersonalQuotaCheckBox.setSelected(false);
        this.karachiQuotaCheckBox.setSelected(false);
    }

    private void clearAllCampusSelection() {
        disciplineChoicesDialog.clearAll();
        for (Component comp : campusesPanel.getComponents()) {
            JCustomCheckBox cb = (JCustomCheckBox) comp;
            cb.setSelected(false);
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox admissionYearComboBox;
    private javax.swing.JCheckBox affiliatedCollegeQuotaCheckBox;
    private javax.swing.JComboBox areaComboBox;
    private javax.swing.JCheckBox armyPersonalQuotaCheckBox;
    private javax.swing.JFormattedTextField arrivalDateinPakistanFormattedTextField;
    private javax.swing.JTextField bankNameTextField;
    private javax.swing.JPanel campusesPanel;
    private javax.swing.JTextField candidateIdTextField;
    private javax.swing.JFormattedTextField chalanDateFormattedTextField;
    private javax.swing.JTextField chalanFeeTextField;
    private javax.swing.JFormattedTextField cnicFormattedTextField;
    private javax.swing.JComboBox cnicOfComboBox;
    private javax.swing.JFormattedTextField dateOfBirthFormattedTextField;
    private javax.swing.JFormattedTextField dateOfValidityFormattedTextField;
    private javax.swing.JFormattedTextField datedFormattedTextField;
    private javax.swing.JButton deleteButton;
    private javax.swing.JCheckBox disabledPersonQuotaCheckBox;
    private javax.swing.JButton disciplineChoiceButton;
    private javax.swing.JComboBox districtComboBox;
    private javax.swing.JTextField divisionTextField;
    private javax.swing.JTextField emailTextField;
    private javax.swing.JTextField fathersNameTextField;
    private javax.swing.JComboBox fathersOrGuardiansOccupationComboBox;
    private javax.swing.JFileChooser fileChooser;
    private javax.swing.JTextField finalPercentageTextField;
    private javax.swing.JPanel forForeignPanel;
    private javax.swing.JCheckBox foreignQuotaCheckBox;
    private javax.swing.JTextField formNoTextField;
    private javax.swing.JComboBox genderComboBox;
    private javax.swing.JCheckBox generalMeritCheckBox;
    private javax.swing.JTextField graduationSeatNoTextField;
    private javax.swing.JTextField graduationTotalMarksTextField;
    private javax.swing.JComboBox grdGroupComboBox;
    private javax.swing.JFormattedTextField grdMarksObtainedFormattedTextField;
    private javax.swing.JComboBox grdUniComboBox;
    private javax.swing.JComboBox grdYearComboBox;
    private javax.swing.JTextField guardiansAddressTextField;
    private javax.swing.JTextField guardiansNameTextField;
    private javax.swing.JComboBox hscBoardComboBox;
    private javax.swing.JComboBox hscGroupComboBox;
    private javax.swing.JTextField hscMarksObtainedTextField;
    private javax.swing.JTextField hscSeatNoTextField;
    private javax.swing.JTextField hscTotalMarksTextField;
    private javax.swing.JComboBox hscYearComboBox;
    private javax.swing.JCheckBox isObjectionCheckBox;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
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
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private final javax.swing.JScrollPane jScrollPane3 = new javax.swing.JScrollPane();
    private javax.swing.JSeparator jSeparator10;
    private javax.swing.JSeparator jSeparator11;
    private javax.swing.JSeparator jSeparator12;
    private javax.swing.JSeparator jSeparator13;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JSeparator jSeparator9;
    private javax.swing.JCheckBox karachiQuotaCheckBox;
    private javax.swing.JLabel loggerDetailLabel;
    private javax.swing.JTextField mobileTextField;
    private javax.swing.JTextField nameTextField;
    private javax.swing.JComboBox nationalityComboBox;
    private javax.swing.JCheckBox noAffidavitCheckBox;
    private javax.swing.JCheckBox noDomicileCheckBox;
    private javax.swing.JCheckBox noGrdMarksCheckBox;
    private javax.swing.JCheckBox noGrdPassCheckBox;
    private javax.swing.JCheckBox noHscMarksCheckBox;
    private javax.swing.JCheckBox noHscPassCheckBox;
    private javax.swing.JCheckBox noSscMarksCheckBox;
    private javax.swing.JCheckBox noSscPassCheckBox;
    private javax.swing.JCheckBox nominationQuotaCheckBox;
    private javax.swing.JTextField objectRemarksTextField;
    private javax.swing.JPanel objectionRemarksCheckboxPanel;
    private javax.swing.JTextField passportNoTextField;
    private javax.swing.JTextField permanentHomeAddressTextField;
    private javax.swing.JButton photoButton;
    private javax.swing.JDialog photoDialog;
    private javax.swing.JLabel photoLabel;
    private javax.swing.JTextField placeOfBirthTextField;
    private javax.swing.JTextField placeOfIssueTextField;
    private javax.swing.JTextField presentPostelAddressTextField;
    private javax.swing.JComboBox programTypeComboBox;
    private javax.swing.JTextField provinceTextField;
    private javax.swing.JButton reLoadButton;
    private javax.swing.JComboBox relationshipComboBox;
    private javax.swing.JComboBox religionComboBox;
    private javax.swing.JTextField remarksTextField;
    private javax.swing.JTextField seatNoTextField;
    private javax.swing.JCheckBox selfFinanceEveningCheckBox;
    private javax.swing.JCheckBox selfFinanceMorningCheckBox;
    private javax.swing.JCheckBox sportsQuotaCheckBox;
    private javax.swing.JComboBox sscBoardComboBox;
    private javax.swing.JComboBox sscGroupComboBox;
    private javax.swing.JTextField sscMarksObtainedTextField;
    private javax.swing.JTextField sscSeatNoTextField;
    private javax.swing.JTextField sscTotalMarksTextField;
    private javax.swing.JComboBox sscYearComboBox;
    private javax.swing.JCheckBox suEmployeesQuotaCheckBox;
    private javax.swing.JComboBox subject1ComboBox;
    private javax.swing.JComboBox subject2ComboBox;
    private javax.swing.JComboBox subject3ComboBox;
    private javax.swing.JComboBox sueQuotaComboBox;
    private javax.swing.JTextField surnameTextField;
    private javax.swing.JTextField telephoneTextField;
    private javax.swing.JTextField testScoreTextField;
    private javax.swing.JPanel titlePanel;
    private javax.swing.JFormattedTextField visaNoDatedFormattedTextField;
    private javax.swing.JTextField visaNoTextField;
    private javax.swing.JComboBox yearsDegreeComboBox;
    // End of variables declaration//GEN-END:variables
    private DocumentFilter documentFilter;
    private DocumentFilter numberDocumentFilter;
    private DisciplineChoicesDialog disciplineChoicesDialog;
    private Resources privileges;
    private Candidate candidate;
}
