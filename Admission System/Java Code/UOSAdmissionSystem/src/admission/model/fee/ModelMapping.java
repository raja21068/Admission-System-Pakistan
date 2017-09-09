package admission.model.fee;

import admission.enums.IBasicDetail;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 *
 * @author Yougeshwar
 */
@Entity
@Table(name = "yog_model_mapping")
public class ModelMapping implements Serializable {
    
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    
    @Column(name = "ID")
    private Integer id;
    
    @Column(name = "MODEL_NAME")
    private String modelName;
    
    @Column(name = "MODEL_ID")
    private Integer modelId;
    
    @Column(name = "REMARKS")
    private String remarks;
    
    @JoinColumn(name = "FEE_MODEL_ID", referencedColumnName = "ID")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private FeeModel feeModel;
    
    public ModelMapping() {
    }
    
    public ModelMapping(Integer id) {
        this.id = id;
    }
    
    public Integer getId() {
        return id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    
    public String getModelName() {
        return modelName;
    }
    
    public void setModelName(String modelName) {
        this.modelName = modelName;
    }
    
    public Integer getModelId() {
        return modelId;
    }
    
    public void setModelId(Integer modelId) {
        this.modelId = modelId;
    }
    
    public String getRemarks() {
        return remarks;
    }
    
    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
    
    public FeeModel getFeeModel() {
        return feeModel;
    }
    
    public void setFeeModel(FeeModel feeModel) {
        this.feeModel = feeModel;
    }
    
    @Transient
    private Object obj;
    
    @Override
    public String toString() {
        if (obj == null) {
            try {
                Class modelClass = Class.forName(modelName);
                if (modelClass.isEnum()) {
                    Object[] enumConstants = modelClass.getEnumConstants();
                    for (Object ob : enumConstants) {
                        Enum e = (Enum) ob;
                        if(e.ordinal() == modelId) {
                            this.obj = ob;
                            break;
                        }
                    }
                } else {
                    obj = admission.controller.DatabaseManager.getSingleRecord(modelClass, modelId).toString();
                }
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(ModelMapping.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return obj.toString();
    }
}
