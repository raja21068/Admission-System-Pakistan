package admission.controller;

import admission.controller.algorithm.CandidateAlg;
import admission.controller.algorithm.CategoryAlg;
import admission.controller.algorithm.DisciplineAlg;
import admission.controller.beans.Candidate;
import admission.enums.CategoryEnum;
import admission.enums.CategoryLogicalCodeEnum;
import admission.enums.DetailOfEnum;
import admission.enums.GroupEnum;
import admission.enums.JurisdictionEnum;
import admission.enums.SelectionStatusEnum;
import admission.model.AdmissionListDetails;
import admission.model.CampusCategory;
import admission.model.CampusProgramOfStudy;
import admission.model.CandidateProgramOfStudy;
import admission.model.CposGroup;
import admission.model.CredentialDetails;
import admission.model.DisciplineCategorySeats;
import admission.model.DistrictSeatDistribution;
import admission.model.Jurisdiction;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
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
import java.util.LinkedList;

/**
 *
 * @author Yougeshwar
 */
public class JDBCDatabaseManager {

    private static Connection con;

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

    public static void load() {
    }

    public static boolean isUserResourceAccess(int id, String code) throws SQLException {
        boolean b;
        String sql = "SELECT * FROM YOG_RESOURCES WHERE USER_ID = " + id + " AND CODE = '" + code + "'";
        try (Statement st = con.createStatement();) {
            try (ResultSet rs = st.executeQuery(sql);) {
                b = rs.next();
            }
        }
        return b;
    }

    public static boolean checkUser(int id) throws SQLException {
        boolean b;
        String sql = "SELECT * FROM YOG_RESOURCES WHERE USER_ID = " + id + " AND 'TYPE' = 'Y'";
        try (Statement st = con.createStatement();) {
            try (ResultSet rs = st.executeQuery(sql);) {
                b = rs.next();
            }
        }
        return b;
    }

    public static List<Map<String, Object>> getResultSet(String sql) throws SQLException {
        List<Map<String, Object>> rows = new ArrayList<>();
        try (Statement st = con.createStatement();
                ResultSet rs = st.executeQuery(sql);) {

            ResultSetMetaData rsMeta = rs.getMetaData();

            int Col_Count = rsMeta.getColumnCount();
            List<String> columns = new ArrayList<>();
            for (int i = 1; i <= Col_Count; i++) {
                columns.add(rsMeta.getColumnLabel(i));
            }

            while (rs.next()) {
                Map<String, Object> row = new LinkedHashMap<>();
                for (String columnName : columns) {
                    Object value = rs.getObject(columnName);
                    row.put(columnName, value);
                }
                rows.add(row);
            }

            st.close();
        }
        return rows;
    }

    public static int executeSQL(String sql) throws SQLException {
        try (Statement st = con.createStatement();) {
            return st.executeUpdate(sql);
        }
    }
//    public static List<CposGroup> getCampusCposGroup(int campusId, int shiftId, int programTypeId, boolean isSeperate) throws SQLException{
//        String sql = "SELECT * FROM "
//                + "cpos_group cposg, campus_program_of_study cpos, program_of_study pos, discipline d, shift s, campus c, program p, program_type pt "
//                + "WHERE cposg.campus_program_of_study_id = cpos.campus_program_of_study_id "
//                + "AND cpos.program_of_study_id = pos.program_of_study_id "
//                + "AND d.discipline_id = pos.discipline_id "
//                + "AND pos.program_id = p.program_id "
//                + "AND p.program_type_id = pt.program_type_id "
//                + "AND cpos.campus_id = c.campus_id "
//                + "AND cpos.shift_id = s.shift_id "
//                + "AND pt.program_type_id = " + programTypeId + " "
//                + "AND c.campus_id = " + campusId + " "
//                + "AND s.shift_id = " + shiftId + " "
//                + "AND cpos.is_seperate = " + isSeperate + " "
//                + "ORDER BY pos.name";
//        
//        //System.out.println(sql);
//        try (Statement st = con.createStatement();
//                ResultSet rs = st.executeQuery(sql);){
//            
//            List<CposGroup> list = new ArrayList<>();
//            
//            while (rs.next()) {
//                CposGroup ob = new CposGroup();
//                
//                ob.setCampusProgramOfStudyId(rs.getInt("cposg.campus_program_of_study_id"));
//                ob.setCposGroupId(rs.getInt("cposg.cpos_group_id"));
//                ob.setGroup(rs.getString("cposg.group_"));
//                ob.setRemarks(rs.getString("cposg.remarks"));
//                ob.setPosIsQuotaOriented(rs.getBoolean("pos.is_quota_oriented"));
//                ob.setProgramOfStudyId(rs.getInt("cpos.program_of_study_id"));
//                ob.setPosName(rs.getString("pos.name"));
//                ob.setIsBachelor(rs.getBoolean("pt.is_bachelor"));
//                ob.setDisciplineCode(rs.getString("d.code"));
//                ob.setProgramName(rs.getString("p.name"));
//                ob.setPosPCode(rs.getInt("pos.p_code"));
//                ob.setCampusName(rs.getString("c.location"));
//                
//                list.add(ob);
//            }
//            
//            return list;
//        }
//    }
//    public static List<CposGroup> getMainCposGroup(boolean isMain, int shiftId, int programTypeId, boolean isSeperate) throws SQLException{
//        String sql = "SELECT * FROM "
//                + "cpos_group cposg, campus_program_of_study cpos, program_of_study pos, discipline d, shift s, campus c, program p, program_type pt "
//                + "WHERE cposg.campus_program_of_study_id = cpos.campus_program_of_study_id "
//                + "AND cpos.program_of_study_id = pos.program_of_study_id "
//                + "AND d.discipline_id = pos.discipline_id "
//                + "AND pos.program_id = p.program_id "
//                + "AND p.program_type_id = pt.program_type_id "
//                + "AND cpos.campus_id = c.campus_id "
//                + "AND cpos.shift_id = s.shift_id "
//                + "AND pt.program_type_id = " + programTypeId + " "
//                + "AND c.is_main = " + isMain + " "
//                + "AND s.shift_id = " + shiftId + " "
//                + "AND cpos.is_seperate = " + isSeperate + " "
//                + "ORDER BY pos.name";
//        
//        //System.out.println(sql);
//        try (Statement st = con.createStatement();
//                ResultSet rs = st.executeQuery(sql);){
//            
//            List<CposGroup> list = new ArrayList<>();
//            
//            while (rs.next()) {
//                CposGroup ob = new CposGroup();
//                
//                ob.setCampusProgramOfStudyId(rs.getInt("cposg.campus_program_of_study_id"));
//                ob.setCposGroupId(rs.getInt("cposg.cpos_group_id"));
//                ob.setGroup(rs.getString("cposg.group_"));
//                ob.setRemarks(rs.getString("cposg.remarks"));
//                ob.setPosIsQuotaOriented(rs.getBoolean("pos.is_quota_oriented"));
//                ob.setProgramOfStudyId(rs.getInt("cpos.program_of_study_id"));
//                ob.setPosName(rs.getString("pos.name"));
//                ob.setIsBachelor(rs.getBoolean("pt.is_bachelor"));
//                ob.setDisciplineCode(rs.getString("d.code"));
//                ob.setProgramName(rs.getString("p.name"));
//                ob.setPosPCode(rs.getInt("pos.p_code"));
//                ob.setCampusName(rs.getString("c.location"));
//                
//                list.add(ob);
//            }
//            
//            return list;
//        }
//    }

    public static List<CposGroup> getMainCposGroup(boolean isMain, int programTypeId, boolean isSeperate) throws SQLException {
//        String sql = "SELECT * FROM "
//                + "cpos_group cposg, campus_program_of_study cpos, program_of_study pos, discipline d, shift s, campus c, program p, program_type pt "
//                + "WHERE cposg.campus_program_of_study_id = cpos.campus_program_of_study_id "
//                + "AND cpos.program_of_study_id = pos.program_of_study_id "
//                + "AND d.discipline_id = pos.discipline_id "
//                + "AND pos.program_id = p.program_id "
//                + "AND p.program_type_id = pt.program_type_id "
//                + "AND cpos.campus_id = c.campus_id "
//                + "AND s.shift_id = cpos.shift_id "
//                + "AND pt.program_type_id = " + programTypeId + " "
//                + "AND c.is_main = " + isMain + " "
//                + "AND cpos.is_seperate = " + isSeperate + " "
//                + "ORDER BY pos.name";

        String sql = "SELECT * FROM cpos_group cposg "
                + "INNER JOIN campus_program_of_study cpos ON cpos.campus_program_of_study_id = cposg.campus_program_of_study_id "
                + "INNER JOIN program_of_study pos ON pos.program_of_study_id = cpos.program_of_study_id "
                + "INNER JOIN discipline d ON d.discipline_id = pos.discipline_id "
                + "INNER JOIN shift s ON s.shift_id = cpos.shift_id "
                + "INNER JOIN campus c ON c.campus_id = cpos.campus_id "
                + "INNER JOIN program p ON p.program_id = pos.program_id "
                + "INNER JOIN program_type pt ON pt.program_type_id = p.program_type_id "
                + "WHERE pt.program_type_id = " + programTypeId + " "
                + "AND c.is_main = " + isMain + " "
                + "AND cpos.is_seperate = " + isSeperate + " "
                + "ORDER BY pos.name";

//        System.out.println(sql);
        try (Statement st = con.createStatement();
                ResultSet rs = st.executeQuery(sql);) {

            List<CposGroup> list = new ArrayList<>();

            while (rs.next()) {
                CposGroup ob = new CposGroup();

                ob.setCampusProgramOfStudyId(rs.getInt("cposg.campus_program_of_study_id"));
                ob.setCposGroupId(rs.getInt("cposg.cpos_group_id"));
//                ob.setGroup(GroupEnum.values()[rs.getInt("cposg.group_")]);
                ob.setRemarks(rs.getString("cposg.remarks"));
                ob.setPosIsQuotaOriented(rs.getBoolean("pos.is_quota_oriented"));
                ob.setProgramOfStudyId(rs.getInt("pos.program_of_study_id"));
                ob.setPosName(rs.getString("pos.name"));
                ob.setIsBachelor(rs.getBoolean("pt.is_bachelor"));
                ob.setDisciplineCode(rs.getString("d.code"));
                ob.setProgramName(rs.getString("p.name"));
                ob.setPosPCode(rs.getInt("pos.p_code"));
                ob.setCampusName(rs.getString("c.location"));
                ob.setShiftName(rs.getString("s.name"));
                ob.setIsMorning(rs.getBoolean("s.is_morning"));
                ob.setShiftId(rs.getInt("s.shift_id"));
                ob.setProgramTypeId(rs.getInt("pt.program_type_id"));

                list.add(ob);
            }

            return list;
        }
    }

    public static List<CposGroup> getCampusCposGroup(int campusId, int programTypeId, boolean isSeperate) throws SQLException {
        String sql = "SELECT * FROM "
                + "cpos_group cposg, campus_program_of_study cpos, program_of_study pos, discipline d, shift s, campus c, program p, program_type pt "
                + "WHERE cposg.campus_program_of_study_id = cpos.campus_program_of_study_id "
                + "AND cpos.program_of_study_id = pos.program_of_study_id "
                + "AND d.discipline_id = pos.discipline_id "
                + "AND pos.program_id = p.program_id "
                + "AND p.program_type_id = pt.program_type_id "
                + "AND cpos.campus_id = c.campus_id "
                + "AND s.shift_id = cpos.shift_id "
                + "AND pt.program_type_id = " + programTypeId + " "
                + "AND c.campus_id = " + campusId + " "
                + "AND cpos.is_seperate = " + isSeperate + " "
                + "ORDER BY pos.name";

        //System.out.println(sql);
        try (Statement st = con.createStatement();
                ResultSet rs = st.executeQuery(sql);) {

            List<CposGroup> list = new ArrayList<>();

            while (rs.next()) {
                CposGroup ob = new CposGroup();

                ob.setCampusProgramOfStudyId(rs.getInt("cposg.campus_program_of_study_id"));
                ob.setCposGroupId(rs.getInt("cposg.cpos_group_id"));
                ob.setGroup(GroupEnum.values()[rs.getInt("cposg.group_")]);
                ob.setRemarks(rs.getString("cposg.remarks"));
                ob.setPosIsQuotaOriented(rs.getBoolean("pos.is_quota_oriented"));
                ob.setProgramOfStudyId(rs.getInt("cpos.program_of_study_id"));
                ob.setPosName(rs.getString("pos.name"));
                ob.setIsBachelor(rs.getBoolean("pt.is_bachelor"));
                ob.setDisciplineCode(rs.getString("d.code"));
                ob.setProgramName(rs.getString("p.name"));
                ob.setPosPCode(rs.getInt("pos.p_code"));
                ob.setCampusName(rs.getString("c.location"));
                ob.setShiftName(rs.getString("s.name"));
                ob.setIsMorning(rs.getBoolean("s.is_morning"));
                ob.setShiftId(rs.getInt("s.shift_id"));
                ob.setProgramTypeId(rs.getInt("pt.program_type_id"));

                list.add(ob);
            }

            return list;
        }
    }

    public static CposGroup getCampusCposGroup(int cposgId) throws SQLException {
        String sql = "SELECT * FROM "
                + "cpos_group cposg, campus_program_of_study cpos, program_of_study pos, discipline d, shift s, campus c, program p, program_type pt, department dept "
                + "WHERE cposg.campus_program_of_study_id = cpos.campus_program_of_study_id "
                + "AND cpos.program_of_study_id = pos.program_of_study_id "
                + "AND d.discipline_id = pos.discipline_id "
                + "AND dept.department_id = d.department_id "
                + "AND pos.program_id = p.program_id "
                + "AND p.program_type_id = pt.program_type_id "
                + "AND cpos.campus_id = c.campus_id "
                + "AND s.shift_id = cpos.shift_id "
                + "AND cposg.cpos_group_id = " + cposgId + " "
                + "ORDER BY pos.name";

        //System.out.println(sql);
        try (Statement st = con.createStatement();
                ResultSet rs = st.executeQuery(sql);) {

            CposGroup ob = null;
            if (rs.next()) {
                ob = new CposGroup();

                ob.setCampusProgramOfStudyId(rs.getInt("cposg.campus_program_of_study_id"));
                ob.setCposGroupId(rs.getInt("cposg.cpos_group_id"));
                ob.setGroup(GroupEnum.values()[rs.getInt("cposg.group_code")]);
                ob.setRemarks(rs.getString("cposg.remarks"));
                ob.setPosIsQuotaOriented(rs.getBoolean("pos.is_quota_oriented"));
                ob.setProgramOfStudyId(rs.getInt("cpos.program_of_study_id"));
                ob.setPosName(rs.getString("pos.name"));
                ob.setIsBachelor(rs.getBoolean("pt.is_bachelor"));
                ob.setDisciplineCode(rs.getString("d.code"));
                ob.setProgramName(rs.getString("p.name"));
                ob.setPosPCode(rs.getInt("pos.p_code"));
                ob.setCampusName(rs.getString("c.location"));
                ob.setShiftName(rs.getString("s.name"));
                ob.setIsMorning(rs.getBoolean("s.is_morning"));
                ob.setShiftId(rs.getInt("s.shift_id"));
                ob.setProgramTypeId(rs.getInt("pt.program_type_id"));
                ob.setDepartmentName(rs.getString("dept.name"));
                ob.setCode(rs.getString("cposg.code"));
            }

            return ob;
        }
    }

    public static int getCampusCposGroup(int campusId, int programTypeId) throws SQLException {
        String sql = "SELECT count(cposg.cpos_group_id) total FROM "
                + "cpos_group cposg, campus_program_of_study cpos, program_of_study pos, discipline d, shift s, campus c, program p, program_type pt "
                + "WHERE cposg.campus_program_of_study_id = cpos.campus_program_of_study_id "
                + "AND cpos.program_of_study_id = pos.program_of_study_id "
                + "AND d.discipline_id = pos.discipline_id "
                + "AND pos.program_id = p.program_id "
                + "AND p.program_type_id = pt.program_type_id "
                + "AND cpos.campus_id = c.campus_id "
                + "AND s.shift_id = cpos.shift_id "
                + "AND pt.program_type_id = " + programTypeId + " "
                + "AND c.campus_id = " + campusId + " "
                + "ORDER BY pos.name";

        //System.out.println(sql);
        try (Statement st = con.createStatement();
                ResultSet rs = st.executeQuery(sql);) {

            if (rs.next()) {
                return rs.getInt("total");
            }

            return 0;
        }
    }

    public static int getMainCposGroup(boolean isMain, int programTypeId) throws SQLException {
        String sql = "SELECT count(cposg.cpos_group_id) total FROM "
                + "cpos_group cposg, campus_program_of_study cpos, program_of_study pos, discipline d, shift s, campus c, program p, program_type pt "
                + "WHERE cposg.campus_program_of_study_id = cpos.campus_program_of_study_id "
                + "AND cpos.program_of_study_id = pos.program_of_study_id "
                + "AND d.discipline_id = pos.discipline_id "
                + "AND pos.program_id = p.program_id "
                + "AND p.program_type_id = pt.program_type_id "
                + "AND cpos.campus_id = c.campus_id "
                + "AND s.shift_id = cpos.shift_id "
                + "AND pt.program_type_id = " + programTypeId + " "
                + "AND c.is_main = " + isMain + " "
                + "ORDER BY pos.name";

        //System.out.println(sql);
        try (Statement st = con.createStatement();
                ResultSet rs = st.executeQuery(sql);) {

            if (rs.next()) {
                return rs.getInt("total");
            }

            return 0;
        }
    }

