package admission.model.view;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "CANDIDATE_CHOICES_CHECKER_VIEW"
)
public class CandidateChoicesChecker implements java.io.Serializable {

    private Integer id;
    private Integer candidateId;
    private String campus;
    private String shift;
    private String choices;
    private Integer displayOrder;
    private Integer isMorning;
    
    public CandidateChoicesChecker() {
    }

    @Id
    @Column(name = "ID", unique = true, nullable = false)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "CANDIDATE_ID")
    public Integer getCandidateId() {
        return candidateId;
    }

    public void setCandidateId(Integer candidateId) {
        this.candidateId = candidateId;
    }

    @Column(name = "CAMPUS")
    public String getCampus() {
        return campus;
    }

    public void setCampus(String campus) {
        this.campus = campus;
    }

    @Column(name = "SHIFT")
    public String getShift() {
        return shift;
    }

    public void setShift(String shift) {
        this.shift = shift;
    }

    @Column(name = "CHOICES")
    public String getChoices() {
        return choices;
    }

    public void setChoices(String choices) {
        this.choices = choices;
    }

    @Column(name = "DISPLAY_ORDER")
    public Integer getDisplayOrder() {
        return displayOrder;
    }

    public void setDisplayOrder(Integer displayOrder) {
        this.displayOrder = displayOrder;
    }

    @Column(name = "IS_MORNING")
    public Integer getIsMorning() {
        return isMorning;
    }

    public void setIsMorning(Integer isMorning) {
        this.isMorning = isMorning;
    }
    
}
