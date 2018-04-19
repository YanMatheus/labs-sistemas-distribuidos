package client.GUI;

/**
 * "Janela" inicial para a recuperação
 * dos parâmetros para a conexão remota.
 *
 * @author Yan
 * @author Micael
 */
public class ConnectionDialog extends javax.swing.JDialog {
    String ip;
    int porta;
    String nickname;

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
        nicknameTextField = new javax.swing.JFormattedTextField();

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
        jLabel1.setText("<IP>:<Porta>");

        jLabel2.setFont(new java.awt.Font("Microsoft JhengHei", 1, 12)); // NOI18N
        jLabel2.setForeground(java.awt.Color.white);
        jLabel2.setText("nickname");

        ipTextField.setBackground(java.awt.Color.darkGray);
        ipTextField.setForeground(java.awt.Color.white);
        ipTextField.setText("localhost:4444");
        ipTextField.setToolTipText("digite o endereço IP do servidor seguido da porta");
        ipTextField.setCaretColor(java.awt.Color.white);
        ipTextField.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        ipTextField.setSelectionColor(new java.awt.Color(0, 230, 118));
        ipTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ipTextFieldActionPerformed(evt);
            }
        });
        ipTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                ipTextFieldKeyTyped(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                ipTextFieldKeyReleased(evt);
            }
        });

        nicknameTextField.setBackground(java.awt.Color.darkGray);
        nicknameTextField.setForeground(java.awt.Color.white);
        nicknameTextField.setText("foo");
        nicknameTextField.setToolTipText("digite a porta para a conexão com o servidor");
        nicknameTextField.setCaretColor(java.awt.Color.white);
        nicknameTextField.setSelectionColor(new java.awt.Color(0, 230, 118));
        nicknameTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nicknameTextFieldActionPerformed(evt);
            }
        });
        nicknameTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                nicknameTextFieldKeyTyped(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                nicknameTextFieldKeyReleased(evt);
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
                            .addComponent(nicknameTextField, javax.swing.GroupLayout.Alignment.LEADING))
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
                    .addComponent(nicknameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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
        String[] parametrosConexao = ipTextField.getText().split(":");
        this.ip = parametrosConexao[0];
        this.porta = Integer.parseInt( parametrosConexao[1] );
        this.nickname = nicknameTextField.getText();

        this.setVisible(false);
    }//GEN-LAST:event_btnConectarActionPerformed

    private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelButtonActionPerformed
        System.exit(0);
    }//GEN-LAST:event_cancelButtonActionPerformed

    private void ipTextFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ipTextFieldKeyReleased
        String nicknameText = nicknameTextField.getText().trim();
        String serverIP = ipTextField.getText().trim();
        btnConectar.setEnabled( !nicknameText.isEmpty() && !serverIP.isEmpty() );
    }//GEN-LAST:event_ipTextFieldKeyReleased

    private void nicknameTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nicknameTextFieldActionPerformed
        if ( btnConectar.isEnabled() ) btnConectarActionPerformed(evt);
    }//GEN-LAST:event_nicknameTextFieldActionPerformed

    private void ipTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ipTextFieldActionPerformed
        if ( btnConectar.isEnabled() ) btnConectarActionPerformed(evt);
    }//GEN-LAST:event_ipTextFieldActionPerformed

    private void nicknameTextFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nicknameTextFieldKeyReleased
        String nicknameText = nicknameTextField.getText();
        String serverIP = ipTextField.getText();
        btnConectar.setEnabled( !nicknameText.isEmpty() && !serverIP.isEmpty() );
    }//GEN-LAST:event_nicknameTextFieldKeyReleased

    private void nicknameTextFieldKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nicknameTextFieldKeyTyped
        if ( Character.isSpaceChar(evt.getKeyChar()) ) evt.consume();
    }//GEN-LAST:event_nicknameTextFieldKeyTyped

    private void ipTextFieldKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ipTextFieldKeyTyped
        if ( Character.isSpaceChar(evt.getKeyChar()) ) evt.consume();
    }//GEN-LAST:event_ipTextFieldKeyTyped



    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton btnConectar;
    private javax.swing.JButton cancelButton;
    private javax.swing.JPanel dialogPanel;
    private javax.swing.JFormattedTextField ipTextField;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JFormattedTextField nicknameTextField;
    // End of variables declaration//GEN-END:variables
}
