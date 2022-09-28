package views;

import java.awt.Image;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import models.usuariosModel;
import org.edisoncor.gui.util.Avatar;

public class ventanaMenu extends javax.swing.JFrame {

    private usuariosModel usuariosmodel;

    public ventanaMenu() {

        initComponents();
        setLocationRelativeTo(this);
        llenarMenu();
        this.setSize(1300, 850);

    }

    public void cargarUsuarioSistema(usuariosModel usuario) {
        usuariosmodel = usuario;
    }

    private void llenarMenu() {
        List<Avatar> avatars = new ArrayList<>();

        avatars.add(new Avatar("Cambiar Clave", loadImage("/img/clave.png")));
        avatars.add(new Avatar("Ver Reservas y Huespedes", loadImage("/img/informes.png")));
        avatars.add(new Avatar("Reservas", loadImage("/img/pedidos y despacho.png")));
        avatars.add(new Avatar("Usuarios de Sistema", loadImage("/img/datos empleados.png")));
        avatars.add(new Avatar("Salir del Sistema", loadImage("/img/salir.png")));
        menu.setAvatars(avatars);

    }

    private static Image loadImage(String FileName) {
        try {
            return ImageIO.read(ventanaMenu.class.getResource(FileName));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            return null;
        }
    }

    public int Modulo(int index) {

        switch (index) {

            case 0:
                actualizarClave upClave = new actualizarClave(null, true);
                upClave.mostrarUsuario(usuariosmodel.getUsuario());
                upClave.setVisible(true);
                break;
            case 1:
                sistemaBusqueda verHuespedReservas = new sistemaBusqueda(null, true);
                verHuespedReservas.setVisible(true);
                break;
            case 2:
                reservas reserva = new reservas(null, true);
                reserva.setVisible(true);

                break;
            case 3:
                registroUsuarios registrousuarios = new registroUsuarios(null, true);
                registrousuarios.setVisible(true);

                break;
            case 4:
                this.dispose();
                break;

        }
        return index;

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        menu = new org.edisoncor.gui.panel.PanelAvatarChooser();
        buttonIpod1 = new org.edisoncor.gui.button.ButtonIpod();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("MENU ");
        setModalExclusionType(java.awt.Dialog.ModalExclusionType.APPLICATION_EXCLUDE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        menu.setForeground(new java.awt.Color(0, 153, 102));
        menu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/fondo.png"))); // NOI18N
        menu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menuMouseClicked(evt);
            }
        });
        menu.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                menuKeyPressed(evt);
            }
        });
        menu.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        buttonIpod1.setText("...");
        buttonIpod1.setColorDeSombra(new java.awt.Color(204, 0, 51));
        buttonIpod1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                buttonIpod1MouseClicked(evt);
            }
        });
        menu.add(buttonIpod1, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 260, 247, 261));

        getContentPane().add(menu, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1282, 800));

        pack();
    }// </editor-fold>//GEN-END:initComponents

private void menuKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_menuKeyPressed
    llenarMenu();
}//GEN-LAST:event_menuKeyPressed

    private void menuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuMouseClicked
        // TODO add your handling code here:
        llenarMenu();
    }//GEN-LAST:event_menuMouseClicked

    private void buttonIpod1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonIpod1MouseClicked
        // TODO add your handling code here:
        int eleccion = JOptionPane.showConfirmDialog(null, "¿Estas seguro que quieres entrar a este modulo"
                + "?" + "\n " + menu.getSelectedtitulo());
        if (eleccion == 0) {
            int index = menu.getAvatarIndex();
            Modulo(index);
            System.out.println(index);
        }

    }//GEN-LAST:event_buttonIpod1MouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;

                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ventanaMenu.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ventanaMenu.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ventanaMenu.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ventanaMenu.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new ventanaMenu().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private org.edisoncor.gui.button.ButtonIpod buttonIpod1;
    private org.edisoncor.gui.panel.PanelAvatarChooser menu;
    // End of variables declaration//GEN-END:variables

}
