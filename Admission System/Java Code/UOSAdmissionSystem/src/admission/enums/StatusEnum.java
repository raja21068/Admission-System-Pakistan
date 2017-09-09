package admission.enums;

/**
 *
 * @author Yougeshwar available 
 */
public enum StatusEnum implements IBasicDetail {
    AVAILABLE("A", "Available"),
    UN_AVAILABLE("U", "Un-Available");
    
    private String code;
    private String title;

    private StatusEnum(String code, String title) {
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