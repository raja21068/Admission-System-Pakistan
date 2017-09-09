package admission.reports.beans;

import java.util.List;

/**
 *
 * @author Yougeshwar
 */
public class ChoicesCheckerJRBean {
    private String campus;
    private String shift;
    private List<String> choicesList;

    public String getCampus() {
        return campus;
    }

    public void setCampus(String campus) {
        this.campus = campus;
    }

    public String getShift() {
        return shift;
    }

    public void setShift(String shift) {
        this.shift = shift;
    }
    
    public List<String> getChoicesList() {
        return choicesList;
    }

    public void setChoicesList(List<String> choicesList) {
        this.choicesList = choicesList;
    }
    
}
