package admission.services;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Map;

/**
 *
 * @author Yougeshwar
 */
public interface SessionService extends Remote {
    public Map<String, Object> getMap() throws RemoteException;
    public Object getValue(String key) throws RemoteException;
    public Object setValue(String key, Object value) throws RemoteException;
}
