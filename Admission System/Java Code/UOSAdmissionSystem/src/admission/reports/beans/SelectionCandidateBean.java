/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package admission.reports.beans;

import admission.model.District;
import admission.model.Taluka;

/**
 *
 * @author Server
 */
public class SelectionCandidateBean {

    private int seatNo;
    private String gender;
    private String name;
    private String fathersName;
    private String surname;
    private String area;
    private String district;
    
    private int matricObtained;
    private int interObtained;
    private int graduationObtained;
    
    private int matricYear;
    private int interYear;
    private int graduationYear;
    
    private float matricPerc;
    private float interPerc;
    private float graduationPerc;

    private int score;
    private float scorePerc;

    private String matricGroup;
    private String interGroup;
    private String graduationGroup;
    
    
    private int deductionMarks;
    private int choiceNo;
    
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

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public int getMatricObtained() {
        return matricObtained;
    }

    public void setMatricObtained(int matricObtained) {
        this.matricObtained = matricObtained;
    }

    public int getInterObtained() {
        return interObtained;
    }

    public void setInterObtained(int interObtained) {
        this.interObtained = interObtained;
    }

    public int getGraduationObtained() {
        return graduationObtained;
    }

    public void setGraduationObtained(int graduationObtained) {
        this.graduationObtained = graduationObtained;
    }

    public int getMatricYear() {
        return matricYear;
    }

    public void setMatricYear(int matricYear) {
        this.matricYear = matricYear;
    }

    public int getInterYear() {
        return interYear;
    }

    public void setInterYear(int interYear) {
        this.interYear = interYear;
    }

    public int getGraduationYear() {
        return graduationYear;
    }

    public void setGraduationYear(int graduationYear) {
        this.graduationYear = graduationYear;
    }

    public float getMatricPerc() {
        return matricPerc;
    }

    public void setMatricPerc(float matricPerc) {
        this.matricPerc = matricPerc;
    }

    public float getInterPerc() {
        return interPerc;
    }

    public void setInterPerc(float interPerc) {
        this.interPerc = interPerc;
    }

    public float getGraduationPerc() {
        return graduationPerc;
    }

    public void setGraduationPerc(float graduationPerc) {
        this.graduationPerc = graduationPerc;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public float getScorePerc() {
        return scorePerc;
    }

    public void setScorePerc(float scorePerc) {
        this.scorePerc = scorePerc;
    }

    public String getMatricGroup() {
        return matricGroup;
    }

    public void setMatricGroup(String matricGroup) {
        this.matricGroup = matricGroup;
    }

    public String getInterGroup() {
        return interGroup;
    }

    public void setInterGroup(String interGroup) {
        this.interGroup = interGroup;
    }

    public String getGraduationGroup() {
        return graduationGroup;
    }

    public void setGraduationGroup(String graduationGroup) {
        this.graduationGroup = graduationGroup;
    }

    public int getDeductionMarks() {
        return deductionMarks;
    }

    public void setDeductionMarks(int deductionMarks) {
        this.deductionMarks = deductionMarks;
    }

    public int getChoiceNo() {
        return choiceNo;
    }

    public void setChoiceNo(int choiceNo) {
        this.choiceNo = choiceNo;
    }
    
    
    
}
