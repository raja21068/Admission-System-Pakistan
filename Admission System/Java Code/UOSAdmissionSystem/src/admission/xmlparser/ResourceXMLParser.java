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
public final class ResourceXMLParser extends DefaultHandler {

    private static final List list = new ArrayList();
    
    static {
//        list.add("All Reports");
        
        try {
            ResourceXMLParser reportXMLParser = new ResourceXMLParser();
            list.addAll(reportXMLParser.getMap().values());
        } catch (SAXException | IOException ex) {
            MessageBox.error(null, ex);
            Logger.getLogger(ResourceXMLParser.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private final HashMap<String, ResourceCategory> map = new HashMap<>();

    private ResourceXMLParser() throws SAXException, FileNotFoundException, IOException {
        super();

        XMLReader xr = XMLReaderFactory.createXMLReader();
        xr.setContentHandler(this);
        xr.setErrorHandler(this);

        xr.parse(new InputSource(getClass().getResourceAsStream("/admission/xml/config.xml")));
    }

    @Override
    public void startElement(String uri, String name, String qName, Attributes attributes) throws SAXException {
        switch (name) {
            case "config":
                break;
            case "resource":
                String categoryName = attributes.getValue("category");
                String code = attributes.getValue("code");
                String resourceName = attributes.getValue("name");
                ResourceCategory rc = map.get(categoryName);
                if (rc == null) {
                    rc = new ResourceCategory();
                    rc.setName(categoryName);
                    map.put(categoryName, rc);
                }
                Resource resource = new Resource();
                resource.setCode(code);
                resource.setName(resourceName);
                rc.addResource(resource);
                break;
        }
    }

    @Override
    public void endElement(String uri, String name, String qName) throws SAXException {
    }

    public HashMap<String, ResourceCategory> getMap() {
        return map;
    }

    public static List<ResourceCategory> getResourceCategoryList() {
        return list;
    }
}
