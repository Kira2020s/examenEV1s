package examenprimeraevaluacion;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class CRUDXML {

    Document documento;

    public CRUDXML(File archivoXML) throws ParserConfigurationException, IOException, SAXException {

        //Agarrem el document XML
        DocumentBuilderFactory DBFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder DBbuilder = DBFactory.newDocumentBuilder();
        documento = DBbuilder.parse(archivoXML);

    }


    //LLegim tot el document XML i tornem les feches.
    public ArrayList<String> readAll(){

        //Creem la llista de nodos.
        NodeList listaNodos = documento.getElementsByTagName("festivo");

        ArrayList<String> listaFechas = new ArrayList<>();

        //I la recorreguem añadint a la llista de feches
        for(int i =0; i<listaNodos.getLength();i++){

            Element elemento = (Element) listaNodos.item(i);

            listaFechas.add(elemento.getTextContent());


        }

        return listaFechas;

    }


}
