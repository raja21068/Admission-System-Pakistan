package admission.dbupdate;

import admission.xmlparser.DbUpdateXMLParser;
import admission.xmlparser.Update;
import admission.xmlparser.View;
import admission.xmlparser.ViewXMLParser;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import admission.utils.IConstant;

/**
 *
 * @author Yougeshwar
 */
public class DbUpdateExecuter {

    public static void execute() {
        try {
            System.out.println("Checking version...");

            List<Update> dbUpdateList = DbUpdateXMLParser.getDbUpdateList();
            boolean b = true;

            if (!dbUpdateList.isEmpty()) {
                DBConn conn = new DBConn();
                int dbVersion = conn.getDBVersion();

                for (Update update : dbUpdateList) {

                    int ver = Integer.parseInt(update.getVersion());

                    if (ver > dbVersion) {
                        if (update.getType().equals("sql")) {

                            try {
                                conn.executeUpdate(update.getContent().trim());
                            } catch (SQLException ex) {
                                Logger.getLogger(DbUpdateExecuter.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            try {
                                conn.executeDBVersion(ver, System.currentTimeMillis());
                            } catch (SQLException ex) {
                                Logger.getLogger(DbUpdateExecuter.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            System.out.println(update.getContent());
                            System.out.println("Version updated " + ver);
                            b = false;
                            dbVersion = ver;
                        } else if (update.getType().startsWith("view")) {

                            List<View> viewList = ViewXMLParser.getViewList();

                            for (View view : viewList) {

                                if (view.getName().equals(update.getContent().trim())) {
                                    switch (update.getType()) {
                                        case "view-create":
                                            try {
                                                System.out.println("CREATE VIEW " + view.getName() + " AS " + view.getContent());
                                                conn.executeUpdate("CREATE VIEW " + view.getName() + " AS " + view.getContent());
                                            } catch (SQLException ex) {
                                                Logger.getLogger(DbUpdateExecuter.class.getName()).log(Level.SEVERE, null, ex);
                                                break;
                                            }
                                            try {
                                                conn.executeDBVersion(ver, System.currentTimeMillis());
                                            } catch (SQLException ex) {
                                                Logger.getLogger(DbUpdateExecuter.class.getName()).log(Level.SEVERE, null, ex);
                                                break;
                                            }
                                            System.out.println("Version updated " + ver);
                                            b = false;
                                            dbVersion = ver;
                                            break;
                                        case "view-drop-create":
                                            try {
                                                System.out.println("DROP VIEW " + view.getName());
                                                conn.executeUpdate("DROP VIEW " + view.getName());
                                            } catch (SQLException ex) {
                                                Logger.getLogger(DbUpdateExecuter.class.getName()).log(Level.SEVERE, null, ex);
                                                break;
                                            }
                                            try {
                                                System.out.println("CREATE VIEW " + view.getName() + " AS " + view.getContent());
                                                conn.executeUpdate("CREATE VIEW " + view.getName() + " AS " + view.getContent());
                                            } catch (SQLException ex) {
                                                Logger.getLogger(DbUpdateExecuter.class.getName()).log(Level.SEVERE, null, ex);
                                                break;
                                            }
                                            try {
                                                conn.executeDBVersion(ver, System.currentTimeMillis());
                                            } catch (SQLException ex) {
                                                Logger.getLogger(DbUpdateExecuter.class.getName()).log(Level.SEVERE, null, ex);
                                                break;
                                            }
                                            System.out.println("Version updated " + ver);
                                            b = false;
                                            dbVersion = ver;
                                            break;
                                        case "view-drop":
                                            try {
                                                System.out.println("DROP VIEW " + view.getName());
                                                conn.executeUpdate("DROP VIEW " + view.getName());
                                            } catch (SQLException ex) {
                                                Logger.getLogger(DbUpdateExecuter.class.getName()).log(Level.SEVERE, null, ex);
                                                break;
                                            }
                                            try {
                                                conn.executeDBVersion(ver, System.currentTimeMillis());
                                            } catch (SQLException ex) {
                                                Logger.getLogger(DbUpdateExecuter.class.getName()).log(Level.SEVERE, null, ex);
                                                break;
                                            }
                                            System.out.println("Version updated " + ver);
                                            b = false;
                                            dbVersion = ver;
                                            break;
                                    }
                                    break;
                                }
                            }
                        }
                    }
                }
            }
            if (b) {
                System.out.println("Version update to date");
            }
        } catch (SQLException ex) {
            Logger.getLogger(DbUpdateExecuter.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static class DBConn {

        private Connection con;

        public DBConn() {
            try {
                init();
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(DBConn.class.getName()).log(Level.SEVERE, null, ex);
                admission.utils.MessageBox.info(null, "Library not running");
                System.exit(0);
            } catch (SQLException ex) {
                Logger.getLogger(DBConn.class.getName()).log(Level.SEVERE, null, ex);
                admission.utils.MessageBox.info(null, "Database server not running");
                System.exit(0);
            }
        }

        private void init() throws ClassNotFoundException, SQLException {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(IConstant.DB.URL, IConstant.DB.USERNAME, IConstant.DB.PASSWORD);
        }

        public int getDBVersion() throws SQLException {
            String sql = "SELECT MAX(VERSION) AS VERSION FROM YOG_DB_UPDATE";
            int version = 0;
            try (Statement st = con.createStatement();) {
                try (ResultSet rs = st.executeQuery(sql);) {
                    if (rs.next()) {
                        version = rs.getInt("VERSION");
                    }
                }
                return version;
            }
        }

        public int executeUpdate(String sql) throws SQLException {
            try (Statement st = con.createStatement();) {
                return st.executeUpdate(sql);
            }
        }

        public int executeDBVersion(int version, long modifiedDate) throws SQLException {
            String sql = "UPDATE YOG_DB_UPDATE SET VERSION = " + version + ", MODIFIED_DATE = " + modifiedDate;
            try (Statement st = con.createStatement();) {
                return st.executeUpdate(sql);
            }
        }
    }
}
