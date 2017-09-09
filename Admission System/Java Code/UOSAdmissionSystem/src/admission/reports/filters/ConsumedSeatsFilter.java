package admission.reports.filters;

import admission.controller.DatabaseManager;
import admission.enums.MessageEnum;
import admission.model.AdmissionList;
import admission.model.AdmissionSession;
import admission.model.AdmissionYear;
import admission.model.Campus;
import admission.model.CposGroup;
import admission.model.ProgramType;
import admission.reports.ReportFilterProvider;
import java.util.List;
import java.util.Map;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import admission.utils.MessageBox;
import javax.swing.DefaultComboBoxModel;

/**
 *
 * @author Yougeshwar
 */
public class ConsumedSeatsFilter extends javax.swing.JPanel implements ReportFilterProvider {

    public ConsumedSeatsFilter() {
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
        jLabel28 = new javax.swing.JLabel();
        admissionSessionComboBox = new javax.swing.JComboBox();

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

        jLabel28.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel28.setText("Adm. Session");

        admissionSessionComboBox.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        admissionSessionComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                admissionSessionComboBoxActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel13)
                                .addGap(10, 10, 10)
                                .addComponent(admissionYearComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel26, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(admissionListComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel25, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel28, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(admissionSessionComboBox, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(campusComboBox, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
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
                        .addComponent(jLabel28))
                    .addComponent(admissionSessionComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(campusComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel25))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(admissionListComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel26))
                .addGap(21, 21, 21))
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {admissionListComboBox, admissionYearComboBox, campusComboBox});

    }// </editor-fold>//GEN-END:initComponents

    private void campusComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_campusComboBoxActionPerformed
        getAdmissionList();
    }//GEN-LAST:event_campusComboBoxActionPerformed

    private void admissionYearComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_admissionYearComboBoxActionPerformed
        setAdmissionSession();
        getAdmissionList();
    }//GEN-LAST:event_admissionYearComboBoxActionPerformed

    private void admissionSessionComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_admissionSessionComboBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_admissionSessionComboBoxActionPerformed

    @Override
    public JRBeanCollectionDataSource getJRDataSource() {
        JRBeanCollectionDataSource source = null;

        AdmissionYear ay = (AdmissionYear) admissionYearComboBox.getSelectedItem();
        ProgramType pt = ((AdmissionSession)admissionSessionComboBox.getSelectedItem()).getProgramType();
        Campus campus = (Campus) campusComboBox.getSelectedItem();
        AdmissionList al = (AdmissionList) admissionListComboBox.getSelectedItem();
        if (ay == null || pt == null || campus == null || al == null) {
            MessageBox.info(this, MessageEnum.MSG_16);
            return source;
        }

        try{
            List<CposGroup> campusCposGroup = DatabaseManager.getCampusCposGroup(campus.getCampusId(), pt.getProgramTypeId());
            
        }catch(Exception ex){
            MessageBox.error(this, ex);
            ex.printStackTrace(System.err);
        }
        
        source = new JRBeanCollectionDataSource(null);
        
        return source;
    }

    @Override
    public Map fillReportParameter(Map map) {
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
        admissionListComboBox.setModel(new DefaultComboBoxModel(list.toArray()));
//        for (AdmissionList al : list) {
//            admissionListComboBox.addItem(al);
//        }
    }
    
    private void setAdmissionSession(){
        AdmissionYear ay = (AdmissionYear) admissionYearComboBox.getSelectedItem();
        List<AdmissionSession> data = DatabaseManager.getData(AdmissionSession.class,"admissionYear.admissionYearId = "+ay.getAdmissionYearId() ,"admissionYear.admissionYearId");
        admissionSessionComboBox.setModel(new DefaultComboBoxModel(data.toArray()));
    }
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox admissionListComboBox;
    private javax.swing.JComboBox admissionSessionComboBox;
    private javax.swing.JComboBox admissionYearComboBox;
    private javax.swing.JComboBox campusComboBox;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel28;
    // End of variables declaration//GEN-END:variables
}
