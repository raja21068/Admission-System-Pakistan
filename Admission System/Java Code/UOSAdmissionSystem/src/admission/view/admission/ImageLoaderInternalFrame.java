package admission.view.admission;

import admission.controller.DatabaseManager;
import admission.controller.JDBCDatabaseManager;
import admission.model.AdmissionYear;
import admission.model.ProgramType;
import admission.model.security.Resources;
import admission.reports.BusyIndicator;
import admission.utils.ExtensionsFilter;
import admission.utils.RoundedBorder;
import java.awt.Cursor;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;

/**
 *
 * @author Raja Kumar & Jay Kumar
 */
public class ImageLoaderInternalFrame extends javax.swing.JInternalFrame {

    public ImageLoaderInternalFrame() {
        initComponents();
        
        this.titlePanel.setBorder(RoundedBorder.createGradientBorder());
        
        
        admission.utils.Utility.hideOnEscape(this);
    }

    @Override
    public void setVisible(boolean aFlag) {
        if(aFlag) {
            this.getProgramType();
            this.getAdmissionYear();
        }
        super.setVisible(aFlag);
    }
    
    public void setPrivileges(Resources privileges){
        this.privileges = privileges;
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        fileChooser = new javax.swing.JFileChooser();
        importButton = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        programTypeComboBox = new javax.swing.JComboBox();
        jLabel11 = new javax.swing.JLabel();
        admissionYearComboBox = new javax.swing.JComboBox();
        backButton = new javax.swing.JButton();
        progressBar = new javax.swing.JProgressBar();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        pathTextField = new javax.swing.JTextField();
        browseButton = new javax.swing.JButton();
        titlePanel = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();

        ExtensionsFilter ef = new ExtensionsFilter("Image files", "png", "gif", "jpg", "jpeg", "bmp");
        fileChooser.setFileSelectionMode(javax.swing.JFileChooser.DIRECTORIES_ONLY);
        fileChooser.setName(""); // NOI18N

        setClosable(true);
        setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);
        setTitle("Image Loader");

        importButton.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        importButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Gnome-Emblem-Downloads-32.png"))); // NOI18N
        importButton.setText("Import");
        importButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                importButtonActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel10.setText("Program Type:");

        programTypeComboBox.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        jLabel11.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel11.setText("Admission Year:");

