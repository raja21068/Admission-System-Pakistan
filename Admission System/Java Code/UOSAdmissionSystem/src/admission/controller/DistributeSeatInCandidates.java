package admission.controller;

import admission.controller.beans.Candidate;
import admission.controller.beans.Category;
import admission.controller.beans.ProgramOfStudy;
import admission.model.AdmissionYear;
import admission.model.CampusCategory;
import admission.model.CposGroup;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import admission.utils.IConstant;
import admission.utils.Sorter;

/**
 *
 * @author Raja Kumar & Jay Kumar
 */
public class DistributeSeatInCandidates {
    /**
     * @param listOfPos is list of all programs, each program have list of categories and each category have list of candidates
     * @param ay is admission year
     * @param progressBar
     * @param label
     * @throws java.sql.SQLException
     * 
     * This method use for to distribute seats in candidates
     */
    public static void distribute(List<ProgramOfStudy> listOfPos, AdmissionYear ay, JProgressBar progressBar, JLabel label) throws SQLException {
        progressBar.setValue(0);
        progressBar.setMaximum(listOfPos.size() - 1);
        
        /**
         * Loop on list of programs
         */
        for (int i = 0; i < listOfPos.size(); i++) {
            ProgramOfStudy pos = listOfPos.get(i);
            CposGroup cposg = pos.getCposg();
            List<Category> listOfCat = pos.getCategories();
            
            /**
             * Loop on list of categories
             */
            for (int k = 0; k < listOfCat.size(); k++) {
                
                Category category = listOfCat.get(k);
                CampusCategory cc = category.getCampusCategory();
            
                /**
                 * If program is quota oriented then they have separate method to distribute seats
                 * Only GENERAL_MERIT_QUOTA and UPPER_SINDH_OUT_OF_JURISDICTION_QUOTA have district wise seat distribution
                 */
                if (cposg.isPosIsQuotaOriented()) {
                    /**
                     * If category is one from both of them then call forGMQO(...) method
                     */
                    if(cc.getCategoryCode() == IConstant.CategoryCodes.GENERAL_MERIT_QUOTA || 
                            cc.getCategoryCode() == IConstant.CategoryCodes.UPPER_SINDH_OUT_OF_JURISDICTION_QUOTA) {
                 
                        /**
                         * forGMQO(...) method use to select candidates
                         */
                        int seats = forGMQO(category, cposg, ay, label);
                        /**
                         * These two method use for remove candidate repetition
                         * 1. removeRepetition(...) method use for to remove repetition in same program
                         * 2. removeRepetitionByPOS(...) method use for to remove repetition from program wise
                         */
                        removeRepetition(pos, category, 0, seats, listOfPos);
                        removeRepetitionByPOS(pos, category, listOfPos, 0, seats, ay, label);
                        continue;
                    }
                }
                
                /**
                 * If category is COMMERCE_QUOTA then call commerce(...)
                 * else set selected candidates count via logic
                 * if total seats > size of candidates then set size of candidates else total seats
                 */
                if(cc.getCategoryCode() == IConstant.CategoryCodes.COMMERCE_QUOTA) {
                    commerce(category, label);
                } else {
                    category.setSelectedSeats(category.getTotalSeats() > category.getCandidates().size() ? category.getCandidates().size() : category.getTotalSeats());
                }
                label.setText(cc.getCategoryName());
                
                /**
                 * These two method use for remove candidate repetition
                 * 1. removeRepetition(...) method use for to remove repetition in same program
                 * 2. removeRepetitionByPOS(...) method use for to remove repetition from program wise
                 */
                removeRepetition(pos, category, 0, category.getSelectedSeats(), listOfPos);
                removeRepetitionByPOS(pos, category, listOfPos, 0, category.getSelectedSeats(), ay, label);
//                removeRepetition(category, listOfCat, 0, category.getSelectedSeats() > 0 ? category.getSelectedSeats() : category.getTotalSeats());
//                removeRepetitionByPOS(pos, category, listOfPos, 0, category.getSelectedSeats() > 0 ? category.getSelectedSeats() : category.getTotalSeats(), ay, label);
            }
        }
            //Waiting
//            for (int k = 0; k < list1.size(); k++) {
//                Category category = list1.get(k);
//                CampusCategory cc = category.getCampusCategory();
//                CposGroup cposg = pos.getCposg();
//                    
//                if (cposg.getPosIsQuotaOriented())
//                    if(cc.getCategoryCode() == Constant.CategoryCodes.GENERAL_MERIT_QUOTA || cc.getCategoryCode() == Constant.CategoryCodes.UPPER_SINDH_OUT_OF_JURISDICTION_QUOTA) {
//                        forGMQO2(list1, cc, cposg, ay, shift, admissionSessionId, label);
//                        continue;
//                    }
//                
//                DisciplineCategorySeats dcs = JDBCDatabaseManager.getDisciplineCategorySeats(cc.getCampusCategoryId(), admissionSessionId, cposg.getCposGroupId());
//                
//                int end = dcs.getNumberOfSeats() + Constant.WAITING_CANDIDATE_LIMIT;
//                if (end <= category.getCandidates().size()) {
//                    removeRepetition(category, list1, dcs.getNumberOfSeats(), end);
//                }
//            }
        
        /**
         * This code is use to remove non-selected candidates from list
         */
        for (int i = 0; i < listOfPos.size(); i++) {
            ProgramOfStudy pos = listOfPos.get(i);
            List<Category> listOfCat = pos.getCategories();
            
            for (int k = 0; k < listOfCat.size(); k++) {
                Category category = listOfCat.get(k);
                CampusCategory cc = category.getCampusCategory();
                CposGroup cposg = pos.getCposg();
            
                /**
                 * This is same as above code, but in this there is another condition for BADIN campus
                 * In BADIN campus the seats are for district not divided in urban and rural
                 */
                if (cposg.isPosIsQuotaOriented() && cc.getCampusId() != 4) {
                    if(cc.getCategoryCode() == IConstant.CategoryCodes.GENERAL_MERIT_QUOTA || 
                            cc.getCategoryCode() == IConstant.CategoryCodes.UPPER_SINDH_OUT_OF_JURISDICTION_QUOTA
                            ) {
                        /**
                         * This method use remove non-selected candidates from program, for quota oriented programs
                         */
                        forGMQO3(category);
                        continue;
                    }
                }              
                label.setText(cc.getCategoryName());
                
                List<Candidate> candidates = category.getCandidates();
                /**
                 * if total seats > size of candidates then set size of candidates else total seats
                 */
                Integer total = category.getTotalSeats() > candidates.size() ? candidates.size() : category.getTotalSeats();
//                Integer total = dcs.getNumberOfSeats() > candidates.size() ? candidates.size() : dcs.getNumberOfSeats();
//                Integer total = dcs.getNumberOfSeats() + Constant.WAITING_CANDIDATE_LIMIT > candidates.size() ? candidates.size() : dcs.getNumberOfSeats() + Constant.WAITING_CANDIDATE_LIMIT;

                /**
                 * If total is zero or in minus then clear list of candidates from the category
                 * else create sublist of selected candidates
                 */
                if(total <= 0){
                    candidates.clear();
                }else 
                    candidates = candidates.subList(0, total);
                    
                category.setCandidates(candidates);
                    
//                for (int l = 0; l < total; l++) {
//                    Candidate candidate = candidates.get(l);
//                    
//                    if(l < category.getTotalSeats()){
//                        candidate.setStatus("S");
//                    }else candidate.setStatus("W");
//                    
//                    label.setText(candidate.getName());
//                }
                
                Sorter.sortByPercentage(candidates);
            }
            
            progressBar.setValue(i);
        }
    }
    
