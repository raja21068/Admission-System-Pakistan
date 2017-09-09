package admission.reports.beans;

/**
 *
 * @author Yougeshwar
 */
public class CandidateChallanDetailJRBean {
    private int year;
    private int seatNo;
    private int challanNo;
    private String name;
    private String fathersName;
    private String discipline;
    private String totalAmount;
    private String code;
    private String validDate;
    private String paidDate;
    private String feeList;

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getSeatNo() {
        return seatNo;
    }

    public void setSeatNo(int seatNo) {
        this.seatNo = seatNo;
    }

    public int getChallanNo() {
        return challanNo;
    }

    public void setChallanNo(int challanNo) {
        this.challanNo = challanNo;
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

    public String getDiscipline() {
        return discipline;
    }

    public void setDiscipline(String discipline) {
        this.discipline = discipline;
    }

    public String getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(String totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getPaidDate() {
        return paidDate;
    }

    public void setPaidDate(String paidDate) {
        this.paidDate = paidDate;
    }
    
    public String getValidDate() {
        return validDate;
    }

    public void setValidDate(String validDate) {
        this.validDate = validDate;
    }

    public String getFeeList() {
        return feeList;
    }

    public void setFeeList(String feeList) {
        this.feeList = feeList;
    }

}
