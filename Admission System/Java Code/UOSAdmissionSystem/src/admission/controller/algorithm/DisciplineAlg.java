package admission.controller.algorithm;

import admission.enums.CategoryLogicalCodeEnum;
import admission.enums.GroupEnum;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Yougeshwar
 */
public class DisciplineAlg {
    private int posId;
    private int cposId;
    private int cposgId;
    private int campusId;
    private GroupEnum cposGroup;
    private boolean isMorning;
    private boolean quotaOriented;
    private Map<CategoryLogicalCodeEnum, CategoryAlg> categoryMap = new LinkedHashMap<>();
    private List<Object[]> prerequisiteList = new ArrayList<>(); /* programId, subjectId, percentage, isNone */
    
    public DisciplineAlg() {
    }

    public int getPosId() {
        return posId;
    }

    public void setPosId(int posId) {
        this.posId = posId;
    }

    public int getCposId() {
        return cposId;
    }

    public void setCposId(int cposId) {
        this.cposId = cposId;
    }

    public int getCposgId() {
        return cposgId;
    }

    public void setCposgId(int cposgId) {
        this.cposgId = cposgId;
    }

    public int getCampusId() {
        return campusId;
    }

    public void setCampusId(int campusId) {
        this.campusId = campusId;
    }

    public GroupEnum getCposGroup() {
        return cposGroup;
    }

    public void setCposGroup(GroupEnum cposGroup) {
        this.cposGroup = cposGroup;
    }
    
    public boolean isMorning() {
        return isMorning;
    }

    public void setMorning(boolean morning) {
        this.isMorning = morning;
    }

    public void setQuotaOriented(boolean quotaOriented) {
        this.quotaOriented = quotaOriented;
    }

    public boolean isQuotaOriented() {
        return quotaOriented;
    }

    public Map<CategoryLogicalCodeEnum, CategoryAlg> getCategoryMap() {
        return categoryMap;
    }

    public void setCategoryMap(Map<CategoryLogicalCodeEnum, CategoryAlg> categoryList) {
        this.categoryMap = categoryList;
    }
    
    public List<Object[]> getPrerequisiteList() {
        return prerequisiteList;
    }

    public void setPrerequisiteList(List<Object[]> prerequisiteList) {
        this.prerequisiteList = prerequisiteList;
    }
    
}
