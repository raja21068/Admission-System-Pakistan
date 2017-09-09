/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package admission.reports;

import admission.model.AdmissionList;
import admission.model.District;
import admission.model.ProgramOfStudy;
import admission.model.ProgramType;
import admission.reports.beans.SelectionCategoryBean;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRPrintPage;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author Server
 */
public class SelectionReport {

    List<JasperPrint> prints;

    public SelectionReport() {
        prints = new LinkedList<>();
    }

    public void append(ProgramType pt, ProgramOfStudy pos, List<SelectionCategoryBean> cats, AdmissionList admissionList, String shift) throws JRException, IOException {
        JRBeanCollectionDataSource dataSource = new net.sf.jasperreports.engine.data.JRBeanCollectionDataSource(cats);
        Map<String, Object> params = new HashMap();
        String admissionListName = "";
        params.put("footer1", "");
        params.put("footer2", "");
        params.put("footer3", "");
        params.put("footer4", "");
        params.put("footer5", "");
        params.put("footer6", "");
        params.put("objection", "");
        if (admissionList.getListNo().intValue() == 1) {
            admissionListName = "1st";
        } else if (admissionList.getListNo().intValue() == 2) {
            admissionListName = "2nd";
        } else if (admissionList.getListNo().intValue() == 3) {
            admissionListName = "3rd";
        } else if (admissionList.getListNo().intValue() == 4) {
            admissionListName = "4th";
        } else {
            admissionListName = "Special List";
        }
        params.put("listNo", admissionListName);
        params.put("discipline", pos.getName());
        params.put("campus", admissionList.getCampus().getName());
        params.put("campusLocation", admissionList.getCampus().getLocation());
        params.put("shift", shift.toUpperCase());
        params.put("admissionYear", admissionList.getAdmissionSession().getAdmissionYear().getYear());
//        InputStream resourceAsStream = getClass().getResourceAsStream("/reports/selection_list.jasper");
//        InputStream resourceAsStream2 = getClass().getResourceAsStream("/reports/selection_list_master_subreport.jasper");
//        System.out.println(resourceAsStream);
//        System.out.println(">" + resourceAsStream2);
        JasperPrint print = JasperFillManager.fillReport(getClass().getResourceAsStream("/reports/selection_list.jasper"), params, dataSource);
        prints.add(print);
    }

    public void append2(ProgramType pt, ProgramOfStudy pos, List<SelectionCategoryBean> cats, AdmissionList admissionList, String shift) throws JRException, IOException {
        JRBeanCollectionDataSource dataSource = new net.sf.jasperreports.engine.data.JRBeanCollectionDataSource(cats);
        Map<String, Object> params = new HashMap();
        String admissionListName = "";
        params.put("footer1", "COMPUTER PROGRAMMER");
        params.put("footer2", "");
        params.put("footer3", "");
        params.put("footer4", "");
        params.put("footer5", "");
        params.put("footer6", "ASSISTANT DIRECTOR ADMISSIONS");
        params.put("objection", "");
        if (admissionList.getListNo().intValue() == 1) {
            admissionListName = "1st";
        } else if (admissionList.getListNo().intValue() == 2) {
            admissionListName = "2nd";
        } else if (admissionList.getListNo().intValue() == 3) {
            admissionListName = "3rd";
        } else if (admissionList.getListNo().intValue() == 4) {
            admissionListName = "4th";
        } else {
            admissionListName = "Special List";
        }
        params.put("listNo", admissionListName);
        params.put("discipline", pos.getName());
        params.put("campus", admissionList.getCampus().getName());
        params.put("campusLocation", admissionList.getCampus().getLocation());
        params.put("shift", shift.toUpperCase());
        params.put("admissionYear", admissionList.getAdmissionSession().getAdmissionYear().getYear());
//        InputStream resourceAsStream = getClass().getResourceAsStream("/reports/selection_list.jasper");
//        InputStream resourceAsStream2 = getClass().getResourceAsStream("/reports/selection_list_master_subreport.jasper");
//        System.out.println(resourceAsStream);
//        System.out.println(">" + resourceAsStream2);
        JasperPrint print = JasperFillManager.fillReport(getClass().getResourceAsStream("/reports/selection_list.jasper"), params, dataSource);
        prints.add(print);
    }

