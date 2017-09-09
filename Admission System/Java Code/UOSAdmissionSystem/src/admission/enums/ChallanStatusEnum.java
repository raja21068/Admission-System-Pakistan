package admission.enums;

/**
 *
 * @author Yougeshwar
 */
public enum ChallanStatusEnum implements IBasicDetail {

    SUBMITTED("S", "Submitted"),
    NON_SUBMITTED("NS", "Non-Submitted");

    private String code;
    private String title;

    private ChallanStatusEnum(String code, String title) {
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