    private static int commerce(Category category, JLabel label) {
//        int[] seats = new int[]{3, 2, 3, 2};
        /**
         * Female rural = 3
         * Female urban = 2
         * Male rural = 3
         * Male urban = 2
         * 
         * In commerce quota seats divided on gender and area
         */
        
        /**
         * In this code some hard code is written, but will be changed in dynamic function
         */
                         // F:R, F:U, M:R, M:U
        int[] seats = new int[]{1, 0, 0, 0};
        
        if(category.getCampusCategory().getCampusId() == 5)
//            seats = new int[]{1, 1, 1, 1};
            seats = new int[]{0, 0, 1, 0};
        
        List<Candidate> cnList = category.getCandidates();
        
        int position = 0;
        int count = 0;
        
        for (int i = 0; i < cnList.size(); i++) {
            Candidate candidate = cnList.get(i);
            label.setText(candidate.getName());
            
            if(candidate.getGender().equals("F")) {
                if(candidate.getArea().equals("R") && seats[0] > 0) {
                    cnList.add(position++, cnList.remove(i));
                    seats[0]--; count++;
                } else if(candidate.getArea().equals("U") && seats[1] > 0) {
                    cnList.add(position++, cnList.remove(i));
                    seats[1]--; count++;
                }
            } else {
                if(candidate.getArea().equals("R") && seats[2] > 0) {
                    cnList.add(position++, cnList.remove(i));
                    seats[2]--; count++;
                } else if(candidate.getArea().equals("U") && seats[3] > 0) {
                    cnList.add(position++, cnList.remove(i));
                    seats[3]--; count++;
                }
            }
        }
        category.setSelectedSeats(count);
        return count;
    }
    
