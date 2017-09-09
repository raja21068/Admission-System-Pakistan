package temp;

//~--- non-JDK imports --------------------------------------------------------

import admission.controller.JDBCDatabaseManager;

import admission.utils.IConstant;

//~--- JDK imports ------------------------------------------------------------

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import java.sql.SQLException;

import java.util.Hashtable;
import java.util.Scanner;

/**
 *
 * @author Raja Kumar & Jay Kumar
 */
public class DataLoaderNew2014_MASTER2 {
    public static void main(String[] args) throws FileNotFoundException, SQLException {

//      Hashtable<Integer, String> map = new Hashtable<>(2715);
//      
        Scanner scan = new Scanner(new FileInputStream("YOUGESHGRADCSV.txt"));

//        
//      while(scan.hasNextLine()){
//          String line = scan.nextLine();
//          String[] split = line.split(",");
//          Integer seatNo = Integer.parseInt(split[0].trim());
//          if(seatNo == 3810) System.out.println("yes");
//          map.put(seatNo, line);
//      }
//      
//      int[][] candidatesId = JDBCDatabaseManager.getCandidatesId(3, 1, 2);
//      System.out.println(candidatesId.length);
//      System.exit(0);
        while (scan.hasNextLine()) {
            String   line  = scan.nextLine();
            String[] split = line.split(",");
            int      id    = JDBCDatabaseManager.getCandidateId(3, 2, Integer.parseInt(split[0]));

            if (id == 0) {
                continue;
            }

            String  grdGroup      = split[1].trim();
            String  marksObtained = (split[2].trim());
            Integer yearsDegree   = Integer.parseInt(split[3].trim());    // grdPeriod
            String  totalMarks    = (split[4].trim());
            String  passingYear   = (split[5].trim());
            String  grdSeatNo     = (split[6].trim());
            int     issuerId      = JDBCDatabaseManager.getIssuerIdByCode(split[7].trim());

            if (issuerId == 0) {
                continue;
            }

            JDBCDatabaseManager.updateCandidates(id, yearsDegree);
            JDBCDatabaseManager.updateCredential(id, grdGroup, marksObtained, grdSeatNo, issuerId, passingYear,
                    totalMarks, IConstant.GRADUATION);
            System.out.println(split[0] + " : Done");
        }

//      while(scan.hasNextLine()){
//          String line = scan.nextLine();
//          String arr[] = line.split(",");
//          System.out.println(seatNo + " : Done");
//      }
        System.exit(0);
    }
}


//~ Formatted by Jindent --- http://www.jindent.com
