package admission.reports;

import admission.controller.JDBCDatabaseManager;
import admission.model.CampusCategory;
import admission.model.CposGroup;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.print.Book;
import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JProgressBar;
import admission.utils.DateUtility;

/**
 *
 * @author Raja Kumar & Jay Kumar
 */
public class BankSelectionReport {
    private static final int MAX_PER_PAGE = 20;
    private final JProgressBar progressBar;
    
    public BankSelectionReport(JProgressBar progressBar) {
        this.progressBar = progressBar;
    }
    
    public void print(int campusId, boolean isMain, int programTypeId, int admissionListId, int year, boolean isSeperate) throws PrinterException, SQLException {
        
        PageFormat pageFormat = getPageFormat();
        Book book = new Book();

        List<CampusCategory> ccList;
        List<CposGroup> cposgList;
        if(isMain) {
            ccList = JDBCDatabaseManager.getMainCampusCategories(isMain, programTypeId);
            cposgList = JDBCDatabaseManager.getMainCposGroup(true, programTypeId, isSeperate);
        } else {
            ccList = JDBCDatabaseManager.getCampusCategories(campusId, programTypeId);
            cposgList = JDBCDatabaseManager.getCampusCposGroup(campusId, programTypeId, isSeperate);
        }
        
        progressBar.setMaximum(ccList.size() - 1);
        progressBar.setValue(0);
        
        for (int i = 0; i < ccList.size(); i++) {
            CampusCategory cc = ccList.get(i);
            
            for (int j = 0; j < cposgList.size(); j++) {
                CposGroup cposg = cposgList.get(j);
                String posName;//!cposg.isIsBachelor() ? (cposg.getProgramName().equals("M.LIS") || cposg.getProgramName().equals("PGD") ? "" : + (Previous)) + " " + cposg.getPosName() : cposg.toString();
                
                if(!cposg.isIsBachelor()) {
                    posName = cposg.getProgramName() + (cposg.getProgramName().equals("M.LIS") || cposg.getProgramName().equals("PGD") || cposg.getProgramName().startsWith("1 Year Con")  || cposg.getProgramName().equals("LL.M") ? "" : " (PREVIOUS)") + " " + cposg.getPosName() + (cc.isMorning() ? "" : " EVENING");
                } else posName = cposg.toString();
                
                List<Object[]> admissionListDetails = JDBCDatabaseManager.getAdmissionListDetail(cposg.getCposGroupId(), cc.getCampusCategoryId(), admissionListId);
                
                if(admissionListDetails.isEmpty()) continue;
                List<Object[]> data = new ArrayList<>(MAX_PER_PAGE);
                int count = 0;
                for (int k = 0; k < admissionListDetails.size(); k++) {
                    if(count >= MAX_PER_PAGE) {
                        book.append(new Page(cc.toString(), posName, year, data), pageFormat);
                        count = 0;
                        data = new ArrayList<>(MAX_PER_PAGE);
                    } 
                    Object[] row = admissionListDetails.get(k);
                    row[0] = k + 1;
                    data.add(row);
                    count++;
                }
                if(count > 0) {
                    book.append(new Page(cc.toString(), posName, year, data), pageFormat);
                } 
                count = 0;
            }
            progressBar.setValue(i);
        }
        
        PrinterJob printJob = PrinterJob.getPrinterJob();
        printJob.setPageable(book);
        printJob.setJobName("Bank Selection Report - Admission System");
        
        if (printJob.printDialog()) {
            printJob.print();
        }
    }
    
    private PageFormat getPageFormat() {
        PageFormat pageFormat = new PageFormat();
        Paper paper = new Paper();

        double pageHeight = 841.68;
        double pageWidth = 595.44;

        paper.setSize(pageWidth, pageHeight);
        paper.setImageableArea(10.0, 10.0, pageWidth - 20.0, pageHeight - 20.0);
        pageFormat.setPaper(paper);
        pageFormat.setOrientation(PageFormat.LANDSCAPE);

        return pageFormat;
    } //end getPageFormat()
    
    private class Page implements Printable {
        private boolean access;
        
        private int year;
        private String category;
        private String posName;
        private List<Object[]> data;

        
        public Page(String category, String posName, int year, List<Object[]> data) {
            this.category = category;
            this.posName = posName;
            this.data = data;
            this.year = year;
        }
        
