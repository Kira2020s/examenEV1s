/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML2.java to edit this template
 */
package examensql;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 *
 * @author denos
 */

/*

Al iniciar el programa se tiene que cargar en el textArea todo la información


Base de datos -> EmpresaDB
Tabla -> Empleados

id (int, clave primaria)
nombre (varchar)
puesto (varchar)
salario (double)



*/

//FUNCIONA
public class FXMLDocumentController implements Initializable {

    private GestorEmpleados GE;

    @FXML
    private Label label;
    @FXML
    private TextArea areaSalida;
    @FXML
    private TextField fieldId;
    @FXML
    private TextField fieldNombre;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        //Carrega tota la informacio de la tabla empleados en base de datos inicialment en el areaSalida;
        try {
            GE = new GestorEmpleados();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        mostrarEmpleados();

    }    

    @FXML
    private void handleButtonActionBorrar(ActionEvent event) {

        //Si el campo del ID esta vacio detiene el metodo y nos muestra una alerta
        if(fieldId.getText().isEmpty()){

            Alert alerta = new Alert(Alert.AlertType.ERROR, "EL campo ID no puede estar vacío.");
            alerta.show();
            return;

        }


        int id = Integer.valueOf(fieldId.getText());

        GE.eliminarEmpleado(id);

        mostrarEmpleados();

    }

    //Agarra el text en el fieldNombre i quidra al gestor de empleados per a fer una consulta en el nom del field.
    @FXML
    private void handleButtonActionFiltro(ActionEvent event) throws SQLException {

        //Si el campo del nombre esta vacio detiene el metodo y nos muestra una alerta
        if(fieldNombre.getText().isEmpty()){

            Alert alerta = new Alert(Alert.AlertType.ERROR, "EL campo nombre no puede estar vacío.");
            alerta.show();
            return;

        }

        String nombre = fieldNombre.getText();

        String stringListEmpleados = GE.buscarEmpleadoPorNombre(nombre);

        //Si el gestor mos torna "" vol dir que esta el result set esta buit.
        if(stringListEmpleados.equals("")){

            areaSalida.setText("No existe empleado con tal nombre.");

        }else{

        areaSalida.setText(GE.buscarEmpleadoPorNombre(nombre));
        }
    }

    @FXML
    private void handleButtonActionMostrarTodo(ActionEvent event) {

        mostrarEmpleados();

    }

    private void mostrarEmpleados(){

        try {
            areaSalida.setText(GE.listarEmpleados());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    
}
