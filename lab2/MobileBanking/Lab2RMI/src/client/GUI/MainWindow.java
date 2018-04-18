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
                                    new ImageIcon("/home/victor/Área de Trabalho/moneyico.png"));

        if (selectOpt != JOptionPane.OK_OPTION) System.exit(0);

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
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnDepositar = new javax.swing.JButton();
        btnSacar = new javax.swing.JButton();
        lbMoeda = new javax.swing.JLabel();
        lbSaldo = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        lbLogado = new javax.swing.JLabel();
        lbNickName = new javax.swing.JLabel();
        lbAtualizacao = new javax.swing.JLabel();
        lbData = new javax.swing.JLabel();
        lbHora = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();

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

        btnDepositar.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        btnDepositar.setText("Depositar");
        btnDepositar.setPreferredSize(new java.awt.Dimension(180, 60));
        btnDepositar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDepositarActionPerformed(evt);
            }
        });

        btnSacar.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        btnSacar.setText("Sacar");
        btnSacar.setPreferredSize(new java.awt.Dimension(180, 60));
        btnSacar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSacarActionPerformed(evt);
            }
        });

        lbMoeda.setFont(new java.awt.Font("Ubuntu", 1, 36)); // NOI18N
        lbMoeda.setText("R$");

        lbSaldo.setFont(new java.awt.Font("Ubuntu", 1, 36)); // NOI18N
        lbSaldo.setText("0,00");

        lbLogado.setText("Logado como:");

        lbNickName.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        lbNickName.setText("NickName");

        lbAtualizacao.setFont(new java.awt.Font("Ubuntu", 2, 12)); // NOI18N
        lbAtualizacao.setText("Última Atualização:");

        lbData.setFont(new java.awt.Font("Ubuntu", 1, 12)); // NOI18N
        lbData.setText("01/01/1970");

        lbHora.setFont(new java.awt.Font("Ubuntu", 1, 12)); // NOI18N
        lbHora.setText("00:00");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator2)
            .addComponent(jSeparator3, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lbLogado)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lbNickName))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lbAtualizacao)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lbData)
                                .addGap(18, 18, 18)
                                .addComponent(lbHora))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(72, 72, 72)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnSacar, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnDepositar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(90, 90, 90)
                        .addComponent(lbMoeda)
                        .addGap(18, 18, 18)
                        .addComponent(lbSaldo)))
                .addContainerGap(212, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbNickName)
                    .addComponent(lbLogado))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbMoeda)
                    .addComponent(lbSaldo))
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbAtualizacao)
                    .addComponent(lbData)
                    .addComponent(lbHora))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnDepositar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, Short.MAX_VALUE)
                .addComponent(btnSacar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(64, 64, 64))
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
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JLabel lbAtualizacao;
    private javax.swing.JLabel lbData;
    private javax.swing.JLabel lbHora;
    private javax.swing.JLabel lbLogado;
    private javax.swing.JLabel lbMoeda;
    private javax.swing.JLabel lbNickName;
    private javax.swing.JLabel lbSaldo;
    // End of variables declaration//GEN-END:variables
}
