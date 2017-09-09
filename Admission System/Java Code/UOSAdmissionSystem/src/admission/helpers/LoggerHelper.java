package admission.helpers;

import admission.controller.DatabaseManager;
import admission.controller.JDBCDatabaseManager;
import admission.model.security.Log;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.HibernateException;

/**
 *
 * @author Yougeshwar
 */
public class LoggerHelper {
    public static void addLog(Class modelClass, Integer modelId) {
        try {
            Integer activeUserId = CommonHelper.getActiveUserId();
            
            JDBCDatabaseManager.addLog(activeUserId, "ADD", modelClass.getSimpleName(), modelId);
        } catch (SQLException ex) {
            Logger.getLogger(LoggerHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public static void updateLog(Class modelClass, Integer modelId) {
        try {
            Integer activeUserId = CommonHelper.getActiveUserId();
            
            JDBCDatabaseManager.addLog(activeUserId, "UPDATE", modelClass.getSimpleName(), modelId);
        } catch (SQLException ex) {
            Logger.getLogger(LoggerHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public static void deleteLog(Class modelClass, Integer modelId) {
        try {
            Integer activeUserId = CommonHelper.getActiveUserId();
            
            JDBCDatabaseManager.addLog(activeUserId, "DELETE", modelClass.getSimpleName(), modelId);
        } catch (SQLException ex) {
            Logger.getLogger(LoggerHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public static Log getLoggerName(Class modelClass, Integer modelId, String modelAction) {
        Log log = null;
        try {
            log = DatabaseManager.getSingleRecord(Log.class, "modelName = '" + modelClass.getSimpleName() + "' AND modelId = " + modelId + " AND modelAction = '" + modelAction + "'");
        } catch (HibernateException ex) {
            Logger.getLogger(LoggerHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return log;
    }
}
