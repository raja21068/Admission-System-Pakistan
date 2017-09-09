package admission.utils;

import admission.reports.beans.CredentialDetailJRBean;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.LinkedList;
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

    public static String[] MASTER_SELECTED_COLUMNS= {"S.NO", "Candidate_Id", "Admission_List_Id","Cpos_group_id" ,"Candidate_Program_Of_Study_Id","Campus_Category_Id", "Category_code" , "Cat_logical_code" ,"SEAT NO","Name","Father" ,"DISTRICT","Area","PERC", "CHOICES", "LAST CRED", "YEARS_DEGREE", "Optional Subjects"};
    public static String[] MASTER_NOT_SELECTED_COLUMNS= {"S.NO", "SEAT NO", "STUDENT NAME", "FATHER NAME", "DISTRICT", "AREA", "PERC", "CHOICENO", "CATEGORY", "PROGRAM", "CHOICES", "LAST CRED", "YEARS_DEGREE", "Optional Subjects"};
    public static String[] MASTER_DISCIPLINE_COLUMNS= {"S.No", "Seat No", "Name", "Father's Name", "District", "Area", "Choice#", "", "SSC-OB", "SSC-T", "SSC-Year", "SSC-GRP","HSC-OB", "HSC-T","HSC-Year", "HSC-GRP", "DEG-OB", "DEG-T", "GRD-Year","GRD-DEG","S"+IConstant.MATRIC_PER+"%","H"+IConstant.M_INTER_PER+"%","D"+IConstant.DEGREE_PER+"%","Score","Score%","Total%"};   
    
    public static String[] BACHALOR_SELECTED_COLUMNS= {"S.NO", "Candidate_Id", "Admission_List_Id","Cpos_group_id" ,"Candidate_Program_Of_Study_Id","Campus_Category_Id", "Category_code" , "Cat_logical_code" ,"SEAT NO","Name","Father" ,"DISTRICT","Area","PERC", "CHOICES", "LAST CRED", "YEARS_DEGREE", "Optional Subjects"};
    public static String[] BACHALOR_NOT_SELECTED_COLUMNS= {"S.NO", "SEAT NO", "STUDENT NAME", "FATHER NAME", "DISTRICT", "AREA", "PERC", "CHOICENO", "CATEGORY", "PROGRAM", "CHOICES", "LAST CRED"};
    public static String[] BACHALOR_DISCIPLINE_COLUMNS= {"S.No", "Seat No","X","Name", "Father's Name", "District", "Area", "Choice#", "", "SSC-OB", "SSC-T", "SSC-Year", "SSC-GRP","HSC-OB", "HSC-T","HSC-Year", "HSC-GRP","S"+IConstant.MATRIC_PER+"%","H"+IConstant.INTER_PER+"%","Ded. Marks","AFT.Ded.","TST_SCR","TST%","CPN"};
    
    String filePath;
    HSSFWorkbook book;
    HSSFSheet sheet;

    public ExcelHandler(String filePath) {
        this.filePath = filePath;
        createNewFile();
    }

    public ExcelHandler(File file) {
        this.filePath = file.getAbsolutePath();
        createNewFile();
    }

    public void createNewFile() {
        book = new HSSFWorkbook();
    }

    public void createSheet() {
        sheet = book.createSheet();
    }

    public void createSheet(String sheetName) {
        sheet = book.createSheet(sheetName);
    }

    public void appendRow(Object[] row) {
        int lastRow = sheet.getLastRowNum();
        Row workRow = sheet.createRow(lastRow + 1);
        int column = 0;
        for (int i = 0; i < row.length; i++) {
            Cell cell = workRow.createCell(column);
//            cell.setCellValue((String)row[i]);
            if (row[i] instanceof String) {
                cell.setCellValue((String) row[i]);
            } else if (row[i] instanceof Integer) {
                cell.setCellValue((Integer) row[i]);
            } else if (row[i] instanceof Double) {
                cell.setCellValue((String) row[i]);
            } else if (row[i] instanceof ArrayList) {
                try {
                    ArrayList<CredentialDetailJRBean> list = (ArrayList<CredentialDetailJRBean>) row[i];
                    int index = 0;
                    LinkedList<Float> listCrePerc = new LinkedList<>();
                    for (CredentialDetailJRBean cd : list) {
//                        workRow.createCell(++column).setCellValue(cd.getDetailOf());
                        workRow.createCell(++column).setCellValue(cd.getoMrks());
                        workRow.createCell(++column).setCellValue(cd.gettMrks());
                        workRow.createCell(++column).setCellValue(cd.getYear());
                        workRow.createCell(++column).setCellValue(cd.getGrp());
                        if (index == 0) {
                            if (list.size() == 3) {
                                Float matric = (cd.getoMrks() / cd.gettMrks()) * 100.0F * IConstant.M_MATRIC_PER;
                                listCrePerc.add(matric);
                            }else{
                                Float matric = (cd.getoMrks() / cd.gettMrks()) * 100.0F * IConstant.MATRIC_PER;
                                listCrePerc.add(matric);
                            }
                        } else if (index == 1) {
                            if (list.size() == 3) {
                                Float inter = (cd.getoMrks() / cd.gettMrks()) * 100.0F * IConstant.M_INTER_PER;
                                listCrePerc.add(inter);
                            }else if(list.size() == 2){
                                Float inter = (cd.getoMrks() / cd.gettMrks()) * 100.0F * IConstant.INTER_PER;
                                listCrePerc.add(inter);
                            }
                        } else if (index == 2) {
                            Float deg = (cd.getoMrks() / cd.gettMrks()) * 100.0F * IConstant.DEGREE_PER;
                            listCrePerc.add(deg);
                        }
                        index++;
                    }
                    for (Float perc : listCrePerc) {
                        workRow.createCell(++column).setCellValue(perc);
                    }
                } catch (Exception ex) {
                    ex.printStackTrace(System.err);
                }
            } else {
                cell.setCellValue(row[i] == null ? "" : row[i].toString());

            }
            column++;
        }
    }

//    public void appendRow(Object[] row) {
//        int lastRow = sheet.getLastRowNum();
//        Row workRow = sheet.createRow(lastRow + 1);
//        for (int i = 0; i < row.length; i++) {
//            Cell cell = workRow.createCell(i);
//            cell.setCellValue((String) row[i]);
//        }
//    }
    public void readyAndClose() throws Exception {
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
