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
//import admission.model.Program;
//import admission.model.ProgramType;
//import admission.model.Religion;
//import admission.model.Shift;
//import admission.model.Subject;
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
//public class DataLoaderNew2014 {
//    public static void main(String[] args) throws FileNotFoundException {
//        String cats[] = new String[] {
//            "", "", Constant.GM_DUR_QUOTA, Constant.SUE_QUOTA, Constant.CN_SUE_QUOTA, Constant.AC_QUOTA,
//            Constant.DP_QUOTA, Constant.SFM_QUOTA, Constant.SFE_QUOTA, Constant.NO_QUOTA, Constant.SP_QUOTA,
//            Constant.AP_QUOTA
//        };
//        ProgramType   pt           = (ProgramType) DatabaseManager.getSingleRecord(ProgramType.class.getName(), 2);
//        AdmissionYear ay           = (AdmissionYear) DatabaseManager.getSingleRecord(AdmissionYear.class.getName(), 3);
//        Campus        campus       = (Campus) DatabaseManager.getSingleRecord(Campus.class.getName(), 1);
//        Test          test         = (Test) DatabaseManager.getSingleRecord(Test.class.getName(), 2);
//        Shift         morningShift = (Shift) DatabaseManager.getSingleRecord(Shift.class.getName(), 1);
//        Shift         eveningShift = (Shift) DatabaseManager.getSingleRecord(Shift.class.getName(), 2);
//
//        if ((pt == null) || (ay == null) || (campus == null) || (test == null)) {
//            System.out.println("some item null");
//
//            return;
//        }
//
//        Scanner scan = new Scanner(new FileInputStream("MASTER_CSV.csv"));
//
//        while (scan.hasNextLine()) {
//            String   line     = scan.nextLine();
//            String   arr[]    = line.split(",");
//            Integer  seatNo   = Integer.parseInt(arr[0].trim());
//            Integer  formSno  = Integer.parseInt(arr[1].trim());
//            String   gender   = arr[2].trim();
//            Religion religion = (Religion) DatabaseManager.getSingleRecord(Religion.class.getName(),
//                                    Integer.parseInt(arr[3].trim()));
//
//            if (religion == null) {
//                religion = (Religion) DatabaseManager.getSingleRecord(Religion.class.getName(), "name='ISLAM'");
//            }
//
//            String   cnicNo      = "";
//            String   cnicOf      = "";
//            String   name        = arr[10].trim();
//            String   fathersName = arr[11].trim();
//            String   surname     = arr[12].trim();
//            District dis         = (District) DatabaseManager.getSingleRecord(District.class.getName(),
//                                       "remarks='" + arr[13].trim() + "'");
//            String  area        = arr[14].trim();
//            Subject subjectObj1 = (Subject) DatabaseManager.getSingleRecord(Subject.class.getName(),
//                                      "remarks='" + arr[15].trim() + "'");
//            Subject subjectObj2 = (Subject) DatabaseManager.getSingleRecord(Subject.class.getName(),
//                                      "remarks='" + arr[16].trim() + "'");
//            Subject subjectObj3 = (Subject) DatabaseManager.getSingleRecord(Subject.class.getName(),
//                                      "remarks='" + arr[17].trim() + "'");
//            String  subject1             = ((subjectObj1 == null)
//                                            ? ""
//                                            : "" + subjectObj1.getSubjectId());
//            String  subject2             = ((subjectObj2 == null)
//                                            ? ""
//                                            : "" + subjectObj2.getSubjectId());
//            String  subject3             = ((subjectObj3 == null)
//                                            ? ""
//                                            : "" + subjectObj3.getSubjectId());
//            String  permanentHomeAddress = "";
//            String  presentPostelAddress = "";
//            Country country              = (Country) DatabaseManager.getSingleRecord(Country.class.getName(),
//                                               "name='PAKISTAN'");
//            Date    dateOfBirth          = null;
//            String  placeOfBirth         = "";
//            String  fathersOccupation    = "";
//            String  guardiansName        = "";
//            String  relationship         = "";
//            String  guardiansAddress     = "";
//            String  telephone            = "";
//            String  mobile               = "";
//            String  email                = "";
//            byte    OBJECTION            = arr[38].equals("Y")
//                                           ? (byte) 1
//                                           : (byte) 0;
//            String  OBJREMARKS           = arr[39];
//            Integer sscGroup             = Integer.parseInt(arr[40]);
//            Integer sscMarksObtained     = Integer.parseInt(arr[41].trim());    // System.out.println(sscMarksObtained + " : " + seatNo);
//            Integer sscTotalMarks = Integer.parseInt(arr[42].trim());
//            Integer sscYear       = Integer.parseInt(arr[43].trim());
//            Integer sscSeatNo     = Integer.parseInt(arr[44].trim());
//            Issuer  sscIssuer     = (Issuer) DatabaseManager.getSingleRecord(Issuer.class.getName(),
//                                        "remarks='" + arr[45] + "'");
//            Integer hscGroup         = Integer.parseInt(arr[46]);    // getChange(arr[13].trim());
//            Integer hscMarksObtained = Integer.parseInt(arr[47].trim());
//            Integer hscTotalMarks    = Integer.parseInt(arr[48].trim());
//            Integer hscYear          = Integer.parseInt(arr[49].trim());
//            Integer hscSeatNo        = Integer.parseInt(arr[50].trim());
//            Issuer  hscIssuer        = (Issuer) DatabaseManager.getSingleRecord(Issuer.class.getName(),
//                                           "remarks='" + arr[51] + "'");
//            Program grdGroup = (Program) DatabaseManager.getSingleRecord(Program.class.getName(),
//                                   "remarks='" + arr[52] + "'");    // getChange(arr[13].trim());
//            Integer grdMarksObtained = Integer.parseInt(arr[53].trim());
//            Integer yearsDegree      = Integer.parseInt(arr[54].trim());    // grdPeriod
//            Integer grdTotalMarks    = Integer.parseInt(arr[55].trim());
//            Integer grdYear          = Integer.parseInt(arr[56].trim());
//            Integer grdSeatNo        = Integer.parseInt(arr[57].trim());
//            Issuer  grdIssuer        = (Issuer) DatabaseManager.getSingleRecord(Issuer.class.getName(),
//                                           "code=" + arr[58]);
//            Integer chalanFee        = 0;
//            Date    chalanDate       = null;
//            Integer amountRs         = 0;
//
////          Integer yearsDegree = null;
//            String    remarks        = "";
//            Integer   testScore      = Integer.parseInt(arr[59].trim());
//            Float     percentage     = 0.0F;
//            Integer   deductionMarks = 0;
//            Candidate c              = new Candidate(pt, dis, test, ay, null, religion, country, seatNo, formSno,
//                                           cnicNo, cnicOf, name, fathersName, surname, gender, area, dateOfBirth,
//                                           placeOfBirth, permanentHomeAddress, presentPostelAddress, guardiansName,
//                                           relationship, guardiansAddress, fathersOccupation, telephone, mobile, email,
//                                           chalanFee, chalanDate, amountRs, yearsDegree, subject1, subject2, subject3,
//                                           null, remarks, testScore, percentage, deductionMarks, OBJECTION, "", null,
//                                           null, null, null, null, null, null, null);
//            int id = DatabaseManager.addData(c);
//
//            if (id < 1) {
//                System.out.println(id);
//                System.exit(0);
//            }
//
//            c.setCandidateId(id);
//            DatabaseManager.addData(new CredentialDetails(sscIssuer, c, sscTotalMarks, sscMarksObtained,
//                    getChange(sscGroup), sscSeatNo, sscYear, Constant.SSC, ""));
//            DatabaseManager.addData(new CredentialDetails(hscIssuer, c, hscTotalMarks, hscMarksObtained,
//                    getChange(hscGroup), hscSeatNo, hscYear, Constant.HSC, ""));
//            DatabaseManager.addData(new CredentialDetails(grdIssuer, c, grdTotalMarks, grdMarksObtained,
//                    "" + grdGroup.getProgramId(), grdSeatNo, grdYear, Constant.GRADUATION, ""));
//
//            Campus        cam           = (Campus) DatabaseManager.getSingleRecord(Campus.class.getName(), 1);
//            AppliedCampus appliedCampus = new AppliedCampus(cam, c, "");
//
//            DatabaseManager.addData(appliedCampus);
//
//            for (int i = 4; i <= 9; i++) {
//                int cc = Integer.parseInt(arr[i].trim());
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
//            for (int i = 18; i <= 27; i++) {
//                String pCode = arr[i].trim();
//
//                if (pCode.isEmpty() || pCode.equals("1") || pCode.equals("0")) {
//                    continue;
//                }
//
//                CampusProgramOfStudy cpos;
//
//                if (arr[52].equals("22") && pCode.equals("9")) {
//                    cpos = (CampusProgramOfStudy) DatabaseManager.getSingleRecord(CampusProgramOfStudy.class.getName(),
//                            148);
//                } else {
//                    cpos = (CampusProgramOfStudy) DatabaseManager.getCpos(campus.getCampusId(),
//                            Integer.parseInt(pCode), pt.getProgramTypeId(), morningShift.getShiftId());
//                }
//
//                if (cpos == null) {
//                    continue;
//                }
//
//                DatabaseManager.addData(new CandidateProgramOfStudy(cpos, c, i - 30, "", null));
//            }
//
//            for (int i = 28; i <= 37; i++) {
//                String pCode = arr[i].trim();
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
//                DatabaseManager.addData(new CandidateProgramOfStudy(cpos, c, i - 40, "", null));
//            }
//
//            DatabaseManager.refresh(c);
//            deductionMarks = utilities.DeductionCalculation.forMasterUSindh(ay.getYear(), c);
//            c.setDeductionMarks(deductionMarks);
//
//            if (c.getTestScore() != null) {
//                percentage = Sorter.getPercentage(c);
//                c.setPercentage(percentage);
//            }
//
//            DatabaseManager.updateData(c);
//            System.out.println(seatNo + " : Done");
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
