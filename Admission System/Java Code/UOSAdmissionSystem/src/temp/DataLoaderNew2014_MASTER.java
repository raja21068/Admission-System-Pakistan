//package temp;
//
////~--- non-JDK imports --------------------------------------------------------
//
//import admission.controller.DatabaseManager;
//
//import admission.model.AdmissionYear;
//import admission.model.AppliedCampus;
//import admission.model.AppliedCategory;
//import admission.model.Campus;
//import admission.model.CampusProgramOfStudy;
//import admission.model.Candidate;
//import admission.model.CandidateProgramOfStudy;
//import admission.model.Country;
//import admission.model.CredentialDetails;
//import admission.model.District;
//import admission.model.Issuer;
//import admission.model.ProgramType;
//import admission.model.Religion;
//import admission.model.Shift;
//import admission.model.Test;
//
//import utilities.Constant;
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
// * @author Raja Kumar & Jay Kumar
// */
//public class DataLoaderNew2014_MASTER {
//    public static void main(String[] args) throws FileNotFoundException {
//        ProgramType   pt     = (ProgramType) DatabaseManager.getSingleRecord(ProgramType.class.getName(), 2);
//        AdmissionYear ay     = (AdmissionYear) DatabaseManager.getSingleRecord(AdmissionYear.class.getName(), 3);
//        Campus        campus = (Campus) DatabaseManager.getSingleRecord(Campus.class.getName(), 1);
//        Scanner       scan   = new Scanner(new FileInputStream("data.txt"));
//
//        while (scan.hasNextLine()) {
//            String  line             = scan.nextLine();
//            String  arr[]            = line.split(",");
//            Integer seatNo           = Integer.parseInt(arr[3].trim());
//            Issuer  sscIssuer        = (Issuer) DatabaseManager.getSingleRecord(Issuer.class.getName(), 1);
//            Integer sscMarksObtained = Integer.parseInt(arr[54].trim());    // System.out.println(sscMarksObtained + " : " + seatNo);
//            Integer sscTotalMarks    = Integer.parseInt(arr[56].trim());
//            Integer sscYear          = Integer.parseInt(arr[57].trim());
//            Integer sscSeatNo        = Integer.parseInt(arr[58].trim());
//            String  sscGroup         = "GS";
//            Issuer  hscIssuer        = (Issuer) DatabaseManager.getSingleRecord(Issuer.class.getName(), 1);
//            Integer hscTotalMarks    = Integer.parseInt(arr[63].trim());
//            Integer hscMarksObtained = Integer.parseInt(arr[61].trim());
//            Integer hscYear          = Integer.parseInt(arr[64].trim());
//            Integer hscSeatNo        = Integer.parseInt(arr[65].trim());
//            String  hscGroup         = "";                                  // getChange(arr[13].trim());
//            Issuer  grdIssuer        = (Issuer) DatabaseManager.getSingleRecord(Issuer.class.getName(), 11);
//            Integer grdTotalMarks    = Integer.parseInt(arr[71].trim());
//            Integer grdMarksObtained = Integer.parseInt(arr[68].trim());
//            Integer grdYear          = Integer.parseInt(arr[72].trim());
//            Integer grdSeatNo        = Integer.parseInt(arr[73].trim());
//            String  grdGroup         = "";                                  // getChange(arr[13].trim());
//            Integer yearsDegree      = Integer.parseInt(arr[70].trim());    // grdPeriod
//
////          System.out.print(sscMarksObtained + ", " + sscTotalMarks + ", " + sscYear + ", " + sscSeatNo + ", ");
////          System.out.print(hscMarksObtained + ", " + hscTotalMarks + ", " + hscYear + ", " + hscSeatNo + ", ");
////          System.out.println(grdMarksObtained + ", " + grdTotalMarks + ", " + grdYear + ", " + grdSeatNo + ", " + yearsDegree);
//            Candidate candidate = DatabaseManager.getCandidate(ay.getAdmissionYearId(), pt.getProgramTypeId(),
//                                      String.valueOf(seatNo));
//
//            if (candidate == null) {
//                continue;
//            }
//
//            candidate.setYearsDegree(yearsDegree);
//
//            Set               set     = null;//candidate.getCredentialDetailses();
//            Object[]          toArray = set.toArray();
//            CredentialDetails matric  = (CredentialDetails) toArray[0];
//            CredentialDetails inter   = (CredentialDetails) toArray[1];
//            CredentialDetails degree  = (CredentialDetails) toArray[2];
//
//            matric.setIssuer(sscIssuer);
//            matric.setMarksObtained(sscMarksObtained);
//            matric.setPassingYear(sscYear);
//            matric.setSeatNo(sscSeatNo);
//            matric.setTotalMarks(Float.parseFloat(sscTotalMarks + ""));
//            inter.setIssuer(hscIssuer);
//            inter.setMarksObtained(hscMarksObtained);
//            inter.setPassingYear(hscYear);
//            inter.setSeatNo(hscSeatNo);
//            inter.setTotalMarks(Float.parseFloat(hscTotalMarks + ""));
//            degree.setIssuer(grdIssuer);
//            degree.setMarksObtained(grdMarksObtained);
//            degree.setPassingYear(grdYear);
//            degree.setSeatNo(grdSeatNo);
//            degree.setTotalMarks(Float.parseFloat(grdTotalMarks + ""));
//            DatabaseManager.updateData(matric);
//            DatabaseManager.updateData(inter);
//            DatabaseManager.updateData(degree);
//            DatabaseManager.updateData(candidate);
//            System.out.println(seatNo + " : Done");
//        }
//
//        System.exit(0);
//    }
//}
//
//
////~ Formatted by Jindent --- http://www.jindent.com
