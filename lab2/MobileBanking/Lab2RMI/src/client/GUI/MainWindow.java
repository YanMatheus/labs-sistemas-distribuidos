package client.GUI;

import java.awt.GridLayout;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.concurrent.Callable;
import javax.swing.ImageIcon;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.NumberFormatter;

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

        this.lbNickName.setText(connDialog.nickname);
        connDialog.dispose();
    }


    private void mostrarDialogConfirmação(String titulo, String corpo, Callable acaoOk) {
        JTextField serverHostField = new JTextField(8);
        JPanel panel = new JPanel( new GridLayout(0, 2) );
        NumberFormatter nfUS = new NumberFormatter( NumberFormat.getInstance(Locale.US) );
        JFormattedTextField serverPortField = new JFormattedTextField(nfUS);

        nfUS.setValueClass(Integer.class);
        nfUS.setMinimum(0);
        nfUS.setAllowsInvalid(false);

        /* desenhar o panel */
        serverPortField.setText("0");

        //panel.add( new JLabel(corpo) );
        panel.add( new JLabel("R$") );
        panel.add(serverPortField);

        //
        int selectOpt = JOptionPane.showConfirmDialog(null, panel, titulo, 
                                    JOptionPane.OK_CANCEL_OPTION, 2, 
                                    new ImageIcon("drawable/moneyico.png"));

        //if (selectOpt != JOptionPane.OK_OPTION));

        String serverHost = serverHostField.getText();
        int serverPort = Integer.parseInt(
        serverPortField.getText().replace(",", ""));

        try {
            acaoOk.call();
            // this.clientController = new ClientSocketController(serverHost, serverPort);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null,
                "Não foi possível efetuar a operação",
                "Erro na Operação", JOptionPane.ERROR_MESSAGE);
        }
  }
    
    
  public void setLabelSaldo(Double valor) {
      lbSaldo.setText("R$" + valor.toString());
  }
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jSeparator3 = new javax.swing.JSeparator();
        lbHora = new javax.swing.JLabel();
        lbData = new javax.swing.JLabel();
        lbAtualizacao = new javax.swing.JLabel();
        lbNickName = new javax.swing.JLabel();
        lbLogado = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        lbSaldo = new javax.swing.JLabel();
        btnSacar = new javax.swing.JButton();
        btnDepositar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Mobile Banking");
        setBackground(java.awt.Color.darkGray);
        setPreferredSize(new java.awt.Dimension(320, 350));
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jPanel1.setBackground(java.awt.Color.darkGray);

        lbHora.setFont(new java.awt.Font("Ubuntu", 1, 12)); // NOI18N
        lbHora.setForeground(java.awt.Color.white);
        lbHora.setText("00:00");

        lbData.setFont(new java.awt.Font("Ubuntu", 1, 12)); // NOI18N
        lbData.setForeground(java.awt.Color.white);
        lbData.setText("01/01/1970");

        lbAtualizacao.setFont(new java.awt.Font("Ubuntu", 0, 12)); // NOI18N
        lbAtualizacao.setForeground(java.awt.Color.white);
        lbAtualizacao.setText("Última Atualização:");

        lbNickName.setFont(new java.awt.Font("Ubuntu", 1, 12)); // NOI18N
        lbNickName.setForeground(java.awt.Color.white);
        lbNickName.setText("NickName");

        lbLogado.setFont(new java.awt.Font("Ubuntu", 0, 12)); // NOI18N
        lbLogado.setForeground(java.awt.Color.white);
        lbLogado.setText("Logado como:");

        lbSaldo.setFont(new java.awt.Font("Ubuntu", 1, 36)); // NOI18N
        lbSaldo.setForeground(java.awt.Color.white);
        lbSaldo.setText("R$ 0,00");

        btnSacar.setBackground(new java.awt.Color(0, 230, 118));
        btnSacar.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        btnSacar.setForeground(java.awt.Color.darkGray);
        btnSacar.setText("Sacar");
        btnSacar.setBorder(null);
        btnSacar.setBorderPainted(false);
        btnSacar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnSacar.setPreferredSize(new java.awt.Dimension(180, 60));
        btnSacar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSacarActionPerformed(evt);
            }
        });

        btnDepositar.setBackground(new java.awt.Color(0, 230, 118));
        btnDepositar.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        btnDepositar.setForeground(java.awt.Color.darkGray);
        btnDepositar.setText("Depositar");
        btnDepositar.setBorder(null);
        btnDepositar.setBorderPainted(false);
        btnDepositar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnDepositar.setPreferredSize(new java.awt.Dimension(180, 60));
        btnDepositar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDepositarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(80, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnSacar, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnDepositar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(63, 63, 63))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jSeparator2)
                        .addComponent(jSeparator3, javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(lbLogado)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(lbNickName))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGap(6, 6, 6)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(lbAtualizacao)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(lbData)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(lbHora))))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGap(98, 98, 98)
                            .addComponent(lbSaldo)))
                    .addContainerGap()))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(197, Short.MAX_VALUE)
                .addComponent(btnDepositar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnSacar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lbNickName)
                        .addComponent(lbLogado))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(lbSaldo)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(lbAtualizacao)
                    .addGap(3, 3, 3)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lbData)
                        .addComponent(lbHora))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(173, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        System.exit(0);
    }//GEN-LAST:event_formWindowClosing

    private void btnDepositarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDepositarActionPerformed
        mostrarDialogConfirmação("Realizar Depósito",
                "Digite o valor para depositar", (Callable<Boolean>) () -> {
            return false;
        });
    }//GEN-LAST:event_btnDepositarActionPerformed

    private void btnSacarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSacarActionPerformed
        mostrarDialogConfirmação("Realizar Saque",
                "Digite o valor para sacar", (Callable<Boolean>) () -> {
            return false;
        });
    }//GEN-LAST:event_btnSacarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDepositar;
    private javax.swing.JButton btnSacar;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JLabel lbAtualizacao;
    private javax.swing.JLabel lbData;
    private javax.swing.JLabel lbHora;
    private javax.swing.JLabel lbLogado;
    private javax.swing.JLabel lbNickName;
    private javax.swing.JLabel lbSaldo;
    // End of variables declaration//GEN-END:variables
}
