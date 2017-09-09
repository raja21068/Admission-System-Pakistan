package admission.reports.beans;

/**
 *
 * @author Yougeshwar
 */
public class DetailsBean {
    private int SNo;
    private int seatNo;
    private String gender;
    private String name;
    private String fathersName;
    private String surname;
    private String district;
    private String area;
    private float percentage;
    private String objection;
    private String objectionRemarks;
    private String category;
    private String discipline;
    private int choiceNo;

    public DetailsBean() {
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getArea() {
        return area;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCategory() {
        return category;
    }

    public void setDiscipline(String discipline) {
        this.discipline = discipline;
    }

    public String getDiscipline() {
        return discipline;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getDistrict() {
        return district;
    }

    public void setFathersName(String fathersName) {
        this.fathersName = fathersName;
    }

    public String getFathersName() {
        return fathersName;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getGender() {
        return gender;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setObjection(String objection) {
        this.objection = objection;
    }

    public String getObjection() {
        return objection;
    }

    public void setObjectionRemarks(String objectionRemarks) {
        this.objectionRemarks = objectionRemarks;
    }

    public String getObjectionRemarks() {
        return objectionRemarks;
    }

    public void setPercentage(float percentage) {
        this.percentage = percentage;
    }

    public float getPercentage() {
        return percentage;
    }

    public void setSeatNo(int seatNo) {
        this.seatNo = seatNo;
    }

    public int getSeatNo() {
        return seatNo;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getSurname() {
        return surname;
    }

    public void setSNo(int SNo) {
        this.SNo = SNo;
    }

    public int getSNo() {
        return SNo;
    }

    public void setChoiceNo(int choiceNo) {
        this.choiceNo = choiceNo;
    }

    public int getChoiceNo() {
        return choiceNo;
    }
    
    
}
