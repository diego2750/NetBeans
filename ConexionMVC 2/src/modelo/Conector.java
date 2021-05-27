package modelo;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Conector {
    /*DECLARACION DE VARIABLES*/
    private String driver = "com.mysql.jdbc.Driver";
    private String servidor = "127.0.0.1";
    private String usuario = "root";
    private String password = "";
    private String bd = "db_java";
    private String cadena;
    
    /*OBJETO DE CONEXION*/
    Connection con;
    
    /*DECLARAR EL OBEJETO QUE REALIZA CONSULTAS*/
    Statement st;
    
    /*METODOS PARA LA CONEXION*/
    public void conectar(){
        this.cadena = "jdbc:mysql://" + this.servidor + "/" + this.bd;
        try{
            Class.forName(this.driver).newInstance();
            this.con = DriverManager.getConnection(this.cadena, this.usuario, this.password);
        }catch(Exception e){
                System.err.println(e.getMessage());
        }
    }
    
    /*METODO PATA DESCONECTAR*/
    public void desconectar(){
        try{
            this.con.close();
        }catch(Exception e){
            System.err.println(e.getMessage());
        }
    }
    
    /*METODO PARA REALIZAR DIVERSAS CONSULTAS*/
    public int consultas(String consulta){
        int resultado;
        try{
            this.conectar();
            this.st = this.con.createStatement();
            resultado = this.st.executeUpdate(consulta);
        }catch(Exception e){
            System.err.println(e.getMessage());
            return 0;
        } 
        return resultado;
    }
    
    /*METODO PARA OBTENER REGISTROS*/
    public ResultSet consulta_datos(String consulta){
        try{
            this.conectar();
            ResultSet resultado = null;
            this.st = this.con.createStatement();
            resultado = st.executeQuery(consulta);
            return resultado;
        }catch(Exception e){
        }//finally{
//            this.desconectar();
//        }
        return null;
    }
}
