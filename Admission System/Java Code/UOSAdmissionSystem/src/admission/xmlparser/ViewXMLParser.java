package admission.xmlparser;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import admission.utils.MessageBox;
import java.io.InputStream;
import java.net.URISyntaxException;
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
public final class ViewXMLParser {

    private static final List<View> list = new ArrayList();
    
    static {
        try {
            ViewXMLParser viewXMLParser = new ViewXMLParser();
            list.addAll(viewXMLParser.getList());
        } catch (URISyntaxException ex) {
            MessageBox.error(null, ex);
            Logger.getLogger(ViewXMLParser.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private final List<View> viewList = new ArrayList<>();

    private ViewXMLParser() throws URISyntaxException {
        InputStream in = getClass().getResourceAsStream("/admission/xml/views.xml");
        SAXReader reader = new SAXReader();

        Document dom4jDocument;
        XPath path;
        List<Element> results;

        try {

            dom4jDocument = reader.read(in);
            path = new Dom4jXPath("views/view");
            results = path.selectNodes(dom4jDocument);

            for (Element element : results) {
                String name = element.attribute("name").getData().toString().trim();
                String content = element.getData().toString().replaceAll("^\\s+|\\s+$|\\s*(\n)\\s*|(\\s)\\s*", "$1$2").replace("\t"," ").replaceAll("\n", " ");//element.getData().toString().trim();
                
                View view = new View();
                view.setName(name);
                view.setContent(content);
                
                viewList.add(view);
            }

        } catch (JaxenException | DocumentException ex) {
            MessageBox.error(null, ex);
            Logger.getLogger(DbUpdateXMLParser.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<View> getList() {
        return viewList;
    }

    public static List<View> getViewList() {
        return list;
    }
}
