package admission.controller.algorithm;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.List;

/**
 *
 * @author Yougeshwar
 */
public class CategoryAlg {
    private int campusCategoryId;
    private int totalSeats;
    private int consumedSeats=0;
    private boolean quotaOriented;
    /**
     * DistrictId, [area, seats] e.g: ((0urban : seats), (1rural : seats))
     */
    Map<Integer, int[]> jurisMap = new LinkedHashMap<>();
                                 // MR MU FR FU
    private int[][] seats = new int[][]{
       //R  U
        {3, 2}, // M 
        {3, 2}};// F
    
    private List<CandidateAlg> candidatesList = new ArrayList<>();

    public CategoryAlg() {
        candidatesList = new ArrayList<>();
    }

    public int[][] getSeats() {
        return seats;
    }
    
    public boolean isSeatsAvailable() {
        if(totalSeats<=0){return false;}
//        return getCandidatesSize() < totalSeats;
        return consumedSeats < totalSeats;
    }
    
    public int getCampusCategoryId() {
        return campusCategoryId;
    }

    public void setCampusCategoryId(int campusCategoryId) {
        this.campusCategoryId = campusCategoryId;
    }

    
    public List<CandidateAlg> getCandidatesList() {
        return candidatesList;
    }

    public void setCandidatesList(List<CandidateAlg> candidatesList) {
        this.candidatesList = candidatesList;
    }

    public Map<Integer, int[]> getJurisMap() {
        return jurisMap;
    }

    public void setJurisMap(Map<Integer, int[]> jurisMap) {
        System.out.println("Jurisdiction Map: "+jurisMap +" " + (jurisMap!= null ? jurisMap.size():""));
        this.jurisMap = jurisMap;
    }

    public int getTotalSeats() {
        return totalSeats;
    }

    public void setTotalSeats(int totalSeats) {
        this.totalSeats = totalSeats;
    }

    public void setQuotaOriented(boolean quotaOriented) {
        this.quotaOriented = quotaOriented;
    }

    public boolean isQuotaOriented() {
        return quotaOriented;
    }
    
    public void addCandidate(CandidateAlg c) {
        candidatesList.add(c);
        setConsumedSeats(consumedSeats+1);
    }
    
    public boolean removeCandidate(CandidateAlg c) {
        return candidatesList.remove(c);
    }
    
    public int getCandidatesSize() {
        return candidatesList.size();
    }

    public int getConsumedSeats() {
        return consumedSeats;
    }

    public void setConsumedSeats(int consumedSeats) {
        assert consumedSeats<0 : "Consumed Seats is lesser than zero"; 
        if(consumedSeats<0){
            System.out.println("Consumed Seats is lesser than zero");
        }
        this.consumedSeats = consumedSeats;
    }

    public void setSeats(int[][] seats) {
        this.seats = seats;
    }
   
}
