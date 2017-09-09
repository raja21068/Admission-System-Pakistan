package admission.reports;

import admission.model.Campus;
import admission.model.CposGroup;
import admission.reports.beans.ProgramOfStudyDataBean;
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
import admission.utils.Coder;
import admission.utils.DateUtility;
import admission.utils.RollNoFormatter;

/**
 *
 * @author Raja Kumar & Jay Kumar
 */
public class ProgramInformationReport {
    private static final int MAX_PER_PAGE = 44;
    
    public ProgramInformationReport() {
    }
    
//    public void print(int year, CposGroup cposg, List<Object[]> dataList) throws PrinterException, SQLException {
    public void print(int year, List<ProgramOfStudyDataBean> beanList) throws PrinterException, SQLException {
        
        PageFormat pageFormat = getPageFormat();
        Book book = new Book();
        
        for (int i = 0; i < beanList.size(); i++) {
            ProgramOfStudyDataBean bean = beanList.get(i);
            CposGroup cposg = bean.getCposg();
            
            List<Object[]> dataList = bean.getCandidatesData();
            
            String posName;//!cposg.isIsBachelor() ? (cposg.getProgramName().equals("M.LIS") || cposg.getProgramName().equals("PGD") ? "" : + (Previous)) + " " + cposg.getPosName() : cposg.toString();
            if(!cposg.isIsBachelor()) {
                posName = (
                        cposg.getProgramName().equals("M.B.A") 
                        ? (cposg.isIsMorning() ? cposg.getProgramName() + " (PREVIOUS)" : cposg.getProgramName() + " First Year") :
                    
                        cposg.getProgramName().equals("M.C.S") || 
                        cposg.getProgramName().equals("LL.M") || 
//                    cposg.getProgramName().equals("LL.B") || 
                        cposg.getProgramName().equals("M.COM") || 
//                    cposg.getProgramName().equals("B.COM") || 
                        cposg.getProgramName().equals("M.P.A") 
                        ? cposg.getProgramName() + " (PREVIOUS)" :
                    
                        cposg.getProgramName().equals("PGD") || 
                        cposg.getProgramName().startsWith("1 Year Con")
                        ? cposg.getProgramName() + " " + cposg.getPosName() :
                    
                        cposg.getProgramName().equals("BPEHSS") ||
                        cposg.getProgramName().equals("M.LIS") ||
                        cposg.getProgramName().equals("MPEHSS")
                        ? cposg.getPosName() + " (" + cposg.getProgramName() + ")" : 
                    
                        cposg.getProgramName().equals("M.Sc (HONS)") ? cposg.getProgramName() + " " + cposg.getPosName() : 
                        cposg.getProgramName() + " (PREVIOUS) " + cposg.getPosName());
            
                posName += (cposg.isIsMorning() ? "" : " EVENING");
            } else {
//                posName = cposg.toString();
            posName = cposg.getProgramName().equals("B.B.A") ? cposg.getProgramName() + " (HONS) FIRST YEAR" 
                    : (cposg.getProgramName().equals("B.P.A") || 
                      cposg.getProgramName().equals("LL.M")) ? cposg.getProgramName() + " FIRST YEAR" 
                    : cposg.getProgramName().equals("PHARM-D")? cposg.getProgramName() + " FIRST PROF."
                    : cposg.getProgramName() + " (" + cposg.getPosName() + ") FIRST YEAR"
                    ;
            posName += (cposg.isIsMorning() ? "" : " (EVENING)");
            }
            
            List<Object[]> data = new ArrayList<>(MAX_PER_PAGE);
            int pageNo = 1;
            int count = 0;
            for (int k = 0; k < dataList.size(); k++) {
                if(count >= MAX_PER_PAGE) {
                    book.append(new Page(year, posName, cposg, data, pageNo++), pageFormat);
                    count = 0;
                    data = new ArrayList<>(MAX_PER_PAGE);
                }
                Object[] row = dataList.get(k);
                data.add(row);
                count++;
            }
            if(count > 0) {
                book.append(new Page(year, posName, cposg, data, pageNo++), pageFormat);
            }
        }
        PrinterJob printJob = PrinterJob.getPrinterJob();
        printJob.setPageable(book);
        printJob.setJobName("Roll No List - Admission System");
        
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
        pageFormat.setOrientation(PageFormat.PORTRAIT);

        return pageFormat;
    } //end getPageFormat()
    
    private class Page implements Printable {
        private boolean access;
        
        private int year;
        private CposGroup cposg;
        private String posName;
        private List<Object[]> data;
        private int pageNo;

        
        public Page(int year, String posName, CposGroup cposg, List<Object[]> data, int pageNo) {
            this.posName = posName;
            this.cposg = cposg;
            this.data = data;
            this.year = year;
            this.pageNo = pageNo;
        }
        
