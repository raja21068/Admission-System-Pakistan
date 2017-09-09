package admission.reports.filters;

import admission.controller.DatabaseManager;
import admission.controller.JDBCDatabaseManager;
import admission.enums.MessageEnum;
import admission.model.AdmissionList;
import admission.model.AdmissionSession;
import admission.model.AdmissionYear;
import admission.model.Campus;
import admission.model.ProgramType;
import admission.model.view.SelectionListView;
import admission.reports.ReportFilterProvider;
import admission.reports.beans.MasterSelectionListDetailJRBean;
import admission.reports.beans.MasterSelectionListJRBean;
import admission.utils.IConstant;
import admission.utils.ListNumberToWord;
import java.util.List;
import java.util.Map;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import admission.utils.MessageBox;
import admission.utils.ProgramTitleHandler;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Yougeshwar
 */
public class BachelorSelectionListFilter extends javax.swing.JPanel implements ReportFilterProvider {

    public BachelorSelectionListFilter() {
        initComponents();

        getAdmissionYear();
        getCampus();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel25 = new javax.swing.JLabel();
        campusComboBox = new javax.swing.JComboBox();
        jLabel13 = new javax.swing.JLabel();
        admissionYearComboBox = new javax.swing.JComboBox();
        jLabel26 = new javax.swing.JLabel();
        admissionListComboBox = new javax.swing.JComboBox();
        showSignPADCheckBox = new javax.swing.JCheckBox();
        jLabel27 = new javax.swing.JLabel();
        showSignOtherCheckBox = new javax.swing.JCheckBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        lowerNotificationTextArea = new javax.swing.JTextArea();
        orderBySeatNoCheckBox = new javax.swing.JCheckBox();

        jLabel25.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel25.setText("Campus:");

        campusComboBox.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        campusComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                campusComboBoxActionPerformed(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel13.setText("Admission Year:");

        admissionYearComboBox.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        admissionYearComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                admissionYearComboBoxActionPerformed(evt);
            }
        });

        jLabel26.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel26.setText("Admission List:");

        admissionListComboBox.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        showSignPADCheckBox.setText("Programmer & Director");
        showSignPADCheckBox.setMargin(new java.awt.Insets(2, -2, 2, 2));

        jLabel27.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel27.setText("Show Sign");

        showSignOtherCheckBox.setText("Others");
        showSignOtherCheckBox.setMargin(new java.awt.Insets(2, -2, 2, 2));

        lowerNotificationTextArea.setColumns(20);
        lowerNotificationTextArea.setRows(5);
        jScrollPane1.setViewportView(lowerNotificationTextArea);

        orderBySeatNoCheckBox.setText("Order By Seat#");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel25, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel13, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(showSignPADCheckBox)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(showSignOtherCheckBox))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(admissionListComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(orderBySeatNoCheckBox))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(admissionYearComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(campusComboBox, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane1))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jLabel13))
                    .addComponent(admissionYearComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jLabel25))
                    .addComponent(campusComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(admissionListComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel26)
                    .addComponent(orderBySeatNoCheckBox))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(showSignPADCheckBox, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(showSignOtherCheckBox, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jLabel27, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {admissionListComboBox, admissionYearComboBox, campusComboBox});

    }// </editor-fold>//GEN-END:initComponents

    private void campusComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_campusComboBoxActionPerformed
        getAdmissionList();
    }//GEN-LAST:event_campusComboBoxActionPerformed

    private void admissionYearComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_admissionYearComboBoxActionPerformed
        getAdmissionList();
    }//GEN-LAST:event_admissionYearComboBoxActionPerformed

    private int year;
    private String listNo;

    @Override
    public JRBeanCollectionDataSource getJRDataSource() {
        JRBeanCollectionDataSource source = null;

        AdmissionYear ay = (AdmissionYear) admissionYearComboBox.getSelectedItem();
        ProgramType pt = DatabaseManager.getSingleRecord(ProgramType.class, "isBachelor = true");
        Campus campus = (Campus) campusComboBox.getSelectedItem();
        AdmissionList al = (AdmissionList) admissionListComboBox.getSelectedItem();
        if (ay == null || pt == null || campus == null || al == null) {
            MessageBox.info(this, MessageEnum.MSG_16);
            return source;
        }

        this.year = ay.getYear();
        this.listNo = ListNumberToWord.convert(al.getListNo());

        List<SelectionListView> dataList = DatabaseManager.getData(SelectionListView.class, "admissionListId = " + al.getAdmissionListId(), "campus, shift DESC, discipline, catDisplayOrder, "+ (orderBySeatNoCheckBox.isSelected()? " seatNo ASC" : " percentage DESC" ));

        List<MasterSelectionListJRBean> bachelorBeanList = new ArrayList<>();

        Map<String, List<MasterSelectionListDetailJRBean>> map = new LinkedHashMap<>();
        for (SelectionListView slv : dataList) {
            String s = slv.getCampus() + "<>" + slv.getShift() + "<>" + slv.getDiscipline() + "<>" + slv.getCampusLocation() + "<>" + slv.getProgram();
            List<MasterSelectionListDetailJRBean> msldList = map.get(s);
            if (msldList == null) {
                msldList = new ArrayList<>();
                map.put(s, msldList);
            }

            try {
                String sql = "SELECT CD.TOTAL_MARKS, CD.MARKS_OBTAINED, CD.PASSING_YEAR, P.NAME AS GROUP_NAME, CD.DETAIL_OF "
                        + "FROM CREDENTIAL_DETAILS CD "
                        + "INNER JOIN PROGRAM P ON P.PROGRAM_ID = CD.PROGRAM_ID "
                        + "WHERE CANDIDATE_ID = " + slv.getCandidateId() + " "
                        + "ORDER BY DETAIL_OF";

                List<Map<String, Object>> result = JDBCDatabaseManager.getResultSet(sql);
                int mtm = (int) result.get(0).get("TOTAL_MARKS");
                float mom = (float) result.get(0).get("MARKS_OBTAINED");
                int myr = (int) result.get(0).get("PASSING_YEAR");
                int itm = (int) result.get(1).get("TOTAL_MARKS");
                float iom = (float) result.get(1).get("MARKS_OBTAINED");
                int iyr = (int) result.get(1).get("PASSING_YEAR");
                String grdGroup = (String) result.get(1).get("GROUP_NAME");

                MasterSelectionListDetailJRBean bean = new MasterSelectionListDetailJRBean();
                bean.setSeatNo(slv.getSeatNo());
                bean.setName(slv.getName());
                bean.setFathersName(slv.getFathersName());
                bean.setDistrict(slv.getDistrict());
                bean.setArea(slv.getArea().getCode());
                bean.setChoiceNo(slv.getChoiceNo());
                bean.setDeductionMarks(slv.getDeductionMarks());
                bean.setTestScore(slv.getTestScore());
                bean.setFinalPer(slv.getPercentage());
                bean.setMatricObtained((int)mom);
                bean.setMatricYear(myr);
                bean.setInterObtained((int)iom);
                bean.setInterYear(iyr);
                
                bean.setMatricPer(mom / mtm * 100.0F * IConstant.MATRIC_PER);
                bean.setInterPer((iom-slv.getDeductionMarks()) / itm * 100.0F * IConstant.INTER_PER);
                bean.setTestPer(slv.getTestScore() / IConstant.TOTAL_MARKS * 100.0F * IConstant.TEST_PER);

                bean.setGrdGroup(grdGroup);
                bean.setCategory(slv.getCategory());

                msldList.add(bean);
            } catch (SQLException ex) {
                Logger.getLogger(BachelorSelectionListFilter.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        for (Map.Entry<String, List<MasterSelectionListDetailJRBean>> entry : map.entrySet()) {
            String s = entry.getKey();
            List<MasterSelectionListDetailJRBean> list = entry.getValue();

            String[] array = s.split("<>");

            MasterSelectionListJRBean bean = new MasterSelectionListJRBean();
            bean.setCampus(array[0]);
            bean.setShift(array[1]);
            bean.setDiscipline(ProgramTitleHandler.handleBachelor(array[4], array[2], array[1]));
            bean.setCampusLocation(array[3]);
            bean.setCandidatesList(new JRBeanCollectionDataSource(list));
            
            bachelorBeanList.add(bean);
        }
        source = new JRBeanCollectionDataSource(bachelorBeanList);
        
        return source;
    }

    @Override
    public Map fillReportParameter(Map map) {
        map.put("ADMISSION_YEAR", year);
        map.put("LIST_NO", listNo);
        map.put("SHOW_SIGN", showSignPADCheckBox.isSelected());
        map.put("OTHER_SIGN", showSignOtherCheckBox.isSelected());
        map.put("NOTIFICATION", lowerNotificationTextArea.getText());
        return map;
    }

    private void getAdmissionYear() {
        this.admissionYearComboBox.removeAllItems();

        List<AdmissionYear> list = DatabaseManager.getData(AdmissionYear.class.getName(), "year DESC");
        for (AdmissionYear list1 : list) {
            this.admissionYearComboBox.addItem(list1);
        }
    }

    private void getCampus() {
        campusComboBox.removeAllItems();

        List<Campus> list = DatabaseManager.getData(Campus.class, "displayOrder");
        for (Campus campus : list) {
            campusComboBox.addItem(campus);
        }
    }

    private void getAdmissionList() {
        admissionListComboBox.removeAllItems();

        AdmissionYear ay = (AdmissionYear) admissionYearComboBox.getSelectedItem();
        ProgramType pt = DatabaseManager.getSingleRecord(ProgramType.class, "isBachelor = true");
        Campus campus = (Campus) campusComboBox.getSelectedItem();
        if (ay == null || pt == null || campus == null) {
            return;
        }

        AdmissionSession as = DatabaseManager.getSingleRecord(AdmissionSession.class, "admissionYear.admissionYearId = " + ay.getAdmissionYearId() + " AND programType.programTypeId = " + pt.getProgramTypeId());
        if (as == null) {
            MessageBox.error(this, "Admission session not found");
            return;
        }

        List<AdmissionList> list = DatabaseManager.getData(AdmissionList.class, "admissionSession.admissionSessionId = " + as.getAdmissionSessionId() + " AND campus.campusId = " + campus.getCampusId(), "listNo");
        for (AdmissionList al : list) {
            admissionListComboBox.addItem(al);
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox admissionListComboBox;
    private javax.swing.JComboBox admissionYearComboBox;
    private javax.swing.JComboBox campusComboBox;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea lowerNotificationTextArea;
    private javax.swing.JCheckBox orderBySeatNoCheckBox;
    private javax.swing.JCheckBox showSignOtherCheckBox;
    private javax.swing.JCheckBox showSignPADCheckBox;
    // End of variables declaration//GEN-END:variables
}
