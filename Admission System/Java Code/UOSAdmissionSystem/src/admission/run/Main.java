package admission.run;


import admission.controller.DatabaseManager;
import admission.controller.JDBCDatabaseManager;
import admission.dbupdate.DbUpdateExecuter;
import admission.model.security.User;
import admission.services.KeyConstant;
import admission.services.Session;
import admission.services.SessionService;
import admission.view.ConfigurationDialog;
import admission.view.MainFrame;
import admission.view.admission.AdmissionListDialog;
import admission.view.security.LoginDialog;
import admission.utils.DefaulPopupEventQueue;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;
import java.rmi.RemoteException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import admission.utils.IConstant;

/**
 *
 * @author Raja Kumar & Jay Kumar
 */
public class Main {

    public static void main(final String[] args) throws IOException {

        String[] li = {"Licensee=Jyloo Software", "LicenseRegistrationNumber=------", "Product=Synthetica", "LicenseType=For internal tests only", "ExpireDate=--.--.----", "MaxVersion=2.999.999" };
        UIManager.put("Synthetica.license.info", li);
        UIManager.put("Synthetica.license.key", "E1CBD033-B07718A2-1E181B5F-A78A6DFF-813D8FB4");
        try {
            UIManager.setLookAndFeel("de.javasoft.plaf.synthetica.SyntheticaAluOxideLookAndFeel");
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        
//        try {
//            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
//        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
////            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
//        }
        Toolkit.getDefaultToolkit().getSystemEventQueue().push(new DefaulPopupEventQueue());
        String userHome = System.getProperty("user.home");
        String path = userHome + "\\" + IConstant.DB.PROPERTY_PATH;
        File file = new File(path);

        final Properties prop = new Properties();
        if (file.exists()) {
            prop.load(new java.io.FileInputStream(file));

            IConstant.DB.DRIVER = prop.getProperty("driver");
            IConstant.DB.URL = prop.getProperty("url");
            IConstant.DB.USERNAME = prop.getProperty("user");
            IConstant.DB.PASSWORD = new admission.utils.AltEncrypter().decrypt(prop.getProperty("password"));
            IConstant.USER.LAST_USER_LOGINED = prop.getProperty("last.user", "user");

            JDBCDatabaseManager.load();
        }
        if (IConstant.DB.STATUS == 0) {
            new ConfigurationDialog(new java.awt.Frame()).setVisible(true);
            return;
        }

        DbUpdateExecuter.execute();
        
        DatabaseManager.load();

        final User user = LoginDialog.showDialog();

        SessionService ss = Session.getSessionService();
        try {
            ss.setValue(KeyConstant.ACTIVE_USER_ID, user.getId());
        } catch (RemoteException ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
//                utilities.Utility.colorRender();
                if (args.length == 0) {
                    MainFrame mainFrame = new MainFrame();
                    mainFrame.setUser(user);
                    mainFrame.setTitle("Admission Management System A Public Sector Higher Education DAI, Univeristy of Sindh, Jamshoro - " + user.getFirstName());
                    mainFrame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
                    mainFrame.setResizable(false);
                    mainFrame.setVisible(true);
                } else {
                    AdmissionListDialog dialog = new AdmissionListDialog(new javax.swing.JFrame(), true);
                    dialog.setVisible(true);
                }
            }
        });
    }
}
