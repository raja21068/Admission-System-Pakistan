package admission.reports.filters;

import admission.controller.DatabaseManager;
import admission.controller.JDBCDatabaseManager;
import admission.enums.MessageEnum;
import admission.model.AdmissionYear;
import admission.model.Part;
import admission.model.ProgramType;
import admission.reports.ReportFilterProvider;
import admission.reports.beans.BonafideCertificateJRBean;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.MissingResourceException;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.hibernate.HibernateException;
import admission.utils.MessageBox;

/**
 *
 * @author Yougeshwar
 */
public class BonafideCertificateFilter extends javax.swing.JPanel implements ReportFilterProvider {

    public BonafideCertificateFilter() {
        initComponents();

        getAdmissionYear();
        getProgramType();
        getPart();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        allCandidateCheckBox = new javax.swing.JCheckBox();
        jLabel25 = new javax.swing.JLabel();
        partComboBox = new javax.swing.JComboBox();
        jLabel13 = new javax.swing.JLabel();
        admissionYearComboBox = new javax.swing.JComboBox();
        jLabel14 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        programTypeComboBox = new javax.swing.JComboBox();
        seatNoTextField = new javax.swing.JTextField();

        allCandidateCheckBox.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        allCandidateCheckBox.setText("All Candidates");
        allCandidateCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                allCandidateCheckBoxActionPerformed(evt);
            }
        });

        jLabel25.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel25.setText("Part:");

        partComboBox.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        jLabel13.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel13.setText("Admission Year:");

        admissionYearComboBox.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        jLabel14.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel14.setText("Seat No.:");

        jLabel18.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel18.setText("Program Type:");

        programTypeComboBox.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        programTypeComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                programTypeComboBoxActionPerformed(evt);
            }
        });

        seatNoTextField.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        seatNoTextField.setToolTipText("");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel13)
                        .addGap(10, 10, 10)
                        .addComponent(admissionYearComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(programTypeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(partComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(seatNoTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(34, 34, 34)
                        .addComponent(allCandidateCheckBox)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                .addGap(11, 11, 11)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jLabel18))
                    .addComponent(programTypeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jLabel25))
                    .addComponent(partComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(9, 9, 9)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(jLabel14))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(seatNoTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(allCandidateCheckBox))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {admissionYearComboBox, partComboBox, programTypeComboBox, seatNoTextField});

    }// </editor-fold>//GEN-END:initComponents

    private void allCandidateCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_allCandidateCheckBoxActionPerformed
        // TODO add your handling code here:
        seatNoTextField.setEnabled(!allCandidateCheckBox.isSelected());
    }//GEN-LAST:event_allCandidateCheckBoxActionPerformed

    private void programTypeComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_programTypeComboBoxActionPerformed
        // TODO add your handling code here:
        getPart();
    }//GEN-LAST:event_programTypeComboBoxActionPerformed

    @Override
    public JRBeanCollectionDataSource getJRDataSource() {
        JRBeanCollectionDataSource source = null;
        
        AdmissionYear ay = (AdmissionYear) admissionYearComboBox.getSelectedItem();
        ProgramType pt = (ProgramType) programTypeComboBox.getSelectedItem();
        Part part = (Part) partComboBox.getSelectedItem();
        if (ay == null || pt == null || part == null) {
            MessageBox.info(this, MessageEnum.MSG_16);
            return source;
        }

        String seatNo = seatNoTextField.getText();
        if (seatNo.isEmpty()) {
            return source;
        }

        List<BonafideCertificateJRBean> list = new ArrayList();
        try {
            HashMap<String, String> candidate = JDBCDatabaseManager.getCandidate(ay.getAdmissionYearId(), pt.getProgramTypeId(), part.getPartId(), seatNo, true);
            if(candidate == null) return null;
            
            candidate.put("part", part.getName());
            BonafideCertificateJRBean bean = new BonafideCertificateJRBean(candidate, ay.getYear(), (pt.getIsBachelor()));
            list.add(bean);

            source = new JRBeanCollectionDataSource(list);
        } catch (SQLException | HibernateException | MissingResourceException ex) {
            MessageBox.error(this, ex);
            Logger.getLogger(BonafideCertificateFilter.class.getName()).log(Level.SEVERE, null, ex);
        }
        
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

    private void getProgramType() {
        this.programTypeComboBox.removeAllItems();

        List<ProgramType> list = DatabaseManager.getData(ProgramType.class.getName(), "name");
        for (ProgramType list1 : list) {
            this.programTypeComboBox.addItem(list1);
        }
    }

    private void getPart() {
        partComboBox.removeAllItems();

        ProgramType pt = (ProgramType) programTypeComboBox.getSelectedItem();
        if (pt == null) {
            return;
        }

        List<Part> list = DatabaseManager.getData(Part.class, "programType.programTypeId = " + pt.getProgramTypeId(), "name");
        for (Part list1 : list) {
            partComboBox.addItem(list1);
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox admissionYearComboBox;
    private javax.swing.JCheckBox allCandidateCheckBox;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JComboBox partComboBox;
    private javax.swing.JComboBox programTypeComboBox;
    private javax.swing.JTextField seatNoTextField;
    // End of variables declaration//GEN-END:variables
}