        @Override
        public int print(Graphics graphics, PageFormat pf, int pageIndex) throws PrinterException {
            if(!access){
                access = true;
                return PAGE_EXISTS;
            }
            
            String title1 = "DIRECTORATE OF ADMISSIONS, UNIVERSITY OF SINDH, JAMSHORO";
            String title2 = "ADMISSION LIST OF ACADEMIC YEAR " + year;
            String title3 = cposg.getDepartmentName();//cposg.getCampusProgramOfStudy().getProgramOfStudy().getDiscipline().getDepartment().toString();
            String title4 = posName;
            
            if(!(cposg.getCampusProgramOfStudy().getCampus().getIsMain())) {
                Campus campus = cposg.getCampusProgramOfStudy().getCampus();
                title3 = campus.getName() + ", " + campus.getLocation();
            }
            
            final int w = (int)pf.getWidth() - 20;
            int h = (int)pf.getHeight() - 20;
            
            Graphics2D g2d = (Graphics2D) graphics;
            g2d.translate(pf.getImageableX(), pf.getImageableY());
            g2d.setPaint(Color.black);

            Font font = new Font("Times New Roman", Font.BOLD, 12);
            g2d.setFont(font);
            FontMetrics fm = g2d.getFontMetrics();
            
            int y = 10; // x = 270;
            int x = 10;
            
            String str = (pageNo) + "";
            int sw = fm.stringWidth(str);
            g2d.drawString(str, (int)(w - sw), y += 10);
            g2d.drawString(DateUtility.getDateToString(new Date()), x, y);
//            g2d.drawString("17-01-2014`", 0, y);
            
            str = title1;
            sw = fm.stringWidth(str);
            g2d.drawString(str, (int)(w - sw) / 2, y += 10);
            
            str = title2;
            sw = fm.stringWidth(str);
            g2d.drawString(str, (int)(w - sw) / 2, y += 15);
            
            str = title3;
            sw = fm.stringWidth(str);
            g2d.drawString(str, (int)(w - sw) / 2, y += 15);
            
            str = title4;
            sw = fm.stringWidth(str);
            g2d.drawString(str, (int)(w - sw) / 2, y += 15);
            
            font = new Font("Times New Roman", Font.BOLD, 10);
            g2d.setFont(font);
            
            g2d.drawString("SNO.", x, y += 20);
            g2d.drawString("SEAT#", x + 35, y);
            g2d.drawString("NAME", x + 75, y);
            g2d.drawString("FATHER'S NAME", x + 240, y);
            g2d.drawString("SURNAME", x + 400, y);
            g2d.drawString("ROLL NO.", x + 490, y);
            g2d.drawLine(x, y += 5, w, y);
            
            y -= 5;
            
            font = new Font("Times New Roman", Font.PLAIN, 10);
            g2d.setFont(font);
            
            for (int i = 0; i < data.size(); i++) {
                Object[] objects = data.get(i);
                
                if(objects[5] != null)
                    objects[5] = RollNoFormatter.format(year, cposg.getCode(), (int)objects[5]);
                else objects[5] = "";
                
                g2d.drawString("" + objects[0], x + 0, y += 15);
                g2d.drawString("" + objects[1], x + 35, y);
                g2d.drawString("" + objects[2], x + 75, y);
                g2d.drawString("" + objects[3], x + 240, y);
                g2d.drawString("" + objects[4], x + 400, y);
                g2d.drawString("" + objects[5], x + 490, y);
            }
            g2d.drawLine(x + 0, y += 5, w, y);
            y += 5;
            
            font = new Font("Times New Roman", Font.BOLD, 10);
            g2d.setFont(font);
            
            title4 = "ASSISTANT DIRECTOR ADMISSIONS";
//            String title5 = "DEPUTY DIRECTOR ADMISSIONS";
            String title51 = "UNIVERSITY OF SINDH, JAMSHORO";
            String title6 = "NOTE: THE UNIVERSITY OF SINDH RESERVES THE RIGHT TO CORRECT ANY ERROR/OMMISION DETECTED LATER ON,";
            String title61 = "AND ALSO RESERVES THE RIGHT TO CANCEL ANY PROVISIONAL ADMISSION AT ANY TIME WITHOUT ISSUING NOTICE.";
            String title7 = "COMPUTER PROGRAMMER";
            
            g2d.drawString(title7, x, h - 35);
            
//            str = title4;
//            sw = fm.stringWidth(str);
//            g2d.drawString(title4, (w - sw) / 2, h - 25);
//            str = title51;
//            sw = fm.stringWidth(str);
//            g2d.drawString(title51, (w - sw) / 2, h - 47);

            str = title4;
            sw = fm.stringWidth(str);
            g2d.drawString(title4, x + w - sw, h - 35);
//            str = title51;
//            sw = fm.stringWidth(str);
//            g2d.drawString(title51, w - sw, h - 47);

            font = new Font("Times New Roman", Font.BOLD, 8);
            g2d.setFont(font);
            
            g2d.drawString(title6, x + 0, h - 20);
            g2d.drawString(title61, x + 0, h - 10);
            
            return PAGE_EXISTS;
        }
    }
}