    /**
     * This method is use for to distribute seats in quota oriented programs
     */
    private static int forGMQO(Category category, CposGroup cposg, AdmissionYear ay, JLabel label) throws SQLException{
        
        HashMap<Integer, Integer[]> map = copyMap(category.getMap());
        
        List<Candidate> cnList = category.getCandidates();
        int position = 0;
        int count = 0;
        for (int j = 0; j < cnList.size(); j++) {
            Candidate candidate = cnList.get(j);
            Integer districtId = candidate.getDistrictId();
            String area = candidate.getArea();
            Integer[] seats = map.get(districtId);
            
            if(seats != null){
                candidate.setStatus("None");
                /**
                 * If seats of urban and rural both are zero and other seat are > 0 then 
                 * else for urban and rural code
                 */
                if(seats[0] == 0 && seats[1] == 0) {
                    if (seats[2] > 0) {
                        candidate.setStatus("S");
                        cnList.add(position++, cnList.remove(j));
                        --seats[2];
                        count++;
                        /**
                         * This is for BADIN Campus
                         */
                        category.setStatus(1);
                    }
                } else if(area.equals("U") && seats[0] > 0) {
                    candidate.setStatus("S");
                    cnList.add(position++, cnList.remove(j));
                    --seats[0];
                    count++;
                } else if(area.equals("R") && seats[1] > 0) {
                    candidate.setStatus("S");
                    cnList.add(position++, cnList.remove(j));
                    --seats[1];
                    count++;
                }
            }
            label.setText(candidate.getName());
        }
        /**
         * This code base on campuses except JAMSHORO and HYDERABAD campus
         * This code means if after seat distribution also seats are remaining then those seats are opened for other districts
         */
        int c = category.getTotalSeats();
        if (category.getCampusCategory().getCampusId() != 1 && category.getCampusCategory().getCampusId() != 2) {
            count = (c > cnList.size()) ? cnList.size() : c;
        }
        if (c == 0) count = 0;
        category.setSelectedSeats(count);
//        removeRepetition(category, list, 0, category.getSelectedSeats());
                
        return category.getSelectedSeats();
    }
//    private static void forGMQO2(List<Category> list, CampusCategory cc, CposGroup cposg, AdmissionYear ay, Shift shift, Integer admissionSessionId, JLabel label) throws SQLException{
//        boolean b = cc.isCampusIsMain();//Coder.Decoder.booleanDecode(cc.getIsMain());
//        if(b){ // Main Campus
//            b = cposg.getPosIsQuotaOriented();
//            if(b){ // for quota oriented
////                for (int i = 0; i < 4; i++) {
////                    Category c = list.get(i);
////                    
////                    DisciplineCategorySeats dcs = JDBCDatabaseManager.getDisciplineCategorySeats(c.getCampusCategory().getCampusCategoryId(), admissionSessionId, cposg.getCposGroupId());
////                    int end = dcs.getNumberOfSeats() + Constant.WAITING_CANDIDATE_LIMIT;
////                    if (end <= c.getCandidates().size()) {
////                        removeRepetition(c, list, dcs.getNumberOfSeats(), end);
////                    }
////                }
//            }/*else{
//                for (int i = 0; i < 4; i++) {
//                    Category c = list.get(i);
//                    
//                    DisciplineCategorySeats dcs = JDBCDatabaseManager.getDisciplineCategorySeats(c.getCampusCategory().getCampusCategoryId(), admissionSessionId, cposg.getCposGroupId());
//                    int end = dcs.getNumberOfSeats() + Constant.WAITING_CANDIDATE_LIMIT;
//                    if (end <= c.getCandidates().size()) {
//                        removeRepetition(c, list, dcs.getNumberOfSeats(), end);
//                    }
//                }
//            }*/
//        } else {// for other campuses
//        }
//    }
    
