//package temp;
//
////~--- non-JDK imports --------------------------------------------------------
//
//import admission.controller.DatabaseManager;
//
//import admission.model.Account;
//import admission.model.AdmissionList;
//import admission.model.AdmissionListDetails;
//import admission.model.CampusCategory;
//import admission.model.Candidate;
//import admission.model.CandidateProgramOfStudy;
//import admission.model.CposGroup;
//import admission.model.Part;
//import admission.model.PartRegistry;
//
//import utilities.Coder;
//import utilities.Constant;
//import utilities.DateFormatter;
//
////~--- JDK imports ------------------------------------------------------------
//
//import java.io.File;
//import java.io.FileNotFoundException;
//import java.io.IOException;
//
//import java.nio.charset.Charset;
//import java.nio.file.Paths;
//
//import java.text.ParseException;
//
//import java.util.Date;
//import java.util.List;
//import java.util.Set;
//
///**
// *
// * @author Yougeshwar
// */
//public class ChallanEntry {
//    public static void main(String[] args) throws FileNotFoundException, IOException, ParseException {
//        List<String> lines = java.nio.file.Files.readAllLines(Paths.get(new File("bhpd.csv").toURI()),
//                                 Charset.forName("UTF-8"));
//        Part           part  = (Part) DatabaseManager.getSingleRecord(Part.class.getName(), 1);
//        CampusCategory cc    = (CampusCategory) DatabaseManager.getSingleRecord(CampusCategory.class.getName(), 85);
//        CposGroup      cposg = (CposGroup) DatabaseManager.getSingleRecord(CposGroup.class.getName(), 154);
//        AdmissionList  al    = (AdmissionList) DatabaseManager.getSingleRecord(AdmissionList.class.getName(), 14);
//
//        // ONLY FEE
//        for (int i = 0; i < lines.size(); i++) {
//            String line = lines.get(i);
//
//            if (line.isEmpty()) {
//                continue;
//            }
//
//            String[] tokens = line.split(",");
//            String   seatNo = tokens[0];
//
////          String posCode = tokens[1];
////          boolean isMorning = tokens[2].equals("0");
//            String    challanNo = tokens[1];
//            String    amount    = tokens[2];
//            String    date      = tokens[3];
//            Candidate candidate = DatabaseManager.getCandidate(3, 2, seatNo);
//
//            if (candidate == null) {
//                System.out.println("(Not Found) " + tokens[0] + "," + tokens[1] + ",=,NIL");
//
//                continue;
//            }
//
//            Set set = candidate.getCandidateProgramOfStudies();
//
//            if (set == null) {
//                System.out.println(seatNo + " NO");
//
//                continue;
//            }
//
//            CandidateProgramOfStudy cnpos   = null;
//            Object[]                toArray = set.toArray();
//
//            for (Object toArray1 : toArray) {
//                CandidateProgramOfStudy temp = (CandidateProgramOfStudy) toArray1;
//
//                if (temp.getCampusProgramOfStudy().getCampusProgramOfStudyId().intValue() == 166) {
//                    cnpos = temp;
//
//                    break;
//                }
//            }
//
//            System.out.println(cnpos);
//
////          Set set = candidate.getAdmissionListDetailses();
////          if(set == null){
////              System.out.println(tokens[0] + "," + tokens[1] + ",=,NIL");
////              continue;
////          }
//
//            Account account;
//            Set     accounts = candidate.getAccounts();
//
//            if ((accounts == null) || accounts.isEmpty()) {
//                account = new Account(candidate, new Date(), Coder.Encoder.booleanEncode(true), "", null);
//
//                int id = DatabaseManager.addData(account);
//
//                account.setAccountId(id);
//            } else {
//                account = (Account) accounts.toArray()[0];
//            }
//
//            AdmissionListDetails ald = new AdmissionListDetails(cc, al, cposg, cnpos, candidate, 0f, 0f, 0f, 0f, 0f,
//                                           candidate.getPercentage(), "S", Coder.Encoder.booleanEncode(true), null, "",
//                                           null);
//            int id = DatabaseManager.addData(ald);
//
//            ald.setAdmissionListDetailsId(id);
//
//            PartRegistry pt = new PartRegistry(null, account, ald, part, Integer.parseInt(challanNo),
//                                               Integer.parseInt(amount), DateFormatter.getStringToDate(date),
//                                               Constant.Challan.ADMISSION, "", null);
//
//            DatabaseManager.addData(pt);
//
////          ald.setActive(Coder.Encoder.booleanEncode(true));
////          DatabaseManager.updateData(ald);
//            System.out.println((i + 1) + " : " + seatNo);
//        }
//
//        // FEE WITH RETAIN
////      for (int i = 284; i < 307; i++) {
////          String line = lines.get(i);
////          String[] tokens = line.split(",");
////          
////          String seatNo = tokens[0];
////          String posCode = tokens[1];
////          boolean isMorning = tokens[2].equals("1");
////          String challanNo = tokens[3];
////          String amount = tokens[4];
////          String date = tokens[5];
////          String challanNo2 = tokens[6];
////          String amount2 = tokens[7];
////          String date2 = tokens[8];
////          
////          Candidate candidate = DatabaseManager.getCandidate(3, 2, seatNo);
////          if(candidate == null) {
////              System.out.println("(Not Found) " + tokens[0] + "," + tokens[1] + ",=,NIL");
////              continue;
////          }
////          Set set = candidate.getAdmissionListDetailses();
////          if(set == null){
////              System.out.println(tokens[0] + "," + tokens[1] + ",=,NIL");
////              continue;
////          }
////          
////          Account account;
////          Set accounts = candidate.getAccounts();
////          if(accounts == null || accounts.isEmpty()) {
////              account = new Account(candidate, new Date(), "", Coder.Encoder.booleanEncode(true), "", null);
////              int id = DatabaseManager.addData(account);
////              account.setAccountId(id);
////          } else {
////              account = (Account) accounts.toArray()[0];
////          }
////          
////          Object[] toArray = set.toArray();
////          for (Object toArray1 : toArray) {
////              AdmissionListDetails ald = (AdmissionListDetails) toArray1;
////              
////              Integer pCode = ald.getCposGroup().getCampusProgramOfStudy().getProgramOfStudy().getPCode();
////              boolean shift = Coder.Decoder.booleanDecode(ald.getCampusCategory().getShift().getIsMorning());
////              if(posCode.equals("" + pCode) && (shift == isMorning)){
////                  PartRegistry pt = new PartRegistry(null, account, ald, part, Integer.parseInt(challanNo), Integer.parseInt(amount), DateFormatter.getStringToDate(date), Constant.Challan.ADMISSION, "", null);
////                  DatabaseManager.addData(pt);
////                  pt = new PartRegistry(null, account, ald, part, Integer.parseInt(challanNo2), Integer.parseInt(amount2), DateFormatter.getStringToDate(date2), Constant.Challan.RETAIN, "", null);
////                  DatabaseManager.addData(pt);
////              
////                  ald.setActive(Coder.Encoder.booleanEncode(true));
////                  DatabaseManager.updateData(ald);
////              
////                  break;
////              }
////          }
////          System.out.println(i + " : " + seatNo);
////      }
////      // ONLY RETAIN
////      for (int i = 307; i < lines.size(); i++) {
////          String line = lines.get(i);
////          String[] tokens = line.split(",");
////          
////          String seatNo = tokens[0];
////          String challanNo2 = tokens[6];
////          String amount2 = tokens[7];
////          String date2 = tokens[8];
////          
////          Candidate candidate = DatabaseManager.getCandidate(3, 2, seatNo);
////          if(candidate == null) {
////              System.out.println("(Not Found) " + tokens[0] + "," + tokens[1] + ",=,NIL");
////              continue;
////          }
////          Set set = candidate.getAdmissionListDetailses();
////          if(set == null){
////              System.out.println(tokens[0] + "," + tokens[1] + ",=,NIL");
////              continue;
////          }
////          
////          Account account;
////          Set accounts = candidate.getAccounts();
////          
////          if(accounts == null || accounts.isEmpty()) {
////              System.out.println("Account not created : " + seatNo);
////              continue;
////          }
////          
////          account = (Account) accounts.toArray()[0];
////          
////          Object[] toArray = set.toArray();
////          for (Object toArray1 : toArray) {
////              AdmissionListDetails ald = (AdmissionListDetails) toArray1;
////              Set partRegistries = ald.getPartRegistries();
////              if(partRegistries == null || partRegistries.isEmpty()) continue;
////              
////              PartRegistry pt = new PartRegistry(null, account, ald, part, Integer.parseInt(challanNo2), Integer.parseInt(amount2), DateFormatter.getStringToDate(date2), Constant.Challan.RETAIN, "", null);
////              DatabaseManager.addData(pt);
////              
////              ald.setActive(Coder.Encoder.booleanEncode(true));
////              DatabaseManager.updateData(ald);
////              
////              break;
////          }
////          System.out.println(i + " : " + seatNo);
////      }
//        System.exit(0);
//
//        /*
//         *  for (int i = 782; i < lines.size(); i++) {
//         *    String line = lines.get(i);
//         *    String[] tokens = line.split(",");
//         *
//         *    String seatNo = tokens[0];
//         *    String posCode = tokens[1];
//         *    String challanNo = tokens[2];
//         *    String amount = tokens[3];
//         *    String date = tokens[4];
//         *    String challanNo2 = tokens[6];
//         *    String amount2 = tokens[7];
//         *    String date2 = tokens[8];
//         *
//         *    Candidate candidate = DatabaseManager.getCandidate(3, 2, seatNo);
//         *    if(candidate == null) {
//         *        System.out.println("(Not Found) " + tokens[0] + "," + tokens[1] + ",=,NIL");
//         *        continue;
//         *    }
//         *    Set set = candidate.getAdmissionListDetailses();
//         *    if(set == null){
//         *        System.out.println(tokens[0] + "," + tokens[1] + ",=,NIL");
//         *        continue;
//         *    }
//         *
//         *    Object[] toArray = set.toArray();
//         *    for (Object toArray1 : toArray) {
//         *        AdmissionListDetails ald = (AdmissionListDetails) toArray1;
//         *        Account account = new Account(candidate, null, "", Coder.Encoder.booleanEncode(true), "", null);
//         *        int id = DatabaseManager.addData(account);
//         *        account.setAccountId(id);
//         *
//         *        PartRegistry pt = new PartRegistry(null, account, ald, part, Integer.parseInt(challanNo), Integer.parseInt(amount), DateFormatter.getStringToDate(date), Constant.Challan.ADMISSION, "1st canceled list Admission: General Merit/Quota, " + posCode + ", Fee submited: Rs." + amount, null);
//         *        DatabaseManager.addData(pt);
//         *        pt = new PartRegistry(null, account, ald, part, Integer.parseInt(challanNo2), Integer.parseInt(amount2), DateFormatter.getStringToDate(date2), Constant.Challan.RETAIN, "1st canceled list Admission: General Merit/Quota, " + posCode + ", Retain Fee submited: Rs." + amount2, null);
//         *        DatabaseManager.addData(pt);
//         *
//         *        ald.setActive(Coder.Encoder.booleanEncode(true));
//         *        DatabaseManager.updateData(ald);
//         *
//         *        break;
//         *    }
//         *    System.out.println(i + " : " + seatNo);
//         * }
//         */
//    }
//}
//
//
////~ Formatted by Jindent --- http://www.jindent.com
