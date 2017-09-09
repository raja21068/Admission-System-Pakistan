package admission.utils;

import admission.controller.DatabaseManager;
import admission.model.CredentialDetails;
import admission.model.OptionalSubject;
import admission.model.Subject;
import java.util.List;

/**
 *
 * @author Yougeshwar
 */
public class CandidateHelper {

    public static float getPercentage(admission.model.Candidate cn) {
        List<CredentialDetails> credentialDetailsList = DatabaseManager.getData(CredentialDetails.class, "candidate.candidateId = " + cn.getCandidateId(), "detailOf");
//        Object[] toArray = cn.getCredentialDetailses().toArray();
        CredentialDetails matric = credentialDetailsList.get(0);
        CredentialDetails inter = credentialDetailsList.get(1);

        Float interMarksObtained = inter.getMarksObtained();

        Float matricPer = 0.0F;
        Float interPer = 0.0F;
        Float degreePer = 0.0F;

        Float up = matric.getMarksObtained() * 1.0F;
        Float down = matric.getTotalMarks() * 1.0F;
        
        // For Master
        if (credentialDetailsList.size() == 3) {
            if (up > 0 && down > 0) {
                matricPer = (up / down) * 100.0F * IConstant.M_MATRIC_PER;
            }
            
            CredentialDetails degree = credentialDetailsList.get(2);

            up = interMarksObtained * 1.0F;
            down = inter.getTotalMarks() * 1.0F;
            if (up > 0 && down > 0) {
                interPer = (up / down) * 100.0F * IConstant.M_INTER_PER;
            }

            up = (degree.getMarksObtained() - cn.getDeductionMarks()) * 1.0F;
            down = (degree.getTotalMarks() * 1.0F);

            if (up > 0 && down > 0) {
                degreePer = (up / down) * 100.0F * IConstant.DEGREE_PER;
            }
        } else { /*For Bachelor*/
            if (up > 0 && down > 0) {
                matricPer = (up / down) * 100.0F * IConstant.MATRIC_PER;
            }
            up = (interMarksObtained - cn.getDeductionMarks()) * 1.0F;
            down = inter.getTotalMarks() * 1.0F;
            if (up > 0 && down > 0) {
                interPer = (up / down) * 100.0F * IConstant.INTER_PER;
            }
        }

        Float testPer = (cn.getTestScore() == null ? 0 : cn.getTestScore() / IConstant.TOTAL_MARKS) * 100.0F * IConstant.TEST_PER;
        Float finalPer = matricPer + interPer + degreePer + testPer;

        return Float.parseFloat(NumberFormatter.format(finalPer));
    }

    public static float getPercentage(admission.model.Candidate cn, int score) {
        List<CredentialDetails> credentialDetailsList = DatabaseManager.getData(CredentialDetails.class, "candidate.candidateId = " + cn.getCandidateId(), "detailOf");
//        Object[] toArray = cn.getCredentialDetailses().toArray();
        CredentialDetails matric = credentialDetailsList.get(0);
        CredentialDetails inter = credentialDetailsList.get(1);

        Float interMarksObtained = inter.getMarksObtained();

        Float matricPer = 0.0F;
        Float interPer = 0.0F;
        Float degreePer = 0.0F;

        Float up = matric.getMarksObtained() * 1.0F;
        Float down = matric.getTotalMarks() * 1.0F;
        
        //For Master
        if (credentialDetailsList.size() == 3) {
            if (up > 0 && down > 0) {
                matricPer = (up / down) * 100.0F * IConstant.M_MATRIC_PER;
            }
            CredentialDetails degree = credentialDetailsList.get(2);

            up = interMarksObtained * 1.0F;
            down = inter.getTotalMarks() * 1.0F;
            if (up > 0 && down > 0) {
                interPer = (up / down) * 100.0F * IConstant.M_INTER_PER;
            }

            up = (degree.getMarksObtained() - cn.getDeductionMarks()) * 1.0F;
            down = (degree.getTotalMarks() * 1.0F);

            if (up > 0 && down > 0) {
                degreePer = (up / down) * 100.0F * IConstant.DEGREE_PER;
            }
        } else {/*For Bachelor*/
            if (up > 0 && down > 0) {
                matricPer = (up / down) * 100.0F * IConstant.MATRIC_PER;
            }
            up = (interMarksObtained - cn.getDeductionMarks()) * 1.0F;
            down = inter.getTotalMarks() * 1.0F;
            if (up > 0 && down > 0) {
                interPer = (up / down) * 100.0F * IConstant.INTER_PER;
            }
        }

        Float testPer = (score / IConstant.TOTAL_MARKS) * 100.0F * IConstant.TEST_PER;
        Float finalPer = matricPer + interPer + degreePer + testPer;

        return Float.parseFloat(NumberFormatter.format(finalPer));
    }

    public static float getGRDPercentage(admission.model.Candidate cn) {
        List<CredentialDetails> credentialDetailsList = DatabaseManager.getData(CredentialDetails.class, "candidate.candidateId = " + cn.getCandidateId(), "detailOf");
//        Object[] toArray = cn.getCredentialDetailses().toArray();

        Float degreePer = 0.0F;

        if (credentialDetailsList.size() == 3) {
            CredentialDetails degree = credentialDetailsList.get(2);

            Float up = (degree.getMarksObtained() - cn.getDeductionMarks()) * 1.0F;
            Float down = (degree.getTotalMarks() * 1.0F);

            if (up > 0 && down > 0) {
                degreePer = (up / down) * 100.0F * IConstant.DEGREE_PER;
            }
        }

        return Float.parseFloat(NumberFormatter.format(degreePer));
    }