    /**
     * This method use to remove non-selected candidates from each category of program, for quota oriented
     */
    private static void forGMQO3(Category category) throws SQLException{
        List<Candidate> candidates = category.getCandidates();
        Integer total = category.getSelectedSeats() > candidates.size() ? candidates.size() : category.getSelectedSeats();
            
//            for (int j = 0; j < total; j++) {
//                Candidate candidate = candidates.get(j);
//                
//                if (j < category.getTotalSeats())
//                    candidate.setStatus("S");
//                else candidate.setStatus("W");
//                label.setText(candidate.getName());
//            }

        if(total == 0){
            candidates.clear();
        }else 
            candidates = candidates.subList(0, total);
                
        category.setCandidates(candidates);
        Sorter.sortByPercentage(candidates);
    }
    
//    private static HashMap<Integer, Integer[]> toMap(List<DistrictSeatDistribution> dsdList, int multiply, int campusCategoryId, int cposgId) throws SQLException{
//        HashMap<Integer, Integer[]> map = new HashMap<>(dsdList.size());
//        for (int i = 0; i < dsdList.size(); i++) {
//            DistrictSeatDistribution dsd = dsdList.get(i);
//            int urban = JDBCDatabaseManager.getReservedCandidatesCount(dsd.getAdmissionYearId(), campusCategoryId, cposgId, dsd.getDistrictId(), "U");
//            int rural = JDBCDatabaseManager.getReservedCandidatesCount(dsd.getAdmissionYearId(), campusCategoryId, cposgId, dsd.getDistrictId(), "R");
//            
//            Integer arr[] = new Integer[]{dsd.getUrban() * multiply - urban, dsd.getRural() * multiply - rural, multiply * dsd.getOther() - urban - rural};
//            map.put(dsd.getDistrictId(), arr);
//        }
//        return map;
//    }
    
//    private static int count(List<DistrictSeatDistribution> dsdList, int multiply){
//        int count = 0;
//        for (int i = 0; i < dsdList.size(); i++) {
//            DistrictSeatDistribution dsd = dsdList.get(i);
//            count += dsd.getUrban() * multiply;
//            count += dsd.getRural() * multiply;
//            count += dsd.getOther() * multiply;
//        }
//        return count;
//    }
    
