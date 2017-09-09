
<?php if ( ! defined('BASEPATH')) exit('No direct script access allowed');
/**
 * Created by PhpStorm.
 * User: RAJA
 * Date: 8/9/2015
 * Time: 1:39 PM
 */
class Variable {
    public static $FORM_RUPEES = 1800;
    public static $SESSION_YEAR = 2016;
    public static $CURRENT_YEAR = 2015;
    public static $ADMISSION_YEAR_ID = 6;
    public static $CHOICE_MAX = 10;
    public static $CHOICE_MIN = 1;

    public static $ALLAM_I_I_KAZI_JAMSHORO=1;
  //  public static $ELSA_KAZI_HYDERABAD=2;
  //  public static $LARKANA_CAMPUS=3;
  //  public static $LAAR_BADIN_CAMPUS=4;
  //  public static $MIRPURKHAS_CAMPUS=5;
  // public static $THATA_CAMPUS=7;


    public static $SINDH_PROVINCE_ID = 1;

    public static $message = "message";
    public static $MORNING_SHIFT = 1;
    public static $EVENING_SHIFT = 2;
    public static $LOCK = "lock";
    public static $PRINT_FORM = "print_form";
    public static $USER_TYPE = "user_type";
    public static $USER_CANDIDATE = 1;
    public static $USER_SLIP_ISSUER = 2;

    // Campus
    public static $JAMSHORO_CAMPUS = 1;
    public static $BADIN_CAMPUS = 4;
    public static $MIRPUR_KHAS_CAMPUS = 5;
    public static $DADU_CAMPUS = 6;
    public static $LARKANA_CAMPUS = 3;
    public static $BHITSHAH_CAMPUS = 8;
   public static $THATTA_CAMPUS = 7;

    //gender
    public static $MALE = 0;
    public static $FEMALE = 1;

    public static $CAMPUS_OPTIONAL = "optional_campus";

    //Enums                                 0       1      2    3   4     5    6    7    8     9    10
    public static $CategoryEnumCode = array("GEN","SFM","SFE","SUE","DP","NO","AC","SP","FR","AP","CNSUE");
    public static $CategoryEnumDesc = array("General Merit","Self Finance (Morning)","Evening","Sindh University Employee","Disable Person","Nominee","Affiliated College","Sports","Foriegn","AP","CNSUE");


    //Levels
    public static $SSC_LEVEL = 0;
    public static $HSC_LEVEL = 1;
    public static $GRD_LEVEL = 2;

    //handling files
    public static $CONFIRM_FORM = "confirm.php";
    public static $ADMISSION_FORM = "admission_form.php";
    public static $AJAX_OPTIONAL_SUBJECT = "index.php/app_form/ajax_optional_subject_handler";
    public static $AJAX_CHOICES = "index.php/app_form/ajax_choices_handler";
    public static $PROVANCE_CHOICES = "index.php/app_form/ajax_District_handler";
  public static $DELETE_CHOICES = "index.php/app_form/deleteCandidateProgramOfStudy";
    public static $PROCESS_FILE = "process";
    public static $GOTO_TAB_PERSONAL = "goto_personal_info_tab";
    public static $GOTO_TAB_EDUCATION = "goto_educational_info_tab";
    public static $GOTO_TAB_APPLY_FOR = "goto_apply_for_tab";
    public static $SUBMIT_CANDIDATE = "submit_form";

    //Application Constant
    public static $BACHELOR_ID = 1;
    public static $MASTER_ID = 2;


    //Request
    public static $LOGIN = "Login";
    public static $USERNAME = "username";
    public static $PASSWORD = "password";
    public static $NEXT = "Next &Gt;";
    public static $SAVE= "Save";
    public static $SUBMIT_ALL = "Submit &Gt;";
    public static $PREVIOUS = "Previous";
    public static $REQUEST = "request";
    public static $TAB = "tab";
    public static $TAB_PERSONAL = 1;
    public static $TAB_EDUCATION = 2;
    public static $TAB_APPLY_FOR = 3;
    public static $TAB_PERSONAL_FILE = "tab_personal.php";
    public static $TAB_EDUCATION_FILE = "tab_educational.php";
    public static $TAB_APPLY_FOR_FILE = "tab_apply_for.php";



