package admission.reports.filters;

import admission.controller.DatabaseManager;
import admission.controller.JDBCDatabaseManager;
import admission.model.AdmissionYear;
import admission.model.ProgramType;
import admission.reports.ReportFilterProvider;
import admission.reports.beans.UserAdmissionFormAuditJRBean;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.hibernate.HibernateException;
import admission.utils.MessageBox;

/**
 *
 * @author Yougeshwar
 */
public class UserAdmissionFormAuditReportFilter extends javax.swing.JPanel implements ReportFilterProvider {

    private String dateFrom = "";
    private String dateTo = "";
    private String programTypeYear = "";
    private String reportOf = "";
    

    public UserAdmissionFormAuditReportFilter() {
        initComponents();

        getAdmissionYear();
        getProgramType();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jLabel13 = new javax.swing.JLabel();
        admissionYearComboBox = new javax.swing.JComboBox();
        jLabel18 = new javax.swing.JLabel();
        programTypeComboBox = new javax.swing.JComboBox();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        dateToFormattedTextField = new javax.swing.JFormattedTextField();
        dateFromFormattedTextField = new javax.swing.JFormattedTextField();
        addedFormRadioButton = new javax.swing.JRadioButton();
        updatedFormRadioButton = new javax.swing.JRadioButton();
        jLabel21 = new javax.swing.JLabel();

        jLabel13.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel13.setText("Admission Year*");

        admissionYearComboBox.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        jLabel18.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel18.setText("Program Type*");

        programTypeComboBox.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        jLabel19.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel19.setText("Date From*");

        jLabel20.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel20.setText("To:");

        try {
            dateToFormattedTextField.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##-##-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        dateToFormattedTextField.setToolTipText("DD-MM-YYYY");

        try {
            dateFromFormattedTextField.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##-##-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        dateFromFormattedTextField.setToolTipText("DD-MM-YYYY");

        buttonGroup1.add(addedFormRadioButton);
        addedFormRadioButton.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        addedFormRadioButton.setSelected(true);
        addedFormRadioButton.setText("Added Form");

        buttonGroup1.add(updatedFormRadioButton);
        updatedFormRadioButton.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        updatedFormRadioButton.setText("Updated Form");

        jLabel21.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel21.setText("Report of*");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel18, javax.swing.GroupLayout.DEFAULT_SIZE, 92, Short.MAX_VALUE)
                            .addComponent(jLabel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, 104, Short.MAX_VALUE))
                    .addComponent(jLabel21, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(addedFormRadioButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(updatedFormRadioButton))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(dateFromFormattedTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel20)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(dateToFormattedTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(programTypeComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(admissionYearComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(48, Short.MAX_VALUE))
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jLabel13, jLabel18, jLabel19});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(admissionYearComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(programTypeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19)
                    .addComponent(jLabel20)
                    .addComponent(dateToFormattedTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dateFromFormattedTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addedFormRadioButton)
                    .addComponent(updatedFormRadioButton)
                    .addComponent(jLabel21))
                .addContainerGap(34, Short.MAX_VALUE))
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {admissionYearComboBox, dateFromFormattedTextField, dateToFormattedTextField, programTypeComboBox});

    }// </editor-fold>//GEN-END:initComponents

    @Override
    public JRBeanCollectionDataSource getJRDataSource() {
        JRBeanCollectionDataSource source = null;
        
        AdmissionYear ay = (AdmissionYear) admissionYearComboBox.getSelectedItem();
        ProgramType pt = (ProgramType) programTypeComboBox.getSelectedItem();
        dateFrom = dateFromFormattedTextField.getText();
        dateTo = dateToFormattedTextField.getText();
        
        if (ay == null || pt == null || dateFrom.trim().length() == 4 || dateTo.trim().length() == 4) {
            return source;
        }
        
        programTypeYear = pt.getName() + " - " + ay.getYear();
        
        long milliDateFrom = admission.utils.Utility.dateToMillis(dateFrom);
        long milliDateTo = admission.utils.Utility.dateToMillis(dateTo);

        String modelAction = "AND LOG.MODEL_ACTION = 'ADD' ";
        reportOf = "List of Added Form";
        if(updatedFormRadioButton.isSelected()) {
            modelAction = "AND LOG.MODEL_ACTION = 'UPDATE' ";
            reportOf = "List of Updated Form";
        }
        List<UserAdmissionFormAuditJRBean> list = new ArrayList<>();
        try {
            String sql = "SELECT U.ID, U.FIRST_NAME, COUNT(LOG.ID) AS TOTAL_ENTRY "
                    + "FROM YOG_LOG AS LOG "
                    + "INNER JOIN YOG_USER AS U ON U.ID = LOG.USER_ID "
                    + "INNER JOIN CANDIDATE AS C ON C.CANDIDATE_ID = LOG.MODEL_ID "
                    + "WHERE LOG.MODEL_NAME = 'Candidate' "
                    + modelAction
                    + "AND C.ADMISSION_YEAR_ID = " + ay.getAdmissionYearId() + " "
                    + "AND C.PROGRAM_TYPE_ID = " + pt.getProgramTypeId() + " "
//                    + "AND LOG.LOG_DATE >= " + milliDateFrom + " AND LOG.LOG_DATE <= " + milliDateTo + " "
                    + "AND LOG.LOG_DATE BETWEEN (UNIX_TIMESTAMP(STR_TO_DATE('" + dateFrom + "', '%d-%m-%Y')) * 1000 - 43200000) AND (UNIX_TIMESTAMP(STR_TO_DATE('" + dateTo + "', '%d-%m-%Y')) * 1000 - 43200000) "
                    + "GROUP BY C.ADMISSION_YEAR_ID, C.PROGRAM_TYPE_ID, U.ID "
                    + "ORDER BY U.FIRST_NAME";
            
            List<Map<String, Object>> resultSet = JDBCDatabaseManager.getResultSet(sql);
            
            for (Map<String, Object> map : resultSet) {
                UserAdmissionFormAuditJRBean bean = new UserAdmissionFormAuditJRBean();
                bean.setUserName((String) map.get("FIRST_NAME"));
                bean.setTotalFormEntry(((Long) map.get("TOTAL_ENTRY")).intValue());
                
                list.add(bean);
            }
            
            source = new JRBeanCollectionDataSource(list);

        } catch (HibernateException | SQLException e) {
            MessageBox.error(this, e);
            return null;
        }

        return source;
    }

    @Override
    public Map fillReportParameter(Map map) {
        map.put("DATE_FROM", dateFrom);
        map.put("DATE_TO", dateTo);
        map.put("PROGRAM_TYPE_YEAR", programTypeYear);
        map.put("REPORT_OF", reportOf);
        
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

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JRadioButton addedFormRadioButton;
    private javax.swing.JComboBox admissionYearComboBox;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JFormattedTextField dateFromFormattedTextField;
    private javax.swing.JFormattedTextField dateToFormattedTextField;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JComboBox programTypeComboBox;
    private javax.swing.JRadioButton updatedFormRadioButton;
    // End of variables declaration//GEN-END:variables
}
