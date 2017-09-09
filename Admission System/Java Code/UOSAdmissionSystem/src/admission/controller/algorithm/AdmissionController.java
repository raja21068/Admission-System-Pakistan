package admission.controller.algorithm;

import admission.enums.CategoryEnum;
import admission.enums.CategoryLogicalCodeEnum;
import admission.enums.GenderEnum;
import admission.enums.GroupEnum;
import admission.enums.ProvinceEnum;
import admission.model.AdmissionYear;
import admission.model.ProgramType;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author Yougeshwar
 */
public class AdmissionController {

    final private int STATUS_NOT_SELECTED = -1;
    final private int STATUS_FINAL_SELECTED = 0;
    final private int STATUS_PARTIAL_CHOICE_SELECTED = 1;
    final private int STATUS_PARTIAL_SHIFT_SELECTED = 2;

    private List<CandidateAlg> cList;
    /**
     * [campusId, [isMorning, [cposId, List<DisciplineAlg>]]]
     */
    private Map<Integer, Map<Boolean, Map<Integer, List<DisciplineAlg>>>> dMap;
    /**
     * [posId, List[programId, subjectId, percentage]]
     */
    private Map<Integer, List<Object[]>> prerequisite;
    private final AdmissionYear ay;
    private final ProgramType pt;

    public AdmissionController(AdmissionYear ay, ProgramType pt) {
        this.ay = ay;
        this.pt = pt;
    }

    public void setdMap(Map<Integer, Map<Boolean, Map<Integer, List<DisciplineAlg>>>> dMap) {
        this.dMap = dMap;
    }

    public void setcList(List<CandidateAlg> cList) {
        this.cList = cList;
    }

    public void setPrerequisite(Map<Integer, List<Object[]>> prerequisite) {
        this.prerequisite = prerequisite;
    }

    public boolean process() {
        for (CandidateAlg cn : cList) { //System.out.println(cn.getId() + " : " + cn.getPercentage());
            if(cn.getSeatNo() == 6200)
            {
                System.out.println("");
            }
            CANDIDATE_LEVEL:
            {
                /**
                 * Candidate: choicesMap[campusId, [isMorning, Integer[posId,
                 * cposId, cnposId, choiceNo]]]
                 */
                Map<Integer, Map<Boolean, List<Integer[]>>> choicesMap = cn.getChoices();
                Set<Integer> campusIds = choicesMap.keySet();
                if (campusIds == null) {
                    continue;
                }

                for (Integer campusId : campusIds) {
                    /**
                     * Candidate: shiftWC[isMorning, Integer[posId, cposId,
                     * cnposId, choiceNo]]
                     */
                    Map<Boolean, List<Integer[]>> shiftWiseChoices = choicesMap.get(campusId);
                    if (shiftWiseChoices == null) {
                        continue;
                    }

                    /**
                     * Discipline: campusWD[cposId, List<DisciplineAlg>]
                     */
                    Map<Boolean, Map<Integer, List<DisciplineAlg>>> shiftWiseCposDiscipline = dMap.get(campusId);

                    /**
                     * Shift loop (1st morning then evening)
                     */
                    boolean isMorning = true;
                    for (int i = 0; i < 2; i++) {
                        SHIFT_LEVEL:
                        {

                            Map<Integer, List<DisciplineAlg>> cposDisciplineList = shiftWiseCposDiscipline.get(isMorning);
                            if (cposDisciplineList == null || cposDisciplineList.isEmpty()) {
                                break SHIFT_LEVEL;
                            }
                            /**
                             * choicesList[Integer[posId, cposId, cnposId,
                             * choiceNo]]
                             */
                            List<Integer[]> choicesList = shiftWiseChoices.get(isMorning);
                            if (choicesList == null || choicesList.isEmpty()) {
                                break SHIFT_LEVEL;
                            }
                            // <editor-fold defaultstate="collapsed" desc="Choices Loop">  
                            for (Integer[] choice : choicesList) {
                                int posId = choice[0];
                                int cposId = choice[1];
                                int cnposId = choice[2];
                                int choiceNo = choice[3];

                                /**
                                 * This code is use for to stop further
                                 * selection of candidate on the basis of
                                 * previous selection choice no.
                                 */
                                Object[] preSelection = cn.getSelection();
                                if (preSelection != null) {
                                    boolean preIsMorning = (boolean) preSelection[1];
                                    int preChoiceNo = (int) preSelection[4];
                                    int preCatLogocalCode = (Integer) preSelection[CandidateAlg.Selection.SELECTION_CAT_LOGICAL_CODE];
                                    if (isMorning == preIsMorning) {
                                        if (preChoiceNo == choiceNo) {
                                            boolean sf = preCatLogocalCode == CategoryLogicalCodeEnum.SELF_FINANCE_QUOTA.ordinal(); 
                                            boolean opsf = preCatLogocalCode == CategoryLogicalCodeEnum.OP_SELF_FINANCE_QUOTA.ordinal(); 
                                            if ((!sf) && (!opsf) ) {
                                                break SHIFT_LEVEL;
                                            }
                                        }
                                        if (preChoiceNo < choiceNo) {
                                            break SHIFT_LEVEL;
                                        }
                                    }
                                }
                                int selection = -1;

                                List<DisciplineAlg> dList = cposDisciplineList.get(cposId);
                                if (dList == null || dList.isEmpty()) {
                                    continue;
                                }

                                /**
                                 * This condition is true for all discipline
                                 * except Computer Science Because Computer
                                 * Science selection based on more than one
                                 * group.
                                 */
                                if (dList.size() == 1) {
                                    DisciplineAlg d = dList.get(0);
                                    if (cn.isPosAllocated(!isMorning, d.getPosId())) {// if pos already allocated then continue the next choice
                                        continue;
                                    }

                                    /**
                                     * Checking the prerequisite (candidate is
                                     * applicable for selection in this
                                     * descipline or not)
                                     */
                                    if (pt.getIsBachelor()) {                                                                               /* Changed by JK from Percentage  */

                                        if (prerequisite.get(d.getPosId()) == null) {
                                            System.out.println("Preprequisite not found : pos" + d.getPosId());
                                        }
                                        if (!PrerequisiteLocater.bachalorPrerequisiteAlg(prerequisite.get(d.getPosId()), cn.getProgramId(), cn.getLastCredetialPercentage())) {
                                            continue;
                                        }
                                    } else {                                                                                                                                                                   /* Changed by JK from Percentage  */

                                        if (!PrerequisiteLocater.masterPrerequisiteAlg(d.getPosId(), isMorning, cn.getDegreeYears(), cn.getProgramId(), cn.getOptionalSubjectIds(), prerequisite.get(d.getPosId()), cn.getLastCredetialPercentage())) {
                                            continue;
                                        }
                                    }

                                    selection = selection(cn, choiceNo, d, isMorning, cnposId, cposId, campusId);

                                } else {
                                    // for BS-CS multiple groups
                                    for (DisciplineAlg d : dList) {
                                        if (d.getCposGroup().getTitle().toUpperCase().equals(cn.getProgramGrp().toUpperCase())) {
                                            selection = selection(cn, choiceNo, d, isMorning, cnposId, cposId, campusId);
                                        }
                                    }
                                }
                                if (selection == STATUS_FINAL_SELECTED) {
                                    break CANDIDATE_LEVEL;
                                } else if (selection == STATUS_PARTIAL_CHOICE_SELECTED) {
//                                    break; // choice loop
                                } else if (selection == STATUS_PARTIAL_SHIFT_SELECTED) {
                                    break SHIFT_LEVEL; // choice loop
                                }
                            }// </editor-fold>
                        }
                        isMorning = false;
                    }
                }
            }
        }
        return false;
    }

