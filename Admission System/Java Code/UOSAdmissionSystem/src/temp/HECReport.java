/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package temp;

import admission.controller.DatabaseManager;
import admission.model.AdmissionListDetails;
import admission.model.Candidate;
import admission.model.CredentialDetails;
import java.util.List;
import admission.utils.CandidateHelper;

/**
 *
 * @author Yougeshwar
 */
public class HECReport {
    public static void main(String[] args) {
        List<AdmissionListDetails> list = DatabaseManager.getAdmissionListDetailses(3, 1, 3);
//        System.out.println(list.size());
        for (int i = 0; i < list.size(); i++) {
            AdmissionListDetails ald = list.get(i);
            Candidate cn = ald.getCandidate();
            
            String rollNo = admission.utils.RollNoFormatter.format(2014, ald.getCposGroup().getCode(), ald.getRollNo());
            float per = 0.0f;//CandidateHelper.getPercentage((CredentialDetails)cn.getCredentialDetailses().toArray()[1]);
            
            if(per >= 60)
                System.out.println(cn.getSeatNo() + "," + cn.getName() + "," + cn.getFathersName() + "," + cn.getSurname() + "," + cn.getCnicNo() + "," + per + "," + rollNo + "," + ald.getCposGroup());
        }
        System.exit(0);
    }
}
