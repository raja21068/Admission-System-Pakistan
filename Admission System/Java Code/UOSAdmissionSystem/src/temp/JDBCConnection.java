package temp;

//~--- JDK imports ------------------------------------------------------------

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.Date;
import java.util.Scanner;

/**
 *
 * @author Yougeshwar
 */
public class JDBCConnection {
    public static void main(String[] args) throws ClassNotFoundException, SQLException, FileNotFoundException {

        // TODO code application logic here
        Class.forName("com.mysql.jdbc.Driver");

        Connection con   = DriverManager.getConnection("jdbc:mysql://localhost:3306/us_admission", "root",
                               "Kasak_2005");
        Statement  st    = con.createStatement();
        Scanner    scan  = new Scanner(new FileInputStream("test_score2014.txt"));
        int        s     = 0,
                   f     = 0;
        long       start = System.currentTimeMillis();

        while (scan.hasNextLine()) {
            String[] line   = scan.nextLine().split(",");
            String   seatNo = line[0];
            String   score  = line[1];

//          System.out.println(seatNo + " : " + score + "");
            String update = "UPDATE candidate SET test_score = " + score + " WHERE seat_no = " + seatNo
                            + " AND admission_year_id = 3 AND program_type_id = 1";
            int effect = st.executeUpdate(update);

            if (effect > 0) {
                System.out.println(seatNo + " : " + score + " - Done");
                s++;
            } else {
                System.out.println(seatNo + " : " + score + " - Failed");
                f++;
            }
        }

        long end = System.currentTimeMillis();

        System.out.println(new java.text.SimpleDateFormat("hh:mm:ss:SSS").format(new Date(end - start)));
        System.out.println("Success: " + s + ", Failed: " + f);
    }
}


//~ Formatted by Jindent --- http://www.jindent.com
