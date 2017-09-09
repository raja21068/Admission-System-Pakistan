package admission.view.admission;

/**
 *
 * @author Yougeshwar
 */
public class AdmissionListDialog extends javax.swing.JDialog {

    public AdmissionListDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        
        setSize(610, 450);
        setLocationRelativeTo(null);
        
        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent e) {
                System.exit(0);
            }
        });

        admissionListPanel = new AdmissionListPanel();
        this.getContentPane().add(admissionListPanel);
    }

    @Override
    public void setVisible(boolean aFlag) {
        admissionListPanel.setVisible(aFlag);
        super.setVisible(aFlag);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Admission List");
        setPreferredSize(new java.awt.Dimension(591, 388));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
    private final AdmissionListPanel admissionListPanel;
}