        @Override
        public int print(Graphics graphics, PageFormat pf, int pageIndex) throws PrinterException {
            if(!access){
                access = true;
                return PAGE_EXISTS;
            }
            
            String title1 = "DIRECTORATE OF ADMISSIONS, UNIVERSITY OF SINDH, JAMSHORO ";
            String title2 = "LIST OF CANDIDATES PROVISIONALLY SELECTED FOR ADMISSION FOR THE ACADEMIC YEAR " + year + " ON";
            String title3 = "DISCIPLINE: " + posName;
            
            final int w = (int)pf.getWidth() - 20;
            int h = (int)pf.getHeight() - 20;
            
            Graphics2D g2d = (Graphics2D) graphics;
            g2d.translate(pf.getImageableX(), pf.getImageableY());
            g2d.setPaint(Color.black);

            Font font = new Font("Times New Roman", Font.BOLD, 12);
            g2d.setFont(font);
            FontMetrics fm = g2d.getFontMetrics();
            
            int y = 10; // x = 270;
            
            String str = (pageIndex + 1) + "";
            int sw = fm.stringWidth(str);
            g2d.drawString(str, (int)(w - sw), y += 10);
//            g2d.drawString(DateFormatter.getDateToString(new Date()), 0, y);
            g2d.drawString("17-01-2014`", 0, y);
            
            str = title1;
            sw = fm.stringWidth(str);
            g2d.drawString(str, (int)(w - sw) / 2, y += 10);
            
            str = title2;
            sw = fm.stringWidth(str);
            g2d.drawString(str, (int)(w - sw) / 2, y += 15);
            
            str = category;
            sw = fm.stringWidth(str);
            g2d.drawString(str, (int)(w - sw) / 2, y += 15);
            
            str = title3;
            g2d.drawString(str, 0, y += 20);
            
            g2d.drawString("SNO.", 0, y += 20);
            g2d.drawString("SEAT NO.", 35, y);
            g2d.drawString("NAME", 105, y);
            g2d.drawString("FATHER'S NAME", 310, y);
            g2d.drawString("SURNAME", 510, y);
            g2d.drawString("FINAL SCORE", 620, y);
            g2d.drawLine(0, y += 10, w, y);
            
            font = new Font("Times New Roman", Font.PLAIN, 12);
            g2d.setFont(font);
            
            for (Object[] objects : data) {
                g2d.drawString("" + objects[0], 0, y += 18);
                g2d.drawString("" + objects[1], 35, y);
                g2d.drawString("" + objects[2], 105, y);
                g2d.drawString("" + objects[3], 310, y);
                g2d.drawString("" + objects[4], 510, y);
                g2d.drawString("" + objects[5], 630, y);
            }
            
            font = new Font("Times New Roman", Font.BOLD, 12);
            g2d.setFont(font);
            
            String title4 = "ASSISTANT DIRECTOR ADMISSIONS";
            String title5 = "DEPUTY DIRECTOR ADMISSIONS";
//            String title51 = "UNIVERSITY OF SINDH, JAMSHORO";
            String title6 = "NOTE: THE UNIVERSITY OF SINDH RESERVES THE RIGHT TO CORRECT ANY ERROR/OMMISION DETECTED LATER ON,";
            String title61 = "AND ALSO RESERVES THE RIGHT TO CANCEL ANY PROVISIONAL ADMISSION AT ANY TIME WITHOUT ISSUING NOTICE.";
            String title7 = "COMPUTER PROGRAMMER";
            
            
            g2d.drawString(title7, 0, h - 60);
            
            str = title4;
            sw = fm.stringWidth(str);
            g2d.drawString(title4, (w - sw) / 2, h - 60);
//            str = title51;
//            sw = fm.stringWidth(str);
//            g2d.drawString(title51, (w - sw) / 2, h - 47);
            
            str = title5;
            sw = fm.stringWidth(str);
            g2d.drawString(title5, w - sw, h - 60);
//            str = title51;
//            sw = fm.stringWidth(str);
//            g2d.drawString(title51, w - sw, h - 47);
            
            g2d.drawString(title6, 0, h - 25);
            g2d.drawString(title61, 0, h - 12);
            
            return PAGE_EXISTS;
        }
    }
}
