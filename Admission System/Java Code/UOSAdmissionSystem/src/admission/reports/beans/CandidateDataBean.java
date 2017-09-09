package admission.reports.beans;

import admission.model.CredentialDetails;
import admission.model.District;
import admission.model.Taluka;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import admission.utils.IConstant;
import admission.utils.DateUtility;
import admission.utils.RollNoFormatter;

/**
 *
 * @author Raja Kumar & Jay Kumar
 */
public class CandidateDataBean {
     //private Integer candidateId;
     private int seatNo;
     private String gender;
     private String name;
     private String fathersName;
     private String surname;
     private String area;
     private District district;
     private Taluka taluka;
//     private List<CredentialDetails> credentialDetails;
     private CredentialDetails ssc;
     private CredentialDetails hsc;
     private List<String> appliedCategories;
     private List<String> appliedCampuses;
     private List<String> candidateProgramOfStudies; 

     private String rollNo;
     private String program;
     private String deptName;
     private int year;
     private String validUpto;
     private String address;
     private String cnic;
     private java.awt.Image image;
     
    public CandidateDataBean(){
        this.year = DateUtility.getYear(new java.util.Date());
    }
     
    public CandidateDataBean(District district, Taluka taluka, int seatNo, String name, String fathersName, String surname, String gender, String area, CredentialDetails ssc, CredentialDetails hsc,/*List<CredentialDetails> credentialDetails,*/ List<String> candidateProgramOfStudies, List<String> appliedCategories, List<String> appliedCampuses) {
       this.district = district;
       this.taluka = taluka;
       this.seatNo = seatNo;
       this.name = name;
       this.fathersName = fathersName;
       this.surname = surname;
       this.gender = gender;
       this.area = area;
       this.ssc = ssc;
       this.hsc = hsc;
//       this.credentialDetails = credentialDetails;
       this.appliedCategories = appliedCategories;
       this.appliedCampuses = appliedCampuses;
    }
   
    public CandidateDataBean(Map<String, Object> data, int year, boolean isBachalor) {
        this.year = year;
        this.seatNo = (Integer)data.get("seat_no");
        this.name = (String) data.get("name");
        this.fathersName = (String) data.get("fname");
        this.surname = (String) data.get("surname");
        this.rollNo = RollNoFormatter.format(year, (String) data.get("code"), Integer.parseInt((String)data.get("roll_no")));
        this.program = admission.utils.Utility.programFormat(isBachalor, Boolean.parseBoolean((String) data.get("is_morning")), (String) data.get("program"), (String) data.get("selection"), (String) data.get("part"));
        this.deptName = (String) data.get("department");
        this.year = DateUtility.getYear(new Date());
        this.validUpto = "DECEMBER " + (year);
        this.address = (String) data.get("address");
        this.cnic = (String) data.get("cnic");

        String discipline = (String) data.get("selection");
        if(discipline.equalsIgnoreCase("COMPUTER SCIENCE")){
            String code  = (String) data.get("code");
            if(code.contains("CSM"))rollNo+="   (Pre-Medical Group)";
            if(code.contains("CSE"))rollNo+="   (Pre-Engineering Group)";
            if(code.contains("CSC"))rollNo+="   (Pre-Commerce Group)";
        }
        File file = new File(IConstant.PATH + data.get("image_path"));
        try {
            image = ImageIO.read(file);
        } catch (IOException ex) {
            Logger.getLogger(CandidateDataBean.class.getName()).log(Level.SEVERE, null, ex);
        }
//        this.image = ImageUtils.readImage((byte[]) data.get("image"));
        if(cnic.length() < 15) {
            int c = 15 - cnic.length();
            for (int i = 0; i < c; i++) {
                cnic += " ";
            }
        }
    }
    
    public District getDistrict() {
        return this.district;
    }
    
    public void setDistrict(District district) {
        this.district = district;
    }
    public Taluka getTaluka() {
        return this.taluka;
    }
    
    public void setTaluka(Taluka taluka) {
        this.taluka = taluka;
    }
    public int getSeatNo() {
        return this.seatNo;
    }
    
    public void setSeatNo(int seatNo) {
        this.seatNo = seatNo;
    }
    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    public String getFathersName() {
        return this.fathersName;
    }
    
    public void setFathersName(String fathersName) {
        this.fathersName = fathersName;
    }
    public String getSurname() {
        return this.surname;
    }
    
    public void setSurname(String surname) {
        this.surname = surname;
    }
    public String getGender() {
        return this.gender;
    }
    
    public void setGender(String gender) {
        this.gender = gender;
    }
    public String getArea() {
        return this.area;
    }
    
    public void setArea(String area) {
        this.area = area;
    }
//    public List<CredentialDetails> getCredentialDetails() {
//        return this.credentialDetails;
//    }
//    
//    public void setCredentialDetails(List<CredentialDetails> credentialDetails) {
//        this.credentialDetails = credentialDetails;
//    }
    public CredentialDetails getSsc() {
        return this.ssc;
    }
    
    public void setSsc(CredentialDetails ssc) {
        this.ssc = ssc;
    }
    public CredentialDetails getHsc() {
        return this.hsc;
    }
    
    public void setHsc(CredentialDetails hsc) {
        this.hsc = hsc;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setCnic(String cnic) {
        this.cnic = cnic;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public void setProgram(String program) {
        this.program = program;
    }

    public void setRollNo(String rollNo) {
        this.rollNo = rollNo;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }
    

    public void setValidUpto(String validUpto) {
        this.validUpto = validUpto;
    }

    public void setYear(int year) {
        this.year = year;
    }
    
    public List<String> getCandidateProgramOfStudies() {
        return this.candidateProgramOfStudies;
    }
    
    public void setCandidateProgramOfStudies(List<String> candidateProgramOfStudies) {
        this.candidateProgramOfStudies = candidateProgramOfStudies;
    }
    public List<String> getAppliedCategories() {
        return this.appliedCategories;
    }
    
    public void setAppliedCategories(List<String> appliedCategories) {
        this.appliedCategories = appliedCategories;
    }
    public List<String> getAppliedCampuses() {
        return this.appliedCampuses;
    }
    
    public void setAppliedCampuses(List<String> appliedCampuses) {
        this.appliedCampuses = appliedCampuses;
    }

    public String getAddress() {
        return address;
    }

    public String getCnic() {
        return cnic;
    }

    public Image getImage() {
        return image;
    }

    public String getProgram() {
        return program;
    }

    public String getRollNo() {
        return rollNo;
    }

    public String getDeptName() {
        return deptName;
    }
    
    public String getValidUpto() {
        return validUpto;
    }

    public int getYear() {
        return year;
    }
    
    public void loadRollNo(String code, int rollNo) {
        this.rollNo = RollNoFormatter.format(year, code, rollNo);
    }
    public void loadProgram(boolean isBachalor, boolean isMorning, String program, String selection, String partName, int rollNo) {
        this.program = admission.utils.Utility.programFormat(isBachalor, isMorning, program, selection, partName);
    }
}
