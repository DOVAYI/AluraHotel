package views;

import Controllers.usuariosController;
import java.util.Arrays;
import javax.swing.JOptionPane;
import models.usuariosModel;

public class registroUsuarios extends javax.swing.JDialog {

    private usuariosController usuarios;

    public registroUsuarios(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setSize(1050, 560);
        setLocationRelativeTo(this);
        lblMensaje.setVisible(false);
    }

    private boolean validarCampos() {
        boolean rsp = false;

        String password = String.valueOf(txtPassword.getPassword());
        String repetirPassword = String
                .valueOf(txtRepetirPassword.getPassword());

        if (txtUsuario.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Debe ingresar un usuario");
            txtUsuario.requestFocus();
        } else if (password.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Debe ingresar contraseña");
            txtPassword.requestFocus();
        } else if (repetirPassword.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Debe repetir contraseña");
            txtRepetirPassword.requestFocus();
        } else {
            rsp = true;
        }
        return rsp;
    }

    private void limpiarCampos() {
        txtUsuario.setText(null);
        txtPassword.setText(null);
        txtRepetirPassword.setText(null);
    }

    private void guardar() {

        usuariosModel usuariosmodel = new usuariosModel(txtUsuario.getText(),
                String.valueOf(txtPassword.getPassword()));
        usuarios = new usuariosController();
        boolean rsp = usuarios.guardar(usuariosmodel);
        if (rsp) {
            JOptionPane.showMessageDialog(null,
                    "Usuario guardado con ¡EXITO! :)");
            limpiarCampos();
            lblMensaje.setVisible(false);
        } else {
            lblMensaje.setVisible(true);

        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelImage1 = new org.edisoncor.gui.panel.PanelImage();
        jPanel1 = new javax.swing.JPanel();
        txtPassword = new javax.swing.JPasswordField();
        jLabel2 = new javax.swing.JLabel();
        txtUsuario = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        btnGuardar = new javax.swing.JButton();
        txtRepetirPassword = new javax.swing.JPasswordField();
        jLabel4 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        lblMensaje = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        btnSalir = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        panelImage1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/fondologin.png"))); // NOI18N
        panelImage1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Registro Usuarios Sistema", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Gabriola", 0, 36), new java.awt.Color(255, 255, 255))); // NOI18N
        jPanel1.setOpaque(false);
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel1.add(txtPassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 225, 260, 30));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/claveok.png"))); // NOI18N
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 220, 350, 40));

        txtUsuario.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        jPanel1.add(txtUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 125, 250, 30));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/usuariook.png"))); // NOI18N
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 120, 350, 40));

        btnGuardar.setBackground(new java.awt.Color(0, 255, 51));
        btnGuardar.setFont(new java.awt.Font("Gabriola", 1, 24)); // NOI18N
        btnGuardar.setForeground(new java.awt.Color(255, 255, 255));
        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });
        jPanel1.add(btnGuardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 390, -1, -1));
        jPanel1.add(txtRepetirPassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 315, 260, 30));

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/claveok.png"))); // NOI18N
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 310, 350, 40));

        jLabel1.setFont(new java.awt.Font("Gabriola", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Repetir contraseña");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 290, -1, 20));

        lblMensaje.setFont(new java.awt.Font("Gabriola", 1, 28)); // NOI18N
        lblMensaje.setForeground(new java.awt.Color(255, 255, 255));
        lblMensaje.setText("No se pudo Guardar Usuario");
        jPanel1.add(lblMensaje, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 460, -1, 20));

        jLabel6.setFont(new java.awt.Font("Gabriola", 1, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Escriba contraseña ");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 200, -1, 20));

        jLabel7.setFont(new java.awt.Font("Gabriola", 1, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Usuario o Nombre que utilizara para ingresar al sistema");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 95, -1, 20));

        panelImage1.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 390, 490));

        btnSalir.setBackground(new java.awt.Color(255, 255, 255));
        btnSalir.setFont(new java.awt.Font("Gabriola", 1, 24)); // NOI18N
        btnSalir.setForeground(new java.awt.Color(0, 0, 255));
        btnSalir.setText("Salir");
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });
        panelImage1.add(btnSalir, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 410, 150, 50));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelImage1, javax.swing.GroupLayout.DEFAULT_SIZE, 941, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelImage1, javax.swing.GroupLayout.PREFERRED_SIZE, 550, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        if (validarCampos()) {
            if (String.valueOf(txtPassword.getPassword()).equals(String
                    .valueOf(txtRepetirPassword.getPassword()))) {
                guardar();
            } else {
                JOptionPane.showMessageDialog(null,
                        "Las contraseñas no coinciden");
            }

        }

    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        try {
            usuarios.cerrarConexion(1, 1);
            this.dispose();
        } catch (Exception e) {
            this.dispose();
        }
    }//GEN-LAST:event_btnSalirActionPerformed

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                registroUsuarios dialog
                        = new registroUsuarios(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton btnSalir;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblMensaje;
    private org.edisoncor.gui.panel.PanelImage panelImage1;
    private javax.swing.JPasswordField txtPassword;
    private javax.swing.JPasswordField txtRepetirPassword;
    private javax.swing.JTextField txtUsuario;
    // End of variables declaration//GEN-END:variables
}