    // Personal Information Tab
    public static $CHALLAN_NO = "challanNo";
    public static $CHALLAN_DATE = "challanDate";
    public static $PROGRAM_TYPE = "programType";
    public static $STUDENT_NAME = "student_name";
    public static $FATHER_NAME = "fathers_name";
    public static $SURNAME = "surname";
    public static $GENDER = "gender";
    public static $CNIC = "cnic";
    public static $DATE_OF_BIRTH = "birth_date";
    public static $BIRTH_PLACE = "birth_place";
    public static $DISTRICT = "district";
    public static $AREA = "area";
    public static $COUNTRY = "country";
    public static $RELIGION = "religion";
    public static $GUARDIAN_NAME = "guardian_name";
    public static $GUARDIAN_RELATIONSHIP = "guardian_relationship";
    public static $GUARDIAN_OCCUPATION = "guardian_occupation";
    public static $TELEPHONE = "telephone";
    public static $MOBILE = "mobile";
    public static $EMAIL = "email";
    public static $PERMENENT_ADDRESS = "permanent_address";
    public static $POSTAL_ADDRESS = "postal_address";
    public static $GUARDIAN_ADDRESS = "guardian_address";
    public static $STUDENT_PICTURE = "std_pic";
    public static $FAMILY_MOBIE = "family_mobile";
    public static $BANK_BRANCH = "bank_branch";
    public static $PRINT_FORM_DATE_SUMBIT = "print_date";
    public static $OBJECTION_REMARKS="objection_remarks";
    public static $IS_OBJECTION="is_objection";




    //Education Information Tab
    public static $SSC_BOARD = "ssc_board";
    public static $SSC_SEAT_NO = "ssc_seat";
    public static $SSC_TOTAL = "ssc_total";
    public static $SSC_OBTAIN = "ssc_obtained";
    public static $SSC_GROUP = "ssc_group";
    public static $SSC_YEAR = "ssc_year";

    public static $HSC_BOARD = "hsc_board";
    public static $HSC_SEAT_NO = "hsc_seat";
    public static $HSC_TOTAL = "hsc_total";
    public static $HSC_OBTAIN = "hsc_obtained";
    public static $HSC_GROUP = "hsc_group";
    public static $HSC_YEAR = "hsc_year";

    public static $GRD_UNIVERSITY = "bachelor_university";
    public static $GRD_SEAT_NO = "grd_seat";
    public static $GRD_DEGREE_YEARS = "degree_years";
    public static $GRD_TOTAL = "bachelor_total";
    public static $GRD_OBTAIN = "bachelor_obtained";
    public static $GRD_GROUP = "bachelor_group";
    public static $GRD_SUB_ONE = "optional_sub1";
    public static $GRD_SUB_TWO = "optional_sub2";
    public static $GRD_SUB_THREE = "optional_sub3";
    public static $GRD_YEAR = "bachelor_year";


    public static $REQUEST_CHOICES = "";

    //Apply For Tab
    public static $CAMPUS = "campus";

    public static $CATEGORY = "category[]";
    public static $CATEGORY_NAME = "category";

    public static $MORNING_CHOICE = "mor-choice[]";
    public static $MORNING_CHOICE_NAME = "mor-choice";

    public static $EVENING_CHOICE = "eve-choice[]";
    public static $EVENING_CHOICE_NAME = "eve-choice";


    /**
     * @return int
     */
    public static function FORM_RUPEES()
    {
        return self::$FORM_RUPEES;
    }

 public static function DELETE_CHOICES()
 {
  return self::$DELETE_CHOICES;
 }
    /**
     * @return int
     */
    public static function SESSION_YEAR()
    {
        return self::$SESSION_YEAR;
    }

    /**
     * @return int
     */
    public static function CURRENT_YEAR()
    {
        return self::$CURRENT_YEAR;
    }

    /**
     * @return int
     */

    public static function ADMISSION_YEAR_ID()
    {
        return self::$ADMISSION_YEAR_ID;
    }

    /**
     * @return int
     */
    public static function CHOICE_MAX()
    {
        return self::$CHOICE_MAX;
    }

    /**
     * @return int
     */
    public static function CHOICE_MIN()
    {
        return self::$CHOICE_MIN;
    }

    /**
     * @return string
     */
    public static function Message()
    {
        return self::$message;
    }

    /**
     * @return int
     */
    public static function MORNING_SHIFT()
    {
        return self::$MORNING_SHIFT;
    }

    /**
     * @return int
     */
    public static function EVENING_SHIFT()
    {
        return self::$EVENING_SHIFT;
    }

    /**
     * @return string
     */
    public static function LOCK()
    {
        return self::$LOCK;
    }

