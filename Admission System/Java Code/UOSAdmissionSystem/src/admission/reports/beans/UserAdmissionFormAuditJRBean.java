/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package admission.reports.beans;

/**
 *
 * @author Yougeshwar
 */
public class UserAdmissionFormAuditJRBean {
    private String userName;
    private int totalFormEntry;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getTotalFormEntry() {
        return totalFormEntry;
    }

    public void setTotalFormEntry(int totalFormEntry) {
        this.totalFormEntry = totalFormEntry;
    }
}
