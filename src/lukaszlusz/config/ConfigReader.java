package lukaszlusz.config;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;

import lukaszlusz.GUI.DbInfoInput;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.IOException;

import lukaszlusz.GUI.ErrorBox;

public class ConfigReader {
    private static ConfigReader INSTANCE = null;
    String filepath = "dbconfig.xml";
    private boolean dbInfoLoaded = false;
    private DbInfo dbInfo = new DbInfo();

    private ConfigReader() {}

    public static ConfigReader getInstance() {
        if (INSTANCE == null) INSTANCE = new ConfigReader();
        return INSTANCE;
    }

    public DbInfo getDbInfo() {
        if (!dbInfoLoaded) {
            File xmlFile = new File(filepath);
            if(!fileExist(xmlFile)) new DbInfoInput();
        }
            tryToLoadDbInfo();
        return dbInfo;
    }

    private void tryToLoadDbInfo() {
            try {
                loadDbInfo();
            }  catch (ParserConfigurationException | SAXException | IOException e) {
                e.printStackTrace();
                new DbInfoInput();
                try {
                    loadDbInfo();
                } catch (Exception e1) {
                    e1.printStackTrace();
                    new ErrorBox("Błąd podczas odczytu pliku konfiguracyjnego");
                    System.exit(-1);
                }

            }
    }

    private void loadDbInfo() throws IOException, ParserConfigurationException, SAXException {
        File xmlFile = new File(filepath);
        Document document = getParsedDocument(xmlFile);
        readDbInfoFromDocument(document);
    }

    private boolean fileExist(File xmlFile) {
        if(!xmlFile.exists() || xmlFile.isDirectory()) return false;
        return true;
    }

    private Document getParsedDocument(File xmlFile) throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = dbFactory.newDocumentBuilder();
        Document document = documentBuilder.parse(xmlFile);
        document.getDocumentElement().normalize();
        return document;
    }

    private void readDbInfoFromDocument(Document document) {
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

}
