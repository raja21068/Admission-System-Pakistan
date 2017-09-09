
package admission.utils;

import com.hexiong.jdbf.DBFWriter;
import com.hexiong.jdbf.JDBFException;
import com.hexiong.jdbf.JDBField;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Yougeshwar
 */
public class DBFGenerator {
    private String path;
    private DBFWriter dbfWriter;
    
    public DBFGenerator(String path, int code) throws JDBFException {
        this.path = path;
        if(code == IConstant.BACHELOR) 
            bachalorFields();
        else if(code == IConstant.MASTER) 
            masterFields();
        else {
            Logger.getLogger(DBFGenerator.class.getName()).log(Level.WARNING, "Invalide code");
        }
    }
    
    private void bachalorFields() throws JDBFException {
        JDBField[] fields = {
            new JDBField("ID", 'C', 8, 0),
            new JDBField("Name", 'C', 32, 0),
            new JDBField("TestN", 'N', 20, 0),
            new JDBField("TestF", 'F', 20, 6),
            new JDBField("TestD", 'D', 8, 0)
        };
        dbfWriter = new DBFWriter(path + "bachalor.dbf", fields);
    }
    
    private void masterFields() throws JDBFException {
        JDBField[] fields = {
            new JDBField("SEATNO", 'N', 8, 0),       /* 0 */
            new JDBField("FORMNO", 'N', 8, 0),       /* 1 */
            new JDBField("SEX", 'C', 1, 0),          /* 2 */
            new JDBField("RELIGION", 'N', 1, 0),     /* 3 */
            new JDBField("CAT1", 'N', 2, 0),         /* 4 */
            new JDBField("CAT2", 'N', 2, 0),         /* 5 */
            new JDBField("CAT3", 'N', 2, 0),         /* 6 */
            new JDBField("CAT4", 'N', 2, 0),         /* 7 */
            new JDBField("CAT5", 'N', 2, 0),         /* 8 */
            new JDBField("CAT6", 'N', 2, 0),         /* 9 */
            new JDBField("NAME", 'C', 32, 0),        /* 10*/
            new JDBField("FNAME", 'C', 32, 0),       /* 11*/
            new JDBField("SURNAME", 'C', 32, 0),     /* 12*/
            new JDBField("DISTRICT", 'N', 2, 0),     /* 13*/
            new JDBField("URBAN", 'C', 1, 0),        /* 14*/
            new JDBField("OPT1", 'N', 2, 0),         /* 15*/
            new JDBField("OPT2", 'N', 2, 0),         /* 16*/
            new JDBField("OPT3", 'N', 2, 0),         /* 17*/
            new JDBField("CH1", 'N', 2, 0),          /* 18*/
            new JDBField("CH2", 'N', 2, 0),          /* 19*/
            new JDBField("CH3", 'N', 2, 0),          /* 20*/
            new JDBField("CH4", 'N', 2, 0),          /* 21*/
            new JDBField("CH5", 'N', 2, 0),          /* 22*/
            new JDBField("CH6", 'N', 2, 0),          /* 23*/
            new JDBField("CH7", 'N', 2, 0),          /* 24*/
            new JDBField("CH8", 'N', 2, 0),          /* 25*/
            new JDBField("CH9", 'N', 2, 0),          /* 26*/
            new JDBField("CH10", 'N', 2, 0),         /* 27*/
            new JDBField("SFCH1", 'N', 2, 0),        /* 28*/
            new JDBField("SFCH2", 'N', 2, 0),        /* 29*/
            new JDBField("SFCH3", 'N', 2, 0),        /* 30*/
            new JDBField("SFCH4", 'N', 2, 0),        /* 31*/
            new JDBField("SFCH5", 'N', 2, 0),        /* 32*/
            new JDBField("SFCH6", 'N', 2, 0),        /* 33*/
            new JDBField("SFCH7", 'N', 2, 0),        /* 34*/
            new JDBField("SFCH8", 'N', 2, 0),        /* 35*/
            new JDBField("SFCH9", 'N', 2, 0),        /* 36*/
            new JDBField("SFCH10", 'N', 2, 0),       /* 37*/
            new JDBField("OBJ", 'C', 1, 0),          /* 38*/
            new JDBField("OBJREMARKS", 'C', 128, 0), /* 39*/
            
            new JDBField("SSCGROUP", 'N', 2, 0),     /* 40*/
            new JDBField("SSCMOBT", 'N', 3, 0),      /* 41*/
            new JDBField("SSCOUTOF", 'N', 4, 0),     /* 42*/
            new JDBField("SSCYEAR", 'N', 4, 0),      /* 43*/
            new JDBField("SSCSEATNO", 'N', 10, 0),   /* 44*/
            new JDBField("SSCBOARD", 'N', 2, 0),     /* 45*/
            new JDBField("SSCPER", 'F', 10, 2),       /* 46*/
            new JDBField("HSCGROUP", 'N', 2, 0),     /* 47*/
            new JDBField("HSCMOBT", 'N', 4, 0),      /* 48*/
            new JDBField("HSCOUTOF", 'N', 4, 0),     /* 49*/
            new JDBField("HSCYEAR", 'N', 4, 0),      /* 50*/
            new JDBField("HSCSEATNO", 'N', 10, 0),   /* 51*/
            new JDBField("HSCBOARD", 'N', 2, 0),     /* 52*/
            new JDBField("HSCPER", 'F', 10, 2),       /* 53*/
            new JDBField("GRDGROUP", 'N', 2, 0),     /* 54*/
            new JDBField("GRDPERIOD", 'N', 2, 0),    /* 55*/
            new JDBField("GRDMOBT", 'N', 4, 0),      /* 56*/
            new JDBField("GRDLESS", 'N', 2, 0),      /* 57*/
            new JDBField("GRDOUTOF", 'N', 5, 0),     /* 58*/
            new JDBField("GRDYEAR", 'N', 4, 0),      /* 59*/
            new JDBField("GRDSEATNO", 'N', 10, 0),   /* 60*/
            new JDBField("GRDBOARD", 'N', 2, 0),     /* 61*/
            new JDBField("GRDPER", 'F', 10, 6),       /* 62*/
            new JDBField("TESTMARKS", 'N', 3, 0),    /* 63*/
            new JDBField("FINALSCORE", 'F', 10, 2),   /* 64*/
            new JDBField("CATSEL1", 'C', 100, 0),      /* 65*/
            new JDBField("CATSEL2", 'C', 100, 0),      /* 66*/
            new JDBField("CATSEL3", 'C', 100, 0),      /* 67*/
            new JDBField("CATSEL4", 'C', 100, 0),      /* 68*/
            new JDBField("CATSEL5", 'C', 100, 0),      /* 69*/
            new JDBField("SEL1", 'N', 3, 0),      /* 70*/
            new JDBField("SHIFT1", 'N', 1, 0),      /* 71*/
            new JDBField("CHNO1", 'N', 5, 0),      /* 72*/
            new JDBField("CHAMT1", 'N', 5, 0),      /* 73*/
            new JDBField("CHDT1", 'D', 8, 0),      /* 74*/
            new JDBField("SEL2", 'N', 3, 0),      /* 70*/
            new JDBField("SHIFT2", 'N', 1, 0),      /* 71*/
            new JDBField("CHNO2", 'N', 5, 0),      /* 72*/
            new JDBField("CHAMT2", 'N', 5, 0),      /* 73*/
            new JDBField("CHDT2", 'D', 8, 0),      /* 74*/
            new JDBField("RCHNO3", 'N', 5, 0),      /* 72*/
            new JDBField("RCHAMT3", 'N', 5, 0),      /* 73*/
            new JDBField("RCHDT3", 'D', 8, 0),      /* 74*/
        };
        
        dbfWriter = new DBFWriter(path + "master.dbf", fields);
    }
    
    public void addRecord(Object[] record) throws JDBFException {
        dbfWriter.addRecord(record);
    }
    
    public void close() throws JDBFException {
        if(dbfWriter != null)
            dbfWriter.close();
    }
}