    /**
     * status -1 : not selected 0 : final selected 1 : make selection in next
     * choice
     */
    private int selection(CandidateAlg cn, int choiceNo, DisciplineAlg d, boolean isMorning, int cnposId, int cposId, int campusId) {
        int status = -1;
        int seatNo = cn.getSeatNo(); 
            if(seatNo == 9861){
                System.out.println("");
            }
        STATUS_LABEL:
        {
            //<editor-fold defaultstate="collapsed" desc="If Morning">
            if (isMorning) {
                if (cn.getProvinceCode() == ProvinceEnum.SINDH.ordinal() && (cn.getDistrictId() != 22 && campusId != 7))/*22 of karachi & 7 for thatta campus*/ {
                    // <editor-fold defaultstate="collapsed" desc="If General Merit">
                    if (cn.isContainCategory(CategoryEnum.GM_DUR_QUOTA) && cn.isCatNotAllocatedWithChoiceNo(isMorning, choiceNo, CategoryEnum.GM_DUR_QUOTA.ordinal())) {
                        /**
                         * GM, Upr Sindh, Female, Upr Female, Commerce
                         */
                        CategoryAlg INNER_MERIT = d.getCategoryMap().get(CategoryLogicalCodeEnum.GENERAL_MERIT_QUOTA);
                        CategoryAlg INNER_F_MERIT = d.getCategoryMap().get(CategoryLogicalCodeEnum.FEMALE_JURISDICTION);

                        CategoryAlg UPPER_MERIT = d.getCategoryMap().get(CategoryLogicalCodeEnum.UPPER_SINDH_OUT_OF_JURISDICTION_QUOTA);
                        CategoryAlg UPPER_F_MERIT = d.getCategoryMap().get(CategoryLogicalCodeEnum.FEMALE_OUT_OF_JURISDICTION);

                        CategoryAlg COMM_MERIT = d.getCategoryMap().get(CategoryLogicalCodeEnum.COMMERCE_QUOTA);

                        boolean b = false;
                        // Discipline, having selection on quota basis
                        if (d.isQuotaOriented()) {
                            //<editor-fold defaultstate="collapsed" desc="Discipline Quota Oriented">
                            if (cn.isJurisdication()) { // Jurisdiction
                                int[] array = INNER_MERIT.getJurisMap().get(cn.getDistrictId());

                                //<editor-fold defaultstate="collapsed" desc="District Urban Rural">
                                if (array[cn.getArea()] > 0) {// check seats from quota district
                                    array[cn.getArea()]--;
                                    INNER_MERIT.addCandidate(cn);
                                    int catCode = cn.addCurrentSelection(isMorning, d.getPosId(), CategoryEnum.GM_DUR_QUOTA.ordinal(), choiceNo, CategoryLogicalCodeEnum.GENERAL_MERIT_QUOTA.ordinal(), cnposId, INNER_MERIT, d, cposId,this);
                                    releaseSeat(catCode, cn);
                                    b = true;
                                } //</editor-fold>
                                //<editor-fold defaultstate="collapsed" desc="Other district seats (for badin campus where distributed by district not by urbal rural)">
                                else if (array.length > 2 && array[2] > 0) {
                                    array[2]--;
                                    INNER_MERIT.addCandidate(cn);
                                    int catCode = cn.addCurrentSelection(isMorning, d.getPosId(), CategoryEnum.GM_DUR_QUOTA.ordinal(), choiceNo, CategoryLogicalCodeEnum.GENERAL_MERIT_QUOTA.ordinal(), cnposId, INNER_MERIT, d, cposId,this);
                                    releaseSeat(catCode, cn);
                                    b = true;
                                } //</editor-fold>
                                else {
                                    // checking seats in FEMALE QUOTA
                                    if (GenderEnum.values()[cn.getGender()].equals(GenderEnum.FEMALE) && INNER_F_MERIT.isSeatsAvailable()) {
                                        INNER_F_MERIT.addCandidate(cn);
                                        int catCode = cn.addCurrentSelection(isMorning, d.getPosId(), CategoryEnum.GM_DUR_QUOTA.ordinal(), choiceNo, CategoryLogicalCodeEnum.FEMALE_JURISDICTION.ordinal(), cnposId, INNER_F_MERIT, d, cposId,this);
                                        releaseSeat(catCode, cn);
                                        b = true;
                                    } else if (cn.getProgramGrp().equals("COMMERCE")) { // checking from COMMERCE GROUP AND SEATS
                                        if (COMM_MERIT != null && COMM_MERIT.isSeatsAvailable() && COMM_MERIT.getSeats()[cn.getGender()][cn.getArea()] > 0) {
                                            COMM_MERIT.getSeats()[cn.getGender()][cn.getArea()]--;
                                            COMM_MERIT.addCandidate(cn);
                                            int catCode = cn.addCurrentSelection(isMorning, d.getPosId(), CategoryEnum.GM_DUR_QUOTA.ordinal(), choiceNo, CategoryLogicalCodeEnum.COMMERCE_QUOTA.ordinal(), cnposId, COMM_MERIT, d, cposId,this);
                                            releaseSeat(catCode, cn);
                                            b = true;
                                        }
                                    }
                                }
                            } else { // Out of Jurisdication
                                if (UPPER_MERIT != null) {
                                    int[] array = UPPER_MERIT.getJurisMap().get(cn.getDistrictId());
                                    if (array != null && array[cn.getArea()] > 0) {// check seats from quota district
                                        array[cn.getArea()]--;
                                        UPPER_MERIT.addCandidate(cn);
                                        int catCode = cn.addCurrentSelection(isMorning, d.getPosId(), CategoryEnum.GM_DUR_QUOTA.ordinal(), choiceNo, CategoryLogicalCodeEnum.UPPER_SINDH_OUT_OF_JURISDICTION_QUOTA.ordinal(), cnposId, UPPER_MERIT, d, cposId,this);
                                        releaseSeat(catCode, cn);
                                        b = true;
                                    } else {
                                        if (GenderEnum.values()[cn.getGender()].equals(GenderEnum.FEMALE) && UPPER_F_MERIT.isSeatsAvailable()) {
                                            UPPER_F_MERIT.addCandidate(cn);
                                            int catCode = cn.addCurrentSelection(isMorning, d.getPosId(), CategoryEnum.GM_DUR_QUOTA.ordinal(), choiceNo, CategoryLogicalCodeEnum.FEMALE_OUT_OF_JURISDICTION.ordinal(), cnposId, UPPER_F_MERIT, d, cposId,this);
                                            releaseSeat(catCode, cn);
                                            b = true;
                                        } else if (cn.getProgramGrp().contains("COM")) {
                                            if (COMM_MERIT != null && COMM_MERIT.isSeatsAvailable() && COMM_MERIT.getSeats()[cn.getGender()][cn.getArea()] > 0) {
                                                COMM_MERIT.getSeats()[cn.getGender()][cn.getArea()]--;
                                                COMM_MERIT.addCandidate(cn);
                                                int catCode = cn.addCurrentSelection(isMorning, d.getPosId(), CategoryEnum.GM_DUR_QUOTA.ordinal(), choiceNo, CategoryLogicalCodeEnum.COMMERCE_QUOTA.ordinal(), cnposId, COMM_MERIT, d, cposId,this);
                                                releaseSeat(catCode, cn);
                                                b = true;
                                            }
                                        }
                                    }
                                }
                            }
                        }//</editor-fold> 
                        else { // Without quota oriented
                            //<editor-fold defaultstate="collapsed" desc="Discipline Without Quota Oriented">  
                            if (cn.isJurisdication()) { // Jurisdiction
                                if (INNER_MERIT.isSeatsAvailable()) {
                                    INNER_MERIT.addCandidate(cn);
                                    int catCode = cn.addCurrentSelection(isMorning, d.getPosId(), CategoryEnum.GM_DUR_QUOTA.ordinal(), choiceNo, CategoryLogicalCodeEnum.GENERAL_MERIT_QUOTA.ordinal(), cnposId, INNER_MERIT, d, cposId,this);
                                    releaseSeat(catCode, cn);

                                    b = true;
                                } // Female Quota
                                else if (GenderEnum.values()[cn.getGender()].equals(GenderEnum.FEMALE) && INNER_F_MERIT.isSeatsAvailable()) {
                                    INNER_F_MERIT.addCandidate(cn);
                                    int catCode = cn.addCurrentSelection(isMorning, d.getPosId(), CategoryEnum.GM_DUR_QUOTA.ordinal(), choiceNo, CategoryLogicalCodeEnum.FEMALE_JURISDICTION.ordinal(), cnposId, INNER_F_MERIT, d, cposId,this);
                                    releaseSeat(catCode, cn);

                                    b = true;
                                } else if (COMM_MERIT != null && cn.getProgramGrp().contains("COM") && COMM_MERIT.isSeatsAvailable() && COMM_MERIT.getSeats()[cn.getGender()][cn.getArea()] > 0) {
                                    COMM_MERIT.addCandidate(cn);
                                    int catCode = cn.addCurrentSelection(isMorning, d.getPosId(), CategoryEnum.GM_DUR_QUOTA.ordinal(), choiceNo, CategoryLogicalCodeEnum.COMMERCE_QUOTA.ordinal(), cnposId, COMM_MERIT, d, cposId,this);
                                    releaseSeat(catCode, cn);

                                    b = true;
                                }
                            } else {
                                if (UPPER_MERIT == null) {
//                                    System.out.println(UPPER_MERIT);
                                }
                                if (UPPER_MERIT != null && UPPER_MERIT.isSeatsAvailable()) {
                                    UPPER_MERIT.addCandidate(cn);
                                    int catCode = cn.addCurrentSelection(isMorning, d.getPosId(), CategoryEnum.GM_DUR_QUOTA.ordinal(), choiceNo, CategoryLogicalCodeEnum.UPPER_SINDH_OUT_OF_JURISDICTION_QUOTA.ordinal(), cnposId, UPPER_MERIT, d, cposId,this);
                                    releaseSeat(catCode, cn);

                                    b = true;
                                } else if (UPPER_F_MERIT != null && GenderEnum.values()[cn.getGender()].equals(GenderEnum.FEMALE) && UPPER_F_MERIT.isSeatsAvailable()) {
                                    UPPER_F_MERIT.addCandidate(cn);
                                    int catCode = cn.addCurrentSelection(isMorning, d.getPosId(), CategoryEnum.GM_DUR_QUOTA.ordinal(), choiceNo, CategoryLogicalCodeEnum.FEMALE_OUT_OF_JURISDICTION.ordinal(), cnposId, UPPER_F_MERIT, d, cposId,this);
                                    releaseSeat(catCode, cn);

                                    b = true;
                                } else if (COMM_MERIT != null && cn.getProgramGrp().contains("COM") && COMM_MERIT.isSeatsAvailable() && COMM_MERIT.getSeats()[cn.getGender()][cn.getArea()] > 0) {
                                    COMM_MERIT.addCandidate(cn);
                                    int catCode = cn.addCurrentSelection(isMorning, d.getPosId(), CategoryEnum.GM_DUR_QUOTA.ordinal(), choiceNo, CategoryLogicalCodeEnum.COMMERCE_QUOTA.ordinal(), cnposId, COMM_MERIT, d, cposId,this);
                                    releaseSeat(catCode, cn);

                                    b = true;
                                }
                            }
                        }
                        //</editor-fold>

                        if (b) { // if selection of this candidate on MERIT BASIS,  then it will process next student
                            cn.getCategories().remove(CategoryEnum.GM_DUR_QUOTA);
                            status = choiceNo == 1 ? STATUS_FINAL_SELECTED : STATUS_PARTIAL_SHIFT_SELECTED;
                            break STATUS_LABEL;
                        }
                    }
                //</editor-fold>

                    //<editor-fold defaultstate="collapsed" desc="If Affilated College">
                    CategoryAlg cat = d.getCategoryMap().get(CategoryLogicalCodeEnum.SUE_SD_AC_QUOTA);
                    if (cn.isContainCategory(CategoryEnum.AC_QUOTA) && cat != null && cat.isSeatsAvailable()) {
                        if (cn.isCatNotAllocatedWithChoiceNo(isMorning, choiceNo, CategoryEnum.AC_QUOTA.ordinal())) {
                            cat.addCandidate(cn);
                            int catCode = cn.addCurrentSelection(isMorning, d.getPosId(), CategoryEnum.AC_QUOTA.ordinal(), choiceNo, CategoryLogicalCodeEnum.SUE_SD_AC_QUOTA.ordinal(), cnposId, cat, d, cposId,this);
                            releaseSeat(catCode, cn);
                            status = choiceNo == 1 ? STATUS_FINAL_SELECTED : STATUS_PARTIAL_SHIFT_SELECTED;
                            cn.getCategories().remove(CategoryEnum.AC_QUOTA);
                            break STATUS_LABEL;
                        }
                    }
                    //</editor-fold>

                    //<editor-fold desc="Disabled Person" defaultstate="collapsed">
                    if (cn.isContainCategory(CategoryEnum.DP_QUOTA)) {
                        if (cn.isCatNotAllocatedWithChoiceNo(isMorning, choiceNo, CategoryEnum.DP_QUOTA.ordinal())) {
                            CategoryAlg DP_MERIT = d.getCategoryMap().get(CategoryLogicalCodeEnum.DISABLE_QUOTA);
                            if (DP_MERIT != null && DP_MERIT.isSeatsAvailable()) {
                                DP_MERIT.addCandidate(cn);
                                int catCode = cn.addCurrentSelection(isMorning, d.getPosId(), CategoryEnum.DP_QUOTA.ordinal(), choiceNo, CategoryLogicalCodeEnum.DISABLE_QUOTA.ordinal(), cnposId, DP_MERIT, d, cposId,this);
                                releaseSeat(catCode, cn);
                                status = choiceNo == 1 ? STATUS_FINAL_SELECTED : STATUS_PARTIAL_SHIFT_SELECTED;
                                cn.getCategories().remove(CategoryEnum.DP_QUOTA);
                                break STATUS_LABEL;
                            }
                        }
                    }
                    //</editor-fold>
                    
                    //<editor-fold desc="Karachi Quota" defaultstate="collapsed">
                    if (cn.isContainCategory(CategoryEnum.KHI_RES_QUOTA)) {
                        if (cn.isCatNotAllocatedWithChoiceNo(isMorning, choiceNo, CategoryEnum.KHI_RES_QUOTA.ordinal())) {
                            CategoryAlg KARACHI_MERIT = d.getCategoryMap().get(CategoryLogicalCodeEnum.KHI_QUOTA);
                            if (KARACHI_MERIT != null && KARACHI_MERIT.isSeatsAvailable()) {
                                KARACHI_MERIT.addCandidate(cn);
                                int catCode = cn.addCurrentSelection(isMorning, d.getPosId(), CategoryEnum.KHI_RES_QUOTA.ordinal(), choiceNo, CategoryLogicalCodeEnum.KHI_QUOTA.ordinal(), cnposId, KARACHI_MERIT, d, cposId,this);
                                releaseSeat(catCode, cn);
                                status = choiceNo == 1 ? STATUS_FINAL_SELECTED : STATUS_PARTIAL_SHIFT_SELECTED;
                                cn.getCategories().remove(CategoryEnum.KHI_RES_QUOTA);
                                break STATUS_LABEL;
                            }
                        }
                    }
                    //</editor-fold>

                    //<editor-fold defaultstate="collapsed" desc="COMMENTED If Sports Quota">
                    /*
                if (cn.isContainCategory(CategoryEnum.SP_QUOTA) && cn.isCatNotAllocatedWithChoiceNo(isMorning, choiceNo, CategoryEnum.SP_QUOTA.ordinal())) {
                    CategoryAlg catSports = d.getCategoryMap().get(CategoryLogicalCodeEnum.SPORTS_QUOTA);
                    if (catSports.isSeatsAvailable()) {
                        catSports.addCandidate(cn);
                        int catCode = cn.addCurrentSelection(isMorning, d.getPosId(), CategoryEnum.SP_QUOTA.ordinal(), choiceNo, CategoryLogicalCodeEnum.SPORTS_QUOTA.ordinal(), cnposId, cat, d, cposId,this);
                        releaseSeat(catCode, cn);
                        status = choiceNo == 1 ? STATUS_FINAL_SELECTED : STATUS_PARTIAL_SHIFT_SELECTED;
                        cn.getCategories().remove(CategoryEnum.SP_QUOTA);
                        break STATUS_LABEL;
                    }
                }*/
                //</editor-fold>
                }

                //<editor-fold defaultstate="collapsed" desc="If Sindh University Employee Quota">
                if (cn.isContainCategory(CategoryEnum.SUE_QUOTA) && cn.isCatNotAllocatedWithChoiceNo(isMorning, choiceNo, CategoryEnum.SUE_QUOTA.ordinal())) {
                    CategoryAlg cat = d.getCategoryMap().get(CategoryLogicalCodeEnum.SUE_SD_QUOTA);
                    if (cat.isSeatsAvailable()) {
                        cat.addCandidate(cn);
                        int catCode = cn.addCurrentSelection(isMorning, d.getPosId(), CategoryEnum.SUE_QUOTA.ordinal(), choiceNo, CategoryLogicalCodeEnum.SUE_SD_QUOTA.ordinal(), cnposId, cat, d, cposId,this);
                        releaseSeat(catCode, cn);
                        status = choiceNo == 1 ? STATUS_FINAL_SELECTED : STATUS_PARTIAL_SHIFT_SELECTED;
                        cn.getCategories().remove(CategoryEnum.SUE_QUOTA);
                        break STATUS_LABEL;
                    }
                }
                //</editor-fold>

                
                
                //<editor-fold defaultstate="collapsed" desc="If Army Public Quota">
                if (cn.isContainCategory(CategoryEnum.AP_QUOTA) && cn.isCatNotAllocatedWithChoiceNo(isMorning, choiceNo, CategoryEnum.AP_QUOTA.ordinal())) {
                    CategoryAlg cat = d.getCategoryMap().get(CategoryLogicalCodeEnum.ARMY_PER_QUOTA);
                    if (cat.isSeatsAvailable()) {
                        cat.addCandidate(cn);
                        int catCode = cn.addCurrentSelection(isMorning, d.getPosId(), CategoryEnum.AP_QUOTA.ordinal(), choiceNo, CategoryLogicalCodeEnum.ARMY_PER_QUOTA.ordinal(), cnposId, cat, d, cposId,this);
                        releaseSeat(catCode, cn);
                        status = choiceNo == 1 ? STATUS_FINAL_SELECTED : STATUS_PARTIAL_SHIFT_SELECTED;
                        cn.getCategories().remove(CategoryEnum.AP_QUOTA);
                        break STATUS_LABEL;
                    }
                }
                //</editor-fold>

//              if (cn.isContainCategory(CategoryEnum.NO_QUOTA) && cn.isCatNotAllocated(isMorning, choiceNo, CategoryEnum.NO_QUOTA.ordinal())) {
//                    
//                }
                //<editor-fold defaultstate="collapsed" desc="If Self Finance Morning">
                if (cn.isContainCategory(CategoryEnum.SFM_QUOTA) && cn.isCatNotAllocatedWithChoiceNo(isMorning, choiceNo, CategoryEnum.SFM_QUOTA.ordinal())) {
                    CategoryAlg SF_SINDH = d.getCategoryMap().get(CategoryLogicalCodeEnum.SELF_FINANCE_QUOTA);
                    CategoryAlg SF_OP = d.getCategoryMap().get(CategoryLogicalCodeEnum.OP_SELF_FINANCE_QUOTA);
                    //<editor-fold desc="If candidate of Sindh">
                    if (cn.getProvinceCode() == ProvinceEnum.SINDH.ordinal()) {
                        if (SF_SINDH != null && SF_SINDH.isSeatsAvailable()) { // 22 is district id of KARACHI
                            SF_SINDH.addCandidate(cn);
                            int catCode = cn.addCurrentSelection(isMorning, d.getPosId(), CategoryEnum.SFM_QUOTA.ordinal(), choiceNo, CategoryLogicalCodeEnum.SELF_FINANCE_QUOTA.ordinal(), cnposId, SF_SINDH, d, cposId,this);
                            releaseSeat(catCode, cn);
                            status = STATUS_PARTIAL_CHOICE_SELECTED;
                            cn.getCategories().remove(CategoryEnum.SFM_QUOTA);
                            break STATUS_LABEL;
                        }
                    } //</editor-fold>
                    //<editor-fold desc="else candidate of other province">
                    else {
                        if (SF_OP != null && SF_OP.isSeatsAvailable()) {
                            SF_OP.addCandidate(cn);
                            int catCode = cn.addCurrentSelection(isMorning, d.getPosId(), CategoryEnum.SFM_QUOTA.ordinal(), choiceNo, CategoryLogicalCodeEnum.OP_SELF_FINANCE_QUOTA.ordinal(), cnposId, SF_OP, d, cposId,this);
                            releaseSeat(catCode, cn);
                            status = STATUS_PARTIAL_CHOICE_SELECTED;
                            cn.getCategories().remove(CategoryEnum.SFM_QUOTA);
                            break STATUS_LABEL;
                        }
                    }//</editor-fold>
                }//</editor-fold>
            } //</editor-fold>
            //<editor-fold defaultstate="collapsed" desc="else Self Finance Evening">
            else {
                if (cn.isContainCategory(CategoryEnum.SFE_QUOTA) && cn.isCatNotAllocatedWithChoiceNo(isMorning, choiceNo, CategoryEnum.SFE_QUOTA.ordinal())) {
                    CategoryAlg SF_ = d.getCategoryMap().get(CategoryLogicalCodeEnum.SELF_FINANCE_QUOTA);

//                    if (cn.getProvinceCode() == ProvinceEnum.SINDH.ordinal()) {
//                        if ((cn.getDistrictId() == 22 && d.getCposId() == 189)) {
//                            // Karachi district student not allowed in MBA evening program
//                            // 22 is district id of KARACHI and 189 is Jamshoro campus MBA evening program
//                        } else 
                    if (SF_ != null && SF_.isSeatsAvailable()) {
                        SF_.addCandidate(cn);
                        int catCode = cn.addCurrentSelection(isMorning, d.getPosId(), CategoryEnum.SFE_QUOTA.ordinal(), choiceNo, CategoryLogicalCodeEnum.SELF_FINANCE_QUOTA.ordinal(), cnposId, SF_, d, cposId,this); /* previous SFM  by JK */

                        releaseSeat(catCode, cn);
                        status = STATUS_PARTIAL_CHOICE_SELECTED;
                        cn.getCategories().remove(CategoryEnum.SFE_QUOTA);/* previous SFM  by JK */

                        break STATUS_LABEL;
                    }
//                    }
                }
            }
            //</editor-fold>
        }

        return status;
    }

