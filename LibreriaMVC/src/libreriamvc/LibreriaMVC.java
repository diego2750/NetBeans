package libreriamvc;
import controlador.Controlador;
import modelo.LibreriaDAO;
import modelo.LibreriaVO;
import vista.Formulario;
import vista.Join;

public class LibreriaMVC {
   
    public static void main(String[] args) {
        Formulario vista = new Formulario();
        Join join = new Join();
        
        LibreriaDAO ldao = new LibreriaDAO();
        LibreriaVO lvo = new LibreriaVO();
        
        Controlador con = new Controlador(vista, ldao, lvo, join);
        
        vista.setVisible(true);
        vista.setLocationRelativeTo(null);
    }
    
}
