package admission.model.view;

import admission.enums.AreaEnum;
import admission.enums.GenderEnum;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "CANDIDATE_DETAIL_CHECKER_VIEW"
)
public class CandidateDetailChecker implements java.io.Serializable {

    private Integer id;
    private Integer admissionYearId;
    private Integer programTypeId;
    private Integer seatNo;
    private GenderEnum gender;
    private String name;
    private String fathersName;
    private String surname;
    private String district;
    private AreaEnum area;
    private String appliedCategories;
    private String user;
    private Integer chalanFee;
    private String chalanNo;
    private java.util.Date chalanDate;
    private Integer yearsDegree;
    private String objectionRemarks;
    
    public CandidateDetailChecker() {
    }

    @Id
    @Column(name = "ID", unique = true, nullable = false)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "ADMISSION_YEAR_ID")
    public Integer getAdmissionYearId() {
        return admissionYearId;
    }

    public void setAdmissionYearId(Integer admissionYearId) {
        this.admissionYearId = admissionYearId;
    }

    @Column(name = "PROGRAM_TYPE_ID")
    public Integer getProgramTypeId() {
        return programTypeId;
    }

    public void setProgramTypeId(Integer programTypeId) {
        this.programTypeId = programTypeId;
    }

    @Column(name = "SEAT_NO")
    public Integer getSeatNo() {
        return seatNo;
    }

    public void setSeatNo(Integer seatNo) {
        this.seatNo = seatNo;
    }

    @Column(name = "GENDER")
    @Enumerated
    public GenderEnum getGender() {
        return gender;
    }

    public void setGender(GenderEnum gender) {
        this.gender = gender;
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

    @Column(name = "SURNAME")
    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
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

    @Column(name = "APPLIED_CATEGORIES")
    public String getAppliedCategories() {
        return appliedCategories;
    }

    public void setAppliedCategories(String appliedCategories) {
        this.appliedCategories = appliedCategories;
    }

    @Column(name = "USER")
    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    @Column(name = "CHALAN_DATE")
    public java.util.Date getChalanDate() {
        return chalanDate;
    }

    public void setChalanDate(java.util.Date chalanDate) {
        this.chalanDate = chalanDate;
    }

    @Column(name = "CHALAN_FEE")
    public Integer getChalanFee() {
        return chalanFee;
    }

    public void setChalanFee(Integer chalanFee) {
        this.chalanFee = chalanFee;
    }

    @Column(name = "CHALAN_NO")
    public String getChalanNo() {
        return chalanNo;
    }

    public void setChalanNo(String chalanNo) {
        this.chalanNo = chalanNo;
    }

    @Column(name = "OBJECTION_REMARKS")
    public String getObjectionRemarks() {
        return objectionRemarks;
    }

    public void setObjectionRemarks(String objectionRemarks) {
        this.objectionRemarks = objectionRemarks;
    }

    @Column(name = "YEARS_DEGREE")
    public Integer getYearsDegree() {
        return yearsDegree;
    }

    public void setYearsDegree(Integer yearsDegree) {
        this.yearsDegree = yearsDegree;
    }
}
