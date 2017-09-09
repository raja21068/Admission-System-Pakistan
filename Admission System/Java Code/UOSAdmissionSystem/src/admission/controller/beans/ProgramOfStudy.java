package admission.controller.beans;

import admission.model.CposGroup;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Raja Kumar & Jay Kumar
 */
public class ProgramOfStudy {
    private CposGroup cposg;
    private List<Category> categories;

    public ProgramOfStudy() {}

    public ProgramOfStudy(CposGroup cposg, List<Category> categories) {
        this.cposg = cposg;
        this.categories = categories;
    }

    public ProgramOfStudy(CposGroup cposg) {
        this.cposg = cposg;
        this.categories = new ArrayList<>();
    }

    public void setCposg(CposGroup cposg) {
        this.cposg = cposg;
    }

    public CposGroup getCposg() {
        return cposg;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public List<Category> getCategories() {
        return categories;
    }
    
    public void addToList(Category category){
       this.categories.add(category);
    }
    public Category getFromList(int index){
        return this.categories.get(index);
    }

    @Override
    public String toString() {
        return this.cposg.toString();
    }
    
}
