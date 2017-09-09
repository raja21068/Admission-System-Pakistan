package admission.controller;

import admission.model.AdmissionList;
import admission.model.AdmissionListDetails;
import admission.model.AdmissionSession;
import admission.model.Campus;
import admission.model.CampusCategory;
import admission.model.CampusProgramOfStudy;
import admission.model.Candidate;
import admission.model.CandidateProgramOfStudy;
import admission.model.Category;
import admission.model.CposGroup;
import admission.model.Discipline;
import admission.model.DisciplineCategorySeats;
import admission.model.District;
import admission.model.DistrictSeatDistribution;
import admission.model.Faculty;
import admission.model.Jurisdiction;
import admission.model.Part;
import admission.model.PartRegistry;
import admission.model.Prerequisite;
import admission.model.ProgramOfStudy;
import admission.model.ProgramSubject;
import admission.model.Test;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Raja Kumar & Jay Kumar
 */
public class DatabaseManager {

    private static Session session;

    static {
        init();
    }

    public static void init() {
        System.out.println("Please wait...");
        try {
            session = admission.controller.HibernateUtil.openSession();
        } catch (HibernateException he) {
            he.printStackTrace();
            throw he;
        }
    }

    public static void load() {
        System.out.println("Loaded successfully");
    }

    public static void refresh(Object object) {
        session.refresh(object);
    }

    public static List getData(String className, String orderBy) throws HibernateException {
        Transaction trancaction = null;
        try {
            trancaction = session.beginTransaction();

            String hql = "FROM " + className + " ORDER BY " + orderBy;
            Query query = session.createQuery(hql);
            query.setCacheable(true);
            List list = query.list();

            trancaction.commit();

            return list;
        } catch (HibernateException he) {
            if (trancaction != null) {
                trancaction.rollback();
                session.flush();
            }
            throw he;
        }
    }

    public static <T> List<T> getData(Class<T> className, String orderBy) throws HibernateException {
        Transaction trancaction = null;
        try {
            trancaction = session.beginTransaction();

            String hql = "FROM " + className.getSimpleName() + " ORDER BY " + orderBy;
            Query query = session.createQuery(hql);
            query.setCacheable(true);
            List<T> list = query.list();

            trancaction.commit();

            return list;
        } catch (HibernateException he) {
            if (trancaction != null) {
                trancaction.rollback();
                session.flush();
            }
            throw he;
        }
    }

    public static <T> List<T> executeQuery(Class<T> className, String hql) throws HibernateException {
        Transaction trancaction = null;
        try {
            trancaction = session.beginTransaction();

            Query query = session.createQuery(hql);
            query.setCacheable(true);
            List<T> list = query.list();

            trancaction.commit();

            return list;
        } catch (HibernateException he) {
            if (trancaction != null) {
                trancaction.rollback();
                session.flush();
            }
            throw he;
        }
    }

    public static <T> List<T> executeQuery(Class<T> className, String hql, Map<String, Object> params) throws HibernateException {
        Transaction trancaction = null;
        try {
            trancaction = session.beginTransaction();

            Query query = session.createQuery(hql);
            for (Entry<String, Object> e : params.entrySet()) {
                query.setParameter(e.getKey(), e.getValue());
            }
            query.setCacheable(true);
            List<T> list = query.list();

            trancaction.commit();

            return list;
        } catch (HibernateException he) {
            if (trancaction != null) {
                trancaction.rollback();
                session.flush();
            }
            throw he;
        }
    }

    public static List getData(String className, String where, String orderBy) throws HibernateException {
        Transaction trancaction = null;
        try {
            trancaction = session.beginTransaction();

            String hql = "FROM " + className + " WHERE " + where + " ORDER BY " + orderBy;
            Query query = session.createQuery(hql);
            query.setCacheable(true);
            List list = query.list();

            trancaction.commit();

            return list;
        } catch (HibernateException he) {
            if (trancaction != null) {
                trancaction.rollback();
                session.flush();
            }
            throw he;
        }
    }

    public static <T> List<T> getData(Class<T> className, String where, String orderBy) throws HibernateException {
        Transaction trancaction = null;
        try {
            trancaction = session.beginTransaction();

            String hql = "FROM " + className.getSimpleName() + " WHERE " + where + (orderBy != null && !orderBy.isEmpty() ? " ORDER BY " + orderBy : "");
            Query query = session.createQuery(hql);
            query.setCacheable(true);
            List<T> list = query.list();

            trancaction.commit();

            return list;
        } catch (HibernateException he) {
            if (trancaction != null) {
                trancaction.rollback();
                session.flush();
            }
            throw he;
        }
    }

    public static void clear() {
        if (session != null) {
            session.flush();
            session.clear();
        }
    }

    public static int addData(Object object) throws HibernateException {
        Transaction trancaction = null;
        try {
            trancaction = session.beginTransaction();

            Integer id = (Integer) session.save(object);

//            session.flush();
//            session.clear();
            trancaction.commit();
            return id;
        } catch (HibernateException he) {
            if (trancaction != null) {
                trancaction.rollback();
                session.flush();
            }
            throw he;
        }
    }

    public static void updateData(Object object) {
        Transaction trancaction = null;
        try {
            trancaction = session.beginTransaction();

            session.update(object);

            trancaction.commit();
        } catch (HibernateException he) {
            if (trancaction != null) {
                trancaction.rollback();
                session.flush();
            }
            throw he;
        }
    }

    public static void save(Object object) throws HibernateException {
        Transaction trancaction = null;
        try {
            trancaction = session.beginTransaction();

            session.saveOrUpdate(object);

            trancaction.commit();
        } catch (HibernateException he) {
            if (trancaction != null) {
                trancaction.rollback();
                session.flush();
            }
            throw he;
        }
    }

    public static int deleteData(String className, String where) {
        Transaction trancaction = null;
        try {
            trancaction = session.beginTransaction();

            String hql = "DELETE FROM " + className + " WHERE " + where;
            Query query = session.createQuery(hql);
            query.setCacheable(true);

            int result = query.executeUpdate();

//            session.flush();
//            session.clear();
            trancaction.commit();
            return result;
        } catch (HibernateException he) {
            if (trancaction != null) {
                trancaction.rollback();
                session.flush();
            }
            throw he;
        }
    }

    public static <T> int deleteData(Class<T> className, String where) {
        Transaction trancaction = null;
        try {
            trancaction = session.beginTransaction();

            String hql = "DELETE FROM " + className.getSimpleName() + " WHERE " + where;
            Query query = session.createQuery(hql);
            query.setCacheable(true);

            int result = query.executeUpdate();

//            session.flush();
//            session.clear();
            trancaction.commit();
            return result;
        } catch (HibernateException he) {
            if (trancaction != null) {
                trancaction.rollback();
                session.flush();
            }
            throw he;
        }
    }

    public static boolean deleteData(Object ob) throws HibernateException {
        Transaction trancaction = null;
        try {
            trancaction = session.beginTransaction();
            session.delete(ob);

            session.flush();
            session.clear();

            trancaction.commit();
            return true;
        } catch (HibernateException he) {
            if (trancaction != null) {
                trancaction.rollback();
                session.flush();
            }
            throw he;
        }
    }

    public static Object getSingleRecord(String className, Integer id) throws HibernateException {
        Transaction trancaction = null;
        try {
            trancaction = session.beginTransaction();

            Object object = session.get(className, id);

            trancaction.commit();

            return object;
        } catch (HibernateException he) {
            if (trancaction != null) {
                trancaction.rollback();
            }
            throw he;
        }
    }

    public static <T> T getSingleRecord(Class<T> modelClass, Integer id) throws HibernateException {
        Transaction trancaction = null;
        try {
            trancaction = session.beginTransaction();

            Object object = session.get(modelClass, id);

            trancaction.commit();

            return (T) object;
        } catch (HibernateException he) {
            if (trancaction != null) {
                trancaction.rollback();
            }
            throw he;
        }
    }

    public static Object getSingleRecord(String className, String where) throws HibernateException {
        Transaction trancaction = null;
        try {
            trancaction = session.beginTransaction();
            String hql = "FROM " + className + " WHERE " + where;
            Query query = session.createQuery(hql);
            query.setCacheable(true);
            List list = query.list();

            trancaction.commit();

            return list.isEmpty() ? null : list.get(0);
        } catch (HibernateException he) {
            if (trancaction != null) {
                trancaction.rollback();
            }
            throw he;
        }
    }

    public static <T> T getSingleRecord(Class<T> _class, String where) throws HibernateException {
        Transaction trancaction = null;
        try {
            trancaction = session.beginTransaction();
            String hql = "FROM " + _class.getSimpleName() + " WHERE " + where;
            Query query = session.createQuery(hql);
            query.setCacheable(true);
            List<T> list = query.list();

            trancaction.commit();

            return list.isEmpty() ? null : list.get(0);
        } catch (HibernateException he) {
            if (trancaction != null) {
                trancaction.rollback();
            }
            throw he;
        }
    }

//****************************************************************************************************/
    public static Object userAuthentication(String username, String password) throws HibernateException {
        Transaction trancaction = null;
        try {
            trancaction = session.beginTransaction();

            String hql = "FROM User WHERE username='" + username + "' AND password='" + password + "'";
            Query query = session.createQuery(hql);
            query.setCacheable(true);
            List list = query.list();

            trancaction.commit();

            return list.isEmpty() ? null : list.get(0);
        } catch (HibernateException he) {
            if (trancaction != null) {
                trancaction.rollback();
            }
            throw he;
        }
    }

