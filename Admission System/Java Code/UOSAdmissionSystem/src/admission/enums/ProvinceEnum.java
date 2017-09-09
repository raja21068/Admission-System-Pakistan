package admission.enums;

/**
 *
 * @author Yougeshwar
 */
public enum ProvinceEnum implements IBasicDetail {
    SINDH("SD", "Sindh"), // SINDH
    PUNJAB("PB", "Punjab"), // PUNJAB
    KP("KP", "Khyber Pakhtunkhwa"), 
    BALOCHISTAN("BK", "Balochistan"), 
    FATA("FATA", "Fata"), 
    AJK("AJK", "AJK"), 
    NA("NA", "Nothern Areas"), 
    OTHERS("OTH", "Others"), 
    NWAP("NWAP", "NWAP"); 

    private String code;
    private String title;

    private ProvinceEnum(String code, String title) {
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
