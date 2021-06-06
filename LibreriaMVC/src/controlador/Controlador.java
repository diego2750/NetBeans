package controlador;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import modelo.LibreriaDAO;
import modelo.LibreriaVO;
import vista.Formulario;
import vista.Join;
public class Controlador implements ActionListener, WindowListener{
    
    Formulario vista = new Formulario();
    Join join = new Join();
    LibreriaDAO ldao = new LibreriaDAO();
    LibreriaVO lvo = new LibreriaVO();

    public Controlador(Formulario vista, LibreriaDAO ldao, LibreriaVO lvo, Join join){
        this.vista = vista;
        this.join = join;
        this.ldao = ldao;
        this.lvo = lvo;
        
        vista.addWindowListener(this);
        this.vista.btnRegistrar.addActionListener(this);
        this.vista.btnCancelar.addActionListener(this);
        this.vista.btnActualizar.addActionListener(this);
        this.vista.btnEliminar.addActionListener(this);
        this.vista.btnTruncar.addActionListener(this);
        this.vista.btnJOIN.addActionListener(this);
    }
    
    /*LIMPIAR*/
    private void limpiar(){
        vista.txtAutor.setText("");
        vista.txtSerieAutor.setText("");
        vista.txtLibro.setText("");
        vista.txtGenero.setText("");
        vista.txtSerieLibro.setText("");
        vista.txtPrecio.setText("");
        vista.txtCopias.setText("");
    }
    
    private static boolean isNumeric(String cadena){
        try {
                Integer.parseInt(cadena);
                return true;
        } catch (NumberFormatException nfe){
                return false;
        }
    }
    
    private static boolean isLong(String cadena){
            try {
                    Long.parseLong(cadena);
                    return true;
            } catch (NumberFormatException nfe){
                    return false;
            }
        }
    
    /*REGISTRAR*/
    private void inesertarRegistro(){
        if(Long.parseLong(vista.txtSerieAutor.getText()) != Long.parseLong(vista.txtSerieLibro.getText()) || 
           isLong(vista.txtSerieAutor.getText()) == false || isLong(vista.txtSerieLibro.getText()) == false){
            vista.jOptionPane.showMessageDialog(null, "El no. de autor y el no. de serie de libro\n"+"no coinciden");
            vista.txtSerieLibro.setText("");
        }
        else if(isNumeric(vista.txtCopias.getText()) == false || isNumeric(vista.txtPrecio.getText()) == false){
            vista.jOptionPane.showMessageDialog(null, "El valor no es numerico");
        }
        else{
            lvo.setAutor(vista.txtAutor.getText());
            lvo.setNum_serie(Long.parseLong(vista.txtSerieAutor.getText()));
            lvo.setLibro(vista.txtLibro.getText());
            lvo.setGenero_libro(vista.txtGenero.getText());
            lvo.setNum_seriefk(Long.parseLong(vista.txtSerieLibro.getText()));
            lvo.setPrecio(Integer.parseInt(vista.txtPrecio.getText()));
            lvo.setCopias(Integer.parseInt(vista.txtCopias.getText()));
            ldao.insertar(lvo);
            
             limpiar();
             mostrar();
        }
    }
    
    /*AGREGAR CHECKBOX*/
    private void addCheckbox(int colum, JTable table){
        TableColumn tc = table.getColumnModel().getColumn(colum);
        tc.setCellEditor(table.getDefaultEditor(Boolean.class));
        tc.setCellRenderer(table.getDefaultRenderer(Boolean.class));
    }
    
    private boolean IsSelected(int row, int column, JTable table){    
        return table.getValueAt(row, column) != null;                       
    }
    
