package temp;

//~--- non-JDK imports --------------------------------------------------------

import admission.controller.DatabaseManager;

import admission.model.AdmissionListDetails;
import admission.model.CampusProgramOfStudy;
import admission.model.Candidate;
import admission.model.CandidateProgramOfStudy;
import admission.model.CposGroup;
import admission.model.PartRegistry;

import admission.utils.Coder;
import admission.utils.IConstant;

//~--- JDK imports ------------------------------------------------------------

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import java.util.List;
import java.util.Scanner;
import java.util.Set;

/**
 *
 * @author Yougeshwar
 */
public class AdmissionListChecking {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner              scan = new Scanner(new FileInputStream("SEATNO.txt"));
        CampusProgramOfStudy cpos =
            (CampusProgramOfStudy) DatabaseManager.getSingleRecord(CampusProgramOfStudy.class.getName(), 166);

        while (scan.hasNextLine()) {

//          String line = scan.nextLine();
//          String tokens[] = line.split(",");
            String    seatNo    = scan.nextLine();    // tokens[0];

//          Integer code = Integer.parseInt(tokens[1]);
            Candidate candidate = DatabaseManager.getCandidate(3, 2, seatNo);

            if (candidate == null) {

//              System.out.println("(Not Found) " + tokens[0] + "," + tokens[1] + ",=,NIL");
                System.out.println("(Not Found) " + seatNo);

                continue;
            }

            Set set = null;//candidate.getCandidateProgramOfStudies();

//          
//          Boolean b = DatabaseManager.isAlreadyPaid(3, 2, Integer.parseInt(seatNo));
//          if(b) {
//              System.out.println(seatNo);
//          }
//          Set set = candidate.getAdmissionListDetailses();
            if (set == null) {
                System.out.println(seatNo + " NO");

                continue;
            }

            Object[] toArray = set.toArray();
            boolean  b       = true;

            for (Object toArray1 : toArray) {
                CandidateProgramOfStudy cnpos = (CandidateProgramOfStudy) toArray1;

                if (cnpos.getCampusProgramOfStudy().getCampusProgramOfStudyId().intValue() == 166) {

//                  cnpos.setCampusProgramOfStudy(cpos);
//                  DatabaseManager.updateData(cnpos);
                    b = false;

                    break;
                }
            }

            if (b) {

//              CandidateProgramOfStudy cnpos = new CandidateProgramOfStudy(cpos, candidate, 1, "", null);
//              DatabaseManager.addData(cnpos);
                System.out.println(seatNo);
            }

//          //
//                      Object[] toArray = set.toArray();
//                      for (Object toArray1 : toArray) {
//                          AdmissionListDetails ald = (AdmissionListDetails) toArray1;
//                          Integer pCode = ald.getCposGroup().getCampusProgramOfStudy().getProgramOfStudy().getPCode();
//                          System.out.println(seatNo + ",=," + pCode + "," + ald.getCampusCategory().toString() + "," + ald.getCampusCategory().getShift().toString());
//                          
//                          Set set1 = ald.getPartRegistries();
//                          if(set1 == null) continue;
//                          Object[] ar = set1.toArray();
//                          boolean  b;
//                          if(ald.getCampusCategory().getCategory().getCode().intValue() == Constant.CategoryCodes.SELF_FINANCE_QUOTA) {
//                              b = false;
//                              continue;
//                          }
//                          b = true;
//                          System.out.println(ald.getCposGroup() + "(" + ald.getCampusCategory() + ")(" + ald.getCampusCategory().getShift() + ")(" + ald.getAdmissionList().getListNo() + "),");
//                          for (int j = 0; j < ar.length; j++) {
//                              PartRegistry pr = (PartRegistry) ar[j];
//                              
//                          }
//                          
//                      }
            // ANOTHER
//                      String seatNo = scan.nextLine();
//                      Candidate candidate = DatabaseManager.getCandidate(3, 2, seatNo);
//                      if(candidate == null) {
//                          System.out.println("(Not Found) " + seatNo);
//                          continue;
//                      }
//                      Set accounts = candidate.getAccounts();
//                      if(accounts == null || accounts.isEmpty()) {
//                          System.out.println(seatNo + " Not Registered");
//                          continue;
//                      }
//          //            
//          //            System.out.println(seatNo + " Registered");
//                      Set set = candidate.getAdmissionListDetailses();
//                      if(set == null || set.isEmpty()) {
//          //                System.out.println("No Selection " + seatNo + ", " + code);
//                          continue;
//                      }
//          //                System.out.println(line);
//                      Object[] toArray = set.toArray();
//          ////            boolean b = true;
//          ////            String s = seatNo + ",";
//          ////            int id = 0;
//          ////            System.out.print(seatNo + ",");
//                      for (int i = 0; i < toArray.length; i++) {
//                          AdmissionListDetails ald = (AdmissionListDetails) toArray[i];
//          //                
//                          Integer pCode = ald.getCposGroup().getCampusProgramOfStudy().getProgramOfStudy().getPCode();
//                        
//                          if(Coder.Decoder.booleanDecode(ald.getActive())) {
//          //                    if(pCode.intValue() == code.intValue())
//                                  System.out.println(seatNo);
//                          }
//          //                Set set1 = ald.getPartRegistries();
//          //                if(set1 != null && !set1.isEmpty()) {
//          //                    continue;
//          //                }
//          //                Object[] toArray1 = set1.toArray();
//          //                if(ald.getCampusCategory().getCategory().getCode().intValue() == Constant.CategoryCodes.SELF_FINANCE_QUOTA) {
//          //                    b = false;
//          //                    continue;
//          //                }
//          //                b = true;
//          //                s += (ald.getCposGroup() + "(" + ald.getCampusCategory() + ")(" + ald.getCampusCategory().getShift() + ")(" + ald.getAdmissionList().getListNo() + "),");
//          //                for (int j = 0; j < toArray1.length; j++) {
//          //                    PartRegistry pr = (PartRegistry) toArray1[j];
//          //                    
//          //                }
//                      }
//                      if(b) 
//                      System.out.println(s);
        }

        System.exit(0);
    }
}


//~ Formatted by Jindent --- http://www.jindent.com
