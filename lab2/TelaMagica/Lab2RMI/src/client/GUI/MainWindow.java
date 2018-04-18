package client.GUI;

import javax.swing.JOptionPane;

/**
 * Janela inicial após iniciar a conexão.
 *
 * @author Yan
 * @author Micael
 * @author Victor
 */
public class MainWindow extends javax.swing.JFrame {
    private ClientController cc = null;

    public MainWindow(ClientController cc) {
        initComponents();
        this.cc = cc;

        ConnectionDialog connDialog = new ConnectionDialog(this, true);
        connDialog.setVisible(true);

        do {

            try {

                break;
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null,
                    "Não foi possível conectar",
                    "Erro ao Conectar", JOptionPane.ERROR_MESSAGE);
                connDialog.setVisible(true);
            }

        } while (true);

        connDialog.dispose();
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Lab 2 - RMI - Tela Mágica");
        setBackground(java.awt.Color.darkGray);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });
        getContentPane().setLayout(new java.awt.GridLayout(1, 0));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        System.exit(0);
    }//GEN-LAST:event_formWindowClosing


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
