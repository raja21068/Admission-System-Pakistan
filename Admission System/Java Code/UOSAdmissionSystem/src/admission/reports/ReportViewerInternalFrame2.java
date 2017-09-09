package admission.reports;

import admission.reports.beans.CandidateDataBean;
import admission.controller.DatabaseManager;
import admission.controller.JDBCDatabaseManager;
import admission.model.AdmissionList;
import admission.model.AdmissionListDetails;
import admission.model.AdmissionSession;
import admission.model.AdmissionYear;
import admission.model.AppliedCampus;
import admission.model.AppliedCategory;
import admission.model.Campus;
import admission.model.CampusCategory;
import admission.model.Candidate;
import admission.model.CandidateProgramOfStudy;
import admission.model.CategoryType;
import admission.model.CposGroup;
import admission.model.CredentialDetails;
import admission.model.DisciplineCategorySeats;
import admission.model.Faculty;
import admission.model.Part;
import admission.model.ProgramType;
import admission.model.Shift;
import admission.reports.beans.BonafideCertificateJRBean;
import admission.reports.beans.DetailsBean;
import admission.reports.beans.ProgramOfStudyDataBean;
import admission.reports.beans.SeatsBean;
import admission.reports.beans.SeatsDistributionDataBean;
import com.yog.component.slidingpanel.SlidePaneFactory;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.MissingResourceException;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JComboBox;
import javax.swing.text.AbstractDocument;
import javax.swing.text.JTextComponent;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import org.hibernate.HibernateException;
import admission.utils.IConstant;
import admission.utils.DateUtility;

/**
 *
 * @author Raja Kumar & Jay Kumar
 */
public class ReportViewerInternalFrame2 extends javax.swing.JInternalFrame {

