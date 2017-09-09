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
public class SelectionCategoryBean {

    private String category;
    private List<SelectionCandidateBean> candidates;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public List<SelectionCandidateBean> getCandidates() {
        return candidates;
    }

    public void setCandidates(List<SelectionCandidateBean> candidates) {
        this.candidates = candidates;
    }
    
    
    
}