    public static float getPercentage(CredentialDetails cd) {
        Float per = 0.0F;

        Float up = (cd.getMarksObtained()) * 1.0F;
        Float down = (cd.getTotalMarks() * 1.0F);

        if (up > 0 && down > 0) {
            per = (up / down) * 100.0F;
        }

        return Float.parseFloat(NumberFormatter.format(per));
    }

//    public static float getSSCPercentage(admission.model.Candidate cn) {
//        List<CredentialDetails> credentialDetailsList = DatabaseManager.getData(CredentialDetails.class, "candidate.candidateId = " + cn.getCandidateId(), "detailOf");
//        CredentialDetails matric = credentialDetailsList.get(0);
//
//        Float matricPer = 0.0F;
//
//        Float up = matric.getMarksObtained() * 1.0F;
//        Float down = matric.getTotalMarks() * 1.0F;
//        
//        if (up > 0 && down > 0) {
//            matricPer = (up / down) * 100.0F * IConstant.MATRIC_PER;
//        }
//
//        return Float.parseFloat(NumberFormatter.format(matricPer));
//    }

    public static float getHSCPercentage(admission.model.Candidate cn) {
        List<CredentialDetails> credentialDetailsList = DatabaseManager.getData(CredentialDetails.class, "candidate.candidateId = " + cn.getCandidateId(), "detailOf");
        CredentialDetails inter = credentialDetailsList.get(1);

        Float interMarksObtained = inter.getMarksObtained();

        Float interPer = 0.0F;

        if (credentialDetailsList.size() == 3) {
            Float up = interMarksObtained * 1.0F;
            Float down = inter.getTotalMarks() * 1.0F;
            if (up > 0 && down > 0) {
                interPer = (up / down) * 100.0F * IConstant.M_INTER_PER;
            }
        } else {
            Float up = (interMarksObtained - cn.getDeductionMarks()) * 1.0F;
            Float down = inter.getTotalMarks() * 1.0F;
            if (up > 0 && down > 0) {
                interPer = (up / down) * 100.0F * IConstant.INTER_PER;
            }
        }

        return Float.parseFloat(NumberFormatter.format(interPer));
    }

    public static float getPercentage(CredentialDetails cd, float multiply, int deductionMarks) {
        Float per = 0.0F;

        Float up = (cd.getMarksObtained() - deductionMarks) * 1.0F;
        Float down = cd.getTotalMarks() * 1.0F;
        if (up > 0 && down > 0) {
            per = (up / down) * 100.0F * multiply;
        }

        return per;
    }

    public static float getPercentage(CredentialDetails[] toArray, int array[]) {
//        Object[] toArray = cn.getCredentialDetailses().toArray();
        CredentialDetails matric = toArray[0];
        CredentialDetails inter = toArray[1];
        if (inter == null || matric == null) {
            System.out.println(array[0] + " NULL");
            return 0.0F;
        }

        Float interMarksObtained = inter.getMarksObtained();

        Float matricPer = 0.0F;
        Float interPer = 0.0F;
        Float degreePer = 0.0F;

        Float up = matric.getMarksObtained() * 1.0F;
        Float down = matric.getTotalMarks() * 1.0F;
        if (up > 0 && down > 0) {
            matricPer = (up / down) * 100.0F * IConstant.MATRIC_PER;
        }

        /*if(toArray.length == 3){
         CredentialDetails degree = (CredentialDetails) toArray[2];
            
         up = interMarksObtained * 1.0F;
         down = inter.getTotalMarks() * 1.0F;
         if(up > 0 && down > 0)
         interPer = (up / down) * 100.0F * Constant.M_INTER_PER;
            
         up = (degree.getMarksObtained() - array[1]) * 1.0F;
         down = (degree.getTotalMarks() * 1.0F);
            
         if(up > 0 && down > 0)
         degreePer = (up / down) * 100.0F * Constant.DEGREE_PER;
         } else*/ {
            up = (interMarksObtained - array[1]) * 1.0F;
            down = inter.getTotalMarks() * 1.0F;
            if (up > 0 && down > 0) {
                interPer = (up / down) * 100.0F * IConstant.INTER_PER;
            }
        }

        Float testPer = (array[2] / IConstant.TOTAL_MARKS) * 100.0F * IConstant.TEST_PER;
        Float finalPer = matricPer + interPer + degreePer + testPer;
//        System.out.println(matricPer + ", " + interPer + ", " + degreePer + ", " + testPer);
        return finalPer;
    }

    public static void optionalSubjectModel(CredentialDetails cd, Object input, int i) {
        List<OptionalSubject> list = DatabaseManager.getData(OptionalSubject.class, "credentialDetails.credentialDetailsId = " + cd.getCredentialDetailsId(), "optionalSubjectId");
        
        if (input instanceof Subject) {
            Subject subject = (Subject) input;
            if (list.size() >= i + 1) {
                list.get(i).setSubject(subject);
                DatabaseManager.updateData(list.get(i));
            } else {
                DatabaseManager.addData(new OptionalSubject(subject, cd));
            }
        } else {
            if (list.size() >= i + 1) {
                DatabaseManager.deleteData(list.get(i));
            }
        }
    }
}
