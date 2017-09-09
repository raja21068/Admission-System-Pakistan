package temp;

//~--- non-JDK imports --------------------------------------------------------

import admission.controller.DatabaseManager;

import admission.model.Campus;
import admission.model.CampusProgramOfStudy;
import admission.model.Candidate;
import admission.model.CandidateProgramOfStudy;
import admission.model.ProgramType;
import admission.model.Shift;

//~--- JDK imports ------------------------------------------------------------

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

/**
 *
 * @author Raja Kumar & Jay Kumar
 */
public class UpdateCandidate2014_BACHALOR {
    public static void main(String[] args) throws FileNotFoundException {

//      DatabaseManager.load();
//      ProgramType pt = (ProgramType) DatabaseManager.getSingleRecord(ProgramType.class.getName(), 1);
//      Campus campus = (Campus) DatabaseManager.getSingleRecord(Campus.class.getName(), 1);
//      Shift morningShift = (Shift) DatabaseManager.getSingleRecord(Shift.class.getName(), 1);
//      Shift eveningShift = (Shift) DatabaseManager.getSingleRecord(Shift.class.getName(), 2);
//      int allama = 1;
//      int elsa = 2;
//      int morning = 1;
//      int evening = 2;
//      System.out.println(pt);
        Scanner scan = new Scanner(new FileInputStream("batch2014csv.txt"));

        while (scan.hasNextLine()) {
            String lines  = scan.nextLine();
            String line[] = lines.split(",");

            for (int i = 1; i < line.length; i++) {
                if (line[i].equals("58")) {
                    System.out.println(lines);

                    break;
                }
            }

//          Candidate candidate = DatabaseManager.getCandidate(3, 1, lines[0]);
//          if (candidate == null) continue;
//          Set set = candidate.getCandidateProgramOfStudies();

//          List<Integer> list = new ArrayList<>();
//          for (Iterator it = set.iterator(); it.hasNext();) {
//              CandidateProgramOfStudy ob = (CandidateProgramOfStudy) it.next();
//              Integer id = ob.getCampusProgramOfStudy().getCampusProgramOfStudyId();
//              if(list.contains(id)) {
//                  DatabaseManager.deleteData(CandidateProgramOfStudy.class.getName(), "candidateProgramOfStudyId=" + ob.getCandidateProgramOfStudyId());
//                  System.out.println(lines + " - Deleted");
//              } else 
//                  list.add(id);
//          }
//          for (int i = 11; i <= 20; i++) {
//              int pCode = Integer.parseInt(line[i].trim());
//          
//              if(pCode == 1 || pCode == 0) continue;
//              
//              if(list.contains(pCode)){
////                  for (int j = 0; j < list.size(); j++) {
////                      System.out.print(list.get(j) + ", ");
////                  }
//                  System.out.println(line[0]);
//                  break;
//              } else {
//                  list.add(pCode);
//              }
//          }
//          Candidate c = DatabaseManager.getCandidate(3, 1, line[0]);
//          if (c == null) continue;
//          for (int i = 1; i <= 10; i++) {
//              String pCode = line[i].trim();
//          
//              if(pCode.isEmpty() || pCode.equals("1") || pCode.equals("0")) continue;
//              if(pCode.equals("41") || pCode.equals("40")) 
//                  pCode = "9";
//              int campus = cMap(Integer.parseInt(pCode));
//              int shift = sMap(Integer.parseInt(pCode), 1);
//              CampusProgramOfStudy cpos = (CampusProgramOfStudy) DatabaseManager.getCpos(campus, Integer.parseInt(pCode), 1, shift);
//              if(cpos == null) continue;
//              DatabaseManager.addData(new CandidateProgramOfStudy(cpos, c, i, "", null));
//          }
//          for (int i = 11; i <= 20; i++) {
//              String pCode = line[i].trim();
//          
//              if(pCode.isEmpty() || pCode.equals("1") || pCode.equals("0")) continue;
//              if(pCode.equals("41") || pCode.equals("40")) 
//                  pCode = "9";
//              int campus = cMap(Integer.parseInt(pCode));
//              int shift = sMap(Integer.parseInt(pCode), 2);
//              CampusProgramOfStudy cpos = (CampusProgramOfStudy) DatabaseManager.getCpos(campus, Integer.parseInt(pCode), 1, shift);
//              if(cpos == null) continue;
//              DatabaseManager.addData(new CandidateProgramOfStudy(cpos, c, i - 10, "", null));
//          }
//          DatabaseManager.refresh(c);
//          System.out.println(line[0] + " : Done");
        }

//      Collections.sort(list);
//      for (int i = 0; i < list.size(); i++) {
//          Integer integer = list.get(i);
//          System.out.println(integer);
//      }
        System.exit(0);
    }

    private static int cMap(int i) {
        int c = 1;

        switch (i) {
        case 49 :
            c = 2;

            break;

        case 51 :
            c = 2;

            break;

        case 58 :
            c = 2;

            break;
        }

        return c;
    }

    private static int sMap(int i, int d) {
        switch (i) {
        case 59 :
            d = 2;

            break;

        case 57 :
            d = 2;

            break;

        case 58 :
            d = 2;

            break;
        }

        return d;
    }

    private static String getChange(int i) {
        String[] s = new String[] {
            "", "SC", "AR", "PM", "PE", "AR", "HM", "GS", "GSB", "CM", "CD", "", "OD"
        };

        return s[i];
    }
}


//~ Formatted by Jindent --- http://www.jindent.com