    public static Candidate getCandidate(Integer admissionYearId, Integer programTypeId) throws HibernateException {
        Transaction trancaction = null;
        try {
            trancaction = session.beginTransaction();

            String hql = "SELECT c FROM Candidate AS c "
                    + "INNER JOIN c.admissionYear AS ay "
                    + "INNER JOIN c.programType AS pt "
                    + "WHERE ay.admissionYearId = " + admissionYearId + " "
                    + "AND pt.programTypeId = " + programTypeId;

            Query query = session.createQuery(hql);
            query.setCacheable(true);
            List<Candidate> list = query.list();

            trancaction.commit();

            return list.isEmpty() ? null : list.get(0);
        } catch (HibernateException he) {
            if (trancaction != null) {
                trancaction.rollback();
            }
            throw he;
        }
    }

    public static Candidate getCandidate(Integer candidateId) throws HibernateException {
        Transaction trancaction = null;
        try {
            trancaction = session.beginTransaction();

//            String hql = "SELECT c FROM Candidate AS c "
//                    + "JOIN c.admissionYear AS ay "
//                    + "JOIN c.programType AS pt "
//                    + "WHERE ay.admissionYearId = " + admissionYearId + " "
//                    + "AND pt.programTypeId = " + programTypeId;
            Criteria criteria = session.createCriteria(Candidate.class);
            criteria.add(Restrictions.eqProperty("candidateld", String.valueOf(candidateId)));
            List<Candidate> list = criteria.list();

            trancaction.commit();

            return list.isEmpty() ? null : list.get(0);
        } catch (HibernateException he) {
            if (trancaction != null) {
                trancaction.rollback();
            }
            throw he;
        }
    }

    public static Candidate getCandidate(Integer admissionYearId, Integer programTypeId, String seatNo) throws HibernateException {
        Transaction trancaction = null;
        try {
            trancaction = session.beginTransaction();

            String hql = "SELECT c FROM Candidate AS c "
                    + "INNER JOIN c.admissionYear AS ay "
                    + "INNER JOIN c.programType AS pt "
                    + "WHERE ay.admissionYearId = " + admissionYearId + " "
                    + "AND pt.programTypeId = " + programTypeId + " "
                    + "AND c.seatNo = " + seatNo;

            Query query = session.createQuery(hql);
            query.setCacheable(true);
            List<Candidate> list = query.list();

            trancaction.commit();

            return list.isEmpty() ? null : list.get(0);
        } catch (HibernateException he) {
            if (trancaction != null) {
                trancaction.rollback();
            }
            throw he;
        }
    }

    public static List<Candidate> getCandidates(Integer admissionYearId, Integer campusId, Integer programTypeId) throws HibernateException {
        Transaction trancaction = null;
        try {
            trancaction = session.beginTransaction();

            String hql = "SELECT cn FROM AppliedCampus AS ac "
                    + "JOIN ac.candidate AS cn "
                    + "JOIN ac.campus AS c "
                    + "JOIN cn.admissionYear AS ay "
                    + "JOIN cn.programType AS pt "
                    + "WHERE ay.admissionYearId = " + admissionYearId + " "
                    + "AND c.campusId = " + campusId + " "
                    + "AND pt.programTypeId = " + programTypeId + " "
                    + "ORDER BY cn.seatNo";

            Query query = session.createQuery(hql);
            query.setCacheable(true);
            List<Candidate> list = query.list();

            trancaction.commit();

            return list;
        } catch (HibernateException he) {
            if (trancaction != null) {
                trancaction.rollback();
            }
            throw he;
        }
    }

    public static List<Candidate> getMainCampusCandidates(Integer admissionYearId, boolean isMain, Integer programTypeId) throws HibernateException {
        Transaction trancaction = null;
        try {
            trancaction = session.beginTransaction();

            String hql = "SELECT cn FROM AppliedCampus AS ac "
                    + "JOIN ac.candidate AS cn "
                    + "JOIN ac.campus AS c "
                    + "JOIN cn.admissionYear AS ay "
                    + "JOIN cn.programType AS pt "
                    + "WHERE ay.admissionYearId = " + admissionYearId + " "
                    + "AND c.isMain = " + isMain + " "
                    + "AND pt.programTypeId = " + programTypeId + " "
                    + "ORDER BY cn.seatNo";

            Query query = session.createQuery(hql);
            query.setCacheable(true);
            List<Candidate> list = query.list();

            trancaction.commit();

            return list;
        } catch (HibernateException he) {
            if (trancaction != null) {
                trancaction.rollback();
            }
            throw he;
        }
    }

//    public static List<CampusProgramOfStudy> getCandidateChoices(Integer campusId, Integer shiftId, Integer candidateId) throws HibernateException {
//        Transaction trancaction = null;
//        try {
//            trancaction = session.beginTransaction();
//
//            String hql = "SELECT cpos FROM CandidateProgramOfStudy AS cnpos "
//                    + "JOIN cnpos.campusProgramOfStudy AS cpos "
//                    + "JOIN cpos.campus AS c "
//                    + "JOIN cpos.shift AS s "
//                    + "JOIN cnpos.candidate AS cn "
//                    + "WHERE c.campusId=" + campusId + " "
//                    + "AND s.shiftId=" + shiftId + " "
//                    + "AND cn.candidateId=" + candidateId + " "
//                    + "ORDER BY cnpos.candidateProgramOfStudyId";
//
//            Query query = session.createQuery(hql);
//            query.setCacheable(true);
//            List<CampusProgramOfStudy> list = query.list();
//
//            trancaction.commit();
//
//            return list;
//        } catch (HibernateException he) {
//            if (trancaction != null) {
//                trancaction.rollback();
//            }
//            throw he;
//        }
//    }
    public static int updateDisplayOrder(String className, String value, String where) {
        Transaction trancaction = null;
        try {
            trancaction = session.beginTransaction();

            String hql = "UPDATE " + className + " SET displayOrder = " + value + " WHERE " + where;
            Query query = session.createQuery(hql);
            query.setCacheable(true);

            int result = query.executeUpdate();

            trancaction.commit();
            return result;
        } catch (HibernateException he) {
            if (trancaction != null) {
                trancaction.rollback();
            }
            throw he;
        }
    }

    public static Test getTest(Integer campusId, Integer admissionYearId, Integer programTypeId) throws HibernateException {
        Transaction trancaction = null;
        try {
            trancaction = session.beginTransaction();

            String hql = "SELECT t FROM Test AS t "
                    + "JOIN t.campus AS c "
                    + "JOIN t.admissionYear AS ay "
                    + "JOIN t.programType AS pt "
                    + "WHERE c.campusId = " + campusId + " "
                    + "AND ay.admissionYearId = " + admissionYearId + " "
                    + "AND pt.programTypeId = " + programTypeId;

            Query query = session.createQuery(hql);
            query.setCacheable(true);
            List<Test> list = query.list();

            trancaction.commit();

            return list.isEmpty() ? null : list.get(0);
        } catch (HibernateException he) {
            if (trancaction != null) {
                trancaction.rollback();
            }
            throw he;
        }
    }

