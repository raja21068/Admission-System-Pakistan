package admission.utils;

/**
 *
 * @author Yougeshwar
 */
public class Icon {
    public static javax.swing.ImageIcon getInfo() {
        return new javax.swing.ImageIcon(Icon.class.getClass().getResource("/images/Info.png"));
    }
    public static javax.swing.ImageIcon getError() {
        return new javax.swing.ImageIcon(Icon.class.getClass().getResource("/images/Error.png"));
    }
    public static javax.swing.ImageIcon getQueston() {
        return new javax.swing.ImageIcon(Icon.class.getClass().getResource("/images/Question.png"));
    }
    public static javax.swing.ImageIcon getWarning() {
        return new javax.swing.ImageIcon(Icon.class.getClass().getResource("/images/Warning.png"));
    }
    public static javax.swing.ImageIcon getLock() {
        return new javax.swing.ImageIcon(Icon.class.getClass().getResource("/images/Lock.png"));
    }
}
