package admission.enums;

/**
 *
 * @author Yougeshwar
 */
public enum DurationEnum {

    ONE_YEAR("1 Year", 1),
    TWO_YEAR("2 Year", 2),
    THREE_YEAR("3 Year", 3),
    FOUR_YEAR("4 Year", 4),
    FIVE_YEAR("5 Year", 5),
    ;
    
    private String name;
    private int year;

    private DurationEnum(String name, int year) {
        this.name = name;
        this.year = year;
    }

    public String getName() {
        return name;
    }

    public int getYear() {
        return year;
    }

    @Override
    public String toString() {
        return name; //To change body of generated methods, choose Tools | Templates.
    }
}
