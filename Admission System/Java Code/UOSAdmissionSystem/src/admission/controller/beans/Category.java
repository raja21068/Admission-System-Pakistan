package admission.controller.beans;

import admission.model.CampusCategory;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Raja Kumar & Jay Kumar
 */

public class Category {
    private CampusCategory campusCategory;
    private List<Candidate> candidates = new ArrayList<>();
    private boolean isJurisdication;
    private int totalSeats;
    private int selectedSeats;
    private int status;
    private HashMap<Integer, Integer[]> map;
    
    public Category() {}

    public Category(CampusCategory campusCategory) {
        this.campusCategory = campusCategory;
    }

    public CampusCategory getCampusCategory() {
        return campusCategory;
    }

    public void setCampusCategory(CampusCategory campusCategory) {
        this.campusCategory = campusCategory;
    }
    
    public void setCandidates(List<Candidate> candidates) {
        this.candidates = candidates;
    }

    public List<Candidate> getCandidates() {
        return candidates;
    }

    public void addToList(Candidate c){
        this.candidates.add(c);
    }
    public boolean removeFromList(Candidate c){
//        for (int i = 0; i < candidates.size(); i++) {
//            Candidate candidate = candidates.get(i);
//            if (candidate.getCandidateId() == c.getCandidateId())
                return this.candidates.remove(c);
//        }
    }

    public boolean isIsJurisdication() {
        return isJurisdication;
    }
    
    
//    public Candidate removeFromListIndex(int i){
//        return this.candidates.remove(i);
//    }

    public void setIsJurisdication(boolean isJurisdication) {
        this.isJurisdication = isJurisdication;
    }

    public void setTotalSeats(int totalSeats) {
        this.totalSeats = totalSeats;
    }

    public int getTotalSeats() {
        return totalSeats;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getStatus() {
        return status;
    }

    public void setSelectedSeats(int selectedSeats) {
        this.selectedSeats = selectedSeats;
    }

    public int getSelectedSeats() {
        return selectedSeats;
    }

    public void setMap(HashMap<Integer, Integer[]> map) {
        this.map = map;
    }

    public HashMap<Integer, Integer[]> getMap() {
        return map;
    }
    
}
