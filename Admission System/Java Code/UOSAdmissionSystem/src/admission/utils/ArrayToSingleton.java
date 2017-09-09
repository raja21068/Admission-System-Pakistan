package admission.utils;

import java.util.List;

/**
 *
 * @author Yougeshwar
 */
public class ArrayToSingleton {
    /**
     * @param args
     * @return Returns comma separated values
     */
    public static String singleton(int[] args) {
        String s = "";
        for (Object ob : args) {
            s += ob.toString() + ",";
        }
        s = StringUtils.removeEnd(s, ",");
        return s;
    }
    public static String singleton(List args) {
        String s = "";
        if(args == null) return s;
        
        for (Object ob : args) {
            s += ob.toString() + ",";
        }
        s = StringUtils.removeEnd(s, ",");
        return s;
    }
    public static String singleton(String[] args) {
        String s = "";
        for (Object ob : args) {
            s += ob.toString() + ",";
        }
        s = StringUtils.removeEnd(s, ",");
        return s;
    }
}
