package admission.reports;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.beans.PropertyVetoException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;
import admission.utils.MessageBox;
import admission.utils.Utility;

/**
 *
 * @author Yougeshwar
 */
public class ReportViewerInternalFrame extends javax.swing.JInternalFrame {

    public ReportViewerInternalFrame() {
        initComponents();

        final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        screenSize.setSize(1060, screenSize.height - 100);
        setSize(screenSize);

        admission.utils.Utility.hideOnEscape(this);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        reportsTabbedPane = new javax.swing.JTabbedPane();

        setClosable(true);
        setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);

        reportsTabbedPane.setTabLayoutPolicy(javax.swing.JTabbedPane.SCROLL_TAB_LAYOUT);
        reportsTabbedPane.setFocusable(false);
        getContentPane().add(reportsTabbedPane, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public void addTab(JPanel panel, String title) {
        if(!isVisible()) {
            reportsTabbedPane.removeAll();
        }
        reportsTabbedPane.add(panel);
        reportsTabbedPane.setTabComponentAt(reportsTabbedPane.indexOfComponent(panel), Utility.getTitlePanel(reportsTabbedPane, panel, Utility.getTabTitle2(title)));
        reportsTabbedPane.setSelectedComponent(panel);
        
        setVisible(true);
    }

    @Override
    public void setVisible(boolean aFlag) {
        if (aFlag) {
            try {
                setIcon(false);
                setSelected(true);
            } catch (PropertyVetoException ex) {
                MessageBox.error(this, ex);
                Logger.getLogger(ReportViewerInternalFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        super.setVisible(aFlag);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTabbedPane reportsTabbedPane;
    // End of variables declaration//GEN-END:variables
}
