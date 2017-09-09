/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package admission.reports.beans;

import java.util.HashMap;
import admission.utils.RollNoFormatter;
import admission.utils.TextFormatter;

/**
 *
 * @author Yougeshwar
 */
public class BonafideCertificateJRBean {
    private String name;
    private String fathersName;
    private String rollNo;
    private String department;
    private String campus;
    private String location;
    private String program;
    private String respect;
    private int year;

    public BonafideCertificateJRBean(HashMap<String, String> data, int year, boolean isBachalor) {
        this.year = year;
        this.name = (data.get("gender").equals("M") ? "Mr. " : "Ms. ") + TextFormatter.capitalizeEachWord(data.get("name"));
        this.fathersName = (data.get("gender").equals("M") ? "S/o " : "D/o ") + TextFormatter.capitalizeEachWord(data.get("fname"));
        this.rollNo = RollNoFormatter.format(year, data.get("code"), Integer.parseInt(data.get("roll_no")));
        this.department = TextFormatter.capitalizeEachWord(data.get("department"));
        this.campus = TextFormatter.capitalizeEachWord(data.get("campus"));
        this.location = TextFormatter.capitalizeEachWord(data.get("campus_location"));
        this.program = admission.utils.Utility.programFormat(isBachalor, Boolean.parseBoolean(data.get("is_morning")), data.get("program"), data.get("selection"), data.get("part"));
        this.respect = data.get("gender").equals("M") ? "He" : "She";
        
//        this.department = department.replaceAll("&","&amp;amp;");
    }
    
    public BonafideCertificateJRBean(String name, String fathersName, String rollNo, String department, String campus, String location, String program, int year) {
        this.name = name;
        this.fathersName = fathersName;
        this.rollNo = rollNo;
        this.department = department;
        this.campus = campus;
        this.location = location;
        this.program = program;
        this.year = year;
    }
    
    public void setCampus(String campus) {
        this.campus = campus;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public void setFathersName(String fathersName) {
        this.fathersName = fathersName;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setProgram(String program) {
        this.program = program;
    }

    public void setRollNo(String rollNo) {
        this.rollNo = rollNo;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setRespect(String respect) {
        this.respect = respect;
    }

    public String getCampus() {
        return campus;
    }

    public String getDepartment() {
        return department;
    }

    public String getFathersName() {
        return fathersName;
    }

    public String getLocation() {
        return location;
    }

    public String getName() {
        return name;
    }

    public String getProgram() {
        return program;
    }

    public String getRollNo() {
        return rollNo;
    }

    public int getYear() {
        return year;
    }

    public String getRespect() {
        return respect;
    }
    
    
}
