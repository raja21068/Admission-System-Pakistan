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
//import admission.model.AdmissionList;
//import admission.model.AdmissionListDetails;
//import admission.model.CampusCategory;
//import admission.model.Candidate;
//import admission.model.CandidateProgramOfStudy;
//import admission.model.CposGroup;
//
////~--- JDK imports ------------------------------------------------------------
//
//import java.io.FileInputStream;
//import java.io.FileNotFoundException;
//
//import java.util.Scanner;
//
///**
// *
// * @author Yougeshwar
// */
//public class SelectionLoader2014_BACHELOR {
//    public static void main(String[] args) throws FileNotFoundException {
//        Scanner       scan = new Scanner(new FileInputStream("SEL_MPS.csv"));
//        AdmissionList al   = (AdmissionList) DatabaseManager.getSingleRecord(AdmissionList.class.getName(), 5);
//
//        while (scan.hasNextLine()) {
//            String         lines    = scan.nextLine();
//            String         line[]   = lines.split(",");
//            String         seatNo   = line[0];
//            String         choiceNo = line[1];
//            String         ccId     = line[2];
//            String         cposgId  = line[3];
//            Candidate      cn       = (Candidate) DatabaseManager.getSingleRecord(Candidate.class.getName(),
//                                          "seatNo = " + seatNo);
//            CampusCategory cc       = (CampusCategory) DatabaseManager.getSingleRecord(CampusCategory.class.getName(),
//                                          "campusCategoryId = " + ccId);
//            CposGroup cposg = (CposGroup) DatabaseManager.getSingleRecord(CposGroup.class.getName(),
//                                  "cposGroupId = " + cposgId);
//            CandidateProgramOfStudy cnpos = DatabaseManager.getCandidateProgramOfStudy(cn.getCandidateId(),
//                                                cposg.getCampusProgramOfStudy().getCampusProgramOfStudyId(), choiceNo);
//
////          CandidateProgramOfStudy cnpos = (CandidateProgramOfStudy) DatabaseManager.getSingleRecord(CandidateProgramOfStudy.class.getName(), "candidateId = " + cn.getCandidateId() + " AND choiceNo = " + choiceNo);
//            AdmissionListDetails ald = new AdmissionListDetails(cc, al, cposg, cnpos, cn, "");
//
//            DatabaseManager.addData(ald);
//            System.out.println(seatNo + " : Done");
//        }
//
//        System.exit(0);
//    }
//}
//
//
////~ Formatted by Jindent --- http://www.jindent.com
