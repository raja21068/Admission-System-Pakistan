package admission.controller.algorithm;

import admission.controller.JDBCDatabaseManager;
import admission.utils.ArrayToSingleton;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Yougeshwar
 */
public class PrerequisiteLocater {

    /**
     * @param posId
     * @param group
     * @return
     * @throws java.sql.SQLException
     */
    public static boolean bachalorPrerequisite(Integer posId, String group) throws SQLException {
        int countPrerequisite = JDBCDatabaseManager.getCountPrerequisite(posId);
        if (countPrerequisite <= 0) {
            return true;
        }

        return JDBCDatabaseManager.isPrerequisite(posId, group);
    }

    public static boolean bachalorPrerequisite(int posId, int programId) {
        try {
            int countPrerequisite = JDBCDatabaseManager.getCountPrerequisite(posId);
            if (countPrerequisite <= 0) {
                return true;
            }

            return JDBCDatabaseManager.isPrerequisite(posId, programId);
        } catch (SQLException ex) {
            Logger.getLogger(PrerequisiteLocater.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public static boolean bachalorPrerequisiteAlg(List<Object[]> prerequisiteList, int programId, float percentage) {
        if (prerequisiteList.size() <= 0) {
            return true;
        }
        for (Object[] ob : prerequisiteList) {
            int preProgramId = (int) ob[0];
            float prePercentage = (float) ob[2];
            if (preProgramId == programId && prePercentage <= percentage) {
                return true;
            }
        }

        return false;
    }

    /**
     * Work remaining
     *
     * @param posId
     * @param isMorning
     * @param degreePeriod
     * @param programId
     * @param subjectIds
     * @return
     * @throws java.sql.SQLException
     */
    public static boolean masterPrerequisite(int posId, boolean isMorning, int degreePeriod, String programId, String... subjectIds) throws SQLException {
        int countPrerequisite = JDBCDatabaseManager.getCountPrerequisite(posId);
        if (countPrerequisite <= 0) {
            return true;
        }
        subjectIds[0] = subjectIds[0].isEmpty() ? "0" : subjectIds[0];
        subjectIds[1] = subjectIds[1].isEmpty() ? "0" : subjectIds[1];
        subjectIds[2] = subjectIds[2].isEmpty() ? "0" : subjectIds[2];

        if (JDBCDatabaseManager.isPrerequisite(posId, subjectIds[0], subjectIds[1], subjectIds[2], programId)) {

//            return !isMorning || (degreePeriod <= 3);
            // 66 = MSc CS, 36 = BCS 3YR, 
            // 107 = Rural Development MSc
            // 64 = BIT 3YR
            return !isMorning || (posId == 66 && (programId.equals("36") || programId.equals("64")) ? false : (posId == 107) ? true : degreePeriod <= 3);
        } else {
            return false;
        }
    }

    public static boolean masterPrerequisite(int posId, boolean isMorning, int degreePeriod, int programId, List<Integer> subjectIds) {
        try {
            int countPrerequisite = JDBCDatabaseManager.getCountPrerequisite(posId);
            if (countPrerequisite <= 0) {
                return true;
            }

            if (JDBCDatabaseManager.isPrerequisite(posId, ArrayToSingleton.singleton(subjectIds), programId)) {
//            return !isMorning || (degreePeriod <= 3);
                // 66 = MSc CS, 36 = BCS 3YR,
                // 107 = Rural Development MSc
                // 64 = BIT 3YR
                return !isMorning || (posId == 66 && (programId == 36 || programId == 64) ? false : (posId == 107) ? true : degreePeriod <= 3);
            } else {
                return false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(PrerequisiteLocater.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;
    }

    public static boolean masterPrerequisiteAlg(int posId, boolean isMorning, int degreePeriod, int programId, List<Integer> subjectIds, List<Object[]> prerequisiteList, float percentage) {
        if (prerequisiteList == null || prerequisiteList.isEmpty()) {
            return true;
        }
        // 66 = MSc CS, 36 = BCS 3YR,
        // 107 = Rural Development MSc
        // 64 = BCIT 3YR
        for (Object[] ob : prerequisiteList) {
            int preProgramId = (int) ob[0];
            int preSubjectId = (int) ob[1];
            float prePercentage = (float) ob[2];
            boolean preIsNone = (boolean) ob[3];
            
            if (preProgramId == programId && prePercentage <= percentage) {
                if(preIsNone) return true;
                
                if (subjectIds == null) {
                    return true;
//                  !isMorning || (posId == 66 && (programId == 36 || programId == 64) ? false : (posId == 107) ? true : degreePeriod <= 3);
                } else {
                    for (Integer subjectId : subjectIds) {
                        if (preSubjectId == subjectId) {
                            return true;
//                          !isMorning || (posId == 66 && (programId == 36 || programId == 64) ? false : (posId == 107) ? true : degreePeriod <= 3);
                        }
                    }
                }
            }
        }

//        if (JDBCDatabaseManager.isPrerequisite(posId, ArrayToSingleton.singleton(subjectIds), programId)) {
////            return !isMorning || (degreePeriod <= 3);
//            // 66 = MSc CS, 36 = BCS 3YR,
//            // 107 = Rural Development MSc
//            // 64 = BCIT 3YR
//            return !isMorning || (posId == 66 && (programId == 36 || programId == 64) ? false : (posId == 107) ? true : degreePeriod <= 3);
//        } else {
        return false;
//        }
    }
}
