package admission.enums;

/**
 *
 * @author Yougeshwar
 */
public enum OrientedEnum implements IBasicDetail {
    QUOTA("Q", "Quota Oriented"),
    NON_QUOTA("N", "Non-Qouta Oriented");
    
    private String code;
    private String title;

    private OrientedEnum(String code, String title) {
        this.code = code;
        this.title = title;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

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