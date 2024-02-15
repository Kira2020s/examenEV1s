/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package examensql;

import java.sql.Array;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author denos
 */
public class GestorEmpleados {
    
    // listarEmpleados: Muestra en la consola todos los empleados de la tabla.
    // buscarEmpleadoPorNombre: Recibe el nombre de un empleado como parámetro y muestra en la consola la información de ese empleado.
    // eliminarEmpleado: Recibe el ID de un empleado y elimina ese empleado de la base de datos.

    private ConexionSQL conexion;

    public GestorEmpleados() throws SQLException {

        this.conexion = new ConexionSQL();

    }

    //Torna un string en la inforacio de tots els empleats de la base de datos, gastant el Read() de la clase ConexionSQL
    public String listarEmpleados() throws SQLException {

        ArrayList<Empleado> listaEmpleados = conexion.Read("SELECT * FROM Empleado");

        String stringEmpleados = "";

        for( Empleado e : listaEmpleados){

            stringEmpleados+= "ID: "+e.getId()+" | Nombre: "+ e.getNombre()+" | Puesto: "+e.getPuesto()+" | Salario: "+e.getSalario()+"€\n";

        }

        return stringEmpleados;

    }

    //Crida al crud per a fer una consulta en la base en el nom que li pasem
    public String buscarEmpleadoPorNombre(String nombre) throws SQLException {

        String consulta = "SELECT * FROM Empleado WHERE nombre LIKE \""+nombre+"\"";

        ArrayList<Empleado> listaEmpleados = conexion.Read(consulta);

        String stringEmpleados = "";

        for( Empleado e : listaEmpleados){

            stringEmpleados+= "ID: "+e.getId()+" | Nombre: "+ e.getNombre()+" | Puesto: "+e.getPuesto()+" | Salario: "+e.getSalario()+"€\n";

        }

        return stringEmpleados;

    }

    //Llama al metodo del crud para eliminar empleado
    public void eliminarEmpleado(int id){

        conexion.Delete(id);


    }


}
