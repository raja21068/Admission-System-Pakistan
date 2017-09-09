package admission.model.fee;

import admission.enums.AmountTypeEnum;
import admission.enums.FeeCategoryTypeEnum;
import admission.enums.ProgramTypeEnum;
import admission.enums.ShiftEnum;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author Yougeshwar
 */
@Entity
@Table(name = "yog_fee_model")
public class FeeModel implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    
    @Column(name = "ID")
    private Integer id;
    
    @Column(name = "PROGRAM_TYPE")
    @Enumerated
    private ProgramTypeEnum programType;
    
    @Column(name = "SHIFT")
    @Enumerated
    private ShiftEnum shift;
    
    @Column(name = "CATEGORY_TYPE")
    @Enumerated
    private FeeCategoryTypeEnum categoryType;
    
    @Column(name = "NAME")
    private String name;
    
    @Column(name = "AMOUNT_TYPE")
    @Enumerated
    private AmountTypeEnum amountType;
    
    @Column(name = "REMARKS")
    private String remarks;

    public FeeModel() {
    }

    public FeeModel(Integer id) {
        this.id = id;
    }

    public FeeModel(Integer id, ProgramTypeEnum programType, ShiftEnum shift) {
        this.id = id;
        this.programType = programType;
        this.shift = shift;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ProgramTypeEnum getProgramType() {
        return programType;
    }

    public void setProgramType(ProgramTypeEnum programType) {
        this.programType = programType;
    }

    public ShiftEnum getShift() {
        return shift;
    }

    public void setShift(ShiftEnum shift) {
        this.shift = shift;
    }

    public FeeCategoryTypeEnum getCategoryType() {
        return categoryType;
    }

    public void setCategoryType(FeeCategoryTypeEnum categoryType) {
        this.categoryType = categoryType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRemarks() {
        return remarks;
    }

    public AmountTypeEnum getAmountType() {
        return amountType;
    }

    public void setAmountType(AmountTypeEnum amountType) {
        this.amountType = amountType;
    }
    
    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    @Override
    public String toString() {
        return name;
    }
}
