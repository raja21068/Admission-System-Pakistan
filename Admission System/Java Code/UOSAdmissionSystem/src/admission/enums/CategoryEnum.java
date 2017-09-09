package admission.enums;

/**
 *
 * @author Yougeshwar
 */
public enum CategoryEnum implements IBasicDetail {
    GM_DUR_QUOTA("GM_DUR_QUOTA", "GEN", "GENERAL MERIT/DISTRICT U/R QUOTA QUOTA"), // 0
    SFM_QUOTA("SFM_QUOTA", "SFM", "SELF FINANCE (MORNING) QUOTA"), // 1
    SFE_QUOTA("SFE_QUOTA", "SFE", "SELF FINANCE (EVENING) QUOTA"), // 2
    SUE_QUOTA("SUE_QUOTA", "SUE", "S.U. EMPLOYEES QUOTA"), // 3
    DP_QUOTA("DP_QUOTA", "DP", "DISABLED PERSON QUOTA"), // 4
    NO_QUOTA("NO_QUOTA", "NO", "NOMINATION QUOTA"), // 5
    AC_QUOTA("AC_QUOTA", "AC", "AFFILIATED COLLEGE QUOTA"), // 6
    SP_QUOTA("SP_QUOTA", "SP", "SPORTS QUOTA"), // 7
    FR_QUOTA("FR_QUOTA", "FR", "FOREIGN QUOTA"), // 8
    AP_QUOTA("AP_QUOTA", "AP", "ARMY PERSONAL QUOTA"), // 9
    CN_SUE_QUOTA("CN_SUE_QUOTA", "CNSUE", "Center S.U. EMPLOYEES QUOTA"), // 10
    KHI_RES_QUOTA("KHI_RES_QUOTA", "KHIRQ", "KARACHI RESERVED QUOTA");//11
    
    private String title;
    private String code;
    private String desc;

    private CategoryEnum(String title, String code, String desc) {
        this.title = title;
        this.code = code;
        this.desc = desc;
    }

    public String getTitle() {
        return title;
    }

    public String getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

    @Override
    public String toString() {
        return desc;
    }
    
}
