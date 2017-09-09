package admission.reports.beans;

import admission.model.CampusCategory;
import admission.model.CposGroup;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Raja Kumar & Jay Kumar
 */
public class ProgramOfStudyDataBean {
    private CposGroup cposg;
    private List<CampusCategory> campusCategories;
    private List<Object[]> candidatesData;
    
    private String posName;
    
    public ProgramOfStudyDataBean() {}

    public ProgramOfStudyDataBean(CposGroup cposg, List<CampusCategory> campusCategories) {
        this.cposg = cposg;
        this.campusCategories = campusCategories;
    }

    public ProgramOfStudyDataBean(CposGroup cposg) {
        this.cposg = cposg;
        this.campusCategories = new ArrayList<>();
        this.candidatesData = new ArrayList<>();
    }

    public ProgramOfStudyDataBean(CposGroup cposg, List<Object[]> candidatesData, String posName) {
        this.cposg = cposg;
        this.candidatesData = candidatesData;
    }
    
    
    public void setCposg(CposGroup cposg) {
        this.cposg = cposg;
    }

    public CposGroup getCposg() {
        return cposg;
    }

    public void setCampusCategories(List<CampusCategory> campusCategories) {
        this.campusCategories = campusCategories;
    }

    public List<CampusCategory> getCampusCategories() {
        return campusCategories;
    }

    public void setCandidatesData(List<Object[]> candidatesData) {
        this.candidatesData = candidatesData;
    }

    public List<Object[]> getCandidatesData() {
        return candidatesData;
    }

    public void setPosName(String posName) {
        this.posName = posName;
    }

    public String getPosName() {
        return posName;
    }
}
