package admission.xmlparser;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
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
public final class OldDbUpdateXMLParser extends DefaultHandler {

    private static final List<Update> list = new ArrayList();

    static {
        try {
            OldDbUpdateXMLParser dbUpdateXMLParser = new OldDbUpdateXMLParser();
            list.addAll(dbUpdateXMLParser.getList());
        } catch (SAXException | IOException ex) {
            MessageBox.error(null, ex);
            Logger.getLogger(OldDbUpdateXMLParser.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private final List<Update> dbUpdateList = new ArrayList<>();

    private OldDbUpdateXMLParser() throws SAXException, FileNotFoundException, IOException {
        super();

        XMLReader xr = XMLReaderFactory.createXMLReader();
        xr.setContentHandler(this);
        xr.setErrorHandler(this);

        xr.parse(new InputSource(getClass().getResourceAsStream("/admission/xml/dbupdate.xml")));
    }

    private Update update;

    @Override
    public void startElement(String uri, String name, String qName, Attributes attributes) throws SAXException {
        switch (name) {
            case "update":
                String version = attributes.getValue("version");
                String type = attributes.getValue("type");
                update = new Update();
                update.setVersion(version);
                update.setType(type);
                break;
        }
    }

    StringBuilder sb = new StringBuilder("");

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        String content = new String(ch, start, length).trim();
        if (update != null && !content.isEmpty()) {
            sb.append(content).append(" ");
        }
    }

    @Override
    public void endElement(String uri, String name, String qName) throws SAXException {
        switch (name) {
            case "update":
                update.setContent(sb.toString());
                sb = new StringBuilder("");
                dbUpdateList.add(update);
                update = null;
                break;
        }
    }

    public List<Update> getList() {
        return dbUpdateList;
    }

    public static List<Update> getDbUpdateList() {
        return list;
    }
}
