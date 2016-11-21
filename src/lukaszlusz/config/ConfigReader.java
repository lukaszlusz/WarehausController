package lukaszlusz.config;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ConfigReader {
    String filepath = "dbconfig.xml";
    private boolean dbInfoLoaded = false;
    private DbInfo dbInfo = new DbInfo();

    private void loadDbInfo() throws IOException, ParserConfigurationException, SAXException {
        File xmlFile = new File(filepath);
        if(xmlFile.exists() == false || xmlFile.isDirectory()) throw new FileNotFoundException();

        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = dbFactory.newDocumentBuilder();
        Document document = documentBuilder.parse(xmlFile);
        document.getDocumentElement().normalize();

        NodeList nodeList = document.getElementsByTagName("dbinfo");
        Node node = nodeList.item(0);
        if (node.getNodeType() == Node.ELEMENT_NODE) {
            Element element = (Element) node;
            element.getAttribute("id");
            dbInfo.address = element.getElementsByTagName("address").item(0).getTextContent();
            dbInfo.dbName = element.getElementsByTagName("dbname").item(0).getTextContent();
            dbInfo.port = element.getElementsByTagName("port").item(0).getTextContent();
            dbInfo.user = element.getElementsByTagName("user").item(0).getTextContent();
            dbInfo.password = element.getElementsByTagName("password").item(0).getTextContent();

            dbInfoLoaded = true;
        }
    }

    public DbInfo getDbInfo() {
        if (dbInfoLoaded == false) {
            try {
                loadDbInfo();
            } catch (IOException e) {
                System.err.print("Brak pliku konfiguracyjnego");
                //TODO: open input dialog and create configuration file

            } catch (ParserConfigurationException | SAXException e) {
                e.printStackTrace();
                //TODO: errorbox
                //TODO: open input dialog and create configuration file and try load one more time
                System.exit(-1);
            }
        }
        return dbInfo;
    }
}