    /**
     * @return string
     */
    public static function PRINT_FORM()
    {
        return self::$PRINT_FORM;
    }

    /**
     * @return array
     */
    public static function CategoryEnumCode()
    {
        return self::$CategoryEnumCode;
    }

    /**
     * @return array
     */
    public static function CategoryEnumDesc()
    {
        return self::$CategoryEnumDesc;
    }

    /**
     * @return int
     */
    public static function SSC_LEVEL()
    {
        return self::$SSC_LEVEL;
    }

    /**
     * @return int
     */
    public static function HSC_LEVEL()
    {
        return self::$HSC_LEVEL;
    }

    /**
     * @return int
     */
    public static function GRD_LEVEL()
    {
        return self::$GRD_LEVEL;
    }

    /**
     * @return string
     */
    public static function CONFIRM_FORM()
    {
        return self::$CONFIRM_FORM;
    }

    /**
     * @return string
     */
    public static function ADMISSION_FORM()
    {
        return self::$ADMISSION_FORM;
    }

    /**
     * @return string
     */
    public static function AJAX_OPTIONAL_SUBJECT()
    {
        return self::$AJAX_OPTIONAL_SUBJECT;
    }

    /**
     * @return string
     */
    public static function AJAX_CHOICES()
    {
        return self::$AJAX_CHOICES;
    }


    /**
     * @return string
     */
    public static function PROVINCE_CHOICE()
    {
        return self::$PROVANCE_CHOICES;
    }

    /**
     * @return string
     */
    public static function PROCESS_FILE()
    {
        return self::$PROCESS_FILE;
    }

    /**
     * @return int
     */
    public static function BACHELOR_ID()
    {
        return self::$BACHELOR_ID;
    }

    /**
     * @return int
     */
    public static function MASTER_ID()
    {
        return self::$MASTER_ID;
    }

    /**
     * @return string
     */
    public static function LOGIN()
    {
        return self::$LOGIN;
    }

    /**
     * @return string
     */
    public static function USERNAME()
    {
        return self::$USERNAME;
    }

    /**
     * @return string
     */
    public static function PASSWORD()
    {
        return self::$PASSWORD;
    }

    /**
     * @return string
     */
    public static function NEXT()
    {
        return self::$NEXT;
    }

    /**
     * @return string
     */
    public static function SAVE()
    {
        return self::$SAVE;
    }

    /**
     * @return string
     */
    public static function SUBMIT_ALL()
    {
        return self::$SUBMIT_ALL;
    }

    /**
     * @return string
     */
    public static function PREVIOUS()
    {
        return self::$PREVIOUS;
    }

    /**
     * @return string
     */
    public static function REQUEST()
    {
        return self::$REQUEST;
    }

    /**
     * @return string
     */
    public static function TAB()
    {
        return self::$TAB;
    }

    /**
     * @return int
     */
    public static function TAB_PERSONAL()
    {
        return self::$TAB_PERSONAL;
    }

    /**
     * @return int
     */
    public static function TAB_EDUCATION()
    {
        return self::$TAB_EDUCATION;
    }

    /**
     * @return int
     */
    public static function TAB_APPLY_FOR()
    {
        return self::$TAB_APPLY_FOR;
    }

    /**
     * @return string
     */
    public static function TAB_PERSONAL_FILE()
    {
        return self::$TAB_PERSONAL_FILE;
    }

    /**
     * @return string
     */
    public static function TAB_EDUCATION_FILE()
    {
        return self::$TAB_EDUCATION_FILE;
    }

    /**
     * @return string
     */
    public static function TAB_APPLY_FOR_FILE()
    {
        return self::$TAB_APPLY_FOR_FILE;
    }

    /**
     * @return string
     */
    public static function PROGRAM_TYPE()
    {
        return self::$PROGRAM_TYPE;
    }

    /**
     * @return string
     */
    public static function STUDENT_NAME()
    {
        return self::$STUDENT_NAME;
    }

    /**
     * @return string
     */
    public static function FATHER_NAME()
    {
        return self::$FATHER_NAME;
    }

    /**
     * @return string
     */
    public static function SURNAME()
    {
        return self::$SURNAME;
    }

    /**
     * @return string
     */
    public static function GENDER()
    {
        return self::$GENDER;
    }

    /**
     * @return string
     */
    public static function CNIC()
    {
        return self::$CNIC;
    }

    /**
     * @return string
     */
    public static function DATE_OF_BIRTH()
    {
        return self::$DATE_OF_BIRTH;
    }

