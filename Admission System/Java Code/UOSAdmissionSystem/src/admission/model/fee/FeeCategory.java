package admission.model.fee;

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
@Table(name = "yog_fee_category")
public class FeeCategory implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    
    @Column(name = "ID")
    private Integer id;
    
    @Column(name = "SHIFT")
    @Enumerated
    private ShiftEnum shift;
    
    @Column(name = "NAME")
    private String name;
    
    @Column(name = "PROGRAM_TYPE")
    @Enumerated
    private ProgramTypeEnum programType;
    
    @Column(name = "CATEGORY_TYPE")
    @Enumerated
    private FeeCategoryTypeEnum categoryType;
    
    @Column(name = "CODE")
    private int code;
    
    @Column(name = "DISPLAY_ORDER")
    private int displayOrder;
    
    @Column(name = "REMARKS")
    private String remarks;

    public FeeCategory() {
    }

    public FeeCategory(Integer id) {
        this.id = id;
    }

    public FeeCategory(Integer id, ShiftEnum shift, String name, ProgramTypeEnum programType, FeeCategoryTypeEnum categoryType, int code, int displayOrder) {
        this.id = id;
        this.shift = shift;
        this.name = name;
        this.programType = programType;
        this.categoryType = categoryType;
        this.code = code;
        this.displayOrder = displayOrder;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ShiftEnum getShift() {
        return shift;
    }

    public void setShift(ShiftEnum shift) {
        this.shift = shift;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ProgramTypeEnum getProgramType() {
        return programType;
    }

    public void setProgramType(ProgramTypeEnum programType) {
        this.programType = programType;
    }

    public FeeCategoryTypeEnum getCategoryType() {
        return categoryType;
    }

    public void setCategoryType(FeeCategoryTypeEnum categoryType) {
        this.categoryType = categoryType;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public int getDisplayOrder() {
        return displayOrder;
    }

    public void setDisplayOrder(int displayOrder) {
        this.displayOrder = displayOrder;
    }

    @Override
    public String toString() {
        return name;
    }
}
