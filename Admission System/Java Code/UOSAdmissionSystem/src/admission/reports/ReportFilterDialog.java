package admission.reports;

import admission.enums.MessageEnum;
import admission.helpers.CommonHelper;
import admission.helpers.ReportHelper;
import admission.view.MainFrame;
import admission.xmlparser.Report;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;
import net.sf.jasperreports.engine.DefaultJasperReportsContext;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRPropertiesUtil;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.fill.AsynchronousFillHandle;
import net.sf.jasperreports.engine.fill.FillListener;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import admission.utils.MessageBox;
import admission.utils.RoundedBorder;

/**
 *
 * @author Yougeshwar
 */
public class ReportFilterDialog extends javax.swing.JDialog {

    public ReportFilterDialog(java.awt.Dialog parent, boolean modal) {
        super(parent, modal);
        initComponents();

        setLocationRelativeTo(null);
        this.titlePanel.setBorder(RoundedBorder.createGradientBorder());

        parameterMap = new HashMap<>();
        ReportHelper.fillMapParameter(parameterMap);
    }

    public void setReport(Report report) {
        this.report = report;
    }

    public void showFilter() {
        this.view = null;
        this.filter = null;
        jScrollPane1.getViewport().removeAll();
        if (report == null) {
            MessageBox.info(this, "Report is not intialized");
            setVisible(true);
            return;
        }
        String filterClass = report.getFilterClass();
        try {
            filter = Class.forName(filterClass).newInstance();
            JPanel panel = (JPanel) filter;
            admission.utils.Utility.setStarkRed(panel);
            this.jScrollPane1.setViewportView(panel);
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(ReportFilterDialog.class.getName()).log(Level.SEVERE, null, ex);
            MessageBox.error(this, ex);
        }
        setVisible(true);
    }

    public void showFilter(ReportFilterProvider rfp, JRViewer300 view) {
        jScrollPane1.getViewport().removeAll();
        if (report == null) {
            MessageBox.info(this, "Report is not intialized");
            setVisible(true);
            return;
        }
        this.filter = rfp;
        this.view = view;
        this.jScrollPane1.setViewportView((JPanel) filter);
        setVisible(true);
    }

    public void setFilter(ReportFilterProvider rfp, JRViewer300 view) {
        jScrollPane1.getViewport().removeAll();
        if (report == null) {
            MessageBox.info(this, "Report is not intialized");
            return;
        }
        this.filter = rfp;
        this.view = view;
        this.jScrollPane1.setViewportView((JPanel) filter);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        titlePanel = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jSeparator1 = new javax.swing.JSeparator();
        cancelButton = new javax.swing.JButton();
        okButton = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Report Filter");
        setResizable(false);

        titlePanel.setLayout(new java.awt.BorderLayout());

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/filter.png"))); // NOI18N
        jLabel2.setText("Report Filter");
        jLabel2.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, java.awt.Color.darkGray), javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5)));
        titlePanel.add(jLabel2, java.awt.BorderLayout.CENTER);

        getContentPane().add(titlePanel, java.awt.BorderLayout.PAGE_START);

        cancelButton.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        cancelButton.setText("Cancel");
        cancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelButtonActionPerformed(evt);
            }
        });

        okButton.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        okButton.setText("OK");
        okButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                okButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator1)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(488, Short.MAX_VALUE)
                .addComponent(okButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cancelButton)
                .addContainerGap())
        );

        jPanel2Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {cancelButton, okButton});

        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cancelButton)
                    .addComponent(okButton))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel2, java.awt.BorderLayout.PAGE_END);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 331, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel3, java.awt.BorderLayout.LINE_END);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 331, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel4, java.awt.BorderLayout.LINE_START);

        jPanel5.setLayout(new java.awt.BorderLayout());
        jPanel5.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        getContentPane().add(jPanel5, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelButtonActionPerformed
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_cancelButtonActionPerformed

    private void okButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_okButtonActionPerformed
        executeReport(evt);
    }//GEN-LAST:event_okButtonActionPerformed

    public void executeReport(java.awt.event.ActionEvent evt) {
        BusyIndicator.showWhile(new Runnable() {
            @Override public void run() {
                try {
                    DefaultJasperReportsContext context = DefaultJasperReportsContext.getInstance();
                    String s = JRPropertiesUtil.getInstance(context).getProperty("net.sf.jasperreports.xpath.executer.factory");
                    
                    JRPropertiesUtil.getInstance(context).setProperty("net.sf.jasperreports.xpath.executer.factory",
                            "net.sf.jasperreports.engine.util.xml.JaxenXPathExecuterFactory");
                    
                    s = JRPropertiesUtil.getInstance(context).getProperty("net.sf.jasperreports.xpath.executer.factory");
                    
                    JasperDesign jd = JRXmlLoader.load(getClass().getResourceAsStream("/reports/" + report.getJasperFile()));
                    JasperReport jr = JasperCompileManager.compileReport(jd);
                    if (jr != null) {
                        ReportFilterProvider rfp = (ReportFilterProvider) filter;
                        JRBeanCollectionDataSource beanColDataSource = rfp.getJRDataSource();
                        if (beanColDataSource == null) {
                            //MessageBox.info(ReportFilterDialog.this, MessageEnum.MSG_NO_FOUND);
                            return;
                        }
                        rfp.fillReportParameter(parameterMap);
                        parameterMap.put("SUBREPORT_DIR", getClass().getResource("/reports/").toString());
                        parameterMap.put("PRINTED_BY", CommonHelper.getActiveUserName());
                        parameterMap.put("DEVELOPED_BY", "YOUGESHWAR KHATRI");

//                        AsynchronousFillHandle createHandle = AsynchronousFillHandle.createHandle(jr, parameterMap, beanColDataSource);
//                        createHandle.addFillListener(new FillListener() {
//                            @Override public void pageGenerated(JasperPrint jp, int i) {
//                                System.out.println(i + " : pageGenerated");
//                            }
//                            @Override public void pageUpdated(JasperPrint jp, int i) {
//                                System.out.println(i + " : pageUpdated");
//                            }
//                        });
                        
                        JasperPrint jp = JasperFillManager.fillReport(jr, parameterMap, beanColDataSource);
                        if (view == null) {
                            view = new JRViewer300(jp);
                        } else {
                            view.loadJasperReport(jp);
                        }
                        view.setReportFilterObject(rfp, report, ReportFilterDialog.this);
                        MainFrame.reportViewerInternalFrame.addTab(view, report.getName());
//                        view.loadJasperReport(jp);
                    }
                } catch (JRException ex) {
                    Logger.getLogger(ReportFilterDialog.class.getName()).log(Level.SEVERE, null, ex);
                    MessageBox.error(ReportFilterDialog.this, ex);
                }
            }
        });
        dispose();
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cancelButton;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JButton okButton;
    private javax.swing.JPanel titlePanel;
    // End of variables declaration//GEN-END:variables
    private Report report;
    private Object filter;
    private final Map<String, Object> parameterMap;
    private JRViewer300 view;
}
