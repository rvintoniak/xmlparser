import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by roman on 21.10.14.
 */
public class Parser {
    public final static String pathname = "C:\\file.xml";

    public List<Address> getData() throws Exception {

        File fXmlFile = new File(pathname);
        List<Address> list = new ArrayList<Address>();
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(fXmlFile);

        doc.getDocumentElement().normalize();
        NodeList nodeList = doc.getElementsByTagName("address");
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node nNode = nodeList.item(i);
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                Element eElement = (Element) nNode;
                list.add(new Address(Double.valueOf(eElement.getAttribute("latitude")), Double.valueOf(eElement.getAttribute("longitude")),eElement.getAttribute("description")));
            }
        }

        return list;
    }


    public void createFile(Address address) throws ParserConfigurationException, TransformerException {
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

        // root elements
        Document doc = docBuilder.newDocument();
        Element rootElement = doc.createElement("addresses");
        doc.appendChild(rootElement);

        // staff elements
        addNewElement(address, doc, rootElement);
    }

    public void updateFile(Address address) throws IOException, SAXException, ParserConfigurationException, TransformerException {
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

        // root elements
        Document doc = docBuilder.parse(new File(pathname));
        Element rootElement = doc.getDocumentElement();

        // staff elements
        addNewElement(address, doc, rootElement);
    }

    private void addNewElement(Address address, Document doc, Element rootElement) throws TransformerException {
        Element addressElement = doc.createElement("address");
        rootElement.appendChild(addressElement);

        // set attribute to staff element
        Attr latitude = doc.createAttribute("latitude");
        latitude.setValue(String.valueOf(address.getLatitude()));
        addressElement.setAttributeNode(latitude);

        Attr longitude = doc.createAttribute("longitude");
        longitude.setValue(String.valueOf(address.getLongitude()));
        addressElement.setAttributeNode(longitude);

        Attr description = doc.createAttribute("description");
        description.setValue(String.valueOf(address.getDescription()));
        addressElement.setAttributeNode(description);

        // write the content into xml file
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(new File(pathname));
        transformer.transform(source, result);
    }


}
