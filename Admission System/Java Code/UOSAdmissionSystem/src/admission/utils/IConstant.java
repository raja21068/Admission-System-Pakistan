package admission.utils;

/**
 *
 * @author Raja Kumar & Jay Kumar
 */
public interface IConstant {
    
    public static final String PREFIX = "YOG";

    public class DB {
        public final static String PROPERTY_PATH = "usindh_admission.properties";
        public final static String PASS_PHRASE = "2K10-CSE-86";
        public static int STATUS = 0;
        public static String DRIVER = "";
        public static String URL = "";
        public static String USERNAME = "";
        public static String PASSWORD = "";
    }

    public class USER {

        public static String LAST_USER_LOGINED = "";
        public static admission.view.admission.AdmissionFormInternalFrame ADMISSION_FORM;
        
    }

    public static final int CHOICE_LIMIT = 10;
    public static final Float TOTAL_MARKS = 400.0F;

    public static final Float MATRIC_PER =  0.10F; // Bachalor
    public static final Float INTER_PER =   0.30F;  // Bachalor
    public static final Float TEST_PER =    0.60F; // Bachalor & Master
    
    public static final Float M_MATRIC_PER =  0.05F; // Master
    public static final Float M_INTER_PER =   0.05F;  // Master    
    public static final Float DEGREE_PER =    0.30F;  // Master
    
    public static final String PATH = "photos/"; // Bachalor & Master

    public static final int WAITING_CANDIDATE_LIMIT = 10;
    public static final int SSC = 0;
    public static final int HSC = 1;
    public static final int GRADUATION = 2;

    public static final int BACHELOR = 4;
    public static final int MASTER = 5;
//    public static final String BACHELOR = "Bachelor";
//    public static final String NON_BACHELOR = "Non-Bachelor";
//    public static final String MORNING = "Morning";
//    public static final String EVENING = "Evening";

    /*public static final int JAMSHORO = 0;
     public static final int HYDERABAD = 1;
     public static final int LARKANA = 2;
     public static final int BADIN = 3;
     public static final int MIRPUR_KHAS = 4;
     public static final int DADU = 5;
     public static final int THATTA = 6;*/
    public static final String SINDH = "SD"; // SINDH
    public static final String PUNJAB = "PB"; // PUNJAB
    public static final String NWAP = "NWAP";
    public static final String BALOCHISTAN = "BK";
    public static final String AJK = "AJK";
    public static final String FATA = "FATA";

    public static final String GM_DUR_QUOTA = "GEN"; // GENERAL MERIT/DISTRICT U/R QUOTA QUOTA
    public static final String SFM_QUOTA = "SFM"; // SELF FINANCE (MORNING) QUOTA
    public static final String SFE_QUOTA = "SFE"; // SELF FINANCE (EVENING) QUOTA
    public static final String SUE_QUOTA = "SUE"; // S.U. EMPLOYEES QUOTA
    public static final String DP_QUOTA = "DP"; // DISABLED PERSON QUOTA
    public static final String NO_QUOTA = "NO"; // NOMINATION QUOTA
    public static final String AP_QUOTA = "AP"; // ARMY QUOTA
    public static final String AC_QUOTA = "AC"; // AFFILIATED COLLEGE QUOTA
    public static final String SP_QUOTA = "SP"; // SPORTS QUOTA
    public static final String FR_QUOTA = "FR"; // FOREIGN QUOTA
    public static final String CN_SUE_QUOTA = "CNSUE"; // Center S.U. EMPLOYEES QUOTA

    public interface CategoryCodes {

        public static final int GENERAL_MERIT_QUOTA = 0;
        public static final int UPPER_SINDH_OUT_OF_JURISDICTION_QUOTA = 1;
        public static final int FEMALE_JURISDICTION = 2;
        public static final int FEMALE_OUT_OF_JURISDICTION = 3;

        public static final int DISABLE_QUOTA = 4;
        public static final int SPORTS_QUOTA = 5;

