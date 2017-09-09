package com.yog.component;

import java.awt.Font;
import javax.swing.JCheckBox;

/**
 *
 * @author Yougeshwar
 */
public class JCustomCheckBox extends JCheckBox {

    private Object value = null;
    private boolean red = false;

    public JCustomCheckBox(Object value, boolean selected) {
        super(value == null ? "" : "" + value, selected);
        this.value = value;
        this.setFont(new Font("Arial", Font.PLAIN, 12));
    }

    @Override
    public boolean isSelected() {
        return super.isSelected();
    }

    @Override
    public void setSelected(boolean selected) {
        super.setSelected(selected);
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public boolean isRed() {
        return red;
    }

    public void setRed(boolean red) {
        this.red = red;
    }

    @Override
    public String toString() {
        return this.getValue().toString();
    }
}