    public static CampusCategory getCampusCategory(int campusId, int programTypeId, int shiftId, int code) throws SQLException {
        String sql = "SELECT * FROM campus_category cc, campus c, program_type pt, shift s, category ct "
                + "WHERE cc.campus_id = c.campus_id "
                + "AND cc.program_type_id = pt.program_type_id "
                + "AND cc.shift_id = s.shift_id "
                + "AND cc.category_id = ct.category_id "
                + "AND c.campus_id = " + campusId + " "
                + "AND pt.program_type_id = " + programTypeId + " "
                + "AND s.shift_id = " + shiftId + " "
                + "AND ct.code = " + code;

        //System.out.println(sql);
        try (Statement st = con.createStatement();
                ResultSet rs = st.executeQuery(sql);) {

            CampusCategory ob = null;

            if (rs.next()) {
                ob = new CampusCategory();
                ob.setCampusCategoryId(rs.getInt("campus_category_id"));
                ob.setCampusId(rs.getInt("cc.campus_id"));
                ob.setCampusIsMain(rs.getBoolean("c.is_main"));
                ob.setProgramTypeId(rs.getInt("cc.program_type_id"));
                ob.setCategoryId(rs.getInt("cc.category_id"));
                ob.setShiftId(rs.getInt("cc.shift_id"));
                ob.setOnPercent((rs.getBoolean("cc.on_percent")));
                ob.setPercent(rs.getInt("cc.percent"));
                ob.setDisplayOrder(rs.getInt("cc.display_order"));
                ob.setRemarks(rs.getString("cc.remarks"));
                ob.setCategoryCode(code);
                ob.setCategoryName(rs.getString("ct.name"));
                ob.setShiftName(rs.getString("s.name"));
                ob.setMorning(rs.getBoolean("s.is_morning"));
            }

            return ob;
        }
    }

    public static List<CampusCategory> getCampusCategories(int campusId, int programTypeId) throws SQLException {
        String sql = "SELECT * FROM campus_category cc, campus c, program_type pt, shift s, category ct "
                + "WHERE cc.campus_id = c.campus_id "
                + "AND cc.program_type_id = pt.program_type_id "
                + "AND cc.shift_id = s.shift_id "
                + "AND cc.category_id = ct.category_id "
                + "AND c.campus_id = " + campusId + " "
                + "AND pt.program_type_id = " + programTypeId + " "
                + "ORDER BY s.is_morning DESC";

        //System.out.println(sql);
        try (Statement st = con.createStatement();
                ResultSet rs = st.executeQuery(sql);) {

            List<CampusCategory> list = new ArrayList<>();

            while (rs.next()) {
                CampusCategory ob = new CampusCategory();

                ob.setCampusCategoryId(rs.getInt("campus_category_id"));
                ob.setCampusId(rs.getInt("cc.campus_id"));
                ob.setCampusIsMain(rs.getBoolean("c.is_main"));
                ob.setProgramTypeId(rs.getInt("cc.program_type_id"));
                ob.setCategoryId(rs.getInt("cc.category_id"));
                ob.setShiftId(rs.getInt("cc.shift_id"));
                ob.setOnPercent((rs.getBoolean("cc.on_percent")));
                ob.setPercent(rs.getInt("cc.percent"));
                ob.setDisplayOrder(rs.getInt("cc.display_order"));
                ob.setRemarks(rs.getString("cc.remarks"));
                ob.setCategoryCode(rs.getInt("ct.code"));
                ob.setCategoryName(rs.getString("ct.name"));
                ob.setMorning(rs.getBoolean("s.is_morning"));

                list.add(ob);
            }

            return list;
        }
    }

    public static List<CampusCategory> getMainCampusCategories(boolean isMain, int programTypeId) throws SQLException {
        String sql = "SELECT * FROM campus_category cc, campus c, program_type pt, shift s, category ct "
                + "WHERE cc.campus_id = c.campus_id "
                + "AND cc.program_type_id = pt.program_type_id "
                + "AND cc.shift_id = s.shift_id "
                + "AND cc.category_id = ct.category_id "
                + "AND c.is_main = " + isMain + " "
                + "AND pt.program_type_id = " + programTypeId + " "
                + "ORDER BY s.is_morning DESC";

        //System.out.println(sql);
        try (Statement st = con.createStatement();
                ResultSet rs = st.executeQuery(sql);) {

            List<CampusCategory> list = new ArrayList<>();

            while (rs.next()) {
                CampusCategory ob = new CampusCategory();

                ob.setCampusCategoryId(rs.getInt("campus_category_id"));
                ob.setCampusId(rs.getInt("cc.campus_id"));
                ob.setCampusIsMain(rs.getBoolean("c.is_main"));
                ob.setProgramTypeId(rs.getInt("cc.program_type_id"));
                ob.setCategoryId(rs.getInt("cc.category_id"));
                ob.setShiftId(rs.getInt("cc.shift_id"));
                ob.setOnPercent((rs.getBoolean("cc.on_percent")));
                ob.setPercent(rs.getInt("cc.percent"));
                ob.setDisplayOrder(rs.getInt("cc.display_order"));
                ob.setRemarks(rs.getString("cc.remarks"));
                ob.setCategoryCode(rs.getInt("ct.code"));
                ob.setCategoryName(rs.getString("ct.name"));
                ob.setMorning(rs.getBoolean("s.is_morning"));

                list.add(ob);
            }

            return list;
        }
    }

    public static Jurisdiction getJurisdiction(int campusId, int districtId) throws SQLException {
        String sql = "SELECT * FROM jurisdiction j, campus c, district d "
                + "WHERE j.campus_id = c.campus_id "
                + "AND j.district_id = d.district_id "
                + "AND c.campus_id = " + campusId + " "
                + "AND d.district_id=" + districtId;

        //System.out.println(sql);
        try (Statement st = con.createStatement();
                ResultSet rs = st.executeQuery(sql);) {

            Jurisdiction ob = null;
            if (rs.next()) {
                ob = new Jurisdiction();

                ob.setCampusId(rs.getInt("j.campus_id"));
                ob.setDistrictId(rs.getInt("j.district_id"));
                ob.setTalukaId(rs.getInt("j.taluka_id"));
                ob.setIsJurisdiction((rs.getBoolean("j.is_jurisdiction")));
                ob.setRemarks(rs.getString("j.remarks"));
            }
            return ob;
        }
    }

    public static int getSubjectId(String code) throws SQLException {
        String sql = "SELECT * FROM subject WHERE remarks = '" + code + "'";

        //System.out.println(sql);
        try (Statement st = con.createStatement();
                ResultSet rs = st.executeQuery(sql);) {

            int id = 0;
            if (rs.next()) {
                id = rs.getInt("subject_id");
            }
            return id;
        }
    }

    public static CandidateProgramOfStudy getCandidateProgramOfStudy(int cposId, int candidateId) throws SQLException {
        String sql = "SELECT * FROM candidate_program_of_study cnpos, candidate c, campus_program_of_study cpos "
                + "WHERE cnpos.candidate_id = c.candidate_id "
                + "AND cnpos.campus_program_of_study_id = " + cposId + " "
                + "AND c.candidate_id = " + candidateId;

        //System.out.println(sql);
        try (Statement st = con.createStatement();
                ResultSet rs = st.executeQuery(sql);) {

            CandidateProgramOfStudy ob = null;
            if (rs.next()) {
                ob = new CandidateProgramOfStudy();
                ob.setCampusProgramOfStudyId(rs.getInt("cnpos.campus_program_of_study_id"));
                ob.setCandidateProgramOfStudyId(rs.getInt("cnpos.candidate_program_of_study_id"));
                ob.setCandidateId(rs.getInt("cnpos.candidate_id"));
                ob.setChoiceNo(rs.getInt("cnpos.choice_no"));
                ob.setRemarks(rs.getString("cnpos.remarks"));
            }
            return ob;
        }
    }

    public static List<Integer> getCNPOSChoicesCode(int shiftId, int campusId, int candidateId) throws SQLException {
        String sql = "SELECT pos.p_code FROM candidate_program_of_study cnpos "
                + "INNER JOIN campus_program_of_study cpos ON cpos.campus_program_of_study_id = cnpos.campus_program_of_study_id "
                + "INNER JOIN program_of_study pos ON pos.program_of_study_id = cpos.program_of_study_id "
                + "WHERE cpos.shift_id = " + shiftId + " "
                + "AND cpos.campus_id = " + campusId + " "
                + "AND cnpos.candidate_id = " + candidateId;

        //System.out.println(sql);
        try (Statement st = con.createStatement();
                ResultSet rs = st.executeQuery(sql);) {

            List<Integer> list = new ArrayList<>();
            while (rs.next()) {
                list.add(rs.getInt("pos.p_code"));
            }
            return list;
        }
    }

    public static List<Integer> getCNPOSChoicesCode(int shiftId, boolean isMain, int candidateId) throws SQLException {
        String sql = "SELECT pos.p_code FROM candidate_program_of_study cnpos "
                + "INNER JOIN campus_program_of_study cpos ON cpos.campus_program_of_study_id = cnpos.campus_program_of_study_id "
                + "INNER JOIN program_of_study pos ON pos.program_of_study_id = cpos.program_of_study_id "
                + "INNER JOIN campus c ON c.campus_id = cpos.campus_id "
                + "WHERE cpos.shift_id = " + shiftId + " "
                + "AND c.is_main = " + isMain + " "
                + "AND cnpos.candidate_id = " + candidateId;

        //System.out.println(sql);
        try (Statement st = con.createStatement();
                ResultSet rs = st.executeQuery(sql);) {

            List<Integer> list = new ArrayList<>();
            while (rs.next()) {
                list.add(rs.getInt("pos.p_code"));
            }
            return list;
        }
    }

