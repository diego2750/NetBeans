package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.table.DefaultTableModel;
import modelo.PaisDAO;
import modelo.PaisVO;
import vista.Formulario;
import javax.swing.*;
import javax.swing.table.TableColumn;

public class ControladorFRM implements ActionListener, WindowListener{
    
    Formulario vista = new Formulario();
    PaisDAO pdao = new PaisDAO();
    PaisVO pvo = new PaisVO();
    
    public ControladorFRM(Formulario vista, PaisDAO pdao, PaisVO pvo){
        this.vista = vista;
        this.pdao = pdao;
        this.pvo = pvo;
        
        vista.addWindowListener(this);
        this.vista.btnRegistrar.addActionListener(this);
        this.vista.btnCancelar.addActionListener(this);
        this.vista.btnActualizar.addActionListener(this);
        this.vista.btnEliminar.addActionListener(this);
        this.vista.btnTruncar.addActionListener(this);
        this.vista.btnNotas.addActionListener(this);
    }
    
    /*LIMPIAR*/
    private void limpiar(){
        vista.txtCapital.setText("");
        vista.txtPais.setText("");
        vista.txtPoblacion.setText("");
    }
    
    /*REGISTRAR*/
    private void inesertarPais(){
        pvo.setPais(vista.txtPais.getText());
        pvo.setCapital(vista.txtCapital.getText());
        pvo.setPoblacion(Long.parseLong(vista.txtPoblacion.getText()));
        pdao.insertar(pvo);
        
        limpiar();
        mostrar();
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
        m.addColumn("Pais");
        m.addColumn("Capital");
        m.addColumn("Poblacion");
        m.addColumn("");
        
        vista.Tabla.setModel(m);
        
        for(PaisVO pvo: this.pdao.consultarTabla()){
            m.addRow(new Object[]{pvo.getId_pais(), pvo.getPais(), pvo.getCapital(), pvo.getPoblacion()});
            addCheckbox(4, vista.Tabla);
        }
    }
    
    /*ACTUALIZAR O MODIFICAR*/
    private void actualizar(){
        for (int i = 0; i < vista.Tabla.getRowCount(); i++){
                pvo.setId_pais(Integer.parseInt(vista.Tabla.getValueAt(i, 0).toString()));
                pvo.setPais(vista.Tabla.getValueAt(i, 1).toString());
                pvo.setCapital(vista.Tabla.getValueAt(i, 2).toString());
                pvo.setPoblacion(Long.parseLong(vista.Tabla.getValueAt(i, 3).toString()));
            pdao.actualizar(pvo);
        }
        
        mostrar();
    }
    
    /*ELIMINAR*/
    private void eliminar(){
        for (int i = 0; i < vista.Tabla.getRowCount(); i++){
            if  (IsSelected(i, 4, vista.Tabla)){
                pvo.setId_pais(Integer.parseInt(vista.Tabla.getValueAt(i, 0).toString()));
            }
            pdao.eliminarRegistro(pvo);
        }
        
        mostrar();
    }
    
    /*TRUNCAR TABLA*/
    private void truncartabla(){
        pdao.truncar();
        mostrar();
    }
    
    /*NOTAS*/
    private void notas(){
        vista.jOptionPane.showMessageDialog(null, "Para eliminar los registros seleccione una o mas checkbox's " + 
                "dependiendo cuantos registros quiere borrar.\n\n" + 
                "Para modificar un registro o mas seleccione los datos en la tabla que se ve abajo ingresando\n" +
                "los que desee y asegurese de presionar enter para dejar el nuevo dato y luego presione actualizar");
        
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == vista.btnRegistrar){
            this.inesertarPais();
        }
        
        if(e.getSource() == vista.btnCancelar){
            vista.dispose();
        }
        
        if(e.getSource() == vista.btnActualizar){
            this.actualizar();
        }
        
        if(e.getSource() == vista.btnEliminar ){
            this.eliminar();
        }
        
        if(e.getSource() == vista.btnTruncar ){
            this.truncartabla();
        }
        
        if(e.getSource() == vista.btnNotas){
            this.notas();
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
