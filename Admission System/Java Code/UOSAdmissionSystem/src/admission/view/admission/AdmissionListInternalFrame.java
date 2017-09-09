package admission.view.admission;

import admission.model.security.Resources;

/**
 *
 * @author Raja Kumar & Jay Kumar
 */
public class AdmissionListInternalFrame extends javax.swing.JInternalFrame {

    public AdmissionListInternalFrame() {
        initComponents();
        
        admission.utils.Utility.hideOnEscape(this);
        setSize(605, 470);
        
        admissionListPanel = new AdmissionListPanel();
        admissionListPanel.setInternalFrame(this);
        this.getContentPane().add(admissionListPanel);
    }

    @Override
    public void setVisible(boolean aFlag) {
        if(aFlag)
            admissionListPanel.setVisible(aFlag);
        super.setVisible(aFlag);
    }
    
    public void setPrivileges(Resources privileges){
        admissionListPanel.setPrivileges(privileges);
    }
    
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setClosable(true);
        setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);
        setTitle("Admission List");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
    private final AdmissionListPanel admissionListPanel;
}
