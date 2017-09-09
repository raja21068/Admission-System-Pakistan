package admission.utils;

import java.text.ParseException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Raja Kumar & Jay Kumar
 */

public class DateUtility {
    public static String getDateToString(java.util.Date date){
        if(date == null) return "";
        
        java.text.SimpleDateFormat format = new java.text.SimpleDateFormat("dd-MM-yyyy");
        
        return format.format(date);
    }
    public static java.util.Date getStringToDate(String date){
        if(date.isEmpty() || date.length() == 4) return null;
        
        java.text.SimpleDateFormat format = new java.text.SimpleDateFormat("dd-MM-yyyy"); //dd-MM-yyyy
        try {
            return format.parse(date);
        } catch (ParseException ex) {
            Logger.getLogger(DateUtility.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    public static java.util.Date getStringToYear(String date){
        if(date.isEmpty()) return null;
        
        java.text.SimpleDateFormat format = new java.text.SimpleDateFormat("yyyy");
        try {
            return format.parse(date);
        } catch (ParseException ex) {
            Logger.getLogger(DateUtility.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    public static String getYearToString(java.util.Date date){
        if(date == null) {
            date = new Date();
        }
        java.text.SimpleDateFormat format = new java.text.SimpleDateFormat("yyyy");
        return format.format(date);
    }
    public static int getYear(java.util.Date date){
        if(date == null) {
            date = new Date();
        }
        java.text.SimpleDateFormat format = new java.text.SimpleDateFormat("yyyy");
        return Integer.parseInt(format.format(date));
    }
    public static String getTimeToString(java.util.Date date){
        if(date == null) return "";
        
        java.text.SimpleDateFormat format = new java.text.SimpleDateFormat("hh:mm a");
        
        return format.format(date);
    }
    public static java.util.Date getStringToTime(String date){
        if(date.isEmpty()) return null;
        
        java.text.SimpleDateFormat format = new java.text.SimpleDateFormat("hh:mm a");
        try {
            return format.parse(date);
        } catch (ParseException ex) {
            Logger.getLogger(DateUtility.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    public static String toDate(long milliseconds) {
        Date date = new Date(milliseconds);
        
        java.text.SimpleDateFormat format = new java.text.SimpleDateFormat("dd-MM-yyyy");
        
        return format.format(date);
    }
    public static long toMilliseconds(String s) {
        java.text.SimpleDateFormat format = new java.text.SimpleDateFormat("dd-MM-yyyy");
        try {
            Date date = format.parse(s);
            return date.getTime();
        } catch (ParseException ex) {
            Logger.getLogger(DateUtility.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0l;
    }
}
