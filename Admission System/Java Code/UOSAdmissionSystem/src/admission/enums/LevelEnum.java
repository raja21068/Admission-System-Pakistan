package admission.enums;

/**
 *
 * @author Yougeshwar
 */
public enum LevelEnum implements IBasicDetail {
    SSC("S", "SSC"),
    HSC("H", "HSC"),
    OTHER("O", "Other"),
    NONE("N", "None");
    
    private String code;
    private String title;

    private LevelEnum(String code, String title) {
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