    public static List<Faculty> getMainCampusHierarchy(Integer campusId, Integer shiftId) {
        Transaction trancaction = null;
        try {
            trancaction = session.beginTransaction();

            String hql = "SELECT DISTINCT f FROM CampusProgramOfStudy cpos "
                    + "JOIN cpos.programOfStudy pos "
                    + "JOIN cpos.campus c "
                    + "JOIN cpos.shift s "
                    + "JOIN pos.discipline di "
                    + "JOIN di.department dp "
                    + "JOIN dp.faculty f "
                    + "WHERE c.campusId = " + campusId + " "
                    + "AND s.shiftId = " + shiftId + " "
                    + "ORDER BY f.name, dp.name, di.name, pos.name";

            Query query = session.createQuery(hql);
            query.setCacheable(true);
            List<Faculty> list = query.list();

            trancaction.commit();

            return list;
        } catch (HibernateException he) {
            if (trancaction != null) {
                trancaction.rollback();
            }
            throw he;
        }
    }

//    public static List<ProgramOfStudy> getFacultyProgramOfStudy(Integer facultyId) throws HibernateException{
//        Transaction trancaction = null; 
//        try{
//            trancaction = session.beginTransaction();
//        
//            String hql = "SELECT pos FROM ProgramOfStudy AS pos "
//                    + "JOIN pos.discipline AS di "
//                    + "JOIN di.department AS de "
//                    + "JOIN de.faculty AS fac "
//                    + "WHERE fac.facultyId = " + facultyId + " "
//                    + "ORDER BY pos.name";
//
//            Query query = session.createQuery(hql);
//            List<ProgramOfStudy> list = query.list();
//        
//            trancaction.commit();
//            
//            return list;
//        }catch (HibernateException he) {
//            if (trancaction != null) trancaction.rollback();
//            throw he;
//        }
//    }
//    public static Vector<PosGroup> getFacultyProgramOfStudyGroup(Integer facultyId) throws HibernateException{
//        Transaction trancaction = null; 
//        try{
//            trancaction = session.beginTransaction();
//        
//            String hql = "SELECT posg FROM PosGroup AS posg "
//                    + "JOIN posg.programOfStudy AS pos "
//                    + "JOIN pos.discipline AS di "
//                    + "JOIN di.department AS de "
//                    + "JOIN de.faculty AS fac "
//                    + "WHERE fac.facultyId = " + facultyId + " "
//                    + "ORDER BY pos.name";
//
//            Query query = session.createQuery(hql);
//            List<PosGroup> list = query.list();
//        
//            trancaction.commit();
//            
//            return new Vector<>(list);
//        }catch (HibernateException he) {
//            if (trancaction != null) trancaction.rollback();
//            throw he;
//        }
//    }
    public static List<CampusCategory> getCampusCategory(Integer campusId, Integer programTypeId, Integer shiftId, String orderBy) throws HibernateException {
        Transaction trancaction = null;
        try {
            trancaction = session.beginTransaction();

            String hql = "SELECT cc FROM CampusCategory AS cc "
                    + "JOIN cc.campus AS c "
                    + "JOIN cc.programType AS pt "
                    + "JOIN cc.shift AS s "
                    + "WHERE c.campusId = " + campusId + " "
                    + "AND pt.programTypeId = " + programTypeId + " "
                    + "AND s.shiftId = " + shiftId + " "
                    + "ORDER BY cc." + orderBy;

            Query query = session.createQuery(hql);
            query.setCacheable(true);
            List<CampusCategory> list = query.list();

            trancaction.commit();

            return list;
        } catch (HibernateException he) {
            if (trancaction != null) {
                trancaction.rollback();
            }
            throw he;
        }
    }

    public static List<CampusCategory> getCampusCategory(Integer cposgId, Integer admissionListId) throws HibernateException {
        Transaction trancaction = null;
        try {
            trancaction = session.beginTransaction();

            String hql = "SELECT DISTINCT cc FROM AdmissionListDetails AS ald "
                    + "JOIN ald.campusCategory AS cc "
                    + "JOIN ald.cposGroup AS cposg "
                    + "JOIN ald.admissionList AS al "
                    + "WHERE cposg.cposGroupId = " + cposgId + " "
                    + "AND al.admissionListId = " + admissionListId + " "
                    + "ORDER BY cc.displayOrder";
//            System.out.println(hql);

            Query query = session.createQuery(hql);
            query.setCacheable(true);
            List<CampusCategory> list = query.list();

            trancaction.commit();

            return list;
        } catch (HibernateException he) {
            if (trancaction != null) {
                trancaction.rollback();
            }
            throw he;
        }
    }

    public static List<AdmissionListDetails> getAdmissionListDetails(Integer campusCategoryId, Integer admissionListId) throws HibernateException {
        Transaction trancaction = null;
        try {
            trancaction = session.beginTransaction();

            String hql = "SELECT DISTINCT ald FROM AdmissionListDetails AS ald "
                    + "JOIN ald.campusCategory AS cc "
                    + "JOIN ald.admissionList AS al "
                    + "JOIN ald.candidateProgramOfStudy AS cpos "
                    + "WHERE cc.campusCategoryId = " + campusCategoryId + " "
                    + "AND al.admissionListId = " + admissionListId + " "
                    + "ORDER BY ald.admissionListDetailsId";
//            System.out.println(hql);
            Query query = session.createQuery(hql);
            query.setCacheable(true);
            List<AdmissionListDetails> list = query.list();

            trancaction.commit();

            return list;
        } catch (HibernateException he) {
            if (trancaction != null) {
                trancaction.rollback();
            }
            throw he;
        }
    }

    public static List<AdmissionListDetails> getAdmissionListDetails(Integer admissionListId) throws HibernateException {
        Transaction trancaction = null;
        try {
            trancaction = session.beginTransaction();

            String hql = "SELECT DISTINCT ald FROM AdmissionListDetails AS ald "
                    + "JOIN ald.campusCategory AS cc "
                    + "JOIN ald.admissionList AS al "
                    + "JOIN ald.candidateProgramOfStudy AS cpos "
                    + "WHERE al.admissionListId = " + admissionListId + " "
                    + "ORDER BY ald.admissionListDetailsId";
//            System.out.println(hql);
            Query query = session.createQuery(hql);
            query.setCacheable(true);
            List<AdmissionListDetails> list = query.list();

            trancaction.commit();

            return list;
        } catch (HibernateException he) {
            if (trancaction != null) {
                trancaction.rollback();
            }
            throw he;
        }
    }

    public static CampusCategory getCampusCategory(Integer campusId, Integer programTypeId, Integer shiftId, Integer code) throws HibernateException {
        Transaction trancaction = null;
        try {
            trancaction = session.beginTransaction();

            String hql = "SELECT cc FROM CampusCategory AS cc "
                    + "JOIN cc.campus AS ca "
                    + "JOIN cc.programType AS pt "
                    + "JOIN cc.shift AS s "
                    + "JOIN cc.category AS c "
                    + "WHERE ca.campusId = " + campusId + " "
                    + "AND pt.programTypeId = " + programTypeId + " "
                    + "AND s.shiftId = " + shiftId + " "
                    + "AND c.code = " + code;

            Query query = session.createQuery(hql);
            query.setCacheable(true);
            List<CampusCategory> list = query.list();

            trancaction.commit();

            return list.isEmpty() ? null : list.get(0);
        } catch (HibernateException he) {
            if (trancaction != null) {
                trancaction.rollback();
            }
            throw he;
        }
    }

    public static List<CampusProgramOfStudy> getCampusFacultyProgramOfStudy(Integer facultyId, Integer campusId, Integer programTypeId, Integer shiftId) throws HibernateException {
        Transaction trancaction = null;
        try {
            trancaction = session.beginTransaction();

            String hql = "SELECT cpos FROM CampusProgramOfStudy AS cpos "
                    + "JOIN cpos.programOfStudy AS pos "
                    + "JOIN cpos.shift AS s "
                    + "JOIN cpos.campus AS ca "
                    + "JOIN pos.discipline AS di "
                    + "JOIN di.department AS de "
                    + "JOIN de.faculty AS fac "
                    + "JOIN pos.program AS prog "
                    + "JOIN prog.programType AS pt "
                    + "WHERE fac.facultyId = " + facultyId + " "
                    + "AND ca.campusId = " + campusId + " "
                    + "AND pt.programTypeId = " + programTypeId + " "
                    + "AND s.shiftId = " + shiftId + " "
                    + "ORDER BY pos.name";

            Query query = session.createQuery(hql);
            query.setCacheable(true);
            List<CampusProgramOfStudy> list = query.list();

            trancaction.commit();

            return list;
        } catch (HibernateException he) {
            if (trancaction != null) {
                trancaction.rollback();
            }
            throw he;
        }
    }

    public static List<Faculty> getCampusFaculties(Integer campusId) throws HibernateException {
        Transaction trancaction = null;
        try {
            trancaction = session.beginTransaction();

            String hql = "SELECT DISTINCT fac FROM CampusProgramOfStudy AS cpos "
                    + "JOIN cpos.programOfStudy AS pos "
                    + "JOIN cpos.campus AS ca "
                    + "JOIN pos.discipline AS di "
                    + "JOIN di.department AS de "
                    + "JOIN de.faculty AS fac "
                    + "WHERE ca.campusId = " + campusId + " "
                    + "ORDER BY fac.displayOrder";

            Query query = session.createQuery(hql);
            query.setCacheable(true);
            List<Faculty> list = query.list();

            trancaction.commit();

            return list;
        } catch (HibernateException he) {
            if (trancaction != null) {
                trancaction.rollback();
            }
            throw he;
        }
    }

