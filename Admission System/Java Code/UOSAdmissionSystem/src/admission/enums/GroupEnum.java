package admission.enums;

/**
 *
 * @author Yougeshwar
 */
public enum GroupEnum implements IBasicDetail {
    GENERAL("G", "General"),
    ENGINEERING("PE", "Engineering"),
    MEDICAL("PM", "Medical"),
    COMMERCE("PC", "Commerce"),
    COMMERCE_GENERAL("CG", "Commerce General"),
    ARTS("AR", "Arts");

    private String code;
    private String title;

    private GroupEnum(String code, String title) {
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
