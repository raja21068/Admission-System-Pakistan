package temp;

import admission.controller.DatabaseManager;
import admission.controller.JDBCDatabaseManager;
import admission.model.Program;
import admission.model.ProgramSubject;
import admission.model.Subject;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.Scanner;
import admission.utils.IConstant;

/**
 *
 * @author Yougeshwar
 */
public class Temp {
    public static void main(String[] args) throws FileNotFoundException, IOException {
        Scanner scan = new Scanner(new FileInputStream("P_AND_OPS.csv"));
        scan.nextLine();
        load();
        int c = 0;
        while (scan.hasNext()) { 
            String line = scan.nextLine();
            String chunks[] = line.split(",");
            
            String pId = chunks[0];
            String opsId = chunks[1];
            
            ProgramSubject ps = (ProgramSubject) DatabaseManager.getSingleRecord(ProgramSubject.class.getName(), "program.programId = " + pId + " AND subject.subjectId = " + opsId);
            if(ps == null) {c++;
                Program p = (Program) DatabaseManager.getSingleRecord(Program.class.getName(), "programId = " + pId);
                Subject s = (Subject) DatabaseManager.getSingleRecord(Subject.class.getName(), "subjectId = " + opsId);
                System.out.println(c + ":" + p + "(" + pId + ")" + " : " + s + "(" + opsId + ")");
                ps = new ProgramSubject(s, p);
                DatabaseManager.save(ps);
            }
        }
        System.out.println(c);
    }
    
    public static void load() throws FileNotFoundException, IOException {
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