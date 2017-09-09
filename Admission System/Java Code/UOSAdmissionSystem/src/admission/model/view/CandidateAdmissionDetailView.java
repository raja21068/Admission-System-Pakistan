package admission.model.view;

import admission.enums.CategoryEnum;
import admission.enums.DurationEnum;
import admission.enums.OrientedEnum;
import admission.enums.ProgramTypeEnum;
import admission.enums.SemesterEnum;
import admission.enums.ShiftEnum;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "YOGV_CANDIDATE_ADMISSION_DETAIL"
)
public class CandidateAdmissionDetailView implements java.io.Serializable {

    private Integer id;
    private Integer candidateId;
    private Integer admissionListId;
    private Integer admissionYearId;
    private ProgramTypeEnum programType;
    private ShiftEnum shift;
    private Integer cposgId;
    private Integer cposId;
    private Integer posId;
    private Integer programId;
    private Integer disciplineId;
    private Integer facultyId;
    private OrientedEnum oriented;
    private CategoryEnum category;
    private DurationEnum duration;
    private SemesterEnum semester;
    private Boolean nonSindh;
    private Boolean boardJurisdication;
    private Integer seatNo;
    private String name;
    private String fathersName;
    private String discipline;
    private String program;
    
    public CandidateAdmissionDetailView() {
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

    @Column(name = "ADMISSION_YEAR_ID")
    public Integer getAdmissionYearId() {
        return admissionYearId;
    }

    public void setAdmissionYearId(Integer admissionYearId) {
        this.admissionYearId = admissionYearId;
    }

    @Column(name = "PROGRAM_TYPE")
    @Enumerated
    public ProgramTypeEnum getProgramType() {
        return programType;
    }

    public void setProgramType(ProgramTypeEnum programType) {
        this.programType = programType;
    }

    @Column(name = "SHIFT")
    @Enumerated
    public ShiftEnum getShift() {
        return shift;
    }

    public void setShift(ShiftEnum shift) {
        this.shift = shift;
    }
    
    @Column(name = "CPOS_GROUP_ID")
    public Integer getCposgId() {
        return cposgId;
    }

    public void setCposgId(Integer cposgId) {
        this.cposgId = cposgId;
    }

    @Column(name = "CAMPUS_PROGRAM_OF_STUDY_ID")
    public Integer getCposId() {
        return cposId;
    }

    public void setCposId(Integer cposId) {
        this.cposId = cposId;
    }

    @Column(name = "PROGRAM_OF_STUDY_ID")
    public Integer getPosId() {
        return posId;
    }

    public void setPosId(Integer posId) {
        this.posId = posId;
    }

    @Column(name = "PROGRAM_ID")
    public Integer getProgramId() {
        return programId;
    }

    public void setProgramId(Integer programId) {
        this.programId = programId;
    }

    @Column(name = "FACULTY_ID")
    public Integer getFacultyId() {
        return facultyId;
    }

    public void setFacultyId(Integer facultyId) {
        this.facultyId = facultyId;
    }
    
    @Column(name = "DISCIPLINE_ID")
    public Integer getDisciplineId() {
        return disciplineId;
    }

    public void setDisciplineId(Integer disciplineId) {
        this.disciplineId = disciplineId;
    }

    @Column(name = "ORIENTED")
    @Enumerated
    public OrientedEnum getOriented() {
        return oriented;
    }

    public void setOriented(OrientedEnum oriented) {
        this.oriented = oriented;
    }
    
    @Column(name = "CATEGORY")
    public CategoryEnum getCategory() {
        return category;
    }

    public void setCategory(CategoryEnum category) {
        this.category = category;
    }

    @Column(name = "DURATION")
    @Enumerated
    public DurationEnum getDuration() {
        return duration;
    }

    public void setDuration(DurationEnum duration) {
        this.duration = duration;
    }

    @Column(name = "SEMESTER")
    @Enumerated
    public SemesterEnum getSemester() {
        return semester;
    }

    public void setSemester(SemesterEnum semester) {
        this.semester = semester;
    }

    @Column(name = "IS_NON_SINDH")
    public Boolean isNonSindh() {
        return nonSindh;
    }

    public void setNonSindh(Boolean nonSindh) {
        this.nonSindh = nonSindh;
    }

    @Column(name = "IS_BOARD_JURISDICTION")
    public Boolean isBoardJurisdication() {
        return boardJurisdication;
    }
    
    public void setBoardJurisdication(Boolean boardJurisdication) {
        this.boardJurisdication = boardJurisdication;
    }

    @Column(name = "SEAT_NO")
    public Integer getSeatNo() {
        return seatNo;
    }

    public void setSeatNo(Integer seatNo) {
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
    
}
