package admission.reports;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.print.Book;
import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.MediaPrintableArea;
import javax.print.attribute.standard.PrinterResolution;
import org.imgscalr.Scalr;
import admission.utils.ImageUtils;

/**
 *
 * @author Raja Kumar & Jay Kumar
 */
public class IDCardReport {
    private static final int WIDTH = 3 * 72;
    private static final int HEIGHT = 2 * 72;
    
    public static void main(String[] args) throws PrinterException {
        new IDCardReport().print();
    }
    public IDCardReport() {
    }
    
//    public void print(int year, CposGroup cposg, List<Object[]> dataList) throws PrinterException, SQLException {
    public void print() throws PrinterException {
        
        PageFormat pageFormat = getPageFormat();
        Book book = new Book();
        book.append(new Page(), pageFormat);
        
        PrinterJob printJob = PrinterJob.getPrinterJob();
        printJob.setPageable(book);
        printJob.setJobName("Student ID Card - Admission Cell");
        
//        PrintRequestAttributeSet aset = new HashPrintRequestAttributeSet();
//        aset.add(new PrinterResolution(300, 300, PrinterResolution.DPI));
//        aset.add(new MediaPrintableArea(0, 0, 150, 100, MediaPrintableArea.MM));
        
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
        
        public Page() {
        }
        
        @Override
        public int print(Graphics graphics, PageFormat pf, int pageIndex) throws PrinterException {
            if(!access){
                access = true;
                return PAGE_EXISTS;
            }
            
            final int w = (int) pf.getWidth() - 20;
            int h = (int) pf.getHeight() - 20;
            
            Graphics2D g2d = (Graphics2D) graphics;
            g2d.translate(pf.getImageableX(), pf.getImageableY());
            g2d.setPaint(Color.black);
            Font font = new Font("Arial", Font.BOLD, 12);
            g2d.setFont(font);
            FontMetrics fm = g2d.getFontMetrics();
            
            Image image = new javax.swing.ImageIcon(getClass().getResource("images/card_logo.png")).getImage();
            g2d.drawImage(image, 11, 11, null);
            g2d.drawRect(10, 10, WIDTH, HEIGHT);
            
            String s = "UNIVERSITY OF SINDH";
            int sw = fm.stringWidth(s);
            g2d.drawString(s, WIDTH - sw, 23);
            
            font = new Font("Arial", Font.PLAIN, 8);
            g2d.setFont(font);
            fm = g2d.getFontMetrics();
            
            s = "JAMSHORO / HYDERABAD";
            sw = fm.stringWidth(s);
            g2d.drawString(s, WIDTH - sw - 15, 35);
            
            font = new Font("Arial", 3, 10);
            g2d.setFont(font);
            fm = g2d.getFontMetrics();
            
            s = "CODE# 6084";
            g2d.drawString(s, 13, 10 + image.getHeight(null) + 10);
            
            return PAGE_EXISTS;
        }
    }
}
