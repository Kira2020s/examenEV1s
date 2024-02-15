/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML2.java to edit this template
 */
package leeryescribirxml;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author denos
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    private Label label;
    
    @FXML
    private void handleButtonAction(ActionEvent event) throws ParserConfigurationException, SAXException, IOException, TransformerConfigurationException, TransformerException {
        
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dbBuilder = dbFactory.newDocumentBuilder();
        Document document = dbBuilder.parse(new File("bicicletas.xml"));
        
        Element raiz = document.getDocumentElement();
        System.out.println("Contenido XML " + raiz.getNodeName() + ":");
        NodeList nodeList = document.getElementsByTagName("bicicleta");
        
        for (int i=0;i<nodeList.getLength();i++){
            Node node = nodeList.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE){
                Element eElement = (Element) node;
                System.out.println("Bicicleta " + eElement.getAttribute("id"));
                System.out.println("Marca " + eElement.getElementsByTagName("marca").item(0).getTextContent());
                System.out.println("Modelo " + eElement.getElementsByTagName("modelo").item(0).getTextContent());
                System.out.println("Año " + eElement.getElementsByTagName("anyo").item(0).getTextContent());
                System.out.println("Color " + eElement.getElementsByTagName("color").item(0).getTextContent());
                System.out.println("-------------------------");
            }
        }
        
        /*
        // Crear un documento XML vacío
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
        Document doc = docBuilder.newDocument();

        // Crear el elemento raíz
        Element rootElement = doc.createElement("bicicletas");
        doc.appendChild(rootElement);

        // Agregar elementos y datos al documento
        Element childElement = doc.createElement("bicicleta");
        childElement.setAttribute("id", "1");
        
        // Agregar elementos dentro de bicicleta -> marca
        Element marca = doc.createElement("marca");
        marca.appendChild(doc.createTextNode("Orbea"));        
        childElement.appendChild(marca);
        
        // Agregar elementos dentro de bicicleta -> modelo
        Element modelo = doc.createElement("modelo");
        modelo.appendChild(doc.createTextNode("Oiz"));        
        childElement.appendChild(modelo);
        
        // Agregar elementos dentro de bicicleta -> anyo
        Element anyo = doc.createElement("anyo");
        anyo.appendChild(doc.createTextNode("2024"));        
        childElement.appendChild(anyo);   
        
        // Agregar elementos dentro de bicicleta -> color
        Element color = doc.createElement("color");
        color.appendChild(doc.createTextNode("negra"));        
        childElement.appendChild(color);   
        
        // Agregamos bicicleta con ID=1 al root Bicicletas
        rootElement.appendChild(childElement);
        
        // Guardar el documento XML en un archivo
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");  // Opcional: dar formato al archivo

        // Especifica la ruta del archivo en el que deseas guardar el XML
        File xmlFile = new File("archivo.xml");

        // Guarda el documento XML en el archivo especificado
        Result output = new StreamResult(xmlFile);
        transformer.transform(new DOMSource(doc), output);

        System.out.println("Archivo XML generado correctamente.");
        */
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
