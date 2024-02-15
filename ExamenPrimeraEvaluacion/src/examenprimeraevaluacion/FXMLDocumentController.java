/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML2.java to edit this template
 */
package examenprimeraevaluacion;

import java.io.*;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;

/**
 *
 * @author Arian
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    private Label label;
    @FXML
    private Button buttonConfiguracion;
    @FXML
    private TextField fieldTitulo;
    @FXML
    private TextField fieldDescripcion;
    @FXML
    private TextField fieldFecha;
    @FXML
    private Button buttonAnotar;
    @FXML
    private TextField fieldHecha;
    @FXML
    private TextArea areaAnotaciones;
    @FXML
    private Button buttonHecha;
    @FXML
    private TextArea areaXML;

    CRUDSQL crudSQL;

    private ArrayList<Anotacion> listaAnotaciones;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        // Crear la clase y métodos necesarios
        // Al abrir la aplicación se tiene que:
        // - Leer de datosConf.txt toda la información necesaria para crear la conexión de MySQL


        //Recogemos los datos del archivo de configuracion para crear el CRUD
        File conf = new File("datosConf.txt");

        FileReader FR = null;
        try {
            FR = new FileReader(conf);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        BufferedReader lector = new BufferedReader(FR);

        String servidor = null;
        String nombre = null;
        String pass = null;
        String base = null;
        try {
            servidor = lector.readLine();
            nombre = lector.readLine();
            pass = lector.readLine();
            base = lector.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }



        try {
            crudSQL = new CRUDSQL(servidor,nombre,pass,base);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        rellenarTextArea();


        //Ahora rellenamos el areaXML

        rellenarAreaXML();

        // - Todas las anotaciones disponibles que haya en MySQL en TextArea con nombre areaAnotaciones;
        // - Se tiene que cargar el archivo festivos.xml y mostrarlos en el TextArea con nombre areaXML;
        //   - root: festivos
        //   - nodo: festivo
        //   - elemento: fecha
        
    }    

    @FXML
    private void handleButtonActionConfiguracion(ActionEvent event) throws IOException {
        
        //Instrucciones en el Controler de Configuación
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("Configuracion.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void handleButtonActionAnotar(ActionEvent event) throws SQLException {
        // Crear la clase y métodos necesarios
        // Se tiene que crear una anotación nueva y mostrar en el TextArea con nombre areaAnotaciones;


        //Si els camps estan buits alerta
        if(fieldTitulo.getText().isEmpty()||fieldDescripcion.getText().isEmpty()||fieldFecha.getText().isEmpty()){

            Alert alerta = new Alert(Alert.AlertType.ERROR, "Debes rellenar todos los campos.");
            alerta.show();
            return;

        }

        //Insertem en la base de datos
        crudSQL.create(new Anotacion(fieldTitulo.getText(), fieldDescripcion.getText(), fieldFecha.getText()));

        Alert alerta = new Alert(Alert.AlertType.INFORMATION, "Anotación añadida correctamente.");
        alerta.show();

        //I tornem a rellenar el textArea
        rellenarTextArea();
    }

    @FXML
    private void handleButtonActionHecha(ActionEvent event) throws SQLException {
        // Crear la clase y métodos necesarios
        // Se tiene que eliminar la anotación y quitar del TextArea con nombre areaAnotaciones;


        //Si el camp ID esta buit
        if(fieldHecha.getText().isEmpty()){

            Alert alerta = new Alert(Alert.AlertType.ERROR, "Especifica qué tarea has hecho.");
            alerta.show();
            return;

        }

        //Eliminamos el registro con ese ID y volvemos a mostrar todas las anotaciones.
        crudSQL.delete(Integer.valueOf(fieldHecha.getText()));
        rellenarTextArea();
        Alert alerta = new Alert(Alert.AlertType.INFORMATION, "Anotación eliminada correctamente.");
        alerta.show();

    }

    public void rellenarTextArea(){

        areaAnotaciones.setText("");

        //Rellenamos la lista de anotaciones
        try {
            listaAnotaciones = crudSQL.devolverTodasAnotaciones();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        //Rellenamos el textArea
        for( Anotacion anotacion: listaAnotaciones){

            areaAnotaciones.setText(areaAnotaciones.getText()+"ID: "+anotacion.getId()+" | Titulo: "+anotacion.getTitulo()+" | Descripción: "+anotacion.getDescripcion()+" | Fecha: "+anotacion.getFecha()+"\n");

        }

    }

    //En este metodo rellenem el areaXML en la llista que obtenim del crudXML
    public void rellenarAreaXML(){

        File archivoXML = new File("festivos.xml");
        CRUDXML crudXML = null;
        try {
            crudXML = new CRUDXML(archivoXML);
        } catch (ParserConfigurationException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (SAXException e) {
            throw new RuntimeException(e);
        }

        ArrayList<String> listaFestivos = crudXML.readAll();

        areaXML.setText("");


        for( String festivo : listaFestivos){

            areaXML.setText(areaXML.getText()+festivo);

        }


    }

    //Arian Gisbert

}
