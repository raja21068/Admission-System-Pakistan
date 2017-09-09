package admission.model.fee;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import admission.enums.FeeOfEnum;

/**
 *
 * @author Yougeshwar
 */
@Entity
@Table(name = "yog_additional_fee")
public class AdditionalFee implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    
    @Column(name = "ID")
    private Integer id;
    
    @Column(name = "ADMISSION_YEAR_ID")
    private Integer admissionYearId;
    
    @Column(name = "FEE_OF")
    @Enumerated
    private FeeOfEnum feeOf;
    
    @Column(name = "FEE")
    private Long fee;
    
    @Column(name = "REMARKS")
    private String remarks;

    public AdditionalFee() {
    }

    public AdditionalFee(Integer id) {
        this.id = id;
    }

    public AdditionalFee(Integer id, Integer admissionYearId) {
        this.id = id;
        this.admissionYearId = admissionYearId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAdmissionYearId() {
        return admissionYearId;
    }

    public void setAdmissionYearId(Integer admissionYearId) {
        this.admissionYearId = admissionYearId;
    }

    public FeeOfEnum getFeeOf() {
        return feeOf;
    }

    public void setFeeOf(FeeOfEnum feeOf) {
        this.feeOf = feeOf;
    }

    public Long getFee() {
        return fee;
    }

    public void setFee(Long fee) {
        this.fee = fee;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    @Override
    public String toString() {
        return feeOf.getTitle();
    }
}
