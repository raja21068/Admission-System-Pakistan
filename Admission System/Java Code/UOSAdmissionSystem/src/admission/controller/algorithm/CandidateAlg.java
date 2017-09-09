package admission.controller.algorithm;

import admission.enums.CategoryEnum;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Yougeshwar
 */
public class CandidateAlg {

    private int id;
    private int districtId;
    private boolean jurisdication;
    private int provinceCode;
    private int seatNo;
    private int gender;
    private int area;
    private int score;
    private float percentage;
    private int programId; // Last Education Group ID
    private String programGrp; // Last Education Group
    private int degreeYears;
    private float lastCredetialPercentage;
    private List<Integer> optionalSubjectIds;
    private List<CategoryEnum> categories;
    private Map<Integer, Map<Boolean, List<Integer[]>>> choices; /* [campusId, [isMorning, List<Integer[posId, cposId, cnposId, choiceNo]>]]*/

    private Object[] selection; /* [0campusId, 1isMorning, 2posId, 3cposgId, 4choiceNo, 5selCat, 6catLogicalCode, 7CAMPUS_PROGRAM_OF_STUDY_ID]*/
//    private Map<Integer, Map<Boolean, Object[]>> selection; /* [campusId, [isMorning, [posId, cposgId, choiceNo, selCat]]]*/

    private Map<Boolean, List<int[]>> currentSelection = new LinkedHashMap<>(); /* [[isMorning, [posId, cat, choiceNo,catLogicalOrdinal,cnposId,CampusCategoryId,CposgId,cposId]]]*/


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDistrictId() {
        return districtId;
    }

    public void setDistrictId(int districtId) {
        this.districtId = districtId;
    }

    public void setJurisdication(boolean jurisdication) {
        this.jurisdication = jurisdication;
    }

    public boolean isJurisdication() {
        return jurisdication;
    }

    public int getProvinceCode() {
        return provinceCode;
    }

    public void setProvinceCode(int provinceCode) {
        this.provinceCode = provinceCode;
    }

    public int getSeatNo() {
        return seatNo;
    }

