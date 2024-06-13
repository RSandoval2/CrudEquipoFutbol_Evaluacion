package interfazgrafica;

import entidadesdenegocio.Equipo;

import logicadenegocio.equipoBL;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;

public class CrudEquipo extends JFrame {
    private JPanel jpPrincipal;
    private JTextField txtId;
    private JTextField txtNombre;
    private JTextField txtApellido;
    private JTextField txtNumero;
    private JTextField txtPosicion;
    private JButton btnNuevo;
    private JButton btnGuardar;
    private JButton btnModificar;
    private JButton btnEliminar;
    private JButton btnCancelar;
    private JButton btnSalir;
    private JRadioButton rdbId;
    private JRadioButton rdbNombre;
    private JRadioButton rdbNumero;
    private JButton btnBuscar;
    private JTextField txtCriterio;
    private JTable jtEquipo;
    private ButtonGroup criterioBusqueda;

    //Instancias propias
    ArrayList<Equipo> listaJugadores;
    Equipo jugador;
    equipoBL equipoBL = new equipoBL();

    public static void main(String[] args) throws SQLException {
        new CrudEquipo();
    }

    public CrudEquipo() throws SQLException {
        inicializar();
        actualizarForm();

        btnSalir.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                System.exit(0);
            }
        });
        btnCancelar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                try{
                    actualizarForm();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        btnNuevo.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                txtNombre.setEnabled(true);
                txtApellido.setEnabled(true);
                txtNumero.setEnabled(true);
                txtPosicion.setEnabled(true);
                txtNombre.grabFocus();
                btnGuardar.setEnabled(true);
                btnNuevo.setEnabled(false);
                btnCancelar.setEnabled(true);
            }
        });
        btnGuardar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                jugador = new Equipo();
                jugador.setNombrejugador(txtNombre.getText());
                jugador.setApellidojugador(txtApellido.getText());
                jugador.setNumerocamiseta(Integer.parseInt(txtNumero.getText()));
                jugador.setPosicion(txtPosicion.getText());
                try{
                  equipoBL.guardar(jugador);
                    JOptionPane.showMessageDialog(null, "Se guardó con éxito");
                    actualizarForm();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        btnModificar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                jugador = new Equipo();
                jugador.setId(Integer.parseInt(txtId.getText()));
                jugador.setNombrejugador(txtNombre.getText());
                jugador.setApellidojugador(txtApellido.getText());
                jugador.setNumerocamiseta(Integer.parseInt(txtNumero.getText()));
                jugador.setPosicion(txtPosicion.getText());
                try{
                    equipoBL.modificar(jugador);
                    JOptionPane.showMessageDialog(null, "Se modificó con éxito");
                    actualizarForm();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        jtEquipo.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                int fila = jtEquipo.getSelectedRow();
                txtId.setText(jtEquipo.getValueAt(fila, 0).toString());
                txtNombre.setText(jtEquipo.getValueAt(fila, 1).toString());
                txtApellido.setText(jtEquipo.getValueAt(fila, 2).toString());
                txtNumero.setText(jtEquipo.getValueAt(fila, 3).toString());
                txtPosicion.setText(jtEquipo.getValueAt(fila,4).toString());

                txtNombre.setEnabled(true);
                txtApellido.setEnabled(true);
                txtNumero.setEnabled(true);
                txtPosicion.setEnabled(true);

                btnNuevo.setEnabled(false);
                btnModificar.setEnabled(true);
                btnEliminar.setEnabled(true);
                btnCancelar.setEnabled(true);
            }
        });
        btnEliminar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                jugador = new Equipo();
                jugador.setId(Integer.parseInt(txtId.getText()));
                try{
                    equipoBL.eliminar(jugador);
                    JOptionPane.showMessageDialog(null, "Se eliminó correctamente");
                    actualizarForm();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        btnBuscar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if(txtCriterio.getText().equals("") || (!rdbId.isSelected() &&
                        !rdbNombre.isSelected() && !rdbNumero.isSelected()) ){
                    JOptionPane.showMessageDialog(null,
                            "Seleccione un criterio de búsqueda o escriba el valor a buscar");
                }

                jugador = new Equipo();

                if(rdbId.isSelected()){
                    jugador.setId(Integer.parseInt(txtCriterio.getText()));
                    try{
                        llenarTabla(equipoBL.obtenerDatosFiltrados(jugador));
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                }

                if(rdbNombre.isSelected()){
                    jugador.setNombrejugador(txtCriterio.getText());
                    try{
                        llenarTabla(equipoBL.obtenerDatosFiltrados(jugador));
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                }

                if(rdbNumero.isSelected()){
                    jugador.setNumerocamiseta(Integer.parseInt(txtNumero.getText()));
                    try{
                        llenarTabla(equipoBL.obtenerDatosFiltrados(jugador));
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            }
        });
    }

    void inicializar(){
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(600, 700);
        setTitle("Control de Estudiantes");
        setLayout(null);
        setLocationRelativeTo(null);
        setResizable(false);

        criterioBusqueda = new ButtonGroup();
        criterioBusqueda.add(rdbId);
        criterioBusqueda.add(rdbNombre);
        criterioBusqueda.add(rdbNumero);

        setContentPane(jpPrincipal);
        setVisible(true);
    }

    void llenarTabla(ArrayList<Equipo> equipos){
        Object[] objects = new Object[5];
        listaJugadores = new ArrayList<>();
        String[] encabezado = {"ID", "NOMBRE", "APELLIDO", "NUMERO", "POSICION"};
        DefaultTableModel tm = new DefaultTableModel(null, encabezado);
        listaJugadores.addAll(equipos);
        for(Equipo item : listaJugadores){
            objects[0] = item.getId();
            objects[1] = item.getNombrejugador();
            objects[2] = item.getApellidojugador();
            objects[3] = item.getNumerocamiseta();
            objects[4] = item.getPosicion();
            tm.addRow(objects);
        }
        jtEquipo.setModel(tm);
    }

    void actualizarForm() throws SQLException {
        txtId.setText("");
        txtNombre.setText("");
        txtApellido.setText("");
        txtNumero.setText("");
        txtPosicion.setText("");

        txtId.setEnabled(false);
        txtNombre.setEnabled(false);
        txtApellido.setEnabled(false);
        txtNumero.setEnabled(false);
        txtPosicion.setEnabled(false);


        btnNuevo.setEnabled(true);
        btnGuardar.setEnabled(false);
        btnModificar.setEnabled(false);
        btnEliminar.setEnabled(false);

        txtCriterio.setText("");
        criterioBusqueda.clearSelection();

        llenarTabla(equipoBL.obtenerTodos());
    }

}
