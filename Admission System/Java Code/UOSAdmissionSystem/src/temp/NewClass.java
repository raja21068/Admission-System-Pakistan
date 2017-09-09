package temp;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 *
 * @author Yougeshwar
 */
public class NewClass {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scan = new Scanner(new FileInputStream("optional_subjects.csv"));
        
        String s = "INSERT INTO OPTIONAL_SUBJECT (CREDENTIAL_DETAILS_ID, PROGRAM_SUBJECT_ID) VALUES ";
        System.out.println(s);
        scan.nextLine();
        while (scan.hasNext()) {
            String line = scan.nextLine();
            
            String chunks[] = line.split(",");
            
            String cdId = chunks[0];
            String ops1 = chunks[1];
            String ops2 = chunks[2];
            String ops3 = chunks[3];
            
            if(!ops1.equals(ops2) && !ops1.equals(ops3) && !ops1.equals("0")) {
                System.out.println("(" + cdId + ", " + ops1 + "),");
            }
            if(!ops2.equals(ops1) && !ops2.equals(ops3) && !ops2.equals("0")) {
                System.out.println("(" + cdId + ", " + ops2 + "),");
            }
            if(!ops3.equals(ops1) && !ops3.equals(ops2) && !ops3.equals("0")) {
                System.out.println("(" + cdId + ", " + ops3 + "),");
            }
        }
    }
}
