package admission.enums;

/**
 *
 * @author Yougeshwar
 */
public enum GenderEnum implements IBasicDetail {
    MALE("M", "Male"),
    FEMALE("F", "Female"),
    OTHER("O", "Other");
    
    private String code;
    private String title;

    private GenderEnum(String code, String title) {
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
