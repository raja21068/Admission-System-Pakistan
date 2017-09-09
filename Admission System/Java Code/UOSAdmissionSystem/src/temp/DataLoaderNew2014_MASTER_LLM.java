//package temp;
//
////~--- non-JDK imports --------------------------------------------------------
//
//import admission.controller.DatabaseManager;
//import admission.controller.JDBCDatabaseManager;
//import admission.model.Account;
//import admission.model.AdmissionList;
//import admission.model.AdmissionListDetails;
//import admission.model.AdmissionYear;
//import admission.model.AppliedCampus;
//import admission.model.AppliedCategory;
//import admission.model.Campus;
//import admission.model.CampusCategory;
//import admission.model.CampusProgramOfStudy;
//import admission.model.Candidate;
//import admission.model.CandidateProgramOfStudy;
//import admission.model.Country;
//import admission.model.CposGroup;
//import admission.model.CredentialDetails;
//import admission.model.District;
//import admission.model.Issuer;
//import admission.model.Part;
//import admission.model.PartRegistry;
//import admission.model.ProgramType;
//import admission.model.Religion;
//import admission.model.Test;
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.FileNotFoundException;
//import java.io.IOException;
//
//import java.util.Date;
//import java.util.Properties;
//import java.util.Scanner;
//import utilities.Coder;
//import utilities.Constant;
//import utilities.DateFormatter;
//import utilities.DeductionCalculation;
//import utilities.Sorter;
//
///**
// *
// * @author Raja Kumar & Jay Kumar
// */
//public class DataLoaderNew2014_MASTER_LLM {
//    public static void main(String[] args) throws FileNotFoundException, IOException {
//        load();
//        
//        ProgramType   pt     = (ProgramType) DatabaseManager.getSingleRecord(ProgramType.class.getName(), 2);
//        AdmissionYear ay     = (AdmissionYear) DatabaseManager.getSingleRecord(AdmissionYear.class.getName(), 3);
//        Campus        campus = (Campus) DatabaseManager.getSingleRecord(Campus.class.getName(), 2);
//        Scanner       scan   = new Scanner(new FileInputStream("LLM.csv"));
//        //LLM
//        CampusProgramOfStudy cpos = (CampusProgramOfStudy) DatabaseManager.getSingleRecord(CampusProgramOfStudy.class.getName(), 113);
//        CposGroup cposg = (CposGroup) DatabaseManager.getSingleRecord(CposGroup.class.getName(), 170);
//        AdmissionList al = (AdmissionList) DatabaseManager.getSingleRecord(AdmissionList.class.getName(), 14);
//        Part part = (Part) DatabaseManager.getSingleRecord(Part.class.getName(), 1);
//        Test test = (Test) DatabaseManager.getSingleRecord(Test.class.getName(), 2);
//        Religion religion = (Religion) DatabaseManager.getSingleRecord(Religion.class.getName(), "name='ISLAM'");
//        Country country = (Country) DatabaseManager.getSingleRecord(Country.class.getName(), "name='PAKISTAN'");
//        CampusCategory cc = (CampusCategory) DatabaseManager.getSingleRecord(CampusCategory.class.getName(), 86);
//        
//        String cat = Constant.SFE_QUOTA;
//        
//        while (scan.hasNextLine()) {
//            String  line             = scan.nextLine();
//            String  arr[]            = line.split(",");
//            Integer seatNo           = Integer.parseInt(arr[0].trim());
//            Integer formNo           = Integer.parseInt(arr[1].trim());
//            
//            String name = arr[2];
//            String fname = arr[3];
//            String surname = arr[4];
//            String gender = arr[5];
//            
//            String cnic = arr[6];
//            District d = (District) DatabaseManager.getSingleRecord(District.class.getName(), Integer.parseInt(arr[7].trim()));
//            String area = arr[8];
//            String paddr = arr[9];
//            
//            Candidate c = new Candidate(pt, d, test, ay, null, religion, country, seatNo, formNo, cnic, "S", name, fname, surname, gender, area, null, "", "", paddr, "", "", "", "", "", "", "", 1300, null, 1300, 4, null, null, null, null, "", 1, 0.0F, 0, Coder.Encoder.booleanEncode(false), "", "", null, null, null, null, null, null, null);
//            int id = DatabaseManager.addData(c);
//            if(id < 1) continue;
//            c.setCandidateId(id);
//            
//            AppliedCategory ac = new AppliedCategory(c, cat, "");
//            DatabaseManager.addData(ac);
//            
//            AppliedCampus ac2 = new AppliedCampus(campus, c, "");
//            DatabaseManager.addData(ac2);
//            
//            CandidateProgramOfStudy cnpos = new CandidateProgramOfStudy(cpos, c, 1, "", null);
//            id = DatabaseManager.addData(cnpos);
//            cnpos.setCandidateProgramOfStudyId(id);
//                        
////            Sorter.get
//            String  sscGroup         = arr[10];
//            Integer sscMarksObtained = Integer.parseInt(arr[11].trim());    // System.out.println(sscMarksObtained + " : " + seatNo);
//            Integer sscTotalMarks    = Integer.parseInt(arr[12].trim());
//            Integer sscYear          = Integer.parseInt(arr[13].trim());
//            Integer sscSeatNo        = Integer.parseInt(arr[14].trim());
//            Issuer  sscIssuer        = (Issuer) DatabaseManager.getSingleRecord(Issuer.class.getName(), Integer.parseInt(arr[15].trim()));
//            CredentialDetails cd = new CredentialDetails(sscIssuer, c, sscTotalMarks, sscMarksObtained, sscGroup, sscSeatNo, sscYear, Constant.SSC, "");
//            DatabaseManager.addData(cd);
//            
//            String  hscGroup         = arr[16];                                  // getChange(arr[13].trim());
//            Integer hscMarksObtained = Integer.parseInt(arr[17].trim());
//            Integer hscTotalMarks    = Integer.parseInt(arr[18].trim());
//            Integer hscYear          = Integer.parseInt(arr[19].trim());
//            Integer hscSeatNo        = Integer.parseInt(arr[20].trim());
//            Issuer  hscIssuer        = (Issuer) DatabaseManager.getSingleRecord(Issuer.class.getName(), Integer.parseInt(arr[21].trim()));
//            cd = new CredentialDetails(hscIssuer, c, hscTotalMarks, hscMarksObtained, hscGroup, hscSeatNo, hscYear, Constant.HSC, "");
//            DatabaseManager.addData(cd);
//            
//            String  grdGroup         = arr[22];                                  // getChange(arr[13].trim());
//            Integer grdMarksObtained = Integer.parseInt(arr[23].trim());
//            Integer grdTotalMarks    = Integer.parseInt(arr[24].trim());
//            Integer grdYear          = Integer.parseInt(arr[25].trim());
//            Integer grdSeatNo        = Integer.parseInt(arr[26].trim());
//            Issuer  grdIssuer        = (Issuer) DatabaseManager.getSingleRecord(Issuer.class.getName(), Integer.parseInt(arr[27].trim()));
//            cd = new CredentialDetails(grdIssuer, c, grdTotalMarks, grdMarksObtained, grdGroup, grdSeatNo, grdYear, Constant.GRADUATION, "");
//            DatabaseManager.addData(cd);
//            
//            DatabaseManager.refresh(c);
//            
//            int deductionMarks = DeductionCalculation.forMasterUSindh(ay.getYear(), c);
//            c.setDeductionMarks(deductionMarks);
//            float percentage = Sorter.getPercentage(c);
//            c.setPercentage(percentage);
//            DatabaseManager.updateData(c);
//            DatabaseManager.refresh(c);
//            
//            AdmissionListDetails ald = new AdmissionListDetails(cc, al, cposg, cnpos, c, "S", Coder.Encoder.booleanEncode(false), "", null);
//            id = DatabaseManager.addData(ald);
//            ald.setAdmissionListDetailsId(id);
//            
////            Account acc = new Account(c, new Date(), Coder.Encoder.booleanEncode(true), "", null);
////            id = DatabaseManager.addData(acc);
////            acc.setAccountId(id);
////            
////            int challanNo = Integer.parseInt(arr[28]);
////            Date challandate = DateFormatter.getStringToDate(arr[29]);
////            int amount = Integer.parseInt(arr[30]);
////            PartRegistry pr = new PartRegistry(null, acc, ald, part, challanNo, amount, challandate, Constant.Challan.ADMISSION, "", null);
////            DatabaseManager.addData(pr);
//            
//            System.out.println(seatNo + " : Done");
//        }
//
//        System.exit(0);
//    }
//    
//    private static void load() throws FileNotFoundException, IOException {
//        String userHome = System.getProperty("user.home");
//        String path = userHome + "\\" + Constant.DB.PROPERTY_PATH;
//        File file = new File(path);
//        
//        final Properties prop = new Properties();
//        if (file.exists()) {
//            prop.load(new java.io.FileInputStream(file));
//            
//            Constant.DB.DRIVER = prop.getProperty("driver");
//            Constant.DB.URL = prop.getProperty("url");
//            Constant.DB.USERNAME = prop.getProperty("user");
//            Constant.DB.PASSWORD = new utilities.AltEncrypter().decrypt(prop.getProperty("password"));
//            
//            JDBCDatabaseManager.load();
//        }
//    }
//}