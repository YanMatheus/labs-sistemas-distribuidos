package client.GUI;

import java.awt.GridLayout;
import java.rmi.RemoteException;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.text.NumberFormatter;
import shared.BusyOperationException;

/**
 * Janela inicial após iniciar a conexão.
 *
 * @author Yan
 * @author Micael
 * @author Victor
 */
public class MainWindow extends javax.swing.JFrame {
    private ClientMain cm = null;

    public MainWindow(ClientMain cm) {
        initComponents();
        this.cm = cm;
    }

    public void setLnNickName(String nickname) {
        this.lbNickName.setText("Bem Vindo(a), " + nickname + "!");
    }

    private void mostrarDialogConfirmação(String titulo, CallbackAcaoCritica acaoOk) {
        JPanel panel = new JPanel( new GridLayout(0, 2) );
        NumberFormatter nfUS = new NumberFormatter( NumberFormat.getInstance(Locale.US) );
        JFormattedTextField tfQuantia = new JFormattedTextField(nfUS);
        tfQuantia.setText("0");

        nfUS.setValueClass(Integer.class);
        nfUS.setMinimum(0);
        nfUS.setAllowsInvalid(false);

        panel.add( new JLabel("$") );
        panel.add(tfQuantia);

        java.net.URL imgUrl = getClass().getResource("drawable/moneyico.png");
        int selectOpt = JOptionPane.showConfirmDialog(null, panel, titulo,
                                    JOptionPane.OK_CANCEL_OPTION, 2,
                                    new ImageIcon(imgUrl));

        if (selectOpt != JOptionPane.OK_OPTION) return;

        Double quantia = Double.parseDouble( tfQuantia.getText().replace(",", "") );

        try {
            acaoOk.call(quantia);
        } catch (RemoteException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null,
                "Não foi possível efetuar essa operação",
                "Falha na Operação", JOptionPane.ERROR_MESSAGE);
        } catch (BusyOperationException ex) {
            JOptionPane.showMessageDialog(null,
                "Esta operação está sendo\nrealizada por outro cliente, aguarde.",
                "Operação Ocupada!", JOptionPane.INFORMATION_MESSAGE);
        }
  }

    public void setLabelSaldo(double valor) {
        lbSaldo.setText("$ " + valor);
  }

    private double getValorLabelSaldo() {
        return Double.parseDouble( lbSaldo.getText().substring(2) );
    }

    public void setLabelQuantidadeClientes(int qtdClientes) {
        lbQuantidadeClientes.setText("" + qtdClientes);
    }

    public void atualizarUltimaMovimentacao(String nicknameAutor) {
        Date dNow = new Date();
        SimpleDateFormat ft = new SimpleDateFormat ("dd/MM/yyyy 'às' H:mm:ss");
        lbDataEAutor.setText( ft.format(dNow) + " ~ " + nicknameAutor );
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnDepositar = new javax.swing.JButton();
        btnSacar = new javax.swing.JButton();
        lbSaldo = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        lbNickName = new javax.swing.JLabel();
        lbAtualizacao = new javax.swing.JLabel();
        lbDataEAutor = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        lbQuantidadeClientes = new javax.swing.JLabel();

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
        btnDepositar.setForeground(new java.awt.Color(0, 102, 0));
        btnDepositar.setText("DEPOSITAR");
        btnDepositar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnDepositar.setPreferredSize(new java.awt.Dimension(180, 60));
        btnDepositar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDepositarActionPerformed(evt);
            }
        });

        btnSacar.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        btnSacar.setForeground(new java.awt.Color(102, 0, 0));
        btnSacar.setText("SACAR");
        btnSacar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnSacar.setPreferredSize(new java.awt.Dimension(180, 60));
        btnSacar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSacarActionPerformed(evt);
            }
        });

        lbSaldo.setFont(new java.awt.Font("Ubuntu", 1, 36)); // NOI18N
        lbSaldo.setText("$ 0.0");

        lbNickName.setFont(new java.awt.Font("Ubuntu", 0, 12)); // NOI18N
        lbNickName.setText("Bem Vindo(a). Nickname!");

        lbAtualizacao.setFont(new java.awt.Font("Ubuntu", 0, 12)); // NOI18N
        lbAtualizacao.setText("Última Movimentação:");

        lbDataEAutor.setFont(new java.awt.Font("Ubuntu", 1, 12)); // NOI18N
        lbDataEAutor.setText("<nenhuma>");

        lbQuantidadeClientes.setFont(new java.awt.Font("Ubuntu", 1, 12)); // NOI18N
        lbQuantidadeClientes.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbQuantidadeClientes.setText("0");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator2)
            .addComponent(jSeparator3, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lbNickName)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lbQuantidadeClientes, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(0, 129, Short.MAX_VALUE)
                                .addComponent(btnSacar, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(lbSaldo)))
                        .addGap(8, 8, 8))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbAtualizacao)
                            .addComponent(lbDataEAutor))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnDepositar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbNickName)
                    .addComponent(lbQuantidadeClientes))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbSaldo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lbAtualizacao)
                .addGap(3, 3, 3)
                .addComponent(lbDataEAutor)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnDepositar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnSacar, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        try {
            cm.fecharConexao();
        } catch (RemoteException ex) {
            Logger.getLogger(MainWindow.class.getName())
                  .log(Level.SEVERE, null, ex);
            System.exit(1);
        }
        System.exit(0);
    }//GEN-LAST:event_formWindowClosing

    private void btnDepositarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDepositarActionPerformed
        mostrarDialogConfirmação("Realizar Depósito", new CallbackAcaoCritica() {
            @Override
            public void call(double x) throws RemoteException, BusyOperationException {
                if (x > 0)
                    cm.depositar(x);
            }
        });
    }//GEN-LAST:event_btnDepositarActionPerformed

    private void btnSacarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSacarActionPerformed
        mostrarDialogConfirmação("Realizar Saque", new CallbackAcaoCritica() {
            @Override
            public void call(double x) throws RemoteException, BusyOperationException {
                if (x > 0 && getValorLabelSaldo() >= x)
                    cm.sacar(x);
            }
        });
    }//GEN-LAST:event_btnSacarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDepositar;
    private javax.swing.JButton btnSacar;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JLabel lbAtualizacao;
    private javax.swing.JLabel lbDataEAutor;
    private javax.swing.JLabel lbNickName;
    private javax.swing.JLabel lbQuantidadeClientes;
    private javax.swing.JLabel lbSaldo;
    // End of variables declaration//GEN-END:variables
}
