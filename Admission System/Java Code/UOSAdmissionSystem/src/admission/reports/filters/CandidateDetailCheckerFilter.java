package admission.reports.filters;

import admission.controller.DatabaseManager;
import admission.controller.JDBCDatabaseManager;
import admission.enums.AreaEnum;
import admission.enums.CategoryEnum;
import admission.enums.DetailOfEnum;
import admission.enums.GenderEnum;
import admission.model.AdmissionYear;
import admission.model.ProgramType;
import admission.reports.ReportFilterProvider;
import admission.reports.beans.CandidateDetailCheckerJRBean;
import admission.reports.beans.ChoicesCheckerJRBean;
import admission.reports.beans.CredentialDetailCheckerJRBean;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.text.AbstractDocument;
import javax.swing.text.JTextComponent;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.apache.velocity.util.StringUtils;
import org.hibernate.HibernateException;
import admission.utils.DateUtility;
import admission.utils.MessageBox;

/**
 *
 * @author Yougeshwar
 */
public class CandidateDetailCheckerFilter extends javax.swing.JPanel implements ReportFilterProvider {

    private String programTypeYear = "";

    public CandidateDetailCheckerFilter() {
        initComponents();

        admission.utils.UppercaseDocumentFilter upperDocumentFilter = new admission.utils.UppercaseDocumentFilter();
        ((AbstractDocument) checkerNameTextField.getDocument()).setDocumentFilter(upperDocumentFilter);

        getAdmissionYear();
        getProgramType();

        seatNoRangeComboBox.getEditor().getEditorComponent().addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                seatNoRangeComboBoxKeyPressed(e);
            }
        });
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel13 = new javax.swing.JLabel();
        admissionYearComboBox = new javax.swing.JComboBox();
        jLabel18 = new javax.swing.JLabel();
        programTypeComboBox = new javax.swing.JComboBox();
        jLabel19 = new javax.swing.JLabel();
        pressEnterLabel = new javax.swing.JLabel();
        seatNoRangeComboBox = new javax.swing.JComboBox();
        jLabel20 = new javax.swing.JLabel();
        checkerNameTextField = new javax.swing.JTextField();

        jLabel13.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel13.setText("Admission Year*");

        admissionYearComboBox.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        jLabel18.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel18.setText("Program Type*");

        programTypeComboBox.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        jLabel19.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel19.setText("Seat No.*");

        pressEnterLabel.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        pressEnterLabel.setText("Press enter");
        pressEnterLabel.setEnabled(false);

        seatNoRangeComboBox.setEditable(true);
        seatNoRangeComboBox.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        seatNoRangeComboBox.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                seatNoRangeComboBoxKeyPressed(evt);
            }
        });

        jLabel20.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel20.setText("Checker Name");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(4, 4, 4)
                        .addComponent(admissionYearComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel18, javax.swing.GroupLayout.DEFAULT_SIZE, 104, Short.MAX_VALUE)
                            .addComponent(jLabel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(4, 4, 4)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(seatNoRangeComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(pressEnterLabel))
                            .addComponent(checkerNameTextField)
                            .addComponent(programTypeComboBox, 0, 224, Short.MAX_VALUE))))
                .addContainerGap(35, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(admissionYearComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(programTypeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(checkerNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel20))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(seatNoRangeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pressEnterLabel)
                    .addComponent(jLabel19))
                .addContainerGap(81, Short.MAX_VALUE))
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {admissionYearComboBox, checkerNameTextField, programTypeComboBox, seatNoRangeComboBox});

    }// </editor-fold>//GEN-END:initComponents

    private void seatNoRangeComboBoxKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_seatNoRangeComboBoxKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_DELETE) {
            int index = seatNoRangeComboBox.getSelectedIndex();
            if (index < 0) {
                return;
            }
            seatNoRangeComboBox.removeItemAt(index);
        } else if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER) {
            JTextComponent tc = (JTextComponent) seatNoRangeComboBox.getEditor().getEditorComponent();
            if (tc.getText().isEmpty()) {
                return;
            }
            seatNoRangeComboBox.addItem(tc.getText());
            tc.setText("");
        }
    }//GEN-LAST:event_seatNoRangeComboBoxKeyPressed

    @Override
    public JRBeanCollectionDataSource getJRDataSource() {
        JRBeanCollectionDataSource source = null;

        AdmissionYear ay = (AdmissionYear) admissionYearComboBox.getSelectedItem();
        ProgramType pt = (ProgramType) programTypeComboBox.getSelectedItem();
        int count = seatNoRangeComboBox.getItemCount();
//        String seatNoFrom = seatNoFromTextField.getText();
//        String seatNoTo = seatNoToTextField.getText();

        if (ay == null || pt == null || count <= 0) {
            return source;
        }

        List<CandidateDetailCheckerJRBean> list = new ArrayList<>();
        try {
            String seatNos = "";
            for (int i = 0; i < count; i++) {
                String seatNoRange = seatNoRangeComboBox.getItemAt(i).toString();
                if (seatNoRange.contains("-")) {
                    String[] chunks = seatNoRange.split("-");
                    String seatNoFrom = chunks[0];
                    String seatNoTo = chunks[1];

//                    String hql = "admissionYearId = " + ay.getAdmissionYearId() + " "
//                            + "AND programTypeId = " + pt.getProgramTypeId() + " "
//                            + "AND seatNo BETWEEN " + seatNoFrom + " AND " + seatNoTo;
                    String sql = "SELECT C.CANDIDATE_ID AS ID, C.ADMISSION_YEAR_ID, C.PROGRAM_TYPE_ID, C.SEAT_NO,  "
                            + "	C.GENDER, C.NAME, C.FATHERS_NAME, C.SURNAME, D.NAME AS DISTRICT,  C.AREA, "
                            + "	(SELECT GROUP_CONCAT(CODE) FROM APPLIED_CATEGORY WHERE CANDIDATE_ID = C.CANDIDATE_ID) AS APPLIED_CATEGORIES, "
                            + "	U.FIRST_NAME AS USER, C.OBJECTION_REMARKS, C.REMARKS AS CHALAN_NO, C.CHALAN_FEE, C.CHALAN_DATE, IFNULL(C.YEARS_DEGREE, 0) AS YEARS_DEGREE "
                            + "	FROM CANDIDATE AS C "
                            + "	INNER JOIN DISTRICT AS D ON C.DISTRICT_ID = D.DISTRICT_ID "
                            + "	INNER JOIN YOG_LOG AS LOG ON LOG.MODEL_ID = C.CANDIDATE_ID "
                            + "	INNER JOIN YOG_USER AS U ON U.ID = LOG.USER_ID "
                            + "	WHERE LOG.MODEL_ACTION = 'ADD' AND LOG.MODEL_NAME = 'Candidate'"
                            + " AND ADMISSION_YEAR_ID = " + ay.getAdmissionYearId() + " "
                            + " AND PROGRAM_TYPE_ID = " + pt.getProgramTypeId() + ""
                            + " AND SEAT_NO BETWEEN " + seatNoFrom + " AND " + seatNoTo + ""
                            + " ORDER BY SEAT_NO";
                    List<CandidateDetailCheckerJRBean> candidateList = getCandidateList(sql);

                    list.addAll((candidateList));
                } else {
                    seatNos += seatNoRange + ",";
                }
            }
            if (!seatNos.isEmpty()) {
//                String hql = "admissionYearId = " + ay.getAdmissionYearId() + " "
//                        + "AND programTypeId = " + pt.getProgramTypeId() + " "
//                        + "AND seatNo IN (" + utilities.StringUtils.removeEnd(seatNos, ",") + ")";

                String sql = "SELECT C.CANDIDATE_ID AS ID, C.ADMISSION_YEAR_ID, C.PROGRAM_TYPE_ID, C.SEAT_NO,  "
                        + "	C.GENDER, C.NAME, C.FATHERS_NAME, C.SURNAME, D.NAME AS DISTRICT,  C.AREA, "
                        + "	(SELECT GROUP_CONCAT(CODE) FROM APPLIED_CATEGORY WHERE CANDIDATE_ID = C.CANDIDATE_ID) AS APPLIED_CATEGORIES, "
                        + "	U.FIRST_NAME AS USER, C.OBJECTION_REMARKS, C.REMARKS AS CHALAN_NO, C.CHALAN_FEE, C.CHALAN_DATE, IFNULL(C.YEARS_DEGREE, 0) AS YEARS_DEGREE "
                        + "	FROM CANDIDATE AS C "
                        + "	INNER JOIN DISTRICT AS D ON C.DISTRICT_ID = D.DISTRICT_ID "
                        + "	INNER JOIN YOG_LOG AS LOG ON LOG.MODEL_ID = C.CANDIDATE_ID "
                        + "	INNER JOIN YOG_USER AS U ON U.ID = LOG.USER_ID "
                        + "	WHERE LOG.MODEL_ACTION = 'ADD' AND LOG.MODEL_NAME = 'Candidate'"
                        + " AND ADMISSION_YEAR_ID = " + ay.getAdmissionYearId() + " "
                        + " AND PROGRAM_TYPE_ID = " + pt.getProgramTypeId() + ""
                        + " AND SEAT_NO IN (" + admission.utils.StringUtils.removeEnd(seatNos, ",") + ") "
                        + " ORDER BY SEAT_NO";

                List<CandidateDetailCheckerJRBean> candidateList = getCandidateList(sql);

                list.addAll((candidateList));
            }

            source = new JRBeanCollectionDataSource(list);
            programTypeYear = pt.getName() + " - " + ay.getYear();
//            header = "CHECKING LIST OF CANDIDATES PROVISIONAL APPLY FOR VARIOUS SUBJECTS OF " + pt.getName() + "'S PROGRAMME DURING THE ACADEMIC YEAR " + ay.getYear() + " PROGRAMME ";
//            footer = "SIGNATURE-FEED IN COMPUTER BY / SIGNATURE-RECORD CHECKED FORM / SIGNATURE-RECORD CHECKED IN SUPERVISION OF ___________________ -ASST:DIRECTOR";

        } catch (HibernateException e) {
            MessageBox.error(this, e);
            return null;
        }

        return source;
    }

    @Override
    public Map fillReportParameter(Map map) {
        map.put("PROGRAM_TYPE_YEAR", programTypeYear);
        map.put("CHECKED_BY", checkerNameTextField.getText());

        return map;
    }

    private void getAdmissionYear() {
        this.admissionYearComboBox.removeAllItems();

        List<AdmissionYear> list = DatabaseManager.getData(AdmissionYear.class.getName(), "year DESC");
        for (AdmissionYear list1 : list) {
            this.admissionYearComboBox.addItem(list1);
        }
    }

    private void getProgramType() {
        this.programTypeComboBox.removeAllItems();

        List<ProgramType> list = DatabaseManager.getData(ProgramType.class.getName(), "name");
        for (ProgramType list1 : list) {
            this.programTypeComboBox.addItem(list1);
        }
    }

    private List<CandidateDetailCheckerJRBean> getCandidateList(String hql) {
        List<CandidateDetailCheckerJRBean> list = new ArrayList<>();
        try {
            List<Map<String, Object>> resultSet = JDBCDatabaseManager.getResultSet(hql); //Data(CandidateDetailChecker.class, hql, "seatNo");

            for (Map<String, Object> map : resultSet) {
                CandidateDetailCheckerJRBean bean = new CandidateDetailCheckerJRBean();

                int candidateId = (int) map.get("ID");
                bean.setSeatNo((int) map.get("SEAT_NO"));
                bean.setGender(GenderEnum.values()[(int) map.get("GENDER")].getCode());
                bean.setName((String) map.get("NAME"));
                bean.setFathersName((String) map.get("FATHERS_NAME"));
                bean.setSurname((String) map.get("SURNAME"));
                bean.setDistrict((String) map.get("DISTRICT"));
                bean.setArea(AreaEnum.values()[(int) map.get("AREA")].getCode());
                bean.setUser((String) map.get("USER"));
                bean.setChalanDate(DateUtility.getDateToString((Date) map.get("CHALAN_DATE")));
                bean.setChalanFee((int) map.get("CHALAN_FEE"));
                bean.setChalanNo((String) map.get("CHALAN_NO"));
                bean.setObjectionRemarks((String) map.get("OBJECTION_REMARKS"));
                bean.setYearsDegree((int) (long) map.get("YEARS_DEGREE"));
                
                List<String> categoryList = new ArrayList<>();
                String cats = (String) map.get("APPLIED_CATEGORIES");
                if (cats != null) {
                    String[] split = StringUtils.split(cats, ",");
                    for (String s : split) {
                        String code = CategoryEnum.values()[Integer.parseInt(s)].getCode();
                        categoryList.add(code);
                    }
                }

                bean.setCategoryList(categoryList);

                List<String> optionalSubjects = new ArrayList<>();
                List<CredentialDetailCheckerJRBean> cdcBeanList = new ArrayList<>();
                List<Map<String, Object>> resultSet1 = JDBCDatabaseManager.getResultSet(""
                        + "SELECT CD.CREDENTIAL_DETAILS_ID, CD.TOTAL_MARKS, CD.MARKS_OBTAINED, CD.SEAT_NO, CD.PASSING_YEAR, CD.DETAIL_OF, I.ACRONYM, P.NAME AS PROGRAM_NAME, P.REMARKS "
                        + "FROM CREDENTIAL_DETAILS CD "
                        + "INNER JOIN PROGRAM P ON P.PROGRAM_ID = CD.PROGRAM_ID "
                        + "INNER JOIN ISSUER I ON I.ISSUER_ID = CD.ISSUER_ID WHERE CANDIDATE_ID = " + candidateId);
//                List<CredentialDetails> crdList = DatabaseManager.getData(CredentialDetails.class, "candidate.candidateId = " + cdc.getId(), "detailOf");
                for (Map<String, Object> cd : resultSet1) {
                    CredentialDetailCheckerJRBean cdcBean = new CredentialDetailCheckerJRBean();

                    DetailOfEnum detailOf = DetailOfEnum.values()[(int) cd.get("DETAIL_OF")];
                    cdcBean.setDetailOf(detailOf.getTitle());
                    cdcBean.setoMrks((float) cd.get("MARKS_OBTAINED"));
                    cdcBean.setYear((int) cd.get("PASSING_YEAR"));
                    cdcBean.settMrks((int) cd.get("TOTAL_MARKS"));
                    cdcBean.setSeatNo((String)cd.get("SEAT_NO"));
                    cdcBean.setBd((String) cd.get("ACRONYM"));

                    if (detailOf.equals(DetailOfEnum.GRADUATION)) {
                        cdcBean.setGrp((String) cd.get("PROGRAM_NAME"));

                        String sql = "SELECT DS.CODE FROM OPTIONAL_SUBJECT OS "
                                + "INNER JOIN SUBJECT SB ON SB.SUBJECT_ID = OS.SUBJECT_ID "
                                + "INNER JOIN DISCIPLINE DS ON DS.DISCIPLINE_ID = SB.DISCIPLINE_ID "
                                + "WHERE OS.CREDENTIAL_DETAILS_ID = " + cd.get("CREDENTIAL_DETAILS_ID") + " "
                                + "ORDER BY OS.OPTIONAL_SUBJECT_ID";

                        try {
                            List<Map<String, Object>> resultSet3 = JDBCDatabaseManager.getResultSet(sql);
                            for (Map<String, Object> map3 : resultSet3) {
                                optionalSubjects.add(map3.get("CODE").toString());
                            }
                        } catch (SQLException ex) {
                            Logger.getLogger(CandidateDetailCheckerFilter.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    } else {
                        cdcBean.setGrp((String) cd.get("REMARKS"));
                    }

                    cdcBeanList.add(cdcBean);
                }
                bean.setCredentialDetails(new JRBeanCollectionDataSource(cdcBeanList));
                bean.setOptionalSubject(optionalSubjects);

                List<ChoicesCheckerJRBean> choicesBeanList = new ArrayList<>();
                String sql = "SELECT CNPOS.CANDIDATE_PROGRAM_OF_STUDY_ID AS ID, CNPOS.CANDIDATE_ID, CM.NAME AS CAMPUS, S.NAME AS SHIFT,  "
                        + "	GROUP_CONCAT(POS.CODE order by CNPOS.CHOICE_NO) AS CHOICES,  "
                        + "	GROUP_CONCAT(CNPOS.CHOICE_NO order by CNPOS.CHOICE_NO) AS CHOICES_NO,  "
                        + "	S.IS_MORNING, CM.DISPLAY_ORDER "
                        + "	FROM CANDIDATE_PROGRAM_OF_STUDY AS CNPOS "
                        + "	INNER JOIN CAMPUS_PROGRAM_OF_STUDY AS CPOS ON CNPOS.CAMPUS_PROGRAM_OF_STUDY_ID = CPOS.CAMPUS_PROGRAM_OF_STUDY_ID "
                        + "	INNER JOIN PROGRAM_OF_STUDY AS POS ON CPOS.PROGRAM_OF_STUDY_ID = POS.PROGRAM_OF_STUDY_ID "
                        + "	INNER JOIN CAMPUS AS CM ON CM.CAMPUS_ID = CPOS.CAMPUS_ID "
                        + "	INNER JOIN SHIFT AS S ON S.SHIFT_ID = CPOS.SHIFT_ID "
                        + "	WHERE POS.CODE <> '' AND POS.CODE IS NOT NULL "
                        + "     AND CNPOS.CANDIDATE_ID = " + candidateId
                        + "	GROUP BY CNPOS.CANDIDATE_ID, CM.NAME, S.NAME";
                List<Map<String, Object>> resultSet2 = JDBCDatabaseManager.getResultSet(sql);
//                List<CandidateChoicesChecker> choicesDbList = DatabaseManager.getData(CandidateChoicesChecker.class, "candidateId = " + cdc.getId(), "displayOrder ASC, isMorning DESC");
                for (Map<String, Object> ccc : resultSet2) {
                    ChoicesCheckerJRBean ccBean = new ChoicesCheckerJRBean();

                    ccBean.setCampus((String) ccc.get("CAMPUS"));
                    ccBean.setShift((String) ccc.get("SHIFT"));
                    List<String> choicesList = new ArrayList<>();
                    choicesList.addAll(Arrays.asList(StringUtils.split((String) ccc.get("CHOICES"), ",")));

                    ccBean.setChoicesList(choicesList);
                    choicesBeanList.add(ccBean);
                }

                bean.setChoices(new JRBeanCollectionDataSource(choicesBeanList));

                list.add(bean);
            }

        } catch (SQLException ex) {
            Logger.getLogger(CandidateDetailCheckerFilter.class.getName()).log(Level.SEVERE, null, ex);
            MessageBox.error(this, ex);
        }
        return list;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox admissionYearComboBox;
    private javax.swing.JTextField checkerNameTextField;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel pressEnterLabel;
    private javax.swing.JComboBox programTypeComboBox;
    private javax.swing.JComboBox seatNoRangeComboBox;
    // End of variables declaration//GEN-END:variables
}
