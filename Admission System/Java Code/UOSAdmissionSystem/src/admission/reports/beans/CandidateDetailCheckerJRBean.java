package admission.reports.beans;

import java.util.List;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

/**
 *
 * @author Yougeshwar
 */
public class CandidateDetailCheckerJRBean {
    private int seatNo;
    private String gender;
    private String name;
    private String fathersName;
    private String surname;
    private String district;
    private String area;
    private String user;
    private int chalanFee;
    private String chalanNo;
    private String chalanDate;
    private int yearsDegree;
    private String objectionRemarks;
    private List<String> optionalSubject;
    private List<String> categoryList;
    private JRBeanCollectionDataSource credentialDetails;
    private JRBeanCollectionDataSource choices;

    public int getSeatNo() {
        return seatNo;
    }

    public void setSeatNo(int seatNo) {
        this.seatNo = seatNo;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFathersName() {
        return fathersName;
    }

    public void setFathersName(String fathersName) {
        this.fathersName = fathersName;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public List<String> getCategoryList() {
        return categoryList;
    }

    public void setCategoryList(List<String> categoryList) {
        this.categoryList = categoryList;
    }

    public int getChalanFee() {
        return chalanFee;
    }

    public void setChalanFee(int chalanFee) {
        this.chalanFee = chalanFee;
    }

    public String getChalanNo() {
        return chalanNo;
    }

    public void setChalanNo(String chalanNo) {
        this.chalanNo = chalanNo;
    }

    public String getChalanDate() {
        return chalanDate;
    }

    public void setChalanDate(String chalanDate) {
        this.chalanDate = chalanDate;
    }

    public int getYearsDegree() {
        return yearsDegree;
    }

    public void setYearsDegree(int yearsDegree) {
        this.yearsDegree = yearsDegree;
    }

    public String getObjectionRemarks() {
        return objectionRemarks;
    }

    public void setObjectionRemarks(String objectionRemarks) {
        this.objectionRemarks = objectionRemarks;
    }

    public List<String> getOptionalSubject() {
        return optionalSubject;
    }

    public void setOptionalSubject(List<String> optionalSubject) {
        this.optionalSubject = optionalSubject;
    }
    
    

    public JRBeanCollectionDataSource getCredentialDetails() {
        return credentialDetails;
    }

    public void setCredentialDetails(JRBeanCollectionDataSource credentialDetails) {
        this.credentialDetails = credentialDetails;
    }

    public JRBeanCollectionDataSource getChoices() {
        return choices;
    }

    public void setChoices(JRBeanCollectionDataSource choices) {
        this.choices = choices;
    }
}
