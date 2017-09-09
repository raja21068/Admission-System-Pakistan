/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package admission.reports.beans;

/**
 *
 * @author Server
 */
public class CategoryDistrictJRBean {
    private int vacantUrban;
    private int vacantRural;
    private int vacantOther;
    private int consumedUrban;
    private int consumedRural;
    private int consumedOther;

    public int getUrbanTotal() {
        return vacantUrban;
    }

    public void setUrbanTotal(int urbanTotal) {
        this.vacantUrban = urbanTotal;
    }

    public int getRuralTotal() {
        return vacantRural;
    }

    public void setRuralTotal(int ruralTotal) {
        this.vacantRural = ruralTotal;
    }

    public int getOtherTotal() {
        return vacantOther;
    }

    public void setOtherTotal(int otherTotal) {
        this.vacantOther = otherTotal;
    }

    public int getUrbanConsumed() {
        return consumedUrban;
    }

    public void setUrbanConsumed(int urbanConsumed) {
        this.consumedUrban = urbanConsumed;
    }

    public int getRuralConsumed() {
        return consumedRural;
    }

    public void setRuralConsumed(int ruralConsumed) {
        this.consumedRural = ruralConsumed;
    }

    public int getOtherConsumed() {
        return consumedOther;
    }

    public void setOtherConsumed(int otherConsumed) {
        this.consumedOther = otherConsumed;
    }
    
    
}
