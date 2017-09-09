package admission.view.admission;

import admission.controller.DatabaseManager;
import admission.model.Campus;
import admission.model.Candidate;
import admission.model.CandidateProgramOfStudy;
import admission.model.ProgramType;
import admission.model.Shift;
import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import admission.utils.RoundedBorder;

/**
 *
 * @author Raja Kumar & Jay Kumar
 */
public class DisciplineChoicesDialog extends javax.swing.JDialog {

    public DisciplineChoicesDialog(AdmissionFormPanel parent) {
        super(JOptionPane.getFrameForComponent(parent), true);
        this.admissionFormPanel = parent;
        
        initComponents();
        
        this.titlePanel.setBorder(RoundedBorder.createGradientBorder());
        
        this.setLocationRelativeTo(null);
        admission.utils.Utility.hideOnEscape(this);
        getShift();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        titlePanel = new javax.swing.JPanel();
        jLabel38 = new javax.swing.JLabel();
        tabbedPane = new javax.swing.JTabbedPane();
        jSeparator1 = new javax.swing.JSeparator();
        backButton = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Discipline Choices");

        titlePanel.setLayout(new java.awt.BorderLayout());

        jLabel38.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel38.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Gnome-Format-Text-Direction-Rtl-40.png"))); // NOI18N
        jLabel38.setText("Discipline Choices");
        jLabel38.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, java.awt.Color.darkGray), javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5)));
        titlePanel.add(jLabel38, java.awt.BorderLayout.CENTER);

        tabbedPane.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        backButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Gnome-Go-Home-32.png"))); // NOI18N
        backButton.setToolTipText("Hide Form");
        backButton.setFocusPainted(false);
        backButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backButtonActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel1.setText("<html>Numpad '+' and '-' key use for up and down");
        jLabel1.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(titlePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jSeparator1)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 210, Short.MAX_VALUE)
                .addComponent(backButton)
                .addContainerGap())
            .addComponent(tabbedPane)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(titlePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tabbedPane, javax.swing.GroupLayout.DEFAULT_SIZE, 362, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(backButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void backButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backButtonActionPerformed
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_backButtonActionPerformed
    
    public void addChoicePanel(ProgramType pt, Campus campus, Candidate candidate){
        for (SessionTabbedPanel tab : sessionTabbedPanel) {
            tab.addChoicePanel(pt, campus, candidate);
        }
    }
    
    public void removeChoicePanel(String title){
        for (SessionTabbedPanel sessionTabbedPanel1 : sessionTabbedPanel) {
            sessionTabbedPanel1.removeChoicePanel(title);
        }
    }
    
    public void clearAll(){
        for (SessionTabbedPanel sessionTabbedPanel1 : sessionTabbedPanel) {
            sessionTabbedPanel1.removeAllTabs();
        }
        System.gc();
    }
    
    private void getShift(){
        shiftList = DatabaseManager.getData(Shift.class.getName(), "shiftId");
        
        sessionTabbedPanel = new SessionTabbedPanel[shiftList.size()];
        for (int i = 0; i < sessionTabbedPanel.length; i++) {
            Shift shift = shiftList.get(i);
            sessionTabbedPanel[i] = new SessionTabbedPanel(shift);
            this.tabbedPane.addTab(shift.getName(), sessionTabbedPanel[i]);
        }
    }
    
    public List<CandidateProgramOfStudy> getAppliedCampusDisciplineList(){
        List<CandidateProgramOfStudy> list = new ArrayList();
        for (SessionTabbedPanel sessionTabbedPanel1 : sessionTabbedPanel) {
            List<CandidateProgramOfStudy> acList = sessionTabbedPanel1.getChoiceList();
            for (CandidateProgramOfStudy cmpos : acList) {
                list.add(cmpos);
            }
        }
        return list;
    }
    
    private class SessionTabbedPanel extends JPanel{
        private JTabbedPane tabbedPane;
        private Shift shift;
        private List<ChoicePanel> choicePanelList;
        
        SessionTabbedPanel(Shift shift){
            this.shift = shift;
            tabbedPane = new JTabbedPane();
            choicePanelList = new ArrayList<>();
            setLayout(new BorderLayout());
            add(this.tabbedPane, java.awt.BorderLayout.CENTER);
        }
        
        public void addChoicePanel(ProgramType pt, Campus campus, Candidate candidate){
            ChoicePanel cp = new ChoicePanel(pt, campus, shift, candidate);
            choicePanelList.add(cp);
            this.tabbedPane.addTab(campus.getLocation(), cp);
        }
        
        public void removeChoicePanel(String title){
            for (int i = 0; i < tabbedPane.getTabCount(); i++) {
                if(tabbedPane.getTitleAt(i).equals(title)){
                    tabbedPane.remove(i);
                    choicePanelList.remove(i);
                    break;
                }
            }
        }
        
        public void removeAllTabs(){
            tabbedPane.removeAll();
            choicePanelList.clear();
        }
        
        public List<CandidateProgramOfStudy> getChoiceList(){
            List<CandidateProgramOfStudy> list = new ArrayList();
            for (ChoicePanel choicePanel : choicePanelList) {
                List<CandidateProgramOfStudy> acList = choicePanel.getAppliedProgramOfStudyList();
                for (CandidateProgramOfStudy cmpos : acList) {
                    list.add(cmpos);
                }
            }
            return list;
        }
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton backButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JSeparator jSeparator1;
    protected javax.swing.JTabbedPane tabbedPane;
    private javax.swing.JPanel titlePanel;
    // End of variables declaration//GEN-END:variables
    private AdmissionFormPanel admissionFormPanel;
    private List<Shift> shiftList;
    private SessionTabbedPanel sessionTabbedPanel[];
}
