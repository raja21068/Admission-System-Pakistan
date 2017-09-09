package admission.controller.excel;

import java.io.File;
import java.io.FileOutputStream;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author JAY KUMAR
 */
public class ExcelHandler {

    String filePath;
    HSSFWorkbook book;
    HSSFSheet sheet;
    public ExcelHandler(String filePath){
        this.filePath = filePath;
        createNewFile();
    }
    public ExcelHandler(File file){
        this.filePath = file.getAbsolutePath();
        createNewFile();
    }
    
    public void createNewFile(){
        book = new HSSFWorkbook();
    }
    
    public void createSheet(){
        sheet = book.createSheet();
    }
    public void createSheet(String sheetName){
        sheet = book.createSheet(sheetName);
    }
    
    public void appendRow(Object[] row){
        int lastRow = sheet.getLastRowNum();
        Row workRow = sheet.createRow(lastRow+1);
        for(int i=0;i<row.length;i++){
            Cell cell = workRow.createCell(i);
            cell.setCellValue((String)row[i]);
        }
    }

    public void readyAndClose()throws Exception{
        FileOutputStream out = new FileOutputStream(new File(filePath));
        book.write(out);
        out.close();
    }
    
//    @Override
//    protected void finalize() throws Throwable {
//        super.finalize(); //To change body of generated methods, choose Tools | Templates.
//        FileOutputStream out = new FileOutputStream(filePath);
//        book.write(out);
//        out.close();
//    }
    
    
}
