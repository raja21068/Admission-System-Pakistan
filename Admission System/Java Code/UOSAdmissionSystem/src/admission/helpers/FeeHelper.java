package admission.helpers;

import admission.controller.JDBCDatabaseManager;
import admission.enums.AmountTypeEnum;
import admission.enums.CategoryEnum;
import admission.enums.FeeCategoryTypeEnum;
import admission.enums.FeeOfEnum;
import admission.enums.OrientedEnum;
import admission.model.Discipline;
import admission.model.Faculty;
import admission.model.Part;
import admission.model.Program;
import admission.model.view.CandidateAdmissionDetailView;
import admission.utils.MessageBox;
import admission.view.beans.FeeBean;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Yougeshwar
 */
public class FeeHelper {

    /**
     * @param cad Candidate Admission Detail View
     * @param partId Part
     * @param partNo Part No
     * @return List of Fee bean
     */
    public static List<FeeBean> getFee(CandidateAdmissionDetailView cad, int partId, int partNo) {
        List<FeeBean> list = new ArrayList<>();
        if (cad.getCategory().equals(CategoryEnum.FR_QUOTA)) {
            return null;
        }

        if (cad.getCategory().equals(CategoryEnum.SFE_QUOTA)) {
            String sql = "SELECT FEE AS FEE_AMOUNT FROM YOG_ADDITIONAL_FEE WHERE ADMISSION_YEAR_ID = " + cad.getAdmissionYearId() + " AND FEE_OF = " + FeeOfEnum.ADMISSION_FEE_SFE.ordinal();
            int feeAmount = getValue(sql, "FEE_AMOUNT");
            FeeBean elgibiltyCrftFeeBean = new FeeBean("Enrollment Fee", feeAmount);
            list.add(elgibiltyCrftFeeBean);
            
            feeAmount = executeFeeAmountSteps(cad, partId, FeeCategoryTypeEnum.SFE, "");
            AmountTypeEnum amountType = executeAmountTypeSteps(cad, partId);
            if (amountType.equals(AmountTypeEnum.TOTAL)) {
                feeAmount = feeAmount / cad.getSemester().getSemester();
            }
            FeeBean sfeFeeBean = new FeeBean("Self Finance Evening Fee", feeAmount);
            list.add(sfeFeeBean);

            return list;
        } else {
            int feeAmount = executeFeeAmountSteps(cad, partId, FeeCategoryTypeEnum.MERIT, "");
            AmountTypeEnum amountType = executeAmountTypeSteps(cad, partId);
            if (amountType.equals(AmountTypeEnum.TOTAL)) {
                feeAmount = feeAmount / cad.getDuration().getYear();
            }
            if (cad.getCategory().equals(CategoryEnum.SUE_QUOTA)) { // 11 Bus fare, 14 Exam fee
                // Exclude 11 and 14 remaining amount divite be 2 and add 11 and 14 cat amount
                int septAmt = executeFeeAmountSteps(cad, partId, FeeCategoryTypeEnum.MERIT, "11, 14");
                int a = feeAmount - septAmt;
                feeAmount = a / 2 + septAmt;
            }
            FeeBean meritFeeBean = new FeeBean("Merit Fee", feeAmount);
            list.add(meritFeeBean);

            if (cad.getCategory().equals(CategoryEnum.SFM_QUOTA)) {
                feeAmount = executeFeeAmountSteps(cad, partId, FeeCategoryTypeEnum.SFM, "");
                FeeBean sfmFeeBean = new FeeBean("Self Finance Fee", feeAmount);
                list.add(sfmFeeBean);
            }
        }
        if (partNo == 1) {
            if (cad.isBoardJurisdication()) { // Out of juriadiction
                String sql = "SELECT FEE AS FEE_AMOUNT FROM YOG_ADDITIONAL_FEE WHERE ADMISSION_YEAR_ID = " + cad.getAdmissionYearId() + " AND FEE_OF = " + FeeOfEnum.ENROLLMENT_FEE_OTHER_BOARD_UNI.ordinal();
                int feeAmount = getValue(sql, "FEE_AMOUNT");
                FeeBean elgibiltyCrftFeeBean = new FeeBean("Enrollment Fee", feeAmount);
                list.add(elgibiltyCrftFeeBean);

                sql = "SELECT FEE AS FEE_AMOUNT FROM YOG_ADDITIONAL_FEE WHERE ADMISSION_YEAR_ID = " + cad.getAdmissionYearId() + " AND FEE_OF = " + FeeOfEnum.ELIGIBILITY_CRFT_OUT_OF_JURISDICTION.ordinal();
                feeAmount = getValue(sql, "FEE_AMOUNT");
                FeeBean selfFinanceFeeBean = new FeeBean("Eligibilty Crft Fee", feeAmount);
                list.add(selfFinanceFeeBean);
            }
            if (cad.isNonSindh()) {
                String sql = "SELECT FEE AS FEE_AMOUNT FROM YOG_ADDITIONAL_FEE WHERE ADMISSION_YEAR_ID = " + cad.getAdmissionYearId() + " AND FEE_OF = " + FeeOfEnum.OTHER_PROVINCES.ordinal();
                int feeAmount = getValue(sql, "FEE_AMOUNT");
                FeeBean selfFinanceFeeBean = new FeeBean("Other Province", feeAmount);
                list.add(selfFinanceFeeBean);
            }
        }
        return list;
    }

