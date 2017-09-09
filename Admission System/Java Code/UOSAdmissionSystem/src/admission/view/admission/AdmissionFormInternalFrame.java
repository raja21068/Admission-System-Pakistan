package admission.view.admission;

import admission.helpers.CommonHelper;
import admission.services.KeyConstant;
import admission.view.MainFrame;
import com.yog.component.OperationButtons;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.*;
import java.beans.PropertyVetoException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import admission.utils.RoundedBorder;
import admission.utils.Utility;
import static admission.utils.Utility.getTabTitle;

/**
 *
 * @author Raja Kumar & Jay Kumar
 */
public class AdmissionFormInternalFrame extends javax.swing.JInternalFrame {

    public AdmissionFormInternalFrame() {
        initComponents();
        final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        screenSize.setSize(1100, screenSize.height - 80);
        setSize(screenSize);

        this.titlePanel.setBorder(RoundedBorder.createGradientBorder());
        this.topPanel.add(this.getOperationButtons(), BorderLayout.EAST);

//        utilities.Utility.hideOnEscape(this);
        if (CommonHelper.checkUserResourceAccess(KeyConstant.SAVE_ADMISSION_FORM)) {
            InputMap inputMap = getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
            ActionMap actionMap = getRootPane().getActionMap();

            inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK), actionMap);
            actionMap.put(actionMap, new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    AdmissionFormPanel tab = (AdmissionFormPanel) formsTabbedPane.getSelectedComponent();
                    if (tab == null) {
                        return;
                    }
                    String title = tab.save();
                    if (title != null && !title.isEmpty()) {
                        //formsTabbedPane.setTabComponentAt(formsTabbedPane.indexOfComponent(tab), Utility.getTitlePanel(formsTabbedPane, tab, Utility.getTabTitle(title)));
                        setTabTitle(tab, title);
                        tab.requestFocus();
                    }
                }
            });
        }
    }

    private JPanel getOperationButtons() {
        operationButtons = new OperationButtons() {
            @Override
            public void saveOperation(ActionEvent evt) {
                AdmissionFormPanel tab = (AdmissionFormPanel) formsTabbedPane.getSelectedComponent();
                if (tab == null) {
                    return;
                }
                String title = tab.save();
                if (title != null && !title.isEmpty()) {
                    setTabTitle(tab, title);
                    tab.requestFocus();
                }
            }

            @Override
            public void deleteOperation(ActionEvent evt) {
                AdmissionFormPanel tab = (AdmissionFormPanel) formsTabbedPane.getSelectedComponent();
                if (tab == null) {
                    return;
                }
                tab.delete();
                setTabTitle(tab, "New Entry");
                tab.requestFocus();
            }

            @Override
            public void newOperation(ActionEvent evt) {
                AdmissionFormPanel tab = new AdmissionFormPanel();
                formsTabbedPane.add(tab);
                setTabTitle(tab, "New Entry");
                formsTabbedPane.setSelectedComponent(tab);
                tab.requestFocus();
            }

            @Override
            public void backOperation(ActionEvent evt) {
                AdmissionFormInternalFrame.this.setVisible(false);
            }
        };
        operationButtons.setSize(335, 60);
        operationButtons.add(reloadButton);
        operationButtons.setVisible(CommonHelper.checkUserResourceAccess(KeyConstant.SAVE_ADMISSION_FORM) || CommonHelper.checkUserResourceAccess(KeyConstant.UPDATE_ADMISSION_FORM),
                CommonHelper.checkUserResourceAccess(KeyConstant.DELETE_ADMISSION_FORM));
        return operationButtons;
    }

    @Override
    public void setVisible(boolean aFlag) {
        if (aFlag) {
            AdmissionFormPanel tab = new AdmissionFormPanel();
            formsTabbedPane.add(tab);
            formsTabbedPane.setTabComponentAt(formsTabbedPane.indexOfComponent(tab), Utility.getTitlePanel(formsTabbedPane, tab, Utility.getTabTitle("New Entry")));
        } else {
            if (formsTabbedPane != null) {
                formsTabbedPane.removeAll();
            }
            System.gc();
        }
        super.setVisible(aFlag);
    }

//    public void setPrivileges(Privileges privileges){
//        this.privileges = privileges;
//        this.addButton.setEnabled((privileges.isAddPrivilige()));
//        this.updateButton.setEnabled((privileges.isUpdatePrivilige()));
//        this.deleteButton.setEnabled((privileges.isDeletePrivilige()));
//    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        reloadButton = new javax.swing.JButton();
        topPanel = new javax.swing.JPanel();
        titlePanel = new javax.swing.JPanel();
        jLabel37 = new javax.swing.JLabel();
        formsTabbedPane = new javax.swing.JTabbedPane();

        reloadButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Gnome-View-Refresh-32.png"))); // NOI18N
        reloadButton.setToolTipText("Reload");
        reloadButton.setFocusPainted(false);
        reloadButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                reloadButtonActionPerformed(evt);
            }
        });

        setClosable(true);
        setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Admission Form");
        setToolTipText("");

        topPanel.setLayout(new java.awt.BorderLayout());

        titlePanel.setLayout(new java.awt.BorderLayout());

        jLabel37.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel37.setIcon(new javax.swing.ImageIcon(getClass().getResource("/admission/view/images/form-icon.png"))); // NOI18N
        jLabel37.setText("Admission Form");
        jLabel37.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, java.awt.Color.darkGray), javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5)));
        titlePanel.add(jLabel37, java.awt.BorderLayout.CENTER);

        topPanel.add(titlePanel, java.awt.BorderLayout.CENTER);

        getContentPane().add(topPanel, java.awt.BorderLayout.PAGE_START);

        formsTabbedPane.setTabLayoutPolicy(javax.swing.JTabbedPane.SCROLL_TAB_LAYOUT);
        formsTabbedPane.setTabPlacement(javax.swing.JTabbedPane.LEFT);
        formsTabbedPane.setFocusable(false);
        getContentPane().add(formsTabbedPane, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void reloadButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_reloadButtonActionPerformed
        // TODO add your handling code here:
        AdmissionFormPanel tab = (AdmissionFormPanel) formsTabbedPane.getSelectedComponent();
        if (tab == null) {
            return;
        }
        tab.loadCatalog();
    }//GEN-LAST:event_reloadButtonActionPerformed

    public void viewCandidate(String seatNo, int ayId, int ptId) {
        AdmissionFormPanel tab = new AdmissionFormPanel();
        String tabTitle = tab.setCandidate(seatNo, ayId, ptId);
        formsTabbedPane.add(tab);
        setTabTitle(tab, tabTitle);
        formsTabbedPane.setSelectedComponent(tab);
        try {
            if (isIcon()) {
                setIcon(false);
            }
        } catch (PropertyVetoException ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (!this.isVisible()) {
            super.setVisible(true);
        }
    }

    public void setTabTitle(JPanel panel, String title) {
        formsTabbedPane.setTabComponentAt(formsTabbedPane.indexOfComponent(panel), Utility.getTitlePanel(formsTabbedPane, panel, getTabTitle(title)));

//        formsTabbedPane.sett
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTabbedPane formsTabbedPane;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JButton reloadButton;
    private javax.swing.JPanel titlePanel;
    private javax.swing.JPanel topPanel;
    // End of variables declaration//GEN-END:variables
    private OperationButtons operationButtons;
//    private ModificationManager modify;
}