        admissionYearComboBox.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        backButton.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        backButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Gnome-Go-Home-32.png"))); // NOI18N
        backButton.setToolTipText("Back");
        backButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backButtonActionPerformed(evt);
            }
        });

        progressBar.setStringPainted(true);

        jLabel3.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel3.setText("Progress:");

        jLabel2.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel2.setText("Path:");

        pathTextField.setEditable(false);

        browseButton.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        browseButton.setText("Browse");
        browseButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                browseButtonActionPerformed(evt);
            }
        });

        titlePanel.setLayout(new java.awt.BorderLayout());

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/admission/view/images/photo_booth.png"))); // NOI18N
        jLabel4.setText("Image Loader");
        jLabel4.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, java.awt.Color.darkGray), javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5)));
        titlePanel.add(jLabel4, java.awt.BorderLayout.CENTER);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(progressBar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(admissionYearComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(programTypeComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(importButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(backButton))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(pathTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 307, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(browseButton))
                            .addComponent(jLabel3))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addComponent(titlePanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(titlePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(programTypeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(admissionYearComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(pathTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(browseButton, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addGap(5, 5, 5)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(progressBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(backButton))
                    .addComponent(importButton))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {admissionYearComboBox, browseButton, pathTextField, programTypeComboBox});

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void importButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_importButtonActionPerformed
        new Thread() {
            @Override public void run() {
                importImages();
            }
        }.start();
    }//GEN-LAST:event_importButtonActionPerformed

    private void backButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backButtonActionPerformed
        // TODO add your handling code here:
        this.setVisible(false);
    }//GEN-LAST:event_backButtonActionPerformed

    private void browseButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_browseButtonActionPerformed
        // TODO add your handling code here:
        if(fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            File folder = fileChooser.getSelectedFile();
            pathTextField.setText(folder.getAbsolutePath());
        }
    }//GEN-LAST:event_browseButtonActionPerformed

    private void getProgramType(){
        this.programTypeComboBox.removeAllItems();
        List<ProgramType> list = DatabaseManager.getData(ProgramType.class.getName(), "name");
        
        for (int i = 0; i < list.size(); i++) {
            this.programTypeComboBox.addItem(list.get(i));
        }
    }
    
    private void getAdmissionYear(){
        this.admissionYearComboBox.removeAllItems();
        List<AdmissionYear> list = DatabaseManager.getData(AdmissionYear.class.getName(), "year DESC");
        
        for (int i = 0; i < list.size(); i++) {
            this.admissionYearComboBox.addItem(list.get(i));
        }
    }
    private void importImages() {
        final ProgramType pt = (ProgramType) this.programTypeComboBox.getSelectedItem();
        final AdmissionYear ay = (AdmissionYear) this.admissionYearComboBox.getSelectedItem();
        if(pt == null || ay == null) return;
                    
        final File folder = fileChooser.getSelectedFile();
        if(!folder.exists()) return;
        
        ImageLoaderInternalFrame.this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        importButton.setEnabled(false);
        backButton.setEnabled(false);
                
        try {
            PrintStream out = new PrintStream(new FileOutputStream(new File("SEAT_NUMBER_IMAGE_NOT_FOUND.CSV")));
            
            List<Object[]> data = JDBCDatabaseManager.getSelectedCandidates(ay.getAdmissionYearId(), pt.getProgramTypeId(), true);
                    
            File[] listFiles = folder.listFiles(new ExtensionsFilter("Image files", "png", "gif", "jpg", "jpeg", "bmp"));
            progressBar.setValue(0);
            progressBar.setMaximum(data.size() - 1);
            int count = 0;
            
            for (int i = 0; i < data.size(); i++) {
                
                int seatNo = (int) data.get(i)[0];
                
                File file = getFile(listFiles, seatNo);
                
                if(file == null) {
                    out.println(seatNo);
                    out.flush();
                    continue;
                }
                
                int candidateId = JDBCDatabaseManager.getCandidateId(ay.getAdmissionYearId(), pt.getProgramTypeId(), seatNo);
                
                String[] exts = file.getName().split("\\.");
                try{
                    BufferedImage image = ImageIO.read(file);
                    ImageIO.write(image, exts[1], new File("photos/" + candidateId + "." + exts[1]));
                    JDBCDatabaseManager.updateCandidateImagePath(ay.getAdmissionYearId(), pt.getProgramTypeId(), seatNo, candidateId + "." + exts[1]);
                    count++;
                } catch (IOException ex) {
                    Logger.getLogger(ImageLoaderInternalFrame.class.getName()).log(Level.SEVERE, null, ex);
                    continue;
                }
                progressBar.setValue(i + 1);
            }
            out.close();
            admission.utils.MessageBox.info(this, count + " images loaded successfully");
        } catch (SQLException | FileNotFoundException ex ) {
            Logger.getLogger(ImageLoaderInternalFrame.class.getName()).log(Level.SEVERE, null, ex);
            admission.utils.MessageBox.info(ImageLoaderInternalFrame.this, ex);
        }
        ImageLoaderInternalFrame.this.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        importButton.setEnabled(true);
        backButton.setEnabled(true);
    }

    private File getFile(File[] list, int seatNo) {
        for (File file : list) { 
            String[] split = file.getName().split("\\.");
//            System.out.println(split.length);
        
            String name = split[0];
            
            if(name.equals(String.valueOf(seatNo)))
                return file;
        }
        return null;
    }
    
    private String[] getName(File file) {
        return file.getName().split("\\.");
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox admissionYearComboBox;
    private javax.swing.JButton backButton;
    private javax.swing.JButton browseButton;
    private javax.swing.JFileChooser fileChooser;
    private javax.swing.JButton importButton;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JTextField pathTextField;
    private javax.swing.JComboBox programTypeComboBox;
    private javax.swing.JProgressBar progressBar;
    private javax.swing.JPanel titlePanel;
    // End of variables declaration//GEN-END:variables
    private Resources privileges;
}
