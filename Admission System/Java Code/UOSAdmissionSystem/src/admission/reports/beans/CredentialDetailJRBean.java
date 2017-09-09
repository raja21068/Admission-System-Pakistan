package admission.reports.beans;

/**
 *
 * @author Yougeshwar
 */
public class CredentialDetailJRBean {
    private String detailOf;
    private Float oMrks;
    private Integer year;
    private Integer tMrks;
    private String grp;
    private String bd;
    private String seatNo;

    public String getDetailOf() {
        return detailOf;
    }

    public void setDetailOf(String detailOf) {
        this.detailOf = detailOf;
    }

    public Float getoMrks() {
        return oMrks;
    }

    public void setoMrks(Float oMrks) {
        this.oMrks = oMrks;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer gettMrks() {
        return tMrks;
    }

    public void settMrks(Integer tMrks) {
        this.tMrks = tMrks;
    }

    public String getGrp() {
        return grp;
    }

    public void setGrp(String grp) {
        this.grp = grp;
    }

    public String getBd() {
        return bd;
    }

    public void setBd(String bd) {
        this.bd = bd;
    }

    public String getSeatNo() {
        return seatNo;
    }

    public void setSeatNo(String seatNo) {
        this.seatNo = seatNo;
    }

    @Override
    public String toString() {
        return detailOf+": "+oMrks+"  "+tMrks+"  "+year+"    "+seatNo+"   "+grp+"   "+bd; //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
