package views;

import Controllers.huespedController;
import Controllers.reservasController;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import models.huesped;
import models.reservasModel;
import utilidades.convertirFechas;

public class sistemaBusqueda extends javax.swing.JDialog {

    private DefaultTableModel tablaModelo;
    private reservasController reservascontroller;
    private huespedController huesped;
    private List<reservasModel> listaReservas;
    private List<huesped> listaHuesped;
    private String tablaSeleccionada = "";
    DefaultTableModel modelo;

    public sistemaBusqueda(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        reservascontroller = new reservasController();
        huesped = new huespedController();
        initComponents();
        setLocationRelativeTo(this);

    }

    private void iniciarTabla(String cabecera[], int tamañoVector,
            List<reservasModel> reservas, List<huesped> huespedes) {
        tablaModelo = new DefaultTableModel(null, cabecera);
        String columnas[] = new String[tamañoVector];

        if (reservas != null) {
            reservas.forEach(reserva -> {
                columnas[0] = String.valueOf(reserva.getFechaInicio());
                columnas[1] = String.valueOf(reserva.getFechaFin());
                columnas[2] = String.valueOf(reserva.getFormaPago());
                columnas[3] = String.valueOf(reserva.getValorTotalReserva());
                tablaModelo.addRow(columnas);
                tablaHuespedReservas.setModel(tablaModelo);
                tablaSeleccionada = "reserva";
            });
        }

        if (huespedes != null) {
            huespedes.forEach(huesped -> {
                columnas[0] = String.valueOf(huesped.getIdentificacion());
                columnas[1] = String.valueOf(huesped.getNombres());
                columnas[2] = String.valueOf(huesped.getNacimiento());
                columnas[3] = String.valueOf(huesped.getTelefonos());
                tablaModelo.addRow(columnas);
                tablaHuespedReservas.setModel(tablaModelo);
                tablaSeleccionada = "huesped";
            });
        }

        if ((reservas == null) && (huespedes == null)) {
            columnas[0] = "";
            columnas[1] = "";
            columnas[2] = "";
            columnas[3] = "";
            tablaModelo.addRow(columnas);
            tablaModelo.removeRow(0);
            tablaHuespedReservas.setModel(tablaModelo);
            tablaSeleccionada = "";
        }

    }

    private String[] cabeceraTablaReserva() {
        String cabecera[] = {"Fecha Incio", "Fecha Fin", "Forma de Pago",
            "Valor Total"};

        return cabecera;
    }

    private String[] cabeceraTablaHuesped() {
        String cabecera[] = {"Identificación", "Nombres y Apellidos",
            "Fecha Nacimiento", "Telefónos"};

        return cabecera;
    }

    private void buscarReservaPorId() {
        try {
            List<reservasModel> reservaPorId = reservascontroller.listar(
                    Integer.parseInt(txtBuscar.getText()));

            if (reservaPorId == null || reservaPorId.size() == 0) {
                JOptionPane.showMessageDialog(null, "registro No encontrado");

            } else {

                iniciarTabla(cabeceraTablaReserva(), 4, reservaPorId, null);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,
                    "Esta ingresando un numero demasiado grande ");
            System.out.println("exception " + e.getMessage());
        }

    }

    private void editarReservaPorId(int fila) {
        modelo = (DefaultTableModel) tablaHuespedReservas.getModel();
        int idHuesped = reservascontroller.listar(0).
                get(fila).getIdHuesped();

        LocalDate inicial = convertirFechas.fechasConvertir(
                Date.valueOf(modelo.getValueAt(fila, 0).toString()));
        LocalDate finalfecha = convertirFechas.fechasConvertir(
                Date.valueOf(modelo.getValueAt(fila, 1).toString()));
        double pago = Double.parseDouble(modelo.getValueAt(fila, 3)
                .toString());

        boolean rsp = reservascontroller.modificar(inicial, finalfecha, pago,
                modelo.getValueAt(fila, 2).toString(), idHuesped);

        if (rsp) {
            JOptionPane.showMessageDialog(null, "Registro Editado con exito");
            iniciarTabla(cabeceraTablaHuesped(), 4, reservascontroller.listar(0), null);
        } else {
            JOptionPane.showMessageDialog(null, "No se pudo Editar Registro");
        }
    }

    private void editarHuespedPorId(int fila) {
        modelo = (DefaultTableModel) tablaHuespedReservas.getModel();
        LocalDate nacimiento = convertirFechas.fechasConvertir(
                Date.valueOf(modelo.getValueAt(fila, 2).toString()));

        int identificacion = Integer.parseInt(modelo.getValueAt(fila, 0).toString());
        boolean rsp = huesped.modificar(modelo.getValueAt(fila, 1).toString(), nacimiento,
                modelo.getValueAt(fila, 3).toString(), identificacion);

        if (rsp) {
            JOptionPane.showMessageDialog(null, "Registro Editado con exito");
            iniciarTabla(cabeceraTablaHuesped(), 4, null, huesped.listar(0));
        } else {
            JOptionPane.showMessageDialog(null, "No se pudo Editar Registro");
        }

    }