    public static List<Candidate> getCandidates(int cposId, int shiftId, int admissionYearId, String categoryCode, int detailOf) throws SQLException {
//        String sql = "SELECT * FROM candidate c, candidate_program_of_study cnpos, applied_category apc, district d, division dv, province pv, credential_details cd "
//                + "WHERE c.candidate_id = cnpos.candidate_id "
//                + "AND apc.candidate_id = c.candidate_id "
//                + "AND d.district_id = c.district_id "
//                + "AND dv.division_id = d.division_id "
//                + "AND pv.province_id = dv.province_id "
//                + "AND cd.candidate_id = c.candidate_id "
//                + "AND cnpos.campus_program_of_study_id = " + cposId + " "
//                + "AND apc.category_code = '" + categoryCode + "' "
//                + "AND c.admission_year_id = " + admissionYearId + " "
//                + "AND c.test_score > 0 "
//                + "AND c.is_objection = false "
//                + "AND cd.detail_of = " + detailOf + " "
//                + "ORDER BY c.percentage DESC";
        String sql = "SELECT c.candidate_id, c.district_id, c.seat_no, c.name, c.fathers_name, c.surname, c.gender, c.area, "
                + "c.years_degree, c.test_score, c.percentage, c.deduction_marks, cnpos.candidate_program_of_study_id, "
                + "cnpos.choice_no, pv.code, cd.group_, d.name AS district_name, c.objection_remarks, c.optional_subject_1, c.optional_subject_2, "
                + "c.optional_subject_3, ald.admission_list_details_id, ald.campus_category_id, ald.cpos_group_id "
                + "FROM candidate_program_of_study cnpos "
                + "INNER JOIN candidate c ON c.candidate_id = cnpos.candidate_id "
                + "INNER JOIN applied_category apc ON apc.candidate_id = c.candidate_id "
                + "INNER JOIN district d ON d.district_id = c.district_id "
                + "INNER JOIN division dv ON dv.division_id = d.division_id "
                + "INNER JOIN province pv ON pv.province_id = dv.province_id "
                + "INNER JOIN credential_details cd ON cd.candidate_id = c.candidate_id "
                + "LEFT JOIN admission_list_details ald ON c.candidate_id = ald.candidate_id "
                + "WHERE ald.admission_list_details_id IS NULL "
                + "AND cnpos.campus_program_of_study_id = " + cposId + " "
                + "AND apc.category_code = '" + categoryCode + "' "
                + "AND c.admission_year_id = " + admissionYearId + " "
                + "AND c.test_score > 0 "
                + "AND c.is_objection = false "
                + "AND cd.detail_of = " + detailOf
                + " "
                + "UNION"
                + " "
                + "SELECT c.candidate_id, c.district_id, c.seat_no, c.name, c.fathers_name, c.surname, c.gender, c.area, "
                + "c.years_degree, c.test_score, c.percentage, c.deduction_marks, cnpos.candidate_program_of_study_id, "
                + "cnpos.choice_no, pv.code, cd.group_, d.name AS district_name, c.objection_remarks, c.optional_subject_1, c.optional_subject_2, "
                + "c.optional_subject_3, ald.admission_list_details_id, ald.campus_category_id, ald.cpos_group_id "
                + "FROM candidate_program_of_study cnpos "
                + "INNER JOIN candidate c ON c.candidate_id = cnpos.candidate_id "
                + "INNER JOIN applied_category apc ON apc.candidate_id = c.candidate_id "
                + "INNER JOIN district d ON d.district_id = c.district_id "
                + "INNER JOIN division dv ON dv.division_id = d.division_id "
                + "INNER JOIN province pv ON pv.province_id = dv.province_id "
                + "INNER JOIN credential_details cd ON cd.candidate_id = c.candidate_id "
                + "INNER JOIN admission_list_details ald ON ald.candidate_id = c.candidate_id "
                + "INNER JOIN cpos_group cposg ON cposg.cpos_group_id = ald.cpos_group_id "
                + "INNER JOIN campus_program_of_study cpos ON cpos.campus_program_of_study_id = cposg.campus_program_of_study_id "
                + "INNER JOIN candidate_program_of_study cnpos2 ON cnpos2.candidate_program_of_study_id = ald.candidate_program_of_study_id "
                + "INNER JOIN part_registry pr ON pr.admission_list_details_id = ald.admission_list_details_id "
                + "WHERE pr.part_registry_id IS NOT NULL "
                + "AND cnpos.campus_program_of_study_id = " + cposId + " "
                + "AND apc.category_code = '" + categoryCode + "' "
                + "AND c.admission_year_id = " + admissionYearId + " "
                + "AND cd.detail_of = " + detailOf + " "
                + "AND ald.admission_list_details_id NOT IN (SELECT admission_list_details_id FROM part_registry WHERE type = " + IConstant.Challan.RETAIN + ") "
                + "AND (cnpos.choice_no < ( "
                + "    SELECT IFNULL(MIN(cnposs.choice_no), cnpos.choice_no + 1) choice_no FROM admission_list_details ald "
                + "    INNER JOIN candidate_program_of_study cnposs ON cnposs.candidate_program_of_study_id = ald.candidate_program_of_study_id "
                + "    INNER JOIN cpos_group cposg ON cposg.cpos_group_id = ald.cpos_group_id "
                + "    INNER JOIN campus_program_of_study cpos ON cpos.campus_program_of_study_id = cposg.campus_program_of_study_id "
                + "    WHERE ald.candidate_id = c.candidate_id "
                + "    AND cpos.shift_id = " + shiftId + " "
                + ") "
                + "OR cnpos.choice_no < cnpos2.choice_no) "
                + " "
                + "UNION"
                + " "
                + "SELECT c.candidate_id, c.district_id, c.seat_no, c.name, c.fathers_name, c.surname, c.gender, c.area, "
                + "c.years_degree, c.test_score, c.percentage, c.deduction_marks, cnpos.candidate_program_of_study_id, "
                + "cnpos.choice_no, pv.code, cd.group_, d.name AS district_name, c.objection_remarks, c.optional_subject_1, c.optional_subject_2, "
                + "c.optional_subject_3, ald.admission_list_details_id, ald.campus_category_id, ald.cpos_group_id "
                + "FROM candidate_program_of_study cnpos "
                + "INNER JOIN candidate c ON c.candidate_id = cnpos.candidate_id "
                + "INNER JOIN applied_category apc ON apc.candidate_id = c.candidate_id "
                + "INNER JOIN district d ON d.district_id = c.district_id "
                + "INNER JOIN division dv ON dv.division_id = d.division_id "
                + "INNER JOIN province pv ON pv.province_id = dv.province_id "
                + "INNER JOIN credential_details cd ON cd.candidate_id = c.candidate_id "
                + "INNER JOIN admission_list_details ald ON ald.candidate_id = c.candidate_id "
                + "INNER JOIN cpos_group cposg ON cposg.cpos_group_id = ald.cpos_group_id "
                + "INNER JOIN campus_program_of_study cpos ON cpos.campus_program_of_study_id = cposg.campus_program_of_study_id "
                + "INNER JOIN candidate_program_of_study cnpos2 ON cnpos2.candidate_program_of_study_id = ald.candidate_program_of_study_id "
                + "INNER JOIN part_registry pr ON pr.admission_list_details_id = ald.admission_list_details_id "
                + "WHERE pr.part_registry_id IS NOT NULL "
                + "AND cnpos.campus_program_of_study_id = " + cposId + " "
                + "AND apc.category_code = '" + categoryCode + "' "
                + "AND c.admission_year_id = " + admissionYearId + " "
                //                + "AND cnpos.choice_no < cnpos2.choice_no ""SELECT c.candidate_id, c.district_id, c.seat_no, c.name, c.fathers_name, c.surname, c.gender, c.area, "
                + "c.years_degree, c.test_score, c.percentage, c.deduction_marks, cnpos.candidate_program_of_study_id, "
                + "cnpos.choice_no, pv.code, cd.group_, d.name AS district_name, c.objection_remarks, c.optional_subject_1, c.optional_subject_2, "
                + "c.optional_subject_3, ald.admission_list_details_id, ald.campus_category_id, ald.cpos_group_id "
                + "FROM candidate_program_of_study cnpos "
                + "INNER JOIN candidate c ON c.candidate_id = cnpos.candidate_id "
                + "INNER JOIN applied_category apc ON apc.candidate_id = c.candidate_id "
                + "INNER JOIN district d ON d.district_id = c.district_id "
                + "INNER JOIN division dv ON dv.division_id = d.division_id "
                + "INNER JOIN province pv ON pv.province_id = dv.province_id "
                + "INNER JOIN credential_details cd ON cd.candidate_id = c.candidate_id "
                + "LEFT JOIN admission_list_details ald ON c.candidate_id = ald.candidate_id "
                + "WHERE ald.admission_list_details_id IS NULL "
                + "AND cnpos.campus_program_of_study_id = " + cposId + " "
                + "AND apc.category_code = '" + categoryCode + "' "
                + "AND c.admission_year_id = " + admissionYearId + " "
                + "AND c.test_score > 0 "
                + "AND c.is_objection = false "
                + "AND cd.detail_of = " + detailOf
                + " "
                + "UNION"
                + " "
                + "SELECT c.candidate_id, c.district_id, c.seat_no, c.name, c.fathers_name, c.surname, c.gender, c.area, "
                + "c.years_degree, c.test_score, c.percentage, c.deduction_marks, cnpos.candidate_program_of_study_id, "
                + "cnpos.choice_no, pv.code, cd.group_, d.name AS district_name, c.objection_remarks, c.optional_subject_1, c.optional_subject_2, "
                + "c.optional_subject_3, ald.admission_list_details_id, ald.campus_category_id, ald.cpos_group_id "
                + "FROM candidate_program_of_study cnpos "
                + "INNER JOIN candidate c ON c.candidate_id = cnpos.candidate_id "
                + "INNER JOIN applied_category apc ON apc.candidate_id = c.candidate_id "
                + "INNER JOIN district d ON d.district_id = c.district_id "
                + "INNER JOIN division dv ON dv.division_id = d.division_id "
                + "INNER JOIN province pv ON pv.province_id = dv.province_id "
                + "INNER JOIN credential_details cd ON cd.candidate_id = c.candidate_id "
                + "INNER JOIN admission_list_details ald ON ald.candidate_id = c.candidate_id "
                + "INNER JOIN cpos_group cposg ON cposg.cpos_group_id = ald.cpos_group_id "
                + "INNER JOIN campus_program_of_study cpos ON cpos.campus_program_of_study_id = cposg.campus_program_of_study_id "
                + "INNER JOIN candidate_program_of_study cnpos2 ON cnpos2.candidate_program_of_study_id = ald.candidate_program_of_study_id "
                + "INNER JOIN part_registry pr ON pr.admission_list_details_id = ald.admission_list_details_id "
                + "WHERE pr.part_registry_id IS NOT NULL "
                + "AND cnpos.campus_program_of_study_id = " + cposId + " "
                + "AND apc.category_code = '" + categoryCode + "' "
                + "AND c.admission_year_id = " + admissionYearId + " "
                + "AND cd.detail_of = " + detailOf + " "
                + "AND ald.admission_list_details_id NOT IN (SELECT admission_list_details_id FROM part_registry WHERE type = " + IConstant.Challan.RETAIN + ") "
                + "AND (cnpos.choice_no < ( "
                + "    SELECT IFNULL(MIN(cnposs.choice_no), cnpos.choice_no + 1) choice_no FROM admission_list_details ald "
                + "    INNER JOIN candidate_program_of_study cnposs ON cnposs.candidate_program_of_study_id = ald.candidate_program_of_study_id "
                + "    INNER JOIN cpos_group cposg ON cposg.cpos_group_id = ald.cpos_group_id "
                + "    INNER JOIN campus_program_of_study cpos ON cpos.campus_program_of_study_id = cposg.campus_program_of_study_id "
                + "    WHERE ald.candidate_id = c.candidate_id "
                + "    AND cpos.shift_id = " + shiftId + " "
                + ") "
                + "OR cnpos.choice_no < cnpos2.choice_no) "
                + " "
                + "UNION"
                + " "
                + "SELECT c.candidate_id, c.district_id, c.seat_no, c.name, c.fathers_name, c.surname, c.gender, c.area, "
                + "c.years_degree, c.test_score, c.percentage, c.deduction_marks, cnpos.candidate_program_of_study_id, "
                + "cnpos.choice_no, pv.code, cd.group_, d.name AS district_name, c.objection_remarks, c.optional_subject_1, c.optional_subject_2, "
                + "c.optional_subject_3, ald.admission_list_details_id, ald.campus_category_id, ald.cpos_group_id "
                + "FROM candidate_program_of_study cnpos "
                + "INNER JOIN candidate c ON c.candidate_id = cnpos.candidate_id "
                + "INNER JOIN applied_category apc ON apc.candidate_id = c.candidate_id "
                + "INNER JOIN district d ON d.district_id = c.district_id "
                + "INNER JOIN division dv ON dv.division_id = d.division_id "
                + "INNER JOIN province pv ON pv.province_id = dv.province_id "
                + "INNER JOIN credential_details cd ON cd.candidate_id = c.candidate_id "
                + "INNER JOIN admission_list_details ald ON ald.candidate_id = c.candidate_id "
                + "INNER JOIN cpos_group cposg ON cposg.cpos_group_id = ald.cpos_group_id "
                + "INNER JOIN campus_program_of_study cpos ON cpos.campus_program_of_study_id = cposg.campus_program_of_study_id "
                + "INNER JOIN candidate_program_of_study cnpos2 ON cnpos2.candidate_program_of_study_id = ald.candidate_program_of_study_id "
                + "INNER JOIN part_registry pr ON pr.admission_list_details_id = ald.admission_list_details_id "
                + "WHERE pr.part_registry_id IS NOT NULL "
                + "AND cnpos.campus_program_of_study_id = " + cposId + " "
                + "AND apc.category_code = '" + categoryCode + "' "
                + "AND c.admission_year_id = " + admissionYearId + " "
                //                + "AND cnpos.choice_no < cnpos2.choice_no "
                + "AND cd.detail_of = " + detailOf + " "
                + "AND ald.admission_list_details_id NOT IN (SELECT admission_list_details_id FROM part_registry WHERE type = " + IConstant.Challan.RETAIN + ") "
                + "AND c.candidate_id NOT IN ( "
                + "    SELECT candidate_id FROM campus_program_of_study cpos "
                + "    INNER JOIN cpos_group cposg ON cposg.campus_program_of_study_id = cpos.campus_program_of_study_id "
                + "    INNER JOIN admission_list_details ald ON ald.cpos_group_id = cposg.cpos_group_id "
                + "    WHERE cpos.shift_id = " + shiftId + " "
                + ") "
                + "ORDER BY percentage DESC";

        System.out.println(sql);
        try (Statement st = con.createStatement();
                ResultSet rs = st.executeQuery(sql);) {

            List<admission.controller.beans.Candidate> list = new ArrayList<>();
            while (rs.next()) {
                admission.controller.beans.Candidate ob = new Candidate();

                ob.setCandidateId(rs.getInt("candidate_id"));
                ob.setDistrictId(rs.getInt("district_id"));
                ob.setSeatNo(rs.getInt("seat_no"));
                ob.setName(rs.getString("name"));
                ob.setFathersName(rs.getString("fathers_name"));
                ob.setSurname(rs.getString("surname"));
                ob.setGender(rs.getString("gender"));
                ob.setArea(rs.getString("area"));
                ob.setYearsDegree(rs.getInt("years_degree"));
                ob.setTestScore(rs.getInt("test_score"));
                ob.setPercentage(rs.getFloat("percentage"));
                ob.setDeductionMarks(rs.getInt("deduction_marks"));
                ob.setCandidateProgramOfStudyId(rs.getInt("candidate_program_of_study_id"));
                ob.setChoiceNo(rs.getInt("choice_no"));
                ob.setProvinceCode(rs.getString("code"));
                ob.setGroup(rs.getString("group_"));
                ob.setDistrictName(rs.getString("district_name"));
                ob.setObjectionRemarks(rs.getString("objection_remarks"));
                ob.setSubject1(rs.getString("optional_subject_1"));
                ob.setSubject2(rs.getString("optional_subject_2"));
                ob.setSubject3(rs.getString("optional_subject_3"));
                ob.setAdmissionListDetailsId(rs.getInt("admission_list_details_id"));
                ob.setSelectedCampusCategoryId(rs.getInt("campus_category_id"));
                ob.setSelectedCposgId(rs.getInt("cpos_group_id"));

                list.add(ob);
            }

            return list;
        }
    }

    public static boolean isCandidateSelected(int candidateId, int cposgId, int ccId) throws SQLException {
        String sql = "SELECT * FROM admission_list_details ald "
                + "WHERE ald.cpos_group_id = " + cposgId + " "
                + "AND ald.campus_category_id = " + ccId + " "
                + "AND ald.candidate_id = " + candidateId;

        try (Statement st = con.createStatement();
                ResultSet rs = st.executeQuery(sql);) {

            return rs.next();
        }
    }

    public static List<Candidate> getCandidates(int admissionYearId, int campusId, int programTypeId) throws SQLException {
        String sql = "SELECT * FROM candidate c, applied_campus ac, district d, division dv, province pv "
                + "WHERE c.candidate_id = ac.candidate_id "
                + "AND d.district_id = c.district_id "
                + "AND dv.division_id = d.division_id "
                + "AND pv.province_id = dv.province_id "
                + "AND ac.campus_id = " + campusId + " "
                + "AND c.admission_year_id = " + admissionYearId + " "
                + "AND c.program_type_id = " + programTypeId + " "
                + "ORDER BY c.seat_no";

//        System.out.println(sql);
        try (Statement st = con.createStatement();
                ResultSet rs = st.executeQuery(sql);) {

            List<admission.controller.beans.Candidate> list = new ArrayList<>();
            while (rs.next()) {
                admission.controller.beans.Candidate ob = new Candidate();

                ob.setCandidateId(rs.getInt("c.candidate_id"));
                ob.setDistrictId(rs.getInt("c.district_id"));
                ob.setSeatNo(rs.getInt("c.seat_no"));
                ob.setName(rs.getString("c.name"));
                ob.setFathersName(rs.getString("c.fathers_name"));
                ob.setSurname(rs.getString("c.surname"));
                ob.setGender(rs.getString("c.gender"));
                ob.setArea(rs.getString("c.area"));
                ob.setYearsDegree(rs.getInt("c.years_degree"));
                ob.setTestScore(rs.getInt("c.test_score"));
                ob.setPercentage(rs.getFloat("c.percentage"));
                ob.setDeductionMarks(rs.getInt("c.deduction_marks"));
//                ob.setCandidateProgramOfStudyId(rs.getInt("cnpos.candidate_program_of_study_id"));
//                ob.setChoiceNo(rs.getInt("cnpos.choice_no"));
                ob.setProvinceCode(rs.getString("pv.code"));
//                ob.setGroup(rs.getString("cd.group_"));
                ob.setDistrictName(rs.getString("d.name"));
                ob.setObjection(rs.getBoolean("c.is_objection"));
                ob.setObjectionRemarks(rs.getString("c.objection_remarks"));

                list.add(ob);
            }

            return list;
        }
    }

    public static int getCandidatesCount(int admissionYearId, int campusId, int programTypeId) throws SQLException {
        String sql = "SELECT count(cn.candidate_id) total FROM applied_campus ac, candidate cn, campus c, admission_year ay, program_type pt "
                + "WHERE ac.campus_id = c.campus_id "
                + "AND cn.candidate_id = ac.candidate_id "
                + "AND cn.admission_year_id = ay.admission_year_id "
                + "AND cn.program_type_id = pt.program_type_id "
                + "AND ay.admission_year_id = " + admissionYearId + " "
                + "AND c.campus_id = " + campusId + " "
                + "AND pt.program_type_id = " + programTypeId;

        try (Statement st = con.createStatement();
                ResultSet rs = st.executeQuery(sql)) {
            if (rs.next()) {
                return rs.getInt("total");
            }
            return 0;
        }
    }

    public static int getFreeCandidatesCount(int admissionYearId, int campusCategoryId, int cposgId, int districtId, int area) throws SQLException {
//        String sql = "SELECT COUNT(ald.admission_list_details_id) AS total_consumed FROM candidate_program_of_study cnpos " 
//                + "INNER JOIN candidate c ON c.candidate_id = cnpos.candidate_id " 
//                + "INNER JOIN admission_list_details ald ON ald.candidate_id = c.candidate_id " 
//                + "INNER JOIN part_registry pr ON pr.admission_list_details_id = ald.admission_list_details_id " 
//                + "WHERE pr.part_registry_id IS NOT NULL " 
//                + "AND ald.cpos_group_id = " + cposgId + " "
//                + "AND ald.campus_category_id = " + campusCategoryId + " " 
//                + "AND c.admission_year_id = " + admissionYearId + " " 
//                + "AND c.test_score > 0 " 
//                + "AND c.is_objection = false "
//                + "AND c.area = '" + area + "' "
//                + "AND c.district_id = " + districtId + " "
//                + "AND (cnpos.choice_no = 1 " 
//                + "OR ald.admission_list_details_id IN (SELECT admission_list_details_id FROM part_registry WHERE `type` = 1))";

        String sql = "SELECT COUNT(DISTINCT(ald.admission_list_details_id)) AS seats FROM admission_list_details ald "
                + "INNER JOIN admission_list al ON al.admission_list_id = ald.admission_list_id "
                + "INNER JOIN candidate c ON c.candidate_id = ald.candidate_id "
                + "INNER JOIN candidate_program_of_study cnpos ON cnpos.candidate_id = ald.candidate_id "
                //                + "INNER JOIN part_registry pr ON pr.admission_list_details_id = ald.admission_list_details_id "
                + "WHERE "
                //                + "pr.part_registry_id IS NOT NULL "
                //                + "AND "
                + "ald.cpos_group_id = " + cposgId + " "
                + "AND ald.campus_category_id = " + campusCategoryId + " "
                + "AND c.admission_year_id = " + admissionYearId + " "
                + "AND c.district_id = " + districtId + " "
                + "AND c.area = " + area + " "
                + "AND ald.admission_list_details_id IN (SELECT admission_list_details_id FROM part_registry)";

//        System.out.println(sql);
        try (Statement st = con.createStatement();
                ResultSet rs = st.executeQuery(sql)) {

            if (rs.next()) {
                return rs.getInt("seats");
            }
            return 0;
        }
    }

    public static int getCandidatesChoices(int admissionYearId, int campusId, int programTypeId) throws SQLException {
        String sql = "SELECT count(cp.candidate_program_of_study_id) total FROM candidate c, applied_campus ac, candidate_program_of_study cp "
                + "WHERE cp.candidate_id = c.candidate_id "
                + "AND ac.candidate_id = c.candidate_id "
                + "AND c.admission_year_id = " + admissionYearId + " "
                + "AND c.program_type_id = " + programTypeId + " "
                + "AND ac.campus_id = " + campusId;

        try (Statement st = con.createStatement();
                ResultSet rs = st.executeQuery(sql)) {
            if (rs.next()) {
                return rs.getInt("total");
            }
            return 0;
        }
    }

    public static int[][] getCandidatesId(int admissionYearId, int campusId, int programTypeId) throws SQLException {
        String sql = "SELECT * FROM applied_campus ac, candidate cn, campus c, admission_year ay, program_type pt "
                + "WHERE ac.campus_id = c.campus_id "
                + "AND cn.candidate_id = ac.candidate_id "
                + "AND cn.admission_year_id = ay.admission_year_id "
                + "AND cn.program_type_id = pt.program_type_id "
                + "AND ay.admission_year_id = " + admissionYearId + " "
                + "AND c.campus_id = " + campusId + " "
                + "AND pt.program_type_id = " + programTypeId + " "
                + "AND cn.test_score > 0";

        int candidates = getCandidatesCount(admissionYearId, campusId, programTypeId);
        int array[][] = new int[candidates][];

        try (Statement st = con.createStatement();
                ResultSet rs = st.executeQuery(sql)) {
            int i = 0;
            while (rs.next()) {
                int ar[] = new int[4];
                ar[0] = rs.getInt("cn.candidate_id");
                ar[1] = rs.getInt("cn.deduction_marks");
                ar[2] = rs.getInt("cn.test_score");
                ar[3] = rs.getInt("cn.seat_no");
                array[i] = ar;
                i++;
            }
            return array;
        }
    }

