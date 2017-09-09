/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package admission.reports.beans;

import java.util.List;

/**
 *
 * @author Server
 */
public class DisciplineCategoryJRBean {

    private String categoryName;
    private boolean isQuotaOriented;
    private List<CategoryDistrictJRBean> districtSeats;
    private int vacantSeats;
    private int consumedSeats;

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public boolean isIsQuotaOriented() {
        return isQuotaOriented;
    }

    public void setIsQuotaOriented(boolean isQuotaOriented) {
        this.isQuotaOriented = isQuotaOriented;
    }

    public List<CategoryDistrictJRBean> getDistrictSeats() {
        return districtSeats;
    }

    public void setDistrictSeats(List<CategoryDistrictJRBean> districtSeats) {
        this.districtSeats = districtSeats;
    }

    public int getVacantSeats() {
        return vacantSeats;
    }

    public void setVacantSeats(int vacantSeats) {
        this.vacantSeats = vacantSeats;
    }

    public int getConsumedSeats() {
        return consumedSeats;
    }

    public void setConsumedSeats(int consumedSeats) {
        this.consumedSeats = consumedSeats;
    }
    
    
}
