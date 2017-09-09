package admission.enums;


public enum SelectionStatusEnum implements IBasicDetail {
    SELECTED("S", "Selected"),
    WAITING("W", "Waiting");
    
    private String code;
    private String title;

    private SelectionStatusEnum(String code, String title) {
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
