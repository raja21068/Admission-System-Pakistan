package admission.controller.beans;

import admission.model.CredentialDetails;
import java.sql.SQLException;

/**
 *
 * @author Raja Kumar & Jay Kumar
 */
public class Candidate {
    private int candidateId;
    private int districtId;
    private int seatNo;
    private String name;
    private String fathersName;
    private String surname;
    private String gender;
    private String area;
    private int yearsDegree;
    private int testScore;
    private float percentage;
    private String group;
    private int deductionMarks;
    private boolean objection;
    private String objectionRemarks;
    private String subject1;
    private String subject2;
    private String subject3;
    private int candidateProgramOfStudyId;
    private int choiceNo;
    private admission.model.CredentialDetails[] credentialDetailses;
    private String status;
    private String provinceCode;
    private String districtName;
    private int cposgId;
    
    private int admissionListDetailsId;
    private int selectedCampusCategoryId;
    private int selectedCposgId;
    
//    private admission.model.Candidate candidate;
//    private CandidateProgramOfStudy cnpos;
    
    public Candidate() {
    }
    
    public void setArea(String area) {
        this.area = area;
    }

    public void setCandidateId(int candidateId) throws SQLException {
        this.candidateId = candidateId;
//        credentialDetailses = JDBCDatabaseManager.getCredentialDetails(candidateId);
    }

    public void setCandidateProgramOfStudyId(int candidateProgramOfStudyId) {
        this.candidateProgramOfStudyId = candidateProgramOfStudyId;
    }

    public void setChoiceNo(int choiceNo) {
        this.choiceNo = choiceNo;
    }

    public void setDeductionMarks(int deductionMarks) {
        this.deductionMarks = deductionMarks;
    }

    public void setDistrictId(int districtId) {
        this.districtId = districtId;
    }

    public void setFathersName(String fathersName) {
        this.fathersName = fathersName;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPercentage(float percentage) {
        this.percentage = percentage;
    }

    public void setSeatNo(int seatNo) {
        this.seatNo = seatNo;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setTestScore(int testScore) {
        this.testScore = testScore;
    }

    public void setYearsDegree(int yearsDegree) {
        this.yearsDegree = yearsDegree;
    }

    public String getArea() {
        return area;
    }

    public int getCandidateId() {
        return candidateId;
    }

    public int getCandidateProgramOfStudyId() {
        return candidateProgramOfStudyId;
    }

    public int getChoiceNo() {
        return choiceNo;
    }

    public int getDeductionMarks() {
        return deductionMarks;
    }

    public int getDistrictId() {
        return districtId;
    }

    public String getFathersName() {
        return fathersName;
    }

    public String getGender() {
        return gender;
    }

    public String getName() {
        return name;
    }

    public float getPercentage() {
        return percentage;
    }

    public int getSeatNo() {
        return seatNo;
    }

    public String getSurname() {
        return surname;
    }

    public int getTestScore() {
        return testScore;
    }

    public int getYearsDegree() {
        return yearsDegree;
    }

    public void setCredentialDetailses(CredentialDetails[] credentialDetailses) {
        this.credentialDetailses = credentialDetailses;
    }

    public CredentialDetails[] getCredentialDetailses() {
        return credentialDetailses;
    }

    public void setProvinceCode(String provinceCode) {
        this.provinceCode = provinceCode;
    }

    public String getProvinceCode() {
        return provinceCode;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setCposgId(int cposgId) {
        this.cposgId = cposgId;
    }

    public int getCposgId() {
        return cposgId;
    }

    public String getGroup() {
        return group;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }

    public String getDistrictName() {
        return districtName;
    }

    public void setObjection(boolean objection) {
        this.objection = objection;
    }

    public boolean isObjection() {
        return objection;
    }

    public void setObjectionRemarks(String objectionRemarks) {
        this.objectionRemarks = objectionRemarks;
    }

    public String getObjectionRemarks() {
        return objectionRemarks;
    }

    public void setSubject1(String subject1) {
        this.subject1 = subject1;
    }

    public void setSubject2(String subject2) {
        this.subject2 = subject2;
    }

    public void setSubject3(String subject3) {
        this.subject3 = subject3;
    }

    public String getSubject1() {
        return subject1;
    }

    public String getSubject2() {
        return subject2;
    }

    public String getSubject3() {
        return subject3;
    }

    public void setAdmissionListDetailsId(int admissionListDetailsId) {
        this.admissionListDetailsId = admissionListDetailsId;
    }

    public int getAdmissionListDetailsId() {
        return admissionListDetailsId;
    }

    
    public void setSelectedCposgId(int selectedCposgId) {
        this.selectedCposgId = selectedCposgId;
    }

    public void setSelectedCampusCategoryId(int selectedCampusCategoryId) {
        this.selectedCampusCategoryId = selectedCampusCategoryId;
    }

    public int getSelectedCposgId() {
        return selectedCposgId;
    }

    public int getSelectedCampusCategoryId() {
        return selectedCampusCategoryId;
    }
    
    
//    public String getInterGroup(){
//        if(this.getCredentialDetailses() == null) return "";
//        CredentialDetails inter = this.getCredentialDetailses().get(1);
//        return inter.getGroup_();
//    }

    public void setGroup(String group) {
        this.group = group;
    }
}