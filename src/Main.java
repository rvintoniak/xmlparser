import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;

/**
 * Created by roman on 21.10.14.
 */
public class Main {
    public static void main(String[] args) throws Exception {

        Address address = new Address(15.1, 12.2, "IF");
        Address address2 = new Address(15.1, 18.2, null);
        Parser parser = new Parser();

        parser.createFile(address);
        parser.updateFile(address);
        parser.updateFile(address2);
        parser.updateFile(address);

        System.out.println(parser.getData());
    }
}