    /**
     * This method is use for to remove candidates repetition from each program category
     */
    private static void removeRepetitionByPOS(ProgramOfStudy posM, Category cat, List<ProgramOfStudy> listPos, int start, int end, AdmissionYear ay, JLabel label) throws SQLException{
        /**
         * @param cat is fetched from posM
         */
        
        List<Candidate> candidates = cat.getCandidates();
        if (candidates.isEmpty() || end == 0 || start >= end) return;
        
        if (end > candidates.size()) {
            end = candidates.size();
        }
        
        /**
         * This loop is for those candidates you want check for repetition
         * This loop fetch candidates from @param cat
         */
        for (int i = start; i < end; i++) {
            Candidate candidate = candidates.get(i);
            
            label:
            /**
             * This loop for lost of programs
             */
            
            for (int y = 0; y < listPos.size(); y++) {
                ProgramOfStudy pos = listPos.get(y);
                
                /**
                 * If posM and pos are both same then ignore program
                 */
                if (posM.equals(pos)) continue;
                
                List<Category> list = pos.getCategories();
                /**
                 * This loop for each category
                 */
                for (int j = 0; j < list.size(); j++) {
                    Category category = list.get(j);
                    
                    /**
                     * If cat is SELF_FINANCE_QUOTA and category is not SELF_FINANCE_QUOTA 
                     * or cat is not SELF_FINANCE_QUOTA and category is SELF_FINANCE_QUOTA then ignore
                     * 
                     * This condition code is use for policy
                     * if only anyone category is SELF_FINANCE_QUOTA the continue execution
                     * because candidate can be selected in one SELF_FINANCE_QUOTA and only one in MERIT_QUOTA
                     */
                    if ((cat.getCampusCategory().getCategoryCode() == IConstant.CategoryCodes.SELF_FINANCE_QUOTA
                            && category.getCampusCategory().getCategoryCode() != IConstant.CategoryCodes.SELF_FINANCE_QUOTA) || 
                            (cat.getCampusCategory().getCategoryCode() == IConstant.CategoryCodes.OP_SELF_FINANCE_QUOTA
                            && category.getCampusCategory().getCategoryCode() != IConstant.CategoryCodes.OP_SELF_FINANCE_QUOTA)) {
                        continue;
                    }

                    List<Candidate> candidates1 = category.getCandidates();
                    for (int k = 0; k < candidates1.size(); k++) {
                        Candidate candidate1 = candidates1.get(k);
                        
                        /**
                         * If both candidate are same
                         */
                        if (candidate.getSeatNo() == candidate1.getSeatNo()) {
                            
                            /**
                             * If both programs are opposite in shift means one in morning and second in evening
                             * and both program not same then break;
                             */
                            if(posM.getCposg().isIsMorning() != pos.getCposg().isIsMorning() && posM.getCposg().getProgramOfStudyId() != pos.getCposg().getProgramOfStudyId()) break;
                                
//                            if ((cat.getCampusCategory().getCategoryCode() == Constant.CategoryCodes.SELF_FINANCE_QUOTA)){
//                                if (candidate1.getChoiceNo() <= candidate.getChoiceNo()) {
//                                    candidates1.remove(candidate1);
//                                }
//                            } else 
                            
                            /**
                             * If already candidate selected choice is >= current selected choice then
                             * remove from previous selection and fetch place other on that seats
                             */
                            if (candidate1.getChoiceNo() >= candidate.getChoiceNo()) {
                                candidates1.remove(candidate1);
                                
                                if (pos.getCposg().isPosIsQuotaOriented() && (category.getCampusCategory().getCategoryCode() == IConstant.CategoryCodes.GENERAL_MERIT_QUOTA || 
                                        category.getCampusCategory().getCategoryCode() == IConstant.CategoryCodes.UPPER_SINDH_OUT_OF_JURISDICTION_QUOTA)) {
                                    
                                    int seats = forGMQO(category, pos.getCposg(), ay, label);
                                    removeRepetition(pos, category, 0, seats, listPos);
                                    removeRepetitionByPOS(pos, category, listPos, 0, seats, ay, label);
                                } else {
                                    if(category.getCampusCategory().getCategoryCode() == IConstant.CategoryCodes.COMMERCE_QUOTA && category.getTotalSeats() > 0) {
                                        commerce(category, label);
                                    }
                                    removeRepetition(pos, category, 0, category.getSelectedSeats() > 0 ? category.getSelectedSeats() : category.getTotalSeats(), listPos);
                                    removeRepetitionByPOS(pos, category, listPos, 0, category.getSelectedSeats() > 0 ? category.getSelectedSeats() : category.getTotalSeats(), ay, label);
                                }
                                if (end > candidates.size()) {
                                    i -= (end - candidates.size());
                                    end = candidates.size();
                                    if(i < 0) i = -1;
                                    break label;
                                }
                            }
                            break;
                        }
                    }
                }
            }
        }
    }
    /**
     * This method use to remove candidates repetition from same program f each category
     */
    private static void removeRepetition(ProgramOfStudy pos, Category cat, int start, int end, List<ProgramOfStudy> listPos) {
        List<Candidate> candidates = cat.getCandidates();
        List<Category> list = pos.getCategories();
        if (candidates.isEmpty() || start >= end) return;
        if (end >= candidates.size()) {
            end = candidates.size();
        }

        /**
         * This loop is for those candidates you want check for repetition
         * This loop fetch candidates from @param cat
         */
        for (int j = start; j < end; j++) {
            Candidate candidate = candidates.get(j);
            
            /**
             * if candidate already selected in any choice now its going to upgrade then previous seat must be released
             */
            if(candidate.getAdmissionListDetailsId() != 0 && candidate.getSelectedCampusCategoryId() != 0 && candidate.getSelectedCposgId() != 0
                    /*&& cat.getCampusCategory().getCategoryCode() == Constant.CategoryCodes.SELF_FINANCE_QUOTA*/) {
                resolveSeat(listPos, candidate, cat.getCampusCategory().getCategoryCode(), pos.getCposg().isIsMorning());
            }
            /**
             * In this code first candidate check in priority base category if candidate is selected in two categories then 
             * it will be removed from second category
             * There is a flag, before reaching both category same it will remove candidate from above category of the current status
             * When both category same then flag will be 0 then it will remove candidate from after category of the current status
             */
            label: {
                boolean b = true;
                for (int k = 0; k < list.size(); k++) {
                    Category cat2 = list.get(k);
                    
                    /**
                     * If both category is same then flag is 0
                     */
                    if(cat.equals(cat2)) {
                        b = false;
                        continue;
                    }
                    
                    List<Candidate> candidates1 = cat2.getCandidates();
                    int stop = cat2.getTotalSeats() >= candidates1.size() ? candidates1.size() : cat2.getTotalSeats();

                    if (pos.getCposg().isPosIsQuotaOriented() && (cat2.getCampusCategory().getCategoryCode() == IConstant.CategoryCodes.GENERAL_MERIT_QUOTA || 
                                        cat2.getCampusCategory().getCategoryCode() == IConstant.CategoryCodes.UPPER_SINDH_OUT_OF_JURISDICTION_QUOTA)) {
                        stop = cat2.getSelectedSeats() > 0 ? cat2.getSelectedSeats() : cat2.getTotalSeats() >= candidates1.size() ? candidates1.size() : cat2.getTotalSeats();
                    }

                    for (int i = 0; i < stop; i++) {
                        Candidate candidate1 = candidates1.get(i);
                    
                        if (candidate.getSeatNo() == candidate1.getSeatNo()) {
                            if(b) {
                                cat.removeFromList(candidate);
                                if (end >= candidates.size()) {
                                    end = candidates.size();
                                } j--;
                                break label;
                            } else {
                                cat2.removeFromList(candidate1);
                            }
                            break;
                        }
                    }
                }
            }
        }
    }
    