    private boolean releaseSeat(int catLogicalCode, CandidateAlg cn) {
        if (catLogicalCode < 0) {
            return false;
        }
        /*[0campusId, 1isMorning, 2posId, 3cposgId, 4choiceNo, 5selCat, 6catLogicalCode, 7CAMPUS_PROGRAM_OF_STUDY_ID]*/
        Object[] selection = cn.getSelection();
        
        int campusId = (int) selection[0];
        boolean isMorning = (boolean) selection[1];
        int cposgId = (int) selection[3];
        int cposId = (int) selection[7];
        int preLogicalCode = (Integer)selection[CandidateAlg.Selection.SELECTION_CAT_LOGICAL_CODE];
        if(preLogicalCode != catLogicalCode){
            return false;
        }
        Map<Boolean, Map<Integer, List<DisciplineAlg>>> map = dMap.get(campusId);
        Map<Integer, List<DisciplineAlg>> innerMap = map.get(isMorning);
        List<DisciplineAlg> list = innerMap.get(cposId);
        for (DisciplineAlg dis : list) {
            if (dis.getCposgId() == cposgId) {
                CategoryAlg cat = dis.getCategoryMap().get(CategoryLogicalCodeEnum.values()[catLogicalCode]);  
                if(dis.isQuotaOriented()){
                    int[] array = cat.getJurisMap().get(cn.getDistrictId());
                    try{
                    array[cn.getArea()]++;
                    }catch(NullPointerException ex){
                       //This exception occurs when no seats avalaible that category in discipline
                       // If releasing self finance in quota oriented it will occur
                    }
                }
                cat.setConsumedSeats(cat.getConsumedSeats() - 1);
                cn.setSelection(null);
                return true;
            }
        }
        return false;
    }
    
    
    /*this method is for removing previous selection from current selection 
     used for when selection process of BS(Commerce)
     if student get better choice then remove current selection*/
    public boolean releaseSeatFromCurrent(boolean isMorning, int catLogicalCode, int[] currentSelection, CandidateAlg cn) {
        int cposgId = (int) currentSelection[CandidateAlg.CurrentSelection.CPOSG_ID];
        int cposId = (int) currentSelection[CandidateAlg.CurrentSelection.CPOS_ID];
        Set<Integer> campusIDs = dMap.keySet();
        for (Integer campusId : campusIDs) {
            Map<Boolean, Map<Integer, List<DisciplineAlg>>> map = dMap.get(campusId);
            Map<Integer, List<DisciplineAlg>> innerMap = map.get(isMorning);
            List<DisciplineAlg> list = innerMap.get(cposId);
            for (DisciplineAlg dis : list) {
                if (dis.getCposgId() == cposgId) {
                    CategoryAlg cat = dis.getCategoryMap().get(CategoryLogicalCodeEnum.values()[catLogicalCode]);
                    cat.setConsumedSeats(cat.getConsumedSeats() - 1);
                    return true;
                }
            }

        }
        return false;
    }

