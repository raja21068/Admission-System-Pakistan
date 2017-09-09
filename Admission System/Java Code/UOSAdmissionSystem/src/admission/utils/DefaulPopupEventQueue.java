package admission.utils;

import java.awt.AWTEvent;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import javax.swing.AbstractAction;
import static javax.swing.Action.ACCELERATOR_KEY;
import javax.swing.ImageIcon;
import javax.swing.JPopupMenu;
import javax.swing.MenuSelectionManager;
import javax.swing.SwingUtilities;
import javax.swing.text.JTextComponent;

/**
 *
 * @author Raja Kumar & Jay Kumar
 */
public class DefaulPopupEventQueue extends EventQueue {

    @Override
    protected void dispatchEvent(AWTEvent event) {
        super.dispatchEvent(event); 
        
        if (!(event instanceof MouseEvent)) {
            return;
        }

        MouseEvent me = (MouseEvent) event;

        // interested only in popuptriggers 
        if (!me.isPopupTrigger()) {
            return;
        }

        // me.getComponent(...) retunrs the heavy weight component on which event occured 
        Component comp = SwingUtilities.getDeepestComponentAt(me.getComponent(), me.getX(), me.getY());

        // interested only in textcomponents 
        if (!(comp instanceof JTextComponent)) {
            return;
        }

        // no popup shown by user code 
        if (MenuSelectionManager.defaultManager().getSelectedPath().length > 0) {
            return;
        }

        // create popup menu and show 
        JTextComponent tc = (JTextComponent) comp;
        JPopupMenu menu = new JPopupMenu();
        menu.add(new CutAction(tc)); 
        menu.add(new CopyAction(tc)); 
        menu.add(new PasteAction(tc)); 
        menu.add(new DeleteAction(tc)); 
        menu.addSeparator(); 
        menu.add(new SelectAllAction(tc)); 

        Point pt = SwingUtilities.convertPoint(me.getComponent(), me.getPoint(), tc);
        menu.show(tc, pt.x, pt.y);
    }

    private class CutAction extends AbstractAction {

        JTextComponent comp;

        public CutAction(JTextComponent comp) {
            super("Cut", new ImageIcon(CutAction.class.getResource("/images/Gnome-Edit-Cut-24.png")));
            this.comp = comp;
            putValue(ACCELERATOR_KEY, javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_X, java.awt.event.InputEvent.CTRL_MASK));
            
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            comp.cut();
        }

        @Override
        public boolean isEnabled() {
            return comp.isEditable()
                    && comp.isEnabled()
                    && comp.getSelectedText() != null;
        }
    }

    private class PasteAction extends AbstractAction {

        JTextComponent comp;

        public PasteAction(JTextComponent comp) {
            super("Paste", new ImageIcon(CutAction.class.getResource("/images/Gnome-Edit-Paste-24.png")));
            this.comp = comp;
            putValue(ACCELERATOR_KEY, javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_V, java.awt.event.InputEvent.CTRL_MASK));
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            comp.paste();
        }

        @Override
        public boolean isEnabled() {
            if (comp.isEditable() && comp.isEnabled()) {
                Transferable contents = Toolkit.getDefaultToolkit().getSystemClipboard().getContents(this);
                return contents.isDataFlavorSupported(DataFlavor.stringFlavor);
            } else {
                return false;
            }
        }
    }

    private class DeleteAction extends AbstractAction {

        JTextComponent comp;

        public DeleteAction(JTextComponent comp) {
            super("Delete", new ImageIcon(CutAction.class.getResource("/images/Gnome-Edit-Delete-32.png")));
            this.comp = comp;
            putValue(ACCELERATOR_KEY, javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_DELETE, 0));
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            comp.replaceSelection(null);
        }

        @Override
        public boolean isEnabled() {
            return comp.isEditable()
                    && comp.isEnabled()
                    && comp.getSelectedText() != null;
        }
    }

    private class CopyAction extends AbstractAction {

        JTextComponent comp;

        public CopyAction(JTextComponent comp) {
            super("Copy", new ImageIcon(CutAction.class.getResource("/images/Gnome-Edit-Copy-32.png")));
            this.comp = comp;
            putValue(ACCELERATOR_KEY, javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.CTRL_MASK));
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            comp.copy();
        }

        @Override
        public boolean isEnabled() {
            return comp.isEnabled()
                    && comp.getSelectedText() != null;
        }
    }

    private class SelectAllAction extends AbstractAction {

        JTextComponent comp;

        public SelectAllAction(JTextComponent comp) {
            super("Select All", new ImageIcon(CutAction.class.getResource("/images/Gnome-Edit-Select-All-24.png")));
            this.comp = comp;
            putValue(ACCELERATOR_KEY, javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_A, java.awt.event.InputEvent.CTRL_MASK));
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            comp.selectAll();
        }

        @Override
        public boolean isEnabled() {
            return comp.isEnabled()
                    && comp.getText().length() > 0;
        }
    }
}
