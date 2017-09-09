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
//
///**
// *
// * @author Raja Kumar & Jay Kumar
// */
//public class DataLoader {
//    public static void main(String[] args) throws FileNotFoundException {
//        ProgramType   pt     = (ProgramType) DatabaseManager.getSingleRecord(ProgramType.class.getName(), 1);
//        AdmissionYear ay     = (AdmissionYear) DatabaseManager.getSingleRecord(AdmissionYear.class.getName(), 2);
//        Campus        campus = (Campus) DatabaseManager.getSingleRecord(Campus.class.getName(), 1);
//        Test          test   = (Test) DatabaseManager.getSingleRecord(Test.class.getName(), 1);
//        Shift         shift  = (Shift) DatabaseManager.getSingleRecord(Shift.class.getName(), 1);
//
//        if ((pt == null) || (ay == null) || (campus == null) || (test == null)) {
//            System.out.println("some item null");
//
//            return;
//        }
//
//        Scanner scan = new Scanner(new FileInputStream("data.txt"));
//
//        while (scan.hasNextLine()) {
//            String   line  = scan.nextLine();
//            String   arr[] = line.split(",");
//            District dis   = (District) DatabaseManager.getSingleRecord(District.class.getName(),
//                                 "name LIKE '" + arr[15].trim() + "%'");
//            Integer  seatNo               = Integer.parseInt(arr[0].trim());
//            String   gender               = arr[1].trim();
//            Integer  formSno              = null;
//            String   cnicNo               = "";
//            String   cnicOf               = "";
//            String   name                 = arr[10].trim();
//            String   fathersName          = arr[11].trim();
//            String   surname              = arr[12].trim();
//            String   area                 = arr[16].trim();
//            String   permanentHomeAddress = "";
//            String   presentPostelAddress = "";
//            Country  country              = (Country) DatabaseManager.getSingleRecord(Country.class.getName(),
//                                                "name='PAKISTAN'");
//            Religion religion             = (Religion) DatabaseManager.getSingleRecord(Religion.class.getName(),
//                                                "name='ISLAM'");
//            Date     dateOfBirth          = null;
//            String   placeOfBirth         = "";
//            String   fathersOccupation    = "";
//            String   guardiansName        = "";
//            String   relationship         = "";
//            String   guardiansAddress     = "";
//            String   telephone            = "";
//            String   mobile               = "";
//            String   email                = "";
//            Issuer   sscIssuer            = (Issuer) DatabaseManager.getSingleRecord(Issuer.class.getName(),
//                                                "location='" + arr[14].trim() + "'");
//            Integer sscTotalMarks    = Integer.parseInt(arr[54].trim());
//            Integer sscMarksObtained = Integer.parseInt(arr[18].trim());
//            Integer sscYear          = Integer.parseInt(arr[20].trim());
//            Integer sscSeatNo        = 0;
//            String  sscGroup         = "GS";
//            Issuer  hscIssuer        = (Issuer) DatabaseManager.getSingleRecord(Issuer.class.getName(),
//                                           "location='" + arr[14].trim() + "'");
//            Integer   hscTotalMarks    = Integer.parseInt(arr[53].trim());
//            Integer   hscMarksObtained = Integer.parseInt(arr[19].trim());
//            Integer   hscYear          = Integer.parseInt(arr[22].trim());
//            Integer   hscSeatNo        = 0;
//            String    hscGroup         = getChange(arr[13].trim());
//            Integer   chalanFee        = 0;
//            Date      chalanDate       = null;
//            Integer   amountRs         = 0;
//            Integer   yearsDegree      = null;
//            String    subject1         = "";
//            String    subject2         = "";
//            String    subject3         = "";
//            String    remarks          = "";
//            Integer   testScore        = Integer.parseInt(arr[27].trim());
//            Float     percentage       = testScore * 0.4F;
//            Integer   deductionMarks   = 0;
//            Candidate c                = new Candidate(pt, dis, test, ay, null, religion, country, seatNo, formSno,
//                                             cnicNo, cnicOf, name, fathersName, surname, gender, area, dateOfBirth,
//                                             placeOfBirth, permanentHomeAddress, presentPostelAddress, guardiansName,
//                                             relationship, guardiansAddress, fathersOccupation, telephone, mobile,
//                                             email, chalanFee, chalanDate, amountRs, yearsDegree, subject1, subject2,
//                                             subject3, null, remarks, testScore, percentage, deductionMarks, (byte) 1,
//                                             "", null, null, null, null, null, null, null, null);
//            int id = DatabaseManager.addData(c);
//
//            if (id < 1) {
//                System.out.println(id);
//                System.exit(0);
//            }
//
//            c.setCandidateId(id);
//            DatabaseManager.addData(new CredentialDetails(sscIssuer, c, sscTotalMarks, sscMarksObtained, sscGroup,
//                    sscSeatNo, sscYear, Constant.SSC, ""));
//            DatabaseManager.addData(new CredentialDetails(hscIssuer, c, hscTotalMarks, hscMarksObtained, hscGroup,
//                    hscSeatNo, hscYear, Constant.HSC, ""));
//
//            for (int i = 2; i <= 4; i++) {
//                String CAMP = arr[i].trim();
//
//                if (CAMP.isEmpty() || CAMP.equals("NI") || CAMP.equals("NIL")) {
//                    continue;
//                }
//
//                Campus        cam           = (Campus) DatabaseManager.getSingleRecord(Campus.class.getName(),
//                                                  "code='" + CAMP + "'");
//                AppliedCampus appliedCampus = new AppliedCampus(cam, c, "");
//
//                DatabaseManager.addData(appliedCampus);
//            }
//
//            for (int i = 5; i <= 9; i++) {
//                String CATEG = arr[i].trim();
//
//                if (CATEG.isEmpty() || CATEG.equals("NI") || CATEG.equals("NIL")) {
//                    continue;
//                }
//
//                String arg1;
//                String arg2 = "";
//
//                if (CATEG.equals("SUEBS") || CATEG.equals("SUEDS")) {
//                    arg1 = "SUE";
//                    arg2 = CATEG;
//                } else {
//                    arg1 = CATEG;
//                }
//
//                AppliedCategory appliedCategory = new AppliedCategory(c, arg1, arg2);
//
//                DatabaseManager.addData(appliedCategory);
//            }
//
//            for (int i = 33; i <= 42; i++) {
//                String pCode = arr[i].trim();
//
//                if (pCode.isEmpty() || pCode.equals("41")) {
//                    continue;
//                }
//
////              System.out.print(pCode + " : ");
//                CampusProgramOfStudy cpos = (CampusProgramOfStudy) DatabaseManager.getCpos(campus.getCampusId(),
//                                                Integer.parseInt(pCode), pt.getProgramTypeId(), shift.getShiftId());
//
////              System.out.println(cpos);
//                if (cpos == null) {
//                    continue;
//                }
//
//                DatabaseManager.addData(new CandidateProgramOfStudy(cpos, c, i - 32, "", null));
//            }
//
//            System.out.println(seatNo + " : Done");
//        }
//    }
//
//    private static String getChange(String data) {
//        switch (data) {
//        case "ENGG" :
//            data = "PE";
//
//            break;
//
//        case "MEDL" :
//            data = "PM";
//
//            break;
//
//        case "COMM" :
//            data = "PC";
//
//            break;
//
//        case "GENS" :
//            data = "GS";
//
//            break;
//
//        case "AODP" :
//            data = "AODP";
//
//            break;
//        }
//
//        return data;
//    }
//}
//
//
////~ Formatted by Jindent --- http://www.jindent.com
