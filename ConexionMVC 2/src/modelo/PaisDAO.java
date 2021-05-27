package modelo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author diego
 */
public class PaisDAO implements ConsultasDAO{

    @Override
    public void insertar(PaisVO p) {
        Conector c = new Conector();
        
        try{
            c.conectar();
            String consulta = "INSERT INTO tbl_paises (pais, capital, poblacion)" + 
                    "VALUES ('" + p.getPais() + "', '"+ p.getCapital() + "', '" + p.getPoblacion() + "');";
            c.consultas(consulta);
        }catch(Exception e){
        }
        
        c.desconectar();
    }

    @Override
    public void actualizar(PaisVO p) {
        Conector c = new Conector();
        c.conectar();
        
        try{
            String sql = "UPDATE tbl_paises SET pais = '"+ p.getPais() +"', capital ='"+ p.getCapital() +"'," + 
                    "poblacion = '" + p.getPoblacion() +"' WHERE id_pais = '"+ p.getId_pais() +"';";
            c.consultas(sql);
        }
        catch(Exception e){}
    }
    
    @Override
    public void eliminarRegistro(PaisVO p){
        Conector c = new Conector();
        c.conectar();
        
        try{
            String sql = "DELETE FROM tbl_paises WHERE id_pais = '"+ p.getId_pais() +"';";
            c.consultas(sql);
        }catch(Exception e){
        }
    }
    
    @Override
    public void truncar(){
        Conector c = new Conector();
        c.conectar();
        try{
            String sql = "TRUNCATE TABLE tbl_paises;";
            c.consultas(sql);
        }catch(Exception e){}
    }

    @Override
    public ArrayList<PaisVO> consultarTabla() {
         Conector c = new Conector();
         ArrayList<PaisVO> info = new ArrayList<>();
         try{
             c.conectar();
             String consulta = "SELECT * FROM tbl_paises;";
             ResultSet rs = c.consulta_datos(consulta);
             while(rs.next()){
                 PaisVO pvo = new PaisVO();
                 pvo.setId_pais(rs.getInt(1));
                 pvo.setPais(rs.getString(2));
                 pvo.setCapital(rs.getString(3));
                 pvo.setPoblacion(rs.getLong(4));
                 info.add(pvo);
             }
         } catch(Exception e){
             System.err.println();
         }
         
         return info;
    }

}