    public static List<CposGroup> getCampusFacultyCposGroup(Integer facultyId, Integer campusId, Integer programTypeId, Integer shiftId) throws HibernateException {
        Transaction trancaction = null;
        try {
            trancaction = session.beginTransaction();

            String hql = "SELECT cposg FROM CposGroup AS cposg "
                    + "JOIN cposg.campusProgramOfStudy AS cpos "
                    + "JOIN cpos.programOfStudy AS pos "
                    + "JOIN cpos.shift AS s "
                    + "JOIN cpos.campus AS ca "
                    + "JOIN pos.discipline AS di "
                    + "JOIN di.department AS de "
                    + "JOIN de.faculty AS fac "
                    + "JOIN pos.program AS prog "
                    + "JOIN prog.programType AS pt "
                    + "WHERE fac.facultyId = " + facultyId + " "
                    + "AND ca.campusId = " + campusId + " "
                    + "AND pt.programTypeId = " + programTypeId + " "
                    + "AND s.shiftId = " + shiftId + " "
                    + "ORDER BY pos.name";

            Query query = session.createQuery(hql);
            query.setCacheable(true);
            List<CposGroup> list = query.list();

            trancaction.commit();

            return list;
        } catch (HibernateException he) {
            if (trancaction != null) {
                trancaction.rollback();
            }
            throw he;
        }
    }

//    public static List<CampusProgramOfStudy> getCampusProgramOfStudy(Integer campusId, Integer programTypeId, Integer shiftId) throws HibernateException {
//        Transaction trancaction = null;
//        try {
//            trancaction = session.beginTransaction();
//
//            String hql = "SELECT cpos FROM CampusProgramOfStudy AS cpos "
//                    + "JOIN cpos.programOfStudy AS pos "
//                    + "JOIN cpos.shift AS s "
//                    + "JOIN cpos.campus AS ca "
//                    + "JOIN pos.program AS prog "
//                    + "JOIN prog.programType AS pt "
//                    + "WHERE ca.campusId = " + campusId + " "
//                    + "AND pt.programTypeId = " + programTypeId + " "
//                    + "AND s.shiftId = " + shiftId + " "
//                    + "ORDER BY pos.name";
//
//            Query query = session.createQuery(hql);
//            query.setCacheable(true);
//            List<CampusProgramOfStudy> list = query.list();
//
//            trancaction.commit();
//
//            return list;
//        } catch (HibernateException he) {
//            if (trancaction != null) {
//                trancaction.rollback();
//            }
//            throw he;
//        }
//    }
    public static List<CampusProgramOfStudy> getCampusProgramOfStudy(Integer campusId, Integer programTypeId, Byte isSeperate) throws HibernateException {
        Transaction trancaction = null;
        try {
            trancaction = session.beginTransaction();

            String hql = "SELECT cpos FROM CampusProgramOfStudy AS cpos "
                    + "JOIN cpos.programOfStudy AS pos "
                    + "JOIN cpos.campus AS ca "
                    + "JOIN pos.program AS prog "
                    + "JOIN prog.programType AS pt "
                    + "WHERE ca.campusId = " + campusId + " "
                    + "AND pt.programTypeId = " + programTypeId + " "
                    + "AND cpos.isSeperate = " + isSeperate + " "
                    + "ORDER BY pos.name";

            Query query = session.createQuery(hql);
            query.setCacheable(true);
            List<CampusProgramOfStudy> list = query.list();

            trancaction.commit();

            return list;
        } catch (HibernateException he) {
            if (trancaction != null) {
                trancaction.rollback();
            }
            throw he;
        }
    }

    public static List<CposGroup> getCampusCposGroup(Integer campusId, Integer shiftId, Integer programTypeId) throws HibernateException {
        Transaction trancaction = null;
        try {
            trancaction = session.beginTransaction();

            String hql = "SELECT cposg FROM CposGroup AS cposg "
                    + "JOIN cposg.campusProgramOfStudy AS cpos "
                    + "JOIN cpos.programOfStudy AS pos "
                    + "JOIN cpos.shift AS s "
                    + "JOIN cpos.campus AS ca "
                    + "JOIN pos.program AS prog "
                    + "JOIN prog.programType AS pt "
                    + "WHERE ca.campusId = " + campusId + " "
                    + "AND pt.programTypeId = " + programTypeId + " "
                    + "AND s.shiftId = " + shiftId + " "
                    + "ORDER BY pos.name";

            Query query = session.createQuery(hql);
            query.setCacheable(true);
            List<CposGroup> list = query.list();

            trancaction.commit();

            return list;
        } catch (HibernateException he) {
            if (trancaction != null) {
                trancaction.rollback();
            }
            throw he;
        }
    }

    public static List<CposGroup> getCampusCposGroup(Integer campusId, Integer programTypeId) throws HibernateException {
        Transaction trancaction = null;
        try {
            trancaction = session.beginTransaction();

            String hql = "SELECT cposg FROM CposGroup AS cposg "
                    + "JOIN cposg.campusProgramOfStudy AS cpos "
                    + "JOIN cpos.programOfStudy AS pos "
                    //                    + "JOIN cpos.shift AS s "
                    + "JOIN cpos.campus AS ca "
                    + "JOIN pos.program AS prog "
                    + "JOIN prog.programType AS pt "
                    + "WHERE ca.campusId = " + campusId + " "
                    + "AND pt.programTypeId = " + programTypeId + " "
                    //                    + "AND s.shiftId = " + shiftId + " "
                    + "ORDER BY pos.name";

            Query query = session.createQuery(hql);
            query.setCacheable(true);
            List<CposGroup> list = query.list();

            trancaction.commit();

            return list;
        } catch (HibernateException he) {
            if (trancaction != null) {
                trancaction.rollback();
            }
            throw he;
        }
    }

    public static List<Discipline> getFacultyDiscipline(Integer facultyId) throws HibernateException {
        Transaction trancaction = null;
        try {
            trancaction = session.beginTransaction();

            String hql = "SELECT di FROM Discipline AS di "
                    + "LEFT JOIN di.department AS de "
                    + "LEFT JOIN de.faculty AS fa "
                    + "WHERE fa.facultyId = " + facultyId + " "
                    + "ORDER BY di.name";

            Query query = session.createQuery(hql);
            query.setCacheable(true);
            List<Discipline> list = query.list();

            trancaction.commit();

            return list;
        } catch (HibernateException he) {
            if (trancaction != null) {
                trancaction.rollback();
            }
            throw he;
        }
    }

    public static List<ProgramOfStudy> getFacultyProgramOfStudy(Integer facultyId, Integer programTypeId) throws HibernateException {
        Transaction trancaction = null;
        try {
            trancaction = session.beginTransaction();

            String hql = "SELECT pos FROM ProgramOfStudy AS pos "
                    + "JOIN pos.program AS prog "
                    + "JOIN prog.programType AS pt "
                    + "JOIN pos.discipline AS di "
                    + "JOIN di.department AS de "
                    + "JOIN de.faculty AS fa "
                    + "WHERE fa.facultyId = " + facultyId + " "
                    + "AND pt.programTypeId = " + programTypeId + " "
                    + "ORDER BY pos.name";

            Query query = session.createQuery(hql);
            query.setCacheable(true);
            List<ProgramOfStudy> list = query.list();

            trancaction.commit();

            return list;
        } catch (HibernateException he) {
            if (trancaction != null) {
                trancaction.rollback();
            }
            throw he;
        }
    }

    public static List<ProgramOfStudy> getProgramOfStudiesByType(Integer programTypeId) throws HibernateException {
        Transaction trancaction = null;
        try {
            trancaction = session.beginTransaction();

            String hql = "SELECT pos FROM ProgramOfStudy AS pos "
                    + "LEFT JOIN pos.program AS prog "
                    + "LEFT JOIN prog.programType AS pt "
                    + "WHERE pt.programTypeId = " + programTypeId + " "
                    + "ORDER BY pos.discipline.name";

            Query query = session.createQuery(hql);
            query.setCacheable(true);
            List<ProgramOfStudy> list = query.list();

            trancaction.commit();

            return list;
        } catch (HibernateException he) {
            if (trancaction != null) {
                trancaction.rollback();
            }
            throw he;
        }
    }

    public static List<CampusProgramOfStudy> getCampusProgramOfStudiesByType(Integer campusId, Integer programTypeId) throws HibernateException {
        Transaction trancaction = null;
        try {
            trancaction = session.beginTransaction();

            String hql = "SELECT cpos FROM CampusProgramOfStudy AS cpos "
                    + "JOIN cpos.programOfStudy AS pos "
                    + "JOIN pos.program AS prog "
                    + "JOIN prog.programType AS pt "
                    + "WHERE pt.programTypeId = " + programTypeId + " "
                    + "AND cpos.campus.campusId = " + campusId + " "
                    + "ORDER BY pos.name";

            Query query = session.createQuery(hql);
            query.setCacheable(true);
            List<CampusProgramOfStudy> list = query.list();

            trancaction.commit();

            return list;
        } catch (HibernateException he) {
            if (trancaction != null) {
                trancaction.rollback();
            }
            throw he;
        }
    }

