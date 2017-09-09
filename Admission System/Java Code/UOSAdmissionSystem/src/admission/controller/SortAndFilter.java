package admission.controller;

import admission.controller.beans.Category;
import admission.controller.beans.ProgramOfStudy;
import admission.controller.beans.Candidate;
import admission.model.CposGroup;
import java.util.List;
import admission.utils.IConstant;

/**
 *
 * @author Raja Kumar & Jay Kumar
 */
public class SortAndFilter {
    
//    public static int count(List<Category> list){
//        int count = 0;
//        for (int i = 0; i < list.size(); i++) {
//            Category category = list.get(i);
//            count += category.getCandidates().size();
//        }
//        
//        return count;
//    }
    
    public static List<Category> removeRepeatation(List<Category> list){
        for (int i = 0; i < list.size(); i++) {
            Category category = list.get(i);
            List<Candidate> candidates = category.getCandidates();
            
            for (int j = 0; j < candidates.size(); j++) {
                Candidate candidate = candidates.get(j);
                
                for (int i1 = i + 1; i1 < list.size(); i1++) {
                    Category category2 = list.get(i1);
                    List<Candidate> candidates2 = category2.getCandidates();
                    
                    for (int j2 = 0; j2 < candidates2.size(); j2++) {
                        Candidate candidate2 = candidates2.get(j2);
                        
                        if(candidate.getCandidateId() == candidate2.getCandidateId()){
                            category2.removeFromList(candidate2);
                           break;
                        }
                    }
                }
            }
        }
        return list;
    }
    
    /**
     * This method remove the repetition in POS
     * @param pos
     * @return the filtered POS
     */
//    public static ProgramOfStudy filterCandidateInPos(ProgramOfStudy pos){
//        List<Category> list = pos.getCategories();
//            for (int j = 0; j < list.size(); j++) {
//                Category category = list.get(j);
//                List<Candidate> candidates = category.getCandidates();
//                
//                for (int k = 0; k < candidates.size(); k++) {
//                    Candidate candidate = candidates.get(k);
//         
//                    /**
//                     * Repetition start
//                     */
////                      
//                        for (int j1 = 0; j1 < list.size(); j1++) {
//                            Category cat1 = list.get(j1);
//                            List<Candidate> cn1 = cat1.getCandidates();
//                                    
//                            for (int l = 0; l < cn1.size(); l++) {
//                                Candidate candidate1 = cn1.get(l);
//                                
//                                /**
//                                 * Comparison of candidates to remove repetition
//                                 */
//                                if(candidate1.getCandidateId() == candidate.getCandidateId()){
//                                    cat1.removeFromList(candidate1);
//                                    break;
//                                }
//                            }
//                        }
//                }
//            }
////      
//        return pos;
//    }
    
    /**
     * This method remove the repeatation of candidates in same campus category by program of studies
     * @param listPos list of program of studies
     * @return the list of filtered pos
     */
    public static List<ProgramOfStudy> filterCandidateByPos(List<ProgramOfStudy> listPos){
        for (int y = 0; y < listPos.size(); y++) {
            ProgramOfStudy pos = listPos.get(y);
            List<Category> list = pos.getCategories();
            
            for (int j = 0; j < list.size(); j++) {
                Category category = list.get(j);
                List<Candidate> candidates = category.getCandidates();
                
                for (int k = 0; k < candidates.size(); k++) {
                    Candidate candidate = candidates.get(k);
                    
                    // Repeatation of POS for filtering
                    for (int y1 = y + 1; y1 < listPos.size(); y1++) {
                        ProgramOfStudy pos1 = listPos.get(y1);
                        List<Category> list1 = pos1.getCategories();
                            
                        nextPos:
                        for (int j1 = 0; j1 < list1.size(); j1++) {
                            Category cat1 = list1.get(j1);
                                        
                            if(category.getCampusCategory().getCampusCategoryId() != cat1.getCampusCategory().getCampusCategoryId())
                                continue;
                                    
                            List<Candidate> cn1 = cat1.getCandidates();
                            for (int l = 0; l < cn1.size(); l++) {
                                Candidate candidate1 = cn1.get(l);
                                /**
                                 * Compare the candidate and campus category if both match then compare there choice no and remove one of them on the basis of choice priority
                                 */ 
                                 if(candidate1.getSeatNo() == candidate.getSeatNo()){
//                                    if(candidate.getPercentage() > candidate1.getPercentage()){
//                                        cat1.removeFromList(candidate1);
//                                    }else if(candidate.getPercentage() < candidate1.getPercentage()){
//                                        category.removeFromList(candidate);
//                                    }
                                    if(candidate.getChoiceNo() < candidate1.getChoiceNo()){
                                        cat1.removeFromList(candidate1);
                                    }else if(candidate.getChoiceNo() > candidate1.getChoiceNo()){
                                        category.removeFromList(candidate);
                                    }
                                    break nextPos;
                                 }
                            }
                        }
                    }/** End of POS repetition loop */
                }
            }
        }
        return listPos;
    }

//    public static int removeRepeatationByPOS(ProgramOfStudy posM, Category cat, List<ProgramOfStudy> listPos, int start, int end){
//        int index = -1; boolean b = true;
//        List<Candidate> candidates = cat.getCandidates();
//        if (candidates.isEmpty()) return index;
//        if (end >= candidates.size()) {
//            end = candidates.size();
//        }
//        if (Constant.CategoryCodes.SELF_FINANCE_QUOTA == cat.getCampusCategory().getCategoryCode()) return index;
//        for (int i = start; i < end; i++) {
//            Candidate candidate = candidates.get(i);
//            
//            for (int y = 0; y < listPos.size(); y++) {
//                ProgramOfStudy pos = listPos.get(y);
//                if (posM.equals(pos)) continue;
//                
//                List<Category> list = pos.getCategories();
//                
//                for (int j = 0; j < list.size(); j++) {
//                    Category category = list.get(j);
//                    List<Candidate> candidates1 = category.getCandidates();
//
//                    for (int k = 0; k < candidates1.size(); k++) {
//                        Candidate candidate1 = candidates1.get(k);
//                        if (candidate.getSeatNo().equals(candidate1.getSeatNo())) {
//                            if (candidate1.getChoiceNo() >= candidate.getChoiceNo() && Constant.CategoryCodes.SELF_FINANCE_QUOTA != cat.getCampusCategory().getCategoryCode()) {
//                                candidates1.remove(candidate1);
//                                if(b) { b = !b;
//                                    index = y;
//                                }
//                                break;
//                            }
//                        }
//                    }
//                }
//            }
//        }
//        return index;
//    }
}
