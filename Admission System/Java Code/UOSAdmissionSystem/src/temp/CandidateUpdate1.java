package temp;

//~--- non-JDK imports --------------------------------------------------------

import admission.controller.JDBCDatabaseManager;
import java.io.File;

//~--- JDK imports ------------------------------------------------------------

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import java.sql.SQLException;
import java.util.Properties;

import java.util.Scanner;
import admission.utils.IConstant;

/**
 *
 * @author Raja Kumar & Jay Kumar
 */
public class CandidateUpdate1 {
    public static void main(String[] args) throws SQLException, FileNotFoundException, IOException {
        load();
        
        Scanner scan = new Scanner(new FileInputStream("columns2.csv"));

        while (scan.hasNextLine()) {
            String line = scan.nextLine();

            if (line.isEmpty()) {
                continue;
            }

            String tokens[] = line.split(",");
            String seatNo   = tokens[0];
            String address  = (tokens.length > 1) ? tokens[1] : "";
            String cnic     = (tokens.length > 2) ? tokens[2] : "";
            int    row      = JDBCDatabaseManager.updateCandidate(3, 1, Integer.parseInt(seatNo), address, cnic);

            if (row > 0) {
                System.out.println(seatNo + " Done");
            } else {
                System.out.println(seatNo + " No");
            }
        }
    }
    
    private static void load() throws FileNotFoundException, IOException {
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
            
            JDBCDatabaseManager.load();
        }
    }
}


//~ Formatted by Jindent --- http://www.jindent.com