    public static function PRINT_FORM_DATE_SUMBIT()
    {
        return self::$PRINT_FORM_DATE_SUMBIT;
    }

    public static function FAMILY_MOBIE()
    {
        return self::$FAMILY_MOBIE;
    }

    public static function BANK_BRANCH()
    {
        return self::$BANK_BRANCH;
    }

    public static function OBJECTION_REMARKS()
    {
        return self::$OBJECTION_REMARKS;
    }

    /**
     * @return string
     */
    public static function BIRTH_PLACE()
    {
        return self::$BIRTH_PLACE;
    }

    /**
     * @return string
     */
    public static function DISTRICT()
    {
        return self::$DISTRICT;
    }

    /**
     * @return string
     */
    public static function AREA()
    {
        return self::$AREA;
    }

    /**
     * @return string
     */
    public static function COUNTRY()
    {
        return self::$COUNTRY;
    }

    /**
     * @return string
     */
    public static function RELIGION()
    {
        return self::$RELIGION;
    }

    /**
     * @return string
     */
    public static function GUARDIAN_NAME()
    {
        return self::$GUARDIAN_NAME;
    }

    /**
     * @return string
     */
    public static function GUARDIAN_RELATIONSHIP()
    {
        return self::$GUARDIAN_RELATIONSHIP;
    }

    /**
     * @return string
     */
    public static function GUARDIAN_OCCUPATION()
    {
        return self::$GUARDIAN_OCCUPATION;
    }

    /**
     * @return string
     */
    public static function TELEPHONE()
    {
        return self::$TELEPHONE;
    }

    /**
     * @return string
     */
    public static function MOBILE()
    {
        return self::$MOBILE;
    }

    /**
     * @return string
     */
    public static function EMAIL()
    {
        return self::$EMAIL;
    }

    /**
     * @return string
     */
    public static function PERMENENT_ADDRESS()
    {
        return self::$PERMENENT_ADDRESS;
    }

    /**
     * @return string
     */
    public static function POSTAL_ADDRESS()
    {
        return self::$POSTAL_ADDRESS;
    }

    /**
     * @return string
     */
    public static function GUARDIAN_ADDRESS()
    {
        return self::$GUARDIAN_ADDRESS;
    }
    public static function IS_OBJECTION()
    {
        return self::$IS_OBJECTION;
    }


    /**
     * @return string
     */
    public static function STUDENT_PICTURE()
    {
        return self::$STUDENT_PICTURE;
    }

    /**
     * @return string
     */
    public static function SSC_BOARD()
    {
        return self::$SSC_BOARD;
    }

    /**
     * @return string
     */
    public static function SSC_SEATNO()
    {
        return self::$SSC_SEAT_NO;
    }

    /**
     * @return string
     */
    public static function SSC_TOTAL()
    {
        return self::$SSC_TOTAL;
    }

    /**
     * @return string
     */
    public static function SSC_OBTAIN()
    {
        return self::$SSC_OBTAIN;
    }

    /**
     * @return string
     */
    public static function SSC_GROUP()
    {
        return self::$SSC_GROUP;
    }

    /**
     * @return string
     */
    public static function SSC_YEAR()
    {
        return self::$SSC_YEAR;
    }

    /**
     * @return string
     */
    public static function HSC_BOARD()
    {
        return self::$HSC_BOARD;
    }

    /**
     * @return string
     */
    public static function HSC_SEATNO()
    {
        return self::$HSC_SEAT_NO;
    }

    /**
     * @return string
     */
    public static function HSC_TOTAL()
    {
        return self::$HSC_TOTAL;
    }

    /**
     * @return string
     */
    public static function HSC_OBTAIN()
    {
        return self::$HSC_OBTAIN;
    }

    /**
     * @return string
     */
    public static function HSC_GROUP()
    {
        return self::$HSC_GROUP;
    }

    /**
     * @return string
     */
    public static function HSC_YEAR()
    {
        return self::$HSC_YEAR;
    }

    /**
     * @return string
     */
    public static function GRD_UNIVERSITY()
    {
        return self::$GRD_UNIVERSITY;
    }

    /**
     * @return string
     */
    public static function GRD_SEAT_NO()
    {
        return self::$GRD_SEAT_NO;
    }

    /**
     * @return string
     */
    public static function GRD_DEGREE_YEARS()
    {
        return self::$GRD_DEGREE_YEARS;
    }

