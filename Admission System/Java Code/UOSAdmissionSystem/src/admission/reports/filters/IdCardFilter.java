package admission.reports.filters;

import admission.controller.DatabaseManager;
import admission.controller.JDBCDatabaseManager;
import admission.model.AdmissionYear;
import admission.model.Campus;
import admission.model.CposGroup;
import admission.model.Part;
import admission.model.ProgramType;
import admission.model.Shift;
import admission.reports.ReportFilterProvider;
import admission.reports.beans.CandidateDataBean;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.MissingResourceException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.text.JTextComponent;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.hibernate.HibernateException;

/**
 *
 * @author Yougeshwar
 */
public class IdCardFilter extends javax.swing.JPanel implements ReportFilterProvider {

    private String campusName = "";
    BufferedImage logoImage;
    BufferedImage signImage;
    BufferedImage transImage;

    public IdCardFilter() {
        initComponents();

        getAdmissionYear();
        getProgramType();
        getPart();
        getCampus();
        getShift();

        allCandidatesCheckBoxActionPerformed(null);

        cardsOfComboBox.getEditor().getEditorComponent().addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                cardsOfComboBoxKeyPressed(e);
            }
        });

        try {
            logoImage = ImageIO.read(getClass().getResource("/images/new_logo2.png"));
            signImage = ImageIO.read(getClass().getResource("/images/DA_sign.png"));
            transImage = ImageIO.read(getClass().getResource("/images/new_logo2_trans.png"));
        } catch (IOException ex) {
            Logger.getLogger(IdCardFilter.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel25 = new javax.swing.JLabel();
        partComboBox = new javax.swing.JComboBox();
        jLabel13 = new javax.swing.JLabel();
        admissionYearComboBox = new javax.swing.JComboBox();
        jLabel18 = new javax.swing.JLabel();
        programTypeComboBox = new javax.swing.JComboBox();
        allCandidatesCheckBox = new javax.swing.JCheckBox();
        pressEnterLabel = new javax.swing.JLabel();
        campusLabel = new javax.swing.JLabel();
        programLabel = new javax.swing.JLabel();
        campusComboBox = new javax.swing.JComboBox();
        shiftLabel = new javax.swing.JLabel();
        shiftComboBox = new javax.swing.JComboBox();
        cposgComboBox = new javax.swing.JComboBox();
        cardsOfComboBox = new javax.swing.JComboBox();
        cardsOfLabel = new javax.swing.JLabel();

        jLabel25.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel25.setText("Part:");

        partComboBox.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        jLabel13.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel13.setText("Admission Year:");

        admissionYearComboBox.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        jLabel18.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel18.setText("Program Type:");

        programTypeComboBox.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        programTypeComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                programTypeComboBoxActionPerformed(evt);
            }
        });

        allCandidatesCheckBox.setText("All Candidates");
        allCandidatesCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                allCandidatesCheckBoxActionPerformed(evt);
            }
        });

        pressEnterLabel.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        pressEnterLabel.setText("Press enter");
        pressEnterLabel.setEnabled(false);

        campusLabel.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        campusLabel.setText("Campus:");

        programLabel.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        programLabel.setText("Program:");

        campusComboBox.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        campusComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                campusComboBoxActionPerformed(evt);
            }
        });

        shiftLabel.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        shiftLabel.setText("Shift:");

        shiftComboBox.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        shiftComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                shiftComboBoxActionPerformed(evt);
            }
        });

        cposgComboBox.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        cardsOfComboBox.setEditable(true);
        cardsOfComboBox.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        cardsOfComboBox.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cardsOfComboBoxKeyPressed(evt);
            }
        });

        cardsOfLabel.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        cardsOfLabel.setText("Cards of:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel13)
                        .addGap(10, 10, 10)
                        .addComponent(admissionYearComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(programTypeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(partComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(cardsOfLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(cardsOfComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(4, 4, 4)
                        .addComponent(pressEnterLabel))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(campusLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(campusComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(shiftLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(shiftComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(programLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(cposgComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(241, 241, 241)
                        .addComponent(allCandidatesCheckBox)))
                .addContainerGap(39, Short.MAX_VALUE))
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
                .addGap(11, 11, 11)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(cardsOfLabel))
                    .addComponent(cardsOfComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(pressEnterLabel)))
                .addGap(11, 11, 11)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(campusLabel))
                    .addComponent(campusComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(shiftLabel))
                    .addComponent(shiftComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(programLabel))
                    .addComponent(cposgComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(7, 7, 7)
                .addComponent(allCandidatesCheckBox)
                .addContainerGap(32, Short.MAX_VALUE))
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {admissionYearComboBox, campusComboBox, cardsOfComboBox, cposgComboBox, partComboBox, programTypeComboBox, shiftComboBox});

    }// </editor-fold>//GEN-END:initComponents

    private void programTypeComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_programTypeComboBoxActionPerformed
        // TODO add your handling code here:
        getPart();
    }//GEN-LAST:event_programTypeComboBoxActionPerformed

    private void allCandidatesCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_allCandidatesCheckBoxActionPerformed
        // TODO add your handling code here:
        setIDCardComponentEnabled(allCandidatesCheckBox.isSelected());
    }//GEN-LAST:event_allCandidatesCheckBoxActionPerformed

    private void campusComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_campusComboBoxActionPerformed
        // TODO add your handling code here:
        getCampusProgramOfStudyGroup();
    }//GEN-LAST:event_campusComboBoxActionPerformed

    private void shiftComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_shiftComboBoxActionPerformed
        // TODO add your handling code here:
        getCampusProgramOfStudyGroup();
    }//GEN-LAST:event_shiftComboBoxActionPerformed

    private void cardsOfComboBoxKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cardsOfComboBoxKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_DELETE) {
            int index = cardsOfComboBox.getSelectedIndex();
            if(index < 0) return;
            cardsOfComboBox.removeItemAt(index);
        } else if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER) {
            JTextComponent tc = (JTextComponent) cardsOfComboBox.getEditor().getEditorComponent();
            if (tc.getText().isEmpty()) {
                return;
            }
            cardsOfComboBox.addItem(tc.getText());
            tc.setText("");
        }
    }//GEN-LAST:event_cardsOfComboBoxKeyPressed

    @Override
    public JRBeanCollectionDataSource getJRDataSource() {
        JRBeanCollectionDataSource source = null;

        AdmissionYear ay = (AdmissionYear) admissionYearComboBox.getSelectedItem();
        ProgramType pt = (ProgramType) programTypeComboBox.getSelectedItem();
        Part part = (Part) partComboBox.getSelectedItem();
        CposGroup cposg = (CposGroup) cposgComboBox.getSelectedItem();
        if (ay == null || pt == null || part == null || (cposg == null && allCandidatesCheckBox.isSelected())) {
            return source;
        }

        List<CandidateDataBean> list = new ArrayList<>();
        try {
            if (!allCandidatesCheckBox.isSelected()) {
                int count = cardsOfComboBox.getItemCount();
                for (int i = 0; i < count; i++) {
                    String seatNo = cardsOfComboBox.getItemAt(i).toString();

                    HashMap<String, Object> candidate = JDBCDatabaseManager.getCandidate2(ay.getAdmissionYearId(), pt.getProgramTypeId(), part.getPartId(), seatNo, true);
                    if (candidate == null) {
                        continue;
                    }

                    candidate.put("part", part.getName());
                    if ((boolean) candidate.get("is_main")) {
                        campusName = candidate.get("campus") + ", " + candidate.get("campus_location");
                    } else {
                        campusName = candidate.get("campus_location") + " CAMPUS";
                    }

                    if (candidate.get("image_path") == null || ((String) candidate.get("image_path")).isEmpty()) {
                        continue;
                    }

                    CandidateDataBean bean = new CandidateDataBean(candidate, ay.getYear(), (pt.getIsBachelor()));
                    list.add(bean);
                }
            } else {
                List<Map<String, Object>> candidates = JDBCDatabaseManager.getCandidate3(ay.getAdmissionYearId(), pt.getProgramTypeId(), part.getPartId(), cposg.getCposGroupId(), true);
                for (Map<String, Object> candidate : candidates) {
                    candidate.put("part", part.getName());

                    if ((boolean) candidate.get("is_main")) {
                        campusName = candidate.get("campus") + ", " + candidate.get("campus_location");
                    } else {
                        campusName = candidate.get("campus_location") + " CAMPUS";
                    }

                    if (candidate.get("image_path") == null || ((String) candidate.get("image_path")).isEmpty()) {
                        continue;
                    }

                    CandidateDataBean bean = new CandidateDataBean(candidate, ay.getYear(), (pt.getIsBachelor()));
                    list.add(bean);
                }
            }

            source = new JRBeanCollectionDataSource(list);
//            cardsOfComboBox.removeAllItems();
        } catch (SQLException | HibernateException | MissingResourceException e) {
        }

        return source;
    }

    @Override
    public Map fillReportParameter(Map map) {
        map.put("logo", logoImage);
        map.put("da_sign", signImage);
        map.put("trans_logo", transImage);
        map.put("campusName", campusName);
        
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

    private void getCampus() {
        this.campusComboBox.removeAllItems();

        List<Campus> list = DatabaseManager.getData(Campus.class.getName(), "displayOrder");
        for (Campus list1 : list) {
            campusComboBox.addItem(list1);
        }
    }

    private void getShift() {
        this.shiftComboBox.removeAllItems();

        List<Shift> list = DatabaseManager.getData(Shift.class.getName(), "isMorning DESC");
        for (Shift list1 : list) {
            shiftComboBox.addItem(list1);
        }
    }

    private void getPart() {
        partComboBox.removeAllItems();

        ProgramType pt = (ProgramType) programTypeComboBox.getSelectedItem();
        if (pt == null) {
            return;
        }

        List<Part> list = DatabaseManager.getParts(pt.getProgramTypeId());
        for (Part list1 : list) {
            partComboBox.addItem(list1);
        }
    }

    private void getCampusProgramOfStudyGroup() {
        cposgComboBox.removeAllItems();

        Campus campus = (Campus) this.campusComboBox.getSelectedItem();
        Shift shift = (Shift) this.shiftComboBox.getSelectedItem();
        ProgramType pt = (ProgramType) this.programTypeComboBox.getSelectedItem();
        if (campus == null || shift == null || pt == null) {
            return;
        }

        List<CposGroup> list = DatabaseManager.getCampusCposGroup(campus.getCampusId(), shift.getShiftId(), pt.getProgramTypeId());

        for (CposGroup list1 : list) {
            cposgComboBox.addItem(list1);
        }
    }

    private void setIDCardComponentEnabled(boolean b) {
        this.campusLabel.setVisible(allCandidatesCheckBox.isSelected());
        this.shiftLabel.setVisible(allCandidatesCheckBox.isSelected());
        this.programLabel.setVisible(allCandidatesCheckBox.isSelected());
//        this.seatNoLabel.setVisible(!allCandidatesCheckBox.isSelected());
        this.campusComboBox.setVisible(allCandidatesCheckBox.isSelected());
        this.shiftComboBox.setVisible(allCandidatesCheckBox.isSelected());
        this.cposgComboBox.setVisible(allCandidatesCheckBox.isSelected());
        this.cardsOfLabel.setVisible(!allCandidatesCheckBox.isSelected());
        this.cardsOfComboBox.setVisible(!allCandidatesCheckBox.isSelected());
        this.pressEnterLabel.setVisible(!allCandidatesCheckBox.isSelected());
//        this.seatNoTextField1.setVisible(!allCandidatesCheckBox.isSelected());
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox admissionYearComboBox;
    private javax.swing.JCheckBox allCandidatesCheckBox;
    private javax.swing.JComboBox campusComboBox;
    private javax.swing.JLabel campusLabel;
    private javax.swing.JComboBox cardsOfComboBox;
    private javax.swing.JLabel cardsOfLabel;
    private javax.swing.JComboBox cposgComboBox;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JComboBox partComboBox;
    private javax.swing.JLabel pressEnterLabel;
    private javax.swing.JLabel programLabel;
    private javax.swing.JComboBox programTypeComboBox;
    private javax.swing.JComboBox shiftComboBox;
    private javax.swing.JLabel shiftLabel;
    // End of variables declaration//GEN-END:variables
}