    public List<CandidateAlg> getcList() {
        return cList;
    }

    public Map<Integer, Map<Boolean, Map<Integer, List<DisciplineAlg>>>> getdMap() {
        return dMap;
    }

    public AdmissionYear getAy() {
        return ay;
    }

    public ProgramType getPt() {
        return pt;
    }

    public boolean processForRemainingSeats() {
        for (CandidateAlg cn : cList) {

            if (cn.isJurisdication()) {
                CANDIDATE_LEVEL:
                {
                    Map<Boolean, List<int[]>> currentSelection = cn.getCurrentSelection();

                    if (currentSelection != null && currentSelection.get(true) != null) {
                        List<int[]> selections = currentSelection.get(true);
                        for (int[] is : selections) {
                            int cat = is[CandidateAlg.CurrentSelection.CAT];
                            int choice = is[CandidateAlg.CurrentSelection.CHOICE_NO];
                            if (choice == 1 && cat != CategoryEnum.SFM_QUOTA.ordinal()) {
                                break CANDIDATE_LEVEL;
                            }
                        }
                    }
                    /**
                     * Candidate: choicesMap[campusId, [isMorning,
                     * Integer[posId, cposId, cnposId, choiceNo]]]
                     */
                    Map<Integer, Map<Boolean, List<Integer[]>>> choicesMap = cn.getChoices();
                    Set<Integer> campusIds = choicesMap.keySet();
                    if (campusIds == null) {
                        continue;
                    }

                    for (Integer campusId : campusIds) {
                        /**
                         * Candidate: shiftWC[isMorning, Integer[posId, cposId,
                         * cnposId, choiceNo]]
                         */
                        Map<Boolean, List<Integer[]>> shiftWiseChoices = choicesMap.get(campusId);
                        if (shiftWiseChoices == null) {
                            continue;
                        }

                        /**
                         * Discipline: campusWD[cposId, List<DisciplineAlg>]
                         */
                        Map<Boolean, Map<Integer, List<DisciplineAlg>>> shiftWiseCposDiscipline = dMap.get(campusId);

                        /**
                         * Shift loop (1st morning then evening)
                         */
                        boolean isMorning = true;
                        for (int i = 0; i < 1; i++) {
                            SHIFT_LEVEL:
                            {

                                Map<Integer, List<DisciplineAlg>> cposDisciplineList = shiftWiseCposDiscipline.get(isMorning);
                                if (cposDisciplineList == null || cposDisciplineList.isEmpty()) {
                                    break SHIFT_LEVEL;
                                }
                                /**
                                 * choicesList[Integer[posId, cposId, cnposId,
                                 * choiceNo]]
                                 */
                                List<Integer[]> choicesList = shiftWiseChoices.get(isMorning);
                                if (choicesList == null || choicesList.isEmpty()) {
                                    break SHIFT_LEVEL;
                                }
                                // <editor-fold defaultstate="collapsed" desc="Choices Loop">  
                                for (Integer[] choice : choicesList) {
                                    int posId = choice[0];
                                    int cposId = choice[1];
                                    int cnposId = choice[2];
                                    int choiceNo = choice[3];

                                    /**
                                     * This code is use for to stop further
                                     * selection of candidate on the basis of
                                     * previous selection choice no.
                                     */
                                    Object[] preSelection = cn.getSelection();
                                if (preSelection != null) {
                                    boolean preIsMorning = (boolean) preSelection[1];
                                    int preChoiceNo = (int) preSelection[4];
                                    int preCatLogocalCode = (Integer) preSelection[CandidateAlg.Selection.SELECTION_CAT_LOGICAL_CODE];
                                    if (isMorning == preIsMorning) {
                                        if (preChoiceNo == choiceNo) {
                                            boolean sf = preCatLogocalCode == CategoryLogicalCodeEnum.SELF_FINANCE_QUOTA.ordinal(); 
                                            boolean opsf = preCatLogocalCode == CategoryLogicalCodeEnum.OP_SELF_FINANCE_QUOTA.ordinal(); 
                                            if ((!sf) && (!opsf) ) {
                                                break SHIFT_LEVEL;
                                            }
                                        }
                                        if (preChoiceNo < choiceNo) {
                                            break SHIFT_LEVEL;
                                        }
                                    }
                                }
                                    int selection = -1;

                                    List<DisciplineAlg> dList = cposDisciplineList.get(cposId);
                                    if (dList == null || dList.isEmpty()) {
                                        continue;
                                    }

                                    /**
                                     * This condition is true for all discipline
                                     * except Computer Science Because Computer
                                     * Science selection based on more than one
                                     * group.
                                     */
                                    if (dList.size() == 1) {
                                        DisciplineAlg d = dList.get(0);
                                        if (cn.isPosAllocated(!isMorning, d.getPosId())) {// if pos already allocated then continue the next choice
                                            continue;
                                        }

                                        /**
                                         * Checking the prerequisite (candidate
                                         * is applicable for selection in this
                                         * descipline or not)
                                         */
                                        if (pt.getIsBachelor()) {                                                                               /* Changed by JK from Percentage  */

                                            if (prerequisite.get(d.getPosId()) == null) {
                                                System.out.println("Preprequisite not found : pos" + d.getPosId());
                                            }
                                            if (!PrerequisiteLocater.bachalorPrerequisiteAlg(prerequisite.get(d.getPosId()), cn.getProgramId(), cn.getLastCredetialPercentage())) {
                                                continue;
                                            }
                                        } else {
                                            if (!PrerequisiteLocater.masterPrerequisiteAlg(d.getPosId(), isMorning, cn.getDegreeYears(), cn.getProgramId(), cn.getOptionalSubjectIds(), prerequisite.get(d.getPosId()), cn.getLastCredetialPercentage())) {
                                                continue;
                                            }
                                        }

                                        selection = selectionForRemainingSeats(cn, choiceNo, d, isMorning, cnposId, cposId, cposDisciplineList);

                                    } else {
                                        // for BS-CS multiple groups
                                        for (DisciplineAlg d : dList) {
                                            if (d.getCposGroup().getTitle().toUpperCase().equals(cn.getProgramGrp().toUpperCase())) {
                                                selection = selectionForRemainingSeats(cn, choiceNo, d, isMorning, cnposId, cposId, cposDisciplineList);
                                            }
                                        }
                                    }
                                    if (selection == STATUS_FINAL_SELECTED) {
                                        break CANDIDATE_LEVEL;
                                    } else if (selection == STATUS_PARTIAL_CHOICE_SELECTED) {
//                                    break; // choice loop
                                    } else if (selection == STATUS_PARTIAL_SHIFT_SELECTED) {
                                        break SHIFT_LEVEL; // choice loop
                                    }
                                }// </editor-fold>
                            }
                            isMorning = false;
                        }
                    }
                }
            }
        }
        return false;
    }

