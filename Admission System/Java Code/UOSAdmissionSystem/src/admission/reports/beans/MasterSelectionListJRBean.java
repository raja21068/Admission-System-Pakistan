package admission.reports.beans;

import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

/**
 *
* @author Raja Kumar & Jay Kumar, Adeel
 */
public class MasterSelectionListJRBean {
    private String campus;
    private String shift;
    private String discipline;
    private String campusLocation;
    private JRBeanCollectionDataSource candidatesList;
    
    public MasterSelectionListJRBean() {
    }

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

    public String getDiscipline() {
        return discipline;
    }

    public void setDiscipline(String discipline) {
        this.discipline = discipline;
    }

    public String getCampusLocation() {
        return campusLocation;
    }

    public void setCampusLocation(String campusLocation) {
        this.campusLocation = campusLocation;
    }

    public JRBeanCollectionDataSource getCandidatesList() {
        return candidatesList;
    }

    public void setCandidatesList(JRBeanCollectionDataSource candidatesList) {
        this.candidatesList = candidatesList;
    }

}
