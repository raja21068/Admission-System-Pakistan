package admission.xmlparser;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.helpers.XMLReaderFactory;
import admission.utils.MessageBox;

/**
 *
 * @author Yougeshwar
 */
public final class ReportXMLParser1 extends DefaultHandler {

    private static final List list = new ArrayList();
    
    static {
        list.add("All Reports");
        
        try {
            ReportXMLParser1 reportXMLParser = new ReportXMLParser1();
            list.addAll(reportXMLParser.getMap().values());
        } catch (SAXException | IOException ex) {
            MessageBox.error(null, ex);
            Logger.getLogger(ReportXMLParser.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private Report report;
    private String content;
    private final HashMap<String, ReportCategory> map = new HashMap<>();

    private ReportXMLParser1() throws SAXException, FileNotFoundException, IOException {
        super();

        XMLReader xr = XMLReaderFactory.createXMLReader();
        xr.setContentHandler(this);
        xr.setErrorHandler(this);

        xr.parse(new InputSource(getClass().getResourceAsStream("/admission/xml/report.xml")));
    }

    @Override
    public void startElement(String uri, String name, String qName, Attributes attributes) throws SAXException {
        switch (name) {
            case "reports":
                break;
            case "report":
                String categoryName = attributes.getValue("category");
                ReportCategory rc = map.get(categoryName);
                if (rc == null) {
                    rc = new ReportCategory();
                    rc.setName(categoryName);
                    map.put(categoryName, rc);
                }
                report = new Report();
                rc.addReport(report);
                break;
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        content = String.copyValueOf(ch, start, length).trim();
    }

    @Override
    public void endElement(String uri, String name, String qName) throws SAXException {
        switch (name) {
            case "report":
                report = null;
                break;
            case "name":
                report.setName(content);
                break;
            case "jasper-file":
                report.setJasperFile(content);
                break;
            case "filter-class":
                report.setFilterClass(content);
                break;
            case "security-context":
                report.setSecurityContext(content);
                break;
            case "description":
                report.setDescription(content);
        }
    }

    public HashMap<String, ReportCategory> getMap() {
        return map;
    }

    public static List<ReportCategory> getReportCategoryList() {
        return list;
    }
}