    public static int getCandidateId(int admissionYearId, int programTypeId, int seatNo) throws SQLException {
        String sql = "SELECT * FROM candidate WHERE seat_no = " + seatNo + " "
                + "AND admission_year_id = " + admissionYearId + " "
                + "AND program_type_id = " + programTypeId;

        try (Statement st = con.createStatement();
                ResultSet rs = st.executeQuery(sql)) {
            int i = 0;
            if (rs.next()) {
                i = rs.getInt("candidate_id");
            }
            return i;
        }
    }

    public static int getCandidateDeductionMarks(int candidateId) throws SQLException {
        String sql = "SELECT * FROM candidate WHERE candidate_id = " + candidateId;

        try (Statement st = con.createStatement();
                ResultSet rs = st.executeQuery(sql)) {
            int i = 0;
            if (rs.next()) {
                i = rs.getInt("deduction_marks");
            }
            return i;
        }
    }

    public static int getProgramId(int code) throws SQLException {
        String sql = "SELECT * FROM program WHERE remarks = '" + code + "'";

        try (Statement st = con.createStatement();
                ResultSet rs = st.executeQuery(sql)) {
            int i = 0;
            if (rs.next()) {
                i = rs.getInt("program_id");
            }
            return i;
        }
    }

    public static String getSubjectCode(String id) throws SQLException {
        String sql = "SELECT * FROM subject WHERE subject_id = " + id;

        try (Statement st = con.createStatement();
                ResultSet rs = st.executeQuery(sql)) {
            String s = "0";
            if (rs.next()) {
                s = rs.getString("remarks");
                s = s.isEmpty() ? "0" : s;
            }
            return s;
        }
    }

    public static String getProgramCode(String id) throws SQLException {
        String sql = "SELECT * FROM program WHERE program_id = " + id;

        try (Statement st = con.createStatement();
                ResultSet rs = st.executeQuery(sql)) {
            String s = "0";
            if (rs.next()) {
                s = rs.getString("remarks");
                s = s.isEmpty() ? "0" : s;
            }
            return s;
        }
    }

    public static int updateCandidates(int candidateId, String percentage) throws SQLException {
        String sql = "UPDATE candidate SET percentage = " + percentage + " WHERE candidate_id = " + candidateId;

        try (Statement st = con.createStatement();) {
            int i = st.executeUpdate(sql);
            return i;
        }
    }

    public static int updateCandidate(int ayId, int ptId, int seatNo, boolean objection, String objRemarks) throws SQLException {
        String sql = "UPDATE candidate SET is_objection = " + objection + ", objection_remarks = '" + objRemarks + "' WHERE admission_year_id = " + ayId + " AND program_type_id = " + ptId + " AND seat_no = " + seatNo;

        try (Statement st = con.createStatement();) {
            int i = st.executeUpdate(sql);
            return i;
        }
    }

    public static int updateCandidate(int ayId, int ptId, String seatNo, String op1, String op2, String op3) throws SQLException {
        String sql = "UPDATE candidate SET optional_subject_1 = '" + op1 + "', optional_subject_2 = '" + op2 + "', optional_subject_3 = '" + op3 + "' WHERE admission_year_id = " + ayId + " AND program_type_id = " + ptId + " AND seat_no = " + seatNo;

        try (Statement st = con.createStatement();) {
            int i = st.executeUpdate(sql);
            return i;
        }
    }

    public static int updateCandidate(int ayId, int ptId, int seatNo, String address, String cnic) throws SQLException {
        String sql = "UPDATE candidate SET present_postel_address = ?, cnic_no = ? WHERE admission_year_id = ? AND program_type_id = ? AND seat_no = ?";

        try (PreparedStatement pst = con.prepareStatement(sql);) {
            pst.setString(1, address);
            pst.setString(2, cnic);
            pst.setInt(3, ayId);
            pst.setInt(4, ptId);
            pst.setInt(5, seatNo);

            int i = pst.executeUpdate();
            return i;
        }
    }

    public static int updateCandidates(int candidateId, int periods) throws SQLException {
        String sql = "UPDATE candidate SET years_degree = " + periods + " WHERE candidate_id = " + candidateId;

        try (Statement st = con.createStatement();) {
            int i = st.executeUpdate(sql);
            return i;
        }
    }

    public static int updateCredential(int candidateId, String group, String obtMrk, String seatNo, int issuerId, String passingYear, String totalMarks, int detailsOf) throws SQLException {
        String sql = "UPDATE credential_details SET group_ = '" + group + "', marks_obtained = " + obtMrk + ", seat_no = " + seatNo + ", issuer_id = " + issuerId + ", passing_year = " + passingYear + ", total_marks = " + totalMarks + " WHERE candidate_id = " + candidateId + " AND detail_of = " + detailsOf;

        try (Statement st = con.createStatement();) {
            int i = st.executeUpdate(sql);
            return i;
        }
    }

    public static int updateCredential(int candidateId, String group, int detailsOf) throws SQLException {
        String sql = "UPDATE credential_details SET group_ = '" + group + "' WHERE candidate_id = " + candidateId + " AND detail_of = " + detailsOf;

        try (Statement st = con.createStatement();) {
            int i = st.executeUpdate(sql);
            return i;
        }
    }

    public static CredentialDetails[] getCredentialDetails(int candidateId) throws SQLException {
        String sql = "SELECT * FROM credential_details cd "
                + "INNER JOIN issuer i ON i.issuer_id = cd.issuer_id "
                + "WHERE cd.candidate_id = " + candidateId + " "
                + "ORDER BY cd.credential_details_id";

        //System.out.println(sql);
        try (Statement st = con.createStatement();
                ResultSet rs = st.executeQuery(sql);) {

            CredentialDetails[] array = new CredentialDetails[3];
            //List<CredentialDetails> list = new ArrayList<>();
            int i = 0;
            while (rs.next()) {
                CredentialDetails ob = new CredentialDetails();

                ob.setCredentialDetailsId(rs.getInt("cd.credential_details_id"));
                ob.setCandidateId(rs.getInt("cd.candidate_id"));
                ob.setIssuerId(rs.getInt("cd.issuer_id"));
                ob.setTotalMarks(rs.getInt("cd.total_marks"));
                ob.setMarksObtained(rs.getFloat("cd.marks_obtained"));
                ob.setGroup(rs.getString("cd.group_"));
                ob.setSeatNo(rs.getString("cd.seat_no"));
                ob.setPassingYear(rs.getInt("cd.passing_year"));
                ob.setDetailOf(DetailOfEnum.values()[rs.getInt("cd.detail_of")]);
                ob.setRemarks(rs.getString("cd.remarks"));
                ob.setIssuerCode(rs.getInt("i.code"));

                array[i] = ob;
                i++;
            }

            return array;
        }
    }

    public static CampusProgramOfStudy getCampusProgramOfStudy(int cposId) throws SQLException {
        String sql = "SELECT * FROM "
                + "campus_program_of_study cpos, shift s "
                + "WHERE s.shift_id = cpos.shift_id "
                + "AND cpos.campus_program_of_study_id = " + cposId;

        //System.out.println(sql);
        try (Statement st = con.createStatement();
                ResultSet rs = st.executeQuery(sql);) {

            CampusProgramOfStudy ob = null;

            if (rs.next()) {
                ob = new CampusProgramOfStudy();

                ob.setCampusProgramOfStudyId(rs.getInt("cpos.campus_program_of_study_id"));
                ob.setCampusId(rs.getInt("cpos.campus_id"));
                ob.setShiftId(rs.getInt("cpos.shift_id"));
                ob.setProgramOfStudyId(rs.getInt("cpos.program_of_study_id"));
                ob.setJurisdiction(JurisdictionEnum.values()[rs.getInt("cpos.jurisdiction")]);
                ob.setShiftIsMorning(rs.getBoolean("s.is_morning"));
                ob.setDisplayOrder(rs.getInt("cpos.display_order"));
                ob.setRemarks(rs.getString("cpos.remarks"));
            }

            return ob;
        }
    }

    public static CampusProgramOfStudy getCampusProgramOfStudy(int campusId, int ptId, int pCode, int shiftId) throws SQLException {
        String sql = "SELECT * FROM campus_program_of_study cpos "
                + "INNER JOIN program_of_study pos ON pos.program_of_study_id = cpos.program_of_study_id "
                + "INNER JOIN shift s ON cpos.shift_id = s.shift_id "
                + "INNER JOIN program p ON pos.program_id = p.program_id "
                + "WHERE cpos.campus_id = " + campusId + " "
                + "AND s.shift_id = " + shiftId + " "
                + "AND p.program_type_id = " + ptId + " "
                + "AND pos.p_code = " + pCode;

        //System.out.println(sql);
        try (Statement st = con.createStatement();
                ResultSet rs = st.executeQuery(sql);) {

            CampusProgramOfStudy ob = null;

            if (rs.next()) {
                ob = new CampusProgramOfStudy();

                ob.setCampusProgramOfStudyId(rs.getInt("cpos.campus_program_of_study_id"));
                ob.setCampusId(rs.getInt("cpos.campus_id"));
                ob.setShiftId(rs.getInt("cpos.shift_id"));
                ob.setProgramOfStudyId(rs.getInt("cpos.program_of_study_id"));
                ob.setJurisdiction(JurisdictionEnum.values()[rs.getInt("cpos.jurisdiction")]);
                ob.setShiftIsMorning(rs.getBoolean("s.is_morning"));
                ob.setDisplayOrder(rs.getInt("cpos.display_order"));
                ob.setRemarks(rs.getString("cpos.remarks"));
            }

            return ob;
        }
    }

    public static List<AdmissionListDetails> getAdmissionListDetail(int candidateId, int admissionListId) throws SQLException {
        String sql = "SELECT * FROM admission_list_details ald, "
                + "cpos_group cposg, "
                + "candidate_program_of_study cnpos, "
                + "campus_category cc, "
                + "category cat, "
                + "campus_program_of_study cpos, "
                + "program_of_study pos, "
                + "shift sh "
                + "WHERE cposg.cpos_group_id = ald.cpos_group_id"
                + "AND cpos.campus_program_of_study_id = cposg.campus_program_of_study_id "
                + "AND pos.program_of_study_id = cpos.program_of_study_id "
                + "AND cc.campus_category_id = ald.campus_category_id "
                + "AND cat.category_id = cc.category_id "
                + "AND sh.shift_id = cc.shift_id "
                + "AND cnpos.candidate_program_of_study_id = ald.candidate_program_of_study_id "
                + "AND ald.candidate_id = " + candidateId + " "
                + "AND ald.admission_list_id = " + admissionListId;

        //System.out.println(sql);
        try (Statement st = con.createStatement();
                ResultSet rs = st.executeQuery(sql);) {

            List<AdmissionListDetails> list = new ArrayList<>();
            while (rs.next()) {
                AdmissionListDetails ald = new AdmissionListDetails();

                ald.setAdmissionListDetailsId(rs.getInt("ald.admission_list_details_id"));
                ald.setRemarks(rs.getString("ald.remarks"));
                ald.setStatus(SelectionStatusEnum.values()[rs.getInt("ald.status")]);

                ald.setCandidateId(rs.getInt("ald.candidate_id"));
                ald.setCampusCategoryName(rs.getString("cat.name"));
                ald.setCposg(rs.getString("cposg.group_"));
                ald.setPosName(rs.getString("pos.name"));
                ald.setShiftName(rs.getString("sh.name"));
                ald.setChoiceNo(rs.getInt("cnpos.choice_no"));
                ald.setPosPCode(rs.getInt("pos.p_code"));

                list.add(ald);
            }

            return list;
        }
    }

    public static List<Object[]> getAdmissionListDetail(int cposgId, int campusCategoryId, int admissionListId) throws SQLException {
        String sql = "SELECT * FROM admission_list_details ald "
                + "INNER JOIN candidate c ON c.candidate_id = ald.candidate_id "
                + "WHERE ald.cpos_group_id = " + cposgId + " "
                + "AND ald.campus_category_id = " + campusCategoryId + " "
                + "AND ald.admission_list_id = " + admissionListId + " "
                + "ORDER BY c.name";

        //System.out.println(sql);
        try (Statement st = con.createStatement();
                ResultSet rs = st.executeQuery(sql);) {

            List<Object[]> list = new ArrayList<>();
            while (rs.next()) {
                Object[] data = new Object[6];

                data[0] = 0;
                data[1] = rs.getInt("c.seat_no");
                data[2] = rs.getString("c.name");
                data[3] = rs.getString("c.fathers_name");
                data[4] = rs.getString("c.surname");
                data[5] = rs.getFloat("c.percentage");

                list.add(data);
            }

            return list;
        }
    }

    public static boolean isPrerequisite(int posId, String group) throws SQLException {
        String sql = "SELECT * FROM "
                + "program_of_study pos, prerequisite pr, program_subject ps, program p "
                + "WHERE p.program_id = ps.program_id "
                + "AND ps.program_subject_id = pr.program_subject_id "
                + "AND pr.program_of_study_id = pos.program_of_study_id "
                + "AND pos.program_of_study_id = " + posId + " "
                + "AND p.remarks = '" + group + "'";

        //System.out.println(sql);
        try (Statement st = con.createStatement();
                ResultSet rs = st.executeQuery(sql);) {

            return rs.next();
        }
    }

    public static boolean isPrerequisite(int posId, int programId) throws SQLException {
        String sql = "SELECT * FROM PREREQUISITE PR "
                + "INNER JOIN PROGRAM_SUBJECT PS ON PR.PROGRAM_SUBJECT_ID = PS.PROGRAM_SUBJECT_ID "
                + "WHERE PR.PROGRAM_OF_STUDY_ID = " + posId
                + "AND PS.PROGRAM_ID = " + programId;

        try (Statement st = con.createStatement();
                ResultSet rs = st.executeQuery(sql);) {

            return rs.next();
        }
    }

    public static boolean isPrerequisite(int posId, String subjectId1, String subjectId2, String subjectId3, String programId) throws SQLException {
        String sql = "SELECT distinct(pr.prerequisite_id) FROM "
                + "program_of_study pos, prerequisite pr, program_subject ps, program p, subject s "
                + "WHERE ps.program_id = " + programId + " "
                + "AND ((s.subject_id = ps.subject_id AND s.is_none = true) "
                + "OR ps.subject_id IN (" + subjectId1 + ", " + subjectId2 + ", " + subjectId3 + ")) "
                + "AND pr.program_subject_id = ps.program_subject_id "
                + "AND pr.program_of_study_id = " + posId;

//        System.out.println(sql);
        try (Statement st = con.createStatement();
                ResultSet rs = st.executeQuery(sql);) {

            return rs.next();
        }
    }

    public static boolean isPrerequisite(int posId, String subjectIds, int programId) throws SQLException {
        String sql = "SELECT DISTINCT(PR.PREREQUISITE_ID) FROM "
                + "PROGRAM_OF_STUDY POS, PREREQUISITE PR, PROGRAM_SUBJECT PS, PROGRAM P, SUBJECT S "
                + "WHERE PS.PROGRAM_ID = " + programId + " "
                + "AND ((S.SUBJECT_ID = PS.SUBJECT_ID AND S.IS_NONE = TRUE) "
                + "OR PS.SUBJECT_ID IN (" + subjectIds + ")) "
                + "AND PR.PROGRAM_SUBJECT_ID = PS.PROGRAM_SUBJECT_ID "
                + "AND PR.PROGRAM_OF_STUDY_ID = " + posId;

//        System.out.println(sql);
        try (Statement st = con.createStatement();
                ResultSet rs = st.executeQuery(sql);) {

            return rs.next();
        }
    }

    public static int getCountPrerequisite(int posId) throws SQLException {
        String sql = "SELECT count(pr.prerequisite_id) total FROM "
                + "program_of_study pos, prerequisite pr "
                + "WHERE pr.program_of_study_id = pos.program_of_study_id "
                + "AND pos.program_of_study_id = " + posId;

//        System.out.println(sql);
        try (Statement st = con.createStatement();
                ResultSet rs = st.executeQuery(sql);) {

            int count = 0;
            if (rs.next()) {
                count = rs.getInt("total");
            }
            return count;
        }
    }

    public static List<DistrictSeatDistribution> getDistrictSeatDistribution(int campusId, int admissionYearId, int shiftId, boolean isJurisdiction) throws SQLException {
        String sql = "SELECT * FROM district_seat_distribution dsd "
                + "INNER JOIN jurisdiction j ON dsd.jurisdiction_id = j.jurisdiction_id "
                + "INNER JOIN admission_year ay ON dsd.admission_year_id = ay.admission_year_id "
                + "INNER JOIN shift sh ON dsd.shift_id = sh.shift_id "
                + "INNER JOIN campus c ON j.campus_id = c.campus_id "
                + "INNER JOIN district d ON d.district_id = j.district_id "
                + "WHERE ay.admission_year_id = " + admissionYearId + " "
                + "AND sh.shift_id = " + shiftId + " "
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
                dsd.setShiftId(rs.getInt("dsd.shift_id"));

                list.add(dsd);
            }

