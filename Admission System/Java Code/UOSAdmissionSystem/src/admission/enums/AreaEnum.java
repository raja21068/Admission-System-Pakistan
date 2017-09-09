package admission.enums;

/**
 *
 * @author Yougeshwar
 */
public enum AreaEnum implements IBasicDetail {

    URBAN("U", "Urban"),
    RURAL("R", "Rural");

    private String code;
    private String title;

    private AreaEnum(String code, String title) {
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
