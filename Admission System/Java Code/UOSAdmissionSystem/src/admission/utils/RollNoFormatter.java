/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package admission.utils;

/**
 *
 * @author Yougeshwar
 */
public class RollNoFormatter {
    public static String format(int year, String pCode, Integer rollNo) {
        if(rollNo == null || rollNo == 0) return "";
        
        String kYear = String.valueOf(year).replace("0", "K");
        
        return kYear + "-" + pCode + "-" + rollNo;
    }
}