    public void append3(ProgramType pt, ProgramOfStudy pos, List<SelectionCategoryBean> cats, AdmissionList admissionList, String shift) throws JRException, IOException {
        JRBeanCollectionDataSource dataSource = new net.sf.jasperreports.engine.data.JRBeanCollectionDataSource(cats);
        Map<String, Object> params = new HashMap();
        String admissionListName = "";
        params.put("footer1", "COMPUTER PROGRAMMER");
        params.put("footer2", "ASSISTANT DIRECTOR ADMISSIONS");
        params.put("footer3", "DEPUTY DIRECTOR ADMISSIONS");
        params.put("footer4", "DIRECTOR ADMISSIONS");
        params.put("footer5", "CONVENOR\nTECHNICAL/SELECTION COMMITEE");
        params.put("footer6", "CONVENOR\nADMISSION COMMITEE");
        params.put("objection", "");
        if (admissionList.getListNo().intValue() == 1) {
            admissionListName = "1st";
        } else if (admissionList.getListNo().intValue() == 2) {
            admissionListName = "2nd";
        } else if (admissionList.getListNo().intValue() == 3) {
            admissionListName = "3rd";
        } else if (admissionList.getListNo().intValue() == 4) {
            admissionListName = "4th";
        } else {
            admissionListName = "Special List";
        }
        params.put("listNo", admissionListName);
        params.put("discipline", pos.getName());
        params.put("campus", admissionList.getCampus().getName());
        params.put("campusLocation", admissionList.getCampus().getLocation());
        params.put("shift", shift.toUpperCase());
        params.put("admissionYear", admissionList.getAdmissionSession().getAdmissionYear().getYear());
//        InputStream resourceAsStream = getClass().getResourceAsStream("/reports/selection_list.jasper");
//        InputStream resourceAsStream2 = getClass().getResourceAsStream("/reports/selection_list_master_subreport.jasper");
//        System.out.println(resourceAsStream);
//        System.out.println(">" + resourceAsStream2);
        JasperPrint print = JasperFillManager.fillReport(getClass().getResourceAsStream("/reports/selection_list.jasper"), params, dataSource);
        prints.add(print);
    }

    
        public void appendObjection(ProgramType pt, ProgramOfStudy pos, List<SelectionCategoryBean> cats, AdmissionList admissionList, String shift) throws JRException, IOException {
        JRBeanCollectionDataSource dataSource = new net.sf.jasperreports.engine.data.JRBeanCollectionDataSource(cats);
        Map<String, Object> params = new HashMap();
        String admissionListName = "";
        params.put("footer1", "COMPUTER PROGRAMMER");
        params.put("footer2", "");
        params.put("footer3", "");
        params.put("footer4", "");
        params.put("footer5", "");
        params.put("footer6", "ASSISTANT DIRECTOR ADMISSIONS");
        params.put("objection", "LAST DATE FOR SUBMISSION OF OBJECTION (IF ANY) AT DIRECTORATE OF ADMISSIONS DURING OFFICE HOURS UPTO 08.11.2014. 1st PROVISIONAL LIST (AFTER CLEARING OBJECTIONS) WILL BE DISPLAYED ON 10.11.2014");
        if (admissionList.getListNo().intValue() == 1) {
            admissionListName = "1st";
        } else if (admissionList.getListNo().intValue() == 2) {
            admissionListName = "2nd";
        } else if (admissionList.getListNo().intValue() == 3) {
            admissionListName = "3rd";
        } else if (admissionList.getListNo().intValue() == 4) {
            admissionListName = "4th";
        } else {
            admissionListName = "Special List";
        }
        params.put("listNo", admissionListName);
        params.put("discipline", pos.getName());
        params.put("campus", admissionList.getCampus().getName());
        params.put("campusLocation", admissionList.getCampus().getLocation());
        params.put("shift", shift.toUpperCase());
        params.put("admissionYear", admissionList.getAdmissionSession().getAdmissionYear().getYear());
//        InputStream resourceAsStream = getClass().getResourceAsStream("/reports/selection_list.jasper");
//        InputStream resourceAsStream2 = getClass().getResourceAsStream("/reports/selection_list_master_subreport.jasper");
//        System.out.println(resourceAsStream);
//        System.out.println(">" + resourceAsStream2);
        JasperPrint print = JasperFillManager.fillReport(getClass().getResourceAsStream("/reports/selection_list.jasper"), params, dataSource);
        prints.add(print);
    }
        
    public void print(java.awt.Frame superparent) {
        if (!prints.isEmpty()) {
            JasperPrint print = prints.get(0);
            for (int i = 1; i < prints.size(); i++) {
                JasperPrint print2 = prints.get(i);
                List<JRPrintPage> pages = print2.getPages();
                for (JRPrintPage jRPrintPage : pages) {
                    print.addPage(jRPrintPage);
                }
            }
//            JRViewer300 jrview = new JRViewer300(print);
//            superparent.setSize(1000, 1000);
//            ((javax.swing.JFrame) superparent).getContentPane().removeAll();
//            ((javax.swing.JFrame) superparent).getContentPane().add(jrview);
//            superparent.setVisible(true);
//            superparent.setAlwaysOnTop(true);
            JasperViewer.viewReport(print, false);
        }
    }
}