    public static List<ProgramOfStudy> getDisciplineProgramOfStudyByType(Integer disciplineId, Integer programTypeId) throws HibernateException {
        Transaction trancaction = null;
        try {
            trancaction = session.beginTransaction();

            String hql = "SELECT pos FROM ProgramOfStudy AS pos "
                    + "JOIN pos.program as prog "
                    + "JOIN prog.programType AS pt "
                    + "JOIN pos.discipline AS dis "
                    + "WHERE pt.programTypeId = " + programTypeId + " "
                    + "AND dis.disciplineId = " + disciplineId + " "
                    + "ORDER BY dis.name";

            Query query = session.createQuery(hql);
            query.setCacheable(true);
            List<ProgramOfStudy> list = query.list();

            trancaction.commit();

            return list;
        } catch (HibernateException he) {
            if (trancaction != null) {
                trancaction.rollback();
            }
            throw he;
        }
    }

//    public static List<Admission> getAdmissions(Integer campusId, Integer admissionSessionId) throws HibernateException {
//        Transaction trancaction = null;
//        try {
//            trancaction = session.beginTransaction();
//
//            String hql = "SELECT ad FROM Admission AS ad "
//                    + "JOIN ad.admissionSession AS ase "
//                    + "JOIN ad.campus AS c "
//                    + "WHERE c.campusId = " + campusId + " "
//                    + "AND ase.admissionSessionId = " + admissionSessionId;
//
//            Query query = session.createQuery(hql);
//            query.setCacheable(true);
//            List<Admission> list = query.list();
//
//            trancaction.commit();
//
//            return list;
//        } catch (HibernateException he) {
//            if (trancaction != null) {
//                trancaction.rollback();
//            }
//            throw he;
//        }
//    }
    public static AdmissionSession getAdmissionSession(Integer admissionYearId, Integer programTypeId) throws HibernateException {
        Transaction trancaction = null;
        try {
            trancaction = session.beginTransaction();

            String hql = "SELECT ase FROM AdmissionSession AS ase "
                    + "INNER JOIN ase.admissionYear AS ay "
                    //                    + "INNER JOIN ase.shift AS s "
                    + "INNER JOIN ase.programType AS pt "
                    + "WHERE ay.admissionYearId = " + admissionYearId + " "
                    //                    + "AND s.shiftId = " + shiftId + " "
                    + "AND pt.programTypeId = " + programTypeId;

            Query query = session.createQuery(hql);
            query.setCacheable(true);
            List<AdmissionSession> list = query.list();

            trancaction.commit();

            return list.isEmpty() ? null : list.get(0);
        } catch (HibernateException he) {
            if (trancaction != null) {
                trancaction.rollback();
            }
            throw he;
        }
    }

    public static AdmissionList getAdmissionList(Integer admissionSessionId, Integer campusId) throws HibernateException {
        Transaction trancaction = null;
        try {
            trancaction = session.beginTransaction();

            String hql = "SELECT al FROM AdmissionList AS al "
                    + "JOIN al.admissionSession AS ase "
                    + "JOIN al.campus AS c "
                    + "WHERE ase.admissionSessionId = " + admissionSessionId + " "
                    + "AND c.campusId = " + campusId;

            Query query = session.createQuery(hql);
            query.setCacheable(true);
            List<AdmissionList> list = query.list();

            trancaction.commit();

            return list.isEmpty() ? null : list.get(0);
        } catch (HibernateException he) {
            if (trancaction != null) {
                trancaction.rollback();
            }
            throw he;
        }
    }

    public static List<AdmissionList> getAdmissionLists(Integer campusId, Integer programTypeId) throws HibernateException {
        Transaction trancaction = null;
        try {
            trancaction = session.beginTransaction();

            String hql = "SELECT al FROM AdmissionList AS al "
                    + "INNER JOIN al.admissionSession AS ase "
                    + "INNER JOIN al.campus AS c "
                    + "INNER JOIN ase.programType AS pt "
                    + "WHERE c.campusId = " + campusId + " "
                    + "AND pt.programTypeId = " + programTypeId;

            Query query = session.createQuery(hql);
            query.setCacheable(true);
            List<AdmissionList> list = query.list();

            trancaction.commit();

            return list;
        } catch (HibernateException he) {
            if (trancaction != null) {
                trancaction.rollback();
            }
            throw he;
        }
    }
    
     public static List<AdmissionList> getAdmissionLists(Integer campusId, Integer programTypeId, Integer admissionYearId) throws HibernateException {
        Transaction trancaction = null;
        try {
            trancaction = session.beginTransaction();

            String hql = "SELECT al FROM AdmissionList AS al "
                    + "INNER JOIN al.admissionSession AS ase "
                    + "INNER JOIN ase.admissionYear AS ay "
                    + "INNER JOIN al.campus AS c "
                    + "INNER JOIN ase.programType AS pt "
                    + "WHERE c.campusId = " + campusId + " "
                    + " AND pt.programTypeId = " + programTypeId
                    + " AND ay.admissionYearId = " + admissionYearId;

            Query query = session.createQuery(hql);
            query.setCacheable(true);
            List<AdmissionList> list = query.list();

            trancaction.commit();

            return list;
        } catch (HibernateException he) {
            if (trancaction != null) {
                trancaction.rollback();
            }
            throw he;
        }
    }

    public static List<AdmissionList> getMainAdmissionLists(Boolean isMain, Integer programTypeId) throws HibernateException {
        Transaction trancaction = null;
        try {
            trancaction = session.beginTransaction();

            String hql = "SELECT al FROM AdmissionList AS al "
                    + "INNER JOIN al.admissionSession AS ase "
                    + "INNER JOIN al.campus AS c "
                    + "INNER JOIN ase.programType AS pt "
                    + "WHERE c.isMain = " + isMain + " "
                    + "AND pt.programTypeId = " + programTypeId;

            Query query = session.createQuery(hql);
            query.setCacheable(true);
            List<AdmissionList> list = query.list();

            trancaction.commit();

            return list;
        } catch (HibernateException he) {
            if (trancaction != null) {
                trancaction.rollback();
            }
            throw he;
        }
    }

    public static AdmissionListDetails getActiveAdmissionListDetails(Integer seatNo, Integer admissionYearId, Integer programTypeId) throws HibernateException {
        Transaction trancaction = null;
        try {
            trancaction = session.beginTransaction();

            String hql = "SELECT ald FROM AdmissionListDetails AS ald "
                    + "JOIN ald.candidate AS c "
                    + "JOIN ald.admissionList AS al "
                    + "JOIN al.admissionSession AS ase "
                    + "JOIN ase.admissionYear AS ay "
                    + "JOIN ase.programType AS pt "
                    + "WHERE ald.active = 1 "
                    + "AND ay.admissionYearId = " + admissionYearId + " "
                    + "AND pt.programTypeId = " + programTypeId + " "
                    + "AND c.seatNo = " + seatNo;

            Query query = session.createQuery(hql);
            query.setCacheable(true);
            List<AdmissionListDetails> list = query.list();

            trancaction.commit();

            return list.isEmpty() ? null : list.get(0);
        } catch (HibernateException he) {
            if (trancaction != null) {
                trancaction.rollback();
            }
            throw he;
        }
    }

    public static AdmissionListDetails getAdmissionListDetails(Integer candidateId, Integer cposgId, Integer nullId) throws HibernateException {
        Transaction trancaction = null;
        try {
            trancaction = session.beginTransaction();

            String hql = "SELECT ald FROM AdmissionListDetails AS ald "
                    + "INNER JOIN ald.candidate AS c "
                    + "INNER JOIN ald.cposGroup AS cposg "
                    + "INNER JOIN ald.campusCategory AS cc "
                    + "WHERE c.candidateId = " + candidateId + " "
                    + "AND cposg.cposGroupId = " + cposgId;

            Query query = session.createQuery(hql);
            query.setCacheable(true);
            List<AdmissionListDetails> list = query.list();

            trancaction.commit();

            return list.isEmpty() ? null : list.get(0);
        } catch (HibernateException he) {
            if (trancaction != null) {
                trancaction.rollback();
            }
            throw he;
        }
    }

    public static List<AdmissionListDetails> getAdmissionListDetailses(String seatNo, Integer admissionYearId, Integer programTypeId) throws HibernateException {
        Transaction trancaction = null;
        try {
            trancaction = session.beginTransaction();

            String hql = "SELECT ald FROM AdmissionListDetails AS ald "
                    + "JOIN ald.candidate AS c "
                    + "JOIN ald.admissionList AS al "
                    + "JOIN ald.candidateProgramOfStudy AS cnpos "
                    + "JOIN al.admissionSession AS ase "
                    + "JOIN ase.admissionYear AS ay "
                    + "JOIN ase.programType AS pt "
                    + "WHERE ay.admissionYearId = " + admissionYearId + " "
                    + "AND pt.programTypeId = " + programTypeId + " "
                    + "AND c.seatNo = " + seatNo + " "
                    + "ORDER BY cnpos.choiceNo";

            Query query = session.createQuery(hql);
            query.setCacheable(true);
            List<AdmissionListDetails> list = query.list();

            trancaction.commit();

            return list;
        } catch (HibernateException he) {
            if (trancaction != null) {
                trancaction.rollback();
            }
            throw he;
        }
    }

