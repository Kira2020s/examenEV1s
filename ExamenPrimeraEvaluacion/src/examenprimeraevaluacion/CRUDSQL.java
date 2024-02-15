package examenprimeraevaluacion;

import com.mysql.cj.jdbc.Driver;

import java.sql.*;
import java.util.ArrayList;

public class CRUDSQL {

    private Connection conexion;

    //Creamos la conexion SQL con los datos pasados
    public CRUDSQL(String servidor, String nombre, String pass, String base) throws SQLException {


        conexion = DriverManager.getConnection("jdbc:mysql://"+servidor+":3306/"+base, nombre, pass);


    }


    //Devolvemos un arrayList con todas las anotaciones
    public ArrayList<Anotacion> devolverTodasAnotaciones() throws SQLException {

        PreparedStatement consulta = conexion.prepareStatement("SELECT * FROM anotaciones");

        ResultSet rs = consulta.executeQuery();

        ArrayList<Anotacion> listaAnotaciones = new ArrayList<>();

        while(rs.next()){


            listaAnotaciones.add(new Anotacion(rs.getInt(1), rs.getString(2), rs.getString(3),rs.getString(4)));

        }

        return listaAnotaciones;

    }

    //Metodo per a añadir a la base de datos una anotació, el objecte es sense ID ja que es auto incremental.
    public void create(Anotacion anotacion) throws SQLException {

        PreparedStatement declaracio = conexion.prepareStatement("INSERT INTO anotaciones(titulo,descripcion,fecha) VALUES(?,?,?)");

        declaracio.setString(1, anotacion.getTitulo());
        declaracio.setString(2, anotacion.getDescripcion());
        declaracio.setString(3, anotacion.getFecha());

        declaracio.execute();

    }

    //Borrem el registro en ixe id de la base de datos
    public void delete(int id) throws SQLException {

        PreparedStatement declaracio = conexion.prepareStatement("DELETE FROM anotaciones WHERE id = "+id);
        declaracio.execute();

    }

}
