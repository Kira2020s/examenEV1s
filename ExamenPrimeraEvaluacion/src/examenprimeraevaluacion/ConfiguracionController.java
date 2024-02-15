/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package examenprimeraevaluacion;

import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Arian
 */
public class ConfiguracionController implements Initializable {

    @FXML
    private TextField fieldServer;
    @FXML
    private TextField fieldUser;
    @FXML
    private TextField fieldPass;
    @FXML
    private TextField fieldDatabase;

    private File archivoConfiguracion;


    //CLASE ACABA
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Crear la clase y métodos necesarios
        
        // Cargar el contenido del archivo TXT y rellenar los campos

        archivoConfiguracion = new File("datosConf.txt");

        //Crear el lector y leer todas las lineas
        FileReader FR = null;
        try {
            FR = new FileReader(archivoConfiguracion);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        BufferedReader lector = new BufferedReader(FR);

        //Según la linea en la que se encuentre rellenamos un campo o otro;


        try {
            fieldServer.setText(lector.readLine());
            fieldUser.setText(lector.readLine());
            fieldPass.setText(lector.readLine());
            fieldDatabase.setText(lector.readLine());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }    

    @FXML
    private void OnActionGuardar(ActionEvent event) throws IOException {
        // Crear la clase y métodos necesarios
        
        // Guardar todos los campos en el archivo TXT

        //Creamos el escritor y sobreescribimos los datos
        FileWriter escritor = new FileWriter(archivoConfiguracion);

        escritor.write(fieldServer.getText()+"\n"+fieldUser.getText()+"\n"+fieldPass.getText()+"\n"+fieldDatabase.getText());
        escritor.flush();



        // ** Muy importante **
        // Los datos de la conexión solo se deben cargar al iniciar el programa
        // Aquí SOLO se guardar en el TXT
        // ** Muy importante **
        
    }
    
}