    public static List<DisciplineCategorySeats> getCposGroupSeatsDistribution(Integer cposGroupId, Integer admissionSessionId, Integer programTypeId, Integer shiftId) throws HibernateException {
        Transaction trancaction = null;
        try {
            trancaction = session.beginTransaction();

            String hql = "SELECT dcs FROM DisciplineCategorySeats AS dcs "
                    + "INNER JOIN dcs.cposGroup AS cposg "
                    + "INNER JOIN cposg.campusProgramOfStudy AS cpos "
                    + "INNER JOIN cpos.shift AS s "
                    + "INNER JOIN dcs.admissionSession AS ase "
                    + "INNER JOIN dcs.campusCategory AS cc "
                    + "INNER JOIN cc.programType AS pt "
                    + "WHERE cposg.cposGroupId = " + cposGroupId + " "
                    + "AND ase.admissionSessionId = " + admissionSessionId + " "
                    + "AND pt.programTypeId = " + programTypeId + " "
                    + "AND s.shiftId = " + shiftId + " "
                    + "ORDER BY cc.displayOrder";

            Query query = session.createQuery(hql);
            query.setCacheable(true);
            List<DisciplineCategorySeats> list = query.list();

            trancaction.commit();

            return list;
        } catch (HibernateException he) {
            if (trancaction != null) {
                trancaction.rollback();
            }
            throw he;
        }
    }

//    public static List<DisciplineCategorySeats> getaSingleDisciplineCategorySeats(Integer campusCategoryId, Integer admissionSessionId, Integer cposGroupId) throws HibernateException{
//        Transaction trancaction = null; 
//        try{
//            trancaction = session.beginTransaction();
//            
//            String hql = "SELECT dcs FROM DisciplineCategorySeats AS dcs "
//                    + "JOIN dcs.cposGroup AS cposg "
//                    + "JOIN dcs.admissionSession AS ase "
//                    + "JOIN dcs.campusCategory AS cc "
//                    + "WHERE cposg.cposGroupId = " + cposGroupId + " "
//                    + "AND ase.admissionSessionId = " + admissionSessionId + " "
//                    + "AND cc.campusCategoryId = " + campusCategoryId;
//
//            Query query = session.createQuery(hql);
//            List<DisciplineCategorySeats> list = query.list();
//            
//            trancaction.commit();
//            
//            return list;
//        }catch (HibernateException he) {
//            if (trancaction != null) trancaction.rollback();
//            throw he;
//        }
//    }
    public static DisciplineCategorySeats getDisciplineCategorySeats(Integer campusCategoryId, Integer admissionSessionId, Integer cposGroupId, Integer shiftId) throws HibernateException {
        Transaction trancaction = null;
        try {
            trancaction = session.beginTransaction();

            String hql = "SELECT dcs FROM DisciplineCategorySeats AS dcs "
                    + "INNER JOIN dcs.cposGroup AS cposg "
                    + "INNER JOIN cposg.campusProgramOfStudy AS cpos "
                    + "INNER JOIN cpos.shift AS s "
                    + "INNER JOIN dcs.admissionSession AS ase "
                    + "INNER JOIN dcs.campusCategory AS cc "
                    + "WHERE cposg.cposGroupId = " + cposGroupId + " "
                    + "AND s.shiftId = " + shiftId + " "
                    + "AND ase.admissionSessionId = " + admissionSessionId + " "
                    + "AND cc.campusCategoryId = " + campusCategoryId;

            Query query = session.createQuery(hql);
            query.setCacheable(true);
            List<DisciplineCategorySeats> list = query.list();

            trancaction.commit();

            return list.isEmpty() ? null : list.get(0);
        } catch (HibernateException he) {
            if (trancaction != null) {
                trancaction.rollback();
            }
            throw he;
        }
    }

    public static CampusProgramOfStudy getCpos(Integer campusId, Integer pCode, Integer programTypeId, Integer shiftId) throws HibernateException {
        Transaction trancaction = null;
        try {
            trancaction = session.beginTransaction();

            String hql = "SELECT cpos FROM CampusProgramOfStudy AS cpos "
                    + "JOIN cpos.campus AS c "
                    + "JOIN cpos.shift AS s "
                    + "JOIN cpos.programOfStudy AS prog "
                    + "JOIN prog.program AS pg "
                    + "JOIN pg.programType AS pt "
                    + "WHERE "
                    + "c.campusId=" + campusId + " "
                    + "AND prog.PCode=" + pCode + " "
                    + "AND s.shiftId=" + shiftId + " "
                    + "AND pt.programTypeId=" + programTypeId;

            Query query = session.createQuery(hql);
            query.setCacheable(true);
            List<CampusProgramOfStudy> list = query.list();

            trancaction.commit();

            return list.isEmpty() ? null : list.get(0);
        } catch (HibernateException he) {
            if (trancaction != null) {
                trancaction.rollback();
            }
            throw he;
        }
    }

    public static List<Candidate> getCandidates(Integer cposId, Integer admissionYearId, String categoryCode) throws HibernateException {
        Transaction trancaction = null;
        try {
            trancaction = session.beginTransaction();

            String hql = "SELECT DISTINCT c FROM CandidateProgramOfStudy AS cnpos, AppliedCategory AS apc "
                    + "JOIN cnpos.candidate AS c "
                    + "JOIN apc.candidate AS c2 "
                    + "JOIN cnpos.campusProgramOfStudy AS cpos "
                    + "JOIN c.admissionYear AS ay "
                    + "WHERE cpos.campusProgramOfStudyId=" + cposId + " "
                    + "AND c = c2 "
                    + "AND apc.categoryCode='" + categoryCode + "' "
                    + "AND ay.admissionYearId=" + admissionYearId + " "
                    + "AND c.testScore > 0 "
                    + "ORDER BY cnpos.choiceNo, c.percentage DESC";

            Query query = session.createQuery(hql);
            query.setCacheable(true);
            List<Candidate> list = query.list();

            trancaction.commit();

            return list;
        } catch (HibernateException he) {
            if (trancaction != null) {
                trancaction.rollback();
            }
            throw he;
        }
    }

    public static List<Candidate> getCandidates(Integer admissionYearId) throws HibernateException {
        Transaction trancaction = null;
        try {
            trancaction = session.beginTransaction();

            String hql = "SELECT c FROM Candidate AS c "
                    + "JOIN c.admissionYear AS ay "
                    + "WHERE ay.admissionYearId=" + admissionYearId;

            Query query = session.createQuery(hql);
            query.setCacheable(true);
            List<Candidate> list = query.list();

            trancaction.commit();

            return list;
        } catch (HibernateException he) {
            if (trancaction != null) {
                trancaction.rollback();
            }
            throw he;
        }
    }

    public static CandidateProgramOfStudy getCandidateProgramOfStudy(Integer cposId, Integer candidateId) throws HibernateException {
        Transaction trancaction = null;
        try {
            trancaction = session.beginTransaction();

            String hql = "SELECT cnpos FROM CandidateProgramOfStudy AS cnpos "
                    + "JOIN cnpos.candidate AS c "
                    + "JOIN cnpos.campusProgramOfStudy AS cpos "
                    + "WHERE cpos.campusProgramOfStudyId=" + cposId + " "
                    + "AND c.candidateId=" + candidateId;

            Query query = session.createQuery(hql);
            query.setCacheable(true);
            List<CandidateProgramOfStudy> list = query.list();

            trancaction.commit();

            return list.isEmpty() ? null : list.get(0);
        } catch (HibernateException he) {
            if (trancaction != null) {
                trancaction.rollback();
            }
            throw he;
        }
    }

    public static List<CandidateProgramOfStudy> getCandidateProgramOfStudy(Integer campusId, Integer shiftId, Integer candidateId) throws HibernateException {
        Transaction trancaction = null;
        try {
            trancaction = session.beginTransaction();

            String hql = "SELECT cnpos FROM CandidateProgramOfStudy AS cnpos "
                    + "INNER JOIN cnpos.candidate AS c "
                    + "INNER JOIN cnpos.campusProgramOfStudy AS cpos "
                    + "INNER JOIN cpos.campus AS cam "
                    + "INNER JOIN cpos.shift AS s "
                    + "WHERE cam.campusId = " + campusId + " "
                    + "AND c.candidateId = " + candidateId + " "
                    + "AND s.shiftId = " + shiftId;

            Query query = session.createQuery(hql);
            query.setCacheable(true);
            List<CandidateProgramOfStudy> list = query.list();

            trancaction.commit();

            return list;
        } catch (HibernateException he) {
            if (trancaction != null) {
                trancaction.rollback();
            }
            throw he;
        }
    }

    public static CandidateProgramOfStudy getCandidateProgramOfStudy(Integer candidateId, Integer cposId, String choiceNo) throws HibernateException {
        Transaction trancaction = null;
        try {
            trancaction = session.beginTransaction();

            String hql = "SELECT cnpos FROM CandidateProgramOfStudy AS cnpos "
                    + "INNER JOIN cnpos.candidate AS c "
                    + "INNER JOIN cnpos.campusProgramOfStudy AS cpos "
                    + "WHERE cpos.campusProgramOfStudyId = " + cposId + " "
                    + "AND c.candidateId = " + candidateId + " "
                    + "AND cnpos.choiceNo = " + choiceNo;

            Query query = session.createQuery(hql);
            query.setCacheable(true);
            List<CandidateProgramOfStudy> list = query.list();

            trancaction.commit();

            return list.isEmpty() ? null : list.get(0);
        } catch (HibernateException he) {
            if (trancaction != null) {
                trancaction.rollback();
            }
            throw he;
        }
    }

