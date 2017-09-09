package temp;

//~--- non-JDK imports --------------------------------------------------------

import admission.controller.JDBCDatabaseManager;

import admission.model.CampusProgramOfStudy;
import admission.model.CredentialDetails;

//~--- JDK imports ------------------------------------------------------------

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Yougeshwar
 */
public class CheckCandidate {
    public static void main(String[] args) throws FileNotFoundException, SQLException {
        for (int i = 1; i <= 70; i++) {
            System.out.println("  WHEN GROUP_ = " + i + " THEN " + i);
        }
        System.exit(0);
        Scanner scan = new Scanner(new FileInputStream("GEN.csv"));

        while (scan.hasNextLine()) {
            String line = scan.nextLine();

            if (line.isEmpty()) {
                continue;
            }

            String[] split = line.split(",");
            int      id    = Integer.parseInt(split[0]);

//          int id1 = 0, id2 = 0, id3 = 0;
//          
//          if(!split[1].isEmpty() && !split[1].equals("1") && !split[1].equals("0"))
//              id1 = JDBCDatabaseManager.getSubjectId(split[1]);
//          if(!split[2].isEmpty() && !split[2].equals("1") && !split[2].equals("0"))
//              id2 = JDBCDatabaseManager.getSubjectId(split[2]);
//          if(!split[3].isEmpty() && !split[3].equals("1") && !split[3].equals("0"))
//              id3 = JDBCDatabaseManager.getSubjectId(split[3]);
//          
//          JDBCDatabaseManager.updateCandidate(3, 2, split[0], "" + id1, "" + id2, "" + id3);
//          JDBCDatabaseManager.addCategory(id, "GEN");
//          int candidateId = JDBCDatabaseManager.getCandidateId(3, 2, Integer.parseInt(split[0]));
//          int programId = JDBCDatabaseManager.getProgramId(Integer.parseInt(split[1]));
//          JDBCDatabaseManager.updateCredential(candidateId, programId + "", 2);

//          System.out.println(split[0] + " : Done | " + programId + " - " + Integer.parseInt(split[1]));
            System.out.println(id + " : Done");
        }

//      while(scan.hasNextLine()){
//          String line = scan.nextLine();
//          if(line.isEmpty()) continue;
//          
//          String tokens[] = line.split(",");
//          int seatNo = Integer.parseInt(tokens[0]);
//          int candidateId = JDBCDatabaseManager.getCandidateId(3, 2, seatNo);
//          int c = 1;
//          List<String> l = new ArrayList<>();
//          for (int i = 1; i <= 10; i++) {
//              String pCode = tokens[i];
//              if(pCode.isEmpty() || pCode.equals("1") || pCode.equals("0") || l.contains(pCode)) continue;
//              
//              CampusProgramOfStudy cpos = JDBCDatabaseManager.getCampusProgramOfStudy(1, 2, Integer.parseInt(pCode), 1);
//              if(cpos == null) {
//                  cpos = JDBCDatabaseManager.getCampusProgramOfStudy(2, 2, Integer.parseInt(pCode), 1);
//                  if(cpos == null) continue;
//              }
//              JDBCDatabaseManager.addCNPOS(candidateId, cpos.getCampusProgramOfStudyId(), c++);
//              l.add(pCode);
//          }
//          l.clear();
//          c = 1;
//          for (int i = 11; i <= 20; i++) {
//              String pCode = tokens[i];
//              if(pCode.isEmpty() || pCode.equals("1") || pCode.equals("0") || l.contains(pCode)) continue;
//              
//              CampusProgramOfStudy cpos = JDBCDatabaseManager.getCampusProgramOfStudy(1, 2, Integer.parseInt(pCode), 2);
//              if(cpos == null) {
//                  cpos = JDBCDatabaseManager.getCampusProgramOfStudy(2, 2, Integer.parseInt(pCode), 2);
//                  if(cpos == null) continue;
//              }
//              JDBCDatabaseManager.addCNPOS(candidateId, cpos.getCampusProgramOfStudyId(), c++);
//              l.add(pCode);
//          }
//          
//          System.out.println(seatNo + " : Done");
//      }

        System.exit(0);
    }
}


//~ Formatted by Jindent --- http://www.jindent.com
