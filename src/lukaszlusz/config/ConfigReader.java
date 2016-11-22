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

import lukaszlusz.GUI.ErrorBox;

public class ConfigReader {
    String filepath = "dbconfig.xml";
    private boolean dbInfoLoaded = false;
    private DbInfo dbInfo = new DbInfo();

    public DbInfo getDbInfo() {
        if (dbInfoLoaded == false) tryToLoadDbInfo();
        return dbInfo;
    }

    private void tryToLoadDbInfo() {
            try {
                loadDbInfo();
            } catch (IOException e) {
                System.err.print("Brak pliku konfiguracyjnego");
                //TODO: open input dialog and create configuration file

            } catch (ParserConfigurationException | SAXException e) {
                e.printStackTrace();
                new ErrorBox("Błąd podczas odczytu pliku konfiguracyjnego");
                //TODO: open input dialog and create configuration file and try load one more time
                System.exit(-1);
            }
    }

    private void loadDbInfo() throws IOException, ParserConfigurationException, SAXException {
        File xmlFile = new File(filepath);
        checkIfFileExist(xmlFile);
        Document document = getParsedDocument(xmlFile);
        readDbInfoFromDocument(document);
    }

    private void checkIfFileExist(File xmlFile) throws FileNotFoundException {
        if(xmlFile.exists() == false || xmlFile.isDirectory()) throw new FileNotFoundException();
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