            return list;
        }
    }

    public static DisciplineCategorySeats getDisciplineCategorySeats(int campusCategoryId, int admissionYearId, int programTypeId, int shiftId, int cposGroupId) throws SQLException {
        String sql = "SELECT * FROM discipline_category_seats dcs, admission_session ads "
                + "WHERE dcs.admission_session_id = ads.admission_session_id "
                + "AND dcs.cpos_group_id = " + cposGroupId + " "
                + "AND ads.admission_year_id = " + admissionYearId + " "
                + "AND ads.program_type_id = " + programTypeId + " "
                + "AND ads.shift_id = " + shiftId + " "
                + "AND dcs.campus_category_id = " + campusCategoryId;

        //System.out.println(sql);
        try (Statement st = con.createStatement();
                ResultSet rs = st.executeQuery(sql);) {

            DisciplineCategorySeats dcs = null;

            while (rs.next()) {
                dcs = new DisciplineCategorySeats();

                dcs.setAdmissionSessionId(rs.getInt("admission_session_id"));
                dcs.setCampusCategoryId(rs.getInt("campus_category_id"));
                dcs.setCposGroupId(rs.getInt("cpos_group_id"));
                dcs.setDisciplineCategorySeatsId(rs.getInt("discipline_category_seats_id"));
                dcs.setNumberOfSeats(rs.getInt("number_of_seats"));
                dcs.setRemarks(rs.getString("remarks"));
            }

            return dcs;
        }
    }

    public static int getDisciplineCategorySeats(int campusCategoryId, int admissionYearId, int programTypeId, int shiftId, int cposGroupId, int challanType) throws SQLException {
        String sql = "SELECT (MAX(total_seats) - MAX(total_consumed)) AS seats FROM "
                + "( "
                + "    SELECT dcs.number_of_seats AS total_seats, NULL AS total_consumed FROM discipline_category_seats dcs "
                + "    INNER JOIN admission_session ads ON dcs.admission_session_id = ads.admission_session_id "
                + "    WHERE dcs.cpos_group_id = " + cposGroupId + " "
                + "    AND ads.admission_year_id = " + admissionYearId + " "
                + "    AND ads.program_type_id = " + programTypeId + " "
                + "    AND ads.shift_id = " + shiftId + " "
                + "    AND dcs.campus_category_id = " + campusCategoryId + " "
                + ""
                + "    UNION"
                + ""
                + "    SELECT NULL AS total_seats, COUNT(DISTINCT(ald.admission_list_details_id)) AS total_consumed FROM admission_list_details ald "
                + "    INNER JOIN admission_list al ON al.admission_list_id = ald.admission_list_id "
                + "    INNER JOIN admission_session ads ON ads.admission_session_id = al.admission_session_id "
                + "    INNER JOIN candidate c ON c.candidate_id = ald.candidate_id "
                + "    INNER JOIN candidate_program_of_study cnpos ON cnpos.candidate_id = ald.candidate_id "
                + "    INNER JOIN part_registry pr ON pr.admission_list_details_id = ald.admission_list_details_id "
                + "    WHERE pr.part_registry_id IS NOT NULL "
                + "    AND ald.cpos_group_id = " + cposGroupId + " "
                + "    AND ald.campus_category_id = " + campusCategoryId + " "
                + "    AND c.admission_year_id = " + admissionYearId + " "
                //                + "    AND ads.shift_id = " + shiftId + " "
                + "    AND ald.admission_list_details_id IN (SELECT admission_list_details_id FROM part_registry) "
                + ") a";
//        System.out.println(sql);
        try (Statement st = con.createStatement();
                ResultSet rs = st.executeQuery(sql);) {

            int seats = 0;
            if (rs.next()) {
                seats = rs.getInt("seats");
            }

            return seats;
        }
    }

    public static int getIssuerIdByCode(String code) throws SQLException {
        String sql = "SELECT * FROM issuer WHERE code = " + code;

        //System.out.println(sql);
        try (Statement st = con.createStatement();
                ResultSet rs = st.executeQuery(sql);) {

            if (rs.next()) {
                return rs.getInt("issuer_id");
            }

            return 0;
        }
    }

    public static int addAdmissionListDetails(int candidateId, int admissionListId, int cposgId, int cnposId, int campusCategoryId, float sscPer, float hscPer, float gradPer, int preTestScore, int otherTestScore, float score, String status, String remarks) throws SQLException {
        String query = "INSERT INTO admission_list_details(candidate_id, admission_list_id, cpos_group_id, candidate_program_of_study_id, campus_category_id, ssc_per, hsc_per, graduation_per, pre_test_score, other_test_score, score, remarks) VALUES "
                + "(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        //System.out.println(query);

        try (PreparedStatement pst = con.prepareStatement(query);) {
            pst.setInt(1, candidateId);
            pst.setInt(2, admissionListId);
            pst.setInt(3, cposgId);
            pst.setInt(4, cnposId);
            pst.setInt(5, campusCategoryId);
            pst.setFloat(6, sscPer);
            pst.setFloat(7, hscPer);
            pst.setFloat(8, gradPer);
            pst.setFloat(9, preTestScore);
            pst.setFloat(10, otherTestScore);
            pst.setFloat(11, score);
//            pst.setString(12, status);
            pst.setString(12, remarks);

            int record = pst.executeUpdate();
            return record;
        }
    }

    public static int addCNPOS(int candidateId, int cposId, int choiceNo) throws SQLException {
        String query = "INSERT INTO candidate_program_of_study (candidate_id, campus_program_of_study_id, choice_no) VALUES (?, ?, ?)";
        //System.out.println(query);

        try (PreparedStatement pst = con.prepareStatement(query);) {
            pst.setInt(1, candidateId);
            pst.setInt(2, cposId);
            pst.setInt(3, choiceNo);

            int record = pst.executeUpdate();
            return record;
        }
    }

    public static int addCategory(int candidateId, String categoryCode) throws SQLException {
        String query = "INSERT INTO applied_category (candidate_id, category_code) VALUES (?, ?)";
        //System.out.println(query);

        try (PreparedStatement pst = con.prepareStatement(query);) {
            pst.setInt(1, candidateId);
            pst.setString(2, categoryCode);

            int record = pst.executeUpdate();
            return record;
        }
    }

    public static int updateCandidateImagePath(int ayId, int ptId, int seatNo, String imagePath) throws SQLException {
        String sql = "UPDATE candidate SET image_path = ? WHERE admission_year_id = ? AND program_type_id = ? AND seat_no = ?";

        try (PreparedStatement pst = con.prepareStatement(sql);) {
            pst.setString(1, imagePath);
            pst.setInt(2, ayId);
            pst.setInt(3, ptId);
            pst.setInt(4, seatNo);

            int i = pst.executeUpdate();
            return i;
        }
    }

    public static List<Object[]> getCandidatesOfCposGroup(int admissionYearId, int cposgId, boolean active) throws SQLException {
        String sql = "SELECT * FROM admission_list_details AS ald "
                + "INNER JOIN candidate AS c ON ald.candidate_id = c.candidate_id "
                + "INNER JOIN district AS d ON d.district_id = c.district_id "
                + "INNER JOIN accounts AS ac ON c.candidate_id = ac.candidate_id "
                + "INNER JOIN cpos_group AS cposg ON cposg.cpos_group_id = ald.cpos_group_id "
                + "INNER JOIN admission_list AS al ON al.admission_list_id = ald.admission_list_id "
                + "INNER JOIN admission_session AS ass ON ass.admission_session_id = al.admission_session_id "
                + "INNER JOIN admission_year AS ay ON ass.admission_year_id = ay.admission_year_id "
                + "WHERE cposg.cpos_group_id = " + cposgId + " "
                + "AND ay.admission_year_id = " + admissionYearId + " "
                + "AND ac.active = " + active + " "
                + "AND ald.active = " + active + " "
                //                + "ORDER BY c.seat_no";
                + "ORDER BY ald.roll_no, c.name";

        try (Statement st = con.createStatement();
                ResultSet rs = st.executeQuery(sql);) {

            List<Object[]> list = new ArrayList<>();
            int i = 1;
            while (rs.next()) {
                list.add(new Object[]{
                    i++,
                    rs.getInt("c.seat_no"),
                    " " + rs.getString("c.name"),
                    rs.getString("c.fathers_name"),
                    rs.getString("c.surname"),
                    rs.getInt("ald.roll_no"),
                    rs.getString("d.name"),
                    rs.getString("c.area"),
                    rs.getFloat("c.percentage"),
                    rs.getString("c.mobile")});
            }

            return list;
        }
    }
    
    public static List<Object[]> getCandidatesOfCposGroupWithCategory(int admissionYearId, int cposgId, boolean active,boolean isOrderByRollNo) throws SQLException {
        String sql = "SELECT * FROM admission_list_details AS ald "
                + "INNER JOIN candidate AS c ON ald.candidate_id = c.candidate_id "
                + "INNER JOIN district AS d ON d.district_id = c.district_id "
                + "INNER JOIN accounts AS ac ON c.candidate_id = ac.candidate_id "
                + "INNER JOIN cpos_group AS cposg ON cposg.cpos_group_id = ald.cpos_group_id "
                + "INNER JOIN admission_list AS al ON al.admission_list_id = ald.admission_list_id "
                + "INNER JOIN admission_session AS ass ON ass.admission_session_id = al.admission_session_id "
                + "INNER JOIN admission_year AS ay ON ass.admission_year_id = ay.admission_year_id "
                + "INNER JOIN campus_category AS cc ON cc.campus_category_id = ald.campus_category_id "
                + "INNER JOIN category AS cat ON cat.category_id = cc.category_id " 
                + "WHERE cposg.cpos_group_id = " + cposgId + " "
                + "AND ay.admission_year_id = " + admissionYearId + " "
                + "AND ac.active = " + active + " "
                + "AND ald.active = " + active + " ";
                //                + "ORDER BY c.seat_no";
                if(isOrderByRollNo){
                    sql+= " ORDER BY ald.roll_no";
                }else{
                    sql+= " ORDER BY c.seat_no";
                }
        try (Statement st = con.createStatement();
                ResultSet rs = st.executeQuery(sql);) {

            List<Object[]> list = new ArrayList<>();
            int i = 1;
            while (rs.next()) {
                list.add(new Object[]{
                    i++,
                    rs.getInt("c.seat_no"),
                    " " + rs.getString("c.name"),
                    rs.getString("c.fathers_name"),
                    rs.getString("c.surname"),
                    rs.getInt("ald.roll_no"),
                    rs.getString("d.name"),
                    rs.getString("c.area"),
                    rs.getFloat("c.percentage"),
                    rs.getString("c.mobile"),
                    rs.getInt("cat.code")
                });
            }

            return list;
        }
    }

    public static List<Object[]> getSelectedCandidates(int admissionYearId, int programTypeId, boolean active) throws SQLException {
        String sql = "SELECT * FROM admission_list_details AS ald "
                + "INNER JOIN candidate AS c ON ald.candidate_id = c.candidate_id "
                + "INNER JOIN accounts AS ac ON c.candidate_id = ac.candidate_id "
                + "INNER JOIN cpos_group AS cposg ON cposg.cpos_group_id = ald.cpos_group_id "
                + "INNER JOIN campus_program_of_study AS cpos ON cposg.campus_program_of_study_id = cpos.campus_program_of_study_id "
                + "INNER JOIN program_of_study AS pos ON cpos.program_of_study_id = pos.program_of_study_id "
                + "INNER JOIN program AS p ON pos.program_id = p.program_id "
                + "INNER JOIN shift AS s ON cpos.shift_id = s.shift_id "
                + "INNER JOIN admission_list AS al ON al.admission_list_id = ald.admission_list_id "
                + "INNER JOIN admission_session AS ass ON ass.admission_session_id = al.admission_session_id "
                + "INNER JOIN admission_year AS ay ON ass.admission_year_id = ay.admission_year_id "
                + "INNER JOIN program_type AS pt ON c.program_type_id = pt.program_type_id "
                + "WHERE ay.admission_year_id = " + admissionYearId + " "
                + "AND c.program_type_id = " + programTypeId + " "
                + "AND ac.active = " + active + " "
                + "AND ald.active = " + active + " "
                + "ORDER BY pos.name";

        try (Statement st = con.createStatement();
                ResultSet rs = st.executeQuery(sql);) {

            List<Object[]> list = new ArrayList<>();
            while (rs.next()) {
                list.add(new Object[]{
                    rs.getInt("c.seat_no"),
                    rs.getString("c.name"),
                    rs.getString("c.fathers_name"),
                    rs.getString("c.surname"),
                    rs.getInt("ald.roll_no"),
                    rs.getString("cposg.code"),
                    rs.getString("pos.name"),
                    rs.getString("p.name"),
                    rs.getString("s.name"),
                    rs.getInt("c.candidate_id"),
                    rs.getInt("ald.sel_cat")
                });
            }

            return list;
        }
    }

    public static List<Object[]> getNonSelectedCandidates(int admissionYearId, int programTypeId, boolean active) throws SQLException {
        String sql = "SELECT seat_no, name, fathers_name, surname FROM candidate "
                + "WHERE admission_year_id = " + admissionYearId + " "
                + "AND program_type_id = " + programTypeId + " "
                + "AND candidate_id NOT IN ( "
                + "SELECT candidate_id FROM admission_list_details "
                + "WHERE admission_list_details_id IN (SELECT admission_list_details_id FROM part_registry)) "
                + "ORDER BY seat_no";

        try (Statement st = con.createStatement();
                ResultSet rs = st.executeQuery(sql);) {

            List<Object[]> list = new ArrayList<>();
            while (rs.next()) {
                list.add(new Object[]{
                    rs.getInt("seat_no"),
                    rs.getString("name"),
                    rs.getString("fathers_name"),
                    rs.getString("surname"),});
            }

            return list;
        }
    }

    public static List<Object[]> getCandidatesOfCposGroup(int admissionYearId, int cposgId, boolean active, int flag) throws SQLException {
        String sql = "SELECT * FROM admission_list_details AS ald "
                + "INNER JOIN candidate AS c ON ald.candidate_id = c.candidate_id "
                + "INNER JOIN accounts AS ac ON c.candidate_id = ac.candidate_id "
                + "INNER JOIN cpos_group AS cposg ON cposg.cpos_group_id = ald.cpos_group_id "
                + "INNER JOIN admission_list AS al ON al.admission_list_id = ald.admission_list_id "
                + "INNER JOIN admission_session AS ass ON ass.admission_session_id = al.admission_session_id "
                + "INNER JOIN admission_year AS ay ON ass.admission_year_id = ay.admission_year_id "
                + "WHERE cposg.cpos_group_id = " + cposgId + " "
                + "AND ay.admission_year_id = " + admissionYearId + " "
                + "AND ac.active = " + active + " "
                + "AND ald.active = " + active + " "
                + "AND ald.flag = " + flag + " "
                + "ORDER BY ald.roll_no, c.name";

        try (Statement st = con.createStatement();
                ResultSet rs = st.executeQuery(sql);) {

            List<Object[]> list = new ArrayList<>();
            int i = 1;
            while (rs.next()) {
                list.add(new Object[]{
                    i++,
                    rs.getInt("c.seat_no"),
                    " " + rs.getString("c.name"),
                    rs.getString("c.fathers_name"),
                    rs.getString("c.surname"),
                    rs.getInt("ald.roll_no"),
                    rs.getString("d.name"),
                    rs.getString("c.area"),
                    rs.getFloat("c.percentage"),
                    rs.getString("c.mobile"),
                });
            }

            return list;
        }
    }

    public static List<Integer> getCandidatesOfCposGroup2(int admissionYearId, int cposgId, boolean active) throws SQLException {
        String sql = "SELECT * FROM admission_list_details AS ald "
                + "INNER JOIN candidate AS c ON ald.candidate_id = c.candidate_id "
                + "INNER JOIN accounts AS ac ON c.candidate_id = ac.candidate_id "
                + "INNER JOIN cpos_group AS cposg ON cposg.cpos_group_id = ald.cpos_group_id "
                + "INNER JOIN admission_list AS al ON al.admission_list_id = ald.admission_list_id "
                + "INNER JOIN admission_session AS ass ON ass.admission_session_id = al.admission_session_id "
                + "INNER JOIN admission_year AS ay ON ass.admission_year_id = ay.admission_year_id "
                + "WHERE cposg.cpos_group_id = " + cposgId + " "
                + "AND ay.admission_year_id = " + admissionYearId + " "
                + "AND ac.active = " + active + " "
                + "AND ald.active = " + active + " "
                + "ORDER BY c.name";

        try (Statement st = con.createStatement();
                ResultSet rs = st.executeQuery(sql);) {

            List<Integer> list = new ArrayList<>();
            while (rs.next()) {
                list.add(rs.getInt("ald.admission_list_details_id"));
            }

            return list;
        }
    }

    public static List<Integer> getCandidatesOfCposGroup3(int admissionYearId, int cposgId, boolean active) throws SQLException {
        String sql = "SELECT * FROM admission_list_details AS ald "
                + "INNER JOIN candidate AS c ON ald.candidate_id = c.candidate_id "
                + "INNER JOIN accounts AS ac ON c.candidate_id = ac.candidate_id "
                + "INNER JOIN cpos_group AS cposg ON cposg.cpos_group_id = ald.cpos_group_id "
                + "INNER JOIN admission_list AS al ON al.admission_list_id = ald.admission_list_id "
                + "INNER JOIN admission_session AS ass ON ass.admission_session_id = al.admission_session_id "
                + "INNER JOIN admission_year AS ay ON ass.admission_year_id = ay.admission_year_id "
                + "WHERE cposg.cpos_group_id = " + cposgId + " "
                + "AND ay.admission_year_id = " + admissionYearId + " "
                + "AND ac.active = " + active + " "
                + "AND ald.active = " + active + " "
                + "AND ald.roll_no IS NULL "
                + "ORDER BY c.name";

        try (Statement st = con.createStatement();
                ResultSet rs = st.executeQuery(sql);) {

            List<Integer> list = new ArrayList<>();
            while (rs.next()) {
                list.add(rs.getInt("ald.admission_list_details_id"));
            }

            return list;
        }
    }

    public static int getMaxRollNo(int admissionYearId, int cposgId, boolean active) throws SQLException {
        String sql = "SELECT MAX(ald.roll_no) AS roll_no FROM admission_list_details AS ald "
                + "INNER JOIN candidate AS c ON ald.candidate_id = c.candidate_id "
                + "INNER JOIN accounts AS ac ON c.candidate_id = ac.candidate_id "
                + "INNER JOIN cpos_group AS cposg ON cposg.cpos_group_id = ald.cpos_group_id "
                + "INNER JOIN admission_list AS al ON al.admission_list_id = ald.admission_list_id "
                + "INNER JOIN admission_session AS ass ON ass.admission_session_id = al.admission_session_id "
                + "INNER JOIN admission_year AS ay ON ass.admission_year_id = ay.admission_year_id "
                + "WHERE cposg.cpos_group_id = " + cposgId + " "
                + "AND ay.admission_year_id = " + admissionYearId + " "
                + "AND ac.active = " + active + " "
                + "AND ald.active = " + active + " "
                + "ORDER BY c.name";

        try (Statement st = con.createStatement();
                ResultSet rs = st.executeQuery(sql);) {

            int rollNo = 0;
            if (rs.next()) {
                rollNo = (rs.getInt("roll_no"));
            }

            return rollNo;
        }
    }

    public static int getCountCandidatesOfCposGroup(int admissionYearId, int cposgId, boolean active) throws SQLException {
        String sql = "SELECT COUNT(ald.admission_list_details_id) active FROM admission_list_details AS ald "
                + "INNER JOIN candidate AS c ON ald.candidate_id = c.candidate_id "
                + "INNER JOIN accounts AS ac ON c.candidate_id = ac.candidate_id "
                + "INNER JOIN cpos_group AS cposg ON cposg.cpos_group_id = ald.cpos_group_id "
                + "INNER JOIN admission_list AS al ON al.admission_list_id = ald.admission_list_id "
                + "INNER JOIN admission_session AS ass ON ass.admission_session_id = al.admission_session_id "
                + "INNER JOIN admission_year AS ay ON ass.admission_year_id = ay.admission_year_id "
                + "WHERE cposg.cpos_group_id = " + cposgId + " "
                + "AND ay.admission_year_id = " + admissionYearId + " "
                + "AND ac.active = " + active + " "
                + "AND ald.active = " + active + " "
                + "ORDER BY c.name";

        try (Statement st = con.createStatement();
                ResultSet rs = st.executeQuery(sql);) {

            int i = 0;
            if (rs.next()) {
                i = rs.getInt("active");
            }

            return i;
        }
    }

    public static int getCountCandidatesOfCposGroup(int admissionYearId, int cposgId, int campusCategory, boolean active) throws SQLException {
        String sql = "SELECT COUNT(ald.admission_list_details_id) active FROM admission_list_details AS ald "
                + "INNER JOIN candidate AS c ON ald.candidate_id = c.candidate_id "
                + "INNER JOIN accounts AS ac ON c.candidate_id = ac.candidate_id "
                + "INNER JOIN cpos_group AS cposg ON cposg.cpos_group_id = ald.cpos_group_id "
                + "INNER JOIN admission_list AS al ON al.admission_list_id = ald.admission_list_id "
                + "INNER JOIN admission_session AS ass ON ass.admission_session_id = al.admission_session_id "
                + "INNER JOIN admission_year AS ay ON ass.admission_year_id = ay.admission_year_id "
                + "WHERE ald.campus_category_id = " + campusCategory + " "
                + "AND cposg.cpos_group_id = " + cposgId + " "
                + "AND ay.admission_year_id = " + admissionYearId + " "
                + "AND ac.active = " + active + " "
                + "AND ald.active = " + active + " "
                + "ORDER BY c.name";

        try (Statement st = con.createStatement();
                ResultSet rs = st.executeQuery(sql);) {

            int i = 0;
            if (rs.next()) {
                i = rs.getInt("active");
            }

            return i;
        }
    }

    public static int getCposgSeats(int admissionYearId, int cposgId) throws SQLException {
        String sql = "SELECT SUM(dcs.number_of_seats) seats FROM discipline_category_seats dcs "
                + "INNER JOIN admission_session ass ON ass.admission_session_id = dcs.admission_session_id "
                + "WHERE ass.admission_year_id = " + admissionYearId + " "
                + "AND dcs.cpos_group_id = " + cposgId;

//        System.out.println(sql);
        try (Statement st = con.createStatement();
                ResultSet rs = st.executeQuery(sql)) {
            int i = 0;
            if (rs.next()) {
                i = rs.getInt("seats");
            }
            return i;
        }
    }

    public static int getCposgSeats(int admissionYearId, int cposgId, int campusCategoryId) throws SQLException {
        String sql = "SELECT dcs.number_of_seats AS seats FROM discipline_category_seats dcs "
                + "INNER JOIN admission_session ass ON ass.admission_session_id = dcs.admission_session_id "
                + "WHERE ass.admission_year_id = " + admissionYearId + " "
                + "AND dcs.campus_category_id = " + campusCategoryId + " "
                + "AND dcs.cpos_group_id = " + cposgId;

//        System.out.println(sql);
        try (Statement st = con.createStatement();
                ResultSet rs = st.executeQuery(sql)) {
            int i = 0;
            if (rs.next()) {
                i = rs.getInt("seats");
            }
            return i;
        }
    }

    public static int updateAdmissionListDetails(int cposgId, Integer flag) throws SQLException {
        String sql = "UPDATE admission_list_details SET status = " + flag + " WHERE cpos_group_id = " + cposgId;

        try (Statement st = con.createStatement();) {

            int i = st.executeUpdate(sql);
            return i;
        }
    }

    public static int updateAdmissionListDetails(int aldId, int rollNo, int flag) throws SQLException {
        String sql = "UPDATE admission_list_details SET roll_no = ?, status = ? WHERE admission_list_details_id = ?";

        try (PreparedStatement pst = con.prepareStatement(sql);) {
            pst.setInt(1, rollNo);
            pst.setInt(2, flag);
            pst.setInt(3, aldId);

            int i = pst.executeUpdate();
            return i;
        }
    }

    public static List<HashMap<String, String>> getHECData(int admissionYearId, int programTypeId, int shiftId, int districtId, String campusCategoryIds, boolean active) throws SQLException {
        String sql = "SELECT * FROM admission_list_details AS ald "
                + "INNER JOIN candidate AS c ON ald.candidate_id = c.candidate_id "
                + "INNER JOIN accounts AS ac ON c.candidate_id = ac.candidate_id "
                + "INNER JOIN cpos_group AS cposg ON cposg.cpos_group_id = ald.cpos_group_id "
                + "INNER JOIN campus_program_of_study AS cpos ON cposg.campus_program_of_study_id = cpos.campus_program_of_study_id "
                + "INNER JOIN program_of_study AS pos ON cpos.program_of_study_id = pos.program_of_study_id "
                + "INNER JOIN admission_list AS al ON al.admission_list_id = ald.admission_list_id "
                + "INNER JOIN admission_session AS ass ON ass.admission_session_id = al.admission_session_id "
                + "INNER JOIN admission_year AS ay ON ass.admission_year_id = ay.admission_year_id "
                + "INNER JOIN program_type AS pt ON ass.program_type_id = pt.program_type_id "
                + "INNER JOIN program AS p ON pos.program_id = p.program_id "
                + "WHERE ay.admission_year_id = " + admissionYearId + " "
                + "AND pt.program_type_id = " + programTypeId + " "
                + "AND cpos.shift_id = " + shiftId + " "
                + "AND c.district_id = " + districtId + " "
                + "AND ac.active = " + active + " "
                + "AND ald.active = " + active + " "
                + "AND ald.campus_category_id IN (" + campusCategoryIds + ") "
                + "ORDER BY c.name";

        try (Statement st = con.createStatement();
                ResultSet rs = st.executeQuery(sql);) {

            List<HashMap<String, String>> list = new ArrayList<>();
            while (rs.next()) {
                HashMap<String, String> map = new HashMap<>();

                map.put("name", rs.getString("c.name"));
                map.put("fname", rs.getString("c.fathers_name"));
                map.put("cnic", rs.getString("c.cnic_no"));
                map.put("address", rs.getString("c.present_postel_address"));
                map.put("seat_no", rs.getString("c.seat_no"));
                map.put("selection", rs.getString("pos.name"));
                map.put("duration", rs.getString("pos.duration"));
                map.put("program", rs.getString("p.name"));
                map.put("candidate_id", rs.getString("c.candidate_id"));

                list.add(map);
            }

            return list;
        }
    }

    public static List<HashMap<String, String>> getHostelData(int admissionYearId, int programTypeId, int shiftId, int partId, int gender, boolean active) throws SQLException {
        String sql = "SELECT * FROM part_registry AS pr "
                + "INNER JOIN admission_list_details AS ald ON ald.admission_list_details_id = pr.admission_list_details_id "
                + "INNER JOIN candidate AS c ON ald.candidate_id = c.candidate_id "
                +" INNER JOIN district d ON d.district_id = c.district_id "
                + "INNER JOIN accounts AS ac ON c.candidate_id = ac.candidate_id "
                + "INNER JOIN cpos_group AS cposg ON cposg.cpos_group_id = ald.cpos_group_id "
                + "INNER JOIN campus_program_of_study AS cpos ON cposg.campus_program_of_study_id = cpos.campus_program_of_study_id "
                + "INNER JOIN program_of_study AS pos ON cpos.program_of_study_id = pos.program_of_study_id "
                + "INNER JOIN admission_list AS al ON al.admission_list_id = ald.admission_list_id "
                + "INNER JOIN admission_session AS ass ON ass.admission_session_id = al.admission_session_id "
                + "INNER JOIN admission_year AS ay ON ass.admission_year_id = ay.admission_year_id "
                + "INNER JOIN program_type AS pt ON ass.program_type_id = pt.program_type_id "
                + "INNER JOIN program AS p ON pos.program_id = p.program_id "
                + "WHERE ay.admission_year_id = " + admissionYearId + " "
                + "AND pt.program_type_id = " + programTypeId + " "
                + "AND c.gender = " + gender + " "
                + "AND cpos.shift_id = " + shiftId + " "
                + "AND ac.active = " + active + " "
                + "AND ald.active = " + active + " "
                + "AND pr.part_id = " + partId + " "
                + "GROUP BY c.candidate_id "
                + "ORDER BY pos.name, c.percentage DESC";

        try (Statement st = con.createStatement();
                ResultSet rs = st.executeQuery(sql);) {

            List<HashMap<String, String>> list = new ArrayList<>();
            while (rs.next()) {
                HashMap<String, String> map = new HashMap<>();

                map.put("seat_no", rs.getString("c.seat_no"));
                map.put("name", rs.getString("c.name"));
                map.put("fname", rs.getString("c.fathers_name"));
                map.put("surname", rs.getString("c.surname"));
                map.put("district", rs.getString("d.name"));
                map.put("selection", rs.getString("pos.name"));
                map.put("program", rs.getString("p.name"));
                map.put("category", CategoryEnum.values()[rs.getInt("ald.sel_cat")].getDesc());
                map.put("percentage", rs.getString("c.percentage"));
                map.put("roll_no", "" + rs.getInt("ald.roll_no"));
                map.put("code", rs.getString("cposg.code"));

                list.add(map);
            }

            return list;
        }
    }

    public static HashMap<String, Object> getCandidate2(int admissionYearId, int programTypeId, int partId, String seatNo, boolean active) throws SQLException {
        String sql = "SELECT * FROM part_registry AS pr "
                + "INNER JOIN admission_list_details AS ald ON ald.admission_list_details_id = pr.admission_list_details_id "
                + "INNER JOIN candidate AS c ON ald.candidate_id = c.candidate_id "
                + "INNER JOIN accounts AS ac ON c.candidate_id = ac.candidate_id "
                + "INNER JOIN cpos_group AS cposg ON cposg.cpos_group_id = ald.cpos_group_id "
                + "INNER JOIN campus_program_of_study AS cpos ON cposg.campus_program_of_study_id = cpos.campus_program_of_study_id "
                + "INNER JOIN campus AS cam ON cam.campus_id = cpos.campus_id "
                + "INNER JOIN shift AS s ON s.shift_id = cpos.shift_id "
                + "INNER JOIN program_of_study AS pos ON cpos.program_of_study_id = pos.program_of_study_id "
                + "INNER JOIN discipline AS dc ON dc.discipline_id = pos.discipline_id "
                + "INNER JOIN department AS dpt ON dpt.department_id = dc.department_id "
                + "INNER JOIN admission_list AS al ON al.admission_list_id = ald.admission_list_id "
                + "INNER JOIN admission_session AS ass ON ass.admission_session_id = al.admission_session_id "
                + "INNER JOIN admission_year AS ay ON ass.admission_year_id = ay.admission_year_id "
                + "INNER JOIN program_type AS pt ON ass.program_type_id = pt.program_type_id "
                + "INNER JOIN program AS p ON pos.program_id = p.program_id "
                + "WHERE ay.admission_year_id = " + admissionYearId + " "
                + "AND pt.program_type_id = " + programTypeId + " "
                + "AND c.seat_no = " + seatNo + " "
                + "AND ac.active = " + active + " "
                + "AND ald.active = " + active + " "
                + "AND pr.part_id = " + partId + " "
                + "ORDER BY pos.name, c.percentage DESC";

//        System.out.println(sql);
        try (Statement st = con.createStatement();
                ResultSet rs = st.executeQuery(sql);) {

            HashMap<String, Object> map = null;
            while (rs.next()) {
//                CandidateDataBean bean = new CandidateDataBean();
                map = new HashMap<>();

                map.put("seat_no", rs.getInt("c.seat_no"));
                map.put("name", rs.getString("c.name"));
                map.put("fname", rs.getString("c.fathers_name"));
                map.put("surname", rs.getString("c.surname"));
                map.put("gender", rs.getString("c.gender"));
                map.put("cnic", rs.getString("c.cnic_no"));
                map.put("address", rs.getString("c.present_postel_address"));
                map.put("selection", rs.getString("pos.name"));
                map.put("program", rs.getString("p.name"));
                map.put("shift", rs.getString("s.name"));
                map.put("is_morning", "" + rs.getBoolean("s.is_morning"));
                map.put("roll_no", "" + rs.getInt("ald.roll_no"));
                map.put("code", rs.getString("cposg.code"));
                map.put("campus", rs.getString("cam.name"));
                map.put("campus_location", rs.getString("cam.location"));
                map.put("is_main", rs.getBoolean("cam.is_main"));
                map.put("department", rs.getString("dpt.name"));
                map.put("image_path", rs.getString("c.image_path"));
            }

            return map;
        }
    }

    public static List<Map<String, Object>> getCandidate3(int admissionYearId, int programTypeId, int partId, int cposgId, boolean active) throws SQLException {
        String sql = "SELECT * FROM admission_list_details AS ald "
                + "INNER JOIN candidate AS c ON ald.candidate_id = c.candidate_id "
                + "INNER JOIN accounts AS ac ON c.candidate_id = ac.candidate_id "
                + "INNER JOIN cpos_group AS cposg ON cposg.cpos_group_id = ald.cpos_group_id "
                + "INNER JOIN campus_program_of_study AS cpos ON cposg.campus_program_of_study_id = cpos.campus_program_of_study_id "
                + "INNER JOIN campus AS cam ON cam.campus_id = cpos.campus_id "
                + "INNER JOIN shift AS s ON s.shift_id = cpos.shift_id "
                + "INNER JOIN program_of_study AS pos ON cpos.program_of_study_id = pos.program_of_study_id "
                + "INNER JOIN discipline AS dc ON dc.discipline_id = pos.discipline_id "
                + "INNER JOIN department AS dpt ON dpt.department_id = dc.department_id "
                + "INNER JOIN admission_list AS al ON al.admission_list_id = ald.admission_list_id "
                + "INNER JOIN admission_session AS ass ON ass.admission_session_id = al.admission_session_id "
                + "INNER JOIN admission_year AS ay ON ass.admission_year_id = ay.admission_year_id "
                + "INNER JOIN program_type AS pt ON ass.program_type_id = pt.program_type_id "
                + "INNER JOIN program AS p ON pos.program_id = p.program_id "
                + "WHERE ald.admission_list_details_id IN (SELECT admission_list_details_id FROM part_registry WHERE part_id = " + partId + ")"
                + "AND ay.admission_year_id = " + admissionYearId + " "
                + "AND pt.program_type_id = " + programTypeId + " "
                + "AND ac.active = " + active + " "
                + "AND ald.active = " + active + " "
                + "AND cposg.cpos_group_id = " + cposgId + " "
                + "ORDER BY ald.roll_no";

//        System.out.println(sql);
        try (Statement st = con.createStatement();
                ResultSet rs = st.executeQuery(sql);) {

            List<Map<String, Object>> list = new ArrayList<>();

            while (rs.next()) {
                Map<String, Object> map = new HashMap<>();

                map.put("seat_no", rs.getInt("c.seat_no"));
                map.put("name", rs.getString("c.name"));
                map.put("fname", rs.getString("c.fathers_name"));
                map.put("surname", rs.getString("c.surname"));
                map.put("gender", rs.getString("c.gender"));
                map.put("cnic", rs.getString("c.cnic_no"));
                map.put("address", rs.getString("c.present_postel_address"));
                map.put("selection", rs.getString("pos.name"));
                map.put("program", rs.getString("p.name"));
                map.put("shift", rs.getString("s.name"));
                map.put("is_morning", "" + rs.getBoolean("s.is_morning"));
                map.put("roll_no", "" + rs.getInt("ald.roll_no"));
                map.put("code", rs.getString("cposg.code"));
                map.put("campus", rs.getString("cam.name"));
                map.put("campus_location", rs.getString("cam.location"));
                map.put("is_main", rs.getBoolean("cam.is_main"));
                map.put("department", rs.getString("dpt.name"));
                map.put("image_path", rs.getString("c.image_path"));

                list.add(map);
            }

            return list;
        }
    }

    public static HashMap<String, String> getCandidate(int admissionYearId, int programTypeId, int partId, String seatNo, boolean active) throws SQLException {
        String sql = "SELECT * FROM part_registry AS pr "
                + "INNER JOIN admission_list_details AS ald ON ald.admission_list_details_id = pr.admission_list_details_id "
                + "INNER JOIN candidate AS c ON ald.candidate_id = c.candidate_id "
                + "INNER JOIN accounts AS ac ON c.candidate_id = ac.candidate_id "
                + "INNER JOIN cpos_group AS cposg ON cposg.cpos_group_id = ald.cpos_group_id "
                + "INNER JOIN campus_program_of_study AS cpos ON cposg.campus_program_of_study_id = cpos.campus_program_of_study_id "
                + "INNER JOIN campus AS cam ON cam.campus_id = cpos.campus_id "
                + "INNER JOIN shift AS s ON s.shift_id = cpos.shift_id "
                + "INNER JOIN program_of_study AS pos ON cpos.program_of_study_id = pos.program_of_study_id "
                + "INNER JOIN discipline AS dc ON dc.discipline_id = pos.discipline_id "
                + "INNER JOIN department AS dpt ON dpt.department_id = dc.department_id "
                + "INNER JOIN admission_list AS al ON al.admission_list_id = ald.admission_list_id "
                + "INNER JOIN admission_session AS ass ON ass.admission_session_id = al.admission_session_id "
                + "INNER JOIN admission_year AS ay ON ass.admission_year_id = ay.admission_year_id "
                + "INNER JOIN program_type AS pt ON ass.program_type_id = pt.program_type_id "
                + "INNER JOIN program AS p ON pos.program_id = p.program_id "
                + "WHERE ay.admission_year_id = " + admissionYearId + " "
                + "AND pt.program_type_id = " + programTypeId + " "
                + "AND c.seat_no = " + seatNo + " "
                + "AND ac.active = " + active + " "
                + "AND ald.active = " + active + " "
                + "AND pr.part_id = " + partId + " "
                + "ORDER BY pos.name, c.percentage DESC";

//        System.out.println(sql);
        try (Statement st = con.createStatement();
                ResultSet rs = st.executeQuery(sql);) {

            HashMap<String, String> map = null;
            if (rs.next()) {
                map = new HashMap<>();

                map.put("seat_no", rs.getString("c.seat_no"));
                map.put("name", rs.getString("c.name"));
                map.put("fname", rs.getString("c.fathers_name"));
                map.put("surname", rs.getString("c.surname"));
                map.put("gender", rs.getString("c.gender"));
                map.put("cnic", rs.getString("c.cnic_no"));
                map.put("address", rs.getString("c.present_postel_address"));
                map.put("selection", rs.getString("pos.name"));
                map.put("program", rs.getString("p.name"));
                map.put("shift", rs.getString("s.name"));
                map.put("is_morning", "" + rs.getBoolean("s.is_morning"));
                map.put("roll_no", "" + rs.getInt("ald.roll_no"));
                map.put("code", rs.getString("cposg.code"));
                map.put("campus", rs.getString("cam.name"));
                map.put("campus_location", rs.getString("cam.location"));
                map.put("department", rs.getString("dpt.name"));
            }

            return map;
        }
    }

    public static List<Object[]> getCandidates(int admissionYearId, int programTypeId, String searchBy, String value) throws SQLException {
        String sql = "SELECT * FROM candidate "
                + "WHERE admission_year_id = " + admissionYearId + " "
                + "AND program_type_id = " + programTypeId + " "
                + "AND " + searchBy + " LIKE '" + value + "%'";

//        System.out.println(sql);
        try (Statement st = con.createStatement();
                ResultSet rs = st.executeQuery(sql);) {

            List<Object[]> list = new ArrayList<>();
            int count = 0;
            while (rs.next()) {
                list.add(new Object[]{
                    ++count,
                    rs.getInt("seat_no"),
                    rs.getString("name"),
                    rs.getString("fathers_name"),
                    rs.getString("surname"),});
            }

            return list;
        }
    }

    public static List<Object[]> getFinanceData(int admissionYearId, int programTypeId, int campusId, int partId) throws SQLException {
        String sql = "SELECT c.seat_no, c.name, c.fathers_name, c.surname, pr.amount, pr.challan_no, pr.challan_date FROM admission_list_details AS ald "
                + "INNER JOIN candidate AS c ON ald.candidate_id = c.candidate_id "
                + "INNER JOIN admission_year AS ay ON c.admission_year_id = ay.admission_year_id "
                + "INNER JOIN program_type AS pt ON c.program_type_id = pt.program_type_id "
                + "INNER JOIN cpos_group AS cposg ON cposg.cpos_group_id = ald.cpos_group_id "
                + "INNER JOIN campus_program_of_study AS cpos ON cposg.campus_program_of_study_id = cpos.campus_program_of_study_id "
                + "INNER JOIN part_registry AS pr ON pr.admission_list_details_id = ald.admission_list_details_id "
                + "WHERE ay.admission_year_id = " + admissionYearId + " "
                + "AND pt.program_type_id = " + programTypeId + " "
                + "AND cpos.campus_id = " + campusId + " "
                + "AND pr.part_id = " + partId + " "
                + "ORDER BY c.seat_no";

//        System.out.println(sql);
        try (Statement st = con.createStatement();
                ResultSet rs = st.executeQuery(sql);) {

            List<Object[]> list = new ArrayList<>();
            while (rs.next()) {
                Object[] array = new Object[7];

                array[0] = rs.getInt("c.seat_no");
                array[1] = rs.getString("c.name");
                array[2] = rs.getString("c.fathers_name");
                array[3] = rs.getString("c.surname");
                array[4] = rs.getInt("pr.amount");
                array[5] = rs.getInt("pr.challan_no");
                array[6] = rs.getDate("pr.challan_date");

                list.add(array);
            }

            return list;
        }
    }