    public static Category getCategory(Integer code) throws HibernateException {
        Transaction trancaction = null;
        try {
            trancaction = session.beginTransaction();

            String hql = "SELECT c FROM Category AS c "
                    + "WHERE c.code=" + code;

            Query query = session.createQuery(hql);
            List<Category> list = query.list();

            trancaction.commit();

            return list.isEmpty() ? null : list.get(0);
        } catch (HibernateException he) {
            if (trancaction != null) {
                trancaction.rollback();
            }
            throw he;
        }
    }

    public static Jurisdiction getJurisdiction(Integer campusId, Integer districtId) throws HibernateException {
        Transaction trancaction = null;
        try {
            trancaction = session.beginTransaction();

            String hql = "SELECT j FROM Jurisdiction AS j "
                    + "JOIN j.campus AS c "
                    + "JOIN j.district AS d "
                    + "WHERE c.campusId=" + campusId + " "
                    + "AND d.districtId=" + districtId + "";

            Query query = session.createQuery(hql);
            query.setCacheable(true);
            List<Jurisdiction> list = query.list();

            trancaction.commit();

            return list.isEmpty() ? null : list.get(0);
        } catch (HibernateException he) {
            if (trancaction != null) {
                trancaction.rollback();
            }
            throw he;
        }
    }

    public static List<DistrictSeatDistribution> getDistrictSeatDistribution(Integer campusId, Integer admissionYearId, Integer shiftId, byte isJurisdiction) throws HibernateException {
        Transaction trancaction = null;
        try {
            trancaction = session.beginTransaction();

            String hql = "SELECT dsd FROM DistrictSeatDistribution AS dsd "
                    + "JOIN dsd.jurisdiction AS j "
                    + "JOIN dsd.admissionYear AS ay "
                    + "JOIN dsd.shift AS sh "
                    + "JOIN j.campus AS c "
                    + "JOIN j.district AS d "
                    + "WHERE c.campusId=" + campusId + " "
                    + "AND j.isJurisdiction=" + isJurisdiction + " "
                    + "AND ay.admissionYearId=" + admissionYearId + " "
                    + "AND sh.shiftId=" + shiftId + " "
                    + "ORDER BY d.name";

            Query query = session.createQuery(hql);
            query.setCacheable(true);
            List<DistrictSeatDistribution> list = query.list();

            trancaction.commit();

            return list;
        } catch (HibernateException he) {
            if (trancaction != null) {
                trancaction.rollback();
            }
            throw he;
        }
    }

    public static List<DistrictSeatDistribution> getDistrictSeatDistribution(Integer admissionYearId) throws HibernateException {
        Transaction trancaction = null;
        try {
            trancaction = session.beginTransaction();

            String hql = "SELECT dsd FROM DistrictSeatDistribution AS dsd "
                    + "JOIN dsd.jurisdiction AS j "
                    + "JOIN dsd.admissionYear AS ay "
                    + "JOIN dsd.shift AS sh "
                    + "JOIN j.campus AS c "
                    + "JOIN j.district AS d "
                    + "WHERE ay.admissionYearId=" + admissionYearId + " "
                    + "ORDER BY d.name";

            Query query = session.createQuery(hql);
            query.setCacheable(true);
            List<DistrictSeatDistribution> list = query.list();

            trancaction.commit();

            return list;
        } catch (HibernateException he) {
            if (trancaction != null) {
                trancaction.rollback();
            }
            throw he;
        }
    }

    public static DistrictSeatDistribution getSingleDistrictSeatDistribution(Integer jurisdictionId, Integer admissionYearId, Integer shiftId) throws HibernateException {
        Transaction trancaction = null;
        try {
            trancaction = session.beginTransaction();

            String hql = "SELECT dsd FROM DistrictSeatDistribution AS dsd "
                    + "JOIN dsd.jurisdiction AS j "
                    + "JOIN dsd.admissionYear AS ay "
                    + "JOIN dsd.shift AS sh "
                    + "WHERE j.jurisdictionId=" + jurisdictionId + " "
                    + "AND ay.admissionYearId=" + admissionYearId + " "
                    + "AND sh.shiftId=" + shiftId;

            Query query = session.createQuery(hql);
            query.setCacheable(true);
            List<DistrictSeatDistribution> list = query.list();

            trancaction.commit();

            return list.isEmpty() ? null : list.get(0);
        } catch (HibernateException he) {
            if (trancaction != null) {
                trancaction.rollback();
            }
            throw he;
        }
    }

    public static List<Jurisdiction> getJurisdictionArea(Integer campusId, byte isJurisdiction) throws HibernateException {
        Transaction trancaction = null;
        try {
            trancaction = session.beginTransaction();

            String hql = "SELECT j FROM Jurisdiction AS j "
                    + "JOIN j.campus AS c "
                    + "JOIN j.district AS d "
                    + "WHERE c.campusId=" + campusId + " "
                    + "AND j.isJurisdiction=" + isJurisdiction + " "
                    + "ORDER BY d.name";

            Query query = session.createQuery(hql);
            query.setCacheable(true);
            List<Jurisdiction> list = query.list();

            trancaction.commit();

            return list;
        } catch (HibernateException he) {
            if (trancaction != null) {
                trancaction.rollback();
            }
            throw he;
        }
    }

    public static boolean isCategoryInCampus(Integer campusId, Integer shiftId, Integer programTypeId, Integer code) throws HibernateException {
        Transaction trancaction = null;
        try {
            trancaction = session.beginTransaction();

            String hql = "SELECT cc FROM CampusCategory AS cc "
                    + "JOIN cc.shift AS s "
                    + "JOIN cc.campus AS ca "
                    + "JOIN cc.programType AS pt "
                    + "JOIN cc.category AS c "
                    + "WHERE ca.campusId = " + campusId + " "
                    + "AND pt.programTypeId = " + programTypeId + " "
                    + "AND s.shiftId = " + shiftId + " "
                    + "AND c.code = " + code;
            //System.out.println(hql);
            Query query = session.createQuery(hql);
            query.setCacheable(true);
            List<CampusCategory> list = query.list();

            trancaction.commit();

            return !list.isEmpty();
        } catch (HibernateException he) {
            if (trancaction != null) {
                trancaction.rollback();
            }
            throw he;
        }
    }

    public static List<ProgramSubject> getProgramSubject(Integer programId) throws HibernateException {
        Transaction trancaction = null;
        try {
            trancaction = session.beginTransaction();

            String hql = "SELECT ps FROM ProgramSubject AS ps "
                    + "JOIN ps.program AS p "
                    + "WHERE p.programId = " + programId;

            Query query = session.createQuery(hql);
            query.setCacheable(true);
            List<ProgramSubject> list = query.list();

            trancaction.commit();

            return list;
        } catch (HibernateException he) {
            if (trancaction != null) {
                trancaction.rollback();
            }
            throw he;
        }
    }

    public static ProgramSubject getProgramSubject(Integer programId, Integer subjectId) throws HibernateException {
        Transaction trancaction = null;
        try {
            trancaction = session.beginTransaction();

            String hql = "SELECT ps FROM ProgramSubject AS ps "
                    + "JOIN ps.program AS p "
                    + "JOIN ps.subject AS s "
                    + "WHERE p.programId = " + programId + " "
                    + "AND s.subjectId = " + subjectId;

            Query query = session.createQuery(hql);
            query.setCacheable(true);
            List<ProgramSubject> list = query.list();

            trancaction.commit();

            return list.isEmpty() ? null : list.get(0);
        } catch (HibernateException he) {
            if (trancaction != null) {
                trancaction.rollback();
            }
            throw he;
        }
    }

    public static Prerequisite getPrerequisite(Integer programOfStudyId, Integer programSubjectId) throws HibernateException {
        Transaction trancaction = null;
        try {
            trancaction = session.beginTransaction();

            String hql = "SELECT pr FROM Prerequisite AS pr "
                    + "JOIN pr.programOfStudy AS pos "
                    + "JOIN pr.programSubject AS ps "
                    + "WHERE pos.programOfStudyId = " + programOfStudyId + " "
                    + "AND ps.programSubjectId = " + programSubjectId;

            Query query = session.createQuery(hql);
            query.setCacheable(true);
            List<Prerequisite> list = query.list();

            trancaction.commit();

            return list.isEmpty() ? null : list.get(0);
        } catch (HibernateException he) {
            if (trancaction != null) {
                trancaction.rollback();
            }
            throw he;
        }
    }

    public static List<Prerequisite> getPrerequisites(Integer programId) throws HibernateException {
        Transaction trancaction = null;
        try {
            trancaction = session.beginTransaction();

            String hql = "SELECT pr FROM Prerequisite AS pr "
                    + "JOIN pr.programSubject AS ps "
                    + "JOIN ps.program AS p "
                    + "WHERE p.programId = " + programId;

            Query query = session.createQuery(hql);
            query.setCacheable(true);
            List<Prerequisite> list = query.list();

            trancaction.commit();

            return list;
        } catch (HibernateException he) {
            if (trancaction != null) {
                trancaction.rollback();
            }
            throw he;
        }
    }

