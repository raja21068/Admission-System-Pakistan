package admission.enums;

/**
 *
 * @author Yougeshwar
 */
public enum FeeCategoryTypeEnum implements IBasicDetail {
    MERIT("M", "Merit"),
    SFM("SFM", "Self Finance Morning"),
    SFE("SFE", "Self Finance Evening"),
    FOREIGN("FOR", "Forgein National"),
    ;
    
    private String code;
    private String title;

    private FeeCategoryTypeEnum(String code, String title) {
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