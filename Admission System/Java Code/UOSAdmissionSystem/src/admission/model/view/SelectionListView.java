package admission.model.view;

import admission.enums.AreaEnum;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "YOGV_SELECTION_LIST"
)
public class SelectionListView implements java.io.Serializable {

    private Integer id;
    private Integer candidateId;
    private Integer admissionListId;
    private String campus;
    private String campusLocation;
    private String shift;
    private String discipline;
    private String program;
    private int seatNo;
    private String name;
    private String fathersName;
    private String district;
    private AreaEnum area;
    private int choiceNo;
    private int deductionMarks;
    private int testScore;
    private float percentage;
    private String category;
    private int catDisplayOrder;
    
    public SelectionListView() {
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

    public void setCandidateId(Integer admissionListDetailId) {
        this.candidateId = admissionListDetailId;
    }

    @Column(name = "ADMISSION_LIST_ID")
    public Integer getAdmissionListId() {
        return admissionListId;
    }

    public void setAdmissionListId(Integer admissionListId) {
        this.admissionListId = admissionListId;
    }

    @Column(name = "CAMPUS")
    public String getCampus() {
        return campus;
    }

    public void setCampus(String campus) {
        this.campus = campus;
    }

    @Column(name = "CAMPUS_LOCATION")
    public String getCampusLocation() {
        return campusLocation;
    }

    public void setCampusLocation(String campusLocation) {
        this.campusLocation = campusLocation;
    }

    @Column(name = "SHIFT")
    public String getShift() {
        return shift;
    }

    public void setShift(String shift) {
        this.shift = shift;
    }

    @Column(name = "DISCIPLINE")
    public String getDiscipline() {
        return discipline;
    }

    public void setDiscipline(String discipline) {
        this.discipline = discipline;
    }

    @Column(name = "PROGRAM")
    public String getProgram() {
        return program;
    }

    public void setProgram(String program) {
        this.program = program;
    }

    @Column(name = "SEAT_NO")
    public int getSeatNo() {
        return seatNo;
    }

    public void setSeatNo(int seatNo) {
        this.seatNo = seatNo;
    }
    
    @Column(name = "NAME")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "FATHERS_NAME")
    public String getFathersName() {
        return fathersName;
    }

    public void setFathersName(String fathersName) {
        this.fathersName = fathersName;
    }

    @Column(name = "DISTRICT")
    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    @Column(name = "AREA")
    @Enumerated
    public AreaEnum getArea() {
        return area;
    }

    public void setArea(AreaEnum area) {
        this.area = area;
    }

    @Column(name = "CHOICE_NO")
    public int getChoiceNo() {
        return choiceNo;
    }

    public void setChoiceNo(int choiceNo) {
        this.choiceNo = choiceNo;
    }

    @Column(name = "DEDUCTION_MARKS")
    public int getDeductionMarks() {
        return deductionMarks;
    }

    public void setDeductionMarks(int deductionMarks) {
        this.deductionMarks = deductionMarks;
    }

    @Column(name = "TEST_SCORE")
    public int getTestScore() {
        return testScore;
    }

    public void setTestScore(int testScore) {
        this.testScore = testScore;
    }

    @Column(name = "PERCENTAGE")
    public float getPercentage() {
        return percentage;
    }

    public void setPercentage(float percentage) {
        this.percentage = percentage;
    }

    @Column(name = "CATEGORY")
    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Column(name = "CAT_DISPLAY_ORDER")
    public int getCatDisplayOrder() {
        return catDisplayOrder;
    }

    public void setCatDisplayOrder(int catDisplayOrder) {
        this.catDisplayOrder = catDisplayOrder;
    }

}
