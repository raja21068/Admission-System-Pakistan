package com.yog.component;

import javax.swing.JComponent;
import javax.swing.JLabel;

/**
 *
 * @author Yougeshwar
 */
public abstract class OperationButtons extends javax.swing.JPanel {

//    public abstract void addOperation(java.awt.event.ActionEvent evt);
    public abstract void newOperation(java.awt.event.ActionEvent evt);
    public abstract void saveOperation(java.awt.event.ActionEvent evt);
    public abstract void deleteOperation(java.awt.event.ActionEvent evt);
    public abstract void backOperation(java.awt.event.ActionEvent evt);
    
    public OperationButtons() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        modifyLabel = new javax.swing.JLabel();
        newButton = new javax.swing.JButton();
        saveButton = new javax.swing.JButton();
        deleteButton = new javax.swing.JButton();
        backButton = new javax.swing.JButton();

        setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT, 5, 0));

        modifyLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        modifyLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/modify.png"))); // NOI18N
        modifyLabel.setEnabled(false);
        modifyLabel.setPreferredSize(new java.awt.Dimension(50, 33));
        add(modifyLabel);

        newButton.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        newButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Gnome-Document-New-32.png"))); // NOI18N
        newButton.setToolTipText("New");
        newButton.setFocusPainted(false);
        newButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newButtonActionPerformed(evt);
            }
        });
        add(newButton);

        saveButton.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        saveButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Gnome-Media-Floppy-32.png"))); // NOI18N
        saveButton.setToolTipText("Save");
        saveButton.setFocusPainted(false);
        saveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveButtonActionPerformed(evt);
            }
        });
        add(saveButton);

        deleteButton.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        deleteButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Gnome-Edit-Delete-32.png"))); // NOI18N
        deleteButton.setToolTipText("Delete");
        deleteButton.setFocusPainted(false);
        deleteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteButtonActionPerformed(evt);
            }
        });
        add(deleteButton);

        backButton.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        backButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Gnome-Go-Home-32.png"))); // NOI18N
        backButton.setToolTipText("Hide Form");
        backButton.setFocusPainted(false);
        backButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backButtonActionPerformed(evt);
            }
        });
        add(backButton);
    }// </editor-fold>//GEN-END:initComponents

    private void saveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveButtonActionPerformed
        // TODO add your handling code here:
        this.saveOperation(evt);
    }//GEN-LAST:event_saveButtonActionPerformed

    private void deleteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteButtonActionPerformed
        // TODO add your handling code here:
        this.deleteOperation(evt);
    }//GEN-LAST:event_deleteButtonActionPerformed

    private void newButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newButtonActionPerformed
        // TODO add your handling code here:
        this.newOperation(evt);
    }//GEN-LAST:event_newButtonActionPerformed

    private void backButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backButtonActionPerformed
        // TODO add your handling code here:
        this.backOperation(evt);
    }//GEN-LAST:event_backButtonActionPerformed

    public void addComponent(JComponent com) {
        this.add(com);
    }
    
    public JLabel getModifyLabel() {
        return modifyLabel;
    }

    public void setVisible(boolean add, boolean delete) {
//        this.modifyLabel.setVisible(label);
        this.saveButton.setVisible(add);
        this.deleteButton.setVisible(delete);
//        this.newButton.setVisible(clear);
//        this.backButton.setVisible(back);
    }
    
    public void setVisible(boolean label, boolean add, boolean delete, boolean clear, boolean back) {
        this.modifyLabel.setVisible(label);
        this.saveButton.setVisible(add);
        this.deleteButton.setVisible(delete);
        this.newButton.setVisible(clear);
        this.backButton.setVisible(back);
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton backButton;
    private javax.swing.JButton deleteButton;
    private javax.swing.JLabel modifyLabel;
    private javax.swing.JButton newButton;
    private javax.swing.JButton saveButton;
    // End of variables declaration//GEN-END:variables
}
