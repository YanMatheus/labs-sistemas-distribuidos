package client.GUI;

import client.connection.SocketController;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.text.BadLocationException;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;

/**
 *
 * @author Yan
 * @author micael
 * @author Victor
 */
public class MainWindow extends javax.swing.JFrame {
    ClientController cc = null;
    long historyLine = 0;

    public MainWindow(ClientController cc) {
        initComponents();
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent we) {
                cc.closeClientSocket();
            }
        });


        this.cc = cc;

        ConnectionDialog connDialog = new ConnectionDialog(this, true);
        connDialog.setVisible(true);

        do {

            try {
                SocketController cs = new SocketController(connDialog.ip, connDialog.port);
                cc.setClientSocket(cs);
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

        jTabbedPane1 = new javax.swing.JTabbedPane();
        tabs_JP = new javax.swing.JPanel();
        btnCalcularDesvio = new javax.swing.JButton();
        tfValoresDesvioPadrao = new javax.swing.JFormattedTextField();
        jLabel1 = new javax.swing.JLabel();
        clearButton = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        historyPanel = new javax.swing.JTextPane();
        jLabel2 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        dirTree = new javax.swing.JTree();
        tfOrigem = new javax.swing.JTextField();
        tfDestino = new javax.swing.JTextField();
        btnBaixar = new javax.swing.JButton();
        lbOrigem = new javax.swing.JLabel();
        lbDestino = new javax.swing.JLabel();
        btnSelecionarOrigem = new javax.swing.JButton();
        btnSelecionarDestino = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(java.awt.Color.darkGray);
        getContentPane().setLayout(new java.awt.GridLayout(1, 0));

        jTabbedPane1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jTabbedPane1.setNextFocusableComponent(tfValoresDesvioPadrao);
        jTabbedPane1.setOpaque(true);

        tabs_JP.setBackground(java.awt.Color.darkGray);

        btnCalcularDesvio.setBackground(new java.awt.Color(0, 230, 118));
        btnCalcularDesvio.setFont(new java.awt.Font("DejaVu Sans", 0, 18)); // NOI18N
        btnCalcularDesvio.setText("Calcular");
        btnCalcularDesvio.setBorder(null);
        btnCalcularDesvio.setEnabled(false);
        btnCalcularDesvio.setNextFocusableComponent(jTabbedPane1);
        btnCalcularDesvio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCalcularDesvioActionPerformed(evt);
            }
        });

        tfValoresDesvioPadrao.setBorder(null);
        tfValoresDesvioPadrao.setToolTipText("Ex: 10 20 30 40");
        tfValoresDesvioPadrao.setName(""); // NOI18N
        tfValoresDesvioPadrao.setNextFocusableComponent(clearButton);
        tfValoresDesvioPadrao.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tfValoresDesvioPadraoKeyTyped(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tfValoresDesvioPadraoKeyReleased(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel1.setForeground(java.awt.Color.lightGray);
        jLabel1.setText("Digite os números separados por espaço:");
        jLabel1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        clearButton.setBackground(new java.awt.Color(0, 230, 118));
        clearButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/client/GUI/drawables/bin.png"))); // NOI18N
        clearButton.setBorderPainted(false);
        clearButton.setIconTextGap(1);
        clearButton.setNextFocusableComponent(btnCalcularDesvio);
        clearButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearButtonActionPerformed(evt);
            }
        });

        jScrollPane2.setBorder(null);

        historyPanel.setEditable(false);
        historyPanel.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jScrollPane2.setViewportView(historyPanel);

        jLabel2.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel2.setForeground(java.awt.Color.lightGray);
        jLabel2.setText("histórico:");
        jLabel2.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        javax.swing.GroupLayout tabs_JPLayout = new javax.swing.GroupLayout(tabs_JP);
        tabs_JP.setLayout(tabs_JPLayout);
        tabs_JPLayout.setHorizontalGroup(
            tabs_JPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tabs_JPLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(tabs_JPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2)
                    .addGroup(tabs_JPLayout.createSequentialGroup()
                        .addGroup(tabs_JPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 680, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 680, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(tabs_JPLayout.createSequentialGroup()
                        .addComponent(tfValoresDesvioPadrao, javax.swing.GroupLayout.PREFERRED_SIZE, 646, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(clearButton, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCalcularDesvio, javax.swing.GroupLayout.DEFAULT_SIZE, 209, Short.MAX_VALUE)))
                .addContainerGap())
        );
        tabs_JPLayout.setVerticalGroup(
            tabs_JPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, tabs_JPLayout.createSequentialGroup()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 332, Short.MAX_VALUE)
                .addGap(28, 28, 28)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(tabs_JPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnCalcularDesvio, javax.swing.GroupLayout.DEFAULT_SIZE, 44, Short.MAX_VALUE)
                    .addComponent(clearButton, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(tfValoresDesvioPadrao))
                .addGap(23, 23, 23))
        );

        tfValoresDesvioPadrao.getAccessibleContext().setAccessibleName("campo de valores");

        jTabbedPane1.addTab("Desvio Padrão", tabs_JP);

        jPanel3.setBackground(java.awt.Color.darkGray);

        javax.swing.tree.DefaultMutableTreeNode treeNode1 = new javax.swing.tree.DefaultMutableTreeNode("server@localhost:4444");
        javax.swing.tree.DefaultMutableTreeNode treeNode2 = new javax.swing.tree.DefaultMutableTreeNode("colors");
        javax.swing.tree.DefaultMutableTreeNode treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("blue");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("violet");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("red");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("yellow");
        treeNode2.add(treeNode3);
        treeNode1.add(treeNode2);
        treeNode2 = new javax.swing.tree.DefaultMutableTreeNode("sports");
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("basketball");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("soccer");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("football");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("hockey");
        treeNode2.add(treeNode3);
        treeNode1.add(treeNode2);
        treeNode2 = new javax.swing.tree.DefaultMutableTreeNode("food");
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("hot dogs");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("pizza");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("ravioli");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("bananas");
        treeNode2.add(treeNode3);
        treeNode1.add(treeNode2);
        dirTree.setModel(new javax.swing.tree.DefaultTreeModel(treeNode1));
        dirTree.setToolTipText("");
        jScrollPane1.setViewportView(dirTree);

        tfOrigem.setName("tf_origem"); // NOI18N
        tfOrigem.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tfOrigemKeyReleased(evt);
            }
        });

        tfDestino.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tfDestinoKeyReleased(evt);
            }
        });

        btnBaixar.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        btnBaixar.setText("Baixar");
        btnBaixar.setEnabled(false);

        lbOrigem.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        lbOrigem.setForeground(java.awt.Color.lightGray);
        lbOrigem.setText("Diretório de origem");

        lbDestino.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        lbDestino.setForeground(java.awt.Color.lightGray);
        lbDestino.setText("Destino");

        btnSelecionarOrigem.setText("Selecionar");
        btnSelecionarOrigem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSelecionarOrigemActionPerformed(evt);
            }
        });
        btnSelecionarOrigem.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnSelecionarOrigemKeyPressed(evt);
            }
        });

        btnSelecionarDestino.setText("Selecionar");
        btnSelecionarDestino.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSelecionarDestinoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 332, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lbDestino)
                            .addComponent(tfOrigem, javax.swing.GroupLayout.DEFAULT_SIZE, 323, Short.MAX_VALUE)
                            .addComponent(tfDestino))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnSelecionarOrigem)
                            .addComponent(btnSelecionarDestino))
                        .addGap(26, 26, 26)
                        .addComponent(btnBaixar, javax.swing.GroupLayout.DEFAULT_SIZE, 127, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(lbOrigem)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 483, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(140, 140, 140)
                .addComponent(lbOrigem)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tfOrigem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnSelecionarOrigem))
                        .addGap(89, 89, 89)
                        .addComponent(lbDestino)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tfDestino, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnSelecionarDestino)))
                    .addComponent(btnBaixar, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Cópia de Diretórios", jPanel3);

        getContentPane().add(jTabbedPane1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void clearButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearButtonActionPerformed
        tfValoresDesvioPadrao.setText("");
    }//GEN-LAST:event_clearButtonActionPerformed

    private void btnCalcularDesvioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCalcularDesvioActionPerformed
        btnCalcularDesvio.setEnabled(false);

        Double resultado = this.cc.calcularDesvioPadrao( tfValoresDesvioPadrao.getText() );
        if (resultado != null) {
            try {
                historyPanel.getDocument().insertString(0,
                    String.format("[%d]= %f\n", ++historyLine, resultado), null);
            } catch (BadLocationException ex) {
                ex.printStackTrace();
            }
        }

        btnCalcularDesvio.setEnabled(true);
    }//GEN-LAST:event_btnCalcularDesvioActionPerformed

    private void tfValoresDesvioPadraoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfValoresDesvioPadraoKeyReleased
        btnCalcularDesvio.setEnabled( !tfValoresDesvioPadrao.getText().trim().isEmpty() );
    }//GEN-LAST:event_tfValoresDesvioPadraoKeyReleased

    private void tfValoresDesvioPadraoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfValoresDesvioPadraoKeyTyped
        char enter = evt.getKeyChar();
        if ( !Character.isDigit(enter)
          && !Character.isSpaceChar(enter)
          && enter != '.' ) evt.consume();
    }//GEN-LAST:event_tfValoresDesvioPadraoKeyTyped

    private void tfOrigemKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfOrigemKeyReleased
        // TODO add your handling code here:
        String origemText  = tfOrigem.getText().trim();
        String destinoText = tfDestino.getText().trim();
        btnBaixar.setEnabled( !origemText.isEmpty() && !destinoText.isEmpty() );
    }//GEN-LAST:event_tfOrigemKeyReleased

    private void tfDestinoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfDestinoKeyReleased
        // TODO add your handling code here:
        String origemText  = tfOrigem.getText().trim();
        String destinoText = tfDestino.getText().trim();
        btnBaixar.setEnabled( !origemText.isEmpty() && !destinoText.isEmpty() );
    }//GEN-LAST:event_tfDestinoKeyReleased

    private void btnSelecionarOrigemKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnSelecionarOrigemKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnSelecionarOrigemKeyPressed

    private String toPath(String s) {

        String str;
        String path = File.separator;

        str = s.replaceAll("[\\[\\]]","").replaceAll("\\s+", "");
        String termo[] = str.split(",");

        for (int i = 0; i < termo.length; ++i)
            path += termo[i] + File.separator;

        return path;
    }

    private void atuarSobreDiretorioSelecionado(TreePath tp) {
        if (tp == null) return;
        TreeNode node = (TreeNode) tp.getLastPathComponent();
        if (!node.isLeaf())
            tfOrigem.setText(toPath(""+tp));
    }

    private void btnSelecionarOrigemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSelecionarOrigemActionPerformed
        // TODO add your handling code here:
        atuarSobreDiretorioSelecionado(dirTree.getSelectionPath());
    }//GEN-LAST:event_btnSelecionarOrigemActionPerformed

    private void btnSelecionarDestinoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSelecionarDestinoActionPerformed
        // TODO add your handling code here:
        JFileChooser chooser;

        chooser = new JFileChooser();
        chooser.setCurrentDirectory(new java.io.File("."));
        chooser.setDialogTitle("Diretório de Destino");
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

        chooser.setAcceptAllFileFilterUsed(false);

        if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            tfDestino.setText("" + chooser.getSelectedFile());
        } else {
            tfDestino.setText("");
        }
    }//GEN-LAST:event_btnSelecionarDestinoActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBaixar;
    private javax.swing.JButton btnCalcularDesvio;
    private javax.swing.JButton btnSelecionarDestino;
    private javax.swing.JButton btnSelecionarOrigem;
    private javax.swing.JButton clearButton;
    private javax.swing.JTree dirTree;
    private javax.swing.JTextPane historyPanel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel lbDestino;
    private javax.swing.JLabel lbOrigem;
    private javax.swing.JPanel tabs_JP;
    private javax.swing.JTextField tfDestino;
    private javax.swing.JTextField tfOrigem;
    private javax.swing.JFormattedTextField tfValoresDesvioPadrao;
    // End of variables declaration//GEN-END:variables
}
