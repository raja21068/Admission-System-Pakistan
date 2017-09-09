package admission.xmlparser;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import admission.utils.MessageBox;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.jaxen.JaxenException;
import org.jaxen.XPath;
import org.jaxen.dom4j.Dom4jXPath;

/**
 *
 * @author Yougeshwar
 */
public final class ReportXMLParser {

    private Map<String, ReportCategory> map = new LinkedHashMap<>();
    private static final List<Object> list;

    static {
        list = new ArrayList<>();
        list.add("All Reports");
        
        try {
            ReportXMLParser reportXMLParser = new ReportXMLParser();
            list.addAll(reportXMLParser.getMap().values());
        } catch (URISyntaxException ex) {
            MessageBox.error(null, ex);
            Logger.getLogger(ReportXMLParser.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private ReportXMLParser() throws URISyntaxException {
        InputStream in = getClass().getResourceAsStream("/admission/xml/report.xml");
        SAXReader reader = new SAXReader();

        Document dom4jDocument;
        XPath path;
        List<Element> results;

        try {

            dom4jDocument = reader.read(in);
            path = new Dom4jXPath("reports/report");
            results = path.selectNodes(dom4jDocument);
            for (Element element : results) {
                String categoryName = element.attribute("category").getData().toString().trim();
                String name = element.attribute("name").getData().toString().trim();
                String jasperFile = element.attribute("jasper-file").getData().toString().trim();
                String filterClass = element.attribute("filter-class").getData().toString().trim();
                String securityContext = element.attribute("security-context").getData().toString().trim();
                String desc = element.attribute("description").getData().toString().trim();

                Report report = new Report();
                report.setName(name);
                report.setDescription(desc);
                report.setFilterClass(filterClass);
                report.setJasperFile(jasperFile);
                report.setSecurityContext(securityContext);

                ReportCategory rc = map.get(categoryName);
                if (rc == null) {
                    rc = new ReportCategory();
                    rc.setName(categoryName);
                    map.put(categoryName, rc);
                }
                rc.addReport(report);
            }

        } catch (JaxenException | DocumentException ex) {
            MessageBox.error(null, ex);
            Logger.getLogger(DbUpdateXMLParser.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Map<String, ReportCategory> getMap() {
        return map;
    }

    public static List getReportCategoryList() {
        return list;
    }
}
