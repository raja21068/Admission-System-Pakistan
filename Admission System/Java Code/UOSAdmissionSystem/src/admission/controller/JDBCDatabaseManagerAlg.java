package admission.controller;

import admission.controller.algorithm.CandidateAlg;
import admission.controller.algorithm.CategoryAlg;
import admission.controller.algorithm.DisciplineAlg;
import admission.reports.beans.CredentialDetailJRBean;
import admission.enums.CategoryEnum;
import admission.enums.CategoryLogicalCodeEnum;
import admission.enums.DetailOfEnum;
import admission.enums.DurationEnum;
import admission.enums.GroupEnum;
import admission.enums.SemesterEnum;
import admission.model.AdmissionListDetails;
import admission.model.DistrictSeatDistribution;
import admission.model.ProgramOfStudy;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.lang.StringUtils;
import admission.utils.IConstant;
import java.sql.PreparedStatement;
import java.util.LinkedList;

/**
 *
 * @author Yougeshwar
 */
public class JDBCDatabaseManagerAlg {

    private static Connection con;
    private static final int SCORE_FAIL_LIMIT = 59;
    
    static {
        try {
            System.out.println("Loading...");
            init();
            IConstant.DB.STATUS = 1;
            System.out.println("Database loaded successfully.");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(JDBCDatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
            admission.utils.MessageBox.info(null, "Library not running");
            System.exit(0);
        } catch (SQLException ex) {
            Logger.getLogger(JDBCDatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
            admission.utils.MessageBox.info(null, "Database server not running");
            System.exit(0);
        }
    }

    private static void init() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        con = DriverManager.getConnection(IConstant.DB.URL, IConstant.DB.USERNAME, IConstant.DB.PASSWORD);
//        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/us_admission", "root", "Kasak_2005");
    }

    //New Algorthim Methods------------------------------------------------------------------------------------------------------------
    public static List<DisciplineAlg> getDisciplineAlgList(int shiftId, int campusId, int ptId, boolean isSepearated) throws SQLException {
        String sql = "SELECT POS.PROGRAM_OF_STUDY_ID AS POS_ID, CPOS.CAMPUS_PROGRAM_OF_STUDY_ID AS CPOS_ID, "
                + " CPOSG.CPOS_GROUP_ID AS CPOSG_ID, CPOS.CAMPUS_ID, S.SHIFT_ID, S.IS_MORNING, CPOS.IS_QUOTA_ORIENTED, CPOSG.GROUP_CODE "
                + " FROM PROGRAM P "
                + " INNER JOIN PROGRAM_OF_STUDY POS ON POS.PROGRAM_ID = P.PROGRAM_ID "
                + " INNER JOIN CAMPUS_PROGRAM_OF_STUDY CPOS ON POS.PROGRAM_OF_STUDY_ID = CPOS.PROGRAM_OF_STUDY_ID "
                + " INNER JOIN CPOS_GROUP CPOSG ON CPOSG.CAMPUS_PROGRAM_OF_STUDY_ID = CPOS.CAMPUS_PROGRAM_OF_STUDY_ID "
                + " INNER JOIN SHIFT S ON S.SHIFT_ID = CPOS.SHIFT_ID "
                + " WHERE S.SHIFT_ID = " + shiftId + " "
                + " AND CAMPUS_ID = " + campusId + " "
                + " AND P.PROGRAM_TYPE_ID = " + ptId
                + " AND CPOS.IS_SEPERATE = " + isSepearated;

        List<DisciplineAlg> list = new ArrayList<>();

        try (Statement st = con.createStatement();
                ResultSet rs = st.executeQuery(sql);) {

            while (rs.next()) {
                DisciplineAlg ob = new DisciplineAlg();

                ob.setPosId(rs.getInt("POS_ID"));
                ob.setCposId(rs.getInt("CPOS_ID"));
                ob.setCposgId(rs.getInt("CPOSG_ID"));
                ob.setCampusId(rs.getInt("CAMPUS_ID"));
                ob.setCposGroup(GroupEnum.values()[rs.getInt("GROUP_CODE")]);
                ob.setMorning(rs.getBoolean("IS_MORNING"));
                ob.setQuotaOriented(rs.getBoolean("IS_QUOTA_ORIENTED"));

                list.add(ob);
            }
            return list;
        }
    }

    public static List<Object[]> getProgramSubjectAlgList(int posId) throws SQLException {
        String sql = " SELECT PS.PROGRAM_ID,PS.SUBJECT_ID,P.PERCENTAGE,S.IS_NONE FROM PREREQUISITE P "
                + " INNER JOIN PROGRAM_SUBJECT PS ON P.PROGRAM_SUBJECT_ID = PS.PROGRAM_SUBJECT_ID "
                + " INNER JOIN SUBJECT S ON PS.SUBJECT_ID = S.SUBJECT_ID "
                + " WHERE P.PROGRAM_OF_STUDY_ID = " + posId;

        List<Object[]> list = new ArrayList<>();

        try (Statement st = con.createStatement();
                ResultSet rs = st.executeQuery(sql);) {

            while (rs.next()) {

                list.add(new Object[]{
                    rs.getInt("PROGRAM_ID"),
                    rs.getInt("SUBJECT_ID"),
                    rs.getFloat("PERCENTAGE"),
                    rs.getBoolean("IS_NONE"),});
            }
            return list;
        }
    }

    public static Map<CategoryLogicalCodeEnum, CategoryAlg> getDisciplineCategorySeatsAlgList(boolean isQuotaOriented, int ayId, int ptId, int cposgId) throws SQLException {
        String sql = "SELECT CC.CAMPUS_CATEGORY_ID, DCS.NUMBER_OF_SEATS, C.CODE "
                + "FROM DISCIPLINE_CATEGORY_SEATS DCS "
                + "INNER JOIN ADMISSION_SESSION AS ADS ON ADS.ADMISSION_SESSION_ID = DCS.ADMISSION_SESSION_ID "
                + "INNER JOIN CAMPUS_CATEGORY AS CC ON DCS.CAMPUS_CATEGORY_ID = CC.CAMPUS_CATEGORY_ID "
                + "INNER JOIN CATEGORY AS C ON CC.CATEGORY_ID = C.CATEGORY_ID "
                + "WHERE ADS.ADMISSION_YEAR_ID = " + ayId + " "
                + "AND ADS.PROGRAM_TYPE_ID = " + ptId + " "
                + "AND DCS.CPOS_GROUP_ID = " + cposgId;
        
//        if(cposgId == 50){
//            System.out.println("");
//        }
//        
        Map<CategoryLogicalCodeEnum, CategoryAlg> map = new HashMap<>();

        try (Statement st = con.createStatement();
                ResultSet rs = st.executeQuery(sql);) {

            while (rs.next()) {
                CategoryAlg ob = new CategoryAlg();

                ob.setCampusCategoryId(rs.getInt("CAMPUS_CATEGORY_ID"));
                ob.setTotalSeats(rs.getInt("NUMBER_OF_SEATS"));
                ob.setQuotaOriented(isQuotaOriented);
                CategoryLogicalCodeEnum code = CategoryLogicalCodeEnum.values()[rs.getInt("CODE")];

                // Consumed Seats
                int consumedSeats = getDisciplineCategoryConsumedSeatsAlg(ob.getCampusCategoryId(), ayId, cposgId);
                ob.setConsumedSeats(consumedSeats);

                map.put(code, ob);
            }
            return map;
        }
    }

    public static Map<Integer, int[]> getDistrictSeatsAlgList(int ayId, int shiftId) throws SQLException {
        String sql = "SELECT * "
                + "FROM DISTRICT_SEATS_DISTRIBUTION "
                + "WHERE ADMISSION_YEAR_ID = " + ayId + " "
                + "AND SHIFT_ID = " + shiftId;

        Map<Integer, int[]> map = new HashMap<>();

        try (Statement st = con.createStatement();
                ResultSet rs = st.executeQuery(sql);) {

            while (rs.next()) {
                map.put(rs.getInt("DISTRICT_ID"), new int[]{
                    rs.getInt("URBAN"),
                    rs.getInt("RURAL"),
                    rs.getInt("OTHER")
                });
            }
            return map;
        }
    }

    public static List<CandidateAlg> getCandidateAlgForFirstList(int ayId, int ptId, int campusId, String objectionCriterai) throws SQLException {
        String sql = "SELECT C.ADMISSION_YEAR_ID, C.PROGRAM_TYPE_ID, C.CANDIDATE_ID AS ID, C.DISTRICT_ID, PV.CODE AS PROVINCE_CODE, C.SEAT_NO, C.GENDER, C.AREA, "
                + "          C.TEST_SCORE AS SCORE, C.PERCENTAGE, C.YEARS_DEGREE, C.IS_OBJECTION,  "
                + "          (SELECT PROGRAM_ID FROM CREDENTIAL_DETAILS  "
                + "           WHERE CANDIDATE_ID = C.CANDIDATE_ID AND DETAIL_OF = (SELECT MAX(DETAIL_OF) FROM CREDENTIAL_DETAILS WHERE CANDIDATE_ID = C.CANDIDATE_ID) "
                + "          ) AS LAST_PROGRAM_ID, "
                + "         (SELECT PR_I.NAME FROM CREDENTIAL_DETAILS CD_I "
                + "          INNER JOIN PROGRAM PR_I ON PR_I.PROGRAM_ID = CD_I.PROGRAM_ID "
                + "          WHERE CD_I.CANDIDATE_ID = C.CANDIDATE_ID AND CD_I.DETAIL_OF = (SELECT MAX(DETAIL_OF) FROM CREDENTIAL_DETAILS WHERE CANDIDATE_ID = C.CANDIDATE_ID) "
                + "         ) AS PROGRAM_GROUP, "
                + "         (SELECT GROUP_CONCAT(SUBJECT_ID) FROM OPTIONAL_SUBJECT  "
                + "          WHERE CREDENTIAL_DETAILS_ID = (SELECT CREDENTIAL_DETAILS_ID FROM CREDENTIAL_DETAILS WHERE  "
                + "          CANDIDATE_ID = C.CANDIDATE_ID AND DETAIL_OF = (SELECT MAX(DETAIL_OF) FROM CREDENTIAL_DETAILS WHERE CANDIDATE_ID = C.CANDIDATE_ID)) "
                + "         ) AS OPTIONAL_SUBJECT, "
                + "         (SELECT GROUP_CONCAT(CODE) FROM APPLIED_CATEGORY WHERE CANDIDATE_ID = C.CANDIDATE_ID)"
                + "          AS APPLIED_CATEGORY, "
                + "         (SELECT IS_JURISDICTION FROM JURISDICTION J WHERE DISTRICT_ID = D.DISTRICT_ID AND J.CAMPUS_ID = " + campusId + ")"
                + "          AS IS_JURISDICTION "
                + "	FROM CANDIDATE C "
                + "	INNER JOIN DISTRICT D ON D.DISTRICT_ID = C.DISTRICT_ID "
                + "	INNER JOIN DIVISION DV ON DV.DIVISION_ID = D.DIVISION_ID "
                + "	INNER JOIN PROVINCE PV ON PV.PROVINCE_ID = DV.PROVINCE_ID "
                + "	WHERE "
                + "         C.TEST_SCORE > "+SCORE_FAIL_LIMIT+" "
                + "         AND C.IS_OBJECTION = FALSE"
                + "         AND C.ADMISSION_YEAR_ID = " + ayId
                + "         AND C.PROGRAM_TYPE_ID = " + ptId
                + "         AND C.CANDIDATE_ID IN (SELECT CANDIDATE_ID FROM APPLIED_CAMPUS WHERE CAMPUS_ID = " + campusId + ") "
                + "     ORDER BY PERCENTAGE DESC";

        List<CandidateAlg> list = new ArrayList<>();

        try (Statement st = con.createStatement();
                ResultSet rs = st.executeQuery(sql);) {

            while (rs.next()) {
                CandidateAlg ob = new CandidateAlg();

                ob.setId(rs.getInt("ID"));
                ob.setDistrictId(rs.getInt("DISTRICT_ID"));
                ob.setJurisdication(rs.getBoolean("IS_JURISDICTION"));
                ob.setProvinceCode(rs.getInt("PROVINCE_CODE"));
                ob.setSeatNo(rs.getInt("SEAT_NO"));
                ob.setGender(rs.getInt("GENDER"));
                ob.setArea(rs.getInt("AREA"));
                ob.setScore(rs.getInt("SCORE"));
                ob.setPercentage(rs.getFloat("PERCENTAGE"));
                ob.setProgramId(rs.getInt("LAST_PROGRAM_ID"));
                ob.setDegreeYears(rs.getInt("YEARS_DEGREE"));
                ob.setProgramGrp(rs.getString("PROGRAM_GROUP"));

                String opSub = rs.getString("OPTIONAL_SUBJECT");
                String appCat = rs.getString("APPLIED_CATEGORY");

                if (opSub != null && !opSub.isEmpty()) {
                    List<Integer> li = new ArrayList<>();
                    String[] array = StringUtils.split(opSub, ",");
                    for (String s : array) {
                        int id = Integer.parseInt(s);
                        li.add(id);
                    }
                    ob.setOptionalSubjectIds(li);
                }
                if (appCat != null && !appCat.isEmpty()) {
                    List<CategoryEnum> lc = new ArrayList<>();
                    String[] array = StringUtils.split(appCat, ",");
                    for (String s : array) {
                        int i = Integer.parseInt(s);
                        lc.add(CategoryEnum.values()[i]);
                    }
                    ob.setCategories(lc);
                }

                list.add(ob);
            }
            return list;
        }
    }

    public static List<CandidateAlg> getCandidateAlgList(int ayId, int ptId, int campusId, String objectionCriterai) throws SQLException {
//        String sql = "SELECT * FROM CANDIDATE_ALG_VIEW "
//                + "WHERE ADMISSION_YEAR_ID = " + ayId + " "
//                + "AND PROGRAM_TYPE_ID = " + ptId + " "
//                + "AND CAMPUS_ID IN (" + campusIds + ") "
//                + "" + objectCriteria
//                + " ORDER BY PERCENTAGE DESC";
        String sql = "SELECT C.ADMISSION_YEAR_ID, C.PROGRAM_TYPE_ID, C.CANDIDATE_ID AS ID, C.DISTRICT_ID, PV.CODE AS PROVINCE_CODE, C.SEAT_NO, C.GENDER, C.AREA, "
                + "          C.TEST_SCORE AS SCORE, C.PERCENTAGE, C.YEARS_DEGREE, C.IS_OBJECTION,  "
                + "          (SELECT PROGRAM_ID FROM CREDENTIAL_DETAILS  "
                + "           WHERE CANDIDATE_ID = C.CANDIDATE_ID AND DETAIL_OF = (SELECT MAX(DETAIL_OF) FROM CREDENTIAL_DETAILS WHERE CANDIDATE_ID = C.CANDIDATE_ID) "
                + "          ) AS LAST_PROGRAM_ID, "
                + "         (SELECT MY_PR.NAME FROM CREDENTIAL_DETAILS MY_CD INNER JOIN PROGRAM MY_PR ON MY_PR.PROGRAM_ID = MY_CD.PROGRAM_ID WHERE MY_CD.CANDIDATE_ID = C.CANDIDATE_ID AND MY_CD.DETAIL_OF = (SELECT MAX(DETAIL_OF) FROM CREDENTIAL_DETAILS CD_II WHERE CD_II.CANDIDATE_ID = C.CANDIDATE_ID) ) AS PROGRAM_GROUP,"
                + "         (SELECT GROUP_CONCAT(SUBJECT_ID) FROM OPTIONAL_SUBJECT  "
                + "          WHERE CREDENTIAL_DETAILS_ID = (SELECT CREDENTIAL_DETAILS_ID FROM CREDENTIAL_DETAILS WHERE  "
                + "          CANDIDATE_ID = C.CANDIDATE_ID AND DETAIL_OF = (SELECT MAX(DETAIL_OF) FROM CREDENTIAL_DETAILS WHERE CANDIDATE_ID = C.CANDIDATE_ID)) "
                + "         ) AS OPTIONAL_SUBJECT, "
                + "         (SELECT GROUP_CONCAT(CODE) FROM APPLIED_CATEGORY WHERE CANDIDATE_ID = C.CANDIDATE_ID)"
                + "          AS APPLIED_CATEGORY, "
                + "         (SELECT IS_JURISDICTION FROM JURISDICTION J WHERE DISTRICT_ID = D.DISTRICT_ID AND J.CAMPUS_ID = " + campusId + ")"
                + "          AS IS_JURISDICTION "
                + "	FROM CANDIDATE C "
                + "	INNER JOIN DISTRICT D ON D.DISTRICT_ID = C.DISTRICT_ID "
                + "	INNER JOIN DIVISION DV ON DV.DIVISION_ID = D.DIVISION_ID "
                + "	INNER JOIN PROVINCE PV ON PV.PROVINCE_ID = DV.PROVINCE_ID "
                + "	LEFT JOIN ADMISSION_LIST_DETAILS ALD ON C.CANDIDATE_ID = ALD.CANDIDATE_ID "
                + "     LEFT JOIN ADMISSION_LIST AL ON AL.ADMISSION_LIST_ID = ALD.ADMISSION_LIST_ID "
                + "	WHERE "
                + "           (  ALD.ADMISSION_LIST_DETAILS_ID IS NULL OR " + campusId + " NOT IN ( SELECT AL_I.CAMPUS_ID FROM CANDIDATE C_I INNER JOIN ADMISSION_LIST_DETAILS ALD_I ON ALD_I.CANDIDATE_ID  = C_I.CANDIDATE_ID INNER JOIN ADMISSION_LIST AL_I ON AL_I.ADMISSION_LIST_ID = ALD_I.ADMISSION_LIST_ID WHERE C_I.CANDIDATE_ID = C.CANDIDATE_ID ) )"
                + "	AND C.TEST_SCORE > "+SCORE_FAIL_LIMIT+" "
                + "     AND C.IS_OBJECTION = FALSE"
                + "	AND C.ADMISSION_YEAR_ID = " + ayId
                + "	AND C.PROGRAM_TYPE_ID = " + ptId
                + "	AND C.CANDIDATE_ID IN (SELECT CANDIDATE_ID FROM APPLIED_CAMPUS WHERE CAMPUS_ID = " + campusId + ") "
                + " "
                + "	UNION "
                + " "
                + "	SELECT C.ADMISSION_YEAR_ID, C.PROGRAM_TYPE_ID, C.CANDIDATE_ID AS ID, C.DISTRICT_ID, PV.CODE AS PROVINCE_CODE, C.SEAT_NO, C.GENDER, C.AREA, "
                + "            C.TEST_SCORE AS SCORE, C.PERCENTAGE, C.YEARS_DEGREE, C.IS_OBJECTION,  "
                + "            (SELECT PROGRAM_ID FROM CREDENTIAL_DETAILS  "
                + "             WHERE CANDIDATE_ID = C.CANDIDATE_ID AND DETAIL_OF = (SELECT MAX(DETAIL_OF) FROM CREDENTIAL_DETAILS WHERE CANDIDATE_ID = C.CANDIDATE_ID) "
                + "             ) AS LAST_PROGRAM_ID, "
                + "              (SELECT MY_PR.NAME FROM CREDENTIAL_DETAILS MY_CD INNER JOIN PROGRAM MY_PR ON MY_PR.PROGRAM_ID = MY_CD.PROGRAM_ID WHERE MY_CD.CANDIDATE_ID = C.CANDIDATE_ID AND MY_CD.DETAIL_OF = (SELECT MAX(DETAIL_OF) FROM CREDENTIAL_DETAILS CD_II WHERE CD_II.CANDIDATE_ID = C.CANDIDATE_ID) ) AS PROGRAM_GROUP,"
                + "             (SELECT GROUP_CONCAT(SUBJECT_ID) FROM OPTIONAL_SUBJECT  "
                + "              WHERE CREDENTIAL_DETAILS_ID = (SELECT CREDENTIAL_DETAILS_ID FROM CREDENTIAL_DETAILS WHERE  "
                + "              CANDIDATE_ID = C.CANDIDATE_ID AND DETAIL_OF = (SELECT MAX(DETAIL_OF) FROM CREDENTIAL_DETAILS WHERE CANDIDATE_ID = C.CANDIDATE_ID)) "
                + "             ) AS OPTIONAL_SUBJECT, "
                + "             (SELECT GROUP_CONCAT(CODE) FROM APPLIED_CATEGORY WHERE CANDIDATE_ID = C.CANDIDATE_ID AND CODE NOT IN (SELECT SEL_CAT FROM ADMISSION_LIST_DETAILS MY_ALD INNER JOIN ADMISSION_LIST MY_AL ON MY_AL.ADMISSION_LIST_ID= MY_ALD.ADMISSION_LIST_ID WHERE MY_ALD.CANDIDATE_ID = C.CANDIDATE_ID AND ACTIVE = FALSE AND (SEL_CAT = 1 OR  SEL_CAT = 2) AND MY_AL.CAMPUS_ID = " + campusId + " ))"
                + "              AS APPLIED_CATEGORY, "
                + "             (SELECT IS_JURISDICTION FROM JURISDICTION J WHERE DISTRICT_ID = D.DISTRICT_ID AND J.CAMPUS_ID = " + campusId + ") "
                + "              AS IS_JURISDICTION "
                + "	FROM CANDIDATE C "
                + "	INNER JOIN DISTRICT D ON D.DISTRICT_ID = C.DISTRICT_ID "
                + "	INNER JOIN JURISDICTION J ON J.DISTRICT_ID = D.DISTRICT_ID "
                + "	INNER JOIN DIVISION DV ON DV.DIVISION_ID = D.DIVISION_ID "
                + "	INNER JOIN PROVINCE PV ON PV.PROVINCE_ID = DV.PROVINCE_ID "
                + "	INNER JOIN ADMISSION_LIST_DETAILS ALD ON ALD.CANDIDATE_ID = C.CANDIDATE_ID "
                + "	INNER JOIN PART_REGISTRY PR ON PR.ADMISSION_LIST_DETAILS_ID = ALD.ADMISSION_LIST_DETAILS_ID "
                + "	WHERE ALD.ADMISSION_LIST_DETAILS_ID IS NOT NULL "
                + "	AND ALD.ADMISSION_LIST_DETAILS_ID  IN  "
                + "                                         ( "
                + "                                          SELECT ADMISSION_LIST_DETAILS_ID FROM PART_REGISTRY AS PR  "
                + "                                          WHERE PR.TYPE <> 1  "
                + "                                          AND ADMISSION_LIST_DETAILS_ID IN ( "
                + "                                                                         SELECT ADMISSION_LIST_DETAILS_ID FROM ADMISSION_LIST_DETAILS AS ALD "
                + "                                                                         INNER JOIN candidate_program_of_study AS CNPOS_I ON ALD.candidate_program_of_study_id = CNPOS_I.candidate_program_of_study_id"
                + "                                                                         WHERE ALD.ACTIVE = 1 AND CNPOS_I.choice_no > 1 "
                + "                                                                          )   "
                + "                                         OR ADMISSION_LIST_DETAILS_ID NOT IN ( "
                + "                                                 SELECT ADMISSION_LIST_DETAILS_ID FROM ADMISSION_LIST_DETAILS AS ALD "
                + "                                                 INNER JOIN candidate_program_of_study AS CNPOS_I ON ALD.candidate_program_of_study_id = CNPOS_I.candidate_program_of_study_id  "
                + "                                                 INNER JOIN CAMPUS_PROGRAM_OF_STUDY AS CPOS_I ON CPOS_I.CAMPUS_program_of_study_id = CNPOS_I.CAMPUS_program_of_study_id  "
                + "                                                 INNER JOIN SHIFT S ON S.shift_id = CPOS_I.shift_id "
                + "                                                 WHERE S.is_morning = TRUE AND CNPOS_I.CHOICE_NO = 1 AND ALD.SEL_CAT IN (0,3,4,6) AND CPOS_I.CAMPUS_ID = "+ campusId
                + "                                                             )   "
                + "                                         ) "
                + "	AND C.TEST_SCORE > "+SCORE_FAIL_LIMIT+" "
                + "	AND C.ADMISSION_YEAR_ID = " + ayId
                + "	AND C.PROGRAM_TYPE_ID = " + ptId
                + "	AND C.CANDIDATE_ID IN (SELECT CANDIDATE_ID FROM APPLIED_CAMPUS WHERE CAMPUS_ID = " + campusId + ") "
                + "     AND C.CANDIDATE_ID NOT IN (SELECT CN_I.CANDIDATE_ID FROM CANDIDATE CN_I INNER JOIN ADMISSION_LIST_DETAILS ALD_I ON ALD_I.CANDIDATE_ID = CN_I.CANDIDATE_ID INNER JOIN CANDIDATE_PROGRAM_OF_STUDY CNPOS_I ON CNPOS_I.CANDIDATE_PROGRAM_OF_STUDY_ID = ALD_I.CANDIDATE_PROGRAM_OF_STUDY_ID INNER JOIN CAMPUS_PROGRAM_OF_STUDY CPOS_I ON CPOS_I.CAMPUS_PROGRAM_OF_STUDY_ID = CNPOS_I.CAMPUS_PROGRAM_OF_STUDY_ID " +
"                            AND CNPOS_I.CHOICE_NO = 1 " +
"                            AND CPOS_I.CAMPUS_ID =  " + campusId +
"                            AND ALD_I.SEL_CAT IN (0,3,4,6)) "
                + " AND C.CANDIDATE_ID NOT IN (SELECT C_I.CANDIDATE_ID FROM CANDIDATE C_I " +
                "       WHERE " +
                "       PROGRAM_TYPE_ID  = " + ptId +
                "       AND ADMISSION_YEAR_ID =  " + ayId +
                " AND 0=(SELECT COUNT(ALD_I.ADMISSION_LIST_DETAILS_ID) FROM ADMISSION_LIST_DETAILS ALD_I " +
                "       INNER JOIN CPOS_GROUP CPOSG_I ON CPOSG_I.CPOS_GROUP_ID = ALD_I.CPOS_GROUP_ID " +
                "       INNER JOIN CAMPUS_PROGRAM_OF_STUDY CPOS_I ON CPOS_I.CAMPUS_PROGRAM_OF_STUDY_ID = CPOSG_I.CAMPUS_PROGRAM_OF_STUDY_ID " +
                "       WHERE ALD_I.CANDIDATE_ID = C_I.CANDIDATE_ID AND ACTIVE=TRUE AND CPOS_I.CAMPUS_ID = "+ campusId +") " +
                " AND 0< (SELECT COUNT(ALD_I.ADMISSION_LIST_DETAILS_ID) FROM ADMISSION_LIST_DETAILS ALD_I " +
                "        INNER JOIN CPOS_GROUP CPOSG_I ON CPOSG_I.CPOS_GROUP_ID = ALD_I.CPOS_GROUP_ID " +
                "        INNER JOIN CAMPUS_PROGRAM_OF_STUDY CPOS_I ON CPOS_I.CAMPUS_PROGRAM_OF_STUDY_ID = CPOSG_I.CAMPUS_PROGRAM_OF_STUDY_ID " +
                "        WHERE ALD_I.CANDIDATE_ID = C_I.CANDIDATE_ID AND CPOS_I.CAMPUS_ID = "+ campusId +")"
                + " )"
                /* if any candidate has not still selected on MERIT (VK)*/
                + "	UNION "
                + " "
                + "	SELECT C.ADMISSION_YEAR_ID, C.PROGRAM_TYPE_ID, C.CANDIDATE_ID AS ID, C.DISTRICT_ID, PV.CODE AS PROVINCE_CODE, C.SEAT_NO, C.GENDER, C.AREA, "
                + "            C.TEST_SCORE AS SCORE, C.PERCENTAGE, C.YEARS_DEGREE, C.IS_OBJECTION,  "
                + "            (SELECT PROGRAM_ID FROM CREDENTIAL_DETAILS  "
                + "             WHERE CANDIDATE_ID = C.CANDIDATE_ID AND DETAIL_OF = (SELECT MAX(DETAIL_OF) FROM CREDENTIAL_DETAILS WHERE CANDIDATE_ID = C.CANDIDATE_ID) "
                + "             ) AS LAST_PROGRAM_ID, "
                + "              (SELECT MY_PR.NAME FROM CREDENTIAL_DETAILS MY_CD INNER JOIN PROGRAM MY_PR ON MY_PR.PROGRAM_ID = MY_CD.PROGRAM_ID WHERE MY_CD.CANDIDATE_ID = C.CANDIDATE_ID AND MY_CD.DETAIL_OF = (SELECT MAX(DETAIL_OF) FROM CREDENTIAL_DETAILS CD_II WHERE CD_II.CANDIDATE_ID = C.CANDIDATE_ID) ) AS PROGRAM_GROUP,"
                + "             (SELECT GROUP_CONCAT(SUBJECT_ID) FROM OPTIONAL_SUBJECT  "
                + "              WHERE CREDENTIAL_DETAILS_ID = (SELECT CREDENTIAL_DETAILS_ID FROM CREDENTIAL_DETAILS WHERE  "
                + "              CANDIDATE_ID = C.CANDIDATE_ID AND DETAIL_OF = (SELECT MAX(DETAIL_OF) FROM CREDENTIAL_DETAILS WHERE CANDIDATE_ID = C.CANDIDATE_ID)) "
                + "             ) AS OPTIONAL_SUBJECT, "
                + "             (SELECT GROUP_CONCAT(CODE) FROM APPLIED_CATEGORY WHERE CANDIDATE_ID = C.CANDIDATE_ID AND CODE NOT IN (SELECT SEL_CAT FROM ADMISSION_LIST_DETAILS MY_ALD INNER JOIN ADMISSION_LIST MY_AL ON MY_AL.ADMISSION_LIST_ID= MY_ALD.ADMISSION_LIST_ID WHERE MY_ALD.CANDIDATE_ID = C.CANDIDATE_ID AND ACTIVE = FALSE AND (SEL_CAT = 1 OR  SEL_CAT = 2) AND MY_AL.CAMPUS_ID =" + campusId + " ))"
                + "              AS APPLIED_CATEGORY, "
                + "             (SELECT IS_JURISDICTION FROM JURISDICTION J WHERE DISTRICT_ID = D.DISTRICT_ID AND J.CAMPUS_ID = " + campusId + ") "
                + "              AS IS_JURISDICTION "
                + "	FROM CANDIDATE C "
                + "	INNER JOIN DISTRICT D ON D.DISTRICT_ID = C.DISTRICT_ID "
                + "	INNER JOIN JURISDICTION J ON J.DISTRICT_ID = D.DISTRICT_ID "
                + "	INNER JOIN DIVISION DV ON DV.DIVISION_ID = D.DIVISION_ID "
                + "	INNER JOIN PROVINCE PV ON PV.PROVINCE_ID = DV.PROVINCE_ID "
                + "	INNER JOIN ADMISSION_LIST_DETAILS ALD ON ALD.CANDIDATE_ID = C.CANDIDATE_ID "
                + "	WHERE ALD.ADMISSION_LIST_DETAILS_ID IS NOT NULL "
                + "     AND C.CANDIDATE_ID IN (SELECT DISTINCT(CN.CANDIDATE_ID) FROM CANDIDATE CN " +
                "  INNER JOIN ADMISSION_LIST_DETAILS ALD ON ALD.CANDIDATE_ID = CN.CANDIDATE_ID " 
                + " WHERE CN.ADMISSION_YEAR_ID = "+ ayId +" AND CN.PROGRAM_TYPE_ID = "+ ptId 
                + " AND 0 = ( SELECT COUNT(ALD_I.SEL_CAT) FROM ADMISSION_LIST_DETAILS ALD_I WHERE ALD_I.CANDIDATE_ID = CN.CANDIDATE_ID AND ALD_I.SEL_CAT IN (0,3,4,6))) "
                + "	AND C.TEST_SCORE > "+SCORE_FAIL_LIMIT+" "
                + "	AND C.CANDIDATE_ID NOT IN (SELECT ALD_I.CANDIDATE_ID FROM PART_REGISTRY PR_I INNER JOIN ADMISSION_LIST_DETAILS ALD_I ON ALD_I.ADMISSION_LIST_DETAILS_ID = PR_I.ADMISSION_LIST_DETAILS_ID WHERE PR_I.TYPE = 1)"
                + "     AND C.ADMISSION_YEAR_ID = " + ayId
                + "	AND C.PROGRAM_TYPE_ID = " + ptId
                + "	AND C.CANDIDATE_ID IN (SELECT CANDIDATE_ID FROM APPLIED_CAMPUS WHERE CAMPUS_ID = " + campusId + ") "                
                + "	ORDER BY PERCENTAGE DESC";

        List<CandidateAlg> list = new ArrayList<>();

        try (Statement st = con.createStatement();
                ResultSet rs = st.executeQuery(sql);) {

            while (rs.next()) {
                CandidateAlg ob = new CandidateAlg();

                ob.setId(rs.getInt("ID"));
                ob.setDistrictId(rs.getInt("DISTRICT_ID"));
                ob.setJurisdication(rs.getBoolean("IS_JURISDICTION"));
                ob.setProvinceCode(rs.getInt("PROVINCE_CODE"));
                ob.setSeatNo(rs.getInt("SEAT_NO"));
                ob.setGender(rs.getInt("GENDER"));
                ob.setArea(rs.getInt("AREA"));
                ob.setScore(rs.getInt("SCORE"));
                ob.setPercentage(rs.getFloat("PERCENTAGE"));
                ob.setProgramId(rs.getInt("LAST_PROGRAM_ID"));
                ob.setDegreeYears(rs.getInt("YEARS_DEGREE"));
                ob.setProgramGrp(rs.getString("PROGRAM_GROUP"));

                String opSub = rs.getString("OPTIONAL_SUBJECT");
                String appCat = rs.getString("APPLIED_CATEGORY");

                if (opSub != null && !opSub.isEmpty()) {
                    List<Integer> li = new ArrayList<>();
                    String[] array = StringUtils.split(opSub, ",");
                    for (String s : array) {
                        int id = Integer.parseInt(s);
                        li.add(id);
                    }
                    ob.setOptionalSubjectIds(li);
                }
                if (appCat != null && !appCat.isEmpty()) {
                    List<CategoryEnum> lc = new ArrayList<>();
                    String[] array = StringUtils.split(appCat, ",");
                    for (String s : array) {
                        int i = Integer.parseInt(s);
                        lc.add(CategoryEnum.values()[i]);
                    }
                    ob.setCategories(lc);
                }

                list.add(ob);
            }
            return list;
        }
    }

    public static int getDisciplineCategoryConsumedSeatsAlg(int campusCategoryId, int admissionYearId, int cposGroupId) throws SQLException {
        String sql = "SELECT COUNT(DISTINCT(ald.admission_list_details_id)) AS total_consumed FROM admission_list_details ald "
                + "    INNER JOIN admission_list al ON al.admission_list_id = ald.admission_list_id "
                + "    INNER JOIN admission_session ads ON ads.admission_session_id = al.admission_session_id "
                + "    INNER JOIN candidate c ON c.candidate_id = ald.candidate_id "
                + "    INNER JOIN candidate_program_of_study cnpos ON cnpos.candidate_id = ald.candidate_id "
                + "    WHERE ald.cpos_group_id = " + cposGroupId + " "
                + "    AND ald.campus_category_id = " + campusCategoryId + " "
                + "    AND c.admission_year_id = " + admissionYearId + " "
                + "    AND ald.admission_list_details_id IN (SELECT admission_list_details_id FROM part_registry) ";
//        System.out.println(sql);
        try (Statement st = con.createStatement();
                ResultSet rs = st.executeQuery(sql);) {

            int seats = 0;
            if (rs.next()) {
                seats = rs.getInt("total_consumed");
            }

            return seats;
        }
    }

    public static List<DistrictSeatDistribution> getDistrictSeatDistributionAlg(int campusId, int admissionYearId, boolean isJurisdiction) throws SQLException {
        String sql = "SELECT * FROM district_seat_distribution dsd "
                + "INNER JOIN jurisdiction j ON dsd.jurisdiction_id = j.jurisdiction_id "
                + "INNER JOIN admission_year ay ON dsd.admission_year_id = ay.admission_year_id "
                + "INNER JOIN campus c ON j.campus_id = c.campus_id "
                + "INNER JOIN district d ON d.district_id = j.district_id "
                + "WHERE ay.admission_year_id = " + admissionYearId + " "
                + "AND c.campus_id = " + campusId + " "
                + "AND j.is_jurisdiction = " + isJurisdiction + " "
                + "ORDER BY d.name";

//        System.out.println(sql);
        try (Statement st = con.createStatement();
                ResultSet rs = st.executeQuery(sql);) {
            List<DistrictSeatDistribution> list = new ArrayList<>();

            while (rs.next()) {
                DistrictSeatDistribution dsd = new DistrictSeatDistribution();

                dsd.setAdmissionYearId(rs.getInt("dsd.admission_year_id"));
                dsd.setDistrictId(rs.getInt("j.district_id"));
                dsd.setDistrictSeatDistributionId(rs.getInt("dsd.district_seat_distribution_id"));
                dsd.setJurisdictionId(rs.getInt("dsd.jurisdiction_id"));
                dsd.setRemarks(rs.getString("dsd.remarks"));
                dsd.setRural(rs.getInt("dsd.rural"));
                dsd.setUrban(rs.getInt("dsd.urban"));
                dsd.setOther(rs.getInt("dsd.other"));
//                dsd.setShiftId(rs.getInt("dsd.shift_id"));

                list.add(dsd);
            }

            return list;
        }
    }

    public static Map<Integer, Map<Boolean, List<Integer[]>>> getCandidateChoicesAlg(int candidateId, int campusId, boolean isSeparate) throws SQLException {
        String sql = "SELECT CNPOS.CANDIDATE_ID as ID, CM.CAMPUS_ID, S.NAME AS SHIFT, "
                + "	GROUP_CONCAT(CNPOS.CHOICE_NO order by CNPOS.CHOICE_NO) AS CHOICES_NO,  "
                + "	GROUP_CONCAT(CPOS.PROGRAM_OF_STUDY_ID order by CNPOS.CHOICE_NO) AS POS_IDS, "
                + "	GROUP_CONCAT(CPOS.campus_program_of_study_id order by CNPOS.CHOICE_NO) AS CPOS_IDS, "
                + "	GROUP_CONCAT(CNPOS.candidate_program_of_study_id order by CNPOS.CHOICE_NO) AS CNPOS_IDS, "
                + "	S.IS_MORNING, CM.DISPLAY_ORDER "
                + "	FROM CANDIDATE_PROGRAM_OF_STUDY AS CNPOS "
                + "	INNER JOIN CAMPUS_PROGRAM_OF_STUDY AS CPOS ON CNPOS.CAMPUS_PROGRAM_OF_STUDY_ID = CPOS.CAMPUS_PROGRAM_OF_STUDY_ID "
                + "	INNER JOIN CAMPUS AS CM ON CM.CAMPUS_ID = CPOS.CAMPUS_ID "
                + "	INNER JOIN SHIFT AS S ON S.SHIFT_ID = CPOS.SHIFT_ID "
                + "	WHERE CNPOS.candidate_id = " + candidateId + " "
                + "     AND CM.CAMPUS_ID = " + campusId
                + "     AND CPOS.is_seperate = " + isSeparate
                + "      AND (CNPOS.is_recommend is NULL or CNPOS.is_recommend = TRUE) "
                + "	GROUP BY CNPOS.CANDIDATE_ID, S.IS_MORNING  "
                + "     ORDER BY CM.DISPLAY_ORDER, S.IS_MORNING DESC";
//        System.out.println(candidateId);
        try (Statement st = con.createStatement();
                ResultSet rs = st.executeQuery(sql);) {
            /* [campusId, [isMorning, List<Integer[posId, cposId, cnposId, choiceNo]>]]*/
            Map<Integer, Map<Boolean, List<Integer[]>>> choicesMap = new LinkedHashMap<>();
            Map<Boolean, List<Integer[]>> innerMap = new LinkedHashMap<>();
            while (rs.next()) {
                boolean isMorning = rs.getBoolean("IS_MORNING");
                String s1 = rs.getString("CHOICES_NO");
                String s2 = rs.getString("POS_IDS");
                String s3 = rs.getString("CPOS_IDS");
                String s4 = rs.getString("CNPOS_IDS");
                String[] choices = s1.split(",");
                String[] posIds = s2.split(",");
                String[] cposIds = s3.split(",");
                String[] cnposIds = s4.split(",");
                List<Integer[]> list = new ArrayList<>();
                for (int i = 0; i < choices.length; i++) {
                    int choiceNo = Integer.parseInt(choices[i]);
                    int posId = Integer.parseInt(posIds[i]);
                    int cposId = Integer.parseInt(cposIds[i]);
                    int cnposId = Integer.parseInt(cnposIds[i]);
                    list.add(new Integer[]{posId, cposId, cnposId, choiceNo});
                }
                innerMap.put(isMorning, list);
//                choicesMap.put(campusId, innerMap);
            }
            choicesMap.put(campusId, innerMap);   // put the item after getting all shift by JK
            return choicesMap;
        }
    }

    public static Object[] getCandidateActiveSelectionAlg(int candidateId, int campusId) throws SQLException {
        String sql = "SELECT CPOS.CAMPUS_ID, "
                + "		SH.IS_MORNING, "
                + "		CPOS.PROGRAM_OF_STUDY_ID, "
                + "		ALD.CPOS_GROUP_ID, "
                + "		CNPOS.CHOICE_NO, "
                + "		ALD.SEL_CAT,"
                + "             CAT.CODE,"
                + "    		CPOS.CAMPUS_PROGRAM_OF_STUDY_ID "
                + " FROM ADMISSION_LIST_DETAILS AS ALD "
                + " INNER JOIN CANDIDATE_PROGRAM_OF_STUDY AS CNPOS  ON CNPOS.candidate_program_of_study_id = ALD.candidate_program_of_study_id "
                + " INNER JOIN CAMPUS_PROGRAM_OF_STUDY AS CPOS ON CPOS.CAMPUS_PROGRAM_OF_STUDY_ID = CNPOS.CAMPUS_PROGRAM_OF_STUDY_ID "
                + " INNER JOIN SHIFT AS SH ON SH.SHIFT_ID = CPOS.SHIFT_ID "
                + " INNER JOIN CAMPUS_CATEGORY CCAT ON CCAT.CAMPUS_CATEGORY_ID  = ALD.CAMPUS_CATEGORY_ID "
                + " INNER JOIN CATEGORY CAT ON CAT.CATEGORY_ID  = CCAT.CATEGORY_ID "
                + " WHERE "
                + " ALD.CANDIDATE_ID = " + candidateId + " "
                + " AND ALD.ACTIVE = TRUE "
                + " AND ALD.ADMISSION_LIST_DETAILS_ID IN ( SELECT ADMISSION_LIST_DETAILS_ID FROM PART_REGISTRY) "
                + " AND CPOS.CAMPUS_ID = " + campusId
                +"   ORDER BY ALD.ADMISSION_LIST_DETAILS_ID DESC "; 

        try (Statement st = con.createStatement();
                ResultSet rs = st.executeQuery(sql);) {
            Object[] selection = null;

            if (rs.next()) {

                selection = new Object[]{
                    rs.getInt("CAMPUS_ID"),
                    rs.getBoolean("IS_MORNING"),
                    rs.getInt("PROGRAM_OF_STUDY_ID"),
                    rs.getInt("CPOS_GROUP_ID"),
                    rs.getInt("CHOICE_NO"),
                    rs.getInt("SEL_CAT"),
                    rs.getInt("CODE"),
                    rs.getInt("CAMPUS_PROGRAM_OF_STUDY_ID"),};
            }
            return selection;
        }
    }

    public static Map<Integer, List<Object[]>> getPrerequisite(int ptId) throws SQLException {
        String sql = "select pre.program_of_study_id as pos_id, ps.program_id, ps.subject_id, pre.percentage"
                + ",sb.is_none  "
                + " from prerequisite pre "
                + " inner join program_subject ps on ps.program_subject_id = pre.program_subject_id "
                + " inner join program_of_study pos on pos.program_of_study_id = pre.program_of_study_id "
                + " inner join program pr2 on pr2.program_id = pos.program_id "
                + " inner join program pr on pr.program_id = ps.program_id "
                + "                inner join subject sb on ps.subject_id = sb.subject_id"
                + " where pr2.program_type_id = " + ptId // variable was not concated JK
                + " order by pre.program_of_study_id";
        //changed the query JK
        // added is_none JK    
        try (Statement st = con.createStatement();
                ResultSet rs = st.executeQuery(sql);) {
            Map<Integer, List<Object[]>> map = new LinkedHashMap<>();
            while (rs.next()) {
                int posId = rs.getInt("pos_id");
                int programId = rs.getInt("program_id");
                int subjectId = rs.getInt("subject_id");
                float perc = rs.getFloat("percentage");
                boolean isNone = rs.getBoolean("is_none");

                List<Object[]> list = map.get(posId);
                if (list == null) {
                    list = new ArrayList<>();
                    map.put(posId, list);
                }
                list.add(new Object[]{
                    programId,
                    subjectId,
                    perc,
                    isNone
                });
            }
            return map;
        }
    }

    /* [seatno, candidate_name, fathers_name, percentage, program_name, program_remarks, program_of_study_name, district_name, is_jurisdiction] */
    public static Object[] getCandidateAndSelectionInfoAlg(int candidateId, int posId, int campusId) throws SQLException {
        String sql = "SELECT CN.SEAT_NO, "
                + "	   CN.NAME AS CANDIDATE_NAME, "
                + "	   CN.FATHERS_NAME, "
                + "	   CN.PERCENTAGE, "
                + "	   P.NAME AS P_NAME, "
                + "	   P.REMARKS AS P_REMARKS, "
                + "	   POS.NAME AS POS_NAME, "
                + "	   CN.YEARS_DEGREE, "
                + "	   D.NAME AS DISTRICT_NAME, "
                + "	   J.IS_JURISDICTION,"
                + "        CN.DEDUCTION_MARKS, "
                + "        CN.OBJECTION_REMARKS,"
                + "        CN.MOBILE, "
                + "        CN.EMAIL, "
                + "        CN.FAMILY_MOBILE "
                + " FROM CANDIDATE CN  "
                + " INNER JOIN CANDIDATE_PROGRAM_OF_STUDY AS  CNPOS ON CNPOS.candidate_id = CN.candidate_id "
                + " INNER JOIN CAMPUS_PROGRAM_OF_STUDY AS CPOS ON CPOS.CAMPUS_PROGRAM_OF_STUDY_ID = CNPOS.CAMPUS_PROGRAM_OF_STUDY_ID  "
                + " INNER JOIN PROGRAM_OF_STUDY POS ON POS.PROGRAM_OF_STUDY_ID = CPOS.PROGRAM_OF_STUDY_ID "
                + " INNER JOIN PROGRAM AS P ON P.PROGRAM_ID = POS.PROGRAM_ID "
                + " INNER JOIN DISTRICT D ON D.DISTRICT_ID = CN.DISTRICT_ID "
                + " INNER JOIN JURISDICTION J ON J.DISTRICT_ID = CN.DISTRICT_ID "
                + " WHERE CN.CANDIDATE_ID =  " + candidateId
                + " AND POS.PROGRAM_OF_STUDY_ID = " + posId
                + " AND J.CAMPUS_ID = " + campusId;
        Object[] ob = null;
        try (Statement st = con.createStatement();
                ResultSet rs = st.executeQuery(sql);) {
            if (rs.next()) {
                ob = new Object[]{
                    rs.getInt("SEAT_NO"),
                    rs.getString("CANDIDATE_NAME"),
                    rs.getString("FATHERS_NAME"),
                    rs.getFloat("PERCENTAGE"),
                    rs.getString("P_NAME"),
                    rs.getString("P_REMARKS"),
                    rs.getString("POS_NAME"),
                    rs.getString("DISTRICT_NAME"),
                    rs.getBoolean("IS_JURISDICTION"),
                    rs.getInt("YEARS_DEGREE"),
                    rs.getInt("DEDUCTION_MARKS"),
                    rs.getString("OBJECTION_REMARKS"),
                    rs.getString("MOBILE"),
                    rs.getString("EMAIL"),
                    rs.getString("FAMILY_MOBILE"),
                };
            }
        }
        return ob;
    }

    public static Object[] getCandidateAndSelectionInfoAlg(int candidateId, int posId) throws SQLException {
        String sql = "SELECT CN.SEAT_NO, "
                + "	   CN.NAME AS CANDIDATE_NAME, "
                + "	   CN.FATHERS_NAME, "
                + "	   CN.PERCENTAGE, "
                + "	   P.NAME AS P_NAME, "
                + "	   P.REMARKS AS P_REMARKS, "
                + "	   POS.NAME AS POS_NAME, "
                + "	   CN.YEARS_DEGREE, "
                + "	   D.NAME AS DISTRICT_NAME,"
                + "        CN.DEDUCTION_MARKS,"
                + "        CN.OBJECTION_REMARKS, "
                + "        CN.MOBILE, "
                + "        CN.EMAIL, "
                + "        CN.FAMILY_MOBILE "

                + " FROM CANDIDATE CN  "
                + " INNER JOIN CANDIDATE_PROGRAM_OF_STUDY AS  CNPOS ON CNPOS.candidate_id = CN.candidate_id "
                + " INNER JOIN CAMPUS_PROGRAM_OF_STUDY AS CPOS ON CPOS.CAMPUS_PROGRAM_OF_STUDY_ID = CNPOS.CAMPUS_PROGRAM_OF_STUDY_ID  "
                + " INNER JOIN PROGRAM_OF_STUDY POS ON POS.PROGRAM_OF_STUDY_ID = CPOS.PROGRAM_OF_STUDY_ID "
                + " INNER JOIN PROGRAM AS P ON P.PROGRAM_ID = POS.PROGRAM_ID "
                + " INNER JOIN DISTRICT D ON D.DISTRICT_ID = CN.DISTRICT_ID "
                + " WHERE CN.CANDIDATE_ID =  " + candidateId
                + " AND POS.PROGRAM_OF_STUDY_ID = " + posId;
        Object[] ob = null;
        try (Statement st = con.createStatement();
                ResultSet rs = st.executeQuery(sql);) {
            if (rs.next()) {
                ob = new Object[]{
                    rs.getInt("SEAT_NO"),
                    rs.getString("CANDIDATE_NAME"),
                    rs.getString("FATHERS_NAME"),
                    rs.getFloat("PERCENTAGE"),
                    rs.getString("P_NAME"),
                    rs.getString("P_REMARKS"),
                    rs.getString("POS_NAME"),
                    rs.getString("DISTRICT_NAME"),
                    null,
                    rs.getInt("YEARS_DEGREE"),
                    rs.getInt("DEDUCTION_MARKS"),
                    rs.getString("OBJECTION_REMARKS"),
                    rs.getString("MOBILE"),
                    rs.getString("EMAIL"),
                    rs.getString("FAMILY_MOBILE"),};
            }
        }
        return ob;
    }

    public static ArrayList<String> getCandidateChoicesCodeAlg(int candidateId, int campusId) throws SQLException {
        String sql = " SELECT "
                + "  S.IS_MORNING, "
                + "	S.NAME AS SHIFT,  "
                + "	GROUP_CONCAT(POS.CODE ORDER BY CHOICE_NO ASC) AS CHOICES "
                + " FROM CANDIDATE_PROGRAM_OF_STUDY AS CNPOS  "
                + " INNER JOIN CAMPUS_PROGRAM_OF_STUDY AS CPOS ON CNPOS.CAMPUS_PROGRAM_OF_STUDY_ID = CPOS.CAMPUS_PROGRAM_OF_STUDY_ID  "
                + " INNER JOIN PROGRAM_OF_STUDY AS POS ON CPOS.PROGRAM_OF_STUDY_ID = POS.PROGRAM_OF_STUDY_ID  "
                + " INNER JOIN CAMPUS AS CM ON CM.CAMPUS_ID = CPOS.CAMPUS_ID "
                + " INNER JOIN SHIFT AS S ON S.SHIFT_ID = CPOS.SHIFT_ID "
                + " WHERE POS.CODE IS NOT NULL "
                + " AND CNPOS.CANDIDATE_ID =  " + candidateId
                + " AND CPOS.CAMPUS_ID = " + campusId
                + " GROUP BY CNPOS.CANDIDATE_ID, CM.NAME, S.NAME ";
//                + " ORDER BY SHIFT DESC";
//        System.out.println(sql);
        ArrayList<String> strs = new ArrayList();
        try (Statement st = con.createStatement();
                ResultSet rs = st.executeQuery(sql);) {
            while (rs.next()) {
                if (rs.getBoolean("IS_MORNING")) {
                    strs.add("Morning: (" + rs.getString("CHOICES") + " )");
                } else {
                    strs.add("Evening: (" + rs.getString("CHOICES") + " )");
                }
            }
        }
        return strs;
    }

    public static Object[] getLastCredential(int candidateId) throws SQLException {
        String sql = "SELECT CD.DETAIL_OF,P.NAME, I.LOCATION FROM CREDENTIAL_DETAILS CD "
                + " INNER JOIN PROGRAM AS P ON P.PROGRAM_ID = CD.PROGRAM_ID "
                + " INNER JOIN ISSUER AS I ON I.ISSUER_ID = CD.ISSUER_ID "
                + " WHERE CANDIDATE_ID = " + candidateId
                + " AND DETAIL_OF = (SELECT MAX(DETAIL_OF) FROM CREDENTIAL_DETAILS "
                + " WHERE CANDIDATE_ID = " + candidateId + ")";
        Object[] ob = null;
        try (Statement st = con.createStatement();
                ResultSet rs = st.executeQuery(sql);) {
            while (rs.next()) {
                ob = new Object[]{
                    DetailOfEnum.values()[ rs.getInt("DETAIL_OF")].getTitle(),
                    rs.getString("NAME"),
                    rs.getString("LOCATION")
                };
            }
        }
        return ob;
    }

    public static List<String> getOptionalSubjects(int candidateId) throws SQLException {
        String sql = "SELECT DS.CODE FROM CREDENTIAL_DETAILS CD "
                + "INNER JOIN OPTIONAL_SUBJECT OS ON CD.CREDENTIAL_DETAILS_ID = OS.CREDENTIAL_DETAILS_ID "
                + "INNER JOIN SUBJECT SB ON SB.SUBJECT_ID = OS.SUBJECT_ID "
                + "INNER JOIN DISCIPLINE DS ON DS.DISCIPLINE_ID = SB.DISCIPLINE_ID "
                + "WHERE CD.CREDENTIAL_DETAILS_ID IN (SELECT CREDENTIAL_DETAILS_ID FROM CREDENTIAL_DETAILS WHERE CANDIDATE_ID = " + candidateId + ")";
        List<String> list = new ArrayList();
        try (Statement st = con.createStatement();
                ResultSet rs = st.executeQuery(sql);) {
            while (rs.next()) {
                String code = rs.getString("CODE");
                list.add(code);
            }
        }
        return list;
    }

    public static Object[] getCandidate(int candidateId) throws SQLException {
        String sql = "SELECT "
                + "        CN.SEAT_NO, "
                + "	   CN.NAME AS CANDIDATE_NAME, "
                + "	   CN.FATHERS_NAME, "
                + "	   CN.PERCENTAGE, "
                + "	   CN.YEARS_DEGREE, "
                + "	   D.NAME AS DISTRICT_NAME, "
                + "	   J.IS_JURISDICTION, "
                + "        CN.DEDUCTION_MARKS,"
                + "        CN.OBJECTION_REMARKS,"
                + "        CN.AREA "
                + " FROM CANDIDATE CN  "
                + " INNER JOIN DISTRICT D ON D.DISTRICT_ID = CN.DISTRICT_ID "
                + " LEFT JOIN JURISDICTION J ON J.DISTRICT_ID = CN.DISTRICT_ID "
                + " WHERE CN.CANDIDATE_ID =  " + candidateId;
        Object[] ob = null;
        try (Statement st = con.createStatement();
                ResultSet rs = st.executeQuery(sql);) {
            if (rs.next()) {
                ob = new Object[]{
                    rs.getInt("SEAT_NO"),
                    rs.getString("CANDIDATE_NAME"),
                    rs.getString("FATHERS_NAME"),
                    rs.getFloat("PERCENTAGE"),
                    rs.getString("DISTRICT_NAME"),
                    rs.getBoolean("IS_JURISDICTION"),
                    rs.getInt("YEARS_DEGREE"),
                    rs.getInt("DEDUCTION_MARKS"),
                    rs.getString("OBJECTION_REMARKS"),
                    rs.getInt("AREA"),};
            }
        }
        return ob;
    }

    public static float getLastCredentailPercentage(int candidateId) throws SQLException, Exception {
        String sql = "select cd.total_marks,cd.marks_obtained,cd.detail_of from credential_details cd "
                + " where cd.detail_of = ( select max(detail_of)  from credential_details where candidate_id = " + candidateId + ") "
                + " and cd.candidate_id = " + candidateId;
        try (Statement st = con.createStatement();
                ResultSet rs = st.executeQuery(sql);) {
            if (rs.next()) {
                int obtain = rs.getInt("marks_obtained");
                int total = rs.getInt("total_marks");
                if (obtain == 0 || total == 0) {
                    throw new Exception("Credential Detail Not found of candidate_id:" + candidateId);
                }
                float perc = (obtain * 100.0F) / total;
                return perc;
            }
        }
        throw new Exception("Credential Detail Not found of candidate_id:" + candidateId);
    }

    public static List<ProgramOfStudy> getProgramOfStudy(int campusId, int programTypeId, boolean isSeperated) throws Exception {
        String sql = "SELECT distinct(POS.PROGRAM_OF_STUDY_ID), POS.PROGRAM_ID, POS.DISCIPLINE_ID, "
                + "      concat(P.NAME,' (',POS.NAME,')') as programOfStudyName, "
                + "      pos.p_code, "
                + "      POS.duration, "
                + "      POS.semester, "
                + "      POS.is_quota_oriented, "
                + "      POS.remarks, "
                + "      POS.code "
                + "        FROM PROGRAM P "
                + "        INNER JOIN PROGRAM_OF_STUDY POS ON POS.PROGRAM_ID = P.PROGRAM_ID "
                + "        INNER JOIN CAMPUS_PROGRAM_OF_STUDY CPOS ON POS.PROGRAM_OF_STUDY_ID = CPOS.PROGRAM_OF_STUDY_ID "
                + "        INNER JOIN CPOS_GROUP CPOSG ON CPOSG.CAMPUS_PROGRAM_OF_STUDY_ID = CPOS.CAMPUS_PROGRAM_OF_STUDY_ID "
                + "        INNER JOIN SHIFT S ON S.SHIFT_ID = CPOS.SHIFT_ID "
                + "        WHERE CAMPUS_ID = " + campusId
                + "        AND P.PROGRAM_TYPE_ID = " + programTypeId
                + "        AND CPOS.IS_SEPERATE = " + isSeperated
                + "         order by POS.NAME";
        List<ProgramOfStudy> posList = new ArrayList<>();
        try (Statement st = con.createStatement();
                ResultSet rs = st.executeQuery(sql);) {

            while (rs.next()) {
                ProgramOfStudy pos = new ProgramOfStudy();
                pos.setProgramOfStudyId(rs.getInt("program_of_study_id"));
                int programId = rs.getInt("program_id");
                int disciplineId = rs.getInt("discipline_id");
                pos.setName(rs.getString("programOfStudyName"));
//                String posName = ;
                pos.setPCode(rs.getInt("p_code"));
//                int pcode = ;
                pos.setDuration(DurationEnum.values()[rs.getInt("duration")]);
//                int duration = ;
                pos.setSemester(SemesterEnum.values()[rs.getInt("semester")]);
//                int semester = ;
                pos.setIsQuotaOriented(rs.getBoolean("is_quota_oriented"));
//                boolean quotaOriented = ;
                pos.setRemarks(rs.getString("remarks"));
//                String remarks = ;
                pos.setCode(rs.getString("code"));
//                String code = ;
                posList.add(pos);
            }
        }
        return posList;
    }

    public static List<CredentialDetailJRBean> getCredential(int candidateId) throws SQLException {
        String sql = "SELECT CD.SEAT_NO,"
                + "         CD.CREDENTIAL_DETAILS_ID ,"
                + "         CD.PASSING_YEAR,"
                + "         CD.DETAIL_OF,"
                + "         CI.LOCATION AS ACRONYM,"
                + "         CD.TOTAL_MARKS,"
                + "         CD.MARKS_OBTAINED,"
                + "         P.NAME "
                + "         FROM CREDENTIAL_DETAILS CD "
                + " INNER JOIN ISSUER CI ON CI.ISSUER_ID = CD.ISSUER_ID "
                + " INNER JOIN PROGRAM P ON P.PROGRAM_ID = CD.PROGRAM_ID "
                + " WHERE CD.CANDIDATE_ID=" + candidateId
                + "  ORDER BY CD.DETAIL_OF ";

        List<CredentialDetailJRBean> list = new ArrayList();
        try (Statement st = con.createStatement();
                ResultSet rs = st.executeQuery(sql);) {
            while (rs.next()) {
                CredentialDetailJRBean credentialbean = new CredentialDetailJRBean();
                int detailsOf = rs.getInt("DETAIL_OF");
                int credentialId = rs.getInt("CREDENTIAL_DETAILS_ID");
                credentialbean.setDetailOf(DetailOfEnum.values()[detailsOf].getCode());
                credentialbean.setBd(rs.getString("ACRONYM"));
                credentialbean.setGrp(rs.getString("NAME"));
                credentialbean.setYear(rs.getInt("PASSING_YEAR"));
                credentialbean.setoMrks((float) rs.getInt("MARKS_OBTAINED"));
                credentialbean.settMrks(rs.getInt("TOTAL_MARKS"));
                credentialbean.setSeatNo(rs.getString("SEAT_NO"));
                list.add(credentialbean);
            }
        }
        return list;
    }

    public static int addAdmissionListDetails(int candidateId, int admissionListId, int cposgId, int cnposId, int campusCategoryId, int active, int catOrdinal) throws SQLException {
        String query = "INSERT INTO admission_list_details( "
                + " candidate_id, "
                + " admission_list_id,"
                + " cpos_group_id,"
                + " candidate_program_of_study_id,"
                + " campus_category_id,"
                + " active,"
                + " sel_cat "
                + ") VALUES "
                + "(?, ?, ?, ?, ?, ?, ?)";
        //System.out.println(query);

        try (PreparedStatement pst = con.prepareStatement(query);) {
            pst.setInt(1, candidateId);
            pst.setInt(2, admissionListId);
            pst.setInt(3, cposgId);
            pst.setInt(4, cnposId);
            pst.setInt(5, campusCategoryId);
            pst.setInt(6, active);
            pst.setInt(7, catOrdinal);

            int record = pst.executeUpdate();
            return record;
        }
    }

    public static List<Object[]> getCandidateChoice(int candidateId, int campusId) throws SQLException {
        String sql = "SELECT CNPOS.CANDIDATE_ID as ID, CM.CAMPUS_ID, "
                + " POS.NAME, "
                + " CNPOS.CHOICE_NO, "
                + " S.SHIFT_ID, "
                + " concat( CM.name,' ', CM.location,S.NAME) AS SHIFT "
                + " FROM CANDIDATE_PROGRAM_OF_STUDY AS CNPOS "
                + " INNER JOIN CAMPUS_PROGRAM_OF_STUDY AS CPOS ON CNPOS.CAMPUS_PROGRAM_OF_STUDY_ID = CPOS.CAMPUS_PROGRAM_OF_STUDY_ID "
                + " INNER JOIN PROGRAM_OF_STUDY POS ON POS.PROGRAM_OF_STUDY_ID =  CPOS.PROGRAM_OF_STUDY_ID "
                + " INNER JOIN CAMPUS AS CM ON CM.CAMPUS_ID = CPOS.CAMPUS_ID "
                + " "
                + " INNER JOIN SHIFT AS S ON S.SHIFT_ID = CPOS.SHIFT_ID "
                + " "
                + " WHERE CNPOS.candidate_id = " + candidateId
                + " AND CM.CAMPUS_ID =  " + campusId
                + " ORDER BY S.IS_MORNING DESC, CNPOS.CHOICE_NO ";
        List<Object[]> list = new LinkedList<>();
        try (Statement st = con.createStatement();
                ResultSet rs = st.executeQuery(sql);) {
            while (rs.next()) {
                Object[] obs = {
                    candidateId,
                    campusId,
                    rs.getString("NAME"),
                    rs.getInt("CHOICE_NO"),
                    rs.getInt("SHIFT_ID"),
                    rs.getString("SHIFT")
                };
                list.add(obs);
            }
        }
        return list;
    }

    public static int[] getDistrictSeats(int admissionYearId, int campusId, int districtId) throws SQLException {
        String sql = "SELECT urban,rural,other FROM district_seat_distribution d "
                + " inner join jurisdiction j on j.jurisdiction_id = d.jurisdiction_id "
                + " where admission_year_id = " + admissionYearId + " and district_id = " + districtId + " and campus_id = " + campusId;
        try (Statement st = con.createStatement();
                ResultSet rs = st.executeQuery(sql);) {
            if (rs.next()) {
                int[] obs = {
                    rs.getInt("urban"),
                    rs.getInt("rural"),
                    rs.getInt("other")};
                return obs;
            }
        }
        return null;
    }

    public static List<Integer> getAppliedCategories(int candidateId) throws SQLException {
        String sql = "SELECT AC.CODE FROM APPLIED_CATEGORY AC WHERE CANDIDATE_ID = " + candidateId;
        try (Statement st = con.createStatement();
                ResultSet rs = st.executeQuery(sql);) {
            List<Integer> list = new LinkedList();
            while (rs.next()) {
                list.add(rs.getInt("CODE"));
            }
            return list;
        }
    }

    public static boolean isRetainedPartRegistry(int candidateId) throws SQLException {
        String sql = "SELECT PR.AMOUNT "
                + "FROM CANDIDATE C "
                + "INNER JOIN ADMISSION_LIST_DETAILS ALD ON ALD.CANDIDATE_ID = C.CANDIDATE_ID "
                + "INNER JOIN PART_REGISTRY PR ON PR.ADMISSION_LIST_DETAILS_ID = ALD.ADMISSION_LIST_DETAILS_ID "
                + "WHERE C.CANDIDATE_ID = " + candidateId + " AND PR.TYPE = 1";
        try (Statement st = con.createStatement();
                ResultSet rs = st.executeQuery(sql);) {
            while (rs.next()) {
                return true;
            }
        }
        return false;
    }

                      /*CategoryLogicalCodeEnum, isMorning*/
    public static List<Object[]> getDistinctSelectionLogicalCode(int candiateId,int campusId) throws SQLException {
        String sql = "SELECT  CAT.CODE , (SELECT IS_MORNING FROM SHIFT S WHERE S.SHIFT_ID = CPOS.SHIFT_ID) as IS_MORNING "
                + " FROM ADMISSION_LIST_DETAILS ALD "
                + " INNER JOIN CAMPUS_CATEGORY CC ON CC.CAMPUS_CATEGORY_ID = ALD.CAMPUS_CATEGORY_ID "
                + " INNER JOIN CATEGORY CAT ON CAT.CATEGORY_ID = CC.CATEGORY_ID "
                + " INNER JOIN CPOS_GROUP CPOSG ON CPOSG.CPOS_GROUP_ID = ALD.CPOS_GROUP_ID "
                + " INNER JOIN CAMPUS_PROGRAM_OF_STUDY CPOS ON CPOS.CAMPUS_PROGRAM_OF_STUDY_ID = CPOSG.CAMPUS_PROGRAM_OF_STUDY_ID "
                + " WHERE CC.CAMPUS_ID = "+campusId
                + " AND ALD.CANDIDATE_ID = " + candiateId;
        List<Object[]> cats = new LinkedList<>();
        try (Statement st = con.createStatement();
                ResultSet rs = st.executeQuery(sql);) {
            while (rs.next()) {
                cats.add( new Object[]{ CategoryLogicalCodeEnum.values()[rs.getInt("CODE")], rs.getBoolean("IS_MORNING")} );
            }
            return cats;
        }
    }
}
