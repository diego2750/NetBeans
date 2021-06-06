package modelo;

import java.sql.ResultSet;
import java.util.ArrayList;


public class LibreriaDAO implements ConsultasDAO {

    /*REGISTRAR*/
    @Override
    public void insertar(LibreriaVO lbr) {
        Conector c = new Conector();
        c.conectar();
        
        try{
            String consulta = "INSERT INTO autor (nombre, num_serie) VALUES ('"+ lbr.getAutor() +"', '"+ lbr.getNum_serie() +"');";
            c.consultas(consulta);
        }catch(Exception e){
        }
        
        try{
            String consulta = "INSERT INTO libros (libro, genero_libro, copias, precio, num_seriefk)" +
                    "VALUES ('"+lbr.getLibro()+"', '"+lbr.getGenero_libro()+"', '"+lbr.getCopias()+"', '"+lbr.getPrecio()+ "', '"+lbr.getNum_seriefk()+"');";
            c.consultas(consulta);
        }catch(Exception e){}
        
        c.desconectar();
    }

    /*ACTUALIZAR REGISTROS*/
    @Override
    public void actualizar(LibreriaVO lbr) {
        Conector c = new Conector();
        c.conectar();
        
        try{
            String sql = "UPDATE autor SET nombre = '"+ lbr.getAutor() +"', num_serie ='"+lbr.getNum_serie()+"'" + 
                    "WHERE id_autor = '"+lbr.getId_autor()+"';";
            c.consultas(sql);
        }
        catch(Exception e){}
        
        try{
            String sql = "UPDATE libros SET libro ='"+lbr.getLibro()+"', genero_libro ='"+lbr.getGenero_libro()+"', copias ='"+lbr.getCopias()+"'," +
                    "precio ='"+lbr.getPrecio()+"', num_seriefk ='"+lbr.getNum_seriefk()+"' WHERE id_libros ='"+lbr.getId_libro()+"';";
            c.consultas(sql);
        }
        catch(Exception e){}
        
        c.desconectar();
    }

    /*ELIMINAR REGISTROS*/
    @Override
    public void eliminarRegistro(LibreriaVO lbr) {
        Conector c = new Conector();
        c.conectar();
        
        try{
            String sql = "DELETE FROM autor WHERE id_autor = '"+ lbr.getId_autor() +"';";
            c.consultas(sql);
        }catch(Exception e){
        }
        
        try{
            String sql = "DELETE FROM libros WHERE id_libros ='"+lbr.getId_autor()+"';";
            c.consultas(sql);
        }catch(Exception e){}
        
        c.desconectar();
    }
    
    /*TRUNCAR TABLA*/
    @Override
    public void truncar(){
        Conector c = new Conector();
        c.conectar();
        try{
            String sql = "TRUNCATE TABLE libros;";
            c.consultas(sql);
        }catch(Exception e){}
        
        try{
            String sql = "TRUNCATE TABLE autor;";
            c.consultas(sql);
        }catch(Exception e){}
    }

    /*TABLA LIBROS*/
    @Override
    public ArrayList<LibreriaVO> consultarTablaLibros() {
        Conector c = new Conector();
        
         ArrayList<LibreriaVO> libro = new ArrayList<>();
         try{
             c.conectar();
             String consulta = "SELECT * FROM libros;";
             ResultSet rs = c.consulta_datos(consulta);
             while(rs.next()){
                 LibreriaVO lbr = new LibreriaVO();
                 lbr.setId_libro(rs.getInt(1));
                 lbr.setLibro(rs.getString(2));
                 lbr.setGenero_libro(rs.getString(3));
                 lbr.setCopias(rs.getInt(4));
                 lbr.setPrecio(rs.getInt(5));
                 lbr.setNum_seriefk(rs.getInt(6));
                 libro.add(lbr);
             }
             
         }catch(Exception e){
         }

         return libro;
    }
    
    /*TABLA AUTORES*/
    @Override
    public ArrayList<LibreriaVO> consultarTablaAutores(){
         Conector c = new Conector();
         ArrayList<LibreriaVO> autor = new ArrayList<>();
         try{
             c.conectar();
             String consulta = "SELECT * FROM autor;";
             ResultSet rs = c.consulta_datos(consulta);
             while(rs.next()){
                 LibreriaVO lbr = new LibreriaVO();
                 lbr.setId_autor(rs.getInt(1));
                 lbr.setAutor(rs.getString(2));
                 lbr.setNum_serie(rs.getInt(3));
                 autor.add(lbr);
             }
             
         } catch(Exception e){
             System.err.println();
         }
         
         return autor;
    }
    
    @Override
    public ArrayList<LibreriaVO> innerjoin(){
        Conector c = new Conector();
         ArrayList<LibreriaVO> inner = new ArrayList<>();
         try{
             
             c.conectar();
             String consulta = "SELECT atr.id_autor, lb.libro, atr.nombre, lb.genero_libro, lb.precio, lb.copias, atr.num_serie FROM\n" +
                                "libros lb \n" +
                                "INNER JOIN \n"+ 
                                "autor atr \n"+ 
                                "ON lb.num_seriefk = atr.num_serie;";
             ResultSet rs = c.consulta_datos(consulta);
             while(rs.next()){
                 LibreriaVO lbr = new LibreriaVO();
                 lbr.setId_autor(rs.getInt(1));
                 lbr.setLibro(rs.getString(2));
                 lbr.setAutor(rs.getString(3));
                 lbr.setGenero_libro(rs.getString(4));
                 lbr.setPrecio(rs.getInt(5));
                 lbr.setCopias(rs.getInt(6));
                 lbr.setNum_seriefk(rs.getLong(7));
                 inner.add(lbr);
             }
             
         } catch(Exception e){
             System.err.println();
         }
        return inner;
    }
    
}
