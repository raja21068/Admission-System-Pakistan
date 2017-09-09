package admission.enums;

/**
 *
 * @author Yougeshwar
 */
public enum DetailOfEnum implements IBasicDetail {
    SSC("S", "SSC"),
    HSC("H", "HSC"),
    GRADUATION("G", "GRD");
    
    private String code;
    private String title;

    private DetailOfEnum(String code, String title) {
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