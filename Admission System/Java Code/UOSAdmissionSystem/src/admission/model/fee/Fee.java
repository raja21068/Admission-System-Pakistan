package admission.model.fee;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author Yougeshwar
 */
@Entity
@Table(name = "yog_fee")
public class Fee implements Serializable, Cloneable {

//    private static final Long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    
    @Column(name = "ID")
    private Integer id;
    
    @Column(name = "ADMISSION_YEAR_ID")
    private int admissionYearId;
    
    @Column(name = "AMOUNT")
    private Long amount;
    
    @Column(name = "REMARKS")
    private String remarks;
    
    @JoinColumn(name = "FEE_CATEGORY_ID", referencedColumnName = "ID")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private FeeCategory feeCategory;
    
    @JoinColumn(name = "FEE_MODEL_ID", referencedColumnName = "ID")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private FeeModel feeModel;

    public Fee() {
    }

    public Fee(Integer id) {
        this.id = id;
    }

    public Fee(Integer id, int admissionYearId, Long amount) {
        this.id = id;
        this.admissionYearId = admissionYearId;
        this.amount = amount;
    }

    public Fee(int admissionYearId, Long amount, String remarks, FeeCategory feeCategory, FeeModel feeModel) {
        this.admissionYearId = admissionYearId;
        this.amount = amount;
        this.remarks = remarks;
        this.feeCategory = feeCategory;
        this.feeModel = feeModel;
    }
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getAdmissionYearId() {
        return admissionYearId;
    }

    public void setAdmissionYearId(int admissionYearId) {
        this.admissionYearId = admissionYearId;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public FeeCategory getFeeCategory() {
        return feeCategory;
    }

    public void setFeeCategory(FeeCategory feeCategory) {
        this.feeCategory = feeCategory;
    }

    public FeeModel getFeeModel() {
        return feeModel;
    }

    public void setFeeModel(FeeModel feeModel) {
        this.feeModel = feeModel;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
    
}