    public ReportViewerInternalFrame2() {
        initComponents();

        busyDialog = new BusyIndicator(null);
        reportViewerPanel = new JRViewer300(null);
        jPanel1.add(reportViewerPanel, BorderLayout.CENTER);

        admission.utils.Utility.hideOnEscape(this);

        this.jScrollPane1.setViewportView(new SlidingPanel());

        ((AbstractDocument) this.seatNoTextField.getDocument()).setDocumentFilter(new admission.utils.NumberDocumentFilter());
        admission.utils.Utility.comboBoxScroll(cposgComboBox);

        cardsOfComboBox.getEditor().getEditorComponent().addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                cardsOfComboBoxKeyPressed(e);
            }
        });
    }

    @Override
    public void setVisible(boolean aFlag) {
        super.setVisible(aFlag);
        if (aFlag) {
            this.getCampus();
            this.getAdmissionYear();
            this.getProgramType();
            this.getShift();
            this.allCandidatesCheckBoxActionPerformed(null);
            try {
                this.setMaximum(true);
            } catch (PropertyVetoException ex) {
                Logger.getLogger(ReportViewerInternalFrame2.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    class SlidingPanel extends javax.swing.JPanel {

        SlidingPanel() {
            this.setBackground(Color.white);
            javax.swing.ImageIcon icon = new javax.swing.ImageIcon(getClass().getResource("/com/yog/component/slidingpanel/images/title.png"));
            SlidePaneFactory factory = SlidePaneFactory.getInstance();
            factory.add(idCardPanel, "Students ID Card", icon.getImage(), false);
            factory.add(bonafideCertificatePanel, "Bonafide Certificate", icon.getImage(), false);
            factory.add(checkerListPanel, "Candidate Checker List", icon.getImage(), false);
            factory.add(hierarchyPanel, "Setup Hierarchy", icon.getImage(), false);
            factory.add(seatsDistributionPanel, "Seats Distribution", icon.getImage(), false);
            factory.add(categoryListPanel, "Category List", icon.getImage(), false);
            factory.add(admissionListPanel, "Admission List", icon.getImage(), false);
            factory.add(exportDataPanel, "Export Candidates Details", icon.getImage(), false);
            add(factory);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        checkerListPanel = new javax.swing.JPanel();
        runReportButton1 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        admissionYearComboBox1 = new javax.swing.JComboBox();
        campusComboBox1 = new javax.swing.JComboBox();
        jLabel1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        programTypeComboBox1 = new javax.swing.JComboBox();
        hierarchyPanel = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        campusComboBox2 = new javax.swing.JComboBox();
        runReportButton2 = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        programTypeComboBox2 = new javax.swing.JComboBox();
        jLabel6 = new javax.swing.JLabel();
        shiftComboBox1 = new javax.swing.JComboBox();
        seatsDistributionPanel = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        campusComboBox3 = new javax.swing.JComboBox();
        jLabel8 = new javax.swing.JLabel();
        admissionYearComboBox3 = new javax.swing.JComboBox();
        jLabel9 = new javax.swing.JLabel();
        programTypeComboBox3 = new javax.swing.JComboBox();
        runReportButton3 = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        shiftComboBox3 = new javax.swing.JComboBox();
        categoryListPanel = new javax.swing.JPanel();
        runReportButton4 = new javax.swing.JButton();
        admissionListPanel = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        campusComboBox4 = new javax.swing.JComboBox();
        jLabel12 = new javax.swing.JLabel();
        admissionYearComboBox4 = new javax.swing.JComboBox();
        runReportButton5 = new javax.swing.JButton();
        jLabel15 = new javax.swing.JLabel();
        admissionListComboBox1 = new javax.swing.JComboBox();
        admissionSessionComboBox1 = new javax.swing.JComboBox();
        jLabel22 = new javax.swing.JLabel();
        exportDataPanel = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        campusComboBox5 = new javax.swing.JComboBox();
        jLabel17 = new javax.swing.JLabel();
        admissionYearComboBox5 = new javax.swing.JComboBox();
        runReportButton6 = new javax.swing.JButton();
        jLabel20 = new javax.swing.JLabel();
        admissionListComboBox = new javax.swing.JComboBox();
        jLabel21 = new javax.swing.JLabel();
        admissionSessionComboBox = new javax.swing.JComboBox();
        bonafideCertificatePanel = new javax.swing.JPanel();
        runReportButton7 = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        admissionYearComboBox2 = new javax.swing.JComboBox();
        jLabel14 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        programTypeComboBox4 = new javax.swing.JComboBox();
        seatNoTextField = new javax.swing.JTextField();
        jCheckBox1 = new javax.swing.JCheckBox();
        jLabel25 = new javax.swing.JLabel();
        partComboBox1 = new javax.swing.JComboBox();
        idCardPanel = new javax.swing.JPanel();
        runReportButton8 = new javax.swing.JButton();
        jLabel19 = new javax.swing.JLabel();
        admissionYearComboBox = new javax.swing.JComboBox();
        jLabel24 = new javax.swing.JLabel();
        programTypeComboBox = new javax.swing.JComboBox();
        allCandidatesCheckBox = new javax.swing.JCheckBox();
        partComboBox = new javax.swing.JComboBox();
        jLabel26 = new javax.swing.JLabel();
        campusLabel = new javax.swing.JLabel();
        programLabel = new javax.swing.JLabel();
        campusComboBox = new javax.swing.JComboBox();
        shiftLabel = new javax.swing.JLabel();
        shiftComboBox = new javax.swing.JComboBox();
        cposgComboBox = new javax.swing.JComboBox();
        cardsOfComboBox = new javax.swing.JComboBox();
        cardsOfLabel = new javax.swing.JLabel();
        pressEnterLabel = new javax.swing.JLabel();
        jSplitPane2 = new javax.swing.JSplitPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();

        checkerListPanel.setSize(241,164);

        runReportButton1.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        runReportButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/admission/view/images/Report.png"))); // NOI18N
        runReportButton1.setText("Run Report");
        runReportButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                runReportButton1ActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel3.setText("Admission Year:");

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel1.setText("Campus:");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel4.setText("Program Type:");

        javax.swing.GroupLayout checkerListPanelLayout = new javax.swing.GroupLayout(checkerListPanel);
        checkerListPanel.setLayout(checkerListPanelLayout);
        checkerListPanelLayout.setHorizontalGroup(
            checkerListPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(checkerListPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(checkerListPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(campusComboBox1, 0, 224, Short.MAX_VALUE)
                    .addComponent(jLabel3)
                    .addComponent(admissionYearComboBox1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(runReportButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel4)
                    .addComponent(programTypeComboBox1, 0, 167, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        checkerListPanelLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {admissionYearComboBox1, campusComboBox1, programTypeComboBox1, runReportButton1});

        checkerListPanelLayout.setVerticalGroup(
            checkerListPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(checkerListPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(campusComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(admissionYearComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(programTypeComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8)
                .addComponent(runReportButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel2.setText("Campus:");

        runReportButton2.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        runReportButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/admission/view/images/Report.png"))); // NOI18N
        runReportButton2.setText("Run Report");
        runReportButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                runReportButton2ActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel5.setText("Program Type:");

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel6.setText("Shift:");

        javax.swing.GroupLayout hierarchyPanelLayout = new javax.swing.GroupLayout(hierarchyPanel);
        hierarchyPanel.setLayout(hierarchyPanelLayout);
        hierarchyPanelLayout.setHorizontalGroup(
            hierarchyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(hierarchyPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(hierarchyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(shiftComboBox1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(runReportButton2, javax.swing.GroupLayout.DEFAULT_SIZE, 224, Short.MAX_VALUE)
                    .addGroup(hierarchyPanelLayout.createSequentialGroup()
                        .addGroup(hierarchyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(hierarchyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(campusComboBox2, 0, 224, Short.MAX_VALUE)
                                .addComponent(jLabel2)
                                .addComponent(jLabel5)
                                .addComponent(programTypeComboBox2, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jLabel6))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        hierarchyPanelLayout.setVerticalGroup(
            hierarchyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(hierarchyPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(campusComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(programTypeComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(shiftComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(runReportButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel7.setText("Campus:");

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel8.setText("Admission Year:");

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel9.setText("Program Type:");

        runReportButton3.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        runReportButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/admission/view/images/Report.png"))); // NOI18N
        runReportButton3.setText("Run Report");
        runReportButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                runReportButton3ActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel10.setText("Shift:");

        javax.swing.GroupLayout seatsDistributionPanelLayout = new javax.swing.GroupLayout(seatsDistributionPanel);
        seatsDistributionPanel.setLayout(seatsDistributionPanelLayout);
        seatsDistributionPanelLayout.setHorizontalGroup(
            seatsDistributionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(seatsDistributionPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(seatsDistributionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(seatsDistributionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(campusComboBox3, 0, 224, Short.MAX_VALUE)
                        .addComponent(jLabel8)
                        .addComponent(admissionYearComboBox3, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(runReportButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel9)
                        .addComponent(programTypeComboBox3, 0, 224, Short.MAX_VALUE)
                        .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jLabel10)
                    .addComponent(shiftComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        seatsDistributionPanelLayout.setVerticalGroup(
            seatsDistributionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(seatsDistributionPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(campusComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(admissionYearComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(programTypeComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(shiftComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(runReportButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        runReportButton4.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        runReportButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/admission/view/images/Report.png"))); // NOI18N
        runReportButton4.setText("Run Report");
        runReportButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                runReportButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout categoryListPanelLayout = new javax.swing.GroupLayout(categoryListPanel);
        categoryListPanel.setLayout(categoryListPanelLayout);
        categoryListPanelLayout.setHorizontalGroup(
            categoryListPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(categoryListPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(runReportButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        categoryListPanelLayout.setVerticalGroup(
            categoryListPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(categoryListPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(runReportButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel11.setText("Campus:");

        campusComboBox4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                campusComboBox4ActionPerformed(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel12.setText("Admission Year:");

        admissionYearComboBox4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                admissionYearComboBox4ActionPerformed(evt);
            }
        });

        runReportButton5.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        runReportButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/admission/view/images/Report.png"))); // NOI18N
        runReportButton5.setText("Run Report");
        runReportButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                runReportButton5ActionPerformed(evt);
            }
        });

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel15.setText("List:");

        admissionSessionComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                admissionSessionComboBox1ActionPerformed(evt);
            }
        });

        jLabel22.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel22.setText("Admission Session:");

        javax.swing.GroupLayout admissionListPanelLayout = new javax.swing.GroupLayout(admissionListPanel);
        admissionListPanel.setLayout(admissionListPanelLayout);
        admissionListPanelLayout.setHorizontalGroup(
            admissionListPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(admissionListPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(admissionListPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(campusComboBox4, 0, 224, Short.MAX_VALUE)
                    .addComponent(jLabel12)
                    .addComponent(admissionYearComboBox4, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(runReportButton5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel15)
                    .addComponent(admissionListComboBox1, 0, 224, Short.MAX_VALUE)
                    .addComponent(jLabel22)
                    .addComponent(admissionSessionComboBox1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        admissionListPanelLayout.setVerticalGroup(
            admissionListPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(admissionListPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(campusComboBox4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(admissionYearComboBox4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel22)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(admissionSessionComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel15)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(admissionListComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(runReportButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel16.setText("Campus:");

        campusComboBox5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                campusComboBox5ActionPerformed(evt);
            }
        });

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel17.setText("Admission Year:");

        admissionYearComboBox5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                admissionYearComboBox5ActionPerformed(evt);
            }
        });

        runReportButton6.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        runReportButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/admission/view/images/Report.png"))); // NOI18N
        runReportButton6.setText("Run Report");
        runReportButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                runReportButton6ActionPerformed(evt);
            }
        });

        jLabel20.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel20.setText("List:");

        jLabel21.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel21.setText("Admission Session:");

        admissionSessionComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                admissionSessionComboBoxActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout exportDataPanelLayout = new javax.swing.GroupLayout(exportDataPanel);
        exportDataPanel.setLayout(exportDataPanelLayout);
        exportDataPanelLayout.setHorizontalGroup(
            exportDataPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(exportDataPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(exportDataPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(campusComboBox5, 0, 224, Short.MAX_VALUE)
                    .addComponent(jLabel17)
                    .addComponent(admissionYearComboBox5, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(runReportButton6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel20)
                    .addComponent(admissionListComboBox, 0, 224, Short.MAX_VALUE)
                    .addComponent(jLabel21)
                    .addComponent(admissionSessionComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        exportDataPanelLayout.setVerticalGroup(
            exportDataPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(exportDataPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel16)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(campusComboBox5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel17)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(admissionYearComboBox5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel21)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(admissionSessionComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel20)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(admissionListComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(runReportButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        checkerListPanel.setSize(241,164);

        runReportButton7.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        runReportButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/admission/view/images/Report.png"))); // NOI18N
        runReportButton7.setText("Run Report");
        runReportButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                runReportButton7ActionPerformed(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel13.setText("Admission Year:");

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel14.setText("Seat No.:");

        jLabel18.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel18.setText("Program Type:");

        programTypeComboBox4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                programTypeComboBox4ActionPerformed(evt);
            }
        });

        seatNoTextField.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        seatNoTextField.setToolTipText("");
        seatNoTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                seatNoTextFieldActionPerformed(evt);
            }
        });

        jCheckBox1.setText("All  Candidates");
        jCheckBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox1ActionPerformed(evt);
            }
        });

        jLabel25.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel25.setText("Part:");

        javax.swing.GroupLayout bonafideCertificatePanelLayout = new javax.swing.GroupLayout(bonafideCertificatePanel);
        bonafideCertificatePanel.setLayout(bonafideCertificatePanelLayout);
        bonafideCertificatePanelLayout.setHorizontalGroup(
            bonafideCertificatePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bonafideCertificatePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(bonafideCertificatePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(bonafideCertificatePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel13)
                        .addComponent(admissionYearComboBox2, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(runReportButton7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel18)
                        .addComponent(programTypeComboBox4, 0, 224, Short.MAX_VALUE)
                        .addGroup(bonafideCertificatePanelLayout.createSequentialGroup()
                            .addComponent(jLabel14)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(seatNoTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jCheckBox1)))
                    .addComponent(jLabel25)
                    .addComponent(partComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        bonafideCertificatePanelLayout.setVerticalGroup(
            bonafideCertificatePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bonafideCertificatePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(admissionYearComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel18)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(programTypeComboBox4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel25)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(partComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(bonafideCertificatePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(seatNoTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jCheckBox1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(runReportButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        bonafideCertificatePanelLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {admissionYearComboBox2, programTypeComboBox4, seatNoTextField});

        checkerListPanel.setSize(241,164);

        runReportButton8.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        runReportButton8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/admission/view/images/Report.png"))); // NOI18N
        runReportButton8.setText("Run Report");
        runReportButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                runReportButton8ActionPerformed(evt);
            }
        });

        jLabel19.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel19.setText("Admission Year:");

        jLabel24.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel24.setText("Program Type:");

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

        jLabel26.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel26.setText("Part:");

        campusLabel.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        campusLabel.setText("Campus:");

        programLabel.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        programLabel.setText("Program:");

        campusComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                campusComboBoxActionPerformed(evt);
            }
        });

        shiftLabel.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        shiftLabel.setText("Shift:");

        shiftComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                shiftComboBoxActionPerformed(evt);
            }
        });

        cardsOfComboBox.setEditable(true);
        cardsOfComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cardsOfComboBoxActionPerformed(evt);
            }
        });
        cardsOfComboBox.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cardsOfComboBoxKeyPressed(evt);
            }
        });

        cardsOfLabel.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        cardsOfLabel.setText("Cards of:");

        pressEnterLabel.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        pressEnterLabel.setText("Press enter");

        javax.swing.GroupLayout idCardPanelLayout = new javax.swing.GroupLayout(idCardPanel);
        idCardPanel.setLayout(idCardPanelLayout);
        idCardPanelLayout.setHorizontalGroup(
            idCardPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(idCardPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(idCardPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabel19, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(admissionYearComboBox, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(runReportButton8, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel24, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(programTypeComboBox, javax.swing.GroupLayout.Alignment.LEADING, 0, 224, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, idCardPanelLayout.createSequentialGroup()
                        .addGap(107, 107, 107)
                        .addComponent(allCandidatesCheckBox))
                    .addComponent(jLabel26, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(partComboBox, 0, 224, Short.MAX_VALUE)
                    .addComponent(campusLabel, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(campusComboBox, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(shiftLabel, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(shiftComboBox, 0, 224, Short.MAX_VALUE)
                    .addComponent(programLabel, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cposgComboBox, 0, 224, Short.MAX_VALUE)
                    .addComponent(cardsOfLabel, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(idCardPanelLayout.createSequentialGroup()
                        .addComponent(cardsOfComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pressEnterLabel)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        idCardPanelLayout.setVerticalGroup(
            idCardPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(idCardPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel19)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(admissionYearComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel24)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(programTypeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel26)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(partComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cardsOfLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(idCardPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cardsOfComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pressEnterLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(campusLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(campusComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(shiftLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(shiftComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(programLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cposgComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(allCandidatesCheckBox)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(runReportButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        idCardPanelLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {admissionYearComboBox, cardsOfComboBox, partComboBox, programTypeComboBox});

        setClosable(true);
        setIconifiable(true);
        setTitle("Report Viewer");

        jSplitPane2.setDividerLocation(300);
        jSplitPane2.setOneTouchExpandable(true);

        jScrollPane1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        jSplitPane2.setTopComponent(jScrollPane1);

        jPanel1.setLayout(new java.awt.BorderLayout());
        jSplitPane2.setRightComponent(jPanel1);

        getContentPane().add(jSplitPane2, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void runReportButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_runReportButton1ActionPerformed
        // TODO add your handling code here:
        new Thread() {
            @Override
            public void run() {
                ReportViewerInternalFrame2.this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                new Thread() {
                    @Override
                    public void run() {
                        busyDialog.setVisible(true);
                    }
                }.start();

                try {
                    JasperDesign jd = JRXmlLoader.load(getClass().getResourceAsStream("/admission/reports/candidate_checker_report.jrxml"));
                    jr = JasperCompileManager.compileReport(jd);
                    if (jr != null) {
                        Campus campus = (Campus) campusComboBox1.getSelectedItem();
                        AdmissionYear ay = (AdmissionYear) admissionYearComboBox1.getSelectedItem();
                        ProgramType pt = (ProgramType) programTypeComboBox1.getSelectedItem();
                        if (campus == null || ay == null || pt == null) {
                            return;
                        }

                        List<CandidateDataBean> data = getCheckerReportData(campus, ay, pt);
                        JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(data);

                        Map parameters = new HashMap();
                        String header = getCheckerListHeader(campus, ay, pt);
                        String footer = getCheckerListFooter();
                        parameters.put("header", header);
                        parameters.put("footer", footer);
                        parameters.put("date", DateUtility.getDateToString(new Date()));

                        JasperPrint jp = JasperFillManager.fillReport(jr, parameters, beanColDataSource);
                        reportViewerPanel.loadJasperPrint(jp);
                    }
                } catch (HibernateException | MissingResourceException | JRException e) {
                    admission.utils.MessageBox.info(ReportViewerInternalFrame2.this, e);
                    jr = null;
                    e.printStackTrace();
                }
                busyDialog.dispose();
                ReportViewerInternalFrame2.this.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
            }
        }.start();
    }//GEN-LAST:event_runReportButton1ActionPerformed

    private void runReportButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_runReportButton2ActionPerformed
        // TODO add your handling code here:
        new Thread() {
            @Override
            public void run() {
                new Thread() {
                    @Override
                    public void run() {
                        busyDialog.setVisible(true);
                    }
                }.start();

                try {
                    JasperDesign jd = JRXmlLoader.load(getClass().getResourceAsStream("/admission/reports/main_campus_hierarchy_report.jrxml"));
                    jr = JasperCompileManager.compileReport(jd);
                    if (jr != null) {
                        Campus campus = (Campus) campusComboBox2.getSelectedItem();
                        Shift session = (Shift) shiftComboBox1.getSelectedItem();
                        ProgramType pt = (ProgramType) programTypeComboBox2.getSelectedItem();
                        if (campus == null || session == null || pt == null) {
                            return;
                        }

                        List<Faculty> data = getHierarchyReportData(campus, session);
                        JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(data);

                        Map parameters = new HashMap();
                        String header = "List of Program of studies in " + campus.getName() + " Campus";
                        //String footer = getCheckerListFooter();
                        parameters.put("header", header);
                        //parameters.put("footer", footer);
                        //parameters.put("date", DateFormatter.getDateToString(new Date()));

                        JasperPrint jp = JasperFillManager.fillReport(jr, parameters, beanColDataSource);
                        reportViewerPanel.loadJasperPrint(jp);
                    }
                } catch (HibernateException | MissingResourceException | JRException e) {
                    admission.utils.MessageBox.info(ReportViewerInternalFrame2.this, e);
                    jr = null;
                    e.printStackTrace();
                }
                busyDialog.dispose();
                ReportViewerInternalFrame2.this.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
            }
        }.start();
    }//GEN-LAST:event_runReportButton2ActionPerformed

    private void runReportButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_runReportButton3ActionPerformed
        // TODO add your handling code here:
        new Thread() {
            @Override
            public void run() {
                new Thread() {
                    @Override
                    public void run() {
                        busyDialog.setVisible(true);
                    }
                }.start();

                try {
                    JasperDesign jd = JRXmlLoader.load(getClass().getResourceAsStream("/admission/reports/seats_distribution.jrxml"));
                    jr = JasperCompileManager.compileReport(jd);
                    if (jr != null) {
                        Campus campus = (Campus) campusComboBox2.getSelectedItem();
                        AdmissionYear ay = (AdmissionYear) admissionYearComboBox3.getSelectedItem();
                        Shift session = (Shift) shiftComboBox3.getSelectedItem();
                        ProgramType pt = (ProgramType) programTypeComboBox3.getSelectedItem();
                        if (campus == null || ay == null || session == null || pt == null) {
                            return;
                        }

                        List<String> campusCategory = getCampusCategory(campus, pt, session);

                        Map parameters = new HashMap();
                        String title = "Distribution of allocated seats for " + pt.getName() + "' " + session.getName() + " Program " + ay.getYear();

                        List<SeatsDistributionDataBean> data = getSeats();
                        JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(data);

                        parameters.put("title", title);
                        parameters.put("headers", campusCategory);

                        JasperPrint jp = JasperFillManager.fillReport(jr, parameters, beanColDataSource);
                        reportViewerPanel.loadJasperPrint(jp);
                    }
                } catch (HibernateException | MissingResourceException | JRException e) {
                    admission.utils.MessageBox.info(ReportViewerInternalFrame2.this, e);
                    jr = null;
                    e.printStackTrace();
                }
                busyDialog.dispose();
                ReportViewerInternalFrame2.this.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
            }
        }.start();
    }//GEN-LAST:event_runReportButton3ActionPerformed

    private void runReportButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_runReportButton4ActionPerformed
        // TODO add your handling code here:
        new Thread() {
            @Override
            public void run() {
                new Thread() {
                    @Override
                    public void run() {
                        busyDialog.setVisible(true);
                    }
                }.start();

                try {
                    JasperDesign jd = JRXmlLoader.load(getClass().getResourceAsStream("/admission/reports/category_list.jrxml"));
                    jr = JasperCompileManager.compileReport(jd);
                    if (jr != null) {
                        Map parameters = new HashMap();
                        String title = "Category List";

                        List<CategoryType> data = DatabaseManager.getData(CategoryType.class.getName(), "categoryTypeId");
                        JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(data);

                        parameters.put("title", title);

                        JasperPrint jp = JasperFillManager.fillReport(jr, parameters, beanColDataSource);
                        reportViewerPanel.loadJasperPrint(jp);
                    }
                } catch (HibernateException | MissingResourceException | JRException e) {
                    admission.utils.MessageBox.info(ReportViewerInternalFrame2.this, e);
                    jr = null;
                    e.printStackTrace();
                }
                busyDialog.dispose();
                ReportViewerInternalFrame2.this.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
            }
        }.start();
    }//GEN-LAST:event_runReportButton4ActionPerformed

    private void runReportButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_runReportButton5ActionPerformed
        // TODO add your handling code here:
        new Thread() {
            @Override
            public void run() {
                Campus campus = (Campus) campusComboBox4.getSelectedItem();
                AdmissionYear ay = (AdmissionYear) admissionYearComboBox4.getSelectedItem();
                AdmissionList al = (AdmissionList) admissionListComboBox1.getSelectedItem();

                if (al == null) {
                    admission.utils.MessageBox.info(ReportViewerInternalFrame2.this, "Admission List not created");
                    return;
                }
                ReportViewerInternalFrame2.this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                new Thread() {
                    @Override
                    public void run() {
                        busyDialog.setVisible(true);
                    }
                }.start();

                List<CposGroup> cposgList = DatabaseManager.getCampusCposGroup(campus.getCampusId(), al.getAdmissionSession().getProgramType().getProgramTypeId());
                List<ProgramOfStudyDataBean> list = new ArrayList<>(cposgList.size());
                for (int i = 0; i < cposgList.size(); i++) {
                    CposGroup cposGroup = cposgList.get(i);

                    List<CampusCategory> campusCategories = DatabaseManager.getCampusCategory(cposGroup.getCposGroupId(), al.getAdmissionListId());

                    for (int j = 0; j < campusCategories.size(); j++) {
                        CampusCategory campusCategory = campusCategories.get(j);
                        List<AdmissionListDetails> admissionListDetails = DatabaseManager.getAdmissionListDetails(campusCategory.getCampusCategoryId(), al.getAdmissionListId());
                        campusCategory.setNewAld(admissionListDetails);
                    }

                    ProgramOfStudyDataBean ob = new ProgramOfStudyDataBean(cposGroup, campusCategories);
                    list.add(ob);
                }
                try {
                    JasperDesign jd = JRXmlLoader.load(getClass().getResourceAsStream("/admission/reports/bachalor_admission_list_report.jrxml"));
                    jr = JasperCompileManager.compileReport(jd);
                    if (jr != null) {
                        Map parameters = new HashMap();
                        String title = "LIST OF CANDIDATES PROVISIONALLY SELECTED FOR ADMISSION DURING THE ACADEMIC YEAR " + ay.getYear();

                        JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(list);

                        parameters.put("title", title);

                        JasperPrint jp = JasperFillManager.fillReport(jr, parameters, beanColDataSource);
                        reportViewerPanel.loadJasperPrint(jp);
                    }
                } catch (HibernateException | MissingResourceException | JRException e) {
                    admission.utils.MessageBox.info(ReportViewerInternalFrame2.this, e);
                    jr = null;
                    e.printStackTrace();
                    busyDialog.dispose();
                }
                busyDialog.dispose();
                ReportViewerInternalFrame2.this.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
            }
        }.start();
    }//GEN-LAST:event_runReportButton5ActionPerformed

    private void campusComboBox4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_campusComboBox4ActionPerformed
        // TODO add your handling code here:
        this.getAdmissionList(campusComboBox4, admissionSessionComboBox1, admissionListComboBox1);
    }//GEN-LAST:event_campusComboBox4ActionPerformed

    private void admissionYearComboBox4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_admissionYearComboBox4ActionPerformed
        // TODO add your handling code here:
        this.getAdmissionSession(admissionYearComboBox4, admissionSessionComboBox1);
    }//GEN-LAST:event_admissionYearComboBox4ActionPerformed

    private void campusComboBox5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_campusComboBox5ActionPerformed
        // TODO add your handling code here:
        this.getAdmissionList(campusComboBox5, admissionSessionComboBox, admissionListComboBox);
    }//GEN-LAST:event_campusComboBox5ActionPerformed

    private void admissionYearComboBox5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_admissionYearComboBox5ActionPerformed
        // TODO add your handling code here:
        this.getAdmissionSession(admissionYearComboBox5, admissionSessionComboBox);
    }//GEN-LAST:event_admissionYearComboBox5ActionPerformed

    private void runReportButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_runReportButton6ActionPerformed
        // TODO add your handling code here:
//        AdmissionYear ay = (AdmissionYear) this.admissionYearComboBox.getSelectedItem();
//        ProgramType pt = (ProgramType) this.programTypeComboBox.getSelectedItem();
//        if(ay == null || pt == null) return;
//
//        String seatNo = this.seatNoTextField.getText();
//        if(seatNo.isEmpty()) return;
//
//        Candidate candidate = DatabaseManager.getCandidate(ay.getAdmissionYearId(), pt.getProgramTypeId(), seatNo);
//        if(candidate == null) {
//            utilities.MessageBox.info(this, "Candidate not found");
//            return;
//        }
        final Campus c = (Campus) this.campusComboBox5.getSelectedItem();
        final AdmissionSession as = (AdmissionSession) this.admissionSessionComboBox.getSelectedItem();
        final AdmissionList al = (AdmissionList) this.admissionListComboBox.getSelectedItem();
        if (c == null || as == null || al == null) {
            return;
        }

        new Thread() {
            @Override
            public void run() {
                try {
                    setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                    new Thread() {
                        @Override
                        public void run() {
                            busyDialog.setVisible(true);
                        }
                    }.start();

                    List<admission.controller.beans.Candidate> candidates = JDBCDatabaseManager.getCandidates(as.getAdmissionYear().getAdmissionYearId(), c.getCampusId(), as.getProgramType().getProgramTypeId());
                    if (candidates.isEmpty()) {
                        busyDialog.dispose();
                        return;
                    }
                    List<DetailsBean> list = new ArrayList<>();
                    for (int i = 0; i < candidates.size(); i++) {
                        admission.controller.beans.Candidate cn = candidates.get(i);
                        List<AdmissionListDetails> aldList = JDBCDatabaseManager.getAdmissionListDetail(cn.getCandidateId(), al.getAdmissionListId());

                        DetailsBean bean = new DetailsBean();
                        if (aldList.isEmpty()) {
                            bean.setSNo(i + 1);
                            bean.setSeatNo(cn.getSeatNo());
                            bean.setGender(cn.getGender());
                            bean.setName(cn.getName());
                            bean.setFathersName(cn.getFathersName());
                            bean.setSurname(cn.getSurname());
                            bean.setDistrict(cn.getDistrictName());
                            bean.setArea(cn.getArea());
                            bean.setPercentage(cn.getPercentage());
                            bean.setObjection(cn.isObjection() ? "Y" : "N");
                            bean.setObjectionRemarks(cn.getObjectionRemarks());
                            bean.setChoiceNo(0);
                            bean.setCategory("NONE");
                            bean.setDiscipline("NONE");
                        } else {
                            for (int j = 0; j < aldList.size(); j++) {
                                AdmissionListDetails ald = aldList.get(j);

                                bean.setSNo(i + 1);
                                bean.setSeatNo(cn.getSeatNo());
                                bean.setGender(cn.getGender());
                                bean.setName(cn.getName());
                                bean.setFathersName(cn.getFathersName());
                                bean.setSurname(cn.getSurname());
                                bean.setDistrict(cn.getDistrictName());
                                bean.setArea(cn.getArea());
                                bean.setPercentage(cn.getPercentage());
                                bean.setObjection(cn.isObjection() ? "Y" : "N");
                                bean.setObjectionRemarks(cn.getObjectionRemarks());
                                bean.setChoiceNo(ald.getChoiceNo());
                                bean.setCategory(ald.getCampusCategoryName());
                                bean.setDiscipline(ald.getPosName() + (ald.getCposg().equals("G") ? "" : " " + ald.getCposg()));
                            }
                        }
                        list.add(bean);
                    }
                    try {
                        JasperDesign jd = JRXmlLoader.load(getClass().getResourceAsStream("/admission/reports/candidate_details_report.jrxml"));
                        jr = JasperCompileManager.compileReport(jd);
                        if (jr != null) {
                            Map parameters = new HashMap();

                            String title = "CANDIDATES DETAILS OF " + c.toString() + " CAMPUS DURING THE ACADEMIC YEAR " + as.getAdmissionYear().toString();
                            parameters.put("title", title);

                            JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(list);
                            JasperPrint jp = JasperFillManager.fillReport(jr, parameters, beanColDataSource);
                            reportViewerPanel.loadJasperPrint(jp);
                        }
                    } catch (HibernateException | MissingResourceException | JRException e) {
                        admission.utils.MessageBox.info(ReportViewerInternalFrame2.this, e);
                        jr = null;
                        busyDialog.dispose();
                        ReportViewerInternalFrame2.this.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
                    }
                    busyDialog.dispose();
                    ReportViewerInternalFrame2.this.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
                } catch (SQLException ex) {
                    busyDialog.dispose();
                    Logger.getLogger(ReportViewerInternalFrame2.class.getName()).log(Level.SEVERE, null, ex);
                    admission.utils.MessageBox.info(ReportViewerInternalFrame2.this, ex);
                }
            }
        }.start();
    }//GEN-LAST:event_runReportButton6ActionPerformed

    private void admissionSessionComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_admissionSessionComboBoxActionPerformed
        // TODO add your handling code here:
        getAdmissionList(campusComboBox5, admissionSessionComboBox, admissionListComboBox);
    }//GEN-LAST:event_admissionSessionComboBoxActionPerformed

    private void admissionSessionComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_admissionSessionComboBox1ActionPerformed
        // TODO add your handling code here:
        getAdmissionList(campusComboBox5, admissionSessionComboBox1, admissionListComboBox1);
    }//GEN-LAST:event_admissionSessionComboBox1ActionPerformed

    private void runReportButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_runReportButton7ActionPerformed
        // TODO add your handling code here:
        new Thread() {
            @Override
            public void run() {
                AdmissionYear ay = (AdmissionYear) admissionYearComboBox2.getSelectedItem();
                ProgramType pt = (ProgramType) programTypeComboBox4.getSelectedItem();
                Part part = (Part) partComboBox1.getSelectedItem();
                if (ay == null || pt == null || part == null) {
                    return;
                }

                String seatNo = seatNoTextField.getText();
                if (seatNo.isEmpty()) {
                    return;
                }

                new Thread() {
                    @Override
                    public void run() {
                        busyDialog.setVisible(true);
                    }
                }.start();

                List<BonafideCertificateJRBean> list = new ArrayList();
                try {
                    HashMap<String, String> candidate = JDBCDatabaseManager.getCandidate(ay.getAdmissionYearId(), pt.getProgramTypeId(), part.getPartId(), seatNo, true);
                    if (candidate == null) {
                        busyDialog.dispose();
                        ReportViewerInternalFrame2.this.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
                        admission.utils.MessageBox.info(ReportViewerInternalFrame2.this, "Candidate have no selection");
                        return;
                    }
//                    String dept = candidate.get("department");
//                    dept = dept.replaceAll("&", "&amp;");
//                    candidate.put("department", dept);

                    candidate.put("part", part.getName());
                    BonafideCertificateJRBean bean = new BonafideCertificateJRBean(candidate, ay.getYear(), (pt.getIsBachelor()));
                    list.add(bean);

                    JasperDesign jd = JRXmlLoader.load(getClass().getResourceAsStream("/admission/reports/bonafide_certificate.jrxml"));
                    jr = JasperCompileManager.compileReport(jd);
                    if (jr != null) {
                        Map parameters = new HashMap();

                        BufferedImage image = ImageIO.read(getClass().getResource("/images/org_logo.jpg"));
                        parameters.put("left_logo", image);
                        image = ImageIO.read(getClass().getResource("/images/right_logo.jpg"));
                        parameters.put("right_logo", image);

                        JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(list);
                        JasperPrint jp = JasperFillManager.fillReport(jr, parameters, beanColDataSource);
                        reportViewerPanel.loadJasperPrint(jp);
                    }
                    busyDialog.dispose();
                    ReportViewerInternalFrame2.this.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
                } catch (IOException | SQLException | HibernateException | MissingResourceException | JRException e) {
                    admission.utils.MessageBox.info(ReportViewerInternalFrame2.this, e);
                    jr = null;
                    busyDialog.dispose();
                    ReportViewerInternalFrame2.this.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
                }
            }
        }.start();
    }//GEN-LAST:event_runReportButton7ActionPerformed

    private void jCheckBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox1ActionPerformed
        // TODO add your handling code here:
        seatNoTextField.setEnabled(!jCheckBox1.isSelected());
    }//GEN-LAST:event_jCheckBox1ActionPerformed

    private void seatNoTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_seatNoTextFieldActionPerformed
        // TODO add your handling code here:
        runReportButton7ActionPerformed(evt);
    }//GEN-LAST:event_seatNoTextFieldActionPerformed

    private void runReportButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_runReportButton8ActionPerformed
        // TODO add your handling code here:
        new Thread() {
            @Override
            public void run() {
                AdmissionYear ay = (AdmissionYear) admissionYearComboBox.getSelectedItem();
                ProgramType pt = (ProgramType) programTypeComboBox.getSelectedItem();
                Part part = (Part) partComboBox.getSelectedItem();
                CposGroup cposg = (CposGroup) cposgComboBox.getSelectedItem();
                if (ay == null || pt == null || part == null || (cposg == null && allCandidatesCheckBox.isSelected())) {
                    return;
                }

//                String seatNo = seatNoTextField1.getText();
//                if(!allCandidatesCheckBox.isSelected() && seatNo.isEmpty()) return;
                new Thread() {
                    @Override
                    public void run() {
                        busyDialog.setVisible(true);
                    }
                }.start();

                List<CandidateDataBean> list = new ArrayList<>();
                try {
                    String campusName = "";
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
                        for (int i = 0; i < candidates.size(); i++) {
                            Map<String, Object> candidate = candidates.get(i);
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
                    JasperDesign jd = JRXmlLoader.load(getClass().getResourceAsStream("/admission/reports/id_card_report.jrxml"));
                    jr = JasperCompileManager.compileReport(jd);
                    if (jr != null) {
                        Map parameters = new HashMap();

                        BufferedImage image = ImageIO.read(getClass().getResource("/images/new_logo2.png"));
                        parameters.put("logo", image);
                        image = ImageIO.read(getClass().getResource("/images/DA_sign.png"));
                        parameters.put("da_sign", image);
                        image = ImageIO.read(getClass().getResource("/images/new_logo2_trans.png"));
                        parameters.put("trans_logo", image);
                        parameters.put("campusName", campusName);

                        JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(list);
                        JasperPrint jp = JasperFillManager.fillReport(jr, parameters, beanColDataSource);
                        reportViewerPanel.loadJasperPrint(jp);
                    }
                    cardsOfComboBox.removeAllItems();
                    busyDialog.dispose();
                    ReportViewerInternalFrame2.this.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
                } catch (SQLException | IOException | HibernateException | MissingResourceException | JRException e) {
                    admission.utils.MessageBox.info(ReportViewerInternalFrame2.this, e);
                    jr = null;
                    busyDialog.dispose();
                    ReportViewerInternalFrame2.this.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
                }
            }
        }.start();
    }//GEN-LAST:event_runReportButton8ActionPerformed

    private void allCandidatesCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_allCandidatesCheckBoxActionPerformed
        // TODO add your handling code here:
        setIDCardComponentEnabled(allCandidatesCheckBox.isSelected());
    }//GEN-LAST:event_allCandidatesCheckBoxActionPerformed

    private void programTypeComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_programTypeComboBoxActionPerformed
        // TODO add your handling code here:
        ProgramType pt = (ProgramType) programTypeComboBox.getSelectedItem();
        if (pt == null) {
            return;
        }
        getPart(pt, partComboBox);
        getCampusProgramOfStudyGroup();
    }//GEN-LAST:event_programTypeComboBoxActionPerformed

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

    private void programTypeComboBox4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_programTypeComboBox4ActionPerformed
        // TODO add your handling code here:
        ProgramType pt = (ProgramType) programTypeComboBox4.getSelectedItem();
        if (pt == null) {
            return;
        }

        getPart(pt, partComboBox1);
    }//GEN-LAST:event_programTypeComboBox4ActionPerformed

    private void cardsOfComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cardsOfComboBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cardsOfComboBoxActionPerformed

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

    private void getCampusProgramOfStudyGroup() {
        cposgComboBox.removeAllItems();

        Campus campus = (Campus) this.campusComboBox.getSelectedItem();
        Shift shift = (Shift) this.shiftComboBox.getSelectedItem();
        ProgramType pt = (ProgramType) this.programTypeComboBox.getSelectedItem();
        if (campus == null || shift == null || pt == null) {
            return;
        }

        List<CposGroup> list = DatabaseManager.getCampusCposGroup(campus.getCampusId(), shift.getShiftId(), pt.getProgramTypeId());

        for (int i = 0; i < list.size(); i++) {
            cposgComboBox.addItem(list.get(i));
        }
    }

    private void getCampus() {
        this.campusComboBox.removeAllItems();
        this.campusComboBox1.removeAllItems();
        this.campusComboBox2.removeAllItems();
        this.campusComboBox3.removeAllItems();
        this.campusComboBox4.removeAllItems();
        this.campusComboBox5.removeAllItems();

        List<Campus> list = DatabaseManager.getData(Campus.class.getName(), "name");
        for (int i = 0; i < list.size(); i++) {
            campusComboBox.addItem(list.get(i));
            campusComboBox1.addItem(list.get(i));
            campusComboBox2.addItem(list.get(i));
            campusComboBox3.addItem(list.get(i));
            campusComboBox4.addItem(list.get(i));
            campusComboBox5.addItem(list.get(i));
        }
    }

    private void getAdmissionYear() {
        this.admissionYearComboBox1.removeAllItems();
        this.admissionYearComboBox2.removeAllItems();
        this.admissionYearComboBox3.removeAllItems();
        this.admissionYearComboBox4.removeAllItems();
        this.admissionYearComboBox5.removeAllItems();
        this.admissionYearComboBox.removeAllItems();

        List<AdmissionYear> list = DatabaseManager.getData(AdmissionYear.class.getName(), "year DESC");
        for (int i = 0; i < list.size(); i++) {
            admissionYearComboBox1.addItem(list.get(i));
            admissionYearComboBox2.addItem(list.get(i));
            admissionYearComboBox3.addItem(list.get(i));
            admissionYearComboBox4.addItem(list.get(i));
            admissionYearComboBox5.addItem(list.get(i));
            admissionYearComboBox.addItem(list.get(i));
        }
    }

    private void getProgramType() {
        this.programTypeComboBox1.removeAllItems();
        this.programTypeComboBox2.removeAllItems();
        this.programTypeComboBox3.removeAllItems();
        this.programTypeComboBox4.removeAllItems();
        this.programTypeComboBox.removeAllItems();

        List<ProgramType> list = DatabaseManager.getData(ProgramType.class.getName(), "programTypeId");
        for (int i = 0; i < list.size(); i++) {
            programTypeComboBox1.addItem(list.get(i));
            programTypeComboBox2.addItem(list.get(i));
            programTypeComboBox3.addItem(list.get(i));
            programTypeComboBox4.addItem(list.get(i));
            programTypeComboBox.addItem(list.get(i));
        }
    }

    private void getPart(ProgramType pt, JComboBox combo) {
        combo.removeAllItems();

        List<Part> list = DatabaseManager.getParts(pt.getProgramTypeId());
        for (int i = 0; i < list.size(); i++) {
            combo.addItem(list.get(i));
//            partComboBox.addItem(list.get(i));
        }
    }

    private void getShift() {
        this.shiftComboBox1.removeAllItems();
        this.shiftComboBox3.removeAllItems();
        this.shiftComboBox.removeAllItems();

        List<Shift> list = DatabaseManager.getData(Shift.class.getName(), "shiftId");
        for (int i = 0; i < list.size(); i++) {
            shiftComboBox.addItem(list.get(i));
            shiftComboBox1.addItem(list.get(i));
            shiftComboBox3.addItem(list.get(i));
        }
    }

    private void getAdmissionSession(JComboBox ayCombo, JComboBox asCombo) {
        asCombo.removeAllItems();

        AdmissionYear ay = (AdmissionYear) ayCombo.getSelectedItem();
        if (ay == null) {
            return;
        }

        Set set = ay.getAdmissionSessions();
        if (set == null) {
            return;
        }

        Object[] toArray = set.toArray();
        for (Object toArray1 : toArray) {
            asCombo.addItem(toArray1);
        }
    }

    private void getAdmissionList(JComboBox campusCombo, JComboBox asCombo, JComboBox combo) {
        combo.removeAllItems();

        Campus campus = (Campus) campusCombo.getSelectedItem();
        AdmissionSession as = (AdmissionSession) asCombo.getSelectedItem();
        if (as == null || campus == null) {
            return;
        }

       List<AdmissionList> list = DatabaseManager.getData(AdmissionList.class, "admissionSession.admissionSessionId = " + as.getAdmissionSessionId() + " AND campus.campusId = " + campus.getCampusId(), "listNo DESC");

        for (AdmissionList al : list) {
            combo.addItem(al);
        }
    }

//    private void getList(){
//        this.admissionListComboBox1.removeAllItems();
//        
//        Campus campus = (Campus) campusComboBox4.getSelectedItem();
//        AdmissionYear ay = (AdmissionYear) admissionYearComboBox4.getSelectedItem();
//        Shift shift = (Shift) shiftComboBox4.getSelectedItem();
//        ProgramType pt = (ProgramType) programTypeComboBox4.getSelectedItem();
//        if(campus == null || ay == null || shift == null || pt == null) return;
//                
//        AdmissionSession as = DatabaseManager.getAdmissionSession(ay.getAdmissionYearId(), shift.getShiftId(), pt.getProgramTypeId());
//        if(as == null){
//            utilities.MessageBox.info(ReportViewerInternalFrame.this, "Admission Session not found");
//            return;
//        }
//        
//        Set set = as.getAdmissionLists();
//        for (Iterator it = set.iterator(); it.hasNext();) {
//            admissionListComboBox1.addItem(it.next());
//        }
//    }
    private List<CandidateDataBean> getCheckerReportData(Campus campus, AdmissionYear ay, ProgramType pt) throws HibernateException {
        List<Candidate> candidates = DatabaseManager.getCandidates(ay.getAdmissionYearId(), campus.getCampusId(), pt.getProgramTypeId());
        List<CandidateDataBean> list = new ArrayList<>();

        for (int i = 0; i < candidates.size(); i++) {
            Candidate candidate = candidates.get(i);

            CandidateDataBean cdb = new CandidateDataBean();
            cdb.setSeatNo(candidate.getSeatNo());
            cdb.setGender(candidate.getGender().getCode());
            cdb.setName(candidate.getName());
            cdb.setFathersName(candidate.getFathersName());
            cdb.setSurname(candidate.getSurname());
            cdb.setArea(candidate.getArea().getCode());
            cdb.setDistrict(candidate.getDistrict());
            cdb.setSsc(this.getCredentialDetails(candidate, IConstant.SSC));
            cdb.setHsc(this.getCredentialDetails(candidate, IConstant.HSC));
            cdb.setAppliedCampuses(this.getAppliedCampus(candidate));
            cdb.setAppliedCategories(this.getAppliedCategory(candidate));
            cdb.setCandidateProgramOfStudies(this.getAppliedDiscipline(candidate));

            list.add(cdb);
        }
        return list;
    }

    private List<Faculty> getHierarchyReportData(Campus campus, Shift shift) throws HibernateException {
        List<Faculty> list = DatabaseManager.getMainCampusHierarchy(campus.getCampusId(), shift.getShiftId());
        return list;
    }

    private String getCheckerListHeader(Campus campus, AdmissionYear ay, ProgramType pt) {
        String header = "CHECKING LIST OF CANDIDATES PROVISIONAL APPLY FOR VARIOUS SUBJECTS OF " + pt.getName() + "'S PROGRAMME DURING THE ACADEMIC YEAR " + ay.getYear() + " PROGRAMME " + campus.getName() + " CAMPUS ";
        return header;
    }

    private String getCheckerListFooter() {
        String footer = "SIGNATURE-FEED IN COMPUTER BY / SIGNATURE-RECORD CHECKED FORM / SIGNATURE-RECORD CHECKED IN SUPERVISION OF ___________________ -ASST:DIRECTOR";
        return footer;
    }

    private List<String> getAppliedCampus(Candidate candidate) {
        List<String> list = new ArrayList<>();

        List<AppliedCampus> appliedCampusList = DatabaseManager.getData(AppliedCampus.class, "candidate.candidateId = " + candidate.getCandidateId(), "appliedCampusId");
        if (appliedCampusList.isEmpty()) {
            return list;
        }

        for (AppliedCampus ac : appliedCampusList) {
            list.add(ac.getCampus().getCode());
        }
        return list;
    }

    private List<String> getAppliedCategory(Candidate candidate) {
        List<String> list = new ArrayList<>();

        List<AppliedCategory> appliedCategoryList = DatabaseManager.getData(AppliedCategory.class, "candidate.candidateId = " + candidate.getCandidateId(), "appliedCategoryId");
        if (appliedCategoryList.isEmpty()) {
            return list;
        }

        for (AppliedCategory ac : appliedCategoryList) {
            list.add(ac.getCategoryCode());
        }
        return list;
    }

    private List<String> getAppliedDiscipline(Candidate candidate) { // Work Remaning
        List<String> list = new ArrayList<>();

        List<CandidateProgramOfStudy> data = DatabaseManager.getData(CandidateProgramOfStudy.class, "candidate.candidateId = " + candidate.getCandidateId(), "candidateProgramOfStudyId");
//        Set set = candidate.getCandidateProgramOfStudies();

        for (CandidateProgramOfStudy cnpos : data) {
            list.add(cnpos.getCampusProgramOfStudy().getProgramOfStudy().getDiscipline().getCode());
        }
        int count = IConstant.CHOICE_LIMIT;
        if (list.size() != count) {
            count = count - list.size();
            for (int i = 0; i < count; i++) {
                list.add("NIL");
            }
        }
        return list;
    }

    private CredentialDetails getCredentialDetails(Candidate candidate, int detailOf) {
        CredentialDetails credentialDetails = DatabaseManager.getSingleRecord(CredentialDetails.class, "candidate.candidateId = " + candidate.getCandidateId() + " AND detailOf = " + detailOf);

        return credentialDetails;
    }

    private List<String> getCampusCategory(Campus campus, ProgramType pt, Shift shift) {
        List<CampusCategory> campusCategory = DatabaseManager.getCampusCategory(campus.getCampusId(), pt.getProgramTypeId(), shift.getShiftId(), "displayOrder");

        List<String> list = new ArrayList<>();
        for (int i = 0; i < campusCategory.size(); i++) {
            CampusCategory cc = campusCategory.get(i);
            list.add(cc.toString());
        }
        list.add("TOTAL SEATS");
        return list;
    }

    private List<SeatsDistributionDataBean> getSeats() {
        List<SeatsDistributionDataBean> list = new ArrayList<>();

        Campus campus = (Campus) this.campusComboBox3.getSelectedItem();
        AdmissionYear ay = (AdmissionYear) this.admissionYearComboBox3.getSelectedItem();
        ProgramType pt = (ProgramType) this.programTypeComboBox3.getSelectedItem();
        Shift shift = (Shift) this.shiftComboBox3.getSelectedItem();
        if (campus == null || ay == null || pt == null || shift == null) {
            return list;
        }

//        AdmissionSession as = DatabaseManager.getAdmissionSession(ay.getAdmissionYearId(), shift.getShiftId(), pt.getProgramTypeId());
        AdmissionSession as = DatabaseManager.getSingleRecord(AdmissionSession.class, "admissionYear.admissionYearId = " + ay.getAdmissionYearId() + " AND programType.programTypeId = " + pt.getProgramTypeId());
        if (as == null) {
            admission.utils.MessageBox.info(this, "AdmissionSession not found");
            return list;
        }

        if ((campus.getIsMain())) {
            List<Faculty> facList = DatabaseManager.getCampusFaculties(campus.getCampusId());
            for (int i = 0; i < facList.size(); i++) {
                Faculty faculty = facList.get(i);
                List<SeatsBean> rows = new ArrayList<>();

                List<CposGroup> list1 = DatabaseManager.getCampusFacultyCposGroup(faculty.getFacultyId(), campus.getCampusId(), pt.getProgramTypeId(), shift.getShiftId());
                for (CposGroup cposGroup : list1) {
                    List<DisciplineCategorySeats> list2 = DatabaseManager.getCposGroupSeatsDistribution(cposGroup.getCposGroupId(), as.getAdmissionSessionId(), pt.getProgramTypeId(), shift.getShiftId());
                    if (list2.isEmpty()) {
                        continue;
                    }

                    List<Integer> row = new ArrayList<>();
                    Integer count = 0;
                    for (DisciplineCategorySeats dcs : list2) {
                        row.add(dcs.getNumberOfSeats());
                        count += dcs.getNumberOfSeats();
                    }
                    row.add(count);
                    rows.add(new SeatsBean(cposGroup.toString(), row));
                }
                list.add(new SeatsDistributionDataBean(faculty.getName(), rows));
            }
        }
        return list;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox admissionListComboBox;
    private javax.swing.JComboBox admissionListComboBox1;
    private javax.swing.JPanel admissionListPanel;
    private javax.swing.JComboBox admissionSessionComboBox;
    private javax.swing.JComboBox admissionSessionComboBox1;
    private javax.swing.JComboBox admissionYearComboBox;
    private javax.swing.JComboBox admissionYearComboBox1;
    private javax.swing.JComboBox admissionYearComboBox2;
    private javax.swing.JComboBox admissionYearComboBox3;
    private javax.swing.JComboBox admissionYearComboBox4;
    private javax.swing.JComboBox admissionYearComboBox5;
    private javax.swing.JCheckBox allCandidatesCheckBox;
    private javax.swing.JPanel bonafideCertificatePanel;
    private javax.swing.JComboBox campusComboBox;
    private javax.swing.JComboBox campusComboBox1;
    private javax.swing.JComboBox campusComboBox2;
    private javax.swing.JComboBox campusComboBox3;
    private javax.swing.JComboBox campusComboBox4;
    private javax.swing.JComboBox campusComboBox5;
    private javax.swing.JLabel campusLabel;
    private javax.swing.JComboBox cardsOfComboBox;
    private javax.swing.JLabel cardsOfLabel;
    private javax.swing.JPanel categoryListPanel;
    private javax.swing.JPanel checkerListPanel;
    private javax.swing.JComboBox cposgComboBox;
    private javax.swing.JPanel exportDataPanel;
    private javax.swing.JPanel hierarchyPanel;
    private javax.swing.JPanel idCardPanel;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSplitPane jSplitPane2;
    private javax.swing.JComboBox partComboBox;
    private javax.swing.JComboBox partComboBox1;
    private javax.swing.JLabel pressEnterLabel;
    private javax.swing.JLabel programLabel;
    private javax.swing.JComboBox programTypeComboBox;
    private javax.swing.JComboBox programTypeComboBox1;
    private javax.swing.JComboBox programTypeComboBox2;
    private javax.swing.JComboBox programTypeComboBox3;
    private javax.swing.JComboBox programTypeComboBox4;
    private javax.swing.JButton runReportButton1;
    private javax.swing.JButton runReportButton2;
    private javax.swing.JButton runReportButton3;
    private javax.swing.JButton runReportButton4;
    private javax.swing.JButton runReportButton5;
    private javax.swing.JButton runReportButton6;
    private javax.swing.JButton runReportButton7;
    private javax.swing.JButton runReportButton8;
    private javax.swing.JTextField seatNoTextField;
    private javax.swing.JPanel seatsDistributionPanel;
    private javax.swing.JComboBox shiftComboBox;
    private javax.swing.JComboBox shiftComboBox1;
    private javax.swing.JComboBox shiftComboBox3;
    private javax.swing.JLabel shiftLabel;
    // End of variables declaration//GEN-END:variables
    private JRViewer300 reportViewerPanel;
    private JasperReport jr = null;
    private BusyIndicator busyDialog;
}
