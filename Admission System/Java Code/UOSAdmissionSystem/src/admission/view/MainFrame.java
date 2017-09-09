package admission.view;

import admission.helpers.CommonHelper;
import admission.model.security.User;
import admission.reports.AllReportsDialog;
import admission.reports.BankSelectionReportInternalFrame;
import admission.reports.ExportCandidatesDetailInternalFrame;
import admission.reports.FinanceReportInternalFrame;
import admission.reports.HecReportInternalFrame;
import admission.reports.HostelReportInternalFrame;
import admission.reports.ReportViewerInternalFrame;
import admission.reports.ReportViewerInternalFrame2;
import admission.services.KeyConstant;
import admission.view.accounts.AccountInternalFrame;
import admission.view.admission.AdmissionDetailInternalFrame;
import admission.view.admission.AdmissionFormInternalFrame;
import admission.view.admission.AdmissionListInternalFrame;
import admission.view.admission.AdmissionSessionInternalFrame;
import admission.view.admission.AdmissionYearInternalFrame;
import admission.view.admission.ImageLoaderInternalFrame;
import admission.view.admission.OtherTestScoreImportWizardInternalFrame;
import admission.view.admission.RollNoGeneratorInternalFrame;
import admission.view.admission.TestInternalFrame;
import admission.view.admission.TestScoreImportWizardInternalFrame;
import admission.view.campus.CampusCategoryInternalFrame;
import admission.view.campus.CampusInternalFrame;
import admission.view.campus.CampusProgramOfStudyGroupInformationInternalFrame;
import admission.view.campus.CampusProgramOfStudyGroupInternalFrame;
import admission.view.campus.CampusProgramOfStudyInternalFrame;
import admission.view.fees.AdditionalFeeInternalFrame;
import admission.view.fees.FeeCategoryInternalFrame;
import admission.view.fees.ClassFeeInternalFrame;
import admission.view.fees.FeeModelInternalFrame;
import admission.view.maintain.CategoryInternalFrame;
import admission.view.maintain.CategoryTypeInternalFrame;
import admission.view.maintain.CountryInternalFrame;
import admission.view.maintain.DepartmentInternalFrame;
import admission.view.maintain.DepartmentTypeInternalFrame;
import admission.view.maintain.DisciplineInternalFrame;
import admission.view.maintain.DistrictInternalFrame;
import admission.view.maintain.DivisionInternalFrame;
import admission.view.maintain.FacultyInternalFrame;
import admission.view.maintain.IssuerInternalFrame;
import admission.view.maintain.JurisdictionInternalFrame;
import admission.view.maintain.PartInternalFrame;
import admission.view.maintain.PrerequisiteInternalFrame;
import admission.view.maintain.ProgramInternalFrame;
import admission.view.maintain.ProgramOfStudyInternalFrame;
import admission.view.maintain.ProgramSubjectInternalFrame;
import admission.view.maintain.ProgramTypeInternalFrame;
import admission.view.maintain.ProvinceInternalFrame;
import admission.view.maintain.ReligionInternalFrame;
import admission.view.maintain.ShiftInternalFrame;
import admission.view.maintain.SubjectInternalFrame;
import admission.view.maintain.TalukaInternalFrame;
import admission.view.seats.DisciplineSeatDistributionInternalFrame;
import admission.view.seats.JurisdictionSeatDistributionInternalFrame;
import admission.view.security.UserManagmentInternalFrame;
import java.awt.Component;
import java.awt.Toolkit;
import java.beans.PropertyVetoException;
import java.text.MessageFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JInternalFrame;
import javax.swing.JMenu;
import admission.utils.IConstant;
import admission.utils.DateUtility;
import admission.view.accounts.ChallanEntryDialog;
import admission.view.accounts.ChallanGeneratorDialog;

public class MainFrame extends javax.swing.JFrame {

    public MainFrame() {
        initComponents();

        init();
        //AdmissionInternalFrame admissionInternalFrame;
        //admissionListDetailsInternalFrame = new AdmissionListDetailsInternalFrame();

    }

