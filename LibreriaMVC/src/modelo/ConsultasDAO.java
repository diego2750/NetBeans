package modelo;

import java.util.ArrayList;

public interface ConsultasDAO {
    public void insertar(LibreriaVO lbr);
    public void actualizar(LibreriaVO lbr);
    public void eliminarRegistro(LibreriaVO lbr);
    public void truncar();
    public ArrayList<LibreriaVO> consultarTablaLibros();
    public ArrayList<LibreriaVO> consultarTablaAutores();
    public ArrayList<LibreriaVO> innerjoin();
}
