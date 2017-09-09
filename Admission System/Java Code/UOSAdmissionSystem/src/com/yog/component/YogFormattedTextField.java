/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.yog.component;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import javax.swing.FocusManager;
import javax.swing.JFormattedTextField;

/**
 *
 * @author Raja Kumar & Jay Kumar
 */
public class YogFormattedTextField extends JFormattedTextField{
    private String placeHolder;
    
    public YogFormattedTextField(String placeHolder){
        this.placeHolder = placeHolder;
    }
    
    @Override
    protected void paintComponent(java.awt.Graphics g) {
        super.paintComponent(g);

        if(getText().isEmpty() && ! (FocusManager.getCurrentKeyboardFocusManager().getFocusOwner() == this)){
            Graphics2D g2 = (Graphics2D)g.create();
            g2.setBackground(Color.gray);
            g2.setFont(getFont().deriveFont(Font.ITALIC));
            g2.drawString(placeHolder, 0, 0); //figure out x, y from font's FontMetrics and size of component.
            g2.dispose();
        }
    }
}
