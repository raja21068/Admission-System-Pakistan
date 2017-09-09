package admission.enums;

/**
 *
 * @author Yougeshwar
 */
public enum SemesterEnum {

    TWO_SEMESTER("2 Semester", 2),
    FOUR_SEMESTER("4 Semester", 4),
    SIX_SEMESTER("6 Semester", 6),
    EIGHT_SEMESTER("8 Semester", 8),
    TEN_SEMESTER("10 Semester", 10),
    ;
    
    private String name;
    private int semester;

    private SemesterEnum(String name, int semester) {
        this.name = name;
        this.semester = semester;
    }

    public String getName() {
        return name;
    }

    public int getSemester() {
        return semester;
    }

    @Override
    public String toString() {
        return name; //To change body of generated methods, choose Tools | Templates.
    }
}
