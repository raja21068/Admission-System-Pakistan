/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package temp;

import java.util.Date;
import admission.utils.DateUtility;

/**
 *
 * @author Yougeshwar
 */
public class NewClass1 {
    public static void main(String[] args) {
        long currentTimeMillis = new Date().getTime();//System.currentTimeMillis();
        System.out.println(DateUtility.toDate(currentTimeMillis));
        
//        System.out.println(new Date().getTime());
    }
}
