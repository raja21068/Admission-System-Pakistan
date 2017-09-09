package admission.utils;

import com.yog.component.TabCloseButton;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.BorderFactory;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.KeyStroke;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.plaf.ColorUIResource;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Raja Kumar & Jay Kumar
 */
public class Utility {
    
    public static long currentDateMillis() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        
        return cal.getTimeInMillis();
    }
    
    public static String dateFormat(long date) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
//        sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
        return sdf.format(new Date(date).getTime());
    }
    
    public static long dateToMillis(String date) {
        long dateLong = 0;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
//            sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
            dateLong = sdf.parse(date).getTime();
        } catch (ParseException ex) {
            Logger.getLogger(Utility.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return dateLong;
    }
    
    public static void comboBoxScroll(JComboBox box) {
        if (box == null) {
            return;
        }
        Object comp = box.getUI().getAccessibleChild(box, 0);
        if (!(comp instanceof JPopupMenu)) {
            return;
        }
        JPopupMenu popup = (JPopupMenu) comp;
        final JScrollPane scrollPane = (JScrollPane) popup.getComponent(0);
        scrollPane.setHorizontalScrollBar(new JScrollBar(JScrollBar.HORIZONTAL));
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    }

    public static void removeTableRows(DefaultTableModel model) {
        if (model == null) {
            return;
        }
        int rows = model.getRowCount();
        for (int i = rows - 1; i >= 0; i--) {
            model.removeRow(i);
        }
    }

    public static void filterDataList(JList jList, List dataList, String text) {
        jList.setListData(new Object[]{});

        List newList = new ArrayList();
        for (Object ob : dataList) {
            if (ob.toString().toLowerCase().startsWith(text.toLowerCase())) {
                newList.add(ob);
            }
        }

        jList.setListData(newList.toArray());
    }

    public static void hideOnEscape(Object ob) {
        if (ob instanceof JComponent) {
            final JComponent comp = (JComponent) ob;
            InputMap inputMap = comp.getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
            ActionMap actionMap = comp.getRootPane().getActionMap();

            inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), actionMap);
            actionMap.put(actionMap, new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    comp.setVisible(false);
                }
            });
        } else if (ob instanceof JDialog) {
            final JDialog dialog = (JDialog) ob;
            InputMap inputMap = dialog.getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
            ActionMap actionMap = dialog.getRootPane().getActionMap();

            inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), actionMap);
            actionMap.put(actionMap, new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    dialog.dispose();
                }
            });
        }

    }

    public static String getTabTitle(String s) {
        s = "<html><body width=70 bottommargin=10 topmargin=10 marginwidth=0 marginheight=5>" + s + "</body></html>";
        return s;
    }

    public static String getTabTitle2(String s) {
        s = "<html><body bottommargin=10 topmargin=10 marginwidth=0 marginheight=5>" + s + "</body></html>";
        return s;
    }

    public static JPanel getTitlePanel(final JTabbedPane tabbedPane, final JPanel panel, String title) {
        JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        titlePanel.setOpaque(false);
        JLabel titleLbl = new JLabel(title);
        titleLbl.setFont(new Font("Arial", Font.PLAIN, 12));
        titleLbl.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 5));
        titlePanel.add(titleLbl);
        JButton closeButton = new TabCloseButton();

        closeButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                tabbedPane.remove(panel);
            }
        });
        titlePanel.add(closeButton);

        return titlePanel;
    }

    public static String programFormat(boolean isBachelor, boolean isMorning, String program, String discipline, String partName) {
        String posName;//!cposg.isIsBachelor() ? (program.equals("M.LIS") || program.equals("PGD") ? "" : + (Previous)) + " " + cposg.getPosName() : cposg.toString();

        if (!isBachelor) {
            posName = 
                    (program.equals("M.B.A (HONS)")
                    ? (isMorning ? program + " " + partName + "" : program + " First Year")
                    : 
                    program.equals("M.C.S")
                    || program.equals("LL.M")
                    || //                  program.equals("LL.B") ||
                    program.equals("M.COM")
                    || //                  program.equals("B.COM") ||
                    program.equals("M.P.A")
                    ? program + " (" + partName + ")"
                    : program.equals("PGD")
                    || program.startsWith("1 Year Con")
                    ? program + " " + discipline
                    : program.equals("BPEHSS")
                    || program.equals("M.LIS")
                    || program.equals("MPEHSS")
                    ? program
                    : //                    ? discipline + " (" + program + ")" :
                    program.equals("M.Sc (HONS)") ? program + " " + discipline
                    : program + " (" + partName + ") " + discipline);

            posName += (isMorning ? "" : " (EVENING)");
        } else {
//            String groupName = "";
//            if(cposgCode!=null){
//                if(cposgCode.equalsIgnoreCase("CSM"))groupName = "MEDICAL";
//                if(cposgCode.equalsIgnoreCase("CSE"))groupName = "ENGINEERING";
//                if(cposgCode.equalsIgnoreCase("CSC"))groupName = "COMMERCE";
//            }
            posName = program.equals("B.B.A") ? program + " (HONS) " + partName
                    : program.equals("PHARM-D") ? partName.replace("YEAR","") + " PROF. " + program
                    : program.equals("LL.B") ? program + " FIRST YEAR"
//                    : discipline.equalsIgnoreCase("COMPUTER SCIENCE") ? program+ " (" + discipline + ") "+ groupName+" " + partName
                    : discipline.contains("PHYSICAL EDUCATION") ? program+ " (PHESS) " + partName
                    : program + " (" + discipline + ") " + partName;
            posName += (isMorning ? "" : " (EVENING)");
//            posName = (program.equals("B.B.A") ||
//                    program.equals("B.P.A") ||
//                    program.equals("PHARM-D") ||
//                    program.equals("LL.M")) ? program + " " + partName
//                    : program + " (" + discipline + ") " + partName;
        }
        return posName;
    }
    
    public static void loadComboBox(JComboBox combo, List list) {
        combo.removeAllItems();

        for (Object object : list) {
            combo.addItem(object);
        }
    }

    public static <E extends Enum<E>> void loadEnum(JComboBox combo, Class<E> c) {
        combo.removeAllItems();

        for (E e : c.getEnumConstants()) {
            combo.addItem(e);
        }
    }

    public static void colorRender() {
        UIManager.put("TextField.inactiveBackground", new ColorUIResource(Color.white));

        Color c = new Color(209, 226, 242);
        UIManager.put("ComboBox.selectionBackground", new ColorUIResource(c));
        UIManager.put("TextField.selectionBackground", new ColorUIResource(c));
        UIManager.put("FormattedTextField.selectionBackground", new ColorUIResource(c));
        UIManager.put("List.selectionBackground", new ColorUIResource(c));
        UIManager.put("Menu.selectionBackground", new ColorUIResource(c));
        UIManager.put("MenuItem.selectionBackground", new ColorUIResource(c));
        UIManager.put("PasswordField.selectionBackground", new ColorUIResource(c));
        UIManager.put("Table.selectionBackground", new ColorUIResource(c));
        UIManager.put("TextArea.selectionBackground", new ColorUIResource(c));
        UIManager.put("ToolTip.background", new ColorUIResource(c));
//        ComboBox.selectionBackground	javax.swing.plaf.ColorUIResource[r=163,g=184,b=204]	javax.swing.plaf.ColorUIResource[r=163,g=184,b=204]
//        UIManager.put("ComboBox.background", new ColorUIResource(UIManager.getColor("TextField.background")));
//        UIManager.put("ComboBox.foreground", new ColorUIResource(UIManager.getColor("TextField.foreground")));
//        UIManager.put("InternalFrame.background", new ColorUIResource(UIManager.getColor("TextField.foreground")));
//        UIManager.put("Panel.background", new ColorUIResource(UIManager.getColor("TextField.background")));
    }

    public static void setStarkRed(Container container) {
        Component[] components = container.getComponents();
        for (Component component : components) {
            if (component instanceof JLabel) {
                JLabel l = (JLabel) component;
                String text = l.getText();
                if (!text.endsWith("*")) {
                    continue;
                }
                text = "<html>" + text.substring(0, text.length() - 1) + "<font color='red' size='5'>*</font>";
                l.setText(text);
            } else if (component instanceof Container) {
                if (component instanceof JPanel) {
                    Border b = ((JPanel) component).getBorder();
                    if (b instanceof TitledBorder) {
                        TitledBorder tb = (TitledBorder) b;
                        String text = tb.getTitle();
                        if (text.endsWith("*")) {
                            text = "<html>" + text.substring(0, text.length() - 1) + "<font color='red' size='5'>*</font>";
                            tb.setTitle(text);
                        }
                    }
                }
                setStarkRed((Container) component);
            }
        }
    }
}
