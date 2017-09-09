package admission.reports.beans;

import java.util.List;

/**
 *
 * @author Raja Kumar & Jay Kumar
 */
public class SeatsDistributionDataBean {
    String faculty;
    List<SeatsBean> rows;

    public SeatsDistributionDataBean(String faculty, List<SeatsBean> rows) {
        this.faculty = faculty;
        this.rows = rows;
    }
    
    
    public String getFaculty() {
        return faculty;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    public void setRows(List<SeatsBean> rows) {
        this.rows = rows;
    }

    public List<SeatsBean> getRows() {
        return rows;
    }
}
