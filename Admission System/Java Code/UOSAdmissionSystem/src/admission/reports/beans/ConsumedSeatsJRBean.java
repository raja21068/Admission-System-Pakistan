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
public class ConsumedSeatsJRBean {

    private String discipline;
    private List<DisciplineCategoryJRBean> categories;

    public String getDiscipline() {
        return discipline;
    }

    public void setDiscipline(String discipline) {
        this.discipline = discipline;
    }

    public List<DisciplineCategoryJRBean> getCategories() {
        return categories;
    }

    public void setCategories(List<DisciplineCategoryJRBean> categories) {
        this.categories = categories;
    }
    
    
}
