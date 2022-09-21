package views;

import Controllers.huespedController;
import Controllers.reservasController;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import javax.swing.JOptionPane;
import models.huesped;
import models.reservasModel;

public class reservas extends javax.swing.JDialog {

    reservasModel reservamodel;
    int aux = 0;
    huespedController huespedcontroller;
    reservasController reservacontroller;

    public reservas(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setSize(1120, 610);
        setLocationRelativeTo(this);
        desHabilitarCampos();

    }

    private void desHabilitarCampos() {
        txtNombres.setEnabled(false);
        txtIdentificacion.setEnabled(false);
        fechaNacido.setEnabled(false);
        txtTelefonos.setEnabled(false);
        btnGuardar.setEnabled(false);
        txtNumeroReserva.setEnabled(false);
        txtValorReserva.setEnabled(false);
    }

    private void activarGuardarHuesped() {
        txtNombres.setEnabled(true);
        txtIdentificacion.setEnabled(true);
        fechaNacido.setEnabled(true);
        txtTelefonos.setEnabled(true);
        btnGuardar.setEnabled(true);
        huespedcontroller = new huespedController();
        String maximoId = String.valueOf(huespedcontroller.buscarMaximoIdReserva() + 1);
        txtNumeroReserva.setText(maximoId);

    }

    private void calcularValorReserva(java.beans.PropertyChangeEvent evt) {

        if (aux > 0) {
            Date inicial = fechaInicial.getDate();
            if (inicial != null) {
                LocalDate inicio = convertirFecha(inicial);
                LocalDate fin = convertirFecha(fechaFinal.getDate());
                reservamodel = new reservasModel(inicio, fin);
                double total = reservamodel.valorTotalReservas();

                if (total > 0) {
                    String valorFinal = String.valueOf(total);

                    txtValorReserva.setText(valorFinal);
                } else {
                    JOptionPane.showMessageDialog(null, "Esta eligiendo fecha final incorrecta");
                }
            }

        }
        aux++;

    }

    private LocalDate convertirFecha(Date fecha) {
        LocalDate fechaconvertida = Instant.ofEpochMilli(fecha.getTime())
                .atZone(ZoneId.systemDefault())
                .toLocalDate();;
        return fechaconvertida;

    }

    private boolean guardar() {

        boolean rsp = false;
        try {
            LocalDate nacido = convertirFecha(fechaNacido.getDate());

            huesped huesped = new huesped(Integer.parseInt(txtIdentificacion.getText()),
                    txtNombres.getText(), nacido, txtTelefonos.getText());
            huespedcontroller = new huespedController();
            rsp = huespedcontroller.guardar(huesped);
            huespedcontroller.cerrarConexion();
        } catch (Exception dt) {
            JOptionPane.showMessageDialog(null, "Ocurrio un error" + dt.getMessage());
        }

        return rsp;
    }

    private boolean guardarReserva() {
        boolean rsp = false;
        try {
            LocalDate inicioFecha = convertirFecha(fechaInicial.getDate());
            LocalDate finalFecha = convertirFecha(fechaFinal.getDate());

            reservasModel reservas = new reservasModel(inicioFecha, finalFecha,
                    (String) comboFormaPago.getSelectedItem(),
                    Integer.parseInt(txtIdentificacion.getText()));

            reservacontroller = new reservasController();
            reservacontroller.guardarReserva(reservas);
            reservacontroller.cerrarConexion();
            rsp = true;
        } catch (Exception e) {
            System.out.println("error " + e.getMessage());
        }

        return rsp;
    }

    private boolean validarCamposReserva() {
        boolean camposValidos = false;

        if (fechaInicial.getDate() == null) {
            JOptionPane.showMessageDialog(null, "Debe Elegir la fecha Inicial");
        } else if (fechaFinal.getDate() == null) {
            JOptionPane.showMessageDialog(null, "Debe Elegir la fecha Final");
        } else if (comboFormaPago.getSelectedItem().equals("SELECCIONE")) {
            JOptionPane.showMessageDialog(null, "Debe Elegir forma de pago");
        } else {
            camposValidos = true;
        }

        return camposValidos;

    }

    private boolean validarCamposGuardar() {
        boolean camposValidos = false;
        if (txtIdentificacion.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Debe escribir numero de identificacion del Huesped");
            txtIdentificacion.requestFocus();
        } else if (txtNombres.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Debe escribir nombres de huesped");
            txtNombres.requestFocus();
        } else if (fechaNacido.getDate() == null) {
            JOptionPane.showMessageDialog(null, "Debe frecha de nacimiento");
        } else {
            camposValidos = true;
        }
        return camposValidos;
    }

    private void limpiarCampos() {
        fechaInicial.setDate(null);
        fechaInicial.setDate(null);
        txtValorReserva.setText(null);
        comboFormaPago.setSelectedIndex(0);
        txtNumeroReserva.setText(null);
        txtIdentificacion.setText(null);
        txtNombres.setText(null);
        fechaNacido.setDate(null);
        txtTelefonos.setText(null);
        desHabilitarCampos();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panel1 = new org.edisoncor.gui.panel.Panel();
        fechaInicial = new com.toedter.calendar.JDateChooser();
        jLabel2 = new javax.swing.JLabel();
        fechaFinal = new com.toedter.calendar.JDateChooser();
        jLabel3 = new javax.swing.JLabel();
        txtValorReserva = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        comboFormaPago = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        btnGuardar = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtTelefonos = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtNumeroReserva = new javax.swing.JTextField();
        fechaNacido = new com.toedter.calendar.JDateChooser();
        txtNombres = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        btnSiguiente = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        txtIdentificacion = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/reserva.png"))); // NOI18N
        panel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        getContentPane().add(panel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 0, 540, 593));

