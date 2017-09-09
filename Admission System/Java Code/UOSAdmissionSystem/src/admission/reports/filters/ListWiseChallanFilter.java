package admission.reports.filters;

import admission.controller.DatabaseManager;
import admission.controller.JDBCDatabaseManager;
import admission.enums.CategoryEnum;
import admission.enums.MessageEnum;
import admission.enums.ProgramTypeEnum;
import admission.helpers.FeeHelper;
import admission.model.AdmissionList;
import admission.model.AdmissionSession;
import admission.model.AdmissionYear;
import admission.model.Campus;
import admission.model.Part;
import admission.model.view.CandidateAdmissionDetailView;
import admission.reports.ReportFilterProvider;
import admission.reports.beans.CandidateChallanDetailJRBean;
import admission.utils.DateUtility;
import java.util.List;
import java.util.Map;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import admission.utils.MessageBox;
import admission.utils.NumberFormatter;
import admission.utils.ProgramTitleHandler;
import admission.view.beans.FeeBean;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Yougeshwar
 */
public class ListWiseChallanFilter extends javax.swing.JPanel implements ReportFilterProvider {

    public ListWiseChallanFilter() {
        initComponents();

        getAdmissionYear();
        getCampus();

        categoryComboBox.addItem(CategoryEnum.GM_DUR_QUOTA);
        categoryComboBox.addItem(CategoryEnum.SFM_QUOTA);
        categoryComboBox.addItem(CategoryEnum.SFE_QUOTA);
        
        admission.utils.Utility.comboBoxScroll(categoryComboBox);
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
        jLabel5 = new javax.swing.JLabel();
        admissionSessionComboBox = new javax.swing.JComboBox();
        jLabel7 = new javax.swing.JLabel();
        partComboBox = new javax.swing.JComboBox();
        jLabel8 = new javax.swing.JLabel();
        categoryComboBox = new javax.swing.JComboBox();

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

        jLabel5.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel5.setText("Admission Session:");

        admissionSessionComboBox.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        admissionSessionComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                admissionSessionComboBoxActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel7.setText("Part:");

        partComboBox.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        jLabel8.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel8.setText("Category:");

        categoryComboBox.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel25, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel26, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(admissionListComboBox, 0, 224, Short.MAX_VALUE)
                    .addComponent(admissionYearComboBox, 0, 224, Short.MAX_VALUE)
                    .addComponent(campusComboBox, 0, 224, Short.MAX_VALUE)
                    .addComponent(admissionSessionComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(partComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(categoryComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(53, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(11, 11, 11)
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
                    .addComponent(admissionSessionComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(admissionListComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel26))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(partComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(categoryComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addContainerGap(31, Short.MAX_VALUE))
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {admissionListComboBox, admissionYearComboBox, campusComboBox});

    }// </editor-fold>//GEN-END:initComponents

    private void campusComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_campusComboBoxActionPerformed
        getAdmissionList();
    }//GEN-LAST:event_campusComboBoxActionPerformed

    private void admissionYearComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_admissionYearComboBoxActionPerformed
        getAdmissionSession();
    }//GEN-LAST:event_admissionYearComboBoxActionPerformed

    private void admissionSessionComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_admissionSessionComboBoxActionPerformed
        getPart();
        getAdmissionList();
    }//GEN-LAST:event_admissionSessionComboBoxActionPerformed

    @Override
    public JRBeanCollectionDataSource getJRDataSource() {
        JRBeanCollectionDataSource source = null;

        AdmissionYear ay = (AdmissionYear) admissionYearComboBox.getSelectedItem();
        AdmissionList al = (AdmissionList) admissionListComboBox.getSelectedItem();
        Part part = (Part) partComboBox.getSelectedItem();
        if (ay == null || al == null || part == null) {
            MessageBox.info(this, MessageEnum.MSG_16);
            return source;
        }

        CategoryEnum category = (CategoryEnum) categoryComboBox.getSelectedItem();
        String s;
        if (!category.equals(CategoryEnum.SFM_QUOTA) && !category.equals(CategoryEnum.SFE_QUOTA)) {
            s = "category <> " + CategoryEnum.SFM_QUOTA.ordinal() + " AND category <> " + CategoryEnum.SFE_QUOTA.ordinal();
        } else {
            s = "category = " + category.ordinal();
        }

        List<CandidateAdmissionDetailView> dataList = DatabaseManager.getData(CandidateAdmissionDetailView.class, "admissionListId = " + al.getAdmissionListId() + " AND " + s, "discipline, seatNo");

        List<CandidateChallanDetailJRBean> list = new ArrayList<>();

        for (CandidateAdmissionDetailView cadv : dataList) {
            List<FeeBean> feeList = FeeHelper.getFee(cadv, part.getPartId(), part.getPartNo());
            int totalAmount = getTotalAmount(feeList);
            if(totalAmount == 0) continue;
            
            List<Map<String, Object>> resultSet;
            try {
                resultSet = JDBCDatabaseManager.getResultSet("SELECT * FROM YOG_TEMP_CHALLAN WHERE ADMISSION_LIST_DETAILS_ID = " + cadv.getId());
                if (resultSet.isEmpty()) {
                    continue;
                }
            } catch (SQLException ex) {
                Logger.getLogger(ListWiseChallanFilter.class.getName()).log(Level.SEVERE, null, ex);
                continue;
            }

            CandidateChallanDetailJRBean bean = new CandidateChallanDetailJRBean();
            bean.setName(cadv.getName());
            bean.setFathersName(cadv.getFathersName());
            bean.setYear(ay.getYear());
            bean.setSeatNo(cadv.getSeatNo());

            String discipline;
            if (cadv.getProgramType().equals(ProgramTypeEnum.BACHELOR)) {
                discipline = ProgramTitleHandler.handleBachelor(cadv.getProgram(), cadv.getDiscipline(), cadv.getShift().getTitle());
            } else {
                discipline = ProgramTitleHandler.handleMaster(cadv.getProgram(), cadv.getDiscipline(), cadv.getShift().getTitle());
            }
            bean.setDiscipline(discipline);

            bean.setFeeList(getTotalAmountS(feeList));
            bean.setTotalAmount(NumberFormatter.format(getTotalAmount(feeList)));
            bean.setCode((String) resultSet.get(0).get("CODE"));
            bean.setValidDate(DateUtility.toDate((Long) resultSet.get(0).get("VALID_TO")));

            list.add(bean);
        }

        source = new JRBeanCollectionDataSource(list);

        return source;
    }

    @Override
    public Map fillReportParameter(Map map) {
        return map;
    }

    private int getTotalAmount(List<FeeBean> list) {
        int i = 0;
        for (FeeBean feeBean : list) {
            i += feeBean.getAmount();
        }
        return i;
    }

    private String getTotalAmountS(List<FeeBean> list) {
        String s = "";
        for (FeeBean feeBean : list) {
            s += NumberFormatter.format(feeBean.getAmount()) + "/=\n";
        }
        return s;
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

    private void getPart() {
        partComboBox.removeAllItems();
        AdmissionSession as = (AdmissionSession) this.admissionSessionComboBox.getSelectedItem();
        if (as == null) {
            return;
        }

        List<Part> list = DatabaseManager.getData(Part.class, "programType.programTypeId = " + as.getProgramType().getProgramTypeId(), "partNo");
        for (Part part : list) {
            partComboBox.addItem(part);
        }
    }
    
    private void getAdmissionSession() {
        this.admissionSessionComboBox.removeAllItems();

        AdmissionYear ay = (AdmissionYear) this.admissionYearComboBox.getSelectedItem();
        if (ay == null) {
            return;
        }

        List<AdmissionSession> list = DatabaseManager.getData(AdmissionSession.class, "admissionYear.admissionYearId = " + ay.getAdmissionYearId(), "admissionSessionId");

        for (AdmissionSession as : list) {
            this.admissionSessionComboBox.addItem(as);
        }
    }

    private void getAdmissionList() {
        admissionListComboBox.removeAllItems();
        Campus campus = (Campus) campusComboBox.getSelectedItem();
        AdmissionSession as = (AdmissionSession) this.admissionSessionComboBox.getSelectedItem();
        if (as == null || campus == null) {
            return;
        }

        List<AdmissionList> list = DatabaseManager.getData(AdmissionList.class, "admissionSession.admissionSessionId = " + as.getAdmissionSessionId() + " AND campus.campusId = " + campus.getCampusId(), "listNo DESC");
        for (AdmissionList al : list) {
            admissionListComboBox.addItem(al);
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox admissionListComboBox;
    private javax.swing.JComboBox admissionSessionComboBox;
    private javax.swing.JComboBox admissionYearComboBox;
    private javax.swing.JComboBox campusComboBox;
    private javax.swing.JComboBox categoryComboBox;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JComboBox partComboBox;
    // End of variables declaration//GEN-END:variables
}
