/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package examensql;

import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author denos
 */
public class ConexionSQL {

    private Connection conexio;


    //Operaciones CRUD
    

    //Creem la conexio en la base de datos
    public ConexionSQL() throws SQLException {

        DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
        this.conexio = DriverManager.getConnection("jdbc:mysql://localhost:3306/EmpresaDB", "root", "Arian041229");

    }


    //Donat un objecte empleado, el insertem en la base de datos, el insertem sense ID perque es autoincremental.
    public void Create(Empleado empleado) throws SQLException {

        PreparedStatement declaracio = conexio.prepareStatement("INSERT INTO Empleado(nombre,puesto,salario) VALUES (?,?,?)");

        declaracio.setString(1,empleado.getNombre());
        declaracio.setString(2, empleado.getPuesto());
        declaracio.setDouble(3, empleado.getSalario());

        declaracio.execute();

    }

    //Mos torna un array list de empleados que llig en la base de datos segons la consulta que li pasem
    public ArrayList<Empleado>  Read(String stringConsulta) throws SQLException {

        PreparedStatement consulta = conexio.prepareStatement(stringConsulta);

        ResultSet rs = consulta.executeQuery();

        ArrayList<Empleado> listaEmpleados = new ArrayList<>();


        //Si el resultSet esta buit torna la llista buida
        if(rs.isBeforeFirst()==false){

            return listaEmpleados;

        }

        while(rs.next()){

            listaEmpleados.add(new Empleado(rs.getInt(1), rs.getString(2),rs.getString(3), rs.getDouble(4)));

        }

        return listaEmpleados;

    }

    //Dado un ID, actualiza sus datos con los nuevos datos
    public void Update(int id, Empleado nuevoEmpleado) throws SQLException {

        PreparedStatement declaracio = conexio.prepareStatement("UPDATE Empleado SET nombre=?,puesto=?,salario=? WHERE ID = "+id);

        declaracio.setString(1,nuevoEmpleado.getNombre());
        declaracio.setString(2, nuevoEmpleado.getPuesto());
        declaracio.setDouble(3, nuevoEmpleado.getSalario());

        declaracio.execute();

    }


    //Donat un ID, eliminem el empleat asociat a ixe ID en la base de datos.
    public void Delete(int id){

        PreparedStatement declaracio = null;

        try {
            declaracio = conexio.prepareStatement("DELETE FROM Empleado WHERE ID = "+id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            declaracio.execute();
        } catch (SQLException e) {
            System.out.println("ERROR EN EL BORRADO");
        }

    }

    //Tanquem la conexio
    public void Close() throws SQLException {

        conexio.close();

    }
    

}