    private void eliminarReservaPorId(int fila) {
        try {
            int idHuesped = reservascontroller.listar(0).
                    get(fila).getIdHuesped();

            if (reservascontroller.eliminar(idHuesped)) {
                JOptionPane.showMessageDialog(null,
                        "Registro Eliminado con exito");
                listaReservas = reservascontroller.listar(0);
                if (listaReservas.size() == 0) {
                    listaReservas = null;
                }
                iniciarTabla(cabeceraTablaReserva(), 4,
                        listaReservas, null);

            } else {
                JOptionPane.showMessageDialog(null, "No se pudo Eliminar Reserva");
            }
        } catch (Exception e) {
            System.out.println("error metodo eliminarReservaPorId " + e.getMessage());
        }

    }

    private void eliminarHuespedPorId(int fila) {
        modelo = (DefaultTableModel) tablaHuespedReservas.getModel();
        boolean rsp = huesped.eliminar(Integer.parseInt(modelo.getValueAt(fila, 0)
                .toString()));
        if (rsp) {
            JOptionPane.showMessageDialog(null, "Huesped Eliminad con Exito");
            listaHuesped = huesped.listar(0);
            if (listaHuesped.size() == 0) {
                listaHuesped = null;
            }
            iniciarTabla(cabeceraTablaHuesped(), 4, null, listaHuesped);
        } else {
            JOptionPane.showMessageDialog(null, "Error al eliminar Huesped");
        }

    }

