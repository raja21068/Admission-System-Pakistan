package admission.model;
// Generated Jun 30, 2014 5:25:15 PM by Hibernate Tools 3.6.0

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import admission.utils.IConstant;

/**
 * PartRegistry generated by hbm2java
 */
@Entity
@Table(name = "part_registry"
)
public class PartRegistry implements java.io.Serializable {

    private Integer partRegistryId;
    private Accounts accounts;
    private Part part;
    private AdmissionListDetails admissionListDetails;
    private Integer challanNo;
    private Integer amount;
    private Date challanDate;
    private Integer type;
    private String remarks;

    public PartRegistry() {
    }

    public PartRegistry(Accounts accounts, Part part, AdmissionListDetails admissionListDetails) {
        this.accounts = accounts;
        this.part = part;
        this.admissionListDetails = admissionListDetails;
    }

    public PartRegistry(Accounts accounts, Part part, AdmissionListDetails admissionListDetails, Integer challanNo, Integer amount, Date challanDate, Integer type, String remarks) {
        this.accounts = accounts;
        this.part = part;
        this.admissionListDetails = admissionListDetails;
        this.challanNo = challanNo;
        this.amount = amount;
        this.challanDate = challanDate;
        this.type = type;
        this.remarks = remarks;
    }

    @Id
    @GeneratedValue(strategy = IDENTITY)

    @Column(name = "part_registry_id", unique = true, nullable = false)
    public Integer getPartRegistryId() {
        return this.partRegistryId;
    }

    public void setPartRegistryId(Integer partRegistryId) {
        this.partRegistryId = partRegistryId;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id", nullable = false)
    public Accounts getAccounts() {
        return this.accounts;
    }

    public void setAccounts(Accounts accounts) {
        this.accounts = accounts;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "part_id", nullable = false)
    public Part getPart() {
        return this.part;
    }

    public void setPart(Part part) {
        this.part = part;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "admission_list_details_id", nullable = false)
    public AdmissionListDetails getAdmissionListDetails() {
        return this.admissionListDetails;
    }

    public void setAdmissionListDetails(AdmissionListDetails admissionListDetails) {
        this.admissionListDetails = admissionListDetails;
    }

    @Column(name = "challan_no")
    public Integer getChallanNo() {
        return this.challanNo;
    }

    public void setChallanNo(Integer challanNo) {
        this.challanNo = challanNo;
    }

    @Column(name = "amount")
    public Integer getAmount() {
        return this.amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "challan_date", length = 10)
    public Date getChallanDate() {
        return this.challanDate;
    }

    public void setChallanDate(Date challanDate) {
        this.challanDate = challanDate;
    }

    @Column(name = "type")
    public Integer getType() {
        return this.type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    @Column(name = "remarks")
    public String getRemarks() {
        return this.remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    @Override
    public String toString() {
        return getType() == IConstant.Challan.ADMISSION ? "Admission" : getType() == IConstant.Challan.RETAIN ? "Retain" : "Refund";//part.toString() + " (" + DateFormatter.getDateToString(challanDate) + ")";
    }

}
