/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package admission.xmlparser;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Yougeshwar
 */
public class ReportCategory {
    private String name;
    private List<Report> reportList = new ArrayList<>();

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void addReport(Report report) {
        this.reportList.add(report);
    }

    public void removeReport(Report report) {
        this.reportList.remove(report);
    }

    public List<Report> getReportList() {
        return reportList;
    }

    @Override
    public String toString() {
        return name;
    }
}