    private void buscarHuespedPorId() {
        try {
            List<huesped> huespedPorId = huesped.listar(
                    Integer.parseInt(txtBuscar.getText()));

            if (huespedPorId == null || huespedPorId.size() == 0) {
                JOptionPane.showMessageDialog(null, "registro No encontrado");

            } else {

                iniciarTabla(cabeceraTablaHuesped(), 4, null, huespedPorId);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,
                    "Esta ingresando un numero demasiado grande ");
            System.out.println("exception " + e.getMessage());
        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panel2 = new org.edisoncor.gui.panel.Panel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaHuespedReservas = new javax.swing.JTable();
        btnReservas = new javax.swing.JButton();
        btnEditar = new javax.swing.JButton();
        btnHuesped = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        txtBuscar = new javax.swing.JTextField();
        btnBuscar = new javax.swing.JButton();
        comboHuespedOrReserva = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Huesped y Reservas", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Gabriola", 1, 32), new java.awt.Color(255, 255, 255))); // NOI18N
        panel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/fondo.png"))); // NOI18N
        panel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tablaHuespedReservas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tablaHuespedReservas);

        panel2.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 160, 870, 230));

        btnReservas.setBackground(new java.awt.Color(255, 0, 255));
        btnReservas.setFont(new java.awt.Font("Gabriola", 1, 24)); // NOI18N
        btnReservas.setForeground(new java.awt.Color(255, 255, 255));
        btnReservas.setText("Reservas");
        btnReservas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReservasActionPerformed(evt);
            }
        });
        panel2.add(btnReservas, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 120, 110, 40));

        btnEditar.setBackground(new java.awt.Color(0, 204, 204));
        btnEditar.setFont(new java.awt.Font("Gabriola", 1, 28)); // NOI18N
        btnEditar.setForeground(new java.awt.Color(255, 255, 255));
        btnEditar.setText("Editar");
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });
        panel2.add(btnEditar, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 430, 200, 40));

        btnHuesped.setBackground(new java.awt.Color(0, 0, 204));
        btnHuesped.setFont(new java.awt.Font("Gabriola", 1, 24)); // NOI18N
        btnHuesped.setForeground(new java.awt.Color(255, 255, 255));
        btnHuesped.setText("Huesped");
        btnHuesped.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHuespedActionPerformed(evt);
            }
        });
        panel2.add(btnHuesped, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 120, 110, 40));

        btnEliminar.setBackground(new java.awt.Color(255, 51, 51));
        btnEliminar.setFont(new java.awt.Font("Gabriola", 1, 28)); // NOI18N
        btnEliminar.setForeground(new java.awt.Color(255, 255, 255));
        btnEliminar.setText("Eliminar");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });
        panel2.add(btnEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 430, 200, 40));

        btnSalir.setBackground(new java.awt.Color(0, 255, 51));
        btnSalir.setFont(new java.awt.Font("Gabriola", 1, 24)); // NOI18N
        btnSalir.setForeground(new java.awt.Color(255, 255, 255));
        btnSalir.setText("Salir");
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });
        panel2.add(btnSalir, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 520, 130, 40));

        jLabel1.setFont(new java.awt.Font("Gabriola", 1, 20)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Buscar por:");
        panel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 70, 90, 20));

        txtBuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtBuscarKeyTyped(evt);
            }
        });
        panel2.add(txtBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 90, 220, 30));

        btnBuscar.setBackground(new java.awt.Color(0, 204, 0));
        btnBuscar.setFont(new java.awt.Font("Gabriola", 0, 20)); // NOI18N
        btnBuscar.setForeground(new java.awt.Color(255, 255, 255));
        btnBuscar.setText("Buscar");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });
        panel2.add(btnBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 90, 100, 30));

        comboHuespedOrReserva.setBackground(new java.awt.Color(102, 102, 255));
        comboHuespedOrReserva.setFont(new java.awt.Font("Gabriola", 1, 20)); // NOI18N
        comboHuespedOrReserva.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione", "Huesped", "Reserva" }));
        panel2.add(comboHuespedOrReserva, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 90, 130, 30));

        jLabel2.setFont(new java.awt.Font("Gabriola", 1, 20)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("N° de Identificación:");
        panel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(625, 70, 150, 20));

        getContentPane().add(panel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1040, 580));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnReservasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReservasActionPerformed
        listaReservas = reservascontroller.listar(0);
        if (listaReservas.size() == 0) {
            listaReservas = null;
        }
        iniciarTabla(cabeceraTablaReserva(), 4, listaReservas, null);

    }//GEN-LAST:event_btnReservasActionPerformed

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        reservascontroller.cerrarConexion(0, 1);
        huesped.cerrarConexion(0, 1);
        this.dispose();
    }//GEN-LAST:event_btnSalirActionPerformed

    private void btnHuespedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHuespedActionPerformed
        listaHuesped = huesped.listar(0);
        if (listaHuesped.size() == 0) {
            listaHuesped = null;
        }
        iniciarTabla(cabeceraTablaHuesped(), 4, null, listaHuesped);

    }//GEN-LAST:event_btnHuespedActionPerformed

    private void txtBuscarKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarKeyTyped
        char c = evt.getKeyChar();
        if (c < '0' || c > '9') {
            evt.consume();
        }
    }//GEN-LAST:event_txtBuscarKeyTyped

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        if (txtBuscar.getText().isEmpty()
                || comboHuespedOrReserva.getSelectedItem().
                        equals("Seleccione")) {
            JOptionPane.showMessageDialog(null,
                    "Debe ingresar el numero de identificación "
                    + "y/ó elegir tipo de busqueda");
            txtBuscar.requestFocus();
        } else {
            if (comboHuespedOrReserva.getSelectedItem().equals("Huesped")) {
                buscarHuespedPorId();
            } else if (comboHuespedOrReserva.getSelectedItem().
                    equals("Reserva")) {
                buscarReservaPorId();
            }
        }
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        if (!tablaSeleccionada.equals("")) {
            int filaSeleccionada = tablaHuespedReservas.getSelectedRow();
            if (tablaSeleccionada.equals("reserva")) {
                if (filaSeleccionada == -1) {
                    JOptionPane.showMessageDialog(null,
                            "Debe seleccionar una fila de tabla,"
                            + " para eliminar registro");
                } else {
                    eliminarReservaPorId(filaSeleccionada);
                }
            } else {
                if (filaSeleccionada == -1) {
                    JOptionPane.showMessageDialog(null,
                            "Debe seleccionar una fila de tabla,"
                            + " para eliminar registro");
                } else {
                    eliminarHuespedPorId(filaSeleccionada);
                }
            }

        }

    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
        if (!tablaSeleccionada.equals("")) {
            int filaSeleccionada = tablaHuespedReservas.getSelectedRow();
            if (tablaSeleccionada.equals("reserva")) {
                if (filaSeleccionada == -1) {
                    JOptionPane.showMessageDialog(null,
                            "Debe seleccionar una fila de tabla,"
                            + " para eliminar registro");
                } else {
                    editarReservaPorId(filaSeleccionada);
                }
            } else {
                if (filaSeleccionada == -1) {
                    JOptionPane.showMessageDialog(null,
                            "Debe seleccionar una fila de tabla,"
                            + " para eliminar registro");
                } else {
                    editarHuespedPorId(filaSeleccionada);
                }
            }

        }
    }//GEN-LAST:event_btnEditarActionPerformed

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                sistemaBusqueda dialog = new sistemaBusqueda(
                        new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnHuesped;
    private javax.swing.JButton btnReservas;
    private javax.swing.JButton btnSalir;
    private javax.swing.JComboBox<String> comboHuespedOrReserva;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private org.edisoncor.gui.panel.Panel panel2;
    private javax.swing.JTable tablaHuespedReservas;
    private javax.swing.JTextField txtBuscar;
    // End of variables declaration//GEN-END:variables
}
