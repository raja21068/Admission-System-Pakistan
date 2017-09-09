package admission.enums;

/**
 *
 * @author Yougeshwar
 */
public enum FeeOfEnum implements IBasicDetail {
    ENROLLMENT_FEE_OTHER_BOARD_UNI("OBU", "Enrollment fee from Other Board or Universities"), // Enrollment fee for migration
    ELIGIBILITY_CRFT_OUT_OF_JURISDICTION("OUT-J", "Eligibilty Crft out of jurisdiction"),
    OTHER_PROVINCES("OP", "Other Provinces"),
    ADMISSION_FEE_SFE("AF_SFE", "Evening Admission Fee"),
    ;
    
    private String code;
    private String title;

    private FeeOfEnum(String code, String title) {
        this.code = code;
        this.title = title;
    }

    @Override
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return title;
    }
}