    private void init() {
        this.setEnabledMenu(false);

        if (CommonHelper.checkUserResourceAccess(KeyConstant.ACCOUNT_FORM)) {
            accountInternalFrame = new AccountInternalFrame();
            this.desktopPane.add(accountInternalFrame);
            accountMenuItem.setEnabled(true);
        }
        if (CommonHelper.checkUserResourceAccess(KeyConstant.PART_FORM)) {
            partInternalFrame = new PartInternalFrame();
            this.desktopPane.add(partInternalFrame);
            partMenuItem.setEnabled(true);
        }
        if (CommonHelper.checkUserResourceAccess(KeyConstant.ADMISSION_LIST_FORM)) {
            admissionListInternalFrame = new AdmissionListInternalFrame();
            this.desktopPane.add(admissionListInternalFrame);
            admissionListMenuItem.setEnabled(true);
        }
        if (CommonHelper.checkUserResourceAccess(KeyConstant.USER_MANAGMENT_FORM)) {
            this.userManagmentInternalFrame = new UserManagmentInternalFrame();
            this.desktopPane.add(userManagmentInternalFrame);
            userManagmentMenuItem.setEnabled(true);
        }
        if (CommonHelper.checkUserResourceAccess(KeyConstant.ADMISSION_SESSION_FORM)) {
            admissionSessionInternalFrame = new AdmissionSessionInternalFrame();
            this.desktopPane.add(admissionSessionInternalFrame);
            admissionSessionMenuItem.setEnabled(true);
        }
        if (CommonHelper.checkUserResourceAccess(KeyConstant.ADMISSION_YEAR_FORM)) {
            admissionYearInternalFrame = new AdmissionYearInternalFrame();
            this.desktopPane.add(admissionYearInternalFrame);
            admissionYearMenuItem.setEnabled(true);
        }
        if (CommonHelper.checkUserResourceAccess(KeyConstant.CAMPUS_FORM)) {
            campusInternalFrame = new CampusInternalFrame();
            this.desktopPane.add(campusInternalFrame);
            campusMenuItem.setEnabled(true);
        }
        if (CommonHelper.checkUserResourceAccess(KeyConstant.CAMPUS_POS_FORM)) {
            campusProgramOfStudyInternalFrame = new CampusProgramOfStudyInternalFrame();
            this.desktopPane.add(campusProgramOfStudyInternalFrame);
            campusProgramOfStudyMenuItem.setEnabled(true);
        }
        if (CommonHelper.checkUserResourceAccess(KeyConstant.CAMPUS_POSG_FORM)) {
            campusProgramOfStudyGroupInternalFrame = new CampusProgramOfStudyGroupInternalFrame();
            this.desktopPane.add(campusProgramOfStudyGroupInternalFrame);
            cposgMenuItem.setEnabled(true);
        }
        if (CommonHelper.checkUserResourceAccess(KeyConstant.CAMPUS_CATEGORY_FORM)) {
            campusCategoryInternalFrame = new CampusCategoryInternalFrame();
            this.desktopPane.add(campusCategoryInternalFrame);
            campusCategorysMenuItem.setEnabled(true);
        }
        if (CommonHelper.checkUserResourceAccess(KeyConstant.CATEGORY_FORM)) {
            categoryInternalFrame = new CategoryInternalFrame();
            this.desktopPane.add(categoryInternalFrame);
            categoryMenuItem.setEnabled(true);
        }
        if (CommonHelper.checkUserResourceAccess(KeyConstant.CATEGORY_TYPE_FORM)) {
            categoryTypeInternalFrame = new CategoryTypeInternalFrame();
            this.desktopPane.add(categoryTypeInternalFrame);
            categoryTypeMenuItem.setEnabled(true);
        }
        if (CommonHelper.checkUserResourceAccess(KeyConstant.COUNTRY_FORM)) {
            countryInternalFrame = new CountryInternalFrame();
            this.desktopPane.add(countryInternalFrame);
            countryMenuItem.setEnabled(true);
        }
        if (CommonHelper.checkUserResourceAccess(KeyConstant.DEPARTMENT_FORM)) {
            departmentInternalFrame = new DepartmentInternalFrame();
            this.desktopPane.add(departmentInternalFrame);
            deptMenuItem.setEnabled(true);
        }
        if (CommonHelper.checkUserResourceAccess(KeyConstant.DEPARTMENT_TYPE_FORM)) {
            departmentTypeInternalFrame = new DepartmentTypeInternalFrame();
            this.desktopPane.add(departmentTypeInternalFrame);
            deptTypeMenuItem.setEnabled(true);
        }
        if (CommonHelper.checkUserResourceAccess(KeyConstant.DISCIPLINE_FORM)) {
            disciplineInternalFrame = new DisciplineInternalFrame();
            this.desktopPane.add(disciplineInternalFrame);
            disciplineMenuItem.setEnabled(true);
        }
        if (CommonHelper.checkUserResourceAccess(KeyConstant.DISTRICT_FORM)) {
            districtInternalFrame = new DistrictInternalFrame();
            this.desktopPane.add(districtInternalFrame);
            districtMenuItem.setEnabled(true);
        }
        if (CommonHelper.checkUserResourceAccess(KeyConstant.DIVISION_FORM)) {
            divisionInternalFrame = new DivisionInternalFrame();
            this.desktopPane.add(divisionInternalFrame);
            divisionMenuItem.setEnabled(true);
        }
        if (CommonHelper.checkUserResourceAccess(KeyConstant.FACULTY_FORM)) {
            facultyInternalFrame = new FacultyInternalFrame();
            this.desktopPane.add(facultyInternalFrame);
            facultyMenuItem.setEnabled(true);
        }
        if (CommonHelper.checkUserResourceAccess(KeyConstant.SHIFT_FORM)) {
            shiftInternalFrame = new ShiftInternalFrame();
            this.desktopPane.add(shiftInternalFrame);
            shiftMenuItem.setEnabled(true);
        }
        if (CommonHelper.checkUserResourceAccess(KeyConstant.ISSUER_FORM)) {
            issuerInternalFrame = new IssuerInternalFrame();
            this.desktopPane.add(issuerInternalFrame);
            issuerMenuItem.setEnabled(true);
        }
        if (CommonHelper.checkUserResourceAccess(KeyConstant.JURISDICTION_FORM)) {
            jurisdictionInternalFrame = new JurisdictionInternalFrame();
            this.desktopPane.add(jurisdictionInternalFrame);
            jurisdictionMenuItem.setEnabled(true);
        }
        if (CommonHelper.checkUserResourceAccess(KeyConstant.JURISDICTION_SEAT_DISTRIBUTION_FORM)) {
            jurisdictionSeatDistributionInternalFrame = new JurisdictionSeatDistributionInternalFrame();
            this.desktopPane.add(jurisdictionSeatDistributionInternalFrame);
            districtSeatDistributionMenuItem.setEnabled(true);
        }
        if (CommonHelper.checkUserResourceAccess(KeyConstant.PROGRAM_OF_STUDY_FORM)) {
            programOfStudyInternalFrame = new ProgramOfStudyInternalFrame();
            this.desktopPane.add(programOfStudyInternalFrame);
            programOfStudyMenuItem.setEnabled(true);
        }
        if (CommonHelper.checkUserResourceAccess(KeyConstant.PROGRAM_FORM)) {
            programInternalFrame = new ProgramInternalFrame();
            this.desktopPane.add(programInternalFrame);
            programMenuItem.setEnabled(true);
        }
        if (CommonHelper.checkUserResourceAccess(KeyConstant.PROGRAM_TYPE_FORM)) {
            programTypeInternalFrame = new ProgramTypeInternalFrame();
            this.desktopPane.add(programTypeInternalFrame);
            programTypeMenuItem.setEnabled(true);
        }
        if (CommonHelper.checkUserResourceAccess(KeyConstant.PROVINCE_FORM)) {
            provinceInternalFrame = new ProvinceInternalFrame();
            this.desktopPane.add(provinceInternalFrame);
            provinceMenuItem.setEnabled(true);
        }
        if (CommonHelper.checkUserResourceAccess(KeyConstant.RELIGION_FORM)) {
            religionInternalFrame = new ReligionInternalFrame();
            this.desktopPane.add(religionInternalFrame);
            religionMenuItem.setEnabled(true);
        }
        if (CommonHelper.checkUserResourceAccess(KeyConstant.DISCIPLINE_SEAT_DISTRIBUTION_FORM)) {
            seatDistributionInternalFrame = new DisciplineSeatDistributionInternalFrame();
            this.desktopPane.add(seatDistributionInternalFrame);
            disciplineSeatDistMenuItem.setEnabled(true);
        }
        if (CommonHelper.checkUserResourceAccess(KeyConstant.ADMISSION_FORM)) {
            admissionFormInternalFrame = new AdmissionFormInternalFrame();
            this.desktopPane.add(admissionFormInternalFrame);
            admissionFormMenuItem.setEnabled(true);
        }
        if (CommonHelper.checkUserResourceAccess(KeyConstant.TALUKA_FORM)) {
            talukaInternalFrame = new TalukaInternalFrame();
            this.desktopPane.add(talukaInternalFrame);
            talukaMenuItem.setEnabled(true);
        }
        if (CommonHelper.checkUserResourceAccess(KeyConstant.TEST_FORM)) {
            testInternalFrame = new TestInternalFrame();
            this.desktopPane.add(testInternalFrame);
            testMenuItem.setEnabled(true);
        }
        if (CommonHelper.checkUserResourceAccess(KeyConstant.TEST_SCORE_FORM)) {
            testScoreImportWizardInternalFrame = new TestScoreImportWizardInternalFrame();
            this.desktopPane.add(testScoreImportWizardInternalFrame);
            testDataWizardsMenuItem.setEnabled(true);
        }
        if (CommonHelper.checkUserResourceAccess(KeyConstant.OTHER_TEST_SCORE_FORM)) {
            otherTestScoreImportWizardInternalFrame = new OtherTestScoreImportWizardInternalFrame();
            this.desktopPane.add(otherTestScoreImportWizardInternalFrame);
            otherTestDataWizardsMenuItem.setEnabled(true);
        }
        if (CommonHelper.checkUserResourceAccess(KeyConstant.ALL_REPORTS_FORM)) {
            reportViewerInternalFrame = new ReportViewerInternalFrame();
            allReportsDialog = new AllReportsDialog(this, true);
            this.desktopPane.add(reportViewerInternalFrame);
            allReportsMenuItem.setEnabled(true);
        }
        if (CommonHelper.checkUserResourceAccess(KeyConstant.SUBJECT_FORM)) {
            subjectInternalFrame = new SubjectInternalFrame();
            this.desktopPane.add(subjectInternalFrame);
            subjectMenuItem.setEnabled(true);
        }
        if (CommonHelper.checkUserResourceAccess(KeyConstant.PROGRAM_SUBJECT_FORM)) {
            programSubjectInternalFrame = new ProgramSubjectInternalFrame();
            this.desktopPane.add(programSubjectInternalFrame);
            programSubjectMenuItem.setEnabled(true);
        }
        if (CommonHelper.checkUserResourceAccess(KeyConstant.PREREQUISITE_FORM)) {
            prerequisiteInternalFrame = new PrerequisiteInternalFrame();
            this.desktopPane.add(prerequisiteInternalFrame);
            prerequisiteMenuItem.setEnabled(true);
        }
        if (CommonHelper.checkUserResourceAccess(KeyConstant.EXPORT_CANDIDATE_FORM)) {
            exportCandidatesDetailInternalFrame = new ExportCandidatesDetailInternalFrame();
            this.desktopPane.add(exportCandidatesDetailInternalFrame);
            exportMenuItem.setEnabled(true);
        }
        if (CommonHelper.checkUserResourceAccess(KeyConstant.BANK_SELECTION_REPORT_FORM)) {
            bankSelectionReportInternalFrame = new BankSelectionReportInternalFrame();
            this.desktopPane.add(bankSelectionReportInternalFrame);
            bankReportMenuItem.setEnabled(true);
        }
        if (CommonHelper.checkUserResourceAccess(KeyConstant.ADMISSION_DETAIL_FORM)) {
            admissionDetailInternalFrame = new AdmissionDetailInternalFrame();
            this.desktopPane.add(admissionDetailInternalFrame);
            admissionDetailMenuItem.setEnabled(true);
        }
        if (CommonHelper.checkUserResourceAccess(KeyConstant.IMAGE_LOADER_FORM)) {
            imageLoaderInternalFrame = new ImageLoaderInternalFrame();
            this.desktopPane.add(imageLoaderInternalFrame);
            imageLoaderMenuItem.setEnabled(true);
            imageLoaderMenuItem.setEnabled(true);
        }
        if (CommonHelper.checkUserResourceAccess(KeyConstant.CPOSG_FORM)) {
            campusProgramOfStudyGroupInformationInternalFrame = new CampusProgramOfStudyGroupInformationInternalFrame();
            this.desktopPane.add(campusProgramOfStudyGroupInformationInternalFrame);
            programInfromationMenuItem.setEnabled(true);
        }
        if (CommonHelper.checkUserResourceAccess(KeyConstant.ROLL_NO_GENERATOR_FORM)) {
            rollNoGeneratorInternalFrame = new RollNoGeneratorInternalFrame();
            this.desktopPane.add(rollNoGeneratorInternalFrame);
            rollNoGeneratorMenuItem.setEnabled(true);
        }
        if (CommonHelper.checkUserResourceAccess(KeyConstant.HEC_REPORT_FORM)) {
            hecReportInternalFrame = new HecReportInternalFrame();
            this.desktopPane.add(hecReportInternalFrame);
            hecReportMenuItem.setEnabled(true);
        }
        if (CommonHelper.checkUserResourceAccess(KeyConstant.HOTEL_REPORT_FORM)) {
            hostelReportInternalFrame = new HostelReportInternalFrame();
            this.desktopPane.add(hostelReportInternalFrame);
            hostelReportMenuItem.setEnabled(true);
        }
        if (CommonHelper.checkUserResourceAccess(KeyConstant.FINANCE_REPORT_FORM)) {
            financeReportInternalFrame = new FinanceReportInternalFrame();
            this.desktopPane.add(financeReportInternalFrame);
            financeReportMenuItem.setEnabled(true);
        }
        if (CommonHelper.checkUserResourceAccess(KeyConstant.SEARCH_ENGINE_FORM)) {
            searchEngineInternalFrame = new SearchEngineInternalFrame();
            this.desktopPane.add(searchEngineInternalFrame);
            searchEngineMenuItem.setEnabled(true);
        }
        if (CommonHelper.checkUserResourceAccess(KeyConstant.FEE_CATEGORY_FORM)) {
            feeCategoryInternalFrame = new FeeCategoryInternalFrame();
            this.desktopPane.add(feeCategoryInternalFrame);
            feeCategoryMenuItem.setEnabled(true);
        }
        if (CommonHelper.checkUserResourceAccess(KeyConstant.FEE_MODEL_FORM)) {
            feeModelInternalFrame = new FeeModelInternalFrame();
            this.desktopPane.add(feeModelInternalFrame);
            feeModelMenuItem.setEnabled(true);
        }
        if (CommonHelper.checkUserResourceAccess(KeyConstant.CLASS_FEE_FORM)) {
            classFeeInternalFrame = new ClassFeeInternalFrame();
            this.desktopPane.add(classFeeInternalFrame);
            classFeeMenuItem.setEnabled(true);
        }
        if (CommonHelper.checkUserResourceAccess(KeyConstant.ADDITIONAL_FEE_FORM)) {
            additionalFeeInternalFrame = new AdditionalFeeInternalFrame();
            this.desktopPane.add(additionalFeeInternalFrame);
            additionalFeeMenuItem.setEnabled(true);
        }
        if (CommonHelper.checkUserResourceAccess(KeyConstant.CHALLAN_GENERATOR_DIALOG)) {
            challanGeneratorDialog = new ChallanGeneratorDialog(this);
            challanGeneratorMenuItem.setEnabled(true);
        }
        if (CommonHelper.checkUserResourceAccess(KeyConstant.CHALLAN_ENTRY_DIALOG)) {
            challanEntryDialog = new ChallanEntryDialog(this);
            challanEntryMenuItem.setEnabled(true);
        }
        if (CommonHelper.checkUserResourceAccess(KeyConstant.CONFIGURATION_FORM)) {
            configurationDialog = new ConfigurationDialog(this);
            configrationMenuItem.setEnabled(true);
        }
        
//        if (CommonHelper.checkUserResourceAccess(KeyConstant.PROGRAM_OF_STUDY_FOR_PROGRAM_FORM)) {
//            allowedProgramOfStudyInternalFrame = new AllowedProgramOfStudyInternalFrame();
//            this.desktopPane.add(allowedProgramOfStudyInternalFrame);
//            allowedProgramOfStudyMenuItem.setEnabled(true);
//        }

        user = null;

        IConstant.USER.ADMISSION_FORM = admissionFormInternalFrame;

        this.copyRightLabel.setText(MessageFormat.format("Copyright \u00a9 {0} by University Of Sindh, Jamshoro. All Rights Reserved.", DateUtility.getYearToString(new Date())));
        this.setSize(java.awt.Toolkit.getDefaultToolkit().getScreenSize());
        this.setExtendedState(MAXIMIZED_BOTH);
        this.setLocationRelativeTo(null);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        reportViewerMenuItem = new javax.swing.JMenuItem();
        allowedProgramOfStudyMenuItem = new javax.swing.JMenuItem();
        desktopPane = new javax.swing.JDesktopPane();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        copyRightLabel = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        menuBar = new javax.swing.JMenuBar();
        setupMenu = new javax.swing.JMenu();
        religionMenuItem = new javax.swing.JMenuItem();
        jSeparator10 = new javax.swing.JPopupMenu.Separator();
        userManagmentMenuItem = new javax.swing.JMenuItem();
        configrationMenuItem = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        exitMenuItem = new javax.swing.JMenuItem();
        areaMenu = new javax.swing.JMenu();
        countryMenuItem = new javax.swing.JMenuItem();
        provinceMenuItem = new javax.swing.JMenuItem();
        divisionMenuItem = new javax.swing.JMenuItem();
        districtMenuItem = new javax.swing.JMenuItem();
        talukaMenuItem = new javax.swing.JMenuItem();
        maintainMenu = new javax.swing.JMenu();
        facultyMenuItem = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JPopupMenu.Separator();
        deptTypeMenuItem = new javax.swing.JMenuItem();
        deptMenuItem = new javax.swing.JMenuItem();
        jSeparator3 = new javax.swing.JPopupMenu.Separator();
        disciplineMenuItem = new javax.swing.JMenuItem();
        jSeparator4 = new javax.swing.JPopupMenu.Separator();
        programTypeMenuItem = new javax.swing.JMenuItem();
        programMenuItem = new javax.swing.JMenuItem();
        programOfStudyMenuItem = new javax.swing.JMenuItem();
        jSeparator16 = new javax.swing.JPopupMenu.Separator();
        categoryMenu = new javax.swing.JMenu();
        categoryTypeMenuItem = new javax.swing.JMenuItem();
        categoryMenuItem = new javax.swing.JMenuItem();
        jSeparator7 = new javax.swing.JPopupMenu.Separator();
        campusMenu = new javax.swing.JMenu();
        campusMenuItem = new javax.swing.JMenuItem();
        campusCategorysMenuItem = new javax.swing.JMenuItem();
        campusProgramOfStudyMenuItem = new javax.swing.JMenuItem();
        cposgMenuItem = new javax.swing.JMenuItem();
        jSeparator12 = new javax.swing.JPopupMenu.Separator();
        jurisdictionMenuItem = new javax.swing.JMenuItem();
        jSeparator8 = new javax.swing.JPopupMenu.Separator();
        disciplineSeatDistMenuItem = new javax.swing.JMenuItem();
        districtSeatDistributionMenuItem = new javax.swing.JMenuItem();
        jSeparator11 = new javax.swing.JPopupMenu.Separator();
        feeMenu = new javax.swing.JMenu();
        feeCategoryMenuItem = new javax.swing.JMenuItem();
        feeModelMenuItem = new javax.swing.JMenuItem();
        classFeeMenuItem = new javax.swing.JMenuItem();
        additionalFeeMenuItem = new javax.swing.JMenuItem();
        jSeparator13 = new javax.swing.JPopupMenu.Separator();
        issuerMenuItem = new javax.swing.JMenuItem();
        jSeparator14 = new javax.swing.JPopupMenu.Separator();
        subjectMenuItem = new javax.swing.JMenuItem();
        programSubjectMenuItem = new javax.swing.JMenuItem();
        jSeparator15 = new javax.swing.JPopupMenu.Separator();
        prerequisiteMenuItem = new javax.swing.JMenuItem();
        infoMenu = new javax.swing.JMenu();
        programInfromationMenuItem = new javax.swing.JMenuItem();
        searchEngineMenuItem = new javax.swing.JMenuItem();
        sessionMenu = new javax.swing.JMenu();
        admissionYearMenuItem = new javax.swing.JMenuItem();
        shiftMenuItem = new javax.swing.JMenuItem();
        admissionSessionMenuItem = new javax.swing.JMenuItem();
        jSeparator6 = new javax.swing.JPopupMenu.Separator();
        partMenuItem = new javax.swing.JMenuItem();
        admissionMenu = new javax.swing.JMenu();
        admissionFormMenuItem = new javax.swing.JMenuItem();
        imageLoaderMenuItem = new javax.swing.JMenuItem();
        jSeparator9 = new javax.swing.JPopupMenu.Separator();
        admissionListMenuItem = new javax.swing.JMenuItem();
        jSeparator5 = new javax.swing.JPopupMenu.Separator();
        admissionDetailMenuItem = new javax.swing.JMenuItem();
        challanGeneratorMenuItem = new javax.swing.JMenuItem();
        challanEntryMenuItem = new javax.swing.JMenuItem();
        accountMenuItem = new javax.swing.JMenuItem();
        rollNoGeneratorMenuItem = new javax.swing.JMenuItem();
        testMenu = new javax.swing.JMenu();
        testMenuItem = new javax.swing.JMenuItem();
        testDataWizardsMenuItem = new javax.swing.JMenuItem();
        otherTestDataWizardsMenuItem = new javax.swing.JMenuItem();
        reportsMenu = new javax.swing.JMenu();
        exportMenuItem = new javax.swing.JMenuItem();
        bankReportMenuItem = new javax.swing.JMenuItem();
        hecReportMenuItem = new javax.swing.JMenuItem();
        hostelReportMenuItem = new javax.swing.JMenuItem();
        financeReportMenuItem = new javax.swing.JMenuItem();
        jSeparator17 = new javax.swing.JPopupMenu.Separator();
        allReportsMenuItem = new javax.swing.JMenuItem();
        helpMenu = new javax.swing.JMenu();
        aboutMenuItem = new javax.swing.JMenuItem();

        reportViewerMenuItem.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        reportViewerMenuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Gnome-Text-X-Script-32.png"))); // NOI18N
        reportViewerMenuItem.setText("All Report");
        reportViewerMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                reportViewerMenuItemActionPerformed(evt);
            }
        });

        allowedProgramOfStudyMenuItem.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        allowedProgramOfStudyMenuItem.setText("Program of Study for Program");
        allowedProgramOfStudyMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                allowedProgramOfStudyMenuItemActionPerformed(evt);
            }
        });

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Admission Management System in A Public Sector Higher Education DAI, Univeristy of Sindh, Jamshoro");
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/images/new_logo2.png")));

        desktopPane.setBackground(new java.awt.Color(255, 255, 255));
        desktopPane.setDragMode(javax.swing.JDesktopPane.OUTLINE_DRAG_MODE);

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/new_logo2.png"))); // NOI18N

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Admission Management System");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("<html><center>A Public Sector Higher Education DAI<br>University Of Sindh, Jamshoro");

        copyRightLabel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        copyRightLabel.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        copyRightLabel.setText("Copyright © {year} by University Of Sindh, Jamshoro. All Rights Reserved.");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setText("");

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel5.setText("");

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel6.setText("");

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel7.setText("");

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel8.setText("");

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel9.setText("");

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel10.setText("");

        javax.swing.GroupLayout desktopPaneLayout = new javax.swing.GroupLayout(desktopPane);
        desktopPane.setLayout(desktopPaneLayout);
        desktopPaneLayout.setHorizontalGroup(
            desktopPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(desktopPaneLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(desktopPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 932, Short.MAX_VALUE)
                    .addComponent(jLabel3)
                    .addGroup(desktopPaneLayout.createSequentialGroup()
                        .addGroup(desktopPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, desktopPaneLayout.createSequentialGroup()
                                .addGroup(desktopPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel4))
                                .addGap(18, 18, 18)
                                .addGroup(desktopPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel8)))
                            .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.LEADING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(copyRightLabel))
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        desktopPaneLayout.setVerticalGroup(
            desktopPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(desktopPaneLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addGap(32, 32, 32)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
                .addGroup(desktopPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel10)
                .addGap(16, 16, 16)
                .addGroup(desktopPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(copyRightLabel, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, desktopPaneLayout.createSequentialGroup()
                        .addGroup(desktopPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(jLabel8))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel9)))
                .addContainerGap())
        );
        desktopPane.setLayer(jLabel2, javax.swing.JLayeredPane.DEFAULT_LAYER);
        desktopPane.setLayer(jLabel1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        desktopPane.setLayer(jLabel3, javax.swing.JLayeredPane.DEFAULT_LAYER);
        desktopPane.setLayer(copyRightLabel, javax.swing.JLayeredPane.DEFAULT_LAYER);
        desktopPane.setLayer(jLabel4, javax.swing.JLayeredPane.DEFAULT_LAYER);
        desktopPane.setLayer(jLabel5, javax.swing.JLayeredPane.DEFAULT_LAYER);
        desktopPane.setLayer(jLabel6, javax.swing.JLayeredPane.DEFAULT_LAYER);
        desktopPane.setLayer(jLabel7, javax.swing.JLayeredPane.DEFAULT_LAYER);
        desktopPane.setLayer(jLabel8, javax.swing.JLayeredPane.DEFAULT_LAYER);
        desktopPane.setLayer(jLabel9, javax.swing.JLayeredPane.DEFAULT_LAYER);
        desktopPane.setLayer(jLabel10, javax.swing.JLayeredPane.DEFAULT_LAYER);

        getContentPane().add(desktopPane, java.awt.BorderLayout.CENTER);

        setupMenu.setMnemonic('F');
        setupMenu.setText("File");
        setupMenu.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        religionMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_R, java.awt.event.InputEvent.CTRL_MASK));
        religionMenuItem.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        religionMenuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Gnome-Preferences-Desktop-Theme-32.png"))); // NOI18N
        religionMenuItem.setText("Religion");
        religionMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                religionMenuItemActionPerformed(evt);
            }
        });
        setupMenu.add(religionMenuItem);
        setupMenu.add(jSeparator10);

        userManagmentMenuItem.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        userManagmentMenuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Gnome-System-Users-32.png"))); // NOI18N
        userManagmentMenuItem.setText("User Management");
        userManagmentMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                userManagmentMenuItemActionPerformed(evt);
            }
        });
        setupMenu.add(userManagmentMenuItem);

        configrationMenuItem.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        configrationMenuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Gnome-Preferences-System-32.png"))); // NOI18N
        configrationMenuItem.setText("Configration");
        configrationMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                configrationMenuItemActionPerformed(evt);
            }
        });
        setupMenu.add(configrationMenuItem);
        setupMenu.add(jSeparator1);

        exitMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F4, java.awt.event.InputEvent.ALT_MASK));
        exitMenuItem.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        exitMenuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Gnome-System-Shutdown-32.png"))); // NOI18N
        exitMenuItem.setText("Exit");
        exitMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitMenuItemActionPerformed(evt);
            }
        });
        setupMenu.add(exitMenuItem);

        menuBar.add(setupMenu);

        areaMenu.setMnemonic('A');
        areaMenu.setText("Area");
        areaMenu.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        countryMenuItem.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        countryMenuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Gnome-Preferences-Desktop-Locale-32.png"))); // NOI18N
        countryMenuItem.setMnemonic('C');
        countryMenuItem.setText("Country");
        countryMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                countryMenuItemActionPerformed(evt);
            }
        });
        areaMenu.add(countryMenuItem);

        provinceMenuItem.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        provinceMenuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Application-Default-Red-32.png"))); // NOI18N
        provinceMenuItem.setMnemonic('P');
        provinceMenuItem.setText("Province");
        provinceMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                provinceMenuItemActionPerformed(evt);
            }
        });
        areaMenu.add(provinceMenuItem);

        divisionMenuItem.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        divisionMenuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Application-Default-Orange-32.png"))); // NOI18N
        divisionMenuItem.setMnemonic('v');
        divisionMenuItem.setText("Division");
        divisionMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                divisionMenuItemActionPerformed(evt);
            }
        });
        areaMenu.add(divisionMenuItem);

        districtMenuItem.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        districtMenuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Application-Default-Icon-32.png"))); // NOI18N
        districtMenuItem.setMnemonic('D');
        districtMenuItem.setText("District");
        districtMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                districtMenuItemActionPerformed(evt);
            }
        });
        areaMenu.add(districtMenuItem);

        talukaMenuItem.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        talukaMenuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Application-Default-Red-32.png"))); // NOI18N
        talukaMenuItem.setMnemonic('T');
        talukaMenuItem.setText("Taluka");
        talukaMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                talukaMenuItemActionPerformed(evt);
            }
        });
        areaMenu.add(talukaMenuItem);

        menuBar.add(areaMenu);

        maintainMenu.setMnemonic('M');
        maintainMenu.setText("Maintain");
        maintainMenu.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        facultyMenuItem.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        facultyMenuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/admission/view/images/icon.png"))); // NOI18N
        facultyMenuItem.setText("Faculty");
        facultyMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                facultyMenuItemActionPerformed(evt);
            }
        });
        maintainMenu.add(facultyMenuItem);
        maintainMenu.add(jSeparator2);

        deptTypeMenuItem.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        deptTypeMenuItem.setText("Department Type");
        deptTypeMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deptTypeMenuItemActionPerformed(evt);
            }
        });
        maintainMenu.add(deptTypeMenuItem);

        deptMenuItem.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        deptMenuItem.setText("Department");
        deptMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deptMenuItemActionPerformed(evt);
            }
        });
        maintainMenu.add(deptMenuItem);
        maintainMenu.add(jSeparator3);

        disciplineMenuItem.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        disciplineMenuItem.setText("Discipline");
        disciplineMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                disciplineMenuItemActionPerformed(evt);
            }
        });
        maintainMenu.add(disciplineMenuItem);
        maintainMenu.add(jSeparator4);

        programTypeMenuItem.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        programTypeMenuItem.setText("Program Type");
        programTypeMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                programTypeMenuItemActionPerformed(evt);
            }
        });
        maintainMenu.add(programTypeMenuItem);

        programMenuItem.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        programMenuItem.setText("Program");
        programMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                programMenuItemActionPerformed(evt);
            }
        });
        maintainMenu.add(programMenuItem);

        programOfStudyMenuItem.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        programOfStudyMenuItem.setText("Program of Study");
        programOfStudyMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                programOfStudyMenuItemActionPerformed(evt);
            }
        });
        maintainMenu.add(programOfStudyMenuItem);
        maintainMenu.add(jSeparator16);

        categoryMenu.setMnemonic('C');
        categoryMenu.setText("Category");
        categoryMenu.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        categoryTypeMenuItem.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        categoryTypeMenuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/admission/view/images/icon.png"))); // NOI18N
        categoryTypeMenuItem.setText("Category Type");
        categoryTypeMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                categoryTypeMenuItemActionPerformed(evt);
            }
        });
        categoryMenu.add(categoryTypeMenuItem);

        categoryMenuItem.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        categoryMenuItem.setText("Category");
        categoryMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                categoryMenuItemActionPerformed(evt);
            }
        });
        categoryMenu.add(categoryMenuItem);

        maintainMenu.add(categoryMenu);
        maintainMenu.add(jSeparator7);

        campusMenu.setText("Campus");
        campusMenu.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        campusMenuItem.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        campusMenuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/admission/view/images/icon.png"))); // NOI18N
        campusMenuItem.setText("Campus");
        campusMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                campusMenuItemActionPerformed(evt);
            }
        });
        campusMenu.add(campusMenuItem);

        campusCategorysMenuItem.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        campusCategorysMenuItem.setText("Campus Category");
        campusCategorysMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                campusCategorysMenuItemActionPerformed(evt);
            }
        });
        campusMenu.add(campusCategorysMenuItem);

        campusProgramOfStudyMenuItem.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        campusProgramOfStudyMenuItem.setText("Campus Program of Study");
        campusProgramOfStudyMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                campusProgramOfStudyMenuItemActionPerformed(evt);
            }
        });
        campusMenu.add(campusProgramOfStudyMenuItem);

        cposgMenuItem.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cposgMenuItem.setText("Campus Program of Study Group");
        cposgMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cposgMenuItemActionPerformed(evt);
            }
        });
        campusMenu.add(cposgMenuItem);

        maintainMenu.add(campusMenu);
        maintainMenu.add(jSeparator12);

        jurisdictionMenuItem.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jurisdictionMenuItem.setText("Jurisdiction");
        jurisdictionMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jurisdictionMenuItemActionPerformed(evt);
            }
        });
        maintainMenu.add(jurisdictionMenuItem);
        maintainMenu.add(jSeparator8);

        disciplineSeatDistMenuItem.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        disciplineSeatDistMenuItem.setText("Discipline Seat Distribution");
        disciplineSeatDistMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                disciplineSeatDistMenuItemActionPerformed(evt);
            }
        });
        maintainMenu.add(disciplineSeatDistMenuItem);

        districtSeatDistributionMenuItem.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        districtSeatDistributionMenuItem.setText("District Quota Seat Distribution");
        districtSeatDistributionMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                districtSeatDistributionMenuItemActionPerformed(evt);
            }
        });
        maintainMenu.add(districtSeatDistributionMenuItem);
        maintainMenu.add(jSeparator11);

        feeMenu.setText("Fee");
        feeMenu.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        feeCategoryMenuItem.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        feeCategoryMenuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/admission/view/images/icon.png"))); // NOI18N
        feeCategoryMenuItem.setText("Fee Category");
        feeCategoryMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                feeCategoryMenuItemActionPerformed(evt);
            }
        });
        feeMenu.add(feeCategoryMenuItem);

        feeModelMenuItem.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        feeModelMenuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/admission/view/images/icon.png"))); // NOI18N
        feeModelMenuItem.setText("Fee Model");
        feeModelMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                feeModelMenuItemActionPerformed(evt);
            }
        });
        feeMenu.add(feeModelMenuItem);

        classFeeMenuItem.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        classFeeMenuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/admission/view/images/icon.png"))); // NOI18N
        classFeeMenuItem.setText("Class Fee");
        classFeeMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                classFeeMenuItemActionPerformed(evt);
            }
        });
        feeMenu.add(classFeeMenuItem);

        additionalFeeMenuItem.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        additionalFeeMenuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/admission/view/images/icon.png"))); // NOI18N
        additionalFeeMenuItem.setText("Additional Fee");
        additionalFeeMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                additionalFeeMenuItemActionPerformed(evt);
            }
        });
        feeMenu.add(additionalFeeMenuItem);

        maintainMenu.add(feeMenu);
        maintainMenu.add(jSeparator13);

        issuerMenuItem.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        issuerMenuItem.setText("Issuer");
        issuerMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                issuerMenuItemActionPerformed(evt);
            }
        });
        maintainMenu.add(issuerMenuItem);
        maintainMenu.add(jSeparator14);

        subjectMenuItem.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        subjectMenuItem.setText("Subject");
        subjectMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                subjectMenuItemActionPerformed(evt);
            }
        });
        maintainMenu.add(subjectMenuItem);

        programSubjectMenuItem.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        programSubjectMenuItem.setText("Program Subject");
        programSubjectMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                programSubjectMenuItemActionPerformed(evt);
            }
        });
        maintainMenu.add(programSubjectMenuItem);
        maintainMenu.add(jSeparator15);

        prerequisiteMenuItem.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        prerequisiteMenuItem.setText("Prerequisite");
        prerequisiteMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                prerequisiteMenuItemActionPerformed(evt);
            }
        });
        maintainMenu.add(prerequisiteMenuItem);

        menuBar.add(maintainMenu);

        infoMenu.setMnemonic('I');
        infoMenu.setText("Info");
        infoMenu.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        programInfromationMenuItem.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        programInfromationMenuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Accessories-Dictionary-32.png"))); // NOI18N
        programInfromationMenuItem.setText("Program Information");
        programInfromationMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                programInfromationMenuItemActionPerformed(evt);
            }
        });
        infoMenu.add(programInfromationMenuItem);

        searchEngineMenuItem.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        searchEngineMenuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/search-24.png"))); // NOI18N
        searchEngineMenuItem.setText("Search Engine");
        searchEngineMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchEngineMenuItemActionPerformed(evt);
            }
        });
        infoMenu.add(searchEngineMenuItem);

        menuBar.add(infoMenu);

        sessionMenu.setMnemonic('S');
        sessionMenu.setText("Session");
        sessionMenu.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        admissionYearMenuItem.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        admissionYearMenuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Gnome-X-Office-Calendar-32.png"))); // NOI18N
        admissionYearMenuItem.setText("Year");
        admissionYearMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                admissionYearMenuItemActionPerformed(evt);
            }
        });
        sessionMenu.add(admissionYearMenuItem);

        shiftMenuItem.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        shiftMenuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Application-Default-Icon-32.png"))); // NOI18N
        shiftMenuItem.setText("Shift");
        shiftMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                shiftMenuItemActionPerformed(evt);
            }
        });
        sessionMenu.add(shiftMenuItem);

        admissionSessionMenuItem.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        admissionSessionMenuItem.setText("Admission Session");
        admissionSessionMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                admissionSessionMenuItemActionPerformed(evt);
            }
        });
        sessionMenu.add(admissionSessionMenuItem);
        sessionMenu.add(jSeparator6);

        partMenuItem.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        partMenuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Gtk-Dnd-Multiple-32.png"))); // NOI18N
        partMenuItem.setText("Part");
        partMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                partMenuItemActionPerformed(evt);
            }
        });
        sessionMenu.add(partMenuItem);

        menuBar.add(sessionMenu);

        admissionMenu.setMnemonic('A');
        admissionMenu.setText("Admission");
        admissionMenu.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        admissionFormMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_A, java.awt.event.InputEvent.CTRL_MASK));
        admissionFormMenuItem.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        admissionFormMenuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Editor.png"))); // NOI18N
        admissionFormMenuItem.setText("Admission Form");
        admissionFormMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                admissionFormMenuItemActionPerformed(evt);
            }
        });
        admissionMenu.add(admissionFormMenuItem);

        imageLoaderMenuItem.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        imageLoaderMenuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Gnome-Image-Missing-32.png"))); // NOI18N
        imageLoaderMenuItem.setText("Image Loader");
        imageLoaderMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                imageLoaderMenuItemActionPerformed(evt);
            }
        });
        admissionMenu.add(imageLoaderMenuItem);
        admissionMenu.add(jSeparator9);

        admissionListMenuItem.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        admissionListMenuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Gnome-View-Sort-Ascending-32.png"))); // NOI18N
        admissionListMenuItem.setText("Admission List");
        admissionListMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                admissionListMenuItemActionPerformed(evt);
            }
        });
        admissionMenu.add(admissionListMenuItem);
        admissionMenu.add(jSeparator5);

        admissionDetailMenuItem.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        admissionDetailMenuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Gnome-X-Office-Drawing-32.png"))); // NOI18N
        admissionDetailMenuItem.setText("Admission Detail");
        admissionDetailMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                admissionDetailMenuItemActionPerformed(evt);
            }
        });
        admissionMenu.add(admissionDetailMenuItem);

        challanGeneratorMenuItem.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        challanGeneratorMenuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Gnome-Edit-Select-All-24.png"))); // NOI18N
        challanGeneratorMenuItem.setText("Challan Generator");
        challanGeneratorMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                challanGeneratorMenuItemActionPerformed(evt);
            }
        });
        admissionMenu.add(challanGeneratorMenuItem);

        challanEntryMenuItem.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        challanEntryMenuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Gnome-Edit-Select-All-24.png"))); // NOI18N
        challanEntryMenuItem.setText("Challan Entry");
        challanEntryMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                challanEntryMenuItemActionPerformed(evt);
            }
        });
        admissionMenu.add(challanEntryMenuItem);

        accountMenuItem.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        accountMenuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Emblem-Money-32.png"))); // NOI18N
        accountMenuItem.setText("Account");
        accountMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                accountMenuItemActionPerformed(evt);
            }
        });
        admissionMenu.add(accountMenuItem);

        rollNoGeneratorMenuItem.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        rollNoGeneratorMenuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Gnome-View-Sort-Descending-32.png"))); // NOI18N
        rollNoGeneratorMenuItem.setText("Roll No Generator");
        rollNoGeneratorMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rollNoGeneratorMenuItemActionPerformed(evt);
            }
        });
        admissionMenu.add(rollNoGeneratorMenuItem);

        menuBar.add(admissionMenu);

        testMenu.setMnemonic('T');
        testMenu.setText("Test");
        testMenu.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        testMenuItem.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        testMenuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/admission/view/images/icon.png"))); // NOI18N
        testMenuItem.setText("Test");
        testMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                testMenuItemActionPerformed(evt);
            }
        });
        testMenu.add(testMenuItem);

        testDataWizardsMenuItem.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        testDataWizardsMenuItem.setText("Test Score Import Wizard");
        testDataWizardsMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                testDataWizardsMenuItemActionPerformed(evt);
            }
        });
        testMenu.add(testDataWizardsMenuItem);

        otherTestDataWizardsMenuItem.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        otherTestDataWizardsMenuItem.setText("Other Test Score Import Wizard");
        otherTestDataWizardsMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                otherTestDataWizardsMenuItemActionPerformed(evt);
            }
        });
        testMenu.add(otherTestDataWizardsMenuItem);

        menuBar.add(testMenu);

        reportsMenu.setMnemonic('R');
        reportsMenu.setText("Reports");
        reportsMenu.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        exportMenuItem.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        exportMenuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Gnome-X-Office-Spreadsheet-32.png"))); // NOI18N
        exportMenuItem.setText("Export Candidates Detail");
        exportMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exportMenuItemActionPerformed(evt);
            }
        });
        reportsMenu.add(exportMenuItem);

        bankReportMenuItem.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        bankReportMenuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Gnome-X-Office-Spreadsheet-32.png"))); // NOI18N
        bankReportMenuItem.setText("Bank Selection Report");
        bankReportMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bankReportMenuItemActionPerformed(evt);
            }
        });
        reportsMenu.add(bankReportMenuItem);

        hecReportMenuItem.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        hecReportMenuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Gnome-X-Office-Spreadsheet-32.png"))); // NOI18N
        hecReportMenuItem.setText("HEC Report");
        hecReportMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hecReportMenuItemActionPerformed(evt);
            }
        });
        reportsMenu.add(hecReportMenuItem);

        hostelReportMenuItem.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        hostelReportMenuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Gnome-X-Office-Spreadsheet-32.png"))); // NOI18N
        hostelReportMenuItem.setText("Hostel Report");
        hostelReportMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hostelReportMenuItemActionPerformed(evt);
            }
        });
        reportsMenu.add(hostelReportMenuItem);

        financeReportMenuItem.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        financeReportMenuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Gnome-X-Office-Spreadsheet-32.png"))); // NOI18N
        financeReportMenuItem.setText("Finance Report");
        financeReportMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                financeReportMenuItemActionPerformed(evt);
            }
        });
        reportsMenu.add(financeReportMenuItem);
        reportsMenu.add(jSeparator17);

        allReportsMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F2, 0));
        allReportsMenuItem.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        allReportsMenuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Gnome-Text-X-Script-32.png"))); // NOI18N
        allReportsMenuItem.setText("All Report");
        allReportsMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                allReportsMenuItemActionPerformed(evt);
            }
        });
        reportsMenu.add(allReportsMenuItem);

        menuBar.add(reportsMenu);

        helpMenu.setMnemonic('H');
        helpMenu.setText("Help");
        helpMenu.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        aboutMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F1, 0));
        aboutMenuItem.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        aboutMenuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Gnome-Help-Faq-32.png"))); // NOI18N
        aboutMenuItem.setText("About");
        aboutMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aboutMenuItemActionPerformed(evt);
            }
        });
        helpMenu.add(aboutMenuItem);

        menuBar.add(helpMenu);

        setJMenuBar(menuBar);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void setOpened(JInternalFrame frame) {
        try {
            frame.setIcon(false);
            frame.setSelected(true);
        } catch (PropertyVetoException ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (!frame.isVisible()) {
            frame.setVisible(true);
        }
    }

    private void districtMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_districtMenuItemActionPerformed
        setOpened(districtInternalFrame);
    }//GEN-LAST:event_districtMenuItemActionPerformed

    private void talukaMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_talukaMenuItemActionPerformed
        setOpened(talukaInternalFrame);
    }//GEN-LAST:event_talukaMenuItemActionPerformed

    private void exitMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitMenuItemActionPerformed
        System.exit(0);
    }//GEN-LAST:event_exitMenuItemActionPerformed

    private void divisionMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_divisionMenuItemActionPerformed
        setOpened(divisionInternalFrame);
    }//GEN-LAST:event_divisionMenuItemActionPerformed

    private void religionMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_religionMenuItemActionPerformed
        setOpened(religionInternalFrame);
    }//GEN-LAST:event_religionMenuItemActionPerformed

    private void campusMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_campusMenuItemActionPerformed
        setOpened(campusInternalFrame);
    }//GEN-LAST:event_campusMenuItemActionPerformed

    private void facultyMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_facultyMenuItemActionPerformed
        setOpened(facultyInternalFrame);
    }//GEN-LAST:event_facultyMenuItemActionPerformed

    private void deptTypeMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deptTypeMenuItemActionPerformed
        setOpened(departmentTypeInternalFrame);
    }//GEN-LAST:event_deptTypeMenuItemActionPerformed

    private void deptMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deptMenuItemActionPerformed
        setOpened(departmentInternalFrame);
    }//GEN-LAST:event_deptMenuItemActionPerformed

    private void disciplineMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_disciplineMenuItemActionPerformed
        setOpened(disciplineInternalFrame);
    }//GEN-LAST:event_disciplineMenuItemActionPerformed

    private void programTypeMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_programTypeMenuItemActionPerformed
        setOpened(programTypeInternalFrame);
    }//GEN-LAST:event_programTypeMenuItemActionPerformed

    private void programOfStudyMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_programOfStudyMenuItemActionPerformed
        setOpened(programOfStudyInternalFrame);
    }//GEN-LAST:event_programOfStudyMenuItemActionPerformed

    private void disciplineSeatDistMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_disciplineSeatDistMenuItemActionPerformed
        setOpened(seatDistributionInternalFrame);
    }//GEN-LAST:event_disciplineSeatDistMenuItemActionPerformed

    private void feeCategoryMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_feeCategoryMenuItemActionPerformed
        setOpened(feeCategoryInternalFrame);
    }//GEN-LAST:event_feeCategoryMenuItemActionPerformed

    private void shiftMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_shiftMenuItemActionPerformed
        setOpened(shiftInternalFrame);
    }//GEN-LAST:event_shiftMenuItemActionPerformed

    private void issuerMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_issuerMenuItemActionPerformed
        setOpened(issuerInternalFrame);
    }//GEN-LAST:event_issuerMenuItemActionPerformed

    private void countryMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_countryMenuItemActionPerformed
        setOpened(countryInternalFrame);
    }//GEN-LAST:event_countryMenuItemActionPerformed

    private void admissionFormMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_admissionFormMenuItemActionPerformed
        setOpened(admissionFormInternalFrame);
    }//GEN-LAST:event_admissionFormMenuItemActionPerformed

    private void admissionSessionMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_admissionSessionMenuItemActionPerformed
        
        setOpened(admissionSessionInternalFrame);
        //this.admissionSessionInternalFrame.setVisible(true);
    }//GEN-LAST:event_admissionSessionMenuItemActionPerformed

    private void categoryMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_categoryMenuItemActionPerformed
        
        setOpened(categoryInternalFrame);
        //this.categoryInternalFrame.setVisible(true);
    }//GEN-LAST:event_categoryMenuItemActionPerformed

    private void testMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_testMenuItemActionPerformed
        
        setOpened(testInternalFrame);
        //this.testInternalFrame.setVisible(true);
    }//GEN-LAST:event_testMenuItemActionPerformed

    private void testDataWizardsMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_testDataWizardsMenuItemActionPerformed
        
        setOpened(testScoreImportWizardInternalFrame);
