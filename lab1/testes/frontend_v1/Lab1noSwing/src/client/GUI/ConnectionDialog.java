package client.GUI;

/**
 * "Janela" inicial para a recuperação
 * dos parâmetros para a conexão remota.
 *
 * @author Yan
 * @author micael
 */
public class ConnectionDialog extends javax.swing.JDialog {

    String ip = null;
    int port = 0;

    public ConnectionDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);

        initComponents();
        ipTextField.requestFocusInWindow();

        this.setLocationRelativeTo(null);
    }



    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        dialogPanel = new javax.swing.JPanel();
        cancelButton = new javax.swing.JButton();
        btnConectar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        ipTextField = new javax.swing.JFormattedTextField();
        portTextField = new javax.swing.JFormattedTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setResizable(false);
        setType(java.awt.Window.Type.POPUP);

        dialogPanel.setBackground(java.awt.Color.darkGray);

        cancelButton.setBackground(new java.awt.Color(180, 0, 26));
        cancelButton.setFont(new java.awt.Font("Microsoft JhengHei", 1, 12)); // NOI18N
        cancelButton.setBorderPainted(false);
        cancelButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cancelButton.setLabel("CANCELAR");
        cancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelButtonActionPerformed(evt);
            }
        });

        btnConectar.setBackground(new java.awt.Color(0, 230, 118));
        btnConectar.setFont(new java.awt.Font("Microsoft JhengHei", 1, 12)); // NOI18N
        btnConectar.setText("CONECTAR");
        btnConectar.setAutoscrolls(true);
        btnConectar.setBorderPainted(false);
        btnConectar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnConectar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConectarActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Microsoft JhengHei", 1, 12)); // NOI18N
        jLabel1.setForeground(java.awt.Color.white);
        jLabel1.setText("IP:");

        jLabel2.setFont(new java.awt.Font("Microsoft JhengHei", 1, 12)); // NOI18N
        jLabel2.setForeground(java.awt.Color.white);
        jLabel2.setText("Porta:");

        ipTextField.setBackground(java.awt.Color.darkGray);
        ipTextField.setForeground(java.awt.Color.white);
        ipTextField.setText("localhost");
        ipTextField.setToolTipText("digite o endereço IP do servidor");
        ipTextField.setCaretColor(java.awt.Color.white);
        ipTextField.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        ipTextField.setSelectionColor(new java.awt.Color(0, 230, 118));
        ipTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                ipTextFieldKeyReleased(evt);
            }
        });

        portTextField.setBackground(java.awt.Color.darkGray);
        portTextField.setForeground(java.awt.Color.white);
        portTextField.setText("4444");
        portTextField.setToolTipText("digite a porta para a conexão com o servidor");
        portTextField.setCaretColor(java.awt.Color.white);
        portTextField.setSelectionColor(new java.awt.Color(0, 230, 118));
        portTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                portTextFieldKeyTyped(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                portTextFieldKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout dialogPanelLayout = new javax.swing.GroupLayout(dialogPanel);
        dialogPanel.setLayout(dialogPanelLayout);
        dialogPanelLayout.setHorizontalGroup(
            dialogPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dialogPanelLayout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addGroup(dialogPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(dialogPanelLayout.createSequentialGroup()
                        .addComponent(cancelButton, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 67, Short.MAX_VALUE)
                        .addComponent(btnConectar, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, dialogPanelLayout.createSequentialGroup()
                        .addGap(42, 42, 42)
                        .addGroup(dialogPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(dialogPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jSeparator2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 148, Short.MAX_VALUE)
                            .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(ipTextField)
                            .addComponent(portTextField, javax.swing.GroupLayout.Alignment.LEADING))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(48, 48, 48))
        );
        dialogPanelLayout.setVerticalGroup(
            dialogPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dialogPanelLayout.createSequentialGroup()
                .addContainerGap(83, Short.MAX_VALUE)
                .addGroup(dialogPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(ipTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addGroup(dialogPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(portTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(52, 52, 52)
                .addGroup(dialogPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnConectar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cancelButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(dialogPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(dialogPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnConectarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConectarActionPerformed
        this.ip   = ipTextField.getText().trim();
        this.port = Integer.parseInt( portTextField.getText().trim() );

        this.setVisible(false);
    }//GEN-LAST:event_btnConectarActionPerformed

    private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelButtonActionPerformed
        System.exit(0);
    }//GEN-LAST:event_cancelButtonActionPerformed

    private void portTextFieldKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_portTextFieldKeyTyped
        char enter = evt.getKeyChar();
        if (!(Character.isDigit(enter))
            || portTextField.getText().trim().length() >= 5) evt.consume();
    }//GEN-LAST:event_portTextFieldKeyTyped

    private void ipTextFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ipTextFieldKeyReleased
        String portText = portTextField.getText().trim();
        String serverIP = ipTextField.getText().trim();
        btnConectar.setEnabled( !portText.isEmpty() && !serverIP.isEmpty() );
    }//GEN-LAST:event_ipTextFieldKeyReleased

    private void portTextFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_portTextFieldKeyReleased
        String portText = portTextField.getText().trim();
        String serverIP = ipTextField.getText().trim();
        btnConectar.setEnabled( !portText.isEmpty() && !serverIP.isEmpty() );
    }//GEN-LAST:event_portTextFieldKeyReleased



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
            java.util.logging.Logger.getLogger(ConnectionDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ConnectionDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ConnectionDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ConnectionDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                ConnectionDialog dialog = new ConnectionDialog(new javax.swing.JFrame(), true);

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
    public javax.swing.JButton btnConectar;
    private javax.swing.JButton cancelButton;
    private javax.swing.JPanel dialogPanel;
    private javax.swing.JFormattedTextField ipTextField;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JFormattedTextField portTextField;
    // End of variables declaration//GEN-END:variables
}
