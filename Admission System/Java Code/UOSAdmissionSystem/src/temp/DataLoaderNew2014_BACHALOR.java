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
//import java.util.Set;
//
///**
// *
// * @author Raja Kumar & Jay Kumar
// */
//public class DataLoaderNew2014_BACHALOR {
//    public static void main(String[] args) throws FileNotFoundException {
//        String cats[] = new String[] {
//            "", "", Constant.GM_DUR_QUOTA, Constant.SUE_QUOTA, Constant.CN_SUE_QUOTA, Constant.AC_QUOTA,
//            Constant.DP_QUOTA, Constant.SFM_QUOTA, Constant.SFE_QUOTA, Constant.NO_QUOTA, Constant.SP_QUOTA,
//            Constant.AP_QUOTA
//        };
//        ProgramType   pt           = (ProgramType) DatabaseManager.getSingleRecord(ProgramType.class.getName(), 1);
//        AdmissionYear ay           = (AdmissionYear) DatabaseManager.getSingleRecord(AdmissionYear.class.getName(), 3);
//        Campus        campus       = (Campus) DatabaseManager.getSingleRecord(Campus.class.getName(), 1);
//        Test          test         = (Test) DatabaseManager.getSingleRecord(Test.class.getName(), 3);
//        Shift         morningShift = (Shift) DatabaseManager.getSingleRecord(Shift.class.getName(), 1);
//        Shift         eveningShift = (Shift) DatabaseManager.getSingleRecord(Shift.class.getName(), 2);
//        Campus        cam          = (Campus) DatabaseManager.getSingleRecord(Campus.class.getName(), 1);
//
//        if ((pt == null) || (ay == null) || (campus == null) || (test == null)) {
//            System.out.println("some item null");
//
//            return;
//        }
//
//        Scanner scan = new Scanner(new FileInputStream("batch2014.txt"));
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
////          String CATEG2 = line[5];
////          String CATEG3 = line[6];
////          String CATEG4 = line[7];
////          String CATEG5 = line[8];
////          String CATEG6 = line[9];
//            String  NAME     = line[10];
//            String  FNAME    = line[11];
//            String  SURNAME  = line[12];
//            Integer DISTRICT = Integer.parseInt(line[13]);
//            String  URBAN    = line[14];
//
////          String CHOICE1 = line[15];
////          String CHOICE2 = line[16];
////          String CHOICE3 = line[17];
////          String CHOICE4 = line[18];
////          String CHOICE5 = line[19];
////          String CHOICE6 = line[20];
////          String CHOICE7 = line[21];
////          String CHOICE8 = line[22];
////          String CHOICE9 = line[23];
////          String CHOICE10 = line[24];
////          String SFCHOICE1 = line[25];
////          String SFCHOICE2 = line[26];
////          String SFCHOICE3 = line[27];
////          String SFCHOICE4 = line[28];
////          String SFCHOICE5 = line[29];
////          String SFCHOICE6 = line[30];
////          String SFCHOICE7 = line[31];
////          String SFCHOICE8 = line[32];
////          String SFCHOICE9 = line[33];
////          String SFCHOICE10 = line[34];
//            Integer SSCGROUP = Integer.parseInt(line[35]);
//            Integer SSCMOBT  = Integer.parseInt(line[36]);
//
////          String SSCLESS = line[37]);
//            Integer SSCOUTOF  = Integer.parseInt(line[38]);
//            Integer SSCYEAR   = Integer.parseInt(line[39]);
//            Integer SSCSEATNO = Integer.parseInt(line[40]);
//            Integer SSCBOARD  = Integer.parseInt(line[41]);
//            Integer HSCGROUP  = Integer.parseInt(line[42]);
//            Integer HSCMOBT   = Integer.parseInt(line[43]);
//
////          String HSCLESS = line[44];
//            Integer  HSCOUTOF  = Integer.parseInt(line[45]);
//            Integer  HSCYEAR   = Integer.parseInt(line[46]);
//            Integer  HSCSEATNO = Integer.parseInt(line[47]);
//            Integer  HSCBOARD  = Integer.parseInt(line[48]);
//            District dis       = (District) DatabaseManager.getSingleRecord(District.class.getName(),
//                                     "remarks='" + DISTRICT + "'");
//
////          Integer seatNo = Integer.parseInt(arr[0].trim()); 
////          String gender = arr[2].trim();
////          Integer formSno = Integer.parseInt(arr[1].trim());
//            String cnicNo = "";
//            String cnicOf = "";
//
////          String name = arr[10].trim();
////          String fathersName = arr[11].trim();
////          String surname = arr[12].trim();
////          String area = arr[14].trim();
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
//
////          Integer sscTotalMarks = Integer.parseInt(arr[41].trim());
////          Integer sscMarksObtained = Integer.parseInt(arr[39].trim()); //System.out.println(sscMarksObtained + " : " + seatNo);
////          Integer sscYear = Integer.parseInt(arr[42].trim());
////          Integer sscSeatNo = Integer.parseInt(arr[43].trim());
//            String sscGroup  = getChange(SSCGROUP);
//            Issuer hscIssuer = (Issuer) DatabaseManager.getSingleRecord(Issuer.class.getName(),
//                                   "remarks='" + HSCBOARD + "'");
//
////          Integer hscTotalMarks = Integer.parseInt(arr[49].trim());
////          Integer hscMarksObtained = Integer.parseInt(arr[47].trim());
////          Integer hscYear = Integer.parseInt(arr[52].trim());
////          Integer hscSeatNo = Integer.parseInt(arr[51].trim());
//            String hscGroup = getChange(HSCGROUP);
//
////          Issuer grdIssuer = (Issuer) DatabaseManager.getSingleRecord(Issuer.class.getName(), 1);
////          Integer grdTotalMarks = Integer.parseInt(arr[57].trim());
////          Integer grdMarksObtained = Integer.parseInt(arr[54].trim());
////          Integer grdYear = Integer.parseInt(arr[58].trim());
////          Integer grdSeatNo = Integer.parseInt(arr[59].trim());
////          String grdGroup = "";//getChange(arr[13].trim());
////          Integer yearsDegree = Integer.parseInt(arr[56].trim()); //grdPeriod
//            Integer chalanFee  = 0;
//            Date    chalanDate = null;
//            Integer amountRs   = 0;
//
////          Integer yearsDegree = null;
////          String subject1 = arr[15].trim();
////          String subject2 = arr[16].trim();
////          String subject3 = arr[17].trim();
//            String    remarks        = "";
//            Integer   testScore      = 0;    // Integer.parseInt(arr[27].trim());
//            Float     percentage     = 0.0F;
//            Integer   deductionMarks = 0;
//            Candidate c              = new Candidate(pt, dis, test, ay, null, religion, country, SEATNO, FORMNO,
//                                           cnicNo, cnicOf, NAME, FNAME, SURNAME, SEX, URBAN, dateOfBirth, placeOfBirth,
//                                           permanentHomeAddress, presentPostelAddress, guardiansName, relationship,
//                                           guardiansAddress, fathersOccupation, telephone, mobile, email, chalanFee,
//                                           chalanDate, amountRs, 0, "", "", "", null, remarks, testScore, percentage,
//                                           deductionMarks, (byte) 1, "", null, null, null, null, null, null, null, null);
//
////          Candidate c = new Candidate(pt, dis, test, ay, null, religion, country, seatNo, formSno, cnicNo, cnicOf, name, fathersName, surname, gender, area, dateOfBirth, placeOfBirth, permanentHomeAddress, presentPostelAddress, guardiansName, relationship, guardiansAddress, fathersOccupation, telephone, mobile, email, chalanFee, chalanDate, amountRs, yearsDegree, subject1, subject2, subject3, null, remarks, testScore, percentage, deductionMarks, null, null, null, null, null, null, null);
//            int id = DatabaseManager.addData(c);
//
//            if (id < 1) {
//                System.out.println(id);
//                System.exit(0);
//            }
//
//            c.setCandidateId(id);
//            DatabaseManager.addData(new CredentialDetails(sscIssuer, c, SSCOUTOF, SSCMOBT, sscGroup, SSCGROUP, SSCYEAR,
//                    Constant.SSC, ""));
//            DatabaseManager.addData(new CredentialDetails(hscIssuer, c, HSCOUTOF, HSCMOBT, hscGroup, HSCSEATNO,
//                    HSCYEAR, Constant.HSC, ""));
//
////          DatabaseManager.addData(new CredentialDetails(grdIssuer, c, grdTotalMarks, grdMarksObtained, grdGroup, grdSeatNo, grdYear, Constant.GRADUATION, ""));
////          
//            AppliedCampus appliedCampus = new AppliedCampus(cam, c, "");
//
//            DatabaseManager.addData(appliedCampus);
//
////        
//            for (int i = 4; i <= 9; i++) {
//                int cc = Integer.parseInt(line[i].trim());
//
//                if (cc == 1) {
//                    continue;
//                }
//
//                String          CATEG           = cats[cc].trim();
//                AppliedCategory appliedCategory = new AppliedCategory(c, CATEG, "");
//
//                DatabaseManager.addData(appliedCategory);
//            }
//
////        
//            for (int i = 15; i <= 24; i++) {
//                String pCode = line[i].trim();
//
//                if (pCode.isEmpty() || pCode.equals("1") || pCode.equals("0")) {
//                    continue;
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
//                DatabaseManager.addData(new CandidateProgramOfStudy(cpos, c, i - 14, "", null));
//            }
//
//            for (int i = 25; i <= 34; i++) {
//                String pCode = line[i].trim();
//
//                if (pCode.isEmpty() || pCode.equals("1") || pCode.equals("0")) {
//                    continue;
//                }
//
//                CampusProgramOfStudy cpos = (CampusProgramOfStudy) DatabaseManager.getCpos(campus.getCampusId(),
//                                                Integer.parseInt(pCode), pt.getProgramTypeId(),
//                                                eveningShift.getShiftId());
//
//                if (cpos == null) {
//                    continue;
//                }
//
//                DatabaseManager.addData(new CandidateProgramOfStudy(cpos, c, i - 24, "", null));
//            }
//
////        
//            DatabaseManager.refresh(c);
//            deductionMarks = utilities.DeductionCalculation.forBachalor(ay.getYear(), c);
//            c.setDeductionMarks(deductionMarks);
//            DatabaseManager.updateData(c);
//
////          float percentage1 = Sorter.getPercentage(c);
//            System.out.println(SEATNO + " : Done");
//
////          }
////          
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
