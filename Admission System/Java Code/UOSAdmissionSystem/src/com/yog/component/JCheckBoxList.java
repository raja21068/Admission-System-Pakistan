package com.yog.component;

import java.awt.Component;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.JCheckBox;
import javax.swing.JList;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author Raja Kumar & Jay Kumar
 */

public class JCheckBoxList extends JList {
    protected Border noFocusBorder = new EmptyBorder(1, 1, 1, 1);

    public JCheckBoxList () {
        super();

        super.setModel(new DefaultListModel());
        setCellRenderer(new JYCheckboxListCellRenderer());

        addMouseListener(new MouseAdapter() {
            @Override public void mousePressed(MouseEvent e) {
                int index = locationToIndex(e.getPoint());
                if (index != -1) {
                    Object obj = getModel().getElementAt(index);
                    if (obj instanceof JCheckBox) {
                        JCheckBox checkbox = (JCheckBox) obj;
                        checkbox.setSelected(!checkbox.isSelected());
                        repaint();
                    }
                }
            }
        });
        
        addKeyListener(new KeyAdapter() {
            @Override public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() != KeyEvent.VK_SPACE) return;
                int index = JCheckBoxList.this.getSelectedIndex();
                if (index != -1) {
                    Object obj = getModel().getElementAt(index);
                    if (obj instanceof JCheckBox) {
                        JCheckBox checkbox = (JCheckBox) obj;
                        checkbox.setSelected(!checkbox.isSelected());
                        repaint();
                    }
                }
            }
        });
        
        setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }
    
    @SuppressWarnings("unchecked")
    public int[] getCheckedIndexes() {
        java.util.List list = new java.util.ArrayList();
        
        DefaultListModel dlm = (DefaultListModel) getModel();
        
        for (int i = 0; i < dlm.size(); ++i) {
            Object obj = getModel().getElementAt(i);
            if (obj instanceof JCheckBox) {
                JCheckBox checkbox = (JCheckBox) obj;
                if (checkbox.isSelected()) {
                    list.add(new Integer(i));
                }
            }
        }

        int[] indexes = new int[list.size()];

        for (int i = 0; i < list.size(); ++i) {
            indexes[i] = ((Integer) list.get(i)).intValue();
        }

        return indexes;
    }

    @SuppressWarnings("unchecked")
    public java.util.List getCheckedItems() {
        java.util.List list = new java.util.ArrayList();
        
        DefaultListModel dlm = (DefaultListModel) super.getModel();
        
        for (int i = 0; i < dlm.size(); ++i) {
            Object obj = getModel().getElementAt(i);
            if (obj instanceof JCheckBox) {
                JCheckBox checkbox = (JCheckBox) obj;
                if (checkbox.isSelected()) {
                    list.add(checkbox);
                }
            }
        }
        return list;
    }
    
    @SuppressWarnings("unchecked")
    public java.util.List getAllItems() {
        java.util.List list = new java.util.ArrayList();
        
        DefaultListModel dlm = (DefaultListModel) super.getModel();
        
        for (int i = 0; i < dlm.size(); ++i) {
            Object obj = getModel().getElementAt(i);
            if (obj instanceof JCheckBox) {
                JCheckBox checkbox = (JCheckBox) obj;
                list.add(checkbox);
            }
        }
        return list;
    }
    
    @SuppressWarnings("unchecked")
    public void clearAllSelection() {
        DefaultListModel dlm = (DefaultListModel) super.getModel();
        
        for (int i = 0; i < dlm.size(); ++i) {
            Object obj = getModel().getElementAt(i);
            if (obj instanceof JCheckBox) {
                JCheckBox checkbox = (JCheckBox) obj;
                checkbox.setSelected(false);
            }
        }
        this.repaint();
    }
    
    class JYCheckboxListCellRenderer extends DefaultListCellRenderer {

        @Override
        public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
            if (value instanceof JCheckBoxListItem) {
                JCheckBoxListItem checkbox = (JCheckBoxListItem) value;
                checkbox.setBackground(isSelected ? list.getSelectionBackground() : list.getBackground());
                setFont(list.getFont());
                checkbox.setBackground(list.getBackground());
                checkbox.setForeground(list.getForeground());
                checkbox.setEnabled(isEnabled());
                checkbox.setFont(getFont());
                checkbox.setFocusPainted(false);
                checkbox.setBorderPainted(true);
                checkbox.setBorder(isSelected ? UIManager.getBorder("List.focusCellHighlightBorder") : noFocusBorder);

                return checkbox;
            } else {
                return super.getListCellRendererComponent(list, value.getClass().getName(), index,  isSelected, cellHasFocus);
            }
        }
    }
    
    public static class JCheckBoxListItem extends JCheckBox {

        private Object value = null;

        private boolean red = false;

        public JCheckBoxListItem(Object value, boolean selected) {
            super(value == null ? "" : "" + value, selected);
            this.value = value;
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
}