        public static final int SUE_SD_QUOTA = 6; // SINDH UNIVERSITY EMPOLYEE DAUGTHER SON
        //public static final int SUE_SB_QUOTA = 7;  // SINDH UNIVERSITY EMPOLYEE SISTER BROTHER
        public static final int SUE_SD_AC_QUOTA = 7; // SINDH UNIVERSITY EMPOLYEE AFFILATEDCOLLEGE SISTER BROTHER
        //public static final int SUE_COM_QUOTA = 8; // SINDH UNIVERSITY EMPOLYEE COMMERCE
        public static final int SUE_SD_NCEAC_QUOTA = 9; // SINDH UNIVERSITY EMPOLYEE OF NATIONAL CENTER OF EXCELLENCE IN ANALYTICAL CHMISTRY

        public static final int NOMINEE_NA_QUOTA = 10; // NOMINEE OF NORTHERN AREAS

        public static final int SELF_FINANCE_QUOTA = 11;
        public static final int OP_SELF_FINANCE_QUOTA = 12;

        public static final int PUNJAB_QUOTA = 13; // NOMINEE OF OTHER PROVINCES
        public static final int BLOCHISTAN_QUOTA = 14; // NOMINEE OF BLOCHISTAN PROVINCES
        public static final int NWFP_QUOTA = 15; // NOMINEE OF N.W.F.P PROVINCES
        //public static final int NOMINEE_OP_QUOTA = 13; // NOMINEE OF OTHER PROVINCES
        //public static final int NOMINEE_BF_QUOTA = 16; // NOMINEE OF BALOCHISTAN AND FATA
        public static final int AJK_GOV_QUOTA = 16; // NOMINEE OF A.J.K GOVERMENT
        public static final int FATA_QUOTA = 22; // NOMINEE OF BALOCHISTAN AND FATA
        public static final int NOMINEE_PHARM_QUOTA = 17; // NOMINEE OF PHARMACEUTICAL INDUSTRY

        public static final int ARMY_PER_QUOTA = 18; // ARMY PERSONAL (ONLY FOR MASTER'S DEGREE PROGRAM)

        public static final int FOREIGN_PKTAP_MERIT_QUOTA = 19;
        public static final int FOREIGN_SELF_QUOTA = 20;
        //public static final int FOREIGN_QUOTA = 18;
        //public static final int FOREIGN_TMC_QUOTA = 19; // FOREIGN THAI MUSLIMS CANDIDATES
        public static final int COMMERCE_QUOTA = 21;  // COMMERCE
    }

    public static final int ADMIN_FORM = 51;
    public static final int ADMISSION_FORM = 52;
    public static final int ADMISSION_SESSION_FORM = 53;
    public static final int CAMPUS_FORM = 54;
    public static final int CANDIDATE_ADMISSION_FORM = 55;
    public static final int CATEGORY_FORM = 56;
    public static final int CATEGORY_TYPE_FORM = 57;
    public static final int COUNTRY_FORM = 58;
    public static final int CREDENTIAL_FORM = 59;
    public static final int DEPARTMENT_FORM = 60;
    public static final int DEPARTMENT_TYPE_FORM = 61;
    public static final int DISCIPLINE_FORM = 62;
    public static final int DISTRICT_FORM = 63;
    public static final int DIVISION_FORM = 64;
    public static final int FACULTY_FORM = 65;
    public static final int FEE_DETAILS_FORM = 66;
    public static final int FEE_FORM = 67;
    public static final int GROUP_FORM = 68;
    public static final int ISSUER_FORM = 69;
    public static final int ISSUER_TYPE_FORM = 70;
    public static final int JURISDICTION_FORM = 71;
    public static final int PRIVILEGES_FORM = 72;
    public static final int PROGRAM_FORM = 73;
    public static final int PROGRAM_OF_STUDY_FORM = 74;
    public static final int PROGRAM_TYPE_FORM = 75;
    public static final int RELIGION_FORM = 76;
    public static final int SEAT_DISTRIBUTION_FORM = 77;
    public static final int TALUKA_FORM = 78;
    public static final int TEST_FORM = 79;

    public static final String SUPER = "S";
    public static final String OPERATOR = "O";

    public interface Challan {
        public static final int ADMISSION = 0;
        public static final int RETAIN = 1;
        public static final int REFUND = 2;
    }
}
