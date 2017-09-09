
package admission.services;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import admission.utils.MessageBox;

/**
 *
 * @author Yougeshwar
 */
public class Session extends UnicastRemoteObject implements SessionService {
    private static final Logger logger = Logger.getLogger(Session.class.getName());
    
    private final Map<String, Object> map = new LinkedHashMap<>();
    
    static {
        try {
            init();
        } catch (RemoteException | MalformedURLException ex) {
            logger.log(Level.SEVERE, null, ex);
            MessageBox.error(null, ex);
            System.exit(0);
        }
    }
    
    private static void init() throws RemoteException, MalformedURLException {
        Registry registry = LocateRegistry.createRegistry(1099);
        registry.rebind("SessionService", new Session());
    }
    
    public static SessionService getSessionService() {
        SessionService ss = null;
        try {
            Registry reg = LocateRegistry.getRegistry("localhost");
            ss = (SessionService) reg.lookup("SessionService");
        } catch (NotBoundException | RemoteException ex) {
            logger.log(Level.SEVERE, null, ex);
        }
        
        return ss;
    }
    
    private Session() throws RemoteException{
    }

    @Override
    public Map<String, Object> getMap() {
        return map;
    }
    
    @Override
    public Object getValue(String key) {
        logger.log(Level.INFO, "Object fatched on : " + key, key);
        return map.get(key);
    }
    
    @Override
    public Object setValue(String key, Object value) {
        logger.log(Level.INFO, "Object stored on : " + key, key);
        return map.put(key, value);
    }    
}
