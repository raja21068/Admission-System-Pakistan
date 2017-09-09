/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package admission.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author Jay Kumarr
 */
@Entity
@Table(name = "bank_branch"
)
public class BankBranch {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "bank_branch_id", unique = true, nullable = false)
    private Integer bankBranchId;
    
    @Column(name = "name")
    private String name;

    public Integer getBankBranchId() {
        return bankBranchId;
    }

    public void setBankBranchId(Integer bankBranchId) {
        this.bankBranchId = bankBranchId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name; //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
}