    private static int executeFeeAmountSteps(CandidateAdmissionDetailView cad, int partId, FeeCategoryTypeEnum cat, String catCode) {
        String sql = getFeeAmountSQL(cad.getProgramType().ordinal(), cad.getShift().ordinal(), cat.ordinal(),
                new String[]{Program.class.getName(), Discipline.class.getName(), Part.class.getName()},
                new int[]{cad.getProgramId(), cad.getDisciplineId(), partId}, catCode);
        int feeAmount = getValue(sql, "FEE_AMOUNT");
        if (feeAmount <= 0) {
            sql = getFeeAmountSQL(cad.getProgramType().ordinal(), cad.getShift().ordinal(), cat.ordinal(),
                    new String[]{OrientedEnum.class.getName(), Faculty.class.getName(), Part.class.getName()},
                    new int[]{cad.getOriented().ordinal(), cad.getFacultyId(), partId}, catCode);
            feeAmount = getValue(sql, "FEE_AMOUNT");
            if (feeAmount <= 0) {
                sql = getFeeAmountSQL(cad.getProgramType().ordinal(), cad.getShift().ordinal(), cat.ordinal(),
                        new String[]{Program.class.getName(), Discipline.class.getName()},
                        new int[]{cad.getProgramId(), cad.getDisciplineId()}, catCode);
                feeAmount = getValue(sql, "FEE_AMOUNT");
                if (feeAmount <= 0) {
                    sql = getFeeAmountSQL(cad.getProgramType().ordinal(), cad.getShift().ordinal(), cat.ordinal(),
                            new String[]{Program.class.getName(), Part.class.getName()},
                            new int[]{cad.getProgramId(), partId}, catCode);
                    feeAmount = getValue(sql, "FEE_AMOUNT");
                }
            }
        }
        return feeAmount;
    }

    private static AmountTypeEnum executeAmountTypeSteps(CandidateAdmissionDetailView cad, int partId) {
        String sql = getAmountTypeSQL(cad.getProgramType().ordinal(), cad.getShift().ordinal(),
                new String[]{Program.class.getName(), Discipline.class.getName(), Part.class.getName()},
                new int[]{cad.getProgramId(), cad.getDisciplineId(), partId});
        int amountType = getValue(sql, "AMOUNT_TYPE");
        if (amountType <= 0) {
            sql = getAmountTypeSQL(cad.getProgramType().ordinal(), cad.getShift().ordinal(),
                    new String[]{OrientedEnum.class.getName(), Faculty.class.getName(), Part.class.getName()},
                    new int[]{cad.getOriented().ordinal(), cad.getFacultyId(), partId});
            amountType = getValue(sql, "AMOUNT_TYPE");
            if (amountType <= 0) {
                sql = getAmountTypeSQL(cad.getProgramType().ordinal(), cad.getShift().ordinal(),
                        new String[]{Program.class.getName(), Discipline.class.getName()},
                        new int[]{cad.getProgramId(), cad.getDisciplineId()});
                amountType = getValue(sql, "AMOUNT_TYPE");
                if (amountType <= 0) {
                    sql = getAmountTypeSQL(cad.getProgramType().ordinal(), cad.getShift().ordinal(),
                            new String[]{Program.class.getName(), Part.class.getName()},
                            new int[]{cad.getProgramId(), partId});
                    amountType = getValue(sql, "AMOUNT_TYPE");
                }
            }
        }
        
        return AmountTypeEnum.values()[amountType];
    }

    private static int getValue(String sql, String param) {
        try {
            return JDBCDatabaseManager.getIntValue(sql, param);
        } catch (SQLException ex) {
            Logger.getLogger(FeeHelper.class.getName()).log(Level.SEVERE, null, ex);
            MessageBox.error(null, ex);
        }
        return 0;
    }

    private static String getAmountTypeSQL(int pt, int shift, String[] models, int[] ids) {
        String sql = "SELECT FM.AMOUNT_TYPE "
                + "FROM YOG_FEE_MODEL FM "
                + "WHERE FM.PROGRAM_TYPE IN (" + pt + ", 2) "
                + "AND FM.SHIFT = " + shift + " ";

        for (int i = 0; i < models.length; i++) {
            String s = models[i];
            int id = ids[i];
            sql += "AND FM.ID IN (SELECT FEE_MODEL_ID FROM YOG_MODEL_MAPPING WHERE MODEL_NAME = '" + s + "' AND MODEL_ID = " + id + ") ";
        }
        return sql;
    }

    private static String getFeeAmountSQL(int pt, int shift, int cat, String[] models, int[] ids, String catCode) {
        String sql = "SELECT IFNULL(SUM(FEE.AMOUNT), 0) AS FEE_AMOUNT "
                + "FROM YOG_FEE FEE "
                + "INNER JOIN YOG_FEE_CATEGORY FC ON FEE.FEE_CATEGORY_ID = FC.ID "
                + "INNER JOIN YOG_FEE_MODEL FM ON FEE.FEE_MODEL_ID = FM.ID "
                + "WHERE FC.PROGRAM_TYPE IN (" + pt + ", 2) "
                + "AND FM.PROGRAM_TYPE IN (" + pt + ", 2) "
                + "AND FC.SHIFT = " + shift + " "
                + "AND FM.SHIFT = " + shift + " "
                + "AND FC.CATEGORY_TYPE = " + cat + " ";
        if (!catCode.isEmpty()) {
            sql += "AND FC.CODE IN (" + catCode + ")";
        }
        for (int i = 0; i < models.length; i++) {
            String s = models[i];
            int id = ids[i];
            sql += "AND FM.ID IN (SELECT FEE_MODEL_ID FROM YOG_MODEL_MAPPING WHERE MODEL_NAME = '" + s + "' AND MODEL_ID = " + id + ") ";
        }
        return sql;
    }
}
