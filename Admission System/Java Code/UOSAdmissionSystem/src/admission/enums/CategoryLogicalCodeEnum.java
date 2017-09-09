package admission.enums;

/**
 *
 * @author Yougeshwar
 */
public enum CategoryLogicalCodeEnum {

    GENERAL_MERIT_QUOTA(0, "General Merit (Jurisdiction)"),
    UPPER_SINDH_OUT_OF_JURISDICTION_QUOTA(1, "General Merit (Out of Jurisdiction)"),
    FEMALE_JURISDICTION(2, "Female Quota (Jurisdiction)"),
    FEMALE_OUT_OF_JURISDICTION(3, "Female Quota (Out of Jurisdiction)"),
    DISABLE_QUOTA(4, "Disabled Person Quota"),
    SPORTS_QUOTA(5, "Sports Quota"),
    SUE_SD_QUOTA(6, "S.U. Employees Quota"), // SINDH UNIVERSITY EMPOLYEE DAUGTHER SON
    
    SUE_SD_AC_QUOTA(7, "S.U. Affilated College Quota"), // SINDH UNIVERSITY EMPOLYEE AFFILATEDCOLLEGE SISTER BROTHER
    SUE_SD_NCEAC_QUOTA(8, "S.U. Employees of NCEAC"), // SINDH UNIVERSITY EMPOLYEE OF NATIONAL CENTER OF EXCELLENCE IN ANALYTICAL CHMISTRY

    NOMINEE_NA_QUOTA(9, "Nominee Of Northern Areas"), // NOMINEE OF NORTHERN AREAS

    SELF_FINANCE_QUOTA(10, "Self Finance "),
    OP_SELF_FINANCE_QUOTA(11, "Other Provinces Self Finance "),
    PUNJAB_QUOTA(12, "Punjab Provinces"), // NOMINEE OF OTHER PROVINCES
    BLOCHISTAN_QUOTA(13, "Nominee Of Balochistan Province"), // NOMINEE OF BLOCHISTAN PROVINCES
    NWFP_QUOTA(14, "Nominee Of N.W.F.P Province"), // NOMINEE OF N.W.F.P PROVINCES
    AJK_GOV_QUOTA(15, "Nominee Of A.J.K Goverment"), // NOMINEE OF A.J.K GOVERMENT
    NOMINEE_PHARM_QUOTA(16, "Nominee Of Pharmaceutical Industry"), // NOMINEE OF PHARMACEUTICAL INDUSTRY

    ARMY_PER_QUOTA(17, "Nomination of Army Personal"), // ARMY PERSONAL (ONLY FOR MASTER'S DEGREE PROGRAM)

    FOREIGN_PKTAP_MERIT_QUOTA(18, "Foreign PKTAP MERIT"),
    FOREIGN_SELF_QUOTA(19, "Foreign Nationals Self Finance"),
    COMMERCE_QUOTA(20, "Commerce Quota"),  // COMMERCE
    FATA_QUOTA(21, "Nominee Of Fata"), // NOMINEE OF BALOCHISTAN AND FATA
    TEST_QUOTA(22, "TEST"),// NOMINEE OF BALOCHISTAN AND FATA
    KHI_QUOTA(23, "KARACHI QUOTA"); 

    private int code;
    private String title;

    private CategoryLogicalCodeEnum(int code, String title) {
        this.code = code;
        this.title = title;
    }

    public int getCode() {
        return code;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public String toString() {
        return title;
    }
}
