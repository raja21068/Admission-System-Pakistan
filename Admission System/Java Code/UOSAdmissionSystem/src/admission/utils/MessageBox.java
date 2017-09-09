package admission.utils;

import javax.swing.JOptionPane;

/**
 *
 * @author Yougeshwar
 */
public class MessageBox {
    public static void error(java.awt.Component c, Object ob) {
        JOptionPane.showMessageDialog(c, ob, "Error", JOptionPane.ERROR_MESSAGE, Icon.getError());
    }
    public static void info(java.awt.Component c, Object ob) {
        JOptionPane.showMessageDialog(c, ob, "Information", JOptionPane.INFORMATION_MESSAGE, Icon.getInfo());
    }
    public static void lock(java.awt.Component c, Object ob) {
        JOptionPane.showMessageDialog(c, ob, "Access Denied", JOptionPane.INFORMATION_MESSAGE, Icon.getLock());
    }
    public static void info(java.awt.Component c, Object ob, String title) {
        JOptionPane.showMessageDialog(c, ob, title, JOptionPane.INFORMATION_MESSAGE, Icon.getInfo());
    }
    public static void warning(java.awt.Component c, Object ob) {
        JOptionPane.showMessageDialog(c, ob, "Warning", JOptionPane.WARNING_MESSAGE, Icon.getWarning());
    }
    public static int confirm3(java.awt.Component c, Object ob) {
        return JOptionPane.showConfirmDialog(c, ob, "Question", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, Icon.getQueston());
    }
    public static int confirm2(java.awt.Component c, Object ob) {
        return JOptionPane.showConfirmDialog(c, ob, "Question", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, Icon.getQueston());
    }
    
    public static String input(java.awt.Component c, Object ob) {
        return JOptionPane.showInputDialog(c, ob);
    }
    
}