    /**
     * status -1 : not selected 0 : final selected 1 : make selection in next
     * choice
     */
    private int selectionForRemainingSeats(CandidateAlg cn, int choiceNo, DisciplineAlg d, boolean isMorning, int cnposId, int cposId, Map<Integer, List<DisciplineAlg>> cposDisciplineList) {
        int status = STATUS_NOT_SELECTED;

        STATUS_LABEL:
        {
            //<editor-fold defaultstate="collapsed" desc="If Morining">
            if (isMorning) {
                // <editor-fold defaultstate="collapsed" desc="If General Merit">
//                if (cn.isContainCategory(CategoryEnum.GM_DUR_QUOTA) && cn.isCatNotAllocatedWithChoiceNo(isMorning, choiceNo, CategoryEnum.GM_DUR_QUOTA.ordinal())) {
                /**
                 * GM, Upr Sindh, Female, Upr Female, Commerce
                 */
                CategoryAlg INNER_MERIT = d.getCategoryMap().get(CategoryLogicalCodeEnum.GENERAL_MERIT_QUOTA);

                boolean b = false;
                // Discipline, having selection on quota basis
                if (d.isQuotaOriented()) {

                    if (INNER_MERIT.isSeatsAvailable()) {
                        Map<Boolean, List<int[]>> currentSelection = cn.getCurrentSelection();
                        if (currentSelection != null && currentSelection.get(true) != null) {
                            List<int[]> sel = currentSelection.get(true);

                            int firstSelection[] = sel.get(0);
                            int previousChoiceNo = firstSelection[CandidateAlg.CurrentSelection.CHOICE_NO];
                            int previousCat = firstSelection[CandidateAlg.CurrentSelection.CAT];
                            //<editor-fold defaultstate="collapsed" desc="If same choice in which previously selected">
                            if (previousChoiceNo == choiceNo) {
                                if (previousCat == CategoryEnum.SFM_QUOTA.ordinal()) {
                                    for (int j = 0; j < sel.size(); j++) {
                                        int loopCpos = sel.get(j)[CandidateAlg.CurrentSelection.CPOS_ID];
                                        List<DisciplineAlg> dList = cposDisciplineList.get(loopCpos);
                                        DisciplineAlg selDiscipline = dList.get(0);
                                        int catCode = sel.get(j)[CandidateAlg.CurrentSelection.CAT];
                                        if (catCode == CategoryEnum.SFM_QUOTA.ordinal()) {
                                            CategoryAlg SF_SINDH = selDiscipline.getCategoryMap().get(CategoryLogicalCodeEnum.SELF_FINANCE_QUOTA);
                                            SF_SINDH.getCandidatesList().remove(cn);
                                            sel.remove(sel.get(j));
                                        } else {
                                            CategoryAlg MERIT = selDiscipline.getCategoryMap().get(CategoryLogicalCodeEnum.GENERAL_MERIT_QUOTA);
                                            MERIT.getCandidatesList().remove(cn);
                                            sel.remove(sel.get(j));
                                        }

                                    }
                                } else {
                                    return STATUS_FINAL_SELECTED;
                                }
                            }//</editor-fold>
                            //<editor-fold defaultstate="collapsed" desc="if Previous selection is better than current">
                            else if (previousChoiceNo < choiceNo) {
                                if (previousCat != CategoryEnum.SFM_QUOTA.ordinal()) {
                                    return STATUS_FINAL_SELECTED;
                                }
                            }//</editor-fold>
                            //<editor-fold defaultstate="collapsed" desc="if this choice is better than previous">
                            else if (previousChoiceNo > choiceNo) {
                                for (int j = 0; j < sel.size(); j++) {
                                    int loopCpos = sel.get(j)[CandidateAlg.CurrentSelection.CPOS_ID];
                                    List<DisciplineAlg> dList = cposDisciplineList.get(loopCpos);
                                    DisciplineAlg selDiscipline = dList.get(0);
                                    int catCode = sel.get(j)[CandidateAlg.CurrentSelection.CAT];
                                    if (catCode == CategoryEnum.SFM_QUOTA.ordinal()) {
                                        CategoryAlg SF_SINDH = selDiscipline.getCategoryMap().get(CategoryLogicalCodeEnum.SELF_FINANCE_QUOTA);
                                        SF_SINDH.getCandidatesList().remove(cn);
                                        sel.remove(sel.get(j));
                                    } else {
                                        CategoryAlg MERIT = selDiscipline.getCategoryMap().get(CategoryLogicalCodeEnum.GENERAL_MERIT_QUOTA);
                                        MERIT.getCandidatesList().remove(cn);
                                        sel.remove(sel.get(j));
                                    }
                                }
                            }//</editor-fold>

                        }

                        INNER_MERIT.addCandidate(cn);
                        int catCode = cn.addCurrentSelection(isMorning, d.getPosId(), CategoryEnum.GM_DUR_QUOTA.ordinal(), choiceNo, CategoryLogicalCodeEnum.GENERAL_MERIT_QUOTA.ordinal(), cnposId, INNER_MERIT, d, cposId,this);
                        releaseSeat(catCode, cn);

                        b = true;
                    }
                }//</editor-fold> 

                if (b) { // if selection of this candidate on MERIT BASIS,  then it will process next student
                    cn.getCategories().remove(CategoryEnum.GM_DUR_QUOTA);
                    status = choiceNo == 1 ? STATUS_FINAL_SELECTED : STATUS_PARTIAL_SHIFT_SELECTED;
                    break STATUS_LABEL;
                }
//                }
                //</editor-fold>

                //<editor-fold defaultstate="collapsed" desc="If Self Finance Morning">
                if (cn.isContainCategory(CategoryEnum.SFM_QUOTA) && cn.isCatNotAllocatedWithChoiceNo(isMorning, choiceNo, CategoryEnum.SFM_QUOTA.ordinal())) {
                    CategoryAlg SF_SINDH = d.getCategoryMap().get(CategoryLogicalCodeEnum.SELF_FINANCE_QUOTA);

                    if (SF_SINDH != null && SF_SINDH.isSeatsAvailable()) {
                        SF_SINDH.addCandidate(cn);
                        int catCode = cn.addCurrentSelection(isMorning, d.getPosId(), CategoryEnum.SFM_QUOTA.ordinal(), choiceNo, CategoryLogicalCodeEnum.SELF_FINANCE_QUOTA.ordinal(), cnposId, SF_SINDH, d, cposId,this);
                        releaseSeat(catCode, cn);
                        status = STATUS_PARTIAL_CHOICE_SELECTED;
                        cn.getCategories().remove(CategoryEnum.SFM_QUOTA);
                        break STATUS_LABEL;
                    }

                }//</editor-fold>
            } //</editor-fold>
        }

        return status;
    }

