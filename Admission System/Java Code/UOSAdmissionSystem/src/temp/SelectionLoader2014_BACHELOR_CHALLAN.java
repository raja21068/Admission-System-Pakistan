//
///*
//* To change this license header, choose License Headers in Project Properties.
//* To change this template file, choose Tools | Templates
//* and open the template in the editor.
// */
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
//import java.io.FileInputStream;
//import java.io.FileNotFoundException;
//
//import java.util.Date;
//import java.util.Scanner;
//import java.util.Set;
//
///**
// *
// * @author Yougeshwar
// */
//public class SelectionLoader2014_BACHELOR_CHALLAN {
//    public static void main(String[] args) throws FileNotFoundException {
//        Scanner scan = new Scanner(new FileInputStream("SEL_CHALLAN_MPS.csv"));
//        Part    part = (Part) DatabaseManager.getSingleRecord(Part.class.getName(), 3);
//
//        while (scan.hasNextLine()) {
//            String lines  = scan.nextLine();
//            String line[] = lines.split(",");
//            String seatNo = line[0];
//
////          String ccId = line[1];
//            String    cposgId   = line[1];
//            int       challanNo = Integer.parseInt(line[2]);
//            int       amount    = Integer.parseInt(line[3]);
//            Date      date      = DateFormatter.getStringToDate(line[4]);
//            Candidate cn        = (Candidate) DatabaseManager.getCandidate(3, 1, seatNo);
//            CposGroup cposg     = (CposGroup) DatabaseManager.getSingleRecord(CposGroup.class.getName(),
//                                      "cposGroupId = " + cposgId);
//
////
//            Set                  set = cn.getAdmissionListDetailses();
//            AdmissionListDetails ald = (AdmissionListDetails) set.toArray()[0];
//
////          AdmissionListDetails ald = DatabaseManager.getAdmissionListDetails(cn.getCandidateId(), cposg.getCposGroupId(), null);
////          System.out.println(seatNo + " : " + ald);
//            ald.setActive(Coder.Encoder.booleanEncode(true));
//            DatabaseManager.updateData(ald);
//
//            Account ac = new Account(cn, new Date(), Coder.Encoder.booleanEncode(true), "", null);
//            int     id = DatabaseManager.addData(ac);
//
//            if (id > 0) {
//                ac.setAccountId(id);
//            }
//
//            PartRegistry pr = new PartRegistry(null, ac, ald, part, challanNo, amount, date,
//                                               Constant.Challan.ADMISSION, "", null);
//
//            DatabaseManager.addData(pr);
//            System.out.println(seatNo + " : Done");
//        }
//
//        System.exit(0);
//    }
//}
//
//
////~ Formatted by Jindent --- http://www.jindent.com