    /**
     * This method is use to resolve seat
     */
    private static void resolveSeat(List<ProgramOfStudy> listPos, Candidate cn, int categoryCode, boolean isMorning) {
        for (int i = 0; i < listPos.size(); i++) {
            ProgramOfStudy pos = listPos.get(i);
            if(pos.getCposg().getCposGroupId().intValue() != cn.getSelectedCposgId()) continue;
            
            List<Category> categories = pos.getCategories();
            for (int j = 0; j < categories.size(); j++) {
                Category category = categories.get(j);
                
                if(category.getCampusCategory().getCampusCategoryId().intValue() == cn.getSelectedCampusCategoryId()) {
                    if(category.getCampusCategory().getCategoryCode() != categoryCode) continue;
                    
                    if(pos.getCposg().isIsMorning() != isMorning) 
                        continue;
                    
                    if(pos.getCposg().isPosIsQuotaOriented() && (category.getCampusCategory().getCategoryCode() == IConstant.CategoryCodes.GENERAL_MERIT_QUOTA 
                            || category.getCampusCategory().getCategoryCode() == IConstant.CategoryCodes.UPPER_SINDH_OUT_OF_JURISDICTION_QUOTA)) {
                        
                        Integer[] seats = category.getMap().get(cn.getDistrictId());
                        
                        if(seats[0] == 0 && seats[1] == 0) {
                            if (seats[2] > 0) {
                                seats[2]++;
                            }
                        } else if(cn.getArea().equals("U") && seats[0] > 0) {
                            seats[0]++;
                        } else if(cn.getArea().equals("R") && seats[1] > 0) {
                            seats[1]++;
                        }
                    }
                    
                    if(pos.getCposg().getCposGroupId() == 113 && category.getCampusCategory().getCampusCategoryId() == 38) System.out.println(cn.getSeatNo());
                    category.setTotalSeats(category.getTotalSeats() + 1);
                    cn.setSelectedCampusCategoryId(0);
                    cn.setSelectedCposgId(0);
                    return;
                }
            }
        }
    }
    private static HashMap<Integer, Integer[]> copyMap(HashMap<Integer, Integer[]> map) {
        HashMap<Integer, Integer[]> copy = new HashMap<>();
        Set<Integer> keySet = map.keySet();
        for (Integer key : keySet) {
            Integer[] get = map.get(key);
            copy.put(key.intValue(), get.clone());
        }
        return copy;
    }
//    private static void backToMerit(List<Category> list) {
//        
//        for (int k = 0; k < list.size(); k++) {
//            Category cat = list.get(k);
//            if(cat.getCampusCategory().getCategoryCode() != Constant.CategoryCodes.GENERAL_MERIT_QUOTA ||
//                    cat.getCampusCategory().getCategoryCode() != Constant.CategoryCodes.UPPER_SINDH_OUT_OF_JURISDICTION_QUOTA) 
//                continue;
//            
//            if(cat.getCandidates().size() >= cat.getTotalSeats()) continue;
//            if(cat.getTotalSeats() - cat.getSelectedSeats() <= 0) continue;
//            
//            Category selfCat = getCategory(Constant.CategoryCodes.SELF_FINANCE_QUOTA, list);
//            if(cat.getCampusCategory().getCategoryCode() == Constant.CategoryCodes.GENERAL_MERIT_QUOTA) {
//                Category femCat = getCategory(Constant.CategoryCodes.FEMALE_JURISDICTION, list);
//            } else {
//                Category femCat = getCategory(Constant.CategoryCodes.FEMALE_OUT_OF_JURISDICTION, list);
//            } 
//        }
//    }
//    
//    private static Category getCategory(int code, List<Category> list) {
//        for (int i = 0; i < list.size(); i++) {
//            Category category = list.get(i);
//            if(category.getCampusCategory().getCategoryCode() == code)
//                return category;
//        }
//        
//        return null;
//    }
}