    /**
     * @return string
     */
    public static function GRD_TOTAL()
    {
        return self::$GRD_TOTAL;
    }

    /**
     * @return string
     */
    public static function GRD_OBTAIN()
    {
        return self::$GRD_OBTAIN;
    }

    /**
     * @return string
     */
    public static function GRD_GROUP()
    {
        return self::$GRD_GROUP;
    }

    /**
     * @return string
     */
    public static function GRD_SUB_ONE()
    {
        return self::$GRD_SUB_ONE;
    }

    /**
     * @return string
     */
    public static function GRD_SUB_TWO()
    {
        return self::$GRD_SUB_TWO;
    }

    /**
     * @return string
     */
    public static function GRD_SUB_THREE()
    {
        return self::$GRD_SUB_THREE;
    }

    /**
     * @return string
     */
    public static function GRD_YEAR()
    {
        return self::$GRD_YEAR;
    }

    /**
     * @return string
     */
    public static function REQUEST_CHOICES()
    {
        return self::$REQUEST_CHOICES;
    }

    /**
     * @return string
     */
    public static function CAMPUS()
    {
        return self::$CAMPUS;
    }

    /**
     * @return string
     */
    public static function CATEGORY()
    {
        return self::$CATEGORY;
    }

    /**
     * @return string
     */
    public static function CATEGORY_NAME()
    {
        return self::$CATEGORY_NAME;
    }

    /**
     * @return string
     */
    public static function MORNING_CHOICE()
    {
        return self::$MORNING_CHOICE;
    }

    /**
     * @return string
     */
    public static function MORNING_CHOICE_NAME()
    {
        return self::$MORNING_CHOICE_NAME;
    }

    /**
     * @return string
     */
    public static function EVENING_CHOICE()
    {
        return self::$EVENING_CHOICE;
    }

    /**
     * @return string
     */
    public static function EVENING_CHOICE_NAME()
    {
        return self::$EVENING_CHOICE_NAME;
    }

    /**
     * @return string
     */
    public static function CHALLAN_NO()
    {
        return self::$CHALLAN_NO;
    }

    /**
     * @return string
     */
    public static function CHALLAN_DATE()
    {
        return self::$CHALLAN_DATE;
    }

    /**
     * @return string
     */
    public static function GOTO_TAB_PERSONAL()
    {
        return self::$GOTO_TAB_PERSONAL;
    }

    /**
     * @return string
     */
    public static function GOTO_TAB_EDUCATION()
    {
        return self::$GOTO_TAB_EDUCATION;
    }

    /**
     * @return int
     */
    public static function JAMSHORO_CAMPUS()
    {
        return self::$JAMSHORO_CAMPUS;
    }

    /**
     * @return int
     */
    public static function BADIN_CAMPUS()
    {
        return self::$BADIN_CAMPUS;
    }

    /**
     * @return int
     */
    public static function MIRPURKHAS_CAMPUS()
    {
        return self::$MIRPUR_KHAS_CAMPUS;
    }

    /**
     * @return int
     */
    public static function DADU_CAMPUS()
    {
        return self::$DADU_CAMPUS;
    }

    /**
     * @return int
     */
    public static function LARKANA_CAMPUS()
    {
        return self::$LARKANA_CAMPUS;
    }

    /**
     * @return int
     */
    public static function  BHITSHAH_CAMPUS()
    {
        return self::$BHITSHAH_CAMPUS;
    }

    /**
     * @return int
     */
    public static function THATTA_CAMPUS()
    {
        return self::$THATTA_CAMPUS;
    }

    /**
     * @return string
     */
    public static function CAMPUS_OPTIONAL()
    {
        return self::$CAMPUS_OPTIONAL;
    }

    /**
     * @return string
     */
    public static function USER_TYPE()
    {
        return self::$USER_TYPE;
    }

    /**
     * @return int
     */
    public static function USER_CANDIDATE()
    {
        return self::$USER_CANDIDATE;
    }

    /**
     * @return int
     */
    public static function USER_SLIP_ISSUER()
    {
        return self::$USER_SLIP_ISSUER;
    }

    /**
     * @return int
     */
    public static function SINDH_PROVINCE_ID()
    {
        return self::$SINDH_PROVINCE_ID;
    }






    public static function yearOptions($yearSelected = 0){
        for($i = 2015;$i>1950;$i--){
            echo "<option value='$i' ";
            if($i == $yearSelected){
                echo " selected='true' ";
            }
            echo ">$i</option>";
        }
    }
}
?>