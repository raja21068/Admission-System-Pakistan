package admission.utils;

import admission.model.CredentialDetails;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 *
 * @author Raja Kumar & Jay Kumar
 */
public class Sorter {
    public static List sort(Set data) {
        List list = new ArrayList();
        for (Iterator it = data.iterator(); it.hasNext();) {
            list.add(it.next());
        }
        Collections.sort(list, new Comparator<Object>() {
            @Override public int compare(Object o1, Object o2) {
                return o1.toString().compareTo(o2.toString());
            }
        });
        return list;
    }
    
    public static void listSort(List data) {
        Collections.sort(data, new Comparator<Object>() {
            @Override public int compare(Object o1, Object o2) {
                return o1.toString().compareTo(o2.toString());
            }
        });
        //return data;
    }
    
    public static void sortByPercentage(List data) {
        Collections.sort(data, new Comparator<Object>() {
            @Override public int compare(Object ob1, Object ob2) {
                admission.controller.beans.Candidate candidate1 = (admission.controller.beans.Candidate) ob1;
                admission.controller.beans.Candidate candidate2 = (admission.controller.beans.Candidate) ob2;
                
                float p1 = candidate1.getPercentage(); //Sorter.getPercentage(candidate1.getCandidate());
                float p2 = candidate2.getPercentage(); //Sorter.getPercentage(candidate2.getCandidate());
                
                int c1 = candidate1.getChoiceNo();
                int c2 = candidate2.getChoiceNo();
                
                /** Choice No. priority*/
//                int r = (c1 > c2) ? 1 : (c1 < c2) ? -1 : (c1 == c2 && p1 < p2) ? 1 : (c1 == c2 && p1 > p2) ? -1 : 0;
                
                /** Percentage priority*/
//                int r = (p1 < p2) ? 1 : (p1 > p2) ? 0 : (p1 == p2 && c1 > c2) ? 0 : (p1 == p2 && c1 < c2) ? 1 : 0;
                int r = (p1 < p2) ? 1 : (p1 > p2) ? -1 : 0;//(p1 == p2 && c1 < c2) ? 1 : (p1 == p2 && c1 > c2) ? -1 : 0;
                
                return r;//ob1.toString().compareTo(ob2.toString());
            }
        });
        //return data;
    }
    
    public static void sortByThree(List data) {
        Collections.sort(data, new Comparator<Object>() {
            @Override public int compare(Object ob1, Object ob2) {
                admission.controller.beans.Candidate cn1 = (admission.controller.beans.Candidate) ob1;
                admission.controller.beans.Candidate cn2 = (admission.controller.beans.Candidate) ob2;
                
                String dn1 = cn1.getDistrictName();
                String dn2 = cn2.getDistrictName();
                
                String area1 = cn1.getArea();
                String area2 = cn2.getArea();
                
                float p1 = cn1.getPercentage();
                float p2 = cn2.getPercentage();
                
                int c1 = cn1.getChoiceNo();
                int c2 = cn2.getChoiceNo();
                
                int r = dn1.compareTo(dn2) != 0 ? dn1.compareTo(dn2) : 
                        area1.compareTo(area2) != 0 ? area1.compareTo(area2) : 
                        (p1 < p2) ? 1 : (p1 > p2) ? -1 : 
                        (p1 == p2 && c1 < c2) ? -1 : 
                        (p1 == p2 && c1 > c2) ? 1 : 0;
                
                return r;//ob1.toString().compareTo(ob2.toString());
            }
        });
        //return data;
    }
}