    /*MOSTRAR TABLA*/
    private void mostrar(){
        DefaultTableModel m = new DefaultTableModel();
        m.setColumnCount(0);
        m.addColumn("ID");
        m.addColumn("Libro");
        m.addColumn("Genero");
        m.addColumn("No.Serie");
        m.addColumn("Precio");
        m.addColumn("Copias");
        
        vista.tblLibros.setModel(m);
        
        for(LibreriaVO lvo: this.ldao.consultarTablaLibros()){
            m.addRow(new Object[]{lvo.getId_libro(), lvo.getLibro(), lvo.getGenero_libro(), lvo.getNum_seriefk(), lvo.getPrecio(), lvo.getCopias()});
        }
        
        DefaultTableModel m2 = new DefaultTableModel();
        m2.setColumnCount(0);
        m2.addColumn("ID");
        m2.addColumn("Autor");
        m2.addColumn("No.Serie");
        m2.addColumn("");
        
        vista.tblAutores.setModel(m2);
        
        for(LibreriaVO lvo: this.ldao.consultarTablaAutores()){
            m2.addRow(new Object[]{lvo.getId_autor(), lvo.getAutor(), lvo.getNum_serie()});
            addCheckbox(3, vista.tblAutores);
        }
        
        DefaultTableModel m3 = new DefaultTableModel();
        m3.setColumnCount(0);
        m3.addColumn("ID");
        m3.addColumn("Libro");
        m3.addColumn("Autor");
        m3.addColumn("Genero");
        m3.addColumn("Precio");
        m3.addColumn("Copias");
        m3.addColumn("No.Serie");
        
        join.tblJOIN.setModel(m3);
        
       for(LibreriaVO lvo: this.ldao.innerjoin()){
            m3.addRow(new Object[]{lvo.getId_autor(), lvo.getLibro(), lvo.getAutor(), lvo.getGenero_libro(), lvo.getPrecio(), lvo.getCopias(), lvo.getNum_seriefk()});
       }
        
    }
    
    /*ACTUALIZAR O MODIFICAR*/
    private void actualizar(){
        for (int i = 0; i < vista.tblAutores.getRowCount(); i++){
                lvo.setId_autor(Integer.parseInt(vista.tblAutores.getValueAt(i, 0).toString()));
                lvo.setAutor(vista.tblAutores.getValueAt(i, 1).toString());
                lvo.setNum_serie(Long.parseLong(vista.tblAutores.getValueAt(i, 2).toString()));
            ldao.actualizar(lvo);
        }
        
        for (int i = 0; i < vista.tblLibros.getRowCount(); i++){
                lvo.setId_libro(Integer.parseInt(vista.tblLibros.getValueAt(i, 0).toString()));
                lvo.setLibro(vista.tblLibros.getValueAt(i, 1).toString());
                lvo.setGenero_libro(vista.tblLibros.getValueAt(i, 2).toString());
                lvo.setNum_seriefk(Long.parseLong(vista.tblLibros.getValueAt(i, 3).toString()));
                lvo.setPrecio(Integer.parseInt(vista.tblLibros.getValueAt(i, 4).toString()));
                lvo.setCopias(Integer.parseInt(vista.tblLibros.getValueAt(i, 5).toString()));
            ldao.actualizar(lvo);
        }
        mostrar();
    }
    
    private void eliminar(){
//        for (int i = 0; i < vista.tblLibros.getRowCount(); i++){
//            if  (IsSelected(i, 6, vista.tblLibros)){
//                lvo.setId_libro(Integer.parseInt(vista.tblLibros.getValueAt(i, 0).toString()));
//            }
//            ldao.eliminarRegistro(lvo);
//        }
        
        for (int i = 0; i < vista.tblAutores.getRowCount(); i++){
            if  (IsSelected(i, 3, vista.tblAutores)){
                lvo.setId_autor(Integer.parseInt(vista.tblAutores.getValueAt(i, 0).toString()));
            }
            ldao.eliminarRegistro(lvo);
        }
        
        mostrar();
    }
    
    /*TRUNCAR TABLA*/
    private void truncartabla(){
        ldao.truncar();
        mostrar();
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == vista.btnRegistrar){
            this.inesertarRegistro();
        }
        
        if(e.getSource() == vista.btnCancelar){
            vista.dispose();
        }
        
        if(e.getSource() == vista.btnActualizar){
            this.actualizar();
        }
        
        if(e.getSource() == vista.btnEliminar){
            this.eliminar();
        }
        
        if(e.getSource() == vista.btnTruncar){
            this.truncartabla();
        }
        
        if(e.getSource() == vista.btnJOIN){
            join.setVisible(true);
            join.setLocationRelativeTo(null);
        }
    }

    @Override
    public void windowOpened(WindowEvent e) {
        this.mostrar();
        
    }

    @Override
    public void windowClosing(WindowEvent e) {
        
    }

    @Override
    public void windowClosed(WindowEvent e) {
        
    }

    @Override
    public void windowIconified(WindowEvent e) {
        
    }

    @Override
    public void windowDeiconified(WindowEvent e) {
        
    }

    @Override
    public void windowActivated(WindowEvent e) {
       
    }

    @Override
    public void windowDeactivated(WindowEvent e) {
        
    }
    
}
