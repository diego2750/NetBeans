package modelo;

import java.util.ArrayList;


public interface ConsultasDAO {
    public void insertar(PaisVO p);
    public void actualizar(PaisVO p);
    public void eliminarRegistro(PaisVO p);
    public ArrayList<PaisVO> consultarTabla();
    public void truncar();
}
