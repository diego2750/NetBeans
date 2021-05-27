package conexionmvc.pkg2;
import controlador.ControladorFRM;
import modelo.PaisDAO;
import modelo.PaisVO;
import vista.Formulario;
 
public class ConexionMVC2 {

    
    public static void main(String[] args) {
        Formulario frm = new Formulario();
        
        PaisVO pvo = new PaisVO();
        PaisDAO pdao = new PaisDAO();
        
        ControladorFRM cont = new ControladorFRM(frm, pdao, pvo);
        
        frm.setVisible(true);
        frm.setLocationRelativeTo(null);
    }
    
}
