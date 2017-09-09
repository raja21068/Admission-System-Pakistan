package admission.reports.beans;

import java.util.List;

/**
 *
 * @author Raja Kumar & Jay Kumar
 */
public class SeatsBean {
    private String discipline;
    List<Integer> row;

    public SeatsBean(String discipline, List<Integer> row) {
        this.discipline = discipline;
        this.row = row;
    }

    public void setDiscipline(String discipline) {
        this.discipline = discipline;
    }

    public void setRow(List<Integer> row) {
        this.row = row;
    }

    public String getDiscipline() {
        return discipline;
    }

    public List<Integer> getRow() {
        return row;
    }
}
