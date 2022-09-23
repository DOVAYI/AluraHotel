package views;

import Controllers.huespedController;
import Controllers.reservasController;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import models.huesped;
import models.reservasModel;

public class sistemaBusqueda extends javax.swing.JDialog {

    DefaultTableModel tablaModelo;
    reservasController reservascontroller;
    huespedController huesped;

    public sistemaBusqueda(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        reservascontroller = new reservasController();
        huesped = new huespedController();
        initComponents();
        setLocationRelativeTo(this);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panel2 = new org.edisoncor.gui.panel.Panel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaHuespedReservas = new javax.swing.JTable();
        btnReservas = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        btnHuesped = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();

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

        jButton2.setBackground(new java.awt.Color(0, 204, 204));
        jButton2.setFont(new java.awt.Font("Gabriola", 1, 24)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("Editar");
        panel2.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 430, 110, 40));

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

        jButton4.setBackground(new java.awt.Color(255, 51, 51));
        jButton4.setFont(new java.awt.Font("Gabriola", 1, 24)); // NOI18N
        jButton4.setForeground(new java.awt.Color(255, 255, 255));
        jButton4.setText("Eliminar");
        panel2.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 430, 110, 40));

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

        getContentPane().add(panel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1040, 580));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnReservasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReservasActionPerformed
        String cabecera[] = {"Fecha Incio", "Fecha Fin", "Forma de Pago",
            "Valor Total"};
        iniciarTabla(cabecera, 4, reservascontroller.listar(), null);
    }//GEN-LAST:event_btnReservasActionPerformed

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        reservascontroller.cerrarConexion(0, 1);
        huesped.cerrarConexion(0, 1);
        this.dispose();
    }//GEN-LAST:event_btnSalirActionPerformed

    private void btnHuespedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHuespedActionPerformed
        String cabecera[] = {"Identificación", "Nombres y Apellidos",
            "Fecha Nacimiento", "Telefónos"};
        iniciarTabla(cabecera, 4, null, huesped.listar());
    }//GEN-LAST:event_btnHuespedActionPerformed

    public void iniciarTabla(String cabecera[], int tamañoVector,
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
            });
        }

    }

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                sistemaBusqueda dialog = new sistemaBusqueda(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton btnHuesped;
    private javax.swing.JButton btnReservas;
    private javax.swing.JButton btnSalir;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton4;
    private javax.swing.JScrollPane jScrollPane1;
    private org.edisoncor.gui.panel.Panel panel2;
    private javax.swing.JTable tablaHuespedReservas;
    // End of variables declaration//GEN-END:variables
}