//        this.testScoreImportWizardInternalFrame.setVisible(true);
    }//GEN-LAST:event_testDataWizardsMenuItemActionPerformed

    private void userManagmentMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_userManagmentMenuItemActionPerformed
        
        setOpened(userManagmentInternalFrame);
        //this.adminInternalFrame.setVisible(true);
    }//GEN-LAST:event_userManagmentMenuItemActionPerformed

    private void jurisdictionMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jurisdictionMenuItemActionPerformed
        
        setOpened(jurisdictionInternalFrame);
        //this.jurisdictionInternalFrame.setVisible(true);
    }//GEN-LAST:event_jurisdictionMenuItemActionPerformed

    private void programMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_programMenuItemActionPerformed
        
        setOpened(programInternalFrame);
        //this.programInternalFrame.setVisible(true);
    }//GEN-LAST:event_programMenuItemActionPerformed

    private void categoryTypeMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_categoryTypeMenuItemActionPerformed
        
        setOpened(categoryTypeInternalFrame);
        //this.categoryTypeInternalFrame.setVisible(true);
    }//GEN-LAST:event_categoryTypeMenuItemActionPerformed

    private void campusProgramOfStudyMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_campusProgramOfStudyMenuItemActionPerformed
        
        setOpened(campusProgramOfStudyInternalFrame);
        //this.campusProgramOfStudyInternalFrame.setVisible(true);
    }//GEN-LAST:event_campusProgramOfStudyMenuItemActionPerformed

    private void reportViewerMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_reportViewerMenuItemActionPerformed
        
        setOpened(reportViewerInternalFrame2);
        //this.reportViewerInternalFrame2.setVisible(true);
    }//GEN-LAST:event_reportViewerMenuItemActionPerformed

    private void cposgMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cposgMenuItemActionPerformed
        
        setOpened(campusProgramOfStudyGroupInternalFrame);
        //this.campusProgramOfStudyGroupInternalFrame.setVisible(true);
    }//GEN-LAST:event_cposgMenuItemActionPerformed

    private void admissionYearMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_admissionYearMenuItemActionPerformed
        
        setOpened(admissionYearInternalFrame);
        //this.admissionYearInternalFrame.setVisible(true);
    }//GEN-LAST:event_admissionYearMenuItemActionPerformed

    private void provinceMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_provinceMenuItemActionPerformed
        
        setOpened(provinceInternalFrame);
        //this.provinceInternalFrame.setVisible(true);
    }//GEN-LAST:event_provinceMenuItemActionPerformed

    private void campusCategorysMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_campusCategorysMenuItemActionPerformed
        
        setOpened(campusCategoryInternalFrame);
        //this.campusCategoryInternalFrame.setVisible(true);
    }//GEN-LAST:event_campusCategorysMenuItemActionPerformed

    private void admissionListMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_admissionListMenuItemActionPerformed
        
        setOpened(admissionListInternalFrame);
        //this.admissionListInternalFrame.setVisible(true);
    }//GEN-LAST:event_admissionListMenuItemActionPerformed

    private void districtSeatDistributionMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_districtSeatDistributionMenuItemActionPerformed
        
        setOpened(jurisdictionSeatDistributionInternalFrame);
        //this.jurisdictionSeatDistributionInternalFrame.setVisible(true);
    }//GEN-LAST:event_districtSeatDistributionMenuItemActionPerformed

    private void accountMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_accountMenuItemActionPerformed
        setOpened(accountInternalFrame);
    }//GEN-LAST:event_accountMenuItemActionPerformed

    private void partMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_partMenuItemActionPerformed
        setOpened(partInternalFrame);
    }//GEN-LAST:event_partMenuItemActionPerformed

    private void feeModelMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_feeModelMenuItemActionPerformed
        setOpened(feeModelInternalFrame);
    }//GEN-LAST:event_feeModelMenuItemActionPerformed

    private void subjectMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_subjectMenuItemActionPerformed
        setOpened(subjectInternalFrame);
    }//GEN-LAST:event_subjectMenuItemActionPerformed

    private void programSubjectMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_programSubjectMenuItemActionPerformed
        setOpened(programSubjectInternalFrame);
    }//GEN-LAST:event_programSubjectMenuItemActionPerformed

    private void prerequisiteMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_prerequisiteMenuItemActionPerformed
        setOpened(prerequisiteInternalFrame);
    }//GEN-LAST:event_prerequisiteMenuItemActionPerformed

    private void otherTestDataWizardsMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_otherTestDataWizardsMenuItemActionPerformed
        setOpened(otherTestScoreImportWizardInternalFrame);
    }//GEN-LAST:event_otherTestDataWizardsMenuItemActionPerformed

    private void exportMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exportMenuItemActionPerformed
        
        setOpened(exportCandidatesDetailInternalFrame);
        //this.exportCandidatesDetailInternalFrame.setVisible(true);
    }//GEN-LAST:event_exportMenuItemActionPerformed

    private void aboutMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aboutMenuItemActionPerformed
        
    }//GEN-LAST:event_aboutMenuItemActionPerformed

    private void bankReportMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bankReportMenuItemActionPerformed
        
        setOpened(bankSelectionReportInternalFrame);
        //this.bankSelectionReportInternalFrame.setVisible(true);
    }//GEN-LAST:event_bankReportMenuItemActionPerformed

    private void admissionDetailMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_admissionDetailMenuItemActionPerformed
        setOpened(admissionDetailInternalFrame);
        //this.admissionDetailInternalFrame.setVisible(true);
    }//GEN-LAST:event_admissionDetailMenuItemActionPerformed

    private void imageLoaderMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_imageLoaderMenuItemActionPerformed
        
        setOpened(imageLoaderInternalFrame);
        //this.imageLoaderInternalFrame.setVisible(true);
    }//GEN-LAST:event_imageLoaderMenuItemActionPerformed

    private void programInfromationMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_programInfromationMenuItemActionPerformed
        
        setOpened(campusProgramOfStudyGroupInformationInternalFrame);
        //this.campusProgramOfStudyGroupInformationInternalFrame.setVisible(true);
    }//GEN-LAST:event_programInfromationMenuItemActionPerformed

    private void rollNoGeneratorMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rollNoGeneratorMenuItemActionPerformed
        
        setOpened(rollNoGeneratorInternalFrame);
        //this.rollNoGeneratorInternalFrame.setVisible(true);
    }//GEN-LAST:event_rollNoGeneratorMenuItemActionPerformed

    private void hecReportMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hecReportMenuItemActionPerformed
        
        setOpened(hecReportInternalFrame);
        //this.hecReportInternalFrame.setVisible(true);
    }//GEN-LAST:event_hecReportMenuItemActionPerformed

    private void hostelReportMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hostelReportMenuItemActionPerformed
        
        setOpened(hostelReportInternalFrame);
        //this.hostelReportInternalFrame.setVisible(true);
    }//GEN-LAST:event_hostelReportMenuItemActionPerformed

    private void searchEngineMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchEngineMenuItemActionPerformed
        
        setOpened(searchEngineInternalFrame);
        //this.searchEngineInternalFrame.setVisible(true);
    }//GEN-LAST:event_searchEngineMenuItemActionPerformed

    private void configrationMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_configrationMenuItemActionPerformed
        
        configurationDialog.setVisible(true);
    }//GEN-LAST:event_configrationMenuItemActionPerformed

    private void financeReportMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_financeReportMenuItemActionPerformed
        setOpened(financeReportInternalFrame);
    }//GEN-LAST:event_financeReportMenuItemActionPerformed

    private void allReportsMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_allReportsMenuItemActionPerformed
        allReportsDialog.setVisible(true);
    }//GEN-LAST:event_allReportsMenuItemActionPerformed

    private void allowedProgramOfStudyMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_allowedProgramOfStudyMenuItemActionPerformed
