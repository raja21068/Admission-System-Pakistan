package com.yog.component;

import java.util.List;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JComponent;

/**
 *
 * @author Raja Kumar & Jay Kumar
 */
public class ComboBoxToolTipRenderer extends DefaultListCellRenderer {
    List<String> tooltips;

    @Override
    public java.awt.Component getListCellRendererComponent(javax.swing.JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        JComponent comp = (JComponent) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

        if (index > -1 && value != null && tooltips != null) {
            list.setToolTipText(tooltips.get(index));
        }
        return comp;
    }

    public void setTooltips(List<String> tooltips) {
        this.tooltips = tooltips;
    }
}
