/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package admission.utils;

import admission.controller.DatabaseManager;
import admission.model.Candidate;
import admission.model.CredentialDetails;
import java.util.List;
import java.util.Set;

/**
 *
 * @author Yougeshwar
 */
public class DeductionCalculation {
    
    public static int forBachalor(Integer currentYear, Candidate candidate) {
        int deductionMarks = 0;
        
        List<CredentialDetails> credentialDetailsList = DatabaseManager.getData(CredentialDetails.class, "candidate.candidateId = " + candidate.getCandidateId(), "detailOf");
        if(credentialDetailsList.size() != 2) return deductionMarks;
        
//        Object[] toArray = set.toArray();
        
        CredentialDetails matric = credentialDetailsList.get(0);
//        Integer currentYear = ay.getYear();
        Integer matricYear = matric.getPassingYear();
        
        Integer difference = currentYear - matricYear;
        
        if (difference >= 8) {
            deductionMarks = 25;
        } else if (difference > 3) {
            deductionMarks = (difference - 3) * 5;
        }
        
        return deductionMarks;
    }
    
    public static int forMasterUSindh(Integer currentYear, Candidate candidate) {
        int deductionMarks = 0;
        
        List<CredentialDetails> credentialDetailsList = DatabaseManager.getData(CredentialDetails.class, "candidate.candidateId = " + candidate.getCandidateId(), "detailOf");
        if(credentialDetailsList.size() != 3) return deductionMarks;
        
        CredentialDetails matric = credentialDetailsList.get(0);
//        Integer currentYear = ay.getYear();
        Integer matricYear = matric.getPassingYear();
        
        Integer difference = (currentYear - matricYear - 1);
        Integer degreePeriod = candidate.getYearsDegree();
        
        /** 
         * 3 is basic difference at inter level
         * 5 is for total number of deduction marks
         */
        if (difference >= (3 + degreePeriod + 5)) { 
            deductionMarks = 25;
        } else if (difference > (3 + degreePeriod)) {
            deductionMarks = (difference - (3 + degreePeriod)) * 5;
        }
        
        return deductionMarks;
    }
    
    public static int forMasterOther(Integer currentYear, Candidate candidate) {
        int deductionMarks = 0;
//        if(set.size() != 3) return deductionMarks;
//        
//        Object[] toArray = set.toArray();
//        
//        CredentialDetails matric = (CredentialDetails) toArray[0];
//        Integer currentYear = ay.getYear();
//        Integer matricYear = matric.getPassingYear();
//        
//        Integer difference = (currentYear - matricYear);
//        
//        if (difference >= (3 + degreePeriod + 5)) { /** 5 is for total number of deduction marks*/
//            deductionMarks = 25;
//        } else if (difference > (3 + degreePeriod)) {
//            deductionMarks = (difference - (3 + degreePeriod)) * 5;
//        }
        
        return deductionMarks;
    }
}