//        setOpened(allowedProgramOfStudyInternalFrame);
    }//GEN-LAST:event_allowedProgramOfStudyMenuItemActionPerformed

    private void classFeeMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_classFeeMenuItemActionPerformed
        setOpened(classFeeInternalFrame);
    }//GEN-LAST:event_classFeeMenuItemActionPerformed

    private void additionalFeeMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_additionalFeeMenuItemActionPerformed
        setOpened(additionalFeeInternalFrame);
    }//GEN-LAST:event_additionalFeeMenuItemActionPerformed

    private void challanGeneratorMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_challanGeneratorMenuItemActionPerformed
        challanGeneratorDialog.setVisible(true);
    }//GEN-LAST:event_challanGeneratorMenuItemActionPerformed

    private void challanEntryMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_challanEntryMenuItemActionPerformed
        challanEntryDialog.setVisible(true);
    }//GEN-LAST:event_challanEntryMenuItemActionPerformed

    public void setUser(User admin) {
        this.user = admin;
    }

    private void setEnabledMenu(boolean b) {
        userManagmentMenuItem.setEnabled(b);
        religionMenuItem.setEnabled(b);
        for (int i = 1; i < menuBar.getMenuCount() - 1; i++) {
            JMenu menu = menuBar.getMenu(i);
            setMenu(menu, b);
        }
    }

    private void setMenu(JMenu menu, boolean b) {
        for (int j = 0; j < menu.getMenuComponentCount(); j++) {
            Component comp = menu.getMenuComponent(j);
            if (comp instanceof javax.swing.JSeparator) {
            } else if (comp instanceof JMenu) {
                setMenu((JMenu) comp, b);
            } else {
                comp.setEnabled(b);
            }
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem aboutMenuItem;
    private javax.swing.JMenuItem accountMenuItem;
    private javax.swing.JMenuItem additionalFeeMenuItem;
    private javax.swing.JMenuItem admissionDetailMenuItem;
    private javax.swing.JMenuItem admissionFormMenuItem;
    private javax.swing.JMenuItem admissionListMenuItem;
    private javax.swing.JMenu admissionMenu;
    private javax.swing.JMenuItem admissionSessionMenuItem;
    private javax.swing.JMenuItem admissionYearMenuItem;
    private javax.swing.JMenuItem allReportsMenuItem;
    private javax.swing.JMenuItem allowedProgramOfStudyMenuItem;
    private javax.swing.JMenu areaMenu;
    private javax.swing.JMenuItem bankReportMenuItem;
    private javax.swing.JMenuItem campusCategorysMenuItem;
    private javax.swing.JMenu campusMenu;
    private javax.swing.JMenuItem campusMenuItem;
    private javax.swing.JMenuItem campusProgramOfStudyMenuItem;
    private javax.swing.JMenu categoryMenu;
    private javax.swing.JMenuItem categoryMenuItem;
    private javax.swing.JMenuItem categoryTypeMenuItem;
    private javax.swing.JMenuItem challanEntryMenuItem;
    private javax.swing.JMenuItem challanGeneratorMenuItem;
    private javax.swing.JMenuItem classFeeMenuItem;
    private javax.swing.JMenuItem configrationMenuItem;
    private javax.swing.JLabel copyRightLabel;
    private javax.swing.JMenuItem countryMenuItem;
    private javax.swing.JMenuItem cposgMenuItem;
    private javax.swing.JMenuItem deptMenuItem;
    private javax.swing.JMenuItem deptTypeMenuItem;
    private javax.swing.JDesktopPane desktopPane;
    private javax.swing.JMenuItem disciplineMenuItem;
    private javax.swing.JMenuItem disciplineSeatDistMenuItem;
    private javax.swing.JMenuItem districtMenuItem;
    private javax.swing.JMenuItem districtSeatDistributionMenuItem;
    private javax.swing.JMenuItem divisionMenuItem;
    private javax.swing.JMenuItem exitMenuItem;
    private javax.swing.JMenuItem exportMenuItem;
    private javax.swing.JMenuItem facultyMenuItem;
    private javax.swing.JMenuItem feeCategoryMenuItem;
    private javax.swing.JMenu feeMenu;
    private javax.swing.JMenuItem feeModelMenuItem;
    private javax.swing.JMenuItem financeReportMenuItem;
    private javax.swing.JMenuItem hecReportMenuItem;
    private javax.swing.JMenu helpMenu;
    private javax.swing.JMenuItem hostelReportMenuItem;
    private javax.swing.JMenuItem imageLoaderMenuItem;
    private javax.swing.JMenu infoMenu;
    private javax.swing.JMenuItem issuerMenuItem;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JPopupMenu.Separator jSeparator10;
    private javax.swing.JPopupMenu.Separator jSeparator11;
    private javax.swing.JPopupMenu.Separator jSeparator12;
    private javax.swing.JPopupMenu.Separator jSeparator13;
    private javax.swing.JPopupMenu.Separator jSeparator14;
    private javax.swing.JPopupMenu.Separator jSeparator15;
    private javax.swing.JPopupMenu.Separator jSeparator16;
    private javax.swing.JPopupMenu.Separator jSeparator17;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    private javax.swing.JPopupMenu.Separator jSeparator3;
    private javax.swing.JPopupMenu.Separator jSeparator4;
    private javax.swing.JPopupMenu.Separator jSeparator5;
    private javax.swing.JPopupMenu.Separator jSeparator6;
    private javax.swing.JPopupMenu.Separator jSeparator7;
    private javax.swing.JPopupMenu.Separator jSeparator8;
    private javax.swing.JPopupMenu.Separator jSeparator9;
    private javax.swing.JMenuItem jurisdictionMenuItem;
    private javax.swing.JMenu maintainMenu;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JMenuItem otherTestDataWizardsMenuItem;
    private javax.swing.JMenuItem partMenuItem;
    private javax.swing.JMenuItem prerequisiteMenuItem;
    private javax.swing.JMenuItem programInfromationMenuItem;
    private javax.swing.JMenuItem programMenuItem;
    private javax.swing.JMenuItem programOfStudyMenuItem;
    private javax.swing.JMenuItem programSubjectMenuItem;
    private javax.swing.JMenuItem programTypeMenuItem;
    private javax.swing.JMenuItem provinceMenuItem;
    private javax.swing.JMenuItem religionMenuItem;
    private javax.swing.JMenuItem reportViewerMenuItem;
    private javax.swing.JMenu reportsMenu;
    private javax.swing.JMenuItem rollNoGeneratorMenuItem;
    private javax.swing.JMenuItem searchEngineMenuItem;
    private javax.swing.JMenu sessionMenu;
    private javax.swing.JMenu setupMenu;
    private javax.swing.JMenuItem shiftMenuItem;
    private javax.swing.JMenuItem subjectMenuItem;
    private javax.swing.JMenuItem talukaMenuItem;
    private javax.swing.JMenuItem testDataWizardsMenuItem;
    private javax.swing.JMenu testMenu;
    private javax.swing.JMenuItem testMenuItem;
    private javax.swing.JMenuItem userManagmentMenuItem;
    // End of variables declaration//GEN-END:variables

    private AccountInternalFrame accountInternalFrame;
    private PartInternalFrame partInternalFrame;
    private UserManagmentInternalFrame userManagmentInternalFrame;
    private AdmissionListInternalFrame admissionListInternalFrame;
    //private AdmissionListDetailsInternalFrame admissionListDetailsInternalFrame;
    private AdmissionSessionInternalFrame admissionSessionInternalFrame;
    private AdmissionYearInternalFrame admissionYearInternalFrame;
    private CampusInternalFrame campusInternalFrame;
    private CampusProgramOfStudyInternalFrame campusProgramOfStudyInternalFrame;
    private CampusProgramOfStudyGroupInternalFrame campusProgramOfStudyGroupInternalFrame;
    private CampusCategoryInternalFrame campusCategoryInternalFrame;
    private CategoryTypeInternalFrame categoryTypeInternalFrame;
    private CategoryInternalFrame categoryInternalFrame;
    private CountryInternalFrame countryInternalFrame;
    private DepartmentInternalFrame departmentInternalFrame;
    private DepartmentTypeInternalFrame departmentTypeInternalFrame;
    private DisciplineInternalFrame disciplineInternalFrame;
    private DistrictInternalFrame districtInternalFrame;
    private DivisionInternalFrame divisionInternalFrame;
    private FacultyInternalFrame facultyInternalFrame;
    private ShiftInternalFrame shiftInternalFrame;
    private IssuerInternalFrame issuerInternalFrame;
    private JurisdictionInternalFrame jurisdictionInternalFrame;
    private JurisdictionSeatDistributionInternalFrame jurisdictionSeatDistributionInternalFrame;
    private ProgramOfStudyInternalFrame programOfStudyInternalFrame;
    private ProgramTypeInternalFrame programTypeInternalFrame;
    private ProgramInternalFrame programInternalFrame;
    private ProvinceInternalFrame provinceInternalFrame;
    private ReligionInternalFrame religionInternalFrame;
    private DisciplineSeatDistributionInternalFrame seatDistributionInternalFrame;
    private AdmissionFormInternalFrame admissionFormInternalFrame;
    private TalukaInternalFrame talukaInternalFrame;
    private TestInternalFrame testInternalFrame;
    private TestScoreImportWizardInternalFrame testScoreImportWizardInternalFrame;
    private OtherTestScoreImportWizardInternalFrame otherTestScoreImportWizardInternalFrame;
    public ReportViewerInternalFrame2 reportViewerInternalFrame2;
    public static ReportViewerInternalFrame reportViewerInternalFrame;
    private User user;
    private SubjectInternalFrame subjectInternalFrame;
    private ProgramSubjectInternalFrame programSubjectInternalFrame;
    private PrerequisiteInternalFrame prerequisiteInternalFrame;
    private ExportCandidatesDetailInternalFrame exportCandidatesDetailInternalFrame;
    private BankSelectionReportInternalFrame bankSelectionReportInternalFrame;
    private AdmissionDetailInternalFrame admissionDetailInternalFrame;
    private ImageLoaderInternalFrame imageLoaderInternalFrame;
    private CampusProgramOfStudyGroupInformationInternalFrame campusProgramOfStudyGroupInformationInternalFrame;
    private RollNoGeneratorInternalFrame rollNoGeneratorInternalFrame;
    private HecReportInternalFrame hecReportInternalFrame;
    private HostelReportInternalFrame hostelReportInternalFrame;
    private FinanceReportInternalFrame financeReportInternalFrame;
    private SearchEngineInternalFrame searchEngineInternalFrame;
    private AllReportsDialog allReportsDialog;
    private ConfigurationDialog configurationDialog;
//    private AllowedProgramOfStudyInternalFrame allowedProgramOfStudyInternalFrame;
    private FeeCategoryInternalFrame feeCategoryInternalFrame;
    private FeeModelInternalFrame feeModelInternalFrame;
    private ClassFeeInternalFrame classFeeInternalFrame;
    private AdditionalFeeInternalFrame additionalFeeInternalFrame;
    private ChallanGeneratorDialog challanGeneratorDialog;
    private ChallanEntryDialog challanEntryDialog;
    
}
