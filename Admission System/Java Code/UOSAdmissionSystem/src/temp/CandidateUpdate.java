package temp;

//~--- non-JDK imports --------------------------------------------------------

import admission.controller.JDBCDatabaseManager;

import admission.model.CredentialDetails;

//~--- JDK imports ------------------------------------------------------------

import java.sql.SQLException;

import java.text.DecimalFormat;
import java.text.NumberFormat;

import admission.utils.CandidateHelper;

/**
 *
 * @author Raja Kumar & Jay Kumar
 */
public class CandidateUpdate {
    public static void main(String[] args) throws SQLException {

//      DisciplineCategorySeats dsd = DatabaseManager.getDisciplineCategorySeats(1, 5, 5);
//      System.out.println(dsd.getNumberOfSeats());
//      List<DistrictSeatDistribution> dsd1 = DatabaseManager.getDistrictSeatDistribution(1, 3, 1, (byte)1);
//      int count = 0;
//      for (int i = 0; i < dsd1.size(); i++) {
//          DistrictSeatDistribution dsd = dsd1.get(i);
//          count += dsd.getRural() + dsd.getUrban();
//      }
//      System.out.println(count);
//      System.exit(0);
        NumberFormat nf           = new DecimalFormat("#0.00");
        int[][]      candidatesId = JDBCDatabaseManager.getCandidatesId(3, 1, 1);

        for (int[] id : candidatesId) {

            // AdmissionYear ay = candidate.getAdmissionYear();
            // Set set = candidate.getCredentialDetailses();
//          Object[] ar = set.toArray();
//          Integer deductionMarks;
//          
//          if (set.size() == 3) {
//              deductionMarks = DeductionCalculation.forMasterUSindh(admissionYear.getYear(), candidate);
//          } else {
//              deductionMarks = DeductionCalculation.forBachalor(admissionYear.getYear(), candidate);
//          }
//          candidate.setDeductionMarks(deductionMarks);
//          int forBachalor = DeductionCalculation.forBachalor(ay.getYear(), candidate);
//          candidate.setDeductionMarks(forBachalor);
//          if(candidate.getTestScore() == null || candidate.getTestScore() <= 0)
//              continue;
            CredentialDetails[] credentialDetails = JDBCDatabaseManager.getCredentialDetails(id[0]);
            String              percentage        = nf.format(CandidateHelper.getPercentage(credentialDetails, id));

            JDBCDatabaseManager.updateCandidates(id[0], percentage);
            System.out.println(id[0] + " : " + percentage + " - Done");

//          Set set = candidate.getCandidateProgramOfStudies();
//          
//          System.out.print(i + " - " + candidate.getSeatNo() + " (");
//          
//          Object[] toArray = set.toArray();
//          for (int j = 0; j < toArray.length; j++) {
//              CandidateProgramOfStudy ob = (CandidateProgramOfStudy) toArray[j];
//              ob.setChoiceNo(j + 1);
//              System.out.print((j + 1) + ", ");
//              //System.out.print(ob.getCandidateProgramOfStudyId() + ", ");
//              DatabaseManager.updateData(ob);
//          }
//          candidate.calculatePercentage();
//          DatabaseManager.updateData(candidate);
        }

        System.exit(0);
    }
}


//~ Formatted by Jindent --- http://www.jindent.com