    public void setSeatNo(int seatNo) {
        this.seatNo = seatNo;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public int getArea() {
        return area;
    }

    public void setArea(int area) {
        this.area = area;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public float getPercentage() {
        return percentage;
    }

    public void setPercentage(float percentage) {
        this.percentage = percentage;
    }

    public int getProgramId() {
        return programId;
    }

    public void setProgramId(int programId) {
        this.programId = programId;
    }

    public String getProgramGrp() {
        return programGrp;
    }

    public void setProgramGrp(String programGrp) {
        this.programGrp = programGrp;
    }

    public int getDegreeYears() {
        return degreeYears;
    }

    public void setDegreeYears(int degreeYears) {
        this.degreeYears = degreeYears;
    }

    public List<Integer> getOptionalSubjectIds() {
        return optionalSubjectIds;
    }

    public void setOptionalSubjectIds(List<Integer> optionalSubjectIds) {
        this.optionalSubjectIds = optionalSubjectIds;
    }

    public List<CategoryEnum> getCategories() {
        return categories;
    }

    public void setCategories(List<CategoryEnum> categories) {
        this.categories = categories;
    }

    public Map<Integer, Map<Boolean, List<Integer[]>>> getChoices() {
        return choices;
    }

    public void setChoices(Map<Integer, Map<Boolean, List<Integer[]>>> choices) {
        this.choices = choices;
    }

    public Object[] getSelection() {
        return selection;
    }

    public void setSelection(Object[] selection) {
        this.selection = selection;
    }

    public boolean isContainCategory(CategoryEnum cat) {
        if (categories == null) {
//            System.out.println(seatNo);
            return false;
        }
        return categories.contains(cat);
    }

    /*
     if returned value is -1 than it will not release seats from discipline
     otherwise returned value wiil be a category code and seat with will be released from discipline of that category  
     */
    public int addCurrentSelection(boolean isMorning, int posId, int catOrdinal, int choiceNo, int catLogicalOrdinal, int cnposId, CategoryAlg catAlg, DisciplineAlg dis, int cposId, AdmissionController ay) {

        List<int[]> list = currentSelection.get(isMorning);
        if (list == null) {
            list = new ArrayList<>();
            currentSelection.put(isMorning, list);
        }
        
        for (int i=0;i<list.size();i++) {
         int choiceNoCurrent = (Integer)list.get(i)[CurrentSelection.CHOICE_NO];
         int catOrdinalCurrent = (Integer)list.get(i)[CurrentSelection.CAT];
         int catLogicalOrdinalCurrent = (Integer)list.get(i)[CurrentSelection.CAT_LOGICAL_CODE];
         if(choiceNoCurrent>=choiceNo && catOrdinalCurrent == catOrdinal){
             if(ay.releaseSeatFromCurrent(isMorning, catLogicalOrdinalCurrent, list.get(i), this)){
                 list.remove(list.get(i));
                 break;
             };
         }
        }
        
        list.add(new int[]{posId, catOrdinal, choiceNo, catLogicalOrdinal, cnposId, catAlg.getCampusCategoryId(), dis.getCposgId(), cposId});

        // For releasing seats
        if (selection != null) {
            int preChoiceNo = (int) selection[4];
            int preSelCat = (int) selection[5];
            int preLogicalCatCode = (int) selection[6];

            if (choiceNo < preChoiceNo && catOrdinal == preSelCat) {
                return preLogicalCatCode;
            }
//            else if (choiceNo <= preChoiceNo && catOrdinal != preSelCat) {
//                return preLogicalCatCode;
//            }
        }
        return -1;
    }

    public boolean isCatNotAllocatedWithChoiceNo(boolean isMorning, int choiceNo, int cat) {
        if (selection != null) {
            int preSelCat = (int) selection[5];
            int preChoiceNo = (int) selection[4];
//            int catCode = (int)selection[6];
            if (preSelCat == cat && choiceNo >= preChoiceNo) { /*changed from == to  >= */
                return false;
            }
        }
        List<int[]> list = currentSelection.get(isMorning);
        if (list == null || list.isEmpty()) {
            return true; /* changed from false to true JK*/
        }

        for (int[] obs : currentSelection.get(isMorning)) {
            int catS = obs[CurrentSelection.CAT];
            int preChoiceNumber = obs[CurrentSelection.CHOICE_NO];
            if (catS == (cat) && preChoiceNumber<= choiceNo) {
                return false;
            }
        }
        return true;
    }

    public boolean isPosAllocated(boolean isMorning, int posId) {
        List<int[]> list = currentSelection.get(isMorning);
        if (list == null || list.isEmpty()) {
            return false;
        }

        for (int[] obs : list) {
            int posIdS = obs[0];
            if (posIdS == posId) {
                return true;
            }
        }
        return false;
    }

    public Map<Boolean, List<int[]>> getCurrentSelection() {
        return currentSelection;
    }

    public void setCurrentSelection(Map<Boolean, List<int[]>> currentSelection) {
        this.currentSelection = currentSelection;
    }

    public float getLastCredetialPercentage() {
        return lastCredetialPercentage;
    }

    public void setLastCredetialPercentage(float lastCredetialPercentage) {
        this.lastCredetialPercentage = lastCredetialPercentage;
    }

    public void emptyCurrentSelection() {
        currentSelection = new LinkedHashMap<>();
    }

    public static interface CurrentSelection {
//     [posId, cat, choiceNo,catLogicalOrdinal,cnposId,CampusCategoryId,CposgId,cposId]]]*/

        public int POS_ID = 0;
        public int CAT = 1;
        public int CHOICE_NO = 2;
        public int CAT_LOGICAL_CODE = 3;
        public int CNPOS_ID = 4;
        public int CAMPUS_CATEGORY_ID = 5;
        public int CPOSG_ID = 6;
        public int CPOS_ID = 7;
    }

    public static interface Selection {

        public static int SELECTION_CAMPUS_ID = 0;
        public static int SELECTION_IS_MORNING = 1;
        public static int SELECTION_POS_ID = 2;
        public static int SELECTION_CPOSG_ID = 3;
        public static int SELECTION_CHOICE_NO = 4;
        public static int SELECTION_SEL_CAT = 5;
        public static int SELECTION_CAT_LOGICAL_CODE = 6;
        public static int SELECTION_CPOS_ID = 7;
    }
}
