package admission.reports.beans;

/**
 *
 * @author Yougeshwar
 */
public class CredentialDetailCheckerJRBean {
    private String detailOf;
    private float oMrks;
    private int year;
    private int tMrks;
    private String grp;
    private String bd;
    private String seatNo;

    public String getDetailOf() {
        return detailOf;
    }

    public void setDetailOf(String detailOf) {
        this.detailOf = detailOf;
    }

    public float getoMrks() {
        return oMrks;
    }

    public void setoMrks(float oMrks) {
        this.oMrks = oMrks;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int gettMrks() {
        return tMrks;
    }

    public void settMrks(int tMrks) {
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
    
}
