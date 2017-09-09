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
//import utilities.Sorter;
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
//public class DataLoaderNew2014_BACHALOR_LAAR {
//    public static void main(String[] args) throws FileNotFoundException {
//        String cats[] = new String[] {
//            "", "", Constant.GM_DUR_QUOTA, Constant.SUE_QUOTA, Constant.CN_SUE_QUOTA, Constant.AC_QUOTA,
//            Constant.DP_QUOTA, Constant.SFM_QUOTA, Constant.SFE_QUOTA, Constant.NO_QUOTA, Constant.SP_QUOTA,
//            Constant.AP_QUOTA
//        };
//        ProgramType   pt           = (ProgramType) DatabaseManager.getSingleRecord(ProgramType.class.getName(), 1);
//        AdmissionYear ay           = (AdmissionYear) DatabaseManager.getSingleRecord(AdmissionYear.class.getName(), 3);
//        Campus        campus       = (Campus) DatabaseManager.getSingleRecord(Campus.class.getName(), 4);
//        Test          test         = (Test) DatabaseManager.getSingleRecord(Test.class.getName(), 5);
//        Shift         morningShift = (Shift) DatabaseManager.getSingleRecord(Shift.class.getName(), 1);
//
////      Shift eveningShift = (Shift) DatabaseManager.getSingleRecord(Shift.class.getName(), 2);
////      Campus cam = (Campus) DatabaseManager.getSingleRecord(Campus.class.getName(), 1);
//
//        if ((pt == null) || (ay == null) || (campus == null) || (test == null)) {
//            System.out.println("some item null");
//
//            return;
//        }
//
//        Scanner scan = new Scanner(new FileInputStream("LAAR_MADHIA.CSV"));
//
//        while (scan.hasNextLine()) {
//            String  lines    = scan.nextLine();
//            String  line[]   = lines.split(",");
//            Integer SEATNO   = Integer.parseInt(line[0]);
//            Integer FORMNO   = Integer.parseInt(line[1]);
//            String  SEX      = line[2];
//            Integer RELIGION = Integer.parseInt(line[3]);
//
////          String CATEG1 = line[4];
//            String  NAME     = line[6];
//            String  FNAME    = line[7];
//            String  SURNAME  = line[8];
//            Integer DISTRICT = Integer.parseInt(line[9]);
//            String  URBAN    = line[10];
//
////          String CHOICE1 = line[15];
////          String CHOICE2 = line[16];
////          String CHOICE3 = line[17];
////          String CHOICE4 = line[18];
////          String CHOICE5 = line[19];
//            byte    OBJECTION  = line[14].equals("Y")
//                                 ? (byte) 1
//                                 : (byte) 0;
//            String  OBJREMARKS = line[15];
//            Integer SSCGROUP   = Integer.parseInt(line[16]);
//            Integer SSCMOBT    = Integer.parseInt(line[17]);
//
////          String SSCLESS = line[37]);
//            Integer  SSCOUTOF  = Integer.parseInt(line[18]);
//            Integer  SSCYEAR   = Integer.parseInt(line[19]);
//            Integer  SSCSEATNO = Integer.parseInt(line[20]);
//            Integer  SSCBOARD  = Integer.parseInt(line[21]);
//            Integer  HSCGROUP  = Integer.parseInt(line[22]);
//            Integer  HSCMOBT   = Integer.parseInt(line[23]);
//            Integer  HSCOUTOF  = Integer.parseInt(line[24]);
//            Integer  HSCYEAR   = Integer.parseInt(line[25]);
//            Integer  HSCSEATNO = Integer.parseInt(line[26]);
//            Integer  HSCBOARD  = Integer.parseInt(line[27]);
//            Integer  TEST      = line[28].equals("N")
//                                 ? null
//                                 : Integer.parseInt(line[28]);
//            District dis       = (District) DatabaseManager.getSingleRecord(District.class.getName(),
//                                     "remarks='" + DISTRICT + "'");
//            String   cnicNo               = "";
//            String   cnicOf               = "";
//            String   permanentHomeAddress = "";
//            String   presentPostelAddress = "";
//            Country  country              = (Country) DatabaseManager.getSingleRecord(Country.class.getName(),
//                                                "name='PAKISTAN'");
//            Religion religion             = (Religion) DatabaseManager.getSingleRecord(Religion.class.getName(),
//                                                RELIGION);
//
//            if (religion == null) {
//                religion = (Religion) DatabaseManager.getSingleRecord(Religion.class.getName(), "name='ISLAM'");
//            }
//
//            Date   dateOfBirth       = null;
//            String placeOfBirth      = "";
//            String fathersOccupation = "";
//            String guardiansName     = "";
//            String relationship      = "";
//            String guardiansAddress  = "";
//            String telephone         = "";
//            String mobile            = "";
//            String email             = "";
//            Issuer sscIssuer         = (Issuer) DatabaseManager.getSingleRecord(Issuer.class.getName(),
//                                           "remarks='" + SSCBOARD + "'");
//            String sscGroup  = getChange(SSCGROUP);
//            Issuer hscIssuer = (Issuer) DatabaseManager.getSingleRecord(Issuer.class.getName(),
//                                   "remarks='" + HSCBOARD + "'");
//            String    hscGroup       = getChange(HSCGROUP);
//            Integer   chalanFee      = 0;
//            Date      chalanDate     = null;
//            Integer   amountRs       = 0;
//            String    remarks        = "";
//            Integer   testScore      = TEST;    // Integer.parseInt(arr[27].trim());
//            Float     percentage     = 0.0F;
//            Integer   deductionMarks = 0;
//            Candidate c              = new Candidate(pt, dis, test, ay, null, religion, country, SEATNO, FORMNO,
//                                           cnicNo, cnicOf, NAME, FNAME, SURNAME, SEX, URBAN, dateOfBirth, placeOfBirth,
//                                           permanentHomeAddress, presentPostelAddress, guardiansName, relationship,
//                                           guardiansAddress, fathersOccupation, telephone, mobile, email, chalanFee,
//                                           chalanDate, amountRs, 0, "", "", "", null, remarks, testScore, percentage,
//                                           deductionMarks, OBJECTION, OBJREMARKS, null, null, null, null, null, null, null,
//                                           null);
//            int id = DatabaseManager.addData(c);
//
//            if (id < 1) {
//                System.out.println(id);
//                System.exit(0);
//            }
//
//            c.setCandidateId(id);
//            DatabaseManager.addData(new CredentialDetails(sscIssuer, c, SSCOUTOF, SSCMOBT, sscGroup, SSCSEATNO,
//                    SSCYEAR, Constant.SSC, ""));
//            DatabaseManager.addData(new CredentialDetails(hscIssuer, c, HSCOUTOF, HSCMOBT, hscGroup, HSCSEATNO,
//                    HSCYEAR, Constant.HSC, ""));
//
//            AppliedCampus appliedCampus = new AppliedCampus(campus, c, "");
//
//            DatabaseManager.addData(appliedCampus);
//
//            for (int i = 4; i <= 5; i++) {
//                Integer         cat             = Integer.parseInt(line[i]);
//                AppliedCategory appliedCategory = new AppliedCategory(c, cats[cat], "");
//
//                DatabaseManager.addData(appliedCategory);
//            }
//
//            for (int i = 11; i <= 13; i++) {
//                String pCode = line[i].trim();
//
//                if (pCode.isEmpty() || pCode.equals("1") || pCode.equals("0")) {
//                    continue;
//                }
//
//                if (pCode.equals("40") || pCode.equals("41")) {
//                    pCode = "9";
//                }
//
//                CampusProgramOfStudy cpos = (CampusProgramOfStudy) DatabaseManager.getCpos(campus.getCampusId(),
//                                                Integer.parseInt(pCode), pt.getProgramTypeId(),
//                                                morningShift.getShiftId());
//
//                if (cpos == null) {
//                    continue;
//                }
//
//                DatabaseManager.addData(new CandidateProgramOfStudy(cpos, c, i - 10, "", null));
//            }
//
//            DatabaseManager.refresh(c);
//            deductionMarks = utilities.DeductionCalculation.forBachalor(ay.getYear(), c);
//            c.setDeductionMarks(deductionMarks);
//
//            if (c.getTestScore() != null) {
//                percentage = Sorter.getPercentage(c);
//                c.setPercentage(percentage);
//            }
//
//            DatabaseManager.updateData(c);
//            System.out.println(SEATNO + " : Done");
//        }
//
//        System.exit(0);
//    }
//
//    private static String getChange(int i) {
//        String[] s = new String[] {
//            "", "SC", "AR", "PM", "PE", "AR", "HM", "GS", "GSB", "CM", "CD", "", "OD"
//        };
//
//        return s[i];
//    }
//}
//
//
////~ Formatted by Jindent --- http://www.jindent.com
