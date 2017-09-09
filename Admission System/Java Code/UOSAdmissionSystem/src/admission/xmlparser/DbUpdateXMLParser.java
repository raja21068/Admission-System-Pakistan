package admission.xmlparser;

import java.io.InputStream;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.jaxen.JaxenException;
import org.jaxen.XPath;
import org.jaxen.dom4j.Dom4jXPath;
import admission.utils.MessageBox;

/**
 *
 * @author Yougeshwar
 */
public final class DbUpdateXMLParser {

    private static final List<Update> list = new ArrayList();

    static {
        try {
            DbUpdateXMLParser dbUpdateXMLParser = new DbUpdateXMLParser();
            list.addAll(dbUpdateXMLParser.getList());
        } catch (URISyntaxException ex) {
            MessageBox.error(null, ex);
            Logger.getLogger(DbUpdateXMLParser.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private final List<Update> dbUpdateList = new ArrayList<>();

    private DbUpdateXMLParser() throws URISyntaxException {
        InputStream in = getClass().getResourceAsStream("/admission/xml/dbupdate.xml");
        SAXReader reader = new SAXReader();

        Document dom4jDocument;
        XPath path;
        List<Element> results;

        try {

            dom4jDocument = reader.read(in);
            path = new Dom4jXPath("dbupdate/update");
            results = path.selectNodes(dom4jDocument);

            for (Element element : results) {
                String version = element.attribute("version").getData().toString().trim();
                String type = element.attribute("type").getData().toString().trim();
                String content = element.getData().toString().replaceAll("^\\s+|\\s+$|\\s*(\n)\\s*|(\\s)\\s*", "$1$2").replace("\t"," ").replaceAll("\n", " ");//element.getData().toString().trim();
                
                Update update = new Update();
                update.setVersion(version);
                update.setType(type);
                update.setContent(content);
                
                dbUpdateList.add(update);
            }

        } catch (JaxenException | DocumentException ex) {
            MessageBox.error(null, ex);
            Logger.getLogger(DbUpdateXMLParser.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<Update> getList() {
        return dbUpdateList;
    }

    public static List<Update> getDbUpdateList() {
        return list;
    }
}