    public static List<CposGroup> getCandidatesOfCposGroup(Integer admissionYearId, Integer cposgId) throws HibernateException {
        Transaction trancaction = null;
        try {
            trancaction = session.beginTransaction();

            String hql = "SELECT ald FROM AdmissionListDetails AS ald "
                    + "INNER JOIN ald.candidate AS c "
                    + "INNER JOIN c.accounts AS ac "
                    + "INNER JOIN ald.cposGroup AS cposg "
                    + "INNER JOIN ald.admissionList AS al "
                    + "INNER JOIN al.admissionSession AS ass "
                    + "INNER JOIN ass.admissionYear AS ay "
                    + "WHERE cposg.cposGroupId = " + cposgId + " "
                    + "AND ay.admissionYearId = " + admissionYearId + " "
                    + "ORDER BY c.name";

            Query query = session.createQuery(hql);
            query.setCacheable(true);
            List<CposGroup> list = query.list();

            trancaction.commit();

            return list;
        } catch (HibernateException he) {
            if (trancaction != null) {
                trancaction.rollback();
            }
            throw he;
        }
    }

    public static Boolean isAlreadyPaid(Integer admissionYearId, Integer programTypeId, Integer seatNo) throws HibernateException {
        Transaction trancaction = null;
        try {
            trancaction = session.beginTransaction();

            String hql = "SELECT pr FROM PartRegistry AS pr "
                    + "INNER JOIN pr.admissionListDetails AS ald "
                    + "INNER JOIN ald.candidate AS c "
                    + "INNER JOIN c.admissionYear AS ay "
                    + "INNER JOIN c.programType AS pt "
                    + "WHERE c.seatNo = " + seatNo + " "
                    + "AND ay.admissionYearId = " + admissionYearId + " "
                    + "AND pt.programTypeId = " + programTypeId;

            Query query = session.createQuery(hql);
            query.setCacheable(true);
            List<PartRegistry> list = query.list();

            trancaction.commit();

            return !list.isEmpty();
        } catch (HibernateException he) {
            if (trancaction != null) {
                trancaction.rollback();
            }
            throw he;
        }
    }

    public static List<AdmissionListDetails> getAdmissionListDetailses(Integer admissionYearId, Integer programTypeId, Integer partId) throws HibernateException {
        Transaction trancaction = null;
        try {
            trancaction = session.beginTransaction();

            String hql = "SELECT ald FROM AdmissionListDetails AS ald "
                    + "INNER JOIN ald.candidate AS c "
                    + "INNER JOIN ald.admissionList AS al "
                    //                    + "INNER JOIN ald.candidateProgramOfStudy AS cnpos "
                    + "INNER JOIN al.admissionSession AS ase "
                    + "INNER JOIN ase.admissionYear AS ay "
                    + "INNER JOIN ase.programType AS pt "
                    + "WHERE ald IN (SELECT pr.admissionListDetails FROM PartRegistry pr "
                    + "INNER JOIN pr.part as p "
                    + "WHERE p.partId = " + partId + ") "
                    + "AND ay.admissionYearId = " + admissionYearId + " "
                    + "AND pt.programTypeId = " + programTypeId + " "
                    + "ORDER BY c.seatNo";

            Query query = session.createQuery(hql);
            query.setCacheable(true);
            List<AdmissionListDetails> list = query.list();

            trancaction.commit();

            return list;
        } catch (HibernateException he) {
            if (trancaction != null) {
                trancaction.rollback();
            }
            throw he;
        }
    }

    public static List<AdmissionListDetails> getAdmissionListDetailses(Integer admissionYearId, Integer programTypeId, Integer campusId, Integer partId) throws HibernateException {
        Transaction trancaction = null;
        try {
            trancaction = session.beginTransaction();

            String hql = "SELECT ald FROM AdmissionListDetails AS ald "
                    + "INNER JOIN ald.candidate AS c "
                    + "INNER JOIN ald.cposGroup AS cposg "
                    + "INNER JOIN cposg.campusProgramOfStudy AS cpos "
                    + "INNER JOIN cpos.campus AS cam "
                    + "INNER JOIN c.admissionYear AS ay "
                    + "INNER JOIN c.programType AS pt "
                    + "WHERE ald IN (SELECT pr.admissionListDetails FROM PartRegistry pr "
                    + "INNER JOIN pr.part as p "
                    + "WHERE p.partId = " + partId + ") "
                    + "AND ay.admissionYearId = " + admissionYearId + " "
                    + "AND pt.programTypeId = " + programTypeId + " "
                    + "AND cam.campusId = " + campusId + " "
                    + "ORDER BY c.seatNo";

            Query query = session.createQuery(hql);
            query.setCacheable(true);
            List<AdmissionListDetails> list = query.list();

            trancaction.commit();

            return list;
        } catch (HibernateException he) {
            if (trancaction != null) {
                trancaction.rollback();
            }
            throw he;
        }
    }

    public static List<Part> getParts(Integer programTypeId) throws HibernateException {
        Transaction trancaction = null;
        try {
            trancaction = session.beginTransaction();

            String hql = "SELECT part FROM Part AS part "
                    + "INNER JOIN part.programType AS pt "
                    + "WHERE pt.programTypeId = " + programTypeId;

            Query query = session.createQuery(hql);
            query.setCacheable(true);
            List<Part> list = query.list();

            trancaction.commit();

            return list;
        } catch (HibernateException he) {
            if (trancaction != null) {
                trancaction.rollback();
            }
            throw he;
        }
    }

    public static List<District> getProvinceDistricts(Integer provinceId) throws HibernateException {
        Transaction trancaction = null;
        try {
            trancaction = session.beginTransaction();

            String hql = "SELECT d FROM District AS d "
                    + "INNER JOIN d.division AS di "
                    + "INNER JOIN di.province AS p "
                    + "WHERE p.provinceId = " + provinceId + " "
                    + "ORDER BY d.name";

            Query query = session.createQuery(hql);
            query.setCacheable(true);
            List<District> list = query.list();

            trancaction.commit();

            return list;
        } catch (HibernateException he) {
            if (trancaction != null) {
                trancaction.rollback();
            }
            throw he;
        }
    }

    public static AdmissionList getNextAdmissionList(AdmissionList al) throws HibernateException {
        Transaction trancaction = null;
        AdmissionSession admissionSession = al.getAdmissionSession();
        Campus campus = al.getCampus();
        try {
            trancaction = session.beginTransaction();

            String hql = "SELECT AL FROM AdmissionList AL"
                    + "  WHERE "
                    + "  AL.listNo > " + al.getListNo()
                    + "   AND AL.campus.campusId = " + campus.getCampusId()
                    + "   AND AL.admissionSession.admissionSessionId = " + admissionSession.getAdmissionSessionId()
                    + "    ORDER BY AL.listNo ASC";
            Query query = session.createQuery(hql);
            query.setCacheable(true);
            List<AdmissionList> list = query.list();

            trancaction.commit();
            if (!list.isEmpty()) {
                return list.get(0);
            }
            return null;
        } catch (HibernateException he) {
            if (trancaction != null) {
                trancaction.rollback();
            }
            throw he;
        }
    }
    
    
    public static List<AdmissionListDetails> getActiveAdmissionListDetails(AdmissionList admissionList) throws HibernateException {
        Transaction trancaction = null;
        try {
            trancaction = session.beginTransaction();

            String hql = "SELECT DISTINCT ald FROM AdmissionListDetails AS ald "
                    + " JOIN ald.campusCategory AS cc "
                    + " JOIN ald.admissionList AS al "
                    + " JOIN ald.candidateProgramOfStudy AS cpos "
                    + " WHERE al.admissionListId = " + admissionList.getAdmissionListId()
                    + " AND ald.active = TRUE "
                    + " ORDER BY ald.admissionListDetailsId";
//            System.out.println(hql);
            Query query = session.createQuery(hql);
            query.setCacheable(true);
            List<AdmissionListDetails> list = query.list();

            trancaction.commit();

            return list;
        } catch (HibernateException he) {
            if (trancaction != null) {
                trancaction.rollback();
            }
            throw he;
        }
    }
    
     public static List<AdmissionListDetails> getAdmissionListDetails(Candidate candidate, AdmissionList nextAdmissionList) throws HibernateException {
        Transaction trancaction = null;
        try {
            trancaction = session.beginTransaction();

            String hql = "SELECT ALD FROM AdmissionListDetails ALD "
                    + " WHERE "
                    + " ALD.candidate.candidateId = "+candidate.getCandidateId()
                    +"  AND ALD.admissionList.admissionListId = "+nextAdmissionList.getAdmissionListId();
            Query query = session.createQuery(hql);
//            query.setCacheable(true);
            List<AdmissionListDetails> list = query.list();

            trancaction.commit();

            return list;
        } catch (HibernateException he) {
            if (trancaction != null) {
                trancaction.rollback();
            }
            throw he;
        }
    }
}
