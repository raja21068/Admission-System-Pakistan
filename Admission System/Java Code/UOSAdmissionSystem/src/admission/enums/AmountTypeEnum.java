package admission.enums;

/**
 *
 * @author Yougeshwar
 */
public enum AmountTypeEnum implements IBasicDetail {

    PARTIAL("P", "Partial Amount"),
    TOTAL("T", "Total Amount");

    private String code;
    private String title;

    private AmountTypeEnum(String code, String title) {
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
