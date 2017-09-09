package admission.helpers;

import admission.controller.DatabaseManager;
import admission.controller.JDBCDatabaseManager;
import admission.model.security.User;
import admission.services.KeyConstant;
import admission.services.Session;
import admission.services.SessionService;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import admission.utils.MessageBox;

/**
 *
 * @author Yougeshwar
 */
public class CommonHelper {

    public static boolean checkUserResourceAccess(String code) {
        Integer id = CommonHelper.getActiveUserId();
        if(id == 0) return false;

        try {
            if(checkActiveUser()) {
                return true;
            }
            return JDBCDatabaseManager.isUserResourceAccess(id, code);
        } catch (SQLException ex) {
            Logger.getLogger(CommonHelper.class.getName()).log(Level.SEVERE, null, ex);
            MessageBox.error(null, ex);
        }
        return false;
    }
    
    public static boolean checkActiveUser() {
        Integer id = CommonHelper.getActiveUserId();
        if(id == 0) return false;

        try {
            return JDBCDatabaseManager.checkUser(id);
        } catch (SQLException ex) {
            Logger.getLogger(CommonHelper.class.getName()).log(Level.SEVERE, null, ex);
            MessageBox.error(null, ex);
        }
        return false;
    }
    
    public static Integer getActiveUserId() {
        SessionService ss = Session.getSessionService();
        Integer id = 0;
        try {
            id = (Integer) ss.getValue(KeyConstant.ACTIVE_USER_ID);
        } catch (RemoteException ex) {
            Logger.getLogger(CommonHelper.class.getName()).log(Level.SEVERE, null, ex);
            MessageBox.error(null, ex);
        }
        return id;
    }
    
    public static String getActiveUserName() {
        SessionService ss = Session.getSessionService();
        String name = "";
        try {
            Integer id = (Integer) ss.getValue(KeyConstant.ACTIVE_USER_ID);
            User user = DatabaseManager.getSingleRecord(User.class, id);
            name = user.getFirstName() + " " + user.getLastName();
        } catch (RemoteException ex) {
            Logger.getLogger(CommonHelper.class.getName()).log(Level.SEVERE, null, ex);
            MessageBox.error(null, ex);
        }
        return name;
    }
    
    public static Object getSessionValue(String key) {
        SessionService ss = Session.getSessionService();
        Object ob = null;
        try {
             ob = ss.getValue(key);
        } catch (RemoteException ex) {
            Logger.getLogger(CommonHelper.class.getName()).log(Level.SEVERE, null, ex);
            MessageBox.error(null, ex);
        }
        return ob;
    }
    
    public static Object setSessionValue(String key, Object value) {
        SessionService ss = Session.getSessionService();
        Object ob = null;
        try {
             ob = ss.setValue(key, value);
        } catch (RemoteException ex) {
            Logger.getLogger(CommonHelper.class.getName()).log(Level.SEVERE, null, ex);
            MessageBox.error(null, ex);
        }
        return ob;
    }
    
}
