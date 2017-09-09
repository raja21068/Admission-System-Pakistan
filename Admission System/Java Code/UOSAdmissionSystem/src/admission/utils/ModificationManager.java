package admission.utils;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.JLabel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 *
 * @author Yougeshwar
 */
public class ModificationManager implements DocumentListener, ChangeListener, PropertyChangeListener, ActionListener{
    private boolean modify;
    private JLabel modifyLabel;
    
    @Override
    public void insertUpdate(DocumentEvent e) {
        setModify(true);
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        setModify(true);
    }

    @Override
    public void changedUpdate(DocumentEvent e) {
        setModify(true);
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        setModify(true);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        setModify(true);
    }

    public boolean isModify() {
        return modify;
    }

    public void setModify(boolean modify) {
        this.modify = modify;
        if (modifyLabel != null) this.modifyLabel.setEnabled(modify); 
    }

    public void setModifyLabel(JLabel modifyLabel) {
        this.modifyLabel = modifyLabel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        setModify(true);
    }
}