//    public static List<int[]> getSelectedCandidatesSeatNo(int admissionYearId, int programTypeId) throws SQLException {
//        String sql = "SELECT * FROM candidate c "
//                + "INNER JOIN admissionList_details ald ON ald.candidate_id = c.candidate_id "
//                + "WHERE c.admission_year_id = " + admissionYearId + " "
//                + "AND c.program_type_id = " + programTypeId + " "
//                + "AND ald.active = true";
//        
//        try (Statement st = con.createStatement();
//                ResultSet rs = st.executeQuery(sql);){
//            
//            List<int[]> list = new ArrayList<>();
//            while (rs.next()) {
//                list.add(new int[]{
//                    rs.getInt("c.candidate_id"),
//                    rs.getInt("c.seat_no")
//                });
//            }
//            
//            return list;
//        }
//    }

    public static int addLog(int userId, String modelAction, String modelName, int modelId) throws SQLException {
        String sql = "INSERT INTO YOG_LOG (USER_ID, MODEL_ACTION, MODEL_NAME, MODEL_ID, LOG_DATE) VALUES "
                + "(" + userId + ", '" + modelAction + "', '" + modelName + "', " + modelId + ", (UNIX_TIMESTAMP(CURDATE()) * 1000) - 43200000)";
//        System.out.println(sql);
        try (Statement st = con.createStatement();) {
            return st.executeUpdate(sql);
        }
    }

    public static List<AdmissionListDetails> getaAdmissionListDetailses(int admissionListId) throws SQLException {
        String sql = "SELECT * FROM ADMISSION_LIST_DETAILS WHERE ADMISSION_LIST_ID = " + admissionListId;

        List<AdmissionListDetails> list = new ArrayList<>();

        try (Statement st = con.createStatement();
                ResultSet rs = st.executeQuery(sql);) {

            while (rs.next()) {
                AdmissionListDetails ald = new AdmissionListDetails();
                ald.setAdmissionListDetailsId(rs.getInt("ADMISSION_LIST_DETAILS_ID"));

                list.add(ald);
            }
            return list;
        }
    }

    public static List<Long> getCodes() {
        String sql = "SELECT CODE FROM YOG_TEMP_CHALLAN";

        List<Long> list = new ArrayList<>();

        try (Statement st = con.createStatement();
                ResultSet rs = st.executeQuery(sql);) {

            while (rs.next()) {
                String key = rs.getString("CODE");
                long l = Long.parseLong(key.substring(key.indexOf("-") + 1, key.lastIndexOf("-")));
                list.add(l);
            }
        } catch (SQLException ex) {
            Logger.getLogger(JDBCDatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public static boolean isConfirm(String sql) throws SQLException {
        try (Statement st = con.createStatement();
                ResultSet rs = st.executeQuery(sql);) {

            return rs.next();
        }
    }

    public static int getIntValue(String sql, String param) throws SQLException {
        try (Statement st = con.createStatement();
                ResultSet rs = st.executeQuery(sql);) {

            if (rs.next()) {
                return rs.getInt(param);
            }
            return 0;
        }
    }

    public static String getStringValue(String sql, String param) throws SQLException {
        try (Statement st = con.createStatement();
                ResultSet rs = st.executeQuery(sql);) {

            if (rs.next()) {
                return rs.getString(param);
            }
            return null;
        }
    }

    //New Algorthim Methods------------------------------------------------------------------------------------------------------------
    public static List<DisciplineAlg> getDisciplineAlgList(int shiftId, int campusId, int ptId) throws SQLException {
        String sql = "SELECT POS.PROGRAM_OF_STUDY_ID AS POS_ID, CPOS.CAMPUS_PROGRAM_OF_STUDY_ID AS CPOS_ID, "
                + "CPOSG.CPOS_GROUP_ID AS CPOSG_ID, CPOS.CAMPUS_ID, S.SHIFT_ID, S.IS_MORNING, POS.IS_QUOTA_ORIENTED, CPOSG.GROUP_CODE "
                + "FROM PROGRAM P "
                + "INNER JOIN PROGRAM_OF_STUDY POS ON POS.PROGRAM_ID = P.PROGRAM_ID "
                + "INNER JOIN CAMPUS_PROGRAM_OF_STUDY CPOS ON POS.PROGRAM_OF_STUDY_ID = CPOS.PROGRAM_OF_STUDY_ID "
                + "INNER JOIN CPOS_GROUP CPOSG ON CPOSG.CAMPUS_PROGRAM_OF_STUDY_ID = CPOS.CAMPUS_PROGRAM_OF_STUDY_ID "
                + "INNER JOIN SHIFT S ON S.SHIFT_ID = CPOS.SHIFT_ID "
                + "WHERE S.SHIFT_ID = " + shiftId + " "
                + "AND CAMPUS_ID = " + campusId + " "
                + "AND P.PROGRAM_TYPE_ID = " + ptId;

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

    public static List<int[]> getProgramSubjectAlgList(int posId) throws SQLException {
        String sql = "SELECT * FROM PREREQUISITE P "
                + "INNER JOIN PROGRAM_SUBJECT PS ON P.PROGRAM_SUBJECT_ID = PS.PROGRAM_SUBJECT_ID "
                + "WHERE P.PROGRAM_OF_STUDY_ID = " + posId;

        List<int[]> list = new ArrayList<>();

        try (Statement st = con.createStatement();
                ResultSet rs = st.executeQuery(sql);) {

            while (rs.next()) {

                list.add(new int[]{
                    rs.getInt("PROGRAM_ID"),
                    rs.getInt("SUBJECT_ID")
                });
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
                int consumedSeats = JDBCDatabaseManager.getDisciplineCategoryConsumedSeatsAlg(ob.getCampusCategoryId(), ayId, cposgId);
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

    public static List<CandidateAlg> getCandidateAlgList(int ayId, int ptId, int campusId, String objectionCriterai) throws SQLException {
//        String sql = "SELECT * FROM CANDIDATE_ALG_VIEW "
//                + "WHERE ADMISSION_YEAR_ID = " + ayId + " "
//                + "AND PROGRAM_TYPE_ID = " + ptId + " "
//                + "AND CAMPUS_ID IN (" + campusIds + ") "
//                + "" + objectCriteria
//                + " ORDER BY PERCENTAGE DESC";
        String sql = "SELECT C.ADMISSION_YEAR_ID, C.PROGRAM_TYPE_ID, C.CANDIDATE_ID AS ID, C.DISTRICT_ID, PV.CODE AS PROVINCE_CODE, C.SEAT_NO, C.GENDER, C.AREA, "
                + "	C.TEST_SCORE AS SCORE, C.PERCENTAGE, C.YEARS_DEGREE, C.IS_OBJECTION,  "
                + "	(SELECT PROGRAM_ID FROM CREDENTIAL_DETAILS  "
                + "	 WHERE CANDIDATE_ID = C.CANDIDATE_ID AND DETAIL_OF = (SELECT MAX(DETAIL_OF) FROM CREDENTIAL_DETAILS WHERE CANDIDATE_ID = C.CANDIDATE_ID) "
                + "	) AS LAST_PROGRAM_ID, "
                + "	(SELECT PR_I.REMARKS FROM CREDENTIAL_DETAILS CD_I "
                + "     INNER JOIN PROGRAM PR_I ON PR_I.PROGRAM_ID = CD_I.PROGRAM_ID "
                + "	 WHERE CD_I.CANDIDATE_ID = C.CANDIDATE_ID AND CD_I.DETAIL_OF = (SELECT MAX(DETAIL_OF) FROM CREDENTIAL_DETAILS WHERE CANDIDATE_ID = C.CANDIDATE_ID) "
                + "	) AS PROGRAM_GROUP, "
                + "	(SELECT GROUP_CONCAT(SUBJECT_ID) FROM OPTIONAL_SUBJECT  "
                + "	 WHERE CREDENTIAL_DETAILS_ID = (SELECT CREDENTIAL_DETAILS_ID FROM CREDENTIAL_DETAILS WHERE  "
                + "	 CANDIDATE_ID = C.CANDIDATE_ID AND DETAIL_OF = (SELECT MAX(DETAIL_OF) FROM CREDENTIAL_DETAILS WHERE CANDIDATE_ID = C.CANDIDATE_ID)) "
                + "	) AS OPTIONAL_SUBJECT, "
                + "	(SELECT GROUP_CONCAT(CODE) FROM APPLIED_CATEGORY WHERE CANDIDATE_ID = C.CANDIDATE_ID) AS APPLIED_CATEGORY, "
                + "    (SELECT IS_JURISDICTION FROM JURISDICTION J WHERE DISTRICT_ID = D.DISTRICT_ID AND J.CAMPUS_ID = " + campusId + ") AS IS_JURISDICTION "
                + "	FROM CANDIDATE C "
                + "	INNER JOIN DISTRICT D ON D.DISTRICT_ID = C.DISTRICT_ID "
                + "	INNER JOIN DIVISION DV ON DV.DIVISION_ID = D.DIVISION_ID "
                + "	INNER JOIN PROVINCE PV ON PV.PROVINCE_ID = DV.PROVINCE_ID "
                + "	LEFT JOIN ADMISSION_LIST_DETAILS ALD ON C.CANDIDATE_ID = ALD.CANDIDATE_ID "
                + "	WHERE ALD.ADMISSION_LIST_DETAILS_ID IS NULL "
                + "	AND C.TEST_SCORE > 0 "
                + "	AND C.ADMISSION_YEAR_ID = " + ayId
                + "	AND C.PROGRAM_TYPE_ID = " + ptId
                + "	AND C.CANDIDATE_ID IN (SELECT CANDIDATE_ID FROM APPLIED_CAMPUS WHERE CAMPUS_ID = " + campusId + ") "
                + " "
                + "	UNION "
                + " "
                + "	SELECT C.ADMISSION_YEAR_ID, C.PROGRAM_TYPE_ID, C.CANDIDATE_ID AS ID, C.DISTRICT_ID, PV.CODE AS PROVINCE_CODE, C.SEAT_NO, C.GENDER, C.AREA, "
                + "	C.TEST_SCORE AS SCORE, C.PERCENTAGE, C.YEARS_DEGREE, C.IS_OBJECTION,  "
                + "	(SELECT PROGRAM_ID FROM CREDENTIAL_DETAILS  "
                + "	 WHERE CANDIDATE_ID = C.CANDIDATE_ID AND DETAIL_OF = (SELECT MAX(DETAIL_OF) FROM CREDENTIAL_DETAILS WHERE CANDIDATE_ID = C.CANDIDATE_ID) "
                + "	) AS LAST_PROGRAM_ID, "
                + "	(SELECT PR_I.REMARKS FROM CREDENTIAL_DETAILS CD_I "
                + "     INNER JOIN PROGRAM PR_I ON PR_I.PROGRAM_ID = CD_I.PROGRAM_ID "
                + "	 WHERE CD_I.CANDIDATE_ID = C.CANDIDATE_ID AND CD_I.DETAIL_OF = (SELECT MAX(DETAIL_OF) FROM CREDENTIAL_DETAILS WHERE CANDIDATE_ID = C.CANDIDATE_ID) "
                + "	) AS PROGRAM_GROUP, "
                + "	(SELECT GROUP_CONCAT(SUBJECT_ID) FROM OPTIONAL_SUBJECT  "
                + "	 WHERE CREDENTIAL_DETAILS_ID = (SELECT CREDENTIAL_DETAILS_ID FROM CREDENTIAL_DETAILS WHERE  "
                + "	 CANDIDATE_ID = C.CANDIDATE_ID AND DETAIL_OF = (SELECT MAX(DETAIL_OF) FROM CREDENTIAL_DETAILS WHERE CANDIDATE_ID = C.CANDIDATE_ID)) "
                + "	) AS OPTIONAL_SUBJECT, "
                + "	(SELECT GROUP_CONCAT(CODE) FROM APPLIED_CATEGORY WHERE CANDIDATE_ID = C.CANDIDATE_ID AND CODE NOT IN (SELECT SEL_CAT FROM ADMISSION_LIST_DETAILS WHERE CANDIDATE_ID = C.CANDIDATE_ID AND ACTIVE = FALSE AND SEL_CAT = 1)) AS APPLIED_CATEGORY, "
                + "    (SELECT IS_JURISDICTION FROM JURISDICTION J WHERE DISTRICT_ID = D.DISTRICT_ID AND J.CAMPUS_ID = " + campusId + ") AS IS_JURISDICTION "
                + "	FROM CANDIDATE C "
                + "	INNER JOIN DISTRICT D ON D.DISTRICT_ID = C.DISTRICT_ID "
                + "	INNER JOIN JURISDICTION J ON J.DISTRICT_ID = D.DISTRICT_ID "
                + "	INNER JOIN DIVISION DV ON DV.DIVISION_ID = D.DIVISION_ID "
                + "	INNER JOIN PROVINCE PV ON PV.PROVINCE_ID = DV.PROVINCE_ID "
                + "	INNER JOIN ADMISSION_LIST_DETAILS ALD ON ALD.CANDIDATE_ID = C.CANDIDATE_ID "
                + "	INNER JOIN PART_REGISTRY PR ON PR.ADMISSION_LIST_DETAILS_ID = ALD.ADMISSION_LIST_DETAILS_ID "
                + "	WHERE ALD.ADMISSION_LIST_DETAILS_ID IS NOT NULL "
                + "	AND ALD.ADMISSION_LIST_DETAILS_ID  IN  "
                + "    ( "
                + "		SELECT ADMISSION_LIST_DETAILS_ID FROM PART_REGISTRY AS PR  "
                + "		WHERE PR.TYPE <> 1  "
                + "		AND ADMISSION_LIST_DETAILS_ID IN ( "
                + "                SELECT ADMISSION_LIST_DETAILS_ID FROM ADMISSION_LIST_DETAILS AS ALD "
                + "			    INNER JOIN candidate_program_of_study AS CNPOS_I ON ALD.candidate_program_of_study_id = CNPOS_I.candidate_program_of_study_id  "
                + "				WHERE ALD.ACTIVE = 1 AND CNPOS_I.choice_no > 1  "
                + "        )   "
                + "		OR ADMISSION_LIST_DETAILS_ID NOT IN ( "
                + "                SELECT ADMISSION_LIST_DETAILS_ID FROM ADMISSION_LIST_DETAILS AS ALD "
                + "			    INNER JOIN candidate_program_of_study AS CNPOS_I ON ALD.candidate_program_of_study_id = CNPOS_I.candidate_program_of_study_id  "
                + "				INNER JOIN CAMPUS_PROGRAM_OF_STUDY AS CPOS_I ON CPOS_I.CAMPUS_program_of_study_id = CNPOS_I.CAMPUS_program_of_study_id  "
                + "			    INNER JOIN SHIFT S ON S.shift_id = CPOS_I.shift_id "
                + "				WHERE ALD.ACTIVE = 1 AND S.is_morning = TRUE AND CNPOS_I.CHOICE_NO = 1 AND ALD.SEL_CAT IN (0,3,4,6) "
                + "        )   "
                + "	) "
                + "	AND C.TEST_SCORE > 0 "
                + "	AND C.ADMISSION_YEAR_ID = " + ayId
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

    public static Map<Integer, Map<Boolean, List<Integer[]>>> getCandidateChoicesAlg(int candidateId, int campusId) throws SQLException {
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
                + "	GROUP BY CNPOS.CANDIDATE_ID, S.IS_MORNING  "
                + "     ORDER BY CM.DISPLAY_ORDER, S.IS_MORNING DESC";

        try (Statement st = con.createStatement();
                ResultSet rs = st.executeQuery(sql);) {
            /* [campusId, [isMorning, List<Integer[posId, cposId, cnposId, choiceNo]>]]*/
            Map<Integer, Map<Boolean, List<Integer[]>>> choicesMap = new LinkedHashMap<>();
            while (rs.next()) {
                Map<Boolean, List<Integer[]>> innerMap = new LinkedHashMap<>();
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
                choicesMap.put(campusId, innerMap);
            }
            return choicesMap;
        }
    }

    public static Object[] getCandidateSelectionAlg(int candidateId, int campusId) throws SQLException {
        String sql = "SELECT CPOS.CAMPUS_ID, "
                + "		SH.IS_MORNING, "
                + "		CPOS.PROGRAM_OF_STUDY_ID, "
                + "		ALD.CPOS_GROUP_ID, "
                + "		CNPOS.CHOICE_NO, "
                + "		ALD.SEL_CAT,"
                + "             CAT.CODE,"
                + "    		CPOS.CAMPUS_PROGRAM_OF_STUDY_ID "
                + "FROM ADMISSION_LIST_DETAILS AS ALD "
                + "INNER JOIN CANDIDATE_PROGRAM_OF_STUDY AS CNPOS  ON CNPOS.candidate_program_of_study_id = ALD.candidate_program_of_study_id "
                + "INNER JOIN CAMPUS_PROGRAM_OF_STUDY AS CPOS ON CPOS.CAMPUS_PROGRAM_OF_STUDY_ID = CNPOS.CAMPUS_PROGRAM_OF_STUDY_ID "
                + "INNER JOIN SHIFT AS SH ON SH.SHIFT_ID = CPOS.SHIFT_ID "
                + "INNER JOIN CAMPUS_CATEGORY CCAT ON CCAT.CAMPUS_CATEGORY_ID  = ALD.CAMPUS_CATEGORY_ID "
                + "INNER JOIN CATEGORY CAT ON CAT.CATEGORY_ID  = CCAT.CATEGORY_ID "
                + "WHERE ALD.ACTIVE = TRUE AND ALD.ADMISSION_LIST_DETAILS_ID IN ( SELECT ADMISSION_LIST_DETAILS_ID FROM PART_REGISTRY) "
                + "AND ALD.CANDIDATE_ID = " + candidateId + " "
                + "AND CPOS.CAMPUS_ID = " + campusId;

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
        String sql = "select pre.program_of_study_id as pos_id, ps.program_id, ps.subject_id, pre.percentage  "
                + "from prerequisite pre "
                + "inner join program_subject ps on ps.program_subject_id = pre.program_subject_id "
                + "inner join program_of_study pos on pos.program_of_study_id = pre.program_of_study_id "
                + "inner join program pr2 on pr2.program_id = pos.program_id "
                + "inner join program pr on pr.program_id = ps.program_id "
                + "where pr2.program_type_id = 2 "
                + "order by pre.program_of_study_id";
        try (Statement st = con.createStatement();
                ResultSet rs = st.executeQuery(sql);) {
            Map<Integer, List<Object[]>> map = new LinkedHashMap<>();
            while (rs.next()) {
                int posId = rs.getInt("pos_id");
                int programId = rs.getInt("program_id");
                int subjectId = rs.getInt("subject_id");
                float perc = rs.getFloat("percentage");

                List<Object[]> list = map.get(posId);
                if (list == null) {
                    list = new ArrayList<>();
                    map.put(posId, list);
                }
                list.add(new Object[]{
                    programId,
                    subjectId,
                    perc
                });
            }
            return map;
        }
    }
    
    public static List<Object[]> getexportResultForBacheclorAdmissionList(int programTypeId, int admissionYearId, int admissionListId)throws SQLException{
        String sql = "  SELECT " +
                    "   C.CANDIDATE_ID " +
                    ",  C.SEAT_NO " +
                    ",  C.NAME " +
                    ",  C.FATHERS_NAME " +
                    ",     (SELECT D.NAME FROM DISTRICT D WHERE D.DISTRICT_ID = C.DISTRICT_ID) " +
                    "   AS DISTRICT " +
                    ",  C.AREA " +
                    ",     (SELECT P.NAME  FROM CREDENTIAL_DETAILS CD INNER JOIN PROGRAM P ON P.PROGRAM_ID = CD.PROGRAM_ID WHERE DETAIL_OF = 1 AND CD.CANDIDATE_ID = C.CANDIDATE_ID) " +
                    "   AS DEGREE " +
                    ",     (SELECT MARKS_OBTAINED FROM CREDENTIAL_DETAILS CD WHERE DETAIL_OF = 0 AND CD.CANDIDATE_ID = C.CANDIDATE_ID) " +
                    "   AS SSC_OBTAINED " +
                    ",     (SELECT ((MARKS_OBTAINED/TOTAL_MARKS)*100) FROM CREDENTIAL_DETAILS CD WHERE DETAIL_OF = 0 AND CD.CANDIDATE_ID = C.CANDIDATE_ID) " +
                    "   AS SSC_PERC " +
                    ",     (SELECT MARKS_OBTAINED FROM CREDENTIAL_DETAILS CD WHERE DETAIL_OF = 1 AND CD.CANDIDATE_ID = C.CANDIDATE_ID) " +
                    "   AS HSC_OBTAINED " +
                    ",     (SELECT ((MARKS_OBTAINED/TOTAL_MARKS)*100) FROM CREDENTIAL_DETAILS CD WHERE DETAIL_OF = 1 AND CD.CANDIDATE_ID = C.CANDIDATE_ID) " +
                    "   AS HSC_PERC " +
                    ",     (SELECT MARKS_OBTAINED FROM CREDENTIAL_DETAILS CD WHERE DETAIL_OF = 2 AND CD.CANDIDATE_ID = C.CANDIDATE_ID) " +
                    "   AS GRD_OBTAINED " +
                    ",     (SELECT ((MARKS_OBTAINED/TOTAL_MARKS)*100) FROM CREDENTIAL_DETAILS CD WHERE DETAIL_OF = 2 AND CD.CANDIDATE_ID = C.CANDIDATE_ID) " +
                    "   AS GRD_PERC " +
                    ",  C.DEDUCTION_MARKS " +
                    ",  C.TEST_SCORE " +
                    ",  (C.TEST_SCORE*0.4) AS TEST_PERC " +
                    ",     (SELECT ((MARKS_OBTAINED/TOTAL_MARKS)*100)*0.1 FROM CREDENTIAL_DETAILS CD WHERE DETAIL_OF = 0 AND CD.CANDIDATE_ID = C.CANDIDATE_ID) " +
                    "      + " +
                    "      (SELECT (( (MARKS_OBTAINED-C.DEDUCTION_MARKS) /TOTAL_MARKS)*100)*0.5 FROM CREDENTIAL_DETAILS CD WHERE DETAIL_OF = 1 AND CD.CANDIDATE_ID = C.CANDIDATE_ID) " +
                    "      + " +
                    "      (((C.TEST_SCORE/400)*100)*0.4) " +
                    "      AS CPN " +
                    ",  CONCAT(CM.NAME,' ',CM.LOCATION) AS CAMPUS " +
                    ",  SEL_CAT AS CATEGORY " +
                    ",  CONCAT(P.NAME,'(',POS.NAME,')') AS DISCIPLINE " +
                    ",  CNPOS.CHOICE_NO AS CHOICE_NO " +
                    ",  CPOS.CAMPUS_ID AS CAMPUS_ID " +
                    ",  C.PROGRAM_TYPE_ID AS PROGRAM_TYPE " +
                    ",  0 AS FEES_AMOUNT " +
                    ",  0 AS LAST_DATE " +
                    ",  AY.YEAR AS ADMISSION_SESSION " +
                    ",  AL.LIST_NO AS ADMISSION_LIST_NO " +
                    ",  C.OBJECTION_REMARKS " +
                    ",  (SELECT MARKS_OBTAINED-C.DEDUCTION_MARKS FROM CREDENTIAL_DETAILS CD WHERE DETAIL_OF = 1 AND CD.CANDIDATE_ID = C.CANDIDATE_ID) " +
                    "   AS MARKS_AFTER_DEDUCTION " +
                    ",  (SELECT II.LOCATION FROM CREDENTIAL_DETAILS CD INNER JOIN ISSUER II ON II.ISSUER_ID = CD.ISSUER_ID WHERE DETAIL_OF = 1 AND CD.CANDIDATE_ID = C.CANDIDATE_ID  ) " +
                    "   AS ISSUER " +
                    ",     (SELECT TOTAL_MARKS FROM CREDENTIAL_DETAILS CD WHERE DETAIL_OF = 0 AND CD.CANDIDATE_ID = C.CANDIDATE_ID) " +
                    "   AS SSC_TOTAL " +
                    ",     (SELECT TOTAL_MARKS FROM CREDENTIAL_DETAILS CD WHERE DETAIL_OF = 1 AND CD.CANDIDATE_ID = C.CANDIDATE_ID) " +
                    "   AS HSC_TOTAL " +
                    ",     (SELECT TOTAL_MARKS FROM CREDENTIAL_DETAILS CD WHERE DETAIL_OF = 2 AND CD.CANDIDATE_ID = C.CANDIDATE_ID) " +
                    "   AS GRD_TOTAL " +
                    ",     (SELECT PASSING_YEAR FROM CREDENTIAL_DETAILS CD WHERE DETAIL_OF = 0 AND CD.CANDIDATE_ID = C.CANDIDATE_ID) " +
                    "   AS SSC_YEAR " +
                    ",     (SELECT PASSING_YEAR FROM CREDENTIAL_DETAILS CD WHERE DETAIL_OF = 1 AND CD.CANDIDATE_ID = C.CANDIDATE_ID) " +
                    "   AS HSC_YEAR " +
                    ",     (SELECT PASSING_YEAR FROM CREDENTIAL_DETAILS CD WHERE DETAIL_OF = 2 AND CD.CANDIDATE_ID = C.CANDIDATE_ID) " +
                    "   AS GRD_YEAR "+ 
                    " , C.email AS EMAIL"+ 
                    " , C.family_mobile  AS FAMILY_MOBILE  "+
                    "FROM CANDIDATE C " +
                    "INNER JOIN ADMISSION_LIST_DETAILS ALD ON ALD.CANDIDATE_ID = C.CANDIDATE_ID " +
                    "INNER JOIN CANDIDATE_PROGRAM_OF_STUDY CNPOS ON CNPOS.CANDIDATE_PROGRAM_OF_STUDY_ID = ALD.CANDIDATE_PROGRAM_OF_STUDY_ID " +
                    "INNER JOIN ADMISSION_LIST AL ON AL.ADMISSION_LIST_ID = ALD.ADMISSION_LIST_ID " +
                    "INNER JOIN ADMISSION_SESSION ASES ON ASES.ADMISSION_SESSION_ID = AL.ADMISSION_SESSION_ID " +
                    "INNER JOIN ADMISSION_YEAR AY ON AY.ADMISSION_YEAR_ID = ASES.ADMISSION_YEAR_ID " +
                    "INNER JOIN CPOS_GROUP CPOSG ON CPOSG.CPOS_GROUP_ID = ALD.CPOS_GROUP_ID " +
                    "INNER JOIN CAMPUS_PROGRAM_OF_STUDY CPOS ON CPOS.CAMPUS_PROGRAM_OF_STUDY_ID = CPOSG.CAMPUS_PROGRAM_OF_STUDY_ID " +
                    "INNER JOIN CAMPUS CM ON CM.CAMPUS_ID = CPOS.CAMPUS_ID " +
                    "INNER JOIN PROGRAM_OF_STUDY POS ON POS.PROGRAM_OF_STUDY_ID = CPOS.PROGRAM_OF_STUDY_ID " +
                    "INNER JOIN PROGRAM P ON P.PROGRAM_ID = POS.PROGRAM_ID " +
                    "WHERE C.PROGRAM_TYPE_ID = " +programTypeId+
                    " AND C.ADMISSION_YEAR_ID = " +admissionYearId+
                    " AND ALD.ADMISSION_LIST_ID = "+admissionListId;
        
        try (Statement st = con.createStatement();
                ResultSet rs = st.executeQuery(sql);) {
                List<Object[]> list = new LinkedList();
                int columns = rs.getMetaData().getColumnCount();
                while(rs.next()){
                    List objects = new LinkedList();
                    for(int index = 1; index <= columns;index++){
                        objects.add(rs.getObject(index));
                    }
                    list.add(objects.toArray());
                }
                return list;
        
        }
        
    }
    
}
