/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package temp;

import admission.controller.DatabaseManager;
import admission.model.AdmissionListDetails;
import admission.model.Candidate;
import java.util.List;

/**
 *
 * @author Yougeshwar
 */
public class NoImageCandidates {
    public static void main(String[] args) {
        List<AdmissionListDetails> list = DatabaseManager.getAdmissionListDetailses(3, 2, 1);
//        System.out.println(list.size());
        
        int count = 0;
        for (int i = 0; i < list.size(); i++) {
            AdmissionListDetails ald = list.get(i);
            Candidate cn = ald.getCandidate();
            
            if(cn.getImagePath() == null) {
//                System.out.println(cn.getSeatNo());
                count++;
            }
        }
        System.out.println(count);
        System.exit(0);
    }
}
