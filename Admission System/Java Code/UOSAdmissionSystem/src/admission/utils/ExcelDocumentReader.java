package admission.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author Raja Kumar & Jay Kumar
 */
public class ExcelDocumentReader {

    private HSSFSheet xlsSheet;
    private XSSFSheet xlsxSheet;
    private String path;
    boolean b;

    public ExcelDocumentReader(String path) throws IOException {
        this.path = path;
        b = path.endsWith("xls");

        InputStream ExcelFileToRead = new FileInputStream(this.path);
        if (b) {
            HSSFWorkbook wb = new HSSFWorkbook(ExcelFileToRead);
            xlsSheet = wb.getSheetAt(0);
        } else {
            XSSFWorkbook wb = new XSSFWorkbook(ExcelFileToRead);
            xlsxSheet = wb.getSheetAt(0);
        }
    }

    public int getRowCount() {
        return b ? xlsSheet.getLastRowNum() : xlsxSheet.getLastRowNum();
    }

    public Iterator<Row> getRowIterator() {
        return b ? xlsSheet.rowIterator() : xlsxSheet.rowIterator();
    }
    /*
     public Vector<Vector<String>> readXLSFile(String path) throws IOException{
     InputStream ExcelFileToRead = new FileInputStream(path);
     HSSFWorkbook wb = new HSSFWorkbook(ExcelFileToRead);
     xlsSheet = wb.getSheetAt(0);
        
     Iterator rows = xlsSheet.rowIterator();
 
     Vector<Vector<String>> v = new Vector<>();
     while (rows.hasNext()){
     HSSFRow row = (HSSFRow) rows.next();
            
     Vector<String> vRow = new Vector<>();
            
     Iterator cells = row.cellIterator();
     while (cells.hasNext()){
     HSSFCell cell = (HSSFCell) cells.next();
     vRow.add(cell.toString());
     }
     v.add(vRow);
     }
     return v;
     }
    
     public Vector<Vector<String>> readXLSXFile(String path) throws IOException{
     InputStream ExcelFileToRead = new FileInputStream(path);
     XSSFWorkbook  wb = new XSSFWorkbook(ExcelFileToRead);
		
     xlsxSheet = wb.getSheetAt(0);
        
     //        System.out.println(sheet.getLastRowNum());
     //        System.out.println(sheet.getFirstRowNum());
        
     Vector<Vector<String>> v = new Vector<>();
     Iterator rows = sheet.rowIterator();
     while (rows.hasNext()){
     XSSFRow row = (XSSFRow) rows.next();
            
     Vector<String> vRow = new Vector<>();
            
     Iterator cells = row.cellIterator();
     while (cells.hasNext()){
     XSSFCell cell = (XSSFCell) cells.next();
     vRow.add(cell.toString());
     }
     v.add(vRow);
     }
     return v;
     }*/
}