    public void processForBSCommerce() {
//        for (CandidateAlg cn : cList) {
//          /*think about it (release seats from selection of CandidateAlg)*/    
//        }
        
        for (CandidateAlg cn : cList) {
            int seat = cn.getSeatNo();
//            COMMERCE DIPLOMA
//            if(seat == 8363){
//                System.out.println("");
//            }
            boolean isCommerce = cn.getProgramGrp().toUpperCase().equals(GroupEnum.COMMERCE.toString().toUpperCase());
            boolean isCommerceDiploma = cn.getProgramGrp().toUpperCase().equals("COMMERCE DIPLOMA");
            if ( (!isCommerce) && (!isCommerceDiploma)  ) {
                continue;
            }
            CANDIDATE_LEVEL:
            {
                Map<Integer, Map<Boolean, List<Integer[]>>> choicesMap = cn.getChoices();
                Set<Integer> campusIds = choicesMap.keySet();
                if (campusIds == null) {
                    continue;
                }

                for (Integer campusId : campusIds) {
                    Map<Boolean, List<Integer[]>> shiftWiseChoices = choicesMap.get(campusId);
                    if (shiftWiseChoices == null) {
                        continue;
                    }

                    Map<Boolean, Map<Integer, List<DisciplineAlg>>> shiftWiseCposDiscipline = dMap.get(campusId);
                    boolean isMorning = true;

                    Map<Integer, List<DisciplineAlg>> cposDisciplineList = shiftWiseCposDiscipline.get(isMorning);
                    if (cposDisciplineList == null || cposDisciplineList.isEmpty()) {
                        break CANDIDATE_LEVEL;
                    }

                    List<Integer[]> choicesList = shiftWiseChoices.get(isMorning);
                    if (choicesList == null || choicesList.isEmpty()) {
                        break CANDIDATE_LEVEL;
                    }
                    // <editor-fold defaultstate="collapsed" desc="Choices Loop">  
                    for (Integer[] choice : choicesList) {
                        int posId = choice[0];
                        int cposId = choice[1];
                        int cnposId = choice[2];
                        int choiceNo = choice[3];

                        /*Program of study ID of BS(Commerce)*/
                        if (posId != 58) {
                            continue;
                        }
                        /**
                         * This code is use for to stop further selection of
                         * candidate on the basis of previous selection choice
                         * no.
                         */
                        Object[] preSelection = cn.getSelection();
                        if (preSelection != null) {
                            boolean preIsMorning = (boolean) preSelection[1];
                            int preChoiceNo = (int) preSelection[4];

                            if (isMorning == preIsMorning) {
                                if (preChoiceNo < choiceNo) {
                                    break CANDIDATE_LEVEL;
                                }
                            }
                        }
                        int selection = -1;

                        List<DisciplineAlg> dList = cposDisciplineList.get(cposId);
                        if (dList == null || dList.isEmpty()) {
                            continue;
                        }

                        /**
                         * This condition is true for all discipline except
                         * Computer Science Because Computer Science selection
                         * based on more than one group.
                         */
                        if (dList.size() == 1) {
                            DisciplineAlg d = dList.get(0);
                            if (cn.isPosAllocated(!isMorning, d.getPosId())) {// if pos already allocated then continue the next choice
                                continue;
                            }

                            /**
                             * Checking the prerequisite (candidate is
                             * applicable for selection in this descipline or
                             * not)
                             */
                            if (pt.getIsBachelor()) {                                                                               /* Changed by JK from Percentage  */

                                if (prerequisite.get(d.getPosId()) == null) {
                                    System.out.println("Preprequisite not found : pos" + d.getPosId());
                                }
                                if (!PrerequisiteLocater.bachalorPrerequisiteAlg(prerequisite.get(d.getPosId()), cn.getProgramId(), cn.getLastCredetialPercentage())) {
                                    continue;
                                }
                            } else {                                                                                                                                                                   /* Changed by JK from Percentage  */

                                if (!PrerequisiteLocater.masterPrerequisiteAlg(d.getPosId(), isMorning, cn.getDegreeYears(), cn.getProgramId(), cn.getOptionalSubjectIds(), prerequisite.get(d.getPosId()), cn.getLastCredetialPercentage())) {
                                    continue;
                                }
                            }

                            selection = selection(cn, choiceNo, d, isMorning, cnposId, cposId, campusId);
                            if(cn.getCurrentSelection() != null && (!cn.getCurrentSelection().isEmpty())  ){
                                List<int[]> current = cn.getCurrentSelection().get(isMorning);
                                int[] commerceSelection = current.get(0);
                                CategoryEnum en = CategoryEnum.values()[(Integer)commerceSelection[CandidateAlg.CurrentSelection.CAT]];
                                if(!cn.getCategories().contains(en)){
                                    cn.getCategories().add(en);
                                }
                            }
                            
                            break CANDIDATE_LEVEL;
                        }
                    }// </editor-fold>
                }

            }
        }

    }

//    private void clearSelectionAndReleaseSeats(CandidateAlg cn, List<DisciplineAlg> dList) {
//        Map<Boolean, List<int[]>> currentSelection = cn.getCurrentSelection();
//        if (currentSelection != null) {
//            List<int[]> cs = currentSelection.get(true);
//            if (cs != null && !cs.isEmpty()) {
//                for (int[] is : cs) {
//                    int previousChoiceNo = is[CandidateAlg.CurrentSelection.CHOICE_NO];
//                    int previousCat = is[CandidateAlg.CurrentSelection.CAT];
//                    int previousCposId = is[CandidateAlg.CurrentSelection.CPOS_ID];
//                    if(previousCat)
//                }
//            }
//        }
//    }
}
