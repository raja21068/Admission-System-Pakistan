package admission.enums;

/**
 *
 * @author Yougeshwar
 */
public enum JurisdictionEnum implements IBasicDetail {
    BOTH("B", "Both"),
    INSIDE("I", "Inside"),
    OUT_OF("O", "Out of");
    
    private String code;
    private String title;

    private JurisdictionEnum(String code, String title) {
        this.code = code;
        this.title = title;
    }

    public String getCode() {
        return code;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public String toString() {
        return title;
    }
    
}