        fechaInicial.setBackground(new java.awt.Color(51, 51, 255));
        fechaInicial.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(51, 51, 255), 1, true));
        getContentPane().add(fechaInicial, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 135, 242, 36));

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 21)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(51, 51, 255));
        jLabel2.setText("INFORMACION HUÉSPED");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 10, 260, 35));

        fechaFinal.setBackground(new java.awt.Color(51, 51, 255));
        fechaFinal.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(51, 51, 255), 1, true));
        getContentPane().add(fechaFinal, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 222, 242, 36));
        fechaFinal.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                calcularValorReserva(evt);

            }
        });

        jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(51, 51, 255));
        jLabel3.setText("FORMA DE PAGO");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 374, -1, 20));
        getContentPane().add(txtValorReserva, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 309, 242, 36));

        jLabel4.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(51, 51, 255));
        jLabel4.setText("FECHA FINAL");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 198, -1, 20));

        comboFormaPago.setBackground(new java.awt.Color(204, 204, 255));
        comboFormaPago.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        comboFormaPago.setForeground(new java.awt.Color(51, 51, 255));
        comboFormaPago.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "SELECCIONE", "EFECTIVO", "TARJETA DE CREDITO", "TARJETA DEBITO", "CHEQUE" }));
        getContentPane().add(comboFormaPago, new org.netbeans.lib.awtextra.AbsoluteConstraints(13, 396, 242, 36));

        jLabel5.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(51, 51, 255));
        jLabel5.setText("VALOR DE LA RESERVA");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 286, -1, 20));

        btnGuardar.setBackground(new java.awt.Color(102, 102, 255));
        btnGuardar.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        btnGuardar.setForeground(new java.awt.Color(255, 255, 255));
        btnGuardar.setText("GUARDAR");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });
        getContentPane().add(btnGuardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 483, 140, 30));

        jLabel6.setFont(new java.awt.Font("Times New Roman", 1, 21)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(51, 51, 255));
        jLabel6.setText("SISTEMA DE RESERVAS");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 11, 250, 35));

        jLabel7.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(51, 51, 255));
        jLabel7.setText("FECHA DE INICIO");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, -1, 20));
        getContentPane().add(txtTelefonos, new org.netbeans.lib.awtextra.AbsoluteConstraints(825, 396, 242, 36));

        jLabel8.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(51, 51, 255));
        jLabel8.setText("TELEFÓNO");
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(825, 374, -1, 20));
        getContentPane().add(txtNumeroReserva, new org.netbeans.lib.awtextra.AbsoluteConstraints(1020, 50, 60, 40));
        getContentPane().add(fechaNacido, new org.netbeans.lib.awtextra.AbsoluteConstraints(825, 309, 242, 36));
        getContentPane().add(txtNombres, new org.netbeans.lib.awtextra.AbsoluteConstraints(825, 222, 242, 36));

        jLabel9.setFont(new java.awt.Font("Times New Roman", 1, 10)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(51, 51, 255));
        jLabel9.setText("Numero de Reserva");
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 50, -1, 40));

        jLabel10.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(51, 51, 255));
        jLabel10.setText("NOMBRES");
        getContentPane().add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(825, 198, -1, 20));

        jLabel11.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(51, 51, 255));
        jLabel11.setText("FECHA DE NACIMIENTO");
        getContentPane().add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(825, 286, -1, 20));

        btnSiguiente.setBackground(new java.awt.Color(102, 102, 255));
        btnSiguiente.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        btnSiguiente.setForeground(new java.awt.Color(255, 255, 255));
        btnSiguiente.setText("SIGUIENTE");
        btnSiguiente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSiguienteActionPerformed(evt);
            }
        });
        getContentPane().add(btnSiguiente, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 483, 140, 30));

        jLabel12.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(51, 51, 255));
        jLabel12.setText("IDENTIFICACIÓN");
        getContentPane().add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(825, 110, -1, 20));

        txtIdentificacion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtIdentificacionKeyTyped(evt);
            }
        });
        getContentPane().add(txtIdentificacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(825, 135, 242, 36));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        if (validarCamposGuardar()) {
            if (guardar()) {
                if (guardarReserva()) {
                    JOptionPane.showMessageDialog(null, "Registro Guardado con exito");
                    limpiarCampos();
                } else {
                    JOptionPane.showMessageDialog(null, "Ocurrio un error");
                }
            }

        }

    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnSiguienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSiguienteActionPerformed
        if (validarCamposReserva()) {
            activarGuardarHuesped();

        }

    }//GEN-LAST:event_btnSiguienteActionPerformed

    private void txtIdentificacionKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtIdentificacionKeyTyped
        char c = evt.getKeyChar();
        if (c < '0' || c > '9') {
            evt.consume();
        }
    }//GEN-LAST:event_txtIdentificacionKeyTyped

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                reservas dialog = new reservas(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnSiguiente;
    private javax.swing.JComboBox<String> comboFormaPago;
    private com.toedter.calendar.JDateChooser fechaFinal;
    private com.toedter.calendar.JDateChooser fechaInicial;
    private com.toedter.calendar.JDateChooser fechaNacido;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private org.edisoncor.gui.panel.Panel panel1;
    private javax.swing.JTextField txtIdentificacion;
    private javax.swing.JTextField txtNombres;
    private javax.swing.JTextField txtNumeroReserva;
    private javax.swing.JTextField txtTelefonos;
    private javax.swing.JTextField txtValorReserva;
    // End of variables declaration//GEN-END:variables
}
