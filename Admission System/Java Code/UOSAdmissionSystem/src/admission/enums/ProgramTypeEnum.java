package admission.enums;

/**
 *
 * @author Yougeshwar
 */
public enum ProgramTypeEnum implements IBasicDetail {
    BACHELOR("B", "Bachelor"),
    MASTER("M", "Master"),
    BOTH("B", "Both");
    
    private String code;
    private String title;

    private ProgramTypeEnum(String code, String title) {
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