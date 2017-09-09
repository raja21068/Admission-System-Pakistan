package admission.reports;

import admission.controller.DatabaseManager;
import admission.helpers.CommonHelper;
import admission.model.security.Resources;
import admission.services.KeyConstant;
import admission.services.Session;
import admission.services.SessionService;
import admission.xmlparser.Report;
import admission.xmlparser.ReportCategory;
import admission.xmlparser.ReportXMLParser;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.KeyEvent;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListSelectionModel;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JList;
import admission.utils.MessageBox;
import admission.utils.RoundedBorder;
import admission.utils.Sorter;

/**
 *
 * @author Yougeshwar
 */
public class AllReportsDialog extends javax.swing.JDialog {

    public AllReportsDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

        this.setLocationRelativeTo(null);
        this.titlePanel.setBorder(RoundedBorder.createGradientBorder());
        admission.utils.Utility.hideOnEscape(this);

        reportFilterDialog = new ReportFilterDialog(this, true);

        categoriesList.setCellRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                label.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Gnome-Emblem-Package-32.png")));
                return label;
            }
        });
        reportsList.setSelectionModel(new DisabledItemSelectionModel());
        reportsList.setCellRenderer(new DisabledItemListCellRenderer());
    }

    @Override
    public void setVisible(boolean b) {
        if (b) {
            descriptionLabel.setText("");
            getReportCategory();
        }
        super.setVisible(b);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        titlePanel = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        categoriesList = new javax.swing.JList();
        jScrollPane2 = new javax.swing.JScrollPane();
        reportsList = new javax.swing.JList();
        jSeparator1 = new javax.swing.JSeparator();
        cancelButton = new javax.swing.JButton();
        okButton = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        reportJXSearchField = new org.jdesktop.swingx.JXSearchField();
        categoryJXSearchField = new org.jdesktop.swingx.JXSearchField();
        jSeparator2 = new javax.swing.JSeparator();
        descriptionLabel = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("All Reports");
        setResizable(false);

        titlePanel.setLayout(new java.awt.BorderLayout());

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/report-icon2.png"))); // NOI18N
        jLabel2.setText("All Reports");
        jLabel2.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, java.awt.Color.darkGray), javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5)));
        titlePanel.add(jLabel2, java.awt.BorderLayout.CENTER);

        jLabel1.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel1.setText("Categories");

        categoriesList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        categoriesList.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                categoriesListValueChanged(evt);
            }
        });
        jScrollPane1.setViewportView(categoriesList);

        reportsList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        reportsList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                reportsListMouseClicked(evt);
            }
        });
        reportsList.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                reportsListKeyPressed(evt);
            }
        });
        reportsList.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                reportsListValueChanged(evt);
            }
        });
        jScrollPane2.setViewportView(reportsList);

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

        jLabel3.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel3.setText("Reports");

        reportJXSearchField.setInstantSearchDelay(0);
        reportJXSearchField.setPreferredSize(new java.awt.Dimension(75, 25));
        reportJXSearchField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                reportJXSearchFieldActionPerformed(evt);
            }
        });

        categoryJXSearchField.setInstantSearchDelay(0);
        categoryJXSearchField.setPreferredSize(new java.awt.Dimension(75, 25));
        categoryJXSearchField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                categoryJXSearchFieldActionPerformed(evt);
            }
        });

        jSeparator2.setOrientation(javax.swing.SwingConstants.VERTICAL);

        descriptionLabel.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        descriptionLabel.setForeground(new java.awt.Color(0, 51, 102));
        descriptionLabel.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        jLabel5.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel5.setText("Description");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(titlePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jSeparator1)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(jLabel1)
                            .addComponent(categoryJXSearchField, javax.swing.GroupLayout.DEFAULT_SIZE, 214, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel3)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 294, Short.MAX_VALUE)
                            .addComponent(reportJXSearchField, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(descriptionLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addGap(0, 160, Short.MAX_VALUE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(okButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cancelButton)))
                .addContainerGap())
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {cancelButton, okButton});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(titlePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel3)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(reportJXSearchField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(categoryJXSearchField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 312, Short.MAX_VALUE)
                            .addComponent(jScrollPane1)))
                    .addComponent(jSeparator2)
                    .addComponent(descriptionLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cancelButton)
                    .addComponent(okButton))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelButtonActionPerformed
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_cancelButtonActionPerformed

    private void categoriesListValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_categoriesListValueChanged
        // TODO add your handling code here:
        getReports();
    }//GEN-LAST:event_categoriesListValueChanged

    private void categoryJXSearchFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_categoryJXSearchFieldActionPerformed
        // TODO add your handling code here:
        String text = categoryJXSearchField.getText();
        admission.utils.Utility.filterDataList(categoriesList, reportCategoryDataList, text);
    }//GEN-LAST:event_categoryJXSearchFieldActionPerformed

    private void reportJXSearchFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_reportJXSearchFieldActionPerformed
        // TODO add your handling code here:
        String text = reportJXSearchField.getText();
        admission.utils.Utility.filterDataList(reportsList, reportDataList, text);
    }//GEN-LAST:event_reportJXSearchFieldActionPerformed

    private void reportsListValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_reportsListValueChanged
        // TODO add your handling code here:
        Report report = (Report) reportsList.getSelectedValue();
        if (report == null) {
            return;
        }

        descriptionLabel.setText(report.getDescription());
    }//GEN-LAST:event_reportsListValueChanged

    private void okButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_okButtonActionPerformed
        // TODO add your handling code here:
        openFilter();
    }//GEN-LAST:event_okButtonActionPerformed

    private void reportsListMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_reportsListMouseClicked
        // TODO add your handling code here:
        if (evt.getClickCount() == 2) {
            openFilter();
        }
    }//GEN-LAST:event_reportsListMouseClicked

    private void reportsListKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_reportsListKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            openFilter();
        }
    }//GEN-LAST:event_reportsListKeyPressed

    private void getReportCategory() {
        reportCategoryDataList = ReportXMLParser.getReportCategoryList();
        Sorter.listSort(reportCategoryDataList);
        categoriesList.setListData(reportCategoryDataList.toArray());
    }

    private void getReports() {

        reportDataList.clear();
        reportsList.setListData(reportDataList.toArray());

        if (categoriesList.getSelectedValue() instanceof String) {
            for (int i = 1; i < reportCategoryDataList.size(); i++) {
                ReportCategory rc = reportCategoryDataList.get(i);
                List<Report> reportList = rc.getReportList();
                for (Report report : reportList) {
                    if (CommonHelper.checkUserResourceAccess(report.getSecurityContext())) {
                        report.setAccessible(true);
                    }
                    reportDataList.add(report);
                }
            }
        } else {
            ReportCategory rc = (ReportCategory) categoriesList.getSelectedValue();
            if (rc == null) {
                return;
            }
            List<Report> reportList = rc.getReportList();
            for (Report report : reportList) {
                if (CommonHelper.checkUserResourceAccess(report.getSecurityContext())) {
                    report.setAccessible(true);
                }
                reportDataList.add(report);
            }
        }
        Sorter.listSort(reportDataList);
        reportsList.setListData(reportDataList.toArray());
    }

    private void openFilter() {
        Report report = (Report) reportsList.getSelectedValue();
        if (report == null) {
            return;
        }

        dispose();

        reportFilterDialog.setReport(report);
        reportFilterDialog.showFilter();
    }

    private class DisabledItemListCellRenderer extends DefaultListCellRenderer {

        @Override
        public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
            JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
            label.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Gnome-X-Office-Spreadsheet-32.png")));

            Report report = (Report) reportsList.getModel().getElementAt(index);
            if (report == null) {
                return label;
            }

            label.setEnabled(report.isAccessible());
            return label;
        }
    }

    private class DisabledItemSelectionModel extends DefaultListSelectionModel {

        @Override
        public void setSelectionInterval(int index0, int index1) {
            Report report = (Report) reportsList.getModel().getElementAt(index0);
            if (report == null) {
                return;
            }

            if (report.isAccessible()) {
                super.setSelectionInterval(index0, index0);
            } else {
                /**
                 * The previously selected index is before this one, so walk
                 * forward to find the next selectable item.
                 */
                if (getAnchorSelectionIndex() < index0) {
                    for (int i = index0; i < reportsList.getModel().getSize(); i++) {
                        Report report1 = (Report) reportsList.getModel().getElementAt(i);
                        if (report1.isAccessible()) {
                            super.setSelectionInterval(i, i);
                            return;
                        }
                    }
                } /**
                 * Otherwise, walk backward to find the next selectable item.
                 */
                else {
                    for (int i = index0; i >= 0; i--) {
                        Report report1 = (Report) reportsList.getModel().getElementAt(i);
                        if (report1.isAccessible()) {
                            super.setSelectionInterval(i, i);
                            return;
                        }
                    }
                }
            }
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cancelButton;
    private javax.swing.JList categoriesList;
    private org.jdesktop.swingx.JXSearchField categoryJXSearchField;
    private javax.swing.JLabel descriptionLabel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JButton okButton;
    private org.jdesktop.swingx.JXSearchField reportJXSearchField;
    private javax.swing.JList reportsList;
    private javax.swing.JPanel titlePanel;
    // End of variables declaration//GEN-END:variables
    private List<ReportCategory> reportCategoryDataList;
    private List<Report> reportDataList = new ArrayList<>();
    private ReportFilterDialog reportFilterDialog;
}
